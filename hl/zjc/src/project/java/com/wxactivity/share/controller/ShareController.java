/**
 * 
 */
package com.wxactivity.share.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.common.po.PageVO;
import com.api.order.OrderService;
import com.api.userInfo.UserInfoService;
import com.wxactivity.share.service.ShareService;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.shareGoods.service.ShareGoodsService;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcMessagePO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class ShareController {
	@Autowired
	private ShareService ShareService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	
	
	/**
	 * 商品分享进度
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} notokenapi/app/v1/goodsShare.jhtml 商品分享进度
	 * @apiName 商品分享进度
	 * @apiGroup Share
	 *
	 * @apiParam {String} open_id open_id
	 * @apiParam {String} goods_id 商品id
	 * @apiParam {String} page 默认第一页
	 */
	@RequestMapping(value = "/activity/v1/goodsShare")
	public void goodsShare(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("page", 1);
		String outMsg = ShareService.goodsShare(httpModel);
		httpModel.getRequest().setAttribute("PageVO", outMsg);
		//return "project/wxstore/index.jsp";
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 查询商品详情
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} notokenapi/app/v1/shareGoodsDetailed.jhtml 查询商品详情
	 * @apiName 查询商品详情
	 * @apiGroup Share
	 *
	 * @apiParam {String} goods_id 商品id
	 */
	@RequestMapping(value = "/activity/v1/shareGoodsDetailed")
	public void shareGoodsDetailed(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = ShareService.shareGoodsDetailed(httpModel);
		httpModel.getRequest().setAttribute("PageVO", outMsg);
		//return "project/wxstore/index.jsp";
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 查询商品助力好友
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} notokenapi/app/v1/shareFriends.jhtml 查询商品助力好友
	 * @apiName 查询商品助力好友
	 * @apiGroup Share
	 *
	 * @apiParam {String} share_open_id share_open_id
	 * @apiParam {String} page 第几页
	 */
	@RequestMapping(value = "/activity/v1/shareFriends")
	public void shareFriends(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = ShareService.shareFriends(httpModel);
		httpModel.getRequest().setAttribute("PageVO", outMsg);
		//return "project/wxstore/index.jsp";
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 添加助力信息
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} notokenapi/app/v1/AddShare.jhtml 添加助力信息
	 * @apiName 添加助力信息
	 * @apiGroup Share
	 *
	 * @apiParam {String} share_open_id 分享人open_id
	 * @apiParam {String} goods_id 商品ID
	 * @apiParam {String} goods_integral 商品价格
	 * @apiParam {String} open_id 助力人id
	 * @apiParam {String} img 助力人头像
	 * @apiParam {String} nickname 助力人昵称
	 */
	@RequestMapping(value = "/activity/v1/AddShare")
	public void AddShare(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = ShareService.AddShare(httpModel);
		httpModel.getRequest().setAttribute("PageVO", outMsg);
		//return "project/wxstore/index.jsp";
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 商品分享助力数据新增
	 * @apiParam {String} share_open_id 分享人open_id
	 * @apiParam {String} goods_id 商品ID
	 */
	@RequestMapping(value = "/activity/v1/shareActivityGoodsUser")
	public void shareActivityGoodsUser(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = ShareService.addShareActivity(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 查询助力好友是否可以助力
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} notokenapi/activity/v1/Is_share.jhtml 查询助力好友是否可以助力
	 * @apiName 查询助力好友是否可以助力
	 * @apiGroup Share
	 *
	 * @apiParam {String} share_open_id 分享人open_id
	 * @apiParam {String} open_id 助力人open_id
	 * @apiParam {String} goods_id 商品id
	 */
	@RequestMapping(value = "/activity/v1/Is_share")
	public void Is_share(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = ShareService.Is_share(httpModel);
		httpModel.getRequest().setAttribute("PageVO", outMsg);
		//return "project/wxstore/index.jsp";
		WebCxt.write(response, outMsg);
	}
}
