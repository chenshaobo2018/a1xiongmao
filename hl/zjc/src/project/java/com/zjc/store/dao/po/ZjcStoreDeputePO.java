package com.zjc.store.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_store_depute[zjc_store_depute]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-08-27 17:08:23
 */
public class ZjcStoreDeputePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 委托人ID
	 */
	private Integer user_id;
	
	/**
	 * 委托人姓名
	 */
	private String real_name;
	
	/**
	 * 身份证号
	 */
	private String id_card;
	
	/**
	 * 联系电话
	 */
	private String contract_mobile;
	
	/**
	 * 企业ID
	 */
	private Integer store_id;
	
	/**
	 * 企业电话
	 */
	private String office_mobile;
	
	/**
	 * 开户户名
	 */
	private String account_name;
	
	/**
	 * 开户账号
	 */
	private String account_mumber;
	
	/**
	 * 开户银行
	 */
	private String bank;
	
	/**
	 * 申请时间
	 */
	private Date add_time;
	
	/**
	 * 审核状态：1待审，2失败，3通过
	 */
	private String state;
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 委托人ID
	 * 
	 * @return user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	
	/**
	 * 委托人姓名
	 * 
	 * @return real_name
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * 身份证号
	 * 
	 * @return id_card
	 */
	public String getId_card() {
		return id_card;
	}
	
	/**
	 * 联系电话
	 * 
	 * @return contract_mobile
	 */
	public String getContract_mobile() {
		return contract_mobile;
	}
	
	/**
	 * 企业ID
	 * 
	 * @return store_id
	 */
	public Integer getStore_id() {
		return store_id;
	}
	
	/**
	 * 企业电话
	 * 
	 * @return office_mobile
	 */
	public String getOffice_mobile() {
		return office_mobile;
	}
	
	/**
	 * 开户户名
	 * 
	 * @return account_name
	 */
	public String getAccount_name() {
		return account_name;
	}
	
	/**
	 * 开户账号
	 * 
	 * @return account_mumber
	 */
	public String getAccount_mumber() {
		return account_mumber;
	}
	
	/**
	 * 开户银行
	 * 
	 * @return bank
	 */
	public String getBank() {
		return bank;
	}
	
	/**
	 * 申请时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 审核状态：1待审，2失败，3通过
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}
	

	/**
	 * 委托人ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 委托人姓名
	 * 
	 * @param real_name
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	/**
	 * 身份证号
	 * 
	 * @param id_card
	 */
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	
	/**
	 * 联系电话
	 * 
	 * @param contract_mobile
	 */
	public void setContract_mobile(String contract_mobile) {
		this.contract_mobile = contract_mobile;
	}
	
	/**
	 * 企业ID
	 * 
	 * @param store_id
	 */
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * 企业电话
	 * 
	 * @param office_mobile
	 */
	public void setOffice_mobile(String office_mobile) {
		this.office_mobile = office_mobile;
	}
	
	/**
	 * 开户户名
	 * 
	 * @param account_name
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	/**
	 * 开户账号
	 * 
	 * @param account_mumber
	 */
	public void setAccount_mumber(String account_mumber) {
		this.account_mumber = account_mumber;
	}
	
	/**
	 * 开户银行
	 * 
	 * @param bank
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	/**
	 * 申请时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 审核状态：1待审，2失败，3通过
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

}