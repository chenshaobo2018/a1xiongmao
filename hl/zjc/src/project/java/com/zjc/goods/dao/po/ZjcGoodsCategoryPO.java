package com.zjc.goods.dao.po;

import java.util.List;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_goods_category[zjc_goods_category]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-05-22 17:25:46
 */
public class ZjcGoodsCategoryPO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品分类id
	 */
	private Integer id;
	
	/**
	 * 商品分类名称
	 */
	private String name;
	
	/**
	 * 手机端显示的商品分类名
	 */
	private String mobile_name;
	
	/**
	 * 父id
	 */
	private Integer parent_id;
	
	/**
	 * 父名称
	 */
	private String parent_name;
	/**
	 * 家族图谱
	 */
	private String parent_id_path;
	
	/**
	 * 等级
	 */
	private Integer level;
	
	/**
	 * 顺序排序
	 */
	private Integer sort_order;
	
	/**
	 * 是否显示
	 */
	private Integer is_show;
	
	/**
	 * 分类图片
	 */
	private String image;
	
	/**
	 * 是否推荐为热门分类
	 */
	private Integer is_hot;
	
	/**
	 * 分类分组默认0
	 */
	private Integer cat_group;
	
	/**
	 * 提成比例一
	 */
	private String commission_rate_1;
	
	/**
	 * 提成比例二
	 */
	private String commission_rate_2;
	
	/**
	 * 提成比例二
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
	 * 子节点
	 */
	private List<ZjcGoodsCategoryPO> child_list;
	

	/**
	 * 商品分类id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 商品分类名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 手机端显示的商品分类名
	 * 
	 * @return mobile_name
	 */
	public String getMobile_name() {
		return mobile_name;
	}
	
	/**
	 * 父id
	 * 
	 * @return parent_id
	 */
	public Integer getParent_id() {
		return parent_id;
	}
	
	/**
	 * 家族图谱
	 * 
	 * @return parent_id_path
	 */
	public String getParent_id_path() {
		return parent_id_path;
	}
	
	/**
	 * 等级
	 * 
	 * @return level
	 */
	public Integer getLevel() {
		return level;
	}
	
	/**
	 * 顺序排序
	 * 
	 * @return sort_order
	 */
	public Integer getSort_order() {
		return sort_order;
	}
	
	/**
	 * 是否显示
	 * 
	 * @return is_show
	 */
	public Integer getIs_show() {
		return is_show;
	}
	
	/**
	 * 分类图片
	 * 
	 * @return image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * 是否推荐为热门分类
	 * 
	 * @return is_hot
	 */
	public Integer getIs_hot() {
		return is_hot;
	}
	
	/**
	 * 分类分组默认0
	 * 
	 * @return cat_group
	 */
	public Integer getCat_group() {
		return cat_group;
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
	 * 提成比例二
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
	 * 商品分类id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 商品分类名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 手机端显示的商品分类名
	 * 
	 * @param mobile_name
	 */
	public void setMobile_name(String mobile_name) {
		this.mobile_name = mobile_name;
	}
	
	/**
	 * 父id
	 * 
	 * @param parent_id
	 */
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 家族图谱
	 * 
	 * @param parent_id_path
	 */
	public void setParent_id_path(String parent_id_path) {
		this.parent_id_path = parent_id_path;
	}
	
	/**
	 * 等级
	 * 
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/**
	 * 顺序排序
	 * 
	 * @param sort_order
	 */
	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}
	
	/**
	 * 是否显示
	 * 
	 * @param is_show
	 */
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	
	/**
	 * 分类图片
	 * 
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * 是否推荐为热门分类
	 * 
	 * @param is_hot
	 */
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	
	/**
	 * 分类分组默认0
	 * 
	 * @param cat_group
	 */
	public void setCat_group(Integer cat_group) {
		this.cat_group = cat_group;
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
	 * 提成比例二
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

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public List<ZjcGoodsCategoryPO> getChild_list() {
		return child_list;
	}

	public void setChild_list(List<ZjcGoodsCategoryPO> child_list) {
		this.child_list = child_list;
	}
	
}