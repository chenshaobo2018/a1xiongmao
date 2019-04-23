/**
 * 
 */
package com.api.pintuan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.pintuan.service.PinTuanOrderService;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class OrderPinTuanController {

	@Autowired
	private PinTuanOrderService PinTuanOrderService;
	
	/** @api {post} api/app/v1/pinOrderSub.jhtml 提交商品页面
	 * @apiName orderSub提交商品页面
	 * @apiGroup Order
	 * 
	 * @apiParam {String} token token
	 * @apiParam {String} address_id 地址id
	 * @apiParam {String} total_amount  订单总额
	 * @apiParam {String} store_id  店铺id
	 * @apiParam {String} Goods  商品数据
	 * @apiParam {String} pay_type  支付方式
	 * @apiParam {String} user_note  备注信息
	 * @apiParam {String} Goods  商品数据，如[{"goods_id":"19552","spec_key_name":null,"spec_key":null,"goods_num":1}]
	 * @apiParam {String} voucher_id  代金券id
	 * @apiParam {String} pin_order_id  如果从参与拼单中去下单 则需要传参与人的订单id
	 * @apiParam {String} is_shop_group 是否是拼团订单
	 */
	/**
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/pinOrderSub")
	public void pinOrderSub(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=PinTuanOrderService.orderSub(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	/** @api {post} api/app/v1/queryOrderPersonnel.jhtml 查询当前订单那些人参与了
	 * @apiName queryOrderPersonnel查询当前订单那些人参与了
	 * @apiGroup Personnel
	 * 
	 * @apiParam {String} token token
	 * @apiParam {String} pin_order_id 订单id
	 * @apiParam {String} is_head 是否是团长（团长为1）
	 */
	/**
	 * 查询当前订单那些人参与了
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/queryOrderPersonnel")
	public void queryOrderPersonnel(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=PinTuanOrderService.queryOrderPersonnel(request, response);
		 WebCxt.write(response, outMsg);
	}
}
