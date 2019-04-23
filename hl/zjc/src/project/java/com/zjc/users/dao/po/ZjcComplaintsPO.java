package com.zjc.users.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_complaints[zjc_complaints]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 投诉管理表
 * @author AHei
 * @date 2017-06-05 15:44:07
 */
public class ZjcComplaintsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 投诉ID
	 */
	private Integer id;
	
	/**
	 * 投诉用户ID
	 */
	private String from_user_id;
	
	/**
	 * 被投诉ID
	 */
	private String to_user_id;
	
	/**
	 * 投诉人联系电话
	 */
	private String phone;
	
	/**
	 * 投诉理由
	 */
	private String info;
	
	/**
	 * 投诉状态 1：提交投诉待处理 2：已处理
	 */
	private Integer status;
	
	/**
	 * 管理员备注
	 */
	private String note;
	
	/**
	 * 处理管理员ID
	 */
	private Integer admin_id;
	
	/**
	 * 管理员名
	 */
	private String admin_name;
	
	/**
	 * 投诉时间
	 */
	private Date add_time;
	
	/**
	 * 处理时间
	 */
	private Date handle_time;
	
	/**
	 * 被诉的订单ID
	 */
	private Integer order_id;
	
	/**
	 * 图片，多张图片以都好隔开
	 */
	private String img;
	
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 投诉ID
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 投诉用户ID
	 * 
	 * @return from_user_id
	 */
	public String getFrom_user_id() {
		return from_user_id;
	}
	
	/**
	 * 被投诉ID
	 * 
	 * @return to_user_id
	 */
	public String getTo_user_id() {
		return to_user_id;
	}
	
	/**
	 * 投诉人联系电话
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 投诉理由
	 * 
	 * @return info
	 */
	public String getInfo() {
		return info;
	}
	
	/**
	 * 投诉状态 1：提交投诉待处理 2：已处理
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 管理员备注
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * 处理管理员ID
	 * 
	 * @return admin_id
	 */
	public Integer getAdmin_id() {
		return admin_id;
	}
	
	/**
	 * 管理员名
	 * 
	 * @return admin_name
	 */
	public String getAdmin_name() {
		return admin_name;
	}
	
	/**
	 * 投诉时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 处理时间
	 * 
	 * @return handle_time
	 */
	public Date getHandle_time() {
		return handle_time;
	}
	

	/**
	 * 投诉ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 投诉用户ID
	 * 
	 * @param from_user_id
	 */
	public void setFrom_user_id(String from_user_id) {
		this.from_user_id = from_user_id;
	}
	
	/**
	 * 被投诉ID
	 * 
	 * @param to_user_id
	 */
	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}
	
	/**
	 * 投诉人联系电话
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 投诉理由
	 * 
	 * @param info
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
	 * 投诉状态 1：提交投诉待处理 2：已处理
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 管理员备注
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * 处理管理员ID
	 * 
	 * @param admin_id
	 */
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	
	/**
	 * 管理员名
	 * 
	 * @param admin_name
	 */
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	
	/**
	 * 投诉时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 处理时间
	 * 
	 * @param handle_time
	 */
	public void setHandle_time(Date handle_time) {
		this.handle_time = handle_time;
	}

	/**
	 * 被诉的订单ID
	 * 
	 * @return
	 */
	public Integer getOrder_id() {
		return order_id;
	}

	/**
	 * 被诉的订单ID
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	/**
	 * 图片，多张图片以都好隔开
	 * 
	 * @return
	 */
	public String getImg() {
		return img;
	}

	/**
	 * 图片，多张图片以都好隔开
	 * 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	

}