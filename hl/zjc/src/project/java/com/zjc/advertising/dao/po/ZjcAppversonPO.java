package com.zjc.advertising.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_appverson[zjc_appverson]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-19 09:54:18
 */
/**
 * @author Administrator
 *
 */
public class ZjcAppversonPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * app类型
	 */
	private Integer app_type;
	
	/**
	 * 版本号
	 */
	private Integer app_verson;
	
	/**
	 * 0 不强制升级，1 强制升级
	 */
	private Integer is_force;
	
	/**
	 * 升级日志
	 */
	private String verson_log;
	
	/**
	 * 下载路径
	 */
	private String download_url;
	
	/**
	 * md5
	 */
	private String md5;
	
	/**
	 * 版本号名称
	 */
	private String app_verson_name;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 0表示用户端；1表示商家端
	 */
	private Integer type;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * app类型
	 * 
	 * @return app_type
	 */
	public Integer getApp_type() {
		return app_type;
	}
	
	/**
	 * 版本号
	 * 
	 * @return app_verson
	 */
	public Integer getApp_verson() {
		return app_verson;
	}
	
	/**
	 * 0 不强制升级，1 强制升级
	 * 
	 * @return is_force
	 */
	public Integer getIs_force() {
		return is_force;
	}
	
	/**
	 * 升级日志
	 * 
	 * @return verson_log
	 */
	public String getVerson_log() {
		return verson_log;
	}
	
	/**
	 * 下载路径
	 * 
	 * @return download_url
	 */
	public String getDownload_url() {
		return download_url;
	}
	
	/**
	 * md5
	 * 
	 * @return md5
	 */
	public String getMd5() {
		return md5;
	}
	
	/**
	 * 版本号名称
	 * 
	 * @return app_verson_name
	 */
	public String getApp_verson_name() {
		return app_verson_name;
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
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * app类型
	 * 
	 * @param app_type
	 */
	public void setApp_type(Integer app_type) {
		this.app_type = app_type;
	}
	
	/**
	 * 版本号
	 * 
	 * @param app_verson
	 */
	public void setApp_verson(Integer app_verson) {
		this.app_verson = app_verson;
	}
	
	/**
	 * 0 不强制升级，1 强制升级
	 * 
	 * @param is_force
	 */
	public void setIs_force(Integer is_force) {
		this.is_force = is_force;
	}
	
	/**
	 * 升级日志
	 * 
	 * @param verson_log
	 */
	public void setVerson_log(String verson_log) {
		this.verson_log = verson_log;
	}
	
	/**
	 * 下载路径
	 * 
	 * @param download_url
	 */
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	
	/**
	 * md5
	 * 
	 * @param md5
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	/**
	 * 版本号名称
	 * 
	 * @param app_verson_name
	 */
	public void setApp_verson_name(String app_verson_name) {
		this.app_verson_name = app_verson_name;
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
	 * 0表示用户端；1表示商家端
	 * 
	 * @return
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 0表示用户端；1表示商家端
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}