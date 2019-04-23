package com.zjc.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.article.dao.po.ZjcArticleCatPO;

/**
 * <b>zjc_article_cat[zjc_article_cat]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-07-07 11:48:02
 */
@Dao("zjcArticleCatDao")
public interface ZjcArticleCatDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_article_catPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcArticleCatPO zjcArticleCatPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_article_catPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcArticleCatPO zjcArticleCatPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_article_catPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcArticleCatPO zjcArticleCatPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcArticleCatPO
	 */
	ZjcArticleCatPO selectByKey(@Param(value = "cat_id") Integer cat_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcArticleCatPO
	 */
	ZjcArticleCatPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcArticleCatPO>
	 */
	List<ZjcArticleCatPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcArticleCatPO>
	 */
	List<ZjcArticleCatPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcArticleCatPO>
	 */
	List<ZjcArticleCatPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcArticleCatPO>
	 */
	List<ZjcArticleCatPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "cat_id") Integer cat_id);
	
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
	 * 根据不同的条件查询对应的数据
	 * @param pDto
	 * @return
	 */
	List<ZjcArticleCatPO> listLikePage(Dto pDto);
	
	/**
	 * 更新分类
	 * @param pDto
	 * @return
	 */
	int updateArticleCat(Dto pDto);
	
	/**
	 * 删除分类，提示删除下面的所有文章
	 * @param cat_id
	 * @return
	 */
	int delete(@Param(value = "cat_id") Integer cat_id);
	
	/**
	 * 获取全部分类名称
	 * @return
	 */
	List<ZjcArticleCatPO> getTypes();
	
	/**
	 * 添加分类
	 * @param pDto
	 * @return
	 */
	int insertCat(Dto pDto);
}
