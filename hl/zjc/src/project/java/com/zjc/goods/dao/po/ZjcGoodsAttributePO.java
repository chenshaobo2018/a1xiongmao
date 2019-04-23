package com.zjc.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_goods_attribute[zjc_goods_attribute]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-02 11:50:20
 */
public class ZjcGoodsAttributePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 属性id
	 */
	private Integer attr_id;
	
	/**
	 * 属性名称
	 */
	private String attr_name;
	
	/**
	 * 属性分类id
	 */
	private Integer type_id;
	
	/**
	 * 0不需要检索 1关键字检索 2范围检索
	 */
	private Integer attr_index;
	
	/**
	 * 0唯一属性 1单选属性 2复选属性
	 */
	private Integer attr_type;
	
	/**
	 *  0 手工录入 1从列表中选择 2多行文本框
	 */
	private Integer attr_input_type;
	
	/**
	 * 可选值列表
	 */
	private String attr_values;
	
	/**
	 * 属性排序
	 */
	private Integer order;
	
	/**
	 * 类型名称
	 */
	private String name;
	

	/**
	 * 属性id
	 * 
	 * @return attr_id
	 */
	public Integer getAttr_id() {
		return attr_id;
	}
	
	/**
	 * 属性名称
	 * 
	 * @return attr_name
	 */
	public String getAttr_name() {
		return attr_name;
	}
	
	/**
	 * 属性分类id
	 * 
	 * @return type_id
	 */
	public Integer getType_id() {
		return type_id;
	}
	
	/**
	 * 0不需要检索 1关键字检索 2范围检索
	 * 
	 * @return attr_index
	 */
	public Integer getAttr_index() {
		return attr_index;
	}
	
	/**
	 * 0唯一属性 1单选属性 2复选属性
	 * 
	 * @return attr_type
	 */
	public Integer getAttr_type() {
		return attr_type;
	}
	
	/**
	 *  0 手工录入 1从列表中选择 2多行文本框
	 * 
	 * @return attr_input_type
	 */
	public Integer getAttr_input_type() {
		return attr_input_type;
	}
	
	/**
	 * 可选值列表
	 * 
	 * @return attr_values
	 */
	public String getAttr_values() {
		return attr_values;
	}
	
	/**
	 * 属性排序
	 * 
	 * @return order
	 */
	public Integer getOrder() {
		return order;
	}
	

	/**
	 * 属性id
	 * 
	 * @param attr_id
	 */
	public void setAttr_id(Integer attr_id) {
		this.attr_id = attr_id;
	}
	
	/**
	 * 属性名称
	 * 
	 * @param attr_name
	 */
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	
	/**
	 * 属性分类id
	 * 
	 * @param type_id
	 */
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * 0不需要检索 1关键字检索 2范围检索
	 * 
	 * @param attr_index
	 */
	public void setAttr_index(Integer attr_index) {
		this.attr_index = attr_index;
	}
	
	/**
	 * 0唯一属性 1单选属性 2复选属性
	 * 
	 * @param attr_type
	 */
	public void setAttr_type(Integer attr_type) {
		this.attr_type = attr_type;
	}
	
	/**
	 *  0 手工录入 1从列表中选择 2多行文本框
	 * 
	 * @param attr_input_type
	 */
	public void setAttr_input_type(Integer attr_input_type) {
		this.attr_input_type = attr_input_type;
	}
	
	/**
	 * 可选值列表
	 * 
	 * @param attr_values
	 */
	public void setAttr_values(String attr_values) {
		this.attr_values = attr_values;
	}
	
	/**
	 * 属性排序
	 * 
	 * @param order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}