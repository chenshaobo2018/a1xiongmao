/**
 * 
 */
package com.zjc.region.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.zjc.region.dao.ZjcRegionDao;
import com.zjc.region.dao.po.ZjcRegionPO;

/**
 * @author Administrator
 *
 */
@Service(value="zjcRegionService")
public class ZjcRegionService {

	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcRegionDao zjcRegionDao;
	@Autowired
	private IdService idService;
	
	/**
	 * 查询省信息  联动一级查询
	 * 
	 * @param httpModel
	 */
	public void getProvince(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> provinceDtos = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getProvince", qDto);
		httpModel.setOutMsg(AOSJson.toJson(provinceDtos));
	}
	
	/**
	 * 查询市区街道信息   联动查询
	 * 
	 * @param httpModel
	 */
	public void getAddr(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> addrDtos = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getAddr", qDto);
		httpModel.setOutMsg(AOSJson.toJson(addrDtos));
	}
	
	/**
	 * 通过主键id查询地址信息
	 * 
	 * @param httpModel
	 */
	public void getRegion(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		ZjcRegionPO zjcRegionPO = zjcRegionDao.selectByKey(inDto.getInteger("id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcRegionPO));
	}
	
}
