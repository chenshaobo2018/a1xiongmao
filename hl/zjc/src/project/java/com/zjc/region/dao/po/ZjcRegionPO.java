package com.zjc.region.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_region[zjc_region]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-10 15:13:48
 */
public class ZjcRegionPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 索引ID
	 */
	private Integer id;
	
	/**
	 * 地区名称
	 */
	private String name;
	
	/**
	 * 地区父ID
	 */
	private Integer parent_id;
	
	/**
	 * 排序
	 */
	private Integer area_sort;
	
	/**
	 * 地区深度，从1开始
	 */
	private Integer level;
	

	/**
	 * 索引ID
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 地区名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 地区父ID
	 * 
	 * @return parent_id
	 */
	public Integer getParent_id() {
		return parent_id;
	}
	
	/**
	 * 排序
	 * 
	 * @return area_sort
	 */
	public Integer getArea_sort() {
		return area_sort;
	}
	
	/**
	 * 地区深度，从1开始
	 * 
	 * @return level
	 */
	public Integer getLevel() {
		return level;
	}
	

	/**
	 * 索引ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 地区名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 地区父ID
	 * 
	 * @param parent_id
	 */
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 排序
	 * 
	 * @param area_sort
	 */
	public void setArea_sort(Integer area_sort) {
		this.area_sort = area_sort;
	}
	
	/**
	 * 地区深度，从1开始
	 * 
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	

}