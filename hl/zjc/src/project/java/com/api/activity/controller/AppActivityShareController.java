/**
 * 
 */
package com.api.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.ApiPublic.Apiconstant;
import com.api.activity.service.AppActivityShareService;
import com.api.common.po.MessageVO;
import com.wxactivity.share.dao.po.ZjcSharePrizePO;
import com.wxactivity.share.service.ShareService;
import com.wxactivity.share.util.SharePrizeUtil;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class AppActivityShareController {
	@Autowired
	private AppActivityShareService AppActivityShareService;
    
	
	/**
     * 查询助力累计数据
     * @param request
     * @param response
     */
	/**
	 * @api {get} api/activity/v1/shareHomePowers.jhtml 查询助力累计数据
	 * @apiName shareHomePowers查询助力累计数据
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id  user_id必须传的值
	 */
	@RequestMapping(value = "/activity/v1/shareHomePowers")
	public void shareHomePowers(HttpServletRequest request,HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("page", request.getParameter("page"));
		MessageVO shareGoods = AppActivityShareService.shareGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(shareGoods));
	}
	
	/**
	 * 商品分享进度
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} api/activity/v1/appGoodsShare.jhtml 商品分享进度
	 * @apiName appGoodsShare商品分享进度
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} goods_id 商品id(查该人员全部分享商品不传,查单个传)
	 * @apiParam {String} page 从第一页开始
	 */
	@RequestMapping(value = "/activity/v1/appGoodsShare")
	public void appGoodsShare(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = AppActivityShareService.appGoodsShare(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * 查询商品助力好友
	 * @param request
	 * @param response
	 */
	/**
	 * @api {get} api/activity/v1/appShareFriends.jhtml 查询商品助力好友
	 * @apiName appShareFriends查询商品助力好友
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} goods_id goods_id
	 */
	@RequestMapping(value = "/activity/v1/appShareFriends")
	public void appShareFriends(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = AppActivityShareService.appShareFriends(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * @api {get} api/activity/v1/shareActivityGoodsUser.jhtml 商品分享助力数据新增
	 * @apiName shareActivityGoodsUser商品分享助力数据新增
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id 分享人user_id
	 * @apiParam {String} goods_id 商品ID
	 */
	@RequestMapping(value = "/activity/v1/shareActivityGoodsUser")
	public void shareActivityGoodsUser(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = AppActivityShareService.addShareActivity(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	
	
	/**
	 * @api {get} api/activity/v1/shareUser.jhtml 查询分享人
	 * @apiName shareUser 查询分享人
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id 分享人user_id
	 * @apiParam {String} page 第几页
	 */
	@RequestMapping(value = "/activity/v1/shareUser")
	public void shareUser(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg = AppActivityShareService.shareUser(httpModel);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/activity/v1/appWinSharePrize.jhtml 抽奖接口
	 * @apiName shareUser 抽奖接口
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} token token
	 */
	@RequestMapping(value = "/activity/v1/appWinSharePrize")
	public void appWinSharePrize(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		MessageVO outMsg = AppActivityShareService.getWinSharePrize(request,response);
		WebCxt.write(response, AOSJson.toJson(outMsg));
	}
	
	
	/**
	 * @api {get} api/activity/v1/getAppWinPrizeList.jhtml 查询抽中的奖品
	 * @apiName getAppWinPrizeList 查询抽中的奖品分享人
	 * @apiGroup Share
	 *
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} page page
	 * @apiParam {String} token token
	 */
	@RequestMapping(value = "/activity/v1/getAppWinPrizeList")
	public void getAppWinPrizeList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		MessageVO outMsg = AppActivityShareService.getWinPrizeList(request,response);
		WebCxt.write(response, AOSJson.toJson(outMsg));
	}
	
	
	/**
	 * 被分享人进入助力详情界面
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value="/activity/v1/initShareDetail")
	public String initShareDetail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("goods_id"))){
			zjcGoodsPO = wxActivityGoodsService.getGoodsDetailByGoodsId(httpModel.getInDto());
		}
		httpModel.setAttribute("open_id",httpModel.getRequest().getSession().getAttribute("openid"));
		logger.info("----------------------shareID---------------------:"+httpModel.getInDto().getString("shareId"));
		httpModel.setAttribute("shareId",httpModel.getInDto().getString("shareId"));
		httpModel.setAttribute("goods_id",httpModel.getInDto().getString("goods_id"));
		httpModel.setAttribute("zjcGoodsPO", zjcGoodsPO);
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("shareId"))){
			ZjcUsersInfoPO zInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", httpModel.getInDto().getString("shareId")));
			httpModel.setAttribute("zjcUsersInfoPO", zInfoPO);
		}
		return "project/wxactivity/share_detail.jsp";
	}*/
	
	
	
}
