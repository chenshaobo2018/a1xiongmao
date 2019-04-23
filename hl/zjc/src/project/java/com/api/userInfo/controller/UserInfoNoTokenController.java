package com.api.userInfo.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.userInfo.UserInfoService;


/**
 * app接口管理控制器
 * 
 * @author pb
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class UserInfoNoTokenController {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoNoTokenController.class);

	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * @api {get} notokenapi/app/v1/findPassword.jhtml 找回密码
	 * @apiName 找回密码
	 * @apiGroup User
	 *
	 * @apiParam {String} code 验证短信
	 * @apiParam {String} mobile 电话
	 * @apiParam {String} new_password 新密码
	 * @apiParam {String} type 验证码类型
	 */
	/**
	 * app接口-找回密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/findPassword")
	public void findPassword(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.findPassword(httpModel);
	    WebCxt.write(response, outMsg);
	}
}