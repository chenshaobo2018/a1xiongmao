package com.zjc.system.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_member_other[zjc_member_other]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-01-30 09:57:56
 */
public class ZjcMemberOtherPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 消费易物券返回时间点
	 */
	private Date return_time;
	
	/**
	 * 可转易物券比例
	 */
	private String convertible_barter;
	
	/**
	 * 消费易物券比例
	 */
	private String consumption_coupons;
	
	/**
	 * 小额易物券限制
	 */
	private String small_barter_coupon_limits;
	
	/**
	 * 平台推荐码
	 */
	private String platform_recommended_code;
	
	/**
	 * 商家商品数量限制
	 */
	private String merchants_quantity_limit;
	
	/**
	 * 易物券转账最小值
	 */
	private String transfer_minimum;
	
	/**
	 * 易物券转账最大值
	 */
	private String maximum_transfer;
	
	/**
	 * 易物担保未确认失效天数
	 */
	private String invalid_number;
	
	/**
	 * 易物担保手续费
	 */
	private String barter_guarantee_fee;
	
	/**
	 * 易物券转账手续费
	 */
	private String barter_voucher_transfer_fees;
	
	/**
	 * 手续费最大值
	 */
	private String maximum_fee;
	
	/**
	 * 普通商家返利延迟天数
	 */
	private String rebate_days_delay;
	
	/**
	 * 特殊商家返利延迟天数
	 */
	private String special_rebate_delay_days;
	
	/**
	 * 订单超时收货天数
	 */
	private String order_receiving_days_overtime;
	
	/**
	 * 添加时间
	 */
	private Date date_time;
	
	/**
	 * signstep
	 */
	private Integer signstep;
	
	/**
	 * minoutpoint
	 */
	private Integer minoutpoint;
	
	/**
	 * 商家一滴酒提成比例
	 */
	private String shop_drop_commission_rate;
	
	/**
	 * 商家一滴酒提成开始日期（多少天后开始提成）
	 */
	private String shop_drop_commission_limit_days;
	
	/**
	 * 会员一滴酒提成比例
	 */
	private String user_drop_commission_rate;
	
	/**
	 * 会议一滴酒提成开始天数（表示多少天后开始提成）
	 */
	private String user_drop_commission_limit_days;
	

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 消费易物券返回时间点
	 * 
	 * @return return_time
	 */
	public Date getReturn_time() {
		return return_time;
	}
	
	/**
	 * 可转易物券比例
	 * 
	 * @return convertible_barter
	 */
	public String getConvertible_barter() {
		return convertible_barter;
	}
	
	/**
	 * 消费易物券比例
	 * 
	 * @return consumption_coupons
	 */
	public String getConsumption_coupons() {
		return consumption_coupons;
	}
	
	/**
	 * 小额易物券限制
	 * 
	 * @return small_barter_coupon_limits
	 */
	public String getSmall_barter_coupon_limits() {
		return small_barter_coupon_limits;
	}
	
	/**
	 * 平台推荐码
	 * 
	 * @return platform_recommended_code
	 */
	public String getPlatform_recommended_code() {
		return platform_recommended_code;
	}
	
	/**
	 * 商家商品数量限制
	 * 
	 * @return merchants_quantity_limit
	 */
	public String getMerchants_quantity_limit() {
		return merchants_quantity_limit;
	}
	
	/**
	 * 易物券转账最小值
	 * 
	 * @return transfer_minimum
	 */
	public String getTransfer_minimum() {
		return transfer_minimum;
	}
	
	/**
	 * 易物券转账最大值
	 * 
	 * @return maximum_transfer
	 */
	public String getMaximum_transfer() {
		return maximum_transfer;
	}
	
	/**
	 * 易物担保未确认失效天数
	 * 
	 * @return invalid_number
	 */
	public String getInvalid_number() {
		return invalid_number;
	}
	
	/**
	 * 易物担保手续费
	 * 
	 * @return barter_guarantee_fee
	 */
	public String getBarter_guarantee_fee() {
		return barter_guarantee_fee;
	}
	
	/**
	 * 易物券转账手续费
	 * 
	 * @return barter_voucher_transfer_fees
	 */
	public String getBarter_voucher_transfer_fees() {
		return barter_voucher_transfer_fees;
	}
	
	/**
	 * 手续费最大值
	 * 
	 * @return maximum_fee
	 */
	public String getMaximum_fee() {
		return maximum_fee;
	}
	
	/**
	 * 普通商家返利延迟天数
	 * 
	 * @return rebate_days_delay
	 */
	public String getRebate_days_delay() {
		return rebate_days_delay;
	}
	
	/**
	 * 特殊商家返利延迟天数
	 * 
	 * @return special_rebate_delay_days
	 */
	public String getSpecial_rebate_delay_days() {
		return special_rebate_delay_days;
	}
	
	/**
	 * 订单超时收货天数
	 * 
	 * @return order_receiving_days_overtime
	 */
	public String getOrder_receiving_days_overtime() {
		return order_receiving_days_overtime;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return date_time
	 */
	public Date getDate_time() {
		return date_time;
	}
	
	/**
	 * signstep
	 * 
	 * @return signstep
	 */
	public Integer getSignstep() {
		return signstep;
	}
	
	/**
	 * minoutpoint
	 * 
	 * @return minoutpoint
	 */
	public Integer getMinoutpoint() {
		return minoutpoint;
	}
	
	/**
	 * 商家一滴酒提成比例
	 * 
	 * @return shop_drop_commission_rate
	 */
	public String getShop_drop_commission_rate() {
		return shop_drop_commission_rate;
	}
	
	/**
	 * 商家一滴酒提成开始日期（多少天后开始提成）
	 * 
	 * @return shop_drop_commission_limit_days
	 */
	public String getShop_drop_commission_limit_days() {
		return shop_drop_commission_limit_days;
	}
	
	/**
	 * 会员一滴酒提成比例
	 * 
	 * @return user_drop_commission_rate
	 */
	public String getUser_drop_commission_rate() {
		return user_drop_commission_rate;
	}
	
	/**
	 * 会议一滴酒提成开始天数（表示多少天后开始提成）
	 * 
	 * @return user_drop_commission_limit_days
	 */
	public String getUser_drop_commission_limit_days() {
		return user_drop_commission_limit_days;
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
	 * 消费易物券返回时间点
	 * 
	 * @param return_time
	 */
	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}
	
	/**
	 * 可转易物券比例
	 * 
	 * @param convertible_barter
	 */
	public void setConvertible_barter(String convertible_barter) {
		this.convertible_barter = convertible_barter;
	}
	
	/**
	 * 消费易物券比例
	 * 
	 * @param consumption_coupons
	 */
	public void setConsumption_coupons(String consumption_coupons) {
		this.consumption_coupons = consumption_coupons;
	}
	
	/**
	 * 小额易物券限制
	 * 
	 * @param small_barter_coupon_limits
	 */
	public void setSmall_barter_coupon_limits(String small_barter_coupon_limits) {
		this.small_barter_coupon_limits = small_barter_coupon_limits;
	}
	
	/**
	 * 平台推荐码
	 * 
	 * @param platform_recommended_code
	 */
	public void setPlatform_recommended_code(String platform_recommended_code) {
		this.platform_recommended_code = platform_recommended_code;
	}
	
	/**
	 * 商家商品数量限制
	 * 
	 * @param merchants_quantity_limit
	 */
	public void setMerchants_quantity_limit(String merchants_quantity_limit) {
		this.merchants_quantity_limit = merchants_quantity_limit;
	}
	
	/**
	 * 易物券转账最小值
	 * 
	 * @param transfer_minimum
	 */
	public void setTransfer_minimum(String transfer_minimum) {
		this.transfer_minimum = transfer_minimum;
	}
	
	/**
	 * 易物券转账最大值
	 * 
	 * @param maximum_transfer
	 */
	public void setMaximum_transfer(String maximum_transfer) {
		this.maximum_transfer = maximum_transfer;
	}
	
	/**
	 * 易物担保未确认失效天数
	 * 
	 * @param invalid_number
	 */
	public void setInvalid_number(String invalid_number) {
		this.invalid_number = invalid_number;
	}
	
	/**
	 * 易物担保手续费
	 * 
	 * @param barter_guarantee_fee
	 */
	public void setBarter_guarantee_fee(String barter_guarantee_fee) {
		this.barter_guarantee_fee = barter_guarantee_fee;
	}
	
	/**
	 * 易物券转账手续费
	 * 
	 * @param barter_voucher_transfer_fees
	 */
	public void setBarter_voucher_transfer_fees(String barter_voucher_transfer_fees) {
		this.barter_voucher_transfer_fees = barter_voucher_transfer_fees;
	}
	
	/**
	 * 手续费最大值
	 * 
	 * @param maximum_fee
	 */
	public void setMaximum_fee(String maximum_fee) {
		this.maximum_fee = maximum_fee;
	}
	
	/**
	 * 普通商家返利延迟天数
	 * 
	 * @param rebate_days_delay
	 */
	public void setRebate_days_delay(String rebate_days_delay) {
		this.rebate_days_delay = rebate_days_delay;
	}
	
	/**
	 * 特殊商家返利延迟天数
	 * 
	 * @param special_rebate_delay_days
	 */
	public void setSpecial_rebate_delay_days(String special_rebate_delay_days) {
		this.special_rebate_delay_days = special_rebate_delay_days;
	}
	
	/**
	 * 订单超时收货天数
	 * 
	 * @param order_receiving_days_overtime
	 */
	public void setOrder_receiving_days_overtime(String order_receiving_days_overtime) {
		this.order_receiving_days_overtime = order_receiving_days_overtime;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param date_time
	 */
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
	/**
	 * signstep
	 * 
	 * @param signstep
	 */
	public void setSignstep(Integer signstep) {
		this.signstep = signstep;
	}
	
	/**
	 * minoutpoint
	 * 
	 * @param minoutpoint
	 */
	public void setMinoutpoint(Integer minoutpoint) {
		this.minoutpoint = minoutpoint;
	}
	
	/**
	 * 商家一滴酒提成比例
	 * 
	 * @param shop_drop_commission_rate
	 */
	public void setShop_drop_commission_rate(String shop_drop_commission_rate) {
		this.shop_drop_commission_rate = shop_drop_commission_rate;
	}
	
	/**
	 * 商家一滴酒提成开始日期（多少天后开始提成）
	 * 
	 * @param shop_drop_commission_limit_days
	 */
	public void setShop_drop_commission_limit_days(String shop_drop_commission_limit_days) {
		this.shop_drop_commission_limit_days = shop_drop_commission_limit_days;
	}
	
	/**
	 * 会员一滴酒提成比例
	 * 
	 * @param user_drop_commission_rate
	 */
	public void setUser_drop_commission_rate(String user_drop_commission_rate) {
		this.user_drop_commission_rate = user_drop_commission_rate;
	}
	
	/**
	 * 会议一滴酒提成开始天数（表示多少天后开始提成）
	 * 
	 * @param user_drop_commission_limit_days
	 */
	public void setUser_drop_commission_limit_days(String user_drop_commission_limit_days) {
		this.user_drop_commission_limit_days = user_drop_commission_limit_days;
	}
	

}