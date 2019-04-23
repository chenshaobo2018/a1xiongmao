/**
 * 
 */
package com.wxactivity.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.wxactivity.goods.service.WxShareGoodsService;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class WxShareGoodsController {
	@Autowired
	private WxShareGoodsService WxShareGoodsService;
	
	    /**
	     * 查询首页商品
	     * @param request
	     * @param response
	     */
		/**
		 * @api {get} notokenapi/app/v1/shareHomePageGoods.jhtml 查询首页商品
		 * @apiName 查询首页商品
		 * @apiGroup Share
		 *
		 * @apiParam {String} goods_id 如果是走商品详情页进来则必须传商品id
		 */
		@RequestMapping(value = "/activity/v1/shareHomePageGoods")
		public void shareHomePageGoods(HttpServletRequest request,HttpServletResponse response) {
			HttpModel httpModel = new HttpModel(request, response);
			httpModel.setAttribute("page", request.getParameter("page"));
			MessageVO homePageGoods = WxShareGoodsService.shareHomePageGoods(httpModel);
			WebCxt.write(response, AOSJson.toJson(homePageGoods));
		}
		/**
	     * 查询积分详细
	     * @param request
	     * @param response
	     */
		/**
		 * @api {get} notokenapi/app/v1/shareIntegralDetailed.jhtml 查询积分详细
		 * @apiName 查询积分详细
		 * @apiGroup Share
		 *
		 * @apiParam {String} share_open_id  openg_id必须传的值
		 *  @apiParam {String} page  第几页
		 */
		@RequestMapping(value = "/activity/v1/shareIntegralDetailed")
		public void shareIntegralDetailed(HttpServletRequest request,HttpServletResponse response) {
			HttpModel httpModel = new HttpModel(request, response);
			httpModel.setAttribute("page", request.getParameter("page"));
			MessageVO homePageGoods = WxShareGoodsService.shareHomePageGoods(httpModel);
			httpModel.getRequest().setAttribute("homePageGoods", homePageGoods);
			WebCxt.write(response, AOSJson.toJson(homePageGoods));
		}
		
	/**
     * 查询助力累计数据
     * @param request
     * @param response
     */
	/**
	 * @api {get} notokenapi/app/v1/shareHomePowers.jhtml 查询首页商品
	 * @apiName 查询首页商品
	 * @apiGroup Share
	 *
	 * @apiParam {String} openg_id  openg_id必须传的值
	 */
	@RequestMapping(value = "/activity/v1/shareHomePowers")
	public void shareHomePowers(HttpServletRequest request,HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("page", request.getParameter("page"));
		MessageVO shareGoods = WxShareGoodsService.shareGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(shareGoods));
	}
	
	/**
	 * 跳转到商品分享列表页
	 * 
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} notokenapi/app/v1/shareHomePowers.jhtml 查询首页商品
	 * @apiName 查询首页商品
	 * @apiGroup Share
	 *
	 * @apiParam {String} openg_id openg_id必须传的值
	 * @apiParam {String} goods_id 如果是走商品详情页进来则必须传商品id
	 */
	@RequestMapping(value = "/activity/v1/initShareProduction")
	public String initShareProduction(HttpServletRequest request, HttpServletResponse response) {
		return "project/wxactivity/share_product.jsp";
	}
}
