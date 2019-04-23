package com.zjc.goods.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_brand[zjc_brand]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-06 15:57:52
 */
public class ZjcBrandPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 品牌表
	 */
	private Integer id;
	
	/**
	 * 品牌名称
	 */
	private String name;
	
	/**
	 * 品牌logo
	 */
	private String logo;
	
	/**
	 * 品牌地址
	 */
	private String url;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 品牌分类
	 */
	private String cat_name;
	
	/**
	 * 分类id
	 */
	private Integer parent_cat_id;
	
	/**
	 * 分类id
	 */
	private Integer cat_id;
	
	/**
	 * 是否推荐
	 */
	private Integer is_hot;
	

	/**
	 * 品牌表
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 品牌名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 品牌logo
	 * 
	 * @return logo
	 */
	public String getLogo() {
		return logo;
	}
	
	/**
	 * 品牌地址
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * 品牌分类
	 * 
	 * @return cat_name
	 */
	public String getCat_name() {
		return cat_name;
	}
	
	/**
	 * 分类id
	 * 
	 * @return parent_cat_id
	 */
	public Integer getParent_cat_id() {
		return parent_cat_id;
	}
	
	/**
	 * 分类id
	 * 
	 * @return cat_id
	 */
	public Integer getCat_id() {
		return cat_id;
	}
	
	/**
	 * 是否推荐
	 * 
	 * @return is_hot
	 */
	public Integer getIs_hot() {
		return is_hot;
	}
	

	/**
	 * 品牌表
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 品牌名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 品牌logo
	 * 
	 * @param logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/**
	 * 品牌地址
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * 品牌分类
	 * 
	 * @param cat_name
	 */
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	/**
	 * 分类id
	 * 
	 * @param parent_cat_id
	 */
	public void setParent_cat_id(Integer parent_cat_id) {
		this.parent_cat_id = parent_cat_id;
	}
	
	/**
	 * 分类id
	 * 
	 * @param cat_id
	 */
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	
	/**
	 * 是否推荐
	 * 
	 * @param is_hot
	 */
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	

}