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
@RequestMapping(value = "api")
public class SignController {
	
	@Autowired
	private SignService signService;
	
	/**
	 * @api {get} api/app/v1/daySign.jhtml 当天签到
	 * @apiName 签到
	 * @apiGroup Sign
	 *
	 * @apiParam {String} token 
	 */
	/**
	 * 签到
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/daySign")
	public void daySign(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= signService.daySign(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getSignList.jhtml 签到历史
	 * @apiName 签到
	 * @apiGroup Sign
	 *
	 * @apiParam {String} token 
	 * @apiParam {String} page 第几页 
	 */
	/**
	 * 签到历史
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getSignList")
	public void getSignList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= signService.getSignList(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getMonthSign.jhtml 本月的签到数据
	 * @apiName 签到
	 * @apiGroup Sign
	 *
	 * @apiParam {String} token 
	 */
	/**
	 * 本月的签到数据
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getMonthSign")
	public void getMonthSign(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= signService.getMonthSign(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/signOut.jhtml 本月的签到数据
	 * @apiName 签到
	 * @apiGroup Sign
	 *
	 * @apiParam {String} token 
	 * @apiParam {String} outPoint 提取的数量
	 */
	/**
	 * 本月的签到数据
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/signOut")
	public void signOut(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= signService.signOut(request, response);
		WebCxt.write(response, outMsg);
	}
}
