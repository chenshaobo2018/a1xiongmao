/**
 * 
 */
package com.store.goods.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.goods.controller.GoodsController;
import com.store.goods.service.StoreOrderService;
import com.zjc.order.dao.po.ZjcOrderVO2;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;


/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/store")
public class StoreOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private StoreOrderService storeOrderService;
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * 订单页面数据初始化
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toOrderPage")
	public String initOrderPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		//查询全部支付状态
		if(AOSUtils.isEmpty(httpModel.getRequest().getSession().getAttribute("order_status"))){
			List<Dto> order_status = storeOrderService.getAllOrderStatus(httpModel);
			httpModel.getRequest().getSession().setAttribute("order_status", order_status);
		}
		if(AOSUtils.isEmpty(httpModel.getRequest().getSession().getAttribute("pay_status"))){
			List<Dto> pay_status = storeOrderService.getAllPayStatus(httpModel);
			httpModel.getRequest().getSession().setAttribute("pay_status", pay_status);
		}
		if(AOSUtils.isEmpty(httpModel.getRequest().getSession().getAttribute("pay_type"))){
			List<Dto> pay_type = storeOrderService.getAllPayType(httpModel);
			httpModel.getRequest().getSession().setAttribute("pay_type", pay_type);
		}
		if(AOSUtils.isEmpty(httpModel.getRequest().getSession().getAttribute("shipping"))){
			List<Dto> shipping = storeOrderService.getAllShipping(httpModel);
			httpModel.getRequest().getSession().setAttribute("shipping", shipping);
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		request.setAttribute("zjcStorePO", zjcStorePO);
		return "project/store/mct_indent.jsp";
	}
	
	/**
	 * 根据条件搜索商品数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/serachOrder")
	public void serachOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO orders = storeOrderService.serachOrder(httpModel);
		WebCxt.write(response, AOSJson.toJson(orders));
	}
	
	/**
	 * 订单详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/order_detail")
	public void order_detail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		ZjcOrderVO2 order = storeOrderService.order_detial(httpModel);
		WebCxt.write(response, AOSJson.toJson(order));
	}
	
	/**
	 * 打印送货单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/order_invo")
	public void order_invo(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		Dto order = storeOrderService.order_invo(httpModel);
		WebCxt.write(response, AOSJson.toJson(order));
	}
	
	/**
	 * 订单确认
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/invo_comfirm")
	public void invo_comfirm(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeOrderService.invo_comfirm(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	/**
	 * 订单发货
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delivery")
	public void delivery(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeOrderService.delivery(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	/**
	 * 根据条件搜索发货单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/serachInvo")
	public void serachInvo(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO invos = storeOrderService.serachInvo(httpModel);
		WebCxt.write(response, AOSJson.toJson(invos));
	}
	
	/**
	 * 导出订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportOrder")
	public ResponseEntity<byte[]> exportOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		return storeOrderService.exportOrder(httpModel);
	}
	
	/**
	 * 查询账单明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getTransferOrderPage")
	public void getTransferOrderPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO invos = storeOrderService.getTransferOrderPage(httpModel);
		WebCxt.write(response, AOSJson.toJson(invos));
	}
	
	/**
	 * 查询账单明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updataTransferOrderPage")
	public void updataTransferOrderPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO invos = storeOrderService.updataTransferOrderPage(httpModel);
		WebCxt.write(response, AOSJson.toJson(invos));
	}
	
	/**
	 * 导出账单详细
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/TransferExportOrder")
	public ResponseEntity<byte[]> TransferExportOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		return storeOrderService.TransferExportOrder(httpModel);
	}
	
	
	/**
	 * 查询账单明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ReminderToReturn")
	public void ReminderToReturn(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO invos = storeOrderService.ReminderToReturn(httpModel);
		WebCxt.write(response, AOSJson.toJson(invos));
	}
	
	/**
	 * 根据条件搜索催单退单数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/serachCDTHOrder")
	public void serachCDTHOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO orders = storeOrderService.serachCDTHOrder(httpModel);
		WebCxt.write(response, AOSJson.toJson(orders));
	}
	
	/**
	 * 残忍拒绝（退单）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/refuseBackOrder")
	public void refuseBackOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeOrderService.refuseBackOrder(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	/**
	 * 确认退单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/confirmBackOrder")
	public void confirmBackOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeOrderService.confirmBackOrder(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	/**
	 * 导出退单催单订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportTDOrder")
	public ResponseEntity<byte[]> exportTDOrder(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		return storeOrderService.exportTDOrder(httpModel);
	}
}
