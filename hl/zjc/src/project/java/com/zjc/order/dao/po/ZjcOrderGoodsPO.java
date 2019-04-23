package com.zjc.order.dao.po;

import java.math.BigDecimal;

import aos.framework.core.typewrap.PO;

import com.zjc.goods.dao.po.ZjcGoodsPO;

/**
 * <b>zjc_order_goods[zjc_order_goods]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-24 09:08:52
 */
/**
 * @author Administrator
 *
 */
public class ZjcOrderGoodsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id自增
	 */
	private Integer rec_id;
	
	/**
	 * 订单id
	 */
	private Integer order_id;
	
	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * 视频名称
	 */
	private String goods_name;
	
	/**
	 * 商品货号
	 */
	private String goods_sn;
	
	/**
	 * 购买数量
	 */
	private Integer goods_num;
	
	/**
	 * 市场价(比例积分支付)
	 */
	private BigDecimal market_price;
	
	/**
	 * 本店价(等额积分支付)
	 */
	private BigDecimal goods_price;
	
	/**
	 * 商品成本价(现金支付)
	 */
	private BigDecimal cost_price;
	
	/**
	 * 会员折扣价
	 */
	private BigDecimal member_goods_price;
	
	/**
	 * 购买商品赠送积分
	 */
	private Integer give_integral;
	
	/**
	 * 商品规格key
	 */
	private String spec_key;
	
	/**
	 * 规格对应的中文名字
	 */
	private String spec_key_name;
	
	/**
	 * 条码
	 */
	private String bar_code;
	
	/**
	 * 是否评价
	 */
	private Integer is_comment;
	
	/**
	 * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
	 */
	private Integer prom_type;
	
	/**
	 * 活动id
	 */
	private Integer prom_id;
	
	/**
	 * 0未发货，1已发货，2已换货，3已退货
	 */
	private Integer is_send;
	
	/**
	 * 发货单ID
	 */
	private Integer delivery_id;
	
	/**
	 * sku
	 */
	private String sku;
	
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
	 * 商品详细描述
	 */
	private String goods_content;
	
	/**
	 * 设置返还周期权重，1周期返还的默认100，分多期返还的其和为100
	 */
	private String goods_weight;
	
	/**
	 * 是否包邮0否1是
	 */
	private Integer is_free_shipping;
	
	/**
     * 联系电话
     */
    private String contract_phone;
    
    /**
     * 邮费
     */
    private Integer postage;
    /**
     * 预计发货时间
     */
    private Integer expect_delivery;
    /**
     * 规格型号
     */
    private String ts;
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 当日每人限购次数
     */
    private Integer today_limit_times;
    /**
     * 当日每人限购数量
     */
    private Integer today_limit_nums;
    private BigDecimal shop_group_price;
	private Integer shop_group_person;
	private BigDecimal shop_group_market_price;
	
	
	public BigDecimal getShop_group_price() {
		return shop_group_price;
	}

	public void setShop_group_price(BigDecimal shop_group_price) {
		this.shop_group_price = shop_group_price;
	}

	public Integer getShop_group_person() {
		return shop_group_person;
	}

	public void setShop_group_person(Integer shop_group_person) {
		this.shop_group_person = shop_group_person;
	}

	public BigDecimal getShop_group_market_price() {
		return shop_group_market_price;
	}

	public void setShop_group_market_price(BigDecimal shop_group_market_price) {
		this.shop_group_market_price = shop_group_market_price;
	}
	
	/**
	 * 设置返还周期权重，1周期返还的默认100，分多期返还的其和为100
	 * 
	 * @return
	 */
	public String getGoods_weight() {
		return goods_weight;
	}

	/**
	 * 设置返还周期权重，1周期返还的默认100，分多期返还的其和为100
	 * 
	 * @param goods_weight
	 */
	public void setGoods_weight(String goods_weight) {
		this.goods_weight = goods_weight;
	}

	/**
	 * 商家返利比例
	 * 
	 * @return
	 */
	public Float getStore_rebate_rate() {
		return store_rebate_rate;
	}

	/**
	 * 商家返利比例
	 * 
	 * @param store_rebate_rate
	 */
	public void setStore_rebate_rate(Float store_rebate_rate) {
		this.store_rebate_rate = store_rebate_rate;
	}

	/**
	 * 以物易物会员积分返还倍数
	 * 
	 * @return
	 */
	public Float getMarket_price_bs() {
		return market_price_bs;
	}

	/**
	 * 以物易物会员积分返还倍数
	 * 
	 * @param market_price_bs
	 */
	public void setMarket_price_bs(Float market_price_bs) {
		this.market_price_bs = market_price_bs;
	}

	/**
	 * 在线支付会员积分返还倍数
	 * 
	 * @return
	 */
	public Float getShop_price_bs() {
		return shop_price_bs;
	}

	/**
	 * 在线支付会员积分返还倍数
	 * 
	 * @param shop_price_bs
	 */
	public void setShop_price_bs(Float shop_price_bs) {
		this.shop_price_bs = shop_price_bs;
	}

	/**
	 * 商品详细描述
	 * 
	 * @return
	 */
	public String getGoods_content() {
		return goods_content;
	}

	/**
	 * 商品详细描述
	 * 
	 * @param goods_content
	 */
	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}

	/**
	 * 商品信息
	 */
	private ZjcGoodsPO zjcGoodsPO;
	
	public ZjcGoodsPO getZjcGoodsPO() {
		return zjcGoodsPO;
	}

	public void setZjcGoodsPO(ZjcGoodsPO zjcGoodsPO) {
		this.zjcGoodsPO = zjcGoodsPO;
	}

	/**
	 *发货单
	 */
	private ZjcDeliveryDocPO zjcDeliveryDocPO;
	
	
	public ZjcDeliveryDocPO getZjcDeliveryDocPO() {
		return zjcDeliveryDocPO;
	}

	public void setZjcDeliveryDocPO(ZjcDeliveryDocPO zjcDeliveryDocPO) {
		this.zjcDeliveryDocPO = zjcDeliveryDocPO;
	}

	/**
	 * 表id自增
	 * 
	 * @return rec_id
	 */
	public Integer getRec_id() {
		return rec_id;
	}
	
	/**
	 * 订单id
	 * 
	 * @return order_id
	 */
	public Integer getOrder_id() {
		return order_id;
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
	 * 视频名称
	 * 
	 * @return goods_name
	 */
	public String getGoods_name() {
		return goods_name;
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
	 * 购买数量
	 * 
	 * @return goods_num
	 */
	public Integer getGoods_num() {
		return goods_num;
	}
	
	/**
	 * 市场价(比例积分支付)
	 * 
	 * @return market_price
	 */
	public BigDecimal getMarket_price() {
		return market_price;
	}
	
	/**
	 * 本店价(等额积分支付)
	 * 
	 * @return goods_price
	 */
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	
	/**
	 * 商品成本价(现金支付)
	 * 
	 * @return cost_price
	 */
	public BigDecimal getCost_price() {
		return cost_price;
	}
	
	/**
	 * 会员折扣价
	 * 
	 * @return member_goods_price
	 */
	public BigDecimal getMember_goods_price() {
		return member_goods_price;
	}
	
	/**
	 * 购买商品赠送积分
	 * 
	 * @return give_integral
	 */
	public Integer getGive_integral() {
		return give_integral;
	}
	
	/**
	 * 商品规格key
	 * 
	 * @return spec_key
	 */
	public String getSpec_key() {
		return spec_key;
	}
	
	/**
	 * 规格对应的中文名字
	 * 
	 * @return spec_key_name
	 */
	public String getSpec_key_name() {
		return spec_key_name;
	}
	
	/**
	 * 条码
	 * 
	 * @return bar_code
	 */
	public String getBar_code() {
		return bar_code;
	}
	
	/**
	 * 是否评价
	 * 
	 * @return is_comment
	 */
	public Integer getIs_comment() {
		return is_comment;
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
	 * 0未发货，1已发货，2已换货，3已退货
	 * 
	 * @return is_send
	 */
	public Integer getIs_send() {
		return is_send;
	}
	
	/**
	 * 发货单ID
	 * 
	 * @return delivery_id
	 */
	public Integer getDelivery_id() {
		return delivery_id;
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
	 * 表id自增
	 * 
	 * @param rec_id
	 */
	public void setRec_id(Integer rec_id) {
		this.rec_id = rec_id;
	}
	
	/**
	 * 订单id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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
	 * 视频名称
	 * 
	 * @param goods_name
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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
	 * 购买数量
	 * 
	 * @param goods_num
	 */
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	
	/**
	 * 市场价(比例积分支付)
	 * 
	 * @param market_price
	 */
	public void setMarket_price(BigDecimal market_price) {
		this.market_price = market_price;
	}
	
	/**
	 * 本店价(等额积分支付)
	 * 
	 * @param goods_price
	 */
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	
	/**
	 * 商品成本价(现金支付)
	 * 
	 * @param cost_price
	 */
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	
	/**
	 * 会员折扣价
	 * 
	 * @param member_goods_price
	 */
	public void setMember_goods_price(BigDecimal member_goods_price) {
		this.member_goods_price = member_goods_price;
	}
	
	/**
	 * 购买商品赠送积分
	 * 
	 * @param give_integral
	 */
	public void setGive_integral(Integer give_integral) {
		this.give_integral = give_integral;
	}
	
	/**
	 * 商品规格key
	 * 
	 * @param spec_key
	 */
	public void setSpec_key(String spec_key) {
		this.spec_key = spec_key;
	}
	
	/**
	 * 规格对应的中文名字
	 * 
	 * @param spec_key_name
	 */
	public void setSpec_key_name(String spec_key_name) {
		this.spec_key_name = spec_key_name;
	}
	
	/**
	 * 条码
	 * 
	 * @param bar_code
	 */
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	
	/**
	 * 是否评价
	 * 
	 * @param is_comment
	 */
	public void setIs_comment(Integer is_comment) {
		this.is_comment = is_comment;
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
	 * 0未发货，1已发货，2已换货，3已退货
	 * 
	 * @param is_send
	 */
	public void setIs_send(Integer is_send) {
		this.is_send = is_send;
	}
	
	/**
	 * 发货单ID
	 * 
	 * @param delivery_id
	 */
	public void setDelivery_id(Integer delivery_id) {
		this.delivery_id = delivery_id;
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
	 * 邮费
	 * 
	 * @return
	 */
	public Integer getPostage() {
		return postage;
	}
	/**
	 * 邮费
	 * 
	 * @param postage
	 */
	public void setPostage(Integer postage) {
		this.postage = postage;
	}
	/**
	 * 预计发货时间
	 * 
	 * @return
	 */
	public Integer getExpect_delivery() {
		return expect_delivery;
	}
	/**
	 * 预计发货时间
	 * 
	 * @param expect_delivery
	 */
	public void setExpect_delivery(Integer expect_delivery) {
		this.expect_delivery = expect_delivery;
	}
	/**
	 * 规格/型号
	 * 
	 * @return
	 */
	public String getTs() {
		return ts;
	}
	/**
	 * 规格/型号
	 * 
	 * @param ts
	 */
	public void setTs(String ts) {
		this.ts = ts;
	}
	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 当日每人限购次数
	 * 
	 * @return
	 */
	public Integer getToday_limit_times() {
		return today_limit_times;
	}
	/**
	 * 当日每人限购次数
	 * 
	 * @param today_limit_times
	 */
	public void setToday_limit_times(Integer today_limit_times) {
		this.today_limit_times = today_limit_times;
	}
	/**
	 * 当日每人限购数量
	 * 
	 * @return
	 */
	public Integer getToday_limit_nums() {
		return today_limit_nums;
	}
	/**
	 * 当日每人限购数量
	 * 
	 * @param today_limit_nums
	 */
	public void setToday_limit_nums(Integer today_limit_nums) {
		this.today_limit_nums = today_limit_nums;
	}

	/**
	 * 是否包邮：1表示是，0表示否
	 * 
	 * @return
	 */
	public Integer getIs_free_shipping() {
		return is_free_shipping;
	}

	/**
	 * 是否包邮：1表示是，0表示否
	 * 
	 * @param is_free_shipping
	 */
	public void setIs_free_shipping(Integer is_free_shipping) {
		this.is_free_shipping = is_free_shipping;
	}

	/**
	 * 客服电话
	 * 
	 * @return
	 */
	public String getContract_phone() {
		return contract_phone;
	}

	/**
	 * 客服电话
	 * 
	 * @param contract_phone
	 */
	public void setContract_phone(String contract_phone) {
		this.contract_phone = contract_phone;
	}
	

}