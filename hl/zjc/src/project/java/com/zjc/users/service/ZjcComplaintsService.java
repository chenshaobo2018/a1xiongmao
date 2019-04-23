/**
 * 
 */
package com.zjc.users.service;

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
import aos.system.common.model.UserModel;
import aos.system.common.utils.SystemCons;

import com.zjc.users.dao.ZjcComplaintsDao;
import com.zjc.users.dao.po.ZjcComplaintsPO;

/**
 * 投诉管理接口
 * @author Administrator
 *
 */
@Service(value="ZjcComplaintsService")
public class ZjcComplaintsService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcComplaintsDao ZjcComplaintsDao;
	
	/**
	 * 投诉管理页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/users/ZjcComplaints.jsp");
		}
	
	
	
	
	/**
	 * 投诉管理查询
	 * 
	 * @param httpModel
	 */
	public void getUserList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcComplaintsPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcComplaintsDao.listPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	
	/**
	 * 投诉管理查询
	 * 
	 * @param httpModel
	 */
	public void getUserLists(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcComplaintsPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcComplaintsDao.list", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 
	 * 店铺信息修改
	 * 
	 * @param httpModel
	 */
	public void updateStoreInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		//查询原店铺信息
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcComplaintsPO ZjcComplaintsPO = new ZjcComplaintsPO();
			ZjcComplaintsPO.copyProperties(inDto);
			ZjcComplaintsPO.setHandle_time(new Date());
			ZjcComplaintsPO.setAdmin_id(um.getId());
			ZjcComplaintsDao.updateByKey(ZjcComplaintsPO);
			outDto.setAppMsg("投诉信息处理成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
