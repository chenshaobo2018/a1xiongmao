package com.sellerApp.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.web.router.HttpModel;

import com.sellerApp.goods.service.SellerAppGoodsService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "sellerapi")
public class SellerAppGoodsController {
	
	private static Logger logger = LoggerFactory.getLogger(SellerAppGoodsController.class);
	
	@Autowired
	private SellerAppGoodsService sellerAppGoodsService;
	
	/**
	 * @api {get} sellerapi/app/v1/del_to_unsale.jhtml  商品下架
	 * @apiName 1商品下架
	 * @apiGroup Goods
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} goods_id 商品ID
	 */
	/**
	 *  商品下架
	 *  
	 * @param goods_id 商品ID
	 * @param token 用户token
	 */
	@RequestMapping(value = "app/v1/del_to_unsale")
	public void del_to_unsale(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newInDto(request);
		inDto.put("user_id", request.getAttribute("user_id"));
		String outString = sellerAppGoodsService.del_to_unsale(inDto);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/add_store_count.jhtml  添加商品数量
	 * @apiName 1添加商品数量
	 * @apiGroup Goods
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} goods_id 商品ID
	 * @apiParam {String} store_count 新的商品库存
	 */
	/**
	 * 添加商品数量
	 * 
	 * @param goods_id 商品ID
	 * @param token 用户token
	 * @param store_count 新的商品库存
	 */
	@RequestMapping(value = "app/v1/add_store_count")
	public void add_store_count(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newInDto(request);
		inDto.put("store_id", request.getAttribute("store_id"));
		String outString = sellerAppGoodsService.add_store_count(inDto);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/groundGoods.jhtml   上架商品
	 * @apiName  上架商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} goods_id 商品id
	 */
	/**
	 * 上架商品
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "app/v1/groundGoods")
	public void groundGoods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppGoodsService.groundGoods(httpModel);
		WebCxt.write(response, outString);
	}
}
