/**
 * 
 */
package com.api.unionpay.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.AlipayConstant;
import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.unionpay.common.SDKConstants;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcRechargeDao;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.order.dao.po.ZjcRechargePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value = "unionpayService")
public class UnionpayService {

	private static final Logger logger = LoggerFactory.getLogger(UnionpayService.class);
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcRechargeDao zjcRechargeDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	
	@Autowired
	private ApiLogService apiLogService;
	
	/**
	 * 易物券充值订单银联sign
	 * 
	 * @param httpModel
	 * @return
	 */
	public String sign(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto inDto = httpModel.getInDto();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_sn", inDto.getString("order_id"));
		map.put("user_id", httpModel.getAttribute("user_id").toString());
		ZjcRechargePO zjcRechargePO = zjcRechargeDao.selectOne(Dtos.newDto(map));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, String> res = new HashMap<String, String>();
		res.put("version", "5.0.0");// 版本号
		res.put("encoding", "utf-8");// 编码方式
		res.put("txnType", "01");// //交易类型
		res.put("txnSubType", "01");// //交易子类
		res.put("bizType", "000201");// //业务类型
		res.put("signMethod", "01");// //签名方法
		res.put("channelType", "08");// 渠道类型，07-PC，08-手机
		res.put("accessType", "0");// //接入类型
		res.put("merId", "802110048160935");// //商户代码
		res.put("orderId", zjcRechargePO.getOrder_sn());// //商户订单号，8-40位数字字母
		res.put("txnTime", sdf.format(new Date()));// 订单发送时间
		Double daccount = Double.parseDouble(zjcRechargePO.getAccount());
		int iaccount = (int)(daccount * 100);
		res.put("txnAmt", iaccount +"");// 交易金额，单位分
		res.put("currencyCode", "156");// 交易币种
		res.put("orderDesc", "置换易物券");// 交易币种
		res.put(SDKConstants.param_backUrl, SDKConstants.backUrl);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		String SDK_App_Request_Url = "https://gateway.95516.com/gateway/api/appTransReq.do";
		Map<String, String> code = AcpService.post(AcpService.sign(res, ""),SDK_App_Request_Url,"utf-8");
		logger.info("code",code);
		msgVO.setData(code.get("tn"));
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 易物券充值订单银联回调
	 * @param httpModel
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String backRcvResponse(HttpModel httpModel) throws UnsupportedEncodingException{
		String result = "";
		logger.info("BackRcvResponse接收后台通知开始");
		String encoding = httpModel.getRequest().getParameter(SDKConstants.param_encoding);
		// 获取银联通知服务器发送的后台通知参数
		Map<String, String> reqParam = getAllRequestParam(httpModel.getRequest());
		logger.info(AOSJson.toJson(reqParam));
		Map<String, String> valideData = null;
		if (null != reqParam && !reqParam.isEmpty()) {
			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
			valideData = new HashMap<String, String>(reqParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes(encoding), encoding);
				valideData.put(key, value);
			}
		}
		// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
		if (!AcpService.validate(valideData, encoding)) {
			logger.info("验证签名结果[失败].");
			// 验签失败，需解决验签问题
			result = "false";
		} else {
			logger.info("验证签名结果[成功].");
			// 【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
			String orderId = valideData.get("orderId"); // 获取后台通知的数据，其他字段也可用类似方式获取
			ZjcRechargePO zjcRechargePO = zjcRechargeDao.selectOne(Dtos.newDto("order_sn", orderId));
			//String respCode = valideData.get("respCode");
			// 判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
			if (zjcRechargePO != null && AlipayConstant.PAY_SUCCEESS.equals(zjcRechargePO.getPay_status())) {
				ZjcRechargePO newPO = new ZjcRechargePO();
				newPO.copyProperties(zjcRechargePO);
				newPO.setPay_code(SDKConstants.PAY_CODE);
				newPO.setPay_name(SDKConstants.PAY_NAME);
				newPO.setPay_status(AlipayConstant.PAY_SUCCEESS);
				int row = zjcRechargeDao.updateByKey(newPO);
				if (row == 1) {
					logger.info("修改状态成功，对账号进行易物券充值");
					// 充值成功后，充值账户积分的增加
					ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",zjcRechargePO.getUser_id()));
					zjcUsersAccountInfoPO.setMake_over_integral(zjcUsersAccountInfoPO.getMake_over_integral() + Integer.parseInt(zjcRechargePO.getBuy_points()));
					zjcUsersAccountInfoDao.updateByKey(zjcUsersAccountInfoPO);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("logType", "易物券置换");
					map.put("user_id", zjcRechargePO.getUser_id());
					map.put("pay_points", zjcRechargePO.getBuy_points());
					map.put("pay_type", zjcRechargePO.getPay_name());
					map.put("due_tc_points", "0");
					map.put("now_points",zjcUsersAccountInfoPO.getPay_points());
					map.put("now_make_over_integral", zjcUsersAccountInfoPO.getMake_over_integral());
					apiLogService.saveLog(map);
					result = "ok";
				}
			}
		}
		logger.info("返回result=" + result);
		// 返回给银联服务器http 200 状态码
		return result;
	}
	
