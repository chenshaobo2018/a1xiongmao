package com.zjc.store.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_store[zjc_store]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 15:39:14
 */
public class ExportStoreVO extends PO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商家ID
	 */
	private String user_id;
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
	
	/**
	 * 绑定的商品分类
	 */
	private String cat_name;
	
	/**
	 * 真实姓名
	 */
	private String real_name;
	
	/**
	 * 营业执照电子版
	 */
	private String business_licence_number_elc;
	
	/**
	 * 联系电话1
	 */
	private String mobile;
	
	/**
	 * 联系电话2
	 */
	private String contacts_phone;


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getBusiness_licence_number_elc() {
		return business_licence_number_elc;
	}

	public void setBusiness_licence_number_elc(String business_licence_number_elc) {
		this.business_licence_number_elc = business_licence_number_elc;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContacts_phone() {
		return contacts_phone;
	}

	public void setContacts_phone(String contacts_phone) {
		this.contacts_phone = contacts_phone;
	}
}