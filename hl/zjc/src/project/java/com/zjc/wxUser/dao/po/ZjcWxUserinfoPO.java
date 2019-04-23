package com.zjc.wxUser.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_wx_userinfo[zjc_wx_userinfo]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-18 11:30:57
 */
public class ZjcWxUserinfoPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * openid
	 */
	private String openid;
	
	/**
	 * nickname
	 */
	private String nickname;
	
	/**
	 * sex
	 */
	private Integer sex;
	
	/**
	 * language
	 */
	private String language;
	
	/**
	 * city
	 */
	private String city;
	
	/**
	 * province
	 */
	private String province;
	
	/**
	 * country
	 */
	private String country;
	
	/**
	 * headimgurl
	 */
	private String headimgurl;
	
	/**
	 * unionid
	 */
	private String unionid;
	
	/**
	 * 0-app，1-web
	 */
	private Integer type;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * openid
	 * 
	 * @return openid
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * nickname
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * sex
	 * 
	 * @return sex
	 */
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * language
	 * 
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * province
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * country
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * headimgurl
	 * 
	 * @return headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	
	/**
	 * unionid
	 * 
	 * @return unionid
	 */
	public String getUnionid() {
		return unionid;
	}
	
	/**
	 * 0-app，1-web
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * openid
	 * 
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**
	 * nickname
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * sex
	 * 
	 * @param sex
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	/**
	 * language
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * city
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * province
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * country
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * headimgurl
	 * 
	 * @param headimgurl
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	/**
	 * unionid
	 * 
	 * @param unionid
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	/**
	 * 0-app，1-web
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	

}