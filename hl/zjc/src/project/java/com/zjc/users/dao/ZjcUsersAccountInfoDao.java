package com.zjc.users.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * <b>zjc_users_account_info[zjc_users_account_info]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-05-22 14:04:13
 */
@Dao("zjcUsersAccountInfoDao")
public interface ZjcUsersAccountInfoDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_users_account_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcUsersAccountInfoPO zjcUsersAccountInfoPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_users_account_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcUsersAccountInfoPO zjcUsersAccountInfoPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_users_account_infoPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcUsersAccountInfoPO zjcUsersAccountInfoPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcUsersAccountInfoPO
	 */
	ZjcUsersAccountInfoPO selectByKey(@Param(value = "user_id") BigInteger user_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcUsersAccountInfoPO
	 */
	ZjcUsersAccountInfoPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcUsersAccountInfoPO>
	 */
	List<ZjcUsersAccountInfoPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcUsersAccountInfoPO>
	 */
	List<ZjcUsersAccountInfoPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcUsersAccountInfoPO>
	 */
	List<ZjcUsersAccountInfoPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcUsersAccountInfoPO>
	 */
	List<ZjcUsersAccountInfoPO> likePage(Dto pDto);

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
	/*
	 * 查找用户的钱包上限额度
	 */
	int wallet_quota(@Param(value = "user_id") BigInteger user_id);
	
	/*
	 * 提取签到奖励
	 */
	int addPayPoint(Dto dto);
	/**
	 * 查找一级上级
	 * @param Dto
	 * @return
	 */
	ZjcUsersAccountInfoPO getFirstLeaderAccount(ZjcUsersInfoPO zuserinfo);
	
	/**
	 * 查找二级上级
	 * @param Dto
	 * @return
	 */
	ZjcUsersAccountInfoPO getSecondLeaderAccount(ZjcUsersInfoPO zuserinfo);
}
