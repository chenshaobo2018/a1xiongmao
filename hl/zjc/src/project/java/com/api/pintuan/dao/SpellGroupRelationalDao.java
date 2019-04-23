package com.api.pintuan.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;
import com.api.pintuan.dao.po.SpellGroupRelationalPO;

/**
 * <b>spell_group_relational[spell_group_relational]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2018-03-23 15:20:38
 */
@Dao("spellGroupRelationalDao")
public interface SpellGroupRelationalDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param spell_group_relationalPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(SpellGroupRelationalPO spellGroupRelationalPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param spell_group_relationalPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(SpellGroupRelationalPO spellGroupRelationalPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param spell_group_relationalPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(SpellGroupRelationalPO spellGroupRelationalPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return SpellGroupRelationalPO
	 */
	SpellGroupRelationalPO selectByKey(@Param(value = "id") String id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return SpellGroupRelationalPO
	 */
	SpellGroupRelationalPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<SpellGroupRelationalPO>
	 */
	List<SpellGroupRelationalPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<SpellGroupRelationalPO>
	 */
	List<SpellGroupRelationalPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<SpellGroupRelationalPO>
	 */
	List<SpellGroupRelationalPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<SpellGroupRelationalPO>
	 */
	List<SpellGroupRelationalPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "id") String id);
	
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
