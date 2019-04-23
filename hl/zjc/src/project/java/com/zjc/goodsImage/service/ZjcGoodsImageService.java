/**
 * 
 */
package com.zjc.goodsImage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.google.common.collect.Lists;
import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;

/**
 * 商品图片管理
 * 
 * @author zhangchao
 *
 */
@Service(value="zjcGoodsImageService")
public class ZjcGoodsImageService {
	
	@Autowired
	private SqlDao sqlDao;
	
	
	/**
	 * 查询商品图片列表，并生成pic html代码
	 * @param httpModel
	 */
	public void getGoodsImageList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcGoodsImagesPO> goodsImages = sqlDao.list("com.zjc.goodsImage.dao.ZjcGoodsImagesDao.list", qDto);
		List<Dto> newList = Lists.newArrayList();
		Dto dto = Dtos.newDto();
		for(ZjcGoodsImagesPO image :goodsImages){
			dto = image.toDto();
			String htmlString = "";
			htmlString = "<img src=\"" + image.getImage_url() + "\" />";
			dto.put("html", htmlString);
			newList.add(dto);
		}
		httpModel.setOutMsg(AOSJson.toGridJson(newList));
	}
}
