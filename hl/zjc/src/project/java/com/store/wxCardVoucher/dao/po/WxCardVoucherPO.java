package com.store.wxCardVoucher.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>wx_card_voucher[wx_card_voucher]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-05-30 17:00:05
 */
public class WxCardVoucherPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 微信卡券ID
	 */
	private String card_id;
	
	/**
	 * 店铺ID
	 */
	private Integer store_id;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 卡券类型，代金券：CASH；折扣券：DISCOUNT；兑换券：GIFT；优惠券：GENERAL_COUPON
	 */
	private String card_type;
	
	/**
	 * 卡券名称
	 */
	private String card_title;
	
	/**
	 * 卡券库存数量
	 */
	private String quantity;
	
	/**
	 * 1表示固定日期区间，2表示固定时长 （自领取后按天算。)
	 */
	private String type;
	
	/**
	 * 固定日期有效期开始日期(type为0专用)
	 */
	private Date start_time;
	
	/**
	 * 固定日期有效期结束日期(type为0专用)
	 */
	private Date end_time;
	
	/**
	 * 表示自领取后多少天内有效，不支持填写0。(type为1专用)
	 */
	private Integer fixed_term;
	
	/**
	 * 表示自领取后多少天开始生效，领取后当天生效填写0。(type为1专用)
	 */
	private Integer fixed_begin;
	
	/**
	 * 折扣额度（折扣券专用，表示打折额度（百分比）。填30就是七折。）
	 */
	private String discount;
	
	/**
	 * 二维码地址
	 */
	private String show_qrcode_url;
	
	/**
	 * 券logo地址
	 */
	private String logo_url;
	

	/**
	 * 微信卡券ID
	 * 
	 * @return card_id
	 */
	public String getCard_id() {
		return card_id;
	}
	
	/**
	 * 店铺ID
	 * 
	 * @return store_id
	 */
	public Integer getStore_id() {
		return store_id;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 卡券类型，代金券：CASH；折扣券：DISCOUNT；兑换券：GIFT；优惠券：GENERAL_COUPON
	 * 
	 * @return card_type
	 */
	public String getCard_type() {
		return card_type;
	}
	
	/**
	 * 卡券名称
	 * 
	 * @return card_title
	 */
	public String getCard_title() {
		return card_title;
	}
	
	/**
	 * 卡券库存数量
	 * 
	 * @return quantity
	 */
	public String getQuantity() {
		return quantity;
	}
	
	/**
	 * 1表示固定日期区间，2表示固定时长 （自领取后按天算。)
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 固定日期有效期开始日期(type为0专用)
	 * 
	 * @return start_time
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * 固定日期有效期结束日期(type为0专用)
	 * 
	 * @return end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * 表示自领取后多少天内有效，不支持填写0。(type为1专用)
	 * 
	 * @return fixed_term
	 */
	public Integer getFixed_term() {
		return fixed_term;
	}
	
	/**
	 * 表示自领取后多少天开始生效，领取后当天生效填写0。(type为1专用)
	 * 
	 * @return fixed_begin
	 */
	public Integer getFixed_begin() {
		return fixed_begin;
	}
	
	/**
	 * 折扣额度（折扣券专用，表示打折额度（百分比）。填30就是七折。）
	 * 
	 * @return discount
	 */
	public String getDiscount() {
		return discount;
	}
	
	/**
	 * 二维码地址
	 * 
	 * @return show_qrcode_url
	 */
	public String getShow_qrcode_url() {
		return show_qrcode_url;
	}
	

	/**
	 * 微信卡券ID
	 * 
	 * @param card_id
	 */
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	
	/**
	 * 店铺ID
	 * 
	 * @param store_id
	 */
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 卡券类型，代金券：CASH；折扣券：DISCOUNT；兑换券：GIFT；优惠券：GENERAL_COUPON
	 * 
	 * @param card_type
	 */
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	
	/**
	 * 卡券名称
	 * 
	 * @param card_title
	 */
	public void setCard_title(String card_title) {
		this.card_title = card_title;
	}
	
	/**
	 * 卡券库存数量
	 * 
	 * @param quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 1表示固定日期区间，2表示固定时长 （自领取后按天算。)
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 固定日期有效期开始日期(type为0专用)
	 * 
	 * @param start_time
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * 固定日期有效期结束日期(type为0专用)
	 * 
	 * @param end_time
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * 表示自领取后多少天内有效，不支持填写0。(type为1专用)
	 * 
	 * @param fixed_term
	 */
	public void setFixed_term(Integer fixed_term) {
		this.fixed_term = fixed_term;
	}
	
	/**
	 * 表示自领取后多少天开始生效，领取后当天生效填写0。(type为1专用)
	 * 
	 * @param fixed_begin
	 */
	public void setFixed_begin(Integer fixed_begin) {
		this.fixed_begin = fixed_begin;
	}
	
	/**
	 * 折扣额度（折扣券专用，表示打折额度（百分比）。填30就是七折。）
	 * 
	 * @param discount
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	/**
	 * 二维码地址
	 * 
	 * @param show_qrcode_url
	 */
	public void setShow_qrcode_url(String show_qrcode_url) {
		this.show_qrcode_url = show_qrcode_url;
	}
	
	/**
	 * @return  券logo地址
	 */
	public String getLogo_url() {
		return logo_url;
	}

	/**
	 *  券logo地址
	 * 
	 * @param logo_url
	 */
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	
	

}