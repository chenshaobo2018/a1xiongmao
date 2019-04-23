/**
 * 
 */
package com.sellerApp.storeinfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.api.common.po.MessageVO;
import com.sellerApp.storeinfo.service.SellerAppStoreinfoService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "sellerapi")
public class SellerAppStoreinfoController {
	
	private static Logger logger = LoggerFactory.getLogger(SellerAppStoreinfoController.class);
	
	@Autowired
	private SellerAppStoreinfoService sellerAppStoreinfoService;
	
	/**
	 * @api {get} sellerapi/app/v1/transfer.jhtml  商家向自己的会员号转账
	 * @apiName 商家向自己的会员号转账
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} pay_points 转账金额
	 */
	/**
	 * 商家向自己的会员号转账
	 * @param token 用户token
	 * @param pay_points 转账金额
	 */
	@RequestMapping(value = "app/v1/transfer")
	public void transfer(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newInDto(request);
		inDto.put("user_id", request.getAttribute("user_id"));
		String outString = sellerAppStoreinfoService.transfer(inDto);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/entrusted_transfer.jhtml  委托转账详细
	 * @apiName 1委托转账详细
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 */
	/**
	 * 委托转账详细
	 * @param token 用户token
	 */
	@RequestMapping(value = "app/v1/entrusted_transfer")
	public void entrusted_transfer(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newInDto(request);
		inDto.put("user_id", request.getAttribute("user_id"));
		String outString = sellerAppStoreinfoService.entrusted_transfer(inDto);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/store_data.jhtml  店铺数据
	 * @apiName 2店铺数据
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} add_time 统计年月
	 */
	/**
	 * 店铺数据
	 * @param token 用户token
	 */
	@RequestMapping(value = "app/v1/store_data")
	public void store_data(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppStoreinfoService.store_data(httpModel);
		WebCxt.write(response, outString);
	}

	/**
	 * @api {get} sellerapi/app/v1/sysMsg.jhtml  系统消息
	 * @apiName 1系统消息
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} page 当前页码
	 */
	/**
	 * 系统消息
	 * 
	 * @param token 用户token
	 * @param page 当前页码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/sysMsg")
	public void sysMsg(HttpServletRequest request, HttpServletResponse response){
		String outString = sellerAppStoreinfoService.sysMsg(request,response);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/uploadClient.jhtml  更新登陆用户的设备信息
	 * @apiName 1更新登陆用户的设备信息
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} clientId 当前登陆人的设备号
	 * @apiParam {String} client_type 当前登陆人的设备类型：android 1 ,IOS 2,weixin 3
	 */
	/**
	 * 更新登陆用户的设备信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/uploadClient")
	public void uploadClient(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppStoreinfoService.uploadClient(httpModel);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/updateAppStoreInfo.jhtml  修改店铺信息
	 * @apiName 修改店铺信息
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 店铺类型
	 * @apiParam {String} cat_id 商品分类ID
	 * @apiParam {String} province_id 省id
	 * @apiParam {String} city_id 市id
	 * @apiParam {String} area_id 区id
	 * @apiParam {String} twon_id 镇id
	 * @apiParam {String} area_info 省市区地址
	 * @apiParam {String} address_detail 详细地址
	 * @apiParam {String} store_logo 店铺logo
	 * @apiParam {String} contacts_name 联系人
	 * @apiParam {String} contacts_phone 联系电话
	 * @apiParam {String} office_phone 办公电话
	 * @apiParam {String} contacts_email 电子邮件
	 * @apiParam {String} special_premit_img 其他证件
	 * @apiParam {String} business_licence_number_elc 营业执照
	 * @apiParam {String} food_hygiene_img 身份证照
	 */
	/**
	 * 修改店铺信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/updateAppStoreInfo")
	public void updateAppStoreInfo(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppStoreinfoService.updateAppStoreInfo(httpModel);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/saveStoreDept.jhtml  提交委托申请
	 * @apiName 1提交委托申请
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 * @apiParam {String} real_name 委托人姓名
	 * @apiParam {String} id_card 委托人身份证号
	 * @apiParam {String} contract_mobile 联系电话
	 * @apiParam {String} store_name 店铺名称
	 * @apiParam {String} account_name 开户户名
	 * @apiParam {String} account_mumber 开户账号
	 * @apiParam {String} bank 开户行
	 */
	/**
	 * 提交委托申请
	 * 
	 * @param token 用户token
	 * @param real_name 委托人姓名
	 * @param id_card 委托人身份证号
	 * @param contract_mobile 联系电话
	 * @param store_name 店铺名称
	 * @param account_name 开户户名
	 * @param account_mumber 开户账号
	 * @param bank 开户行
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/saveStoreDept")
	public void saveStoreDept(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppStoreinfoService.saveStoreDept(httpModel);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellerapi/app/v1/create_user_account.jhtml  老会员生成对应的商家用户信息
	 * @apiName 老会员生成对应的商家用户信息
	 * @apiGroup Store
	 *
	 * @apiParam {String} token 用户token
	 */
	/**
	 * 老会员生成对应的商家用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/create_user_account")	
	public void create_user_account(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppStoreinfoService.create_user_account(httpModel);
		WebCxt.write(response, outString);
	}
}
