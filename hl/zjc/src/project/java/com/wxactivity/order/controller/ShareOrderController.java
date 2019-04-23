/**
 * 
 */
package com.wxactivity.order.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.wxactivity.goods.service.WxActivityGoodsService;
import com.wxactivity.order.service.ShareOrderService;
import com.wxactivity.share.service.ShareService;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class ShareOrderController {
	@Autowired
	private ShareOrderService ShareOrderService;
	@Autowired
	private WxActivityGoodsService wxActivityGoodsService;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ShareService shareService;
	
	/** @api {post} api/activity/v1/orderSub.jhtml 提交商品页面
	 * @apiName 提交商品页面
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
	 */
	/**
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/orderSub")
	public void orderSub(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 String outMsg=ShareOrderService.orderSub(request, response);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * 初始化兑换页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initOrderSave")
	public String initOrderSave(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		ZjcGoodsPO zjcGoodsPO = wxActivityGoodsService.getGoodsDetailByGoodsId(httpModel.getInDto());
		httpModel.setAttribute("zjcGoodsPO", zjcGoodsPO);
		String openid = request.getSession().getAttribute("openid") == null ? null  : request.getSession().getAttribute("openid").toString();
		if(AOSUtils.isNotEmpty(zjcGoodsPO)){
			httpModel.setAttribute("store_id", AOSUtils.isEmpty(zjcGoodsPO.getStore_id()) ? 1 : zjcGoodsPO.getStore_id());
			httpModel.setAttribute("total_amount", zjcGoodsPO.getMarket_price());
		}
		if(AOSUtils.isNotEmpty(zjcGoodsPO) && AOSUtils.isNotEmpty(openid)){
			Integer typeSum = shareService.is_receive(openid, zjcGoodsPO.getGoods_id().toString());
			if(AOSUtils.isEmpty(typeSum) || typeSum == 0){
				httpModel.setAttribute("is_typeSum", true);
			}else{
				httpModel.setAttribute("is_typeSum", false);
			}
		}
		List<ZjcUserAddressPO> addressList = ShareOrderService.getUserAddressListToGobuy(openid);
		httpModel.setAttribute("addressList", addressList);
		if(addressList != null && addressList.size() > 0 ){
			httpModel.setAttribute("address_id", addressList.get(0).getAddress_id());
		}
		httpModel.setAttribute("user_id", httpModel.getInDto().getString("user_id"));
		return "project/wxactivity/share_address.jsp";
	}
	
	/**
	 * 初始化收货地址设置页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initSettingAddress")
	public String initSettingAddress(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		if(AOSUtils.isNotEmpty(request.getSession().getAttribute("user_id"))){
			String user_id = request.getSession().getAttribute("user_id").toString();
			ZjcUsersInfoPO userInfoPO = zjcUsersInfoDao.selectByKey(new BigInteger(user_id));
			httpModel.setAttribute("userInfoPO", userInfoPO);
			ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(new BigInteger(user_id));
			httpModel.setAttribute("zjcUsersAccountInfoPO", zjcUsersAccountInfoPO);
		}
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("goods_id"))){
			httpModel.setAttribute("goods_id", httpModel.getInDto().getString("goods_id"));
		}
		return "project/wxactivity/setting_address.jsp";
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
}
