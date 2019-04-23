/**
 * 
 */
package com.zjc.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Service("zjcUserLogService")
public class ZjcUserLogService {
	
	@Autowired
	private SqlDao sqlDao;
	
	public void initLogTool(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/users/ZjcUserLogTool.jsp");
	}
	
	public void insertLog(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(inDto.getString("time"))){
			inDto.put("time", AOSUtils.getDateTimeStr());
		}
		inDto.put("show_type", 1);
		inDto.put("descs", inDto.getString("descs").trim());
		try {
			sqlDao.insert("com.zjc.users.dao.ZjcUserLogDao.insert", inDto);
		} catch (Exception e) {
			httpModel.setOutMsg("操作失败！");
		}
		httpModel.setOutMsg("操作成功！");
	}
}
