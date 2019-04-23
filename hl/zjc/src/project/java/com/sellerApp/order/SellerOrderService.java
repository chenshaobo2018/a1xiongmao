package com.sellerApp.order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
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
import com.api.order.dao.ZjcXxOrderDao;
import com.api.order.dao.po.Rebate;
import com.api.order.dao.po.ZjcPaymentSallerPO;
import com.api.order.dao.po.ZjcXxOrderPO;
import com.store.record.dao.ZjcIncomeFlowDao;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcDeliveryDocDao;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcDeliveryDocPO;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.system.dao.po.ZjcMemberMultiplicationPO;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcQueueDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcQueuePO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 需要Token的app接口- 易物交易
 * 
 * @author wgm
 */
@Service(value="orderService")
public class SellerOrderService {
	
	
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcDeliveryDocDao zjcDeliveryDocDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ZjcOrderGoodsDao zjcOrderGoodsDao;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcXxOrderDao zjcXxOrderDao;
	
	@Autowired
	private ZjcIncomeFlowDao zjcIncomeFlowDao;
	
	@Autowired
	private ZjcQueueDao zjcQueueDao;
	

	/**
	 * 查询商家订单列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public String sellerOrderList(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("page")) || AOSUtils.isEmpty(qDto.getString("order_status"))
				|| AOSUtils.isEmpty(qDto.getString("store_id"))){//参数错误
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		if(qDto.getInteger("page")<1){
			msgVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Page_Is_Null.getName());
			return AOSJson.toJson(msgVO);
		}
		//设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		int page = qDto.getInteger("page");
		qDto.put("start", (page-1)*ConstantUtil.pageSize);
		List<ZjcOrderPO> zjcOrderList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.sellerOrderListPage", qDto);
		if(AOSUtils.isEmpty(zjcOrderList)){//数据为空
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
			return AOSJson.toJson(msgVO);
		}
		
		List<ZjcOrderPO> zjcNewOrderList = new ArrayList<ZjcOrderPO>();
		for(ZjcOrderPO zjcOrderPO :zjcOrderList){//遍历订单表，查询订单商品信息
			List<ZjcOrderGoodsPO> newOrderGoodsList = new ArrayList<ZjcOrderGoodsPO>();
			qDto.put("order_id", zjcOrderPO.getOrder_id());
			List<ZjcOrderGoodsPO> orderGoodsList = zjcOrderGoodsDao.list(qDto);
			if(!AOSUtils.isEmpty(orderGoodsList)){
				for(ZjcOrderGoodsPO orderGoods :orderGoodsList){//遍历商品订单数据，获取商品详细数据
					ZjcOrderGoodsPO newordergoods = new ZjcOrderGoodsPO();
					ZjcGoodsPO newgoodsPO = new ZjcGoodsPO();
					ZjcGoodsPO goodsPO = zjcGoodsDao.selectByKey(orderGoods.getGoods_id());
					if(!AOSUtils.isEmpty(goodsPO)){
						newgoodsPO.setOriginal_img(goodsPO.getOriginal_img());
						newordergoods.setZjcGoodsPO(newgoodsPO);
					}
					ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.getZjcDeliveryByOrderId(zjcOrderPO.getOrder_id());
					ZjcDeliveryDocPO newDelivery = new ZjcDeliveryDocPO();
					if(!AOSUtils.isEmpty(zjcDeliveryDocPO)){//设置物流信息
						newDelivery.setId(zjcDeliveryDocPO.getId());
						newDelivery.setShipping_name(zjcDeliveryDocPO.getShipping_name());
						newDelivery.setInvoice_no(zjcDeliveryDocPO.getInvoice_no());
						newordergoods.setZjcDeliveryDocPO(newDelivery);
					}
					//拼装页面展示商品订单数据
					newordergoods.setGoods_id(orderGoods.getGoods_id());
					newordergoods.setOrder_id(orderGoods.getOrder_id());
					newordergoods.setGoods_name(orderGoods.getGoods_name());
					newordergoods.setGoods_num(orderGoods.getGoods_num());
					newordergoods.setSpec_key(orderGoods.getSpec_key());
					newordergoods.setSpec_key_name(orderGoods.getSpec_key_name());
					newordergoods.setGoods_price(orderGoods.getGoods_price());
					newordergoods.setCost_price(orderGoods.getCost_price());
					newordergoods.setMarket_price(orderGoods.getMarket_price());
					newOrderGoodsList.add(newordergoods);
				}
			}
			zjcOrderPO.setZjcOrderGoodsPO(newOrderGoodsList);
			zjcOrderPO.setArea_info("");
			zjcNewOrderList.add(zjcOrderPO);
		}
		
		//生成返回分页参数实体
		PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
		pageVO.setList(zjcOrderList);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(pageVO);
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 订单发货
	 * 
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public String delivery(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(inDto.getString("order_id")) || AOSUtils.isEmpty(inDto.getString("invoice_no"))
				|| AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//参数错误
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		//通过订单ID查询订单信息数据
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(inDto.getInteger("order_id"));
		if(AOSUtils.isEmpty(zjcOrderPO)){//订单不存在
			msgVO.setCode(Apiconstant.Order_NO_Exist.getIndex());
			msgVO.setMsg(Apiconstant.Order_NO_Exist.getName());
			return AOSJson.toJson(msgVO);
		}
		if(zjcOrderPO.getOrder_status() != 0 && zjcOrderPO.getOrder_status() != 1){
			msgVO.setCode(Apiconstant.Order_cannot_ship.getIndex());
			msgVO.setMsg(Apiconstant.Order_cannot_ship.getName());
			return AOSJson.toJson(msgVO);
		}
		if(zjcOrderPO.getShipping_status() == 1){
			msgVO.setCode(Apiconstant.Order_was_ship.getIndex());
			msgVO.setMsg(Apiconstant.Order_was_ship.getName());
			return AOSJson.toJson(msgVO);
		}
		//修改订单状态
		zjcOrderPO.setShipping_status(1);//状态改为已发货
		zjcOrderPO.setShipping_time(new Date());
		zjcOrderPO.setTracking_no(inDto.getString("invoice_no"));//物流编号
		zjcOrderPO.setOrder_status(1);
		try{
			zjcOrderDao.updateByKey(zjcOrderPO);
			//新增订单状态记录
			ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
			zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcOrderActionPONew.setAction_note("商家发货");
			zjcOrderActionPONew.setAction_user(new BigInteger(httpModel.getAttribute("user_id").toString()));
			ZjcSellerInfoPO seller = zjcSellerInfoDao.selectByKey(new BigInteger(httpModel.getAttribute("user_id").toString()));
			zjcOrderActionPONew.setAction_user_name("商家");
			if(AOSUtils.isNotEmpty(seller)){
				zjcOrderActionPONew.setAction_user_name(seller.getReal_name());
			}
			zjcOrderActionPONew.setAction_user_type(3);
			zjcOrderActionPONew.setLog_time(new Date());
			zjcOrderActionPONew.setOrder_id(inDto.getInteger("order_id"));
			zjcOrderActionPONew.setOrder_status(1);
			zjcOrderActionPONew.setPay_status(1);
			zjcOrderActionPONew.setShipping_status(1);
			zjcOrderActionPONew.setStatus_desc("商家发货");
			zjcOrderActionDao.insert(zjcOrderActionPONew);
			//通过订单ID查询订单发货信息
			ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.getZjcDeliveryByOrderId(inDto.getInteger("order_id"));
			//新增发货单记录
			if(AOSUtils.isEmpty(zjcDeliveryDocPO)){
				zjcDeliveryDocPO = new ZjcDeliveryDocPO();
				zjcDeliveryDocPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcDeliveryDocPO.copyProperties(zjcOrderPO);
				zjcDeliveryDocPO.setNote(inDto.getString("note"));
				zjcDeliveryDocPO.setInvoice_no(inDto.getString("invoice_no"));
				zjcDeliveryDocPO.setShipping_code(inDto.getString("shipping_code"));
				zjcDeliveryDocPO.setCreate_time(new Date());
				zjcDeliveryDocPO.setIs_del(0);
				zjcDeliveryDocDao.insert(zjcDeliveryDocPO);
			}
			//修改订单商品发货状态
			List<ZjcOrderGoodsPO> zjcOrderGoodsPOList = zjcOrderGoodsDao.getOrderGoodsByOrderId(inDto.getInteger("order_id"));
			if(null != zjcOrderGoodsPOList){
				for(ZjcOrderGoodsPO zjcOrderGoodsPO : zjcOrderGoodsPOList){
					zjcOrderGoodsPO.setDelivery_id(zjcDeliveryDocPO.getId());
					zjcOrderGoodsPO.setIs_send(1);
					zjcOrderGoodsDao.updateByKey(zjcOrderGoodsPO);
				}
			}
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}catch(Exception e){
			e.printStackTrace();
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 统计订单数量
	 * 
	 * @param httpModel
	 * @return
	 */
	public String countOrderNum(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("store_id"))){//参数错误
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		Dto orderNumDto = sqlDao.selectDto("com.zjc.order.dao.ZjcOrderDao.orderNum", qDto);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(orderNumDto);
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 商家扫码收款
	 * $shop_mobile   商家电话
	 * $shop_mobile    付款会员电话
	 * $amount          付款金额
	 * * */
	@Transactional(rollbackFor=Exception.class)
	public String sellerScanPay(HttpServletRequest request, HttpServletResponse response){
		 MessageVO MessageVO=new MessageVO();
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto dto = httpModel.getInDto();
		 if(AOSUtils.isEmpty(dto.getString("userId")) || AOSUtils.isEmpty(dto.getString("point"))
				 || AOSUtils.isEmpty(httpModel.getAttribute("user_id"))
				 || AOSUtils.isEmpty(dto.getString("pay_code"))){
			 MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			 MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			 return AOSJson.toJson(MessageVO);
		}
		//验证支付码是否已使用
		 Dto payDto = Dtos.newDto();
		 payDto.put("user_id", dto.getString("userId"));
		 payDto.put("pay_code", dto.getString("pay_code"));
		 ZjcPaymentSallerPO paymentSaller = (ZjcPaymentSallerPO) sqlDao.selectOne("com.api.order.dao.ZjcPaymentSallerDao.selectOne", payDto);
		 if(AOSUtils.isEmpty(paymentSaller)){//支付码不存在
			 MessageVO.setMsg(Apiconstant.Pay_Code_Is_Wrong.getName());
			 MessageVO.setCode(Apiconstant.Pay_Code_Is_Wrong.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 if(paymentSaller.getUsed()==1){//支付码已使用
			 MessageVO.setMsg(Apiconstant.Pay_Code_Is_Used.getName());
			 MessageVO.setCode(Apiconstant.Pay_Code_Is_Used.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 
		 MessageVO = apiPublicService.isLockedHandle();
		 if(MessageVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(MessageVO);
		 }
		 MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", dto.getBigInteger("userId")));
		 if(MessageVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(MessageVO);
		 }
		 String amount=request.getParameter("point");
		 //会员账户信息
		 ZjcUsersAccountInfoPO usersAccountInfo=zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("userId"));
		 if(AOSUtils.isEmpty(usersAccountInfo)){//账户不存在
			 MessageVO.setMsg(Apiconstant.Username_Not_Exist.getName());
			 MessageVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			 return AOSJson.toJson(MessageVO);
		 } else if(usersAccountInfo.getPay_points()<Integer.parseInt(amount)){
			 MessageVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
			 MessageVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 MessageVO = apiPublicService.isLineAccount(dto.getBigInteger("userId"),dto.getInteger("point"));
		 if(MessageVO.getCode() != 1){//判断当日转出额度是否超出限制
			 return AOSJson.toJson(MessageVO);
		 }
		 
		 //获取会员id
		 BigInteger user_id = dto.getBigInteger("userId");
		 //通过商家ID查询商家信息
		 dto.put("user_id", httpModel.getAttribute("user_id"));//传输商家ID
		 ZjcSellerInfoPO sellerinfo=zjcSellerInfoDao.selectByKey(dto.getBigInteger("user_id"));
		 if(AOSUtils.isEmpty(sellerinfo)){//商家不存在
			 MessageVO.setMsg(Apiconstant.Sellername_Not_Exist.getName());
			 MessageVO.setCode(Apiconstant.Sellername_Not_Exist.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 try{
			 //商家账户信息
			 ZjcUsersAccountInfoPO sellerAccount = zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
			 //查询会员信息
			 ZjcUsersInfoPO userinfo=(ZjcUsersInfoPO)sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", user_id));
			 //商家是否被限制
			 List<ZjcMemberParameterPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMax",null);
			 //商家提成比例
			 List<ZjcMemberMultiplicationPO> MemberMultiplication = sqlDao.list("com.zjc.system.dao.ZjcMemberMultiplicationDao.selectByMaxKey",null);
			 if(!AOSUtils.isEmpty(paramDtos.get(0).getMenber_spending())&&paramDtos.get(0).getMenber_spending().indexOf(String.valueOf(user_id)) !=-1){
				 MessageVO.setMsg(Apiconstant.Members_close.getName());
			 }
			 //计算商店提成积分
			 int point_saller=(new BigDecimal(amount).multiply(new BigDecimal(MemberMultiplication.get(0).getOffline_shopping_commission_ratio()))).intValue();
			 //减少会员的易物卷
			 int pay_points= usersAccountInfo.getPay_points()-Integer.parseInt(amount);
			 int total_amount=Integer.parseInt(usersAccountInfo.getTotal_amount())+Integer.parseInt(amount);
			 usersAccountInfo.setTotal_amount(String.valueOf(total_amount));
			 usersAccountInfo.setPay_points(pay_points);
			 zjcUsersAccountInfoDao.updateByKey(usersAccountInfo);
			 //调用存储过程,实时改变会员等级
			 sqlDao.call("level",Dtos.newDto("user_id", user_id));
			 //添加商家的易物卷（商家返分）
			 int make_over_integral=sellerAccount.getMake_over_integral()+point_saller;
			 sellerAccount.setMake_over_integral(make_over_integral);
			 zjcUsersAccountInfoDao.updateByKey(sellerAccount);
			//添加线下购物订单
			 ZjcXxOrderPO xxorder=new ZjcXxOrderPO();
			 xxorder.setOrder_sn(ParameterUtil.getOrderSn());
			 xxorder.setUser_id(user_id.intValue());
			 xxorder.setSeller_user_id(dto.getInteger("user_id"));
			 xxorder.setTotal_amount(new BigDecimal(amount));
			 xxorder.setAdd_time(new Date());
			 zjcXxOrderDao.insert(xxorder);
			 
			 //修改支付码记录
			 paymentSaller.setUsed(1);//支付码置为已使用
			 paymentSaller.setEx_sn(xxorder.getOrder_sn());//设置支付订单号
			 sqlDao.update("com.api.order.dao.ZjcPaymentSallerDao.updateByKey", paymentSaller);
			 
			//生成操作流水
			ZjcIncomeFlowPO zjcIncomeFlowPO = new ZjcIncomeFlowPO();
			zjcIncomeFlowPO.setBalance(usersAccountInfo.getMake_over_integral());
			zjcIncomeFlowPO.setExchange_time(xxorder.getAdd_time());
			zjcIncomeFlowPO.setIn_user_id(dto.getInteger("user_id"));
			zjcIncomeFlowPO.setOut_user_id(user_id.intValue());
			zjcIncomeFlowPO.setIncome(point_saller);
			zjcIncomeFlowPO.setType("2");//会员线下购物获得券
			zjcIncomeFlowPO.setOrder_sn(xxorder.getOrder_sn());
			zjcIncomeFlowDao.insert(zjcIncomeFlowPO);
			 
			//系统生成会员消费返回队列
			ZjcQueuePO queue=new ZjcQueuePO();
			//获取线下购物会员赠送券
			int dueToPonts = new BigDecimal(amount).multiply(new BigDecimal(MemberMultiplication.get(0).getOffline_shopping_multiplication_rate())).intValue();
			Rebate Rebate = apiPublicService.distribute_integral(dueToPonts);
			queue.setNote("线下消费后，将易物券返回会员");
			queue.setType(8);
			queue.setAdd_time(new Date());
			queue.setSend_time(new Date());
			queue.setUser_id(user_id);
			queue.setXf_points(Rebate.getXf());
			queue.setKz_points(Rebate.getKz());
			queue.setRelation_id(xxorder.getXx_id());
			zjcQueueDao.insert(queue);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logType", "线下购物");
			map.put("user_id", user_id);
			map.put("to_user_id", dto.getBigInteger("user_id"));
			map.put("pay_points", amount);
			map.put("due_tc_points", dueToPonts);
			map.put("now_points", usersAccountInfo.getPay_points());
			map.put("show_type", 1);
			apiLogService.saveLog(map);
			
			map.put("logType", "线下购物结算");
			map.put("user_id", dto.getBigInteger("user_id"));
			map.put("to_user_id", user_id);
			map.put("make_over_integral", point_saller);
			map.put("mobile", userinfo.getMobile());
			map.put("real_name", userinfo.getReal_name());
			map.put("now_make_over_integral", sellerAccount.getMake_over_integral());
			apiLogService.saveLog(map); 
			
			//会员信息推送
			String userTuisongContent = user_id + "会员向商家" + dto.getBigInteger("user_id") + "实体店会员支付" + amount + "易物券";
			String userTuisongMsg = ParameterUtil.getTuisongMsg(1,"线下交易", "线下交易",userTuisongContent);
			String usertuisong = ParameterUtil.tuisongToSingle(userinfo.getClientid(),userinfo.getSrc_client(),userTuisongMsg);
			//商家推送
			String shopTuisongContent = dto.getBigInteger("user_id") + "实体店收到" + user_id + "会员线下购物结算" + point_saller + "易物券";
			String shopTuisongMsg = ParameterUtil.getTuisongMsg(1,"线下交易", "线下交易",shopTuisongContent);
			String shoptuisong = ParameterUtil.tuisongToSingle(sellerinfo.getClientid(),sellerinfo.getSrc_client(),shopTuisongMsg);
			if(usertuisong.contains("successed_online")||shoptuisong.contains("successed_online")){
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				MessageVO.setData("");
			} else {
				MessageVO.setCode(1234);
				MessageVO.setMsg("推送离线");
			}
		 } catch(Exception e){
			 e.printStackTrace();
			 MessageVO.setCode(Apiconstant.Save_fails.getIndex());
			 MessageVO.setMsg(Apiconstant.Save_fails.getName());
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		 }
		 return  AOSJson.toJson(MessageVO);
	}

}
