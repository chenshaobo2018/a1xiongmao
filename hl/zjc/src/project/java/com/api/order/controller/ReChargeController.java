package com.api.order.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.order.ReChargeService;


/**
 * 需要Tiken的app接口- 易物券订单
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class ReChargeController {
	private static final Logger logger = LoggerFactory.getLogger(ReChargeController.class);

	@Autowired
	private ReChargeService reChargeService;
	
	/**
	 * @api {get} api/app/v1/myRechargeOrder.jhtml 我的易物券订单
	 * @apiName 我的易物券订单
	 * @apiGroup Order
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} pay_status 充值状态0:待支付 1:充值成功 2:交易关闭
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-我的易物券订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/myRechargeOrder")
	public void getMyRechargeOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=reChargeService.getMyRechargeOrder(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/delRechargeOrder.jhtml 删除易物券订单
	 * @apiName 删除易物券订单
	 * @apiGroup Order
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} order_id 订单ID
	 */
	/**
	 * 删除易物券订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/delRechargeOrder")
	public void delRechargeOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=reChargeService.delRechargeOrder(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 新增置换易物券订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/saveRechargeOrder")
	public void saveRechargeOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=reChargeService.saveRechargeOrder(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
}



