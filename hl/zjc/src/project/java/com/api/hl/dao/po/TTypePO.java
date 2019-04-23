package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_type[t_type]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:11
 */
public class TTypePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * type_name
	 */
	private String type_name;
	
	/**
	 * sorting
	 */
	private String sorting;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * type_name
	 * 
	 * @return type_name
	 */
	public String getType_name() {
		return type_name;
	}
	
	/**
	 * sorting
	 * 
	 * @return sorting
	 */
	public String getSorting() {
		return sorting;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * type_name
	 * 
	 * @param type_name
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	/**
	 * sorting
	 * 
	 * @param sorting
	 */
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	

}