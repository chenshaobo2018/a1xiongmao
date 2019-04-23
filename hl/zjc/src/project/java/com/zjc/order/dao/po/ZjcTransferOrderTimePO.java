package com.zjc.order.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_transfer_order_time[zjc_transfer_order_time]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-04-17 09:51:52
 */
public class ZjcTransferOrderTimePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * order_id
	 */
	private Integer order_id;
	
	/**
	 * 转账时间
	 */
	private Date transfer_time;
	
	/**
	 * transfer_type
	 */
	private Integer transfer_type;
	
	/**
	 * 催单
	 */
	private Integer reminder;
	
	/**
	 * 催单时间
	 */
	private Date remindertime;
	
	/**
	 * 退单0发起退单1退单失败2退单成功
	 */
	private Integer single_back;
	
	/**
	 * 退单时间
	 */
	private Date single_back_time;
	
	/**
	 * 确认退单时间
	 */
	private Date confirm_single_back_time;
	

	/**
	 * order_id
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	
	/**
	 * 转账时间
	 * 
	 * @return transfer_time
	 */
	public Date getTransfer_time() {
		return transfer_time;
	}
	
	/**
	 * transfer_type
	 * 
	 * @return transfer_type
	 */
	public Integer getTransfer_type() {
		return transfer_type;
	}
	
	/**
	 * 催单
	 * 
	 * @return reminder
	 */
	public Integer getReminder() {
		return reminder;
	}
	
	/**
	 * 催单时间
	 * 
	 * @return remindertime
	 */
	public Date getRemindertime() {
		return remindertime;
	}
	
	/**
	 * 退单0发起退单1退单失败2退单成功
	 * 
	 * @return single_back
	 */
	public Integer getSingle_back() {
		return single_back;
	}
	
	/**
	 * 退单时间
	 * 
	 * @return single_back_time
	 */
	public Date getSingle_back_time() {
		return single_back_time;
	}
	
	/**
	 * 确认退单时间
	 * 
	 * @return confirm_single_back_time
	 */
	public Date getConfirm_single_back_time() {
		return confirm_single_back_time;
	}
	

	/**
	 * order_id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 转账时间
	 * 
	 * @param transfer_time
	 */
	public void setTransfer_time(Date transfer_time) {
		this.transfer_time = transfer_time;
	}
	
	/**
	 * transfer_type
	 * 
	 * @param transfer_type
	 */
	public void setTransfer_type(Integer transfer_type) {
		this.transfer_type = transfer_type;
	}
	
	/**
	 * 催单
	 * 
	 * @param reminder
	 */
	public void setReminder(Integer reminder) {
		this.reminder = reminder;
	}
	
	/**
	 * 催单时间
	 * 
	 * @param remindertime
	 */
	public void setRemindertime(Date remindertime) {
		this.remindertime = remindertime;
	}
	
	/**
	 * 退单0发起退单1退单失败2退单成功
	 * 
	 * @param single_back
	 */
	public void setSingle_back(Integer single_back) {
		this.single_back = single_back;
	}
	
	/**
	 * 退单时间
	 * 
	 * @param single_back_time
	 */
	public void setSingle_back_time(Date single_back_time) {
		this.single_back_time = single_back_time;
	}
	
	/**
	 * 确认退单时间
	 * 
	 * @param confirm_single_back_time
	 */
	public void setConfirm_single_back_time(Date confirm_single_back_time) {
		this.confirm_single_back_time = confirm_single_back_time;
	}
	

}