package com.wxactivity.share.dao.po;

import aos.framework.core.typewrap.PO;
import java.util.Date;

/**
 * <b>wx_user_activity[wx_user_activity]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2018-01-31 17:44:12
 */
public class WxUserActivityPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * activity_id
	 */
	private String activity_id;
	
	/**
	 * open_id
	 */
	private String open_id;
	
	/**
	 * 积分数
	 */
	private String integral;
	
	/**
	 * 微信头像
	 */
	private String img;
	
	/**
	 * 昵称名字
	 */
	private String nickname;
	
	/**
	 * 添加时间
	 */
	private Date addtime;
	

	/**
	 * activity_id
	 * 
	 * @return activity_id
	 */
	public String getActivity_id() {
		return activity_id;
	}
	
	/**
	 * open_id
	 * 
	 * @return open_id
	 */
	public String getOpen_id() {
		return open_id;
	}
	
	/**
	 * 积分数
	 * 
	 * @return integral
	 */
	public String getIntegral() {
		return integral;
	}
	
	/**
	 * 微信头像
	 * 
	 * @return img
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * 昵称名字
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return addtime
	 */
	public Date getAddtime() {
		return addtime;
	}
	

	/**
	 * activity_id
	 * 
	 * @param activity_id
	 */
	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}
	
	/**
	 * open_id
	 * 
	 * @param open_id
	 */
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	/**
	 * 积分数
	 * 
	 * @param integral
	 */
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	/**
	 * 微信头像
	 * 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * 昵称名字
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param addtime
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	

}