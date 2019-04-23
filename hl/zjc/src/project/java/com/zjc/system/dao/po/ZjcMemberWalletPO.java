package com.zjc.system.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_member_wallet[zjc_member_wallet]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-13 17:47:13
 */
public class ZjcMemberWalletPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer id;
	
	/**
	 * 钱包扩增比例
	 */
	private String wallet_amplification_ratio;
	
	/**
	 * 添加时间
	 */
	private Date date_time;
	

	/**
	 * 表id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 钱包扩增比例
	 * 
	 * @return wallet_amplification_ratio
	 */
	public String getWallet_amplification_ratio() {
		return wallet_amplification_ratio;
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
	 * 钱包扩增比例
	 * 
	 * @param wallet_amplification_ratio
	 */
	public void setWallet_amplification_ratio(String wallet_amplification_ratio) {
		this.wallet_amplification_ratio = wallet_amplification_ratio;
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