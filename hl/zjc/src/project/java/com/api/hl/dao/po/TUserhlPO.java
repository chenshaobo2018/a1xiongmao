package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_userhl[t_userhl]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:11
 */
public class TUserhlPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * username
	 */
	private String username;
	
	/**
	 * phone
	 */
	private String phone;
	
	/**
	 * password
	 */
	private String password;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * username
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
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
	 * password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * username
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

}