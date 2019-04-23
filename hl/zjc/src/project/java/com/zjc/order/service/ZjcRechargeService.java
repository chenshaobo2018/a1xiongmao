/**
 * 积分充值记录管理
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

import com.zjc.order.dao.ZjcRechargeDao;
import com.zjc.order.dao.po.ZjcRechargePO;

/**
 * @author wugaoming
 *
 */
@Service(value="zjcRechargeService")
public class ZjcRechargeService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcRechargeDao zjcRechargeDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化后台管理 积分充值记录列表页面
	 * 
	 * @param httpModel
	 */
	public void initRechargeListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/rechargeList.jsp");
	}
	
	
	/**
	 * 查询后台管理 积分充值记录数据
	 * 
	 * @param httpModel
	 */
	public void listZjcRecharges(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcRechargePO> zjcRechargeList = sqlDao.list("com.zjc.order.dao.ZjcRechargeDao.likeRechargesPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcRechargeList, inDto.getPageTotal()));
	}
}
