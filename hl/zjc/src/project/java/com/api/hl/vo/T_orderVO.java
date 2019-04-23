package com.api.hl.vo;

import com.api.hl.dao.po.TOrderPO;


public class T_orderVO extends TOrderPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4320010976959616742L;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
