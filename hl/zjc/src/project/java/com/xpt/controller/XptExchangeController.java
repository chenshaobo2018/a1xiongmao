/**
 * 
 */
package com.xpt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpt.service.XptLoginService;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class XptExchangeController {
	
	@Autowired
	private XptLoginService XptLoginService;
	
	/**
	 * @api {get} notokenapi/app/v1/XptSaveExchange.jhtml 转账
	 * @apiName 转账
	 * @apiGroup Xpt
	 *
	 * @apiParam {String} real_name 对方姓名
	 * @apiParam {String} mobile 对方手机号
	 * @apiParam {String} points 积分数量
	 * @apiParam {String} random 随机数
	 * @apiParam {String} sign (mobile+points+random)MD5加密串
	 */
	/**
	 * app接口-转账
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/XptSaveExchange")
	public void XptSaveExchange(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.XptSaveExchange(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/getXptUserInfo.jhtml 获取用户信息
	 * @apiName 获取用户信息
	 * @apiGroup Xpt
	 *
	 * @apiParam {String} mobile 电话号码
	 */
	/**
	 * app接口-获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getXptUserInfo")
	public void getXptUserInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.getXptUserInfo(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/authen.jhtml 实名认证
	 * @apiName 实名认证
	 * @apiGroup User
	 *
	 * @apiParam {String} user_id 用户id
	 * @apiParam {String} real_name 真实姓名
	 * @apiParam {String} id_card 身份证号
	 */
	/**
	 * app接口-实名认证
	 * 
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/authen")
	public void authen(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.authen(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getXptUserGoods.jhtml 查询用户购物信息
	 * @apiName 查询用户购物信息
	 * @apiGroup Xpt
	 *
	 * @apiParam {String} user_id 用户id
	 * @apiParam {String} page 第几页(从1开始)
	 */
	/**
	 * app接口-获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getXptUserGoods")
	public void getXptUserGoods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.getXptUserGoods(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getXptOrder.jhtml 查询小平台商品订单
	 * @apiName 查询小平台商品订单
	 * @apiGroup Xpt
	 *
	 * @apiParam {String} order_status 订单状态 0-待确认，1-已确认，2-已收货，3-已取消，4-已完成(已评价)，5-已作废)(非必填)
	 * @apiParam {String} order_sn 订单编号(非必填)
	 * @apiParam {String} user_id 会员id(非必填)
	 * @apiParam {String} goods_name 商品名称(非必填)
	 * @apiParam {String} pay_name 支付方式(非必填)
	 * @apiParam {String} add_time_start 下单时间起(非必填)
	 * @apiParam {String} add_time_end 下单时间止(非必填)
	 * @apiParam {String} pay_status 订单支付状态；0表示未支付，1表示已支付(非必填)
	 * @apiParam {String} points_pay_status 混合支付易物券支付状态; 0表示未支付，1表示已支付(非必填)
	 * @apiParam {String} page 第几页(从1开始)（必填）
	 */
	/**
	 * app接口-查询小平台商品订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getXptOrder")
	public void getXptOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.getXptOrder(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} notokenapi/app/v1/getXptLog.jhtml 查询小平台日志
	 * @apiName 查询小平台日志
	 * @apiGroup Xpt
	 *
	 * @apiParam {String} page 第几页(从1开始)（必填）
	 */
	/**
	 * app接口-查询小平台日志
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getXptLog")
	public void getXptLog(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = XptLoginService.getXptLog(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
}
