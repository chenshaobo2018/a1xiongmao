package com.api.goods.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_activitiy[zjc_activitiy]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-02-07 15:43:09
 */
public class ZjcActivitiyPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * time_id
	 */
	private String time_id;
	
	/**
	 * activities_began
	 */
	private Date activities_began;
	
	/**
	 * activities_end
	 */
	private Date activities_end;
	
	/**
	 * activities_name
	 */
	private String activities_name;
	

	/**
	 * time_id
	 * 
	 * @return time_id
	 */
	public String getTime_id() {
		return time_id;
	}
	
	/**
	 * activities_began
	 * 
	 * @return activities_began
	 */
	public Date getActivities_began() {
		return activities_began;
	}
	
	/**
	 * activities_end
	 * 
	 * @return activities_end
	 */
	public Date getActivities_end() {
		return activities_end;
	}
	
	/**
	 * activities_name
	 * 
	 * @return activities_name
	 */
	public String getActivities_name() {
		return activities_name;
	}
	

	/**
	 * time_id
	 * 
	 * @param time_id
	 */
	public void setTime_id(String time_id) {
		this.time_id = time_id;
	}
	
	/**
	 * activities_began
	 * 
	 * @param activities_began
	 */
	public void setActivities_began(Date activities_began) {
		this.activities_began = activities_began;
	}
	
	/**
	 * activities_end
	 * 
	 * @param activities_end
	 */
	public void setActivities_end(Date activities_end) {
		this.activities_end = activities_end;
	}
	
	/**
	 * activities_name
	 * 
	 * @param activities_name
	 */
	public void setActivities_name(String activities_name) {
		this.activities_name = activities_name;
	}
	

}