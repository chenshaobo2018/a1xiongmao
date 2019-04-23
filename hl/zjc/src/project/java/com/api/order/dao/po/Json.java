/**
 * 
 */
package com.api.order.dao.po;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class Json {
   private String total_amount;//订单总价
   private String address_id;
   private String user_id;
   private String user_note;//用户备注
   private String pay_type;//支付方式
   private String pay_name;//支付方式
   private String store_id;
   private String postFee;//运费
   private String is_shop_group;//是否是拼团订单
   private List<Goods> Goods;

public String getIs_shop_group() {
	return is_shop_group;
}
public void setIs_shop_group(String is_shop_group) {
	this.is_shop_group = is_shop_group;
}
public String getTotal_amount() {
	return total_amount;
}
public void setTotal_amount(String total_amount) {
	this.total_amount = total_amount;
}
public String getAddress_id() {
	return address_id;
}
public void setAddress_id(String address_id) {
	this.address_id = address_id;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUser_note() {
	return user_note;
}
public void setUser_note(String user_note) {
	this.user_note = user_note;
}
public String getPay_type() {
	return pay_type;
}
public void setPay_type(String pay_type) {
	this.pay_type = pay_type;
}
public String getStore_id() {
	return store_id;
}
public void setStore_id(String store_id) {
	this.store_id = store_id;
}
public String getPostFee() {
	return postFee;
}
public void setPostFee(String postFee) {
	this.postFee = postFee;
}
public List<Goods> getGoods() {
	return Goods;
}
public void setGoods(List<Goods> goods) {
	Goods = goods;
}
public String getPay_name() {
	return pay_name;
}
public void setPay_name(String pay_name) {
	this.pay_name = pay_name;
}

}
