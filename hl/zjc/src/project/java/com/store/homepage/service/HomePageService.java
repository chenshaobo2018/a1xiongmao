/**
 * 
 */
package com.store.homepage.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.common.util.ConstantUtil;
import com.store.homepage.HomePageVO;
import com.store.homepage.RecentDays;
import com.store.homepage.RecentMonths;
import com.store.homepage.RecentWeeks;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author lwj
 *
 */
@Service(value="homePageService")
public class HomePageService {
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	/**
	 * 商家页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/store/zjcUsersInfolist.jsp");
		}
	
	public HomePageVO getStore(HttpModel httpModel) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		Dto qDto = httpModel.getInDto();
		qDto.put("user_id", sellerInfoPO.getUser_id());
		//查询门店信息
		List<ZjcStorePO> store = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.selectOne",qDto);
		HomePageVO homepage=new HomePageVO();
		homepage.setStore_name(store.get(0).getStore_name());
		homepage.setTime(formatter.format(store.get(0).getLast_login_time()));
		//查询用户信息
		List<ZjcSellerInfoPO> paramDtos = sqlDao.list("com.zjc.store.dao.ZjcSellerInfoDao.list",Dtos.newDto("user_id", store.get(0).getUser_id()));
		homepage.setUser_name(paramDtos.get(0).getReal_name());
		homepage.setImg(paramDtos.get(0).getHead_pic());
		ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
		ZjcUsersAccountInfoPO.setUser_id(store.get(0).getUser_id());
		//查询资金信息
		List<ZjcUsersAccountInfoPO> UsersAccountInfo = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.usersd",ZjcUsersAccountInfoPO);
		homepage.setTotal_amount(UsersAccountInfo.get(0).getMake_over_integral());
		qDto.put("limit", ConstantUtil.pageSize);
		qDto.put("page", 1);
		qDto.put("start", (qDto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<Dto> dtos = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.billListPage1", qDto);
		if(AOSUtils.isEmpty(dtos.get(0))){
			homepage.setBalance(new BigDecimal(0));
		} else {
			if(AOSUtils.isEmpty(dtos.get(0).getBigDecimal("cost"))){
				homepage.setBalance(new BigDecimal(0));
			}else{
				homepage.setBalance(dtos.get(0).getBigDecimal("cost"));
			}
		}
		ZjcStorePO ZjcStorePO=new ZjcStorePO();
		ZjcStorePO.setStore_id(store.get(0).getStore_id());
		//查询订单信息
		List<ZjcStorePO> dayorder = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.dayorder",ZjcStorePO);
		//统计今天卖出商品总数
		Integer  goodsPOs = (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderGoodsDao.count_today", qDto);
		if(AOSUtils.isEmpty(UsersAccountInfo.get(0).getHas_terminal())){
			homepage.setHas_terminal(0);
		}else{
			homepage.setHas_terminal(UsersAccountInfo.get(0).getHas_terminal());
		}
		homepage.setUser_id(UsersAccountInfo.get(0).getUser_id());
		if(dayorder.get(0)==null){
	  		homepage.setDay_amount(0);
	  		homepage.setDay_paid(0);
	  		homepage.setDay_send_goods(0);
	  		homepage.setDay_evaluate(0);
	  		homepage.setTotal_order(0);
	  		homepage.setTotal_goods(0);
		}else{
			//homepage.setTotal_amount(dayorder.get(0).getZjcUsersAccountInfoPO().getMake_over_integral());
			//TODO
			homepage.setDay_amount(dayorder.get(0).getProvince_id());
			homepage.setDay_paid(dayorder.get(0).getCity_id());
			homepage.setDay_send_goods(dayorder.get(0).getArea_id());
			homepage.setDay_evaluate(dayorder.get(0).getTwon_id());
			homepage.setTotal_order(dayorder.get(0).getCat_id());
			homepage.setTotal_goods(goodsPOs);
		}
		ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
		ZjcGoodsPO.setStore_id(store.get(0).getStore_id());
		//查询商品信息
		List<ZjcGoodsPO> goods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.dayorder",ZjcGoodsPO);
		if(goods.get(0) == null){
			homepage.setThe_sale_goods(0);
			homepage.setOut_stock_goods(0);
			homepage.setShelves_goods(0);
			homepage.setHot_goods(0);
		}else{
			homepage.setThe_sale_goods(goods.get(0).getIs_on_sale());
			homepage.setOut_stock_goods(goods.get(0).getStore_count());
			homepage.setShelves_goods(goods.get(0).getCat_id());
			homepage.setHot_goods(goods.get(0).getIs_hot());
		}		
		ZjcOrderPO ZjcOrderPO=new ZjcOrderPO();
		ZjcOrderPO.setStore_id(store.get(0).getStore_id());
		//订单统计信息
		List<ZjcOrderPO> order = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ordercount",ZjcOrderPO);
		if(order.get(0) == null){
			homepage.setYesterday_sales(0);
			homepage.setSales(0);
			homepage.setCount_order(0);
			homepage.setTotal_sales(0);
		}else{
			homepage.setYesterday_sales(order.get(0).getCountry());
			homepage.setSales(order.get(0).getProvince());
			homepage.setCount_order(order.get(0).getCity());
			homepage.setTotal_sales(order.get(0).getDistrict());
		}
		if(order.get(0).getTwon()!=null){
			homepage.setAmount(order.get(0).getTwon());
		}else{
			homepage.setAmount(0);
		}		
		ZjcUserLogPO userlog=new ZjcUserLogPO();
		userlog.setUser_id(sellerInfoPO.getUser_id());
		//查询当天操作数据
		List<ZjcUserLogPO> Days = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.RecentDays",userlog);
		List<RecentDays> list=new ArrayList<RecentDays>();
		for (int i = 0; i < Days.size(); i++) {
			RecentDays RecentDays=new RecentDays();
			RecentDays.setOperation_records(Days.get(i).getDescs());
			RecentDays.setOperation_time(formatter.format(Days.get(i).getTime()));
			list.add(RecentDays);
		}
		homepage.setRecentDays(list);
		//查询当周操作数据
		List<ZjcUserLogPO> Weeks = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.RecentWeeks",userlog);
		List<RecentWeeks> list1=new ArrayList<RecentWeeks>();
		for (int i = 0; i < Weeks.size(); i++) {
			RecentWeeks RecentWeeks=new RecentWeeks();
			RecentWeeks.setOperation_records(Weeks.get(i).getDescs());
			RecentWeeks.setOperation_time(formatter.format(Weeks.get(i).getTime()));
			list1.add(RecentWeeks);
		}
		homepage.setRecentWeeks(list1);
		//查询当月操作数据
		List<ZjcUserLogPO> Months = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.RecentMonths",userlog);
		List<RecentMonths> list2=new ArrayList<RecentMonths>();
		for (int i = 0; i < Months.size(); i++) {
			RecentMonths RecentMonths=new RecentMonths();
			RecentMonths.setOperation_records(Months.get(i).getDescs());
			RecentMonths.setOperation_time(formatter.format(Months.get(i).getTime()));
			list2.add(RecentMonths);
		}
		homepage.setRecentMonths(list2);
		return homepage;
	}
	
	public HomePageVO transfer(HttpModel httpModel) throws ParseException{
		Dto qDto = httpModel.getInDto();
		ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
		ZjcUsersInfoPO.setUser_id(new BigInteger(qDto.get("user_id").toString()));
		ZjcUsersInfoPO.setMobile(qDto.get("mobile").toString());
		ZjcUsersInfoPO.setReal_name(qDto.get("real_name").toString());
		List<ZjcUsersInfoPO> user = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.transfer",ZjcUsersInfoPO);
		ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
		ZjcUsersAccountInfoPO.setUser_id(user.get(0).getUser_id());
		List<ZjcUsersAccountInfoPO> UsersAccountInfo = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.usersd",qDto);
		int pay_points=Integer.parseInt(qDto.get("pay_points").toString())-Integer.parseInt(qDto.get("poundage").toString());
		int pay=pay_points+UsersAccountInfo.get(0).getPay_points();
		UsersAccountInfo.get(0).setPay_points(pay);
		ZjcUsersAccountInfoDao.updateByKey(UsersAccountInfo.get(0));
		return null;
	}
	
	
	public String userid(HttpModel httpModel) throws ParseException{
		Dto qDto = httpModel.getInDto();
		ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
		ZjcUsersInfoPO.setUser_id(new BigInteger(qDto.get("user_id").toString()));
		List<ZjcUsersInfoPO> user = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getUser",ZjcUsersInfoPO);
		String mag="用户id不存在";
		if(user.size()==1){
			mag="用户id合法";
		}
		return mag;
	}
	
	public String getrealname(HttpModel httpModel) throws ParseException{
		Dto qDto = httpModel.getInDto();
		ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
		ZjcUsersInfoPO.setUser_id(new BigInteger(qDto.get("user_id").toString()));
		ZjcUsersInfoPO.setReal_name(qDto.get("real_name").toString());
		List<ZjcUsersInfoPO> user = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getrealname",ZjcUsersInfoPO);
		String mag="用户id和姓名不匹配";
		if(user.size()==1){
			mag="真实姓名合法";
		}
		return mag;
	}
	
	public String getmobile(HttpModel httpModel) throws ParseException{
		Dto qDto = httpModel.getInDto();
		ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
		ZjcUsersInfoPO.setUser_id(new BigInteger(qDto.get("user_id").toString()));
		ZjcUsersInfoPO.setReal_name(qDto.get("real_name").toString());
		ZjcUsersInfoPO.setMobile(qDto.get("mobile").toString());
		List<ZjcUsersInfoPO> user = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.transfer",ZjcUsersInfoPO);
		String mag="用户id和姓名和电话不匹配";
		if(user.size()==1){
			mag="电话合法";
		}
		return mag;
	}
}
