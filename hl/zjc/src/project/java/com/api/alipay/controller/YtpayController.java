/**
 * 
 */
package com.api.alipay.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.api.alipay.service.YtpayService;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class YtpayController {
	
	@Autowired
	private YtpayService YtpayService;
	/**
	 * 银行卡添加绑定
	 * 
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} api/app/v1/bankSave.jhtml 银行卡信息添加绑定
	 * @apiName 银行卡信息添加绑定
	 * @apiGroup pay
	 *
	 * @apiParam {String} user_id 用户id
	 * @apiParam {String} phone 电话
	 * @apiParam {String} bank_card 银行卡号
	 * @apiParam {String} id_card 身份证号
	 * @apiParam {String} pay_default 是否默认绑定
	 */
	@RequestMapping(value = "/app/v1/bankSave")
	public void bankSave(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
			String outMsg = YtpayService.bankSave(httpModel);
			WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * 银行卡查询
	 * 
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} api/app/v1/queryBank.jhtml 银行卡信息查询
	 * @apiName 银行卡信息查询
	 * @apiGroup pay
	 *
	 * @apiParam {String} user_id 用户id
	 */
	@RequestMapping(value = "/app/v1/queryBank")
	public void queryBank(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
			String outMsg = YtpayService.queryBank(httpModel);
			WebCxt.write(response, outMsg);
	}
	
	/**
	 * 修改银行卡
	 * 
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} api/app/v1/updataBank.jhtml 银行卡信息修改
	 * @apiName 银行卡信息修改
	 * @apiGroup pay
	 *
	 * @apiParam {String} user_id 用户id
	 * @apiParam {String} phone 电话
	 * @apiParam {String} bank_card 银行卡号
	 * @apiParam {String} id_card 身份证号
	 * @apiParam {String} pay_default 是否默认绑定
	 */
	@RequestMapping(value = "/app/v1/updataBank")
	public void updataBank(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
			String outMsg = YtpayService.updataBank(httpModel);
			WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/SLConvert.jhtml 搜了券豆兑换
	 * @apiName 搜了券豆兑换
	 * @apiGroup pay
	 *
	 * @apiParam {String} token  token
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} voucher 多少券
	 */
	@RequestMapping(value = "/app/v1/SLConvert")
	public void SLConvert(HttpServletRequest request, HttpServletResponse response) {
		    HttpModel httpModel = new HttpModel(request, response);
			String outMsg = YtpayService.SLConvert(httpModel);
			WebCxt.write(response, outMsg);
	}
}
