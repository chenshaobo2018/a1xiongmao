package com.api.accountManager.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.accountManager.UserAccountInfoService;


/**
 * app接口-用户账户管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class UserAccountInfoController {
	private static final Logger logger = LoggerFactory.getLogger(UserAccountInfoController.class);

	@Autowired
	private UserAccountInfoService userAccountInfoService;
	
	/**
	 * @api {get} api/app/v1/changeKzToKy.jhtml 易物券转换（可转到可用）
	 * @apiName 易物券转换（可转到可用）
	 * @apiGroup UserAccount
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} points_num 易物券数量
	 */
	/**
	 * app接口-易物券转换（可转到可用）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/changeKzToKy")
	public void changeKzToKy(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAccountInfoService.changeKzToKy(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/companyPublicPay.jhtml 支付企业宣传
	 * @apiName 支付企业宣传
	 * @apiGroup UserAccount
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} cp_id 企业宣传ID
	 * @apiParam {String} sign 校验包
	 * @apiParam {String} random 随机码
	 */
	/**
	 * app接口-支付企业宣传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/companyPublicPay")
	public void companyPublicPay(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAccountInfoService.companyPublicPay(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getExchangRang.jhtml 获取转账范围
	 * @apiName 获取转账范围
	 * @apiGroup Index
	 *
	 */
	/**
	 * app接口-获取转账范围
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getExchangRang")
	public void getExchangRang(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=userAccountInfoService.getExchangRang(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
}