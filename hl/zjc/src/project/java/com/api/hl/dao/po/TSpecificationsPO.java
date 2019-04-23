package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_specifications[t_specifications]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:10
 */
public class TSpecificationsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * goods_id
	 */
	private String goods_id;
	
	/**
	 * specifications_name
	 */
	private String specifications_name;
	
	/**
	 * price
	 */
	private String price;
	
	/**
	 * brand
	 */
	private String brand;
	
	private Integer repertory_number;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
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
	 * specifications_name
	 * 
	 * @return specifications_name
	 */
	public String getSpecifications_name() {
		return specifications_name;
	}
	
	/**
	 * price
	 * 
	 * @return price
	 */
	public String getPrice() {
		return price;
	}
	
	/**
	 * brand
	 * 
	 * @return brand
	 */
	public String getBrand() {
		return brand;
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
	 * goods_id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * specifications_name
	 * 
	 * @param specifications_name
	 */
	public void setSpecifications_name(String specifications_name) {
		this.specifications_name = specifications_name;
	}
	
	/**
	 * price
	 * 
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * brand
	 * 
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getRepertory_number() {
		return repertory_number;
	}

	public void setRepertory_number(Integer repertory_number) {
		this.repertory_number = repertory_number;
	}
	
	

}