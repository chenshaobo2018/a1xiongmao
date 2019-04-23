/**
 * 
 */
package com.zjc.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.system.dao.ZjcMemberSettlementDao;
import com.zjc.system.dao.po.ZjcMemberSettlementPO;

/**
 * @author Administrator
 *结算配置
 */
@Service(value="ZjcMemberSettlement")
public class ZjcMemberSettlementService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberSettlementDao ZjcMemberSettlementDao;
	
	@Autowired
	private IdService idService;
	
	
	
	/**
	 * 
	 * 结算配置新增方法
	 * @param httpModel
	 */
	public void saveZjcMemberSettlemen(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMemberSettlementPO ZjcMemberSettlementPO = new ZjcMemberSettlementPO();
		Date date=new Date();
		ZjcMemberSettlementPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberSettlementPO.setDate_time(date);
		ZjcMemberSettlementPO.copyProperties(inDto);
		ZjcMemberSettlementDao.insert(ZjcMemberSettlementPO);
		outDto.setAppMsg("结算配置新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 结算配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getZjcMemberSettlemen(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcMemberSettlementPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberSettlementDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 结算配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getNewZjcMemberSettlemen(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		ZjcMemberSettlementPO paramDtos = (ZjcMemberSettlementPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberSettlementDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
}
