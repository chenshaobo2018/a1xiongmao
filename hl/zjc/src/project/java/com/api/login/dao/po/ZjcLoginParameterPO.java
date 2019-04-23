package com.api.login.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_login_parameter[zjc_login_parameter]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-27 15:04:49
 */
public class ZjcLoginParameterPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 登陆随机数
	 */
	private String loginrandom;
	
	/**
	 * 使用状态
	 */
	private Integer loginstatus;
	
	/**
	 * 登陆手机号
	 */
	private String mobile;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 登陆随机数
	 * 
	 * @return loginrandom
	 */
	public String getLoginrandom() {
		return loginrandom;
	}
	
	/**
	 * 使用状态
	 * 
	 * @return loginstatus
	 */
	public Integer getLoginstatus() {
		return loginstatus;
	}
	
	/**
	 * 登陆手机号
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 登陆随机数
	 * 
	 * @param loginrandom
	 */
	public void setLoginrandom(String loginrandom) {
		this.loginrandom = loginrandom;
	}
	
	/**
	 * 使用状态
	 * 
	 * @param loginstatus
	 */
	public void setLoginstatus(Integer loginstatus) {
		this.loginstatus = loginstatus;
	}
	
	/**
	 * 登陆手机号
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}