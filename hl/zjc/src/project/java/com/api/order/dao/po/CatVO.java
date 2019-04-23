/**
 * 
 */
package com.api.order.dao.po;

import java.util.List;

/**购物车构建实体
 * @author Administrator
 *
 */
public class CatVO {
	private int store_id;
	private String store_name;
	private List<ZjcCartPO> ZjcCartPO;
	
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public List<ZjcCartPO> getZjcCartPO() {
		return ZjcCartPO;
	}
	public void setZjcCartPO(List<ZjcCartPO> zjcCartPO) {
		ZjcCartPO = zjcCartPO;
	}

}
