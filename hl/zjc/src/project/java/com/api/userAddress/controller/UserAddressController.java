package com.api.userAddress.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.userAddress.UserAddressService;


/**
 * app接口-收货地址管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class UserAddressController {
	private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

	@Autowired
	private UserAddressService userAddressService;
	
	/**
	 * @api {get} api/app/v1/getUserAddress.jhtml 获取我的收获地址列表
	 * @apiName 获取我的收获地址列表
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-获取我的收获地址列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getUserAddress")
	public void getUserAddress(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAddressService.getUserAddress(httpModel);
	    WebCxt.write(response, outMsg);
	    httpModel.getViewPath();
	}
	
	/**
	 * @api {get} api/app/v1/updateDefaultAddress.jhtml 修改默认收货地址
	 * @apiName 修改默认收货地址
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} address_id 收货地址ID
	 */
	/**
	 * app接口-修改默认收货地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/updateDefaultAddress")
	public void updateDefaultAddress(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAddressService.updateDefaultAddress(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/saveAddress.jhtml 新增/编辑收货地址
	 * @apiName 新增/编辑收货地址
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} address_id 地址ID
	 * @apiParam {String} consignee 收货人
	 * @apiParam {String} mobile 联系电话
	 * @apiParam {String} province 省id
	 * @apiParam {String} city  市id
	 * @apiParam {String} district 区id
	 * @apiParam {String} town 街道id（选填）
	 * @apiParam {String} address 详细地址
	 */
	/**
	 * app接口-新增收货地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/saveAddress")
	public void saveAddress(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAddressService.saveAddress(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/delAddress.jhtml 删除收货地址
	 * @apiName 删除收货地址
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} address_id 收货地址ID
	 */
	/**
	 * app接口-删除收货地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/delAddress")
	public void delAddress(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAddressService.delAddress(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getUserDefaultAddress.jhtml 查询默认收货地址
	 * @apiName 查询默认收货地址
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 */
	/**
	 * app接口-获取我的收获地址列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getUserDefaultAddress")
	public void getUserDefaultAddress(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = userAddressService.getUserDefaultAddress(httpModel);
	    WebCxt.write(response, outMsg);
	    httpModel.getViewPath();
	}
	
}