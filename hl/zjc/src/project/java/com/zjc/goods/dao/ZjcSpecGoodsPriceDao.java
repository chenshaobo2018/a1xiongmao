package com.zjc.goods.dao;

import java.util.List;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.goods.dao.po.ZjcSpecGoodsPricePO;

/**
 * <b>zjc_spec_goods_price[zjc_spec_goods_price]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-06-14 09:18:09
 */
@Dao("zjcSpecGoodsPriceDao")
public interface ZjcSpecGoodsPriceDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_spec_goods_pricePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcSpecGoodsPricePO zjcSpecGoodsPricePO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_spec_goods_pricePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcSpecGoodsPricePO zjcSpecGoodsPricePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_spec_goods_pricePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcSpecGoodsPricePO zjcSpecGoodsPricePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcSpecGoodsPricePO
	 */
	ZjcSpecGoodsPricePO selectByKey();

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcSpecGoodsPricePO
	 */
	ZjcSpecGoodsPricePO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcSpecGoodsPricePO>
	 */
	List<ZjcSpecGoodsPricePO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcSpecGoodsPricePO>
	 */
	List<ZjcSpecGoodsPricePO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcSpecGoodsPricePO>
	 */
	List<ZjcSpecGoodsPricePO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcSpecGoodsPricePO>
	 */
	List<ZjcSpecGoodsPricePO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey();
	
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
	 * 根据商品ID查询规格项串
	 * 
	 * @param pDto
	 * @return
	 */
	String getSpecStr(Dto pDto);
	
}
