package com.api.login.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_vouchers[zjc_vouchers]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-12-20 09:55:10
 */
public class ZjcVouchersPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 代金券表id
	 */
	private String vouchers_id;
	
	/**
	 * 用户id
	 */
	private String user_id;
	
	/**
	 * 是否使用
	 */
	private Integer is_use;
	
	/**
	 * 有效期
	 */
	private Date period_validity;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 使用时间
	 */
	private Date use_time;
	
	/**
	 * 使用订单号
	 */
	private String order_id;
	
	/**
	 * 额度
	 */
	private String voucher_limit;
	
	public String getVoucher_limit() {
		return voucher_limit;
	}

	public void setVoucher_limit(String voucher_limit) {
		this.voucher_limit = voucher_limit;
	}

	/**
	 * 代金券表id
	 * 
	 * @return vouchers_id
	 */
	public String getVouchers_id() {
		return vouchers_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * 是否使用
	 * 
	 * @return is_use
	 */
	public Integer getIs_use() {
		return is_use;
	}
	
	/**
	 * 有效期
	 * 
	 * @return period_validity
	 */
	public Date getPeriod_validity() {
		return period_validity;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 使用时间
	 * 
	 * @return use_time
	 */
	public Date getUse_time() {
		return use_time;
	}
	
	/**
	 * 使用订单号
	 * 
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}
	

	/**
	 * 代金券表id
	 * 
	 * @param vouchers_id
	 */
	public void setVouchers_id(String vouchers_id) {
		this.vouchers_id = vouchers_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 是否使用
	 * 
	 * @param is_use
	 */
	public void setIs_use(Integer is_use) {
		this.is_use = is_use;
	}
	
	/**
	 * 有效期
	 * 
	 * @param period_validity
	 */
	public void setPeriod_validity(Date period_validity) {
		this.period_validity = period_validity;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 使用时间
	 * 
	 * @param use_time
	 */
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	
	/**
	 * 使用订单号
	 * 
	 * @param order_id
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	

}