package com.zjc.system.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_member_settlement[zjc_member_settlement]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-13 16:52:00
 */
public class ZjcMemberSettlementPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 购买结算中心所需积分
	 */
	private String settlement_center_points;
	
	/**
	 * 结算中心提成比例
	 */
	private String settlement_center_commission;
	
	/**
	 * 添加时间
	 */
	private Date date_time;
	
	/**
	 * 结算每天不能超过值
	 */
	private int sum_settlement;
	/**
	 * 每天限制转账限制
	 */
	private int sum_points_today;
	/**
	 * 转账单笔限制最少
	 */
	private int points_other_min;
	
	private int update_time;

	public int getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(int update_time) {
		this.update_time = update_time;
	}

	public int getSum_settlement() {
		return sum_settlement;
	}

	public void setSum_settlement(int sum_settlement) {
		this.sum_settlement = sum_settlement;
	}

	public int getSum_points_today() {
		return sum_points_today;
	}

	public void setSum_points_today(int sum_points_today) {
		this.sum_points_today = sum_points_today;
	}

	public int getPoints_other_min() {
		return points_other_min;
	}

	public void setPoints_other_min(int points_other_min) {
		this.points_other_min = points_other_min;
	}

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 购买结算中心所需积分
	 * 
	 * @return settlement_center_points
	 */
	public String getSettlement_center_points() {
		return settlement_center_points;
	}
	
	/**
	 * 结算中心提成比例
	 * 
	 * @return settlement_center_commission
	 */
	public String getSettlement_center_commission() {
		return settlement_center_commission;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return date_time
	 */
	public Date getDate_time() {
		return date_time;
	}
	

	/**
	 * 表id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 购买结算中心所需积分
	 * 
	 * @param settlement_center_points
	 */
	public void setSettlement_center_points(String settlement_center_points) {
		this.settlement_center_points = settlement_center_points;
	}
	
	/**
	 * 结算中心提成比例
	 * 
	 * @param settlement_center_commission
	 */
	public void setSettlement_center_commission(String settlement_center_commission) {
		this.settlement_center_commission = settlement_center_commission;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param date_time
	 */
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	

}
