package com.api.complaints.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.complaints.ComplaintsService;


/**
 * 需要Tiken的app接口- 投诉管理
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class ComplaintsController {
	private static final Logger logger = LoggerFactory.getLogger(ComplaintsController.class);

	@Autowired
	private ComplaintsService complaintsService;
	
	/**
	 * @api {get} api/app/v1/myComplaints.jhtml 我的投诉
	 * @apiName  我的投诉
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} complaints_type 投诉类型（0表示被投诉，1表示投诉）
	 * @apiParam {String} page 第几页
	 */
	/**
	 * app接口-获取我的投诉记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/myComplaints")
	public void getMyComplaints(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=complaintsService.getMyComplaints(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/addComplaints.jhtml 新增投诉记录
	 * @apiName  新增投诉记录
	 * @apiGroup User
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} to_user_id 被投诉人编号
	 * @apiParam {String} info 投诉理由
	 * @apiParam {String} phone 投诉人手机号码
	 */
	/**
	 * 新增投诉记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/addComplaints")
	public void addComplaints(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=complaintsService.addComplaints(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * @api {get} api/app/v1/orderComplaint.jhtml 订单投诉
	 * @apiName  订单投诉
	 * @apiGroup order
	 *
	 * @apiParam {String} token token
	 * @apiParam {String} order_id 订单id
	 * @apiParam {String} order_sn 订单编号
	 * @apiParam {String} store_id 店铺id
	 * @apiParam {String} store_name 店铺名称
	 * @apiParam {String} info 投诉内容
	 * @apiParam {String} mobile 投诉人手机号码
	 * @apiParam {String} img 图片
	 */
	/**
	 * 订单投诉
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/orderComplaint")
	public void orderComplaint(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回的数据
		String outMsg=complaintsService.orderComplaint(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
}



