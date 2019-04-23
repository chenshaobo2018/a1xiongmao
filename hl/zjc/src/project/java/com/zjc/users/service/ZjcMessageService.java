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
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.model.UserModel;
import aos.system.common.utils.SystemCons;

import com.zjc.users.dao.ZjcMessageDao;
import com.zjc.users.dao.po.ZjcMessagePO;
import com.zjc.users.dao.po.ZjcUserLevelPO;

/**
 * 系统消息管理接口
 * @author Administrator
 *
 */
@Service(value="ZjcMessageService")
public class ZjcMessageService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMessageDao ZjcMessageDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 系统消息页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/users/ZjcMessage.jsp");
		}
	
	/**
	 * 系统消息查询
	 * 
	 * @param httpModel
	 */
	public void getUserList(HttpModel httpModel){
		
		Dto qDto = httpModel.getInDto();
		List<ZjcUserLevelPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcMessageDao.like", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	
	/**
	 * 
	 * 系统消息修改
	 * 
	 * @param httpModel
	 */
	public void updateStoreInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//查询原店铺信息
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcMessagePO ZjcMessagePO = new ZjcMessagePO();
			ZjcMessagePO.copyProperties(inDto);
			ZjcMessageDao.updateByKey(ZjcMessagePO);
			outDto.setAppMsg("系统消息修改成功");
		} else {
			outDto.setAppMsg("系统消息修改失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 
	 * 新增系统消息
	 * @param httpModel
	 */
	public void saveMsg(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMessagePO message = ZjcMessageDao.selectOne(Dtos.newDto("title", inDto.getString("title")));
		if(AOSUtils.isEmpty(message)){//标题不能重复
			UserModel um=httpModel.getUserModel();
			ZjcMessagePO ZjcMessagePO = new ZjcMessagePO();
			Date date=new Date();
			ZjcMessagePO.setMessage_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcMessagePO.setAdmin_id(String.valueOf(um.getId()));
			ZjcMessagePO.setSend_time(date);
			ZjcMessagePO.copyProperties(inDto);
			ZjcMessageDao.insert(ZjcMessagePO);
			outDto.setAppMsg("系统消息新增成功");
		} else {
			outDto.setAppMsg("系统消息新增失败，标题不能重复");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	/**
	 * 删除系统消息
	 * 
	 * @param httpModel
	 */
	public void deleteByKey(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUserLevelPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcMessageDao.deleteByKey", qDto);
		if(paramDtos.size()>0){
			httpModel.setOutMsg("系统消息删除成功");
		}else{
			httpModel.setOutMsg("系统消息删除失败");
		}
		
		
	}
}
