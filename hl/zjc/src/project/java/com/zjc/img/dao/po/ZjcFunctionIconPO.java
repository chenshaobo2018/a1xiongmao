package com.zjc.img.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_function_icon[zjc_function_icon]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-08-23 10:51:14
 */
public class ZjcFunctionIconPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 图片路径
	 */
	private String icon;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * 类型
	 */
	private Integer type;
	
	/**
	 * 是否显示
	 */
	private Integer shows;
	
	/**
	 * sort
	 */
	private Integer sort;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 图片路径
	 * 
	 * @return icon
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 类型
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 是否显示
	 * 
	 * @return show
	 */
	public Integer getShows() {
		return shows;
	}
	
	/**
	 * sort
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
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
	 * 图片路径
	 * 
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 类型
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 是否显示
	 * 
	 * @param show
	 */
	public void setShows(Integer shows) {
		this.shows = shows;
	}
	
	/**
	 * sort
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	

}