package com.zjc.store.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_transfer[zjc_transfer]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-09-02 11:23:39
 */
public class ZjcTransferPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表ID
	 */
	private Integer id;
	
	/**
	 * 订单ID
	 */
	private Integer order_id;
	
	/**
	 * 申请转账时间
	 */
	private Date add_time;
	
	/**
	 * 通过时间
	 */
	private Date transit_time;
	
	/**
	 * 转账金额
	 */
	private BigDecimal cash;
	
	/**
	 * 审批员ID
	 */
	private BigInteger approval_id;
	
	/**
	 * 操作员ID
	 */
	private BigInteger operator_id;
	

	/**
	 * 表ID
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
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
	 * 申请转账时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 通过时间
	 * 
	 * @return transit_time
	 */
	public Date getTransit_time() {
		return transit_time;
	}
	
	/**
	 * 转账金额
	 * 
	 * @return cash
	 */
	public BigDecimal getCash() {
		return cash;
	}
	
	/**
	 * 审批员ID
	 * 
	 * @return approval_id
	 */
	public BigInteger getApproval_id() {
		return approval_id;
	}
	
	/**
	 * 操作员ID
	 * 
	 * @return operator_id
	 */
	public BigInteger getOperator_id() {
		return operator_id;
	}
	

	/**
	 * 表ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * 申请转账时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 通过时间
	 * 
	 * @param transit_time
	 */
	public void setTransit_time(Date transit_time) {
		this.transit_time = transit_time;
	}
	
	/**
	 * 转账金额
	 * 
	 * @param cash
	 */
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	
	/**
	 * 审批员ID
	 * 
	 * @param approval_id
	 */
	public void setApproval_id(BigInteger approval_id) {
		this.approval_id = approval_id;
	}
	
	/**
	 * 操作员ID
	 * 
	 * @param operator_id
	 */
	public void setOperator_id(BigInteger operator_id) {
		this.operator_id = operator_id;
	}
	

}