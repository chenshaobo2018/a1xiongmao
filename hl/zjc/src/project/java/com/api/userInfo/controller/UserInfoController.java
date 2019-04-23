package com.api.userInfo.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.userInfo.UserInfoService;


/**
 * app接口管理控制器
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class UserInfoController {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * @api {get} api/app/v1/getUserInfo.jhtml 获取用户信息
	 * @apiName 获取用户信息
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 */
	/**
	 * app接口-获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.getUserInfo(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {update} api/app/v1/updateUserInfo.jhtml 修改用户信息
	 * @apiName 修改用户信息
	 * @apiGroup User
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} nickname 用户昵称
	 * @apiParam {String} email 邮箱
	 * @apiParam {String} head_pic 头像
	 */
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/updateUserInfo")
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = userInfoService.updateUserInfo(httpModel);
		WebCxt.write(response, outMsg);
	}
	

	/**
	 * @api {update} api/app/v1/updateUserPassWord.jhtml 修改用户密码
	 * @apiName 修改用户密码
	 * @apiGroup User
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} password 新密码
	 * @apiParam {String} new_password 新密码2
	 */
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/updateUserPassWord")
	public void updateUserPassWord(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = userInfoService.updateUserPassWord(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getMyShareInfo.jhtml 获取我的分享信息
	 * @apiName 获取我的分享信息
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} is_qualified_member 是否是合格会员(0全部;1合格;2不合格)
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-获取我的分享信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getMyShareInfo")
	public void getMyShareInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.getMyShareInfo(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getMyUserLog.jhtml 获取操作记录
	 * @apiName 获取操作记录
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} is_qualified_member 是否是合格会员
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-获取操作记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getUserLog")
	public void getUserLog(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.getUserLogs(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/updatePayPsd.jhtml 修改支付密码
	 * @apiName 修改支付密码
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} pay_password 支付密码
	 * @apiParam {String} mobile 手机号码
	 * @apiParam {String} code 验证码
	 * @apiParam {String} type 类型
	 */
	/**
	 * app接口-修改支付密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/updatePayPsd")
	public void updatePayPsd(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.updatePayPsd(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/check_user_pay_password.jhtml 查找检验用户支付密码
	 * @apiName 查找检验用户支付密码
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} design 校验码
	 * @apiParam {String} random 随机数
	 */
	/**
	 * app接口-查找检验用户支付密码
	 * 
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/check_user_pay_password")
	public void check_user_pay_password(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.check_user_pay_password(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/myApprentice.jhtml 我的下级
	 * @apiName 我的下级
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} page 第几页
	 * @apiParam {String} type 类型  0全部  1合格 2不合格
	 */
	/**
	 * app接口-我的下级
	 * 
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/myApprentice")
	public void myApprentice(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.myApprentice(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/updateClientID.jhtml 更新设备号
	 * @apiName 更新设备号
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} clientid 会员登录时的设备号
	 * @apiParam {String} src_client 登陆设备的类型：android 1 ,IOS 2,weixin 3
	 */
	/**
	 * app接口-更新设备号
	 * 
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/updateClientID")
	public void updateClientID(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.updateClientID(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/authen.jhtml 实名认证
	 * @apiName 实名认证
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
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
	    String outMsg = userInfoService.authen(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/userCommission.jhtml 用户提成
	 * @apiName 实名认证
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} cash 提成现金
	 */
	@RequestMapping(value = "/app/v1/userCommission")
	public void userCommission(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.userCommission(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getUserCommission.jhtml 查询用户提成金额和总金额
	 * @apiName getUserCommission查询用户提成金额和总金额
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 */
	@RequestMapping(value = "/app/v1/getUserCommission")
	public void getUserCommission(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userInfoService.getUserCommission(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	
	
	
}
