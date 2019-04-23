/**
 * 
 */
package com.api.wxpay.po;

/**
 * @author wgm
 *
 */
public class WeixinPayPo {
	
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 随机字符串
	 */
	private String nonce_str;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 商品描述
	 */
	private String body;
	/**
	 * 商品详情
	 */
	private String detail;
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	/**
	 * 终端IP
	 */
	private String spbill_create_ip;
	/**
	 * 标价金额/分
	 */
	private int total_fee;
	/**
	 * 通知地址
	 */
	private String notify_url;
	/**
	 * 交易类型
	 */
	private String trade_type;
	
	/**
	 * 交易开始时间
	 */
	private String time_start;
	
	/**
	 * 交易结束时间
	 */
	private String time_expire;
	
	
	
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
	
	
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public WeixinPayPo() {
		super();
	}
	
	
	public WeixinPayPo(String appid, String mch_id, String nonce_str,
			String sign, String body, String detail, String out_trade_no,
			String spbill_create_ip, int total_fee, String notify_url,
			String trade_type) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.body = body;
		this.detail = detail;
		this.out_trade_no = out_trade_no;
		this.spbill_create_ip = spbill_create_ip;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
	}
	
	
	public WeixinPayPo(String appid, String mch_id, String nonce_str,
			String body, String detail, String out_trade_no,
			String spbill_create_ip, int total_fee, String notify_url,
			String trade_type) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.body = body;
		this.detail = detail;
		this.out_trade_no = out_trade_no;
		this.spbill_create_ip = spbill_create_ip;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
	}
	

}
