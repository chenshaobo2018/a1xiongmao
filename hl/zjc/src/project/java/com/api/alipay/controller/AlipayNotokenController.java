/**
 * 
 */
package com.api.alipay.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.alipay.api.AlipayApiException;
import com.api.alipay.service.AlipayService;


/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class AlipayNotokenController {
	
	@Autowired
	private AlipayService alipayService;
	
	
    /**
     * 易物券充值订单回调验签
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/app/v1/alipayRechargeNotify") 
    public void alipayNotify(HttpServletRequest request,HttpServletResponse response) {  
    	HttpModel httpModel = new HttpModel(request,response);
    	try{
	    	String result = alipayService.alipayNotify(httpModel);
	        WebCxt.write(response, result);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
		httpModel.getViewPath();
    }  
    
    /**
     * 商品订单在线支付回调验签
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/app/v1/alipayOrderNotify") 
    public void alipayOrderNotify(HttpServletRequest request,HttpServletResponse response) {  
    	HttpModel httpModel = new HttpModel(request,response);
    	String result;
		try {
			result = alipayService.alipayOrderNotify(httpModel);
			WebCxt.write(response, result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpModel.getViewPath();
    }  
    
    /**
	 * ios商品订单在线支付sign
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/alipayIOSOrderNotify")
	public void alipayIOSOrderNotify(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg;
		outMsg = alipayService.alipayIOSOrderNotify(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	
	
	/**
     * 易物券充值订单回调验签
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/app/v1/HuipayNotify") 
    public void HuipayNotify(HttpServletRequest request,HttpServletResponse response) { 
    	HttpModel httpModel = new HttpModel(request,response);
    	try{
	    	String result = alipayService.HuipayNotify( request, response);
	        WebCxt.write(response, result);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
		httpModel.getViewPath();
    }  
    
    
    /**
     * 商品订单在线支付回调验签
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/app/v1/huiOrderNotify") 
    public void huiOrderNotify(HttpServletRequest request,HttpServletResponse response) {  
    	HttpModel httpModel = new HttpModel(request,response);
    	String result;
		try {
			result = alipayService.huiOrderNotify(httpModel);
			WebCxt.write(response, result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpModel.getViewPath();
    }  
}
