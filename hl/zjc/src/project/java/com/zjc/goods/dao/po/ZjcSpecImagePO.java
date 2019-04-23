package com.zjc.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_spec_image[zjc_spec_image]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-14 09:18:09
 */
public class ZjcSpecImagePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品规格图片表id
	 */
	private Integer goods_id;
	
	/**
	 * 规格项id
	 */
	private Integer spec_image_id;
	
	/**
	 * 商品规格图片路径
	 */
	private String src;
	

	/**
	 * 商品规格图片表id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * 规格项id
	 * 
	 * @return spec_image_id
	 */
	public Integer getSpec_image_id() {
		return spec_image_id;
	}
	
	/**
	 * 商品规格图片路径
	 * 
	 * @return src
	 */
	public String getSrc() {
		return src;
	}
	

	/**
	 * 商品规格图片表id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * 规格项id
	 * 
	 * @param spec_image_id
	 */
	public void setSpec_image_id(Integer spec_image_id) {
		this.spec_image_id = spec_image_id;
	}
	
	/**
	 * 商品规格图片路径
	 * 
	 * @param src
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	

}