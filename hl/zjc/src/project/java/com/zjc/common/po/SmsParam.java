/**
 * 
 */
package com.zjc.common.po;

/**
 * 短息参数实体类
 * @author zhangchao
 *
 */
public class SmsParam {

	//短信签名
	private String name;
	//验证码
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
