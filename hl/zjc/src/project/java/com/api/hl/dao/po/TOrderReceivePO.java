package com.api.hl.dao.po;

import aos.framework.core.typewrap.PO;

/**
 * <b>t_order_receive[t_order_receive]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2019-04-21 11:26:03
 */
public class TOrderReceivePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，订单收货表，与订单表多对一关系
	 */
	private String id;
	
	/**
	 * 订单明细id
	 */
	private String order_detailed_id;
	
	/**
	 * 收货时间
	 */
	private String receive_time;
	
	/**
	 * 收货数量
	 */
	private Integer receive_number;
	
	/**
	 * 用户id
	 */
	private String user_id;
	

	/**
	 * 主键，订单收货表，与订单表多对一关系
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 订单明细id
	 * 
	 * @return order_detailed_id
	 */
	public String getOrder_detailed_id() {
		return order_detailed_id;
	}
	
	/**
	 * 收货时间
	 * 
	 * @return receive_time
	 */
	public String getReceive_time() {
		return receive_time;
	}
	
	/**
	 * 收货数量
	 * 
	 * @return receive_number
	 */
	public Integer getReceive_number() {
		return receive_number;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	

	/**
	 * 主键，订单收货表，与订单表多对一关系
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 订单明细id
	 * 
	 * @param order_detailed_id
	 */
	public void setOrder_detailed_id(String order_detailed_id) {
		this.order_detailed_id = order_detailed_id;
	}
	
	/**
	 * 收货时间
	 * 
	 * @param receive_time
	 */
	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}
	
	/**
	 * 收货数量
	 * 
	 * @param receive_number
	 */
	public void setReceive_number(Integer receive_number) {
		this.receive_number = receive_number;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	

}