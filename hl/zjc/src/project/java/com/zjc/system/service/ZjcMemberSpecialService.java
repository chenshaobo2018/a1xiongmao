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

import com.zjc.system.dao.ZjcMemberSpecialDao;
import com.zjc.system.dao.po.ZjcMemberSpecialPO;

/**
 * @author Administrator
 *特殊商品参数设置
 */
@Service(value="ZjcMemberSpecialService")
public class ZjcMemberSpecialService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberSpecialDao ZjcMemberSpecialDao;
	
	@Autowired
	private IdService idService;
	
	
	
	/**
	 * 
	 * 特殊商品配置新增方法
	 * @param httpModel
	 */
	public void saveZjcMemberSpecial(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMemberSpecialPO ZjcMemberSpecialPO = new ZjcMemberSpecialPO();
		Date date=new Date();
		ZjcMemberSpecialPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberSpecialPO.setDate_time(date);
		ZjcMemberSpecialPO.copyProperties(inDto);
		ZjcMemberSpecialDao.insert(ZjcMemberSpecialPO);
		outDto.setAppMsg("特殊商品配置新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 特殊商品配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getZjcMemberSpecial(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcMemberSpecialPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberSpecialDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 特殊商品配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getNewZjcMemberSpecial(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		ZjcMemberSpecialPO paramDtos = (ZjcMemberSpecialPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberSpecialDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
}
