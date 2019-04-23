/**
 * 
 */
package com.store.homepage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import aos.framework.core.typewrap.PO;

/**
 * 首页数据
 * @author Administrator
 *
 */
public class HomePageVO extends PO {
	private static final long serialVersionUID = 1L;
	
 private String user_name;//用户名
 private String user_permissions;//管理权限
 private String store_name;//店铺名称
 private String time;//最后登录时间
 private int total_amount;//用户可转金额
 private int day_amount;//待支付
 private int day_determined;
 private int day_paid;//待发货
 private int day_send_goods;//待评价
 private int day_evaluate;//完成
 private int day_completed;//
 private int total_order;//当天总订单
 private int the_sale_goods;//出售中的商品
 private int out_stock_goods;//缺货商品
 private int shelves_goods;//下架商品
 private int hot_goods;;//热销商品
 private int yesterday_sales;//昨天销量
 private int sales;//月销量
 private int amount;//当月销售额
 private int count_order;//总订单量
 private int total_sales;//总销售额
 private String img;//头像
 private int total_goods;//今天卖出的商品数
 private BigDecimal balance;//账户余额
 private BigInteger user_id;//账号user_id
 private Integer has_terminal;//是否开通终端
 private List<RecentDays> recentDays;

private List<RecentWeeks> recentWeeks;
 private List<RecentMonths> recentMonths;
 
 
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getUser_permissions() {
	return user_permissions;
}
public void setUser_permissions(String user_permissions) {
	this.user_permissions = user_permissions;
}

public String getStore_name() {
	return store_name;
}
public void setStore_name(String store_name) {
	this.store_name = store_name;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getTotal_amount() {
	return total_amount;
}
public void setTotal_amount(int total_amount) {
	this.total_amount = total_amount;
}
public int getDay_amount() {
	return day_amount;
}
public void setDay_amount(int day_amount) {
	this.day_amount = day_amount;
}
public int getDay_determined() {
	return day_determined;
}
public void setDay_determined(int day_determined) {
	this.day_determined = day_determined;
}
public int getDay_paid() {
	return day_paid;
}
public void setDay_paid(int day_paid) {
	this.day_paid = day_paid;
}
public int getDay_send_goods() {
	return day_send_goods;
}
public void setDay_send_goods(int day_send_goods) {
	this.day_send_goods = day_send_goods;
}
public int getDay_evaluate() {
	return day_evaluate;
}
public void setDay_evaluate(int day_evaluate) {
	this.day_evaluate = day_evaluate;
}
public int getDay_completed() {
	return day_completed;
}
public void setDay_completed(int day_completed) {
	this.day_completed = day_completed;
}
public int getTotal_order() {
	return total_order;
}
public void setTotal_order(int total_order) {
	this.total_order = total_order;
}
public int getThe_sale_goods() {
	return the_sale_goods;
}
public void setThe_sale_goods(int the_sale_goods) {
	this.the_sale_goods = the_sale_goods;
}
public int getOut_stock_goods() {
	return out_stock_goods;
}
public void setOut_stock_goods(int out_stock_goods) {
	this.out_stock_goods = out_stock_goods;
}
public int getShelves_goods() {
	return shelves_goods;
}
public void setShelves_goods(int shelves_goods) {
	this.shelves_goods = shelves_goods;
}
public int getHot_goods() {
	return hot_goods;
}
public void setHot_goods(int hot_goods) {
	this.hot_goods = hot_goods;
}

public int getYesterday_sales() {
	return yesterday_sales;
}
public void setYesterday_sales(int yesterday_sales) {
	this.yesterday_sales = yesterday_sales;
}
public int getSales() {
	return sales;
}
public void setSales(int sales) {
	this.sales = sales;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getCount_order() {
	return count_order;
}
public void setCount_order(int count_order) {
	this.count_order = count_order;
}

public int getTotal_sales() {
	return total_sales;
}
public void setTotal_sales(int total_sales) {
	this.total_sales = total_sales;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public List<RecentDays> getRecentDays() {
	return recentDays;
}
public void setRecentDays(List<RecentDays> recentDays) {
	this.recentDays = recentDays;
}
public List<RecentWeeks> getRecentWeeks() {
	return recentWeeks;
}
public void setRecentWeeks(List<RecentWeeks> recentWeeks) {
	this.recentWeeks = recentWeeks;
}
public List<RecentMonths> getRecentMonths() {
	return recentMonths;
}
public void setRecentMonths(List<RecentMonths> recentMonths) {
	this.recentMonths = recentMonths;
}
public int getTotal_goods() {
	return total_goods;
}
public void setTotal_goods(int total_goods) {
	this.total_goods = total_goods;
}
public BigDecimal getBalance() {
	return balance;
}
public void setBalance(BigDecimal balance) {
	this.balance = balance;
}
public Integer getHas_terminal() {
	return has_terminal;
}
public void setHas_terminal(Integer has_terminal) {
	this.has_terminal = has_terminal;
}
public BigInteger getUser_id() {
	return user_id;
}
public void setUser_id(BigInteger user_id) {
	this.user_id = user_id;
}
}
