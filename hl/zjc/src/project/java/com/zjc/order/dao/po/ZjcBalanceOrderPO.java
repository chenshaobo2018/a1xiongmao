package com.zjc.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_balance_order[zjc_balance_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:52:28
 */
public class ZjcBalanceOrderPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private Integer balance_id;
	
	/**
	 * 订单编号
	 */
	private String balance_sn;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 会员名称
	 */
	private String user_name;
	
	/**
	 * 结算中心价格
	 */
	private BigDecimal balance_price;
	
	/**
	 * 0 待审核  1 审核通过 2 审核未通过
	 */
	private Integer status;
	
	/**
	 * 购买时间
	 */
	private Date add_time;
	
	/**
	 * 审核时间
	 */
	private Date verify_time;
	
	/**
	 * 审核备注
	 */
	private String verify_note;
	
	/**
	 * 管理员ID
	 */
	private Integer admin_id;
	
	/**
	 * 管理员名称
	 */
	private String admin_name;
	

	/**
	 * 订单id
	 * 
	 * @return balance_id
	 */
	public Integer getBalance_id() {
		return balance_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @return balance_sn
	 */
	public String getBalance_sn() {
		return balance_sn;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 会员名称
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * 结算中心价格
	 * 
	 * @return balance_price
	 */
	public BigDecimal getBalance_price() {
		return balance_price;
	}
	
	/**
	 * 0 待审核  1 审核通过 2 审核未通过
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 购买时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 审核时间
	 * 
	 * @return verify_time
	 */
	public Date getVerify_time() {
		return verify_time;
	}
	
	/**
	 * 审核备注
	 * 
	 * @return verify_note
	 */
	public String getVerify_note() {
		return verify_note;
	}
	
	/**
	 * 管理员ID
	 * 
	 * @return admin_id
	 */
	public Integer getAdmin_id() {
		return admin_id;
	}
	
	/**
	 * 管理员名称
	 * 
	 * @return admin_name
	 */
	public String getAdmin_name() {
		return admin_name;
	}
	

	/**
	 * 订单id
	 * 
	 * @param balance_id
	 */
	public void setBalance_id(Integer balance_id) {
		this.balance_id = balance_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @param balance_sn
	 */
	public void setBalance_sn(String balance_sn) {
		this.balance_sn = balance_sn;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 会员名称
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * 结算中心价格
	 * 
	 * @param balance_price
	 */
	public void setBalance_price(BigDecimal balance_price) {
		this.balance_price = balance_price;
	}
	
	/**
	 * 0 待审核  1 审核通过 2 审核未通过
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 购买时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 审核时间
	 * 
	 * @param verify_time
	 */
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	
	/**
	 * 审核备注
	 * 
	 * @param verify_note
	 */
	public void setVerify_note(String verify_note) {
		this.verify_note = verify_note;
	}
	
	/**
	 * 管理员ID
	 * 
	 * @param admin_id
	 */
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	
	/**
	 * 管理员名称
	 * 
	 * @param admin_name
	 */
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	

}