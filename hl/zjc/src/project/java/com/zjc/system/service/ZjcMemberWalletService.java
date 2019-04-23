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

import com.zjc.system.dao.ZjcMemberWalletDao;
import com.zjc.system.dao.po.ZjcMemberWalletPO;

/**
 * @author Administrator
 *钱包参数设置
 */
@Service(value="ZjcMemberWalletService")
public class ZjcMemberWalletService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberWalletDao ZjcMemberWalletDao;
	
	@Autowired
	private IdService idService;
	
	
	
	/**
	 * 
	 * 钱包配置新增方法
	 * @param httpModel
	 */
	public void saveZjcMemberWallet(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMemberWalletPO ZjcMemberWalletPO = new ZjcMemberWalletPO();
		Date date=new Date();
		ZjcMemberWalletPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberWalletPO.setDate_time(date);
		ZjcMemberWalletPO.copyProperties(inDto);
		ZjcMemberWalletDao.insert(ZjcMemberWalletPO);
		outDto.setAppMsg("倍增配置新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 钱包配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getZjcMemberWallet(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcMemberWalletPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberWalletDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 钱包配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getNewZjcMemberWallet(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		ZjcMemberWalletPO paramDtos = (ZjcMemberWalletPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberWalletDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
}
