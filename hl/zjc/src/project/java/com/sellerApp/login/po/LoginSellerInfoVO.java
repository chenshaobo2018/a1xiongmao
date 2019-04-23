package com.sellerApp.login.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * 商家登录用户信息展示VO
 * 
 * @author wgm
 * @date 2017-11-02 09:22:16
 */
/**
 * @author Administrator
 *
 */
public class LoginSellerInfoVO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private BigInteger user_id;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	
	/**
	 * 第三方返回昵称
	 */
	private String nickname;
	
	
	/**
	 * 用于app 授权类似于session_id
	 */
	private String token;
	
	/**
	 * token有效期
	 */
	private Integer token_validate_time;
	
	/**
	 * 身份证号码
	 */
	private String id_card;
	
	/**
	 * 真实姓名
	 */
	private String real_name;
	
	/**
	 * 用户可转积分
	 */
	private Integer make_over_integral;
	
	/**
	 * 是否实名认证
	 */
	private Integer authentication;
	
	/**
	 * 店铺名称
	 */
	private String store_name;
	
	/**
	 * 最后登陆时间
	 */
	private Date last_login;
	
	/**
	 * 店铺logo
	 */
	private String store_logo;
	
	/**
	 * 待转券
	 */
	private String pending_transfer;
	
	/**
	 * 店铺ID
	 */
	private int store_id;
	
	public BigInteger getUser_id() {
		return user_id;
	}

	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getToken_validate_time() {
		return token_validate_time;
	}

	public void setToken_validate_time(Integer token_validate_time) {
		this.token_validate_time = token_validate_time;
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

	public Integer getMake_over_integral() {
		return make_over_integral;
	}

	public void setMake_over_integral(Integer make_over_integral) {
		this.make_over_integral = make_over_integral;
	}

	public Integer getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getStore_logo() {
		return store_logo;
	}

	public void setStore_logo(String store_logo) {
		this.store_logo = store_logo;
	}

	public String getPending_transfer() {
		return pending_transfer;
	}

	public void setPending_transfer(String pending_transfer) {
		this.pending_transfer = pending_transfer;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
}