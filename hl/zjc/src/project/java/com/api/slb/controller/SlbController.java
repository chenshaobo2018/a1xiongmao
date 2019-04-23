/**
 * 
 */
package com.api.slb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.login.loginServiceApi;
import com.api.slb.service.SlbService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class SlbController {
	@Autowired
	private SlbService SlbService;
	
	/*@RequestMapping(value = "/app/v1/checkUserRegister")
	public void checkUserRegister(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = SlbService.checkUserRegister(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}*/
	
	/**
	 * @api {post} api/app/v1/transferToSLB.jhtml 积分转slb
	 * @apiName 登陆
	 * @apiGroup Login
	 *
	 * @apiParam {String} mobile 手机号码
	 * @apiParam {String} mobileArea 区号
	 * @apiParam {String} integral 积分
	 */
	@RequestMapping(value = "/app/v1/transferToSLB")
	public void transferToSLB(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = SlbService.transferToSLB(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
}
