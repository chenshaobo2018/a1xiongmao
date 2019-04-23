package com.zjc.goods.dao.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import aos.framework.core.typewrap.PO;

import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;
import com.zjc.store.dao.po.ZjcStorePO;

/**
 * <b>zjc_goods[zjc_goods]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-06-08 14:56:31
 */
/**
 * @author Administrator
 *
 */
public class ZjcGoodsPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	private Integer goods_id;
	
	/**
	 * 分类id
	 */
	private Integer cat_id;
	
	/**
	 * 二级分类
	 */
	private Integer cat_id2;
	
	/**
	 * 扩展分类id
	 */
	private Integer extend_cat_id;
	
	/**
	 * 商品编号
	 */
	private String goods_sn;
	
	/**
	 * 商品名称
	 */
	private String goods_name;
	
	/**
	 * 店铺ID 
	 */
	private Integer store_id;
	
	/**
	 * 点击数
	 */
	private Integer click_count;
	
	/**
	 * 品牌id
	 */
	private Integer brand_id;
	
	/**
	 * 库存数量
	 */
	private Integer store_count;
	
	/**
	 * 商品评论数
	 */
	private Integer comment_count;
	
	/**
	 * 商品重量克为单位
	 */
	private Integer weight;
	
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
	 * 商品关键词
	 */
	private String keywords;
	
	/**
	 * 商品简单描述
	 */
	private String goods_remark;
	
	/**
	 * 商品详细描述
	 */
	private String goods_content;
	
	/**
	 * 商品上传原始图
	 */
	private String original_img;
	
	/**
	 * 是否为实物
	 */
	private Integer is_real;
	
	/**
	 * 是否上架
	 */
	private Integer is_on_sale;
	
	/**
	 * 是否包邮0否1是
	 */
	private Integer is_free_shipping;
	
	/**
	 * 商品上架时间
	 */
	private Date on_time;
	
	/**
	 * 商品排序
	 */
	private Integer sort;
	
	/**
	 * 是否推荐(中军创的优选商品)
	 */
	private Integer is_recommend;
	
	/**
	 * 是否新品
	 */
	private Integer is_new;
	
	/**
	 * 是否热卖
	 */
	private Integer is_hot;
	
	/**
	 * 最后更新时间
	 */
	private Date last_update;
	
	/**
	 * 商品所属类型id，取值表goods_type的cat_id
	 */
	private Integer goods_type;
	
	/**
	 * 商品规格类型，取值表goods_type的cat_id
	 */
	private Integer spec_type;
	
	/**
	 * 购买商品赠送积分
	 */
	private Integer give_integral;
	
	/**
	 * 积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置
	 */
	private Integer exchange_integral;
	
	/**
	 * 供货商ID
	 */
	private Integer suppliers_id;
	
	/**
	 * 商品销量
	 */
	private Integer sales_sum;
	
	/**
	 * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
	 */
	private Integer prom_type;
	
	/**
	 * 优惠活动id
	 */
	private Integer prom_id;
	
	/**
	 * 佣金用于分销分成
	 */
	private BigDecimal commission;
	
	/**
	 * SPU
	 */
	private String spu;
	
	/**
	 * SKU
	 */
	private String sku;
	
	/**
	 * 配送物流shipping_area_id,以逗号分隔
	 */
	private String shipping_area_ids;
	
	/**
	 * 审核员审核状态  1：待审核  2：审核失败 3：审核通过
	 */
	private Integer goods_state_1;
	
	/**
	 * 管理员审核状态  1：待审核 2：审核失败 3：审核成功
	 */
	private Integer goods_state_2;
	
	/**
	 * 提成比例一
	 */
	private String commission_rate_1;
	
	/**
	 * 提成比例二
	 */
	private String commission_rate_2;
	
	/**
	 * 提成比例三
	 */
	private String commission_rate_3;
	
	/**
	 * 钱包扩增比例
	 */
	private String wallet_amplification_rate;
	
	/**
	 * 商家返利比例
	 */
	private String store_rebate_rate;
	
	/**
	 * 审批员操作信息，序列化后数据
	 */
	private String aduit_info;
	
	/**
	 * 管理员审核备注，序列化数据
	 */
	private String admin_aduit_info;
	
	/**
	 * 以物易物会员积分返还倍数
	 */
	private String market_price_bs;
	
	/**
	 * 在线支付会员积分返还倍数
	 */
	private String shop_price_bs;
	
	/**
	 * 审批员id
	 */
	private Integer approval_id;
	
	/**
	 * 是否收藏了商品
	 */
	private boolean hasCollect = false;
	
	private String store_name;
	
	private String category_name;
	private int onlookers;
	private BigDecimal shop_group_market_price;
	/**
	 * 需要几人拼团
	 */
	private Integer shop_group_person;
	
	/**
	 * 拼团价格
	 */
	private BigDecimal shop_group_price;
	private Integer drops;
	
	
	public Integer getDrops() {
		return drops;
	}
	public void setDrops(Integer drops) {
		this.drops = drops;
	}
	/**
	 * 需要几人拼团
	 */
    public Integer getShop_group_person() {
		return shop_group_person;
	}
	public void setShop_group_person(Integer shop_group_person) {
		this.shop_group_person = shop_group_person;
	}
	/**
	 * 拼团价格
	 */
	public BigDecimal getShop_group_price() {
		return shop_group_price;
	}
	public void setShop_group_price(BigDecimal shop_group_price) {
		this.shop_group_price = shop_group_price;
	}
	public int getOnlookers() {
		return onlookers;
	}
	public void setOnlookers(int onlookers) {
		this.onlookers = onlookers;
	}
	
	/**
	 * 限时购活动销量
	 */
	private int limit_goods_sale;
	
	public int getLimit_goods_sale() {
		return limit_goods_sale;
	}
	public void setLimit_goods_sale(int limit_goods_sale) {
		this.limit_goods_sale = limit_goods_sale;
	}

	/**    ---------------------限时购商品属性start---------------------------     **/
	private String limit_market_price;
	private String limit_cost_price;
	private String end_time;
	private String start_time;
	private String activity_date;
	private String activity_date_part;
	
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getLimit_market_price() {
		return limit_market_price;
	}
	public void setLimit_market_price(String limit_market_price) {
		this.limit_market_price = limit_market_price;
	}
	public String getLimit_cost_price() {
		return limit_cost_price;
	}
	public void setLimit_cost_price(String limit_cost_price) {
		this.limit_cost_price = limit_cost_price;
	}
	
	/**    ----------------------限时购商品属性end----------------------     **/

	
	public String getActivity_date() {
		return activity_date;
	}
	public void setActivity_date(String activity_date) {
		this.activity_date = activity_date;
	}
	public String getActivity_date_part() {
		return activity_date_part;
	}
	public void setActivity_date_part(String activity_date_part) {
		this.activity_date_part = activity_date_part;
	}

	/**
     * 设置返还周期权重，1周期返还的默认100，分多期返还的其和为100
     */
    private String goods_weight;
	
    //0否1是。默认0
    private Integer is_gb;
    
    /**
     * 更新次数
     */
    private Integer gxcs;
    
    /**
     * 是否是特殊商品：1是，0否
     */
    private Integer is_car;
    
    /**
     * 是否混合支付：1是，0否
     */
    private Integer is_mixed;
    
    /**
     * 商品产地
     */
    private String place_of_production;
    
    /**
     * 商品产地
     */
    private String manufacturer;
    
    /**
     * 商品包装信息
     */
    private String packing_info;
    
    /**
     * 商品特性
     */
    private String goods_features;
    
    /**
     *是否特价 
     */
    private Integer special_offer;

	/**
     * 联系电话
     */
    private String contract_phone;
    //限时抢购商品类别
    private Integer prompt_goods;
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
     * 每人每次限购数量
     */
    private Integer today_limit_nums;
    
    /**
     * 是否是vip商品：1是，0否
     */
    private Integer is_vip;
    
	/**
	 * 是否是vip商品：1是，0否
	 * 
	 * @return
	 */
	public Integer getIs_vip() {
		return is_vip;
	}
	/**
	 * 是否是vip商品：1是，0否
	 * 
	 * @param is_vip
	 */
	public void setIs_vip(Integer is_vip) {
		this.is_vip = is_vip;
	}
	public BigDecimal getShop_group_market_price() {
		return shop_group_market_price;
	}
	public void setShop_group_market_price(BigDecimal shop_group_market_price) {
		this.shop_group_market_price = shop_group_market_price;
	}
	public Integer getPrompt_goods() {
		return prompt_goods;
	}
	public void setPrompt_goods(Integer prompt_goods) {
		this.prompt_goods = prompt_goods;
	}

	private String user_id;
    private Integer goods_num;
    
    public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}

	private List<ZjcSpecGoodsPricePO> zjcSpecGoodsPricePO;
    
    //商品类别 1购商品2购实惠3易生活
    private int commodity_categories;
    
	public int getCommodity_categories() {
		return commodity_categories;
	}
	public void setCommodity_categories(int commodity_categories) {
		this.commodity_categories = commodity_categories;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * 更新次数
	 * 
	 * @return
	 */
	public Integer getGxcs() {
		return gxcs;
	}
	/**
	 * 更新次数
	 * 
	 * @param gxcs
	 */
	public void setGxcs(Integer gxcs) {
		this.gxcs = gxcs;
	}
	/**
	 * 是否是特殊商品：1是，0否
	 * 
	 * @return
	 */
	public Integer getIs_car() {
		return is_car;
	}
	/**
	 * 是否是特殊商品：1是，0否
	 * 
	 * @param is_car
	 */
	public void setIs_car(Integer is_car) {
		this.is_car = is_car;
	}
	
	public String getGoods_weight() {
		return goods_weight;
	}
	public void setGoods_weight(String goods_weight) {
		this.goods_weight = goods_weight;
	}
	
	/**
	 * 是否是xpt平台
	 */
	private String xpt;
	
	
	public String getXpt() {
		return xpt;
	}
	public void setXpt(String xpt) {
		this.xpt = xpt;
	}
	
	/**
	 * 是否是可用代金券商品;0:否，1:是
	 */
	private int is_voucher;
	
	/**
	 * 是否是可用代金券商品;0:否，1:是
	 * 
	 * @return is_voucher
	 */
	public int getIs_voucher() {
		return is_voucher;
	}
	
	/**
	 * 是否是可用代金券商品;0:否，1:是
	 * 
	 * @param is_voucher
	 */
	public void setIs_voucher(int is_voucher) {
		this.is_voucher = is_voucher;
	}

	/**
	 * 审核人
	 */
	private String aduit_user_name;
	
	/**
	 * 店铺信息
	 */
	private ZjcStorePO ZjcStorePO;
	
	public ZjcStorePO getZjcStorePO() {
		return ZjcStorePO;
	}

	public void setZjcStorePO(ZjcStorePO zjcStorePO) {
		ZjcStorePO = zjcStorePO;
	}

	/**
	 * 商品图片信息列表数据
	 */
	private List<ZjcGoodsImagesPO> ZjcGoodsImagesPO;
	
	public List<ZjcGoodsImagesPO> getZjcGoodsImagesPO() {
		return ZjcGoodsImagesPO;
	}

	public void setZjcGoodsImagesPO(List<ZjcGoodsImagesPO> zjcGoodsImagesPO) {
		ZjcGoodsImagesPO = zjcGoodsImagesPO;
	}
	
	/**
	 * 商品规格型号列表数据
	 */
	private List<ZjcSpecPO> zjcSpecPOList;
	

	public List<ZjcSpecPO> getZjcSpecPOList() {
		return zjcSpecPOList;
	}
	public void setZjcSpecPOList(List<ZjcSpecPO> zjcSpecPOList) {
		this.zjcSpecPOList = zjcSpecPOList;
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
	 * 分类id
	 * 
	 * @return cat_id
	 */
	public Integer getCat_id() {
		return cat_id;
	}
	
	/**
	 * 二级分类
	 * 
	 * @return cat_id2
	 */
	public Integer getCat_id2() {
		return cat_id2;
	}
	
	/**
	 * 扩展分类id
	 * 
	 * @return extend_cat_id
	 */
	public Integer getExtend_cat_id() {
		return extend_cat_id;
	}
	
	/**
	 * 商品编号
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
	 * 店铺ID 
	 * 
	 * @return store_id
	 */
	public Integer getStore_id() {
		return store_id;
	}
	
	/**
	 * 点击数
	 * 
	 * @return click_count
	 */
	public Integer getClick_count() {
		return click_count;
	}
	
	/**
	 * 品牌id
	 * 
	 * @return brand_id
	 */
	public Integer getBrand_id() {
		return brand_id;
	}
	
	/**
	 * 库存数量
	 * 
	 * @return store_count
	 */
	public Integer getStore_count() {
		return store_count;
	}
	
	/**
	 * 商品评论数
	 * 
	 * @return comment_count
	 */
	public Integer getComment_count() {
		return comment_count;
	}
	
	/**
	 * 商品重量克为单位
	 * 
	 * @return weight
	 */
	public Integer getWeight() {
		return weight;
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
	 * 商品关键词
	 * 
	 * @return keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * 商品简单描述
	 * 
	 * @return goods_remark
	 */
	public String getGoods_remark() {
		return goods_remark;
	}
	
	/**
	 * 商品详细描述
	 * 
	 * @return goods_content
	 */
	public String getGoods_content() {
		return goods_content;
	}
	
	/**
	 * 商品上传原始图
	 * 
	 * @return original_img
	 */
	public String getOriginal_img() {
		return original_img;
	}
	
	/**
	 * 是否为实物
	 * 
	 * @return is_real
	 */
	public Integer getIs_real() {
		return is_real;
	}
	
	/**
	 * 是否上架
	 * 
	 * @return is_on_sale
	 */
	public Integer getIs_on_sale() {
		return is_on_sale;
	}
	
	/**
	 * 是否包邮0否1是
	 * 
	 * @return is_free_shipping
	 */
	public Integer getIs_free_shipping() {
		return is_free_shipping;
	}
	
	/**
	 * 商品上架时间
	 * 
	 * @return on_time
	 */
	public Date getOn_time() {
		return on_time;
	}
	
	/**
	 * 商品排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * 是否推荐(中军创的优选商品)
	 * 
	 * @return is_recommend
	 */
	public Integer getIs_recommend() {
		return is_recommend;
	}
	
	/**
	 * 是否新品
	 * 
	 * @return is_new
	 */
	public Integer getIs_new() {
		return is_new;
	}
	
	/**
	 * 是否热卖
	 * 
	 * @return is_hot
	 */
	public Integer getIs_hot() {
		return is_hot;
	}
	
	/**
	 * 最后更新时间
	 * 
	 * @return last_update
	 */
	public Date getLast_update() {
		return last_update;
	}
	
	/**
	 * 商品所属类型id，取值表goods_type的cat_id
	 * 
	 * @return goods_type
	 */
	public Integer getGoods_type() {
		return goods_type;
	}
	
	/**
	 * 商品规格类型，取值表goods_type的cat_id
	 * 
	 * @return spec_type
	 */
	public Integer getSpec_type() {
		return spec_type;
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
	 * 积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置
	 * 
	 * @return exchange_integral
	 */
	public Integer getExchange_integral() {
		return exchange_integral;
	}
	
	/**
	 * 供货商ID
	 * 
	 * @return suppliers_id
	 */
	public Integer getSuppliers_id() {
		return suppliers_id;
	}
	
	/**
	 * 商品销量
	 * 
	 * @return sales_sum
	 */
	public Integer getSales_sum() {
		return sales_sum;
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
	 * 优惠活动id
	 * 
	 * @return prom_id
	 */
	public Integer getProm_id() {
		return prom_id;
	}
	
	/**
	 * 佣金用于分销分成
	 * 
	 * @return commission
	 */
	public BigDecimal getCommission() {
		return commission;
	}
	
	/**
	 * 当日每人限定购买次数
	 * 
	 * @return spu
	 */
	public String getSpu() {
		return spu;
	}
	
	/**
	 * 当日每人限定购买数量
	 * 
	 * @return sku
	 */
	public String getSku() {
		return sku;
	}
	
	/**
	 * 配送物流shipping_area_id,以逗号分隔
	 * 
	 * @return shipping_area_ids
	 */
	public String getShipping_area_ids() {
		return shipping_area_ids;
	}
	
	/**
	 * 审核员审核状态  1：待审核  2：审核失败 3：审核通过
	 * 
	 * @return goods_state_1
	 */
	public Integer getGoods_state_1() {
		return goods_state_1;
	}
	
	/**
	 * 管理员审核状态  1：待审核 2：审核失败 3：审核成功
	 * 
	 * @return goods_state_2
	 */
	public Integer getGoods_state_2() {
		return goods_state_2;
	}
	
	/**
	 * 提成比例一
	 * 
	 * @return commission_rate_1
	 */
	public String getCommission_rate_1() {
		return commission_rate_1;
	}
	
	/**
	 * 提成比例二
	 * 
	 * @return commission_rate_2
	 */
	public String getCommission_rate_2() {
		return commission_rate_2;
	}
	
	/**
	 * 提成比例三
	 * 
	 * @return commission_rate_3
	 */
	public String getCommission_rate_3() {
		return commission_rate_3;
	}
	
	/**
	 * 钱包扩增比例
	 * 
	 * @return wallet_amplification_rate
	 */
	public String getWallet_amplification_rate() {
		return wallet_amplification_rate;
	}
	
	/**
	 * 商家返利比例
	 * 
	 * @return store_rebate_rate
	 */
	public String getStore_rebate_rate() {
		return store_rebate_rate;
	}
	
	/**
	 * 审批员操作信息，序列化后数据
	 * 
	 * @return aduit_info
	 */
	public String getAduit_info() {
		return aduit_info;
	}
	
	/**
	 * 管理员审核备注，序列化数据
	 * 
	 * @return admin_aduit_info
	 */
	public String getAdmin_aduit_info() {
		return admin_aduit_info;
	}
	
	/**
	 * 以物易物会员积分返还倍数
	 * 
	 * @return market_price_bs
	 */
	public String getMarket_price_bs() {
		return market_price_bs;
	}
	
	/**
	 * 在线支付会员积分返还倍数
	 * 
	 * @return shop_price_bs
	 */
	public String getShop_price_bs() {
		return shop_price_bs;
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
	 * 分类id
	 * 
	 * @param cat_id
	 */
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	
	/**
	 * 二级分类
	 * 
	 * @param cat_id2
	 */
	public void setCat_id2(Integer cat_id2) {
		this.cat_id2 = cat_id2;
	}
	
	/**
	 * 扩展分类id
	 * 
	 * @param extend_cat_id
	 */
	public void setExtend_cat_id(Integer extend_cat_id) {
		this.extend_cat_id = extend_cat_id;
	}
	
	/**
	 * 商品编号
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
	 * 店铺ID 
	 * 
	 * @param store_id
	 */
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * 点击数
	 * 
	 * @param click_count
	 */
	public void setClick_count(Integer click_count) {
		this.click_count = click_count;
	}
	
	/**
	 * 品牌id
	 * 
	 * @param brand_id
	 */
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	
	/**
	 * 库存数量
	 * 
	 * @param store_count
	 */
	public void setStore_count(Integer store_count) {
		this.store_count = store_count;
	}
	
	/**
	 * 商品评论数
	 * 
	 * @param comment_count
	 */
	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
	
	/**
	 * 商品重量克为单位
	 * 
	 * @param weight
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
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
	 * 商品关键词
	 * 
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 商品简单描述
	 * 
	 * @param goods_remark
	 */
	public void setGoods_remark(String goods_remark) {
		this.goods_remark = goods_remark;
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
	 * 商品上传原始图
	 * 
	 * @param original_img
	 */
	public void setOriginal_img(String original_img) {
		this.original_img = original_img;
	}
	
	/**
	 * 是否为实物
	 * 
	 * @param is_real
	 */
	public void setIs_real(Integer is_real) {
		this.is_real = is_real;
	}
	
	/**
	 * 是否上架
	 * 
	 * @param is_on_sale
	 */
	public void setIs_on_sale(Integer is_on_sale) {
		this.is_on_sale = is_on_sale;
	}
	
	/**
	 * 是否包邮0否1是
	 * 
	 * @param is_free_shipping
	 */
	public void setIs_free_shipping(Integer is_free_shipping) {
		this.is_free_shipping = is_free_shipping;
	}
	
	/**
	 * 商品上架时间
	 * 
	 * @param on_time
	 */
	public void setOn_time(Date on_time) {
		this.on_time = on_time;
	}
	
	/**
	 * 商品排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * 是否推荐(中军创的优选商品)
	 * 
	 * @param is_recommend
	 */
	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}
	
	/**
	 * 是否新品
	 * 
	 * @param is_new
	 */
	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}
	
	/**
	 * 是否热卖
	 * 
	 * @param is_hot
	 */
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	
	/**
	 * 最后更新时间
	 * 
	 * @param last_update
	 */
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	/**
	 * 商品所属类型id，取值表goods_type的cat_id
	 * 
	 * @param goods_type
	 */
	public void setGoods_type(Integer goods_type) {
		this.goods_type = goods_type;
	}
	
	/**
	 * 商品规格类型，取值表goods_type的cat_id
	 * 
	 * @param spec_type
	 */
	public void setSpec_type(Integer spec_type) {
		this.spec_type = spec_type;
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
	 * 积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置
	 * 
	 * @param exchange_integral
	 */
	public void setExchange_integral(Integer exchange_integral) {
		this.exchange_integral = exchange_integral;
	}
	
	/**
	 * 供货商ID
	 * 
	 * @param suppliers_id
	 */
	public void setSuppliers_id(Integer suppliers_id) {
		this.suppliers_id = suppliers_id;
	}
	
	/**
	 * 商品销量
	 * 
	 * @param sales_sum
	 */
	public void setSales_sum(Integer sales_sum) {
		this.sales_sum = sales_sum;
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
	 * 优惠活动id
	 * 
	 * @param prom_id
	 */
	public void setProm_id(Integer prom_id) {
		this.prom_id = prom_id;
	}
	
	/**
	 * 佣金用于分销分成
	 * 
	 * @param commission
	 */
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	
	/**
	 * 当日每人限定购买次数
	 * 
	 * @param spu
	 */
	public void setSpu(String spu) {
		this.spu = spu;
	}
	
	/**
	 * 当日每人限定购买次数
	 * 
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	/**
	 * 配送物流shipping_area_id,以逗号分隔
	 * 
	 * @param shipping_area_ids
	 */
	public void setShipping_area_ids(String shipping_area_ids) {
		this.shipping_area_ids = shipping_area_ids;
	}
	
	/**
	 * 审核员审核状态  1：待审核  2：审核失败 3：审核通过
	 * 
	 * @param goods_state_1
	 */
	public void setGoods_state_1(Integer goods_state_1) {
		this.goods_state_1 = goods_state_1;
	}
	
	/**
	 * 管理员审核状态  1：待审核 2：审核失败 3：审核成功
	 * 
	 * @param goods_state_2
	 */
	public void setGoods_state_2(Integer goods_state_2) {
		this.goods_state_2 = goods_state_2;
	}
	
	/**
	 * 提成比例一
	 * 
	 * @param commission_rate_1
	 */
	public void setCommission_rate_1(String commission_rate_1) {
		this.commission_rate_1 = commission_rate_1;
	}
	
	/**
	 * 提成比例二
	 * 
	 * @param commission_rate_2
	 */
	public void setCommission_rate_2(String commission_rate_2) {
		this.commission_rate_2 = commission_rate_2;
	}
	
	/**
	 * 提成比例三
	 * 
	 * @param commission_rate_3
	 */
	public void setCommission_rate_3(String commission_rate_3) {
		this.commission_rate_3 = commission_rate_3;
	}
	
	/**
	 * 钱包扩增比例
	 * 
	 * @param wallet_amplification_rate
	 */
	public void setWallet_amplification_rate(String wallet_amplification_rate) {
		this.wallet_amplification_rate = wallet_amplification_rate;
	}
	
	/**
	 * 商家返利比例
	 * 
	 * @param store_rebate_rate
	 */
	public void setStore_rebate_rate(String store_rebate_rate) {
		this.store_rebate_rate = store_rebate_rate;
	}
	
	/**
	 * 审批员操作信息，序列化后数据
	 * 
	 * @param aduit_info
	 */
	public void setAduit_info(String aduit_info) {
		this.aduit_info = aduit_info;
	}
	
	/**
	 * 管理员审核备注，序列化数据
	 * 
	 * @param admin_aduit_info
	 */
	public void setAdmin_aduit_info(String admin_aduit_info) {
		this.admin_aduit_info = admin_aduit_info;
	}
	
	/**
	 * 以物易物会员积分返还倍数
	 * 
	 * @param market_price_bs
	 */
	public void setMarket_price_bs(String market_price_bs) {
		this.market_price_bs = market_price_bs;
	}
	
	/**
	 * 在线支付会员积分返还倍数
	 * 
	 * @param shop_price_bs
	 */
	public void setShop_price_bs(String shop_price_bs) {
		this.shop_price_bs = shop_price_bs;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Integer getApproval_id() {
		return approval_id;
	}

	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}
	

	public String getAduit_user_name() {
		return aduit_user_name;
	}

	public void setAduit_user_name(String aduit_user_name) {
		this.aduit_user_name = aduit_user_name;
	}

	public boolean isHasCollect() {
		return hasCollect;
	}

	public void setHasCollect(boolean hasCollect) {
		this.hasCollect = hasCollect;
	}
	public Integer getIs_gb() {
		return is_gb;
	}
	public void setIs_gb(Integer is_gb) {
		this.is_gb = is_gb;
	}
	/**
	 * 是否混合支付：1是，0否
	 * @return
	 */
	public Integer getIs_mixed() {
		return is_mixed;
	}
	/**
	 * 是否混合支付：1是，0否
	 * @param is_mixed
	 */
	public void setIs_mixed(Integer is_mixed) {
		this.is_mixed = is_mixed;
	}
	public String getPlace_of_production() {
		return place_of_production;
	}
	public void setPlace_of_production(String place_of_production) {
		this.place_of_production = place_of_production;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getPacking_info() {
		return packing_info;
	}
	public void setPacking_info(String packing_info) {
		this.packing_info = packing_info;
	}
	public String getGoods_features() {
		return goods_features;
	}
	public void setGoods_features(String goods_features) {
		this.goods_features = goods_features;
	}
	public String getContract_phone() {
		return contract_phone;
	}
	public void setContract_phone(String contract_phone) {
		this.contract_phone = contract_phone;
	}
	public List<ZjcSpecGoodsPricePO> getZjcSpecGoodsPricePO() {
		return zjcSpecGoodsPricePO;
	}
	public void setZjcSpecGoodsPricePO(List<ZjcSpecGoodsPricePO> zjcSpecGoodsPricePO) {
		this.zjcSpecGoodsPricePO = zjcSpecGoodsPricePO;
	}
	public Integer getSpecial_offer() {
		return special_offer;
	}
	public void setSpecial_offer(Integer special_offer) {
		this.special_offer = special_offer;
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
	
}
