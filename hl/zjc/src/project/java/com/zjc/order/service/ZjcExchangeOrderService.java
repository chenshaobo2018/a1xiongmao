/**
 * 兑换券转账订单管理
 */
package com.zjc.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.zjc.order.dao.ZjcExchangeOrderDao;

/**
 * @author wugaoming
 *
 */
@Service(value="zjcExchangeOrderService")
public class ZjcExchangeOrderService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcExchangeOrderDao zjcExchangeOrderDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化后台管理结算中心订单列表页面
	 * 
	 * @param httpModel
	 */
	public void initExchangeOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/exchangeOrderList.jsp");
	}
	
	public void initExchangeOrderRankPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/exchangeOrderRankPage.jsp");
	}
	
	
	/**
	 * 查询后台管理结算中心订单数据
	 * 
	 * @param httpModel
	 */
	public void listZjcExchangeOrder(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcExchangeOrderDao.exchangeListPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void listZjcExchangeOrders(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcExchangeOrderDao.exchangeRankListPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void getExchangeList(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		inDto.put("limit", 1000);
		inDto.put("page", 1);
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcExchangeOrderDao.getExchangeList", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list));
	}
}
