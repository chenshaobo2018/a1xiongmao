package com.api.store.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.store.StoreService;


/**
 * 不需要Tiken的app接口-店铺管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;
	
	/**
	 * @api {get} notokenapi/app/v1/getStores.jhtml 获取店铺列表
	 * @apiName 获取店铺列表
	 * @apiGroup Store
	 * 
	 * @apiParam {String} store_name 店铺名字
	 * @apiParam {String} lng 经度
	 * @apiParam {String} lat 纬度
	 * @apiParam {String} page 第几页
	 *
	 */
	/**
	 * app接口-获取店铺列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getStores")
	public void getStores(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.getStores(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getStoreInfoByStoreId.jhtml 通过store_id获取店铺信息
	 * @apiName 通过store_id获取店铺信息
	 * @apiGroup Store
	 *
	 * @apiParam {String} store_id 店铺ID
	 * @apiParam {String} token token(非必填，用来判断用户是否收藏了该店铺)
	 */
	/**
	 * app接口-通过store_id获取店铺信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getStoreInfoByStoreId")
	public void getStoreInfoByStoreId(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.getStoreInfoByStoreId(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/companyPublicList.jhtml 企业宣传列表
	 * @apiName 企业宣传列表
	 * @apiGroup Store
	 *
	 * @apiParam {String} page 当前是第几页
	 * @apiParam {String} condition 筛选条件 (all,free,unFree)
	 */
	/**
	 * 企业宣传列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/companyPublicList")
	public void companyPublicList(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.companyPublicList(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/companyPublicDetail.jhtml 企业宣传详情
	 * @apiName 企业宣传详情
	 * @apiGroup Store
	 *
	 * @apiParam {String} cp_id 宣传ID
	 */
	/**
	 * 企业宣传详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/companyPublicDetail")
	public void companyPublicDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.companyPublicDetail(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
}



