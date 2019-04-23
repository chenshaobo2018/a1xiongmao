package com.api.login.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.login.loginServiceApi;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;


/**
 * 不需要Tiken的app接口- 登陆
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private loginServiceApi loginServiceApi;
	
	/**
	 * @api {get} notokenapi/app/v1/login.jhtml 登陆
	 * @apiName 登陆
	 * @apiGroup Login
	 *
	 * @apiParam {String} mobile 手机号码
	 * @apiParam {String} design 校验包：LoginRandom+mobile+password+"zjc_1815"；先将passwordMD5加密，然后整体MD5加密
	 * @apiParam {String} LoginRandom 登陆随机码
	 */
	/**
	 * app接口-登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/login")
	public void loginser2(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = loginServiceApi.app_login(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/SLlogin.jhtml 搜了登录接口
	 * @apiName 登陆
	 * @apiGroup Login
	 *
	 * @apiParam {String} mobile 手机号码
	 * @apiParam {String} password 密码
	 * @apiParam {String} sign 密匙
	 */
	
	/**
	 * app接口-登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/SLlogin")
	public void SLlogin(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = loginServiceApi.SLlogin(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/loginParamater.jhtml 获取登陆参数
	 * @apiName 获取登陆参数
	 * @apiGroup Login
	 *
	 * @apiParam {String} mobile 手机号码
	 */
	/**
	 * app接口-获取登陆参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/loginParamater")
	public void get_login_parameter(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = loginServiceApi.get_login_parameter(request);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/loginClientid.jhtml 往redis里面存Clientid
	 * @apiName 往redis里面存Clientid
	 * @apiGroup Login
	 *
	 * @apiParam {String} clientid clientid
	 */
	/**
	 * 往redis里面存Clientid
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/loginClientid")
	public void loginClientid(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = loginServiceApi.loginClientid(request);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	 
	
}
