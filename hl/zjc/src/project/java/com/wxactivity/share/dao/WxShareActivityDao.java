package com.wxactivity.share.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;
import com.wxactivity.share.dao.po.WxShareActivityPO;

/**
 * <b>wx_share_activity[wx_share_activity]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2018-01-31 17:44:12
 */
@Dao("wxShareActivityDao")
public interface WxShareActivityDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param wx_share_activityPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(WxShareActivityPO wxShareActivityPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param wx_share_activityPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(WxShareActivityPO wxShareActivityPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param wx_share_activityPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(WxShareActivityPO wxShareActivityPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return WxShareActivityPO
	 */
	WxShareActivityPO selectByKey(@Param(value = "share_id") String share_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return WxShareActivityPO
	 */
	WxShareActivityPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<WxShareActivityPO>
	 */
	List<WxShareActivityPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<WxShareActivityPO>
	 */
	List<WxShareActivityPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<WxShareActivityPO>
	 */
	List<WxShareActivityPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<WxShareActivityPO>
	 */
	List<WxShareActivityPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "share_id") String share_id);
	
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
