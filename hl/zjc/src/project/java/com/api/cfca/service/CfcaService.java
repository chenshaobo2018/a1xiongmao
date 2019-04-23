/**
 * 
 */
package com.api.cfca.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.AlipayConstant;
import com.api.ApiPublic.Apiconstant;
import com.api.cfca.controller.MerchantX509Cert;
import com.api.common.po.MessageVO;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.order.dao.po.Rebate;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="CfcaService")
public class CfcaService {
	private static final Logger logger = LoggerFactory
			.getLogger(CfcaService.class);

	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcOrderGoodsDao ZjcOrderGoodsDao;
	@Autowired
	private ZjcGoodsDao ZjcGoodsDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	@Autowired
	private ZjcOrderDao ZjcOrderDao;
	
	public String PayReceived1(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		 try {
			  String v_oid = request.getParameter("v_oid");//订单编号
			  logger.info("--------------参数v_oid:"+v_oid);
			  String v_pmode = new String(request.getParameter("v_pmode").getBytes("ISO-8859-1"),"GBK");//支付方式
			  logger.info("--------------参数v_pmode:"+v_pmode);
			  String v_pstatus = request.getParameter("v_pstatus");//支付结果 20支付成功 30 支付失败
			  logger.info("--------------参数v_pstatus:"+v_pstatus);
			  String v_pstring = new String(request.getParameter("v_pstring").getBytes("ISO-8859-1"),"GBK");//支付结果信息说明
			  logger.info("--------------参数v_pstring:"+v_pstring);
			  String v_amount = request.getParameter("v_amount");//订单金额
			  logger.info("--------------参数v_amount:"+v_amount);
			  String v_moneytype = request.getParameter("v_moneytype");//币种
			  logger.info("--------------参数v_moneytype:"+v_moneytype);
			  /* String v_md5money = request.getParameter("v_md5money");//数字指纹（不校验）
			   String v_md5info = request.getParameter("v_md5info");//数字指纹（不校验）
*/			  String v_sign = request.getParameter("v_sign");//CFCA校验
			  logger.info("--------------参数v_sign:"+v_sign);
			  //CFCA验证
			  String publicKey = "/usr/local/apache-tomcat-7.0.81/webapps/payease_cfca.cer";   //易支付平台CFCA公钥文件
			  //String v_oid="20180418-18338-518381";
			  //String publicKey = "C:\\Users\\Administrator\\Desktop\\payease_cfca.cer";
			  logger.info("--------------参数publicKey:"+publicKey);
			  String rsaSource =v_oid + v_pstatus + v_amount + v_moneytype;
			  logger.info("--------------参数rsaSource:"+rsaSource);
			  String order_id="";
			  boolean verifyReuslt = com.api.cfca.controller.MerchantX509Cert.verifySign(rsaSource,v_sign,publicKey);
			  logger.info("--------------参数verifyReuslt:"+verifyReuslt);
			  if(verifyReuslt){
				    String[] strs=v_oid.split("-");
					for(int i=0,len=strs.length;i<len;i++){
					  order_id=strs[2].toString();
					}
					logger.info("--------------截取后参数order_id:"+order_id);
						// 商户订单号
						ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto("order_id", order_id));
						if(zjcOrderPO != null && !AlipayConstant.PAY_SUCCEESS.equals(zjcOrderPO.getPay_status())){//验证订单是否已支付
							  zjcOrderPO.setV_oid(v_oid);
//							  zjcOrderPO.setPay_code("paySYX");
//							  zjcOrderPO.setPay_name("首易信支付");
							if(AlipayConstant.MIXED_PAY_CODE.equals(zjcOrderPO.getPay_code())){//混合支付
								zjcOrderPO.setPay_time(new Date());
								zjcOrderPO.setPay_code(AlipayConstant.MIXED_PAY_CODE);
								zjcOrderPO.setPay_name(AlipayConstant.MIXED_PAY_NAME);
								zjcOrderPO.setPay_status(1);//支付状态改为已支付
								zjcOrderPO.setPay_time(new Date());
								try{
									// 支付成功后修改充值状态
									zjcOrderDao.updateByKey(zjcOrderPO);
									Dto inDto = Dtos.newDto();
									//现金支付成功，修改返分状态为可执行
									inDto.put("order_id", zjcOrderPO.getOrder_id());
									ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
									int delay_days= Integer.parseInt(zjcMemberOtherPO.getRebate_days_delay());//商家返利延迟天数
									inDto.put("delay_days", delay_days);
									sqlDao.update("com.zjc.users.dao.ZjcQueueDao.requeueByRelationId", inDto);//修改商家返分状态
									sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateUserByRelationId", inDto);//修改会员返分状态
									result = "sent";
								} catch(Exception e) {
									logger.error("--------------混合支付支付宝异常导致订单"+order_id+"出现异常："+e.getMessage());
									result = "error";
								}
							} else {
								zjcOrderPO.setPay_code("paySYX");
								zjcOrderPO.setPay_name("首易信支付");
								zjcOrderPO.setPay_status(AlipayConstant.PAY_SUCCEESS);
								zjcOrderPO.setPay_time(new Date());
								zjcOrderPO.setOrder_status(0);//支付成功后订单状态改为1 表示待收货
								// 支付成功后修改充值状态
								try{
									List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
									ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
									zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
									List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
									//更改商品库存销量信息
									int Goods_nums = orderGood.get(0).getGoods_num();
						    	    Dto countdto = Dtos.newDto();
						    	    countdto.put("Goods_nums", Goods_nums);
						    	    countdto.put("goods_id", goodsd.get(0).getGoods_id());
								    int k=sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateByStoreCount", countdto);
								    if(k == 0){//库存不足
								    	result = "error";
								    	return result;
								    }
								   //限制商品购买次数
								   ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
						    	   Map<String,Object> map = new HashMap<String,Object>();
								   map.put("user_id", request.getAttribute("user_id"));
								   map.put("goods_id", orderGood.get(0).getGoods_id());
								   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
								   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
									   result = "error";
								       return result;
								   }
								    //修改订单状态
									zjcOrderDao.updateByKey(zjcOrderPO);
									
									//写入订单状态表
							        ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
									zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
									zjcOrderActionPONew.setAction_user(zjcOrderPO.getUser_id());
									zjcOrderActionPONew.setAction_user_type(1);//用户
									ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(zjcOrderPO.getUser_id());
									zjcOrderActionPONew.setAction_user_name("");
									if(AOSUtils.isNotEmpty(userInfo)){
										zjcOrderActionPONew.setAction_user_name(userInfo.getNickname());
									}
									zjcOrderActionPONew.setLog_time(new Date());
									zjcOrderActionPONew.setOrder_id(zjcOrderPO.getOrder_id());
									zjcOrderActionPONew.setOrder_status(0);//待确认
									zjcOrderActionPONew.setPay_status(1);//已支付
									zjcOrderActionPONew.setShipping_status(0);//未发货
									zjcOrderActionPONew.setStatus_desc("提交订单");
									zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
									zjcOrderActionDao.insert(zjcOrderActionPONew);
									//zjcTurnIntoUtil.order_online_pay(zjcOrderPO.getOrder_id());
								   //改变会员总消费
								   ZjcUsersAccountInfoPO user = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", zjcOrderPO.getUser_id()));
						    	   ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
						    	   int Total_amount=zjcOrderPO.getCash().intValue()+Integer.parseInt(user.getTotal_amount());
								   useracc.setTotal_amount(String.valueOf(Total_amount));
								   useracc.setUser_id(zjcOrderPO.getUser_id());
								   zjcUsersAccountInfoDao.updateByKey(useracc);
								   Dto inDto = Dtos.newDto();
								   //现金支付成功，修改返分状态为可执行
								   inDto.put("order_id", zjcOrderPO.getOrder_id());
								   sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateUserByRelationId", inDto);//修改会员返分状态
								   Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(zjcOrderPO.getOrder_id());
								   if(Rebate.getPoints() > 0){//修改钱包额度
									   int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(user.getWallet_quota());
									   int Count_wallet_quota=Rebate.getQbbig()+user.getCount_wallet_quota();
									   if(Rebate.getQbbig()>0){
										   useracc.setWallet_quota(String.valueOf(Wallet_quota));
										   useracc.setCount_wallet_quota(Count_wallet_quota);
										   useracc.setUser_id(Rebate.getBuy_user_id());
										   zjcUsersAccountInfoDao.updateByKey(useracc);
									   }
								    }
									result = "sent";
								} catch(Exception e){
									e.printStackTrace();
									result = "error";
									logger.error("--------------现金支付支付宝异常导致订单"+order_id+"出现异常："+e.getMessage());
								}
							}
						}
				}else{
					logger.info("验证失败,不去更新状态");
					result = "error";
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * 异步回调
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String PayReceived2(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		 try {
			  String v_oid = request.getParameter("v_oid");//订单编号
			  logger.info("--------------参数v_oid:"+v_oid);
			  String v_pmode = new String(request.getParameter("v_pmode").getBytes("ISO-8859-1"),"GBK");//支付方式
			  logger.info("--------------参数v_pmode:"+v_pmode);
			  String v_pstatus = request.getParameter("v_pstatus");//支付结果 20支付成功 30 支付失败
			  logger.info("--------------参数v_pstatus:"+v_pstatus);
			  String v_pstring = new String(request.getParameter("v_pstring").getBytes("ISO-8859-1"),"GBK");//支付结果信息说明
			  logger.info("--------------参数v_pstring:"+v_pstring);
			  String v_amount = request.getParameter("v_amount");//订单金额
			  logger.info("--------------参数v_amount:"+v_amount);
			  String v_moneytype = request.getParameter("v_moneytype");//币种
			  logger.info("--------------参数v_moneytype:"+v_moneytype);
			  /* String v_md5money = request.getParameter("v_md5money");//数字指纹（不校验）
			   String v_md5info = request.getParameter("v_md5info");//数字指纹（不校验）
*/			  String v_sign = request.getParameter("v_sign");//CFCA校验
			  logger.info("--------------参数v_sign:"+v_sign);
			  //CFCA验证
			  String publicKey = "/usr/local/apache-tomcat-7.0.81/webapps/payease_cfca.cer";   //易支付平台CFCA公钥文件
			  //String v_oid="20180418-18338-518381";
			  //String publicKey = "C:\\Users\\Administrator\\Desktop\\payease_cfca.cer";
			  logger.info("--------------参数publicKey:"+publicKey);
			  String v_count = request.getParameter("v_count");
			  logger.info("--------------参数v_count:"+v_count);
			  String rsaSource =v_oid+v_pstatus+v_amount+v_moneytype+v_count;
			  logger.info("--------------参数rsaSource:"+rsaSource);
			  String order_id="";
			  boolean verifyReuslt = com.api.cfca.controller.MerchantX509Cert.verifySign(rsaSource,v_sign,publicKey);
			  logger.info("--------------参数verifyReuslt:"+verifyReuslt);
			  if(verifyReuslt){
				    String[] strs=v_oid.split("-");
					for(int i=0,len=strs.length;i<len;i++){
					  order_id=strs[2].toString();
					}
					logger.info("--------------截取后参数order_id:"+order_id);
						// 商户订单号
						ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto("order_id", order_id));
						if(zjcOrderPO != null && !AlipayConstant.PAY_SUCCEESS.equals(zjcOrderPO.getPay_status())){//验证订单是否已支付
							  zjcOrderPO.setV_oid(v_oid);
//							  zjcOrderPO.setPay_code("paySYX");
//							  zjcOrderPO.setPay_name("首易信支付");
							if(AlipayConstant.MIXED_PAY_CODE.equals(zjcOrderPO.getPay_code())){//混合支付
								zjcOrderPO.setPay_time(new Date());
								zjcOrderPO.setPay_code(AlipayConstant.MIXED_PAY_CODE);
								zjcOrderPO.setPay_name(AlipayConstant.MIXED_PAY_NAME);
								zjcOrderPO.setPay_status(1);//支付状态改为已支付
								zjcOrderPO.setPay_time(new Date());
								try{
									// 支付成功后修改充值状态
									zjcOrderDao.updateByKey(zjcOrderPO);
									Dto inDto = Dtos.newDto();
									//现金支付成功，修改返分状态为可执行
									inDto.put("order_id", zjcOrderPO.getOrder_id());
									ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
									int delay_days= Integer.parseInt(zjcMemberOtherPO.getRebate_days_delay());//商家返利延迟天数
									inDto.put("delay_days", delay_days);
									sqlDao.update("com.zjc.users.dao.ZjcQueueDao.requeueByRelationId", inDto);//修改商家返分状态
									sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateUserByRelationId", inDto);//修改会员返分状态
									result = "sent";
								} catch(Exception e) {
									logger.error("--------------混合支付支付宝异常导致订单"+order_id+"出现异常："+e.getMessage());
									result = "error";
								}
							} else {
								zjcOrderPO.setPay_code("paySYX");
								zjcOrderPO.setPay_name("首易信支付");
								zjcOrderPO.setPay_status(AlipayConstant.PAY_SUCCEESS);
								zjcOrderPO.setPay_time(new Date());
								zjcOrderPO.setOrder_status(0);//支付成功后订单状态改为1 表示待收货
								// 支付成功后修改充值状态
								try{
									List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
									ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
									zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
									List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
									//更改商品库存销量信息
									int Goods_nums = orderGood.get(0).getGoods_num();
						    	    Dto countdto = Dtos.newDto();
						    	    countdto.put("Goods_nums", Goods_nums);
						    	    countdto.put("goods_id", goodsd.get(0).getGoods_id());
								    int k=sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateByStoreCount", countdto);
								    if(k == 0){//库存不足
								    	result = "error";
								    	return result;
								    }
								   //限制商品购买次数
								   ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
						    	   Map<String,Object> map = new HashMap<String,Object>();
								   map.put("user_id", request.getAttribute("user_id"));
								   map.put("goods_id", orderGood.get(0).getGoods_id());
								   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
								   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
									   result = "error";
								       return result;
								   }
								    //修改订单状态
									zjcOrderDao.updateByKey(zjcOrderPO);
									
									//写入订单状态表
							        ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
									zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
									zjcOrderActionPONew.setAction_user(zjcOrderPO.getUser_id());
									zjcOrderActionPONew.setAction_user_type(1);//用户
									ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(zjcOrderPO.getUser_id());
									zjcOrderActionPONew.setAction_user_name("");
									if(AOSUtils.isNotEmpty(userInfo)){
										zjcOrderActionPONew.setAction_user_name(userInfo.getNickname());
									}
									zjcOrderActionPONew.setLog_time(new Date());
									zjcOrderActionPONew.setOrder_id(zjcOrderPO.getOrder_id());
									zjcOrderActionPONew.setOrder_status(0);//待确认
									zjcOrderActionPONew.setPay_status(1);//已支付
									zjcOrderActionPONew.setShipping_status(0);//未发货
									zjcOrderActionPONew.setStatus_desc("提交订单");
									zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
									zjcOrderActionDao.insert(zjcOrderActionPONew);
									//zjcTurnIntoUtil.order_online_pay(zjcOrderPO.getOrder_id());
								   //改变会员总消费
								   ZjcUsersAccountInfoPO user = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", zjcOrderPO.getUser_id()));
						    	   ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
						    	   int Total_amount=zjcOrderPO.getCash().intValue()+Integer.parseInt(user.getTotal_amount());
								   useracc.setTotal_amount(String.valueOf(Total_amount));
								   useracc.setUser_id(zjcOrderPO.getUser_id());
								   zjcUsersAccountInfoDao.updateByKey(useracc);
								   Dto inDto = Dtos.newDto();
								   //现金支付成功，修改返分状态为可执行
								   inDto.put("order_id", zjcOrderPO.getOrder_id());
								   sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateUserByRelationId", inDto);//修改会员返分状态
								   Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(zjcOrderPO.getOrder_id());
								   if(Rebate.getPoints() > 0){//修改钱包额度
									   int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(user.getWallet_quota());
									   int Count_wallet_quota=Rebate.getQbbig()+user.getCount_wallet_quota();
									   if(Rebate.getQbbig()>0){
										   useracc.setWallet_quota(String.valueOf(Wallet_quota));
										   useracc.setCount_wallet_quota(Count_wallet_quota);
										   useracc.setUser_id(Rebate.getBuy_user_id());
										   zjcUsersAccountInfoDao.updateByKey(useracc);
									   }
								    }
									result = "sent";
								} catch(Exception e){
									e.printStackTrace();
									result = "error";
									logger.error("--------------现金支付支付宝异常导致订单"+order_id+"出现异常："+e.getMessage());
								}
							}
						}
				}else{
					logger.info("验证失败,不去更新状态");
					result = "error";
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	
	/**
	 * @api {get} notokenapi/app/v1/payBankSubmitMax.jhtml cfca支付
	 * @apiName cfca支付
	 * @apiGroup pay
	 *
	 * @apiParam {String} order_id order_id
	 */
	public MessageVO payBankSubmitMax(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO msgVo = new MessageVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//定义要输出日期字符串的格式
		String dateId1 = sdf.format(new java.util.Date());//获取当前日期
		String order_id=request.getParameter("order_id");
		if(AOSUtils.isEmpty(order_id)){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return msgVo;
		}
		Dto qDto=httpModel.getInDto();
		qDto.put("pay_status", 0);
		ZjcOrderPO orderpo= (ZjcOrderPO) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.selectOne", qDto);
		if(AOSUtils.isEmpty(orderpo)){
			msgVo.setCode(Apiconstant.Order_NO_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Order_NO_Exist.getName());
			return msgVo;
		}
		try {
			String v_oid=dateId1+"-18338-"+order_id;
			String v_ymd=dateId1;
			String v_amount=String.valueOf(orderpo.getCash());
			String v_rcvname="18338";//orderpo.getConsignee();
			String v_rcvpost="18338";
			String v_rcvaddr=orderpo.getConsignee();//"18338";
			String v_rcvtel=orderpo.getOrder_sn();
			String v_orderstatus="0";
			String v_ordername="18338";
			String v_mid="18338";
			String v_moneytype="0";
			String v_pmode="904";
			String v_userref=orderpo.getUser_id().toString();
			String v_url="https://zjc1518.com/aosuite/notokenapi/app/v1/PayReceived1.jhtml";
			String pfxFile = "/usr/local/apache-tomcat-7.0.81/webapps/payeay.pfx";//商户私钥文件
		    //String pfxFile = "C:/Users/Administrator/Desktop/payeay.pfx";
			String pfxPassword = "zjc1518";//私钥键入密码
			String aliasPassword = "zjc1518";
			String aliasName = "{17ce4d07-c8a1-457e-a139-a9d3362d8ab4}";//别名
			String src = v_moneytype + v_ymd + v_amount + v_rcvname + v_oid + v_mid + v_url;
			String sign = MerchantX509Cert.sign(src, pfxFile, aliasName, pfxPassword, aliasPassword);
			orderpo.setV_oid(v_oid);
			ZjcOrderDao.updateByKey(orderpo);
			/*String value="v_oid="+v_oid+"&v_ymd="+v_ymd+"&v_amount="+v_amount+"&v_rcvname="+v_rcvname+"&v_rcvpost="+v_rcvpost
				     +"&v_rcvaddr="+v_rcvaddr+"&v_rcvtel="+v_rcvtel+"&v_orderstatus="+v_orderstatus+"&v_ordername="+v_ordername
				     +"&v_mid="+v_mid+"&v_moneytype="+v_moneytype+"&v_url="+v_url+"&v_pmode="+v_pmode+"&sign="+sign+"&v_userref="+v_userref;*/
			Map<String, String> map=new HashMap<String, String>();
			map.put("v_oid", v_oid);
			map.put("v_ymd", v_ymd);
			map.put("v_amount", v_amount);
			map.put("v_rcvname", v_rcvname);
			map.put("v_rcvpost", v_rcvpost);
			map.put("v_rcvaddr", v_rcvaddr);
			map.put("v_rcvtel", v_rcvtel);
			map.put("v_orderstatus", v_orderstatus);
			map.put("v_ordername", v_ordername);
			map.put("v_mid", v_mid);
			map.put("v_moneytype", v_moneytype);
			map.put("v_pmode", v_pmode);
			map.put("v_userref", v_userref);
			map.put("sign", sign);
			map.put("v_url", v_url);
			map.put("v_idtype","00");
			map.put("v_idnumber","000000000000000");
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(map);
		} catch (Exception e) {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
			e.printStackTrace();
		}
		return msgVo;
	}
}
