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
import com.api.order.OrderService;


/**
 * 不需要Token的app接口- 商品管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class GoodsController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrderService OrderService;
	
	/**
	 * @api {get} notokenapi/app/v1/GoodsLists.jhtml 获取商品列表
	 * @apiName 获取商品列表
	 * @apiGroup goods
	 *
	 * @apiParam {String} page 第几页（必填）
	 * @apiParam {String} is_hot 热卖
	 * @apiParam {String} Special_offer 特价
	 * @apiParam {String} market_price 易物卷
	 * @apiParam {String} shop_price 现金
	 * @apiParam {String} is_mixed 混合支付
	 * @apiParam {String} is_vip 是否是vip商品。1：是，0：否
	 * @apiParam {String} user_id 非必填
	 */
	    //获取商品列表
		@RequestMapping(value = "/app/v1/GoodsLists")
		public void GoodsList(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			String outMsg =OrderService.GoodsList(request, response);
			WebCxt.write(response, outMsg);
		}
		
		/**
		 * @api {get} notokenapi/app/v1/getAd.jhtml 获取首页图片
		 * @apiName 获取首页图片
		 * @apiGroup goods
		 *
		 */
		//获取首页图片
		@RequestMapping(value = "/app/v1/getAd")
		public void getAd(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			String outMsg =OrderService.getAd(request, response);
			WebCxt.write(response, outMsg);
		}
		
		/**
		 * @api {get} notokenapi/app/v1/getMessage.jhtml 获取系统公告
		 * @apiName 获取系统公告
		 * @apiGroup goods
		 *
		 */
		//获取系统公告
		@RequestMapping(value = "/app/v1/getMessage")
		public void getMessage(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			String outMsg =OrderService.getMessage(request, response);
			WebCxt.write(response, outMsg);
		}
		
		/**
		 * @api {get} notokenapi/app/v1/getArticle.jhtml 获取新闻资讯
		 * @apiName 获取新闻资讯
		 * @apiGroup goods
		 *
		 */
		//获取新闻资讯
		@RequestMapping(value = "/app/v1/getArticle")
		public void getArticle(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg =OrderService.getArticle(request, response);
		WebCxt.write(response, outMsg);
		}
	
	/**
	 * @api {get} notokenapi/app/v1/getStoreGoods.jhtml 获取店铺的商品
	 * @apiName 获取店铺的商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} store_id 店铺id
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-获取店铺的商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getStoreGoods")
	public void getStoreGoods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.getStoreGoods(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getNewGoods.jhtml 查找新商品
	 * @apiName 查找新商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} type 查询类型（全部：all,时间：time,类型：price）
	 * @apiParam {String} desc 是否降序查询（true,false）
	 * @apiParam {String} page 第几页
	 */
	/**
	 * 查找新商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getNewGoods")
	public void getNewGoods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.getNewGoods(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/getGoodsDetailByGoodsId.jhtml 通过商品ID查询商品详情
	 * @apiName 通过商品ID查询商品详情
	 * @apiGroup Goods
	 *
	 * @apiParam {String} goods_id 店铺id
	 * @apiParam {String} token token(非必填，用来判断用户是否收藏了该商品)
	 */
	/**
	 * app接口-通过商品ID查询商品详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getGoodsDetailByGoodsId")
	public void getGoodsDetailByGoodsId(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.getGoodsDetailByGoodsId(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getGoodsCategory.jhtml 商品分类查询
	 * @apiName 商品分类查询
	 * @apiGroup Goods 不需要传递参数，直接调用
	 * 
	 *
	 */
	/**
	 * 查询所有商品分类
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getGoodsCategory")
	public void getGoodsCategory(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = goodsService.getGoodsCategorys(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/get_goods_category_tree.jhtml 商品分类
	 * @apiName 商品分类
	 * @apiGroup Goods
	 *
	 */
	/**
	 * app接口-商品分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/get_goods_category_tree")
	public void get_goods_category_tree(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = goodsService.get_goods_category_tree(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/GoodsComments 获取商品评论表
	 * @apiName 获取商品评论表
	 * @apiGroup goods
	 *
	 *  @apiParam {String} goods_id 商品id
	 *  @apiParam {String} page 当前页码
	 */
	//获取商品评论表
	@RequestMapping(value = "/app/v1/GoodsComments")
	public void GoodsComments(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=goodsService.GoodsComments(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/queryLimitGoods 查询限时购商品
	 * @apiName 获取商品评论表
	 * @apiGroup goods
	 *
	 * @apiParam {String} page 当前页码
	 * @apiParam {String} start_time 开始时间
	 * @apiParam {String} end_time 结束时间
	 */
	//获取商品评论表
	@RequestMapping(value = "/app/v1/queryLimitGoods")
	public void queryLimitGoods(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=goodsService.queryLimitGoods(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/newDate 查询系统时间
	 * @apiName 查询系统时间
	 * @apiGroup goods
	 *
	 */
	//获取商品评论表
	@RequestMapping(value = "/app/v1/newDate")
	public void newDate(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=goodsService.newDate(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/queryZjcActivitiy 查询活动时间
	 * @apiName 查询活动时间
	 * @apiGroup goods
	 *
	 */
	//获取商品评论表
	@RequestMapping(value = "/app/v1/queryZjcActivitiy")
	public void queryZjcActivitiy(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=goodsService.queryZjcActivitiy(request, response);
		 WebCxt.write(response, outMsg);
	}
	
}



