/**
 * 
 */
package com.zjc.goods.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.goods.dao.ZjcGoodsTypeDao;
import com.zjc.goods.dao.ZjcSpecGoodsPriceDao;
import com.zjc.goods.dao.po.ZjcGoodsTypePO;
import com.zjc.goods.dao.po.ZjcSpecItemPO;
import com.zjc.goods.dao.po.ZjcSpecPO;
import com.zjc.goods.dao.po.ZjcSpecVO;

/**
 * 商品类型
 * @author Administrator
 *
 */
@Service(value="zjcGoodsTypeService")
public class ZjcGoodsTypeService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcGoodsTypeDao zjcGoodsTypeDao;
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcSpecGoodsPriceDao zjcSpecGoodsPriceDao;
	/**
	 * 初始发商品类型页面
	 * @param httpModel
	 */
	public void initGoodsType(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/goodsManaer/goodsType.jsp");
	}
	
	/**
	 * 商品类型查询
	 * @param httpModel
	 */
	public void listGoodsType(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> typeDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsTypeDao.listPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(typeDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 添加商品类型
	 * @param httpModel
	 */
	public void saveGoodsType(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (zjcGoodsTypeDao.rows(Dtos.newDto("name", inDto.getString("name"))) > 0) {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg(AOSUtils.merge("操作失败，该商品类型[{0}]已经存在。", inDto.getString("name")));
		}else {
			ZjcGoodsTypePO zjcGoodsTypePO = new ZjcGoodsTypePO();
			zjcGoodsTypePO.copyProperties(inDto);
			zjcGoodsTypePO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcGoodsTypeDao.insert(zjcGoodsTypePO);
//			cacheMasterDataService.cacheParamOption(aos_paramsPO.getParams_key(), aos_paramsPO.getValue());
			outDto.setAppMsg("商品类型新增成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改商品类型
	 * @param httpModel
	 */
	public void upDateGoodsType(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcGoodsTypePO zjcGoodsTypeOldPO = zjcGoodsTypeDao.selectByKey(inDto.getInteger("id"));
		if (!StringUtils.equals(zjcGoodsTypeOldPO.getName(), inDto.getString("name"))) {
			if (zjcGoodsTypeDao.rows(Dtos.newDto("name", inDto.getString("name"))) > 0) {
				outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg(AOSUtils.merge("操作失败，商品类型[{0}]已经存在。", inDto.getString("name")));
			}
		}
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcGoodsTypePO zjcGoodsTypePO = new ZjcGoodsTypePO();
			zjcGoodsTypePO.copyProperties(inDto);
			zjcGoodsTypeDao.updateByKey(zjcGoodsTypePO);
			outDto.setAppMsg("商品类型修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除商品类型
	 * @param httpModel
	 */
	public void deleteGoodsType(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String id : selectionIds) {
			//ZjcGoodsTypePO zjcGoodsTypePO = zjcGoodsTypeDao.selectByKey(Integer.valueOf(id));
			zjcGoodsTypeDao.deleteByKey(Integer.valueOf(id));
//			cacheMasterDataService.delParamOption(aos_paramsPO.getParams_key());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条商品类型。", rows));
	}
	
	
	public void listTypeComboBoxData(HttpModel httpModel){
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsTypeDao.listTypeComboBoxData", httpModel.getInDto());
		httpModel.setOutMsg(AOSJson.toJson(list));
	}
	
	/**
	 * 查询对应商品商品类型名称
	 * 
	 * @param httpModel
	 */
	public void getGoodsType(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String specStr = zjcSpecGoodsPriceDao.getSpecStr(dto);
		String typeName = "";
		if(!AOSUtils.isEmpty(specStr)){
			specStr = specStr.replace("_", ",");
			//查询商品规格
			String sqlStr = " and t1.id in ("+ specStr + ")";
			dto.put("sqlStr", sqlStr);
			//查询该商品的规格数据
			List<ZjcSpecPO> specList = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.getSpecs", dto);
			if(!AOSUtils.isEmpty(specList)){
				int type_id = specList.get(0).getType_id();
				ZjcGoodsTypePO goodsType = zjcGoodsTypeDao.selectOne(Dtos.newDto("id", type_id));
				if(!AOSUtils.isEmpty(goodsType)){
					typeName =  goodsType.getName();
				}
			}
		}
		httpModel.setOutMsg(typeName);
	}
	
	/**
	 * 查询规格明细
	 * 
	 * @param httpModel
	 */
	public void getGoodsSpec(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		List<ZjcSpecVO> specvoList = new ArrayList<ZjcSpecVO>();
		String specStr = zjcSpecGoodsPriceDao.getSpecStr(dto);
		if(!AOSUtils.isEmpty(specStr)){
			specStr = specStr.replace("_", ",");
			String strSql = " and id in ("+ specStr + ")";
			dto.put("strSql", strSql);
			//查询该商品的符合条件的规格项数据
			List<ZjcSpecItemPO> itemList = sqlDao.list("com.zjc.goods.dao.ZjcSpecItemDao.getSpecItem", dto);
			//查询商品规格
			String sqlStr = " and t1.id in ("+ specStr + ")";
			dto.put("sqlStr", sqlStr);
			//查询该商品的规格数据
			List<ZjcSpecPO> specList = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.getSpecs", dto);
			if(!AOSUtils.isEmpty(itemList) || !AOSUtils.isEmpty(specList)){
				for(ZjcSpecPO specPO :specList){//规格项分组
					for(ZjcSpecItemPO specItemPO :itemList){
						ZjcSpecVO specvo = new ZjcSpecVO();
						if(specPO.getId()==specItemPO.getSpec_id()){//如果规格数据ZjcSpecPO的ID等于规格项数据ZjcSpecItemPO的spec_id
							specvo.setItem_name(specItemPO.getItem());//详细规格
							specvo.setType_name(specPO.getName());//规格项名称
							specvoList.add(specvo);
						}
					}
				}
			} 
		}
		httpModel.setOutMsg(AOSJson.toJson(specvoList));
	}
}
