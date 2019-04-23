package com.zjc.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.article.dao.po.ZjcArticlePO;
import com.zjc.users.dao.po.ZjcArticleVO;

/**
 * <b>zjc_article[zjc_article]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-07-07 11:48:02
 */
@Dao("zjcArticleDao")
public interface ZjcArticleDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_articlePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcArticlePO zjcArticlePO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_articlePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcArticlePO zjcArticlePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_articlePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcArticlePO zjcArticlePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcArticlePO
	 */
	ZjcArticlePO selectByKey(@Param(value = "article_id") Integer article_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcArticlePO
	 */
	ZjcArticlePO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcArticlePO>
	 */
	List<ZjcArticlePO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcArticlePO>
	 */
	List<ZjcArticlePO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcArticlePO>
	 */
	List<ZjcArticlePO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcArticlePO>
	 */
	List<ZjcArticlePO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "article_id") Integer article_id);
	
	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	int rows(Dto pDto);
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	String calc(Dto pDto);
	
	/**
	 * 根据条件查询合适的数据，返回分页模型
	 * @param pDto
	 * @return
	 */
	List<Dto> listLikePage(Dto pDto);
	
	/**
	 * 更新文章
	 * @param pDto
	 * @return
	 */
	int updateArticle(Dto pDto);
	
	/**
	 * 插入新的文章
	 * @param pDto
	 * @return
	 */
	int insertArticle(Dto pDto);
	
	/**
	 * 获取全部新闻，返回分页模型
	 * @param pDto
	 * @return
	 */
	List<Dto> listNewsPage(Dto pDto);
	
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcArticlePO
	 */
	ZjcArticleVO selectByKey1(@Param(value = "article_id") Integer article_id);

}
