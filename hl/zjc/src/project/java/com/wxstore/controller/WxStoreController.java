/**
 * 
 */
package com.wxstore.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import redis.clients.jedis.Jedis;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.httpclient.AOSHttpClient;
import aos.framework.web.httpclient.HttpRequestVO;
import aos.framework.web.httpclient.HttpResponseVO;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.HttpClientUtil;
import com.api.common.util.ParameterUtil;
import com.api.order.OrderService;
import com.api.userInfo.UserInfoService;
import com.api.wx.wxConstant.Authorize;
import com.api.wx.wxConstant.WxConstant;
import com.gexin.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.store.wxCardVoucher.dao.po.WxCardVoucherPO;
import com.wxstore.service.WxStoreService;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.shareGoods.service.ShareGoodsService;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcMessagePO;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/notokenapi")
public class WxStoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(WxStoreController.class);
	
	@Autowired
	private WxStoreService WxStoreService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private OrderService OrderService;
	@Autowired
	private IdService idService;
	@Autowired
	private ShareGoodsService shareGoodsService;
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	
	/**
	 * 登录页面初始化
	 * 
	 * @param httpModel
	 */
	@RequestMapping(value = "/wx/v1/initWxZjcLogin")
	public String initWxZjcLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("juid", idService.rawUuid());
		httpModel.setAttribute("page", 1);
		PageVO PageVO = WxStoreService.WxStoreGoods(httpModel);
		List<ZjcMessagePO> message = sqlDao.list("com.zjc.users.dao.ZjcMessageDao.list", null);
		List<Dto> goodslist = sqlDao.list("com.zjc.ad.dao.ZjcAdDao.list1", null);
		httpModel.getRequest().setAttribute("PageVO", PageVO);
		httpModel.getRequest().setAttribute("message", message);
		httpModel.getRequest().setAttribute("goodslist", goodslist);
		return "project/wxstore/index.jsp";
	}
	
	/**
	 * 跳转支付宝页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/initAlipay")
	public String initAlipay(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("order_sn", request.getParameter("order_sn"));
		return "project/wxstore/alipay.jsp";
	}	
	
	/**
	 * 微信支付界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/initweixinPay")
	public String initweixinPay(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("order_sn", request.getParameter("order_sn"));
		return "project/wxstore/weixinpay.jsp";
	}	
	//查询商品方法
	@RequestMapping(value = "/wx/v1/WxStoreGoods", method=RequestMethod.GET)
	public void WxStoreGoods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("page", request.getParameter("page"));
		PageVO PageVO = WxStoreService.WxStoreGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(PageVO));
	}
	
	@RequestMapping(value = "/wx/v1/wxGoodsSearch")
	public String wxGoodsSearch(HttpServletRequest request, HttpServletResponse response) {
		return "project/wxstore/goods_search.jsp";
	}
	
	
	//通过关键字或者商品名字查询商品
	@RequestMapping(value = "/wx/v1/queryWxStoreGoods", method=RequestMethod.GET)
	public void queryWxStoreGoods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
		ZjcGoodsPO.setGoods_name(request.getParameter("goods_name"));
		List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodsWxStore", ZjcGoodsPO);
		httpModel.getRequest().setAttribute("goodslist", goodslist);
		WebCxt.write(response, AOSJson.toJson(goodslist));
	}
	
	
	/**
	 * 跳转我的订单
	 * 
	 * @param httpModel
	 */
	@RequestMapping(value = "/wx/v1/wxSendGoods", method=RequestMethod.GET)
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
	
	
	/**
	 * 跳转商品详情
	 */
	@RequestMapping(value = "/wx/v1/goodsDetails")
	public String goodsDetails(HttpServletRequest request,HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodDetail = shareGoodsService.getGoodsDetailByGoodsId(dto);
		httpModel.setAttribute("goodDetail", goodDetail);
		httpModel.setAttribute("user_id", request.getSession().getAttribute("user_id"));
		httpModel.setAttribute("openid", request.getSession().getAttribute("openid"));
		return "project/wxstore/goodsDetails.jsp";
	}
	
	/**
	 * 获取用户收货地址
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wx/v1/getUserAddressList")
	public void getUserAddressList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		List<ZjcUserAddressPO> addressList  = new ArrayList<ZjcUserAddressPO>();
		String outMsg = "";
		if(request.getSession().getAttribute("openid") != null){
			outMsg = WxStoreService.getUserAddressList(request.getSession().getAttribute("openid").toString());
		}
		WebCxt.write(response, outMsg);
	}
	
	/**
	 * 提交商品页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/orderSub")
	public void orderSub(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto inDto = httpModel.getInDto();
		 inDto.put("openid", request.getSession().getAttribute("openid"));
		 String outMsg=WxStoreService.orderSub(request, response);
		 logger.info("----------------------------------下单结果：-------------------------------"+outMsg);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * 微信端买酒支付成功跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/toWinePaySuccess")
	public String toWinePaySuccess(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto inDto = httpModel.getInDto();
		double pzPrice = 41.88;
		httpModel.setAttribute("total_pz_price", pzPrice*inDto.getInteger("buyNum"));
		httpModel.setAttribute("pz_num", 6*inDto.getInteger("buyNum"));
		httpModel.setAttribute("order_sn", inDto.getString("order_sn"));
		return "project/wxstore/confirmOrder.jsp";
	}
	
	/**
	 * 新增收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wx/v1/saveAddress")
	public void saveAddress(HttpServletRequest request, HttpServletResponse response){
		 HttpModel httpModel = new HttpModel(request, response);
		 Dto inDto = httpModel.getInDto();
		 inDto.put("openid", request.getSession().getAttribute("openid"));
		 String outMsg = WxStoreService.addNewAdress(inDto);
		 WebCxt.write(response, outMsg);
	}
	
	/**
	 * 初始化密码修改
	 */
	@RequestMapping(value = "/wx/v1/initPayPwd")
	public String initPayPwd(HttpServletRequest request,HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		String user_id = request.getSession().getAttribute("user_id").toString();
		ZjcUsersInfoPO userInfoPO = zjcUsersInfoDao.selectByKey(new BigInteger(user_id));
		httpModel.setAttribute("userInfoPO", userInfoPO);
		ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(new BigInteger(user_id));
		httpModel.setAttribute("zjcUsersAccountInfoPO", zjcUsersAccountInfoPO);
		return "project/wxstore/pay_pwd.jsp";
	}
	
	/**
	 * WX接口-修改支付密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/updatePayPsd")
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
	 * 初始化购买页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/initGoBuy")
	public String initGoBuy(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto inDto  = httpModel.getInDto();
		ZjcGoodsPO goodDetail = shareGoodsService.getGoodsDetailByGoodsId(inDto);
		httpModel.setAttribute("goodDetail", goodDetail);
	    if(request.getSession().getAttribute("openid") != null){
			String openid = request.getSession().getAttribute("openid").toString();
			List<ZjcUserAddressPO> addressList = WxStoreService.getUserAddressListToGobuy(openid);
			httpModel.setAttribute("addressList", addressList);
		}
		//return "project/wxstore/go_buy.jsp";
		return "project/wxstore/pay_page.jsp";
	}
	
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wx/v1/confirm_order")
	public void confirm_order(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		request.setAttribute("user_id", request.getSession().getAttribute("user_id"));
		//获取返回的数据
		String outMsg=OrderService.confirm_order(httpModel);
		//返回数据
		WebCxt.write(response, outMsg);
	}
	
	
	/**
	 * 初始化分享助力页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/initShareHelp")
	public String initShareHelp(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setAttribute("goods_id", httpModel.getInDto().getString("goods_id"));
		httpModel.setAttribute("user_id", httpModel.getInDto().getString("user_id"));
		request.getSession().setAttribute("share_openid", httpModel.getInDto().getString("share_openid"));
		return "project/zjc/wx/wxShareHelp.jsp";
	}
	
	
	/**
	 * 获取accesstoken和openid
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public Authorize getWxAccesstoken(int type){
		String app_id = WxConstant.APP_ID;
		String app_secret = WxConstant.APP_SECRET;
		if(type == 1){
			app_id = WxConstant.WEB_ID;
			app_secret = WxConstant.WEB_SECRET;
		}
		if(type == 2){//测试账号
			app_id = WxConstant.TEST_WEB_ID;
			app_secret = WxConstant.TEST_WEB_SECRET;
		}
		Map<String, String> inMap = Maps.newHashMap();
		inMap.put("appid", app_id);
		inMap.put("secret", app_secret);
		inMap.put("grant_type", "client_credential");
		HttpRequestVO httpRequestVO = new HttpRequestVO("https://api.weixin.qq.com/cgi-bin/token", inMap);
		HttpResponseVO httpResponseVO = AOSHttpClient.execute1(httpRequestVO);
		String out = httpResponseVO.getOut();
		Authorize result = AOSJson.fromJson(out, Authorize.class);
		
		return result;
	}
	
	/**
	 * 跳转到用户卡券列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value = "/wx/v1/initCardVouchers")
	public void initCardVouchers(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException{
		request.getSession().setAttribute("c_open_id", "o8SwTv5ifMOGw_-VAPR6_1eLALfQ");
		String openid = request.getSession().getAttribute("c_open_id").toString();
		HttpModel httpModel = new HttpModel(request,response);
		//封装创建二维码投放接口请求参数
		Map<String,Object> map = Maps.newHashMap();
		Map<String,String> mapcard = Maps.newHashMap();
		map.put("openid", openid);
		//获取微信accesstoken
		Authorize authorize = getWxAccesstoken(1);
//		Authorize authorize = getWxAccesstoken(2);
        String accessToken =  authorize.getAccess_token();
		String httpOrgCreateTest = "https://api.weixin.qq.com/card/user/getcardlist?access_token="+accessToken; 
		HttpClientUtil httpClientUtil = new HttpClientUtil();
 	    String httpOrgCreateTestRtn = httpClientUtil.httpReqUrl(AOSJson.toJson(map),httpOrgCreateTest);
 	    JSONObject j = JSONObject.parseObject(httpOrgCreateTestRtn);
 	    List<WxCardVoucherPO> newcardvoucherList =  new ArrayList<WxCardVoucherPO>();
 	    logger.info("创建二维码投放返回值:"+j.toJSONString());
 	    if(AOSUtils.isNotEmpty(j.get("card_list"))){//有卡券列表
 			List<Dto> list = AOSJson.fromJson(j.get("card_list").toString());
 			List<WxCardVoucherPO> cardvoucherList = sqlDao.list("com.store.wxCardVoucher.dao.WxCardVoucherDao.list", Dtos.newDto("store_id", httpModel.getInDto().getString("store_id")));
 			if(cardvoucherList.size()>0 && list.size()>0){
 				for(Dto dto :list){
 					for(WxCardVoucherPO cardv :cardvoucherList){
 						if(dto.getString("card_id").equals(cardv.getCard_id())){
 							newcardvoucherList.add(cardv);
 						}
 					}
 				}
 			}
 	    }
		httpModel.setAttribute("cardvoucherList", newcardvoucherList);
		WebCxt.write(response, AOSJson.toGridJson(newcardvoucherList));
	}
	
	
	/**
	 * 获取拉取参数
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wx/v1/getWxCardParams")
	public void getWxCardParams(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpmodel = new HttpModel(request, response);
		Dto dto = httpmodel.getInDto();
		String jsapi_ticket = dto.getString("jsapi_ticket");
		String timestamp = dto.getString("timestamp");
		String noncestr = dto.getString("noncestr");
		String card_type = "DISCOUNT";
		String app_id = "wx3327e0b6cbf7a8d8";
		String card_id = dto.getString("card_id");
		String[] ArrTmp;
		if(AOSUtils.isNotEmpty(card_id)){
			ArrTmp = new String[]{jsapi_ticket,timestamp,noncestr,card_type,app_id,card_id}; 
		} else {
			ArrTmp = new String[]{jsapi_ticket,timestamp,noncestr,card_type,app_id}; 
		}
	    Arrays.sort(ArrTmp); 
	    StringBuffer sf = new StringBuffer(); 
	    for(int i=0;i<ArrTmp.length;i++){ 
	        sf.append(ArrTmp[i]); 
	    }
	    String pjStr = sf.toString();
	    String signature =SHA1(pjStr);  
		//返回数据
		WebCxt.write(response, signature);
	}
	
	
	/**
	 * SHA加密
	 * 
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {  
	    try {  
	        MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");  
	        digest.update(decript.getBytes());  
	        byte messageDigest[] = digest.digest();  
	        // Create Hex String  
	        StringBuffer hexString = new StringBuffer();  
	        // 字节数组转换为 十六进制 数  
	            for (int i = 0; i < messageDigest.length; i++) {  
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
	                if (shaHex.length() < 2) {  
	                    hexString.append(0);  
	                }  
	                hexString.append(shaHex);  
	            }  
	            return hexString.toString();  
	   
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	}  
	
	/**
	 * 核销卡券
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value = "/wx/v1/cancelWxCard")
	public void cancelWxCard(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException{
		HttpModel httpmodel = new HttpModel(request, response);
		Dto dto = httpmodel.getInDto();
		MessageVO msgvo = new MessageVO();
		String encrypt_code = dto.getString("encrypt_code");
		logger.info("---------------encrypt_code:" + encrypt_code);
		String card_id = dto.getString("card_id");
		Map<String,Object> map = Maps.newHashMap();
		//获取微信accesstoken
		Authorize authorize = getWxAccesstoken(1);
		//Authorize authorize = getWxAccesstoken(2);
        String accessToken =  authorize.getAccess_token();
        encrypt_code = URLEncoder.encode(encrypt_code,"UTF-8");
        logger.info("---------------encrypt_code:" + encrypt_code);
        map.put("encrypt_code", encrypt_code);
        //解码code
		String httpOrgCreateTest = "https://api.weixin.qq.com/card/code/decrypt?access_token="+accessToken; 
 	    HttpClientUtil httpClientUtil = new HttpClientUtil();
 	    String httpOrgCreateTestRtn = httpClientUtil.httpReqUrl(AOSJson.toJson(map),httpOrgCreateTest);
 	    JSONObject codeobj = JSONObject.parseObject(httpOrgCreateTestRtn);
		logger.info("Code解码接口返回值:"+codeobj.toJSONString());
		
		if("0".equals(codeobj.get("errcode").toString())){//code解码成功
			String code = codeobj.get("code").toString();
			//查询code接口，判断状态是否正常
			httpOrgCreateTest =  "https://api.weixin.qq.com/card/code/get?access_token="+accessToken; 
			httpClientUtil = new HttpClientUtil();
			map = Maps.newHashMap();
			map.put("card_id", card_id);
			map.put("code", code);
			map.put("check_consume", true);
	 	    httpOrgCreateTestRtn = httpClientUtil.httpReqUrl(AOSJson.toJson(map),httpOrgCreateTest);
	 	    JSONObject codedetailobj = JSONObject.parseObject(httpOrgCreateTestRtn);
			logger.info("Code解码接口返回值:"+codedetailobj.toJSONString());
			if("0".equals(codedetailobj.get("errcode").toString())){//查询code返回结果,状态正常，可以核销，调用核销逻辑
				//调用卡券核销接口
				httpOrgCreateTest =  "https://api.weixin.qq.com/card/code/consume?access_token="+accessToken; 
				httpClientUtil = new HttpClientUtil();
				map = Maps.newHashMap();
				map.put("code", code);
		 	    httpOrgCreateTestRtn = httpClientUtil.httpReqUrl(AOSJson.toJson(map),httpOrgCreateTest);
		 	    JSONObject cancelobj = JSONObject.parseObject(httpOrgCreateTestRtn);
				logger.info("卡券核销接口返回值:"+cancelobj.toJSONString());
				if("0".equals(cancelobj.get("errcode").toString())){//核销成功
					msgvo.setCode(Apiconstant.Do_Success.getIndex());
					msgvo.setMsg("核销成功");
					WebCxt.write(response, AOSJson.toJson(msgvo));
					return;
				} else {
					msgvo.setCode(-1);
					msgvo.setMsg("核销失败");
					WebCxt.write(response, AOSJson.toJson(msgvo));
					return;
				}
			} else {
				msgvo.setCode(-1);
				msgvo.setMsg("核销失败:卡券状态异常");
				WebCxt.write(response, AOSJson.toJson(msgvo));
				return;
			}
		} else {
			msgvo.setCode(-1);
			msgvo.setMsg("核销失败:code解码失败");
			WebCxt.write(response, AOSJson.toJson(msgvo));
			return;
		}
	}
	
	/**
	 * 页面获取jsapi_ticket
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wx/v1/getJsapiTicket")
	public void getJsapiTicket(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVo = new MessageVO();
		String jsapi_ticket="";
		Jedis jedis = JedisUtil.getJedisClient();
		try{
			String jsapi_ticketStr = jedis.get("jsapi_ticket");
	        if(AOSUtils.isEmpty(jsapi_ticketStr)){  
	            jsapi_ticket=getOneJsapiTicket();  
	        }else{  
	        	String[] jsapi_ticketStrArr = jsapi_ticketStr.split(",");
	        	//获取原jsapi_ticket的获取时间
	        	int validate = Integer.parseInt(jsapi_ticketStrArr[2].toString());
	        	//获取原jsapi_ticket的有效期
				int validateTime = Integer.parseInt(jsapi_ticketStrArr[1].toString());
				//获取当前时间(10位数的int型数字)
	            int nowdate= ParameterUtil.dateToInt();
				if(nowdate<=validate+validateTime){//jsapi_ticket有效期内
					jsapi_ticket = jsapi_ticketStrArr[0].toString();
				} else {//有效期外，重新获取jsapi_ticket
					jsapi_ticket=getOneJsapiTicket();
				}
	        } 
	        msgVo.setCode(Apiconstant.Do_Success.getIndex());
	        msgVo.setMsg(Apiconstant.Do_Success.getName());
	        msgVo.setData(jsapi_ticket);
		} catch (Exception e) {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
		    msgVo.setMsg(Apiconstant.Do_Fails.getName());
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		
	
		//返回数据
		WebCxt.write(response, AOSJson.toJson(msgVo));
	}
	
	
	/**
	 * 获取jsapi_ticket存redis
	 * 
	 * @return
	 */
	private String getOneJsapiTicket(){  
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?";  
        String params = "grant_type=client_credential&appid=" + WxConstant.WEB_ID + "&secret=" + WxConstant.WEB_SECRET + ""; 
//        String params = "grant_type=client_credential&appid=" + WxConstant.TEST_WEB_ID + "&secret=" + WxConstant.TEST_WEB_SECRET + "";
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String result = httpClientUtil.httpGet(requestUrl+params);  
        String access_token = JSONObject.parseObject(result).getString("access_token");  
        requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";  
        params = "access_token=" + access_token + "&type=jsapi";  
        result = httpClientUtil.httpGet(requestUrl+params);  
        String jsapi_ticket = JSONObject.parseObject(result).getString("ticket");  
        int activeTime=Integer.parseInt(JSONObject.parseObject(result).getString("expires_in"));  
        Jedis jedis = JedisUtil.getJedisClient();
        //获取当前系统时间的10位数
        int nowTime= ParameterUtil.dateToInt();
		try{
			jedis.del("jsapi_ticket");//散出原jsapi_ticket缓存
			jedis.set("jsapi_ticket", jsapi_ticket + "," + activeTime + "," + nowTime);//重新生成新的jsapi_ticket缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
        return jsapi_ticket;  
    }  
	
	/**
	 * 页面获取wx_card_ticket
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wx/v1/getWxCardTicket")
	public void getWxCardTicket(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVo = new MessageVO();
		String jsapi_ticket="";
		Jedis jedis = JedisUtil.getJedisClient();
		try{
			String jsapi_ticketStr = jedis.get("wx_card_ticket");
	        if(AOSUtils.isEmpty(jsapi_ticketStr)){  
	            jsapi_ticket=getOneWxCardTicket();  
	        }else{  
	        	String[] jsapi_ticketStrArr = jsapi_ticketStr.split(",");
	        	//获取原jsapi_ticket的获取时间
	        	int validate = Integer.parseInt(jsapi_ticketStrArr[2].toString());
	        	//获取原jsapi_ticket的有效期
				int validateTime = Integer.parseInt(jsapi_ticketStrArr[1].toString());
				//获取当前时间(10位数的int型数字)
	            int nowdate= ParameterUtil.dateToInt();
				if(nowdate<=validate+validateTime){//jsapi_ticket有效期内
					jsapi_ticket = jsapi_ticketStrArr[0].toString();
				} else {//有效期外，重新获取jsapi_ticket
					jsapi_ticket=getOneWxCardTicket();
				}
	        } 
	        msgVo.setCode(Apiconstant.Do_Success.getIndex());
	        msgVo.setMsg(Apiconstant.Do_Success.getName());
	        msgVo.setData(jsapi_ticket);
		} catch (Exception e) {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
		    msgVo.setMsg(Apiconstant.Do_Fails.getName());
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		
	
		//返回数据
		WebCxt.write(response, AOSJson.toJson(msgVo));
	}
	
	
	/**
	 * 获取wx_card_ticket存redis
	 * 
	 * @return
	 */
	private String getOneWxCardTicket(){  
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?";  
        String params = "grant_type=client_credential&appid=" + WxConstant.WEB_ID + "&secret=" + WxConstant.WEB_SECRET + ""; 
//        String params = "grant_type=client_credential&appid=" + WxConstant.TEST_WEB_ID + "&secret=" + WxConstant.TEST_WEB_SECRET + "";
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String result = httpClientUtil.httpGet(requestUrl+params);  
        String access_token = JSONObject.parseObject(result).getString("access_token");  
        requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";  
        params = "access_token=" + access_token + "&type=wx_card";  
        result = httpClientUtil.httpGet(requestUrl+params);  
        String wx_card_ticket = JSONObject.parseObject(result).getString("ticket");  
        int activeTime=Integer.parseInt(JSONObject.parseObject(result).getString("expires_in"));  
        Jedis jedis = JedisUtil.getJedisClient();
        //获取当前系统时间的10位数
        int nowTime= ParameterUtil.dateToInt();
		try{
			jedis.del("wx_card_ticket");//散出原jsapi_ticket缓存
			jedis.set("wx_card_ticket", wx_card_ticket + "," + activeTime + "," + nowTime);//重新生成新的jsapi_ticket缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
        return wx_card_ticket;  
    }  
	
}
