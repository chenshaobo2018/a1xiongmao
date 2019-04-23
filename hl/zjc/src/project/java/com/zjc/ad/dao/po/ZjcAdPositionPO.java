package com.zjc.ad.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_ad_position[zjc_ad_position]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-04 18:04:16
 */
public class ZjcAdPositionPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer position_id;
	
	/**
	 * 广告位置名称
	 */
	private String position_name;
	
	/**
	 * 广告位宽度
	 */
	private Integer ad_width;
	
	/**
	 * 广告位高度
	 */
	private Integer ad_height;
	
	/**
	 * 广告描述
	 */
	private String position_desc;
	
	/**
	 * 模板
	 */
	private String position_style;
	
	/**
	 * 0关闭1开启
	 */
	private Integer is_open;
	

	/**
	 * 表id
	 * 
	 * @return position_id
	 */
	public Integer getPosition_id() {
		return position_id;
	}
	
	/**
	 * 广告位置名称
	 * 
	 * @return position_name
	 */
	public String getPosition_name() {
		return position_name;
	}
	
	/**
	 * 广告位宽度
	 * 
	 * @return ad_width
	 */
	public Integer getAd_width() {
		return ad_width;
	}
	
	/**
	 * 广告位高度
	 * 
	 * @return ad_height
	 */
	public Integer getAd_height() {
		return ad_height;
	}
	
	/**
	 * 广告描述
	 * 
	 * @return position_desc
	 */
	public String getPosition_desc() {
		return position_desc;
	}
	
	/**
	 * 模板
	 * 
	 * @return position_style
	 */
	public String getPosition_style() {
		return position_style;
	}
	
	/**
	 * 0关闭1开启
	 * 
	 * @return is_open
	 */
	public Integer getIs_open() {
		return is_open;
	}
	

	/**
	 * 表id
	 * 
	 * @param position_id
	 */
	public void setPosition_id(Integer position_id) {
		this.position_id = position_id;
	}
	
	/**
	 * 广告位置名称
	 * 
	 * @param position_name
	 */
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	
	/**
	 * 广告位宽度
	 * 
	 * @param ad_width
	 */
	public void setAd_width(Integer ad_width) {
		this.ad_width = ad_width;
	}
	
	/**
	 * 广告位高度
	 * 
	 * @param ad_height
	 */
	public void setAd_height(Integer ad_height) {
		this.ad_height = ad_height;
	}
	
	/**
	 * 广告描述
	 * 
	 * @param position_desc
	 */
	public void setPosition_desc(String position_desc) {
		this.position_desc = position_desc;
	}
	
	/**
	 * 模板
	 * 
	 * @param position_style
	 */
	public void setPosition_style(String position_style) {
		this.position_style = position_style;
	}
	
	/**
	 * 0关闭1开启
	 * 
	 * @param is_open
	 */
	public void setIs_open(Integer is_open) {
		this.is_open = is_open;
	}
	

}