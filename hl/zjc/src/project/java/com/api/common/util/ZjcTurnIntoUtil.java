/**
 * 
 */
package com.api.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.order.OrderService;
import com.api.order.dao.po.Rebate;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcGoodsVO;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.ZjcMemberParameterDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcAccountLogDao;
import com.zjc.users.dao.ZjcQueueDao;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcAccountLogPO;
import com.zjc.users.dao.po.ZjcQueuePO;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 商品购买之后反分处理
 * @author Administrator
 *
 */
@Service(value="zjcTurnIntoUtil")
public class ZjcTurnIntoUtil {
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	
	@Autowired
	private ZjcOrderGoodsDao zjcOrderGoodsDao;
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcAccountLogDao zjcAccountLogDao;
	
	@Autowired
	private ZjcMemberParameterDao zjcMemberParameterDao;
	
	@Autowired
	private ZjcUserLogDao zjcUserLogDao;
	
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	@Autowired
	private ZjcQueueDao zjcQueueDao;
	
	@Autowired
	private ZjcUsersDao zjcUsersDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private OrderService orderService;
	/**
	 * 商品订单银联、支付宝在线支付返分处理
	 * 
	 * @param order_id
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unused")
	public String order_online_pay(Integer order_id) throws ParseException{
	    MessageVO MessageVO=new MessageVO();
		ZjcOrderPO zjcOrder= zjcOrderDao.selectOne(Dtos.newDto("order_id", order_id));
		/*ZjcUsersInfoPO ZjcUsersInfo= zjcUsersInfoDao.selectOne(Dtos.newDto("user_id", zjcOrder.getUser_id()));
		OrderVO vo=new OrderVO();
		String new_pay_type="cash";//默认为现金支付
		if("rate".equals(zjcOrder.getPay_code()) || "equal".equals(zjcOrder.getPay_code())){
            new_pay_type = "points"; //积分消费
        }
        BigDecimal total_goods_price = null;
        BigDecimal actual_price = null;*/
        
	    ZjcStorePO Store= zjcStoreDao.selectOne(Dtos.newDto("store_id", zjcOrder.getStore_id()));
	    shop_order(zjcOrder.getOrder_id()); //商城购物记录发送
    	 //商品反分处理
       if(zjcOrder.getPay_status()==1){
    	   ZjcUsersAccountInfoPO user = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", zjcOrder.getUser_id()));
    	   ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
    	   int Total_amount=zjcOrder.getCash().intValue()+Integer.parseInt(user.getTotal_amount());
		   useracc.setTotal_amount(String.valueOf(Total_amount));
		   useracc.setUser_id(zjcOrder.getUser_id());
		   zjcUsersAccountInfoDao.updateByKey(useracc);
    	   //调用存储过程,实时改变会员等级
		   //sqlDao.call("level",Dtos.newDto("user_id", zjcOrder.getUser_id()));
           //下单时商家会员返利 对于特殊商品 * 
		   //deal_goods_ends(zjcOrder.getOrder_id());
		   //订单商品计算提成的易物劵 商品类会员提成按比例处理
		   //Rebate Rebate=plain_Order_Commission(zjcOrder.getOrder_id(),0);
		   Rebate Rebate=plain_Order_Commission_New(zjcOrder.getOrder_id());
		   if(Rebate.getPoints() > 0){//会员返分
			    xf_expire_new(Rebate.getPoints(), zjcOrder.getOrder_id());
				int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(user.getWallet_quota());
				int Count_wallet_quota=Rebate.getQbbig()+user.getCount_wallet_quota();
				if(Rebate.getQbbig()>0){
					useracc.setWallet_quota(String.valueOf(Wallet_quota));
					useracc.setCount_wallet_quota(Count_wallet_quota);
					useracc.setUser_id(Rebate.getBuy_user_id());
					zjcUsersAccountInfoDao.updateByKey(useracc);
				}
		   }
       }
	    MessageVO.setData("");
	    MessageVO.setMsg(Apiconstant.Do_Success.getName());
	    MessageVO.setCode(Apiconstant.Do_Success.getIndex());
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 商城购物易物券返还语句
	 * @param int $order_id
	 */
	@SuppressWarnings("unused")
	public void shop_order(int order_id){
		String str_msg="";
    	//ZjcMemberParameterPO zjcMemberParameterPO = zjcMemberParameterDao.selectByMaxKey();
		//int recharge_member= Integer.parseInt(zjcMemberParameterPO.getBarter_coupo_rate());
		ZjcOrderPO zjcOrder= zjcOrderDao.selectOne(Dtos.newDto("order_id", order_id));
		
	   //获取订单下商品信息
	   int a=0;
	   int points=0;
	   //获取当前订单的商品ID
	   //List<ZjcOrderGoodsPO> ordergoods= zjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrder.getOrder_id()));
	   List<ZjcOrderGoodsPO> ordergoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", zjcOrder.getOrder_id()));
	   //boolean is_car = false;
	   for (ZjcOrderGoodsPO zjcOrderGoodsd : ordergoods) {
		    ZjcGoodsPO goodspo = zjcGoodsDao.selectOne(Dtos.newDto("goods_id", zjcOrderGoodsd.getGoods_id()));
			/*if(goodspo.getIs_car() == 1){
				is_car = true;
			}*/
		    BigDecimal barter_coupons_rate = new BigDecimal(0);
		    //int market_price_bs=0;
			//int shop_price_bs=0;
			if(!AOSUtils.isEmpty(zjcOrderGoodsd.getGoods_weight())){
				BigDecimal weight = new BigDecimal(0);
				String[] weightArr = zjcOrderGoodsd.getGoods_weight().split(",");
				for(int i=0;i<weightArr.length;i++){
					weight = weight.add(new BigDecimal(weightArr[i]));
				}
				
				barter_coupons_rate = weight.divide(new BigDecimal(100));
			} else {
				barter_coupons_rate = new BigDecimal(1);
			}
			/*if(!goodspo.getMarket_price_bs().equals("")){
				market_price_bs=Integer.parseInt(goodspo.getMarket_price_bs());
			}else {
				market_price_bs=1;
			}
			if(!goodspo.getShop_price_bs().equals("")){
				shop_price_bs=Integer.parseInt(goodspo.getShop_price_bs());
			}else {
				shop_price_bs=1;
			}*/
			 //计算商品易物券
			 switch (zjcOrder.getPay_code()) {
	            case "rate":
	            	points += zjcOrderGoodsd.getMarket_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "alipay":
	            	points += zjcOrderGoodsd.getCost_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "wxpay":
	            	points += zjcOrderGoodsd.getCost_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "unionpay":
	            	points += zjcOrderGoodsd.getCost_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "mixed_payment":
	            	points += zjcOrderGoodsd.getCost_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	        }
		}
		//获取用户累计消费金额
		ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", zjcOrder.getUser_id()));
		ZjcStorePO store=zjcStoreDao.selectByKey(zjcOrder.getStore_id());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("logType", "商城购物");
		map.put("user_id", zjcOrder.getUser_id());
		map.put("to_user_id", store.getUser_id());
		map.put("pay_points", zjcOrder.getBarter_coupons());
		map.put("order_sn", zjcOrder.getOrder_sn());
		if(zjcOrder.getPay_code().equals("rate")||zjcOrder.getPay_code().equals("equal")){//易支付
			map.put("pay_type", "礼品兑换");
			//map.put("due_tc_points", points);
		} else if(zjcOrder.getPay_code().equals("mixed_payment")){//混合支付
			map.put("pay_type", "混合支付");
			map.put("due_tc_points", points);
		} else {//现金支付
			map.put("pay_type", "在线支付");
			map.put("due_tc_points", points);
		}
		map.put("now_points", zjcUsersAccountInfoPO.getPay_points());
		map.put("show_type", 1);
		//会员商城购物日志
		apiLogService.saveLog(map);
		//商家实体店交易日志
		map.put("logType", "商家实体店交易");
		map.put("user_id", store.getUser_id());
		map.put("to_user_id", zjcOrder.getUser_id());
		map.put("show_type", 0);
		apiLogService.saveLog(map);
	}
	
