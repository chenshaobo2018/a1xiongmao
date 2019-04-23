/**
 * 
 */
package com.zjc.shareGoods.controller;

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
@RequestMapping(value = "shareGoods")
public class ShareGoodsController {
	
	@Autowired
	private ShareGoodsService shareGoodsService;
	
	/**
	 * 跳转商品详情
	 */
	@RequestMapping(value = "/shareGoodsDetails")
	public String shareGoodsPage(HttpServletRequest request,HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = shareGoodsService.getGoodsDetailByGoodsId(dto);
		httpModel.setAttribute("goodDetail", goodDetail);
		httpModel.setViewPath("project/zjc/wx/shareGoodsDetails.jsp");
		return httpModel.getViewPath();
	}
	
}
