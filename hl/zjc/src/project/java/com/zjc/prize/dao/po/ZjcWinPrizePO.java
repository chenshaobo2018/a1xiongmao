package com.zjc.prize.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_win_prize[zjc_win_prize]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-01-09 14:21:15
 */
public class ZjcWinPrizePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 中奖id
	 */
	private Integer win_prize_id;
	
	/**
	 * 中奖人id
	 */
	private String user_id;
	
	/**
	 * 电话号码
	 */
	private String phone_num;
	
	/**
	 * 中奖时间
	 */
	private Date win_time;
	
	/**
	 * 奖品id
	 */
	private Integer prize_id;
	
	/**
	 * 奖品名称
	 */
	private String prize_name;
	
	private String open_id;
	private String is_use;
	

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getIs_use() {
		return is_use;
	}

	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}
	

	/**
	 * 中奖id
	 * 
	 * @return win_prize_id
	 */
	public Integer getWin_prize_id() {
		return win_prize_id;
	}
	
	/**
	 * 中奖人id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * 电话号码
	 * 
	 * @return phone_num
	 */
	public String getPhone_num() {
		return phone_num;
	}
	
	/**
	 * 中奖时间
	 * 
	 * @return win_time
	 */
	public Date getWin_time() {
		return win_time;
	}
	
	/**
	 * 奖品id
	 * 
	 * @return prize_id
	 */
	public Integer getPrize_id() {
		return prize_id;
	}
	

	/**
	 * 中奖id
	 * 
	 * @param win_prize_id
	 */
	public void setWin_prize_id(Integer win_prize_id) {
		this.win_prize_id = win_prize_id;
	}
	
	/**
	 * 中奖人id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 电话号码
	 * 
	 * @param phone_num
	 */
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	/**
	 * 中奖时间
	 * 
	 * @param win_time
	 */
	public void setWin_time(Date win_time) {
		this.win_time = win_time;
	}
	
	/**
	 * 奖品id
	 * 
	 * @param prize_id
	 */
	public void setPrize_id(Integer prize_id) {
		this.prize_id = prize_id;
	}

	/**
	 * 奖品名称
	 * @return
	 */
	public String getPrize_name() {
		return prize_name;
	}

	/**
	 * 奖品名称
	 * @param prize_name
	 */
	public void setPrize_name(String prize_name) {
		this.prize_name = prize_name;
	}
	
}