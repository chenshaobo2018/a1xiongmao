/**
 * 
 */
package com.api.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.order.CartService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class CartController {
    
	@Autowired
	private CartService CartService;
	
	
	/**
	 * @api {get} api/app/v1/addCart.jhtml 添加购物车
	 * @apiName 添加商品到购物车
	 * @apiGroup cat
	 *
	 * @apiParam @apiParam {order}{
                    "total_amount":"1000",订单总价
                    "address_id":"650",
					"user_id":"10025",
					"user_note":"1",备注
					"pay_type":"rate",支付方式
					"store_id":"1",
					"postFee":"1",运费
					"Goods":[{"goods_id":"1","goods_num":"1","spec_key":"1","spec_key_name":"1"},
					        {"goods_id":"134","goods_num":"2","spec_key":"0","spec_key_name":"0"}]} 
					        json格式
	 */
	/**
	 * 添加商品到购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/addCart")
	public void addCart(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=CartService.addCart(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/cartList.jhtml 获取购物车列表
	 * @apiName 添加商品到购物车
	 * @apiGroup cat
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} user_id 用户id
	 */
	/**
	 * 获取购物车列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/cartList")
	public void cartList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=CartService.cartList(request, response);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/delCart.jhtml 根据id删除购物车里的商品
	 * @apiName 根据id删除购物车里的商品
	 * @apiGroup cat
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} id 购物车id
	 */
	/*
	 * 根据id删除购物车里的商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/delCart")
	public void delCart(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		 String outMsg= CartService.delCart(request, response);
		 WebCxt.write(response, outMsg);
	}
}
