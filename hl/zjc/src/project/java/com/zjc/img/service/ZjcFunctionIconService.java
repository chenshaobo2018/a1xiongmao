/**
 * 
 */
package com.zjc.img.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.img.dao.ZjcFunctionIconDao;
import com.zjc.img.dao.po.ZjcFunctionIconPO;

/**
 * @author Administrator
 *
 */
@Service(value="ZjcFunctionIconService")
public class ZjcFunctionIconService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcFunctionIconDao ZjcFunctionIconDao;
	
	
	/**
	 * 图片管理页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
		    httpModel.setAttribute("juid", httpModel.getUserModel().getJuid());
			httpModel.setViewPath("project/zjc/icon/zjcFunctionIcon.jsp");
		}
	
	/**
	 * 图片管理列表查询
	 * @param httpModel
	 */
	public void listAd(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcFunctionIconPO> paramDtos = sqlDao.list("com.zjc.img.dao.ZjcFunctionIconDao.listPage", qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	/**
	 * 添加图片信息
	 * @param httpModel
	 */
	public void addIcon(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcFunctionIconPO  icon=new ZjcFunctionIconPO();
		icon.copyProperties(inDto);
		icon.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcFunctionIconDao.insert(icon);
		outDto.setAppMsg("添加成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	/**
	 * 修改图片查询消息
	 * @param httpModel
	 */
	public void queryIcon(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcFunctionIconPO> paramDtos = sqlDao.list("com.zjc.img.dao.ZjcFunctionIconDao.selectByKey", qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 修改图片消息
	 * @param httpModel
	 */
	public void updateIcon(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		Dto inDto = httpModel.getInDto();
		ZjcFunctionIconPO  icon=new ZjcFunctionIconPO();
		icon.copyProperties(inDto);
		ZjcFunctionIconDao.updateByKey(icon);
		qDto.setAppMsg("修改成功");
		httpModel.setOutMsg(AOSJson.toJson(qDto));
	}
	
	
	/**
	 * 删除图片消息
	 * @param httpModel
	 */
	@Transactional
	public void deleteIcon(HttpModel httpModel) {
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String id : selectionIds) {
			ZjcFunctionIconPO  icon = ZjcFunctionIconDao.selectByKey(Integer.valueOf(id));
			ZjcFunctionIconDao.deleteByKey(icon.getId());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条广告。", rows));
	}

}