	/**
	 * 商品订单在线支付银联sign
	 * 
	 * @param httpModel
	 * @return
	 */
	public String signOrder(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		msgVO.setCode(-1);
		msgVO.setMsg("暂不支持银联支付");
		msgVO.setData("");
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_sn", inDto.getString("order_id"));
		map.put("user_id", httpModel.getAttribute("user_id").toString());
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto(map));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, String> res = new HashMap<String, String>();
		res.put("version", "5.0.0");// 版本号
		res.put("encoding", "utf-8");// 编码方式
		res.put("txnType", "01");// //交易类型
		res.put("txnSubType", "01");// //交易子类
		res.put("bizType", "000201");// //业务类型
		res.put("signMethod", "01");// //签名方法
		res.put("channelType", "08");// 渠道类型，07-PC，08-手机
		res.put("accessType", "0");// //接入类型
		res.put("merId", "802110048160935");// //商户代码
		res.put("orderId", zjcOrderPO.getOrder_sn());// //商户订单号，8-40位数字字母
		res.put("txnTime", sdf.format(new Date()));// 订单发送时间
		Double daccount = Double.parseDouble(zjcOrderPO.getCash().toString());
		int iaccount = (int)(daccount * 100);
		res.put("txnAmt", iaccount +"");// 交易金额，单位分
		res.put("currencyCode", "156");// 交易币种
		res.put("orderDesc", "商品易货订单：" + zjcOrderPO.getOrder_sn());// 交易币种
		res.put(SDKConstants.param_backUrl, SDKConstants.orderbackUrl);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		String SDK_App_Request_Url = "https://gateway.95516.com/gateway/api/appTransReq.do";
		Map<String, String> code = AcpService.post(AcpService.sign(res, ""),SDK_App_Request_Url,"utf-8");
		msgVO.setData(code.get("tn"));*/
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 商品订单在线支付银联回调
	 * @param httpModel
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public String backRcvResponseOrder(HttpModel httpModel) throws UnsupportedEncodingException, ParseException{
		String result = "";
		logger.info("BackRcvResponse接收后台通知开始");
		String encoding = httpModel.getRequest().getParameter(SDKConstants.param_encoding);
		// 获取银联通知服务器发送的后台通知参数
		Map<String, String> reqParam = getAllRequestParam(httpModel.getRequest());
		logger.info(AOSJson.toJson(reqParam));
		Map<String, String> valideData = null;
		if (null != reqParam && !reqParam.isEmpty()) {
			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
			valideData = new HashMap<String, String>(reqParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes(encoding), encoding);
				valideData.put(key, value);
			}
		}
		// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
		if (!AcpService.validate(valideData, encoding)) {
			logger.info("验证签名结果[失败].");
			// 验签失败，需解决验签问题
			result = "false";
		} else {
			logger.info("验证签名结果[成功].");
			// 【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
			String orderId = valideData.get("orderId"); // 获取后台通知的数据，其他字段也可用类似方式获取
			ZjcOrderPO zjcOrderPO = zjcOrderDao.selectOne(Dtos.newDto("order_sn", orderId));
			//String respCode = valideData.get("respCode");
			// 判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
			logger.info("是否存在该订单" + AOSJson.toJson(zjcOrderPO));
			if (zjcOrderPO != null && AlipayConstant.PAY_SUCCEESS.equals(zjcOrderPO.getPay_status())) {
				if (zjcOrderPO != null) {
					if(SDKConstants.MIXED_PAY_CODE.equalsIgnoreCase(zjcOrderPO.getPay_code())){//混合支付
						zjcOrderPO.setPay_code(SDKConstants.MIXED_PAY_CODE);
						zjcOrderPO.setPay_name(SDKConstants.MIXED_PAY_NAME);
						zjcOrderPO.setPay_status(1);//支付状态改为已支付
						zjcOrderPO.setPay_time(new Date());
						// 支付成功后修改充值状态
						int row = zjcOrderDao.updateByKey(zjcOrderPO);
						if (row == 1) {
							zjcTurnIntoUtil.shop_order_cash(zjcOrderPO.getOrder_id()); //商城购物记录发送
							result = "success";
						} else {
							result = "fail";
						}
					} else {
						zjcOrderPO.setPay_code(SDKConstants.PAY_CODE);
						zjcOrderPO.setPay_name(SDKConstants.PAY_NAME);
						zjcOrderPO.setPay_status(AlipayConstant.PAY_SUCCEESS);
						zjcOrderPO.setOrder_status(1);//支付成功后订单状态改为1 表示待收货
						int row = zjcOrderDao.updateByKey(zjcOrderPO);
						if (row == 1) {
							logger.info("订单状态修改成功");
							 //商品反分处理
							zjcTurnIntoUtil.order_online_pay(zjcOrderPO.getOrder_id());
							result = "ok";
						}
					}
				}
			}
		}
		logger.info("返回result=" + result);
		// 返回给银联服务器http 200 状态码
		return result;
	}
	
	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				// System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
	
}
