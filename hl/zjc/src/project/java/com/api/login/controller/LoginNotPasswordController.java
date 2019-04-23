/**
 * 
 */
package com.api.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.login.LoginNotPasswordService;
import com.api.login.loginServiceApi;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class LoginNotPasswordController {
	
	@Autowired
	private LoginNotPasswordService LoginNotPasswordService;
	/**
	 * @api {get} notokenapi/app/v1/wxOpenIdLogin.jhtml 登陆
	 * @apiName 根据open_id登陆
	 * @apiGroup Login
	 *
	 * @apiParam {String} open_id 微信的union_id
	 */
	/**
	 * app接口-根据open_id登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/wxOpenIdLogin")
	public void wxOpenIdLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = LoginNotPasswordService.wxOpenIdLogin(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/mobileLogin.jhtml 手机号不存在则生成 存在则登录
	 * @apiName 手机号不存在则生成 存在则登录
	 * @apiGroup Login
	 *
	 * @apiParam {String} wxJson 微信的json
	 * @apiParam {String} mobile 手机号
	 * @apiParam {String} code 短信登录码
	 * @apiParam {String} type 短信类型
	 */
	/**
	 * app接口-手机号不存在则生成 存在则登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/mobileLogin")
	public void mobileLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = LoginNotPasswordService.mobileLogin(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	
	
}
