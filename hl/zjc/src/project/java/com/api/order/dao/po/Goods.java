/**
 * 
 */
package com.api.order.dao.po;

/**
 * @author Administrator
 *
 */
public class Goods {
	private String goods_id;
	private String goods_num;//数量
	private String spec_key;//规格
	private String spec_key_name;//规格名字
	
	public String getSpec_key() {
		return spec_key;
	}
	public void setSpec_key(String spec_key) {
		this.spec_key = spec_key;
	}
	public String getSpec_key_name() {
		return spec_key_name;
	}
	public void setSpec_key_name(String spec_key_name) {
		this.spec_key_name = spec_key_name;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	
	
}
