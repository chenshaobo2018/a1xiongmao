package com.api.goods.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.goods.GoodsService;


/**
 * 需要Token的app接口- 商品管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class GoodsTokenController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsTokenController.class);

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * @api {get} api/app/v1/collectGoods.jhtml 收藏商品
	 * @apiName 收藏商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} goods_id 商品id
	 * @apiParam {String} token token
	 */
	/**
	 * app接口-收藏商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/collectGoods")
	public void collect_goods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.collect_goods(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/cancleCollectGoods.jhtml 取消收藏商品
	 * @apiName 取消收藏商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} goods_id 商品id
	 * @apiParam {String} token token
	 */
	/**
	 * app接口-取消收藏商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/cancleCollectGoods")
	public void cancle_collect_goods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.cancle_collect_goods(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/cancleCollectGoods.jhtml 查看我的收藏商品
	 * @apiName 查看我的收藏商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-查看我的收藏商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getMyCollectGoods")
	public void getMyCollectGoods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.getMyCollectGoods(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
}



