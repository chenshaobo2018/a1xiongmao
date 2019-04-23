package com.zjc.users.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_user_operate_log[zjc_user_operate_log]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-10-17 09:35:47
 */
public class ZjcUserOperateLogPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 操作人ID
	 */
	private Integer user_id;
	
	/**
	 * 操作类型
	 */
	private String operate_type;
	
	/**
	 * 操作人
	 */
	private String user_name;
	
	/**
	 * 操作时间
	 */
	private Date operate_time;
	
	/**
	 * 操作备注
	 */
	private String descs;
	

	/**
	 * 操作人ID
	 * 
	 * @return user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	
	/**
	 * 操作类型
	 * 
	 * @return operate_type
	 */
	public String getOperate_type() {
		return operate_type;
	}
	
	/**
	 * 操作人
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * 操作时间
	 * 
	 * @return operate_time
	 */
	public Date getOperate_time() {
		return operate_time;
	}
	
	/**
	 * 操作备注
	 * 
	 * @return descs
	 */
	public String getDescs() {
		return descs;
	}
	

	/**
	 * 操作人ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 操作类型
	 * 
	 * @param operate_type
	 */
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	
	/**
	 * 操作人
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * 操作时间
	 * 
	 * @param operate_time
	 */
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
	
	/**
	 * 操作备注
	 * 
	 * @param descs
	 */
	public void setDescs(String descs) {
		this.descs = descs;
	}
	

}