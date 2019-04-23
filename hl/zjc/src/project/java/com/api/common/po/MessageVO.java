package com.api.common.po;



/**
 * <b>公共实体类</b>
 * 
 * @author wgm
 * @date 2017-06-27 15:04:49
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class MessageVO {

	
	/**
	 * 状态码
	 */
	private int code;
	
	/**
	 * json数据
	 */
	private Object data = new Object();
	
	/**
	 * 提示信息
	 */
	private String msg="";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}