	/**
	 * 商城购物易物券返还语句
	 * @param int $order_id
	 */
	@SuppressWarnings("unused")
	public void shop_order_cash(int order_id){
		String str_msg="";
    	//ZjcMemberParameterPO zjcMemberParameterPO = zjcMemberParameterDao.selectByMaxKey();
		//int recharge_member= Integer.parseInt(zjcMemberParameterPO.getBarter_coupo_rate());
		ZjcOrderPO zjcOrder= zjcOrderDao.selectOne(Dtos.newDto("order_id", order_id));
		
		  //获取订单下商品信息
		  int a=0;
		  int points=0;
		  //获取当前订单的商品ID
		  //List<ZjcOrderGoodsPO> ordergoods= zjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrder.getOrder_id()));
		  List<ZjcOrderGoodsPO> ordergoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", zjcOrder.getOrder_id()));
		  for (ZjcOrderGoodsPO zjcOrderGoodsd : ordergoods) {
		    ZjcGoodsPO goodspo = zjcGoodsDao.selectOne(Dtos.newDto("goods_id", zjcOrderGoodsd.getGoods_id()));
		    BigDecimal barter_coupons_rate = new BigDecimal(0);
		    int market_price_bs=0;
			int shop_price_bs=0;
			if(!AOSUtils.isEmpty(goodspo.getGoods_weight())){
				BigDecimal weight = new BigDecimal(0);
				String[] weightArr = zjcOrderGoodsd.getGoods_weight().split(",");
				for(int i=0;i<weightArr.length;i++){
					weight = weight.add(new BigDecimal(weightArr[i]));
				}
				
				barter_coupons_rate = weight.divide(new BigDecimal(100));
			} else {
				barter_coupons_rate = new BigDecimal(1);
			}
			if(!goodspo.getMarket_price_bs().equals("")){
				market_price_bs=Integer.parseInt(goodspo.getMarket_price_bs());
			}else {
				market_price_bs=1;
			}
			if(!goodspo.getShop_price_bs().equals("")){
				shop_price_bs=Integer.parseInt(goodspo.getShop_price_bs());
			}else {
				shop_price_bs=1;
			}
			 //计算商品易物券
			 switch (zjcOrder.getPay_code()) {
			 case "rate":
	            	points += goodspo.getMarket_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "alipay":
	            	points += goodspo.getShop_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "wxpay":
	            	points += goodspo.getShop_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "unionpay":
	            	points += goodspo.getShop_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	            case "mixed_payment":
	            	points += goodspo.getMarket_price().multiply(new BigDecimal(zjcOrderGoodsd.getGoods_num())).multiply(barter_coupons_rate).intValue();
	                break;
	        }
		}
		//获取用户累计消费金额
		 ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", zjcOrder.getUser_id()));
		 ZjcStorePO store=zjcStoreDao.selectByKey(zjcOrder.getStore_id());
		if(zjcOrder.getPay_code().equals("rate")){
			str_msg = zjcOrder.getUser_id()+"会员在"+store.getUser_id()+"商家实体店消费"+zjcOrder.getTotal_amount()+"可用易物券易物，订单号为"+zjcOrder.getOrder_sn()+"并获得赠送易物券"+points+"当前可用易物券:"+zjcUsersAccountInfoPO.getPay_points();
		}else if(zjcOrder.getPay_code().equals("equal")){
			str_msg = zjcOrder.getUser_id()+"会员在"+store.getUser_id()+"商家实体店消费"+zjcOrder.getTotal_amount()+"可用易物券易物，订单号为"+zjcOrder.getOrder_sn()+"并获得赠送易物券0   当前可用易物券:"+zjcUsersAccountInfoPO.getPay_points();
		}else if(zjcOrder.getPay_code().equals("cash")||zjcOrder.getPay_code().equals("wxpay")||zjcOrder.getPay_code().equals("alipay")||zjcOrder.getPay_code().equals("unionpay")){
			str_msg = zjcOrder.getUser_id()+"会员在"+store.getUser_id()+"商家实体店消费"+zjcOrder.getCash()+"现金，支付方式：在线支付。订单号为："+zjcOrder.getOrder_sn()+"并获得赠送易物券"+points+"当前可用易物券:"+zjcUsersAccountInfoPO.getPay_points();
		}else if(zjcOrder.getPay_code().equals("mixed_payment")){//混合支付会员日志
			str_msg = zjcOrder.getUser_id()+"会员在"+store.getUser_id()+"商家实体店消费的"+zjcOrder.getCash()+"现金，订单号为"+zjcOrder.getOrder_sn()+"当前可用易物券:"+zjcUsersAccountInfoPO.getPay_points();
		}
		if(!str_msg.equals("")){
			ZjcUserLogPO userlog=new ZjcUserLogPO();
			userlog.setUser_id(zjcOrder.getUser_id());
			userlog.setType("会员易物结算");
			userlog.setTime(new Date());
			userlog.setDescs(str_msg);
			zjcUserLogDao.insert(userlog);
			if(store.getUser_id()!=null){
				String store_msg =store.getUser_id()+"商家收到"+zjcOrder.getUser_id()+"会员消费的"+zjcOrder.getTotal_amount()+"可用易物券，订单号为:"+zjcOrder.getOrder_sn()+"请"+store.getUser_id()+"商家实体店到商家后台核对信息并发货";
				if(zjcOrder.getPay_code().equals("mixed_payment")){//混合支付商家日志
					store_msg = store.getUser_id()+"商家收到"+zjcOrder.getUser_id()+"会员消费的"+zjcOrder.getCash()+"现金，订单号为:"+zjcOrder.getOrder_sn()+"请"+store.getUser_id()+"商家实体店到商家后台核对信息并发货";
				}
				ZjcUserLogPO userlogs=new ZjcUserLogPO();
				userlogs.setUser_id(store.getUser_id());
				userlogs.setType("商家易物结算");
				userlogs.setTime(new Date());
				userlogs.setDescs(store_msg);
				zjcUserLogDao.insert(userlogs);
			}
			
		}
	}
	
