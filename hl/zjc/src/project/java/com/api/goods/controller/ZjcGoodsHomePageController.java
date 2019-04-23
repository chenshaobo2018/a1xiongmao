/**
 * 
 */
package com.api.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.goods.ZjcGoodsHomePageService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class ZjcGoodsHomePageController {
	@Autowired
	private ZjcGoodsHomePageService ZjcGoodsHomePageService;
	
	/**
	 * @api {get} notokenapi/app/v1/getHomePage.jhtml 获取App首页列表
	 * @apiName 获取App首页列表
	 * @apiGroup goods
	 *
	 * @apiParam {String} page 第几页（必填）
	 * @apiParam {String} cat_id 分类ID（非必填）
	 * @apiParam {String} cat_id2 二级分类ID（非必填）
	 * @apiParam {String} keywords 关键字（非必填）
	 * @apiParam {String} is_recommend 是否为优选商品1：是；0：否（非必填，优选商品查询请传1）
	 */
	    //获取商品列表
		@RequestMapping(value = "/app/v1/getHomePage")
		public void getHomePage(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			String outMsg =ZjcGoodsHomePageService.getHomePage(request, response);
			WebCxt.write(response, outMsg);
		}

}
