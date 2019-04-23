/**
 * 结算中心订单管理
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

import com.zjc.order.dao.ZjcBalanceOrderDao;
import com.zjc.order.dao.po.ZjcBalanceOrderPO;

/**
 * @author wugaoming
 *
 */
@Service(value="zjcBalanceOrderService")
public class ZjcBalanceOrderService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcBalanceOrderDao zjcBalanceOrderDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化后台管理结算中心订单列表页面
	 * 
	 * @param httpModel
	 */
	public void initBalanceOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/balanceOrderList.jsp");
	}
	
	
	/**
	 * 查询后台管理结算中心订单数据
	 * 
	 * @param httpModel
	 */
	public void listZjcBalanceOrders(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcBalanceOrderPO> zjcBalanceOrderList = zjcBalanceOrderDao.likePage(inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcBalanceOrderList, inDto.getPageTotal()));
	}
}
