/**
 * 
 */
package com.zjc.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import aos.framework.core.typewrap.PO;

/**
 * @author Administrator
 *
 */
public class ZjcOrderVO2 extends PO{
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private Integer order_id;
	
	/**
	 * 订单编号
	 */
	private String order_sn;
	
	/**
	 * 会员名称
	 */
	private String user_name;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 店铺ID
	 */
	private Integer store_id;
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
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
	 * 收货人
	 */
	private String consignee;
	
	/**
	 * 国家
	 */
	private Integer country;
	
	/**
	 * 省份
	 */
	private Integer province;
	
	/**
	 * 城市
	 */
	private Integer city;
	
	/**
	 * 县区
	 */
	private Integer district;
	
	/**
	 * 乡镇
	 */
	private Integer twon;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 邮政编码
	 */
	private String zipcode;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 邮件
	 */
	private String email;
	
	/**
	 * 物流code
	 */
	private String shipping_code;
	
	/**
	 * 物流名称
	 */
	private String shipping_name;
	
	/**
	 * 支付code rate 比例支付 equal  等比例支付 cash 现金支付  wxpay 微信支付  alipay 支付宝支付
	 */
	private String pay_code;
	
	/**
	 * 支付方式名称
	 */
	private String pay_name;
	
	/**
	 * 发票抬头
	 */
	private String invoice_title;
	
	/**
	 * 商品总价
	 */
	private BigDecimal goods_price;
	
	/**
	 * 邮费
	 */
	private BigDecimal shipping_price;
	
	/**
	 * 使用余额
	 */
	private BigDecimal user_money;
	
	/**
	 * 优惠券抵扣
	 */
	private BigDecimal coupon_price;
	
	/**
	 * 使用积分
	 */
	private Integer integral;
	
	/**
	 * 使用积分抵多少钱
	 */
	private BigDecimal integral_money;
	
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
	private Date add_time;
	
	/**
	 * 最后新发货时间
	 */
	private Date shipping_time;
	
	/**
	 * 收货确认时间
	 */
	private Date confirm_time;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	
	/**
	 * 活动id
	 */
	private Integer order_prom_id;
	
	/**
	 * 活动优惠金额
	 */
	private BigDecimal order_prom_amount;
	
	/**
	 * 价格调整
	 */
	private BigDecimal discount;
	
	/**
	 * 用户备注
	 */
	private String user_note;
	
	/**
	 * 管理员备注
	 */
	private String admin_note;
	
	/**
	 * 父单单号
	 */
	private String parent_sn;
	
	/**
	 * 是否已分成0未分成1已分成
	 */
	private Integer is_distribut;
	
	/**
	 * 1-商城普通商品，2-积分充值
	 */
	private Integer order_type;
	
	/**
	 * 支付编号
	 */
	private String pay_sn;
	
	/**
	 * trade_no
	 */
	private String trade_no;
	
	/**
	 * 购买积分
	 */
	private String buy_points;
	
	/**
	 * 是否已删除0未删除1已删除
	 */
	private Integer is_del;
	/**
	 * 物流单号
	 */
	private String tracking_no;
	/**
	 * 收货人详细省市区信息
	 */
	private String area_info;
	
	/**
	 * 现金
	 */
	private BigDecimal cash;
	
	/**
	 * 易物券
	 */
	private BigDecimal barter_coupons;
	
	/**
	 * 易物劵支付状态
	 */
	private Integer points_pay_status;

	
	private List<ZjcOrderGoodsPO> zjcOrderGoodsPO;
	
	private List<ZjcOrderActionVO2> zjcOrderActionPOs;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public BigInteger getUser_id() {
		return user_id;
	}

	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getShipping_status() {
		return shipping_status;
	}

	public void setShipping_status(String shipping_status) {
		this.shipping_status = shipping_status;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getTwon() {
		return twon;
	}

	public void setTwon(Integer twon) {
		this.twon = twon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShipping_code() {
		return shipping_code;
	}

	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}

	public String getShipping_name() {
		return shipping_name;
	}

	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}

