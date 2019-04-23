package com.zjc.users.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_users[zjc_users]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-30 10:25:45
 */
public class ZjcUsersPO extends PO {

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
	 * 用户金额
	 */
	private BigDecimal user_money;
	
	/**
	 * 冻结金额
	 */
	private BigDecimal frozen_money;
	
	/**
	 * 累积分佣金额
	 */
	private BigDecimal distribut_money;
	
	/**
	 * 消费积分
	 */
	private Integer pay_points;
	
	/**
	 * 默认收货地址
	 */
	private Integer address_id;
	
	/**
	 * 注册时间
	 */
	private Integer reg_time;
	
	/**
	 * 最后登录时间
	 */
	private Integer last_login;
	
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
	 * 第三方唯一标示
	 */
	private String openid;
	
	/**
	 * 头像
	 */
	private String head_pic;
	
	/**
	 * 头像七牛地址
	 */
	private String head_pic_qn;
	
	/**
	 * 省份
	 */
	private Integer province;
	
	/**
	 * 市区
	 */
	private Integer city;
	
	/**
	 * 县
	 */
	private Integer district;
	
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
	private Integer level;
	
	/**
	 * 会员折扣，默认1不享受
	 */
	private BigDecimal discount;
	
	/**
	 * 消费累计额度(自然消费)
	 */
	private String total_amount;
	
	/**
	 * 是否被锁定冻结
	 */
	private Integer is_lock;
	
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
	 * 用户可转积分
	 */
	private BigInteger make_over_integral;
	
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
	 * 钱包额度
	 */
	private String wallet_quota;
	
	/**
	 * 合格会员数
	 */
	private Integer qualified_member;
	
	/**
	 * 店铺ID
	 */
	private Integer store_id;
	
	/**
	 * 是否开通了结算中心(0-否，1-是)
	 */
	private Integer settlement_center;
	
	/**
	 * 是否开通了结算提成(0-否，1-是)
	 */
	private Integer settlement_center_tc;
	
	/**
	 * 结算中心开始时间
	 */
	private Integer settlement_center_stime;
	
	/**
	 * 结算中心结束时间
	 */
	private Integer settlement_center_etime;
	
	/**
	 * 是否打开小额免密支付
	 */
	private Integer is_open_min_pay;
	
	/**
	 * 注册赠送积分
	 */
	private String reg_giving_points;
	
	/**
	 * 领到易物卷(返给用户的积分)
	 */
	private String return_points;
	
	/**
	 * 赠送易物卷(应得积分)
	 */
	private String due_tc_points;
	
	/**
	 * 领到易物卷(实际提成)
	 */
	private String practical_tc_points;
	
	/**
	 * 钱包总额度
	 */
	private Integer count_wallet_quota;
	
	/**
	 * has_terminal
	 */
	private Integer has_terminal;
	
	/**
	 * authentication
	 */
	private Integer authentication;
	
	/**
	 * android 1 ,IOS 2,weixin 3
	 */
	private Integer src_client;
	
	/**
	 * 会员登录时的设备号
	 */
	private String clientid;
	

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
	 * 用户金额
	 * 
	 * @return user_money
	 */
	public BigDecimal getUser_money() {
		return user_money;
	}
	
	/**
	 * 冻结金额
	 * 
	 * @return frozen_money
	 */
	public BigDecimal getFrozen_money() {
		return frozen_money;
	}
	
	/**
	 * 累积分佣金额
	 * 
	 * @return distribut_money
	 */
	public BigDecimal getDistribut_money() {
		return distribut_money;
	}
	
	/**
	 * 消费积分
	 * 
	 * @return pay_points
	 */
	public Integer getPay_points() {
		return pay_points;
	}
	
	/**
	 * 默认收货地址
	 * 
	 * @return address_id
	 */
	public Integer getAddress_id() {
		return address_id;
	}
	
	/**
	 * 注册时间
	 * 
	 * @return reg_time
	 */
	public Integer getReg_time() {
		return reg_time;
	}
	
