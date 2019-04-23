package com.zjc.system.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_member_special[zjc_member_special]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-13 17:47:13
 */
public class ZjcMemberSpecialPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 设置前提条件
	 */
	private String premise_condition;
	
	/**
	 * 配置的val值
	 */
	private String limited_product_id;
	
	/**
	 * 特殊商品ID列表
	 */
	private String special_commodity;
	
	/**
	 * 时间条件
	 */
	private Date time_condition;
	
	/**
	 * 限购数量限制
	 */
	private String limit_for_purchasing;
	
	/**
	 * 会员等级限制
	 */
	private String membership_level_limit;
	
	/**
	 * date_time
	 */
	private Date date_time;
	

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置前提条件
	 * 
	 * @return premise_condition
	 */
	public String getPremise_condition() {
		return premise_condition;
	}
	
	/**
	 * 配置的val值
	 * 
	 * @return limited_product_id
	 */
	public String getLimited_product_id() {
		return limited_product_id;
	}
	
	/**
	 * 特殊商品ID列表
	 * 
	 * @return special_commodity
	 */
	public String getSpecial_commodity() {
		return special_commodity;
	}
	
	/**
	 * 时间条件
	 * 
	 * @return time_condition
	 */
	public Date getTime_condition() {
		return time_condition;
	}
	
	/**
	 * 限购数量限制
	 * 
	 * @return limit_for_purchasing
	 */
	public String getLimit_for_purchasing() {
		return limit_for_purchasing;
	}
	
	/**
	 * 会员等级限制
	 * 
	 * @return membership_level_limit
	 */
	public String getMembership_level_limit() {
		return membership_level_limit;
	}
	
	/**
	 * date_time
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
	 * 设置前提条件
	 * 
	 * @param premise_condition
	 */
	public void setPremise_condition(String premise_condition) {
		this.premise_condition = premise_condition;
	}
	
	/**
	 * 配置的val值
	 * 
	 * @param limited_product_id
	 */
	public void setLimited_product_id(String limited_product_id) {
		this.limited_product_id = limited_product_id;
	}
	
	/**
	 * 特殊商品ID列表
	 * 
	 * @param special_commodity
	 */
	public void setSpecial_commodity(String special_commodity) {
		this.special_commodity = special_commodity;
	}
	
	/**
	 * 时间条件
	 * 
	 * @param time_condition
	 */
	public void setTime_condition(Date time_condition) {
		this.time_condition = time_condition;
	}
	
	/**
	 * 限购数量限制
	 * 
	 * @param limit_for_purchasing
	 */
	public void setLimit_for_purchasing(String limit_for_purchasing) {
		this.limit_for_purchasing = limit_for_purchasing;
	}
	
	/**
	 * 会员等级限制
	 * 
	 * @param membership_level_limit
	 */
	public void setMembership_level_limit(String membership_level_limit) {
		this.membership_level_limit = membership_level_limit;
	}
	
	/**
	 * date_time
	 * 
	 * @param date_time
	 */
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	

}