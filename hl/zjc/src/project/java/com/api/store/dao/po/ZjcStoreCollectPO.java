package com.api.store.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

import com.zjc.store.dao.po.ZjcStorePO;

/**
 * <b>zjc_store_collect[zjc_store_collect]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-06 17:59:55
 */
public class ZjcStoreCollectPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer collect_id;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 商家id
	 */
	private Integer store_id;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 店铺
	 */
	private ZjcStorePO zjcStorePO;
	

	/**
	 * 表id
	 * 
	 * @return collect_id
	 */
	public Integer getCollect_id() {
		return collect_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 商家id
	 * 
	 * @return store_id
	 */
	public Integer getStore_id() {
		return store_id;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	

	/**
	 * 表id
	 * 
	 * @param collect_id
	 */
	public void setCollect_id(Integer collect_id) {
		this.collect_id = collect_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 商家id
	 * 
	 * @param store_id
	 */
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public ZjcStorePO getZjcStorePO() {
		return zjcStorePO;
	}

	public void setZjcStorePO(ZjcStorePO zjcStorePO) {
		this.zjcStorePO = zjcStorePO;
	}
	
	

}