package com.zjc.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;

import com.zjc.store.dao.po.ZjcBillOrder;
import com.zjc.store.dao.po.ZjcBillVO;
import com.zjc.store.dao.po.ZjcStorePO;

/**
 * <b>zjc_store[zjc_store]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author AHei
 * @date 2017-05-09 16:22:15
 */
@Dao("zjcStoreDao")
public interface ZjcStoreDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param zjc_storePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(ZjcStorePO zjcStorePO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param zjc_storePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(ZjcStorePO zjcStorePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_storePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(ZjcStorePO zjcStorePO);
	
	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param zjc_storePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey1(ZjcStorePO zjcStorePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ZjcStorePO
	 */
	ZjcStorePO selectByKey(@Param(value = "store_id") Integer store_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ZjcStorePO
	 */
	ZjcStorePO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "store_id") Integer store_id);
	
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
	 * 通过userid查询店铺信息
	 * @param userId
	 * @return
	 */
	ZjcStorePO selectByUserId(@Param(value = "user_id") Integer user_id);
	
	/**
	 * 查询商家列表数据
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> queryStores(Dto pDto);
	
	/**
	 * 查询商家审核列表数据
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> queryAuditStoresPage(Dto pDto);
	
	/**
	 * 查询商家列表数据（多表）
	 * 
	 * @return List<ZjcStorePO>
	 */
	List<ZjcStorePO> queryForListPage(Dto pDto);
	
	/**
	 * 根据商店ID和店主真实姓名查找商店
	 * @return ZjcStorePO
	 */
	ZjcStorePO selectByKeyAndRealName(Dto pDto);

	/**
	 * 通过商品名称查询
	 * 
	 * @param store
	 * @return
	 */
	List<ZjcStorePO> selectStoreByName(ZjcStorePO store);
	
	/**
	 * 导出数据
	 * @param dto
	 * @return
	 */
	List<ZjcBillVO> exceportBillList(Dto dto);
	
	/**
	 * 导出转账订单数据
	 * @param dto
	 * @return
	 */
	List<ZjcBillOrder> exportBillOrder(Dto dto);
}
