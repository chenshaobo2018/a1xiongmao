package com.zjc.store.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * <b>zjc_store[zjc_store]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 15:39:14
 */
public class ZjcStorePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 店铺ID
	 */
	private Integer store_id;
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
	/**
	 * 用户ID
	 */
	private BigInteger user_id;
	
	/**
	 * 绑定的商品分类
	 */
	private Integer cat_id;
	
	/**
	 * 是否有视频权限
	 */
	private Integer show_video;
	
	/**
	 * 1:待审核  2：审核失败 3：正常 4：关闭
	 */
	private Integer store_state;
	
	/**
	 * 最后登录时间
	 */
	private Date last_login_time;
	
	/**
	 * 最后登录IP
	 */
	private String last_login_ip;
	
	/**
	 * 营业执照电子版
	 */
	private String business_licence_number_elc;
	
	/**
	 * 省id
	 */
	private Integer province_id;
	
	/**
	 * 市id
	 */
	private Integer city_id;
	
	/**
	 * 地区id
	 */
	private Integer area_id;
	
	/**
	 * 街道ID
	 */
	private Integer twon_id;
	
	/**
	 * 省市区街道地址
	 */
	private String area_info;
	
	/**
	 * 详细地址
	 */
	private String address_detail;
	
	/**
	 * 办公电话
	 */
	private String office_phone;
	
	/**
	 * 电子邮箱
	 */
	private String contacts_email;
	
	/**
	 * 联系人
	 */
	private String contacts_name;
	
	/**
	 * 联系电话
	 */
	private String contacts_phone;
	
	/**
	 * 开户行
	 */
	private String bank_account_name;
	
	/**
	 * 银行账户
	 */
	private String bank_account_number;
	
	/**
	 * 企业编码
	 */
	private String enterprise_num;
	
	/**
	 * 经度
	 */
	private BigDecimal lng;
	
	/**
	 * 纬度
	 */
	private BigDecimal lat;
	
	/**
	 * 店铺LOGO
	 */
	private String store_logo;
	
	/**
	 * 申请店铺时间
	 */
	private Date add_time;
	
	/**
	 * 视频地址
	 */
	private String video_address;
	
	/**
	 * 店铺简介
	 */
	private String store_info;
	
	/**
	 * 食品卫生证
	 */
	private String food_hygiene_img;
	
	/**
	 * 审核失败原因
	 */
	private String attestation_reason;
	
	/**
	 * 身份证号码
	 */
	private String id_card;
	
	/**
	 * 真实姓名
	 */
	private String real_name;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 用户电话
	 */
	private String mobile;
	
	/**
	 * 是否收藏改店铺
	 */
	private boolean hasCollect = false;
	
	/**
	 * 店主身份证照片正面
	 */
	private String id_card_img;
	
	/**
	 * 特殊经营许可证照片
	 */
	private String special_premit_img;
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
	 * 用户ID
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 绑定的商品分类
	 * 
	 * @return cat_id
	 */
	public Integer getCat_id() {
		return cat_id;
	}
	
	/**
	 * 是否有视频权限
	 * 
	 * @return show_video
	 */
	public Integer getShow_video() {
		return show_video;
	}
	
	/**
	 * 1:待审核  2：审核失败 3：正常 4：关闭
	 * 
	 * @return store_state
	 */
	public Integer getStore_state() {
		return store_state;
	}
	
	/**
	 * 商家会员信息表
	 */
	private ZjcSellerInfoPO zjcSellerInfoPO;
	
	public ZjcSellerInfoPO getZjcSellerInfoPO() {
		return zjcSellerInfoPO;
	}

	public void setZjcSellerInfoPO(ZjcSellerInfoPO zjcSellerInfoPO) {
		this.zjcSellerInfoPO = zjcSellerInfoPO;
	}

	/**
     * 用户账户信息表
     */
	private ZjcUsersAccountInfoPO  zjcUsersAccountInfoPO;

	public ZjcUsersAccountInfoPO getZjcUsersAccountInfoPO() {
		return zjcUsersAccountInfoPO;
	}

	public void setZjcUsersAccountInfoPO(ZjcUsersAccountInfoPO zjcUsersAccountInfoPO) {
		this.zjcUsersAccountInfoPO = zjcUsersAccountInfoPO;
	}
	
	/**
	 * 最后登录时间
	 * 
	 * @return last_login_time
	 */
	public Date getLast_login_time() {
		return last_login_time;
	}
	
	/**
	 * 最后登录IP
	 * 
	 * @return last_login_ip
	 */
	public String getLast_login_ip() {
		return last_login_ip;
	}
	
	/**
	 * 营业执照电子版
	 * 
	 * @return business_licence_number_elc
	 */
	public String getBusiness_licence_number_elc() {
		return business_licence_number_elc;
	}
	
	/**
	 * 省id
	 * 
	 * @return province_id
	 */
	public Integer getProvince_id() {
		return province_id;
	}
	
	/**
	 * 市id
	 * 
	 * @return city_id
	 */
	public Integer getCity_id() {
		return city_id;
	}
	
	/**
	 * 地区id
	 * 
	 * @return area_id
	 */
	public Integer getArea_id() {
		return area_id;
	}
	
	/**
	 * 街道ID
	 * 
	 * @return twon_id
	 */
	public Integer getTwon_id() {
		return twon_id;
	}
	
	/**
	 * 省市区街道地址
	 * 
	 * @return area_info
	 */
	public String getArea_info() {
		return area_info;
	}
	
	/**
	 * 详细地址
	 * 
	 * @return address_detail
	 */
	public String getAddress_detail() {
		return address_detail;
	}
	
	/**
	 * 办公电话
	 * 
	 * @return office_phone
	 */
	public String getOffice_phone() {
		return office_phone;
	}
	
	/**
	 * 电子邮箱
	 * 
	 * @return contacts_email
	 */
	public String getContacts_email() {
		return contacts_email;
	}
	
	/**
	 * 联系人
	 * 
	 * @return contacts_name
	 */
	public String getContacts_name() {
		return contacts_name;
	}
	
	/**
	 * 联系电话
	 * 
	 * @return contacts_phone
	 */
	public String getContacts_phone() {
		return contacts_phone;
	}
	
	/**
	 * 开户行
	 * 
	 * @return bank_account_name
	 */
	public String getBank_account_name() {
		return bank_account_name;
	}
	
	/**
	 * 银行账户
	 * 
	 * @return bank_account_number
	 */
	public String getBank_account_number() {
		return bank_account_number;
	}
	
	/**
	 * 企业编码
	 * 
	 * @return enterprise_num
	 */
	public String getEnterprise_num() {
		return enterprise_num;
	}
	
	/**
	 * 经度
	 * 
	 * @return lng
	 */
	public BigDecimal getLng() {
		return lng;
	}
	
	/**
	 * 纬度
	 * 
	 * @return lat
	 */
	public BigDecimal getLat() {
		return lat;
	}
	
	/**
	 * 店铺LOGO
	 * 
	 * @return store_logo
	 */
	public String getStore_logo() {
		return store_logo;
	}
	
	/**
	 * 申请店铺时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 视频地址
	 * 
	 * @return video_address
	 */
	public String getVideo_address() {
		return video_address;
	}
	
	/**
	 * 店铺简介
	 * 
	 * @return store_info
	 */
	public String getStore_info() {
		return store_info;
	}
	
	/**
	 * 食品卫生证
	 * 
	 * @return food_hygiene_img
	 */
	public String getFood_hygiene_img() {
		return food_hygiene_img;
	}
	
	/**
	 * 审核失败原因
	 * 
	 * @return attestation_reason
	 */
	public String getAttestation_reason() {
		return attestation_reason;
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
	 * 用户ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 绑定的商品分类
	 * 
	 * @param cat_id
	 */
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	
	/**
	 * 是否有视频权限
	 * 
	 * @param show_video
	 */
	public void setShow_video(Integer show_video) {
		this.show_video = show_video;
	}
	
	/**
	 * 1:待审核  2：审核失败 3：正常 4：关闭
	 * 
	 * @param store_state
	 */
	public void setStore_state(Integer store_state) {
		this.store_state = store_state;
	}
	
	/**
	 * 最后登录时间
	 * 
	 * @param last_login_time
	 */
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	/**
	 * 最后登录IP
	 * 
	 * @param last_login_ip
	 */
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	
	/**
	 * 营业执照电子版
	 * 
	 * @param business_licence_number_elc
	 */
	public void setBusiness_licence_number_elc(String business_licence_number_elc) {
		this.business_licence_number_elc = business_licence_number_elc;
	}
	
	/**
	 * 省id
	 * 
	 * @param province_id
	 */
	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}
	
	/**
	 * 市id
	 * 
	 * @param city_id
	 */
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	
	/**
	 * 地区id
	 * 
	 * @param area_id
	 */
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	
	/**
	 * 街道ID
	 * 
	 * @param twon_id
	 */
	public void setTwon_id(Integer twon_id) {
		this.twon_id = twon_id;
	}
	
	/**
	 * 省市区街道地址
	 * 
	 * @param area_info
	 */
	public void setArea_info(String area_info) {
		this.area_info = area_info;
	}
	
	/**
	 * 详细地址
	 * 
	 * @param address_detail
	 */
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	
	/**
	 * 办公电话
	 * 
	 * @param office_phone
	 */
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	
	/**
	 * 电子邮箱
	 * 
	 * @param contacts_email
	 */
	public void setContacts_email(String contacts_email) {
		this.contacts_email = contacts_email;
	}
	
	/**
	 * 联系人
	 * 
	 * @param contacts_name
	 */
	public void setContacts_name(String contacts_name) {
		this.contacts_name = contacts_name;
	}
	
	/**
	 * 联系电话
	 * 
	 * @param contacts_phone
	 */
	public void setContacts_phone(String contacts_phone) {
		this.contacts_phone = contacts_phone;
	}
	
	/**
	 * 开户行
	 * 
	 * @param bank_account_name
	 */
	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}
	
	/**
	 * 银行账户
	 * 
	 * @param bank_account_number
	 */
	public void setBank_account_number(String bank_account_number) {
		this.bank_account_number = bank_account_number;
	}
	
	/**
	 * 企业编码
	 * 
	 * @param enterprise_num
	 */
	public void setEnterprise_num(String enterprise_num) {
		this.enterprise_num = enterprise_num;
	}
	
	/**
	 * 经度
	 * 
	 * @param lng
	 */
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	
	/**
	 * 纬度
	 * 
	 * @param lat
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	
	/**
	 * 店铺LOGO
	 * 
	 * @param store_logo
	 */
	public void setStore_logo(String store_logo) {
		this.store_logo = store_logo;
	}
	
	/**
	 * 申请店铺时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 视频地址
	 * 
	 * @param video_address
	 */
	public void setVideo_address(String video_address) {
		this.video_address = video_address;
	}
	
	/**
	 * 店铺简介
	 * 
	 * @param store_info
	 */
	public void setStore_info(String store_info) {
		this.store_info = store_info;
	}
	
	/**
	 * 食品卫生证
	 * 
	 * @param food_hygiene_img
	 */
	public void setFood_hygiene_img(String food_hygiene_img) {
		this.food_hygiene_img = food_hygiene_img;
	}
	
	/**
	 * 审核失败原因
	 * 
	 * @param attestation_reason
	 */
	public void setAttestation_reason(String attestation_reason) {
		this.attestation_reason = attestation_reason;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isHasCollect() {
		return hasCollect;
	}

	public void setHasCollect(boolean hasCollect) {
		this.hasCollect = hasCollect;
	}

	public String getId_card_img() {
		return id_card_img;
	}

	public void setId_card_img(String id_card_img) {
		this.id_card_img = id_card_img;
	}

	public String getSpecial_premit_img() {
		return special_premit_img;
	}

	public void setSpecial_premit_img(String special_premit_img) {
		this.special_premit_img = special_premit_img;
	}

}