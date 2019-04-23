package com.api.hl.vo;

import java.sql.Date;

import com.api.hl.dao.po.TGoodsPO;


/**
 * <b>t_goods[t_goods]数据VO对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author Administrator
 * @date 2018-08-06 10:29:19
 */
public class T_goodsVO extends TGoodsPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3807872974859561266L;
	
	/**
	 * 材料类型
	 */
	private String type_name;
	
	/**
	 * 项目名称
	 */
	private String project_name;
	
	/**
	 * 供应商名称
	 */
	private String supplier_name;
	

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	
	
}