	public String getPay_code() {
		return pay_code;
	}

	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}

	public String getPay_name() {
		return pay_name;
	}

	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}

	public String getInvoice_title() {
		return invoice_title;
	}

	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	public BigDecimal getShipping_price() {
		return shipping_price;
	}

	public void setShipping_price(BigDecimal shipping_price) {
		this.shipping_price = shipping_price;
	}

	public BigDecimal getUser_money() {
		return user_money;
	}

	public void setUser_money(BigDecimal user_money) {
		this.user_money = user_money;
	}

	public BigDecimal getCoupon_price() {
		return coupon_price;
	}

	public void setCoupon_price(BigDecimal coupon_price) {
		this.coupon_price = coupon_price;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public BigDecimal getIntegral_money() {
		return integral_money;
	}

	public void setIntegral_money(BigDecimal integral_money) {
		this.integral_money = integral_money;
	}

	public BigDecimal getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Date getShipping_time() {
		return shipping_time;
	}

	public void setShipping_time(Date shipping_time) {
		this.shipping_time = shipping_time;
	}

	public Date getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Integer getOrder_prom_id() {
		return order_prom_id;
	}

	public void setOrder_prom_id(Integer order_prom_id) {
		this.order_prom_id = order_prom_id;
	}

	public BigDecimal getOrder_prom_amount() {
		return order_prom_amount;
	}

	public void setOrder_prom_amount(BigDecimal order_prom_amount) {
		this.order_prom_amount = order_prom_amount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getUser_note() {
		return user_note;
	}

	public void setUser_note(String user_note) {
		this.user_note = user_note;
	}

	public String getAdmin_note() {
		return admin_note;
	}

	public void setAdmin_note(String admin_note) {
		this.admin_note = admin_note;
	}

	public String getParent_sn() {
		return parent_sn;
	}

	public void setParent_sn(String parent_sn) {
		this.parent_sn = parent_sn;
	}

	public Integer getIs_distribut() {
		return is_distribut;
	}

	public void setIs_distribut(Integer is_distribut) {
		this.is_distribut = is_distribut;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	public String getPay_sn() {
		return pay_sn;
	}

	public void setPay_sn(String pay_sn) {
		this.pay_sn = pay_sn;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getBuy_points() {
		return buy_points;
	}

	public void setBuy_points(String buy_points) {
		this.buy_points = buy_points;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getTracking_no() {
		return tracking_no;
	}

	public void setTracking_no(String tracking_no) {
		this.tracking_no = tracking_no;
	}

	public String getArea_info() {
		return area_info;
	}

	public void setArea_info(String area_info) {
		this.area_info = area_info;
	}

	public List<ZjcOrderGoodsPO> getZjcOrderGoodsPO() {
		return zjcOrderGoodsPO;
	}

	public void setZjcOrderGoodsPO(List<ZjcOrderGoodsPO> zjcOrderGoodsPO) {
		this.zjcOrderGoodsPO = zjcOrderGoodsPO;
	}

	public List<ZjcOrderActionVO2> getZjcOrderActionPOs() {
		return zjcOrderActionPOs;
	}

	public void setZjcOrderActionPOs(List<ZjcOrderActionVO2> zjcOrderActionPOs) {
		this.zjcOrderActionPOs = zjcOrderActionPOs;
	}
	
	/**
	 * 现金
	 * 
	 * @return
	 */
	public BigDecimal getCash() {
		return cash;
	}

	/**
	 * 现金
	 * 
	 * @param cash
	 */
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	/**
	 * 易物券
	 * 
	 * @return
	 */
	public BigDecimal getBarter_coupons() {
		return barter_coupons;
	}

	/**
	 * 易物券
	 * 
	 * @param barter_coupons
	 */
	public void setBarter_coupons(BigDecimal barter_coupons) {
		this.barter_coupons = barter_coupons;
	}

	public Integer getPoints_pay_status() {
		return points_pay_status;
	}

	public void setPoints_pay_status(Integer points_pay_status) {
		this.points_pay_status = points_pay_status;
	}

	public BigDecimal getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}

}
