/**
 * 
 */
package com.api.goods.dao.po;

import java.util.List;

/**
 * @author pubing
 *
 */
public class ZjcGoodsCategoryVO {
	/**
	 * 分类ID
	 */
	private Integer id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类等级
	 */
	private Integer level;
	/**
	 * 分类父ID
	 */
	private Integer parent_id;
	
	/**
	 * 直属子分类
	 */
	private List<ZjcGoodsCategoryVO> tmenu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public List<ZjcGoodsCategoryVO> getTmenu() {
		return tmenu;
	}

	public void setTmenu(List<ZjcGoodsCategoryVO> tmenu) {
		this.tmenu = tmenu;
	}
}
