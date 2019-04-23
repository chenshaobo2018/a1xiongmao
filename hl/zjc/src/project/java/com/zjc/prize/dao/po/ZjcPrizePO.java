package com.zjc.prize.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_prize[zjc_prize]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-01-08 10:26:46
 */
public class ZjcPrizePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 奖品ID
	 */
	private Integer prize_id;
	
	/**
	 * 奖品名称
	 */
	private String prize_name;
	
	/**
	 * 奖品（剩余）数量
	 */
	private Integer prize_amount;
	
	/**
	 * 奖品权重
	 */
	private Integer prize_weight;
	

	/**
	 * 奖品ID
	 * 
	 * @return prize_id
	 */
	public Integer getPrize_id() {
		return prize_id;
	}
	
	/**
	 * 奖品名称
	 * 
	 * @return prize_name
	 */
	public String getPrize_name() {
		return prize_name;
	}
	
	/**
	 * 奖品（剩余）数量
	 * 
	 * @return prize_amount
	 */
	public Integer getPrize_amount() {
		return prize_amount;
	}
	
	/**
	 * 奖品权重
	 * 
	 * @return prize_weight
	 */
	public Integer getPrize_weight() {
		return prize_weight;
	}
	

	/**
	 * 奖品ID
	 * 
	 * @param prize_id
	 */
	public void setPrize_id(Integer prize_id) {
		this.prize_id = prize_id;
	}
	
	/**
	 * 奖品名称
	 * 
	 * @param prize_name
	 */
	public void setPrize_name(String prize_name) {
		this.prize_name = prize_name;
	}
	
	/**
	 * 奖品（剩余）数量
	 * 
	 * @param prize_amount
	 */
	public void setPrize_amount(Integer prize_amount) {
		this.prize_amount = prize_amount;
	}
	
	/**
	 * 奖品权重
	 * 
	 * @param prize_weight
	 */
	public void setPrize_weight(Integer prize_weight) {
		this.prize_weight = prize_weight;
	}
	

}