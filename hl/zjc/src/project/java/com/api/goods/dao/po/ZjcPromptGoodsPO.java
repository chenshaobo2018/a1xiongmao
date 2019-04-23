package com.api.goods.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_prompt_goods[zjc_prompt_goods]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-02-07 09:31:29
 */
public class ZjcPromptGoodsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * goods_id
	 */
	private Integer goods_id;
	
	/**
	 * start_time
	 */
	private Date start_time;
	
	/**
	 * end_time
	 */
	private Date end_time;
	
	/**
	 * 限时价格（券）
	 */
	private String limit_market_price;
	
	/**
	 * 限时价格（现金）
	 */
	private String limit_cost_price;
	

	/**
	 * goods_id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * start_time
	 * 
	 * @return start_time
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * end_time
	 * 
	 * @return end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * 限时价格（券）
	 * 
	 * @return limit_market_price
	 */
	public String getLimit_market_price() {
		return limit_market_price;
	}
	
	/**
	 * 限时价格（现金）
	 * 
	 * @return limit_cost_price
	 */
	public String getLimit_cost_price() {
		return limit_cost_price;
	}
	

	/**
	 * goods_id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * start_time
	 * 
	 * @param start_time
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * end_time
	 * 
	 * @param end_time
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * 限时价格（券）
	 * 
	 * @param limit_market_price
	 */
	public void setLimit_market_price(String limit_market_price) {
		this.limit_market_price = limit_market_price;
	}
	
	/**
	 * 限时价格（现金）
	 * 
	 * @param limit_cost_price
	 */
	public void setLimit_cost_price(String limit_cost_price) {
		this.limit_cost_price = limit_cost_price;
	}
	

}
