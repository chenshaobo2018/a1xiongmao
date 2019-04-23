package com.zjc.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_bz_order[zjc_bz_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:52:28
 */
public class ZjcBzOrderPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private Integer bz_id;
	
	/**
	 * 订单编号
	 */
	private String bz_sn;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 会员名称
	 */
	private String user_name;
	
	/**
	 * 结算中心价格
	 */
	private BigDecimal bz_price;
	
	/**
	 * 倍增金额
	 */
	private BigDecimal bz_after_price;
	
	/**
	 * 会员编号(邀请码)
	 */
	private String recommended_code;
	
	/**
	 * 结算中心拥有者名称
	 */
	private String seller_name;
	
	/**
	 * 结算中心拥有人ID
	 */
	private Integer seller_id;
	
	/**
	 * 购买时间
	 */
	private Date add_time;
	
	/**
	 * 会员电话
	 */
	private String mobile;
	
	private Integer is_send;
	
	
	

	public Integer getIs_send() {
		return is_send;
	}

	public void setIs_send(Integer is_send) {
		this.is_send = is_send;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 订单id
	 * 
	 * @return bz_id
	 */
	public Integer getBz_id() {
		return bz_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @return bz_sn
	 */
	public String getBz_sn() {
		return bz_sn;
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
	 * 会员名称
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * 结算中心价格
	 * 
	 * @return bz_price
	 */
	public BigDecimal getBz_price() {
		return bz_price;
	}
	
	/**
	 * 倍增金额
	 * 
	 * @return bz_after_price
	 */
	public BigDecimal getBz_after_price() {
		return bz_after_price;
	}
	
	/**
	 * 会员编号(邀请码)
	 * 
	 * @return recommended_code
	 */
	public String getRecommended_code() {
		return recommended_code;
	}
	
	/**
	 * 结算中心拥有者名称
	 * 
	 * @return seller_name
	 */
	public String getSeller_name() {
		return seller_name;
	}
	
	/**
	 * 结算中心拥有人ID
	 * 
	 * @return seller_id
	 */
	public Integer getSeller_id() {
		return seller_id;
	}
	
	/**
	 * 购买时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	

	/**
	 * 订单id
	 * 
	 * @param bz_id
	 */
	public void setBz_id(Integer bz_id) {
		this.bz_id = bz_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @param bz_sn
	 */
	public void setBz_sn(String bz_sn) {
		this.bz_sn = bz_sn;
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
	 * 会员名称
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * 结算中心价格
	 * 
	 * @param bz_price
	 */
	public void setBz_price(BigDecimal bz_price) {
		this.bz_price = bz_price;
	}
	
	/**
	 * 倍增金额
	 * 
	 * @param bz_after_price
	 */
	public void setBz_after_price(BigDecimal bz_after_price) {
		this.bz_after_price = bz_after_price;
	}
	
	/**
	 * 会员编号(邀请码)
	 * 
	 * @param recommended_code
	 */
	public void setRecommended_code(String recommended_code) {
		this.recommended_code = recommended_code;
	}
	
	/**
	 * 结算中心拥有者名称
	 * 
	 * @param seller_name
	 */
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	
	/**
	 * 结算中心拥有人ID
	 * 
	 * @param seller_id
	 */
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	
	/**
	 * 购买时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	

}