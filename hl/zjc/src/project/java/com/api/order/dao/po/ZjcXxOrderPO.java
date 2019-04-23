package com.api.order.dao.po;

import java.math.BigDecimal;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_xx_order[zjc_xx_order]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-20 15:44:25
 */
public class ZjcXxOrderPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 线下购物ID
	 */
	private Integer xx_id;
	
	/**
	 * 订单编号
	 */
	private String order_sn;
	
	/**
	 * 用户ID
	 */
	private Integer user_id;
	
	/**
	 * 超市商家会员ID
	 */
	private Integer seller_user_id;
	
	/**
	 * 订单总额
	 */
	private BigDecimal total_amount;
	
	/**
	 * 商家得到的金额
	 */
	private BigDecimal seller_amount;
	
	/**
	 * 下单支付时间
	 */
	private Date add_time;
	

	/**
	 * 线下购物ID
	 * 
	 * @return xx_id
	 */
	public Integer getXx_id() {
		return xx_id;
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
	public Integer getUser_id() {
		return user_id;
	}
	
	/**
	 * 超市商家会员ID
	 * 
	 * @return seller_user_id
	 */
	public Integer getSeller_user_id() {
		return seller_user_id;
	}
	
	/**
	 * 订单总额
	 * 
	 * @return total_amount
	 */
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	
	/**
	 * 商家得到的金额
	 * 
	 * @return seller_amount
	 */
	public BigDecimal getSeller_amount() {
		return seller_amount;
	}
	
	/**
	 * 下单支付时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	

	/**
	 * 线下购物ID
	 * 
	 * @param xx_id
	 */
	public void setXx_id(Integer xx_id) {
		this.xx_id = xx_id;
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
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 超市商家会员ID
	 * 
	 * @param seller_user_id
	 */
	public void setSeller_user_id(Integer seller_user_id) {
		this.seller_user_id = seller_user_id;
	}
	
	/**
	 * 订单总额
	 * 
	 * @param total_amount
	 */
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	
	/**
	 * 商家得到的金额
	 * 
	 * @param seller_amount
	 */
	public void setSeller_amount(BigDecimal seller_amount) {
		this.seller_amount = seller_amount;
	}
	
	/**
	 * 下单支付时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	

}