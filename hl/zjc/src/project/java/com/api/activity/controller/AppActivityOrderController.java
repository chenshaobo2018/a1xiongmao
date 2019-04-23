/**
 * 
 */
package com.api.activity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.activity.service.AppActivityOrderService;
import com.wxactivity.order.service.ShareOrderService;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class AppActivityOrderController {
	@Autowired
	private AppActivityOrderService AppActivityOrderService;
  
	/** @api {post} api/activity/v1/orderSub.jhtml 提交商品页面
	 * @apiName orderSub提交商品页面
	 * @apiGroup Order
	 * 
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} address_id 地址id
	 * @apiParam {String} total_amount  订单总额
	 * @apiParam {String} store_id  店铺id
	 * @apiParam {String} Goods  商品数据
	 * @apiParam {String} pay_type  支付方式
	 * @apiParam {String} user_note  备注信息
	 * @apiParam {String} Goods  商品数据，如[{"goods_id":"19552","spec_key_name":null,"spec_key":null,"goods_num":1}]
	 */
	/**
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/orderSub")
	public void orderSub(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=AppActivityOrderService.orderSub(request, response);
		 WebCxt.write(response, outMsg);
	}
}
