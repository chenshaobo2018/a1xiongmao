/**
 * 
 */
package com.zjc.article.dao.po;

/**
 * @author pubing
 *
 */
public class TypeVo {
	/**
	 * 返回的类型编号
	 */
	private Integer value;
	/**
	 * 显示的字符串
	 */
	private String display;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public TypeVo(Integer value, String display) {
		super();
		this.value = value;
		this.display = display;
	}
}
