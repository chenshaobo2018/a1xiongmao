/**
 * 
 */
package com.web.good.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.common.po.MessageVO;
import com.taobao.api.ApiException;
import com.web.good.service.WebGoodsService;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/web")
public class WebGoodsController {
	@Autowired
	private WebGoodsService WebGoodsService;
	/**
	 * @api {add} api/app/v1/makeOrder.jhtml 确认订单
	 * @apiName 确认订单
	 * @apiGroup sms
	 *
	 * @apiParam {String} goods_id 商品id
	 * @apiParam {String} goods_num 商品数量
	 */
	
	/**
	 * 确认订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 * @throws ApiException 
	 */
	@RequestMapping(value = "/app/v1/makeOrder")
	public String makeOrder(HttpServletRequest request, HttpServletResponse response) throws ApiException{
		HttpModel httpModel = new HttpModel(request, response);
		//通过商品号查询商品
		MessageVO makeOrder = WebGoodsService.makeOrder(httpModel);
		//通过user_id查询收货地址
		MessageVO shippingAddress = WebGoodsService.shippingAddress(httpModel);
		httpModel.getRequest().setAttribute("makeOrder", makeOrder);
		httpModel.getRequest().setAttribute("shippingAddress", shippingAddress);
		return "/project/pcstore/order.jsp";
	}
	
}
