/**
 * 
 */
package com.api.unionpay.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.alipay.api.AlipayApiException;
import com.api.unionpay.service.UnionpayService;

/**
 * @author 浅笑低吟
 *
 */

@Controller
@RequestMapping("/api")
public class UnionpayController {

	@Autowired
	private UnionpayService unionpayService;

	/**
	 * 易物券充值订单sign
	 * 
	 * @param request
	 * @param response
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/app/v1/unionpayRechargeSign")
	public void sign(HttpServletRequest request, HttpServletResponse response)
			throws  UnsupportedEncodingException {
		HttpModel httpModel = new HttpModel(request, response);
		String result = unionpayService.sign(httpModel);
		WebCxt.write(response, result);
	}
	
	/**
	 * 商品订单在线支付sign
	 * 
	 * @param request
	 * @param response
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/app/v1/unionpayOrderSign")
	public void signOrder(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		HttpModel httpModel = new HttpModel(request, response);
		String result = unionpayService.signOrder(httpModel);
		WebCxt.write(response, result);
	}

}
