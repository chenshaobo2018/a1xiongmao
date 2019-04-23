package com.zjc.system.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_member_parameter[zjc_member_parameter]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-13 11:27:40
 */
public class ZjcMemberParameterPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 合格会员
	 */
	private String qualified_menber;
	
	/**
	 * 会员id跳段
	 */
	private String menber_id;
	
	/**
	 * 会员注册赠送开始时间
	 */
	private Date start_time;
	
	/**
	 * 会员注册赠送结束时间
	 */
	private Date end_time;
	
	/**
	 * 会员注册赠送易物券
	 */
	private String give_barter;
	
	/**
	 * 充值易物券兑换比例
	 */
	private String barter_coupo_rate;
	
	/**
	 * 直推会员总数量限制
	 */
	private String membership_amount_limit;
	
	/**
	 * 合格会员数量限制
	 */
	private String members_always_limit;
	
	/**
	 * 企业号合格会员数量限制
	 */
	private String enterprise_always_limit;
	
	/**
	 * 会员提成比例设置一
	 */
	private String member_comm_percentage_one;
	
	/**
	 * 会员提成比例设置一
	 */
	private String member_comm_percentage_ones;
	
	/**
	 * 会员提成比例设置二
	 */
	private String member_comm_percentage_two;
	
	/**
	 * 会员提成比例设置二
	 */
	private String member_comm_percentage_twos;
	
	/**
	 * 会员提成比例设置三
	 */
	private String member_comm_percentage_three;
	
	/**
	 * 会员提成比例设置三
	 */
	private String member_comm_percentage_threes;
	
	/**
	 * 会员返佣模式
	 */
	private String member_commission_model;
	private int  top_up_barter_volume;
	private int offline_top_up_barter_volume;
	private int top_up_barter_volume_minimum;
	private String integral;
	private String amount;
	
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * 会员每天转入最大值(0-200000)
	 */
	private int day_roll_in_max;
	/**
	 * 当日线下购物最大值（0_200000）
	 */
	private int day_line_shop_max;
	/**
	 * 当日商城购物最大值（0_200000）
	 */
	private int day_online_shop_max;
	/**
	 * 是否全部冻结账户；1表示冻结，0表示启用
	 */
	private String is_lock;
	/**
	 * 结算每天不能超过值
	 */
	private int sum_settlement;
	/**
	 * 会员每天转出最大值(0-200000)
	 */
	private int sum_points_today;
	/**
	 * 单笔转账最小值(0-2000)
	 */
	private int points_other_min;
	
	
    private int update_time;
	
	
	
	/**
	 * 会员每天转入最大值(0-200000)
	 * 
	 * @return
	 */
	public int getDay_roll_in_max() {
		return day_roll_in_max;
	}

	/**
	 * 会员每天转入最大值(0-200000)
	 * 
	 * @param day_roll_in_max
	 */
	public void setDay_roll_in_max(int day_roll_in_max) {
		this.day_roll_in_max = day_roll_in_max;
	}

	/**
	 * 当日线下购物最大值（0_200000）
	 * 
	 * @return
	 */
	public int getDay_line_shop_max() {
		return day_line_shop_max;
	}

	/**
	 * 当日线下购物最大值（0_200000）
	 * 
	 * @param day_line_shop_max
	 */
	public void setDay_line_shop_max(int day_line_shop_max) {
		this.day_line_shop_max = day_line_shop_max;
	}

	/**
	 * 当日商城购物最大值（0_200000）
	 * 
	 * @return
	 */
	public int getDay_online_shop_max() {
		return day_online_shop_max;
	}

	/**
	 * 当日商城购物最大值（0_200000）
	 * 
	 * @param day_online_shop_max
	 */
	public void setDay_online_shop_max(int day_online_shop_max) {
		this.day_online_shop_max = day_online_shop_max;
	}

	/**
	 * 是否全部冻结账户；1表示冻结，0表示启用
	 * 
	 * @return
	 */
	public String getIs_lock() {
		return is_lock;
	}

	/**
	 * 是否全部冻结账户；1表示冻结，0表示启用
	 * 
	 * @param is_lock
	 */
	public void setIs_lock(String is_lock) {
		this.is_lock = is_lock;
	}

	public int getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(int update_time) {
		this.update_time = update_time;
	}
	
	public int getSum_settlement() {
		return sum_settlement;
	}

	public void setSum_settlement(int sum_settlement) {
		this.sum_settlement = sum_settlement;
	}

	public int getSum_points_today() {
		return sum_points_today;
	}

	public void setSum_points_today(int sum_points_today) {
		this.sum_points_today = sum_points_today;
	}

	public int getPoints_other_min() {
		return points_other_min;
	}

	public void setPoints_other_min(int points_other_min) {
		this.points_other_min = points_other_min;
	}

	public int getTop_up_barter_volume() {
		return top_up_barter_volume;
	}

	public void setTop_up_barter_volume(int top_up_barter_volume) {
		this.top_up_barter_volume = top_up_barter_volume;
	}

	public int getOffline_top_up_barter_volume() {
		return offline_top_up_barter_volume;
	}

	public void setOffline_top_up_barter_volume(int offline_top_up_barter_volume) {
		this.offline_top_up_barter_volume = offline_top_up_barter_volume;
	}

	public int getTop_up_barter_volume_minimum() {
		return top_up_barter_volume_minimum;
	}

	public void setTop_up_barter_volume_minimum(int top_up_barter_volume_minimum) {
		this.top_up_barter_volume_minimum = top_up_barter_volume_minimum;
	}

	/**
	 * 创建时间
	 */
	private Date data_time;
	private String menber_spending;
	

	public String getMenber_spending() {
		return menber_spending;
	}

	public void setMenber_spending(String menber_spending) {
		this.menber_spending = menber_spending;
	}

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 合格会员
	 * 
	 * @return qualified_menber
	 */
	public String getQualified_menber() {
		return qualified_menber;
	}
	
	/**
	 * 会员id跳段
	 * 
	 * @return menber_id
	 */
	public String getMenber_id() {
		return menber_id;
	}
	
	/**
	 * 会员注册赠送开始时间
	 * 
	 * @return start_time
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * 会员注册赠送结束时间
	 * 
	 * @return end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * 会员注册赠送易物券
	 * 
	 * @return give_barter
	 */
	public String getGive_barter() {
		return give_barter;
	}
	
	/**
	 * 充值易物券兑换比例
	 * 
	 * @return barter_coupo_rate
	 */
	public String getBarter_coupo_rate() {
		return barter_coupo_rate;
	}
	
	/**
	 * 直推会员总数量限制
	 * 
	 * @return membership_amount_limit
	 */
	public String getMembership_amount_limit() {
		return membership_amount_limit;
	}
	
	/**
	 * 合格会员数量限制
	 * 
	 * @return members_always_limit
	 */
	public String getMembers_always_limit() {
		return members_always_limit;
	}
	
	/**
	 * 企业号合格会员数量限制
	 * 
	 * @return enterprise_always_limit
	 */
	public String getEnterprise_always_limit() {
		return enterprise_always_limit;
	}
	
	/**
	 * 会员提成比例设置一
	 * 
	 * @return member_comm_percentage_one
	 */
	public String getMember_comm_percentage_one() {
		return member_comm_percentage_one;
	}
	
	/**
	 * 会员提成比例设置一
	 * 
	 * @return member_comm_percentage_ones
	 */
	public String getMember_comm_percentage_ones() {
		return member_comm_percentage_ones;
	}
	
	/**
	 * 会员提成比例设置二
	 * 
	 * @return member_comm_percentage_two
	 */
	public String getMember_comm_percentage_two() {
		return member_comm_percentage_two;
	}
	
	/**
	 * 会员提成比例设置二
	 * 
	 * @return member_comm_percentage_twos
	 */
	public String getMember_comm_percentage_twos() {
		return member_comm_percentage_twos;
	}
	
	/**
	 * 会员提成比例设置三
	 * 
	 * @return member_comm_percentage_three
	 */
	public String getMember_comm_percentage_three() {
		return member_comm_percentage_three;
	}
	
	/**
	 * 会员提成比例设置三
	 * 
	 * @return member_comm_percentage_threes
	 */
	public String getMember_comm_percentage_threes() {
		return member_comm_percentage_threes;
	}
	
	/**
	 * 会员返佣模式
	 * 
	 * @return member_commission_model
	 */
	public String getMember_commission_model() {
		return member_commission_model;
	}
	
	/**
	 * 创建时间
	 * 
	 * @return data_time
	 */
	public Date getData_time() {
		return data_time;
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
	 * 合格会员
	 * 
	 * @param qualified_menber
	 */
	public void setQualified_menber(String qualified_menber) {
		this.qualified_menber = qualified_menber;
	}
	
	/**
	 * 会员id跳段
	 * 
	 * @param menber_id
	 */
	public void setMenber_id(String menber_id) {
		this.menber_id = menber_id;
	}
	
	/**
	 * 会员注册赠送开始时间
	 * 
	 * @param start_time
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * 会员注册赠送结束时间
	 * 
	 * @param end_time
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * 会员注册赠送易物券
	 * 
	 * @param give_barter
	 */
	public void setGive_barter(String give_barter) {
		this.give_barter = give_barter;
	}
	
	/**
	 * 充值易物券兑换比例
	 * 
	 * @param barter_coupo_rate
	 */
	public void setBarter_coupo_rate(String barter_coupo_rate) {
		this.barter_coupo_rate = barter_coupo_rate;
	}
	
	/**
	 * 直推会员总数量限制
	 * 
	 * @param membership_amount_limit
	 */
	public void setMembership_amount_limit(String membership_amount_limit) {
		this.membership_amount_limit = membership_amount_limit;
	}
	
	/**
	 * 合格会员数量限制
	 * 
	 * @param members_always_limit
	 */
	public void setMembers_always_limit(String members_always_limit) {
		this.members_always_limit = members_always_limit;
	}
	
	/**
	 * 企业号合格会员数量限制
	 * 
	 * @param enterprise_always_limit
	 */
	public void setEnterprise_always_limit(String enterprise_always_limit) {
		this.enterprise_always_limit = enterprise_always_limit;
	}
	
	/**
	 * 会员提成比例设置一
	 * 
	 * @param member_comm_percentage_one
	 */
	public void setMember_comm_percentage_one(String member_comm_percentage_one) {
		this.member_comm_percentage_one = member_comm_percentage_one;
	}
	
	/**
	 * 会员提成比例设置一
	 * 
	 * @param member_comm_percentage_ones
	 */
	public void setMember_comm_percentage_ones(String member_comm_percentage_ones) {
		this.member_comm_percentage_ones = member_comm_percentage_ones;
	}
	
	/**
	 * 会员提成比例设置二
	 * 
	 * @param member_comm_percentage_two
	 */
	public void setMember_comm_percentage_two(String member_comm_percentage_two) {
		this.member_comm_percentage_two = member_comm_percentage_two;
	}
	
	/**
	 * 会员提成比例设置二
	 * 
	 * @param member_comm_percentage_twos
	 */
	public void setMember_comm_percentage_twos(String member_comm_percentage_twos) {
		this.member_comm_percentage_twos = member_comm_percentage_twos;
	}
	
	/**
	 * 会员提成比例设置三
	 * 
	 * @param member_comm_percentage_three
	 */
	public void setMember_comm_percentage_three(String member_comm_percentage_three) {
		this.member_comm_percentage_three = member_comm_percentage_three;
	}
	
	/**
	 * 会员提成比例设置三
	 * 
	 * @param member_comm_percentage_threes
	 */
	public void setMember_comm_percentage_threes(String member_comm_percentage_threes) {
		this.member_comm_percentage_threes = member_comm_percentage_threes;
	}
	
	/**
	 * 会员返佣模式
	 * 
	 * @param member_commission_model
	 */
	public void setMember_commission_model(String member_commission_model) {
		this.member_commission_model = member_commission_model;
	}
	
	/**
	 * 创建时间
	 * 
	 * @param data_time
	 */
	public void setData_time(Date data_time) {
		this.data_time = data_time;
	}
	

}