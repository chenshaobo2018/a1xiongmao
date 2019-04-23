package com.zjc.store.dao.po;

import java.math.BigDecimal;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_company_public[zjc_company_public]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-15 10:02:17
 */
public class ZjcCompanyPublicPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * cp_id
	 */
	private Integer cp_id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 添加时间
	 */
	private Integer add_time;
	
	/**
	 * 图片
	 */
	private String img;
	
	/**
	 * 图片七牛地址
	 */
	private String img_qn;
	
	/**
	 * 等额积分价
	 */
	private BigDecimal cost_price;
	
	/**
	 * 查看次数
	 */
	private Integer click_num;
	
	/**
	 * 是否显示
	 */
	private Integer is_show;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 用户是否购买了企业宣传
	 */
	private boolean hasBuy=false;
	/**
	 * cp_id
	 * 
	 * @return cp_id
	 */
	public Integer getCp_id() {
		return cp_id;
	}
	
	/**
	 * 标题
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Integer getAdd_time() {
		return add_time;
	}
	
	/**
	 * 图片
	 * 
	 * @return img
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * 图片七牛地址
	 * 
	 * @return img_qn
	 */
	public String getImg_qn() {
		return img_qn;
	}
	
	/**
	 * 等额积分价
	 * 
	 * @return cost_price
	 */
	public BigDecimal getCost_price() {
		return cost_price;
	}
	
	/**
	 * 查看次数
	 * 
	 * @return click_num
	 */
	public Integer getClick_num() {
		return click_num;
	}
	
	/**
	 * 是否显示
	 * 
	 * @return is_show
	 */
	public Integer getIs_show() {
		return is_show;
	}
	
	/**
	 * 排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * 内容
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	

	/**
	 * cp_id
	 * 
	 * @param cp_id
	 */
	public void setCp_id(Integer cp_id) {
		this.cp_id = cp_id;
	}
	
	/**
	 * 标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Integer add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 图片
	 * 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * 图片七牛地址
	 * 
	 * @param img_qn
	 */
	public void setImg_qn(String img_qn) {
		this.img_qn = img_qn;
	}
	
	/**
	 * 等额积分价
	 * 
	 * @param cost_price
	 */
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	
	/**
	 * 查看次数
	 * 
	 * @param click_num
	 */
	public void setClick_num(Integer click_num) {
		this.click_num = click_num;
	}
	
	/**
	 * 是否显示
	 * 
	 * @param is_show
	 */
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	
	/**
	 * 排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * 内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public boolean isHasBuy() {
		return hasBuy;
	}

	public void setHasBuy(boolean hasBuy) {
		this.hasBuy = hasBuy;
	}
	

}