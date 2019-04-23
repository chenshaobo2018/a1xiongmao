package com.api.cfca.dao;

import java.util.List;
import java.math.BigInteger;
import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;
import com.api.cfca.dao.po.ZjcZjBankcardPO;

/**
 * <b>zjc_zj_bankcard[zjc_zj_bankcard]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2018-05-16 09:37:29
 */
@Dao("zjcZjBankcardDao")
public interface ZjcZjBankcardDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_zj_bankcardPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcZjBankcardPO zjcZjBankcardPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_zj_bankcardPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcZjBankcardPO zjcZjBankcardPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_zj_bankcardPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcZjBankcardPO zjcZjBankcardPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcZjBankcardPO
	 */
	ZjcZjBankcardPO selectByKey(@Param(value = "user_id") BigInteger user_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcZjBankcardPO
	 */
	ZjcZjBankcardPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcZjBankcardPO>
	 */
	List<ZjcZjBankcardPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcZjBankcardPO>
	 */
	List<ZjcZjBankcardPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcZjBankcardPO>
	 */
	List<ZjcZjBankcardPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcZjBankcardPO>
	 */
	List<ZjcZjBankcardPO> likePage(Dto pDto);

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
	
}
