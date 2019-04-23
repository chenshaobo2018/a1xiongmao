package com.api.common.util;


/**
 * 常量工具类
 * 
 * @author wgm
 *
 */
public class ConstantUtil {
	
	/**
	 * 每页数量
	 */
	public static final int pageSize = 10;
	
	/**
	 * 是
	 */
	public static final String YES = "1";

	/**
	 * 否
	 */
	public static final String NO = "0";
	
	/**
	 * 设置支付密码短信验证码类型
	 */
	public static final String PAY_PSD_CODE_TYPE = "pay_password";
	
	/**
	 * token有效期
	 */
	public static final int TOKEN_VALIDATE_TIME = 7200;
	
	/**
	 * 短信验证码有效期（10分钟）
	 */
	public static final int CODE_VALIDATE_TIME = 600;
	
	/**
	 * 确认收货备注
	 */
	public static final String CONFIRM_ORDER_NOTE = "您确认了收货";
	
	/**
	 * 确认收货描述
	 */
	public static final String CONFIRM_ORDER_DESC = "确认收货";
	
	/**
	 * 转账订单备注
	 */
	public static final String EXCHANGE_ORDER_DESC = "你发起了转账";
	
	/**
	 * 登陆密码校验类型
	 */
	public static final String LOGIN_PSD_TYPE = "login_psd";
	
	/**
	 * 支付密码校验类型
	 */
	public static final String PAY_PSD_TYPE = "pay_psd";
	
	/**
	 * 经纬度范围半径
	 */
	public static final double DISTANCE = 5000.00;
	
	/**
	 * 地球半径
	 */
	public static final double EARTH_RADIUS = 6378137.0;
	
	/**
	 * 默认总页数
	 */
	public static final int Defalut_Totle_Size = 10000;
	
}