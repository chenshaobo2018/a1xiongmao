package com.zjc.users.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * <b>zjc_users_info[zjc_users_info]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-06-15 09:22:16
 */
@Dao("zjcUsersInfoDao")
public interface ZjcUsersInfoDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_users_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcUsersInfoPO zjcUsersInfoPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_users_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcUsersInfoPO zjcUsersInfoPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_users_infoPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcUsersInfoPO zjcUsersInfoPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcUsersInfoPO
	 */
	ZjcUsersInfoPO selectByKey(@Param(value = "user_id") BigInteger user_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcUsersInfoPO
	 */
	ZjcUsersInfoPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcUsersInfoPO>
	 */
	List<ZjcUsersInfoPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcUsersInfoPO>
	 */
	List<ZjcUsersInfoPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcUsersInfoPO>
	 */
	List<ZjcUsersInfoPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcUsersInfoPO>
	 */
	List<ZjcUsersInfoPO> likePage(Dto pDto);

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
	 * 根据实体查询用户信息记录
	 * @param zuserinfo
	 * @return
	 */
	ZjcUsersInfoPO selectByUseriInfoPO(ZjcUsersInfoPO zuserinfo);
	/**
	 * 查找用户的一级上级ID
	 * @param user_id
	 * @return
	 */
	int firstLeader(@Param(value = "user_id") BigInteger user_id);
	/**
	 * 查找用户的二级上级ID
	 * @param user_id
	 * @return
	 */
	int secondLeader(@Param(value = "user_id") BigInteger user_id);
	/**
	 * 查找用户的合格子会员数
	 * @param user_id
	 * @return
	 */
	int qualifiedMember(@Param(value = "user_id") BigInteger user_id);
	
	/**
	 * 查找用户的支付密码
	 * @return
	 */
	ZjcUsersInfoPO checkPayPsd(Dto dto);
	
	/**
	 * 查询我的直属下级
	 * @param dto
	 * @return
	 */
	List<Dto> myApprenticePage(Dto dto);
	
	/**
	 * 查找用户的会员数
	 * @param user_id
	 * @return
	 */
	int member(@Param(value = "user_id") BigInteger user_id);
	
	/**
	 * 统计
	 * @param dto
	 * @return
	 */
	Dto counter(Dto dto);
	
	/**
	 * 查找一级上级
	 * @param Dto
	 * @return
	 */
	ZjcUsersInfoPO getFirstLeader(ZjcUsersInfoPO zuserinfo);
	
	/**
	 * 查找二级上级
	 * @param Dto
	 * @return
	 */
	ZjcUsersInfoPO getSecondLeader(ZjcUsersInfoPO zuserinfo);
	
	ZjcUsersInfoPO selectByMobile(Dto dto);
	
	/**
	 * 查询用户id,token,token有效期
	 * 
	 * @param pDto
	 * @return
	 */
	ZjcUsersInfoPO selectUserToken(Dto pDto);
}
