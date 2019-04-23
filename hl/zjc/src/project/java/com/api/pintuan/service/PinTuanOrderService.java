/**
 * 
 */
package com.api.pintuan.service;

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

import redis.clients.jedis.Jedis;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.HttpClientUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.goods.dao.po.XpttsgoodsidPO;
import com.api.goods.dao.po.ZjcPromptGoodsPO;
import com.api.login.dao.ZjcVouchersDao;
import com.api.login.dao.po.ZjcVouchersPO;
import com.api.order.OrderService;
import com.api.order.dao.po.Goods;
import com.api.order.dao.po.Json;
import com.api.order.dao.po.OrderVO;
import com.api.order.dao.po.ZjcCartPO;
import com.api.pintuan.dao.SpellGroupRelationalDao;
import com.api.pintuan.dao.po.SpellGroupRelationalPO;
import com.gexin.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcAccountLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcAccountLogPO;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="PinTuanOrderService")
public class PinTuanOrderService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcGoodsDao ZjcGoodsDao;
	@Autowired
	private ZjcOrderGoodsDao ZjcOrderGoodsDao;
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	@Autowired
	private ZjcUsersInfoDao ZjcUsersInfoDao;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private ZjcVouchersDao zjcVouchersDao;
	@Autowired
	private SpellGroupRelationalDao SpellGroupRelationalDao;
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	@Autowired
	private ZjcAccountLogDao ZjcAccountLogDao;

	private static final Logger logger = LoggerFactory
			.getLogger(OrderService.class);

	@SuppressWarnings("serial")
	@Transactional(rollbackFor=Exception.class)
	public String orderSub(HttpServletRequest request, HttpServletResponse response) {
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		//添加拼团数据
		 SpellGroupRelationalPO SpellGroupRelationalPO=new SpellGroupRelationalPO();
         SpellGroupRelationalPO.setUser_id(request.getAttribute("user_id").toString());
         SpellGroupRelationalPO.setIs_head(1);
		 if(AOSUtils.isNotEmpty(dto.getString("pin_order_id"))){
			 SpellGroupRelationalPO.setPin_order_id(dto.getString("pin_order_id"));
			 SpellGroupRelationalPO.setIs_head(0);
		 }
		
		if(AOSUtils.isEmpty(dto.getString("address_id")) || AOSUtils.isEmpty(dto.getString("total_amount")) || AOSUtils.isEmpty(dto.getString("pay_type"))
				|| AOSUtils.isEmpty(dto.getString("store_id"))|| AOSUtils.isEmpty(dto.getString("Goods"))){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		logger.info("========================app下单追踪1：支付编码："+dto.getString("pay_type")+"，下单会员号："+ httpModel.getAttribute("user_id"));
		MessageVO = apiPublicService.isLockedHandle();
		if(MessageVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(MessageVO);
		}
		MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(MessageVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(MessageVO);
		}
		
		
		/*Json order=AOSJson.fromJson(httpModel.getRequest().getParameter("order").toString(), Json.class);*/
		
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
		//order.setPostFee(httpModel.getRequest().getParameter("postFee").toString());
		String goodJson=httpModel.getRequest().getParameter("Goods").toString();
		List<Goods> goodList=AOSJson.fromJson(goodJson, new TypeToken<List<Goods>>(){}.getType());
		order.setGoods(goodList);
		order.setUser_id(request.getAttribute("user_id").toString());
		ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
		ZjcUsersAccountInfoPO.setUser_id(new BigInteger(order.getUser_id()));
		ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
		List<Goods> goods= order.getGoods();
		//订单中商品只能提交一种
		if(goods.size()>1){
			MessageVO.setMsg(Apiconstant.goods_one.getName());
			MessageVO.setCode(Apiconstant.goods_one.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		 SpellGroupRelationalPO.setGoods_id(goods.get(0).getGoods_id());
		//判断是否开通结算中心
	    //List<ZjcUsersAccountInfoPO> uai = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    List<ZjcGoodsPO> ZjcGoods=new ArrayList<ZjcGoodsPO>();
	    XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", goods.get(0).getGoods_id()));
	    ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		SpellGroupRelationalPO.setNike_name(userInfo.getNickname());
		SpellGroupRelationalPO.setHead_img(userInfo.getHead_pic());
		if(AOSUtils.isNotEmpty(userInfo.getXpt())){//小平台用户部分商品不能购买
			if("普通会员".equals(userInfo.getLevel())){//未购买过特殊商品
				if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//小平台用户第一次购买特殊商品商品，改变会员状态
			    	userInfo.setLevel("合格会员");
			    	ZjcUsersInfoDao.updateByKey(userInfo);
				}
			}
		}
	    for (Goods goods2 : goods) {
	    	ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(Integer.parseInt(goods2.getGoods_id()));
	    	if(isgoodnum.getIs_mixed() == 1 && order.getPay_type().equals("rate")){//支付方式不符合商品要求
	    		 MessageVO.setMsg(Apiconstant.order_cannot_create.getName());
				 MessageVO.setCode(Apiconstant.order_cannot_create.getIndex());
		    	 return AOSJson.toJson(MessageVO);
	    	}
	    	if(Integer.parseInt(goods2.getGoods_num()) > isgoodnum.getToday_limit_nums()){//当日超出该商品每单的限购数量
	    		 MessageVO.setMsg("很抱歉！该商品每单限量" + isgoodnum.getToday_limit_nums()+"请重新下单");
				 MessageVO.setCode(Apiconstant.goods_num_enough.getIndex());
		    	 return AOSJson.toJson(MessageVO);
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
	    	//查询限购商品
			ZjcPromptGoodsPO ZjcPromptGoodsPO = (ZjcPromptGoodsPO) sqlDao.selectOne("com.api.goods.dao.ZjcPromptGoodsDao.selectOne", Dtos.newDto("goods_id", goods2.getGoods_id()));
			if(AOSUtils.isNotEmpty(ZjcPromptGoodsPO) && isgoodnum.getPrompt_goods() ==1){//限购商品不为空
			     //没有到时间不能购买
				 Date bt =ZjcPromptGoodsPO.getStart_time();
				 Date et=ZjcPromptGoodsPO.getEnd_time(); 
				 Date time=new Date(); 
				 if (time.before(bt) || time.after(et)){ 
					 MessageVO.setMsg(Apiconstant.Can_not_buy.getName());
					 MessageVO.setCode(Apiconstant.Can_not_buy.getIndex());
			    	 return AOSJson.toJson(MessageVO);
			    }
				Jedis jedis = JedisUtil.getJedisClient();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try{
					jedis.incr(goods2.getGoods_id()+":"+sdf.format(new Date()));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					jedis.close();
				}
				
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
	    ZjcUserAddressPO Address =new ZjcUserAddressPO();
	    if(Addre.size() == 0){//收货地址不存在
	    	MessageVO.setMsg(Apiconstant.Shipping_address_wrong.getName());
	    	MessageVO.setCode(Apiconstant.Shipping_address_wrong.getIndex());
	    	return AOSJson.toJson(MessageVO);
	    }
	    Address = Addre.get(0);
	    int vouchernum = 0;
	    ZjcVouchersPO voucher = new ZjcVouchersPO();
	    if(AOSUtils.isNotEmpty(dto.getString("voucher_id"))){//代金券id不为空
	    	ZjcGoodsPO goodspo = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.selectOne",Dtos.newDto("goods_id", goods.get(0).getGoods_id()));
	    	if(AOSUtils.isNotEmpty(goodspo)&&goodspo.getIs_voucher() != 0){//该商品可以使用代金券
	    		voucher = (ZjcVouchersPO) sqlDao.selectOne("com.api.login.dao.ZjcVouchersDao.selectOne", Dtos.newDto("vouchers_id", dto.getString("voucher_id")));
	    		vouchernum = Integer.parseInt(voucher.getVoucher_limit());//获取代金券额度
	    	}
	    }
	    //针对于中军创的订单计算
	    OrderVO result=zjc_calculate_price(order.getUser_id(),goods,order.getPay_type(),vouchernum);
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
	    MessageVO = apiPublicService.isShopOnLineAccount(new BigInteger(httpModel.getAttribute("user_id").toString()),result.getBarter_coupons().intValue());
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
	    	 order.setIs_shop_group(httpModel.getRequest().getParameter("is_shop_group").toString());
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
	    	SpellGroupRelationalPO.setOrder_id(vo.getOrder_id().toString());
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
	    if(vouchernum > 0){//代金券使用成功
	   		voucher.setIs_use(1);//代金券状态设置为已使用
	   		voucher.setUse_time(new Date());//使用时间
	   		voucher.setOrder_id(list.get(0).getOrder_sn().toString());//使用订单号
	   		zjcVouchersDao.updateByKey(voucher);
	   	}
	    Map<String,String> params=new HashMap<String,String>();
	    params.put("order_id", list.get(0).getOrder_id().toString());
	    params.put("order_sn", list.get(0).getOrder_sn().toString());
	    if(AOSUtils.isNotEmpty(dto.getString("pin_order_id"))){
	    	params.put("pin_order_id", dto.getString("pin_order_id"));
	    }else{
	    	params.put("pin_order_id", list.get(0).getOrder_id().toString());
	    }
	    SpellGroupRelationalPO.setId(String.valueOf(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue()));
	    if(AOSUtils.isEmpty(SpellGroupRelationalPO.getPin_order_id())){
	    	SpellGroupRelationalPO.setPin_order_id(SpellGroupRelationalPO.getOrder_id());
	    }
	    int row=SpellGroupRelationalDao.insert(SpellGroupRelationalPO);
	    if(row==1){
	    	 MessageVO.setData(params);
	 	     MessageVO.setMsg(vo.getMsg());
	 	     MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	    }else {
	    	 MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			 MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
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
			BigDecimal Market_price = Goods.getShop_group_market_price();   
			BigDecimal Cost_price = Goods.getCost_price();
			BigDecimal Shop_price = Goods.getShop_group_price(); 
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
	    	    //String httpOrgCreateTest = "http://cs.daxiaoyunyi.com/interface/zjcOrderCheck.jhtml"; 
	    	    //正式
	    	    String httpOrgCreateTest = "http://www.daxiaoyunyi.com/interface/zjcOrderCheck.jhtml"; 
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
	        ZjcOrderPO.setIs_shop_group(Integer.parseInt(order.getIs_shop_group()));
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
	    	   odergoods.setCost_price(goods.getShop_group_price());
	    	   odergoods.setMarket_price(goods.getShop_group_market_price());
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
	    	       odergoods.setShop_group_market_price(goodsd.get(0).getShop_group_market_price());
	    	       odergoods.setShop_group_price(goodsd.get(0).getShop_group_price());
	    	       odergoods.setShop_group_person(goodsd.get(0).getShop_group_person());
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
	 * 查询当前订单那些人参与了
	 * @param request
	 * @param response
	 * @return
	 */
	public String  queryOrderPersonnel(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("pin_order_id"))){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			List<SpellGroupRelationalPO> SpellGroupRelationalPO = sqlDao.list("com.api.pintuan.dao.SpellGroupRelationalDao.list", qDto);
			if(SpellGroupRelationalPO.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				MessageVO.setData(SpellGroupRelationalPO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 订单支付
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	@SuppressWarnings("unused")
	public MessageVO order_pay(String orderId,String pay_password,Integer user_id){
		    MessageVO MessageVO=new MessageVO();
		    if(AOSUtils.isEmpty(orderId) || AOSUtils.isEmpty(pay_password)){
		    	MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		    	MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				return MessageVO;
			}
		    MessageVO = apiPublicService.isLockedHandle();
			if(MessageVO.getCode() != 1){//限制所有会员操作
				return MessageVO;
			}
			MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", user_id));
			if(MessageVO.getCode() != 1){//会员账户被冻结
				return MessageVO;
			}
			String order_id=orderId;
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			ZjcOrderPO ZjcOrder=new ZjcOrderPO();
			ZjcOrder.setOrder_id(Integer.valueOf(order_id.trim()).intValue());
			List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
			ZjcUsersInfoPO.setUser_id(list.get(0).getUser_id());
			List<ZjcUsersInfoPO> ZjcUsersInfos=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",ZjcUsersInfoPO);
			ZjcUsersInfoPO ZjcUsersInfo=ZjcUsersInfos.get(0);
			String psd_type=ConstantUtil.PAY_PSD_TYPE;
			if(AOSUtils.isEmpty(ZjcUsersInfo.getPay_password())){
			  MessageVO.setMsg(Apiconstant.pay_psd_is_null.getName());
			  MessageVO.setCode(Apiconstant.pay_psd_is_null.getIndex());
			  return MessageVO;
			}
			boolean password= ZjcUsersInfo.getPay_password().equals(AOSCodec.md5("zhongjunchuangya1212" + pay_password));
			if(password==false){
				  MessageVO.setMsg(Apiconstant.Password_Wrong.getName());
				  MessageVO.setCode(Apiconstant.Password_Wrong.getIndex());
				  return MessageVO;
			}
			OrderVO vo=new OrderVO();
			String new_pay_type="cash";//默认为现金支付
			if("rate".equals(list.get(0).getPay_code()) || "equal".equals(list.get(0).getPay_code()) || "mixed_payment".equals(list.get(0).getPay_code())){
	            new_pay_type = "points"; //积分消费
	        }
	        BigDecimal total_goods_price = null;
	        BigDecimal actual_price = null;
	        ZjcStorePO ZjcStorePO =new ZjcStorePO();
	 	    ZjcStorePO.setStore_id(list.get(0).getStore_id());
	 	    List<ZjcStorePO> Stores=sqlDao.list("com.zjc.store.dao.ZjcStoreDao.queryStore",ZjcStorePO);
		    ZjcStorePO Store=Stores.get(0);
	    	try {
	           // 2 写入订单商品表
	    	   if(new_pay_type == "points"){
	    		   List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", list.get(0).getOrder_id()));
	    		   ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
    	    	   zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
    	    	   List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
    	    	   if(goodsd.get(0).getIs_on_sale() == 0){//商品已下架，不能下单
    					MessageVO.setMsg(goodsd.get(0).getGoods_name()+Apiconstant.Commodities_have_shelves.getName());
    					MessageVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
    			    	return MessageVO;
    				}
    	    	   if(goodsd.get(0).getStore_count()<orderGood.get(0).getGoods_num()){//库存不足
			    		MessageVO.setMsg(Apiconstant.Insufficient_inventory.getName());
			    		MessageVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
			    		return MessageVO;
			       }
    			   if(goodsd.get(0).getGoods_state_1() != 3 || goodsd.get(0).getGoods_state_2() != 3){//该商品未审核或者审核不通过
    		    		MessageVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
    		    		MessageVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
    		       }
	    		   
	    		   ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
	    		   ZjcUsersAccountInfoPO.setUser_id(list.get(0).getUser_id());
	    		   // 4 扣除积分
	    		   List<ZjcUsersAccountInfoPO> users=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    		   if(list.get(0).getBarter_coupons().compareTo(new BigDecimal(users.get(0).getPay_points()))==1){//判断余额是否充足
	    		    	MessageVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
	    		    	MessageVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
	    		    	return MessageVO;
	    		   }
	    		   //更改商品库存销量信息
		    	   int Goods_nums = orderGood.get(0).getGoods_num();
		    	   Dto countdto = Dtos.newDto();
		    	   countdto.put("Goods_nums", Goods_nums);
		    	   countdto.put("goods_id", goodsd.get(0).getGoods_id());
				   int k=sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateByStoreCount", countdto);
				   if(k == 0){//库存不足
					   MessageVO.setMsg(Apiconstant.Insufficient_inventory.getName());
					   MessageVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
			    	   return MessageVO;
				   }
	    		   double pay_points=new BigDecimal(users.get(0).getPay_points()).subtract(list.get(0).getGoods_price()).doubleValue(); 
	    		   users.get(0).setPay_points((int)pay_points);
	    		   int nowTotalAmount = Integer.parseInt(users.get(0).getTotal_amount()) + list.get(0).getGoods_price().intValue();
	    		   users.get(0).setTotal_amount(nowTotalAmount+"");//增加累计消费
	    		   ZjcUsersAccountInfoDao.updateByKey(users.get(0));
	    		   zjcTurnIntoUtil.shop_order(list.get(0).getOrder_id()); //商城购物记录发送
	    		   list.get(0).setPay_status(1);
	    		   list.get(0).setPay_time(new Date());
	    		   zjcOrderDao.updateByKey(list.get(0));
	    		   
	    		   //查询是否是小平台商品
			       XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", orderGood.get(0).getGoods_id()));
			       if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//如果是小平台商品，支付成功后，调用小平台系统接口
			          zjcTurnIntoUtil.xptCreateOrder(Integer.valueOf(order_id.trim()).intValue());
			       }
	    		   
	    		   //调用存储过程,实时改变会员等级
	    		   //sqlDao.call("level",Dtos.newDto("user_id", list.get(0).getUser_id()));
    	    		//写入订单状态表
   	    	        ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
   					zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
   					zjcOrderActionPONew.setAction_user(ZjcUsersInfo.getUser_id());
   					zjcOrderActionPONew.setAction_user_type(1);//用户
   					zjcOrderActionPONew.setLog_time(new Date());
   					zjcOrderActionPONew.setOrder_id(list.get(0).getOrder_id());
   					zjcOrderActionPONew.setOrder_status(0);//待确认
   					zjcOrderActionPONew.setPay_status(1);//已支付
   					zjcOrderActionPONew.setShipping_status(0);//未发货
   					zjcOrderActionPONew.setStatus_desc("提交订单");
   					zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
   					zjcOrderActionPONew.setAction_user_name(ZjcUsersInfo.getNickname());
   					zjcOrderActionDao.insert(zjcOrderActionPONew);
	    	   }
			
		    	 //商品反分处理
		     /*  if(list.get(0).getPay_status()==1){
	    		    ZjcGoodsPO goods=new ZjcGoodsPO();
	    		   goods.setGoods_id(ordergoods.get(i-1).getGoods_id());
	    		   List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getgoodsuserid",goods);
	               //下单时商家会员返利 对于特殊商品 * 
				   //zjcTurnIntoUtil.deal_goods_ends(list.get(0).getOrder_id());
				   //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
				   //Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission(list.get(0).getOrder_id(),0);
				   Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(list.get(0).getOrder_id());
				   if(Rebate.getSjfl() > 0 ){//商家返利
    				   zjcTurnIntoUtil.sj_expire(Rebate.getSjfl(),Integer.parseInt(order_id),Rebate.getSale_user_id());
    			   }
				   if(Rebate.getPoints() > 0){
					   zjcTurnIntoUtil.xf_expire(Rebate.getPoints(), list.get(0).getOrder_id(),0);
					   ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
						user.setUser_id(Rebate.getBuy_user_id());
						List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
						int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(list1.get(0).getWallet_quota());
						int Count_wallet_quota=Rebate.getQbbig()+list1.get(0).getCount_wallet_quota();
						int Total_amount=list.get(0).getOrder_amount().intValue() + Integer.parseInt(list1.get(0).getTotal_amount());
						if(Rebate.getQbbig()>0){
							ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
							useracc.setWallet_quota(String.valueOf(Wallet_quota));
							useracc.setCount_wallet_quota(Count_wallet_quota);
							useracc.setUser_id(Rebate.getBuy_user_id());
							ZjcUsersAccountInfoDao.updateByKey(useracc);
						}
				   }
		       }*/
		        ZjcAccountLogPO log=new ZjcAccountLogPO();
		        log.setUser_id(list.get(0).getUser_id());
		        log.setUser_money(new BigDecimal("0"));
		        log.setPay_points(list.get(0).getOrder_amount().intValue());
		        log.setChange_time(new Date());
		        log.setDescd("下单消费");
		        log.setOrder_sn(list.get(0).getOrder_sn());
		        log.setOrder_id(list.get(0).getOrder_id());
		        log.setOrder_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		        ZjcAccountLogDao.insert(log);
		        ZjcOrderPO ZjcOrderPO=new ZjcOrderPO();
			    ZjcOrderPO.setOrder_id(list.get(0).getOrder_id());
			    List<ZjcOrderPO> list1=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrderPO);
			    MessageVO.setData("");
			    MessageVO.setMsg(Apiconstant.Do_Success.getName());
			    MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	    	} catch (Exception e) {
				   e.printStackTrace();
				   vo.setStatus(-1);
				   MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				   MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			}
			return MessageVO;
	}
	
	@SuppressWarnings("serial")
	@Transactional(rollbackFor=Exception.class)
	public String wxOrderSub(HttpServletRequest request, HttpServletResponse response) {
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		//添加拼团数据
		 SpellGroupRelationalPO SpellGroupRelationalPO=new SpellGroupRelationalPO();
         SpellGroupRelationalPO.setUser_id(request.getAttribute("user_id").toString());
         SpellGroupRelationalPO.setIs_head(1);
		 if(AOSUtils.isNotEmpty(dto.getString("pin_order_id"))){
			 SpellGroupRelationalPO.setPin_order_id(dto.getString("pin_order_id"));
			 SpellGroupRelationalPO.setIs_head(0);
		 }
		
		if(AOSUtils.isEmpty(dto.getString("address_id")) || AOSUtils.isEmpty(dto.getString("total_amount")) || AOSUtils.isEmpty(dto.getString("pay_type"))
				|| AOSUtils.isEmpty(dto.getString("store_id"))|| AOSUtils.isEmpty(dto.getString("Goods"))){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		logger.info("========================app下单追踪1：支付编码："+dto.getString("pay_type")+"，下单会员号："+ httpModel.getAttribute("user_id"));
		MessageVO = apiPublicService.isLockedHandle();
		if(MessageVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(MessageVO);
		}
		MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(MessageVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(MessageVO);
		}
		
		
		/*Json order=AOSJson.fromJson(httpModel.getRequest().getParameter("order").toString(), Json.class);*/
		
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
		//order.setPostFee(httpModel.getRequest().getParameter("postFee").toString());
		String goodJson=httpModel.getRequest().getParameter("Goods").toString();
		List<Goods> goodList=AOSJson.fromJson(goodJson, new TypeToken<List<Goods>>(){}.getType());
		order.setGoods(goodList);
		order.setUser_id(request.getAttribute("user_id").toString());
		ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
		ZjcUsersAccountInfoPO.setUser_id(new BigInteger(order.getUser_id()));
		ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
		List<Goods> goods= order.getGoods();
		//订单中商品只能提交一种
		if(goods.size()>1){
			MessageVO.setMsg(Apiconstant.goods_one.getName());
			MessageVO.setCode(Apiconstant.goods_one.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		 SpellGroupRelationalPO.setGoods_id(goods.get(0).getGoods_id());
		//判断是否开通结算中心
	    //List<ZjcUsersAccountInfoPO> uai = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    List<ZjcGoodsPO> ZjcGoods=new ArrayList<ZjcGoodsPO>();
	    XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", goods.get(0).getGoods_id()));
	    ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		SpellGroupRelationalPO.setNike_name(userInfo.getNickname());
		SpellGroupRelationalPO.setHead_img(userInfo.getHead_pic());
		if(AOSUtils.isNotEmpty(userInfo.getXpt())){//小平台用户部分商品不能购买
			if("普通会员".equals(userInfo.getLevel())){//未购买过特殊商品
				if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//小平台用户第一次购买特殊商品商品，改变会员状态
			    	userInfo.setLevel("合格会员");
			    	ZjcUsersInfoDao.updateByKey(userInfo);
				}
			}
		}
	    for (Goods goods2 : goods) {
	    	ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(Integer.parseInt(goods2.getGoods_id()));
	    	if(isgoodnum.getIs_mixed() == 1 && order.getPay_type().equals("rate")){//支付方式不符合商品要求
	    		 MessageVO.setMsg(Apiconstant.order_cannot_create.getName());
				 MessageVO.setCode(Apiconstant.order_cannot_create.getIndex());
		    	 return AOSJson.toJson(MessageVO);
	    	}
	    	if(Integer.parseInt(goods2.getGoods_num()) > isgoodnum.getToday_limit_nums()){//当日超出该商品每单的限购数量
	    		 MessageVO.setMsg("很抱歉！该商品每单限量" + isgoodnum.getToday_limit_nums()+"请重新下单");
				 MessageVO.setCode(Apiconstant.goods_num_enough.getIndex());
		    	 return AOSJson.toJson(MessageVO);
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
	    	//查询限购商品
			ZjcPromptGoodsPO ZjcPromptGoodsPO = (ZjcPromptGoodsPO) sqlDao.selectOne("com.api.goods.dao.ZjcPromptGoodsDao.selectOne", Dtos.newDto("goods_id", goods2.getGoods_id()));
			if(AOSUtils.isNotEmpty(ZjcPromptGoodsPO) && isgoodnum.getPrompt_goods() ==1){//限购商品不为空
			     //没有到时间不能购买
				 Date bt =ZjcPromptGoodsPO.getStart_time();
				 Date et=ZjcPromptGoodsPO.getEnd_time(); 
				 Date time=new Date(); 
				 if (time.before(bt) || time.after(et)){ 
					 MessageVO.setMsg(Apiconstant.Can_not_buy.getName());
					 MessageVO.setCode(Apiconstant.Can_not_buy.getIndex());
			    	 return AOSJson.toJson(MessageVO);
			    }
				Jedis jedis = JedisUtil.getJedisClient();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try{
					jedis.incr(goods2.getGoods_id()+":"+sdf.format(new Date()));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					jedis.close();
				}
				
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
	    ZjcUserAddressPO Address =new ZjcUserAddressPO();
	    if(Addre.size() == 0){//收货地址不存在
	    	MessageVO.setMsg(Apiconstant.Shipping_address_wrong.getName());
	    	MessageVO.setCode(Apiconstant.Shipping_address_wrong.getIndex());
	    	return AOSJson.toJson(MessageVO);
	    }
	    Address = Addre.get(0);
	    int vouchernum = 0;
	    ZjcVouchersPO voucher = new ZjcVouchersPO();
	    if(AOSUtils.isNotEmpty(dto.getString("voucher_id"))){//代金券id不为空
	    	ZjcGoodsPO goodspo = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.selectOne",Dtos.newDto("goods_id", goods.get(0).getGoods_id()));
	    	if(AOSUtils.isNotEmpty(goodspo)&&goodspo.getIs_voucher() != 0){//该商品可以使用代金券
	    		voucher = (ZjcVouchersPO) sqlDao.selectOne("com.api.login.dao.ZjcVouchersDao.selectOne", Dtos.newDto("vouchers_id", dto.getString("voucher_id")));
	    		vouchernum = Integer.parseInt(voucher.getVoucher_limit());//获取代金券额度
	    	}
	    }
	    //针对于中军创的订单计算
	    OrderVO result=zjc_calculate_price(order.getUser_id(),goods,order.getPay_type(),vouchernum);
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
	    MessageVO = apiPublicService.isShopOnLineAccount(new BigInteger(httpModel.getAttribute("user_id").toString()),result.getBarter_coupons().intValue());
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
	    	 order.setIs_shop_group(httpModel.getRequest().getParameter("is_shop_group").toString());
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
	    	SpellGroupRelationalPO.setOrder_id(vo.getOrder_id().toString());
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
	    if(vouchernum > 0){//代金券使用成功
	   		voucher.setIs_use(1);//代金券状态设置为已使用
	   		voucher.setUse_time(new Date());//使用时间
	   		voucher.setOrder_id(list.get(0).getOrder_sn().toString());//使用订单号
	   		zjcVouchersDao.updateByKey(voucher);
	   	}
	    Map<String,String> params=new HashMap<String,String>();
	    params.put("order_id", list.get(0).getOrder_id().toString());
	    params.put("order_sn", list.get(0).getOrder_sn().toString());
	    if(AOSUtils.isNotEmpty(dto.getString("pin_order_id"))){
	    	params.put("pin_order_id", dto.getString("pin_order_id"));
	    }else{
	    	params.put("pin_order_id", list.get(0).getOrder_id().toString());
	    }
	    SpellGroupRelationalPO.setId(String.valueOf(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue()));
	    if(AOSUtils.isEmpty(SpellGroupRelationalPO.getPin_order_id())){
	    	SpellGroupRelationalPO.setPin_order_id(SpellGroupRelationalPO.getOrder_id());
	    }
	    int row=SpellGroupRelationalDao.insert(SpellGroupRelationalPO);
	    if(row==1){
	    	 MessageVO.setData(params);
	 	     MessageVO.setMsg(vo.getMsg());
	 	     MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	    }else {
	    	 MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			 MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	    MessageVO payVo= this.order_pay(list.get(0).getOrder_id().toString(), dto.getString("pay_password"), Integer.parseInt(httpModel.getAttribute("user_id").toString()));
	    if(payVo.getCode() != 1){
	    	MessageVO.setCode(payVo.getCode());
	    	MessageVO.setMsg(payVo.getMsg());
	    }else{
	    	MessageVO.setData(params);
	 	    MessageVO.setMsg(vo.getMsg());
	 	    MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	    }
		return AOSJson.toJson(MessageVO);
	}
	
}
