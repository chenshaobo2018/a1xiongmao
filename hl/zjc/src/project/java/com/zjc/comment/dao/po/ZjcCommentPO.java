package com.zjc.comment.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_comment[zjc_comment]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-31 09:46:01
 */
public class ZjcCommentPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 评论id
	 */
	private Integer comment_id;
	
	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * email邮箱
	 */
	private String email;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 物流评价等级
	 */
	private Integer deliver_rank;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * ip地址
	 */
	private String ip_address;
	
	/**
	 * 是否显示
	 */
	private Integer is_show;
	
	/**
	 * 父id
	 */
	private Integer parent_id;
	
	/**
	 * 评论用户
	 */
	private BigInteger user_id;
	
	/**
	 * 晒单图片
	 */
	private String img;
	
	/**
	 * 订单id
	 */
	private Integer order_id;
	
	/**
	 * 商品评价等级
	 */
	private Integer goods_rank;
	
	/**
	 * 商家服务态度评价等级
	 */
	private Integer service_rank;
	
	/**
	 * again_content
	 */
	private String again_content;
	
	/**
	 * 物流评价等级
	 */
	private Integer logistics_rank;
	
	/**
	 * 产品质量等级
	 */
	private Integer quality_rank;
	
	/**
	 * 追加评论时间
	 */
	private Date again_time;
	

	/**
	 * 评论id
	 * 
	 * @return comment_id
	 */
	public Integer getComment_id() {
		return comment_id;
	}
	
	/**
	 * 商品id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * email邮箱
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 用户名
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 评论内容
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 物流评价等级
	 * 
	 * @return deliver_rank
	 */
	public Integer getDeliver_rank() {
		return deliver_rank;
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
	 * ip地址
	 * 
	 * @return ip_address
	 */
	public String getIp_address() {
		return ip_address;
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
	 * 父id
	 * 
	 * @return parent_id
	 */
	public Integer getParent_id() {
		return parent_id;
	}
	
	/**
	 * 评论用户
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 晒单图片
	 * 
	 * @return img
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * 订单id
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}
	
	/**
	 * 商品评价等级
	 * 
	 * @return goods_rank
	 */
	public Integer getGoods_rank() {
		return goods_rank;
	}
	
	/**
	 * 商家服务态度评价等级
	 * 
	 * @return service_rank
	 */
	public Integer getService_rank() {
		return service_rank;
	}
	
	/**
	 * again_content
	 * 
	 * @return again_content
	 */
	public String getAgain_content() {
		return again_content;
	}
	
	/**
	 * 物流评价等级
	 * 
	 * @return logistics_rank
	 */
	public Integer getLogistics_rank() {
		return logistics_rank;
	}
	
	/**
	 * 产品质量等级
	 * 
	 * @return quality_rank
	 */
	public Integer getQuality_rank() {
		return quality_rank;
	}
	
	/**
	 * 追加评论时间
	 * 
	 * @return again_time
	 */
	public Date getAgain_time() {
		return again_time;
	}
	

	/**
	 * 评论id
	 * 
	 * @param comment_id
	 */
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	
	/**
	 * 商品id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * email邮箱
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 用户名
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 评论内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 物流评价等级
	 * 
	 * @param deliver_rank
	 */
	public void setDeliver_rank(Integer deliver_rank) {
		this.deliver_rank = deliver_rank;
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
	 * ip地址
	 * 
	 * @param ip_address
	 */
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
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
	 * 父id
	 * 
	 * @param parent_id
	 */
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 评论用户
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 晒单图片
	 * 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * 订单id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 商品评价等级
	 * 
	 * @param goods_rank
	 */
	public void setGoods_rank(Integer goods_rank) {
		this.goods_rank = goods_rank;
	}
	
	/**
	 * 商家服务态度评价等级
	 * 
	 * @param service_rank
	 */
	public void setService_rank(Integer service_rank) {
		this.service_rank = service_rank;
	}
	
	/**
	 * again_content
	 * 
	 * @param again_content
	 */
	public void setAgain_content(String again_content) {
		this.again_content = again_content;
	}
	
	/**
	 * 物流评价等级
	 * 
	 * @param logistics_rank
	 */
	public void setLogistics_rank(Integer logistics_rank) {
		this.logistics_rank = logistics_rank;
	}
	
	/**
	 * 产品质量等级
	 * 
	 * @param quality_rank
	 */
	public void setQuality_rank(Integer quality_rank) {
		this.quality_rank = quality_rank;
	}
	
	/**
	 * 追加评论时间
	 * 
	 * @param again_time
	 */
	public void setAgain_time(Date again_time) {
		this.again_time = again_time;
	}
	

}