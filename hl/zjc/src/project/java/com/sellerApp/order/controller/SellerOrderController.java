package com.sellerApp.order.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.order.ExChangeService;
import com.sellerApp.order.SellerOrderService;


/**
 * 需要Token的app接口- 订单列表
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "sellerapi")
public class SellerOrderController {
	private static final Logger logger = LoggerFactory.getLogger(SellerOrderController.class);

	@Autowired
	private SellerOrderService sellerOrderService;
	
	/**
	 * @api {get} sellerapi/app/v1/delivery.jhtml 发货
	 * @apiName 发货
	 * @apiGroup Order
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} order_id 订单id
	 * @apiParam {String} invoice_no 物流单号
	 */
	/**
	 * 发货
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/delivery")
	public void delivery(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=sellerOrderService.delivery(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/** 
	 * @api {get} sellerapi/app/v1/sellerScanPay.jhtml 商家扫码收款
	 * @apiName 商家扫码收款
	 * @apiGroup Order
	 * 
	 * @apiParam {String} token token
	 * @apiParam {String} pay_code 支付随机码
	 * @apiParam {String} userId  会员Id
	 * @apiParam {String} point  支付金额
	 */
	@RequestMapping(value = "/app/v1/sellerScanPay")
	public void sellerScanPay(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=sellerOrderService.sellerScanPay(request, response);
		 WebCxt.write(response, outMsg);
	}
}



