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
 * 需要Tiken的app接口-店铺管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class StoreTokenController {
	private static final Logger logger = LoggerFactory.getLogger(StoreTokenController.class);

	@Autowired
	private StoreService storeService;
	
	/**
	 * @api {get} api/app/v1/collectStore.jhtml 通过店铺ID收藏店铺
	 * @apiName 通过店铺ID收藏店铺
	 * @apiGroup Store
	 *
	 * @apiParam {String} store_id 店铺ID
	 * @apiParam {String} token token
	 */
	/**
	 * app接口-通过店铺ID收藏店铺
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/collectStore")
	public void collect_store(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.collect_store(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/cancleCollectStore.jhtml 通过店铺ID取消收藏店铺
	 * @apiName 通过店铺ID取消收藏店铺
	 * @apiGroup Store
	 *
	 * @apiParam {String} store_id 店铺ID
	 * @apiParam {String} token token
	 */
	/**
	 * app接口-通过店铺ID取消收藏店铺
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/cancleCollectStore")
	public void cancle_collect_store(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.cancle_collect_store(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/cancleCollectStore.jhtml 查询我的收藏店铺
	 * @apiName 查询我的收藏店铺
	 * @apiGroup Store
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-查询我的收藏店铺
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getMyCollectStore")
	public void getMyCollectStore(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.getMyCollectStore(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/checkCPIsBuy.jhtml 判断用户是否购买企业宣传
	 * @apiName 判断用户是否购买企业宣传
	 * @apiGroup Find
	 * @apiParam {String} token token
	 * @apiParam {String} cp_id 企业宣传ID
	 */
	/**
	 * 判断用户是否购买企业宣传
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/checkCPIsBuy")
	public void checkCPIsBuy(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= storeService.checkCPIsBuy(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/applyStore.jhtml 商家申请开店
	 * @apiName 商家申请开店
	 * @apiGroup Store
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} store_name 企业名字
	 * @apiParam {String} province_id 省ID
	 * @apiParam {String} city_id 市ID
	 * @apiParam {String} area_id 区ID
	 * @apiParam {String} twon_id 街道ID
	 * @apiParam {String} address_detail 详细地址
	 * @apiParam {String} lng 经度
	 * @apiParam {String} lat 纬度
	 * @apiParam {String} office_phone 办公电话
	 * @apiParam {String} contacts_name 联系人
	 * @apiParam {String} contacts_phone 联系电话
	 * @apiParam {String} business_licence_number_elc 营业执照
	 * @apiParam {String} food_hygiene_img 卫生许可证(身份证)
	 * @apiParam {String} cat_id 商品分类ID
	 * @apiParam {String} special_premit_img 其他证件
	 * @apiParam {String} store_logo 店铺logo
	 */
	/**
	 * app接口-商家申请开店
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/applyStore")
	public void applyStore(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.applyStore(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/checkShop.jhtml 检查商户
	 * @apiName 检查商户
	 * @apiGroup Store
	 *
	 * @apiParam {String} mobile 商家电话
	 */
	/**
	 * app接口-检查商户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/checkShop")
	public void checkShop(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.checkShop(request, response);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getStoreDetail.jhtml 判断是否有开通店铺
	 * @apiName 判断是否有开通店铺
	 * @apiGroup Store
	 * @apiParam {String} token token
	 */
	/**
	 * 判断是否有开通店铺
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/getStoreDetail")
	public void getStoreDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
		String outMsg = storeService.getStoreDetail(httpModel);
		//写入返回数据到页面
		WebCxt.write(response, outMsg);
	}
}



