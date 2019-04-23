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

import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;

/**
 * @author Administrator
 *其他参数设置
 */
@Service(value="ZjcMemberOtherService")
public class ZjcMemberOtherService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberOtherDao ZjcMemberOtherDao;
	@Autowired
	private IdService idService;
	
	
	
	/**
	 * 
	 * 其他配置新增方法
	 * @param httpModel
	 */
	public void saveZjcMemberOther(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMemberOtherPO ZjcMemberOtherPO = new ZjcMemberOtherPO();
		Date date=new Date();
		ZjcMemberOtherPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberOtherPO.setDate_time(date);
		ZjcMemberOtherPO.copyProperties(inDto);
		ZjcMemberOtherDao.insert(ZjcMemberOtherPO);
		outDto.setAppMsg("其他配置新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 其他配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getZjcMemberOther(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcMemberOtherPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 其他配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getNewZjcMemberOther(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		ZjcMemberOtherPO paramDtos = (ZjcMemberOtherPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
}
