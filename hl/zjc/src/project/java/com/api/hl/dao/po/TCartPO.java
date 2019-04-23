package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_cart[t_cart]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:09
 */
public class TCartPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * user_id
	 */
	private String user_id;
	
	/**
	 * goods_id
	 */
	private String goods_id;
	
	/**
	 * num
	 */
	private String num;
	
	/**
	 * type_id
	 */
	private String type_id;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
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
	 * goods_id
	 * 
	 * @return goods_id
	 */
	public String getGoods_id() {
		return goods_id;
	}
	
	/**
	 * num
	 * 
	 * @return num
	 */
	public String getNum() {
		return num;
	}
	
	/**
	 * type_id
	 * 
	 * @return type_id
	 */
	public String getType_id() {
		return type_id;
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
	 * user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	 * num
	 * 
	 * @param num
	 */
	public void setNum(String num) {
		this.num = num;
	}
	
	/**
	 * type_id
	 * 
	 * @param type_id
	 */
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	

}