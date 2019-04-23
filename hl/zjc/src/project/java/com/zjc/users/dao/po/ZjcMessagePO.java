package com.zjc.users.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_message[zjc_message]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-06 09:55:22
 */
public class ZjcMessagePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * message_id
	 */
	private Integer message_id;
	
	/**
	 * 管理者id
	 */
	private String admin_id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 站内信内容
	 */
	private String message;
	
	/**
	 * 个体消息：0，全体消息1
	 */
	private Integer type;
	
	/**
	 * 发送时间
	 */
	private Date send_time;
	
	/**
	 * 是否弹出 0-不弹出 1-弹出
	 */
	private Integer is_alert;
	
	/**
	 * 弹出截至日期
	 */
	private Date alert_deth_line;
	
	/**
	 * 是否显示 默认显示1
	 */
	private Integer is_display;
	

	/**
	 * message_id
	 * 
	 * @return message_id
	 */
	public Integer getMessage_id() {
		return message_id;
	}
	
	/**
	 * 管理者id
	 * 
	 * @return admin_id
	 */
	public String getAdmin_id() {
		return admin_id;
	}
	
	/**
	 * 标题
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 站内信内容
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 个体消息：0，全体消息1
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 发送时间
	 * 
	 * @return send_time
	 */
	public Date getSend_time() {
		return send_time;
	}
	
	/**
	 * 是否弹出 0-不弹出 1-弹出
	 * 
	 * @return is_alert
	 */
	public Integer getIs_alert() {
		return is_alert;
	}
	
	/**
	 * 弹出截至日期
	 * 
	 * @return alert_deth_line
	 */
	public Date getAlert_deth_line() {
		return alert_deth_line;
	}
	
	/**
	 * 是否显示 默认显示1
	 * 
	 * @return is_display
	 */
	public Integer getIs_display() {
		return is_display;
	}
	

	/**
	 * message_id
	 * 
	 * @param message_id
	 */
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	
	/**
	 * 管理者id
	 * 
	 * @param admin_id
	 */
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	
	/**
	 * 标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 站内信内容
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 个体消息：0，全体消息1
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 发送时间
	 * 
	 * @param send_time
	 */
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	/**
	 * 是否弹出 0-不弹出 1-弹出
	 * 
	 * @param is_alert
	 */
	public void setIs_alert(Integer is_alert) {
		this.is_alert = is_alert;
	}
	
	/**
	 * 弹出截至日期
	 * 
	 * @param alert_deth_line
	 */
	public void setAlert_deth_line(Date alert_deth_line) {
		this.alert_deth_line = alert_deth_line;
	}
	
	/**
	 * 是否显示 默认显示1
	 * 
	 * @param is_display
	 */
	public void setIs_display(Integer is_display) {
		this.is_display = is_display;
	}
	

}