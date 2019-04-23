package com.api.hl.vo;

import com.api.hl.dao.po.TUserProjectPO;

public class T_user_projectVO extends TUserProjectPO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5683908880317604939L;
	private String project_name;

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	

}
