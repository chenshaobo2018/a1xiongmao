package com.api.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>xptgoods[xptgoods]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-12-06 15:07:04
 */
public class XptgoodsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品货号
	 */
	private String goods_sn;
	
	/**
	 * 类型
	 */
	private String xpt_type;
	
	/**
	 * 主键id
	 */
	private Integer id;
	

	/**
	 * 商品货号
	 * 
	 * @return goods_sn
	 */
	public String getGoods_sn() {
		return goods_sn;
	}
	
	/**
	 * 类型
	 * 
	 * @return xpt_type
	 */
	public String getXpt_type() {
		return xpt_type;
	}
	
	/**
	 * 主键id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	

	/**
	 * 商品货号
	 * 
	 * @param goods_sn
	 */
	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}
	
	/**
	 * 类型
	 * 
	 * @param xpt_type
	 */
	public void setXpt_type(String xpt_type) {
		this.xpt_type = xpt_type;
	}
	
	/**
	 * 主键id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	

}