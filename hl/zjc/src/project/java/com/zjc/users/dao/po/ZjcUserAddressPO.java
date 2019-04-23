package com.zjc.users.dao.po;

import java.math.BigInteger;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_user_address[zjc_user_address]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-31 16:50:34
 */
public class ZjcUserAddressPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer address_id;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 收货人
	 */
	private String consignee;
	
	/**
	 * 邮箱地址
	 */
	private String email;
	
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
	 * 地区
	 */
	private Integer district;
	
	/**
	 * 乡镇
	 */
	private Integer twon;
	
	/**
	 * 省市区
	 */
	private String area_info;
	
	/**
	 * 街道地址
	 */
	private String twon_name;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 完整地址
	 */
	private String address_info;
	
	/**
	 * 邮政编码
	 */
	private String zipcode;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 默认收货地址
	 */
	private Integer is_default;
	
	/**
	 * is_pickup
	 */
	private Integer is_pickup;
	

	/**
	 * 表id
	 * 
	 * @return address_id
	 */
	public Integer getAddress_id() {
		return address_id;
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
	 * 收货人
	 * 
	 * @return consignee
	 */
	public String getConsignee() {
		return consignee;
	}
	
	/**
	 * 邮箱地址
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
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
	 * 地区
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
	 * 省市区
	 * 
	 * @return area_info
	 */
	public String getArea_info() {
		return area_info;
	}
	
	/**
	 * 街道地址
	 * 
	 * @return twon_name
	 */
	public String getTwon_name() {
		return twon_name;
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
	 * 完整地址
	 * 
	 * @return address_info
	 */
	public String getAddress_info() {
		return address_info;
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
	 * 默认收货地址
	 * 
	 * @return is_default
	 */
	public Integer getIs_default() {
		return is_default;
	}
	
	/**
	 * is_pickup
	 * 
	 * @return is_pickup
	 */
	public Integer getIs_pickup() {
		return is_pickup;
	}
	

	/**
	 * 表id
	 * 
	 * @param address_id
	 */
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
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
	 * 收货人
	 * 
	 * @param consignee
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	/**
	 * 邮箱地址
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * 地区
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
	 * 省市区
	 * 
	 * @param area_info
	 */
	public void setArea_info(String area_info) {
		this.area_info = area_info;
	}
	
	/**
	 * 街道地址
	 * 
	 * @param twon_name
	 */
	public void setTwon_name(String twon_name) {
		this.twon_name = twon_name;
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
	 * 完整地址
	 * 
	 * @param address_info
	 */
	public void setAddress_info(String address_info) {
		this.address_info = address_info;
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
	 * 默认收货地址
	 * 
	 * @param is_default
	 */
	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
	
	/**
	 * is_pickup
	 * 
	 * @param is_pickup
	 */
	public void setIs_pickup(Integer is_pickup) {
		this.is_pickup = is_pickup;
	}
	

}