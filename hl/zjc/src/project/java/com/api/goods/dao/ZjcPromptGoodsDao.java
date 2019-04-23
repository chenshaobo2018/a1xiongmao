package com.api.goods.dao;

import java.util.List;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.api.goods.dao.po.ZjcPromptGoodsPO;

/**
 * <b>zjc_prompt_goods[zjc_prompt_goods]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2018-02-07 09:31:29
 */
@Dao("zjcPromptGoodsDao")
public interface ZjcPromptGoodsDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_prompt_goodsPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcPromptGoodsPO zjcPromptGoodsPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_prompt_goodsPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcPromptGoodsPO zjcPromptGoodsPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_prompt_goodsPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcPromptGoodsPO zjcPromptGoodsPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcPromptGoodsPO
	 */
	ZjcPromptGoodsPO selectByKey();

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcPromptGoodsPO
	 */
	ZjcPromptGoodsPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcPromptGoodsPO>
	 */
	List<ZjcPromptGoodsPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcPromptGoodsPO>
	 */
	List<ZjcPromptGoodsPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcPromptGoodsPO>
	 */
	List<ZjcPromptGoodsPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcPromptGoodsPO>
	 */
	List<ZjcPromptGoodsPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey();
	
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
	
}