	/**
	 * 下单时商家会员返利
	 * 对于特殊商品
	 * * 
	 * @throws ParseException */
	public void deal_goods_ends(int order_id) throws ParseException {
		Rebate Rebate=plain_Order_Commission(order_id,1);
		if(Rebate.getPoints() > 0){
			//会员返还
			xf_expire(Rebate.getPoints(),order_id,1);
			ZjcUsersAccountInfoPO user= zjcUsersAccountInfoDao.selectByKey(Rebate.getBuy_user_id());
			int Wallet_quota=Rebate.getPoints()+Integer.parseInt(user.getWallet_quota());
			int Count_wallet_quota=Rebate.getPoints()+user.getCount_wallet_quota();
			//int Total_amount=Rebate.getPoints()+Integer.parseInt(user.getTotal_amount());
			//钱包扩容
			if(Rebate.getQbbig()>0){
				ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
				useracc.setWallet_quota(String.valueOf(Wallet_quota));
				useracc.setCount_wallet_quota(Count_wallet_quota);
				useracc.setUser_id(Rebate.getBuy_user_id());
				zjcUsersAccountInfoDao.updateByKey(useracc);
			}
			if(Rebate.getPoints()>0){
				/*ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
				useracc.setTotal_amount(String.valueOf(Total_amount));
				zjcUsersAccountInfoDao.updateByKey(useracc);
				
				ZjcUsersInfoPO zjcuserinfo = zjcUsersInfoDao.selectByKey(Rebate.getBuy_user_id());
				if(zjcuserinfo.getIs_qualified_member()==0){
					become_vip_member(Integer.parseInt(Rebate.getBuy_user_id().toString()));
				}*/
				
				if(Rebate.getSjfl()>0){
					ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
					 Date d=new Date();   
					 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					 String param=zjcMemberOtherPO.getSpecial_rebate_delay_days();
					String date= df.format(new Date(d.getTime() + Integer.parseInt(param) * 24 * 60 * 60 * 1000));
					 Date dates = null;
					try {
						dates = df.parse(date);
						ZjcQueuePO queue=new ZjcQueuePO();
						 queue.setNote("会员商城购物，商家返利");
						 queue.setType(6);
						 queue.setAdd_time(new Date());
						 queue.setSend_time(dates);
						 queue.setUser_id(new BigInteger(String.valueOf(Rebate.getSale_user_id())));
						 queue.setRelation_id(order_id);
						 queue.setKz_points(Rebate.getSjfl());
						 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						 zjcQueueDao.insert(queue);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					 
				}
			}
		}
	}
	
	/**
	 * 下单时商家会员返利
	 * 对于特殊商品
	 * * 
	 * @throws ParseException */
	public void deal_goods_ends_mixed(int order_id) throws ParseException {
		Rebate Rebate=plain_Order_Commission(order_id,1);
		if(Rebate.getPoints() > 0){
			//会员返还
			xf_expire(Rebate.getPoints(),order_id,1);
			ZjcUsersAccountInfoPO user= zjcUsersAccountInfoDao.selectByKey(Rebate.getBuy_user_id());
			int Wallet_quota=Rebate.getPoints()+Integer.parseInt(user.getWallet_quota());
			int Count_wallet_quota=Rebate.getPoints()+user.getCount_wallet_quota();
			//钱包扩容
			if(Rebate.getQbbig()>0){
				ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
				useracc.setWallet_quota(String.valueOf(Wallet_quota));
				useracc.setCount_wallet_quota(Count_wallet_quota);
				useracc.setUser_id(Rebate.getBuy_user_id());
				zjcUsersAccountInfoDao.updateByKey(useracc);
			}
			if(Rebate.getPoints()>0){
				
				if(Rebate.getSjfl()>0){
					ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
					 Date d=new Date();   
					 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					 String param=zjcMemberOtherPO.getSpecial_rebate_delay_days();
					 String date= df.format(new Date(d.getTime() + Integer.parseInt(param) * 24 * 60 * 60 * 1000));
					 Date dates = null;
					//try {
						dates = df.parse(date);
						ZjcQueuePO queue=new ZjcQueuePO();
						 queue.setNote("会员商城购物，商家返利");
						 queue.setType(6);
						 queue.setAdd_time(new Date());
						 queue.setIs_cancle(1);//混合支付默认设置商家返分记录已取消
						 queue.setSend_time(dates);
						 queue.setUser_id(new BigInteger(String.valueOf(Rebate.getSale_user_id())));
						 queue.setRelation_id(order_id);
						 queue.setKz_points(Rebate.getSjfl());
						 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						 zjcQueueDao.insert(queue);
					/*} catch (ParseException e) {
						e.printStackTrace();
					}*/
					 
				}
			}
		}
	}
	
	/**
	 * 生成商家返分记录
	 * 
	 * @param point 返利数值
	 * @param order_id 订单id
	 * @param seller_id 商家id
	 * @throws ParseException
	 */
	public void sj_expire(int point,int order_id,int seller_id) throws ParseException {
		
		 //获取订单的下单时间
		 ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(order_id);
		 if("mixed_payment".equals(zjcOrderPO.getPay_code())||"rate".equals(zjcOrderPO.getPay_code())){//在线支付商家不返分
			 ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
			 Date d=new Date();   
			 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			 String param=zjcMemberOtherPO.getSpecial_rebate_delay_days();//返利延迟天数
			 String date= df.format(new Date(d.getTime() + Integer.parseInt(param) * 24 * 60 * 60 * 1000));
			 Date dates = null;
			 dates = df.parse(date);
			 ZjcQueuePO queue=new ZjcQueuePO();
			 queue.setNote("会员商城购物，商家返利");
			 queue.setType(6);
			 queue.setAdd_time(new Date());
			 if(!"rate".equals(zjcOrderPO.getPay_code())){//混合支付默认设置商家返分记录已取消
				 queue.setIs_cancle(1);
			 }
			 queue.setSend_time(dates);
			 queue.setUser_id(new BigInteger(String.valueOf(seller_id)));
			 queue.setRelation_id(order_id);
			 queue.setKz_points(point);
			 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			 zjcQueueDao.insert(queue);
		 }
	}
	
	/**
	 * 订单商品计算提成的易物劵
	 * 商品类会员提成按比例处理
	 * @param order_id
	 * @return
	 */
	public Rebate plain_Order_Commission(Integer order_id,int is_car){
		//获取当前的系统会员配置
		ZjcMemberParameterPO zjcMemberParameterPO = zjcMemberParameterDao.selectByMaxKey();
		//充值易物劵比例
		//double recharge_member=Double.valueOf(zjcMemberParameterPO.getBarter_coupo_rate());
		//根据订单ID查找店铺的店主ID
		/*ZjcStorePO store=zjcStoreDao.selectOne(Dtos.newDto("order_id", order_id));
		int sale_user_id = store.getUser_id().intValue();*/
		int sale_user_id=(int) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.getSaleUserID", order_id);
		//查找order_id对应的订单信息
		ZjcOrderPO zjcOrderPO=zjcOrderDao.selectByKey(order_id);
		//根据订单的user_id查找该订单的消费人
		ZjcUsersInfoPO zjcUsersInfoPO=zjcUsersInfoDao.selectByKey(zjcOrderPO.getUser_id());
		//该会员的一级上级和二级上级ID
		Object first_leader_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.firstLeader", zjcUsersInfoPO.getUser_id());
		int first_leader=first_leader_obj==null ? 0 :(int) first_leader_obj;
		Object second_leader_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.secondLeader", zjcUsersInfoPO.getUser_id());
		int second_leader= second_leader_obj == null ? 0 :(int)second_leader_obj;
		int first_qualified_member=0;
		int first_wallet_quota=0;
		int secend_qualified_member=0;
		int second_wallet_quota=0;
		if(first_leader!=0){
			//一级上级的合格子会员
			Object first_qualified_member_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.qualifiedMember", first_leader);
			 first_qualified_member= first_qualified_member_obj == null ? 0: (int) first_qualified_member_obj;
			//一级上级的钱包额度
			 Object first_wallet_quota_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.wallet_quota", first_leader);
			 first_wallet_quota = first_wallet_quota_obj == null ? 0 : (int) first_wallet_quota_obj;
		}
		 if(second_leader!=0){
			    //二级上级的合格子会员
			Object secend_qualified_member_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.qualifiedMember", second_leader);
			secend_qualified_member=secend_qualified_member_obj == null ? 0 :(int)secend_qualified_member_obj ;
				//二级上级的钱包额度
			Object second_wallet_quota_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.wallet_quota", second_leader);
			second_wallet_quota = second_wallet_quota_obj==null ? 0 : (int)second_wallet_quota_obj ;
		 }
		//根据订单ID查询该笔订单对于的商品数组
		List<ZjcGoodsVO> goodsList=new ArrayList<ZjcGoodsVO>();
		List<ZjcOrderGoodsPO> ordergoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", order_id));
				 //zjcOrderGoodsDao.list(Dtos.newDto("order_id", order_id));
		for (int i = 0; i < ordergoods.size(); i++) {
			ZjcGoodsVO ZjcGoodsVO=new ZjcGoodsVO();
			ZjcGoodsPO goods = zjcGoodsDao.selectByKey(ordergoods.get(i).getGoods_id());
			ZjcGoodsVO.setGoods_id(goods.getGoods_id());
			ZjcGoodsVO.setGoods_num(ordergoods.get(i).getGoods_num());
			ZjcGoodsVO.setWallet_amplification_rate(Float.parseFloat(goods.getWallet_amplification_rate()));
//			ZjcGoodsVO.setStore_rebate_rate(Float.parseFloat(goods.getStore_rebate_rate()));
//			ZjcGoodsVO.setMarket_price_bs(Float.parseFloat(goods.getMarket_price_bs()));
//			ZjcGoodsVO.setShop_price_bs(Float.parseFloat(goods.getShop_price_bs()));
			ZjcGoodsVO.setStore_rebate_rate(ordergoods.get(i).getStore_rebate_rate());
			ZjcGoodsVO.setMarket_price_bs(ordergoods.get(i).getMarket_price_bs());
			ZjcGoodsVO.setShop_price_bs(ordergoods.get(i).getShop_price_bs());
			ZjcGoodsVO.setCommission_rate_1(Float.parseFloat(goods.getCommission_rate_1()));
			ZjcGoodsVO.setCommission_rate_2(Float.parseFloat(goods.getCommission_rate_2()));
			ZjcGoodsVO.setCommission_rate_3(Float.parseFloat(goods.getCommission_rate_3()));
			ZjcGoodsVO.setMarket_price(ordergoods.get(i).getMarket_price().floatValue());
			ZjcGoodsVO.setGoods_price(ordergoods.get(i).getGoods_price().floatValue());
			ZjcGoodsVO.setCost_price(ordergoods.get(i).getCost_price().floatValue());
			ZjcGoodsVO.setIs_car(goods.getIs_car());
			goodsList.add(ZjcGoodsVO);
		}
		
		//用户返回的易物劵
		int points=0;
		//用户的钱包扩容
		int qbbig=0;
		//商家返利
		int sjfl=0;
		//会员提成
		int first=0,second=0;
		int p=0;
		
		for (ZjcGoodsVO goodsVO : goodsList) {
			//以物易物会员积分返还倍数
			float market_price_bs = (goodsVO.getMarket_price_bs() != 0) ? goodsVO.getMarket_price_bs() : 1;
			//在线支付会员积分返还倍数
			float shop_price_bs = (goodsVO.getShop_price_bs() != 0) ? goodsVO.getShop_price_bs() : 1;
			//一级上级的提成比例
			float first_rate=(first_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_three()))?
					Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_threes()): 
						(first_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_two()))?
								Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_twos()):
										(first_qualified_member >=Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_one()))?
												Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_ones()):0;	
			
			//二级上级的提成比例
			float second_rate=(secend_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_three()))?
					Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_threes()):
						(secend_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_two()))?
							Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_twos()):
								(secend_qualified_member >=Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_one()))?
										Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_ones()):0;	
			if(goodsVO.getIs_car() == is_car){//判断是特殊商品或者普通商品
				switch (zjcOrderPO.getPay_code()) {
				case "rate":
					p+=goodsVO.getMarket_price()*goodsVO.getGoods_num();
					points+=goodsVO.getMarket_price()*goodsVO.getGoods_num()*market_price_bs;
					qbbig+=(int)(goodsVO.getWallet_amplification_rate()*goodsVO.getMarket_price())*goodsVO.getGoods_num();
					first+=(int)(goodsVO.getMarket_price()*first_rate)*goodsVO.getGoods_num();
					second+=(int)(goodsVO.getMarket_price()*second_rate)*goodsVO.getGoods_num();
					sjfl+=(int)(goodsVO.getMarket_price()*goodsVO.getStore_rebate_rate())*goodsVO.getGoods_num();
					break;
				case "alipay":
					p+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num());
					points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
					qbbig+=(int)(goodsVO.getCost_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
					first+=(int)(goodsVO.getCost_price()*first_rate*goodsVO.getGoods_num());
					second+=(int)(goodsVO.getCost_price()*second_rate*goodsVO.getGoods_num());
					sjfl+=(int)(goodsVO.getCost_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
					break;
				case "wxpay":
					p+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num());
					points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
					qbbig+=(int)(goodsVO.getCost_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
					first+=(int)(goodsVO.getCost_price()*first_rate*goodsVO.getGoods_num());
					second+=(int)(goodsVO.getCost_price()*second_rate*goodsVO.getGoods_num());
					sjfl+=(int)(goodsVO.getCost_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
					break;
				case "unionpay":
					p+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num());
					points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
					qbbig+=(int)(goodsVO.getCost_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
					first+=(int)(goodsVO.getCost_price()*first_rate*goodsVO.getGoods_num());
					second+=(int)(goodsVO.getCost_price()*second_rate*goodsVO.getGoods_num());
					sjfl+=(int)(goodsVO.getCost_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
					break;
				case "mixed_payment":
					p+=(int)(goodsVO.getMarket_price()*goodsVO.getGoods_num());
					points+=(int)(goodsVO.getMarket_price()*goodsVO.getGoods_num()*shop_price_bs);
					qbbig+=(int)(goodsVO.getMarket_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
					first+=(int)(goodsVO.getMarket_price()*first_rate*goodsVO.getGoods_num());
					second+=(int)(goodsVO.getMarket_price()*second_rate*goodsVO.getGoods_num());
					sjfl+=(int)(goodsVO.getMarket_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
					break;
				default:
					p+=goodsVO.getGoods_price()*goodsVO.getGoods_num();
					sjfl+=(int)(goodsVO.getGoods_price()*goodsVO.getGoods_num());
					break;
				}
			}
		}
		Rebate Rebate=new Rebate();
		Rebate.setP(p);
		Rebate.setBuy_user_id(zjcUsersInfoPO.getUser_id());
		Rebate.setPoints(points);
		Rebate.setSjfl(sjfl);
		Rebate.setFirst_old(first);
		Rebate.setSecond_old(second);
		Rebate.setFirst(Math.min(first, first_wallet_quota));
		Rebate.setSecond(Math.min(second, second_wallet_quota));
		Rebate.setMdoel(zjcMemberParameterPO.getMember_commission_model());
		Rebate.setFirst_leader(first_leader);
		Rebate.setSecond_leader(second_leader);
		Rebate.setSale_user_id(sale_user_id);
		Rebate.setQbbig(qbbig);
		return Rebate;
	}
	
	public Rebate plain_Order_Commission_New(Integer order_id){
		//获取当前的系统会员配置
		ZjcMemberParameterPO zjcMemberParameterPO = zjcMemberParameterDao.selectByMaxKey();
		//充值易物劵比例
		//double recharge_member=Double.valueOf(zjcMemberParameterPO.getBarter_coupo_rate());
		//根据订单ID查找店铺的店主ID
		/*ZjcStorePO store=zjcStoreDao.selectOne(Dtos.newDto("order_id", order_id));
		int sale_user_id = store.getUser_id().intValue();*/
		int sale_user_id=(int) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.getSaleUserID", order_id);
		//查找order_id对应的订单信息
		ZjcOrderPO zjcOrderPO=zjcOrderDao.selectByKey(order_id);
		//根据订单的user_id查找该订单的消费人
		ZjcUsersInfoPO zjcUsersInfoPO=zjcUsersInfoDao.selectByKey(zjcOrderPO.getUser_id());
		//该会员的一级上级和二级上级ID
		Object first_leader_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.firstLeader", zjcUsersInfoPO.getUser_id());
		int first_leader=first_leader_obj==null ? 0 :(int) first_leader_obj;
		Object second_leader_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.secondLeader", zjcUsersInfoPO.getUser_id());
		int second_leader= second_leader_obj == null ? 0 :(int)second_leader_obj;
		int first_qualified_member=0;
		int first_wallet_quota=0;
		int secend_qualified_member=0;
		int second_wallet_quota=0;
		if(first_leader!=0){
			//一级上级的合格子会员
			Object first_qualified_member_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.qualifiedMember", first_leader);
			 first_qualified_member= first_qualified_member_obj == null ? 0: (int) first_qualified_member_obj;
			//一级上级的钱包额度
			 Object first_wallet_quota_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.wallet_quota", first_leader);
			 first_wallet_quota = first_wallet_quota_obj == null ? 0 : (int) first_wallet_quota_obj;
		}
		 if(second_leader!=0){
			    //二级上级的合格子会员
			Object secend_qualified_member_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.qualifiedMember", second_leader);
			secend_qualified_member=secend_qualified_member_obj == null ? 0 :(int)secend_qualified_member_obj ;
				//二级上级的钱包额度
			Object second_wallet_quota_obj = sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.wallet_quota", second_leader);
			second_wallet_quota = second_wallet_quota_obj==null ? 0 : (int)second_wallet_quota_obj ;
		 }
		//根据订单ID查询该笔订单对于的商品数组
		List<ZjcGoodsVO> goodsList=new ArrayList<ZjcGoodsVO>();
		List<ZjcOrderGoodsPO> ordergoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", order_id));
				 //zjcOrderGoodsDao.list(Dtos.newDto("order_id", order_id));
		for (int i = 0; i < ordergoods.size(); i++) {
			ZjcGoodsVO ZjcGoodsVO=new ZjcGoodsVO();
			ZjcGoodsPO goods = zjcGoodsDao.selectByKey(ordergoods.get(i).getGoods_id());
			ZjcGoodsVO.setGoods_id(goods.getGoods_id());
			ZjcGoodsVO.setGoods_num(ordergoods.get(i).getGoods_num());
			ZjcGoodsVO.setWallet_amplification_rate(Float.parseFloat(goods.getWallet_amplification_rate()));
//			ZjcGoodsVO.setStore_rebate_rate(Float.parseFloat(goods.getStore_rebate_rate()));
//			ZjcGoodsVO.setMarket_price_bs(Float.parseFloat(goods.getMarket_price_bs()));
//			ZjcGoodsVO.setShop_price_bs(Float.parseFloat(goods.getShop_price_bs()));
			ZjcGoodsVO.setStore_rebate_rate(ordergoods.get(i).getStore_rebate_rate());
			ZjcGoodsVO.setMarket_price_bs(ordergoods.get(i).getMarket_price_bs());
			ZjcGoodsVO.setShop_price_bs(ordergoods.get(i).getShop_price_bs());
			ZjcGoodsVO.setCommission_rate_1(Float.parseFloat(goods.getCommission_rate_1()));
			ZjcGoodsVO.setCommission_rate_2(Float.parseFloat(goods.getCommission_rate_2()));
			ZjcGoodsVO.setCommission_rate_3(Float.parseFloat(goods.getCommission_rate_3()));
			ZjcGoodsVO.setMarket_price(ordergoods.get(i).getMarket_price().floatValue());
			ZjcGoodsVO.setGoods_price(ordergoods.get(i).getGoods_price().floatValue());
			ZjcGoodsVO.setCost_price(ordergoods.get(i).getCost_price().floatValue());
			ZjcGoodsVO.setIs_car(goods.getIs_car());
			goodsList.add(ZjcGoodsVO);
		}
		
		//用户返回的易物劵
		int points=0;
		//用户的钱包扩容
		int qbbig=0;
		//商家返利
		int sjfl=0;
		//会员提成
		int first=0,second=0;
		int p=0;
		
		for (ZjcGoodsVO goodsVO : goodsList) {
			//以物易物会员积分返还倍数
			float market_price_bs = (goodsVO.getMarket_price_bs() != 0) ? goodsVO.getMarket_price_bs() : 1;
			//在线支付会员积分返还倍数
			float shop_price_bs = (goodsVO.getShop_price_bs() != 0) ? goodsVO.getShop_price_bs() : 1;
			//一级上级的提成比例
			float first_rate=(first_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_three()))?
					Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_threes()): 
						(first_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_two()))?
								Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_twos()):
										(first_qualified_member >=Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_one()))?
												Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_ones()):0;	
			
			//二级上级的提成比例
			float second_rate=(secend_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_three()))?
					Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_threes()):
						(secend_qualified_member >= Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_two()))?
							Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_twos()):
								(secend_qualified_member >=Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_one()))?
										Float.parseFloat(zjcMemberParameterPO.getMember_comm_percentage_ones()):0;	
			switch (zjcOrderPO.getPay_code()) {
			case "rate":
				p+=goodsVO.getMarket_price()*goodsVO.getGoods_num();
				points+=goodsVO.getMarket_price()*goodsVO.getGoods_num()*market_price_bs;
				qbbig+=(int)(goodsVO.getWallet_amplification_rate()*goodsVO.getMarket_price())*goodsVO.getGoods_num();
				first+=(int)(goodsVO.getMarket_price()*first_rate)*goodsVO.getGoods_num();
				second+=(int)(goodsVO.getMarket_price()*second_rate)*goodsVO.getGoods_num();
				sjfl+=(int)(goodsVO.getMarket_price()*goodsVO.getStore_rebate_rate())*goodsVO.getGoods_num();
				break;
			case "alipay":
				p+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num());
				points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
				qbbig+=(int)(goodsVO.getCost_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
				first+=(int)(goodsVO.getCost_price()*first_rate*goodsVO.getGoods_num());
				second+=(int)(goodsVO.getCost_price()*second_rate*goodsVO.getGoods_num());
				sjfl+=(int)(goodsVO.getCost_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
				break;
			case "wxpay":
				p+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num());
				points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
				qbbig+=(int)(goodsVO.getCost_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
				first+=(int)(goodsVO.getCost_price()*first_rate*goodsVO.getGoods_num());
				second+=(int)(goodsVO.getCost_price()*second_rate*goodsVO.getGoods_num());
				sjfl+=(int)(goodsVO.getCost_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
				break;
			case "unionpay":
				p+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num());
				points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
				qbbig+=(int)(goodsVO.getCost_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
				first+=(int)(goodsVO.getCost_price()*first_rate*goodsVO.getGoods_num());
				second+=(int)(goodsVO.getCost_price()*second_rate*goodsVO.getGoods_num());
				sjfl+=(int)(goodsVO.getCost_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
				break;
			case "mixed_payment":
				p+=(int)(goodsVO.getMarket_price()*goodsVO.getGoods_num());
				points+=(int)(goodsVO.getCost_price()*goodsVO.getGoods_num()*shop_price_bs);
				qbbig+=(int)(goodsVO.getMarket_price()*goodsVO.getWallet_amplification_rate()*goodsVO.getGoods_num());
				first+=(int)(goodsVO.getMarket_price()*first_rate*goodsVO.getGoods_num());
				second+=(int)(goodsVO.getMarket_price()*second_rate*goodsVO.getGoods_num());
				sjfl+=(int)(goodsVO.getMarket_price()*goodsVO.getStore_rebate_rate()*goodsVO.getGoods_num());
				break;
			default:
				p+=goodsVO.getGoods_price()*goodsVO.getGoods_num();
				sjfl+=(int)(goodsVO.getGoods_price()*goodsVO.getGoods_num());
				break;
			}
		}
		Rebate Rebate=new Rebate();
		Rebate.setP(p);
		Rebate.setBuy_user_id(zjcUsersInfoPO.getUser_id());
		Rebate.setPoints(points);
		Rebate.setSjfl(sjfl);
		Rebate.setFirst_old(first);
		Rebate.setSecond_old(second);
		Rebate.setFirst(Math.min(first, first_wallet_quota));
		Rebate.setSecond(Math.min(second, second_wallet_quota));
		Rebate.setMdoel(zjcMemberParameterPO.getMember_commission_model());
		Rebate.setFirst_leader(first_leader);
		Rebate.setSecond_leader(second_leader);
		Rebate.setSale_user_id(sale_user_id);
		Rebate.setQbbig(qbbig);
		return Rebate;
	}
	
	/**
	 * 消费返给会员生成到期队列（只限于订单）
	 * * 
	 * @throws ParseException 
	 * 
	 * */
	public boolean xf_expire(int points , int order_id,int is_car) throws ParseException {
		  //获取订单的下单时间
		  ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(order_id);
		  int a=0;
		  if(!"equal".equals(zjcOrderPO.getPay_code())){//易物券支付周期不返还，则不进行返分
			  //获取当前订单的商品ID
			  List<ZjcOrderGoodsPO> ordergoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", order_id));
			  for (ZjcOrderGoodsPO zjcOrderGoodsPO : ordergoods) {
				   ZjcGoodsPO goods = zjcGoodsDao.selectByKey(zjcOrderGoodsPO.getGoods_id());
				  if(is_car == goods.getIs_car()){
					  //根据商品ID获取当前订单商品的返还权重
						 String [] goods_weight=zjcOrderGoodsPO.getGoods_weight().split(",");
						 for (int j = 0; j < goods_weight.length; j++) {
							 String Goods_weight=goods_weight[j];
							//根据权重算出要返回的
							 int total=new BigDecimal(Goods_weight).divide(new BigDecimal(100)).multiply(new BigDecimal(points)).intValue();
							 ZjcQueuePO queue=new ZjcQueuePO();
							 //try {
								 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								 Date date= df.parse("2033-5-18 11:33:19");
								 if(j==0){
									 queue.setAdd_time(zjcOrderPO.getAdd_time());
								 } else {
									 queue.setAdd_time(date);
								 }
								 Rebate rebate = apiPublicService.distribute_integral(total);
								 queue.setSend_time(new Date());
								 queue.setNote("消费后，将易物券返回会员");
								 queue.setType(4);
								 queue.setUser_id(zjcOrderPO.getUser_id());
								 queue.setRelation_id(order_id);
								 queue.setKz_points(rebate.getKz());
								 queue.setXf_points(rebate.getXf());							
								 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
								 a= zjcQueueDao.insert(queue);
							/* } catch (ParseException e) {
								e.printStackTrace();
							}*/
						}
				  }
				   	 
			  	}
		 }
		if(a==1){
			return true;	
		}else{
			return false;
		}
	}
	
	public boolean xf_expire_new(int points , int order_id) throws ParseException {
		  //获取订单的下单时间
		  ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(order_id);
		  int a=0;
		  if(!"rate".equals(zjcOrderPO.getPay_code())){//易物券支付周期不返还，则不进行返分
			  //获取当前订单的商品ID
			  List<ZjcOrderGoodsPO> ordergoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", order_id));
			  for (ZjcOrderGoodsPO zjcOrderGoodsPO : ordergoods) {
					 //根据商品ID获取当前订单商品的返还权重
					 String [] goods_weight=zjcOrderGoodsPO.getGoods_weight().split(",");
					 for (int j = 0; j < goods_weight.length; j++) {
						 String Goods_weight=goods_weight[j];
						 //根据权重算出要返回的
						 int total=new BigDecimal(Goods_weight).divide(new BigDecimal(100)).multiply(new BigDecimal(points)).intValue();
						 ZjcQueuePO queue=new ZjcQueuePO();
						 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Date date= df.parse("2033-5-18 11:33:19");
						 if(j==0){//设置两天后自动返分
						     Date newdate=zjcOrderPO.getAdd_time();  
						     Calendar calendar = Calendar.getInstance();  
						     calendar.setTime(newdate);  
						     calendar.add(Calendar.DAY_OF_MONTH, +2);  
						     date = calendar.getTime();  
							 queue.setAdd_time(df.parse(df.format(date)));
						 } else {
							 queue.setAdd_time(date);
						 }
						 Rebate rebate = apiPublicService.distribute_integral(total);
						 queue.setSend_time(new Date());
						 queue.setNote("消费后，将易物券返回会员");
						 queue.setType(4);
						 queue.setUser_id(zjcOrderPO.getUser_id());
						 queue.setRelation_id(order_id);
						 queue.setKz_points(rebate.getKz());
						 queue.setXf_points(rebate.getXf());
						 if(!"rate".equals(zjcOrderPO.getPay_code())){
							 queue.setIs_cancle(1); 
						 }
						 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						 a= zjcQueueDao.insert(queue);
					}
			  	}
		 }
		if(a==1){
			return true;	
		}else{
			return false;
		}
	}
	
	/**
	 * 成为合格会员
	 * @param $user_id 用户ID $user_points 用户消费金额
	 * * */
	public void become_vip_member(int user_id){
		//获取配置项
		ZjcMemberParameterPO ZjcMemberParameter=new ZjcMemberParameterPO();
		List<ZjcMemberParameterPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.parameter",ZjcMemberParameter);
		int formal_member=Integer.parseInt(paramDtos.get(0).getQualified_menber());
		
		
		//获取用户累计消费金额
		ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(new BigInteger(String.valueOf(user_id)));
		
		ZjcUsersInfoPO userinfo=new ZjcUsersInfoPO();
		userinfo.setUser_id(new BigInteger(String.valueOf(user_id)));
		//当前会员信息
		ZjcUsersInfoPO zjcUsersInfo = zjcUsersInfoDao.selectByKey(new BigInteger(String.valueOf(user_id)));
		//查询上级会员信息
		ZjcUsersInfoPO higherUsersInfo = zjcUsersInfoDao.selectByKey(new BigInteger(String.valueOf(zjcUsersInfo.getFirst_leader())));
		if(Integer.parseInt(zjcUsersAccountInfoPO.getTotal_amount())>=formal_member){
			zjcUsersInfo.setIs_qualified_member(1);
			int a=zjcUsersInfoDao.updateByKey(zjcUsersInfo);
			if(a==1){
				 //用户成为合格会员将在他的上级合格会员数上加1
				higherUsersInfo.setQualified_member(higherUsersInfo.getQualified_member()+1);
				zjcUsersInfoDao.updateByKey(higherUsersInfo);
			}
		}
	}
	
	/**
	 * 混合支付易物券扣除
	 * 
	 * @param httpModel
	 */
	public boolean mixed_pay(int order_id){
		boolean bool = false;
		ZjcOrderPO ZjcOrder=new ZjcOrderPO();
		ZjcOrder.setOrder_id(order_id);
		List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
        ZjcOrderGoodsPO ZjcOrderGoodsPO=new ZjcOrderGoodsPO();
		ZjcOrderGoodsPO.setOrder_id(order_id);
	    try {
	           // 2 写入订单商品表
			   ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
			   ZjcUsersAccountInfoPO.setUser_id(list.get(0).getUser_id());
			   // 4 扣除积分
			   List<ZjcUsersAccountInfoPO> users=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
			   double pay_points=new BigDecimal(users.get(0).getPay_points()).subtract(list.get(0).getBarter_coupons()).doubleValue(); 
			   users.get(0).setPay_points((int)pay_points);
			   int nowTotalAmount = Integer.parseInt(users.get(0).getTotal_amount()) + list.get(0).getBarter_coupons().intValue();
			   users.get(0).setTotal_amount(nowTotalAmount+"");//增加累计消费
			   zjcUsersAccountInfoDao.updateByKey(users.get(0));
			   shop_order(order_id); //商城购物记录发送
			   list.get(0).setOrder_status(0);
			   list.get(0).setPoints_pay_status(1);//混合支付易物券支付状态已支付
			   zjcOrderDao.updateByKey(list.get(0));
			   //调用存储过程,实时改变会员等级
			   sqlDao.call("level",Dtos.newDto("user_id", list.get(0).getUser_id()));
	    		//写入订单状态表
		        ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_user(list.get(0).getUser_id());
				zjcOrderActionPONew.setAction_user_type(1);//用户
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(order_id);
				zjcOrderActionPONew.setOrder_status(0);//待确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(0);//未发货
				zjcOrderActionPONew.setStatus_desc("提交订单");
				zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
				//商品反分处理
		       if(list.get(0).getPay_status()==1){
                    //下单时商家会员返利 对于特殊商品 * 
    			    deal_goods_ends(order_id);
    			    //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
    			    Rebate Rebate=plain_Order_Commission(order_id,0);
    			    xf_expire(Rebate.getPoints(), order_id,0);
    			    ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
    				user.setUser_id(Rebate.getBuy_user_id());
    				List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
    				int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(list1.get(0).getWallet_quota());
    				int Count_wallet_quota=Rebate.getQbbig()+list1.get(0).getCount_wallet_quota();
    				//int Total_amount=Rebate.getPoints()+Integer.parseInt(list1.get(0).getTotal_amount());
    				int Total_amount=list.get(0).getBarter_coupons().intValue() + Integer.parseInt(list1.get(0).getTotal_amount());
    				if(Rebate.getQbbig()>0){
    					ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
    					useracc.setWallet_quota(String.valueOf(Wallet_quota));
    					useracc.setCount_wallet_quota(Count_wallet_quota);
    					useracc.setUser_id(Rebate.getBuy_user_id());
    					zjcUsersAccountInfoDao.updateByKey(useracc);
    				}
    				if(Rebate.getPoints()>0){
    					ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
    					useracc.setTotal_amount(String.valueOf(Total_amount));
    					useracc.setUser_id(list.get(0).getUser_id());
    					zjcUsersAccountInfoDao.updateByKey(useracc);
    					ZjcUsersInfoPO userinfo=new ZjcUsersInfoPO();
    					userinfo.setUser_id(Rebate.getBuy_user_id());
    					List<ZjcUsersInfoPO> zjcuserinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",userinfo);
    					//成为合格会员
    					if(zjcuserinfo.get(0).getIs_qualified_member()==0){
						    //调用存储过程,实时改变会员等级
			    		    sqlDao.call("level",Dtos.newDto("user_id", Rebate.getBuy_user_id().toString()));
    					}
    				}
		       }
		        ZjcAccountLogPO log=new ZjcAccountLogPO();
		        log.setUser_id(list.get(0).getUser_id());
		        log.setUser_money(new BigDecimal("0"));
		        log.setPay_points(list.get(0).getOrder_amount().intValue());
		        log.setChange_time(new Date());
		        log.setDescd("下单消费");
		        log.setOrder_sn(list.get(0).getOrder_sn());
		        log.setOrder_id(list.get(0).getOrder_id());
		        log.setOrder_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		        zjcAccountLogDao.insert(log);
		        bool = true;
		} catch (Exception e) {
			   bool = false;
			   e.printStackTrace();
		}
       
        return  bool;
	}
	
	/**
	 * 混合支付易物券扣除
	 * 
	 * @param order_id
	 * @throws ParseException 
	 */
	public void order_pay(int order_id) throws ParseException{
		ZjcOrderPO ZjcOrder=new ZjcOrderPO();
		ZjcOrder.setOrder_id(order_id);
		List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
        ZjcOrderGoodsPO ZjcOrderGoodsPO=new ZjcOrderGoodsPO();
		ZjcOrderGoodsPO.setOrder_id(order_id);
	    try {
	           // 2 写入订单商品表
			   ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
			   ZjcUsersAccountInfoPO.setUser_id(list.get(0).getUser_id());
			   // 4 扣除积分
			   List<ZjcUsersAccountInfoPO> users=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
			   double pay_points=new BigDecimal(users.get(0).getPay_points()).subtract(list.get(0).getBarter_coupons()).doubleValue(); 
			   users.get(0).setPay_points((int)pay_points);
			   int nowTotalAmount = Integer.parseInt(users.get(0).getTotal_amount()) + list.get(0).getBarter_coupons().intValue();
			   users.get(0).setTotal_amount(nowTotalAmount+"");//增加累计消费
			   zjcUsersAccountInfoDao.updateByKey(users.get(0));
			   shop_order(order_id); //商城购物记录发送
			   //list.get(0).setPay_status(1);//修改为已支付
			   list.get(0).setOrder_status(0);
			   list.get(0).setPoints_pay_status(1);//混合支付易物券支付状态已支付
			   zjcOrderDao.updateByKey(list.get(0));
			   //调用存储过程,实时改变会员等级
			   sqlDao.call("level",Dtos.newDto("user_id", list.get(0).getUser_id()));
	    		//写入订单状态表
		        ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_user(list.get(0).getUser_id());
				zjcOrderActionPONew.setAction_user_type(1);//用户
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(order_id);
				zjcOrderActionPONew.setOrder_status(0);//待确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(0);//未发货
				zjcOrderActionPONew.setStatus_desc("提交订单");
				zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
		} catch (Exception e) {
			   e.printStackTrace();
		}
    	 //商品反分处理
       if(list.get(0).getPay_status()==1){
           //下单时商家会员返利 对于特殊商品 * 
		   deal_goods_ends(order_id);
		   //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
		   Rebate Rebate=plain_Order_Commission(order_id,0);
		   xf_expire(Rebate.getPoints(), order_id,0);
		   ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
			user.setUser_id(Rebate.getBuy_user_id());
			List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
			int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(list1.get(0).getWallet_quota());
			int Count_wallet_quota=Rebate.getQbbig()+list1.get(0).getCount_wallet_quota();
			//int Total_amount=Rebate.getPoints()+Integer.parseInt(list1.get(0).getTotal_amount());
			int Total_amount=list.get(0).getBarter_coupons().intValue() + Integer.parseInt(list1.get(0).getTotal_amount());
			if(Rebate.getQbbig()>0){
				ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
				useracc.setWallet_quota(String.valueOf(Wallet_quota));
				useracc.setCount_wallet_quota(Count_wallet_quota);
				useracc.setUser_id(Rebate.getBuy_user_id());
				zjcUsersAccountInfoDao.updateByKey(useracc);
			}
			if(Rebate.getPoints()>0){
				ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
				useracc.setTotal_amount(String.valueOf(Total_amount));
				useracc.setUser_id(list.get(0).getUser_id());
				zjcUsersAccountInfoDao.updateByKey(useracc);
				ZjcUsersInfoPO userinfo=new ZjcUsersInfoPO();
				userinfo.setUser_id(Rebate.getBuy_user_id());
				List<ZjcUsersInfoPO> zjcuserinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",userinfo);
				//成为合格会员
				if(zjcuserinfo.get(0).getIs_qualified_member()==0){
				    //调用存储过程,实时改变会员等级
	    		    sqlDao.call("level",Dtos.newDto("user_id", Rebate.getBuy_user_id().toString()));
				}
			}
       }
        ZjcAccountLogPO log=new ZjcAccountLogPO();
        log.setUser_id(list.get(0).getUser_id());
        log.setUser_money(new BigDecimal("0"));
        log.setPay_points(list.get(0).getOrder_amount().intValue());
        log.setChange_time(new Date());
        log.setDescd("下单消费");
        log.setOrder_sn(list.get(0).getOrder_sn());
        log.setOrder_id(list.get(0).getOrder_id());
        log.setOrder_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
        zjcAccountLogDao.insert(log);
	}
	
	/**
	 * 保存小平台订单
	 * 
	 * @param order_id
	 */
	public void xptCreateOrder(int order_id){
		Dto dto = sqlDao.selectDto("com.zjc.order.dao.ZjcOrderDao.getOrderInfoByOrderId", Dtos.newDto("order_id", order_id));
		if(AOSUtils.isNotEmpty(dto)){
			Map<String,String> map = new HashMap<String,String>();
			Iterator<Map.Entry<String, Object>> entries = dto.entrySet().iterator();  
			while (entries.hasNext()) {  
			    Map.Entry<String, Object> entry = entries.next(); 
			    map.put(entry.getKey(), entry.getValue().toString());
			  
			} 
			String charset = "GBK";  
			//测试
	  	    //String httpOrgCreateTest = "http://cs.daxiaoyunyi.com/interface/zjcOrderSave.jhtml"; 
			//正式
			String httpOrgCreateTest = "http://www.daxiaoyunyi.com/interface/zjcOrderSave.jhtml";
	  	    HttpClientUtil httpClientUtil = new HttpClientUtil();
	  	    httpClientUtil.doPost(httpOrgCreateTest,map,charset);
		}
	}
	
	/**
	 * 查询用户在1号店连续下单的天数
	 * 
	 * @return
	 */
	public int getContinuousOrderDays(String user_id,String store_id){//3411419
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		map.put("store_id", store_id);
		int continueTimes = 0;
		for(int i=0;i<7;i++){
			map.put("djs_day", i);
			Integer ordernum = (Integer)sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.getContinuousOrderDays", Dtos.newDto(map));
			if(ordernum == 0 && i!=0){//如果数据中断
				break;
			} else if(ordernum == 0 && i==0){
				continueTimes=0;
			} else {
				continueTimes ++;
			}
		}
		return continueTimes;
	}
	
	
	
	public void getAlsoDrops(int order_id) throws ParseException{
		  //获取订单的下单时间
		  ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(order_id);
		  List<ZjcOrderGoodsPO> ZjcOrderGoodsPO = zjcOrderGoodsDao.list(Dtos.newDto("order_id",order_id));
		  ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		  if("mixed_payment".equals(zjcOrderPO.getPay_code())){//易物券支付周期不返还，则不进行返分
			  for (int i = 0; i < ZjcOrderGoodsPO.size(); i++) {
				  ZjcGoodsPO ZjcGoodsPO=zjcGoodsDao.selectByKey(ZjcOrderGoodsPO.get(i).getGoods_id());
				  if(ZjcGoodsPO.getIs_car()==1){//混合支付并且是特殊商品
					     //根据权重算出要返回的
						 int total=new BigDecimal(zjcMemberOtherPO.getConsumption_coupons()).divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
						 ZjcQueuePO queue=new ZjcQueuePO();
						 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Date date= df.parse("2033-5-18 11:33:19");
						//设置26天后自动返分
					     Date newdate=zjcOrderPO.getAdd_time();  
					     Calendar calendar = Calendar.getInstance();  
					     calendar.setTime(newdate);  
					     calendar.add(Calendar.DAY_OF_MONTH, +26);  
					     date = calendar.getTime();  
						 queue.setAdd_time(df.parse(df.format(date)));
						 queue.setSend_time(new Date());
						 queue.setNote("消费后，将滴返回会员");
						 queue.setType(4);
						 queue.setUser_id(zjcOrderPO.getUser_id());
						 queue.setRelation_id(order_id);
						 queue.setXf_points(total);
						 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						 zjcQueueDao.insert(queue);
					}else if("mixed_payment".equals(zjcOrderPO.getPay_code())&&ZjcGoodsPO.getIs_car()==0){//混合支付但不是特殊商品
						 //根据权重算出要返回的
						 int total=new BigDecimal(zjcMemberOtherPO.getConsumption_coupons()).divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
						 ZjcQueuePO queue=new ZjcQueuePO();
						 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Date date= df.parse("2033-5-18 11:33:19");
						//设置26天后自动返分
					     Date newdate=zjcOrderPO.getAdd_time();  
					     Calendar calendar = Calendar.getInstance();  
					     calendar.setTime(newdate);  
					     calendar.add(Calendar.DAY_OF_MONTH, +26);  
					     date = calendar.getTime();  
						 queue.setAdd_time(df.parse(df.format(date)));
						 queue.setSend_time(new Date());
						 queue.setNote("消费后，将滴返回会员");
						 queue.setType(4);
						 queue.setUser_id(zjcOrderPO.getUser_id());
						 queue.setRelation_id(order_id);
						 queue.setXf_points(total);
						 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						 zjcQueueDao.insert(queue);
						 //会员立即到账50%
						 ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
						 user.setUser_id(zjcOrderPO.getUser_id());
						 /*List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
						 BigDecimal totals=new BigDecimal("0.5").multiply(zjcOrderPO.getBarter_coupons());
						 list1.get(0).setDrops(totals);
						 zjcUsersAccountInfoDao.updateByKey(list1.get(0));*/
					}
			    }
			  }else if("cash".equals(zjcOrderPO.getPay_code())||zjcOrderPO.getPay_code().equals("alipay")||zjcOrderPO.getPay_code().equals("wxpay")){//现金提成
				     ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
				     user.setUser_id(zjcOrderPO.getUser_id());
					 List<ZjcUsersAccountInfoPO> userinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
					 BigDecimal t=zjcOrderPO.getCash();
					 userinfo.get(0).setDrops(userinfo.get(0).getDrops()+t.intValue());
					 zjcUsersAccountInfoDao.updateByKey(userinfo.get(0));
			}
	}
	
	
	public void getAlsoDropst(int order_id) throws ParseException{
		  //获取订单的下单时间
		  ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(order_id);
		  if("mixed_pay_drops".equals(zjcOrderPO.getPay_code())||"mixed_payment".equals(zjcOrderPO.getPay_code())||"rate".equals(zjcOrderPO.getPay_code())){//易物券支付周期不返还，则不进行返分
			     ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
			     user.setUser_id(zjcOrderPO.getUser_id());
				 List<ZjcUsersAccountInfoPO> userinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
				 int total=new BigDecimal("0.5").multiply(zjcOrderPO.getBarter_coupons()).intValue();
				 userinfo.get(0).setDrops(userinfo.get(0).getDrops()+total);
				 zjcUsersAccountInfoDao.updateByKey(userinfo.get(0));
			  }else {//现金提成
				     ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
				     user.setUser_id(zjcOrderPO.getUser_id());
					 List<ZjcUsersAccountInfoPO> userinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
					 int total=new BigDecimal("1").multiply(zjcOrderPO.getCash()).intValue();
					 userinfo.get(0).setDrops(userinfo.get(0).getDrops()+total);
					 zjcUsersAccountInfoDao.updateByKey(userinfo.get(0));
			}
	}
	
}
