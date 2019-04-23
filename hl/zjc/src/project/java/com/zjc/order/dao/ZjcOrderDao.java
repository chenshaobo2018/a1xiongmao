package com.zjc.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.order.dao.po.ZjcOrderExportVO;
import com.zjc.order.dao.po.ZjcOrderPO;

/**
 * <b>zjc_order[zjc_order]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-05-24 09:08:52
 */
@Dao("zjcOrderDao")
public interface ZjcOrderDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_orderPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcOrderPO zjcOrderPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_orderPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcOrderPO zjcOrderPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_orderPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcOrderPO zjcOrderPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcOrderPO
	 */
	ZjcOrderPO selectByKey(@Param(value = "order_id") Integer order_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcOrderPO
	 */
	ZjcOrderPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcOrderPO>
	 */
	List<ZjcOrderPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcOrderPO>
	 */
	List<ZjcOrderPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcOrderPO>
	 */
	List<ZjcOrderPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcOrderPO>
	 */
	List<ZjcOrderPO> likePage(Dto pDto);
	
	/**
	 * 根据Dto模糊查询待导出订单数据
	 * 
	 * @param pDto
	 * @return List<ZjcOrderPO>
	 */
	List<ZjcOrderExportVO> findExportData(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "order_id") Integer order_id);
	
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
	 * 根据order_id修改订单状态为取消
	 * @param pDto
	 * @return int
	 */
	int cancleByKey(Dto pDto);
	
	/**
	 * 根据order_id和订单状态修改订单状态为取消
	 * @param pDto
	 * @return int
	 */
	int deleteByKeyAndOrderStatus(Dto pDto);
	/**
	 * 根据返回的数据完成易物劵结算
	 * @param pDto
	 * @return
	 */
	int confirmSettle(Dto pDto);
	
	/*
	 * 根据订单ID查找卖家ID
	 */
	int getSaleUserID(@Param(value = "order_id") Integer order_id);
	
	/**
	 * 检查用户充值积分所需的现金
	 * @param pDto
	 * @return
	 */
	String check_buy_points(Dto pDto);
	
	/**
	 * 检查用户当天还可以购买多少积分
	 * @param pDto
	 * @return
	 */
	int check_user_points(Dto pDto);
	
	Dto getZjcOrderInfoByOrderId(@Param(value = "order_id") Integer order_id);
}
