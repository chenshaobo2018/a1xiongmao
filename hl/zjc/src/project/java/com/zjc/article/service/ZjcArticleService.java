/**
 * 
 */
package com.zjc.article.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.demo.dao.DemoAccountDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.api.login.dao.ZjcLoginParameterDao;
import com.zjc.article.dao.ZjcArticleCatDao;
import com.zjc.article.dao.ZjcArticleDao;
import com.zjc.article.dao.po.TypeVo;
import com.zjc.article.dao.po.ZjcArticleCatPO;
import com.zjc.article.dao.po.ZjcArticlePO;

/**
 * @author pubing
 *
 */
@Service(value="zjcArticleService")
public class ZjcArticleService {
	@Autowired
	private ZjcArticleDao zjcArticleDao;
	@Autowired
	private ZjcArticleCatDao zjcArticleCatDao;
	@Autowired
	private ZjcLoginParameterDao zjcLoginParameterDao;
	
	@Autowired
	private DemoAccountDao demoAccountDao;
	
	/**
	 * 跳转到指定页面
	 * @param httpModel
	 */
	public void initZjcArticle(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/article/articleList.jsp");
	}
	
	/**
	 * 初始化页面数据
	 * @param httpModel
	 */
	public void listLikeZjcArticle(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		List<Dto> zjcArticleDtos=zjcArticleDao.listLikePage(pDto);
		httpModel.setOutMsg(AOSJson.toJson(zjcArticleDtos));
	}
	
	/**
	 * 根据ID查找文章
	 * @param httpModel
	 */
	public void selectByKey(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		ZjcArticlePO zjcArticleDtos=zjcArticleDao.selectByKey(pDto.getInteger("article_id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcArticleDtos));
	}
	
	/**
	 * 根据ID删除文章
	 * @param httpModel
	 */
	public void deleteByKey(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		Dto outDto = Dtos.newOutDto();
		try {
			zjcArticleDao.deleteByKey(pDto.getInteger("article_id"));
			outDto.setAppMsg("删除成功");
		}catch(Exception e){
			outDto.setAppMsg("删除失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 根据ID更新文章
	 * @param httpModel
	 */
	public void updateArticle(HttpModel httpModel){
		Dto pDto=httpModel.getInDto();
		Dto outDto = Dtos.newOutDto();
		ZjcArticlePO article = zjcArticleDao.selectByKey(pDto.getInteger("article_id"));
		article.copyProperties(pDto);
		try{
			zjcArticleDao.updateByKey(article);
			outDto.setAppMsg("修改成功");
		} catch(Exception e) {
			outDto.setAppMsg("修改失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 获取所有的文章分类
	 * @param httpModel
	 */
	public void getTypes(HttpModel httpModel){
		List<ZjcArticleCatPO> zjcArticleCatDtos=zjcArticleCatDao.getTypes();
		List<TypeVo> types=new ArrayList<>();
		for(ZjcArticleCatPO cat : zjcArticleCatDtos){
			types.add(new TypeVo(cat.getCat_id(),cat.getCat_name()));
		}
		httpModel.setOutMsg(AOSJson.toJson(types));
	}
	
	/**
	 * 插入新的文章
	 * @param httpModel
	 */
	public void insertArticle(HttpModel httpModel) {
		Dto pDto=httpModel.getInDto();
		Dto outDto = Dtos.newOutDto();
		ZjcArticlePO article = new ZjcArticlePO();
		article.copyProperties(pDto);
		article.setAdd_time(new Date());
		try{
			zjcArticleDao.insert(article);
			outDto.setAppMsg("添加成功");
		} catch(Exception e){
			e.printStackTrace();
			outDto.setAppMsg("添加失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
