/**
 * 
 */
package com.zjc.users.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.SystemCons;

import com.zjc.users.dao.ZjcUserLevelDao;
import com.zjc.users.dao.po.ZjcUserLevelPO;

/**
 * 会员等级
 * @author Administrator
 *
 */
@Service(value="ZjcUserLevelService")
public class ZjcUserLevelService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcUserLevelDao ZjcUserLevelDao;
	
	/**
	 * 会员等级页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/users/ZjcUserLevel.jsp");
		}
	
	/**
	 * 会员等级查询
	 * 
	 * @param httpModel
	 */
	public void getUserList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUserLevelPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUserLevelDao.listPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	
	/**
	 * 会员等级修改页面查询
	 * 
	 * @param httpModel
	 */
	public void getUserlevel(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUserLevelPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUserLevelDao.list",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
		
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
		//查询原店铺信息
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcUserLevelPO ZjcUserLevelPO = new ZjcUserLevelPO();
			ZjcUserLevelPO.copyProperties(inDto);
			ZjcUserLevelDao.updateByKey(ZjcUserLevelPO);
			outDto.setAppMsg("店铺信息修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
