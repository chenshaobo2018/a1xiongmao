package com.zjc.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_delivery_doc[zjc_delivery_doc]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:52:29
 */
public class ZjcDeliveryDocPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 发货单ID
	 */
	private Integer id;
	
	/**
	 * 订单ID
	 */
	private Integer order_id;
	
	/**
	 * 订单编号
	 */
	private String order_sn;
	
	/**
	 * 用户ID
	 */
	private BigInteger user_id;
	
	/**
	 * 管理员ID
	 */
	private Integer admin_id;
	
	/**
	 * 管理员类型  1，总后台管理员， 2 商家
	 */
	private Integer admin_type;
	
	/**
	 * 收货人
	 */
	private String consignee;
	
	/**
	 * 邮编
	 */
	private String zipcode;
	
	/**
	 * 联系手机
	 */
	private String mobile;
	
	/**
	 * 国ID
	 */
	private Integer country;
	
	/**
	 * 省ID
	 */
	private Integer province;
	
	/**
	 * 市ID
	 */
	private Integer city;
	
	/**
	 * 区ID
	 */
	private Integer district;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 物流code
	 */
	private String shipping_code;
	
	/**
	 * 快递名称
	 */
	private String shipping_name;
	
	/**
	 * 运费
	 */
	private BigDecimal shipping_price;
	
	/**
	 * 物流单号
	 */
	private String invoice_no;
	
	/**
	 * 座机电话
	 */
	private String tel;
	
	/**
	 * 管理员添加的备注信息
	 */
	private String note;
	
	/**
	 * 友好收货时间
	 */
	private Date best_time;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 是否删除
	 */
	private Integer is_del;
	

	/**
	 * 发货单ID
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 订单ID
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
	 * 用户ID
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
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
	 * 管理员类型  1，总后台管理员， 2 商家
	 * 
	 * @return admin_type
	 */
	public Integer getAdmin_type() {
		return admin_type;
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
	 * 邮编
	 * 
	 * @return zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	
	/**
	 * 联系手机
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 国ID
	 * 
	 * @return country
	 */
	public Integer getCountry() {
		return country;
	}
	
	/**
	 * 省ID
	 * 
	 * @return province
	 */
	public Integer getProvince() {
		return province;
	}
	
	/**
	 * 市ID
	 * 
	 * @return city
	 */
	public Integer getCity() {
		return city;
	}
	
	/**
	 * 区ID
	 * 
	 * @return district
	 */
	public Integer getDistrict() {
		return district;
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
	 * 物流code
	 * 
	 * @return shipping_code
	 */
	public String getShipping_code() {
		return shipping_code;
	}
	
	/**
	 * 快递名称
	 * 
	 * @return shipping_name
	 */
	public String getShipping_name() {
		return shipping_name;
	}
	
	/**
	 * 运费
	 * 
	 * @return shipping_price
	 */
	public BigDecimal getShipping_price() {
		return shipping_price;
	}
	
	/**
	 * 物流单号
	 * 
	 * @return invoice_no
	 */
	public String getInvoice_no() {
		return invoice_no;
	}
	
	/**
	 * 座机电话
	 * 
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * 管理员添加的备注信息
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * 友好收货时间
	 * 
	 * @return best_time
	 */
	public Date getBest_time() {
		return best_time;
	}
	
	/**
	 * 创建时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * 是否删除
	 * 
	 * @return is_del
	 */
	public Integer getIs_del() {
		return is_del;
	}
	

	/**
	 * 发货单ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 订单ID
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
	 * 用户ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
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
	 * 管理员类型  1，总后台管理员， 2 商家
	 * 
	 * @param admin_type
	 */
	public void setAdmin_type(Integer admin_type) {
		this.admin_type = admin_type;
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
	 * 邮编
	 * 
	 * @param zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	/**
	 * 联系手机
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 国ID
	 * 
	 * @param country
	 */
	public void setCountry(Integer country) {
		this.country = country;
	}
	
	/**
	 * 省ID
	 * 
	 * @param province
	 */
	public void setProvince(Integer province) {
		this.province = province;
	}
	
	/**
	 * 市ID
	 * 
	 * @param city
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	
	/**
	 * 区ID
	 * 
	 * @param district
	 */
	public void setDistrict(Integer district) {
		this.district = district;
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
	 * 物流code
	 * 
	 * @param shipping_code
	 */
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	
	/**
	 * 快递名称
	 * 
	 * @param shipping_name
	 */
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}
	
	/**
	 * 运费
	 * 
	 * @param shipping_price
	 */
	public void setShipping_price(BigDecimal shipping_price) {
		this.shipping_price = shipping_price;
	}
	
	/**
	 * 物流单号
	 * 
	 * @param invoice_no
	 */
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	
	/**
	 * 座机电话
	 * 
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * 管理员添加的备注信息
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * 友好收货时间
	 * 
	 * @param best_time
	 */
	public void setBest_time(Date best_time) {
		this.best_time = best_time;
	}
	
	/**
	 * 创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 是否删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	

}