/**
 * 
 */
package com.api.alipay.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.api.ApiPublic.AlipayConstant;
import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.SignUtils;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.goods.dao.po.XpttsgoodsidPO;
import com.api.order.dao.po.Rebate;
import com.api.order.rabbitmq.Sender;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.ZjcRechargeDao;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.order.dao.po.ZjcRechargePO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 
 * 支付宝service
 * 
 * @author zc
 *
 */
@Service(value = "alipayService")
public class AlipayService {

	private static final Logger logger = LoggerFactory
			.getLogger(AlipayService.class);

	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;

	@Autowired
	private SqlDao sqlDao;

	@Autowired
	private ZjcRechargeDao zjcRechargeDao;

	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	
	@Autowired
	private ApiLogService apiLogService;
	
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

	/**
	 * 易物券充值订单sign
	 * 
	 * @param httpModel
	 * @return
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	public String aliySign(HttpModel httpModel) throws AlipayApiException,
			UnsupportedEncodingException {
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", httpModel.getAttribute("user_id").toString());
		map.put("order_sn", inDto.getString("order_id"));
		ZjcRechargePO zjcRechargePO = zjcRechargeDao
				.selectOne(Dtos.newDto(map));
		Map<String, String> params = new HashMap<String, String>();
		String notify_url = "https://zjc1518.com/aosuite/notokenapi/app/v1/alipayRechargeNotify.jhtml";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sdf.format(new Date());
		params.put("app_id", AlipayConstant.APP_ID);
		params.put("method", AlipayConstant.METHOD);
		params.put("format", AlipayConstant.FORMATE);
		params.put("charset", AlipayConstant.CHARSET);
		params.put("sign_type", AlipayConstant.SIGNTYPE);
		params.put("timestamp", timestamp);
		params.put("version", AlipayConstant.VERSION);
		params.put("notify_url", notify_url);

		Map<String, String> busMap = new HashMap<String, String>();
		busMap.put("body", "易物券充值");
		busMap.put("subject", "易物券充值");
		busMap.put("out_trade_no", zjcRechargePO.getOrder_sn());
		busMap.put("timeout_express", AlipayConstant.EXPIRE_TIME);
		busMap.put("total_amount", zjcRechargePO.getAccount());
		busMap.put("seller_id", AlipayConstant.SELLER_ID);
		busMap.put("product_code", AlipayConstant.PRODUCTCODE);
		String busConente = AOSJson.toJson(busMap);
		params.put("biz_content", busConente);
		String sign = getSign(params);
		params.put("sign", sign);
		msgVo.setData(params);
		msgVo.setCode(Apiconstant.Do_Success.getIndex());
		msgVo.setMsg(Apiconstant.Do_Success.getName());
		return AOSJson.toJson(msgVo);
	}

	/**
	 * 易物券充值订单回调验签
	 * 
	 * @param httpModel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public String alipayNotify(HttpModel httpModel) throws UnsupportedEncodingException {
		String result = "";
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = httpModel.getRequest().getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "GBK");
			params.put(name, valueStr);
			System.out.println(name+"--"+valueStr);
		}
		logger.info(params.toString());
		String trade_status = new String(httpModel.getRequest().getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		logger.info("状态码" + trade_status);
		String out_trade_no = new String(httpModel.getRequest().getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		logger.info("订单编号" + out_trade_no);
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params,
					AlipayConstant.ALIPAY_PUBLIC_KEY, AlipayConstant.CHARSET,
					AlipayConstant.SIGNTYPE);
			logger.info(AOSJson.toJson(signVerified));
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.info(e.getErrMsg());
			result = "fail";// 验签发生异常,则直接返回失败
		}
		// 调用SDK验证签名
		if (signVerified) {
			logger.info("验签成功");
			if (trade_status.equals("TRADE_SUCCESS")) {
				// 商户订单号
				ZjcRechargePO zjcRechargePO = zjcRechargeDao.selectOne(Dtos
						.newDto("order_sn", out_trade_no));
				if(zjcRechargePO != null && !AlipayConstant.PAY_SUCCEESS.equals(zjcRechargePO.getPay_status())){//验证订单是否已支付
					logger.info("是否存在该订单" + AOSJson.toJson(zjcRechargePO));
					ZjcRechargePO newRechargePO = new ZjcRechargePO();
					newRechargePO.copyProperties(zjcRechargePO);
					// 支付成功后修改充值状态
					newRechargePO.setPay_code(AlipayConstant.PAY_CODE);
					newRechargePO.setPay_name(AlipayConstant.PAY_NAME);
					newRechargePO.setPay_status(AlipayConstant.PAY_SUCCEESS);
					int row = zjcRechargeDao.updateByKey(newRechargePO);
					logger.info("修改订单状态成功" + row);
					if (row == 1) {
						// 充值成功后，充值账户积分的增加
						ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao
								.selectOne(Dtos.newDto("user_id",
										zjcRechargePO.getUser_id()));
						zjcUsersAccountInfoPO
								.setMake_over_integral(zjcUsersAccountInfoPO
										.getMake_over_integral()
										+ Integer.parseInt(zjcRechargePO
												.getBuy_points()));
						zjcUsersAccountInfoDao
								.updateByKey(zjcUsersAccountInfoPO);
						result = "success";
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("logType", "易物券置换");
						map.put("user_id", zjcRechargePO.getUser_id());
						map.put("pay_points", zjcRechargePO.getBuy_points());
						map.put("pay_type", zjcRechargePO.getPay_name());
						map.put("due_tc_points", "0");
						map.put("now_points",zjcUsersAccountInfoPO.getPay_points());
						map.put("now_make_over_integral", zjcUsersAccountInfoPO.getMake_over_integral());
						apiLogService.saveLog(map);
						logger.info("修改数据成功" + AOSJson.toJson(result));
					} else {
						result = "fail";
					}
				} 
			}
		} else {
			logger.info("验证失败,不去更新状态");
			result = "fail";
		}
		return result;
	}
	
	
	/**
	 * 商品订单在线支付sign
	 * 
	 * @param httpModel
	 * @return
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	public String aliyOrderSign(HttpModel httpModel) throws AlipayApiException,
			UnsupportedEncodingException {
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		Map<String, Object> map = new HashMap<String, Object>();
		if(AOSUtils.isEmpty(inDto.getString("order_id")) || AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//参数传递错误
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		map.put("user_id", httpModel.getAttribute("user_id").toString());
		map.put("order_sn", inDto.getString("order_id"));
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto(map));
		if(AOSUtils.isNotEmpty(zjcOrderPO)&&zjcOrderPO.getPoints_pay_status()==0&&"mixed_payment".equals(zjcOrderPO.getPay_code())){
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
			return AOSJson.toJson(msgVo);
		}
		if(zjcOrderPO.getPoints_pay_status() == 0){
			List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
			ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
	 	    zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
	 	    List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
	 	    if(goodsd.get(0).getIs_on_sale() == 0){//商品已下架，不能下单
		   		msgVo.setMsg(Apiconstant.Commodities_have_shelves.getName());
		   		msgVo.setCode(Apiconstant.Commodities_have_shelves.getIndex());
		    	return AOSJson.toJson(msgVo);
			}
	 	    if(goodsd.get(0).getStore_count()<orderGood.get(0).getGoods_num()){//库存不足
		   		msgVo.setMsg(Apiconstant.Insufficient_inventory.getName());
		   		msgVo.setCode(Apiconstant.Insufficient_inventory.getIndex());
	    		return AOSJson.toJson(msgVo);
	        }
		}
		Map<String, String> params = new HashMap<String, String>();
		String notify_url = "https://zjc1518.com/aosuite/notokenapi/app/v1/alipayOrderNotify.jhtml";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sdf.format(new Date());
		params.put("app_id", AlipayConstant.APP_ID);
		params.put("method", AlipayConstant.METHOD);
		params.put("format", AlipayConstant.FORMATE);
		params.put("charset", AlipayConstant.CHARSET);
		params.put("sign_type", AlipayConstant.SIGNTYPE);
		params.put("timestamp", timestamp);
		params.put("version", AlipayConstant.VERSION);
		params.put("notify_url", notify_url);

		Map<String, String> busMap = new HashMap<String, String>();
		busMap.put("body", "商品易货订单：" + zjcOrderPO.getOrder_sn());
		busMap.put("subject","店铺名称：" +  zjcOrderPO.getStore_name()+"收货人:"+zjcOrderPO.getConsignee()+"收货人电话:"+zjcOrderPO.getMobile());
		busMap.put("out_trade_no", zjcOrderPO.getOrder_sn());
		busMap.put("timeout_express", AlipayConstant.EXPIRE_TIME);
		busMap.put("total_amount", zjcOrderPO.getCash().toString());
		busMap.put("seller_id", AlipayConstant.SELLER_ID);
		busMap.put("product_code", AlipayConstant.PRODUCTCODE);
		busMap.put("subject","店铺名称：" +  zjcOrderPO.getStore_name()+"收货人:"+zjcOrderPO.getConsignee()+"收货人电话:"+zjcOrderPO.getMobile());
		String busConente = AOSJson.toJson(busMap);
		params.put("biz_content", busConente);
		String sign = getSign(params);
		params.put("sign", sign);
		msgVo.setData(params);
		msgVo.setCode(Apiconstant.Do_Success.getIndex());
		msgVo.setMsg(Apiconstant.Do_Success.getName());
		return AOSJson.toJson(msgVo);
	}

	/**
	 * 商品订单在线支付回调验签
	 * 
	 * @param httpModel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public String alipayOrderNotify(HttpModel httpModel) throws UnsupportedEncodingException {
		logger.info("====================支付宝支付回调json串1:");
		String result = "";
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = httpModel.getRequest().getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "GBK");
			params.put(name, valueStr);
		}
		logger.info("====================支付宝支付回调json串："+params.toString());
		String trade_status = new String(httpModel.getRequest().getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		String out_trade_no = new String(httpModel.getRequest().getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		logger.info("==========================支付宝支付状态码：" + trade_status + ",订单编号：" + out_trade_no);
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params,
					AlipayConstant.ALIPAY_PUBLIC_KEY, AlipayConstant.CHARSET,
					AlipayConstant.SIGNTYPE);
			logger.info("=====================支付宝支付订单"+out_trade_no +"验签结果："+AOSJson.toJson(signVerified));
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.error("====================支付宝支付订单"+out_trade_no + "验签异常："+e.getErrMsg());
			result = "fail";// 验签发生异常,则直接返回失败
		}
		// 调用SDK验证签名
		if (signVerified) {
			if (trade_status.equals("TRADE_SUCCESS")) {
				// 商户订单号
				ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto("order_sn", out_trade_no));
				if(zjcOrderPO != null && !AlipayConstant.PAY_SUCCEESS.equals(zjcOrderPO.getPay_status())){//验证订单是否已支付
					if(AlipayConstant.MIXED_PAY_CODE.equals(zjcOrderPO.getPay_code())){//混合支付
						zjcOrderPO.setPay_time(new Date());
						zjcOrderPO.setPay_code(AlipayConstant.MIXED_PAY_CODE);
						zjcOrderPO.setPay_name(AlipayConstant.MIXED_PAY_NAME);
						zjcOrderPO.setPay_status(1);//支付状态改为已支付
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
							
							result = "success";
						} catch(Exception e) {
							logger.error("--------------混合支付支付宝异常导致订单"+out_trade_no+"出现异常："+e.getMessage());
							result = "fail";
						}
					} else {
						zjcOrderPO.setPay_code(AlipayConstant.PAY_CODE);
						zjcOrderPO.setPay_name(AlipayConstant.PAY_NAME);
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
						    	result = "fail";
						    	return result;
						    }
						   //限制商品购买次数
						   ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
				    	   Map<String,Object> map = new HashMap<String,Object>();
						   map.put("user_id", httpModel.getAttribute("user_id"));
						   map.put("goods_id", orderGood.get(0).getGoods_id());
						   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
						   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
							   result = "fail";
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
						   /*//将一二级放入消息队列
						   if(zjcOrderPO.getPay_code().equals("cash")||zjcOrderPO.getPay_code().equals("alipay")||zjcOrderPO.getPay_code().equals("wxpay")){
						    	//存消息队列
						    	Sender Sender=new Sender();
						    	Sender.send(zjcOrderPO.getOrder_id().toString(),zjcOrderPO.getTotal_amount().toString(),zjcOrderPO.getUser_id().toString());
						    }*/
						   
