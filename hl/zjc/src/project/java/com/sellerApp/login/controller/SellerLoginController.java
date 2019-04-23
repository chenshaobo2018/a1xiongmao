package com.sellerApp.login.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.login.loginPassService;
import com.sellerApp.login.SellerLoginService;


/**
 * 不需要Tiken的app接口- 登陆
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "sellernotokenapi")
public class SellerLoginController {
	private static final Logger logger = LoggerFactory.getLogger(SellerLoginController.class);

	@Autowired
	private SellerLoginService sellerLoginService;
	
	/**
	 * @api {get} sellernotokenapi/app/v1/login.jhtml app接口-登陆
	 * @apiName 登陆
	 * @apiGroup Login
	 *
	 * @apiParam {String} mobile 手机号码
	 * @apiParam {String} design 校验包："zhongjunchuangya1212"+password；整体MD5加密
	 * @apiParam {String} LoginRandom 登陆随机码
	 */
	/**
	 * 商家app接口-登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/login")
	public void loginser2(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = sellerLoginService.app_login(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/loginParamater.jhtml app接口-获取登陆参数
	 * @apiName 获取登陆参数
	 * @apiGroup Login
	 *
	 * @apiParam {String} mobile 手机号码
	 */
    /**
     * 获取登陆参数
     * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/loginParamater")
	public void get_login_parameter(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = sellerLoginService.get_login_parameter(request);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
}