	/**
	 * 最后登录时间
	 * 
	 * @return last_login
	 */
	public Integer getLast_login() {
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
	 * 第三方唯一标示
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
	 * 头像七牛地址
	 * 
	 * @return head_pic_qn
	 */
	public String getHead_pic_qn() {
		return head_pic_qn;
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
	 * 市区
	 * 
	 * @return city
	 */
	public Integer getCity() {
		return city;
	}
	
	/**
	 * 县
	 * 
	 * @return district
	 */
	public Integer getDistrict() {
		return district;
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
	public Integer getLevel() {
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
	 * 消费累计额度(自然消费)
	 * 
	 * @return total_amount
	 */
	public String getTotal_amount() {
		return total_amount;
	}
	
	/**
	 * 是否被锁定冻结
	 * 
	 * @return is_lock
	 */
	public Integer getIs_lock() {
		return is_lock;
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
	 * 用户可转积分
	 * 
	 * @return make_over_integral
	 */
	public BigInteger getMake_over_integral() {
		return make_over_integral;
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
	 * 钱包额度
	 * 
	 * @return wallet_quota
	 */
	public String getWallet_quota() {
		return wallet_quota;
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
	 * 是否开通了结算中心(0-否，1-是)
	 * 
	 * @return settlement_center
	 */
	public Integer getSettlement_center() {
		return settlement_center;
	}
	
	/**
	 * 是否开通了结算提成(0-否，1-是)
	 * 
	 * @return settlement_center_tc
	 */
	public Integer getSettlement_center_tc() {
		return settlement_center_tc;
	}
	
	/**
	 * 结算中心开始时间
	 * 
	 * @return settlement_center_stime
	 */
	public Integer getSettlement_center_stime() {
		return settlement_center_stime;
	}
	
	/**
	 * 结算中心结束时间
	 * 
	 * @return settlement_center_etime
	 */
	public Integer getSettlement_center_etime() {
		return settlement_center_etime;
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
	 * 注册赠送积分
	 * 
	 * @return reg_giving_points
	 */
	public String getReg_giving_points() {
		return reg_giving_points;
	}
	
	/**
	 * 领到易物卷(返给用户的积分)
	 * 
	 * @return return_points
	 */
	public String getReturn_points() {
		return return_points;
	}
	
	/**
	 * 赠送易物卷(应得积分)
	 * 
	 * @return due_tc_points
	 */
	public String getDue_tc_points() {
		return due_tc_points;
	}
	
	/**
	 * 领到易物卷(实际提成)
	 * 
	 * @return practical_tc_points
	 */
	public String getPractical_tc_points() {
		return practical_tc_points;
	}
	
	/**
	 * 钱包总额度
	 * 
	 * @return count_wallet_quota
	 */
	public Integer getCount_wallet_quota() {
		return count_wallet_quota;
	}
	
	/**
	 * has_terminal
	 * 
	 * @return has_terminal
	 */
	public Integer getHas_terminal() {
		return has_terminal;
	}
	
	/**
	 * authentication
	 * 
	 * @return authentication
	 */
	public Integer getAuthentication() {
		return authentication;
	}
	
	/**
	 * android 1 ,IOS 2,weixin 3
	 * 
	 * @return src_client
	 */
	public Integer getSrc_client() {
		return src_client;
	}
	
	/**
	 * 会员登录时的设备号
	 * 
	 * @return clientid
	 */
	public String getClientid() {
		return clientid;
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
	 * 用户金额
	 * 
	 * @param user_money
	 */
	public void setUser_money(BigDecimal user_money) {
		this.user_money = user_money;
	}
	
	/**
	 * 冻结金额
	 * 
	 * @param frozen_money
	 */
	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}
	
	/**
	 * 累积分佣金额
	 * 
	 * @param distribut_money
	 */
	public void setDistribut_money(BigDecimal distribut_money) {
		this.distribut_money = distribut_money;
	}
	
	/**
	 * 消费积分
	 * 
	 * @param pay_points
	 */
	public void setPay_points(Integer pay_points) {
		this.pay_points = pay_points;
	}
	
	/**
	 * 默认收货地址
	 * 
	 * @param address_id
	 */
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	
	/**
	 * 注册时间
	 * 
	 * @param reg_time
	 */
	public void setReg_time(Integer reg_time) {
		this.reg_time = reg_time;
	}
	
	/**
	 * 最后登录时间
	 * 
	 * @param last_login
	 */
	public void setLast_login(Integer last_login) {
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
	 * 第三方唯一标示
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
	 * 头像七牛地址
	 * 
	 * @param head_pic_qn
	 */
	public void setHead_pic_qn(String head_pic_qn) {
		this.head_pic_qn = head_pic_qn;
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
	 * 市区
	 * 
	 * @param city
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	
	/**
	 * 县
	 * 
	 * @param district
	 */
	public void setDistrict(Integer district) {
		this.district = district;
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
	public void setLevel(Integer level) {
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
	 * 消费累计额度(自然消费)
	 * 
	 * @param total_amount
	 */
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	
	/**
	 * 是否被锁定冻结
	 * 
	 * @param is_lock
	 */
	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
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
	 * 用户可转积分
	 * 
	 * @param make_over_integral
	 */
	public void setMake_over_integral(BigInteger make_over_integral) {
		this.make_over_integral = make_over_integral;
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
	 * 钱包额度
	 * 
	 * @param wallet_quota
	 */
	public void setWallet_quota(String wallet_quota) {
		this.wallet_quota = wallet_quota;
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
	 * 是否开通了结算中心(0-否，1-是)
	 * 
	 * @param settlement_center
	 */
	public void setSettlement_center(Integer settlement_center) {
		this.settlement_center = settlement_center;
	}
	
	/**
	 * 是否开通了结算提成(0-否，1-是)
	 * 
	 * @param settlement_center_tc
	 */
	public void setSettlement_center_tc(Integer settlement_center_tc) {
		this.settlement_center_tc = settlement_center_tc;
	}
	
	/**
	 * 结算中心开始时间
	 * 
	 * @param settlement_center_stime
	 */
	public void setSettlement_center_stime(Integer settlement_center_stime) {
		this.settlement_center_stime = settlement_center_stime;
	}
	
	/**
	 * 结算中心结束时间
	 * 
	 * @param settlement_center_etime
	 */
	public void setSettlement_center_etime(Integer settlement_center_etime) {
		this.settlement_center_etime = settlement_center_etime;
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
	 * 注册赠送积分
	 * 
	 * @param reg_giving_points
	 */
	public void setReg_giving_points(String reg_giving_points) {
		this.reg_giving_points = reg_giving_points;
	}
	
	/**
	 * 领到易物卷(返给用户的积分)
	 * 
	 * @param return_points
	 */
	public void setReturn_points(String return_points) {
		this.return_points = return_points;
	}
	
	/**
	 * 赠送易物卷(应得积分)
	 * 
	 * @param due_tc_points
	 */
	public void setDue_tc_points(String due_tc_points) {
		this.due_tc_points = due_tc_points;
	}
	
	/**
	 * 领到易物卷(实际提成)
	 * 
	 * @param practical_tc_points
	 */
	public void setPractical_tc_points(String practical_tc_points) {
		this.practical_tc_points = practical_tc_points;
	}
	
	/**
	 * 钱包总额度
	 * 
	 * @param count_wallet_quota
	 */
	public void setCount_wallet_quota(Integer count_wallet_quota) {
		this.count_wallet_quota = count_wallet_quota;
	}
	
	/**
	 * has_terminal
	 * 
	 * @param has_terminal
	 */
	public void setHas_terminal(Integer has_terminal) {
		this.has_terminal = has_terminal;
	}
	
	/**
	 * authentication
	 * 
	 * @param authentication
	 */
	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
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
	 * 会员登录时的设备号
	 * 
	 * @param clientid
	 */
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	

}