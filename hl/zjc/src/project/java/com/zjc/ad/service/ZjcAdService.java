/**
 * 
 */
package com.zjc.ad.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.ad.dao.ZjcAdDao;
import com.zjc.ad.dao.ZjcAdPositionDao;
import com.zjc.ad.dao.po.ZjcAdPO;
import com.zjc.ad.dao.po.ZjcAdPositionPO;

/**
 * @author Administrator
 *
 */
@Service(value="zjcAdService")
public class ZjcAdService {

	@Autowired
	private ZjcAdDao zjcAdDao;
	@Autowired
	private ZjcAdPositionDao zjcAdPositionDao;
	@Autowired
	private IdService idService;
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * 初始化广告列表页面
	 * 
	 * @param httpModel
	 */
	public void initAdPage(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getUserModel().getJuid());
		httpModel.setViewPath("project/zjc/ad/adlist.jsp");
	}
	
	/**
	 * 初始化广告位置页面
	 * 
	 * @param httpModel
	 */
	public void initAdPositionPage(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/ad/adpositionlist.jsp");
	}
	
	
	/**
	 * 广告列表查询
	 * @param httpModel
	 */
	public void listAd(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		qDto.setOrder("orderby");
		List<Dto> adDtos = sqlDao.list("com.zjc.ad.dao.ZjcAdDao.listAdPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(adDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 
	 * 添加广告
	 * @param httpModel
	 */
	public void saveAd(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (zjcAdDao.rows(Dtos.newDto("ad_name", inDto.getString("ad_name"))) > 0) {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg(AOSUtils.merge("操作失败，广告名称[{0}]已经存在。", inDto.getString("ad_name")));
		}else {
			ZjcAdPO zjcAdPO = new ZjcAdPO();
			zjcAdPO.copyProperties(inDto);
			zjcAdPO.setAd_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcAdDao.insert(zjcAdPO);
//			cacheMasterDataService.cacheParamOption(aos_paramsPO.getParams_key(), aos_paramsPO.getValue());
			outDto.setAppMsg("广告新增成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改广告
	 * @param httpModel
	 */
	public void updateAd(HttpModel httpModel) {
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		try{
			ZjcAdPO adOldPO = zjcAdDao.selectByKey(inDto.getInteger("ad_id"));
			if (!StringUtils.equals(adOldPO.getAd_name(), inDto.getString("ad_name"))) {
				if (zjcAdDao.rows(Dtos.newDto("ad_name", inDto.getString("ad_name"))) > 0) {
					outDto.setAppCode(AOSCons.ERROR);
					outDto.setAppMsg(AOSUtils.merge("操作失败，广告[{0}]已经存在。", inDto.getString("ad_name")));
				}
			}
			if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
				ZjcAdPO adPO = new ZjcAdPO();
				adPO.copyProperties(inDto);
				zjcAdDao.updateByKey(adPO);
				outDto.setAppMsg("广告修改成功");
			}
		} catch(Exception e){
			outDto.setAppMsg("广告修改失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除广告
	 * @param httpModel
	 */
	@Transactional
	public void deleteAd(HttpModel httpModel) {
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String ad_id : selectionIds) {
			//ZjcAdPO adPO = zjcAdDao.selectByKey(Integer.valueOf(ad_id));
			zjcAdDao.deleteByKey(Integer.valueOf(ad_id));
//			cacheMasterDataService.delParamOption(aos_paramsPO.getParams_key());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条广告。", rows));
	}
	
	/**
	 * 排序上下移动
	 * @param httpModel
	 */
	public void upOrDownAd(HttpModel httpModel) {
		String ad_id = httpModel.getInDto().getString("ad_id").replace(",", "");
		String upOrDown = httpModel.getInDto().getString("upOrDown");
		try{
			ZjcAdPO adPO = zjcAdDao.selectByKey(Integer.valueOf(ad_id));
			Dto qdto = Dtos.newDto();
			if(!upOrDown.isEmpty()){
				if(Integer.valueOf(upOrDown) == 1){
					//上移操作
					qdto.put("orderby", adPO.getOrderby()-1);
					adPO.setOrderby(adPO.getOrderby()-1);
					ZjcAdPO otherOne = zjcAdDao.selectOne(qdto);
					if(AOSUtils.isNotEmpty(otherOne)){
						otherOne.setOrderby(otherOne.getOrderby()+1);
						zjcAdDao.updateByKey(otherOne);
					}
					zjcAdDao.updateByKey(adPO);
				}else{
					//下移操作
					qdto.put("orderby", adPO.getOrderby()+1);
					adPO.setOrderby(adPO.getOrderby()+1);
					ZjcAdPO otherOne = zjcAdDao.selectOne(qdto);
					if(AOSUtils.isNotEmpty(otherOne)){
						otherOne.setOrderby(otherOne.getOrderby()-1);
						zjcAdDao.updateByKey(otherOne);
					}
					zjcAdDao.updateByKey(adPO);
				}
				httpModel.setOutMsg(AOSUtils.merge("移动排序成功", adPO));
			}
		} catch (Exception e){
			e.printStackTrace();
			httpModel.setOutMsg("移动排序失败");
		}
	}
	
	/**
	 * 广告位置列表查询
	 * @param httpModel
	 */
	public void listAdposition(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> adpositionDtos = sqlDao.list("com.zjc.ad.dao.ZjcAdPositionDao.likePage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(adpositionDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 
	 * 添加广告位置
	 * @param httpModel
	 */
	public void saveAdposition(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (zjcAdPositionDao.rows(Dtos.newDto("position_name", inDto.getString("position_name"))) > 0) {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg(AOSUtils.merge("操作失败，广告位置[{0}]已经存在。", inDto.getString("position_name")));
		}else {
			ZjcAdPositionPO adPositionPO = new ZjcAdPositionPO();
			adPositionPO.copyProperties(inDto);
			adPositionPO.setPosition_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcAdPositionDao.insert(adPositionPO);
			outDto.setAppMsg("广告位置新增成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改广告位置
	 * @param httpModel
	 */
	public void updateAdposition(HttpModel httpModel) {
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcAdPositionPO adPositionOldPO = zjcAdPositionDao.selectByKey(inDto.getInteger("position_id"));
		if (!StringUtils.equals(adPositionOldPO.getPosition_name(), inDto.getString("position_name"))) {
			if (zjcAdPositionDao.rows(Dtos.newDto("position_name", inDto.getString("position_name"))) > 0) {
				outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg(AOSUtils.merge("操作失败，广告位置[{0}]已经存在。", inDto.getString("position_name")));
			}
		}
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcAdPositionPO adPositionPO = new ZjcAdPositionPO();
			adPositionPO.copyProperties(inDto);
			zjcAdPositionDao.updateByKey(adPositionPO);
			outDto.setAppMsg("广告位置修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除广告位置
	 * @param httpModel
	 */
	@Transactional
	public void deleteAdposition(HttpModel httpModel) {
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String position_id : selectionIds) {
			//ZjcAdPositionPO adPositionPO = zjcAdPositionDao.selectByKey(Integer.valueOf(position_id));
			zjcAdPositionDao.deleteByKey(Integer.valueOf(position_id));
//			cacheMasterDataService.delParamOption(aos_paramsPO.getParams_key());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条广告位置。", rows));
	}
	
	public void listPositionComboBoxData(HttpModel httpModel){
		List<Dto> list = sqlDao.list("com.zjc.ad.dao.ZjcAdPositionDao.listPositionComboBoxData", null);
		httpModel.setOutMsg(AOSJson.toJson(list));
	}
}
