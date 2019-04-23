package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_order_detailed[t_order_detailed]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:10
 */
public class TOrderDetailedPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * order_id
	 */
	private String order_id;
	
	/**
	 * material_name
	 */
	private String material_name;
	
	/**
	 * number
	 */
	private String number;
	
	/**
	 * sum_price
	 */
	private String sum_price;
	
	/**
	 * adjust_price
	 */
	private String adjust_price;
	
	private int all_receive_number;//该订单的总的实际收货数量，数据库无该字段
	public int getAll_receive_number() {
		return all_receive_number;
	}

	public void setAll_receive_number(int all_receive_number) {
		this.all_receive_number = all_receive_number;
	}
	
	/**
	 * specifications_id
	 */
	private String specifications_id;
	

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
	 * material_name
	 * 
	 * @return material_name
	 */
	public String getMaterial_name() {
		return material_name;
	}
	
	/**
	 * number
	 * 
	 * @return number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * sum_price
	 * 
	 * @return sum_price
	 */
	public String getSum_price() {
		return sum_price;
	}
	
	/**
	 * adjust_price
	 * 
	 * @return adjust_price
	 */
	public String getAdjust_price() {
		return adjust_price;
	}
	
	/**
	 * specifications_id
	 * 
	 * @return specifications_id
	 */
	public String getSpecifications_id() {
		return specifications_id;
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
	 * material_name
	 * 
	 * @param material_name
	 */
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	
	/**
	 * number
	 * 
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * sum_price
	 * 
	 * @param sum_price
	 */
	public void setSum_price(String sum_price) {
		this.sum_price = sum_price;
	}
	
	/**
	 * adjust_price
	 * 
	 * @param adjust_price
	 */
	public void setAdjust_price(String adjust_price) {
		this.adjust_price = adjust_price;
	}
	
	/**
	 * specifications_id
	 * 
	 * @param specifications_id
	 */
	public void setSpecifications_id(String specifications_id) {
		this.specifications_id = specifications_id;
	}
	

}