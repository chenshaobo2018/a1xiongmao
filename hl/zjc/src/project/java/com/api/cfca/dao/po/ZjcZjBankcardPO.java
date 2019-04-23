package com.api.cfca.dao.po;

import aos.framework.core.typewrap.PO;
import java.math.BigInteger;

/**
 * <b>zjc_zj_bankcard[zjc_zj_bankcard]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-05-16 09:37:29
 */
public class ZjcZjBankcardPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * user_id
	 */
	private BigInteger user_id;
	
	/**
	 * 绑定流水号
	 */
	private String txsnbinding;
	
	/**
	 * 银行id
	 */
	private String bank_id;
	
	/**
	 * 账号名称
	 */
	private String accountname;
	
	/**
	 * 银行卡号
	 */
	private String accountnumber;
	
	/**
	 * 证件类型
	 */
	private String identificationtype;
	
	/**
	 * 证件号码
	 */
	private String identificationnumber;
	
	/**
	 * 手机号
	 */
	private String phonenumber;
	
	/**
	 * 卡类型
	 */
	private String cardtype;
	private String banktext;
	

	/**
	 * user_id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 绑定流水号
	 * 
	 * @return txsnbinding
	 */
	public String getTxsnbinding() {
		return txsnbinding;
	}
	
	/**
	 * 银行id
	 * 
	 * @return bank_id
	 */
	public String getBank_id() {
		return bank_id;
	}
	
	/**
	 * 账号名称
	 * 
	 * @return accountname
	 */
	public String getAccountname() {
		return accountname;
	}
	
	/**
	 * 银行卡号
	 * 
	 * @return accountnumber
	 */
	public String getAccountnumber() {
		return accountnumber;
	}
	
	/**
	 * 证件类型
	 * 
	 * @return identificationtype
	 */
	public String getIdentificationtype() {
		return identificationtype;
	}
	
	/**
	 * 证件号码
	 * 
	 * @return identificationnumber
	 */
	public String getIdentificationnumber() {
		return identificationnumber;
	}
	
	/**
	 * 手机号
	 * 
	 * @return phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	
	/**
	 * 卡类型
	 * 
	 * @return cardtype
	 */
	public String getCardtype() {
		return cardtype;
	}
	

	/**
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 绑定流水号
	 * 
	 * @param txsnbinding
	 */
	public void setTxsnbinding(String txsnbinding) {
		this.txsnbinding = txsnbinding;
	}
	
	/**
	 * 银行id
	 * 
	 * @param bank_id
	 */
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	
	/**
	 * 账号名称
	 * 
	 * @param accountname
	 */
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	/**
	 * 银行卡号
	 * 
	 * @param accountnumber
	 */
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	
	/**
	 * 证件类型
	 * 
	 * @param identificationtype
	 */
	public void setIdentificationtype(String identificationtype) {
		this.identificationtype = identificationtype;
	}
	
	/**
	 * 证件号码
	 * 
	 * @param identificationnumber
	 */
	public void setIdentificationnumber(String identificationnumber) {
		this.identificationnumber = identificationnumber;
	}
	
	/**
	 * 手机号
	 * 
	 * @param phonenumber
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/**
	 * 卡类型
	 * 
	 * @param cardtype
	 */
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getBanktext() {
		return banktext;
	}

	public void setBanktext(String banktext) {
		this.banktext = banktext;
	}
	

}