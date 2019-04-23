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

import com.zjc.goods.dao.ZjcSpecDao;
import com.zjc.goods.dao.ZjcSpecItemDao;
import com.zjc.goods.dao.po.ZjcSpecItemPO;
import com.zjc.goods.dao.po.ZjcSpecPO;

/**
 * 商品规格
 * 
 * @author Administrator
 *
 */
@Service(value="zjcSpecService")
public class ZjcSpecService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcSpecDao zjcSpecDao;
	
	@Autowired
	private ZjcSpecItemDao zjcSpecItemDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化商品规格页面
	 * @param httpModel
	 */
	public void initSpec(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/goodsManaer/zjcSpec.jsp");
	}
	
	/**
	 * 商品规格查询
	 * @param httpModel
	 */
	public void listSpec(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> specDtos = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.queryForListPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(specDtos, qDto.getPageTotal()));
	}
	
	
	/**
	 * 规格项
	 * @param httpModel
	 */
	public void selectItemByTypeID(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> specDtos = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.selectItemByTypeID", qDto);
		httpModel.setOutMsg(AOSJson.toJson(specDtos));
	}
	
	/**
	 * 新增规格
	 * @param httpModel
	 */
	public void saveSpec(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcSpecPO zjcSpecPO = new ZjcSpecPO();
		zjcSpecPO.copyProperties(inDto);
		zjcSpecPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		zjcSpecDao.insert(zjcSpecPO);
		String specItems = inDto.getString("specItems");
		String [] specItemArr= specItems.split(",");
		Integer spec_id = zjcSpecPO.getId();
		for(String specItem : specItemArr){
			ZjcSpecItemPO zjcSpecItemPO = new ZjcSpecItemPO();
			zjcSpecItemPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcSpecItemPO.setSpec_id(spec_id);
			zjcSpecItemPO.setItem(specItem);
			zjcSpecItemDao.insert(zjcSpecItemPO);
		}
		outDto.setAppMsg("商品规格新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改规格
	 * @param httpModel
	 */
	public void updataSpec(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcSpecPO zjcSpecPO = new ZjcSpecPO();
		String specItems = inDto.getString("specItems");
		zjcSpecPO.copyProperties(inDto);
		zjcSpecDao.updateByKey(zjcSpecPO);
		//删除原有规格项
		List<ZjcSpecItemPO> list = sqlDao.list("com.zjc.goods.dao.ZjcSpecItemDao.selectBySpecID", zjcSpecPO.getId());
		for(ZjcSpecItemPO zjcSpecItemPO : list){
			zjcSpecItemDao.deleteByKey(zjcSpecItemPO.getId());
		}
		//新增修改后的规则项
		String [] specItemArr= specItems.split(",");
		Integer spec_id = zjcSpecPO.getId();
		for(String specItem : specItemArr){
			ZjcSpecItemPO zjcSpecItemPO = new ZjcSpecItemPO();
			zjcSpecItemPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcSpecItemPO.setSpec_id(spec_id);
			zjcSpecItemPO.setItem(specItem);
			zjcSpecItemDao.insert(zjcSpecItemPO);
		}
		outDto.setAppMsg("商品规格修改成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除商品规格
	 * @param httpModel
	 */
	public void deleteSpec(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String id : selectionIds) {
			ZjcSpecPO zjcSpecPO = zjcSpecDao.selectByKey(Integer.valueOf(id));
			//删除原有规格项
			List<ZjcSpecItemPO> list = sqlDao.list("com.zjc.goods.dao.ZjcSpecItemDao.selectBySpecID", zjcSpecPO.getId());
			for(ZjcSpecItemPO zjcSpecItemPO : list){
				zjcSpecItemDao.deleteByKey(zjcSpecItemPO.getId());
			}
			zjcSpecDao.deleteByKey(Integer.valueOf(id));
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条商品规格。", rows));
	}
}
