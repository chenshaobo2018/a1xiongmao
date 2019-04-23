/**
 * 
 */
package com.wxstore.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
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
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.goods.dao.po.XpttsgoodsidPO;
import com.api.login.dao.ZjcVouchersDao;
import com.api.login.dao.po.ZjcVouchersPO;
import com.api.order.OrderService;
import com.api.order.dao.po.Goods;
import com.api.order.dao.po.Json;
import com.api.order.dao.po.OrderVO;
import com.api.order.dao.po.Rebate;
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
import com.zjc.users.dao.ZjcUserAddressDao;
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
@Service(value="WxStoreService")
public class WxStoreService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUserAddressDao zjcUserAddressDao;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private ZjcVouchersDao zjcVouchersDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ZjcOrderGoodsDao ZjcOrderGoodsDao;
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcAccountLogDao ZjcAccountLogDao;
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	private static final Logger logger = LoggerFactory.getLogger(WxStoreService.class);
	/**
	 * 登录页面初始化
	 * 
	 * @param httpModel
	 */
	public void initZjcLogin(HttpModel httpModel) {
		httpModel.setViewPath("project/wxstore/index.jsp");
	}
	
	/**
	 * @param httpModel
	 * @return
	 */
	public PageVO WxStoreGoods(HttpModel httpModel) {
		MessageVO MessageVO=new MessageVO();
		Dto qDto=httpModel.getInDto();
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			Object b = httpModel.getAttribute("page");
			String a=String.valueOf(b);
			int page=Integer.parseInt((String)a);
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsWxStorePage", qDto);
			PageVO pageVO=new PageVO();
			if(goodslist.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), 1);
				pageVO.setList(goodslist);
				pageVO.setNowPage(page);
			}
		return pageVO;
	}
	
	
	/**
	 * 收货地址
	 * 
	 * @param openid
	 * @return
	 */
	public String getUserAddressList(String openid){
		MessageVO MessageVO=new MessageVO();
		if(!openid.isEmpty()){
			List<ZjcUserAddressPO> address = new ArrayList<ZjcUserAddressPO>();
			ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", openid));
			address = zjcUserAddressDao.list(Dtos.newDto("user_id", zjcUsersInfoPO.getUser_id()));
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(address);
		}else{
			MessageVO.setCode(Apiconstant.System_busy.getIndex());
			MessageVO.setMsg(Apiconstant.System_busy.getName());
		}
		return AOSJson.toJson(MessageVO);
	} 
	
	/**
	 * 收货地址
	 * 
	 * @param openid
	 * @return
	 */
	public List<ZjcUserAddressPO> getUserAddressListToGobuy(String openid){
		List<ZjcUserAddressPO> address = new ArrayList<ZjcUserAddressPO>();
		if(!openid.isEmpty()){
			ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", openid));
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
			ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid",dto.getString("openid")));
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
				|| AOSUtils.isEmpty(dto.getString("store_id"))|| AOSUtils.isEmpty(dto.getString("Goods"))){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		logger.info("========================微信wxstore下单追踪3：支付编码："+dto.getString("pay_type")+"，下单会员号："+ request.getSession().getAttribute("user_id"));
		MessageVO = apiPublicService.isLockedHandle();
		if(MessageVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(MessageVO);
		}
		MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", request.getSession().getAttribute("user_id")));
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
			order.setPay_name("混合支付易物券");
		} else if(order.getPay_type().equals("mixed_pay_drops")){
			order.setPay_name("混合支付滴");
		} else if(order.getPay_type().equals("shouxinpay")){
			order.setPay_name("首信支付");
		}
		order.setUser_note(httpModel.getInDto().getString("user_note"));
		order.setStore_id(httpModel.getRequest().getParameter("store_id").toString());
		//order.setPostFee(httpModel.getRequest().getParameter("postFee").toString());
		String goodJson=dto.getString("Goods");
		List<Goods> goodList=AOSJson.fromJson(goodJson, new TypeToken<List<Goods>>(){}.getType());
		order.setGoods(goodList);
		order.setUser_id(request.getSession().getAttribute("user_id").toString());
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
		//判断是否开通结算中心
	    //List<ZjcUsersAccountInfoPO> uai = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    List<ZjcGoodsPO> ZjcGoods=new ArrayList<ZjcGoodsPO>();
	    XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", goods.get(0).getGoods_id()));
	    ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", request.getSession().getAttribute("user_id")));
		
		if(AOSUtils.isNotEmpty(userInfo.getXpt())){//小平台用户部分商品不能购买
			if("普通会员".equals(userInfo.getLevel())){//未购买过特殊商品
				if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//小平台用户第一次购买特殊商品商品，改变会员状态
			    	userInfo.setLevel("合格会员");
			    	zjcUsersInfoDao.updateByKey(userInfo);
				}
			}
		}
	    for (Goods goods2 : goods) {
			ZjcGoodsPO isgoodnum = zjcGoodsDao.selectByKey(Integer.parseInt(goods2.getGoods_id()));
			ZjcGoods.add(isgoodnum);
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
	    	if(isgoodnum.getIs_vip() == 1){//如果是VIP商品
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
	    		
	    	}
	    	Map<String,Object> map = new HashMap<String,Object>();
			map.put("user_id", request.getSession().getAttribute("user_id").toString());
			map.put("goods_id", goods2.getGoods_id());
			Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
			if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
				MessageVO.setMsg("很抱歉！该商品每日每人只能购买" + isgoodnum.getToday_limit_times()+"次，请明天再来");
				MessageVO.setCode(Apiconstant.goods_times_enough.getIndex());
		    	return AOSJson.toJson(MessageVO);
			}
			if(isgoodnum.getIs_on_sale() == 0){//商品已下架，不能下单
				MessageVO.setMsg(isgoodnum.getGoods_name()+Apiconstant.Commodities_have_shelves.getName());
				MessageVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
		    	return AOSJson.toJson(MessageVO);
			}
			if(isgoodnum.getStore_count() < Integer.parseInt(goods2.getGoods_num())){//商品库存不足，不能下单
				MessageVO.setMsg(Apiconstant.Insufficient_inventory.getName());
				MessageVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
		    	return AOSJson.toJson(MessageVO);
			} 
			if(isgoodnum.getGoods_state_1() != 3 || isgoodnum.getGoods_state_2() != 3){//该商品未审核或者审核不通过
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
	    if(Addre.size() == 0){
	    	MessageVO.setMsg(Apiconstant.Shipping_address_wrong.getName());
	    	MessageVO.setCode(Apiconstant.Shipping_address_wrong.getIndex());
	    	return AOSJson.toJson(MessageVO);
	    }
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
	    BigDecimal discount = BigDecimal.ZERO;//折扣券
	    if(AOSUtils.isNotEmpty(dto.getString("discount"))){//不为空
	    	discount = dto.getBigDecimal("discount");
	    }
	    OrderVO result=orderService.zjc_calculate_price(order.getUser_id(),goods,order.getPay_type(),vouchernum,discount);
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
	    MessageVO = apiPublicService.isShopOnLineAccount(new BigInteger(request.getSession().getAttribute("user_id").toString()),result.getBarter_coupons().intValue());
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
	    	 vo=orderService.addOrder(order,result,ZjcGoods,Address);
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
	    if(vouchernum > 0){//代金券使用成功
	   		voucher.setIs_use(1);//代金券状态设置为已使用
	   		voucher.setUse_time(new Date());//使用时间
	   		voucher.setOrder_id(list.get(0).getOrder_sn().toString());//使用订单号
	   		zjcVouchersDao.updateByKey(voucher);
	   	}
	    Map<String,String> params=new HashMap<String,String>();
	    params.put("order_id", list.get(0).getOrder_id().toString());
	    params.put("order_sn", list.get(0).getOrder_sn().toString());
	    params.put("buyNum", order.getGoods().get(0).getGoods_num().toString());
	    MessageVO payVo = new MessageVO(); 
	    params.put("pay_type", order.getPay_type());
	    if(order.getPay_type().equals("rate")){
	    	payVo = this.order_pay(list.get(0).getOrder_id().toString(), dto.getString("pay_password"), Integer.parseInt(request.getSession().getAttribute("user_id").toString()));
	    } else if(order.getPay_type().equals("equal")){
			payVo = this.order_pay(list.get(0).getOrder_id().toString(), dto.getString("pay_password"), Integer.parseInt(request.getSession().getAttribute("user_id").toString()));
	    } else if(order.getPay_type().equals("wxpay") || order.getPay_type().equals("alipay") || order.getPay_type().equals("shouxinpay")){
			payVo.setCode(1);
		}else if(order.getPay_type().equals("mixed_payment") || order.getPay_type().equals("mixed_pay_drops")){
			payVo = this.mixed_pay(list.get(0).getOrder_id().toString(), dto.getString("pay_password"), Integer.parseInt(request.getSession().getAttribute("user_id").toString()));
		}
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
	
	
	/**
	 * 混合支付易物券扣除
	 * 
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public MessageVO mixed_pay(String orderId,String pay_password,Integer user_id){
		MessageVO msgVO = new MessageVO();
		if(AOSUtils.isEmpty(orderId) || AOSUtils.isEmpty(pay_password)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return msgVO;
		}
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return msgVO;
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", user_id));
		if(msgVO.getCode() != 1){//会员账户被冻结
			return msgVO;
		}
		String order_id = orderId;
		ZjcOrderPO ZjcOrder=new ZjcOrderPO();
		ZjcOrder.setOrder_id(Integer.valueOf(order_id.trim()).intValue());
		List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
		if(list.get(0).getPoints_pay_status()==1){//订单券已支付
			msgVO.setMsg(Apiconstant.Order_Has_Payed.getName());
			msgVO.setCode(Apiconstant.Order_Has_Payed.getIndex());
			return msgVO;
		}
		ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(list.get(0).getUser_id());
		if(AOSUtils.isEmpty(userInfo.getPay_password())){
			msgVO.setMsg(Apiconstant.pay_psd_is_null.getName());
			msgVO.setCode(Apiconstant.pay_psd_is_null.getIndex());
			return msgVO;
		}
		
		boolean password = userInfo.getPay_password().equals(AOSCodec.md5("zhongjunchuangya1212" + pay_password));
		if(password==false){
			msgVO.setMsg(Apiconstant.Pay_Psd_Error.getName());
			msgVO.setCode(Apiconstant.Pay_Psd_Error.getIndex());
			return msgVO;
		}
	    try {
	    	   List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", list.get(0).getOrder_id()));
   		   	   ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
	    	   zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
	    	   List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
	    	   if(goodsd.get(0).getStore_count()<orderGood.get(0).getGoods_num()){//库存不足
	    		    msgVO.setMsg(Apiconstant.Insufficient_inventory.getName());
	    		    msgVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
		    		return msgVO;
		       }
			   if(goodsd.get(0).getIs_on_sale()!=1){//商品已下架
				  msgVO.setMsg(goodsd.get(0).getGoods_name()+Apiconstant.Commodities_have_shelves.getName());
				  msgVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
	    		  return msgVO;
	    	   }
			   if(goodsd.get(0).getGoods_state_1() != 3 || goodsd.get(0).getGoods_state_2() != 3){//该商品未审核或者审核不通过
				  msgVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
				  msgVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
	    		  return msgVO;
	    	   }
			   //限制商品购买次数
			   ZjcGoodsPO isgoodnum = zjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
	    	   Map<String,Object> map = new HashMap<String,Object>();
			   map.put("user_id", user_id);
			   map.put("goods_id", orderGood.get(0).getGoods_id());
			   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
			   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
				   msgVO.setMsg("很抱歉！该商品每日每人只能购买" + isgoodnum.getToday_limit_times()+"次，请明天再来");
				   msgVO.setCode(Apiconstant.goods_times_enough.getIndex());
			       return msgVO;
			   }
	    	
	           // 2 写入订单商品表
			   ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
			   ZjcUsersAccountInfoPO.setUser_id(list.get(0).getUser_id());
			   // 4 扣除积分
			   List<ZjcUsersAccountInfoPO> users=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
			   
			   if(list.get(0).getPay_code().equals("mixed_pay_drops")){
    			   if(list.get(0).getBarter_coupons().compareTo(new BigDecimal(users.get(0).getDrops()))==1){//判断余额是否充足
    				   msgVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
    				   msgVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
	    		    	return msgVO;
	    		   }
    		   }else if(list.get(0).getBarter_coupons().compareTo(new BigDecimal(users.get(0).getPay_points()))==1){//判断余额是否充足
    			   msgVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
    			   msgVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
    		    	return msgVO;
    		   }
			   //更改商品库存销量信息
	    	   int Goods_nums = orderGood.get(0).getGoods_num();
	    	   Dto countdto = Dtos.newDto();
	    	   countdto.put("Goods_nums", Goods_nums);
	    	   countdto.put("goods_id", goodsd.get(0).getGoods_id());
			   int k=sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateByStoreCount", countdto);
			   if(k == 0){//库存不足
				   msgVO.setMsg(Apiconstant.Insufficient_inventory.getName());
	    		   msgVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
		    	   return msgVO;
			   }
			   if(list.get(0).getPay_code().equals("mixed_pay_drops")){
				   double pay_points=new BigDecimal(users.get(0).getDrops()).subtract(list.get(0).getBarter_coupons()).doubleValue(); 
	    		   users.get(0).setDrops((int)pay_points);
	    		   int nowTotalAmount = Integer.parseInt(users.get(0).getTotal_amount()) + list.get(0).getBarter_coupons().intValue();
	    		   users.get(0).setTotal_amount(nowTotalAmount+"");//增加累计消费
			   }else {
				   double pay_points=new BigDecimal(users.get(0).getPay_points()).subtract(list.get(0).getGoods_price()).doubleValue(); 
	    		   users.get(0).setPay_points((int)pay_points);
	    		   int nowTotalAmount = Integer.parseInt(users.get(0).getTotal_amount()) + list.get(0).getGoods_price().intValue();
	    		   users.get(0).setTotal_amount(nowTotalAmount+"");//增加累计消费
			  }
			   ZjcUsersAccountInfoDao.updateByKey(users.get(0));
			   zjcTurnIntoUtil.shop_order(Integer.valueOf(order_id.trim()).intValue()); //商城购物记录发送
			   list.get(0).setOrder_status(0);
			   list.get(0).setPoints_pay_status(1);//混合支付易物券支付状态已支付
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
				zjcOrderActionPONew.setAction_user(list.get(0).getUser_id());
				zjcOrderActionPONew.setAction_user_type(1);//用户
				zjcOrderActionPONew.setAction_user_name(userInfo.getNickname());
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(Integer.valueOf(order_id.trim()).intValue());
				zjcOrderActionPONew.setOrder_status(0);//待确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(0);//未发货
				zjcOrderActionPONew.setStatus_desc("提交订单");
				zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
				//商品反分处理
			 /*   if(list.get(0).getPoints_pay_status()==1){//易物券支付成功
	               //下单时商家会员返利 对于特殊商品 * 
    			   //zjcTurnIntoUtil.deal_goods_ends_mixed(order_id);
    			   //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
    			   //Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission(order_id,0);
    			   Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(Integer.valueOf(order_id.trim()).intValue());
    			   if(Rebate.getSjfl() > 0 ){//商家返利
    				   zjcTurnIntoUtil.sj_expire(Rebate.getSjfl(),Integer.valueOf(order_id.trim()).intValue(),Rebate.getSale_user_id());
    			   }
    			   if(Rebate.getPoints() > 0){
	    			   zjcTurnIntoUtil.xf_expire_new(Rebate.getPoints(), Integer.valueOf(order_id.trim()).intValue());
	    			   ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
	    				user.setUser_id(Rebate.getBuy_user_id());
	    				List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
	    				int Wallet_quota=Rebate.getQbbig()+Integer.parseInt(list1.get(0).getWallet_quota());
	    				int Count_wallet_quota=Rebate.getQbbig()+list1.get(0).getCount_wallet_quota();
	    				if(Rebate.getQbbig()>0){
	    					ZjcUsersAccountInfoPO useracc=new ZjcUsersAccountInfoPO();
	    					useracc.setWallet_quota(String.valueOf(Wallet_quota));
	    					useracc.setCount_wallet_quota(Count_wallet_quota);
	    					useracc.setUser_id(Rebate.getBuy_user_id());
	    					ZjcUsersAccountInfoDao.updateByKey(useracc);
	    				}
    			   }
		       }*/
		        msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
		} catch (Exception e) {
			   e.printStackTrace();
			   msgVO.setMsg(Apiconstant.Order_is_fail.getName());
			   msgVO.setCode(Apiconstant.Order_is_fail.getIndex());
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		}
       
        return  msgVO;
	}
}
