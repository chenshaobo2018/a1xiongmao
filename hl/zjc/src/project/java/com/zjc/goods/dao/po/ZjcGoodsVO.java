/**
 * 
 */
package com.zjc.goods.dao.po;

/**
 * @author pubing
 *
 */
public class ZjcGoodsVO {
	/**
	 * 商品ID
	 */
	private Integer goods_id;
	/**
	 * 购买数量
	 */
	private Integer goods_num;
	/**
	 * 钱宝扩容比例
	 */
	private Float wallet_amplification_rate;
	/**
	 * 商家返利比例
	 */
	private Float store_rebate_rate;
	/**
	 * 以物易物会员积分返还倍数
	 */
	private Float market_price_bs;
	/**
	 * 在线支付会员积分返还倍数
	 */
	private Float shop_price_bs;
	/**
	 * 提成比例一
	 */
	private Float commission_rate_1;
	/**
	 * 提成比例二
	 */
	private Float commission_rate_2;
	/**
	 * 提成比例三
	 */
	private Float commission_rate_3;
	/**
	 * 积分比例价
	 */
	private Float market_price;
	/**
	 * 本店价(等额积分支付)
	 */
	private Float goods_price;
	
	
	/**
	 * 等额积分价
	 */
	private Float cost_price;
	
	/**
	 * 是否是特殊商品
	 */
	private int is_car;
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	public Float getWallet_amplification_rate() {
		return wallet_amplification_rate;
	}
	public void setWallet_amplification_rate(Float wallet_amplification_rate) {
		this.wallet_amplification_rate = wallet_amplification_rate;
	}
	public Float getStore_rebate_rate() {
		return store_rebate_rate;
	}
	public void setStore_rebate_rate(Float store_rebate_rate) {
		this.store_rebate_rate = store_rebate_rate;
	}
	public Float getMarket_price_bs() {
		return market_price_bs;
	}
	public void setMarket_price_bs(Float market_price_bs) {
		this.market_price_bs = market_price_bs;
	}
	public Float getShop_price_bs() {
		return shop_price_bs;
	}
	public void setShop_price_bs(Float shop_price_bs) {
		this.shop_price_bs = shop_price_bs;
	}
	public Float getCommission_rate_1() {
		return commission_rate_1;
	}
	public void setCommission_rate_1(Float commission_rate_1) {
		this.commission_rate_1 = commission_rate_1;
	}
	public Float getCommission_rate_2() {
		return commission_rate_2;
	}
	public void setCommission_rate_2(Float commission_rate_2) {
		this.commission_rate_2 = commission_rate_2;
	}
	public Float getCommission_rate_3() {
		return commission_rate_3;
	}
	public void setCommission_rate_3(Float commission_rate_3) {
		this.commission_rate_3 = commission_rate_3;
	}
	public Float getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Float market_price) {
		this.market_price = market_price;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public Float getCost_price() {
		return cost_price;
	}
	public void setCost_price(Float cost_price) {
		this.cost_price = cost_price;
	}
	public int getIs_car() {
		return is_car;
	}
	public void setIs_car(int is_car) {
		this.is_car = is_car;
	}
	
}
