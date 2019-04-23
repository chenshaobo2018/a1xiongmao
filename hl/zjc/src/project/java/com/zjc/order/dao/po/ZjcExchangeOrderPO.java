package com.zjc.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_exchange_order[zjc_exchange_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:52:30
 */
public class ZjcExchangeOrderPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private Integer ex_id;
	
	/**
	 * 订单编号
	 */
	private String ex_sn;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 会员名称
	 */
	private String user_name;
	
	/**
	 * 卖家
	 */
	private Integer seller_id;
	
	/**
	 * 卖家名称
	 */
	private String seller_name;
	
	/**
	 * 转多少积分
	 */
	private BigDecimal ex_price;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 手续费
	 */
	private BigDecimal ex_fee;
	
	/**
	 * add_time
	 */
	private Date add_time;
	
	/**
	 * 0待审核  1 已审核  2  未通过  3  已失效
	 */
	private Integer status;
	
	/**
	 * 1 积分转账  2 易物担保
	 */
	private Integer type;
	
	/**
	 * 订单备注
	 */
	private String note;
	
	/**
	 * 易物担保验证码
	 */
	private String code;
	

	/**
	 * 订单id
	 * 
	 * @return ex_id
	 */
	public Integer getEx_id() {
		return ex_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @return ex_sn
	 */
	public String getEx_sn() {
		return ex_sn;
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
	 * 卖家
	 * 
	 * @return seller_id
	 */
	public Integer getSeller_id() {
		return seller_id;
	}
	
	/**
	 * 卖家名称
	 * 
	 * @return seller_name
	 */
	public String getSeller_name() {
		return seller_name;
	}
	
	/**
	 * 转多少积分
	 * 
	 * @return ex_price
	 */
	public BigDecimal getEx_price() {
		return ex_price;
	}
	
	/**
	 * 手机号
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 手续费
	 * 
	 * @return ex_fee
	 */
	public BigDecimal getEx_fee() {
		return ex_fee;
	}
	
	/**
	 * add_time
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 0待审核  1 已审核  2  未通过  3  已失效
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 1 积分转账  2 易物担保
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 订单备注
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * 易物担保验证码
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	

	/**
	 * 订单id
	 * 
	 * @param ex_id
	 */
	public void setEx_id(Integer ex_id) {
		this.ex_id = ex_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @param ex_sn
	 */
	public void setEx_sn(String ex_sn) {
		this.ex_sn = ex_sn;
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
	 * 卖家
	 * 
	 * @param seller_id
	 */
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	
	/**
	 * 卖家名称
	 * 
	 * @param seller_name
	 */
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	
	/**
	 * 转多少积分
	 * 
	 * @param ex_price
	 */
	public void setEx_price(BigDecimal ex_price) {
		this.ex_price = ex_price;
	}
	
	/**
	 * 手机号
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 手续费
	 * 
	 * @param ex_fee
	 */
	public void setEx_fee(BigDecimal ex_fee) {
		this.ex_fee = ex_fee;
	}
	
	/**
	 * add_time
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 0待审核  1 已审核  2  未通过  3  已失效
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 1 积分转账  2 易物担保
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 订单备注
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * 易物担保验证码
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	

}