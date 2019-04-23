package com.zjc.system.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_member_multiplication[zjc_member_multiplication]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-13 15:53:40
 */
public class ZjcMemberMultiplicationPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 默认倍增倍数
	 */
	private String default_multiplier;
	
	/**
	 * 限时倍增开始时间
	 */
	private Date start_time;
	
	/**
	 * 限时倍增结束时间
	 */
	private Date end_time;
	
	/**
	 * 限时倍增倍数
	 */
	private String timed_multiplication;
	
	/**
	 * 特殊商家倍增倍数
	 */
	private String special_merchants_multiplication;
	
	/**
	 * 特殊商家倍增结算会员ID
	 */
	private String special_merchants_multiplication_id;
	/**
	 * 线下购物商家提成比例
	 */
	private String offline_shopping_commission_ratio;
	
	/**
	 * 产生时间
	 */
	private Date date_time;
	
	/**
	 * 线下购物会员赠送比例
	 */
	private String offline_shopping_multiplication_rate;
	
	/**
	 * 线下购物会员赠送比例
	 */
	public String getOffline_shopping_multiplication_rate() {
		return offline_shopping_multiplication_rate;
	}

	/**
	 * 线下购物会员赠送比例
	 */
	public void setOffline_shopping_multiplication_rate(
			String offline_shopping_multiplication_rate) {
		this.offline_shopping_multiplication_rate = offline_shopping_multiplication_rate;
	}

	/**
	 * 线下购物商家提成比例
	 */
	public String getOffline_shopping_commission_ratio() {
		return offline_shopping_commission_ratio;
	}

	/**
	 * 线下购物商家提成比例
	 */
	public void setOffline_shopping_commission_ratio(String offline_shopping_commission_ratio) {
		this.offline_shopping_commission_ratio = offline_shopping_commission_ratio;
	}

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 默认倍增倍数
	 * 
	 * @return default_multiplier
	 */
	public String getDefault_multiplier() {
		return default_multiplier;
	}
	
	/**
	 * 限时倍增开始时间
	 * 
	 * @return start_time
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * 限时倍增结束时间
	 * 
	 * @return end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * 限时倍增倍数
	 * 
	 * @return timed_multiplication
	 */
	public String getTimed_multiplication() {
		return timed_multiplication;
	}
	
	/**
	 * 特殊商家倍增倍数
	 * 
	 * @return special_merchants_multiplication
	 */
	public String getSpecial_merchants_multiplication() {
		return special_merchants_multiplication;
	}
	
	/**
	 * 特殊商家倍增结算会员ID
	 * 
	 * @return special_merchants_multiplication_id
	 */
	public String getSpecial_merchants_multiplication_id() {
		return special_merchants_multiplication_id;
	}
	
	/**
	 * 产生时间
	 * 
	 * @return date_time
	 */
	public Date getDate_time() {
		return date_time;
	}
	

	/**
	 * 表id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 默认倍增倍数
	 * 
	 * @param default_multiplier
	 */
	public void setDefault_multiplier(String default_multiplier) {
		this.default_multiplier = default_multiplier;
	}
	
	/**
	 * 限时倍增开始时间
	 * 
	 * @param start_time
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * 限时倍增结束时间
	 * 
	 * @param end_time
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * 限时倍增倍数
	 * 
	 * @param timed_multiplication
	 */
	public void setTimed_multiplication(String timed_multiplication) {
		this.timed_multiplication = timed_multiplication;
	}
	
	/**
	 * 特殊商家倍增倍数
	 * 
	 * @param special_merchants_multiplication
	 */
	public void setSpecial_merchants_multiplication(String special_merchants_multiplication) {
		this.special_merchants_multiplication = special_merchants_multiplication;
	}
	
	/**
	 * 特殊商家倍增结算会员ID
	 * 
	 * @param special_merchants_multiplication_id
	 */
	public void setSpecial_merchants_multiplication_id(String special_merchants_multiplication_id) {
		this.special_merchants_multiplication_id = special_merchants_multiplication_id;
	}
	
	/**
	 * 产生时间
	 * 
	 * @param date_time
	 */
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	

}