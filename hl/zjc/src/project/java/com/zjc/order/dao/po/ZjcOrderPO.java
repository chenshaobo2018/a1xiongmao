package com.zjc.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_order[zjc_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-24 09:08:52
 */
public class ZjcOrderPO extends PO {

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
	private Integer order_status;
	
	/**
	 * 发货状态(0-未发货，1-已发货)
	 */
	private Integer shipping_status;
	
	/**
	 * 支付状态(0-未支付，1-已支付)
	 */
	private Integer pay_status;
	
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
	
	private String original_img;
	private String cost_price;
	private String market_price;
	
  private String pin_order_id;
	
	public String getPin_order_id() {
		return pin_order_id;
	}

	public void setPin_order_id(String pin_order_id) {
		this.pin_order_id = pin_order_id;
	}
	


	/**
	 * 商品名称
	 */
	private String goods_name;
	
	/**
	 * 是否拼团订单
	 */
	private Integer is_shop_group;
	
	/**
	 * 催单次数
	 */
	private Integer reminder;
	
	/**
	 * 上次催单时间
	 */
	private Date remindertime;
	
	/**
	 * 退单状态：0发起退单1退单中2退单成功3退单失败
	 */
	private Integer single_back;
	
	/**
	 * 退单时间
	 */
	private Date single_back_time;
	
	/**
	 * 确认退单时间
	 */
	private Date confirm_single_back_time;
	
	
	/**
	 * 催单次数
	 * 
	 * @return reminder
	 */
	public Integer getReminder() {
		return reminder;
	}
	
	/**
	 * 上次催单时间
	 * 
	 * @return remindertime
	 */
	public Date getRemindertime() {
		return remindertime;
	}
	
	/**
	 * 退单状态：0发起退单1退单中2退单成功3退单失败
	 * 
	 * @return single_back
	 */
	public Integer getSingle_back() {
		return single_back;
	}
	
	/**
	 * 退单时间
	 * 
	 * @return single_back_time
	 */
	public Date getSingle_back_time() {
		return single_back_time;
	}
	
	/**
	 * 确认退单时间
	 * 
	 * @return confirm_single_back_time
	 */
	public Date getConfirm_single_back_time() {
		return confirm_single_back_time;
	}
	

	/**
	 * 催单次数
	 * 
	 * @param reminder
	 */
	public void setReminder(Integer reminder) {
		this.reminder = reminder;
	}
	
	/**
	 * 上次催单时间
	 * 
	 * @param remindertime
	 */
	public void setRemindertime(Date remindertime) {
		this.remindertime = remindertime;
	}
	
	/**
	 * 退单状态：0发起退单1退单中2退单成功3退单失败
	 * 
	 * @param single_back
	 */
	public void setSingle_back(Integer single_back) {
		this.single_back = single_back;
	}
	
	/**
	 * 退单时间
	 * 
	 * @param single_back_time
	 */
	public void setSingle_back_time(Date single_back_time) {
		this.single_back_time = single_back_time;
	}
	
	/**
	 * 确认退单时间
	 * 
	 * @param confirm_single_back_time
	 */
	public void setConfirm_single_back_time(Date confirm_single_back_time) {
		this.confirm_single_back_time = confirm_single_back_time;
	}
	

	/**
	 * 是否拼团订单
	 */
	public Integer getIs_shop_group() {
		return is_shop_group;
	}

	public void setIs_shop_group(Integer is_shop_group) {
		this.is_shop_group = is_shop_group;
	}


	/**
	 * 商品数量
	 */
	private int goods_num;
	
	
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

	public String getOriginal_img() {
		return original_img;
	}

	public void setOriginal_img(String original_img) {
		this.original_img = original_img;
	}

	public String getCost_price() {
		return cost_price;
	}

	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	
	/**
	 * 是否已分成0未分成1已分成
	 */
	private Integer is_distribut;
	
	/**
	 * 1-商城普通商品，2-积分充值
	 */
	private Integer order_type;
	
	private Integer mj;
	
	public Integer getMj() {
		return mj;
	}

	public void setMj(Integer mj) {
		this.mj = mj;
	}

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
	
	private String v_oid;
	
	
	
	public String getV_oid() {
		return v_oid;
	}

	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}



	/**
	 * 现金
	 */
	private BigDecimal cash;
	
	/**
	 * 易物券
	 */
	private BigDecimal barter_coupons;
	
	/**
	 * 订单委托收取的现金是否已转
	 */
	private Integer has_transfer;
	
	/**
	 * 易物券支付状态;0:未支付;1:已支付
	 */
	private Integer points_pay_status;
	
	private Date transfer_time; 
	private Integer transfer_type;
	
	public Date getTransfer_time() {
		return transfer_time;
	}

	public void setTransfer_time(Date transfer_time) {
		this.transfer_time = transfer_time;
	}

	public Integer getTransfer_type() {
		return transfer_type;
	}

	public void setTransfer_type(Integer transfer_type) {
		this.transfer_type = transfer_type;
	}


	/**
	 *订单商品关系表 
	 */
	private List<ZjcOrderGoodsPO> zjcOrderGoodsPO;
	
	private List<ZjcOrderActionVO2> zjcOrderActionPOs;
	
	/**
	 * 商家联系电话
	 */
	private String contacts_phone;
	
	public String getContacts_phone() {
		return contacts_phone;
	}

	public void setContacts_phone(String contacts_phone) {
		this.contacts_phone = contacts_phone;
	}

	public List<ZjcOrderGoodsPO> getZjcOrderGoodsPO() {
		return zjcOrderGoodsPO;
	}

	public void setZjcOrderGoodsPO(List<ZjcOrderGoodsPO> zjcOrderGoodsPO) {
		this.zjcOrderGoodsPO = zjcOrderGoodsPO;
	}

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
	 * 会员名称
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
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
	 * 店铺ID
	 * 
	 * @return store_id
	 */
	public Integer getStore_id() {
		return store_id;
	}
	
	/**
	 * 店铺名称
	 * 
	 * @return store_name
	 */
	public String getStore_name() {
		return store_name;
	}
	
	/**
	 * 订单状态(0-待确认，1-已确认，2-已收货，3-已取消，4-已完成(已评价)，5-已作废)
	 * 
	 * @return order_status
	 */
	public Integer getOrder_status() {
		return order_status;
	}
	
	/**
	 * 发货状态
	 * 
	 * @return shipping_status
	 */
	public Integer getShipping_status() {
		return shipping_status;
	}
	
	/**
	 * 支付状态
	 * 
	 * @return pay_status
	 */
	public Integer getPay_status() {
		return pay_status;
	}
	
	/**
	 * 收货人
	 * 
	 * @return consignee
	 */
	public String getConsignee() {
		return consignee;
	}
	
	/**
	 * 国家
	 * 
	 * @return country
	 */
	public Integer getCountry() {
		return country;
	}
	
	/**
	 * 省份
	 * 
	 * @return province
	 */
	public Integer getProvince() {
		return province;
	}
	
	/**
	 * 城市
	 * 
	 * @return city
	 */
	public Integer getCity() {
		return city;
	}
	
	/**
	 * 县区
	 * 
	 * @return district
	 */
	public Integer getDistrict() {
		return district;
	}
	
	/**
	 * 乡镇
	 * 
	 * @return twon
	 */
	public Integer getTwon() {
		return twon;
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
	 * 邮政编码
	 * 
	 * @return zipcode
	 */
	public String getZipcode() {
		return zipcode;
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
	 * 邮件
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 物流code
	 * 
	 * @return shipping_code
	 */
	public String getShipping_code() {
		return shipping_code;
	}
	
	/**
	 * 物流名称
	 * 
	 * @return shipping_name
	 */
	public String getShipping_name() {
		return shipping_name;
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
	 * 支付方式名称
	 * 
	 * @return pay_name
	 */
	public String getPay_name() {
		return pay_name;
	}
	
	/**
	 * 发票抬头
	 * 
	 * @return invoice_title
	 */
	public String getInvoice_title() {
		return invoice_title;
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
	 * 邮费
	 * 
	 * @return shipping_price
	 */
	public BigDecimal getShipping_price() {
		return shipping_price;
	}
	
	/**
	 * 使用余额
	 * 
	 * @return user_money
	 */
	public BigDecimal getUser_money() {
		return user_money;
	}
	
	/**
	 * 优惠券抵扣
	 * 
	 * @return coupon_price
	 */
	public BigDecimal getCoupon_price() {
		return coupon_price;
	}
	
	/**
	 * 使用积分
	 * 
	 * @return integral
	 */
	public Integer getIntegral() {
		return integral;
	}
	
	/**
	 * 使用积分抵多少钱
	 * 
	 * @return integral_money
	 */
	public BigDecimal getIntegral_money() {
		return integral_money;
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
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 最后新发货时间
	 * 
	 * @return shipping_time
	 */
	public Date getShipping_time() {
		return shipping_time;
	}
	
	/**
	 * 收货确认时间
	 * 
	 * @return confirm_time
	 */
	public Date getConfirm_time() {
		return confirm_time;
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
	 * 活动id
	 * 
	 * @return order_prom_id
	 */
	public Integer getOrder_prom_id() {
		return order_prom_id;
	}
	
	/**
	 * 活动优惠金额
	 * 
	 * @return order_prom_amount
	 */
	public BigDecimal getOrder_prom_amount() {
		return order_prom_amount;
	}
	
	/**
	 * 价格调整
	 * 
	 * @return discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	
	/**
	 * 用户备注
	 * 
	 * @return user_note
	 */
	public String getUser_note() {
		return user_note;
	}
	
	/**
	 * 管理员备注
	 * 
	 * @return admin_note
	 */
	public String getAdmin_note() {
		return admin_note;
	}
	
	/**
	 * 父单单号
	 * 
	 * @return parent_sn
	 */
	public String getParent_sn() {
		return parent_sn;
	}
	
	/**
	 * 是否已分成0未分成1已分成
	 * 
	 * @return is_distribut
	 */
	public Integer getIs_distribut() {
		return is_distribut;
	}
	
	/**
	 * 1-商城普通商品，2-积分充值
	 * 
	 * @return order_type
	 */
	public Integer getOrder_type() {
		return order_type;
	}
	
	/**
	 * 支付编号
	 * 
	 * @return pay_sn
	 */
	public String getPay_sn() {
		return pay_sn;
	}
	
	/**
	 * trade_no
	 * 
	 * @return trade_no
	 */
	public String getTrade_no() {
		return trade_no;
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
	 * 会员名称
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	 * 店铺ID
	 * 
	 * @param store_id
	 */
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * 店铺名称
	 * 
	 * @param store_name
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	/**
	 * 订单状态(0-待确认，1-已确认，2-已收货，3-已取消，4-已完成(已评价)，5-已作废)
	 * 
	 * @param order_status
	 */
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	
	/**
	 * 发货状态
	 * 
	 * @param shipping_status
	 */
	public void setShipping_status(Integer shipping_status) {
		this.shipping_status = shipping_status;
	}
	
	/**
	 * 支付状态
	 * 
	 * @param pay_status
	 */
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	
	/**
	 * 收货人
	 * 
	 * @param consignee
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	/**
	 * 国家
	 * 
	 * @param country
	 */
	public void setCountry(Integer country) {
		this.country = country;
	}
	
	/**
	 * 省份
	 * 
	 * @param province
	 */
	public void setProvince(Integer province) {
		this.province = province;
	}
	
	/**
	 * 城市
	 * 
	 * @param city
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	
	/**
	 * 县区
	 * 
	 * @param district
	 */
	public void setDistrict(Integer district) {
		this.district = district;
	}
	
	/**
	 * 乡镇
	 * 
	 * @param twon
	 */
	public void setTwon(Integer twon) {
		this.twon = twon;
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
	 * 邮政编码
	 * 
	 * @param zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	 * 邮件
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 物流code
	 * 
	 * @param shipping_code
	 */
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	
	/**
	 * 物流名称
	 * 
	 * @param shipping_name
	 */
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
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
	 * 支付方式名称
	 * 
	 * @param pay_name
	 */
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	
	/**
	 * 发票抬头
	 * 
	 * @param invoice_title
	 */
	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
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
	 * 邮费
	 * 
	 * @param shipping_price
	 */
	public void setShipping_price(BigDecimal shipping_price) {
		this.shipping_price = shipping_price;
	}
	
	/**
	 * 使用余额
	 * 
	 * @param user_money
	 */
	public void setUser_money(BigDecimal user_money) {
		this.user_money = user_money;
	}
	
	/**
	 * 优惠券抵扣
	 * 
	 * @param coupon_price
	 */
	public void setCoupon_price(BigDecimal coupon_price) {
		this.coupon_price = coupon_price;
	}
	
	/**
	 * 使用积分
	 * 
	 * @param integral
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
	/**
	 * 使用积分抵多少钱
	 * 
	 * @param integral_money
	 */
	public void setIntegral_money(BigDecimal integral_money) {
		this.integral_money = integral_money;
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
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 最后新发货时间
	 * 
	 * @param shipping_time
	 */
	public void setShipping_time(Date shipping_time) {
		this.shipping_time = shipping_time;
	}
	
	/**
	 * 收货确认时间
	 * 
	 * @param confirm_time
	 */
	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
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
	 * 活动id
	 * 
	 * @param order_prom_id
	 */
	public void setOrder_prom_id(Integer order_prom_id) {
		this.order_prom_id = order_prom_id;
	}
	
	/**
	 * 活动优惠金额
	 * 
	 * @param order_prom_amount
	 */
	public void setOrder_prom_amount(BigDecimal order_prom_amount) {
		this.order_prom_amount = order_prom_amount;
	}
	
	/**
	 * 价格调整
	 * 
	 * @param discount
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	/**
	 * 用户备注
	 * 
	 * @param user_note
	 */
	public void setUser_note(String user_note) {
		this.user_note = user_note;
	}
	
	/**
	 * 管理员备注
	 * 
	 * @param admin_note
	 */
	public void setAdmin_note(String admin_note) {
		this.admin_note = admin_note;
	}
	
	/**
	 * 父单单号
	 * 
	 * @param parent_sn
	 */
	public void setParent_sn(String parent_sn) {
		this.parent_sn = parent_sn;
	}
	
	/**
	 * 是否已分成0未分成1已分成
	 * 
	 * @param is_distribut
	 */
	public void setIs_distribut(Integer is_distribut) {
		this.is_distribut = is_distribut;
	}
	
	/**
	 * 1-商城普通商品，2-积分充值
	 * 
	 * @param order_type
	 */
	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}
	
	/**
	 * 支付编号
	 * 
	 * @param pay_sn
	 */
	public void setPay_sn(String pay_sn) {
		this.pay_sn = pay_sn;
	}
	
	/**
	 * trade_no
	 * 
	 * @param trade_no
	 */
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	/**
	 * 购买积分
	 * 
	 * @param buy_points
	 */
	public void setBuy_points(String buy_points) {
		this.buy_points = buy_points;
	}

	/**
	 * 是否删除（0表示未删除，1表示已删除）
	 * 
	 * @return is_del
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * 是否删除（0表示未删除，1表示已删除）
	 * 
	 * @param is_del
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getTracking_no() {
		return tracking_no;
	}

	public void setTracking_no(String tracking_no) {
		this.tracking_no = tracking_no;
	}

	public List<ZjcOrderActionVO2> getZjcOrderActionPOs() {
		return zjcOrderActionPOs;
	}

	public void setZjcOrderActionPO(List<ZjcOrderActionVO2> zjcOrderActionPOs) {
		this.zjcOrderActionPOs = zjcOrderActionPOs;
	}
	public String getArea_info() {
		return area_info;
	}

	public void setArea_info(String area_info) {
		this.area_info = area_info;
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

	public Integer getHas_transfer() {
		return has_transfer;
	}

	public void setHas_transfer(Integer has_transfer) {
		this.has_transfer = has_transfer;
	}

	/**
	 * 易物券支付状态；0:未支付；1:已支付
	 * 
	 * @return
	 */
	public Integer getPoints_pay_status() {
		return points_pay_status;
	}

	/**
	 * 易物券支付状态；0:未支付；1:已支付
	 * 
	 * @param points_pay_status
	 */
	public void setPoints_pay_status(Integer points_pay_status) {
		this.points_pay_status = points_pay_status;
	}
	
	
	
}
