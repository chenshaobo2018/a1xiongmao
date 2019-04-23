package com.api.order.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_payment_saller[zjc_payment_saller]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-21 09:19:45
 */
public class ZjcPaymentSallerPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * user_id
	 */
	private Integer user_id;
	
	/**
	 * pay_code
	 */
	private String pay_code;
	
	/**
	 * add_time
	 */
	private Date add_time;
	
	/**
	 * 是否使用 0-未使用 1-已使用
	 */
	private Integer used;
	
	/**
	 * 交易订单编号
	 */
	private String ex_sn;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
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
	 * pay_code
	 * 
	 * @return pay_code
	 */
	public String getPay_code() {
		return pay_code;
	}
	
	/**
	 * add_time
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 是否使用 0-未使用 1-已使用
	 * 
	 * @return used
	 */
	public Integer getUsed() {
		return used;
	}
	
	/**
	 * 交易订单编号
	 * 
	 * @return ex_sn
	 */
	public String getEx_sn() {
		return ex_sn;
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
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * pay_code
	 * 
	 * @param pay_code
	 */
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	/**
	 * add_time
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 是否使用 0-未使用 1-已使用
	 * 
	 * @param used
	 */
	public void setUsed(Integer used) {
		this.used = used;
	}
	
	/**
	 * 交易订单编号
	 * 
	 * @param ex_sn
	 */
	public void setEx_sn(String ex_sn) {
		this.ex_sn = ex_sn;
	}
	

}