package com.zjc.users.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_user_level[zjc_user_level]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-05 09:15:30
 */
public class ZjcUserLevelPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer level_id;
	
	/**
	 * 头衔名称
	 */
	private String level_name;
	
	/**
	 * 等级必要金额
	 */
	private Integer amount;
	
	/**
	 * 折扣
	 */
	private String discount;
	
	/**
	 * 头街 描述
	 */
	private String describes;
	
	/**
	 * 是否开通结算中心
	 */
	private Integer is_use_js;
	
	/**
	 * 开通结算中心个数
	 */
	private Integer is_use_number;
	

	/**
	 * 表id
	 * 
	 * @return level_id
	 */
	public Integer getLevel_id() {
		return level_id;
	}
	
	/**
	 * 头衔名称
	 * 
	 * @return level_name
	 */
	public String getLevel_name() {
		return level_name;
	}
	
	/**
	 * 等级必要金额
	 * 
	 * @return amount
	 */
	public Integer getAmount() {
		return amount;
	}
	
	/**
	 * 折扣
	 * 
	 * @return discount
	 */
	public String getDiscount() {
		return discount;
	}
	
	/**
	 * 头街 描述
	 * 
	 * @return describe
	 */
	public String getDescribes() {
		return describes;
	}
	
	/**
	 * 是否开通结算中心
	 * 
	 * @return is_use_js
	 */
	public Integer getIs_use_js() {
		return is_use_js;
	}
	
	/**
	 * 开通结算中心个数
	 * 
	 * @return is_use_number
	 */
	public Integer getIs_use_number() {
		return is_use_number;
	}
	

	/**
	 * 表id
	 * 
	 * @param level_id
	 */
	public void setLevel_id(Integer level_id) {
		this.level_id = level_id;
	}
	
	/**
	 * 头衔名称
	 * 
	 * @param level_name
	 */
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
	/**
	 * 等级必要金额
	 * 
	 * @param amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	/**
	 * 折扣
	 * 
	 * @param discount
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	/**
	 * 头街 描述
	 * 
	 * @param describe
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	/**
	 * 是否开通结算中心
	 * 
	 * @param is_use_js
	 */
	public void setIs_use_js(Integer is_use_js) {
		this.is_use_js = is_use_js;
	}
	
	/**
	 * 开通结算中心个数
	 * 
	 * @param is_use_number
	 */
	public void setIs_use_number(Integer is_use_number) {
		this.is_use_number = is_use_number;
	}
	

}