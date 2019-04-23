package com.wxactivity.share.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>share_game[share_game]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-02-06 16:32:13
 */
public class ShareGamePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * game_id
	 */
	private String game_id;
	
	/**
	 * 激活码
	 */
	private String game_account;
	
	/**
	 * 游戏激活码名称
	 */
	private String game_name;
	
	/**
	 * 是否抽奖0未抽 1已抽
	 */
	private String type;
	

	/**
	 * game_id
	 * 
	 * @return game_id
	 */
	public String getGame_id() {
		return game_id;
	}
	
	/**
	 * 激活码
	 * 
	 * @return game_account
	 */
	public String getGame_account() {
		return game_account;
	}
	
	/**
	 * 游戏激活码名称
	 * 
	 * @return game_name
	 */
	public String getGame_name() {
		return game_name;
	}
	
	/**
	 * 是否抽奖0未抽 1已抽
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	

	/**
	 * game_id
	 * 
	 * @param game_id
	 */
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	
	/**
	 * 激活码
	 * 
	 * @param game_account
	 */
	public void setGame_account(String game_account) {
		this.game_account = game_account;
	}
	
	/**
	 * 游戏激活码名称
	 * 
	 * @param game_name
	 */
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	
	/**
	 * 是否抽奖0未抽 1已抽
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	

}