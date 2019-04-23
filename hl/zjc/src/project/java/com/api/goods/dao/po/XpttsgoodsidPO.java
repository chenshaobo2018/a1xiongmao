package com.api.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>xpttsgoodsid[xpttsgoodsid]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-12-11 09:55:53
 */
public class XpttsgoodsidPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * goods_id
	 */
	private String goods_id;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
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
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
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
	

}