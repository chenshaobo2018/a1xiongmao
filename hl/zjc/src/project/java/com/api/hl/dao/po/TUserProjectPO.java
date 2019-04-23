package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_user_project[t_user_project]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:12
 */
public class TUserProjectPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * user_id
	 */
	private String user_id;
	
	/**
	 * project_id
	 */
	private String project_id;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * user_id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * project_id
	 * 
	 * @return project_id
	 */
	public String getProject_id() {
		return project_id;
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
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * project_id
	 * 
	 * @param project_id
	 */
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	

}