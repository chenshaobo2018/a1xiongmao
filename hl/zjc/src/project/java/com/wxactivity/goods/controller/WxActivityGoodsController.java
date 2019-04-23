/**
 * 
 */
package com.wxactivity.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.api.common.po.PageVO;
import com.wxactivity.goods.service.WxActivityGoodsService;
import com.wxactivity.share.service.ShareService;
import com.zjc.goods.dao.po.ZjcGoodsPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class WxActivityGoodsController {

	@Autowired
	private WxActivityGoodsService wxGoodsService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ShareService ShareService;
	
	// 初始化活动首页
	@RequestMapping(value = "/activity/v1/initActivityIndex")
	public String initActivityIndex(HttpServletRequest request,HttpServletResponse response){
		return "project/wxactivity/goods_serach.jsp";
	}

	// 查询商品方法
	@RequestMapping(value = "/activity/v1/getActivityWxGoods")
	public void getActivityWxGoods(HttpServletRequest request,HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("page", request.getParameter("page"));
		PageVO PageVO = wxGoodsService.getWxGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(PageVO));
	}

	@RequestMapping(value = "/activity/v1/wxActivityGoodsSearch")
	public String wxActivityGoodsSearch(HttpServletRequest request,HttpServletResponse response) {
		return "project/wxstore/goods_search.jsp";
	}

	/**
	 * 跳转商品详情
	 */
	@RequestMapping(value = "/activity/v1/getActivityGoodsDetails")
	public String getActivityGoodsDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = wxGoodsService.getGoodsDetailByGoodsId(dto);
		httpModel.setAttribute("goodDetail", goodDetail);
		httpModel.setAttribute("user_id", request.getSession().getAttribute("user_id"));
		httpModel.setAttribute("openid", request.getSession().getAttribute("openid"));
		httpModel.setAttribute("direct", dto.getBoolean("direct"));
		String openid = request.getSession().getAttribute("openid") == null ? "" : request.getSession().getAttribute("openid").toString();
		boolean isShareGoods = ShareService.isGoodsShare(openid,httpModel.getInDto().getString("goods_id"));
		httpModel.setAttribute("isShareGoods", isShareGoods);
		return "project/wxactivity/goods_detial.jsp";
	}


	/**
	 * 新增收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/activity/v1/saveActivityAddress")
	public void saveActivityAddress(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto inDto = httpModel.getInDto();
		 inDto.put("openid", request.getSession().getAttribute("openid"));
		 String outMsg = wxGoodsService.addNewAdress(inDto);
		 WebCxt.write(response, outMsg);
	}


	/**
	 * 初始化分享助力页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initActivityShareHelp")
	public String initActivityShareHelp(HttpServletRequest request,
			HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("goods_id",
				httpModel.getInDto().getString("goods_id"));
		httpModel.setAttribute("user_id",
				httpModel.getInDto().getString("user_id"));
		request.getSession().setAttribute("share_openid",
				httpModel.getInDto().getString("share_openid"));
		return "project/zjc/activity/wxShareHelp.jsp";
	}

}
