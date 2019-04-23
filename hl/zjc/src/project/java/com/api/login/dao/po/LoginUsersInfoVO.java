package com.api.login.dao.po;

import java.math.BigInteger;

import aos.framework.core.typewrap.PO;

/**
 * 登录用户信息展示VO
 * 
 * @author AHei
 * @date 2017-06-15 09:22:16
 */
/**
 * @author Administrator
 *
 */
public class LoginUsersInfoVO extends PO {

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
	 * 头像
	 */
	private String head_pic;
	
	/**
	 * 第三方返回昵称
	 */
	private String nickname;
	
	/**
	 * 会员等级
	 */
	private String level;
	
	/**
	 * 用于app 授权类似于session_id
	 */
	private String token;
	
	/**
	 * token有效期
	 */
	private Integer token_validate_time;
	
	/**
	 * 是否合格会员(0-否，1-是)
	 */
	private Integer is_qualified_member;
	
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
	 * 用户可用积分
	 */
	private Integer pay_points;
	
	/**
	 * 上级
	 */
	private Integer first_leader;
	
	/**
	 * 微信unionid
	 */
	private String unionid;
	
	
	/**
	 * 是否实名认证
	 */
	private Integer authentication;
	
	private Integer sqvip;
	private Integer sqamount;
	private Integer sqpay;
	
	private Integer type;
	
   private String provincial_generation;
	
	private String bank_card;
	private String where_it_is;
	
	
	
	private Integer drops;
	
	

	public Integer getDrops() {
		return drops;
	}

	public void setDrops(Integer drops) {
		this.drops = drops;
	}


	public String getProvincial_generation() {
		return provincial_generation;
	}

	public void setProvincial_generation(String provincial_generation) {
		this.provincial_generation = provincial_generation;
	}

	public String getBank_card() {
		return bank_card;
	}

	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}

	public String getWhere_it_is() {
		return where_it_is;
	}

	public void setWhere_it_is(String where_it_is) {
		this.where_it_is = where_it_is;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public String getHead_pic() {
		return head_pic;
	}

	public void setHead_pic(String head_pic) {
		this.head_pic = head_pic;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public Integer getIs_qualified_member() {
		return is_qualified_member;
	}

	public void setIs_qualified_member(Integer is_qualified_member) {
		this.is_qualified_member = is_qualified_member;
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

	public Integer getPay_points() {
		return pay_points;
	}

	public void setPay_points(Integer pay_points) {
		this.pay_points = pay_points;
	}

	public Integer getFirst_leader() {
		return first_leader;
	}

	public void setFirst_leader(Integer first_leader) {
		this.first_leader = first_leader;
	}

	public Integer getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}

	public Integer getSqvip() {
		return sqvip;
	}

	public void setSqvip(Integer sqvip) {
		this.sqvip = sqvip;
	}

	public Integer getSqamount() {
		return sqamount;
	}

	public void setSqamount(Integer sqamount) {
		this.sqamount = sqamount;
	}

	public Integer getSqpay() {
		return sqpay;
	}

	public void setSqpay(Integer sqpay) {
		this.sqpay = sqpay;
	}

	/**
	 * 微信unionid
	 * 
	 * @return
	 */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * 微信unionid
	 * 
	 * @param unionid
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	
}