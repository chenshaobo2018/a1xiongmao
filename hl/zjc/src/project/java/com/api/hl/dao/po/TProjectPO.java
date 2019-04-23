package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_project[t_project]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:10
 */
public class TProjectPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * project_name
	 */
	private String project_name;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * project_name
	 * 
	 * @return project_name
	 */
	public String getProject_name() {
		return project_name;
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
	 * project_name
	 * 
	 * @param project_name
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	

}