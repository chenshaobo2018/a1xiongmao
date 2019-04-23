package com.zjc.ad.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_ad[zjc_ad]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-04 17:47:35
 */
public class ZjcAdPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 广告id
	 */
	private Integer ad_id;
	
	/**
	 * 广告位置ID
	 */
	private Integer pid;
	
	/**
	 * 跳转方式 0不跳转 1商品详情 2跳转文章详情 3跳转链接
	 */
	private Integer jump_type;
	
	/**
	 * 关联ID
	 */
	private Integer relation_id;
	
	/**
	 * 广告名称
	 */
	private String ad_name;
	
	/**
	 * 链接地址
	 */
	private String ad_link;
	
	/**
	 * 图片地址
	 */
	private String ad_code;
	
	/**
	 * 投放时间
	 */
	private Date start_time;
	
	/**
	 * 结束时间
	 */
	private Date end_time;
	
	/**
	 * 添加人
	 */
	private String link_man;
	
	/**
	 * 添加人邮箱
	 */
	private String link_email;
	
	/**
	 * 添加人联系电话
	 */
	private String link_phone;
	
	/**
	 * 点击量
	 */
	private Integer click_count;
	
	/**
	 * 是否显示
	 */
	private Integer enabled;
	
	/**
	 * 排序
	 */
	private Integer orderby;
	
	/**
	 * 是否开启浏览器新窗口
	 */
	private Integer target;
	
	/**
	 * 背景颜色
	 */
	private String bgcolor;
	

	/**
	 * 广告id
	 * 
	 * @return ad_id
	 */
	public Integer getAd_id() {
		return ad_id;
	}
	
	/**
	 * 广告位置ID
	 * 
	 * @return pid
	 */
	public Integer getPid() {
		return pid;
	}
	
	/**
	 * 跳转方式 0不跳转 1商品详情 2跳转文章详情 3跳转链接
	 * 
	 * @return jump_type
	 */
	public Integer getJump_type() {
		return jump_type;
	}
	
	/**
	 * 关联ID
	 * 
	 * @return relation_id
	 */
	public Integer getRelation_id() {
		return relation_id;
	}
	
	/**
	 * 广告名称
	 * 
	 * @return ad_name
	 */
	public String getAd_name() {
		return ad_name;
	}
	
	/**
	 * 链接地址
	 * 
	 * @return ad_link
	 */
	public String getAd_link() {
		return ad_link;
	}
	
	/**
	 * 图片地址
	 * 
	 * @return ad_code
	 */
	public String getAd_code() {
		return ad_code;
	}
	
	/**
	 * 投放时间
	 * 
	 * @return start_time
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * 结束时间
	 * 
	 * @return end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * 添加人
	 * 
	 * @return link_man
	 */
	public String getLink_man() {
		return link_man;
	}
	
	/**
	 * 添加人邮箱
	 * 
	 * @return link_email
	 */
	public String getLink_email() {
		return link_email;
	}
	
	/**
	 * 添加人联系电话
	 * 
	 * @return link_phone
	 */
	public String getLink_phone() {
		return link_phone;
	}
	
	/**
	 * 点击量
	 * 
	 * @return click_count
	 */
	public Integer getClick_count() {
		return click_count;
	}
	
	/**
	 * 是否显示
	 * 
	 * @return enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}
	
	/**
	 * 排序
	 * 
	 * @return orderby
	 */
	public Integer getOrderby() {
		return orderby;
	}
	
	/**
	 * 是否开启浏览器新窗口
	 * 
	 * @return target
	 */
	public Integer getTarget() {
		return target;
	}
	
	/**
	 * 背景颜色
	 * 
	 * @return bgcolor
	 */
	public String getBgcolor() {
		return bgcolor;
	}
	

	/**
	 * 广告id
	 * 
	 * @param ad_id
	 */
	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}
	
	/**
	 * 广告位置ID
	 * 
	 * @param pid
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 跳转方式 0不跳转 1商品详情 2跳转文章详情 3跳转链接
	 * 
	 * @param jump_type
	 */
	public void setJump_type(Integer jump_type) {
		this.jump_type = jump_type;
	}
	
	/**
	 * 关联ID
	 * 
	 * @param relation_id
	 */
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	
	/**
	 * 广告名称
	 * 
	 * @param ad_name
	 */
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	
	/**
	 * 链接地址
	 * 
	 * @param ad_link
	 */
	public void setAd_link(String ad_link) {
		this.ad_link = ad_link;
	}
	
	/**
	 * 图片地址
	 * 
	 * @param ad_code
	 */
	public void setAd_code(String ad_code) {
		this.ad_code = ad_code;
	}
	
	/**
	 * 投放时间
	 * 
	 * @param start_time
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * 结束时间
	 * 
	 * @param end_time
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * 添加人
	 * 
	 * @param link_man
	 */
	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}
	
	/**
	 * 添加人邮箱
	 * 
	 * @param link_email
	 */
	public void setLink_email(String link_email) {
		this.link_email = link_email;
	}
	
	/**
	 * 添加人联系电话
	 * 
	 * @param link_phone
	 */
	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}
	
	/**
	 * 点击量
	 * 
	 * @param click_count
	 */
	public void setClick_count(Integer click_count) {
		this.click_count = click_count;
	}
	
	/**
	 * 是否显示
	 * 
	 * @param enabled
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * 排序
	 * 
	 * @param orderby
	 */
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	
	/**
	 * 是否开启浏览器新窗口
	 * 
	 * @param target
	 */
	public void setTarget(Integer target) {
		this.target = target;
	}
	
	/**
	 * 背景颜色
	 * 
	 * @param bgcolor
	 */
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	

}