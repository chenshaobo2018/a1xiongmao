package com.api.order.dao.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_cart[zjc_cart]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-04 17:12:04
 */
public class ZjcCartPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 购物车表
	 */
	private Integer id;
	
	/**
	 * 用户id
	 */
	private BigInteger user_id;
	
	/**
	 * session
	 */
	private String session_id;
	
	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * 商品货号
	 */
	private String goods_sn;
	
	/**
	 * 商品名称
	 */
	private String goods_name;
	
	/**
	 * 积分比例价
	 */
	private BigDecimal market_price;
	
	/**
	 * 本店价(现金)
	 */
	private BigDecimal shop_price;
	
	/**
	 * 等额积分价
	 */
	private BigDecimal cost_price;
	
	/**
	 * 购买数量
	 */
	private Integer goods_num;
	
	/**
	 * 商品规格key 对应zjc_spec_goods_price 表
	 */
	private String spec_key;
	
	/**
	 * 商品规格组合名称
	 */
	private String spec_key_name;
	
	/**
	 * 商品条码
	 */
	private String bar_code;
	
	/**
	 * 购物车选中状态
	 */
	private Integer selected;
	
	/**
	 * 加入购物车的时间
	 */
	private Date add_time;
	
	/**
	 * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
	 */
	private Integer prom_type;
	
	/**
	 * 活动id
	 */
	private Integer prom_id;
	
	/**
	 * sku
	 */
	private String sku;
	
	/**
	 * 商家ID
	 */
	private Integer store_id;
	
	/**
	 * 商家名称
	 */
	private String store_name;
	/**
	 * 商品图片
	 */
	private String img;
	
	/**
	 * 是否混合支付；0表示否，1表示是
	 */
	private String is_mixed;
	
	/**
	 * 商品类型；0表示不能用代金券券，1表示可用代金券
	 */
	private String is_voucher;

	/**
	 * 购物车表
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 用户id
	 * 
	 * @return user_id
	 */
	public BigInteger getUser_id() {
		return user_id;
	}
	
	/**
	 * session
	 * 
	 * @return session_id
	 */
	public String getSession_id() {
		return session_id;
	}
	
	/**
	 * 商品id
	 * 
	 * @return goods_id
	 */
	public Integer getGoods_id() {
		return goods_id;
	}
	
	/**
	 * 商品货号
	 * 
	 * @return goods_sn
	 */
	public String getGoods_sn() {
		return goods_sn;
	}
	
	/**
	 * 商品名称
	 * 
	 * @return goods_name
	 */
	public String getGoods_name() {
		return goods_name;
	}
	
	/**
	 * 积分比例价
	 * 
	 * @return market_price
	 */
	public BigDecimal getMarket_price() {
		return market_price;
	}
	
	/**
	 * 本店价(现金)
	 * 
	 * @return shop_price
	 */
	public BigDecimal getShop_price() {
		return shop_price;
	}
	
	/**
	 * 等额积分价
	 * 
	 * @return cost_price
	 */
	public BigDecimal getCost_price() {
		return cost_price;
	}
	
	/**
	 * 购买数量
	 * 
	 * @return goods_num
	 */
	public Integer getGoods_num() {
		return goods_num;
	}
	
	/**
	 * 商品规格key 对应zjc_spec_goods_price 表
	 * 
	 * @return spec_key
	 */
	public String getSpec_key() {
		return spec_key;
	}
	
	/**
	 * 商品规格组合名称
	 * 
	 * @return spec_key_name
	 */
	public String getSpec_key_name() {
		return spec_key_name;
	}
	
	/**
	 * 商品条码
	 * 
	 * @return bar_code
	 */
	public String getBar_code() {
		return bar_code;
	}
	
	/**
	 * 购物车选中状态
	 * 
	 * @return selected
	 */
	public Integer getSelected() {
		return selected;
	}
	
	/**
	 * 加入购物车的时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
	 * 
	 * @return prom_type
	 */
	public Integer getProm_type() {
		return prom_type;
	}
	
	/**
	 * 活动id
	 * 
	 * @return prom_id
	 */
	public Integer getProm_id() {
		return prom_id;
	}
	
	/**
	 * sku
	 * 
	 * @return sku
	 */
	public String getSku() {
		return sku;
	}
	
	/**
	 * 商家ID
	 * 
	 * @return store_id
	 */
	public Integer getStore_id() {
		return store_id;
	}
	
	/**
	 * 商家名称
	 * 
	 * @return store_name
	 */
	public String getStore_name() {
		return store_name;
	}
	

	/**
	 * 购物车表
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 用户id
	 * 
	 * @param user_id
	 */
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * session
	 * 
	 * @param session_id
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	/**
	 * 商品id
	 * 
	 * @param goods_id
	 */
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * 商品货号
	 * 
	 * @param goods_sn
	 */
	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}
	
	/**
	 * 商品名称
	 * 
	 * @param goods_name
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	/**
	 * 积分比例价
	 * 
	 * @param market_price
	 */
	public void setMarket_price(BigDecimal market_price) {
		this.market_price = market_price;
	}
	
	/**
	 * 本店价(现金)
	 * 
	 * @param shop_price
	 */
	public void setShop_price(BigDecimal shop_price) {
		this.shop_price = shop_price;
	}
	
	/**
	 * 等额积分价
	 * 
	 * @param cost_price
	 */
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	
	/**
	 * 购买数量
	 * 
	 * @param goods_num
	 */
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	
	/**
	 * 商品规格key 对应zjc_spec_goods_price 表
	 * 
	 * @param spec_key
	 */
	public void setSpec_key(String spec_key) {
		this.spec_key = spec_key;
	}
	
	/**
	 * 商品规格组合名称
	 * 
	 * @param spec_key_name
	 */
	public void setSpec_key_name(String spec_key_name) {
		this.spec_key_name = spec_key_name;
	}
	
	/**
	 * 商品条码
	 * 
	 * @param bar_code
	 */
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	
	/**
	 * 购物车选中状态
	 * 
	 * @param selected
	 */
	public void setSelected(Integer selected) {
		this.selected = selected;
	}
	
	/**
	 * 加入购物车的时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
	 * 
	 * @param prom_type
	 */
	public void setProm_type(Integer prom_type) {
		this.prom_type = prom_type;
	}
	
	/**
	 * 活动id
	 * 
	 * @param prom_id
	 */
	public void setProm_id(Integer prom_id) {
		this.prom_id = prom_id;
	}
	
	/**
	 * sku
	 * 
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	/**
	 * 商家ID
	 * 
	 * @param store_id
	 */
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * 商家名称
	 * 
	 * @param store_name
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIs_mixed() {
		return is_mixed;
	}

	public void setIs_mixed(String is_mixed) {
		this.is_mixed = is_mixed;
	}

	/**
	 * 商品类型；0表示不能用代金券券，1表示可用代金券
	 * 
	 * @return
	 */
	public String getIs_voucher() {
		return is_voucher;
	}

	/**
	 * 商品类型；0表示不能用代金券券，1表示可用代金券
	 * 
	 * @param is_voucher
	 */
	public void setIs_voucher(String is_voucher) {
		this.is_voucher = is_voucher;
	}
	
	
	

}