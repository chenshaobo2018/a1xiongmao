package com.zjc.users.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_withdrawal[zjc_withdrawal]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-02-01 10:54:00
 */
public class ZjcWithdrawalPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * user_id
	 */
	private BigInteger user_id;
	
	/**
	 * 提现金额
	 */
	private String cash;
	
	/**
	 * add_time
	 */
	private Date add_time;
	
	/**
	 * 是否处理
	 */
	private Integer is_withdrawal;
	

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
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 提现金额
	 * 
	 * @return cash
	 */
	public String getCash() {
		return cash;
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
	 * 是否处理
	 * 
	 * @return is_withdrawal
	 */
	public Integer getIs_withdrawal() {
		return is_withdrawal;
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
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 提现金额
	 * 
	 * @param cash
	 */
	public void setCash(String cash) {
		this.cash = cash;
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
	 * 是否处理
	 * 
	 * @param is_withdrawal
	 */
	public void setIs_withdrawal(Integer is_withdrawal) {
		this.is_withdrawal = is_withdrawal;
	}
	

}