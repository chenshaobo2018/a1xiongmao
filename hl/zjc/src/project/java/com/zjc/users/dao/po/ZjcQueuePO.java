package com.zjc.users.dao.po;

import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;


/**
 * <b>zjc_queue[zjc_queue]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-07 08:51:17
 */
public class ZjcQueuePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 消息类型  1 倍增  2 转账 3 结算中到期队列 4 消费积分返回用户 5 易物担保到期取消订单 后期可根据需求添加 6商城购物商家积分返利
	 */
	private Integer type;
	
	/**
	 * 消息加入队列时间 
	 */
	private Date add_time;
	
	/**
	 * 0 未发送 1 已发送
	 */
	private Integer is_send;
	
	/**
	 * 消息执行时间 
	 */
	private Date send_time;
	
	/**
	 * 会员 id
	 */
	private BigInteger user_id;
	
	/**
	 * 需要给会员的消费积分
	 */
	private Integer xf_points;
	
	/**
	 * 需要给会员的可转积分
	 */
	private Integer kz_points;
	
	/**
	 * 执行成功时间
	 */
	private Date success_time;
	
	/**
	 * 发送次数
	 */
	private Integer num;
	
	/**
	 * 关联ID
	 */
	private Integer relation_id;
	
	/**
	 *是否取消返还   0,不取消;1 取消 
	 */
	private Integer is_cancle;
	
	/**
	 * 联系电话
	 */
	private String mobile;
	
	/**
	 * 是否取消返还   0,不取消;1 取消 
	 * 
	 * @return
	 */
	public Integer getIs_cancle() {
		return is_cancle;
	}

	/**
	 * 是否取消返还   0,不取消;1 取消 
	 * 
	 * @param is_cancle
	 */
	public void setIs_cancle(Integer is_cancle) {
		this.is_cancle = is_cancle;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 备注
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * 消息类型  1 倍增  2 转账 3 结算中到期队列 4 消费积分返回用户 5 易物担保到期取消订单 后期可根据需求添加 6商城购物商家积分返利
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 消息加入队列时间 
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 0 未发送 1 已发送
	 * 
	 * @return is_send
	 */
	public Integer getIs_send() {
		return is_send;
	}
	
	/**
	 * 消息执行时间 
	 * 
	 * @return send_time
	 */
	public Date getSend_time() {
		return send_time;
	}
	
	/**
	 * 会员 id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * 需要给会员的消费积分
	 * 
	 * @return xf_points
	 */
	public Integer getXf_points() {
		return xf_points;
	}
	
	/**
	 * 需要给会员的可转积分
	 * 
	 * @return kz_points
	 */
	public Integer getKz_points() {
		return kz_points;
	}
	
	/**
	 * 执行成功时间
	 * 
	 * @return success_time
	 */
	public Date getSuccess_time() {
		return success_time;
	}
	
	/**
	 * 发送次数
	 * 
	 * @return num
	 */
	public Integer getNum() {
		return num;
	}
	
	/**
	 * 关联ID
	 * 
	 * @return relation_id
	 */
	public Integer getRelation_id() {
		return relation_id;
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
	 * 备注
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * 消息类型  1 倍增  2 转账 3 结算中到期队列 4 消费积分返回用户 5 易物担保到期取消订单 后期可根据需求添加 6商城购物商家积分返利
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 消息加入队列时间 
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 0 未发送 1 已发送
	 * 
	 * @param is_send
	 */
	public void setIs_send(Integer is_send) {
		this.is_send = is_send;
	}
	
	/**
	 * 消息执行时间 
	 * 
	 * @param send_time
	 */
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	/**
	 * 会员 id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 需要给会员的消费积分
	 * 
	 * @param xf_points
	 */
	public void setXf_points(Integer xf_points) {
		this.xf_points = xf_points;
	}
	
	/**
	 * 需要给会员的可转积分
	 * 
	 * @param kz_points
	 */
	public void setKz_points(Integer kz_points) {
		this.kz_points = kz_points;
	}
	
	/**
	 * 执行成功时间
	 * 
	 * @param success_time
	 */
	public void setSuccess_time(Date success_time) {
		this.success_time = success_time;
	}
	
	/**
	 * 发送次数
	 * 
	 * @param num
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	/**
	 * 关联ID
	 * 
	 * @param relation_id
	 */
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	

}