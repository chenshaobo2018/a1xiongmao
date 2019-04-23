package com.api.pintuan.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>spell_group_relational[spell_group_relational]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-03-23 15:20:38
 */
public class SpellGroupRelationalPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * order_id
	 */
	private String order_id;
	private String pin_order_id;
	
	/**
	 * goods_id
	 */
	private String goods_id;
	
	/**
	 * user_id
	 */
	private String user_id;
	
	/**
	 * 是否是团长
	 */
	private Integer is_head;
	
	/**
	 * open_id
	 */
	private String open_id;
	
	/**
	 * 头像
	 */
	private String head_img;
	
	/**
	 * 昵称
	 */
	private String nike_name;
	private Integer sum;
	

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public String getPin_order_id() {
		return pin_order_id;
	}

	public void setPin_order_id(String pin_order_id) {
		this.pin_order_id = pin_order_id;
	}

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * order_id
	 * 
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
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
	 * user_id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * 是否是团长
	 * 
	 * @return is_head
	 */
	public Integer getIs_head() {
		return is_head;
	}
	
	/**
	 * open_id
	 * 
	 * @return open_id
	 */
	public String getOpen_id() {
		return open_id;
	}
	
	/**
	 * 头像
	 * 
	 * @return head_img
	 */
	public String getHead_img() {
		return head_img;
	}
	
	/**
	 * 昵称
	 * 
	 * @return nike_name
	 */
	public String getNike_name() {
		return nike_name;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * order_id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 是否是团长
	 * 
	 * @param is_head
	 */
	public void setIs_head(Integer is_head) {
		this.is_head = is_head;
	}
	
	/**
	 * open_id
	 * 
	 * @param open_id
	 */
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	/**
	 * 头像
	 * 
	 * @param head_img
	 */
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	
	/**
	 * 昵称
	 * 
	 * @param nike_name
	 */
	public void setNike_name(String nike_name) {
		this.nike_name = nike_name;
	}
	

}