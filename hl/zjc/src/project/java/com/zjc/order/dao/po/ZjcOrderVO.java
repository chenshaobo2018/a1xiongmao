package com.zjc.order.dao.po;

import java.math.BigDecimal;

import aos.framework.core.typewrap.PO;


/**
 * 订单导出数据展示
 * 
 * @author wugaoming
 *
 */
public class ZjcOrderVO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private Integer order_id;
	
	/**
	 * 订单store_name
	 */
	private String store_name;
	
	/**
	 * 订单user_id
	 */
	private Integer user_id;

	/**
	 * 订单编号
	 */
	private String order_sn;
	
	/**
	 * 收货人
	 */
	private String consignee;

	/**
	 * 订单状态(0-待确认，1-已确认，2-已收货，3-已取消，4-已完成(已评价)，5-已作废)
	 */
	private String order_status;
	
	/**
	 * 发货状态(0-未发货，1-已发货)
	 */
	private String shipping_status;
	
	/**
	 * 支付状态(0-未支付，1-已支付)
	 */
	private String pay_status;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 支付code rate 比例支付 equal  等比例支付 cash 现金支付  wxpay 微信支付  alipay 支付宝支付
	 */
	private String pay_code;
	
	/**
	 * 商品总价
	 */
	private BigDecimal goods_price;
	
	/**
	 * 应付款金额
	 */
	private BigDecimal order_amount;
	
	/**
	 * 订单总价
	 */
	private BigDecimal total_amount;
	
	/**
	 * 下单时间
	 */
	private String add_time;
	
	/**
	 * 最后新发货时间
	 */
	private String shipping_time;
	
	/**
	 * 收货确认时间
	 */
	private String confirm_time;
	
	/**
	 * 商品名称
	 */
	private String goods_name;
	
	/**
	 * 商品数量
	 */
	private int goods_num;
	
	
	/**
	 * 订单id
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @return order_sn
	 */
	public String getOrder_sn() {
		return order_sn;
	}
	
	/**
	 * 订单状态(0-待确认，1-已确认，2-已收货，3-已取消，4-已完成(已评价)，5-已作废)
	 * 
	 * @return order_status
	 */
	public String getOrder_status() {
		return order_status;
	}
	
	/**
	 * 发货状态
	 * 
	 * @return shipping_status
	 */
	public String getShipping_status() {
		return shipping_status;
	}
	
	/**
	 * 支付状态
	 * 
	 * @return pay_status
	 */
	public String getPay_status() {
		return pay_status;
	}
	
	/**
	 * 地址
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 手机
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 支付code rate 比例支付 equal  等比例支付 cash 现金支付  wxpay 微信支付  alipay 支付宝支付
	 * 
	 * @return pay_code
	 */
	public String getPay_code() {
		return pay_code;
	}
	
	/**
	 * 商品总价
	 * 
	 * @return goods_price
	 */
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	
	/**
	 * 应付款金额
	 * 
	 * @return order_amount
	 */
	public BigDecimal getOrder_amount() {
		return order_amount;
	}
	
	/**
	 * 订单总价
	 * 
	 * @return total_amount
	 */
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	
	/**
	 * 下单时间
	 * 
	 * @return add_time
	 */
	public String getAdd_time() {
		return add_time;
	}
	
	/**
	 * 最后新发货时间
	 * 
	 * @return shipping_time
	 */
	public String getShipping_time() {
		return shipping_time;
	}
	
	/**
	 * 收货确认时间
	 * 
	 * @return confirm_time
	 */
	public String getConfirm_time() {
		return confirm_time;
	}

	/**
	 * 订单id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @param order_sn
	 */
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	
	/**
	 * 订单状态(0-待确认，1-已确认，2-已收货，3-已取消，4-已完成(已评价)，5-已作废)
	 * 
	 * @param order_status
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	/**
	 * 发货状态
	 * 
	 * @param shipping_status
	 */
	public void setShipping_status(String shipping_status) {
		this.shipping_status = shipping_status;
	}
	
	/**
	 * 支付状态
	 * 
	 * @param pay_status
	 */
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	
	/**
	 * 地址
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 手机
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 支付code rate 比例支付 equal  等比例支付 cash 现金支付  wxpay 微信支付  alipay 支付宝支付
	 * 
	 * @param pay_code
	 */
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	/**
	 * 商品总价
	 * 
	 * @param goods_price
	 */
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	
	/**
	 * 应付款金额
	 * 
	 * @param order_amount
	 */
	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
	}
	
	/**
	 * 订单总价
	 * 
	 * @param total_amount
	 */
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	
	/**
	 * 下单时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 最后新发货时间
	 * 
	 * @param shipping_time
	 */
	public void setShipping_time(String shipping_time) {
		this.shipping_time = shipping_time;
	}
	
	/**
	 * 收货确认时间
	 * 
	 * @param confirm_time
	 */
	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	
	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
}