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

/**
 * @author wugaoming
 *
 */
@Service(value="zjcConsumeService")
public class ZjcConsumeService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化后台管理结算中心订单列表页面
	 * 
	 * @param httpModel
	 */
	public void initConsumePage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/consumeRankPage.jsp");
	}
	
	
	/**
	 * 查询后台管理结算中心订单数据
	 * 
	 * @param httpModel
	 */
	public void listConsumePage(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.listConsumeRankPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void getOrderList(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		inDto.put("limit", 1000);
		inDto.put("page", 1);
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getOrderListByUserId", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list));
	}
}
