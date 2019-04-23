/**
 * 
 */
package com.api.unionpay.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

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
 * 银联回调
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class UnionpayNotokenController {
	
	@Autowired
	private UnionpayService unionpayService;
	

	/**
	 * 易物券充值订单银联回调
	 * @param request
	 * @param response
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/app/v1/unionRechargeBack")
	public void backRcvResponse(HttpServletRequest request,
			HttpServletResponse response) throws 
			UnsupportedEncodingException {
		HttpModel httpModel = new HttpModel(request, response);
		String result = unionpayService.backRcvResponse(httpModel);
		WebCxt.write(response, result);
		httpModel.getViewPath();
	}
	
	/**
	 * 商品订单在线支付银联回调
	 * @param request
	 * @param response
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/app/v1/unionRechargeBackOrder")
	public void backRcvResponseOrder(HttpServletRequest request,
			HttpServletResponse response) throws 
			UnsupportedEncodingException, ParseException {
		HttpModel httpModel = new HttpModel(request, response);
		String result = unionpayService.backRcvResponseOrder(httpModel);
		WebCxt.write(response, result);
	}

}
