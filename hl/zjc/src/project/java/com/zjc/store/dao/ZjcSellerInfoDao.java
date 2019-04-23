package com.zjc.store.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.store.dao.po.ZjcSellerInfoPO;

/**
 * <b>zjc_seller_info[zjc_seller_info]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 17:56:19
 */
@Dao("zjcSellerInfoDao")
public interface ZjcSellerInfoDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_seller_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcSellerInfoPO zjcSellerInfoPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_seller_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcSellerInfoPO zjcSellerInfoPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_seller_infoPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcSellerInfoPO zjcSellerInfoPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcSellerInfoPO
	 */
	ZjcSellerInfoPO selectByKey(@Param(value = "user_id") BigInteger user_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcSellerInfoPO
	 */
	ZjcSellerInfoPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcSellerInfoPO>
	 */
	List<ZjcSellerInfoPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcSellerInfoPO>
	 */
	List<ZjcSellerInfoPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcSellerInfoPO>
	 */
	List<ZjcSellerInfoPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcSellerInfoPO>
	 */
	List<ZjcSellerInfoPO> likePage(Dto pDto);

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
	 * 根据user_id查询并返回数据持久化对象
	 * 
	 * @return ZjcSellerInfoPO
	 */
	List<ZjcSellerInfoPO> selectByUserId(Dto inDto);
	
	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcSellerInfoPO
	 */
	ZjcSellerInfoPO selectByMobile(Dto pDto);
}
