/**
 * 
 */
package com.zjc.advertising.service;

import java.util.ArrayList;
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

import com.zjc.advertising.dao.ZjcAppversonDao;
import com.zjc.advertising.dao.ZjcSystemInterfaceDao;
import com.zjc.advertising.dao.po.ZjcAppversonPO;
import com.zjc.advertising.dao.po.ZjcSystemInterfacePO;

/**
 * @author Administrator
 *版本信息管理
 */
@Service(value="ZjcAppversonService")
public class ZjcAppversonService {
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcAppversonDao ZjcAppversonDao;
	
	@Autowired
	private ZjcSystemInterfaceDao ZjcSystemInterfaceDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 接口管理列表页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setAttribute("juid", httpModel.getUserModel().getJuid());
			httpModel.setViewPath("project/zjc/advertising/ZjcAppverson.jsp");
		}
  
	/**
	 * 
	 * 新增接口管理
	 * @param httpModel
	 */
	public void saveZjcAppverson(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcAppversonPO ZjcAppversonPO = new ZjcAppversonPO();
        String app_verson_names=(String) inDto.get("app_verson_names");
        String app_type=(String) inDto.get("app_type");
        ZjcAppversonPO.setApp_verson_name(app_verson_names);
        List<ZjcAppversonPO> param=new ArrayList<ZjcAppversonPO>();
        if(app_type==null||"".equals(app_type)){
        	outDto.setAppMsg("应用类型不能为空!");
        }else {
        	 ZjcAppversonPO.setApp_type(Integer.parseInt(app_type));
        	  param = sqlDao.list("com.zjc.advertising.dao.ZjcAppversonDao.selectOnes",ZjcAppversonPO);
        	  if(param.size()==0){
      			ZjcSystemInterfacePO ZjcSystemInterface = new ZjcSystemInterfacePO();
      			ZjcAppversonPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
      			ZjcAppversonPO.copyProperties(inDto);
      			ZjcAppversonPO.setAdd_time(new Date());
      			ZjcSystemInterface.setVersion_number(app_verson_names);
      			List<ZjcSystemInterfacePO> paramDtos = sqlDao.list("com.zjc.advertising.dao.ZjcSystemInterfaceDao.list",ZjcSystemInterface);
      			for (int i = 0; i < paramDtos.size(); i++) {
      				ZjcSystemInterfacePO ZjcSystemInterfacePO = new ZjcSystemInterfacePO();
      				ZjcSystemInterfacePO.copyProperties(paramDtos.get(i));
      				ZjcSystemInterfacePO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
      				ZjcSystemInterfacePO.setDate_time(new Date());
      				ZjcSystemInterfacePO.setVersion_number(ZjcAppversonPO.getApp_verson_name());;
      				ZjcSystemInterfaceDao.insert(ZjcSystemInterfacePO);
      			}
      			ZjcAppversonDao.insert(ZjcAppversonPO);
      			outDto.setAppMsg("版本信息新增成功");
      		}else {
      			outDto.setAppMsg("该版本号已存在!");
      		}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 接口管理页面显示查询
	 * @param httpModel
	 */
	public void getZjcAppverson(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcAppversonPO> paramDtos = sqlDao.list("com.zjc.advertising.dao.ZjcAppversonDao.listPage",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 接口管理修改数据
	 * @param httpModel
	 */
	public void getlistPage(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcAppversonPO> paramDtos = sqlDao.list("com.zjc.advertising.dao.ZjcAppversonDao.list",qDto);
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
			ZjcAppversonPO ZjcAppversonPO = new ZjcAppversonPO();
			ZjcAppversonPO.copyProperties(inDto);
			ZjcAppversonDao.updateByKey(ZjcAppversonPO);
			outDto.setAppMsg("版本信息修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	/**
	 * 下拉
	 * @param httpModel
	 */
	public void listBrandComboBoxData(HttpModel httpModel){
		List<Dto> list = sqlDao.list("com.zjc.advertising.dao.ZjcAppversonDao.listBrandComboBoxData", null);
		httpModel.setOutMsg(AOSJson.toJson(list));
	}

}
