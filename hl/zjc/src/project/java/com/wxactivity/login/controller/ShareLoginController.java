/**
 * 
 */
package com.wxactivity.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.httpclient.AOSHttpClient;
import aos.framework.web.httpclient.HttpRequestVO;
import aos.framework.web.httpclient.HttpResponseVO;
import aos.framework.web.router.HttpModel;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.wx.wxConstant.Authorize;
import com.api.wx.wxConstant.WxConstant;
import com.api.wx.wxConstant.WxUserResult;
import com.google.common.collect.Maps;
import com.taobao.api.ApiException;
import com.wxactivity.login.service.wxShareLoginService;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class ShareLoginController {
	private static final Logger logger = LoggerFactory.getLogger(ShareLoginController.class);
	
	@Autowired
	private wxShareLoginService wxShareLoginService;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	/**
	 * @api {add} wx/v1/sendSMS.jhtml 短信发送
	 * @apiName 短信发送
	 * @apiGroup share
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
	@RequestMapping(value = "/activity/v1/wxShareSendMessage")
	public String wxShareSendMessage(HttpServletRequest request, HttpServletResponse response) throws ApiException{
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = wxShareLoginService.webSendMessage(httpModel);
		 WebCxt.write(response, outMsg);
		 return httpModel.getViewPath();
	}
	
	/**
	 * wx分享活动登录
	 * @param request
	 * @param response
	 * @param model
	 */
	/**
	 * @api {add} wx/v1/webLogin.jhtml wx分享活动登录
	 * @apiName wx分享活动登录
	 * @apiGroup share
	 *
	 * @apiParam {String} vercode 随机码
	 * @apiParam {String} account 账号
	 * @apiParam {String} password 密码
	 * @apiParam {String} open_id open_id
	 */
	@RequestMapping(value = "/activity/v1/wxShareLogin")
	public void wxShareLogin(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		Dto result = wxShareLoginService.webLogin(httpModel);
		MessageVO msg = new MessageVO();
		if(result.getAppCode().equals(AOSCons.SUCCESS)){
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
			msg.setData(result);
		}else{
			msg.setCode(Integer.parseInt(result.getAppCode()));
			msg.setMsg(result.getAppMsg());
			msg.setData("");
		}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	
	/**
	 * wx分享活动注册
	 * @param request
	 * @param response
	 * @param model
	 */
	/**
	 * @api {add} wx/v1/registered.jhtml wx分享活动注册
	 * @apiName wx分享活动注册
	 * @apiGroup share
	 *
	 * @apiParam {String} phone 手机号
	 * @apiParam {String} password 密码
	 * @apiParam {String} shareId 分享id
	 * @apiParam {String} phonecode 手机验证码
	 * @apiParam {String} sms_type 短信类型 (支付验证码 "pay_password" 找回密码 "find_password",注册"register")
	 */
	@RequestMapping(value = "/activity/v1/wxShareRegistered")
	public void wxShareRegistered(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		String result = wxShareLoginService.registered( request,  response);
		WebCxt.write(response, result);
	}
	
	
	/**
	 * 进入wx分享活动生成账号
	 * @param request
	 * @param response
	 * @param model
	 */
	/**
	 * @api {add} wx/v1/registered.jhtml 进入wx分享活动生成账号d
	 * @apiName 进入wx分享活动生成账号
	 * @apiGroup share
	 *
	 */
	@RequestMapping(value = "/activity/v1/wxShareUser")
	public void wxShareUser(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		String result = wxShareLoginService.wxShareUser(request,response);
		WebCxt.write(response, result);
	}
	
	
	/**
	 * 微信商城获取微信code
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getWxShareCode")
	public void getWxShareCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		//获取访问的域名地址
		String tempContextUrl = "https://zjc1518.com/aosuite/";
//		String tempContextUrl = "http://wexin.web.zjc1518.cn/aosuite/";
		String urla = tempContextUrl +"wxact/toWxShareActivity.jhtml";
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConstant.WEB_ID + "&redirect_uri="+urla+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConstant.TEST_WEB_ID + "&redirect_uri="+urla+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
	}
	
	/**
	 * 微信分享活动获取unionid
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/toWxShareActivity")
	public String toWxShareActivity(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		if(AOSUtils.isEmpty(request.getParameter("code"))){
			return httpModel.getViewPath();
		}
		Authorize result = getWxAccesstoken(request.getParameter("code").toString(),1);
//		Authorize result = getWxAccesstoken(request.getParameter("code").toString(),2);
		if(AOSUtils.isEmpty(result)){
			return httpModel.getViewPath();
		}
		logger.info("========================微信分享活动获取微信openid以及access_token：======================:" + AOSJson.toJson(result));
		//获取访问的域名地址
		String urla = "https://zjc1518.com/aosuite";
//		String urla = "http://wexin.web.zjc1518.cn/aosuite";
		if(AOSUtils.isEmpty(result.getOpenid()) || AOSUtils.isEmpty(result.getAccess_token())){
			return httpModel.getViewPath();
		}
		WxUserResult  wxuser = getWxUserInfo(result.getAccess_token(), result.getOpenid());
		logger.info("----------------------------微信分享活动获取微信基本用户信息：-------------" + AOSJson.toJson(wxuser));
		if(AOSUtils.isEmpty(wxuser)|| AOSUtils.isEmpty(wxuser.getUnionid())){
			return httpModel.getViewPath();
		}
		request.getSession().setAttribute("openid", wxuser.getUnionid());//openid存session
		request.getSession().setAttribute("wxuser", AOSJson.toJson(wxuser));//openid存session
		ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", wxuser.getUnionid()));
		if(AOSUtils.isNotEmpty(userInfo)){//绑定了账号
			request.getSession().setAttribute("user_id", userInfo.getUser_id());//user_id存session
		} 
		if(StringUtils.equals(request.getSession().getAttribute("type").toString(), "special")){//跳转到商品详情页面
			//跳转到商品详情
			response.sendRedirect(urla + "/wxact/activity/v1/getActivityGoodsDetails.jhtml?goods_id=" + request.getSession().getAttribute("goods_id"));
		} else if(StringUtils.equals(request.getSession().getAttribute("type").toString(), "sharepage")){//跳转到分享页面
			//跳转到商品详情
			response.sendRedirect(urla + "/wxact/activity/v1/initShareDetail.jhtml?goods_id=" + request.getSession().getAttribute("goods_id")+"&shareId="+request.getSession().getAttribute("share_id"));
		}else if(StringUtils.equals(request.getSession().getAttribute("type").toString(), "redsharepage")){//跳转到分享页面
			//跳转到商品详情
			response.sendRedirect(urla + "/wxact/activity/v1/initShareRedPacket.jhtml?shareId="+request.getSession().getAttribute("shareid"));
		}else {//正常页面
			response.sendRedirect(urla + request.getSession().getAttribute("methodStr").toString());
		}
		return  httpModel.getViewPath();
	
	}
	
	/**
	 * 获取accesstoken和openid
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public Authorize getWxAccesstoken(String code, int type){
		String app_id = WxConstant.APP_ID;
		String app_secret = WxConstant.APP_SECRET;
		if(type == 1){
			app_id = WxConstant.WEB_ID;
			app_secret = WxConstant.WEB_SECRET;
		}
		if(type == 2){
			app_id = WxConstant.TEST_WEB_ID;
			app_secret = WxConstant.TEST_WEB_SECRET;
		}
		Map<String, String> inMap = Maps.newHashMap();
		inMap.put("appid", app_id);
		inMap.put("secret", app_secret);
		inMap.put("code", code);
		inMap.put("grant_type", "authorization_code");
		HttpRequestVO httpRequestVO = new HttpRequestVO("https://api.weixin.qq.com/sns/oauth2/access_token", inMap);
		HttpResponseVO httpResponseVO = AOSHttpClient.execute(httpRequestVO);
		String out = httpResponseVO.getOut();
		Authorize result = AOSJson.fromJson(out, Authorize.class);
		return result;
	}
	
	/**
	 * 根据access_token 和 openid 获取微信用户的信息
	 * 
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public WxUserResult getWxUserInfo(String access_token,String openid){
		Map<String, String> inMap = Maps.newHashMap();
		inMap.put("access_token", access_token);
		inMap.put("openid", openid);
		HttpRequestVO httpRequestVO = new HttpRequestVO("https://api.weixin.qq.com/sns/userinfo", inMap);
		HttpResponseVO httpResponseVO = AOSHttpClient.execute(httpRequestVO);
		String out = httpResponseVO.getOut();
		WxUserResult result = AOSJson.fromJson(out, WxUserResult.class);
		return result;
	}
	/**
	 * @api {add} activity/v1/wxShareAddPhone.jhtml 分享是助力人填写电话默认注册
	 * @apiName 分享是助力人填写电话默认注册
	 * @apiGroup share
	 * 
	 * @apiParam {String} phone 手机号
	 * @apiParam {String} password 密码
	 * @apiParam {String} shareId 分享id
	 * @apiParam {String} phonecode 手机验证码
	 * @apiParam {String} sms_type 短信类型 (支付验证码 "pay_password" 找回密码 "find_password",注册"register")
	 * @apiParam {String} open_id open_id
	 *
	 */
	/**
	 * 分享是助力人填写电话默认注册
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/activity/v1/wxShareAddPhone")
	public void wxShareAddPhone(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		String result = wxShareLoginService.wxShareAddPhone( request, response);
		WebCxt.write(response, result);
	}
	
	/**
	 * 初始化登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initLogin")
	public String initLogin(HttpServletRequest request) {
		return "project/wxactivity/login.jsp";
	}
	
	/**
	 * 初始抽奖页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initLuckDraw")
	public String initLuckDraw(HttpServletRequest request) {
		return "project/wxactivity/activity_sign.jsp";
	}
	
	
	/**
	 * 初始化登录页面，登录后返回订单页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initLogin2")
	public String initLogin2(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("goods_id", request.getParameter("goods_id"));
		return "project/wxactivity/login2.jsp";
	}
	
	@RequestMapping(value = "/activity/v1/goRegister")
	public String goRegister(HttpServletRequest request){
		return "project/wxactivity/register.jsp";
	}
}
