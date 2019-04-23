/**
 * 
 */
package com.xpt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpt.service.XptLoginService;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class XptLoginComtroller {
	@Autowired
	private XptLoginService XptLoginService;
	
	
	/**
	 * @api {get} notokenapi/app/v1/XptRegistered.jhtml 小平台注册
	 * @apiName 小平台注册
	 * @apiGroup Xpt
	 *
	 * @apiParam {String} recommended_code 分享人id
	 * @apiParam {String} nickname 昵称
	 * @apiParam {String} mobile 电话号码
	 * @apiParam {String} password 密码
	 */
	/**
	 * app接口-小平台注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/XptRegistered")
	public void XptRegistered(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.XptRegistered(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/xptAddGoods.jhtml 添加特殊商品货号
	 * @apiName a添加特殊商品货号
	 * @apiGroup Xpt
	 * 
	 * @apiParam {String} goods_sn 商品货号(格式:1,2,3)
	 */
	/**
	 * app接口-小平台注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/xptAddGoods")
	public void xptAddGoods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.xptAddGoods(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	
}
