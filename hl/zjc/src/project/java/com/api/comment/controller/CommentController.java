package com.api.comment.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.comment.CommentService;


/**
 * 需要Tiken的app接口- 评价管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;
	
	/**
	 * @api {get} api/app/v1/add_comment.jhtml 添加评论
	 * @apiName  添加评论
	 * @apiGroup Comment
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} order_id 订单ID（必填）
	 * @apiParam {String} goods_id 商品ID（必填）
	 * @apiParam {String} content 评论内容
	 * @apiParam {String} img 晒单图片（多个评论图片以英文逗号隔开）
	 * @apiParam {String} logistics_rank 物流评价等级（必填）
	 * @apiParam {String} quality_rank 产品质量等级（必填）
	 */
	/**
	 * app接口-添加评论
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/add_comment")
	public void add_comment(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=commentService.add_comment(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/again_content.jhtml 追加评论
	 * @apiName  追加评论
	 * @apiGroup Comment
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} order_id 订单ID（必填）
	 * @apiParam {String} goods_id 商品ID（必填）
	 * @apiParam {String} again_content 追加评论内容（必填）
	 */
	/**
	 * app接口-追加评论
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/again_content")
	public void again_content(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=commentService.again_content(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
}



