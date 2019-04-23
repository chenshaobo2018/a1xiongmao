/**
 * 
 */
package com.api.order.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.web.router.HttpModel;

import com.api.order.OrderService;

/**
 * @author Administrator
 *订单模块
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class OrderController {
	
	@Autowired
	private OrderService OrderService;
	
	    
		/**
		* @api {get} /app/v1/my_order_list 我的订单列表
		* @apiName 我的订单列表
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} order_status 订单状态 0-待支付,1-待发货,2-待收货,3-待评价,4-完成;允许值: 0, 1, 2, 3, 4
		* @apiParam {String} is_shop_group 是否是拼团订单(只在拼单里面传,其他地方不传改参数)
		* @apiParam {String} page 第几页
		*/
		//我的订单列表
		@RequestMapping(value = "/app/v1/my_order_list")
		public void myOrderList(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.myOrderList(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		* @api {get} /app/v1/order_info 订单详情
		* @apiName 订单详情
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} order_id 订单id
		*/
		//订单详情
		@RequestMapping(value = "/app/v1/order_info")
		public void order_Info(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.order_Info(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		* @api {get} /app/v1/order_del 删除订单
		* @apiName 删除订单
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} order_id 订单id
		* @apiParam {String} order_status 订单状态
		*/
		//取消订单
		//删除订单
		@RequestMapping(value = "/app/v1/order_del")
		public void orderDel(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.orderDel(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		/**
		* @api {get} /app/v1/settle_center 根据输入的金额，计算结算明细
		* @apiName 根据输入的金额，计算结算明细
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} center_id 结算中心Id
		* @apiParam {String} center_name 结算中心真实姓名
		* @apiParam {String} points 结算金额
		*/
		//结算中心
		@RequestMapping(value = "/app/v1/settle_center")
		public void settleCenter(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.settleCenter(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		* @api {get} /app/v1/settle_center 倍增订单提交
		* @apiName 倍增订单提交
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} center_id 结算中心Id
		* @apiParam {String} center_name 结算中心真实姓名
		* @apiParam {String} points 结算金额
		* @apiParam {String} random 随机码
		* @apiParam {String} sign 校验包 {加密方式见登录}
		*/
		//结算中心确认结算
		@RequestMapping(value = "/app/v1/confirmSettle")
		public void confirmSettle(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.confirmSettle(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		* @api {get} api/app/v1/order_pay.jhtml  计算中心确认结算
		* @apiName 订单支付
		* @apiGroup Order
		*
		* @apiParam {String} token tokrn
		* @apiParam {String} orderId 订单id
		* @apiParam {String} random 随机数
		* @apiParam {String} sign 校验包（random+mobile+password的MD5+"zjc_1815"），然后整体MD5
		*/
		//订单支付
		@RequestMapping(value = "/app/v1/order_pay")
		public void order_pay(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.order_pay(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		
		}
		
		/**
		* @api {get} api/app/v1/mixed_pay.jhtml  订单混合支付扣除易物券
		* @apiName 订单支付
		* @apiGroup Order
		*
		* @apiParam {String} order_id 订单id
		*/
		//订单支付
		@RequestMapping(value = "/app/v1/mixed_pay")
		public void mixed_pay(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.mixed_pay(httpModel);
			//返回数据
			WebCxt.write(response, outMsg);
		
		}
		
		
		/**
		* @api {get} api/app/v1/mixDrops_pay.jhtml  订单混合支付扣除滴
		* @apiName 订单支付
		* @apiGroup Order
		*
		* @apiParam {String} order_id 订单id
		*/
		//订单支付
		@RequestMapping(value = "/app/v1/mixDrops_pay")
		public void mixDrops_pay(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.mixDrops_pay(httpModel);
			//返回数据
			WebCxt.write(response, outMsg);
		
		}
		
		
		//取消订单
		/*@RequestMapping(value = "/app/v1/cancel_order")
		public void cancel_order(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.cancel_order(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		
		}*/
		
		
		/**
		 * @api {get} api/app/v1/confirm_order.jhtml 确认收货
		 * @apiName 确认收货
		 * @apiGroup Order
		 *
		 * @apiParam {String} token token
		 * @apiParam {String} order_id 订单id
		 */
		/**
		 * app接口-确认收货
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value = "/app/v1/confirm_order")
		public void confirm_order(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.confirm_order(httpModel);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		 * @api {get} api/app/v1/recharge_order_list.jhtml 积分充值订单列表
		 * @apiName 积分充值订单列表
		 * @apiGroup Order
		 *
		 * @apiParam {String} token token
		 * @apiParam {String} page 当前第几页
		 * @apiParam {String} status 支付状态 0未支付，1已支付
		 */
		/**
		 * 积分充值订单列表
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value = "/app/v1/recharge_order_list")
		public void recharge_order_list(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.recharge_order_list(httpModel);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		 * @api {get} api/app/v1/check_buy_points.jhtml 检查购买积分所需现金
		 * @apiName 检查购买积分所需现金
		 * @apiGroup Order
		 *
		 * @apiParam {String} token token
		 * @apiParam {String} buy_points 输入的积分购买数
		 */
		/**
		 * 积分充值订单列表
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value = "/app/v1/check_buy_points")
		public void check_buy_points(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.check_buy_points(httpModel);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		 * @api {get} api/app/v1/check_user_points.jhtml 今天还能购买多少积分
		 * @apiName 今天还能购买多少积分
		 * @apiGroup Order
		 *
		 * @apiParam {String} token token
		 */
		/**
		 * 今天还能购买多少积分
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value = "/app/v1/check_user_points")
		public void check_user_points(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.check_user_points(httpModel);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		/** @api {post} api/app/v1/orderSub.jhtml 提交商品页面
		 * @apiName 提交商品页面
		 * @apiGroup Order
		 * 
		 * @apiParam {String} token token
		 * @apiParam {String} address_id 地址id
		 * @apiParam {String} total_amount  订单总额
		 * @apiParam {String} store_id  店铺id
		 * @apiParam {String} Goods  商品数据
		 * @apiParam {String} pay_type  支付方式
		 * @apiParam {String} user_note  备注信息
		 * @apiParam {String} voucher_id  代金券id
		 */
		/**
		 * 提交商品页面
		 * @param request
		 * @param response
		 * @return
		 * @throws ParseException 
		 */
		@RequestMapping(value = "/app/v1/orderSub")
		public void orderSub(HttpServletRequest request, HttpServletResponse response) throws ParseException{
			 HttpModel httpModel = new HttpModel(request, response);
			 String outMsg=OrderService.orderSub(request, response);
			 WebCxt.write(response, outMsg);
		}
		/** @api {post} api/app/v1/ps_account.jhtml 提交商品页面
		 * 线下支付
		 * @apiGroup Order
		 * 
		 * @apiParam {String} token token
		 * @apiParam {String} pay_code 支付随机码
		 * @apiParam {String} sellerId  商户Id
		 * @apiParam {String} point  支付金额
		 * @apiParam {String} design  校验码(支付随机码+用户电话+用户支付密码+zjc_1815){用户支付密码加密方式见注册}
		 */
		@RequestMapping(value = "/app/v1/userScanPay")
		public void userScanPay(HttpServletRequest request, HttpServletResponse response){
			 HttpModel httpModel = new HttpModel(request, response);
			 String outMsg=OrderService.userScanPay(request, response);
			 WebCxt.write(response, outMsg);
		}
		
		/**
		 * 支付随机码
		 * @param request
		 * @param response
		 * 
		 * @apiParam {String} user_id  用户编码
		 * 
		 */
		@RequestMapping(value = "/app/v1/pay_ment")
		public void pay_ment(HttpServletRequest request, HttpServletResponse response){
			 HttpModel httpModel = new HttpModel(request, response);
			 String outMsg=OrderService.pay_ment(request, response);
			 WebCxt.write(response, outMsg);
		}
		
		/** @api {post} api/app/v1/getVouchers.jhtml 获取可用代金券列表
		 * @apiName 获取可用代金券列表
		 * @apiGroup Order
		 * 
		 * @apiParam {String} token token
		 * @apiParam {String} is_voucher 商品代金券类型 0：不支持代金券；1:代金券商品；
		 */
		/**
		 * 获取可用代金券列表
		 * 
		 * @param request
		 * @param response
		 */
		@RequestMapping(value = "/app/v1/getVouchers")
		public void getVouchers(HttpServletRequest request, HttpServletResponse response){
			 HttpModel httpModel = new HttpModel(request, response);
			 String outMsg=OrderService.getVouchers(httpModel);
			 WebCxt.write(response, outMsg);
		}
		
		/** @api {post} api/app/v1/getMyVouchers.jhtml 获取我的代金券列表
		 * @apiName 获取我的代金券列表
		 * @apiGroup Order
		 * 
		 * @apiParam {String} token token
		 * @apiParam {String} is_use 是否是代金券商品 ：0未使用；1已使用（不传查询全部代金券）
		 */
		/**
		 * 获取代金券列表
		 * 
		 * @param request
		 * @param response
		 */
		@RequestMapping(value = "/app/v1/getMyVouchers")
		public void getMyVouchers(HttpServletRequest request, HttpServletResponse response){
			 HttpModel httpModel = new HttpModel(request, response);
			 String outMsg=OrderService.getMyVouchers(httpModel);
			 WebCxt.write(response, outMsg);
		}
		
		/**
		* @api {get} /app/v1/orderDetail 订单快照
		* @apiName 订单快照
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} order_id 订单id
		*/
		//订单详情
		@RequestMapping(value = "/app/v1/orderDetail")
		public void orderDetail(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.orderDetail(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		/**
		* @api {get} /app/v1/transferOrderDetail 订单快照
		* @apiName 用户订单台账
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} transfer_type 0未转账1转账
		* @apiParam {String} add_time 下单时间
		* @apiParam {String} page 第几页
		*/
		//用户订单台账
		@RequestMapping(value = "/app/v1/transferOrderDetail")
		public void transferOrderDetail(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.transferOrderDetail(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
	/**
		* @api {get} /app/v1/updateTransferOrder 用户订单退单催单
		* @apiName 用户订单退单催单
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} type 1为催单2为退单
		* @apiParam {String} order_id order_id
		* @apiParam {String} reminder 催单次数
		* @apiParam {String} reminderTime 催单时间
		* @apiParam {String} single_back 退单
		* @apiParam {String} single_back_time 退单时间
		* 
		*/
		//用户订单台账
		@RequestMapping(value = "/app/v1/updateTransferOrder")
		public void updateTransferOrder(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.updateTransferOrder(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
		
		/**
		* @api {get} /app/v1/updateOperateOrder 用户订单退单催单
		* @apiName 用户订单退单催单
		* @apiGroup Order
		*
		* @apiParam {String} token 用户token
		* @apiParam {String} type 1为催单2为退单
		* @apiParam {String} order_id 订单id
		* @apiParam {String} goods_id 商品id
		* 
		*/
		//用户订单台账
		@RequestMapping(value = "/app/v1/updateOperateOrder")
		public void updateOperateOrder(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			//获取返回的数据
			String outMsg=OrderService.updateOperateOrder(request, response);
			//返回数据
			WebCxt.write(response, outMsg);
		}
}
