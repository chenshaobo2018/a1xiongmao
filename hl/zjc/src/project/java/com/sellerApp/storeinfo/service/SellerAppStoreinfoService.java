/**
 * 
 */
package com.sellerApp.storeinfo.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.exception.AOSException;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
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
import com.sellerApp.common.MonthUtil;
import com.store.record.dao.ZjcIncomeFlowDao;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.goods.dao.po.ZjcGoodsCategoryPO;
import com.zjc.order.dao.ZjcExchangeOrderDao;
import com.zjc.order.dao.po.ZjcExchangeOrderPO;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDeputeDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStoreDeputePO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;
@Service(value = "sellerAppStoreinfoService")
public class SellerAppStoreinfoService{
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ZjcIncomeFlowDao zjcIncomeFlowDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ApiLogService apiLogService;
	@Autowired
	private ZjcExchangeOrderDao zjcExchangeOrderDao;
	@Autowired
	private ZjcStoreDeputeDao zjcStoreDeputeDao;
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	/**
	 * 店铺详情
	 * 
	 * @param inDto
	 * @return
	 */
	public String getStoreinfo(Dto inDto) {
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(inDto.getString("store_id"))){
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg); 
		}
		Dto outDto = sqlDao.selectDto("com.zjc.store.dao.ZjcStoreDao.selectOne", inDto);
		ZjcRegionPO region = (ZjcRegionPO) sqlDao.selectOne("com.zjc.region.dao.ZjcRegionDao.selectOne", Dtos.newDto("id", outDto.getInteger("province_id")));
		outDto.put("province_name", region.getName());//获取省名字
		region = (ZjcRegionPO) sqlDao.selectOne("com.zjc.region.dao.ZjcRegionDao.selectOne", Dtos.newDto("id", outDto.getInteger("city_id")));
		outDto.put("city_name", region.getName());//获取市名字
		region = (ZjcRegionPO) sqlDao.selectOne("com.zjc.region.dao.ZjcRegionDao.selectOne", Dtos.newDto("id", outDto.getInteger("area_id")));
		outDto.put("area_name", region.getName());//获取区名字
		ZjcGoodsCategoryPO categoryPo = (ZjcGoodsCategoryPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsCategoryDao.selectOne", Dtos.newDto("id", outDto.getInteger("cat_id")));
		if(AOSUtils.isEmpty(categoryPo)){
			outDto.put("cat_name", "暂无分类");//获取店铺分类名词 
		} else {
			outDto.put("cat_name", categoryPo.getName());//获取店铺分类名词 
		}
		msg.setCode(Apiconstant.Do_Success.getIndex());
		msg.setMsg(Apiconstant.Do_Success.getName());
		msg.setData(outDto);
		return AOSJson.toJson(msg);
	}

	/**
	 * 转账到会员号
	 * 
	 * @param inDto
	 * @return
	 */
	public String transfer(Dto inDto) {
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(inDto.getInteger("pay_points"))||AOSUtils.isEmpty(inDto.getInteger("user_id"))){//参数错误
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg);
		}
		
		if(inDto.getInteger("pay_points") < 0 ){
			msg.setCode(Apiconstant.Points_Was_Wrong.getIndex());
			msg.setMsg(Apiconstant.Points_Was_Wrong.getName());
			return AOSJson.toJson(msg);
		}
		
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) sqlDao.selectOne("com.zjc.store.dao.ZjcSellerInfoDao.selectByKey", inDto);
		//店主的账号信息
		ZjcUsersAccountInfoPO sellerAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(sellerInfoPO.getUser_id());
		//转账对象账号信息
		ZjcUsersInfoPO usersInfoPO = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("mobile", sellerInfoPO.getMobile()));
		if(AOSUtils.isEmpty(usersInfoPO)){
			msg.setCode(Apiconstant.Account_Not_Exist.getIndex());
			msg.setMsg("您输入的手机号还没有对应的会员号，请先到https://zjc1518.com/aosuite/生成对应的会员号再尝试转账，O(∩_∩)O谢谢。");
			return AOSJson.toJson(msg);
		}
		ZjcUsersAccountInfoPO usersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(usersInfoPO.getUser_id());
		if(AOSUtils.isEmpty(usersAccountInfoPO)){
			msg.setCode(Apiconstant.ACCOUNT_EXCEPTION.getIndex());
			msg.setMsg(Apiconstant.ACCOUNT_EXCEPTION.getName());
			return AOSJson.toJson(msg);
		}
		if(inDto.getInteger("pay_points") > sellerAccountInfoPO.getMake_over_integral()){//余额是否充足
			msg.setCode(Apiconstant.Account_balance_insufficient.getIndex());
			msg.setMsg(Apiconstant.Account_balance_insufficient.getName());
		}/*else if(AOSUtils.isEmpty(usersInfoPO.getReal_name()) || !usersInfoPO.getReal_name().equals(inDto.getString("real_name"))){
			msg.setCode(Apiconstant.ID_NAME_UN_MACTH.getIndex());
			msg.setMsg(Apiconstant.ID_NAME_UN_MACTH.getName());
		}else if(!sellerInfoPO.getMobile().equals(inDto.getString("mobile"))){
			msg.setCode(Apiconstant.ID_NAME_MOBILE_UN_MACTH.getIndex());
			msg.setMsg(Apiconstant.ID_NAME_MOBILE_UN_MACTH.getName());
		}*/else{
			try{
				//减去店主的易物劵
				int make_over_integral = sellerAccountInfoPO.getMake_over_integral() - inDto.getInteger("pay_points");
				sellerAccountInfoPO.setMake_over_integral(make_over_integral);
				zjcUsersAccountInfoDao.updateByKey(sellerAccountInfoPO);
				//用户加钱
				int add = inDto.getInteger("pay_points") + usersAccountInfoPO.getMake_over_integral();
				usersAccountInfoPO.setMake_over_integral(add);
				zjcUsersAccountInfoDao.updateByKey(usersAccountInfoPO);
				//生成操作流水
				ZjcIncomeFlowPO zjcIncomeFlowPO = new ZjcIncomeFlowPO();
				ZjcUsersAccountInfoPO newSellerAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(sellerInfoPO.getUser_id());
				zjcIncomeFlowPO.setBalance(newSellerAccountInfoPO.getMake_over_integral());
				zjcIncomeFlowPO.setExchange_time(new Date());
				zjcIncomeFlowPO.setExpend(inDto.getInteger("pay_points"));
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
				exchangeOrder.setEx_price(inDto.getBigDecimal("pay_points"));
				exchangeOrder.setMobile(usersInfoPO.getMobile());
				exchangeOrder.setEx_fee(new BigDecimal(0));
				exchangeOrder.setStatus(1);
				exchangeOrder.setAdd_time(new Date());
				exchangeOrder.setType(1);
				exchangeOrder.setNote("商家APP向自己账号转账");
				zjcExchangeOrderDao.insert(exchangeOrder);
				//生成操作商家转出日志
				Map<String, Object> sellerLog = new HashMap<>();
				sellerLog.put("user_id", sellerInfoPO.getUser_id());
				sellerLog.put("to_user_id", usersInfoPO.getUser_id());
				sellerLog.put("make_over_integral", inDto.getInteger("pay_points"));
				sellerLog.put("now_make_over_integral", sellerAccountInfoPO.getMake_over_integral());
				sellerLog.put("logType", "转账");
				sellerLog.put("type", "商家转会员");
				apiLogService.saveLog(sellerLog);
					//生成操作商家转入日志
				Map<String, Object> userLog = new HashMap<>();
				userLog.put("user_id", usersInfoPO.getUser_id());
				userLog.put("to_user_id", sellerInfoPO.getUser_id());
				userLog.put("make_over_integral", inDto.getInteger("pay_points"));
				userLog.put("now_make_over_integral", usersAccountInfoPO.getMake_over_integral());
				userLog.put("logType", "转账");
				userLog.put("type", "会员收商家");
				userLog.put("show_type", 1);
				apiLogService.saveLog(userLog);
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg("转账成功，请及时登录APP查看。");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("pending_transfer", make_over_integral);
				msg.setData(map);
			}catch(Exception e){
					throw new AOSException("易物劵转账失败");
			}
		}
		return AOSJson.toJson(msg);
	}

	public String entrusted_transfer(Dto inDto) {
		MessageVO msg = new MessageVO();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) sqlDao.selectOne("com.zjc.store.dao.ZjcSellerInfoDao.selectByKey", inDto);
		inDto.put("store_id", sellerInfoPO.getStore_id());
		ZjcStoreDeputePO deputePO = zjcStoreDeputeDao.deputeDetial(inDto); 
		if(AOSUtils.isEmpty(deputePO)){//用户还未填写申请
			msg.setCode(Apiconstant.NO_DATA.getIndex());
			msg.setMsg(Apiconstant.NO_DATA.getName());
			return AOSJson.toJson(msg);
		}
		Dto outDto = Dtos.newDto();
		AOSUtils.copyProperties(deputePO, outDto);
		Dto dto = sqlDao.selectDto("com.zjc.store.dao.ZjcStoreDao.billListApp", Dtos.newDto("user_id", inDto.getBigInteger("user_id")));
		if(AOSUtils.isNotEmpty(dto)){
			outDto.put("pending_transfer",dto.getString("pending_transfer"));
		}else{
			outDto.put("pending_transfer",0);
		}
		msg.setCode(Apiconstant.Do_Success.getIndex());
		msg.setMsg(Apiconstant.Do_Success.getName());
		msg.setData(outDto);
		return AOSJson.toJson(msg);
	}

	/**
	 * 店铺数据
	 * 
	 * @param httpModel
	 * @return
	 */
	public String store_data(HttpModel httpModel) {
		MessageVO msg = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("store_id"))){
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg);
		}
		Date date = new Date();
		if(AOSUtils.isNotEmpty(inDto.getDate("add_time"))){
			date = inDto.getDate("add_time");
		}
		try {
			inDto.put("store_id", httpModel.getAttribute("store_id"));
			inDto.put("add_time", date);
			List<Dto> dtoList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.sellerAppCountOrder", inDto);
			if(dtoList.size()==0){
				msg.setCode(Apiconstant.NO_DATA.getIndex());
				msg.setMsg(Apiconstant.NO_DATA.getName());
				return AOSJson.toJson(msg);
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			int maxday=c.get(Calendar.DAY_OF_MONTH);
			//获取传入月份的左右日期
			List<String> dayList = MonthUtil.getMonthFullDay(year, month,maxday);
			//取当前商铺当月有数据的日期
			List<String> cjList = new ArrayList<String>();
			for(Dto dto :dtoList){
				cjList.add(dto.getString("days"));
			}
			//获取两个集合的差集
			dayList.removeAll(cjList);
			//没有数据的天数，所有数据赋值为0
			for(int i=0;i<dayList.size();i++){
				Dto dto1 = Dtos.newDto();
				dto1.put("days", dayList.get(i));
				dto1.put("orderNum", 0);
				dto1.put("cashSum", 0);
				dto1.put("couponsSum", 0);
				dtoList.add(dto1);
			}
			//按日期排序
			Collections.sort(dtoList, new Comparator<Dto>() { 
				@Override
	            public int compare(Dto o1, Dto o2) {
	                Collator collator = Collator.getInstance(Locale.CHINA);
	                return collator.compare(o1.getString("days"), o2.getString("days"));
	            }
			});
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
			msg.setData(dtoList);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msg);
	}

	/**
	 * 获取系统消息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String sysMsg(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newInDto(request);
		MessageVO msgVo = new MessageVO();
		if(AOSUtils.isEmpty(inDto.getString("page"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		inDto.put("limit", ConstantUtil.pageSize);
		int page = inDto.getInteger("page");
		inDto.put("start", (page - 1)*ConstantUtil.pageSize);
		inDto.put("user_id", request.getAttribute("user_id").toString());
		List<Dto> list = sqlDao.list("com.store.record.dao.ZjcIncomeFlowDao.getFlowPage", inDto);
		if(list.size()==0){
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			//生成返回分页参数实体
			PageVO pageVO = ParameterUtil.getPageVO(inDto.getInteger("total"), inDto.getInteger("page"));
			for(Dto dto :list){
				if("商城购物".equals(dto.getString("type")) || "线下购物".equals(dto.getString("type"))){//购物收入
					dto.put("content", "订单号："+dto.getString("order_sn")+"，收入券："+dto.getString("income"));
				} else if("转账".equals(dto.getString("type"))){//转账到会员号支出
					dto.put("content", "订单号："+dto.getString("order_sn")+"，支出券："+dto.getString("expend"));
				} else if("售后处理".equals(dto.getString("type"))){
					if(dto.getInteger("income") != 0){
						dto.put("content", "售后处理："+ dto.getString("order_sn") + "，收入券："+dto.getString("income"));
					} else if(dto.getInteger("expend") != 0){
						dto.put("content", "售后处理："+ dto.getString("order_sn") + "，支出券："+dto.getString("expend"));
					}
				}
			}
			pageVO.setList(list);
			msgVo.setData(pageVO);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 商家app接口-更新设备号
	 * 
	 * @param httpModel
	 * @return
	 */
	public String uploadClient(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getString("clientId"))||AOSUtils.isEmpty(dto.getString("client_type"))||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//设备号或者类型不能为空
			msgVo.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			ZjcSellerInfoPO sellerInfo = zjcSellerInfoDao.selectByKey(dto.getBigInteger("user_id")); 
			sellerInfo.setClientid(dto.getString("clientid"));
			sellerInfo.setSrc_client(dto.getInteger("src_client"));
			//修改
			try{
				zjcSellerInfoDao.updateByKey(sellerInfo);
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			} catch(Exception e) {
				e.printStackTrace();
				msgVo.setCode(Apiconstant.Save_fails.getIndex());
				msgVo.setMsg(Apiconstant.Save_fails.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * 修改店铺信息
	 * 
	 * @param httpModel
	 * @return
	 */
	public String updateAppStoreInfo(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("store_id"))
				||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))
				||AOSUtils.isEmpty(dto.getString("business_licence_number_elc"))
				||AOSUtils.isEmpty(dto.getString("food_hygiene_img"))){//参数错误
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		dto.put("store_id", httpModel.getAttribute("store_id"));
		dto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcSellerInfoPO sellerinfo = zjcSellerInfoDao.selectByKey(dto.getBigInteger("user_id"));
		if(AOSUtils.isNotEmpty(sellerinfo)&&sellerinfo.getIs_lock()==1){//商家账号被锁定
			msgVo.setCode(Apiconstant.Store_Account_Is_Lock.getIndex());
			msgVo.setMsg(Apiconstant.Store_Account_Is_Lock.getName());
			return AOSJson.toJson(msgVo);
		}
		Dto storedto = Dtos.newDto();
		storedto.put("store_id", httpModel.getAttribute("store_id"));
		storedto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcStorePO store = (ZjcStorePO) sqlDao.selectOne("com.zjc.store.dao.ZjcStoreDao.selectOne", storedto);
		if(AOSUtils.isEmpty(store)){//该商家没有该店铺
			msgVo.setCode(Apiconstant.Store_Not_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Store_Not_Exist.getName());
			return AOSJson.toJson(msgVo);
		}
		if(store.getStore_state()==1 || store.getStore_state()==4){//该店铺因一场被关闭或暂未通过审核
			msgVo.setCode(Apiconstant.Store_Is_Audit_Or_Close.getIndex());
			msgVo.setMsg(Apiconstant.Store_Is_Audit_Or_Close.getName());
			return AOSJson.toJson(msgVo);
		}
		store.copyProperties(dto);
		store.setStore_state(1);//店铺状态置为待审核
		try{
			int i = sqlDao.update("com.zjc.store.dao.ZjcStoreDao.updateByKey", store);
			if(i==0){//更新失败
				msgVo.setCode(Apiconstant.Save_fails.getIndex());
				msgVo.setMsg(Apiconstant.Save_fails.getName());
			} else {
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			}
		} catch(Exception e){//操作失败
			e.printStackTrace();
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * 提交委托申请
	 * 
	 * @param httpModel
	 * @return
	 */
	public String saveStoreDept(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("store_id"))
				||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))
				||AOSUtils.isEmpty(dto.getString("real_name"))
				||AOSUtils.isEmpty(dto.getString("id_card"))
				||AOSUtils.isEmpty(dto.getString("account_name"))
				||AOSUtils.isEmpty(dto.getString("account_mumber"))
				||AOSUtils.isEmpty(dto.getString("bank"))){//参数错误
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		dto.put("store_id", httpModel.getAttribute("store_id"));
		dto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcSellerInfoPO sellerinfo = zjcSellerInfoDao.selectOne(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(AOSUtils.isEmpty(sellerinfo)){//商家不存在
			msgVo.setCode(Apiconstant.Store_Not_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Store_Not_Exist.getName());
			return AOSJson.toJson(msgVo);
		}
		ZjcUsersInfoPO userinfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectByMobile", Dtos.newDto("mobile",sellerinfo.getMobile()));
		if(AOSUtils.isEmpty(userinfo)){//商家未开通会员号
			msgVo.setCode(Apiconstant.Store_No_User_Account.getIndex());
			msgVo.setMsg(Apiconstant.Store_No_User_Account.getName());
			return AOSJson.toJson(msgVo);
		}
		//检查用户是否是第一次申请
		List<ZjcStoreDeputePO> oldStoreDeputePOList = zjcStoreDeputeDao.list(Dtos.newDto("store_id", httpModel.getAttribute("store_id")));
		int row = 0;
		ZjcStoreDeputePO storeDeputePO = new ZjcStoreDeputePO();
		storeDeputePO.copyProperties(dto);
		storeDeputePO.setAdd_time(new Date());
		storeDeputePO.setState("1");
		if(oldStoreDeputePOList.size() == 0){//该店铺没有委托申请
			row = zjcStoreDeputeDao.insert(storeDeputePO);
		}else{
			row = sqlDao.update("com.zjc.store.dao.ZjcStoreDeputeDao.updateByStoreId", storeDeputePO);
		}
		if(row == 1){
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * 老会员生成对应的商家用户信息
	 * 
	 * @param httpModel
	 * @return
	 */
	public String create_user_account(HttpModel httpModel) {
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//参数错误
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg);
		}
		ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectOne(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(AOSUtils.isEmpty(sellerInfoPO)){//商家不存在
			msg.setCode(Apiconstant.Store_Not_Exist.getIndex());
			msg.setMsg(Apiconstant.Store_Not_Exist.getName());
			return AOSJson.toJson(msg);
		}
		ZjcUsersInfoPO oldUsersInfoPO = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectByMobile", Dtos.newDto("mobile",sellerInfoPO.getMobile()));
		if(AOSUtils.isNotEmpty(oldUsersInfoPO)){
			msg.setCode(Apiconstant.Username_Has_Existed.getIndex());
			msg.setMsg("您已存在商家会员号，请勿重复开通。");
			return AOSJson.toJson(msg);
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
		return AOSJson.toJson(msg);
	}
}
