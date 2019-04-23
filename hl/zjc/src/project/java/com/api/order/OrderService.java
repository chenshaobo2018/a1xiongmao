/**
 * 
 */
package com.api.order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.HttpClientUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ValidateUtil;
import com.api.common.util.ZjcTurnIntoUtil;
import com.api.goods.dao.po.XpttsgoodsidPO;
import com.api.goods.dao.po.ZjcPromptGoodsPO;
import com.api.login.dao.ZjcVouchersDao;
import com.api.login.dao.po.ZjcVouchersPO;
import com.api.order.dao.ZjcPaymentSallerDao;
import com.api.order.dao.ZjcXxOrderDao;
import com.api.order.dao.po.Goods;
import com.api.order.dao.po.Json;
import com.api.order.dao.po.OrderVO;
import com.api.order.dao.po.Rebate;
import com.api.order.dao.po.ZjcCartPO;
import com.api.order.dao.po.ZjcPaymentSallerPO;
import com.api.order.dao.po.ZjcXxOrderPO;
import com.api.order.rabbitmq.Sender;
import com.api.pintuan.dao.po.SpellGroupRelationalPO;
import com.gexin.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.store.record.dao.ZjcIncomeFlowDao;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.ad.dao.po.ZjcAdPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcBzOrderDao;
import com.zjc.order.dao.ZjcDeliveryDocDao;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.ZjcRechargeDao;
import com.zjc.order.dao.ZjcTransferOrderTimeDao;
import com.zjc.order.dao.po.ZjcBzOrderPO;
import com.zjc.order.dao.po.ZjcDeliveryDocPO;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.order.dao.po.ZjcRechargePO;
import com.zjc.order.dao.po.ZjcTransferOrderTimePO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberMultiplicationPO;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.system.dao.po.ZjcMemberSettlementPO;
import com.zjc.system.dao.po.ZjcMemberWalletPO;
import com.zjc.users.dao.ZjcProvincialGenerationDao;
import com.zjc.users.dao.ZjcQueueDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcProvincialGenerationPO;
import com.zjc.users.dao.po.ZjcQueuePO;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;
import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 *
 */
