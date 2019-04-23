package com.api.goods.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

import com.zjc.goods.dao.po.ZjcGoodsPO;

/**
 * <b>zjc_goods_collect[zjc_goods_collect]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-05 16:53:15
 */
/**
 * @author Administrator
 *
 */
public class ZjcGoodsCollectPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer collect_id;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 商品
	 */
	private ZjcGoodsPO zjcGoodsPO;
	

	/**
	 * 表id
	 * 
	 * @return collect_id
	 */
	public Integer getCollect_id() {
		return collect_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
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
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	

	/**
	 * 表id
	 * 
	 * @param collect_id
	 */
	public void setCollect_id(Integer collect_id) {
		this.collect_id = collect_id;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
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
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public ZjcGoodsPO getZjcGoodsPO() {
		return zjcGoodsPO;
	}

	public void setZjcGoodsPO(ZjcGoodsPO zjcGoodsPO) {
		this.zjcGoodsPO = zjcGoodsPO;
	}
	
	
}