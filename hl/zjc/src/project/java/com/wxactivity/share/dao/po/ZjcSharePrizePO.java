package com.wxactivity.share.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_share_prize[zjc_share_prize]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-02-06 16:32:13
 */
public class ZjcSharePrizePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 奖品ID
	 */
	private Integer share_prize_id;
	
	/**
	 * 奖品名称
	 */
	private String share_prize_name;
	
	/**
	 * 奖品（剩余）数量
	 */
	private Integer share_prize_amount;
	
	/**
	 * 奖品权重
	 */
	private Integer share_prize_weight;
	/**
	 * 消费积分
	 */
	private Integer pay_points;
	
	

	public Integer getPay_points() {
		return pay_points;
	}

	public void setPay_points(Integer pay_points) {
		this.pay_points = pay_points;
	}

	/**
	 * 奖品ID
	 * 
	 * @return share_prize_id
	 */
	public Integer getShare_prize_id() {
		return share_prize_id;
	}
	
	/**
	 * 奖品名称
	 * 
	 * @return share_prize_name
	 */
	public String getShare_prize_name() {
		return share_prize_name;
	}
	
	/**
	 * 奖品（剩余）数量
	 * 
	 * @return share_prize_amount
	 */
	public Integer getShare_prize_amount() {
		return share_prize_amount;
	}
	
	/**
	 * 奖品权重
	 * 
	 * @return share_prize_weight
	 */
	public Integer getShare_prize_weight() {
		return share_prize_weight;
	}
	

	/**
	 * 奖品ID
	 * 
	 * @param share_prize_id
	 */
	public void setShare_prize_id(Integer share_prize_id) {
		this.share_prize_id = share_prize_id;
	}
	
	/**
	 * 奖品名称
	 * 
	 * @param share_prize_name
	 */
	public void setShare_prize_name(String share_prize_name) {
		this.share_prize_name = share_prize_name;
	}
	
	/**
	 * 奖品（剩余）数量
	 * 
	 * @param share_prize_amount
	 */
	public void setShare_prize_amount(Integer share_prize_amount) {
		this.share_prize_amount = share_prize_amount;
	}
	
	/**
	 * 奖品权重
	 * 
	 * @param share_prize_weight
	 */
	public void setShare_prize_weight(Integer share_prize_weight) {
		this.share_prize_weight = share_prize_weight;
	}
	

}