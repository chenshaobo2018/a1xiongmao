/**
 * 
 */
package com.api.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.api.alipay.po.BizContent;


/**
 * 支付公用方法
 * @author Administrator
 *
 */
public class AlipayUtil {

	/**
	 * Url特殊字符处理
	 * @param sign
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String sign) throws UnsupportedEncodingException {
		return URLEncoder.encode(sign, "utf-8").replace("+", "%20");
	}

	/**
	 * 对象转换成json
	 * @param content
	 * @return
	 */
	public static String toJson(BizContent content) {
		String context = "";
		context += "{" + "\"timeout_express\":\""
				+ content.getTimeout_express() + "\"," + "\"seller_id\":\""
				+ content.getSeller_id() + "\"," + "\"product_code\":\""
				+ content.getProduct_code() + "\"," + "\"total_amount\":\""
				+ content.getTotal_amount() + "\"," + "\"subject\":\""
				+ content.getSubject() + "\"," + "\"body\":\""
				+ content.getBody() + "\"," + "\"out_trade_no\":\""
				+ content.getOut_trade_no() + "\"}";
		return context;
	}
}
