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

import com.zjc.system.dao.ZjcMemberMultiplicationDao;
import com.zjc.system.dao.ZjcMemberSettlementDao;
import com.zjc.system.dao.po.ZjcMemberMultiplicationPO;
import com.zjc.system.dao.po.ZjcMemberSettlementPO;

/**
 * @author Administrator
 *倍增参数设置
 */
@Service(value="ZjcMemberMultiplication")
public class ZjcMemberMultiplication {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberMultiplicationDao ZjcMemberMultiplicationDao;
	
	@Autowired
	private ZjcMemberSettlementDao ZjcMemberSettlementDao;
	
	@Autowired
	private IdService idService;
	
	
	
	/**
	 * 
	 * 倍增配置新增方法
	 * @param httpModel
	 */
	public void savezjcMemberMultiplication(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMemberMultiplicationPO ZjcMemberMultiplicationPO = new ZjcMemberMultiplicationPO();
		Date date=new Date();
		ZjcMemberMultiplicationPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberMultiplicationPO.setDate_time(date);
		ZjcMemberMultiplicationPO.copyProperties(inDto);
		ZjcMemberMultiplicationDao.insert(ZjcMemberMultiplicationPO);
		ZjcMemberSettlementPO ZjcMemberSettlementPO = new ZjcMemberSettlementPO();
		ZjcMemberSettlementPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberSettlementPO.setDate_time(date);
		ZjcMemberSettlementPO.copyProperties(inDto);
		ZjcMemberSettlementDao.insert(ZjcMemberSettlementPO);
		outDto.setAppMsg("倍增配置新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 倍增配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getMemberMultiplication(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberMultiplicationDao.selectByMaxKey1",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 倍增配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getNewMemberMultiplication(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		Dto paramDtos =  (Dto) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberMultiplicationDao.selectByMaxKey1",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
}
