/**
 * 
 */
package com.api.wxpay.po;


/**
 * 微信app支付参数
 * 
 * @author wgm
 *
 */
public class WxPayConstantUtil {

	/**
     * 微信开发平台应用ID
     *//*
    public static final String APP_ID="wx2ca1c2ccafe80e1a";
    *//**
     * 应用对应的密钥
     *//*
    public static final String API_KEY="A013D20FE41FA823EDAEA32804B0FE2B";
    *//**
     * 微信支付商户号
     *//*
    public static final String MCH_ID="1491154302";*/
	/**
     * 微信开发平台应用ID
     */
	public static final String APP_ID = "wx9595ef9a71f069a5";
    /**
     * 应用对应的密钥
     */
	public static final String  API_KEY = "a9da990d74223b1816a30bba4813a28d";
    /**
     * 微信支付商户号
     */
	public static final String MCH_ID = "1524576281";
    /**
     * 商品描述
     */
    public static final String BODY="商品购买";
    
    /**
     * 微信支付商户号
     */
    public static final String TRADE_TYPE="APP";

    /**
     * 微信服务器回调通知url
     */
    /*public static String NOTIFY_URL="http://web.zjc1518.cn/aosuite/notokenapi/app/v1/weixinNotify.jhtml";*/
    //public static String NOTIFY_URL="http://106.14.16.202/aosuite/notokenapi/app/v1/weixinNotify.jhtml";
    public static String NOTIFY_URL="https://zjc1518.com/aosuite/notokenapi/app/v1/weixinNotify.jhtml";
    /**
     * 微信支付-统一下单调用地址
     */
    public static String URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    /**
     * 微信支付-订单查询调用地址
     */
    public static String QUERY_URL="https://api.mch.weixin.qq.com/pay/orderquery";
    
    /**
     * 微信支付code
     */
    public static final String PAY_CODE="wxpay";
    
    /**
     * 微信支付name
     */
    public static final String PAY_NAME="微信支付";
    
    /**
     * 混合支付-微信code
     */
    public static final String MIX_PAY_CODE="mixed_payment";
    
    /**
     * 混合支付-微信name
     */
    public static final String MIX_PAY_NAME="混合支付-微信";
    
    /**
     * 微信支付-支付成功
     */
    public static Integer PAY_SUCCEESS = 1;
}
