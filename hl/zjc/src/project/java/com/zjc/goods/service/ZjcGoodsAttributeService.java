/**
 * 
 */
package com.zjc.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.goods.dao.ZjcGoodsAttributeDao;
import com.zjc.goods.dao.po.ZjcGoodsAttributePO;

/**
 * 商品属性管理
 * 
 * @author Administrator
 *
 */
@Service(value="zjcGoodsAttributeService")
public class ZjcGoodsAttributeService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcGoodsAttributeDao zjcGoodsAttributeDao;
	
	/**
	 * 初始化商品规格
	 * @param httpModel
	 */
	public void initGoodsAttribute(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/goodsManaer/goodsAttribute.jsp");
	}
	
	/**
	 * 查询商品属性
	 * @param httpModel
	 */
	public void listGoodsAttribute(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> attributeDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsAttributeDao.listAndTypePage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(attributeDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 新增商品属性
	 * @param httpModel
	 */
	public void saveGoodsAttribute(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcGoodsAttributePO zjcGoodsAttributePO = new ZjcGoodsAttributePO();
		zjcGoodsAttributePO.copyProperties(inDto);
		zjcGoodsAttributePO.setAttr_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		zjcGoodsAttributeDao.insert(zjcGoodsAttributePO);
		//cacheMasterDataService.cacheParamOption(aos_paramsPO.getParams_key(), aos_paramsPO.getValue());
		outDto.setAppMsg("商品属性新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改商品属性
	 * @param httpModel
	 */
	public void upDateGoodsAttribute(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//ZjcGoodsAttributePO zjcGoodsAttributeOldPO = zjcGoodsAttributeDao.selectByKey(inDto.getInteger("attr_id"));
		ZjcGoodsAttributePO zjcGoodsAttributePO = new ZjcGoodsAttributePO();
		zjcGoodsAttributePO.copyProperties(inDto);
		zjcGoodsAttributeDao.updateByKey(zjcGoodsAttributePO);
		outDto.setAppMsg("商品属性修改成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除商品属性
	 * @param httpModel
	 */
	public void deleteGoodsAttribute(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String attr_id : selectionIds) {
			//ZjcGoodsAttributePO zjcGoodsAttributePO = zjcGoodsAttributeDao.selectByKey(Integer.valueOf(attr_id));
			zjcGoodsAttributeDao.deleteByKey(Integer.valueOf(attr_id));
			//cacheMasterDataService.delParamOption(aos_paramsPO.getParams_key());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条商品属性。", rows));
	}
	
	
	/**
	 * 不分页列表查询。
	 * @param httpModel
	 */
	public void  getGoodsAttributeList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> attributeDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsAttributeDao.list", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(attributeDtos, qDto.getPageTotal()));
	}
}
