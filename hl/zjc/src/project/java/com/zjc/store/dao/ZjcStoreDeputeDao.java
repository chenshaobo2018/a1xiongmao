package com.zjc.store.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.store.dao.po.ZjcStoreDeputePO;

/**
 * <b>zjc_store_depute[zjc_store_depute]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-08-27 17:08:23
 */
@Dao("zjcStoreDeputeDao")
public interface ZjcStoreDeputeDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_store_deputePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcStoreDeputePO zjcStoreDeputePO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_store_deputePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcStoreDeputePO zjcStoreDeputePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_store_deputePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcStoreDeputePO zjcStoreDeputePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcStoreDeputePO
	 */
	ZjcStoreDeputePO selectByKey(@Param(value = "user_id") BigInteger user_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcStoreDeputePO
	 */
	ZjcStoreDeputePO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcStoreDeputePO>
	 */
	List<ZjcStoreDeputePO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcStoreDeputePO>
	 */
	List<ZjcStoreDeputePO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcStoreDeputePO>
	 */
	List<ZjcStoreDeputePO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcStoreDeputePO>
	 */
	List<ZjcStoreDeputePO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "user_id") BigInteger user_id);
	
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
	 * 根据条件查询列表
	 * @param dto
	 * @return
	 */
	List<ZjcStoreDeputePO> listSerachPage(Dto dto);
	
	/**
	 * 滆湖user_id查找数据
	 * @param dto
	 * @return
	 */
	ZjcStoreDeputePO deputeDetial(Dto dto);
	
	/**
	 * 滆湖user_id查找数据
	 * @param dto
	 * @return
	 */
	int updateDepute(ZjcStoreDeputePO in);
}
