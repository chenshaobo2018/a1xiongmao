package com.zjc.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.system.dao.po.ZjcMemberParameterPO;

/**
 * <b>zjc_member_parameter[zjc_member_parameter]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-06-13 11:27:40
 */
@Dao("zjcMemberParameterDao")
public interface ZjcMemberParameterDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_member_parameterPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcMemberParameterPO zjcMemberParameterPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_member_parameterPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcMemberParameterPO zjcMemberParameterPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_member_parameterPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcMemberParameterPO zjcMemberParameterPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcMemberParameterPO
	 */
	ZjcMemberParameterPO selectByKey(@Param(value = "id") Integer id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcMemberParameterPO
	 */
	ZjcMemberParameterPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcMemberParameterPO>
	 */
	List<ZjcMemberParameterPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcMemberParameterPO>
	 */
	List<ZjcMemberParameterPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcMemberParameterPO>
	 */
	List<ZjcMemberParameterPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcMemberParameterPO>
	 */
	List<ZjcMemberParameterPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "id") Integer id);
	
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
	 * 查找当前的会员设置
	 * @return
	 */
	ZjcMemberParameterPO selectByMaxKey();
}
