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
import aos.system.common.utils.SystemUtils;

import com.google.common.collect.Lists;
import com.zjc.goods.dao.ZjcGoodsCategoryDao;
import com.zjc.goods.dao.po.ZjcGoodsCategoryPO;

/**
 * 
 * 商品管理
 * 
 * @author Administrator
 *
 */
@Service(value="zjcGoodsCategoryService")
public class ZjcGoodsCategoryService {
	
	@Autowired
	private ZjcGoodsCategoryDao zjcGoodsCategoryDao;
	@Autowired
	private IdService idService;
	@Autowired
	private SqlDao sqlDao;
	
	public void initGoodsCategory(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/goodsManaer/goodsCateory.jsp");
	}
	
	/**
	 * 商品分类树
	 * @param httpModel
	 */
	public void TreeGoodsCategoty(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		inDto.setOrder("sort_order");
		List<ZjcGoodsCategoryPO> zjcGoodsCategoryPOs =  zjcGoodsCategoryDao.list(inDto);
		List<Dto> GoodsDtos = Lists.newArrayList();
		for (ZjcGoodsCategoryPO zjcGoodsCategoryPO : zjcGoodsCategoryPOs) {
			GoodsDtos.add(zjcGoodsCategoryPO.toDto());
		}
		String treeJson = SystemUtils.toTreeModalAsyncLoad(GoodsDtos);
		httpModel.setOutMsg(treeJson);
	}
	
	/**
	 * 商品分类列表
	 * @param httpModel
	 */
	public void listGoodsCategoty(HttpModel httpModel) {
		Dto qDto = httpModel.getInDto();
		List<Dto> GoodsDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.likeCategoryPoPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(GoodsDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 商品分类新增
	 * @param httpModel
	 */
	public void saveGoodsCategoty(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		ZjcGoodsCategoryPO zjcGoodsCategoryPO = new ZjcGoodsCategoryPO();
		zjcGoodsCategoryPO.copyProperties(inDto);
		zjcGoodsCategoryPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		Dto qDto = Dtos.newDto();
		qDto.put("name", zjcGoodsCategoryPO.getName());
		List<ZjcGoodsCategoryPO> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.list", qDto);
		if(list == null || list.size() == 0 ){
			// 生成语义ID
			ZjcGoodsCategoryPO parentGoodsCategoryPO = zjcGoodsCategoryDao.selectByKey(zjcGoodsCategoryPO.getParent_id());
			String max_parent_id_path = (String)sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsCategoryDao.getMaxPathId", zjcGoodsCategoryPO.getParent_id());
			if (AOSUtils.isEmpty(max_parent_id_path)) {
				String temp = "0";
				if (AOSUtils.isNotEmpty(parentGoodsCategoryPO)) {
					temp = parentGoodsCategoryPO.getParent_id_path();
				}
				max_parent_id_path = temp + "_000";
			}
			String parent_id_path = SystemUtils.genParentPathId(max_parent_id_path, 999);
			zjcGoodsCategoryPO.setParent_id_path(parent_id_path);
			zjcGoodsCategoryDao.insert(zjcGoodsCategoryPO);
			
			httpModel.setOutMsg("商品分类新增成功。");
		}else{
			httpModel.setOutMsg("商品分类新增失败,商品分类名称已经存在");
		}
	}
	
	/**
	 * 商品分类修改
	 * 
	 * @param httpModel
	 */
	public void updateGoodsCategoty(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		ZjcGoodsCategoryPO zjcGoodsCategoryPO = new ZjcGoodsCategoryPO();
		zjcGoodsCategoryPO.copyProperties(inDto);
		zjcGoodsCategoryDao.updateByKey(zjcGoodsCategoryPO);
		httpModel.setOutMsg("商品分类修改成功。");
	}
	
	/**
	 * 商品分类删除
	 * @param httpModel
	 */
	public void deleteGoodsCategoty(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		String[] selectionIds = httpModel.getInDto().getRows();
		//ZjcGoodsCategoryPO zjcGoodsCategoryPO = (ZjcGoodsCategoryPO)sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsCategoryDao.checkRootNode", Dtos.newDto("ids", StringUtils.join(selectionIds, ",")));
//		if (AOSUtils.isNotEmpty(zjcGoodsCategoryPO)) {
//			outDto.setAppCode(AOSCons.ERROR);
//			outDto.setAppMsg(AOSUtils.merge("操作失败，根节点[{0}]不能删除。", zjcGoodsCategoryPO.getName()));
//		}else {
			for (String id : selectionIds) {
				ZjcGoodsCategoryPO delPO = zjcGoodsCategoryDao.selectByKey(Integer.valueOf(id)); 
				if (AOSUtils.isEmpty(delPO)) {
					continue;
				}
				
				List<ZjcGoodsCategoryPO> subDelList = zjcGoodsCategoryDao.like(Dtos.newDto("parent_id_path", delPO.getParent_id_path()));
				for (ZjcGoodsCategoryPO subDelPO : subDelList) {
					zjcGoodsCategoryDao.deleteByKey(subDelPO.getId());
				}
				
			}
			outDto.setAppMsg("成功删除功能模块数据。");
//		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	/**
	 * 下拉
	 * @param httpModel
	 */
	public void listPositionComboBoxData(HttpModel httpModel){
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.listGoodsCategoryComboBoxData", null);
		httpModel.setOutMsg(AOSJson.toJson(list));
	}
	
	/**
	 * 带条件的下拉
	 * @param httpModel
	 */
	public void listPositionComboBoxDataByParam(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.listGoodsCategoryComboBoxDataByParam", qDto);
		httpModel.setOutMsg(AOSJson.toJson(list));
	}
}
