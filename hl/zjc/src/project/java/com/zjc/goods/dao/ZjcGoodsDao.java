package com.zjc.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcGoodsVO;

/**
 * <b>zjc_goods[zjc_goods]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:56:31
 */
@Dao("zjcGoodsDao")
public interface ZjcGoodsDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_goodsPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcGoodsPO zjcGoodsPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_goodsPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcGoodsPO zjcGoodsPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_goodsPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcGoodsPO zjcGoodsPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcGoodsPO
	 */
	ZjcGoodsPO selectByKey(@Param(value = "goods_id") Integer goods_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcGoodsPO
	 */
	ZjcGoodsPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcGoodsPO>
	 */
	List<ZjcGoodsPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcGoodsPO>
	 */
	List<ZjcGoodsPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcGoodsPO>
	 */
	List<ZjcGoodsPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcGoodsPO>
	 */
	List<ZjcGoodsPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "goods_id") Integer goods_id);
	
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
	 * 获取新商品，默认按照时间倒叙排列
	 * 
	 * @param pDto
	 * @return String
	 */
	List<ZjcGoodsPO> getNewGoodsPage(Dto pDto);
	/**
	 * 查找订单的商品数组
	 * @param order_id
	 * @return
	 */
	List<ZjcGoodsVO> getOrderGoodsList(@Param(value = "order_id") Integer order_id);
	
	
	/**
	 * 商家后台修改商品信息
	 * 
	 * @param zjc_goodsPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateStoreGoods(ZjcGoodsPO zjcGoodsPO);
	
}
