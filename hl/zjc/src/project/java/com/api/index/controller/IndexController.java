package com.api.index.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.index.IndexService;


/**
 * app接口-通用信息查询
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private IndexService indexService;
	
	/**
	 * @api {get} api/app/v1/getPoundage.jhtml 根据积分计算手续费
	 * @apiName 根据积分计算手续费
	 * @apiGroup Index
	 *
	 * @apiParam {String} points 积分数量
	 */
	/**
	 * app接口-根据积分计算手续费
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getPoundage")
	public void getPoundage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=indexService.getPoundage(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getExchangRang.jhtml 获取转账范围
	 * @apiName 获取转账范围
	 * @apiGroup Index
	 *
	 */
	/**
	 * app接口-获取转账范围
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getExchangRang")
	public void getExchangRang(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=indexService.getExchangRang(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/getAddr.jhtml 级联查询市区街道名字列表
	 * @apiName 级联查询市区街道名字列表
	 * @apiGroup User
	 *
	 * @apiParam {String} parent_id 地区父ID，查询省信息的话，parent_id传0
	 */
	/**
	 * app接口-级联查询省市区街道名字列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getAddr")
	public void getAddr(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = indexService.getAddr(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/checkVersion.jhtml 获取最新版本
	 * @apiName 获取最新版本
	 * @apiGroup User
	 *
	 * @apiParam {String} terminalType 安卓 0, 苹果1
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
	    String outMsg = indexService.checkVersion(httpModel,0);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * 定时任务-确认收货
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/v1/confrimOrder")
	public void confrimOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = indexService.confrimOrder(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} notokenapi/app/v1/getLancher.jhtml 获取会员app启动图形
	 * @apiName 获取会员app启动图形
	 * @apiGroup User
	 *
	 */
	/**
	 * app接口-获取会员app启动图形
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/getLancher")
	public void getLancher(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = indexService.getLancher(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
}



