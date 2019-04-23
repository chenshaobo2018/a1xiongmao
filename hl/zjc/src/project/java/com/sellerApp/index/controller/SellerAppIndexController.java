package com.sellerApp.index.controller;
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
import aos.framework.web.router.HttpModel;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.find.service.FindService;
import com.api.index.IndexService;
import com.api.message.SysMessageService;
import com.api.userInfo.UserInfoService;
import com.sellerApp.goods.service.SellerAppGoodsService;
import com.sellerApp.index.SellerAppIndexService;
import com.sellerApp.order.SellerOrderService;
import com.sellerApp.storeinfo.service.SellerAppStoreinfoService;
import com.taobao.api.ApiException;


/**
 * app接口管理控制器
 * 
 * @author pb
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "sellernotokenapi")
public class SellerAppIndexController {
	private static final Logger logger = LoggerFactory.getLogger(SellerAppIndexController.class);

	@Autowired
	private SellerAppIndexService sellerAppIndexService;
	
	@Autowired
	private SellerAppGoodsService sellerAppGoodsService;
	
	@Autowired
	private SellerOrderService sellerOrderService;
	
	@Autowired
	private SellerAppStoreinfoService sellerAppStoreinfoService;
	
	@Autowired
	private FindService findService;
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * @api {get} sellernotokenapi/app/v1/findPassword.jhtml 找回密码
	 * @apiName 找回密码
	 * @apiGroup User
	 *
	 * @apiParam {String} code 验证短信
	 * @apiParam {String} mobile 电话
	 * @apiParam {String} new_password 新密码
	 * @apiParam {String} type 验证码类型
	 */
	/**
	 * app接口-找回密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/findPassword")
	public void findPassword(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = sellerAppIndexService.findPassword(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/getGoodsComments.jhtml 查看商品评论
	 * @apiName 2查看商品评论
	 * @apiGroup Goods
	 *
	 * @apiParam {String} goods_id 商品id
	 * @apiParam {String} page 当前页码
	 */
	/**
	 * 查看商品评论
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/getGoodsComments")
	public void getGoodsComments(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = sellerAppGoodsService.getGoodsComments(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/goodsDetial.jhtml   商品详情
	 * @apiName  2商品详情
	 * @apiGroup Goods
	 *
	 * @apiParam {String} goods_id 商品ID
	 */
	/**
	 * 商品详情
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "app/v1/goodsDetial")
	public void goodsDetial(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newInDto(request);
		String outString = sellerAppGoodsService.goodsDetial(inDto);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/listStoreGoods.jhtml  列表查询商家商品
	 * @apiName 列表查询商家商品
	 * @apiGroup Goods
	 *
	 * @apiParam {String} store_id 店铺id
	 * @apiParam {String} type 查询类型；1：缺货商品；2：待审商品；3：在售商品；4：下架商品
	 * @apiParam {String} goods_name 商品名称
	 * @apiParam {String} page 当前页码
	 */
	/**
	 * 列表查询商家商品
	 * 
	 * @param page 当前页码
	 * @param token 用户token
	 * @param type 查询类型；1：缺货商品；2：待审商品；3：在售商品；4：下架商品
	 */
	@RequestMapping(value = "app/v1/listStoreGoods")
	public void listStoreGoods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppGoodsService.listStoreGoods(httpModel);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/countGoodsNum.jhtml   统计商品数量
	 * @apiName  统计商品数量
	 * @apiGroup Goods
	 *
	 * @apiParam {String} store_id 店铺ID
	 */
	/**
	 * 统计商品数量
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "app/v1/countGoodsNum")
	public void countGoodsNum(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outString = sellerAppGoodsService.countGoodsNum(httpModel);
		WebCxt.write(response, outString);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/sellerOrderList.jhtml 查找订单
	 * @apiName 查找订单
	 * @apiGroup Order
	 *
	 * @apiParam {String} store_id 店铺ID
	 * @apiParam {String} order_status 0表示待支付订单；1表示待发货订单；2表示待收货订单；3表示已完成订单
	 * @apiParam {String} user_name 用户名
	 * @apiParam {String} mobile 手机号码
	 * @apiParam {String} goods_name 商品名称
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-查找订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/sellerOrderList")
	public void sellerOrderList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=sellerOrderService.sellerOrderList(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/countOrderNum.jhtml 统计订单数量
	 * @apiName 1统计订单数量
	 * @apiGroup Order
	 *
	 * @apiParam {String} store_id 店铺ID
	 */
	/**
	 * 统计订单数量
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/countOrderNum")
	public void countOrderNum(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=sellerOrderService.countOrderNum(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/getAddr.jhtml 级联查询市区街道名字列表
	 * @apiName 级联查询市区街道名字列表
	 * @apiGroup index
	 *
	 * @apiParam {String} parent_id 地区父ID，查询省信息的话，parent_id传0
	 */
	/**
	 * 商家app接口-级联查询省市区街道名字列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getAddr")
	public void getAddr(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = sellerAppIndexService.getAddr(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/getCategoryTreeApp.jhtml 查询绑定的店铺分类数据
	 * @apiName 查询绑定的店铺分类数据
	 * @apiGroup index
	 *
	 * @apiParam {String} cat_id 绑定的店铺分类ID(非必须传)
	 */
	/**
	 * 商家app接口-查询绑定的店铺分类数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getCategoryTreeApp")
	public void getCategoryTreeApp(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = sellerAppIndexService.getCategoryTreeApp(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/getStoreinfo.jhtml  店铺详情
	 * @apiName 店铺详情
	 * @apiGroup Store	
	 *
	 * @apiParam {String} store_id 店铺ID
	 */
	/**
	 * 店铺详情
	 * @param sotre_id 店铺ID
	 */
	@RequestMapping(value = "app/v1/getStoreinfo")
	public void getStoreinfo(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newInDto(request);
		//inDto.put("user_id", request.getAttribute("user_id"));
		String outString = sellerAppStoreinfoService.getStoreinfo(inDto);
		WebCxt.write(response, outString);
	}
	
	@Autowired
	private SysMessageService sysMessageService;
	
	/**
	 * @api {add} sellernotokenapi/app/v1/sendSMS.jhtml 短信发送
	 * @apiName 短信发送
	 * @apiGroup sms
	 *
	 * @apiParam {String} mobile 用户手机号
	 * @apiParam {String} sms_type 短信类型 (支付验证码 "pay_password" 找回密码 "find_password")
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
	
	/**
	 * @api {get} sellernotokenapi/app/v1/checkVersion.jhtml 获取最新版本
	 * @apiName 获取最新版本
	 * @apiGroup User
	 *
	 * @apiParam {String} terminalType 安卓 1, 苹果0
	 * @apiParam {String} type 0表示用户端；1表示商家端
	 */
	/**
	 * app接口-获取最新版本
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/checkVersion")
	public void checkVersion(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = indexService.checkVersion(httpModel,1);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} sellernotokenapi/app/v1/articleDetail.jhtml 文章详情
	 * @apiName 文章详情
	 * @apiGroup index
	 * @apiParam {String} article_id 文章ID
	 */
	/**
	 * 文章详情
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/app/v1/articleDetail")
	public void articleDetail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg= findService.articleDetail(request, response);
		WebCxt.write(response, outMsg);
	}
}