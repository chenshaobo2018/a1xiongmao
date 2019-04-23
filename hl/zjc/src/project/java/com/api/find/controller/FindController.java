/**
 * 
 */
package com.api.find.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.find.service.FindService;

/**
 * @author pubing
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class FindController {
	
	@Autowired
	private FindService findService;
	/**
	 * @api {get} api/app/v1/getServiceList.jhtml 客服列表
	 * @apiName 官方网址
	 * @apiGroup Find
	 *
	 */
	/**
	 * 客服列表
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getServiceList")
	public void getServiceList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.getServiceList(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getFinaceToolList.jhtml 官方网址
	 * @apiName 创富工具
	 * @apiGroup Find
	 */
	/**
	 * 创富工具
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getFinaceToolList")
	public void getFinaceToolList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.getFinaceToolList(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getNewsList.jhtml 获取新闻列表
	 * @apiName 获取新闻列表
	 * @apiGroup Find
	 * @apiParam {String} page 第几页
	 */
	/**
	 * 获取新闻列表
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getNewsList")
	public void getNewsList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.getNewsList(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getSystemMsg.jhtml 平台通知列表
	 * @apiName 平台通知列表
	 * @apiGroup Find
	 * @apiParam {String} page 第几页
	 */
	/**
	 * 平台通知列表
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getSystemMsg")
	public void getSystemMsg(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.getSystemMsg(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/systemMsgDetail.jhtml 系统消息详情
	 * @apiName 平台通知列表
	 * @apiGroup Find
	 * @apiParam {String} message_id 信息ID
	 */
	/**
	 * 系统消息详情
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/systemMsgDetail")
	public void systemMsgDetail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.systemMsgDetail(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getAlertMsg.jhtml 获取弹出公告
	 * @apiName 获取弹出公告
	 * @apiGroup Find
	 */
	/**
	 * 获取弹出公告
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/getAlertMsg")
	public void getAlertMsg(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.getAlertMsg(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/articleDetail.jhtml 文章详情
	 * @apiName 文章详情
	 * @apiGroup Find
	 * @apiParam {String} article_id 文章ID
	 */
	/**
	 * 文章详情
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/articleDetail")
	public void articleDetail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.articleDetail(request, response);
		WebCxt.write(response, outMsg);
	}
}
