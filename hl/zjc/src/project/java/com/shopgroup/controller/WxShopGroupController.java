/**
 * 
 */
package com.shopgroup.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.httpclient.AOSHttpClient;
import aos.framework.web.httpclient.HttpRequestVO;
import aos.framework.web.httpclient.HttpResponseVO;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.HttpClientUtil;
import com.api.common.util.ParameterUtil;
import com.api.wx.wxConstant.Authorize;
import com.api.wx.wxConstant.WxConstant;
import com.api.wx.wxConstant.WxUserResult;
import com.gexin.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/shopGroup")
public class WxShopGroupController {
	
	private static Logger logger = LoggerFactory.getLogger(WxShopGroupController.class);
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	/**
	 * 微信0元购商城获取微信code
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getShopGroupCode")
	public void getShopGroupCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		//获取访问的域名地址
		String tempContextUrl = "https://zjc1518.com/aosuite/";
//		String tempContextUrl = "http://wexin.web.zjc1518.cn/aosuite/";
		String urla = tempContextUrl +"shopGroup/toShopGroup.jhtml";
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConstant.WEB_ID + "&redirect_uri="+urla+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConstant.TEST_WEB_ID + "&redirect_uri="+urla+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
	}
	
	
	/**
	 * 微信0元购活动获取unionid
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/toShopGroup")
	public String toShopGroup(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		if(AOSUtils.isEmpty(request.getParameter("code"))){
			return httpModel.getViewPath();
		}
		Authorize result = getWxAccesstoken(request.getParameter("code").toString(),1);
//		Authorize result = getWxAccesstoken(request.getParameter("code").toString(),2);
		if(AOSUtils.isEmpty(result)){
			return httpModel.getViewPath();
		}
		logger.info("========================微信0元购活动获取微信openid以及access_token：======================:" + AOSJson.toJson(result));
		//获取访问的域名地址
		String urla = "https://zjc1518.com/aosuite";
//		String urla = "http://wexin.web.zjc1518.cn/aosuite";
		if(AOSUtils.isEmpty(result.getOpenid()) || AOSUtils.isEmpty(result.getAccess_token())){
			return httpModel.getViewPath();
		}
		WxUserResult  wxuser = getWxUserInfo(result.getAccess_token(), result.getOpenid());
		logger.info("----------------------------微信0元购活动获取微信基本用户信息：-------------" + AOSJson.toJson(wxuser));
		if(AOSUtils.isEmpty(wxuser)|| AOSUtils.isEmpty(wxuser.getUnionid())){
			return httpModel.getViewPath();
		}
		request.getSession().setAttribute("openid", wxuser.getUnionid());//openid存session
		request.getSession().setAttribute("wxuser", AOSJson.toJson(wxuser));//openid存session
		ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", wxuser.getUnionid()));
		if(AOSUtils.isNotEmpty(userInfo)){//绑定了账号
			request.getSession().setAttribute("user_id", userInfo.getUser_id());//user_id存session
		} 
		if(StringUtils.equals(request.getSession().getAttribute("type").toString(), "spellGoBuy")){//跳转到商品详情页面
			//跳转0元购下单
			if(AOSUtils.isNotEmpty(request.getSession().getAttribute("pin_order_id"))){
				response.sendRedirect(urla + "/shopGroup/activity/v1/initSpellGoBuy.jhtml?goods_id=" + request.getSession().getAttribute("goods_id")
						+ "&pin_order_id=" + request.getSession().getAttribute("pin_order_id"));
			}else{
				response.sendRedirect(urla + "/shopGroup/activity/v1/initSpellGoBuy.jhtml?goods_id=" + request.getSession().getAttribute("goods_id"));
			}
		} else if(StringUtils.equals(request.getSession().getAttribute("type").toString(), "normalGoBuy")){//跳转到分享页面
			//跳转正常下单
			response.sendRedirect(urla + "/shopGroup/activity/v1/initGoBuy.jhtml?goods_id=" + request.getSession().getAttribute("goods_id"));
		}else {//正常页面
			response.sendRedirect(urla + request.getSession().getAttribute("methodStr").toString());
		}
		return  httpModel.getViewPath();
	
	}
	
	/**
	 * 获取accesstoken和openid
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public Authorize getWxAccesstoken(String code, int type){
		String app_id = WxConstant.APP_ID;
		String app_secret = WxConstant.APP_SECRET;
		if(type == 1){
			app_id = WxConstant.WEB_ID;
			app_secret = WxConstant.WEB_SECRET;
		}
		if(type == 2){
			app_id = WxConstant.TEST_WEB_ID;
			app_secret = WxConstant.TEST_WEB_SECRET;
		}
		Map<String, String> inMap = Maps.newHashMap();
		inMap.put("appid", app_id);
		inMap.put("secret", app_secret);
		inMap.put("code", code);
		inMap.put("grant_type", "authorization_code");
		HttpRequestVO httpRequestVO = new HttpRequestVO("https://api.weixin.qq.com/sns/oauth2/access_token", inMap);
		HttpResponseVO httpResponseVO = AOSHttpClient.execute(httpRequestVO);
		String out = httpResponseVO.getOut();
		Authorize result = AOSJson.fromJson(out, Authorize.class);
		return result;
	}
	
	/**
	 * 根据access_token 和 openid 获取微信用户的信息
	 * 
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public WxUserResult getWxUserInfo(String access_token,String openid){
		Map<String, String> inMap = Maps.newHashMap();
		inMap.put("access_token", access_token);
		inMap.put("openid", openid);
		HttpRequestVO httpRequestVO = new HttpRequestVO("https://api.weixin.qq.com/sns/userinfo", inMap);
		HttpResponseVO httpResponseVO = AOSHttpClient.execute(httpRequestVO);
		String out = httpResponseVO.getOut();
		WxUserResult result = AOSJson.fromJson(out, WxUserResult.class);
		return result;
	}
	
	
	/**
	 * 页面获取jsapi_ticket
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/activity/v1/getJsapiTicket")
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
        String params = "grant_type=client_credential&appid=" + WxConstant.TEST_WEB_ID + "&secret=" + WxConstant.TEST_WEB_SECRET + "";  
        //String params = "grant_type=client_credential&appid=" + WxConstant.WEB_ID + "&secret=" + WxConstant.WEB_SECRET + "";  
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
}
