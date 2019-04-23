/**
 * 倍增订单管理
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

import com.zjc.order.dao.ZjcBzOrderDao;
import com.zjc.order.dao.po.ZjcBzOrderPO;

/**
 * @author wugaoming
 *
 */
@Service(value="zjcBzOrderService")
public class ZjcBzOrderService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcBzOrderDao zjcBzOrderDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化后台管理倍增订单列表页面
	 * 
	 * @param httpModel
	 */
	public void initBzOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/bzOrderList.jsp");
	}
	
	
	/**
	 * 查询后台管理倍增订单数据
	 * 
	 * @param httpModel
	 */
	public void listZjcBzOrders(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcBzOrderPO> zjcBzOrderPOOrderList = sqlDao.list("com.zjc.order.dao.ZjcBzOrderDao.likeBzOrdersPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcBzOrderPOOrderList, inDto.getPageTotal()));
	}
	
	public void initBzOrderRankPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/bzOrderRankPage.jsp");
	}
	
	public void listBzOrderRankPage(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcBzOrderDao.listBzOrderRankPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void getBzOrderList(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		inDto.put("limit", 1000);
		inDto.put("page", 1);
		inDto.put("start", 1);
		List<ZjcBzOrderPO> list = sqlDao.list("com.zjc.order.dao.ZjcBzOrderDao.getBzOrderListPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
}
