/**
 * 
 */
package com.api.alipay.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.api.alipay.service.AlipayService;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * 支付宝调用接口
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api")
public class AlipayController {

	@Autowired
	private AlipayService alipayService;
	
	
	/**
	 * 易物券充值订单sign
	 * 
	 * @param request
	 * @param response
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/app/v1/alipayRechargeSign")
	public void sign(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg;
		try {
			outMsg = alipayService.aliySign(httpModel);
			WebCxt.write(response, outMsg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 商品订单在线支付sign
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = "/app/v1/alipayOrderSign")
	public void signOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg;
		try {
			outMsg = alipayService.aliyOrderSign(httpModel);
			WebCxt.write(response, outMsg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}

}
