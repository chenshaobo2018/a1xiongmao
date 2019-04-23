/**
 * 
 */
package com.api.order.dao.po;

import java.math.BigDecimal;

/**订单临时实体
 * @author Administrator
 *
 */
public class OrderVO {
	private Integer postFee;// 物流费
	private Integer couponFee;// 优惠券
	private Integer balance;// 使用用户余额
	private Integer pointsFee;// 积分支付
	private BigDecimal payables;// 应付金额
	private BigDecimal goodsFee;// 商品价格
	private Integer order_prom_id;// 订单优惠活动id
	private Integer order_prom_amount;// 订单优惠活动优惠了多少钱
	private BigDecimal goods_price;
	private BigDecimal total_amount;
	private BigDecimal order_amount;
	private BigDecimal total_goods_num;
	private Integer status;
	private Integer store_id;
	private String msg;
    private Integer order_id;
    private BigDecimal cash;//现金
    private BigDecimal barter_coupons;//易物券
	
	
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPostFee() {
		return postFee;
	}
	public void setPostFee(Integer postFee) {
		this.postFee = postFee;
	}
	public Integer getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getPointsFee() {
		return pointsFee;
	}
	public void setPointsFee(Integer pointsFee) {
		this.pointsFee = pointsFee;
	}
	public BigDecimal getPayables() {
		return payables;
	}
	public void setPayables(BigDecimal payables) {
		this.payables = payables;
	}
	public BigDecimal getGoodsFee() {
		return goodsFee;
	}
	public void setGoodsFee(BigDecimal goodsFee) {
		this.goodsFee = goodsFee;
	}
	public Integer getOrder_prom_id() {
		return order_prom_id;
	}
	public void setOrder_prom_id(Integer order_prom_id) {
		this.order_prom_id = order_prom_id;
	}
	public Integer getOrder_prom_amount() {
		return order_prom_amount;
	}
	public void setOrder_prom_amount(Integer order_prom_amount) {
		this.order_prom_amount = order_prom_amount;
	}
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public BigDecimal getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
	}
	public BigDecimal getTotal_goods_num() {
		return total_goods_num;
	}
	public void setTotal_goods_num(BigDecimal total_goods_num) {
		this.total_goods_num = total_goods_num;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	public BigDecimal getBarter_coupons() {
		return barter_coupons;
	}
	public void setBarter_coupons(BigDecimal barter_coupons) {
		this.barter_coupons = barter_coupons;
	}
	
	
	
	
}
