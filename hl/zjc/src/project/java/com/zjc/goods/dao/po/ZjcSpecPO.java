package com.zjc.goods.dao.po;


import java.util.List;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_spec[zjc_spec]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-14 09:18:09
 */
public class ZjcSpecPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 规格表
	 */
	private Integer id;
	
	/**
	 * 规格类型
	 */
	private Integer type_id;
	
	/**
	 * 规格名称
	 */
	private String name;
	
	/**
	 * 排序
	 */
	private Integer order;
	
	/**
	 * 是否需要检索：1是，0否
	 */
	private Integer search_index;
	
	/**
	 * 规格项名称集
	 */
	private String specItems;
	
	/**
	 * 规格类型名称
	 */
	private String type_name;
	
	/**
	 * 规格详细表
	 */
	private List<ZjcSpecItemPO> zjcSpecItemPOList;
	
	
	
	public List<ZjcSpecItemPO> getZjcSpecItemPOList() {
		return zjcSpecItemPOList;
	}

	public void setZjcSpecItemPOList(List<ZjcSpecItemPO> zjcSpecItemPOList) {
		this.zjcSpecItemPOList = zjcSpecItemPOList;
	}

	/**
	 * 规格表
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 规格类型
	 * 
	 * @return type_id
	 */
	public Integer getType_id() {
		return type_id;
	}
	
	/**
	 * 规格名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 排序
	 * 
	 * @return order
	 */
	public Integer getOrder() {
		return order;
	}
	
	/**
	 * 是否需要检索：1是，0否
	 * 
	 * @return search_index
	 */
	public Integer getSearch_index() {
		return search_index;
	}
	

	/**
	 * 规格表
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 规格类型
	 * 
	 * @param type_id
	 */
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * 规格名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 排序
	 * 
	 * @param order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	/**
	 * 是否需要检索：1是，0否
	 * 
	 * @param search_index
	 */
	public void setSearch_index(Integer search_index) {
		this.search_index = search_index;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getSpecItems() {
		return specItems;
	}

	public void setSpecItems(String specItems) {
		this.specItems = specItems;
	}
	
}