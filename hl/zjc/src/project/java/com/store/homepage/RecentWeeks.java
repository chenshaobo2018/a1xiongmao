/**
 * 
 */
package com.store.homepage;

import aos.framework.core.typewrap.PO;


/**
 * @author Administrator
 *
 */
public class RecentWeeks extends PO {
	 private static final long serialVersionUID = 1L;
	 private String operation_records;//操作记录
	 private String operation_time;//操作时间
	 public String getOperation_records() {
		return operation_records;
	}
	public void setOperation_records(String operation_records) {
		this.operation_records = operation_records;
	}
	public String getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}
	
	
}
