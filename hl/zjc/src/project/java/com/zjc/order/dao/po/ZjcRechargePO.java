package com.zjc.order.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_recharge[zjc_recharge]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:52:29
 */
public class ZjcRechargePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * order_id
	 */
	private BigInteger order_id;
	
	/**
	 * 会员ID
	 */
	private BigInteger user_id;
	
	/**
	 * 会员昵称
	 */
	private String nickname;
	
	/**
	 * 充值单号
	 */
	private String order_sn;
	
	/**
	 * 充值金额
	 */
	private String account;
	
	/**
	 * 充值时间
	 */
	private Date ctime;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	
	/**
	 * pay_code
	 */
	private String pay_code;
	
	/**
	 * 支付方式
	 */
	private String pay_name;
	
	/**
	 * 充值状态0:待支付 1:充值成功 2:交易关闭
	 */
	private Integer pay_status;
	
	/**
	 * 购买积分
	 */
	private String buy_points;
	
	/**
	 * 联系电话
	 */
	private String mobile;
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * order_id
	 * 
	 * @return order_id
	 */
	public BigInteger getOrder_id() {
		return order_id;
	}
	
	/**
	 * 会员ID
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 会员昵称
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * 充值单号
	 * 
	 * @return order_sn
	 */
	public String getOrder_sn() {
		return order_sn;
	}
	
	/**
	 * 充值金额
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * 充值时间
	 * 
	 * @return ctime
	 */
	public Date getCtime() {
		return ctime;
	}
	
	/**
	 * 支付时间
	 * 
	 * @return pay_time
	 */
	public Date getPay_time() {
		return pay_time;
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
	 * 支付方式
	 * 
	 * @return pay_name
	 */
	public String getPay_name() {
		return pay_name;
	}
	
	/**
	 * 充值状态0:待支付 1:充值成功 2:交易关闭
	 * 
	 * @return pay_status
	 */
	public Integer getPay_status() {
		return pay_status;
	}
	
	/**
	 * 购买积分
	 * 
	 * @return buy_points
	 */
	public String getBuy_points() {
		return buy_points;
	}
	

	/**
	 * order_id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(BigInteger order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 会员ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 会员昵称
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 充值单号
	 * 
	 * @param order_sn
	 */
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	
	/**
	 * 充值金额
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 充值时间
	 * 
	 * @param ctime
	 */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	/**
	 * 支付时间
	 * 
	 * @param pay_time
	 */
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
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
	 * 支付方式
	 * 
	 * @param pay_name
	 */
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	
	/**
	 * 充值状态0:待支付 1:充值成功 2:交易关闭
	 * 
	 * @param pay_status
	 */
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	
	/**
	 * 购买积分
	 * 
	 * @param buy_points
	 */
	public void setBuy_points(String buy_points) {
		this.buy_points = buy_points;
	}
	

}