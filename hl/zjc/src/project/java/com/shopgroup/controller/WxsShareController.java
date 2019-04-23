/**
 * 
 */
package com.shopgroup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.typewrap.Dto;
import aos.framework.web.router.HttpModel;

import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.shareGoods.service.ShareGoodsService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxsShare")
public class WxsShareController {
	
	@Autowired
	private ShareGoodsService shareGoodsService;

	/**
	 * 县级代理首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/initWxsShareIndex")
	public String initWxsShareIndex(HttpServletRequest request, HttpServletResponse response){
		return "project/wxs_share/wxs_share.jsp";
	}
	
	/**
	 * 1号店首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/initOneStore")
	public  String initOneStore(HttpServletRequest request, HttpServletResponse response){
		return "project/one_store/one_store.jsp";
	}
	
	
	/**
	 * 跳转商品详情
	 */
	@RequestMapping(value = "/goodsDetails")
	public String goodsDetails(HttpServletRequest request,HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = shareGoodsService.getGoodsDetailByGoodsId(dto);
		httpModel.setAttribute("goodDetail", goodDetail);
		return "project/one_store/goodsDetails.jsp";
	}
}
