package com.zjc.goodsImage.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_goods_images[zjc_goods_images]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-29 15:44:42
 */
public class ZjcGoodsImagesPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 图片id 自增
	 */
	private Integer img_id;
	
	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * 图片地址
	 */
	private String image_url;
	

	/**
	 * 图片id 自增
	 * 
	 * @return img_id
	 */
	public Integer getImg_id() {
		return img_id;
	}
	
	/**
	 * 商品id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * 图片地址
	 * 
	 * @return image_url
	 */
	public String getImage_url() {
		return image_url;
	}
	

	/**
	 * 图片id 自增
	 * 
	 * @param img_id
	 */
	public void setImg_id(Integer img_id) {
		this.img_id = img_id;
	}
	
	/**
	 * 商品id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * 图片地址
	 * 
	 * @param image_url
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	

}