package com.api.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.api.order.dao.po.ZjcPaymentSallerPO;

/**
 * <b>zjc_payment_saller[zjc_payment_saller]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-07-21 09:19:45
 */
@Dao("zjcPaymentSallerDao")
public interface ZjcPaymentSallerDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_payment_sallerPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcPaymentSallerPO zjcPaymentSallerPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_payment_sallerPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcPaymentSallerPO zjcPaymentSallerPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_payment_sallerPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcPaymentSallerPO zjcPaymentSallerPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcPaymentSallerPO
	 */
	ZjcPaymentSallerPO selectByKey(@Param(value = "id") Integer id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcPaymentSallerPO
	 */
	ZjcPaymentSallerPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcPaymentSallerPO>
	 */
	List<ZjcPaymentSallerPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcPaymentSallerPO>
	 */
	List<ZjcPaymentSallerPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcPaymentSallerPO>
	 */
	List<ZjcPaymentSallerPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcPaymentSallerPO>
	 */
	List<ZjcPaymentSallerPO> likePage(Dto pDto);

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
	
}
