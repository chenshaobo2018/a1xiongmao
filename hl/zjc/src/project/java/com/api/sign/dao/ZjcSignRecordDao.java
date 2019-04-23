package com.api.sign.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.api.sign.dao.po.ZjcSignRecordPO;

/**
 * <b>zjc_sign_record[zjc_sign_record]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-07-14 11:07:20
 */
@Dao("zjcSignRecordDao")
public interface ZjcSignRecordDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_sign_recordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcSignRecordPO zjcSignRecordPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_sign_recordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcSignRecordPO zjcSignRecordPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_sign_recordPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcSignRecordPO zjcSignRecordPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcSignRecordPO
	 */
	ZjcSignRecordPO selectByKey(@Param(value = "id") Integer id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcSignRecordPO
	 */
	ZjcSignRecordPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcSignRecordPO>
	 */
	List<ZjcSignRecordPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcSignRecordPO>
	 */
	List<ZjcSignRecordPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcSignRecordPO>
	 */
	List<ZjcSignRecordPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcSignRecordPO>
	 */
	List<ZjcSignRecordPO> likePage(Dto pDto);

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
	 * 查询用户签到次数
	 * @param pDto
	 * @return
	 */
	int signTimes(Dto pDto);
	
	/**
	 * 查询用户累计获得是易物劵
	 * @param pDto
	 * @return
	 */
	int getSignTotal(Dto pDto);
	
	/**
	 * 查询当前可以转出的易物劵
	 * @param pDto
	 * @return
	 */
	int getAbTotal(Dto pDto);
	
	/**
	 * 查询用户当月的签到数据
	 * @param pDto
	 * @return
	 */
	List<ZjcSignRecordPO> listMonthSign(Dto pDto);
	/**
	 * 查找用户当前的数据
	 * @param dto
	 * @return
	 */
	ZjcSignRecordPO findCurrent(Dto dto);
}
