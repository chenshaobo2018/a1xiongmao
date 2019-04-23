package com.wxactivity.share.dao.po;

import aos.framework.core.typewrap.PO;

import java.math.BigInteger;
import java.util.Date;

/**
 * <b>wx_share_activity[wx_share_activity]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-01-31 17:44:11
 */
public class WxShareActivityPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * share_id
	 */
	private String share_id;
	
	/**
	 * 分享人open_id
	 */
	private String share_open_id;
	
	/**
	 * goods_id
	 */
	private String goods_id;
	
	/**
	 * 分享获得积分数
	 */
	private String share_integral;
	
	/**
	 * 商品积分数
	 */
	private String goods_integral;
	
	/**
	 * 助力人open_id
	 */
	private String open_id;
	
	/**
	 * 助力人头像
	 */
	private String img;
	
	/**
	 * 助力人昵称
	 */
	private String nickname;
	
	/**
	 * 是否兑换0未兑换1已兑换
	 */
	private String type;
	private BigInteger user_id;
	
	public BigInteger getUser_id() {
		return user_id;
	}

	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}

	/**
	 * 添加时间
	 */
	private Date addtime;
	private String goods_name;
	private String original_img;
	private String market_price;
	

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getOriginal_img() {
		return original_img;
	}

	public void setOriginal_img(String original_img) {
		this.original_img = original_img;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	/**
	 * share_id
	 * 
	 * @return share_id
	 */
	public String getShare_id() {
		return share_id;
	}
	
	/**
	 * 分享人open_id
	 * 
	 * @return share_open_id
	 */
	public String getShare_open_id() {
		return share_open_id;
	}
	
	/**
	 * goods_id
	 * 
	 * @return goods_id
	 */
	public String getGoods_id() {
		return goods_id;
	}
	
	/**
	 * 分享获得积分数
	 * 
	 * @return share_integral
	 */
	public String getShare_integral() {
		return share_integral;
	}
	
	/**
	 * 商品积分数
	 * 
	 * @return goods_integral
	 */
	public String getGoods_integral() {
		return goods_integral;
	}
	
	/**
	 * 助力人open_id
	 * 
	 * @return open_id
	 */
	public String getOpen_id() {
		return open_id;
	}
	
	/**
	 * 助力人头像
	 * 
	 * @return img
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * 助力人昵称
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * 是否兑换0未兑换1已兑换
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return addtime
	 */
	public Date getAddtime() {
		return addtime;
	}
	

	/**
	 * share_id
	 * 
	 * @param share_id
	 */
	public void setShare_id(String share_id) {
		this.share_id = share_id;
	}
	
	/**
	 * 分享人open_id
	 * 
	 * @param share_open_id
	 */
	public void setShare_open_id(String share_open_id) {
		this.share_open_id = share_open_id;
	}
	
	/**
	 * goods_id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * 分享获得积分数
	 * 
	 * @param share_integral
	 */
	public void setShare_integral(String share_integral) {
		this.share_integral = share_integral;
	}
	
	/**
	 * 商品积分数
	 * 
	 * @param goods_integral
	 */
	public void setGoods_integral(String goods_integral) {
		this.goods_integral = goods_integral;
	}
	
	/**
	 * 助力人open_id
	 * 
	 * @param open_id
	 */
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	/**
	 * 助力人头像
	 * 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * 助力人昵称
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 是否兑换0未兑换1已兑换
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param addtime
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	

}