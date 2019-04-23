/**
 * 
 */
package com.zjc.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.zjc.article.dao.ZjcArticleCatDao;
import com.zjc.article.dao.po.ZjcArticleCatPO;

/**
 * @author pubing
 *
 */
@Service(value="zjcArticleCatService")
public class ZjcArticleCatService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcArticleCatDao zjcArticleCatDao;
	
	/**
	 * 跳转到初始页面
	 * @param httpModel
	 */
	public void initZjcArticleCat(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/article/articleCatList.jsp");
	}
	/**
	 * 初始化页面数据
	 * @param httpModel
	 */
	public void listLikeZjcArticleCat(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		List<ZjcArticleCatPO> articleCatDtos=zjcArticleCatDao.listLikePage(pDto);
		httpModel.setOutMsg(AOSJson.toGridJson(articleCatDtos, pDto.getPageTotal()));
	}
	
	/**
	 * 根据ID查找分类
	 * @param httpModel
	 */
	public void selectByCatId(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		ZjcArticleCatPO articleCatDtos=zjcArticleCatDao.selectByKey(pDto.getInteger("cat_id"));
		httpModel.setOutMsg(AOSJson.toJson(articleCatDtos));
	}
	
	/**
	 * 根据ID更新分类
	 * @param httpModel
	 */
	public void updateArticleCat(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		Dto outDto = Dtos.newOutDto();
		zjcArticleCatDao.updateArticleCat(pDto);
		outDto.setAppMsg("修改成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 根据ID删除分类和对应的文章
	 * @param httpModel
	 */
	public void deleteByKey(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		int i = zjcArticleCatDao.delete(pDto.getInteger("cat_id"));
		Dto outDto = Dtos.newOutDto();
		if(i>=1){
			outDto.setAppMsg("删除成功");
		}else{
			outDto.setAppMsg("删除失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 插入新的分类
	 * @param httpModel
	 */
	public void insertArticleCat(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		int i=zjcArticleCatDao.insertCat(pDto);
		Dto outDto = Dtos.newOutDto();
		if(i>=1){
			outDto.setAppMsg("添加成功");
		}else{
			outDto.setAppMsg("添加失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}

