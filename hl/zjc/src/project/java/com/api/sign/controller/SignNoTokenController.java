/**
 * 
 */
package com.api.sign.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.sign.service.SignService;

/**
 * @author pubing
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class SignNoTokenController {
	
	@Autowired
	private SignService signService;
	
	/**
	 * @api {get} api/app/v1/getSignSetting.jhtml 签到设置
	 * @apiName 签到设置
	 * @apiGroup Sign
	 * 
	 */
	/**
	 * 签到设置
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getSignSetting")
	public void getSignSetting(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= signService.getSignSetting(request, response);
		WebCxt.write(response, outMsg);
	}
}
