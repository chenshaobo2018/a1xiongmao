package com.api.register.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.register.RegisterService;


/**
 * 不需要Tiken的app接口- 注册
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class RegisterController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private RegisterService registerService;
	
	/**
	 * @api {get} notokenapi/app/v1/register.jhtml 注册
	 * @apiName 注册
	 * @apiGroup User
	 *
	 * @apiParam {String} recommended_code 分享人ID
	 * @apiParam {String} nickname 昵称
	 * @apiParam {String} mobile 手机号
	 * @apiParam {String} password 密码
	 */
	/**
	 * app接口-注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/register")
	public void app_register(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = registerService.app_register(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 网页注册
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/web_register")
	public void web_register(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = registerService.web_register(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 网页注册
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/toWebRegisterPage")
	public String toWebRegisterPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setViewPath("project/zjc/webpage/webPageRegister.jsp");
		return httpModel.getViewPath();
	}
	
	/**
	 * 跳转到下载页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/toWebLoadPage")
	public String toWebLoadPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setViewPath("project/zjc/webpage/share.jsp");
		return httpModel.getViewPath();
	}
}