@Service(value = "OrderService")
public class OrderService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcGoodsDao ZjcGoodsDao;
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	@Autowired
	private ZjcOrderGoodsDao ZjcOrderGoodsDao;
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	@Autowired
	private ZjcQueueDao ZjcQueueDao;
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	@Autowired
	private ZjcRechargeDao rechargeDao;
	@Autowired
	private ZjcUsersInfoDao ZjcUsersInfoDao;
	@Autowired
	private ZjcXxOrderDao ZjcXxOrderDao;
	@Autowired
	private ZjcPaymentSallerDao ZjcPaymentSallerDao;
	@Autowired
	private ZjcBzOrderDao zjcBzOrderDao;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private ZjcTransferOrderTimeDao ZjcTransferOrderTimeDao;
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcDeliveryDocDao zjcDeliveryDocDao;
	
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private ZjcIncomeFlowDao zjcIncomeFlowDao;
	
	@Autowired
	private ZjcVouchersDao zjcVouchersDao;
	@Autowired
	private ZjcStoreDao ZjcStoreDao;
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(OrderService.class);
	/**
	 * 获取商品列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String  GoodsList(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page")) || qDto.getInteger("page")<1){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			if(AOSUtils.isNotEmpty(qDto.getString("goods_name")))
				qDto.put("goods_name", ApiPublicService.trim(qDto.getString("goods_name")));
			if(AOSUtils.isNotEmpty(qDto.getString("keywords")))
				qDto.put("keywords", ApiPublicService.trim(qDto.getString("keywords")));
			List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodslistPage", qDto);
			if(goodslist.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				pageVO.setList(goodslist);
				//统计连续购买了多少天
				int continueTimes = 0;
				if(AOSUtils.isNotEmpty(qDto.getString("user_id"))){//传回user_id
					ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", qDto.getString("user_id")));
					continueTimes = zjcTurnIntoUtil.getContinuousOrderDays(qDto.getString("user_id"), "3411419");
					if(userInfo.getIs_open_min_pay() == 1){//可以购买vip商品
						if(continueTimes<6){
							continueTimes = 6;
						}
					} 
				}
				pageVO.setContinueTimes(continueTimes);
				pageVO.setSum(3411419);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 获取首页图片
	 * @param request
	 * @param response
	 * @return
	 */
	public String  getAd(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		List<Dto> goodslist = sqlDao.list("com.zjc.ad.dao.ZjcAdDao.list1", qDto);
		if(goodslist.size()==0){
			MessageVO.setCode(Apiconstant.query_Is_Null.getIndex());
			MessageVO.setMsg(Apiconstant.query_Is_Null.getName());
		}else{
			MessageVO.setData(goodslist);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 获取系统公告
	 * @param request
	 * @param response
	 * @return
	 */
	public String  getMessage(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		List<ZjcAdPO> goodslist = sqlDao.list("com.zjc.users.dao.ZjcMessageDao.list", qDto);
		if(goodslist.size()==0){
			MessageVO.setCode(Apiconstant.query_Is_Null.getIndex());
			MessageVO.setMsg(Apiconstant.query_Is_Null.getName());
		}else{
			MessageVO.setData(goodslist);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(MessageVO);
	}
	
	
	/**
	 * 获取新闻资讯
	 * @param request
	 * @param response
	 * @return
	 */
	public String  getArticle(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		List<ZjcAdPO> goodslist = sqlDao.list("com.zjc.article.dao.ZjcArticleDao.list", qDto);
		if(goodslist.size()==0){
			MessageVO.setCode(Apiconstant.query_Is_Null.getIndex());
			MessageVO.setMsg(Apiconstant.query_Is_Null.getName());
		}else{
			MessageVO.setData(goodslist);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 我的订单列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String myOrderList(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.get("page")) || AOSUtils.isEmpty(qDto.get("order_status"))){
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else{
			if(qDto.getInteger("page")<1){
				MessageVO.setCode(Apiconstant.Page_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.Page_Is_Null.getName());
			}else{
				//设置limit每页条数
				qDto.put("limit", ConstantUtil.pageSize);
				//设置start开始条数
				int page = qDto.getInteger("page");
				qDto.put("start", (page-1)*ConstantUtil.pageSize);
				qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
				List<ZjcOrderPO> zjcOrderList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.myOrderListPage", qDto);
				if(AOSUtils.isEmpty(zjcOrderList)){//数据为空
					MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
					MessageVO.setMsg(Apiconstant.NO_DATA.getName());
				} else {
					List<ZjcOrderPO> zjcNewOrderList = new ArrayList<ZjcOrderPO>();
					for(ZjcOrderPO zjcOrderPO :zjcOrderList){//遍历订单表，查询订单商品信息
						List<ZjcOrderGoodsPO> newOrderGoodsList = new ArrayList<ZjcOrderGoodsPO>();
						qDto.put("order_id", zjcOrderPO.getOrder_id());
						List<ZjcOrderGoodsPO> orderGoodsList = ZjcOrderGoodsDao.list(qDto);
						if(!AOSUtils.isEmpty(orderGoodsList)){
							for(ZjcOrderGoodsPO orderGoods :orderGoodsList){//遍历商品订单数据，获取商品详细数据
								ZjcOrderGoodsPO newordergoods = new ZjcOrderGoodsPO();
								ZjcGoodsPO newgoodsPO = new ZjcGoodsPO();
								ZjcGoodsPO goodsPO = ZjcGoodsDao.selectByKey(orderGoods.getGoods_id());
								if(!AOSUtils.isEmpty(goodsPO)){
									newgoodsPO.setOriginal_img(goodsPO.getOriginal_img());
									newgoodsPO.setIs_vip(goodsPO.getIs_vip());
									newgoodsPO.setPrompt_goods(goodsPO.getPrompt_goods());
									newordergoods.setZjcGoodsPO(newgoodsPO);
								}
								//ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.selectByKey(orderGoods.getDelivery_id());
								ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.getZjcDeliveryByOrderId(zjcOrderPO.getOrder_id());
								ZjcDeliveryDocPO newDelivery = new ZjcDeliveryDocPO();
								if(!AOSUtils.isEmpty(zjcDeliveryDocPO)){//设置物流信息
									newDelivery.setId(zjcDeliveryDocPO.getId());
									newDelivery.setShipping_name(zjcDeliveryDocPO.getShipping_name());
									newDelivery.setInvoice_no(zjcDeliveryDocPO.getInvoice_no());
									newordergoods.setZjcDeliveryDocPO(newDelivery);
								}
								//拼装页面展示商品订单数据
								newordergoods.setGoods_id(orderGoods.getGoods_id());
								newordergoods.setOrder_id(orderGoods.getOrder_id());
								newordergoods.setGoods_name(orderGoods.getGoods_name());
								newordergoods.setGoods_num(orderGoods.getGoods_num());
								newordergoods.setSpec_key(orderGoods.getSpec_key());
								newordergoods.setSpec_key_name(orderGoods.getSpec_key_name());
								newordergoods.setGoods_price(orderGoods.getGoods_price());
								newordergoods.setCost_price(orderGoods.getCost_price());
								newordergoods.setMarket_price(orderGoods.getMarket_price());
								newOrderGoodsList.add(newordergoods);
							}
						}
						zjcOrderPO.setZjcOrderGoodsPO(newOrderGoodsList);
						zjcOrderPO.setArea_info("");
						zjcNewOrderList.add(zjcOrderPO);
					}
					//生成返回分页参数实体
					PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
					pageVO.setList(zjcNewOrderList);
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Success.getName());
					MessageVO.setData(pageVO);
				}
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 订单详情
	 * @param request
	 * @param response
	 * @return
	 */
	public String order_Info(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(qDto.getString("order_id")==null || qDto.getString("order_id")==""){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
		}else{
			List<ZjcOrderGoodsPO> params = sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.getOrderGoodsByOrderId", qDto);
			MessageVO.setMsg(Apiconstant.Do_Success.getName());//
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());//
			MessageVO.setData(params);
		}
		return AOSJson.toJson(MessageVO);
	}
	
	
	/**
	 * 删除订单
	 * @param request
	 * @param response
	 * @return
	 */
	public String orderDel(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("order_id"))){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		List<ZjcOrderPO> params = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.selectByKey", qDto);
		try {
			if("mixed_payment".equals(params.get(0).getPay_code())){
				if(params.get(0).getPoints_pay_status() == 1){//混合支付已付券不能取消
					MessageVO.setMsg(Apiconstant.Order_Not_Cancle.getName());
					MessageVO.setCode(Apiconstant.Order_Not_Cancle.getIndex());
					return AOSJson.toJson(MessageVO);
				}
			}
			if(params.get(0).getOrder_status()==0){
				sqlDao.update("com.zjc.order.dao.ZjcOrderDao.updateByKeyAndOrderStatus",qDto);
				//查询
				ZjcVouchersPO voucher = zjcVouchersDao.selectOne(Dtos.newDto("order_id", params.get(0).getOrder_sn()));
				if(AOSUtils.isNotEmpty(voucher)){//如果使用了代金券，取消订单则改变代金券状态为可用
					voucher.setOrder_id(null);
					voucher.setIs_use(0);
					voucher.setUse_time(null);
					zjcVouchersDao.updateByKey(voucher);
				}
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			}else{
				MessageVO.setMsg(Apiconstant.Order_UnDel.getName());
				MessageVO.setCode(Apiconstant.Order_UnDel.getIndex());
			}
		} catch (Exception e) {
			MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
		}
		
		return AOSJson.toJson(MessageVO);
	}
	
	
	
	

	/**
	 * 购买积分
	 * @param request
	 * @param response
	 * @return
	 */
	public String buyPoints(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		List<ZjcOrderPO> params = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.selectByKey", qDto);
		try {
			if(params.get(0).getOrder_status()==3){
				sqlDao.delete("com.zjc.order.dao.ZjcOrderDao.deleteByKeyAndOrderStatus",qDto);
			}else{
				MessageVO.setMsg(Apiconstant.Order_UnDel.getName());
				MessageVO.setCode(Apiconstant.Order_UnDel.getIndex());
			}
		} catch (Exception e) {
			MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
		}
		
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 结算中心计算
	 * @param request
	 * @param response
	 * @return
	 */
	public String settleCenter(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcUsersAccountInfoPO usersAccount = ZjcUsersAccountInfoDao.selectByKey(qDto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(qDto.getString("center_id"))||AOSUtils.isEmpty(qDto.getString("center_name").toString().trim())
				||AOSUtils.isEmpty(qDto.getString("points"))){//参数不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		} 
		if("1".equals(usersAccount.getIs_lock())) {//账户已被冻结
			msgVO.setCode(Apiconstant.Account_Is_Lock.getIndex());
			msgVO.setMsg(Apiconstant.Account_Is_Lock.getName());
			return AOSJson.toJson(msgVO);
		}
		
		//查询结算对象信息
		qDto.put("user_id", qDto.getString("center_id").trim());
		List<ZjcUsersInfoPO> settleCenterList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.querySettleInfo", qDto);
		if(AOSUtils.isEmpty(settleCenterList)){//结算中心不存在
			msgVO.setCode(Apiconstant.Settlement_Center_Not_Exist.getIndex());
			msgVO.setMsg(Apiconstant.Settlement_Center_Not_Exist.getName());
		} else if(settleCenterList.get(0).getZjcUsersAccountInfoPO().getSettlement_center()== 0){//未开通结算中心
			msgVO.setCode(Apiconstant.Settlement_center.getIndex());
			msgVO.setMsg(Apiconstant.Settlement_center.getName());
		} else if(settleCenterList.get(0).getIs_lock() == 1){//结算中心拥有者被冻结
			msgVO.setCode(Apiconstant.Settle_Owner_Is_Lock.getIndex());
			msgVO.setMsg(Apiconstant.Settle_Owner_Is_Lock.getName());
		} else if(!qDto.getString("center_name").equals(settleCenterList.get(0).getReal_name())){//结算中心真实姓名不一致
			msgVO.setCode(Apiconstant.Settlement_Center_Name_Not_Equal.getIndex());
			msgVO.setMsg(Apiconstant.Settlement_Center_Name_Not_Equal.getName());
		} else {//计算结算中心
			//用户账户信息
			if(usersAccount.getPay_points() < qDto.getInteger("points")){//可转易物券不足
				msgVO.setCode(Apiconstant.KZ_Money_Not_Enough.getIndex());
				msgVO.setMsg(Apiconstant.KZ_Money_Not_Enough.getName());
				return AOSJson.toJson(msgVO);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			//获取倍增倍数配置信息
			List<ZjcMemberMultiplicationPO> bzParameterList = sqlDao.list("com.zjc.system.dao.ZjcMemberMultiplicationDao.selectByMaxKey",null);
			ZjcMemberMultiplicationPO bzParameter = (ZjcMemberMultiplicationPO)bzParameterList.get(0);
			//ZjcMemberWalletPO walletPO = (ZjcMemberWalletPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberWalletDao.selectByMaxKey", null);
			//BigDecimal w =  new BigDecimal(walletPO.getWallet_amplification_ratio());
			//倍增倍数
			BigDecimal rate = new BigDecimal(bzParameter.getDefault_multiplier());
			if(qDto.getString("center_id").toString().equals(bzParameter.getSpecial_merchants_multiplication_id())){//如果是特殊商家的话
				rate = new BigDecimal(bzParameter.getSpecial_merchants_multiplication());
			} else {
				rate = apiPublicService.user_level_rate(qDto.getBigInteger("center_id"));
			}
			//获取易物劵结算值
			int points=qDto.getInteger("points");
			//计算客户支付值
			int user_pay=points;
			map.put("user_pay",user_pay);
			//用户钱包增加值
			//int user_bag = (new BigDecimal(user_pay).multiply(new BigDecimal(1).divide(rate)).multiply(w)).intValue();;
			map.put("user_bag", 0);
			//实体店收入
			int center_income=user_pay - (new BigDecimal(user_pay).multiply(new BigDecimal(1).divide(rate))).intValue();;
			map.put("center_income", center_income);
			//结算补贴
			//Integer leader_income=apiPublicService.plain_commission(user_id, new BigDecimal(user_pay).multiply(new BigDecimal(1).divide(rate)).intValue(), 1);
			map.put("leader_income", 0);
			msgVO.setData(map);
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(msgVO);
	}	
	
	/**
	 * 倍增订单提交
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public String confirmSettle(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel = new HttpModel(request, response);
		
		Dto qDto = httpModel.getInDto();
		boolean b = true;
		if(b){
			msgVO.setMsg("很抱歉，该功能暂时关闭！");
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		if(AOSUtils.isEmpty(qDto.getString("center_id"))||AOSUtils.isEmpty(qDto.getString("center_name"))||AOSUtils.isEmpty(qDto.getString("points"))
				||AOSUtils.isEmpty(qDto.getString("random"))||AOSUtils.isEmpty(qDto.getString("sign"))){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(msgVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(msgVO);
		}
		qDto.put("user_id", httpModel.getAttribute("user_id"));
		if(qDto.getString("center_id").equals(qDto.getString("user_id"))){//不能给本身做倍增
			msgVO.setCode(Apiconstant.User_NOT_MYSELF.getIndex());
			msgVO.setMsg(Apiconstant.User_NOT_MYSELF.getName());
			return AOSJson.toJson(msgVO);
		}
		ZjcUsersInfoPO userInfo = ZjcUsersInfoDao.selectByKey(qDto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(userInfo)){//用户不存在
			msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
		}else {//校验支付密码
			msgVO = apiPublicService.isSettleAccount(qDto.getBigInteger("user_id"),qDto.getInteger("points"));
			if(msgVO.getCode() != 1){//判断当日倍增结算额度是否超出限制
				return AOSJson.toJson(msgVO);
			}
			if(ValidateUtil.checkPsd(userInfo, qDto.getString("random"), qDto.getString("sign"), ConstantUtil.PAY_PSD_TYPE)){//支付密码正确，生成倍增订单
				try {
					Map<String,Object> map = apiPublicService.calculateSettle(qDto.getInteger("points"),qDto.getBigInteger("user_id"),qDto.getBigInteger("center_id"));
					//用户账户信息
					ZjcUsersAccountInfoPO usersAccount = ZjcUsersAccountInfoDao.selectByKey(qDto.getBigInteger("user_id"));
					//记录用户本身账户原可转积分
					int oldUserKY = usersAccount.getPay_points();
					if(oldUserKY < qDto.getInteger("points")){//可转易物券不足
						msgVO.setCode(Apiconstant.KZ_Money_Not_Enough.getIndex());
						msgVO.setMsg(Apiconstant.KZ_Money_Not_Enough.getName());
						return AOSJson.toJson(msgVO);
					}
					//记录用户本身账户原累计消费额度
					int oldTotalAmount = Integer.parseInt(usersAccount.getTotal_amount());
					//记录用户本身账户原钱包额度
					int old_wallet_quota = Integer.valueOf(usersAccount.getWallet_quota());
					usersAccount.setPay_points(oldUserKY-qDto.getInteger("points"));//减少可用积分
					//查找钱包扩容比例
					List<ZjcMemberWalletPO> walletPO = sqlDao.list("com.zjc.system.dao.ZjcMemberWalletDao.selectByMaxKey",null);
					BigDecimal walletRate = new BigDecimal(walletPO.get(0).getWallet_amplification_ratio());
					BigDecimal userbag = new BigDecimal(map.get("user_bag").toString());
					int walletAddNum = walletRate.multiply(userbag).intValue();
					usersAccount.setWallet_quota(old_wallet_quota + walletAddNum + "");//增加钱包额度
					usersAccount.setCount_wallet_quota(usersAccount.getCount_wallet_quota() + walletAddNum);//增加钱包总额度
					usersAccount.setTotal_amount(oldTotalAmount+Integer.valueOf(map.get("user_pay").toString()) + "");//增加累计消费
					//减少用户本身可用积分
					ZjcUsersAccountInfoDao.updateByKey(usersAccount);
					//调用存储过程,实时改变会员等级
					//sqlDao.call("level",Dtos.newDto("user_id", qDto.getBigInteger("user_id")));
					//结算中心拥有者账户信息
					ZjcUsersAccountInfoPO settleAccount = ZjcUsersAccountInfoDao.selectByKey(qDto.getBigInteger("center_id"));
					//记录结算中心账户原可转积分
					int oldSettleKZ = settleAccount.getMake_over_integral();
					//记录结算中心账户原累计赠送积分
					int oldduePoints = Integer.parseInt(settleAccount.getDue_tc_points());
					//实际提成
					int oldpraticalTcPoints = Integer.parseInt(settleAccount.getPractical_tc_points());
					//钱包额度
					int oldwallet = Integer.parseInt(settleAccount.getWallet_quota());
					int settleTC = 0;
					if(settleAccount.getSettlement_center_tc() == 1){//开通结算提成
						//查询结算提成比例
						List<ZjcMemberSettlementPO> settleParameter = sqlDao.list("com.zjc.system.dao.ZjcMemberSettlementDao.selectByMaxKey",null);
						int pay=Integer.parseInt(map.get("user_pay").toString())-Integer.parseInt(map.get("center_income").toString());
						settleTC = (new BigDecimal(pay)).multiply(new BigDecimal(settleParameter.get(0).getSettlement_center_commission())).intValue();
						//settleTC = (new BigDecimal(map.get("center_income").toString())).multiply(new BigDecimal(settleParameter.get(0).getSettlement_center_commission())).intValue();
					}
					int centerVal = 0;
					if(settleTC>oldwallet){
						centerVal = oldwallet;
					} else {
						centerVal = settleTC;
					}
					settleAccount.setMake_over_integral(oldSettleKZ + Integer.parseInt(map.get("center_income").toString()) +centerVal);//增加可转积分
					settleAccount.setWallet_quota((oldwallet-centerVal)+"");//减少钱包额度
					settleAccount.setDue_tc_points((oldduePoints+settleTC)+"");//增加累计赠送
					settleAccount.setPractical_tc_points((oldpraticalTcPoints+centerVal)+"");//增加实际提成
					//增加结算中心拥有者可转积分
					ZjcUsersAccountInfoDao.updateByKey(settleAccount);
					//生成结算提成日志
					Map<String,Object> dueMap = new HashMap<String,Object>();
					dueMap.put("logType", "结算提成");
					dueMap.put("user_id", qDto.getBigInteger("center_id"));
					dueMap.put("due_tc_points", settleTC);
					dueMap.put("now_make_over_integral", oldSettleKZ +centerVal);//当前结算中心拥有者可用积分
					dueMap.put("show_type", 1);
					apiLogService.saveLog(dueMap);
					ZjcBzOrderPO bzOrder = new ZjcBzOrderPO();
					bzOrder.setBz_id(idService.nextValue(SystemCons.SEQ.SEQ_ORDER).intValue());
					bzOrder.setBz_sn(ParameterUtil.getOrderSn());
					bzOrder.setBz_price(qDto.getBigDecimal("points").subtract(new BigDecimal(map.get("center_income").toString())));
					bzOrder.setSeller_id(qDto.getInteger("center_id"));
					bzOrder.setSeller_name(qDto.getString("center_name"));
					bzOrder.setUser_id(qDto.getBigInteger("user_id"));
					bzOrder.setUser_name(userInfo.getReal_name());
					bzOrder.setAdd_time(new Date());
					bzOrder.setBz_after_price(qDto.getBigDecimal("points"));
					zjcBzOrderDao.insert(bzOrder);
					//成功，生成倍增返还用户易物券记录
					Rebate rebate = apiPublicService.distribute_integral(qDto.getInteger("points"));
				    ZjcQueuePO queue = new ZjcQueuePO();
					queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					queue.setUser_id(qDto.getBigInteger("user_id"));
					queue.setRelation_id(bzOrder.getBz_id());
					queue.setXf_points(rebate.getXf());
					queue.setKz_points(rebate.getKz());
					queue.setType(1);
					queue.setAdd_time(new Date());
					queue.setSend_time(new Date());
					queue.setNote("易物券倍增返回给会员");
					ZjcQueueDao.insert(queue);
					//生成会员易物结算日志
					Map<String,Object> logMap = new HashMap<String,Object>();
					logMap.put("logType", "结算");
					logMap.put("type", "会员");
					logMap.put("user_id", qDto.getBigInteger("user_id"));
					logMap.put("to_user_id", qDto.getBigInteger("center_id"));
					logMap.put("pay_points", qDto.getString("points"));//会员结算的易物券
					logMap.put("due_tc_points", rebate.getXf()+rebate.getKz());
					logMap.put("now_points", oldUserKY-qDto.getInteger("points"));//会员可用积分
					logMap.put("show_type", 1);
					apiLogService.saveLog(logMap);
					//生成结算中心拥有者结算日志
					logMap.put("logType", "结算");
					logMap.put("type", "结算中心");
					logMap.put("user_id", qDto.getBigInteger("center_id"));
					logMap.put("to_user_id", qDto.getBigInteger("user_id"));
					logMap.put("make_over_integral", Integer.parseInt(map.get("center_income").toString()));
					logMap.put("mobile", userInfo.getMobile());
					logMap.put("real_name", userInfo.getReal_name());
					logMap.put("now_make_over_integral", oldSettleKZ + Integer.parseInt(map.get("center_income").toString())+settleTC);//商家可转积分
					apiLogService.saveLog(logMap);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch (Exception e) {
					msgVO.setCode(Apiconstant.Do_Fails.getIndex());
					msgVO.setMsg(Apiconstant.Do_Fails.getName());
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			} else {//支付密码错误
				msgVO.setCode(Apiconstant.Pay_Psd_Error.getIndex());
				msgVO.setMsg(Apiconstant.Pay_Psd_Error.getName());
			}
			
		}
		return AOSJson.toJson(msgVO);
	}
	
	/*
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("serial")
	@Transactional(rollbackFor=Exception.class)
	public String orderSub(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		MessageVO MessageVO=new MessageVO();
		Map<String,String> params=new HashMap<String,String>();
		HttpModel httpModel=new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
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
			order.setPay_name("混合支付易物券");
		}else if(order.getPay_type().equals("mixed_pay_drops")){
			order.setPay_name("混合支付滴");
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
		//判断是否开通结算中心
	    //List<ZjcUsersAccountInfoPO> uai = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    List<ZjcGoodsPO> ZjcGoods=new ArrayList<ZjcGoodsPO>();
	    XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", goods.get(0).getGoods_id()));
	    ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
	    /*if(AOSUtils.isEmpty(userInfo.getXpt())){//非小平台用户部分商品不能购买
			if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//小平台商品，不能购买
				MessageVO.setMsg(Apiconstant.Goods_Is_Xpt.getName());
				MessageVO.setCode(Apiconstant.Goods_Is_Xpt.getIndex());
		    	return AOSJson.toJson(MessageVO);
			}
		}*/
		
		if(AOSUtils.isNotEmpty(userInfo.getXpt())){//小平台用户部分商品不能购买
			if("普通会员".equals(userInfo.getLevel())){//未购买过特殊商品
				if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//小平台用户第一次购买特殊商品商品，改变会员状态
			    	userInfo.setLevel("合格会员");
			    	ZjcUsersInfoDao.updateByKey(userInfo);
				}
			}
		}
	    for (Goods goods2 : goods) {
	    	/*if(!"34529".equals(goods2.getGoods_id())){
	    		MessageVO.setMsg("很抱歉，该商品暂时不能购买");
				 MessageVO.setCode(Apiconstant.order_cannot_create.getIndex());
		    	 return AOSJson.toJson(MessageVO);
	    	}*/
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
	    	if(isgoodnum.getIs_vip() == 1){//如果是VIP商品
	    		if(userInfo.getIs_open_min_pay()!= 1){//如果不能购买vip商品
	    			int continueTimes= zjcTurnIntoUtil.getContinuousOrderDays(request.getAttribute("user_id").toString(), "3411419");
	    			if(continueTimes<6){//连续六天在“1号店”购买任意一款预售商品，才能购买本商品
		    			 MessageVO.setMsg(Apiconstant.Goods_Cannot_Buy.getName());
						 MessageVO.setCode(Apiconstant.Goods_Cannot_Buy.getIndex());
				    	 return AOSJson.toJson(MessageVO);
		    		}
	    			userInfo.setIs_open_min_pay(1);//满足连续购买6天，则可以购买vip商品
	    			ZjcUsersInfoDao.updateByKey(userInfo);
	    		}
	    		
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
				/*Map<String,Object> map = new HashMap<String,Object>();
				map.put("user_id", httpModel.getAttribute("user_id"));
				map.put("goods_id", goods2.getGoods_id());
				List<ZjcOrderPO> ordered =  sqlDao.list("com.zjc.order.dao.ZjcOrderDao.Promptlist",Dtos.newDto(map));
				if(ordered.size()>0){
					MessageVO.setMsg(Apiconstant.Have_to_buy.getName());
					MessageVO.setCode(Apiconstant.Have_to_buy.getIndex());
					return AOSJson.toJson(MessageVO);
				}*/
			}
			ZjcGoodsPO.setGoods_id(Integer.parseInt(goods2.getGoods_id()));
			List<ZjcGoodsPO> getgoodsuserid = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getgoodsuserid",ZjcGoodsPO);
			params.put("shop_price", getgoodsuserid.get(0).getGoods_remark());
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
	    BigDecimal discount = BigDecimal.ZERO;//折扣券
	    if(AOSUtils.isNotEmpty(dto.getString("discount"))){//不为空
	    	discount = dto.getBigDecimal("discount");
	    }
	    OrderVO result=zjc_calculate_price(order.getUser_id(),goods,order.getPay_type(),vouchernum,discount);
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
	    if(vouchernum > 0){//代金券使用成功
	   		voucher.setIs_use(1);//代金券状态设置为已使用
	   		voucher.setUse_time(new Date());//使用时间
	   		voucher.setOrder_id(list.get(0).getOrder_sn().toString());//使用订单号
	   		zjcVouchersDao.updateByKey(voucher);
	   	}
	    /*if(!"mixed_payment".equals(list.get(0).getPay_code())&&!"rate".equals(list.get(0).getPay_code())){//不是礼品兑换和热销预购商品，先生成会员返分记录
	    	 Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(list.get(0).getOrder_id());
			   if(Rebate.getPoints() > 0){//会员返分
				   zjcTurnIntoUtil.xf_expire_new(Rebate.getPoints(), list.get(0).getOrder_id());
			   }
	    }*/
	    
	    params.put("order_id", list.get(0).getOrder_id().toString());
	    params.put("order_sn", list.get(0).getOrder_sn().toString());
	    //zjcTurnIntoUtil.getAlsoDropst(list.get(0).getOrder_id());
	    //混合支付会员反分
	    /*if(list.get(0).getPay_code().equals("mixed_payment")||order.getPay_type().equals("cash")||order.getPay_type().equals("alipay")||order.getPay_type().equals("wxpay")){
	    	zjcTurnIntoUtil.getAlsoDrops(list.get(0).getOrder_id());
	    }*/
	    /*if(order.getPay_type().equals("cash")||order.getPay_type().equals("alipay")||order.getPay_type().equals("wxpay")){
	    	//存消息队列
	    	Sender Sender=new Sender();
	    	Sender.send(list.get(0).getOrder_id().toString(),list.get(0).getTotal_amount().toString(),list.get(0).getUser_id().toString());
	    }*/
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
	public OrderVO zjc_calculate_price(String user_id,List<Goods> ordergoods,String pay_type,int vouchernum,BigDecimal discount){
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
			Integer Drops = Goods.getDrops();
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
	            case "mixed_pay_drops":
	            	total_goods_price =total_goods_price.add(Market_price.multiply(Goods_num).add(new BigDecimal(Drops).multiply(Goods_num)));
	            	cash = cash.add(Shop_price.multiply(Goods_num));
	            	barter_coupons = barter_coupons.add(new BigDecimal(Drops).multiply(Goods_num));
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
		//int compCash = (cash.subtract(new BigDecimal(vouchernum))).compareTo(BigDecimal.ZERO);
		if(discount.compareTo(BigDecimal.ZERO)==0){//app端使用代金券
			int compCash = (cash.subtract(new BigDecimal(vouchernum))).compareTo(BigDecimal.ZERO);
			OrderVO.setCash(cash.subtract(new BigDecimal(vouchernum)));//支付现金
			if(compCash < 1 && vouchernum != 0){//如果支付现金小于代金券额度，则不能使用该代金券
				//OrderVO.setCash(BigDecimal.ZERO);//支付现金
				OrderVO.setStatus(-1);
				return OrderVO;
			}
		} else {//微信端使用折扣券
			OrderVO.setGoods_price(total_goods_price.multiply(discount));
			OrderVO.setTotal_amount(total_goods_price.multiply(discount));
			OrderVO.setOrder_amount(total_goods_price.multiply(discount));
			OrderVO.setTotal_goods_num(total_goods_price.multiply(discount));
			OrderVO.setCash(cash.multiply(discount));//支付现金
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
	        /*if(orderGood.get(0).getGoods_id().toString().equals("34529")){
				   
			   }*/
	       /* if("mixed_pay_drops".equals(order.getPay_type())){
	        	ZjcOrderPO.setDrops(drops);
	        }*/
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
	    	 /*  odergoods.setMarket_price_bs(Float.valueOf(goods.getMarket_price_bs()));
	    	   odergoods.setShop_price_bs(Float.valueOf(goods.getShop_price_bs()));
	    	   odergoods.setStore_rebate_rate(Float.valueOf(goods.getStore_rebate_rate()));
	    	   odergoods.setGoods_content(goods.getGoods_content());
	    	   odergoods.setGoods_weight(goods.getGoods_weight());
	    	   odergoods.setGoods_id(goods.getGoods_id());
	    	   odergoods.setGoods_name(goods.getGoods_name());
	    	   odergoods.setGoods_sn(goods.getGoods_sn());
	    	   odergoods.setMarket_price(goods.getMarket_price());
	    	   odergoods.setIs_free_shipping(goods.getIs_free_shipping());
	    	   odergoods.setContract_phone(goods.getContract_phone());
	    	   odergoods.setPostage(goods.getPostage());
	    	   odergoods.setExpect_delivery(goods.getExpect_delivery());
	    	   odergoods.setTs(goods.getTs());
	    	   odergoods.setRemark(goods.getRemark());
	    	   odergoods.setToday_limit_times(goods.getToday_limit_times());
	    	   odergoods.setToday_limit_nums(goods.getToday_limit_nums());*/
	    	   odergoods.setGoods_price(goods.getCost_price());
	    	   odergoods.setCost_price(goods.getShop_price());
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
	 * 订单支付
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	@SuppressWarnings("unused")
	public String order_pay(HttpServletRequest request, HttpServletResponse response){
		    MessageVO MessageVO=new MessageVO();
		    HttpModel httpModel=new HttpModel(request, response);
		    Dto qDto=httpModel.getInDto();
		    if(AOSUtils.isEmpty(qDto.getString("orderId")) || AOSUtils.isEmpty(qDto.getString("random")) || AOSUtils.isEmpty(qDto.getString("sign"))){
		    	MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		    	MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				return AOSJson.toJson(MessageVO);
			}
		    MessageVO = apiPublicService.isLockedHandle();
			if(MessageVO.getCode() != 1){//限制所有会员操作
				return AOSJson.toJson(MessageVO);
			}
			MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
			if(MessageVO.getCode() != 1){//会员账户被冻结
				return AOSJson.toJson(MessageVO);
			}
			String order_id=request.getParameter("orderId");
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			ZjcOrderPO ZjcOrder=new ZjcOrderPO();
			ZjcOrder.setOrder_id(Integer.valueOf(order_id.trim()).intValue());
			List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
			if(list.get(0).getPay_status()==1){//订单券已支付
				MessageVO.setMsg(Apiconstant.Order_Has_Payed.getName());
				MessageVO.setCode(Apiconstant.Order_Has_Payed.getIndex());
				return AOSJson.toJson(MessageVO);
			}
			ZjcUsersInfoPO.setUser_id(list.get(0).getUser_id());
			List<ZjcUsersInfoPO> ZjcUsersInfos=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",ZjcUsersInfoPO);
			ZjcUsersInfoPO ZjcUsersInfo=ZjcUsersInfos.get(0);
			String random=request.getParameter("random");
			String design=request.getParameter("sign");
			String psd_type=ConstantUtil.PAY_PSD_TYPE;
			boolean password=ValidateUtil.checkPsd(ZjcUsersInfo, random, design, psd_type);
			if(password==false){
				  MessageVO.setMsg(Apiconstant.Password_Wrong.getName());
				  MessageVO.setCode(Apiconstant.Password_Wrong.getIndex());
				  return AOSJson.toJson(MessageVO);
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
    					MessageVO.setMsg(Apiconstant.Commodities_have_shelves.getName());
    					MessageVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
    			    	return AOSJson.toJson(MessageVO);
    				}
    	    	   if(goodsd.get(0).getStore_count()<orderGood.get(0).getGoods_num()){//库存不足
			    		MessageVO.setMsg(Apiconstant.Insufficient_inventory.getName());
			    		MessageVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
			    		return AOSJson.toJson(MessageVO);
			       }
    	    	   if(goodsd.get(0).getGoods_state_1() != 3 || goodsd.get(0).getGoods_state_2() != 3){//该商品未审核或者审核不通过
		    		  MessageVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
		    		  MessageVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
		    		  return AOSJson.toJson(MessageVO);
		    	   }
    	    	   ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
    	    	   Map<String,Object> map = new HashMap<String,Object>();
    			   map.put("user_id", httpModel.getAttribute("user_id"));
    			   map.put("goods_id", orderGood.get(0).getGoods_id());
    			   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
    			   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
    				  MessageVO.setMsg("很抱歉！该商品每日每人只能购买" + isgoodnum.getToday_limit_times()+"次，请明天再来");
    				  MessageVO.setCode(Apiconstant.goods_times_enough.getIndex());
    			      return AOSJson.toJson(MessageVO);
    			   }
    	    	   
	    		   ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
	    		   ZjcUsersAccountInfoPO.setUser_id(list.get(0).getUser_id());
	    		   // 4 扣除积分
	    		   List<ZjcUsersAccountInfoPO> users=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
	    		   if(list.get(0).getPay_code().equals("mixed_pay_drops")){
	    			   if(list.get(0).getBarter_coupons().compareTo(new BigDecimal(users.get(0).getDrops()))==1){//判断余额是否充足
		    		    	MessageVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
		    		    	MessageVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
		    		    	return AOSJson.toJson(MessageVO);
		    		   }
	    		   }else if(list.get(0).getBarter_coupons().compareTo(new BigDecimal(users.get(0).getPay_points()))==1){//判断余额是否充足
	    		    	MessageVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
	    		    	MessageVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
	    		    	return AOSJson.toJson(MessageVO);
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
			    	   return AOSJson.toJson(MessageVO);
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
		       /*if(list.get(0).getPay_status()==1){
	               //下单时商家会员返利 对于特殊商品 * 
				   //zjcTurnIntoUtil.deal_goods_ends(list.get(0).getOrder_id());
				   //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
				   //Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission(list.get(0).getOrder_id(),0);
				   Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(Integer.parseInt(order_id));
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
			return AOSJson.toJson(MessageVO);
	}
	

	

	/**
	 * app接口-确认收货
	 * 
	 * @param httpModel
	 * @return
	 */
	public String confirm_order(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("order_id"))){//查询条件order_id为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(dto.getInteger("order_id"));
			if(AOSUtils.isEmpty(zjcOrderPO)){//订单数据不存在
				msgVO.setCode(Apiconstant.Order_NO_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Order_NO_Exist.getName());
			} else if(zjcOrderPO.getOrder_status() != 1){//该订单不能收货确认
				msgVO.setCode(Apiconstant.Order_NO_Confirm.getIndex());
				msgVO.setMsg(Apiconstant.Order_NO_Confirm.getName());
			} else {//确认收货
				zjcOrderPO.setOrder_status(2);//已收货
				zjcOrderPO.setConfirm_time(new Date());
				try{
					zjcOrderDao.updateByKey(zjcOrderPO);
					Dto pDto = Dtos.newDto();
					pDto.put("type", 6);
					pDto.put("relation_id", zjcOrderPO.getOrder_id());
					ZjcQueuePO queuePO = ZjcQueueDao.selectOne(pDto);
					if("rate".equals(zjcOrderPO.getPay_code()) && AOSUtils.isEmpty(queuePO)){//礼品兑换时，同时该订单没有生成商家返分,确认收货，商家返利
						points(dto.getInteger("order_id"));
					}
					//上级会员提成
					//higher_commission(zjcOrderPO);
					//商家返分 
					/*boolean is_car = true;
					List<ZjcOrderGoodsPO> ordergoods = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
					for(ZjcOrderGoodsPO orderGood :ordergoods){
						ZjcGoodsPO goods = ZjcGoodsDao.selectByKey(orderGood.getGoods_id());
						if(goods.getIs_car()==1){
							is_car = false;
						}
					}
					Dto pDto = Dtos.newDto();
					pDto.put("type", 6);
					pDto.put("relation_id", zjcOrderPO.getOrder_id());
					ZjcQueuePO queuePO = ZjcQueueDao.selectOne(pDto);
					if(is_car && AOSUtils.isEmpty(queuePO)){//是普通商品，同时该商品没有生成商家返分，进行商家返分，否则不返分
						points(dto.getInteger("order_id"));
					}*/
					//降序查询订单状态列表数据
					List<ZjcOrderActionPO> orderActionList =  sqlDao.list("com.zjc.order.dao.ZjcOrderActionDao.listDataDesc", dto);
					dto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
					ZjcOrderActionPO orderAction = orderActionList.get(0);
					orderAction.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					orderAction.setAction_user(dto.getBigInteger("user_id"));//操作人
					orderAction.setAction_user_type(1);//用户
					orderAction.setAction_user_name(orderAction.getAction_user_name());
					orderAction.setLog_time(new Date());
					orderAction.setAction_note(ConstantUtil.CONFIRM_ORDER_NOTE);
					orderAction.setStatus_desc(ConstantUtil.CONFIRM_ORDER_DESC);
					//新增订单状态
					zjcOrderActionDao.insert(orderAction);
					//zjcTurnIntoUtil.getAlsoDropst(zjcOrderPO.getOrder_id());
					int total=0;
					if(zjcOrderPO.getPay_code().equals("mixed_pay_drops")){
						 total=new BigDecimal("50").divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
					}else if(zjcOrderPO.getPay_code().equals("cash")||zjcOrderPO.getPay_code().equals("wxpay")||zjcOrderPO.getPay_code().equals("alipay")){
						 total=zjcOrderPO.getCash().intValue();
					}else {
						total=new BigDecimal("5").divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
					}
					 ZjcQueuePO queue=new ZjcQueuePO();
					 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 Date date= df.parse("2033-5-18 11:33:19");
					 //设置周期后自动返分
				     Date newdate=zjcOrderPO.getAdd_time();  
				     Calendar calendar = Calendar.getInstance();  
				     calendar.setTime(newdate);  
				     calendar.add(Calendar.DAY_OF_MONTH, +0);  
				     date = calendar.getTime();  
					 queue.setAdd_time(df.parse(df.format(date)));
					 queue.setSend_time(new Date());
					 queue.setNote("消费后，将易货券返回会员");
					 queue.setType(4);
					 queue.setUser_id(new BigInteger(httpModel.getRequest().getAttribute("user_id").toString()));
					 queue.setRelation_id(zjcOrderPO.getOrder_id());
					 queue.setXf_points(total);
					 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					 ZjcQueueDao.insert(queue);
					 //给商家自动转分
					 if(zjcOrderPO.getPay_code().equals("mixed_pay_drops")){
						 total=zjcOrderPO.getBarter_coupons().intValue();
					}else if(zjcOrderPO.getPay_code().equals("cash")||zjcOrderPO.getPay_code().equals("wxpay")||zjcOrderPO.getPay_code().equals("alipay")){
						 total=zjcOrderPO.getCash().intValue();
					}
				     Calendar calendars = Calendar.getInstance();  
				     calendars.setTime(newdate);  
				     calendars.add(Calendar.DAY_OF_MONTH, +7);  
				     date = calendars.getTime();
				     queue.setAdd_time(new Date());
					 queue.setSend_time(df.parse(df.format(date)));
					 queue.setNote("消费后，将易货券返回商家");
					 queue.setType(4);
					 queue.setUser_id(new BigInteger(httpModel.getRequest().getAttribute("user_id").toString()));
					 queue.setRelation_id(zjcOrderPO.getOrder_id());
					 queue.setXf_points(total);
					 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					 ZjcQueueDao.insert(queue);
					 msgVO.setCode(Apiconstant.Do_Success.getIndex());
					 msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-确认收货
	 * 
	 * @param httpModel
	 * @return
	 */
	public String recharge_order_list(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getInteger("page")) || AOSUtils.isEmpty(dto.getInteger("status"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else{
			if(dto.getInteger("page")<1){
				msgVO.setCode(Apiconstant.Page_Is_Null.getIndex());
				msgVO.setMsg(Apiconstant.Page_Is_Null.getName());
			}else{
				//设置limit每页条数
				dto.put("limit", ConstantUtil.pageSize);
				//设置start开始条数
				int page = dto.getInteger("page");
				dto.put("start", (page-1)*ConstantUtil.pageSize);
				List<ZjcRechargePO> rechargePOs=rechargeDao.getRechargeByStatusPage(dto);
				if(AOSUtils.isEmpty(rechargePOs)){
					msgVO.setCode(Apiconstant.NO_DATA.getIndex());
					msgVO.setMsg(Apiconstant.NO_DATA.getName());
				}else{
					//生成返回分页参数实体
					PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
					pageVO.setList(rechargePOs);
					msgVO.setData(pageVO);
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-充值积分需要的现金
	 * 
	 * @param httpModel
	 * @return
	 */
	public String check_buy_points(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("buy_points")) || AOSUtils.isEmpty(dto.getString("token"))){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		try{
			String out=zjcOrderDao.check_buy_points(dto);
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setData(out);
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}catch (Exception e){
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-检查用户今天还能充值多少积分
	 * 
	 * @param httpModel
	 * @return
	 */
	public String check_user_points(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("token"))){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		ZjcMemberParameterPO paramater = (ZjcMemberParameterPO)sqlDao.selectOne("com.zjc.system.dao.ZjcMemberParameterDao.selectByMax", null);
		dto.put("sum_points_today", paramater.getSum_points_today());
		dto.put("user_id", httpModel.getAttribute("user_id"));
		try{
			Integer out=zjcOrderDao.check_user_points(dto);
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setData(out);
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}catch (Exception e){
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVO);
	}
	
	
	
	
	public List<ZjcStorePO>  get_userid_by_storeid(int store_id)
	{
		ZjcStorePO store=new ZjcStorePO();
		store.setStore_id(store_id);
		List<ZjcStorePO> stores=sqlDao.list("com.zjc.store.dao.ZjcStoreDao.queryStore",store);;
	    return stores;
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @param response
	 * @return String
	 */
	/*public String cancel_order(HttpServletRequest request, HttpServletResponse response){
		    MessageVO MessageVO=new MessageVO();
			HttpModel httpModel=new HttpModel(request, response);
			Dto qDto=httpModel.getInDto();
			String order_id=qDto.getString("order_id");
			if(AOSUtils.isEmpty(order_id) || AOSUtils.isEmpty(qDto.getString("sign"))){
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				return AOSJson.toJson(MessageVO);
			}
			ZjcOrderPO ZjcOrder=new ZjcOrderPO();
			ZjcOrder.setOrder_id(Integer.valueOf(order_id.trim()).intValue());
			List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
			ZjcOrderGoodsPO ZjcOrderGoodsPO=new ZjcOrderGoodsPO();
			ZjcOrderGoodsPO.setOrder_id(list.get(0).getOrder_id());
			 //获取当前订单的商品ID
			List<ZjcOrderGoodsPO> ordergoods=sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.getOrderGoodsByOrderId",ZjcOrderGoodsPO);
			String new_pay_type="cash";//默认为现金支付
			if("rate".equals(list.get(0).getPay_code()) || "equal".equals(list.get(0).getPay_code())){
	            new_pay_type = "points"; //积分消费
	        }
			for (int i = 0; i < ordergoods.size(); i++) {
				 ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
		   	     zjcGoodsPO.setGoods_id(ordergoods.get(i-1).getGoods_id());
		   	     int Goods_nums=ordergoods.get(i-1).getGoods_num();
		   	     List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
		   	  try {
	 	    	   if(new_pay_type == "points"){
	 	    		   //增加库存
	     	    	   int store_count=goodsd.get(0).getStore_count()+Goods_nums;
	     	    	   //减少销量
	     	    	   int sales_sum=goodsd.get(0).getSales_sum()-Goods_nums;
	     	    	   zjcGoodsPO.setStore_count(store_count);
	     	    	   zjcGoodsPO.setSales_sum(sales_sum);
	 	    		   ZjcGoodsDao.updateByKey(zjcGoodsPO);
	 	    	   }
			} catch (Exception e) {
				   e.printStackTrace();
				   MessageVO.setMsg(Apiconstant.cancel_order_failure.getName());
			}
		}
		 MessageVO.setMsg(Apiconstant.cancel_order_success.getName());
		return  AOSJson.toJson(MessageVO);
	}*/
	

	/**
	 * 向商家付款
	 * $shop_mobile   商家电话
	 * $shop_mobile    付款会员电话
	 * $amount          付款金额
	 * * */
	@Transactional(rollbackFor=Exception.class)
	public String userScanPay(HttpServletRequest request, HttpServletResponse response){
		 MessageVO MessageVO=new MessageVO();
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto dto = httpModel.getInDto();
		 if(AOSUtils.isEmpty(dto.getString("sellerId")) || AOSUtils.isEmpty(dto.getString("point"))
				 || AOSUtils.isEmpty(dto.getString("design"))
				 || AOSUtils.isEmpty(dto.getString("pay_code"))
				 || AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
			 MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			 MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			 return AOSJson.toJson(MessageVO);
		}
		 //验证支付码是否已使用
		 Dto payDto = Dtos.newDto();
		 payDto.put("user_id", httpModel.getAttribute("user_id"));
		 payDto.put("pay_code", dto.getString("pay_code"));
		 ZjcPaymentSallerPO paymentSaller = (ZjcPaymentSallerPO) sqlDao.selectOne("com.api.order.dao.ZjcPaymentSallerDao.selectOne", payDto);
		 if(AOSUtils.isEmpty(paymentSaller)){//支付码不存在
			 MessageVO.setMsg(Apiconstant.Pay_Code_Is_Wrong.getName());
			 MessageVO.setCode(Apiconstant.Pay_Code_Is_Wrong.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 if(paymentSaller.getUsed()==1){//支付码已使用
			 MessageVO.setMsg(Apiconstant.Pay_Code_Is_Used.getName());
			 MessageVO.setCode(Apiconstant.Pay_Code_Is_Used.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 MessageVO = apiPublicService.isLockedHandle();
		 if(MessageVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(MessageVO);
		 }
		 MessageVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		 if(MessageVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(MessageVO);
		 }
		 dto.put("user_id", httpModel.getAttribute("user_id"));
		 //String user_mobile=request.getParameter("user_mobile");
		 //String shop_mobile=request.getParameter("shop_mobile");
		 String amount=request.getParameter("point");
		 //会员信息
		 ZjcUsersAccountInfoPO usersAccountInfo=ZjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
		 if(AOSUtils.isEmpty(usersAccountInfo)){//账户不存在
			 MessageVO.setMsg(Apiconstant.Username_Not_Exist.getName());
			 MessageVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			 return AOSJson.toJson(MessageVO);
		 } else if(usersAccountInfo.getPay_points()<Integer.parseInt(amount)){
			 MessageVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
			 MessageVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
			 return AOSJson.toJson(MessageVO);
		 }
		 MessageVO = apiPublicService.isLineAccount(dto.getBigInteger("user_id"),dto.getInteger("point"));
		 if(MessageVO.getCode() != 1){//判断当日转出额度是否超出限制
			 return AOSJson.toJson(MessageVO);
		 }
		 //通过电话查询会员id
		 ZjcUsersInfoPO userinfo2=new ZjcUsersInfoPO();
		 //userinfo2.setMobile(user_mobile);
		 userinfo2.setUser_id(dto.getBigInteger("user_id"));
		 List<ZjcUsersInfoPO> zjcuser=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",userinfo2);
		 BigInteger user_id=zjcuser.get(0).getUser_id();
		 //通过电话查询商家id
		 ZjcSellerInfoPO ZjcSellerInfoPO=new ZjcSellerInfoPO();
		 //ZjcSellerInfoPO.setMobile(shop_mobile);
		 ZjcSellerInfoPO.setUser_id(dto.getBigInteger("sellerId"));
		 List<ZjcSellerInfoPO> SellerInfo=sqlDao.list("com.zjc.store.dao.ZjcSellerInfoDao.selectByUseriInfoPO",ZjcSellerInfoPO);
		 BigInteger seller_user_id=SellerInfo.get(0).getUser_id();
		 ZjcUsersInfoPO userinfo=new ZjcUsersInfoPO();
		 userinfo.setUser_id(user_id);
		 List<ZjcUsersInfoPO> zjcuserinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUseriInfoPO",userinfo);
		 //商家信息
		 ZjcUsersAccountInfoPO user1=new ZjcUsersAccountInfoPO();
		 user1.setUser_id(seller_user_id);
		 List<ZjcUsersAccountInfoPO> UsersAccountInfo1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user1);
		 //商家是否被限制
		 List<ZjcMemberParameterPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMax",null);
		 //商家提成比例
		 List<ZjcMemberMultiplicationPO> MemberMultiplication = sqlDao.list("com.zjc.system.dao.ZjcMemberMultiplicationDao.selectByMaxKey",null);
		 if(!AOSUtils.isEmpty(paramDtos.get(0).getMenber_spending())&&paramDtos.get(0).getMenber_spending().indexOf(String.valueOf(user_id)) !=-1){
			 MessageVO.setMsg(Apiconstant.Members_close.getName());
		 }
		 //计算商店提成积分
		 int point_saller=(new BigDecimal(amount).multiply(new BigDecimal(MemberMultiplication.get(0).getOffline_shopping_commission_ratio()))).intValue();
		//生成转账订单
		 try{
			 //ZjcExchangeOrderDao.insert(changorder);
			 //减少会员的易物卷
			 int pay_points= usersAccountInfo.getPay_points()-Integer.parseInt(amount);
			 int total_amount=Integer.parseInt(usersAccountInfo.getTotal_amount())+Integer.parseInt(amount);
			 usersAccountInfo.setTotal_amount(String.valueOf(total_amount));
			 usersAccountInfo.setPay_points(pay_points);
			 
			 ZjcUsersAccountInfoDao.updateByKey(usersAccountInfo);
			 //调用存储过程,实时改变会员等级
			 //sqlDao.call("level",Dtos.newDto("user_id", user_id));
			 //添加商家的易物卷（商家返分）
			 int make_over_integral=UsersAccountInfo1.get(0).getMake_over_integral()+point_saller;
			 UsersAccountInfo1.get(0).setMake_over_integral(make_over_integral);
			 ZjcUsersAccountInfoDao.updateByKey(UsersAccountInfo1.get(0));
			//添加线下购物订单
			 ZjcXxOrderPO xxorder=new ZjcXxOrderPO();
			 xxorder.setOrder_sn(ParameterUtil.getOrderSn());
			 xxorder.setUser_id(user_id.intValue());
			 xxorder.setSeller_user_id(seller_user_id.intValue());
			 xxorder.setTotal_amount(new BigDecimal(amount));
			 xxorder.setAdd_time(new Date());
			 ZjcXxOrderDao.insert(xxorder);
			 
			 //修改支付码记录
			 paymentSaller.setUsed(1);//支付码置为已使用
			 paymentSaller.setEx_sn(xxorder.getOrder_sn());//设置支付订单号
			 sqlDao.update("com.api.order.dao.ZjcPaymentSallerDao.updateByKey", paymentSaller);
			 
			//生成操作流水
			ZjcIncomeFlowPO zjcIncomeFlowPO = new ZjcIncomeFlowPO();
			zjcIncomeFlowPO.setBalance(UsersAccountInfo1.get(0).getMake_over_integral());
			zjcIncomeFlowPO.setExchange_time(xxorder.getAdd_time());
			zjcIncomeFlowPO.setIn_user_id(UsersAccountInfo1.get(0).getUser_id().intValue());
			zjcIncomeFlowPO.setOut_user_id(dto.getInteger("user_id"));
			zjcIncomeFlowPO.setIncome(point_saller);
			zjcIncomeFlowPO.setType("2");//会员线下购物获得券
			zjcIncomeFlowPO.setOrder_sn(xxorder.getOrder_sn());
			zjcIncomeFlowDao.insert(zjcIncomeFlowPO);
			 
			//系统生成会员消费返回队列
			 ZjcQueuePO queue=new ZjcQueuePO();
			 //获取线下购物会员赠送券
			 int dueToPonts = new BigDecimal(amount).multiply(new BigDecimal(MemberMultiplication.get(0).getOffline_shopping_multiplication_rate())).intValue();
			 Rebate Rebate = apiPublicService.distribute_integral(dueToPonts);
			 queue.setNote("线下消费后，将易物券返回会员");
			 queue.setType(8);
			 queue.setAdd_time(new Date());
			 queue.setSend_time(new Date());
			 queue.setUser_id(user_id);
			 queue.setXf_points(Rebate.getXf());
			 queue.setKz_points(Rebate.getKz());
			 queue.setRelation_id(xxorder.getXx_id());
			 ZjcQueueDao.insert(queue);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logType", "线下购物");
			map.put("user_id", user_id);
			map.put("to_user_id", seller_user_id);
			map.put("pay_points", amount);
			map.put("due_tc_points", dueToPonts);
			map.put("now_points", usersAccountInfo.getPay_points());
			map.put("show_type", 1);
			apiLogService.saveLog(map);
			
			map.put("logType", "线下购物结算");
			map.put("user_id", seller_user_id);
			map.put("to_user_id", user_id);
			map.put("make_over_integral", point_saller);
			map.put("mobile", zjcuser.get(0).getMobile());
			map.put("real_name", zjcuser.get(0).getReal_name());
			map.put("now_make_over_integral", UsersAccountInfo1.get(0).getMake_over_integral());
			apiLogService.saveLog(map); 
			
			 String userTuisongContent = user_id + "会员向商家" + seller_user_id + "实体店会员支付" + amount + "易物券";
			 //String shopTuisongMsg = seller_user_id + "实体店收到" + user_id + "会员线下购物结算" + point_saller + "易物券";
			 //会员信息推送
			 //tuisong(zjcuserinfo.get(0).getClientid(),zjcuserinfo.get(0).getSrc_client(),userTuisongMsg);
			 String userTuisongMsg = ParameterUtil.getTuisongMsg(1,"线下交易", "线下交易",userTuisongContent);
			 String tuisong = ParameterUtil.tuisongToSingle(zjcuserinfo.get(0).getClientid(),zjcuserinfo.get(0).getSrc_client(),userTuisongMsg);
			 if(tuisong.contains("successed_online")){
				 MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				 MessageVO.setMsg(Apiconstant.Do_Success.getName());
				 MessageVO.setData("");
			 } else {
				 MessageVO.setCode(1234);
				 MessageVO.setMsg("推送离线");
			 }
			 //tuisong(SellerInfo.get(0).getClientid,SellerInfo.get(0).getSrc_client(),userTuisongMsg);
		 } catch(Exception e){
			 e.printStackTrace();
			 MessageVO.setCode(Apiconstant.Save_fails.getIndex());
			 MessageVO.setMsg(Apiconstant.Save_fails.getName());
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		 }
		 return  AOSJson.toJson(MessageVO);
	}
	/**
	 * 支付随机码
	 * @param request
	 * @param response
	 * @return
	 */
	public String pay_ment(HttpServletRequest request, HttpServletResponse response){
		 MessageVO MessageVO=new MessageVO();
		 String user_id=(String) request.getAttribute("user_id");
		 if(AOSUtils.isEmpty(user_id)){
			 MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			 MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			 return AOSJson.toJson(MessageVO);
		}
		 ZjcPaymentSallerPO payment=new ZjcPaymentSallerPO();
		 String payCode=ParameterUtil.getOrderSn();
		 payment.setPay_code(payCode);
		 payment.setUser_id(Integer.parseInt(user_id));
		 payment.setAdd_time(new Date());
		 payment.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		 try{
			 ZjcPaymentSallerDao.insert(payment);
			 MessageVO.setData(payCode);
			 MessageVO.setMsg(Apiconstant.Do_Success.getName());
			 MessageVO.setCode(Apiconstant.Do_Success.getIndex());
		 } catch(Exception e){
			 e.printStackTrace();
			 MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			 MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
		 }
		
		 return  AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 支付消息推送
	 * @param request
	 * @param response
	 * @return
	 */
	public void tuisong(String clientid,int src_client,String msg){
		 ParameterUtil.tuisongToSingle(clientid,src_client,msg);
	}
	
	/**
	 * 上级会员提成
	 * 
	 * @param zjcOrderPO
	 */
	public void higher_commission(ZjcOrderPO zjcOrderPO){
		//商品订单计算会员提成
		Rebate rebate = zjcTurnIntoUtil.plain_Order_Commission(zjcOrderPO.getOrder_id(),2);
		if(rebate.getFirst() == 0){//提成为0，直接退出
			return;
		}
		//查询当前会员的基本信息
		ZjcUsersInfoPO userinfo = ZjcUsersInfoDao.selectByKey(zjcOrderPO.getUser_id());
		//查询当前会员的上级会员的账户信息
		ZjcUsersAccountInfoPO higheraccount = ZjcUsersAccountInfoDao.selectByKey(new BigInteger(userinfo.getFirst_leader()+""));
		int old_tc_points = Integer.parseInt(higheraccount.getDue_tc_points());
		int old_count_wallet = Integer.parseInt(higheraccount.getPractical_tc_points());
		int duc_tc_points = rebate.getFirst() + old_tc_points;
		higheraccount.setDue_tc_points(duc_tc_points+"");//增加上级的分享服务赠送易物券
		if(higheraccount.getCount_wallet_quota()>=duc_tc_points){//上级会员的赠送额度大于其赠送易物券
			int practical_tc_points = old_count_wallet + rebate.getFirst();
			higheraccount.setPractical_tc_points(practical_tc_points+"");//增加上级会员分享服务领到的易物券
			higheraccount.setMake_over_integral(higheraccount.getMake_over_integral() + rebate.getFirst());//增加可转积分
		} else if(higheraccount.getCount_wallet_quota()>old_tc_points&&higheraccount.getCount_wallet_quota()<duc_tc_points){
			higheraccount.setPractical_tc_points(higheraccount.getCount_wallet_quota()+"");//增加上级领到易物券（实际）为其赠送额度
			higheraccount.setMake_over_integral(higheraccount.getMake_over_integral() + higheraccount.getCount_wallet_quota()-old_tc_points);//增加可转积分
		} 
		ZjcUsersAccountInfoDao.updateByKey(higheraccount);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("logType", "上级提成");
		map.put("user_id", userinfo.getFirst_leader());
		map.put("due_tc_points", rebate.getFirst());
		map.put("now_make_over_integral", higheraccount.getMake_over_integral());
		apiLogService.saveLog(map);
		
	}
	
	 /**
	  * 商家反分
	  * @param order_id
	  */
	 public void points(int order_id){
		 ZjcQueuePO queue=new ZjcQueuePO();
		 ZjcOrderPO ZjcOrder=new ZjcOrderPO();
		 ZjcOrder.setOrder_id(order_id);
		 List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
		 ZjcStorePO store=new ZjcStorePO();
		 store.setStore_id(list.get(0).getStore_id());
		 List<ZjcStorePO> stores=sqlDao.list("com.zjc.store.dao.ZjcStoreDao.queryStore",store);
		 ZjcSellerInfoPO sellerInfo = zjcSellerInfoDao.selectOne(Dtos.newDto("user_id", stores.get(0).getUser_id()));
		 if(!AOSUtils.isEmpty(sellerInfo)){
			 try {
				 //List<ZjcOrderGoodsPO> orderGoods = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", order_id));
				 List<ZjcOrderGoodsPO> orderGoods= sqlDao.list("com.zjc.order.dao.ZjcOrderGoodsDao.listOrderGoods", Dtos.newDto("order_id", order_id));
				 int  kz_points = 0;
				 if(!AOSUtils.isEmpty(orderGoods)){
					 for(ZjcOrderGoodsPO orderGood :orderGoods){
						 //ZjcGoodsPO good =  ZjcGoodsDao.selectByKey(orderGood.getGoods_id());
						 BigDecimal storeRate = new BigDecimal(orderGood.getStore_rebate_rate());//商家返分比例
						 switch (list.get(0).getPay_code()) {
				            case "rate":
				            	kz_points += (orderGood.getMarket_price().multiply(new BigDecimal(orderGood.getGoods_num()))).multiply(storeRate).intValue();
				                break;
				            case "alipay":
				            	kz_points += (orderGood.getCost_price().multiply(new BigDecimal(orderGood.getGoods_num()))).multiply(storeRate).intValue();
				                break;
				            case "wxpay":
				            	kz_points += (orderGood.getCost_price().multiply(new BigDecimal(orderGood.getGoods_num()))).multiply(storeRate).intValue();
				                break;
				            case "unionpay":
				            	kz_points += (orderGood.getCost_price().multiply(new BigDecimal(orderGood.getGoods_num()))).multiply(storeRate).intValue();
				                break;
				            case "mixed_payment":
				            	kz_points += (orderGood.getMarket_price().multiply(new BigDecimal(orderGood.getGoods_num()))).multiply(storeRate).intValue();
				                break;
				        }
					 }
				 }
				 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				 queue.setNote("会员商城购物，商家返利");
				 queue.setType(6);
				 queue.setAdd_time(new Date());
				 List<ZjcMemberOtherPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKeys",null);
				 Date d=new Date();   
				 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				 String param=paramDtos.get(0).getRebate_days_delay();
				 String date= df.format(new Date(d.getTime() + Integer.parseInt(param) * 24 * 60 * 60 * 1000));
				 Date dates = null;
				 dates = df.parse(date);
				 queue.setSend_time(dates);
				 queue.setUser_id(sellerInfo.getUser_id());
				 queue.setKz_points(kz_points);
				 queue.setNum(1);
				 queue.setRelation_id(list.get(0).getOrder_id());
				 
				 ZjcQueueDao.insert(queue);
			 } catch (ParseException e) {
				 e.printStackTrace();
			 }
		 }
	}
	 /**
	  * 反分数据
	  * @param httpModel
	  */
	 public void fanfen(HttpModel httpModel){
			Dto dto = httpModel.getInDto();
			String id=dto.get("id").toString();
			String [] queid=id.split(",");
			for (int i = 0; i < queid.length; i++) {
				 ZjcQueuePO ZjcQueuePO =new ZjcQueuePO();
				 ZjcQueuePO.setId(Integer.parseInt(queid[i]));
				 List<ZjcQueuePO> queue=sqlDao.list("com.zjc.users.dao.ZjcQueueDao.selectByKeys",ZjcQueuePO);
				 ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
				 ZjcUsersAccountInfoPO.setUser_id(queue.get(0).getUser_id());
				 List<ZjcUsersAccountInfoPO> zjcuserin=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.usersd",ZjcUsersAccountInfoPO);
				 int make_over_integral=zjcuserin.get(0).getMake_over_integral()+queue.get(0).getKz_points();
				 zjcuserin.get(0).setMake_over_integral(make_over_integral);
				 ZjcUsersAccountInfoDao.updateByKey(zjcuserin.get(0));
				 queue.get(0).setIs_send(1);
				 queue.get(0).setSend_time(new Date());
				 queue.get(0).setSuccess_time(new Date());
				 ZjcQueueDao.updateByKey(queue.get(0));
			}
	 }
	 
	/**
	 * 混合支付易物券扣除
	 * 
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public String mixed_pay(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("orderId")) || AOSUtils.isEmpty(dto.getString("random")) || AOSUtils.isEmpty(dto.getString("sign"))){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(msgVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(msgVO);
		}
		int order_id = dto.getInteger("orderId");
		ZjcOrderPO ZjcOrder=new ZjcOrderPO();
		ZjcOrder.setOrder_id(order_id);
		List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
		if(list.get(0).getPoints_pay_status()==1){//订单券已支付
			msgVO.setMsg(Apiconstant.Order_Has_Payed.getName());
			msgVO.setCode(Apiconstant.Order_Has_Payed.getIndex());
			return AOSJson.toJson(msgVO);
		}
		ZjcUsersInfoPO userInfo = ZjcUsersInfoDao.selectByKey(list.get(0).getUser_id());
		String random=dto.getString("random");
		String design=dto.getString("sign");
		String psd_type=ConstantUtil.PAY_PSD_TYPE;
		boolean password=ValidateUtil.checkPsd(userInfo, random, design, psd_type);
		if(password==false){
			msgVO.setMsg(Apiconstant.Pay_Psd_Error.getName());
			msgVO.setCode(Apiconstant.Pay_Psd_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
	    try {
	    	   List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", list.get(0).getOrder_id()));
   		   	   ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
	    	   zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
	    	   List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
	    	   if(goodsd.get(0).getStore_count()<orderGood.get(0).getGoods_num()){//库存不足
	    		    msgVO.setMsg(Apiconstant.Insufficient_inventory.getName());
	    		    msgVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
		    		return AOSJson.toJson(msgVO);
		       }
			   if(goodsd.get(0).getIs_on_sale()!=1){//商品已下架
				  msgVO.setMsg(goodsd.get(0).getGoods_name()+Apiconstant.Commodities_have_shelves.getName());
				  msgVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
	    		  return AOSJson.toJson(msgVO);
	    	   }
			   if(goodsd.get(0).getGoods_state_1() != 3 || goodsd.get(0).getGoods_state_2() != 3){//该商品未审核或者审核不通过
				  msgVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
				  msgVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
	    		  return AOSJson.toJson(msgVO);
	    	   }
			   //限制商品购买次数
			   ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
	    	   Map<String,Object> map = new HashMap<String,Object>();
			   map.put("user_id", httpModel.getAttribute("user_id"));
			   map.put("goods_id", orderGood.get(0).getGoods_id());
			   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
			   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
				   msgVO.setMsg("很抱歉！该商品每日每人只能购买" + isgoodnum.getToday_limit_times()+"次，请明天再来");
				   msgVO.setCode(Apiconstant.goods_times_enough.getIndex());
			       return AOSJson.toJson(msgVO);
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
	    		    	return AOSJson.toJson(msgVO);
	    		   }
    		   }else if(list.get(0).getBarter_coupons().compareTo(new BigDecimal(users.get(0).getPay_points()))==1){//判断余额是否充足
    			   msgVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
    			   msgVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
    		    	return AOSJson.toJson(msgVO);
    		   }
			 /*  if(list.get(0).getBarter_coupons().intValue() <= users.get(0).getPay_points()){//验证易物券是否足够
*/				   //更改商品库存销量信息
		    	   int Goods_nums = orderGood.get(0).getGoods_num();
		    	   Dto countdto = Dtos.newDto();
		    	   countdto.put("Goods_nums", Goods_nums);
		    	   countdto.put("goods_id", goodsd.get(0).getGoods_id());
				   int k=sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateByStoreCount", countdto);
				   if(k == 0){//库存不足
					   msgVO.setMsg(Apiconstant.Insufficient_inventory.getName());
		    		   msgVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
			    	   return AOSJson.toJson(msgVO);
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
				   zjcTurnIntoUtil.shop_order(order_id); //商城购物记录发送
				   list.get(0).setOrder_status(0);
				   list.get(0).setPoints_pay_status(1);//混合支付易物券支付状态已支付
				   zjcOrderDao.updateByKey(list.get(0));
				   
				   //查询是否是小平台商品
			       XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", orderGood.get(0).getGoods_id()));
			       if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//如果是小平台商品，支付成功后，调用小平台系统接口
			          zjcTurnIntoUtil.xptCreateOrder(order_id);
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
					zjcOrderActionPONew.setOrder_id(order_id);
					zjcOrderActionPONew.setOrder_status(0);//待确认
					zjcOrderActionPONew.setPay_status(1);//已支付
					zjcOrderActionPONew.setShipping_status(0);//未发货
					zjcOrderActionPONew.setStatus_desc("提交订单");
					zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
					zjcOrderActionDao.insert(zjcOrderActionPONew);
					//商品反分处理
				    if(list.get(0).getPoints_pay_status()==1){//易物券支付成功
		               //下单时商家会员返利 对于特殊商品 * 
	    			   //zjcTurnIntoUtil.deal_goods_ends_mixed(order_id);
	    			   //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
	    			   //Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission(order_id,0);
	    			   /*Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(order_id);
	    			   if(Rebate.getSjfl() > 0 ){//商家返利
	    				   zjcTurnIntoUtil.sj_expire(Rebate.getSjfl(),order_id,Rebate.getSale_user_id());
	    			   }
	    			   if(Rebate.getPoints() > 0){
		    			   zjcTurnIntoUtil.xf_expire_new(Rebate.getPoints(), order_id);
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
	    			   }*/
				    
				    	
			       }
			        msgVO.setMsg(Apiconstant.Do_Success.getName());
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
			  /* } else {//易物券不足
				   msgVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
				   msgVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
			   }*/
		} catch (Exception e) {
			   e.printStackTrace();
			   msgVO.setMsg(Apiconstant.Order_is_fail.getName());
			   msgVO.setCode(Apiconstant.Order_is_fail.getIndex());
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		}
       
        return  AOSJson.toJson(msgVO);
	}

	
	/**
	 * 混合支付滴扣除
	 * 
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public String mixDrops_pay(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("orderId")) || AOSUtils.isEmpty(dto.getString("random")) || AOSUtils.isEmpty(dto.getString("sign"))){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
		if(msgVO.getCode() != 1){//会员账户被冻结
			return AOSJson.toJson(msgVO);
		}
		int order_id = dto.getInteger("orderId");
		ZjcOrderPO ZjcOrder=new ZjcOrderPO();
		ZjcOrder.setOrder_id(order_id);
		List<ZjcOrderPO> list=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.ZjcOrderPOlists",ZjcOrder);
		if(list.get(0).getPoints_pay_status()==1){//订单券已支付
			msgVO.setMsg(Apiconstant.Order_Has_Payed.getName());
			msgVO.setCode(Apiconstant.Order_Has_Payed.getIndex());
			return AOSJson.toJson(msgVO);
		}
		ZjcUsersInfoPO userInfo = ZjcUsersInfoDao.selectByKey(list.get(0).getUser_id());
		String random=dto.getString("random");
		String design=dto.getString("sign");
		String psd_type=ConstantUtil.PAY_PSD_TYPE;
		boolean password=ValidateUtil.checkPsd(userInfo, random, design, psd_type);
		if(password==false){
			msgVO.setMsg(Apiconstant.Pay_Psd_Error.getName());
			msgVO.setCode(Apiconstant.Pay_Psd_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
	    try {
	    	   List<ZjcOrderGoodsPO> orderGood = ZjcOrderGoodsDao.list(Dtos.newDto("order_id", list.get(0).getOrder_id()));
   		   	   ZjcGoodsPO zjcGoodsPO=new ZjcGoodsPO();
	    	   zjcGoodsPO.setGoods_id(orderGood.get(0).getGoods_id());
	    	   List<ZjcGoodsPO> goodsd=sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",zjcGoodsPO);
	    	   if(goodsd.get(0).getStore_count()<orderGood.get(0).getGoods_num()){//库存不足
	    		    msgVO.setMsg(Apiconstant.Insufficient_inventory.getName());
	    		    msgVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
		    		return AOSJson.toJson(msgVO);
		       }
			   if(goodsd.get(0).getIs_on_sale()!=1){//商品已下架
				  msgVO.setMsg(goodsd.get(0).getGoods_name()+Apiconstant.Commodities_have_shelves.getName());
				  msgVO.setCode(Apiconstant.Commodities_have_shelves.getIndex());
	    		  return AOSJson.toJson(msgVO);
	    	   }
			   if(goodsd.get(0).getGoods_state_1() != 3 || goodsd.get(0).getGoods_state_2() != 3){//该商品未审核或者审核不通过
				  msgVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
				  msgVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
	    		  return AOSJson.toJson(msgVO);
	    	   }
			   //限制商品购买次数
			   ZjcGoodsPO isgoodnum = ZjcGoodsDao.selectByKey(orderGood.get(0).getGoods_id());
	    	   Map<String,Object> map = new HashMap<String,Object>();
			   map.put("user_id", httpModel.getAttribute("user_id"));
			   map.put("goods_id", orderGood.get(0).getGoods_id());
			   Integer eachdaybuytimes =  (Integer) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.countTodayBuyTimes",Dtos.newDto(map));
			   if(eachdaybuytimes >= isgoodnum.getToday_limit_times()){//当日超出该商品每人的限购次数
				   msgVO.setMsg("很抱歉！该商品每日每人只能购买" + isgoodnum.getToday_limit_times()+"次，请明天再来");
				   msgVO.setCode(Apiconstant.goods_times_enough.getIndex());
			       return AOSJson.toJson(msgVO);
			   }
	    	
	           // 2 写入订单商品表
			   ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
			   ZjcUsersAccountInfoPO.setUser_id(list.get(0).getUser_id());
			   // 4 扣除积分
			   List<ZjcUsersAccountInfoPO> users=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",ZjcUsersAccountInfoPO);
			   if(list.get(0).getBarter_coupons().intValue() <= users.get(0).getDrops()){//验证滴是否足够
				   //更改商品库存销量信息
		    	   int Goods_nums = orderGood.get(0).getGoods_num();
		    	   Dto countdto = Dtos.newDto();
		    	   countdto.put("Goods_nums", Goods_nums);
		    	   countdto.put("goods_id", goodsd.get(0).getGoods_id());
				   int k=sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateByStoreCount", countdto);
				   if(k == 0){//库存不足
					   msgVO.setMsg(Apiconstant.Insufficient_inventory.getName());
		    		   msgVO.setCode(Apiconstant.Insufficient_inventory.getIndex());
			    	   return AOSJson.toJson(msgVO);
				   }
				   double pay_points=new BigDecimal(users.get(0).getPay_points()).subtract(list.get(0).getBarter_coupons()).doubleValue(); 
				   users.get(0).setPay_points((int)pay_points);
				   int nowTotalAmount = Integer.parseInt(users.get(0).getTotal_amount()) + list.get(0).getBarter_coupons().intValue() + list.get(0).getCash().intValue();
				   users.get(0).setTotal_amount(nowTotalAmount+"");//增加累计消费
				   ZjcUsersAccountInfoDao.updateByKey(users.get(0));
				   zjcTurnIntoUtil.shop_order(order_id); //商城购物记录发送
				   list.get(0).setOrder_status(0);
				   list.get(0).setPoints_pay_status(1);//混合支付易物券支付状态已支付
				   zjcOrderDao.updateByKey(list.get(0));
				   
				   //查询是否是小平台商品
			       XpttsgoodsidPO xpttsgoodsidPO = (XpttsgoodsidPO) sqlDao.selectOne("com.api.goods.dao.XpttsgoodsidDao.selectOne", Dtos.newDto("goods_id", orderGood.get(0).getGoods_id()));
			       if(AOSUtils.isNotEmpty(xpttsgoodsidPO)){//如果是小平台商品，支付成功后，调用小平台系统接口
			          zjcTurnIntoUtil.xptCreateOrder(order_id);
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
					zjcOrderActionPONew.setOrder_id(order_id);
					zjcOrderActionPONew.setOrder_status(0);//待确认
					zjcOrderActionPONew.setPay_status(1);//已支付
					zjcOrderActionPONew.setShipping_status(0);//未发货
					zjcOrderActionPONew.setStatus_desc("提交订单");
					zjcOrderActionPONew.setAction_note("您提交了订单，请等待系统确认");
					zjcOrderActionDao.insert(zjcOrderActionPONew);
					//商品反分处理
				    if(list.get(0).getPoints_pay_status()==1){//易物券支付成功
		               //下单时商家会员返利 对于特殊商品 * 
	    			   //zjcTurnIntoUtil.deal_goods_ends_mixed(order_id);
	    			   //订单商品计算提成的易物劵 商品类会员提成按比例处理(普通商品)
	    			   //Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission(order_id,0);
	    			   Rebate Rebate=zjcTurnIntoUtil.plain_Order_Commission_New(order_id);
	    			   if(Rebate.getSjfl() > 0 ){//商家返利
	    				   zjcTurnIntoUtil.sj_expire(Rebate.getSjfl(),order_id,Rebate.getSale_user_id());
	    			   }
	    			   if(Rebate.getPoints() > 0){
		    			   zjcTurnIntoUtil.xf_expire_new(Rebate.getPoints(), order_id);
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
			       }
			        msgVO.setMsg(Apiconstant.Do_Success.getName());
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
			   } else {//易物券不足
				   msgVO.setMsg(Apiconstant.Account_balance_insufficient.getName());
				   msgVO.setCode(Apiconstant.Account_balance_insufficient.getIndex());
			   }
		} catch (Exception e) {
			   e.printStackTrace();
			   msgVO.setMsg(Apiconstant.Order_is_fail.getName());
			   msgVO.setCode(Apiconstant.Order_is_fail.getIndex());
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		}
       
        return  AOSJson.toJson(msgVO);
	}
	
	
	
	
	/**
	 * 获取代金券列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getVouchers(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
	    if(AOSUtils.isEmpty(httpModel.getAttribute("user_id")) || AOSUtils.isEmpty(dto.getString("is_voucher"))){//参数错误
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
	    dto.put("user_id", httpModel.getAttribute("user_id"));
	    /*dto.put("voucher_limit", 18);//普通商品，使用18的代金券
	    if("1".equals(dto.getString("is_voucher"))){//特殊商品，可使用58元的代金券
	    	dto.put("voucher_limit", 58);
	    }*/
	    dto.put("is_use", 0);
	    List<ZjcVouchersPO> voucherList = zjcVouchersDao.list(dto);
	    if(AOSUtils.isEmpty(voucherList)){//暂无数据
	    	msgVO.setMsg(Apiconstant.NO_DATA.getName());
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
	    } else {
	    	msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setData(voucherList);
	    }
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 查询我的代金券列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getMyVouchers(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
	    if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//参数错误
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
	    dto.put("user_id", httpModel.getAttribute("user_id"));
	    List<ZjcVouchersPO> voucherList = zjcVouchersDao.list(dto);
	    if(AOSUtils.isEmpty(voucherList)){//暂无数据
	    	msgVO.setMsg(Apiconstant.NO_DATA.getName());
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
	    } else {
	    	msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setData(voucherList);
	    }
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 订单快照
	 * @param request
	 * @param response
	 * @return
	 */
	public String orderDetail(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("order_id").trim())|| AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//判断查询条件是否为空
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		ZjcOrderPO orderpo= (ZjcOrderPO) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.myOrderDetail", qDto);
		if(AOSUtils.isEmpty(orderpo)){//查询订单不存在
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		SpellGroupRelationalPO spellList = (SpellGroupRelationalPO) sqlDao.selectOne("com.api.pintuan.dao.SpellGroupRelationalDao.list", Dtos.newDto("order_id",qDto.getString("order_id")));
		if(AOSUtils.isNotEmpty(spellList)){
			orderpo.setPin_order_id(spellList.getPin_order_id());
		}
		ZjcOrderGoodsPO orderGoodsList = (ZjcOrderGoodsPO) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderGoodsDao.getOrderGoodsDetail", qDto);
		if(AOSUtils.isNotEmpty(orderGoodsList)){//订单商品存在
			ZjcOrderGoodsPO orderGoods = orderGoodsList;
			ZjcGoodsPO newgoodsPO = new ZjcGoodsPO();
			ZjcGoodsPO goodsPO = ZjcGoodsDao.selectByKey(orderGoods.getGoods_id());
			if(AOSUtils.isNotEmpty(goodsPO)){//商品存在
				newgoodsPO.setOriginal_img(goodsPO.getOriginal_img());
				if(AOSUtils.isNotEmpty(orderGoodsList.getShop_group_market_price())){
					newgoodsPO.setShop_group_market_price(orderGoodsList.getShop_group_market_price());
				}
				if(AOSUtils.isNotEmpty(orderGoodsList.getShop_group_person())){
					newgoodsPO.setShop_group_person(orderGoodsList.getShop_group_person());
				}
				if(AOSUtils.isNotEmpty(orderGoodsList.getShop_group_price())){
					newgoodsPO.setShop_group_price(orderGoodsList.getShop_group_price());
				}
				orderGoods.setZjcGoodsPO(newgoodsPO);
			}
			ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.getZjcDeliveryByOrderId(orderGoods.getOrder_id());
			ZjcDeliveryDocPO newDelivery = new ZjcDeliveryDocPO();
			if(AOSUtils.isNotEmpty(zjcDeliveryDocPO)){//设置物流信息
				newDelivery.setId(zjcDeliveryDocPO.getId());
				newDelivery.setShipping_name(zjcDeliveryDocPO.getShipping_name());
				newDelivery.setInvoice_no(zjcDeliveryDocPO.getInvoice_no());
				orderGoods.setZjcDeliveryDocPO(newDelivery);
			}
			if(AOSUtils.isNotEmpty(orderGoods.getGoods_content())){//订单快照商品信息存在
				orderGoods.setGoods_content(ParameterUtil.createHtmlBody(orderGoods.getGoods_name(), orderGoods.getGoods_content()));
			} else {
				orderGoods.setGoods_content(ParameterUtil.createHtmlBody(orderGoods.getGoods_name(), "暂无信息"));
			}
			List<ZjcOrderGoodsPO> newOrderGoodsList = new ArrayList<ZjcOrderGoodsPO>();
			newOrderGoodsList.add(orderGoods);
			orderpo.setZjcOrderGoodsPO(newOrderGoodsList);
			orderpo.setArea_info("");
		}
		MessageVO.setMsg(Apiconstant.Do_Success.getName());//
		MessageVO.setCode(Apiconstant.Do_Success.getIndex());//
		MessageVO.setData("");
		if(AOSUtils.isNotEmpty(orderpo)){
			MessageVO.setData(orderpo);
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 用户订单台账
	 * @param request
	 * @param response
	 * @return
	 */
	public String transferOrderDetail(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//判断查询条件是否为空
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		qDto.put("user_id", httpModel.getAttribute("user_id"));
		//设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(qDto.getInteger("page"))){
			qDto.put("page", 1);
			}
		int page = qDto.getInteger("page");
		qDto.put("start", (page-1)*ConstantUtil.pageSize);
		if(AOSUtils.isNotEmpty(qDto.getString("transfer_type"))){
			if("0".equals(qDto.getString("transfer_type"))){
				qDto.put("transfer", "0");
				qDto.put("transfer_type", "");
			}
		}
		List<Dto> orderpo=sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTransferUserOrderPage", qDto);
		if(AOSUtils.isEmpty(orderpo)){//查询订单不存在
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
		pageVO.setList(orderpo);
		pageVO.setTotalSize(qDto.getInteger("total"));
		MessageVO.setMsg(Apiconstant.Do_Success.getName());//
		MessageVO.setCode(Apiconstant.Do_Success.getIndex());//
		MessageVO.setData(pageVO);
		return AOSJson.toJson(MessageVO);
	}
	
	
	/**
	 * 退单，催单操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateTransferOrder(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))||AOSUtils.isEmpty(qDto.getString("order_id"))){//判断查询条件是否为空
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		Date date=new Date();
		ZjcTransferOrderTimePO ZjcTransferOrderTimePO=new ZjcTransferOrderTimePO();
		//查询数据是否存在
		ZjcTransferOrderTimePO ZjcTransferOrderTime=(ZjcTransferOrderTimePO) sqlDao.selectOne("com.zjc.order.dao.ZjcTransferOrderTimeDao.selectOne", Dtos.newDto("order_id",qDto.getString("order_id").toString()));
		if(AOSUtils.isEmpty(ZjcTransferOrderTime)){//数据不存在
			ZjcTransferOrderTimePO.setOrder_id(Integer.valueOf(qDto.getString("order_id")));
			if("1".equals(qDto.getString("type"))){//类型为催单
				ZjcTransferOrderTimePO.setReminder(1);
				ZjcTransferOrderTimePO.setRemindertime(date);
			}else {//类型为退单
				ZjcTransferOrderTimePO.setSingle_back(0);
				ZjcTransferOrderTimePO.setSingle_back_time(date);
			}
			int row=ZjcTransferOrderTimeDao.insert(ZjcTransferOrderTimePO);
			if(row==1){
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setData(ZjcTransferOrderTimePO);
				return AOSJson.toJson(MessageVO);
			}else {
				MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				return AOSJson.toJson(MessageVO);
			}
		}else {//数据存在
			if("1".equals(qDto.getString("type"))){//类型为催单
				if(ZjcTransferOrderTime.getReminder()>0){//催单次数大于0次
					try {
					    Date lTime = new Date();
					    long minsBetween = (lTime.getTime() - ZjcTransferOrderTime.getRemindertime().getTime()) / 1000/60/60/24;
					    if(minsBetween >= 1){
					    	if(ZjcTransferOrderTime.getReminder()<3){
					    		ZjcTransferOrderTime.setReminder(ZjcTransferOrderTime.getReminder()+1);
								ZjcTransferOrderTime.setRemindertime(date);
					    	}else {
					    		MessageVO.setMsg("每个订单最多催三次");
								MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
								return AOSJson.toJson(MessageVO);
							}
					    } else {
					    	MessageVO.setMsg("请在24小时后催单");
							MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
							return AOSJson.toJson(MessageVO);
					    }
					} catch (Exception e) {
						e.printStackTrace();
						MessageVO.setMsg(Apiconstant.Do_Fails.getName());
						MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
						return AOSJson.toJson(MessageVO);
					}
				}else {
					ZjcTransferOrderTime.setReminder(ZjcTransferOrderTime.getReminder()+1);
					ZjcTransferOrderTime.setRemindertime(date);
				}
			}else {//类型为退单
				ZjcTransferOrderTime.setSingle_back(0);
				ZjcTransferOrderTime.setSingle_back_time(date);
			}
			int row=ZjcTransferOrderTimeDao.updateByKey(ZjcTransferOrderTime);
			if(row==1){
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setData(ZjcTransferOrderTime);
				return AOSJson.toJson(MessageVO);
			}else {
				MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				return AOSJson.toJson(MessageVO);
			}
		}
	}
	
	/**
	 * 退单，催单操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateOperateOrder(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))||AOSUtils.isEmpty(qDto.getString("order_id"))
				||AOSUtils.isEmpty(qDto.getString("goods_id"))||AOSUtils.isEmpty(qDto.getString("type"))){//判断查询条件是否为空
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		//查询数据是否存在
		ZjcOrderPO zjcOrderPO=zjcOrderDao.selectByKey(qDto.getInteger("order_id"));
		if(AOSUtils.isEmpty(zjcOrderPO)){//订单数据不存在
			MessageVO.setMsg(Apiconstant.Order_NO_Exist.getName());
			MessageVO.setCode(Apiconstant.Order_NO_Exist.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		try{
			if("1".equals(qDto.getString("type"))){//类型为催单
				if(zjcOrderPO.getSingle_back()!=0){//该订单已退单，不能再催单
					MessageVO.setMsg(Apiconstant.Order_Cannot_Reminder_If_Chargeback.getName());
					MessageVO.setCode(Apiconstant.Order_Cannot_Reminder_If_Chargeback.getIndex());
					return AOSJson.toJson(MessageVO);
				}
				if(zjcOrderPO.getReminder()>0){//催单次数大于0次
				    Date lTime = new Date();
				    long minsBetween = (lTime.getTime() - zjcOrderPO.getRemindertime().getTime()) / 1000/60/60/24;
				    if(minsBetween < 1){//催单间隔时间小于24小时
				    	MessageVO.setMsg(Apiconstant.Order_Cannot_Reminder.getName());
						MessageVO.setCode(Apiconstant.Order_Cannot_Reminder.getIndex());
						return AOSJson.toJson(MessageVO);
				    }
				    if(zjcOrderPO.getReminder()>=3){//催单次数达到3次
				    	MessageVO.setMsg(Apiconstant.Order_Cannot_Repeat_Reminder.getName());
						MessageVO.setCode(Apiconstant.Order_Cannot_Repeat_Reminder.getIndex());
						return AOSJson.toJson(MessageVO);
				    }
		    		zjcOrderPO.setReminder(zjcOrderPO.getReminder()+1);
		    		zjcOrderPO.setRemindertime(new Date());
		    		zjcOrderDao.updateByKey(zjcOrderPO);
		    		MessageVO.setMsg(Apiconstant.Do_Success.getName());
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				}else {//首次催单
					zjcOrderPO.setReminder(zjcOrderPO.getReminder()+1);
					zjcOrderPO.setRemindertime(new Date());
					zjcOrderDao.updateByKey(zjcOrderPO);
					MessageVO.setMsg(Apiconstant.Do_Success.getName());
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				}
			}else {//类型为退单，状态改为退单中
				ZjcGoodsPO goodsPO = ZjcGoodsDao.selectByKey(qDto.getInteger("goods_id"));
				if(goodsPO.getIs_vip()==1){//vip商品订单
					MessageVO.setMsg(Apiconstant.Vip_Order_Cannot_Chargeback.getName());
					MessageVO.setCode(Apiconstant.Vip_Order_Cannot_Chargeback.getIndex());
					return AOSJson.toJson(MessageVO);
				}
				if(goodsPO.getPrompt_goods()==1){//秒杀商品订单
					MessageVO.setMsg(Apiconstant.Prompt_Order_Cannot_Chargeback.getName());
					MessageVO.setCode(Apiconstant.Prompt_Order_Cannot_Chargeback.getIndex());
					return AOSJson.toJson(MessageVO);
				}
				if(AOSUtils.isNotEmpty(zjcOrderPO.getIs_shop_group())&&zjcOrderPO.getIs_shop_group()==1){//拼团订单
					MessageVO.setMsg(Apiconstant.PinTuan_Order_Cannot_Chargeback.getName());
					MessageVO.setCode(Apiconstant.PinTuan_Order_Cannot_Chargeback.getIndex());
					return AOSJson.toJson(MessageVO);
				}
				if(zjcOrderPO.getSingle_back() != 0){//不处于发起退单状态
					MessageVO.setMsg(Apiconstant.Order_Cannot_Chargeback.getName());
					MessageVO.setCode(Apiconstant.Order_Cannot_Chargeback.getIndex());
					return AOSJson.toJson(MessageVO);
				}
				zjcOrderPO.setSingle_back(1);
				zjcOrderPO.setSingle_back_time(new Date());
				zjcOrderDao.updateByKey(zjcOrderPO);
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			}
		} catch(Exception e){//系统异常
			e.printStackTrace();
			MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		return AOSJson.toJson(MessageVO);
	}
}
