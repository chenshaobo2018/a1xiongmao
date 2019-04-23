/**
 * 
 */
package com.api.pintuan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.common.po.MessageVO;
import com.api.pintuan.service.PinTuanService;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class PinTuanController {
	@Autowired
	private PinTuanService PinTuanService;
	/**
	 * @api {get} notokenapi/app/v1/queryPinTuan.jhtml 查询现金拼团商品
	 * @apiName queryPinTuan查询现金拼团商品
	 * @apiGroup goods
	 *
	 * @apiParam {String} page 第几页
	 * @apiParam {String} cat_id 一级分类
	 */
	/**
	 * app接口-提交易物交易
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/queryPinTuan")
	public void queryPinTuan(HttpServletRequest request, HttpServletResponse response){
		//获取返回的数据
		String outMsg=PinTuanService.queryPinTuan(request,response);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/queryPinTuanInvitation.jhtml 查询邀请拼团
	 * @apiName queryPinTuanInvitation查询邀请拼团
	 * @apiGroup Invitation
	 *
	 * @apiParam {String} goods_id 商品id
	 */
	/**
	 * app接口-提交易物交易
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/queryPinTuanInvitation")
	public void queryPinTuanInvitation(HttpServletRequest request, HttpServletResponse response){
		//获取返回的数据
		String outMsg=PinTuanService.queryPinTuanInvitation(request,response);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getPinTuanWxGoods.jhtml 易物卷拼团商品
	 * @apiName getPinTuanWxGoods易物卷拼团商品
	 * @apiGroup goods
	 *
	 * @apiParam {String} page 第几页
	 * @apiParam {String} cat_id 一级分类
	 */
	/**
	 * app接口-提交易物交易
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	    // 查询商品方法
		@RequestMapping(value = "/app/v1/getPinTuanWxGoods")
		public void getPinTuanWxGoods(HttpServletRequest request,HttpServletResponse response) {
			HttpModel httpModel = new HttpModel(request, response);
			httpModel.setAttribute("page", request.getParameter("page"));
			MessageVO MessageVO = PinTuanService.getPinTuanWxGoods(httpModel);
			httpModel.setAttribute("PageVO", MessageVO.getData());
			WebCxt.write(response, AOSJson.toJson(MessageVO));
		}
}
