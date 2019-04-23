package com.zjc.advertising.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_system_interface[zjc_system_interface]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-19 11:10:19
 */
public class ZjcSystemInterfacePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 接口路径
	 */
	private String interfacesd;
	
	/**
	 * 接口名字
	 */
	private String interface_name;
	
	/**
	 * 版本号
	 */
	private String version_number;
	
	/**
	 * 接口描述
	 */
	private String interface_note;
	
	/**
	 * 状态
	 */
	private Integer state;
	
	/**
	 * 添加时间
	 */
	private Date date_time;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 接口路径
	 * 
	 * @return interfacesd
	 */
	public String getInterfacesd() {
		return interfacesd;
	}
	
	/**
	 * 接口名字
	 * 
	 * @return interface_name
	 */
	public String getInterface_name() {
		return interface_name;
	}
	
	/**
	 * 版本号
	 * 
	 * @return version_number
	 */
	public String getVersion_number() {
		return version_number;
	}
	
	/**
	 * 接口描述
	 * 
	 * @return interface_note
	 */
	public String getInterface_note() {
		return interface_note;
	}
	
	/**
	 * 状态
	 * 
	 * @return state
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return date_time
	 */
	public Date getDate_time() {
		return date_time;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 接口路径
	 * 
	 * @param interfacesd
	 */
	public void setInterfacesd(String interfacesd) {
		this.interfacesd = interfacesd;
	}
	
	/**
	 * 接口名字
	 * 
	 * @param interface_name
	 */
	public void setInterface_name(String interface_name) {
		this.interface_name = interface_name;
	}
	
	/**
	 * 版本号
	 * 
	 * @param version_number
	 */
	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}
	
	/**
	 * 接口描述
	 * 
	 * @param interface_note
	 */
	public void setInterface_note(String interface_note) {
		this.interface_note = interface_note;
	}
	
	/**
	 * 状态
	 * 
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param date_time
	 */
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	

}