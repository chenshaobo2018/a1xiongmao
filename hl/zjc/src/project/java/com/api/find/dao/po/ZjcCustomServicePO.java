package com.api.find.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_custom_service[zjc_custom_service]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-16 09:50:30
 */
public class ZjcCustomServicePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 客服ID
	 */
	private Integer id;
	
	/**
	 * 部门
	 */
	private String server_depart;
	
	/**
	 * 电话
	 */
	private String server_tel;
	
	/**
	 * 服务时间
	 */
	private String server_duration;
	
	/**
	 * 区域
	 */
	private String server_district;
	
	/**
	 * 国家
	 */
	private String server_contury;
	
	/**
	 * 是否启用 0-禁用 1-启用
	 */
	private Integer enable;
	
	/**
	 * 排序
	 */
	private Integer sort;
	

	/**
	 * 客服ID
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 部门
	 * 
	 * @return server_depart
	 */
	public String getServer_depart() {
		return server_depart;
	}
	
	/**
	 * 电话
	 * 
	 * @return server_tel
	 */
	public String getServer_tel() {
		return server_tel;
	}
	
	/**
	 * 服务时间
	 * 
	 * @return server_duration
	 */
	public String getServer_duration() {
		return server_duration;
	}
	
	/**
	 * 区域
	 * 
	 * @return server_district
	 */
	public String getServer_district() {
		return server_district;
	}
	
	/**
	 * 国家
	 * 
	 * @return server_contury
	 */
	public String getServer_contury() {
		return server_contury;
	}
	
	/**
	 * 是否启用 0-禁用 1-启用
	 * 
	 * @return enable
	 */
	public Integer getEnable() {
		return enable;
	}
	
	/**
	 * 排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}
	

	/**
	 * 客服ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 部门
	 * 
	 * @param server_depart
	 */
	public void setServer_depart(String server_depart) {
		this.server_depart = server_depart;
	}
	
	/**
	 * 电话
	 * 
	 * @param server_tel
	 */
	public void setServer_tel(String server_tel) {
		this.server_tel = server_tel;
	}
	
	/**
	 * 服务时间
	 * 
	 * @param server_duration
	 */
	public void setServer_duration(String server_duration) {
		this.server_duration = server_duration;
	}
	
	/**
	 * 区域
	 * 
	 * @param server_district
	 */
	public void setServer_district(String server_district) {
		this.server_district = server_district;
	}
	
	/**
	 * 国家
	 * 
	 * @param server_contury
	 */
	public void setServer_contury(String server_contury) {
		this.server_contury = server_contury;
	}
	
	/**
	 * 是否启用 0-禁用 1-启用
	 * 
	 * @param enable
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	/**
	 * 排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	

}