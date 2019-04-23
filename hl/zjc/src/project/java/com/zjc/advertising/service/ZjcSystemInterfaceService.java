/**
 * 
 */
package com.zjc.advertising.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.advertising.dao.ZjcSystemInterfaceDao;
import com.zjc.advertising.dao.po.ZjcSystemInterfacePO;

/**
 * @author Administrator
 *接口管理
 */
@Service(value="ZjcSystemInterfaceService")
public class ZjcSystemInterfaceService {
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcSystemInterfaceDao ZjcSystemInterfaceDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 接口管理列表页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/advertising/interface.jsp");
		}
  
	/**
	 * 
	 * 新增接口管理
	 * @param httpModel
	 */
	public void saveZjcSystemInterface(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcSystemInterfacePO ZjcSystemInterfacePO = new ZjcSystemInterfacePO();
		Date date=new Date();
		ZjcSystemInterfacePO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcSystemInterfacePO.setDate_time(date);
		ZjcSystemInterfacePO.copyProperties(inDto);
		ZjcSystemInterfaceDao.insert(ZjcSystemInterfacePO);
		outDto.setAppMsg("接口信息新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 接口管理页面显示查询
	 * @param httpModel
	 */
	public void getZjcSystemInterface(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcSystemInterfacePO> paramDtos = sqlDao.list("com.zjc.advertising.dao.ZjcSystemInterfaceDao.listPage",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	
	/**
	 * 接口管理修改数据
	 * @param httpModel
	 */
	public void getlistPage(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcSystemInterfacePO> paramDtos = sqlDao.list("com.zjc.advertising.dao.ZjcSystemInterfaceDao.list",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 
	 * 接口管理修改
	 * @param httpModel
	 */
	public void updateInterface(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcSystemInterfacePO ZjcSystemInterfacePO = new ZjcSystemInterfacePO();
			ZjcSystemInterfacePO.copyProperties(inDto);
			ZjcSystemInterfaceDao.updateByKey(ZjcSystemInterfacePO);
			outDto.setAppMsg("接口信息修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 
	 * 接口管理删除方法
	 * @param httpModel
	 */
	public void updatestate(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcSystemInterfacePO ZjcSystemInterfacePO = new ZjcSystemInterfacePO();
			ZjcSystemInterfacePO.setState(0);
			ZjcSystemInterfacePO.copyProperties(inDto);
			ZjcSystemInterfaceDao.updateByKey(ZjcSystemInterfacePO);
			outDto.setAppMsg("接口信息删除成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
