package com.store.storeInfo.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.exception.AOSException;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.store.record.dao.ZjcIncomeFlowDao;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.goods.dao.po.ZjcGoodsCategoryPO;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcExchangeOrderDao;
import com.zjc.order.dao.po.ZjcExchangeOrderPO;
import com.zjc.region.dao.ZjcRegionDao;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.ZjcStoreDeputeDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStoreDeputePO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 店铺基本信息管理服务层
 * 
 * @author wgm
 *
 */
@Service(value="storeInfoService")
public class StoreInfoService {
	
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired
	private ZjcRegionDao zjcRegionDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ZjcUserLogDao zjcUserLogDao;
	@Autowired
	private ZjcStoreDeputeDao zjcStoreDeputeDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcIncomeFlowDao zjcIncomeFlowDao;
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	@Autowired
	private ZjcExchangeOrderDao zjcExchangeOrderDao;
	@Autowired
	private ApiLogService apiLogService;
	/**
	 * @param httpModel
	 * @return
	 */
	public Map<String, Object> getStoreDetailByStoreId(HttpModel httpModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		Dto inDto = httpModel.getInDto();
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		map.put("zjcStorePO", zjcStorePO);
		map.put("real_name", sellerInfoPO.getReal_name());
		//初始化查询省信息
		List<ZjcRegionPO> provinceList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getProvince", inDto);
		map.put("provinceList", provinceList);
		if(!AOSUtils.isEmpty(zjcStorePO)){
			//初始化查询市、区信息
			inDto.put("parent_id", zjcStorePO.getProvince_id());
			List<ZjcRegionPO> cityList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getAddr", inDto);
			map.put("cityList", cityList);
			//初始化查询市、区信息
			inDto.put("parent_id", zjcStorePO.getCity_id());
			List<ZjcRegionPO> areaList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getAddr", inDto);
			map.put("areaList", areaList);
		}
		
		List<ZjcGoodsCategoryPO> categorys = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.listGoodsCategoryComboBoxDataByParam", Dtos.newDto("parent_id", 0));
		map.put("categorys", categorys);
		return map;
	}
	
	/**
	 * 查询省信息  联动一级查询
	 * 
	 * @param httpModel
	 */
	public List<ZjcRegionPO> getProvince(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcRegionPO> provinceList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getProvince", qDto);
		return provinceList;
	}
	
	/**
	 * 查询市区街道信息   联动查询
	 * 
	 * @param httpModel
	 */
	public List<ZjcRegionPO> findAddr(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcRegionPO> addrList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getAddr", qDto);
		return addrList;
	}
	
	/**
	 * 通过主键id查询地址信息
	 * 
	 * @param httpModel
	 */
	public ZjcRegionPO getRegion(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		ZjcRegionPO zjcRegionPO = zjcRegionDao.selectByKey(inDto.getInteger("id"));
		return zjcRegionPO;
	}
	
