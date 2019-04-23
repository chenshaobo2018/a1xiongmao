/**
 * 
 */
package com.zjc.shareGoods.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSUtils;

import com.zjc.goods.dao.ZjcSpecGoodsPriceDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcSpecItemPO;
import com.zjc.goods.dao.po.ZjcSpecPO;
import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;

/**
 * @author Administrator
 *
 */
@Service(value="shareGoodsService")
public class ShareGoodsService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcSpecGoodsPriceDao zjcSpecGoodsPriceDao;
	
	public ZjcGoodsPO getGoodsDetailByGoodsId(Dto inDto) {
		
		//根据商品ID查询商品详情信息
		if(AOSUtils.isEmpty(inDto.getString("goods_id"))){
			return null;
		}
		ZjcGoodsPO goodDetails = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.GoodsDetailed", inDto);
		if(goodDetails != null){
			goodDetails.setZjcGoodsImagesPO(null);
			ZjcGoodsImagesPO imgPO = new ZjcGoodsImagesPO();
			imgPO.setImage_url(goodDetails.getOriginal_img());
			List<ZjcGoodsImagesPO> imgsList = new ArrayList<ZjcGoodsImagesPO>();
			imgsList.add(imgPO);
			goodDetails.setZjcGoodsImagesPO(imgsList);
			String aduit_info =  goodDetails.getAduit_info();
			if(!AOSUtils.isEmpty(aduit_info)){
				String[] aduit_infoArr = aduit_info.split(";");
				String str = aduit_infoArr[5];
				String aduit_user_name = str.substring(str.indexOf("\"")+1, str.indexOf("\"",str.indexOf("\"")+1 ));
				goodDetails.setAduit_user_name(aduit_user_name);
			}
			//通过goods_id查询规格项字符串
			String specStr = zjcSpecGoodsPriceDao.getSpecStr(inDto);
			if(!AOSUtils.isEmpty(specStr)){
				specStr = specStr.replace("_", ",");
				String strSql = " and id in ("+ specStr + ")";
				inDto.put("strSql", strSql);
				//查询该商品的符合条件的规格项数据
				List<ZjcSpecItemPO> itemList = sqlDao.list("com.zjc.goods.dao.ZjcSpecItemDao.getSpecItem", inDto);
				//查询商品规格
				String sqlStr = " and t1.id in ("+ specStr + ")";
				inDto.put("sqlStr", sqlStr);
				//查询该商品的规格数据
				List<ZjcSpecPO> specList = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.getSpecs", inDto);
				if(!AOSUtils.isEmpty(itemList) || !AOSUtils.isEmpty(specList)){
					for(ZjcSpecPO specPO :specList){//规格项分组
						List<ZjcSpecItemPO> newItemList = new ArrayList<ZjcSpecItemPO>();
						for(ZjcSpecItemPO specItemPO :itemList){
							if(specPO.getId()==specItemPO.getSpec_id()){//如果规格数据ZjcSpecPO的ID等于规格项数据ZjcSpecItemPO的spec_id
								newItemList.add(specItemPO);//追加到List,分组
							}
						}
						specPO.setZjcSpecItemPOList(newItemList);
					}
					goodDetails.setZjcSpecPOList(specList);
				} 
			}
		}
		
		return goodDetails;
	}
}
