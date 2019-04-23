package com.zjc.store.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * <b>zjc_seller_info[zjc_seller_info]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 17:56:19
 */
/**
 * @author Administrator
 *
 */
public class ZjcSellerInfoPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private BigInteger user_id;
	
	/**
	 * 邮件
	 */
	private String email;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 支付密码
	 */
	private String pay_password;
	
	/**
	 * 0 保密 1 男 2 女
	 */
	private Integer sex;
	
	/**
	 * 生日
	 */
	private Integer birthday;
	
	/**
	 * 注册时间
	 */
	private Date reg_time;
	
	/**
	 * 最后登录时间
	 */
	private Date last_login;
	
	/**
	 * 最后登录ip
	 */
	private String last_ip;
	
	/**
	 * QQ
	 */
	private String qq;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 是否验证手机
	 */
	private Integer mobile_validated;
	
	/**
	 * 第三方来源 wx weibo alipay
	 */
	private String oauth;
	
	/**
	 * 微信openID
	 */
	private String openid;
	
	/**
	 * 头像
	 */
	private String head_pic;
	
	/**
	 * 是否验证电子邮箱
	 */
	private Integer email_validated;
	
	/**
	 * 第三方返回昵称
	 */
	private String nickname;
	
	/**
	 * 会员等级
	 */
	private String level;
	
	/**
	 * 会员折扣，默认1不享受
	 */
	private BigDecimal discount;
	
	/**
	 * 是否为分销商 0 否 1 是
	 */
	private Integer is_distribut;
	
	/**
	 * 上级
	 */
	private Integer first_leader;
	
	/**
	 * 上上级
	 */
	private Integer second_leader;
	
	/**
	 * 第三个上级
	 */
	private Integer third_leader;
	
	/**
	 * 用于app 授权类似于session_id
	 */
	private String token;
	
	/**
	 * token有效期
	 */
	private Integer token_validate_time;
	
	/**
	 * 会员推荐码
	 */
	private String recommended_code;
	
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
	 * 合格会员数
	 */
	private Integer qualified_member;
	
	/**
	 * 店铺ID
	 */
	private Integer store_id;
	
	/**
	 * 是否打开小额免密支付
	 */
	private Integer is_open_min_pay;
	
	/**
	 * 是否实名认证
	 */
	private Integer authentication;
	
	/**
	 * 是否被冻结锁定（1-冻结锁定，0-启用）
	 */
	private Integer is_lock;
	
	/**
	 * android 1 ,IOS 2,weixin 3
	 */
	private Integer src_client;
	
	/**
	 * 商家登录时的设备号
	 */
	private String clientid;
	
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
	 * 表id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
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
	 * 密码
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 支付密码
	 * 
	 * @return pay_password
	 */
	public String getPay_password() {
		return pay_password;
	}
	
	/**
	 * 0 保密 1 男 2 女
	 * 
	 * @return sex
	 */
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * 生日
	 * 
	 * @return birthday
	 */
	public Integer getBirthday() {
		return birthday;
	}
	
	/**
	 * 注册时间
	 * 
	 * @return reg_time
	 */
	public Date getReg_time() {
		return reg_time;
	}
	
	/**
	 * 最后登录时间
	 * 
	 * @return last_login
	 */
	public Date getLast_login() {
		return last_login;
	}
	
	/**
	 * 最后登录ip
	 * 
	 * @return last_ip
	 */
	public String getLast_ip() {
		return last_ip;
	}
	
	/**
	 * QQ
	 * 
	 * @return qq
	 */
	public String getQq() {
		return qq;
	}
	
	/**
	 * 手机号码
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 是否验证手机
	 * 
	 * @return mobile_validated
	 */
	public Integer getMobile_validated() {
		return mobile_validated;
	}
	
	/**
	 * 第三方来源 wx weibo alipay
	 * 
	 * @return oauth
	 */
	public String getOauth() {
		return oauth;
	}
	
	/**
	 * 微信openID
	 * 
	 * @return openid
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * 头像
	 * 
	 * @return head_pic
	 */
	public String getHead_pic() {
		return head_pic;
	}
	
	/**
	 * 是否验证电子邮箱
	 * 
	 * @return email_validated
	 */
	public Integer getEmail_validated() {
		return email_validated;
	}
	
	/**
	 * 第三方返回昵称
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * 会员等级
	 * 
	 * @return level
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * 会员折扣，默认1不享受
	 * 
	 * @return discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	
	/**
	 * 是否为分销商 0 否 1 是
	 * 
	 * @return is_distribut
	 */
	public Integer getIs_distribut() {
		return is_distribut;
	}
	
	/**
	 * 上级
	 * 
	 * @return first_leader
	 */
	public Integer getFirst_leader() {
		return first_leader;
	}
	
	/**
	 * 上上级
	 * 
	 * @return second_leader
	 */
	public Integer getSecond_leader() {
		return second_leader;
	}
	
	/**
	 * 第三个上级
	 * 
	 * @return third_leader
	 */
	public Integer getThird_leader() {
		return third_leader;
	}
	
	/**
	 * 用于app 授权类似于session_id
	 * 
	 * @return token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * token有效期
	 * 
	 * @return token_validate_time
	 */
	public Integer getToken_validate_time() {
		return token_validate_time;
	}
	
	/**
	 * 会员推荐码
	 * 
	 * @return recommended_code
	 */
	public String getRecommended_code() {
		return recommended_code;
	}
	
	/**
	 * 是否合格会员(0-否，1-是)
	 * 
	 * @return is_qualified_member
	 */
	public Integer getIs_qualified_member() {
		return is_qualified_member;
	}
	
	/**
	 * 身份证号码
	 * 
	 * @return id_card
	 */
	public String getId_card() {
		return id_card;
	}
	
	/**
	 * 真实姓名
	 * 
	 * @return real_name
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * 合格会员数
	 * 
	 * @return qualified_member
	 */
	public Integer getQualified_member() {
		return qualified_member;
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
	 * 是否打开小额免密支付
	 * 
	 * @return is_open_min_pay
	 */
	public Integer getIs_open_min_pay() {
		return is_open_min_pay;
	}
	
	/**
	 * 是否实名认证
	 * 
	 * @return authentication
	 */
	public Integer getAuthentication() {
		return authentication;
	}
	
	/**
	 * 是否被冻结锁定（1-冻结锁定，0-启用）
	 * 
	 * @return is_lock
	 */
	public Integer getIs_lock() {
		return is_lock;
	}
	

	/**
	 * 表id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
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
	 * 密码
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 支付密码
	 * 
	 * @param pay_password
	 */
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	
	/**
	 * 0 保密 1 男 2 女
	 * 
	 * @param sex
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	/**
	 * 生日
	 * 
	 * @param birthday
	 */
	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * 注册时间
	 * 
	 * @param reg_time
	 */
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	
	/**
	 * 最后登录时间
	 * 
	 * @param last_login
	 */
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	
	/**
	 * 最后登录ip
	 * 
	 * @param last_ip
	 */
	public void setLast_ip(String last_ip) {
		this.last_ip = last_ip;
	}
	
	/**
	 * QQ
	 * 
	 * @param qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	/**
	 * 手机号码
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 是否验证手机
	 * 
	 * @param mobile_validated
	 */
	public void setMobile_validated(Integer mobile_validated) {
		this.mobile_validated = mobile_validated;
	}
	
	/**
	 * 第三方来源 wx weibo alipay
	 * 
	 * @param oauth
	 */
	public void setOauth(String oauth) {
		this.oauth = oauth;
	}
	
	/**
	 * 微信openID
	 * 
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**
	 * 头像
	 * 
	 * @param head_pic
	 */
	public void setHead_pic(String head_pic) {
		this.head_pic = head_pic;
	}
	
	/**
	 * 是否验证电子邮箱
	 * 
	 * @param email_validated
	 */
	public void setEmail_validated(Integer email_validated) {
		this.email_validated = email_validated;
	}
	
	/**
	 * 第三方返回昵称
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 会员等级
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 会员折扣，默认1不享受
	 * 
	 * @param discount
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	/**
	 * 是否为分销商 0 否 1 是
	 * 
	 * @param is_distribut
	 */
	public void setIs_distribut(Integer is_distribut) {
		this.is_distribut = is_distribut;
	}
	
	/**
	 * 上级
	 * 
	 * @param first_leader
	 */
	public void setFirst_leader(Integer first_leader) {
		this.first_leader = first_leader;
	}
	
	/**
	 * 上上级
	 * 
	 * @param second_leader
	 */
	public void setSecond_leader(Integer second_leader) {
		this.second_leader = second_leader;
	}
	
	/**
	 * 第三个上级
	 * 
	 * @param third_leader
	 */
	public void setThird_leader(Integer third_leader) {
		this.third_leader = third_leader;
	}
	
	/**
	 * 用于app 授权类似于session_id
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * token有效期
	 * 
	 * @param token_validate_time
	 */
	public void setToken_validate_time(Integer token_validate_time) {
		this.token_validate_time = token_validate_time;
	}
	
	/**
	 * 会员推荐码
	 * 
	 * @param recommended_code
	 */
	public void setRecommended_code(String recommended_code) {
		this.recommended_code = recommended_code;
	}
	
	/**
	 * 是否合格会员(0-否，1-是)
	 * 
	 * @param is_qualified_member
	 */
	public void setIs_qualified_member(Integer is_qualified_member) {
		this.is_qualified_member = is_qualified_member;
	}
	
	/**
	 * 身份证号码
	 * 
	 * @param id_card
	 */
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	
	/**
	 * 真实姓名
	 * 
	 * @param real_name
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	/**
	 * 合格会员数
	 * 
	 * @param qualified_member
	 */
	public void setQualified_member(Integer qualified_member) {
		this.qualified_member = qualified_member;
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
	 * 是否打开小额免密支付
	 * 
	 * @param is_open_min_pay
	 */
	public void setIs_open_min_pay(Integer is_open_min_pay) {
		this.is_open_min_pay = is_open_min_pay;
	}
	
	/**
	 * 是否实名认证
	 * 
	 * @param authentication
	 */
	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}

	/**
	 * 是否被冻结锁定（1-冻结锁定，0-启用）
	 * 
	 * @param is_lock
	 */
	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}

	/**
	 * android 1 ,IOS 2,weixin 3
	 * @return
	 */
	public Integer getSrc_client() {
		return src_client;
	}

	/**
	 * android 1 ,IOS 2,weixin 3
	 * 
	 * @param src_client
	 */
	public void setSrc_client(Integer src_client) {
		this.src_client = src_client;
	}

	/**
	 * 会员登录时的设备号、
	 * 
	 * @return
	 */
	public String getClientid() {
		return clientid;
	}

	/**
	 * 会员登录时的设备号
	 * 
	 * @param clientid
	 */
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	
	

}