							result = "success";
						} catch(Exception e){
							e.printStackTrace();
							result = "fail";
							logger.error("--------------现金支付支付宝异常导致订单"+out_trade_no+"出现异常："+e.getMessage());
						}
					}
					
				}
				//存消息队列
		    	Sender Sender=new Sender();
		    	Sender.send(zjcOrderPO.getOrder_id().toString(),zjcOrderPO.getTotal_amount().toString(),zjcOrderPO.getUser_id().toString());
			}
		} else {
			logger.info("=====================验证失败,不去更新状态========================");
			result = "fail";
		}
		return result;
	}

	/**
	 * 对支付参数信息进行签名
	 *
	 * @param map
	 *            待签名授权信息
	 * @return
	 * @throws AlipayApiException
	 */
	public String getSign(Map<String, String> map) throws AlipayApiException {
		List<String> keys = new ArrayList<>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			authInfo.append(buildKeyValue(key, value, false));
			authInfo.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		authInfo.append(buildKeyValue(tailKey, tailValue, false));

		// String oriSign = SignUtils.sign(authInfo.toString(), rsaKey);
		String oriSign = SignUtils.sign(authInfo.toString(),
				AlipayConstant.APP_PRIVATE_KEY, true);
		String encodedSign = "";

		try {
			encodedSign = URLEncoder.encode(oriSign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedSign;
	}

	/**
	 * 拼接键值对
	 *
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * ios支付宝回调
	 * 
	 * @param httpModel
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String alipayIOSOrderNotify(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO();
		Dto dto = httpModel.getInDto();
		String out_trade_no = dto.getString("order_id");
		String random = dto.getString("random");
		String sign = dto.getString("sign");
		//拼装校验包
		String design=out_trade_no+"zjc_1815"+random;
		design=AOSCodec.md5(design);
		boolean flag = false;
		if(design.equals(sign)){//验签通过
			flag = true;
		}
		// 验证签名通过
		if (flag) {
			logger.info("ios验签成功");
			// 商户订单号
			ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto("order_sn", out_trade_no));
			logger.info("是否存在该订单" + AOSJson.toJson(zjcOrderPO));
			if(zjcOrderPO != null && !AlipayConstant.PAY_SUCCEESS.equals(zjcOrderPO.getPay_status())){//验证订单是否已支付
				if(AlipayConstant.MIXED_PAY_CODE.equals(zjcOrderPO.getPay_code())){//混合支付
					zjcOrderPO.setPay_time(new Date());
					zjcOrderPO.setPay_code(AlipayConstant.MIXED_PAY_CODE);
					zjcOrderPO.setPay_name(AlipayConstant.MIXED_PAY_NAME);
					zjcOrderPO.setPay_status(1);//支付状态改为已支付
					// 支付成功后修改充值状态
					int row = zjcOrderDao.updateByKey(zjcOrderPO);
					if (row == 1) {
						
						List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
						
						//查询是否是小平台商品
				        XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", orderGood.get(0).getGoods_id()));
				        if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//如果是小平台商品，支付成功后，调用小平台系统接口
				          zjcTurnIntoUtil.xptCreateOrder(zjcOrderPO.getOrder_id());
				        }
						
						Dto inDto = Dtos.newDto();
						//现金支付成功，修改返分状态为可执行
						inDto.put("order_id", zjcOrderPO.getOrder_id());
						sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateByRelationId", inDto);
						//zjcTurnIntoUtil.shop_order_cash(zjcOrderPO.getOrder_id()); //商城购物记录发送
						msgVo.setCode(Apiconstant.Do_Success.getIndex());
						msgVo.setMsg(Apiconstant.Do_Success.getName());
					} else {
						msgVo.setCode(Apiconstant.Do_Fails.getIndex());
						msgVo.setMsg(Apiconstant.Do_Fails.getName());
					}
				} else {
					zjcOrderPO.setPay_code(AlipayConstant.PAY_CODE);
					zjcOrderPO.setPay_name(AlipayConstant.PAY_NAME);
					zjcOrderPO.setPay_status(AlipayConstant.PAY_SUCCEESS);
					zjcOrderPO.setPay_time(new Date());
					zjcOrderPO.setOrder_status(0);//支付成功后订单状态改为1 表示待收货
					// 支付成功后修改充值状态
					try{
						zjcOrderDao.updateByKey(zjcOrderPO);
						
						
						List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
						ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
						zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
						List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
						//更改商品库存销量信息
						zjcGoodsPO.setGoods_id(goodsd.get(0).getGoods_id());
						//减少库存
						int Goods_nums = orderGood.get(0).getGoods_num();
						int store_count=goodsd.get(0).getStore_count()-Goods_nums;
						//增加销量
						int sales_sum=goodsd.get(0).getSales_sum()+Goods_nums;
						zjcGoodsPO.setStore_count(store_count);
						zjcGoodsPO.setSales_sum(sales_sum);
						ZjcGoodsDao.updateByKey(zjcGoodsPO);
						
						//查询是否是小平台商品
				        XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", orderGood.get(0).getGoods_id()));
				        if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//如果是小平台商品，支付成功后，调用小平台系统接口
				          zjcTurnIntoUtil.xptCreateOrder(zjcOrderPO.getOrder_id());
				        }
						
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
						zjcTurnIntoUtil.order_online_pay(zjcOrderPO.getOrder_id());
						msgVo.setCode(Apiconstant.Do_Success.getIndex());
						msgVo.setMsg(Apiconstant.Do_Success.getName());
					} catch(Exception e){
						e.printStackTrace();
						msgVo.setCode(Apiconstant.Do_Fails.getIndex());
						msgVo.setMsg(Apiconstant.Do_Fails.getName());
					}
				}
				
			}
		} else {
			logger.info("验证失败,不去更新状态");
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}
   
	
	/**
	 * 商品订单在线支付回调验签
	 * 
	 * @param httpModel
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException 
	 */
	public  String huiOrderNotify(HttpModel httpModel) throws UnsupportedEncodingException{
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"YYYYMMDDHHMMSS");
		String version="1.0.0";//版本号
		String transCode="8888";//交易代码
		String merchantId="888888888888888";//商户编号
		String merOrderNum=httpModel.getRequest().getParameter("merOrderNum");//商户订单号
		String bussId="100000";//业务代码
		String tranAmt="10";//httpModel.getRequest().getParameter("merOrderNum");//交易金额(单位： 分)
		String sysTraceNum="20180117165233564723";//httpModel.getRequest().getParameter("sysTraceNum");//商户请求流水号
		String tranDateTime=httpModel.getRequest().getParameter("tranDateTime");//交易时间
		String currencyType="156";//货币代码
		String merURL="http://106.14.16.202:80/aosuite/notokenapi/app/v1/HuipayNotify.jhtml";//商户返回页面
		String backURL="http://106.14.16.202:80/aosuite/notokenapi/app/v1/HuipayNotify.jhtml";//回调商户地址
		String orderInfo=AOSCodec.byteArrayToHexString("".getBytes("UTF-8"));//订单信息
		String userId="60047";//httpModel.getRequest().getParameter("tranDateTime");//用户 ID
		String userPhoneHF="13552535506";//httpModel.getRequest().getParameter("userPhoneHF");//银行卡绑定手机号
		String userAcctNo="6216261000000000018";//httpModel.getRequest().getParameter("userAcctNo");//银行卡号
		String userNameHF=AOSCodec.byteArrayToHexString("全渠道".getBytes("UTF-8"));//开户名（姓名）
		String quickPayCertNo="341126197709218366";//httpModel.getRequest().getParameter("quickPayCertNo");//身份证号
		String userIp="";//httpModel.getRequest().getParameter("userIp");//订单用户 IP
		String bankId="888880170122900";//支付方式代码
		String stlmId="";
		String entryType="1";
		String txnString=
				version+"|"+transCode+"|"+merchantId+"|"+merOrderNum+"|"+bussId
				+"|"+tranAmt+"|"+sysTraceNum+"|"+tranDateTime+"|"+currencyType +"|"+merURL+"|"+backURL+"|"+orderInfo+"|"+userId;
		String dateKey="8EF53C251102A4E6";
		String signValue = AOSCodec.md5(txnString+dateKey);
		Map<String, String> map=new HashMap<>();
		map.put("version", version);
		map.put("transCode", transCode);
		map.put("merchantId", merchantId);
		map.put("merOrderNum", merOrderNum);
		map.put("bussId", bussId);
		map.put("tranAmt", tranAmt);
		map.put("sysTraceNum", sysTraceNum);
		map.put("tranDateTime", tranDateTime);
		map.put("currencyType", currencyType);
		map.put("merURL", merURL);
		map.put("backURL", backURL);
		map.put("orderInfo", orderInfo);
		map.put("userId", userId);
		map.put("userPhoneHF", userPhoneHF);
		map.put("userAcctNo", userAcctNo);
		map.put("userNameHF", userNameHF);
		map.put("quickPayCertNo", quickPayCertNo);
		map.put("userIp", userIp);
		map.put("bankId", bankId);
		map.put("stlmId",stlmId);
		map.put("entryType", entryType);
		map.put("attach", "");
		map.put("reserver1", "");
		map.put("reserver2", "");
		map.put("reserver3", "");
		map.put("reserver4", "7");
		map.put("txnString", txnString);
		map.put("dateKey", dateKey);
		map.put("signValue", signValue);
		MessageVO msgVO = new MessageVO();
		msgVO.setData(map);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		return AOSJson.toJson(msgVO);
	}
	
	
	public String HuipayNotify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		/*YtPO his=AOSJson.fromJson(request.getParameter("json").toString(), YtPO.class);
		String transCode=his.getTransCode();//版本号
		String merchantId=his.getMerchantId();//版本号
		String respCode=his.getRespCode();//版本号
		String sysTraceNum=his.getSysTraceNum();//版本号
		String merOrderNum=his.getMerOrderNum();//版本号
		String orderId=his.getOrderId();//版本号
		String bussId=his.getBussId();//版本号
		String tranAmt=his.getTranAmt();//版本号
		String orderAmt=his.getOrderAmt();//版本号
		String bankFeeAmt=his.getBankFeeAmt();//版本号
		String integralAmt=his.getIntegralAmt();//版本号
		String vaAmt=his.getVaAmt();//版本号
		String bankAmt=his.getBankAmt();//版本号
		String bankId=his.getBankId();//版本号
		String integralSeq=his.getIntegralSeq();//版本号
		String vaSeq=his.getVaSeq();//版本号
		String bankSeq=his.getBankSeq();//版本号
		String tranDateTime=his.getTranDateTime();//版本号
		String payMentTime=his.getPayMentTime();//版本号
		String settleDate=his.getSettleDate();//版本号
		String currencyType=his.getCurrencyType();//版本号
		String orderInfo=his.getOrderInfo();//版本号
		String userId=his.getUserId();//版本号
		String datakey="8EF53C251102A4E6";
		String signValue=his.getSignValue();//版本号
		
		String txnString=transCode+"|"+merchantId+"|"+respCode+"|"
				+sysTraceNum+"|"+merOrderNum+"|"+orderId+"|"+bussId+"|"
				+tranAmt+"|"+orderAmt+"|"+bankFeeAmt+"|"+integralAmt+"|"
				+vaAmt+"|"+bankAmt+"|"+bankId+"|"+integralSeq+"|"+vaSeq +"|"+bankSeq+"|"
				+tranDateTime+"|"+payMentTime+"|"
				+ settleDate+"|"
				+currencyType+"|"+orderInfo+"|"+userId;
				String sign= AOSCodec.md5(txnString+datakey);
				String result = "";
				if("0000".equals(his.getRespCode())){
					
				
				if(sign.equals(signValue)){
					logger.info("验签成功");
						// 商户订单号
						ZjcRechargePO zjcRechargePO = zjcRechargeDao.selectOne(Dtos
								.newDto("order_sn", merOrderNum));
						if(zjcRechargePO != null && !AlipayConstant.PAY_SUCCEESS.equals(zjcRechargePO.getPay_status())){//验证订单是否已支付
							logger.info("是否存在该订单" + AOSJson.toJson(zjcRechargePO));
							ZjcRechargePO newRechargePO = new ZjcRechargePO();
							newRechargePO.copyProperties(zjcRechargePO);
							// 支付成功后修改充值状态
							newRechargePO.setPay_code(AlipayConstant.PAY_CODE);
							newRechargePO.setPay_name(AlipayConstant.PAY_NAME);
							newRechargePO.setPay_status(AlipayConstant.PAY_SUCCEESS);
							int row = zjcRechargeDao.updateByKey(newRechargePO);
							logger.info("修改订单状态成功" + row);
							if (row == 1) {
								// 充值成功后，充值账户积分的增加
								ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao
										.selectOne(Dtos.newDto("user_id",
												zjcRechargePO.getUser_id()));
								zjcUsersAccountInfoPO
										.setMake_over_integral(zjcUsersAccountInfoPO
												.getMake_over_integral()
												+ Integer.parseInt(zjcRechargePO
														.getBuy_points()));
								zjcUsersAccountInfoDao
										.updateByKey(zjcUsersAccountInfoPO);
								result = "success";
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("logType", "易物券置换");
								map.put("user_id", zjcRechargePO.getUser_id());
								map.put("pay_points", zjcRechargePO.getBuy_points());
								map.put("pay_type", zjcRechargePO.getPay_name());
								map.put("due_tc_points", "0");
								map.put("now_points",zjcUsersAccountInfoPO.getPay_points());
								map.put("now_make_over_integral", zjcUsersAccountInfoPO.getMake_over_integral());
								apiLogService.saveLog(map);
								logger.info("修改数据成功" + AOSJson.toJson(result));
							} else {
								result = "fail";
							}
						} 
					}
				}else {
					result = "fail";
				}*/
				return "success";
				}
	

}
