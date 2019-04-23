/**
 * 
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

import com.api.order.dao.ZjcXxOrderDao;
import com.api.order.dao.po.ZjcXxOrderPO;

/**
 * @author Administrator
 *
 */
@Service("zjcXXOrderService")
public class ZjcXXOrderService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcXxOrderDao zjcXxOrderDao;
	
	public void initXXOrderPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/xxOrderList.jsp");
	}
	
	public void initXXOrderListPage(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<ZjcXxOrderPO> list = sqlDao.list("com.api.order.dao.ZjcXxOrderDao.getListPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void initXXOrderRankPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/xxOrderRankPage.jsp");
	}
	
	public void listXXOrderRankPage(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.api.order.dao.ZjcXxOrderDao.getXXOrderRankPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void getOrderList(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<ZjcXxOrderPO> list = sqlDao.list("com.api.order.dao.ZjcXxOrderDao.getOrderList", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
}
