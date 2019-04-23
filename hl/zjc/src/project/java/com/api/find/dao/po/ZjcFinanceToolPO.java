package com.api.find.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_finance_tool[zjc_finance_tool]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-14 14:54:37
 */
public class ZjcFinanceToolPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * finance_icon
	 */
	private String finance_icon;
	
	/**
	 * 工具名称
	 */
	private String finance_name;
	
	/**
	 * 工具描述
	 */
	private String finance_desc;
	
	/**
	 * 链接类型 0-app下载路径 1-url地址
	 */
	private Integer finance_type;
	
	/**
	 * 工具链接
	 */
	private String finance_url;
	
	/**
	 * 是否启用 0-禁用 1-启用
	 */
	private Integer enable;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * finance_icon
	 * 
	 * @return finance_icon
	 */
	public String getFinance_icon() {
		return finance_icon;
	}
	
	/**
	 * 工具名称
	 * 
	 * @return finance_name
	 */
	public String getFinance_name() {
		return finance_name;
	}
	
	/**
	 * 工具描述
	 * 
	 * @return finance_desc
	 */
	public String getFinance_desc() {
		return finance_desc;
	}
	
	/**
	 * 链接类型 0-app下载路径 1-url地址
	 * 
	 * @return finance_type
	 */
	public Integer getFinance_type() {
		return finance_type;
	}
	
	/**
	 * 工具链接
	 * 
	 * @return finance_url
	 */
	public String getFinance_url() {
		return finance_url;
	}
	
	/**
	 * 是否启用 0-禁用 1-启用
	 * 
	 * @return enable
	 */
	public Integer getEnable() {
		return enable;
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
	 * finance_icon
	 * 
	 * @param finance_icon
	 */
	public void setFinance_icon(String finance_icon) {
		this.finance_icon = finance_icon;
	}
	
	/**
	 * 工具名称
	 * 
	 * @param finance_name
	 */
	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
	}
	
	/**
	 * 工具描述
	 * 
	 * @param finance_desc
	 */
	public void setFinance_desc(String finance_desc) {
		this.finance_desc = finance_desc;
	}
	
	/**
	 * 链接类型 0-app下载路径 1-url地址
	 * 
	 * @param finance_type
	 */
	public void setFinance_type(Integer finance_type) {
		this.finance_type = finance_type;
	}
	
	/**
	 * 工具链接
	 * 
	 * @param finance_url
	 */
	public void setFinance_url(String finance_url) {
		this.finance_url = finance_url;
	}
	
	/**
	 * 是否启用 0-禁用 1-启用
	 * 
	 * @param enable
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	

}