package com.api.goods.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>zjc_goods_sorting[zjc_goods_sorting]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-03-30 10:51:15
 */
public class ZjcGoodsSortingPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * goods_id
	 */
	private Integer goods_id;
	
	/**
	 * update_time
	 */
	private Date update_time;
	
	/**
	 * goods_num
	 */
	private Integer goods_num;
	

	/**
	 * goods_id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * update_time
	 * 
	 * @return update_time
	 */
	public Date getUpdate_time() {
		return update_time;
	}
	
	/**
	 * goods_num
	 * 
	 * @return goods_num
	 */
	public Integer getGoods_num() {
		return goods_num;
	}
	

	/**
	 * goods_id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * update_time
	 * 
	 * @param update_time
	 */
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	/**
	 * goods_num
	 * 
	 * @param goods_num
	 */
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	

}