/**
 * 
 */
package com.api.wxpay.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.order.rabbitmq.Sender;
import com.api.wxpay.po.WeixinPayPo;
import com.api.wxpay.po.WeixinPayUtil;
import com.api.wxpay.po.WxPayConstantUtil;
import com.api.wxpay.po.XMLUtil;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value = "wxPayService")
public class WxPayService {
	private static final Logger logger = LoggerFactory
			.getLogger(WxPayService.class);
	
	@Autowired
	private SqlDao sqlDao;

	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ZjcOrderGoodsDao ZjcOrderGoodsDao;
	
	@Autowired
	private ZjcGoodsDao ZjcGoodsDao;
	
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	
	/**
	 * 微信下单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	public String weixinPay(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException{
		HttpModel httpModel = new HttpModel(request, response);
		MessageVO msgVo = new MessageVO();
		Dto dto = httpModel.getInDto();
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("order_sn", dto.getString("order_id"));
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto(orderMap));
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
		
		//微信支付
	    String nonce_str = WeixinPayUtil.genNonceStr(); //随机数
		int price=zjcOrderPO.getCash().multiply(new BigDecimal(100)).intValue();//微信支付以分为计算单位
		WeixinPayPo weixin=new WeixinPayPo();
		weixin.setAppid(WxPayConstantUtil.APP_ID);// 公众账号ID
		weixin.setMch_id(WxPayConstantUtil.MCH_ID);//商户号
		weixin.setNonce_str(nonce_str);//随机数
		//weixin.setDetail("");//商品详情
		weixin.setSpbill_create_ip(ParameterUtil.getIpAddr(request));// 终端IP
		weixin.setOut_trade_no(zjcOrderPO.getOrder_sn());// 商户订单号
		weixin.setNotify_url(WxPayConstantUtil.NOTIFY_URL);//回调方法
		weixin.setBody(WxPayConstantUtil.BODY);//商品描述
		weixin.setTrade_type(WxPayConstantUtil.TRADE_TYPE);//交易类型
		weixin.setTotal_fee(price);//订单总金额
		
		//Map<String, Object> time=WeixinPayUtil.getTime(new Date());//获取订单创建时间
		
		SortedMap<String, Object> map=new TreeMap<String, Object>();
		map = WeixinPayUtil.transBean2Map(weixin);
		//获取sign
		String sign = WeixinPayUtil.createSign("UTF-8",map,WxPayConstantUtil.API_KEY);
		weixin.setSign(sign);
		SortedMap<String, Object> parm=WeixinPayUtil.transBean2Map(weixin);
		String str=WeixinPayUtil.getRequestXml(parm);
		//返回xml
		String reXml=WeixinPayUtil.httpPost(WxPayConstantUtil.URL, str);
		Map<String, String> m=XMLUtil.doXMLParse(reXml);
		if("SUCCESS".equals(m.get("return_code"))&&"SUCCESS".equals(m.get("result_code"))){
			String prepayId=m.get("prepay_id");
			m=null;
			map.clear();
			map.put("appid", WxPayConstantUtil.APP_ID);
			map.put("partnerid", WxPayConstantUtil.MCH_ID);
			map.put("prepayid",prepayId);
			map.put("package","Sign=WXPay");
			map.put("noncestr", WeixinPayUtil.genNonceStr());
			map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
			map.put("sign", WeixinPayUtil.createSign("UTF-8",map,WxPayConstantUtil.API_KEY));
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(map);
		} else {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(m.get("err_code_des"));
		}
		
		return AOSJson.toJson(msgVo);
	}
	
	
	/**
	 * 微信支付成功回调地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String weixinNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		 System.out.println("===============微信回调成功！================");
		 //读取参数  
       InputStream inputStream ;  
       StringBuffer sb = new StringBuffer();  
       inputStream = request.getInputStream();  
       String s ;  
       BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
       while ((s = in.readLine()) != null){  
           sb.append(s);  
       }  
       in.close();  
       inputStream.close();  
       
       System.out.println("===============微信回调成功2！================"+in);
       
       //解析xml成map  
       Map<String, String> m = new HashMap<String, String>();  
       m = XMLUtil.doXMLParse(sb.toString());  
         
       //过滤空 设置 TreeMap  
       SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
       Iterator it = m.keySet().iterator();  
       while (it.hasNext()) {  
           String parameter = (String) it.next();  
           String parameterValue = m.get(parameter);  
             
           String v = "";  
           if(null != parameterValue) {  
               v = parameterValue.trim();  
           }  
           packageParams.put(parameter, v);  
       }  
         
       //判断签名是否正确  
       if(WeixinPayUtil.isTenpaySign("UTF-8", packageParams,WxPayConstantUtil.API_KEY)) {  
           //------------------------------  
           //处理业务开始  
           //------------------------------  
           String resXml = "";  
           if("SUCCESS".equals((String)packageParams.get("result_code"))){ //验签成功 
               // 这里是支付成功  
               //////////执行自己的业务逻辑////////////////  
               String out_trade_no = (String)packageParams.get("out_trade_no");  
               /** 处理订单逻辑 */
                // 查询订单
				ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto("order_sn", out_trade_no));
				logger.info("是否存在该订单" + AOSJson.toJson(zjcOrderPO));
				if(zjcOrderPO != null && !WxPayConstantUtil.PAY_SUCCEESS.equals(zjcOrderPO.getPay_status())){//验证订单是否已支付
					if(WxPayConstantUtil.MIX_PAY_CODE.equals(zjcOrderPO.getPay_code())){//混合支付
						zjcOrderPO.setPay_time(new Date());
						zjcOrderPO.setPay_code(WxPayConstantUtil.MIX_PAY_CODE);
						zjcOrderPO.setPay_name(WxPayConstantUtil.MIX_PAY_NAME);
						zjcOrderPO.setPay_status(1);//支付状态改为已支付
						// 支付成功后修改充值状态
						try{
							zjcOrderDao.updateByKey(zjcOrderPO);
							Dto inDto = Dtos.newDto();
							//现金支付成功，修改返分状态为可执行
							inDto.put("order_id", zjcOrderPO.getOrder_id());
							sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateByRelationId", inDto);
							//通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
							 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
				                       + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> "; 
						} catch(Exception e) {
							 e.printStackTrace();
							 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
				                       + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
						}
					} else {
						zjcOrderPO.setPay_code(WxPayConstantUtil.PAY_CODE);
						zjcOrderPO.setPay_name(WxPayConstantUtil.PAY_NAME);
						zjcOrderPO.setPay_status(WxPayConstantUtil.PAY_SUCCEESS);
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
							//将一二级放入消息队列
							if(zjcOrderPO.getPay_code().equals("cash")||zjcOrderPO.getPay_code().equals("alipay")||zjcOrderPO.getPay_code().equals("wxpay")){
						    	//存消息队列
						    	Sender Sender=new Sender();
						    	Sender.send(zjcOrderPO.getOrder_id().toString(),zjcOrderPO.getTotal_amount().toString(),zjcOrderPO.getUser_id().toString());
						    }
							
							//通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
							 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
				                       + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
						} catch(Exception e){
							e.printStackTrace();
							 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
				                       + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
						}
					}
					
				}
           } else {  
               resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                       + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
           }  
           //------------------------------  
           //处理业务完毕  
           //------------------------------  
           BufferedOutputStream out = new BufferedOutputStream(  
                   response.getOutputStream());  
           out.write(resXml.getBytes());  
           out.flush();  
           out.close();
           return packageParams.toString();
       } else{  
           return "fail";
       }
	}





	/**
	 * 微信支付-查询订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String weixinQuery(HttpServletRequest request,
			HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		MessageVO msgVo = new MessageVO();
		Dto dto = httpModel.getInDto();
		SortedMap<String, Object> map=new TreeMap<String, Object>();
		map.put("appid", WxPayConstantUtil.APP_ID);//微信开放平台审核通过的应用APPID
		map.put("mch_id", WxPayConstantUtil.MCH_ID);//微信支付分配的商户号
		map.put("out_trade_no", dto.getString("order_id"));
		map.put("nonce_str", WeixinPayUtil.genNonceStr());
		map.put("sign", WeixinPayUtil.createSign("UTF-8",map,WxPayConstantUtil.API_KEY));
		String str=WeixinPayUtil.getRequestXml(map);
		//返回xml
		String reXml=WeixinPayUtil.httpPost(WxPayConstantUtil.QUERY_URL, str);
		try {
			Map<String, String> m=XMLUtil.doXMLParse(reXml);
			if("SUCCESS".equals(m.get("return_code")) && "SUCCESS".equals(m.get("result_code")) && "SUCCESS".equals(m.get("trade_state"))){
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			} else {
				msgVo.setCode(Apiconstant.Do_Fails.getIndex());
				msgVo.setMsg(m.get("trade_state_desc"));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		} catch (IOException e) {
			e.printStackTrace();
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
}
