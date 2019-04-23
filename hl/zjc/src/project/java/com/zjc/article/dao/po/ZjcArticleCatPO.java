package com.zjc.article.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_article_cat[zjc_article_cat]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-07 11:48:02
 */
public class ZjcArticleCatPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer cat_id;
	
	/**
	 * 类别名称
	 */
	private String cat_name;
	
	/**
	 * 系统分组
	 */
	private Integer cat_type;
	
	/**
	 * 夫级ID
	 */
	private Integer parent_id;
	
	/**
	 * 是否导航显示
	 */
	private Integer show_in_nav;
	
	/**
	 * 排序
	 */
	private Integer sort_order;
	
	/**
	 * 分类描述
	 */
	private String cat_desc;
	
	/**
	 * 搜索关键词
	 */
	private String keywords;
	
	/**
	 * 别名
	 */
	private String cat_alias;
	

	/**
	 * 表id
	 * 
	 * @return cat_id
	 */
	public Integer getCat_id() {
		return cat_id;
	}
	
	/**
	 * 类别名称
	 * 
	 * @return cat_name
	 */
	public String getCat_name() {
		return cat_name;
	}
	
	/**
	 * 系统分组
	 * 
	 * @return cat_type
	 */
	public Integer getCat_type() {
		return cat_type;
	}
	
	/**
	 * 夫级ID
	 * 
	 * @return parent_id
	 */
	public Integer getParent_id() {
		return parent_id;
	}
	
	/**
	 * 是否导航显示
	 * 
	 * @return show_in_nav
	 */
	public Integer getShow_in_nav() {
		return show_in_nav;
	}
	
	/**
	 * 排序
	 * 
	 * @return sort_order
	 */
	public Integer getSort_order() {
		return sort_order;
	}
	
	/**
	 * 分类描述
	 * 
	 * @return cat_desc
	 */
	public String getCat_desc() {
		return cat_desc;
	}
	
	/**
	 * 搜索关键词
	 * 
	 * @return keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * 别名
	 * 
	 * @return cat_alias
	 */
	public String getCat_alias() {
		return cat_alias;
	}
	

	/**
	 * 表id
	 * 
	 * @param cat_id
	 */
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	
	/**
	 * 类别名称
	 * 
	 * @param cat_name
	 */
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	/**
	 * 系统分组
	 * 
	 * @param cat_type
	 */
	public void setCat_type(Integer cat_type) {
		this.cat_type = cat_type;
	}
	
	/**
	 * 夫级ID
	 * 
	 * @param parent_id
	 */
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 是否导航显示
	 * 
	 * @param show_in_nav
	 */
	public void setShow_in_nav(Integer show_in_nav) {
		this.show_in_nav = show_in_nav;
	}
	
	/**
	 * 排序
	 * 
	 * @param sort_order
	 */
	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}
	
	/**
	 * 分类描述
	 * 
	 * @param cat_desc
	 */
	public void setCat_desc(String cat_desc) {
		this.cat_desc = cat_desc;
	}
	
	/**
	 * 搜索关键词
	 * 
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 别名
	 * 
	 * @param cat_alias
	 */
	public void setCat_alias(String cat_alias) {
		this.cat_alias = cat_alias;
	}
	

}