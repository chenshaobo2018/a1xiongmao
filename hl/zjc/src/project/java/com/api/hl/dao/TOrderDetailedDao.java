package com.api.hl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.api.hl.dao.po.TOrderDetailedPO;
import com.api.hl.vo.T_order_detailedVO;
import com.api.hl.vo.T_orderqgtjVO;

/**
 * <b>t_order_detailed[t_order_detailed]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:10
 */
@Dao("tOrderDetailedDao")
public interface TOrderDetailedDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param t_order_detailedPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(TOrderDetailedPO tOrderDetailedPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param t_order_detailedPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(TOrderDetailedPO tOrderDetailedPO);

	List<T_orderqgtjVO> qgtjlikePage(Dto pDto);
	
	List<T_orderqgtjVO> qgtjlistVO(Dto pDto);
	
	T_orderqgtjVO qgtjcount(Dto pDto);
	List<T_order_detailedVO>qgorder_detailedByOrderId(@Param(value = "order_id") String order_id);
	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param t_order_detailedPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(TOrderDetailedPO tOrderDetailedPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return TOrderDetailedPO
	 */
	TOrderDetailedPO selectByKey(@Param(value = "id") String id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return TOrderDetailedPO
	 */
	TOrderDetailedPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<TOrderDetailedPO>
	 */
	List<TOrderDetailedPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<TOrderDetailedPO>
	 */
	List<TOrderDetailedPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<TOrderDetailedPO>
	 */
	List<TOrderDetailedPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<TOrderDetailedPO>
	 */
	List<TOrderDetailedPO> likePage(Dto pDto);

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
