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

import com.api.order.ExChangeService;


/**
 * 需要Token的app接口- 易物交易
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class ExChangeController {
	private static final Logger logger = LoggerFactory.getLogger(ExChangeController.class);

	@Autowired
	private ExChangeService exChangeService;
	
	/**
	 * @api {get} api/app/v1/saveExchange.jhtml 提交易物交易
	 * @apiName 提交易物交易
	 * @apiGroup Order
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} real_name 对方姓名
	 * @apiParam {String} mobile 对方手机号
	 * @apiParam {String} points 积分数量
	 * @apiParam {String} sign 校验包
	 * @apiParam {String} random 随机码
	 */
	/**
	 * app接口-提交易物交易
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/saveExchange")
	public void saveExchange(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=exChangeService.saveExchange(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
}



