package com.zjc.prize.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_lottery_draw[zjc_lottery_draw]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-01-09 14:21:39
 */
public class ZjcLotteryDrawPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 抽奖表id
	 */
	private Integer lottery_draw_id;
	
	/**
	 * 剩余抽奖次数
	 */
	private Integer lottery_number;
	
	/**
	 * 会员id号
	 */
	private String user_id;
	
	/**
	 * 微信openid
	 */
	private String open_id;
	

	/**
	 * 抽奖表id
	 * 
	 * @return lottery_draw_id
	 */
	public Integer getLottery_draw_id() {
		return lottery_draw_id;
	}
	
	/**
	 * 剩余抽奖次数
	 * 
	 * @return lottery_number
	 */
	public Integer getLottery_number() {
		return lottery_number;
	}
	
	/**
	 * 会员id号
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * 微信openid
	 * 
	 * @return open_id
	 */
	public String getOpen_id() {
		return open_id;
	}
	

	/**
	 * 抽奖表id
	 * 
	 * @param lottery_draw_id
	 */
	public void setLottery_draw_id(Integer lottery_draw_id) {
		this.lottery_draw_id = lottery_draw_id;
	}
	
	/**
	 * 剩余抽奖次数
	 * 
	 * @param lottery_number
	 */
	public void setLottery_number(Integer lottery_number) {
		this.lottery_number = lottery_number;
	}
	
	/**
	 * 会员id号
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 微信openid
	 * 
	 * @param open_id
	 */
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	

}