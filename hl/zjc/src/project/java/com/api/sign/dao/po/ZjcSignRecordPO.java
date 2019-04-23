package com.api.sign.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_sign_record[zjc_sign_record]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-14 11:07:20
 */
public class ZjcSignRecordPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private Integer user_id;
	
	/**
	 * 易物券
	 */
	private Integer score;
	
	/**
	 * 签到时间
	 */
	private Date time;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 * 总计获取易物券
	 */
	private Integer total;
	
	/**
	 * 可转出的易物券
	 */
	private Integer abvial_total;
	
	/**
	 * 类型
	 */
	private Integer type;
	

	/**
	 * 主键ID
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 用户ID
	 * 
	 * @return user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	
	/**
	 * 易物券
	 * 
	 * @return score
	 */
	public Integer getScore() {
		return score;
	}
	
	/**
	 * 签到时间
	 * 
	 * @return time
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 * 描述
	 * 
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * 总计获取易物券
	 * 
	 * @return total
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * 可转出的易物券
	 * 
	 * @return abvial_total
	 */
	public Integer getAbvial_total() {
		return abvial_total;
	}
	
	/**
	 * 类型
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	

	/**
	 * 主键ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 用户ID
	 * 
	 * @param user_id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 易物券
	 * 
	 * @param score
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	
	/**
	 * 签到时间
	 * 
	 * @param time
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	/**
	 * 描述
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * 总计获取易物券
	 * 
	 * @param total
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * 可转出的易物券
	 * 
	 * @param abvial_total
	 */
	public void setAbvial_total(Integer abvial_total) {
		this.abvial_total = abvial_total;
	}
	
	/**
	 * 类型
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public ZjcSignRecordPO(Integer user_id, Integer score, String desc, Integer total,
			Integer abvial_total, Integer type) {
		super();
		this.user_id = user_id;
		this.score = score;
		this.desc = desc;
		this.total = total;
		this.abvial_total = abvial_total;
		this.type = type;
	}

	public ZjcSignRecordPO() {
		super();
	}
}