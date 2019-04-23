package com.zjc.users.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_user_log[zjc_user_log]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-12 09:41:29
 */
public class ZjcUserLogPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 操作时间
	 */
	private Date time;
	
	/**
	 *  操作类型
	 */
	private String type;
	
	/**
	 * 描述
	 */
	private String descs;
	
	/**
	 * 展示类型；0表示不展示在APP上，1表示要展示在APP上
	 */
	private int show_type; 
	

	/**
	 * 展示类型;0表示不展示在APP上，1表示要展示在APP上
	 * 
	 * @return
	 */
	public int getShow_type() {
		return show_type;
	}

	/**
	 * 展示类型；0表示不展示在APP上，1表示要展示在APP上
	 * 
	 * @param show_type
	 */
	public void setShow_type(int show_type) {
		this.show_type = show_type;
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
	 * 操作时间
	 * 
	 * @return time
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 *  操作类型
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 描述
	 * 
	 * @return desc
	 */
	public String getDescs() {
		return descs;
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
	 * 操作时间
	 * 
	 * @param time
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	/**
	 *  操作类型
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 描述
	 * 
	 * @param desc
	 */
	public void setDescs(String descs) {
		this.descs = descs;
	}

	public ZjcUserLogPO(BigInteger user_id, String type, String descs) {
		super();
		this.user_id = user_id;
		this.type = type;
		this.descs = descs;
	}

	public ZjcUserLogPO() {
		super();
	}
}