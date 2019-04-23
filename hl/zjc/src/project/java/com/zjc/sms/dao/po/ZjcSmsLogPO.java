package com.zjc.sms.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_sms_log[zjc_sms_log]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-03 15:56:17
 */
public class ZjcSmsLogPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 发送时间
	 */
	private Date add_time;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 验证状态 0 未用 ;1已经使用
	 */
	private Integer status;
	

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 手机号
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 类型
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 发送时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 验证码
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
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
	 * 手机号
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 类型
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 发送时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 验证码
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}