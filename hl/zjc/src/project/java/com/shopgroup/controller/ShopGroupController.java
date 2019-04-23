/**
 * 
 */
package com.shopgroup.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.login.dao.po.ZjcVouchersPO;
import com.api.pintuan.service.PinTuanOrderService;
import com.api.userInfo.UserInfoService;
import com.shopgroup.service.ShopGroupService;
import com.wxactivity.goods.service.WxActivityGoodsService;
import com.wxactivity.order.service.ShareOrderService;
import com.wxstore.service.WxStoreService;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/shopGroup")
public class ShopGroupController {
	
	@Autowired
	private WxActivityGoodsService wxGoodsService;
	@Autowired
	private ShareOrderService shareOrderService;
	@Autowired
	private PinTuanOrderService PinTuanOrderService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ShopGroupService shopGroupService;
	@Autowired
	private ShareOrderService ShareOrderService;
	@Autowired
	private WxStoreService WxStoreService;
	@Autowired
	private UserInfoService userInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(ShopGroupController.class);

	@RequestMapping(value = "/activity/v1/initShopGroupIndex")
	public String initShopGroupIndex(HttpServletRequest request, HttpServletResponse response){
		return "project/shopgroup/spell_buy_index.jsp";
	}
	
	@RequestMapping(value = "/activity/v1/initGoodsDetail")
	public String initGoodsDetail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = wxGoodsService.getGoodsDetailByGoodsId(dto);
		BigDecimal market_price=null; 
		BigDecimal a = new BigDecimal("0");
		if(a.compareTo(goodDetail.getMarket_price())==0){
			market_price=goodDetail.getShop_price();
			goodDetail.setShop_group_market_price(market_price);
		}else {
			market_price=goodDetail.getMarket_price();
		}
		httpModel.setAttribute("goodDetail", goodDetail);
		httpModel.setAttribute("openid", request.getSession().getAttribute("openid"));
		return "project/shopgroup/spell_detail.jsp";
	}
	
	/**
	 * 分享页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initSpellBuy")
	public String initSpellBuy(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = wxGoodsService.getGoodsDetailByGoodsId(dto);
		httpModel.setAttribute("goodDetail", goodDetail);
		String pin_order_id = dto.getString("pin_order_id");
		httpModel.setAttribute("pin_order_id", pin_order_id);
		return "project/shopgroup/spell_buy.jsp";
	}
	
	/**
	 * 分享好友页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initSpellShare")
	public String initSpellShare(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = wxGoodsService.getGoodsDetailByGoodsId(dto);
		httpModel.setAttribute("goodDetail", goodDetail);
		String pin_order_id = dto.getString("pin_order_id");
		httpModel.setAttribute("pin_order_id", pin_order_id);
		return "project/shopgroup/spell_share.jsp";
	}
	
	
	@RequestMapping(value = "/activity/v1/initSpellGoBuy")
	public String initSpellGoBuy(HttpServletRequest request, HttpServletResponse response){
		String url = "project/shopgroup/phone_register.jsp";
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = wxGoodsService.getGoodsDetailByGoodsId(dto);
		String openid = request.getSession().getAttribute("openid") == null ? null  : request.getSession().getAttribute("openid").toString();
		//String openid = "oItDov93lq6V_i3Y36Tt7SRvDH1E";
		String pin_order_id = dto.getString("pin_order_id");
		httpModel.setAttribute("pin_order_id", pin_order_id);
		BigDecimal market_price=null;
		if(AOSUtils.isNotEmpty(goodDetail)){
			BigDecimal a = new BigDecimal("0");
			if(a.compareTo(goodDetail.getMarket_price())==0){
				market_price=goodDetail.getShop_price();
				goodDetail.setShop_group_market_price(market_price);
			}else {
				market_price=goodDetail.getMarket_price();
			}
			httpModel.setAttribute("goodDetail", goodDetail);
			httpModel.setAttribute("market_price", goodDetail.getMarket_price());
			httpModel.setAttribute("goods_id", goodDetail.getGoods_id());
			httpModel.setAttribute("store_id", goodDetail.getStore_id());
		}
		if(AOSUtils.isNotEmpty(openid)){
			List<ZjcUserAddressPO> addressList = shareOrderService.getUserAddressListToGobuy(openid);
			httpModel.setAttribute("addressList", addressList);
			if(addressList != null && addressList.size() > 0 ){
				httpModel.setAttribute("address_id", addressList.get(0).getAddress_id());
			}
			ZjcUsersInfoPO zjcUsersInfoPO = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectByOpenid", openid);
			if(AOSUtils.isNotEmpty(zjcUsersInfoPO)){
				httpModel.setAttribute("user_id", zjcUsersInfoPO.getUser_id());
				url = "project/shopgroup/spell_go_buy.jsp";
			}else{
				url = "project/shopgroup/phone_register.jsp";
			}
		}
		httpModel.setAttribute("register_type", "spell");
		return url;
	}
	
	/** @api {post} shopGroup/activity/v1/pinOrderSub.jhtml 提交商品页面
	 * @apiName orderSub提交商品页面
	 * @apiGroup Order
	 * 
	 * @apiParam {String} user_id user_id
	 * @apiParam {String} address_id 地址id
	 * @apiParam {String} total_amount  订单总额
	 * @apiParam {String} store_id  店铺id
	 * @apiParam {String} Goods  商品数据
	 * @apiParam {String} pay_type  支付方式
	 * @apiParam {String} user_note  备注信息
	 * @apiParam {String} Goods  商品数据，如[{"goods_id":"19552","spec_key_name":null,"spec_key":null,"goods_num":1}]
	 * @apiParam {String} voucher_id  代金券id
	 * @apiParam {String} pin_order_id  如果从参与拼单中去下单 则需要传参与人的订单id
	 * @apiParam {String} is_shop_group 是否是拼团订单
	 * @apiParam {String} pay_password 支付密码
	 */
	@RequestMapping(value = "/activity/v1/pinOrderSub")
	public void pinOrderSub(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 request.setAttribute("user_id", httpModel.getInDto().getString("user_id"));
		 String outMsg=PinTuanOrderService.wxOrderSub(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	@RequestMapping(value = "/activity/v1/initSetAddress")
	public String initSetAddress(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		if(AOSUtils.isNotEmpty(request.getSession().getAttribute("openid"))){
			String openid = request.getSession().getAttribute("openid").toString();
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", openid));
			if(AOSUtils.isNotEmpty(userInfo)){
				request.getSession().setAttribute("user_id", userInfo.getUser_id());//user_id存session
				httpModel.setAttribute("userInfoPO", userInfo);
				ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(userInfo.getUser_id());
				httpModel.setAttribute("zjcUsersAccountInfoPO", zjcUsersAccountInfoPO);
			} 
		}
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("goods_id"))){
			httpModel.setAttribute("goods_id", httpModel.getInDto().getString("goods_id"));
		}
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("pin_order_id"))){
			httpModel.setAttribute("pin_order_id", httpModel.getInDto().getString("pin_order_id"));
		}
		httpModel.setAttribute("register_type", httpModel.getInDto().getString("register_type"));
		return "project/shopgroup/set_address.jsp";
	}
	
	/**
	 * 新增收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/activity/v1/saveAddress")
	public void saveAddress(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto inDto = httpModel.getInDto();
		 inDto.put("openid", request.getSession().getAttribute("openid"));
		 String outMsg = ShareOrderService.addNewAdress(inDto);
		 WebCxt.write(response, outMsg);
	}
	
	/** @api {post} shopGroup/activity/v1/queryOrderPersonnel.jhtml 查询当前订单那些人参与了
	 * @apiName queryOrderPersonnel查询当前订单那些人参与了
	 * @apiGroup Personnel
	 * 
	 * @apiParam {String} pin_order_id 订单id
	 */
	/**
	 * 查询当前订单那些人参与了
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/queryOrderPersonnel")
	public void queryOrderPersonnel(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=PinTuanOrderService.queryOrderPersonnel(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	/** @api {post} shopGroup/activity/v1/shopGroupRegister.jhtml 
	 * @apiName shopGroupRegister
	 * @apiGroup Personnel
	 * 
	 * @apiParam {String} mobile 电话
	 * @apiParam {String} code 验证码
	 * @apiParam {String} openid openid
	 * @apiParam {String} type 短信类型
	 * 
	 */
	@RequestMapping(value = "/activity/v1/shopGroupRegister")
	public void shopGroupRegister(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String outMsg=shopGroupService.zeroShopGroupRegister(request, response);
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 功能正在开发中
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initError")
	public String initError(HttpServletRequest request, HttpServletResponse response){
		return "project/shopgroup/error.jsp";
	}
	
	
	/**
	 * 初始化购买页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initGoBuy")
	public String initGoBuy(HttpServletRequest request, HttpServletResponse response){
		String url = "project/shopgroup/phone_register.jsp";
		HttpModel httpModel = new HttpModel(request,response);
		Dto inDto  = httpModel.getInDto();
		ZjcGoodsPO goodDetail = wxGoodsService.getGoodsDetailByGoodsId(inDto);
		if(AOSUtils.isNotEmpty(goodDetail)){
			BigDecimal market_price=null; 
			BigDecimal a = new BigDecimal("0");
			if(a.compareTo(goodDetail.getMarket_price())==0){
				market_price=goodDetail.getShop_price();
				goodDetail.setShop_group_market_price(market_price);
			}else {
				market_price=goodDetail.getMarket_price();
			}
			httpModel.setAttribute("goodDetail", goodDetail);
			httpModel.setAttribute("market_price",market_price);
			httpModel.setAttribute("goods_id", goodDetail.getGoods_id());
			httpModel.setAttribute("store_id", goodDetail.getStore_id());
		}
		if(request.getSession().getAttribute("openid") != null){
			String openid = request.getSession().getAttribute("openid").toString();
			List<ZjcUserAddressPO> addressList = WxStoreService.getUserAddressListToGobuy(openid);
			httpModel.setAttribute("addressList", addressList);
			if(AOSUtils.isNotEmpty(addressList)){
				httpModel.setAttribute("user_id", addressList.get(0).getUser_id());
			}
			ZjcUsersInfoPO zjcUsersInfoPO = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectByOpenid", openid);
			List<ZjcVouchersPO> ZjcVouchersPO =  sqlDao.list("com.api.login.dao.ZjcVouchersDao.list",  Dtos.newDto("user_id", zjcUsersInfoPO.getUser_id()));
			if(AOSUtils.isNotEmpty(zjcUsersInfoPO)){
				httpModel.setAttribute("user_id", zjcUsersInfoPO.getUser_id());
				httpModel.setAttribute("ZjcVouchersPO", ZjcVouchersPO);
				url = "project/shopgroup/go_buy_normal.jsp";
			}else{
				url = "project/shopgroup/phone_register.jsp";
			}
		}
		httpModel.setAttribute("register_type", "normal");
		return url;
	}
	
	/**
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/orderSub")
	public void orderSub(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto inDto = httpModel.getInDto();
		 inDto.put("openid", request.getSession().getAttribute("openid"));
		 String outMsg=WxStoreService.orderSub(request, response);
		 logger.info("----------------------------------下单结果：-------------------------------"+outMsg);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * WX接口-修改支付密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/updatePayPsd")
	public void updatePayPsd(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		Dto inDto = httpModel.getInDto();
		if(inDto.getString("pay_password") != null && inDto.getString("pay_password") != ""){
			String psd = "zhongjunchuangya1212" + inDto.getString("pay_password");
			String md5psd = AOSCodec.md5(psd);
			inDto.put("pay_password", md5psd);
			request.setAttribute("user_id", request.getSession().getAttribute("user_id"));
		}
		//获取返回数据
	    String outMsg = userInfoService.updatePayPsd(httpModel);
	    WebCxt.write(response, outMsg);
	}
	
	/**
	 * 跳转我的订单
	 * 
	 * @param httpModel
	 */
	@RequestMapping(value = "/activity/v1/wxSendGoods", method=RequestMethod.GET)
	public String wxSendGoods(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		ZjcOrderPO ZjcOrderPO=new ZjcOrderPO();
		//用户ID
		//ZjcOrderPO.setUser_id(new BigInteger("1"));
		ZjcOrderPO.setUser_id(new BigInteger(request.getSession().getAttribute("user_id").toString()));
		//代发货
		List<ZjcOrderPO> zjcOrderList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getWxOrderf", ZjcOrderPO);
		//待收货
		List<ZjcOrderPO> zjcOrder = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getWxOrders", ZjcOrderPO);
		request.setAttribute("zjcOrderList", zjcOrderList);
		request.setAttribute("zjcOrder", zjcOrder);
		return "project/wxstore/my_order.jsp";
	}
}
