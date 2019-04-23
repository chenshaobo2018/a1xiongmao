/**
 * 
 */
package com.wxactivity.order.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.HttpClientUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.goods.dao.po.XpttsgoodsidPO;
import com.api.order.dao.po.Goods;
import com.api.order.dao.po.Json;
import com.api.order.dao.po.OrderVO;
import com.api.order.dao.po.ZjcCartPO;
import com.gexin.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUserAddressDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="ShareOrderService")
public class ShareOrderService {
	private static final Logger logger = LoggerFactory.getLogger(ShareOrderService.class);
	
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcOrderGoodsDao ZjcOrderGoodsDao;
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	@Autowired
	private ZjcUsersInfoDao ZjcUsersInfoDao;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private ZjcUserAddressDao zjcUserAddressDao;
	@Autowired
	private ZjcGoodsDao ZjcGoodsDao;
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	/*
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("serial")
	@Transactional(rollbackFor=Exception.class)
	public String orderSub(HttpServletRequest request, HttpServletResponse response) {
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("address_id")) || AOSUtils.isEmpty(dto.getString("total_amount")) || AOSUtils.isEmpty(dto.getString("pay_type"))
				|| AOSUtils.isEmpty(dto.getString("store_id"))|| AOSUtils.isEmpty(dto.getString("Goods")) || AOSUtils.isEmpty(dto.getString("goods_id"))){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		logger.info("========================微信activity下单追踪2：支付编码："+dto.getString("pay_type")+"，下单会员号："+ dto.getBigInteger("user_id"));
		MessageVO = apiPublicService.isLockedHandle();
		if(MessageVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(MessageVO);
		}
		
		MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", dto.getBigInteger("user_id")));
		if(MessageVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(MessageVO);
		}
		
		Json order=new Json();
		order.setAddress_id(httpModel.getRequest().getParameter("address_id").toString());
		order.setTotal_amount(httpModel.getRequest().getParameter("total_amount").toString());
		order.setPay_type(httpModel.getRequest().getParameter("pay_type").toString());
		if(order.getPay_type().equals("rate")){
			order.setPay_name("易支付");
		} else if(order.getPay_type().equals("equal")){
			order.setPay_name("易物券支付(不返)");
		} else if(order.getPay_type().equals("wxpay")){
			order.setPay_name("微信支付");
		} else if(order.getPay_type().equals("alipay")){
			order.setPay_name("支付宝支付");
		} else if(order.getPay_type().equals("unionpay")){
			order.setPay_name("银联支付");
		} else if(order.getPay_type().equals("mixed_payment")){
			order.setPay_name("混合支付");
		}
		order.setUser_note(httpModel.getRequest().getParameter("user_note").toString());
		order.setStore_id(httpModel.getRequest().getParameter("store_id").toString());
		String goodJson=httpModel.getRequest().getParameter("Goods").toString();
		List<Goods> goodList=AOSJson.fromJson(goodJson, new TypeToken<List<Goods>>(){}.getType());
		order.setGoods(goodList);
		order.setUser_id(dto.getString("user_id"));
		ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
		ZjcUsersAccountInfoPO.setUser_id(new BigInteger(order.getUser_id()));
		ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
		List<Goods> goods= order.getGoods();
		//判断是否开通结算中心
	    //List<ZjcUsersAccountInfoPO> uai = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    List<ZjcGoodsPO> ZjcGoods=new ArrayList<ZjcGoodsPO>();
	    ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", dto.getString("user_id")));
	    for (Goods goods2 : goods) {
	    	
	    	ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(Integer.parseInt(goods2.getGoods_id()));
	    	if(isgoodnum.getIs_mixed() == 1 && order.getPay_type().equals("rate")){//支付方式不符合商品要求
	    		 MessageVO.setMsg(Apiconstant.order_cannot_create.getName());
				 MessageVO.setCode(Apiconstant.order_cannot_create.getIndex());
		    	 return AOSJson.toJson(MessageVO);
	    	}
	    	if(isgoodnum.getPrompt_goods() ==1){//微信活动不能购买限时购商品
				 MessageVO.setMsg(Apiconstant.wx_cannot_buy.getName());
				 MessageVO.setCode(Apiconstant.wx_cannot_buy.getIndex());
		    	 return AOSJson.toJson(MessageVO);
			}
	    	if(Integer.parseInt(goods2.getGoods_num()) > isgoodnum.getToday_limit_nums()){//当日超出该商品每单的限购数量
	    		 MessageVO.setMsg("很抱歉！该商品每单限量" + isgoodnum.getToday_limit_nums()+"请重新下单");
				 MessageVO.setCode(Apiconstant.goods_num_enough.getIndex());
		    	 return AOSJson.toJson(MessageVO);
	    	}
	    	
	    	if(userInfo.getIs_open_min_pay()!= 1){//如果不能购买vip商品
    			int continueTimes= zjcTurnIntoUtil.getContinuousOrderDays(request.getAttribute("user_id").toString(), "3411419");
    			if(continueTimes<6){//连续六天在“1号店”购买任意一款预售商品，才能购买本商品
	    			 MessageVO.setMsg(Apiconstant.Goods_Cannot_Buy.getName());
					 MessageVO.setCode(Apiconstant.Goods_Cannot_Buy.getIndex());
			    	 return AOSJson.toJson(MessageVO);
	    		}
    			userInfo.setIs_open_min_pay(1);//满足连续购买6天，则可以购买vip商品
    			zjcUsersInfoDao.updateByKey(userInfo);
    		}
	    	
	    	Map<String,Object> map = new HashMap<String,Object>();
			map.put("user_id", httpModel.getAttribute("user_id"));
			map.put("goods_id", goods2.getGoods_id());
			Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
			if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
				MessageVO.setMsg("很抱歉！该商品每日每人只能购买" + isgoodnum.getToday_limit_times()+"次，请明天再来");
				MessageVO.setCode(Apiconstant.goods_times_enough.getIndex());
		    	return AOSJson.toJson(MessageVO);
			}
	    	
			ZjcGoodsPO.setGoods_id(Integer.parseInt(goods2.getGoods_id()));
			List<ZjcGoodsPO> getgoodsuserid = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getgoodsuserid",ZjcGoodsPO);
			ZjcGoods.add(getgoodsuserid.get(0));
			 //是否上架，库存是否充足
			 if(getgoodsuserid.get(0).getIs_on_sale()!=1){//商品已下架
	    		MessageVO.setMsg(getgoodsuserid.get(0).getGoods_name()+Apiconstant.Commodities_have_shelves.getName());
	    		MessageVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
	    		return AOSJson.toJson(MessageVO);
	    	 }
			 if(getgoodsuserid.get(0).getStore_count()<Integer.parseInt(goods2.getGoods_num())){//商品库存不足
	    		MessageVO.setMsg(Apiconstant.Insufficient_inventory.getName());
	    		MessageVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
	    		return AOSJson.toJson(MessageVO);
	    	 } 
			 if(getgoodsuserid.get(0).getGoods_state_1() != 3 || getgoodsuserid.get(0).getGoods_state_2() != 3){//该商品未审核或者审核不通过
	    		 MessageVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
	    		 MessageVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
	    		 return AOSJson.toJson(MessageVO);
	    	 }
			 ZjcOrderPO ZjcOrderPO=new ZjcOrderPO();
			 ZjcOrderPO.setUser_id(new BigInteger(order.getUser_id()));
		}
		
		ZjcUserAddressPO ZjcUserAddressPO= new ZjcUserAddressPO();
		ZjcUserAddressPO.setAddress_id(Integer.parseInt(order.getAddress_id()));;
		//检查地址是否合法
	    List<ZjcUserAddressPO> Addre = sqlDao.list("com.zjc.users.dao.ZjcUserAddressDao.Address",ZjcUserAddressPO);
	    ZjcUserAddressPO Address= Addre.get(0);
	    if(Address.getAddress_id()!=Integer.parseInt(order.getAddress_id())){
	    	MessageVO.setMsg(Apiconstant.Shipping_address_wrong.getName());
	    	MessageVO.setCode(Apiconstant.Shipping_address_wrong.getIndex());
	    	return AOSJson.toJson(MessageVO);
	    }
	    int vouchernum = 0;
	    
	    //针对于中军创的订单计算
	    OrderVO result=zjc_calculate_price(order.getUser_id(),goods,order.getPay_type(),vouchernum);
	    //if(AOSUtils.isNotEmpty(dto.getString("voucher_id"))){//代金券id不为空
    	if(result.getStatus()==-1){//代金券额度大于商品总价
	    	MessageVO.setMsg(Apiconstant.Voucher_Num_Was_Too_long.getName());
	    	MessageVO.setCode(Apiconstant.Voucher_Num_Was_Too_long.getIndex());
	    	return AOSJson.toJson(MessageVO);
	    }
	    //}
	    if(result.getStatus()==-10){
	    	MessageVO.setMsg(Apiconstant.Insufficient_inventory.getName());
	    	MessageVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
	    	return AOSJson.toJson(MessageVO);
	    }
	    MessageVO = apiPublicService.isShopOnLineAccount(new BigInteger(dto.getString("user_id")),result.getBarter_coupons().intValue());
		if(MessageVO.getCode() != 1){//判断当日转出额度是否超出限制
			return AOSJson.toJson(MessageVO);
		}
	    result.setOrder_prom_id(0);
	    result.setOrder_prom_amount(0);
	    result.setPostFee(0);// 物流费
	    result.setCouponFee(0); // 优惠券
	    result.setBalance(0);// 使用用户余额
	    result.setPointsFee(0);// 积分支付
	    result.setPayables(result.getOrder_amount());// 应付金额
	    result.setGoodsFee(result.getGoods_price());// 商品价格
	    result.setOrder_prom_id(0);// 订单优惠活动id
	    result.setOrder_prom_amount(0);// 订单优惠活动优惠了多少钱
	   //根据支付方式判断账户余额是否足够本次支付  支付类型(rate || equal || wxpay || alipay)

	   /* if(result.getTotal_amount().compareTo(new BigDecimal(uai.get(0).getPay_points()))==1){
	    	MessageVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
	    	return AOSJson.toJson(MessageVO);
	    }*/
	    OrderVO vo = null;
	    try {
	    	 vo=addOrder(order,result,ZjcGoods,Address);
	    	//生成订单错误事务回滚 请从新提交订单
	    	if(vo.getStatus()==-1){
	    		MessageVO.setMsg(Apiconstant.Order_generate_failure.getName());
	    	}
	    	if(vo.getStatus() == -2){//小平台商品不能购买
	    		MessageVO.setCode(Apiconstant.Goods_Not_Buy.getIndex());
	    		MessageVO.setMsg(Apiconstant.Goods_Not_Buy.getName());
	    		return AOSJson.toJson(MessageVO);
	    	}
		} catch (SQLException e) {
			 e.printStackTrace();
			 MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			 MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 return AOSJson.toJson(MessageVO);
		}
	    ZjcOrderPO ZjcOrderPO=new ZjcOrderPO();
	    ZjcOrderPO.setOrder_id(vo.getOrder_id());
	    List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrderPO);
	    if(AOSUtils.isNotEmpty(list)){
	    	if(AOSUtils.isNotEmpty(dto.getBigInteger("user_id"))){
	    		ZjcUsersInfoPO usersInfo = ZjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
		    	if(AOSUtils.isNotEmpty(usersInfo)){
		    		Dto upDto = Dtos.newDto();
		    		upDto.put("share_open_id", usersInfo.getOpenid());
		    		upDto.put("goods_id", dto.getString("goods_id"));
		    		int row = sqlDao.update("com.wxactivity.share.dao.WxShareActivityDao.updateDatasByDto", upDto);
		    		if(row > 0 ){
		    			MessageVO.setMsg(Apiconstant.Do_Success.getName());
		    		    MessageVO.setCode(Apiconstant.Do_Success.getIndex());
		    		}else{
		    			MessageVO.setMsg(Apiconstant.Do_Fails.getName());
		    		    MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
		    		    return AOSJson.toJson(MessageVO);
		    		}
		    	}else{
		    		MessageVO.setMsg(Apiconstant.Do_Fails.getName());
	    		    MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
	    		    return AOSJson.toJson(MessageVO);
		    	}
	    	}else{
		    	MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			    MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			    return AOSJson.toJson(MessageVO);
		    }
	    }else{
	    	MessageVO.setMsg(Apiconstant.Do_Fails.getName());
		    MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
		    return AOSJson.toJson(MessageVO);
	    }
	    Map<String,String> params=new HashMap<String,String>();
	    params.put("order_id", list.get(0).getOrder_id().toString());
	    params.put("order_sn", list.get(0).getOrder_sn().toString());
	    
	    MessageVO.setData(params);
	    MessageVO.setMsg(vo.getMsg());
	    MessageVO.setCode(Apiconstant.Do_Success.getIndex());
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 针对于中军创的订单计算
	 * @param user_id
	 * @param ordergoods
	 * @param pay_type
	 * @param vouchernum 代金券额度
	 * @return
	 */
	public OrderVO zjc_calculate_price(String user_id,List<Goods> ordergoods,String pay_type,int vouchernum){
		OrderVO OrderVO=new OrderVO();
		BigDecimal cash = new BigDecimal(0);//现金
		BigDecimal barter_coupons = new BigDecimal(0);//易物券
		BigDecimal total_goods_price = new BigDecimal(0);
		for (Goods zjcCartPO : ordergoods) {
			
			ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
			ZjcGoodsPO.setGoods_id(Integer.parseInt(zjcCartPO.getGoods_id()));
			List<ZjcGoodsPO> Goodss = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getgoodsuserid",ZjcGoodsPO);
			ZjcGoodsPO Goods=Goodss.get(0);
			if(Goods.getStore_count()<Integer.parseInt(zjcCartPO.getGoods_num())){
				OrderVO.setStatus(-10);
				return OrderVO;
			}
			BigDecimal Market_price = Goods.getMarket_price();   
			BigDecimal Cost_price = Goods.getCost_price();
			BigDecimal Shop_price = Goods.getShop_price(); 
			BigDecimal Goods_num = new BigDecimal(zjcCartPO.getGoods_num());   
			 switch (pay_type) {
	            case "rate" : 
	            	total_goods_price = total_goods_price.add(Market_price.multiply(Goods_num));
	            	barter_coupons = barter_coupons.add(Market_price.multiply(Goods_num));
	                break;
	            case "equal" : 
	            	total_goods_price =total_goods_price.add(Cost_price.multiply(Goods_num));
	            	barter_coupons = barter_coupons.add(Cost_price.multiply(Goods_num));
	                break;
	            case "wxpay" :
	            case "unionpay":
	            	total_goods_price =total_goods_price.add(Shop_price.multiply(Goods_num));
	            	cash = cash.add(Shop_price.multiply(Goods_num));
	                break;
	            case "alipay":
	            	total_goods_price =total_goods_price.add(Shop_price.multiply(Goods_num));
	            	cash = cash.add(Shop_price.multiply(Goods_num));
	                break;
	            case "mixed_payment":
	            	total_goods_price =total_goods_price.add(Shop_price.multiply(Goods_num).add(Market_price.multiply(Goods_num)));
	            	cash = cash.add(Shop_price.multiply(Goods_num));
	            	barter_coupons = barter_coupons.add(Market_price.multiply(Goods_num));
	                break;
	        }
			 //list.add(total_goods_price);
			 
		}
		/*  double count = 0;
		for (int j = 0; j < ordergoods.size(); j++) {
			 count += list.get(j).doubleValue();  
		}*/
		
		OrderVO.setGoods_price(total_goods_price);
		OrderVO.setTotal_amount(total_goods_price);
		OrderVO.setOrder_amount(total_goods_price);
		OrderVO.setTotal_goods_num(total_goods_price);
		int compCash = (cash.subtract(new BigDecimal(vouchernum))).compareTo(BigDecimal.ZERO);
		OrderVO.setCash(cash.subtract(new BigDecimal(vouchernum)));//支付现金
		if(compCash < 1 && vouchernum != 0){//如果支付现金小于代金券额度，则不能使用该代金券
			//OrderVO.setCash(BigDecimal.ZERO);//支付现金
			OrderVO.setStatus(-1);
			return OrderVO;
		}
		OrderVO.setBarter_coupons(barter_coupons);
		OrderVO.setStatus(1);
		return OrderVO;
	}
	
	/**
	 * 添加商品订单
	 * 
	 * @param order
	 * @param result
	 * @param ZjcGoods
	 * @param Address
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor=Exception.class)
	public OrderVO addOrder(Json order, OrderVO result,List<ZjcGoodsPO> ZjcGoods,ZjcUserAddressPO Address) throws SQLException{
			ZjcOrderPO ZjcOrderPO=new ZjcOrderPO();
			ZjcOrderPO.setUser_id(new BigInteger(order.getUser_id()));
			ZjcOrderPO.setAdd_time(new Date());
			ZjcOrderPO.setStore_id(Integer.parseInt(order.getStore_id()));
			List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.count_order",ZjcOrderPO);
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			ZjcUsersInfoPO.setUser_id(new BigInteger(order.getUser_id()));
			List<ZjcUsersInfoPO> ZjcUsersInfos=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",ZjcUsersInfoPO);
			ZjcUsersInfoPO ZjcUsersInfo=ZjcUsersInfos.get(0);
			OrderVO vo=new OrderVO();
			// 仿制灌水 1天只能下 50 单  
			if(list.size()>=50){
				vo.setStatus(-9);
				vo.setMsg(Apiconstant.fifty.getName());
			}
			String new_pay_type="cash";//默认为现金支付
			if("rate".equals(order.getPay_type()) || "equal".equals(order.getPay_type())){
	            new_pay_type = "points"; //积分消费
	        }
			 //按照店铺生成订单
			ZjcOrderPO.setUser_name(ZjcUsersInfo.getNickname());
			ZjcOrderPO.setConsignee(Address.getConsignee());
			ZjcOrderPO.setProvince(Address.getProvince());
			ZjcOrderPO.setCity(Address.getCity());
			ZjcOrderPO.setDistrict(Address.getDistrict());
			ZjcOrderPO.setTwon(Address.getTwon());
			ZjcOrderPO.setAddress(Address.getAddress_info());
			ZjcOrderPO.setMobile(Address.getMobile());
			ZjcOrderPO.setZipcode(Address.getZipcode());
			ZjcOrderPO.setEmail(Address.getEmail());
			ZjcOrderPO.setUser_note(ParameterUtil.filterEmoji(order.getUser_note()));
			ZjcOrderPO.setPay_code(order.getPay_type());
	   	    ZjcOrderPO.setPay_name(order.getPay_name());
	   	    ZjcOrderPO.setPay_status(1);
	        java.util.Random random=new java.util.Random();// 定义随机类
	        int results=random.nextInt(9000)+1000;// 返回[1000,9999)集合中的整数，注意不包括10
	        //设置支付编号
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
	        String a=sdf.format(new Date());
	        StringBuffer sb = new StringBuffer();
	        sb.append(a);
	        sb.append(results);
	        ZjcOrderPO.setPay_sn(sb.toString());
	       // BigDecimal total_goods_price = null;
	       // BigDecimal actual_price = null;
	        ZjcStorePO ZjcStorePO =new ZjcStorePO();
	 	    ZjcStorePO.setStore_id(Integer.parseInt(order.getStore_id()));
	 	    List<ZjcStorePO> Stores=sqlDao.list("com.zjc.store.dao.ZjcStoreDao.queryStore",ZjcStorePO);
		    ZjcStorePO Store=Stores.get(0);
		    ZjcOrderPO.setStore_name(Store.getStore_name());
		    ZjcOrderPO.setGoods_price(result.getGoods_price());
	 	    ZjcOrderPO.setTotal_amount(result.getTotal_amount());
	 	    ZjcOrderPO.setOrder_amount(result.getOrder_amount());
	 	    ZjcOrderPO.setBarter_coupons(result.getBarter_coupons());//易物券
	 	    ZjcOrderPO.setCash(result.getCash());//现金
	        ZjcOrderPO.setOrder_sn(ParameterUtil.getOrderSn());
	        ZjcOrderPO.setOrder_type(1);
	        ZjcOrderPO.setOrder_status(1);//生成订单直接为已支付状态
	        ZjcOrderPO.setOrder_id(idService.nextValue(SystemCons.SEQ.SEQ_ORDER).intValue());
	        //查询是否是小平台商品
	        XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", ZjcGoods.get(0).getGoods_id()));
	        if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//购买小平台商品，判断是够可以购买该商品
	        	ZjcUsersInfoPO userinfo = ZjcUsersInfoDao.selectOne(Dtos.newDto("user_id", order.getUser_id()));
	        	Map<String,String> map = new HashMap<String,String>();
	        	map.put("goods_sn", ZjcGoods.get(0).getGoods_sn());
	        	map.put("mobile", userinfo.getMobile());
	        	map.put("money", result.getCash().toString());
	        	map.put("real_name", userinfo.getReal_name());
	        	map.put("user_id", order.getUser_id());
	        	map.put("ywq", result.getBarter_coupons().toString());
	        	map.put("goods_num", order.getGoods().get(0).getGoods_num());
	    	    String charset = "GBK";  
	    	    //测试
	    	    String httpOrgCreateTest = "http://cs.daxiaoyunyi.com/interface/zjcOrderCheck.jhtml"; 
	    	    //正式
	    	    //String httpOrgCreateTest = "http://www.daxiaoyunyi.com/interface/zjcOrderCheck.jhtml"; 
	    	    HttpClientUtil httpClientUtil = new HttpClientUtil();
	    	    String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,map,charset);
	    	    JSONObject j = JSONObject.parseObject(httpOrgCreateTestRtn);
	        	//去小平台判断是否可以生成订单
	    	    if(!"1".equals(j.get("code").toString())){
	    	    	 vo.setStatus(-2);
	    		     return vo;
	    	    }
	        }
	       
	       //=====================================================  
	        
	        zjcOrderDao.insert(ZjcOrderPO);
	      //订单拆单
	       for (ZjcGoodsPO goods : ZjcGoods) {
	    	   //商品id以及数量集合
	    	   List<Goods> Goods= order.getGoods();
	    	   BigDecimal Goods_num =null;
	    	   int Goods_nums=0;
	    	   ZjcOrderGoodsPO odergoods=new ZjcOrderGoodsPO();
	    	   odergoods.copyProperties(goods);
	    	   for (int i = 0; i < Goods.size(); i++) {
	    		   if(goods.getGoods_id()==Integer.parseInt(Goods.get(i).getGoods_id())){
	    			    odergoods.setSpec_key(Goods.get(i).getSpec_key());
	    		    	odergoods.setSpec_key_name(Goods.get(i).getSpec_key_name());
	    		    	odergoods.setGoods_num(Integer.parseInt(Goods.get(i).getGoods_num()));
	    		   }
	    	   }
	    	   odergoods.setGoods_price(goods.getCost_price());
	    	   odergoods.setCost_price(goods.getShop_price());
	    	 /*  odergoods.setMarket_price_bs(Float.valueOf(goods.getMarket_price_bs()));
	    	   odergoods.setShop_price_bs(Float.valueOf(goods.getShop_price_bs()));
	    	   odergoods.setStore_rebate_rate(Float.valueOf(goods.getStore_rebate_rate()));
	    	   odergoods.setGoods_content(goods.getGoods_content());
	    	   odergoods.setGoods_weight(goods.getGoods_weight());
	    	   odergoods.setGoods_id(goods.getGoods_id());
	    	   odergoods.setGoods_name(goods.getGoods_name());
	    	   odergoods.setGoods_sn(goods.getGoods_sn());
	    	   odergoods.setMarket_price(goods.getMarket_price());
	    	   odergoods.setGoods_price(goods.getCost_price());
	    	   odergoods.setCost_price(goods.getShop_price());*/
	    	   try {
	    	           // 2 写入订单商品表
	    	    	   odergoods.setOrder_id(ZjcOrderPO.getOrder_id());
	    	    	   ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
	    	    	   zjcGoodsPO.setGoods_id(goods.getGoods_id());
	    	    	   List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
    	    		   ZjcCartPO cart=new ZjcCartPO();
    	    		   cart.setUser_id(new BigInteger(order.getUser_id()));
    	    		   cart.setGoods_id(goodsd.get(0).getGoods_id());
    	    		   //删除购物车
    	    		   sqlDao.list("com.api.order.dao.ZjcCartDao.deleteByuserid",cart);
	    	    	   //}
			 
	    	       //添加ZjcOrderGoods表数据
	    	       odergoods.setRec_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
	    	       ZjcOrderGoodsDao.insert(odergoods);
			} catch (Exception e) {
				   e.printStackTrace();
				   vo.setStatus(-1);
				   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
	        vo.setStatus(1);
	        vo.setMsg(Apiconstant.Order_generate_success.getName());
	        vo.setOrder_id(ZjcOrderPO.getOrder_id());
			return vo;
	}
	
	/**
	 * 收货地址
	 * 
	 * @param openid
	 * @return
	 */
	public List<ZjcUserAddressPO> getUserAddressListToGobuy(String openid){
		List<ZjcUserAddressPO> address = new ArrayList<ZjcUserAddressPO>();
		if(AOSUtils.isNotEmpty(openid)){
			ZjcUsersInfoPO zjcUsersInfoPO = ZjcUsersInfoDao.selectOne(Dtos.newDto("openid", openid));
			if(zjcUsersInfoPO != null){
				Dto qDto = Dtos.newDto();
				qDto.put("user_id", zjcUsersInfoPO.getUser_id());
				qDto.put("is_default", 1);
				address = zjcUserAddressDao.list(qDto);
			}else{
				address = null;
			}
		}else{
			address = null;
		}
		return address;
	} 
	
	/**
	 * 新增地址
	 * 
	 * @param dto
	 * @return
	 */
	public String addNewAdress(Dto dto){
		MessageVO MessageVO=new MessageVO();
		ZjcUserAddressPO newAdress = new ZjcUserAddressPO();
		if(dto.getString("openid") != null && dto.getString("openid") != ""){
			ZjcUsersInfoPO zjcUsersInfoPO = ZjcUsersInfoDao.selectOne(Dtos.newDto("openid",dto.getString("openid")));
			if(AOSUtils.isEmpty(zjcUsersInfoPO)){
				MessageVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
				MessageVO.setMsg(Apiconstant.Username_Not_Exist.getName());
				return AOSJson.toJson(MessageVO);
			}
			//先将以前的默认地址设置为非默认
			Dto qAddress = Dtos.newDto();
			qAddress.put("user_id", zjcUsersInfoPO.getUser_id());
			qAddress.put("is_default", 1);
			ZjcUserAddressPO oldDefaultAdress = zjcUserAddressDao.selectOne(qAddress);
			if(oldDefaultAdress != null){
				oldDefaultAdress.setIs_default(0);
				zjcUserAddressDao.updateByKey(oldDefaultAdress);
			}
			//添加新地址
			AOSUtils.copyProperties(dto, newAdress);
			newAdress.setUser_id(zjcUsersInfoPO.getUser_id());
			newAdress.setAddress_info(dto.getString("area_info") + dto.getString("address"));
			newAdress.setIs_default(1);//新增地址设置为默认地址
			int row = zjcUserAddressDao.insert(newAdress);
			if(row == 1){
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}else{
				MessageVO.setCode(Apiconstant.Save_fails.getIndex());
				MessageVO.setMsg(Apiconstant.Save_fails.getName());
			}
		}else {
			MessageVO.setCode(Apiconstant.OPEN_ID_FAILS.getIndex());
			MessageVO.setMsg(Apiconstant.OPEN_ID_FAILS.getName());
		}
		return AOSJson.toJson(MessageVO);
	}
}
