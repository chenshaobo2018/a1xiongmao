package com.zjc.users.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_after_sales_record[zjc_after_sales_record]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-08-21 16:07:05
 */
public class ZjcAfterSalesRecordPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 操作人
	 */
	private Integer operation;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 操作类型
	 */
	private String operation_type;
	
	/**
	 * 操作内容
	 */
	private String operating_content;
	
	/**
	 * user_id
	 */
	private Integer user_id;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 操作人
	 * 
	 * @return operation
	 */
	public Integer getOperation() {
		return operation;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 操作类型
	 * 
	 * @return operation_type
	 */
	public String getOperation_type() {
		return operation_type;
	}
	
	/**
	 * 操作内容
	 * 
	 * @return operating_content
	 */
	public String getOperating_content() {
		return operating_content;
	}
	
	/**
	 * user_id
	 * 
	 * @return user_id
	 */
	public Integer getUser_id() {
		return user_id;
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
	 * 操作人
	 * 
	 * @param operation
	 */
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 操作类型
	 * 
	 * @param operation_type
	 */
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	
	/**
	 * 操作内容
	 * 
	 * @param operating_content
	 */
	public void setOperating_content(String operating_content) {
		this.operating_content = operating_content;
	}
	
	/**
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	

}