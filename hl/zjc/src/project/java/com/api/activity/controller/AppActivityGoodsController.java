/**
 * 
 */
package com.api.activity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.activity.service.AppActivityGoodsService;
import com.api.activity.service.AppActivityShareService;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.zjc.goods.dao.po.ZjcGoodsPO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class AppActivityGoodsController {
	
		@Autowired
		private AppActivityGoodsService AppActivityGoodsService;
		@Autowired
		private AppActivityShareService AppActivityShareService;
		/**
		 * @api {get} notokenapi/activity/v1/getActivityAppGoods.jhtml 获取商品列表
		 * @apiName getActivityAppGoods获取商品列表
		 * @apiGroup goods
		 *
		 * @apiParam {String} page 第几页（必填）
		 */
	    // 查询商品方法
		@RequestMapping(value = "/activity/v1/getActivityAppGoods")
		public void getActivityAppGoods(HttpServletRequest request,HttpServletResponse response) {
			HttpModel httpModel = new HttpModel(request, response);
			MessageVO PageVO = AppActivityGoodsService.getAppGoods(httpModel);
			WebCxt.write(response, AOSJson.toJson(PageVO));
		}
		
		
		/**
	     *查询商品首页数据
	     * @param request
	     * @param response
	     */
		/**
		 * @api {get} notokenapi/activity/v1/shareIntegralDetailed.jhtml 查询商品首页数据
		 * @apiName shareIntegralDetailed查询商品首页数据
		 * @apiGroup goods
		 *
		 * @apiParam {String} goods_id  goods_id (从商品详情跳转到首页是必需传)
		 */
		@RequestMapping(value = "/activity/v1/shareIntegralDetailed")
		public void shareIntegralDetailed(HttpServletRequest request,HttpServletResponse response) {
			HttpModel httpModel = new HttpModel(request, response);
			httpModel.setAttribute("page", request.getParameter("page"));
			MessageVO homePageGoods = AppActivityGoodsService.shareHomePageGoods(httpModel);
			WebCxt.write(response, AOSJson.toJson(homePageGoods));
		}
		
		
		
		/**
		 * 初始化分享助力页面
		 * 
		 * @param request
		 * @param response
		 * @return
		 *//*
		@RequestMapping(value = "/activity/v1/initActivityShareHelp")
		public String initActivityShareHelp(HttpServletRequest request,
				HttpServletResponse response) {
			HttpModel httpModel = new HttpModel(request, response);
			httpModel.setAttribute("goods_id",
					httpModel.getInDto().getString("goods_id"));
			httpModel.setAttribute("user_id",
					httpModel.getInDto().getString("user_id"));
			request.getSession().setAttribute("share_openid",
					httpModel.getInDto().getString("share_openid"));
			return "project/zjc/activity/wxShareHelp.jsp";
		}*/
		
}
