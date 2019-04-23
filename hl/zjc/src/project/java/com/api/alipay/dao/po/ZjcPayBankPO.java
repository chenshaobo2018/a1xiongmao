package com.api.alipay.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_pay_bank[zjc_pay_bank]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-01-17 14:44:37
 */
public class ZjcPayBankPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * bank_id
	 */
	private String bank_id;
	
	/**
	 * user_id
	 */
	private String user_id;
	
	/**
	 * phone
	 */
	private String phone;
	
	/**
	 * bank_card
	 */
	private String bank_card;
	
	/**
	 * id_card
	 */
	private String id_card;
	
	/**
	 * 0不默认1默认
	 */
	private Integer pay_default;
	/**
	 * 是否删除
	 */
	private Integer is_delete;
	

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	/**
	 * bank_id
	 * 
	 * @return bank_id
	 */
	public String getBank_id() {
		return bank_id;
	}
	
	/**
	 * user_id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * phone
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * bank_card
	 * 
	 * @return bank_card
	 */
	public String getBank_card() {
		return bank_card;
	}
	
	/**
	 * id_card
	 * 
	 * @return id_card
	 */
	public String getId_card() {
		return id_card;
	}
	
	/**
	 * 0不默认1默认
	 * 
	 * @return pay_default
	 */
	public Integer getPay_default() {
		return pay_default;
	}
	

	/**
	 * bank_id
	 * 
	 * @param bank_id
	 */
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	
	/**
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * phone
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * bank_card
	 * 
	 * @param bank_card
	 */
	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}
	
	/**
	 * id_card
	 * 
	 * @param id_card
	 */
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	
	/**
	 * 0不默认1默认
	 * 
	 * @param pay_default
	 */
	public void setPay_default(Integer pay_default) {
		this.pay_default = pay_default;
	}
	

}