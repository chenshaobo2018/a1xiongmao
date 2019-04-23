package com.zjc.users.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_users_account_info[zjc_users_account_info]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-22 14:04:13
 */
public class ZjcUsersAccountInfoPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 用户金额
	 */
	private BigDecimal user_money;
	
	/**
	 * 冻结金额
	 */
	private BigDecimal frozen_money;
	
	/**
	 * 累积分佣金额
	 */
	private BigDecimal distribut_money;
	
	/**
	 * 消费积分
	 */
	private Integer pay_points;
	
	/**
	 * 消费累计额度(自然消费)
	 */
	private String total_amount;
	
	
	
	/**
	 * 用户可转积分
	 */
	private Integer make_over_integral;
	
	/**
	 * 钱包额度
	 */
	private String wallet_quota;
	
	/**
	 * 是否开通了结算中心(0-否，1-是)
	 */
	private Integer settlement_center;
	/**
	 * 是否开通了结算提成(0-否，1-是)
	 */
	private Integer settlement_center_tc;
	
	/**
	 * 结算中心开始时间
	 */
	private Date settlement_center_stime;
	
	/**
	 * 结算中心结束时间
	 */
	private Date settlement_center_etime;
	
	/**
	 * 注册赠送积分
	 */
	private String reg_giving_points;
	
	/**
	 * 领到易物卷(返给用户的积分)
	 */
	private String return_points;
	
	/**
	 * 赠送易物卷(应得积分)
	 */
	private String due_tc_points;
	
	/**
	 * 领到易物卷(实际提成)
	 */
	private String practical_tc_points;
	
	/**
	 * 钱包总额度
	 */
	private Integer count_wallet_quota;
	
	/**
	 * 0-未开通终端结算 1-开通终端结算
	 */
	private Integer has_terminal;
	
	private Integer sqvip;
	private Integer sqamount;
	private Integer sqpay;
	//结算总额限制
	private Integer settlement_sum;
	//转账总额限制
	private Integer jf_sum;
	private Integer sum;
	private Date update_time;
	private String is_sq;
	private Integer drops;
	

	public Integer getDrops() {
		return drops;
	}

	public void setDrops(Integer drops) {
		this.drops = drops;
	}

	public String getIs_sq() {
		return is_sq;
	}

	public void setIs_sq(String is_sq) {
		this.is_sq = is_sq;
	}

	/**
	 * 用户待转账金额
	 */
	private BigDecimal pending_transfer;
	
	/**
	 * 用户已转账金额
	 */
	private BigDecimal transferred;
	
	/**
	 * 是否冻结账户；1表示冻结，0表示启用
	 */
	private String is_lock;
	
	/**
	 *  是否冻结账户；1表示冻结，0表示启用 
	 */
	private String acc_is_lock;
	
	

	public String getAcc_is_lock() {
		return acc_is_lock;
	}

	public void setAcc_is_lock(String acc_is_lock) {
		this.acc_is_lock = acc_is_lock;
	}

	/**
	 * 是否冻结账户；1表示冻结，0表示启用
	 * 
	 * @return
	 */
	public String getIs_lock() {
		return is_lock;
	}

	/**
	 * 是否冻结账户；1表示冻结，0表示启用
	 * 
	 * @param is_lock
	 */
	public void setIs_lock(String is_lock) {
		this.is_lock = is_lock;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getSettlement_sum() {
		return settlement_sum;
	}

	public void setSettlement_sum(Integer settlement_sum) {
		this.settlement_sum = settlement_sum;
	}

	public Integer getJf_sum() {
		return jf_sum;
	}

	public void setJf_sum(Integer jf_sum) {
		this.jf_sum = jf_sum;
	}

	public Integer getSqvip() {
		return sqvip;
	}

	public void setSqvip(Integer sqvip) {
		this.sqvip = sqvip;
	}

	public Integer getSqamount() {
		return sqamount;
	}

	public void setSqamount(Integer sqamount) {
		this.sqamount = sqamount;
	}

	public Integer getSqpay() {
		return sqpay;
	}

	public void setSqpay(Integer sqpay) {
		this.sqpay = sqpay;
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
	 * 用户金额
	 * 
	 * @return user_money
	 */
	public BigDecimal getUser_money() {
		return user_money;
	}
	
	/**
	 * 冻结金额
	 * 
	 * @return frozen_money
	 */
	public BigDecimal getFrozen_money() {
		return frozen_money;
	}
	
	/**
	 * 累积分佣金额
	 * 
	 * @return distribut_money
	 */
	public BigDecimal getDistribut_money() {
		return distribut_money;
	}
	
	/**
	 * 消费积分
	 * 
	 * @return pay_points
	 */
	public Integer getPay_points() {
		return pay_points;
	}
	
	/**
	 * 消费累计额度(自然消费)
	 * 
	 * @return total_amount
	 */
	public String getTotal_amount() {
		return total_amount;
	}
	
	
	
	/**
	 * 用户可转积分
	 * 
	 * @return make_over_integral
	 */
	public Integer getMake_over_integral() {
		return make_over_integral;
	}
	
	/**
	 * 钱包额度
	 * 
	 * @return wallet_quota
	 */
	public String getWallet_quota() {
		return wallet_quota;
	}
	
	/**
	 * 是否开通了结算中心(0-否，1-是)
	 * 
	 * @return settlement_center
	 */
	public Integer getSettlement_center() {
		return settlement_center;
	}
	
	/**
	 * 结算中心开始时间
	 * 
	 * @return settlement_center_stime
	 */
	public Date getSettlement_center_stime() {
		return settlement_center_stime;
	}
	
	/**
	 * 结算中心结束时间
	 * 
	 * @return settlement_center_etime
	 */
	public Date getSettlement_center_etime() {
		return settlement_center_etime;
	}
	
	/**
	 * 注册赠送积分
	 * 
	 * @return reg_giving_points
	 */
	public String getReg_giving_points() {
		return reg_giving_points;
	}
	
	/**
	 * 领到易物卷(返给用户的积分)
	 * 
	 * @return return_points
	 */
	public String getReturn_points() {
		return return_points;
	}
	
	/**
	 * 赠送易物卷(应得积分)
	 * 
	 * @return due_tc_points
	 */
	public String getDue_tc_points() {
		return due_tc_points;
	}
	
	/**
	 * 领到易物卷(实际提成)
	 * 
	 * @return practical_tc_points
	 */
	public String getPractical_tc_points() {
		return practical_tc_points;
	}
	
	/**
	 * 钱包总额度
	 * 
	 * @return count_wallet_quota
	 */
	public Integer getCount_wallet_quota() {
		return count_wallet_quota;
	}
	
	/**
	 * 0-未开通终端结算 1-开通终端结算
	 * 
	 * @return has_terminal
	 */
	public Integer getHas_terminal() {
		return has_terminal;
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
	 * 用户金额
	 * 
	 * @param user_money
	 */
	public void setUser_money(BigDecimal user_money) {
		this.user_money = user_money;
	}
	
	/**
	 * 冻结金额
	 * 
	 * @param frozen_money
	 */
	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}
	
	/**
	 * 累积分佣金额
	 * 
	 * @param distribut_money
	 */
	public void setDistribut_money(BigDecimal distribut_money) {
		this.distribut_money = distribut_money;
	}
	
	/**
	 * 消费积分
	 * 
	 * @param pay_points
	 */
	public void setPay_points(Integer pay_points) {
		this.pay_points = pay_points;
	}
	
	/**
	 * 消费累计额度(自然消费)
	 * 
	 * @param total_amount
	 */
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	
	
	
	/**
	 * 用户可转积分
	 * 
	 * @param make_over_integral
	 */
	public void setMake_over_integral(Integer make_over_integral) {
		this.make_over_integral = make_over_integral;
	}
	
	/**
	 * 钱包额度
	 * 
	 * @param wallet_quota
	 */
	public void setWallet_quota(String wallet_quota) {
		this.wallet_quota = wallet_quota;
	}
	
	/**
	 * 是否开通了结算中心(0-否，1-是)
	 * 
	 * @param settlement_center
	 */
	public void setSettlement_center(Integer settlement_center) {
		this.settlement_center = settlement_center;
	}
	
	/**
	 * 结算中心开始时间
	 * 
	 * @param settlement_center_stime
	 */
	public void setSettlement_center_stime(Date settlement_center_stime) {
		this.settlement_center_stime = settlement_center_stime;
	}
	
	/**
	 * 结算中心结束时间
	 * 
	 * @param settlement_center_etime
	 */
	public void setSettlement_center_etime(Date settlement_center_etime) {
		this.settlement_center_etime = settlement_center_etime;
	}
	
	/**
	 * 注册赠送积分
	 * 
	 * @param reg_giving_points
	 */
	public void setReg_giving_points(String reg_giving_points) {
		this.reg_giving_points = reg_giving_points;
	}
	
	/**
	 * 领到易物卷(返给用户的积分)
	 * 
	 * @param return_points
	 */
	public void setReturn_points(String return_points) {
		this.return_points = return_points;
	}
	
	/**
	 * 赠送易物卷(应得积分)
	 * 
	 * @param due_tc_points
	 */
	public void setDue_tc_points(String due_tc_points) {
		this.due_tc_points = due_tc_points;
	}
	
	/**
	 * 领到易物卷(实际提成)
	 * 
	 * @param practical_tc_points
	 */
	public void setPractical_tc_points(String practical_tc_points) {
		this.practical_tc_points = practical_tc_points;
	}
	
	/**
	 * 钱包总额度
	 * 
	 * @param count_wallet_quota
	 */
	public void setCount_wallet_quota(Integer count_wallet_quota) {
		this.count_wallet_quota = count_wallet_quota;
	}
	
	/**
	 * 0-未开通终端结算 1-开通终端结算
	 * 
	 * @param has_terminal
	 */
	public void setHas_terminal(Integer has_terminal) {
		this.has_terminal = has_terminal;
	}

	public ZjcUsersAccountInfoPO() {
		super();
	}

	
	
	public Integer getSettlement_center_tc() {
		return settlement_center_tc;
	}

	public void setSettlement_center_tc(Integer settlement_center_tc) {
		this.settlement_center_tc = settlement_center_tc;
	}

	public ZjcUsersAccountInfoPO(Integer make_over_integral, String wallet_quota, String due_tc_points,
			String practical_tc_points) {
		super();
		this.make_over_integral = make_over_integral;
		this.wallet_quota = wallet_quota;
		this.due_tc_points = due_tc_points;
		this.practical_tc_points = practical_tc_points;
	}

	public BigDecimal getPending_transfer() {
		return pending_transfer;
	}

	public void setPending_transfer(BigDecimal pending_transfer) {
		this.pending_transfer = pending_transfer;
	}

	public BigDecimal getTransferred() {
		return transferred;
	}

	public void setTransferred(BigDecimal transferred) {
		this.transferred = transferred;
	}
}