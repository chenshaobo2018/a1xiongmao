package com.zjc.goods.dao.po;

import java.math.BigDecimal;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_spec_goods_price[zjc_spec_goods_price]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-14 09:18:09
 */
public class ZjcSpecGoodsPricePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * 规格键名
	 */
	private String key;
	
	/**
	 * 规格键名中文
	 */
	private String key_name;
	
	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 库存数量
	 */
	private Integer store_count;
	
	/**
	 * 商品条形码
	 */
	private String bar_code;
	
	/**
	 * SKU
	 */
	private String sku;
	
	/**
	 * 积分比例价
	 */
	private BigDecimal market_price;
	
	/**
	 * 等额积分价
	 */
	private BigDecimal cost_price;
	

	/**
	 * 商品id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * 规格键名
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 规格键名中文
	 * 
	 * @return key_name
	 */
	public String getKey_name() {
		return key_name;
	}
	
	/**
	 * 价格
	 * 
	 * @return price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * 库存数量
	 * 
	 * @return store_count
	 */
	public Integer getStore_count() {
		return store_count;
	}
	
	/**
	 * 商品条形码
	 * 
	 * @return bar_code
	 */
	public String getBar_code() {
		return bar_code;
	}
	
	/**
	 * SKU
	 * 
	 * @return sku
	 */
	public String getSku() {
		return sku;
	}
	
	/**
	 * 积分比例价
	 * 
	 * @return market_price
	 */
	public BigDecimal getMarket_price() {
		return market_price;
	}
	
	/**
	 * 等额积分价
	 * 
	 * @return cost_price
	 */
	public BigDecimal getCost_price() {
		return cost_price;
	}
	

	/**
	 * 商品id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * 规格键名
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 规格键名中文
	 * 
	 * @param key_name
	 */
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	
	/**
	 * 价格
	 * 
	 * @param price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * 库存数量
	 * 
	 * @param store_count
	 */
	public void setStore_count(Integer store_count) {
		this.store_count = store_count;
	}
	
	/**
	 * 商品条形码
	 * 
	 * @param bar_code
	 */
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	
	/**
	 * SKU
	 * 
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	/**
	 * 积分比例价
	 * 
	 * @param market_price
	 */
	public void setMarket_price(BigDecimal market_price) {
		this.market_price = market_price;
	}
	
	/**
	 * 等额积分价
	 * 
	 * @param cost_price
	 */
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	

}