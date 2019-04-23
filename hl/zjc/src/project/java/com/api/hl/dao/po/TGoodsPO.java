package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

import java.util.Date;
import java.util.List;

/**
 * <b>t_goods[t_goods]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-03-05 10:01:09
 */
public class TGoodsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * type_id
	 */
	private String type_id;
	
	/**
	 * material_name
	 */
	private String material_name;
	
	/**
	 * unit
	 */
	private String unit;
	
	/**
	 * brand
	 */
	private String brand;
	
	/**
	 * price
	 */
	private String price;
	
	/**
	 * img_src
	 */
	private String img_src;
	
	/**
	 * note
	 */
	private String note;
	
	/**
	 * project_id
	 */
	private String project_id;
	
	/**
	 * supplier_id
	 */
	private String supplier_id;
	
	/**
	 * createTime
	 */
	private Date createTime;
	
    private List<TSpecificationsPO> specificationsPO;
	
	private String num;
	private String cat_id;
	

	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
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
	 * type_id
	 * 
	 * @return type_id
	 */
	public String getType_id() {
		return type_id;
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
	 * unit
	 * 
	 * @return unit
	 */
	public String getUnit() {
		return unit;
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
	 * price
	 * 
	 * @return price
	 */
	public String getPrice() {
		return price;
	}
	
	/**
	 * img_src
	 * 
	 * @return img_src
	 */
	public String getImg_src() {
		return img_src;
	}
	
	/**
	 * note
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * project_id
	 * 
	 * @return project_id
	 */
	public String getProject_id() {
		return project_id;
	}
	
	/**
	 * supplier_id
	 * 
	 * @return supplier_id
	 */
	public String getSupplier_id() {
		return supplier_id;
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
	 * type_id
	 * 
	 * @param type_id
	 */
	public void setType_id(String type_id) {
		this.type_id = type_id;
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
	 * unit
	 * 
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * brand
	 * 
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
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
	 * img_src
	 * 
	 * @param img_src
	 */
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}
	
	/**
	 * note
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * project_id
	 * 
	 * @param project_id
	 */
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	
	/**
	 * supplier_id
	 * 
	 * @param supplier_id
	 */
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<TSpecificationsPO> getSpecificationsPO() {
		return specificationsPO;
	}

	public void setSpecificationsPO(List<TSpecificationsPO> specificationsPO) {
		this.specificationsPO = specificationsPO;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	

}