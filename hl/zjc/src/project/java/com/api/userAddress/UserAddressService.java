package com.api.userAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.region.dao.ZjcRegionDao;
import com.zjc.users.dao.ZjcUserAddressDao;
import com.zjc.users.dao.po.ZjcUserAddressPO;

/**
 * @author wgm
 * 
 *app接口-收货地址管理service
 */
@Service(value="userAddressService")
public class UserAddressService {
	
	@Autowired
	private ZjcUserAddressDao zjcUserAddressDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcRegionDao zjcRegionDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private ApiLogService apiLogService;
	/**
	 * app接口-获取我的收货地址列表
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getUserAddress(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("token"))){
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		dto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){//当前页数不能为空
			msgVo.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			//查询我的收获地址列表
			List<ZjcUserAddressPO> addressList = zjcUserAddressDao.listPage(dto);
			if(addressList.size() > 0){
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(addressList);
				msgVo.setData(pageVO);
			} else {
				msgVo.setCode(Apiconstant.NO_DATA.getIndex());
				msgVo.setMsg(Apiconstant.NO_DATA.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * app接口-修改默认收货地址
	 * 
	 * @param httpModel
	 * @return
	 */
	public String updateDefaultAddress(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("token")) || AOSUtils.isEmpty(dto.getString("address_id"))){
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		dto.put("user_id", httpModel.getAttribute("user_id"));
		//通过address_id查询收货地址
		ZjcUserAddressPO newDefaultAddress = zjcUserAddressDao.selectByKey(dto.getInteger("address_id"));
		if(AOSUtils.isEmpty(newDefaultAddress)){//收货地址不存在
			msgVo.setCode(Apiconstant.Address_Not_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//收货地址存在
			dto.put("is_default", 1);//设置默认收货地址条件
			dto.put("address_id", null);//设置收货地址ID为空
			//查询原默认收货地址
			List<ZjcUserAddressPO> addressList = zjcUserAddressDao.list(dto);
			if(AOSUtils.isEmpty(addressList)){//判断原默认地址是否存在,不存在则把当前地址设为默认
				newDefaultAddress.setIs_default(1);//修改为默认
				int newSuccessNum = zjcUserAddressDao.updateByKey(newDefaultAddress);
				if(newSuccessNum == 0){//新默认地址修改不成功
					msgVo.setCode(Apiconstant.Save_fails.getIndex());
					msgVo.setMsg(Apiconstant.Save_fails.getName());
				} else {//修改成功
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());
				}
			} else {//原默认地址存在
				if(addressList.size()>1){//原数据不正确
					msgVo.setCode(Apiconstant.Data_Is_Wrong.getIndex());
					msgVo.setMsg(Apiconstant.Data_Is_Wrong.getName());
				} else {
					ZjcUserAddressPO oldDefaultAddress = new ZjcUserAddressPO();
					//获取原默认地址
					oldDefaultAddress = addressList.get(0);
					oldDefaultAddress.setIs_default(0);//修改为不默认
					int successNum = zjcUserAddressDao.updateByKey(oldDefaultAddress);
					if(successNum == 0){//原默认地址修改不成功
						msgVo.setCode(Apiconstant.Save_fails.getIndex());
						msgVo.setMsg(Apiconstant.Save_fails.getName());
					} else {//修改新默认地址
						newDefaultAddress.setIs_default(1);//修改为默认
						int newSuccessNum = zjcUserAddressDao.updateByKey(newDefaultAddress);
						if(newSuccessNum == 0){//新默认地址修改不成功
							msgVo.setCode(Apiconstant.Save_fails.getIndex());
							msgVo.setMsg(Apiconstant.Save_fails.getName());
							oldDefaultAddress.setIs_default(1);//修改为默认
							//新默认地址修改不成功，改回原默认地址
							zjcUserAddressDao.updateByKey(oldDefaultAddress);
						} else {//修改成功
							msgVo.setCode(Apiconstant.Do_Success.getIndex());
							msgVo.setMsg(Apiconstant.Do_Success.getName());
						}
					}
				}
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * app接口-新增收货地址
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String saveAddress(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		//获取参数
		String address_id = dto.getString("address_id");//地址ID
		String consignee = dto.getString("consignee");//收货人
		String mobile = dto.getString("mobile");//联系电话
		String province = dto.getString("province");//省
		String city = dto.getString("city");//市
		String district = dto.getString("district");//区
		String address = dto.getString("address");//详细地址
		if(AOSUtils.isEmpty(dto.getString("token")) || AOSUtils.isEmpty(mobile)){
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		if(AOSUtils.isEmpty(consignee)){//收货人不能为空
			msgVo.setMsg(Apiconstant.Consignee_Is_Null.getName());
			msgVo.setCode(Apiconstant.Consignee_Is_Null.getIndex());
		} /*else if(!ValidateUtil.isRightPhone(mobile)){//手机号码错误
			msgVo.setMsg(Apiconstant.Phone_Is_Wrong.getName());
		}*/ else if(AOSUtils.isEmpty(province)||AOSUtils.isEmpty(city)||AOSUtils.isEmpty(district)){//省市区选择不完整
			msgVo.setMsg(Apiconstant.ProviceCityDistrict_Is_Wrong.getName());
			msgVo.setCode(Apiconstant.ProviceCityDistrict_Is_Wrong.getIndex());
		} else if(AOSUtils.isEmpty(address)){//详细地址不能为空
			msgVo.setMsg(Apiconstant.Address_Is_Null.getName());
			msgVo.setCode(Apiconstant.Address_Is_Null.getIndex());
		} else {//条件判断成功
			//获取省名字
			String province_name = apiPublicService.getAddressName(dto.getInteger("province"));
			//获取市名字
			String city_name = apiPublicService.getAddressName(dto.getInteger("city"));
			//获取区名字
			String district_name = apiPublicService.getAddressName(dto.getInteger("district"));
			//获取区名字
			String twon_name = "";
			if(!AOSUtils.isEmpty(dto.getString("twon"))){//街道ID不为空
				twon_name = apiPublicService.getAddressName(dto.getInteger("twon"));
			}
			
			if(AOSUtils.isEmpty(address_id)){//地址ID为空，新增
				//查询原收货地址列表数据
				List<ZjcUserAddressPO> addressList = sqlDao.list("com.zjc.users.dao.ZjcUserAddressDao.listAddress", dto);
				if(addressList.size()>=10){//最多只能添加10个收货地址
					msgVo.setMsg(Apiconstant.Address_Was_Passed.getName());
					msgVo.setCode(Apiconstant.Address_Was_Passed.getIndex());
				} else {
					ZjcUserAddressPO addressPO = new ZjcUserAddressPO();
					addressPO.setAddress_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					addressPO.setUser_id(dto.getBigInteger("user_id"));
					addressPO.setConsignee(consignee);
					addressPO.setProvince(dto.getInteger("province"));
					addressPO.setCity(dto.getInteger("city"));
					addressPO.setDistrict(dto.getInteger("district"));
					addressPO.setTwon(dto.getInteger("twon"));
					addressPO.setArea_info(province_name + city_name + district_name);//省市区
					addressPO.setTwon_name(twon_name);
					addressPO.setAddress(address);
					addressPO.setAddress_info(province_name + city_name + district_name + address);//完整地址
					addressPO.setMobile(mobile);
					addressPO.setIs_default(0);//不默认为收货地址
					addressPO.setIs_pickup(0);
					try{
						zjcUserAddressDao.insert(addressPO);
							//添加用户日志
							Map<String,Object> map = new HashMap<String,Object>();
							map.put("logType", "收货地址");
							map.put("user_id", dto.getBigInteger("user_id"));
							map.put("do_type", "新增");
							map.put("consignee", consignee);
							map.put("mobile", mobile);
							map.put("address", address);
							apiLogService.saveLog(map);
							msgVo.setCode(Apiconstant.Do_Success.getIndex());
							msgVo.setMsg(Apiconstant.Do_Success.getName());
					} catch(Exception e){
						e.printStackTrace();
						msgVo.setCode(Apiconstant.Save_fails.getIndex());
						msgVo.setMsg(Apiconstant.Save_fails.getName());
					}
				}
			} else {//编辑
				ZjcUserAddressPO addressPO = zjcUserAddressDao.selectByKey(dto.getInteger("address_id"));
				if(AOSUtils.isEmpty(addressPO)){//该收货地址不存在
					msgVo.setCode(Apiconstant.Address_Not_Exist.getIndex());
					msgVo.setMsg(Apiconstant.Address_Not_Exist.getName());
				} else {
					addressPO.setConsignee(consignee);
					addressPO.setProvince(dto.getInteger("province"));
					addressPO.setCity(dto.getInteger("city"));
					addressPO.setDistrict(dto.getInteger("district"));
					addressPO.setTwon(dto.getInteger("twon"));
					addressPO.setArea_info(province_name + city_name + district_name);//省市区
					addressPO.setTwon_name(twon_name);
					addressPO.setAddress(address);
					addressPO.setAddress_info(province_name + city_name + district_name + address);//完整地址
					addressPO.setMobile(mobile);
					try{
						zjcUserAddressDao.updateByKey(addressPO);
						//添加用户日志
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("logType", "修改地址");
						map.put("user_id", dto.getBigInteger("user_id"));
						apiLogService.saveLog(map);
						msgVo.setMsg(Apiconstant.Do_Success.getName());
						msgVo.setCode(Apiconstant.Do_Success.getIndex());
					} catch(Exception e){
						e.printStackTrace();
						msgVo.setCode(Apiconstant.Save_fails.getIndex());
						msgVo.setMsg(Apiconstant.Save_fails.getName());
					}
				}
			}
		}
		return AOSJson.toJson(msgVo);
	}
	

	/**
	 * app接口-删除收货地址
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String delAddress(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("address_id")) || AOSUtils.isEmpty(dto.getString("token"))){
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		dto.put("user_id", httpModel.getAttribute("user_id"));
		//查询选中的收获地址
		ZjcUserAddressPO addressPO = zjcUserAddressDao.selectOne(dto);
		if(AOSUtils.isEmpty(addressPO)){//收货地址不存在
			msgVo.setMsg(Apiconstant.Address_Not_Exist.getName());
			msgVo.setCode(Apiconstant.Address_Not_Exist.getIndex());
		} else {//收货地址不为空
			try{
				zjcUserAddressDao.deleteByKey(dto.getInteger("address_id"));
				dto.put("is_default", 1);//设置默认收货地址条件
				dto.put("address_id", null);//设置收货地址ID为空
				//查询原默认收货地址
				List<ZjcUserAddressPO> addressList = zjcUserAddressDao.list(dto);
				if(AOSUtils.isEmpty(addressList)){//判断原默认地址是否存在：不存在，设置第一个收货地址为默认地址
					dto.put("is_default", null);
					//查询我的地址列表
					addressList = sqlDao.list("com.zjc.users.dao.ZjcUserAddressDao.listAddress", dto);
					if(AOSUtils.isEmpty(addressList)){//判断我的收获地址是否存在:不存在
						msgVo.setCode(Apiconstant.Do_Success.getIndex());
						msgVo.setMsg(Apiconstant.Do_Success.getName());
					} else {//设置第一个地址为默认收货地址
						ZjcUserAddressPO defaultAddress = addressList.get(0);
						defaultAddress.setIs_default(1);
						zjcUserAddressDao.updateByKey(defaultAddress);
						msgVo.setCode(Apiconstant.Do_Success.getIndex());
						msgVo.setMsg(Apiconstant.Do_Success.getName());
					}
				} else {//默认地址存在
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());
				}
			} catch(Exception e){
				e.printStackTrace();
				msgVo.setCode(Apiconstant.Do_Fails.getIndex());
				msgVo.setMsg(Apiconstant.Do_Fails.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * 查询用户默认收货地址
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getUserDefaultAddress(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("token"))){
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		dto.put("user_id", httpModel.getAttribute("user_id"));
		dto.put("is_default", 1);//设置默认收货地址条件
		//查询我的收获地址列表
		ZjcUserAddressPO defaultAddr = zjcUserAddressDao.selectOne(dto);
		if(AOSUtils.isNotEmpty(defaultAddr)){
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(defaultAddr);
		} else {
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
		}
		return AOSJson.toJson(msgVo);
	}
}
