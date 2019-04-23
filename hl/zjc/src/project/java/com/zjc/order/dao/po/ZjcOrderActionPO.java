package com.zjc.order.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_order_action[zjc_order_action]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:52:29
 */
public class ZjcOrderActionPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer action_id;
	
	/**
	 * 订单ID
	 */
	private Integer order_id;
	
	/**
	 * 操作人 0 为管理员操作
	 */
	private BigInteger action_user;
	
	/**
	 *  1用户 ，2 管理员  3，商家用户ID
	 */
	private Integer action_user_type;
	
	/**
	 * 订单状态
	 */
	private Integer order_status;
	
	/**
	 * 配送状态
	 */
	private Integer shipping_status;
	
	/**
	 * 支付状态
	 */
	private Integer pay_status;
	
	/**
	 * 操作备注
	 */
	private String action_note;
	
	/**
	 * 操作时间
	 */
	private Date log_time;
	
	/**
	 * 状态描述
	 */
	private String status_desc;
	
	/**
	 *操作人名字 
	 */
	private String action_user_name;

	/**
	 * 表id
	 * 
	 * @return action_id
	 */
	public Integer getAction_id() {
		return action_id;
	}
	
	/**
	 * 订单ID
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	
	/**
	 * 操作人 0 为管理员操作
	 * 
	 * @return action_user
	 */
	public BigInteger getAction_user() {
		return action_user;
	}
	
	/**
	 *  1用户 ，2 管理员  3，商家用户ID
	 * 
	 * @return action_user_type
	 */
	public Integer getAction_user_type() {
		return action_user_type;
	}
	
	/**
	 * 订单状态
	 * 
	 * @return order_status
	 */
	public Integer getOrder_status() {
		return order_status;
	}
	
	/**
	 * 配送状态
	 * 
	 * @return shipping_status
	 */
	public Integer getShipping_status() {
		return shipping_status;
	}
	
	/**
	 * 支付状态
	 * 
	 * @return pay_status
	 */
	public Integer getPay_status() {
		return pay_status;
	}
	
	/**
	 * 操作备注
	 * 
	 * @return action_note
	 */
	public String getAction_note() {
		return action_note;
	}
	
	/**
	 * 操作时间
	 * 
	 * @return log_time
	 */
	public Date getLog_time() {
		return log_time;
	}
	
	/**
	 * 状态描述
	 * 
	 * @return status_desc
	 */
	public String getStatus_desc() {
		return status_desc;
	}
	

	/**
	 * 表id
	 * 
	 * @param action_id
	 */
	public void setAction_id(Integer action_id) {
		this.action_id = action_id;
	}
	
	/**
	 * 订单ID
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 操作人 0 为管理员操作
	 * 
	 * @param action_user
	 */
	public void setAction_user(BigInteger action_user) {
		this.action_user = action_user;
	}
	
	/**
	 *  1用户 ，2 管理员  3，商家用户ID
	 * 
	 * @param action_user_type
	 */
	public void setAction_user_type(Integer action_user_type) {
		this.action_user_type = action_user_type;
	}
	
	/**
	 * 订单状态
	 * 
	 * @param order_status
	 */
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	
	/**
	 * 配送状态
	 * 
	 * @param shipping_status
	 */
	public void setShipping_status(Integer shipping_status) {
		this.shipping_status = shipping_status;
	}
	
	/**
	 * 支付状态
	 * 
	 * @param pay_status
	 */
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	
	/**
	 * 操作备注
	 * 
	 * @param action_note
	 */
	public void setAction_note(String action_note) {
		this.action_note = action_note;
	}
	
	/**
	 * 操作时间
	 * 
	 * @param log_time
	 */
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	
	/**
	 * 状态描述
	 * 
	 * @param status_desc
	 */
	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
	
	public String getAction_user_name() {
		return action_user_name;
	}

	public void setAction_user_name(String action_user_name) {
		this.action_user_name = action_user_name;
	}

	public ZjcOrderActionPO(Integer order_id, BigInteger action_user, Integer action_user_type, Integer order_status,
			Integer shipping_status, Integer pay_status, String action_note, Date log_time, String status_desc,String action_user_name) {
		super();
		this.order_id = order_id;
		this.action_user = action_user;
		this.action_user_type = action_user_type;
		this.order_status = order_status;
		this.shipping_status = shipping_status;
		this.pay_status = pay_status;
		this.action_note = action_note;
		this.log_time = log_time;
		this.status_desc = status_desc;
		this.action_user_name = action_user_name;
	}

	public ZjcOrderActionPO() {
		super();
	}
}