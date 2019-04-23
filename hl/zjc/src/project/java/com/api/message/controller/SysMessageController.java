/**
 * 
 */
package com.api.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.message.SysMessageService;
import com.taobao.api.ApiException;

/**
 * app接口-不需要token,短信发送接口
 * 
 * @author zc
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class SysMessageController {
	
	@Autowired
	private SysMessageService sysMessageService;
	
	/**
	 * @api {add} api/app/v1/sendSMS.jhtml 短信发送
	 * @apiName 短信发送
	 * @apiGroup sms
	 *
	 * @apiParam {String} mobile 用户手机号
	 * @apiParam {String} sms_type 短信类型 (支付验证码 "pay_password" 找回密码 "find_password"  注册或者登录"lg_or_reg")
	 */
	
	/**
	 * 短信发送
	 * @param request
	 * @param response
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 * @throws ApiException 
	 */
	@RequestMapping(value = "/app/v1/sendSMS")
	public String sendMessage(HttpServletRequest request, HttpServletResponse response) throws ApiException{
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = sysMessageService.alidayuSms(httpModel);
		 WebCxt.write(response, outMsg);
		 return httpModel.getViewPath();
	}
}