	public boolean updateStore(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcStorePO zjcStorePO = new ZjcStorePO();
		zjcStorePO.copyProperties(inDto);
		int row = zjcStoreDao.updateByKey(zjcStorePO);
		if(row == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public MessageVO real_name(HttpModel httpModel){
		MessageVO msg = new MessageVO();
		Dto inDto = httpModel.getInDto();
		ZjcSellerInfoPO sellerInfo = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		inDto.put("mobile", sellerInfo.getMobile());
		ZjcUsersInfoPO zjcUsersPO = zjcUsersInfoDao.selectByMobile(inDto);
		if(!zjcUsersPO.getReal_name().equals(inDto.getString("real_name"))){
			msg.setCode(Apiconstant.ID_NAME_UN_MACTH.getIndex());
			msg.setMsg(Apiconstant.ID_NAME_UN_MACTH.getName());
		}else{
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		}
		return msg;
	}
	
	public MessageVO mobile(HttpModel httpModel){
		MessageVO msg = new MessageVO();
		Dto dto = httpModel.getInDto();
		ZjcSellerInfoPO sellerInfo = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("mobile", sellerInfo.getMobile());
		ZjcUsersInfoPO zjcUsersPO = zjcUsersInfoDao.selectByMobile(dto);
		if(!zjcUsersPO.getReal_name().equals(dto.getString("real_name")) || !zjcUsersPO.getMobile().equals(dto.getString("mobile"))){
			msg.setCode(Apiconstant.ID_NAME_MOBILE_UN_MACTH.getIndex());
			msg.setMsg(Apiconstant.ID_NAME_MOBILE_UN_MACTH.getName());
		}else{
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		}
		return msg;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public MessageVO transfer(HttpModel httpModel) throws ParseException{
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		//店主的账号信息
		ZjcUsersAccountInfoPO sellerAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(sellerInfoPO.getUser_id());
		//转账对象账号信息
		ZjcUsersInfoPO usersInfoPO = zjcUsersInfoDao.selectByMobile(Dtos.newDto("mobile",sellerInfoPO.getMobile()));
		if(AOSUtils.isEmpty(usersInfoPO)){
			msg.setCode(Apiconstant.Account_Not_Exist.getIndex());
			msg.setMsg("您输入的手机号还没有对应的会员号，请点击页面顶部的“开通商家会员号”按钮，生成对应的会员号再尝试转账，谢谢。");
			return msg;
		}
		ZjcUsersAccountInfoPO usersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(usersInfoPO.getUser_id());
		if(AOSUtils.isEmpty(usersAccountInfoPO)){
			msg.setCode(Apiconstant.ACCOUNT_EXCEPTION.getIndex());
			msg.setMsg(Apiconstant.ACCOUNT_EXCEPTION.getName());
			return msg;
		}
		if(dto.getInteger("pay_points") < 0){
			msg.setCode(Apiconstant.Points_Was_Wrong.getIndex());
			msg.setMsg(Apiconstant.Points_Was_Wrong.getName());
			return msg;
		}
		if(dto.getInteger("pay_points") > sellerAccountInfoPO.getMake_over_integral()){//余额是否充足
			msg.setCode(Apiconstant.Account_balance_insufficient.getIndex());
			msg.setMsg(Apiconstant.Account_balance_insufficient.getName());
		}/*else if(AOSUtils.isEmpty(usersInfoPO.getReal_name()) || !usersInfoPO.getReal_name().equals(dto.getString("real_name"))){
			msg.setCode(Apiconstant.ID_NAME_UN_MACTH.getIndex());
			msg.setMsg(Apiconstant.ID_NAME_UN_MACTH.getName());
		}else if(!sellerInfoPO.getMobile().equals(dto.getString("mobile"))){
			msg.setCode(Apiconstant.ID_NAME_MOBILE_UN_MACTH.getIndex());
			msg.setMsg(Apiconstant.ID_NAME_MOBILE_UN_MACTH.getName());
		}*/else{
			try{
				//减去店主的易物劵
				int make_over_integral = sellerAccountInfoPO.getMake_over_integral() - dto.getInteger("pay_points");
				sellerAccountInfoPO.setMake_over_integral(make_over_integral);
				zjcUsersAccountInfoDao.updateByKey(sellerAccountInfoPO);
				//用户加钱
				int add = dto.getInteger("pay_points") + usersAccountInfoPO.getMake_over_integral();
				usersAccountInfoPO.setMake_over_integral(add);
				zjcUsersAccountInfoDao.updateByKey(usersAccountInfoPO);
				//生成操作流水
				ZjcIncomeFlowPO zjcIncomeFlowPO = new ZjcIncomeFlowPO();
				ZjcUsersAccountInfoPO newSellerAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(sellerInfoPO.getUser_id());
				zjcIncomeFlowPO.setBalance(newSellerAccountInfoPO.getMake_over_integral());
				zjcIncomeFlowPO.setExchange_time(new Date());
				zjcIncomeFlowPO.setExpend(dto.getInteger("pay_points"));
				zjcIncomeFlowPO.setIn_user_id(usersInfoPO.getUser_id().intValue());
				zjcIncomeFlowPO.setOut_user_id(Integer.valueOf(sellerAccountInfoPO.getUser_id().toString()));
				zjcIncomeFlowPO.setType("1");
				zjcIncomeFlowPO.setOrder_sn(ParameterUtil.getOrderSn());
				zjcIncomeFlowDao.insert(zjcIncomeFlowPO);
				//生成转账记录
				ZjcExchangeOrderPO exchangeOrder = new ZjcExchangeOrderPO();
				exchangeOrder.setEx_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				exchangeOrder.setEx_sn(ParameterUtil.getOrderSn());//订单编号
				exchangeOrder.setUser_id(usersAccountInfoPO.getUser_id());
				exchangeOrder.setUser_name(usersInfoPO.getReal_name());
				exchangeOrder.setSeller_id(Integer.valueOf(sellerAccountInfoPO.getUser_id().toString()));
				exchangeOrder.setSeller_name(sellerInfoPO.getReal_name());
				exchangeOrder.setEx_price(dto.getBigDecimal("pay_points"));
				exchangeOrder.setMobile(usersInfoPO.getMobile());
				exchangeOrder.setEx_fee(new BigDecimal(0));
				exchangeOrder.setStatus(1);
				exchangeOrder.setAdd_time(new Date());
				exchangeOrder.setType(1);
				exchangeOrder.setNote("商家后台向自己账号转账");
				zjcExchangeOrderDao.insert(exchangeOrder);
				//生成操作商家转出日志
				Map<String, Object> sellerLog = new HashMap<>();
				sellerLog.put("user_id", sellerInfoPO.getUser_id());
				sellerLog.put("to_user_id", usersInfoPO.getUser_id());
				sellerLog.put("make_over_integral", dto.getInteger("pay_points"));
				sellerLog.put("now_make_over_integral", sellerAccountInfoPO.getMake_over_integral());
				sellerLog.put("logType", "转账");
				sellerLog.put("type", "商家转会员");
				apiLogService.saveLog(sellerLog);
					//生成操作商家转入日志
				Map<String, Object> userLog = new HashMap<>();
				userLog.put("user_id", usersInfoPO.getUser_id());
				userLog.put("to_user_id", sellerInfoPO.getUser_id());
				userLog.put("make_over_integral", dto.getInteger("pay_points"));
				userLog.put("now_make_over_integral", usersAccountInfoPO.getMake_over_integral());
				userLog.put("logType", "转账");
				userLog.put("type", "会员收商家");
				userLog.put("show_type", 1);
				apiLogService.saveLog(userLog);
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg("转账成功，请及时登录APP查看。");
			}catch(Exception e){
					throw new AOSException("易物劵转账失败");
			}
		}
		return msg;
	}
	
	public PageVO serachLog(HttpModel httpModel){
		//获取查询参数
		Dto dto=httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("user_id", sellerInfoPO.getUser_id());
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.serachLogPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(goodslist);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	//确认委托申请
	public MessageVO confirm_trust(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcStoreDeputePO storeDeputePO = new ZjcStoreDeputePO();
		storeDeputePO.copyProperties(dto);
		//商家信息
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("mobile", sellerInfoPO.getMobile());
		storeDeputePO.setUser_id(sellerInfoPO.getUser_id().intValue());
		//商家会员信息
		ZjcUsersInfoPO usersInfoPO = zjcUsersInfoDao.selectByMobile(dto);
		if(AOSUtils.isEmpty(usersInfoPO)){
			msg.setCode(Apiconstant.Account_Not_Exist.getIndex());
			msg.setMsg("您还未开通商家会员号，请点击页面顶部的“开通商家会员号”按钮，开通商家会员号后再填写申请");
			return msg;
		}
		ZjcStorePO storePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		/*if(!storeDeputePO.getReal_name().equals(usersInfoPO.getReal_name())){
			msg.setMsg("请使用店主自己的真实姓名！");
		}else if(!storeDeputePO.getId_card().equals(usersInfoPO.getId_card())){
			msg.setMsg("请使用店主自己的身份证号！");
		}else if(!storeDeputePO.getContract_mobile().equals(usersInfoPO.getMobile())){
			msg.setMsg("请使用店主注册电话！");
		}else if(!storePO.getStore_name().equals(storeDeputePO.getStore_name())){
			msg.setMsg("请输入完整正确的店铺名称！");
		}else if(!storePO.getUser_id().toString().equals(storeDeputePO.getUser_id().toString())){
			msg.setMsg("请输入正确的企业ID！");
		}else{*/
			storeDeputePO.setAdd_time(new Date());
			storeDeputePO.setState("1");
			//storeDeputePO.setUser_id(Integer.valueOf(sellerInfoPO.getUser_id().toString()));
			storeDeputePO.setStore_id(storePO.getStore_id());
			//检查用户是否是第一次申请
			List<ZjcStoreDeputePO> oldStoreDeputePOList = zjcStoreDeputeDao.list(Dtos.newDto("store_id", sellerInfoPO.getStore_id()));
			int row = 0;
			if(oldStoreDeputePOList.size() == 0){//该店铺已经申请过一次
				row = zjcStoreDeputeDao.insert(storeDeputePO);
			}else{
				row = sqlDao.update("com.zjc.store.dao.ZjcStoreDeputeDao.updateByStoreId", storeDeputePO);
			}
			if(row == 1){
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg(Apiconstant.Do_Success.getName());
			}else{
				msg.setCode(Apiconstant.Do_Fails.getIndex());
				msg.setMsg(Apiconstant.Do_Fails.getName());
			}
		//}	
		return msg;
	}
	
	/**
	 * 查询数据
	 * @param httpModel
	 * @return
	 */
	public ZjcStoreDeputePO deputeDetial(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		ZjcStoreDeputePO deputePO = zjcStoreDeputeDao.deputeDetial(dto); 
		return deputePO;
	}
	
	public PageVO wait_pay_order(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.waitPayOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO wait_delivery_order(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.waitDeliveryOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO wait_comment_order(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.waitCommentOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO finish_order(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.waitFinishOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO sale_goods(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.saleGoodsPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO stock_goods(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.stockGoodsPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO unsale_goods(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.unsaleGoodsPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public PageVO sells_hot_goods(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.sellsHotGoodsPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public MessageVO create_user_account(HttpModel httpModel){
		MessageVO msg = new MessageVO();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		ZjcUsersInfoPO oldUsersInfoPO = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectByMobile", sellerInfoPO);
		if(AOSUtils.isNotEmpty(oldUsersInfoPO)){
			msg.setCode(Apiconstant.Username_Has_Existed.getIndex());
			msg.setMsg("您已存在商家会员号，请勿重复开通。");
			return msg;
		}
		BigInteger user_id = new BigInteger(idService.nextValue(SystemCons.SEQ.SEQ_USER).longValue()+"");
		//生成会员信息
		ZjcUsersInfoPO newUsersInfoPO = new ZjcUsersInfoPO();
		newUsersInfoPO.copyProperties(sellerInfoPO);
		newUsersInfoPO.setFirst_leader(29999);
		newUsersInfoPO.setUser_id(user_id);
		newUsersInfoPO.setReal_name(sellerInfoPO.getReal_name());
		//生成会员账户信息
		ZjcUsersAccountInfoPO newUsersAccountInfoPO = new ZjcUsersAccountInfoPO();
		newUsersAccountInfoPO.copyProperties(newUsersInfoPO);
		newUsersAccountInfoPO.setUser_money(new BigDecimal(0.0));
		newUsersAccountInfoPO.setFrozen_money(new BigDecimal(0.0));
		newUsersAccountInfoPO.setDistribut_money(new BigDecimal(0.0));
		newUsersAccountInfoPO.setWallet_quota("0");
		newUsersAccountInfoPO.setSettlement_center(0);
		newUsersAccountInfoPO.setReg_giving_points("0");
		newUsersAccountInfoPO.setReturn_points("0");
		newUsersAccountInfoPO.setDue_tc_points("0");
		newUsersAccountInfoPO.setPractical_tc_points("0");
		newUsersAccountInfoPO.setCount_wallet_quota(0);
		newUsersAccountInfoPO.setHas_terminal(0);
		newUsersAccountInfoPO.setSettlement_center_tc(0);
		newUsersAccountInfoPO.setSqamount(0);
		newUsersAccountInfoPO.setSqpay(0);
		newUsersAccountInfoPO.setSqvip(0);
		newUsersAccountInfoPO.setSettlement_sum(0);
		newUsersAccountInfoPO.setJf_sum(0);
		newUsersAccountInfoPO.setSum(0);
		newUsersAccountInfoPO.setPending_transfer(new BigDecimal(0.0));
		newUsersAccountInfoPO.setTransferred(new BigDecimal(0.0));
		newUsersAccountInfoPO.setTotal_amount("0");
		newUsersAccountInfoPO.setMake_over_integral(0);
		newUsersAccountInfoPO.setPay_points(0);
		//插入数据
		try {
			sqlDao.insert("com.zjc.users.dao.ZjcUsersAccountInfoDao.insertAll", newUsersAccountInfoPO);
			sqlDao.insert("com.zjc.users.dao.ZjcUsersInfoDao.insertAll", newUsersInfoPO);
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg("开通会员号成功，请尽快登录APP，账号初始密码和商家后台密码一致。");
		} catch (Exception e) {
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg("开通失败，请稍候再试或者联系客服。");
		}
		return msg;
	}
}
