/**
 * 
 */
package com.web.good.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.login.controller.ValidateCode;
import com.taobao.api.ApiException;
import com.web.good.service.WebLoginService;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/notokenapi")
public class WebLoginController {
	@Autowired
	private WebLoginService WebLoginService;
	
	/**
	 * @api {add} api/app/v1/sendSMS.jhtml 短信发送
	 * @apiName 短信发送
	 * @apiGroup sms
	 *
	 * @apiParam {String} mobile 用户手机号
	 * @apiParam {String} sms_type 短信类型 (支付验证码 "pay_password" 找回密码 "find_password",注册"register")
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
	@RequestMapping(value = "/app/v1/webSendMessage")
	public String webSendMessage(HttpServletRequest request, HttpServletResponse response) throws ApiException{
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = WebLoginService.webSendMessage(httpModel);
		 WebCxt.write(response, outMsg);
		 return httpModel.getViewPath();
	}
	
	/**
	 * web端登录
	 * @param request
	 * @param response
	 * @param model
	 */
	/**
	 * @api {add} app/v1/webLogin.jhtml web端登录
	 * @apiName web端登录
	 * @apiGroup longin
	 *
	 * @apiParam {String} vercode 随机码
	 * @apiParam {String} account 账号
	 * @apiParam {String} password 密码
	 * @apiParam {String} type 跳转页面标记(orderdetail商品详情页)
	 */
	@RequestMapping(value = "/app/v1/webLogin")
	public void webLogin(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		Dto result = WebLoginService.webLogin(httpModel);
		MessageVO msg = new MessageVO();
		if(result.getAppCode().equals(AOSCons.SUCCESS)){
			msg.setCode(Apiconstant.Do_Success.getIndex());
		}else{
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg("密码或账号错误!");
			msg.setData("");
		}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	
	/**
	 * web端登录
	 * @param request
	 * @param response
	 * @param model
	 */
	/**
	 * @api {add} app/v1/registered.jhtml web端注册
	 * @apiName web端注册
	 * @apiGroup longin
	 *
	 * @apiParam {String} phone 手机号
	 * @apiParam {String} password 密码
	 * @apiParam {String} shareId 分享id
	 * @apiParam {String} checkcode 验证码
	 * @apiParam {String} phonecode 手机验证码
	 * @apiParam {String} sms_type 短信类型 (支付验证码 "pay_password" 找回密码 "find_password",注册"register")
	 */
	@RequestMapping(value = "/app/v1/registered")
	public void registered(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		String result = WebLoginService.registered( request,  response);
		MessageVO msg = new MessageVO();
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	/**
	 * 图片验证码
	 * @param request
	 * @param response
	 * @param model
	 */
	/**
	 * @throws IOException 
	 * @api {add} app/v1/registered.ValidateCode 图片验证码
	 * @apiName 图片验证码
	 * @apiGroup longin
	 */
	@RequestMapping(value = "/app/v1/ValidateCode")
	public void ValidateCode(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model) throws IOException{
		    HttpModel httpModel = new HttpModel(request, response);
		    //在内存中构建一副图片  
            BufferedImage image = new BufferedImage(160, 40, BufferedImage.TYPE_INT_RGB);  
            //得到这个图形  
            Graphics g = image.getGraphics();  
		    ValidateCode vCode = new ValidateCode();  
            //设置背景色  
	        vCode.setBackGround(g);  
            //设置边框  
	        vCode.setBorder(g);  
            //画干扰线  
	        vCode.drawRandomLine(g);  
            //写随机数  
	        String random = vCode.drawRandomNum((Graphics2D) g);  
	        request.getSession().setAttribute("checkcode",random);  
	        //图形写给浏览器  
	        response.setContentType("image/jpeg");              //通知浏览器以图片形式打开  
	        response.setDateHeader("expries",-1);               //发送响应头，控制浏览器不要缓存  
	        response.setHeader("Cache-Control","no-cache");  
	        response.setHeader("Pragma","no-cache");  
	        ImageIO.write(image,"jpg",response.getOutputStream()); 
	}
	
	@RequestMapping(value = "/app/v1/toregister")
	public String register(HttpServletRequest request, HttpServletResponse response) throws ApiException{
		return "/project/pcstore/register.jsp";
	}
	
	
	
}
