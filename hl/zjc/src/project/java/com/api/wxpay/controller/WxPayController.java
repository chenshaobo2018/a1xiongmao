/**
 * 
 */
package com.api.wxpay.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;

import com.alipay.api.AlipayApiException;
import com.api.wxpay.service.WxPayService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class WxPayController {

	@Autowired
	private WxPayService wxPayService;
	
	/**
	 * 商品订单微信支付sign
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = "/app/v1/weixinPay")
	public void weixinPay(HttpServletRequest request, HttpServletResponse response) {
		String outMsg;
		try {
			outMsg = wxPayService.weixinPay(request, response);
			WebCxt.write(response, outMsg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信支付回调地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/weixinNotify")
	public void weixinNotify(HttpServletRequest request, HttpServletResponse response) {
		String outMsg;
		try {
			outMsg = wxPayService.weixinNotify(request, response);
			WebCxt.write(response, outMsg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/app/v1/weixinQuery")
	public void weixinQuery(HttpServletRequest request, HttpServletResponse response) {
		String outMsg;
		outMsg = wxPayService.weixinQuery(request, response);
		WebCxt.write(response, outMsg);
	}
}
