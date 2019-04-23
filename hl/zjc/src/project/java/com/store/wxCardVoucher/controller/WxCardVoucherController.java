/**
 * 
 */
package com.store.wxCardVoucher.controller;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSPropertiesHandler;
import aos.framework.core.utils.AOSUtils;
import aos.framework.core.utils.FileUtils;
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
import com.store.storeInfo.service.StoreInfoService;
import com.store.wxCardVoucher.dao.WxCardVoucherDao;
import com.store.wxCardVoucher.dao.po.WxCardVoucherPO;
import com.store.wxCardVoucher.service.WxCardVoucherService;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "store")
public class WxCardVoucherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WxCardVoucherController.class);
	
	@Autowired
	private WxCardVoucherService wxCardVoucherService;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private StoreInfoService storeInfoService;
	
	@Autowired
	private WxCardVoucherDao wxCardVoucherDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * 验证账号是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/checkAccount123")
	public void checkAccount123(HttpServletRequest request,HttpServletResponse response){
		MessageVO msgVo = new MessageVO(); 
		Map<String,Object> dataMap = new HashMap<String, Object>();
		HttpModel httpModel = new HttpModel(request,response);
		Dto inDto = httpModel.getInDto();
		String account = inDto.getString("account");
		ZjcUsersInfoPO userinfo = zjcUsersInfoDao.selectOne(Dtos.newDto("mobile", account));
		if(userinfo == null){
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			dataMap.put("exit", false);
			msgVo.setData(dataMap);
		}else{
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			dataMap.put("exit", true);
			msgVo.setData(dataMap);
		}
		WebCxt.write(response, AOSJson.toJson(msgVo));
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
	 * 获取微信accresstoken
	 * 
	 * @return
	 */
	public String getAccessToken(){
		Jedis jedis =  JedisUtil.getJedisClient();
        String access_token="";
		String expires_in="";
		try{
			String access_tokenStr=jedis.get("Access_token");
			logger.info("---------------------access_tokenStr:"+access_tokenStr);
			if(AOSUtils.isEmpty(access_tokenStr)){
				//获取微信accesstoken
				Authorize result = getWxAccesstoken(1);
				//Authorize result = getWxAccesstoken(2);
				if(AOSUtils.isEmpty(result)){
					return "";
				}
				access_token=result.getAccess_token();
	    		expires_in=result.getExpires_in();
				int times= ParameterUtil.dateToInt()+Integer.parseInt(expires_in);
				jedis.set("Access_token",access_token + "&" + times);
				logger.info("---------------------result1:"+AOSJson.toJson(result));
			} else {
	        	String[]  strs=access_tokenStr.split("&");
	    		String time=strs[1].toString();
	        	if(ParameterUtil.dateToInt()>Integer.parseInt(time)){
	        		//获取微信accesstoken
	        		Authorize result = getWxAccesstoken(1);
					//Authorize result = getWxAccesstoken(2);
	    			logger.info("---------------------result2:"+AOSJson.toJson(result));
	    			if(AOSUtils.isEmpty(result)){
	    				return "";
	    			}
	    			access_token=result.getAccess_token();
	        		expires_in=result.getExpires_in();
	        		if(AOSUtils.isNotEmpty(access_token)&&AOSUtils.isNotEmpty(expires_in)){
	        				jedis.del("Access_token");
	        		}
	    			int times= ParameterUtil.dateToInt()+Integer.parseInt(expires_in);
	    			jedis.set("Access_token",access_token + "&" + times);
	        	}else {
	        		access_token=strs[0].toString();
	        		logger.info("---------------------access_token:"+AOSJson.toJson(access_token));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		
		return access_token;
	}
	
	
	
	/* ==========================================微信商城代码分割线==================================================== */
	
	/**
	 * 跳转到卡卷管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/toCardVoucherList")
	public String toCardVoucherList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		Map<String, Object> map = storeInfoService.getStoreDetailByStoreId(httpModel);
		request.setAttribute("map", map);
		ZjcStorePO zjcStorePO = (ZjcStorePO)map.get("zjcStorePO");
		request.setAttribute("zjcStorePO", zjcStorePO);
		List<WxCardVoucherPO> cardVoucherList = sqlDao.list("com.store.wxCardVoucher.dao.WxCardVoucherDao.listCardVoucherList", Dtos.newDto("store_id", zjcStorePO.getStore_id()));
		request.setAttribute("cardVoucherList", cardVoucherList);
		httpModel.setViewPath("project/store/cardVoucher/toCardVoucherList.jsp");
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/serachCardVoucher")
	public void serachCardVoucher(HttpServletRequest request,HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		/*ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());*/
		List<WxCardVoucherPO> cardVoucherList = sqlDao.list("com.store.wxCardVoucher.dao.WxCardVoucherDao.listCardVoucherList", dto);
		request.setAttribute("cardVoucherList", cardVoucherList);
		WebCxt.write(response, AOSJson.toGridJson(cardVoucherList));
	}
	
	
	/**
	 * 跳转到卡卷新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/toCardVoucherAdd")
	public String toCardVoucherAdd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		Map<String, Object> map = storeInfoService.getStoreDetailByStoreId(httpModel);
		request.setAttribute("map", map);
		ZjcStorePO zjcStorePO = (ZjcStorePO)map.get("zjcStorePO");
		request.setAttribute("zjcStorePO", zjcStorePO);
		httpModel.setViewPath("project/store/cardVoucher/toCardVoucherAdd.jsp");
		return httpModel.getViewPath();
	}
	
	/**
	 * 调转到卡券logo上传界面
	 * 
	 * @param httpModel
	 * @return
	 */
	@RequestMapping(value = "/cardLogoUpload")
	public String cardLogoUpload(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String paramkey = httpModel.getInDto().getString("paramkey");
		String windowsId = httpModel.getInDto().getString("windowsId");
		String inputName = httpModel.getInDto().getString("inputName");
		String updategoods = httpModel.getInDto().getString("updategoods");
		if(StringUtils.isNotEmpty(paramkey)){
			String [] stringArr = AOSCxt.getParam(paramkey).split(":");
			Double prop = Double.parseDouble(stringArr[0]) / Double.parseDouble(stringArr[1]);
			DecimalFormat df = new DecimalFormat("#.00");   
			httpModel.setAttribute("prop",df.format(prop));
		}
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setAttribute("windowsId", windowsId);
		httpModel.setAttribute("inputName", inputName);
		httpModel.setAttribute("updategoods", updategoods);
		return "project/zjc/common/cardLogoPicUpload.jsp";
	}
	
	/**
	 * 使用img src 提交上传截取之后的图片-卡券logo上传
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "uploadCardLogo")
	public void uploadCardLogo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpModel httpModel = new HttpModel(request, response);
		String directory="";
		String savePath = httpModel.getRequest().getServletContext().getRealPath(directory)
				+ File.separator + AOSPropertiesHandler.getProperty("fileUploadPath")
				+ File.separator + "img" + File.separator + AOSUtils.getDateStr();
		String base64 = request.getParameter("img");
		File serverFile = FileUtils.convertBase64DataToImage(base64,savePath);//上传到本地
        String FilePath = serverFile.getPath();
        //获取本地上传图片的相对路径，用于展示信息
        String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
        		+ "/" +AOSPropertiesHandler.getProperty("fileUploadPath")
				+ "/img/" + AOSUtils.getDateStr();//相對路径
        String filename = FilePath.substring(FilePath.lastIndexOf("\\")+1);
        FilePath = returnUrl+"/"+filename;
        try {  
        	//获取微信accesstoken
			Authorize authorize = getWxAccesstoken(1);
			//Authorize authorize = getWxAccesstoken(2);
	        String accessToken =  authorize.getAccess_token();
	        //上传图片素材      
	        String path="https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+accessToken;  
	        String result=wxCardVoucherService.connectHttpsByPost(path, null, serverFile);  
	        result=result.replaceAll("[\\\\]", "");  
	        JSONObject outjson = JSONObject.parseObject(result);
			logger.info("---------------------out"+AOSJson.toJson(outjson));
			String outrl = outjson.get("url") + "";
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("path", outrl);
	        map.put("filePath", FilePath.toString());
	        response.getWriter().print(AOSJson.toJson(map));
	    } catch (Exception e) {  
	        e.printStackTrace(); 
	    }finally{ 
	    	
	    }  
	}
	
	/**
	 * 新增卡券
	 * 
	 * @param httpModel
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value = "/addCardInfo")
	public void addCardInfo(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		Map<String,Object> map = Maps.newHashMap();
		Map<String,String> map1 = Maps.newHashMap();
		Map<String,Object> condimap = wxCardVoucherService.getCardConditionMap(dto);
		//获取微信accesstoken
		Authorize authorize = getWxAccesstoken(1);
		//Authorize authorize = getWxAccesstoken(2);
        String accessToken =  authorize.getAccess_token();
        map.put("card", condimap);
		String httpOrgCreateTest = "https://api.weixin.qq.com/card/create?access_token="+accessToken; 
 	    HttpClientUtil httpClientUtil = new HttpClientUtil();
 	    String httpOrgCreateTestRtn = httpClientUtil.httpReqUrl(AOSJson.toJson(map),httpOrgCreateTest);
 	    JSONObject j = JSONObject.parseObject(httpOrgCreateTestRtn);
		logger.info("新增卡券返回值:"+j.toJSONString());
		
		if("0".equals(j.get("errcode").toString())){
			dto.put("card_id", j.get("card_id"));
			String msgstr = wxCardVoucherService.addCardInfo(dto);
			WebCxt.write(response, msgstr);
			
		} else {
			MessageVO msg = new MessageVO();
			msg.setCode(-1);
			msg.setMsg("卡券新增失败，请确认输入是否正确");
			WebCxt.write(response, AOSJson.toJson(msg));
			return;
		}
		
	}
	
	/**
	 * 创建二维码投放
	 * 
	 * @param request
	 * @param response
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value = "/createCode")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		HttpModel httpModel = new HttpModel(request, response);
		Dto dto = httpModel.getInDto();
		//封装创建二维码投放接口请求参数
		Map<String,Object> map = Maps.newHashMap();
		Map<String,String> mapcard = Maps.newHashMap();
		mapcard.put("card_id", dto.getString("card_id"));
		Map<String,Object> mapinfo = Maps.newHashMap();
		mapinfo.put("card", mapcard);
		map.put("action_info", mapinfo);
		map.put("action_name", "QR_CARD");
		//获取微信accesstoken
		Authorize authorize = getWxAccesstoken(1);
		//Authorize authorize = getWxAccesstoken(2);
        String accessToken =  authorize.getAccess_token();
		String httpOrgCreateTest = "https://api.weixin.qq.com/card/qrcode/create?access_token="+accessToken; 
 	    HttpClientUtil httpClientUtil = new HttpClientUtil();
 	    String httpOrgCreateTestRtn = httpClientUtil.httpReqUrl(AOSJson.toJson(map),httpOrgCreateTest);
 	    JSONObject j = JSONObject.parseObject(httpOrgCreateTestRtn);
		logger.info("创建二维码投放返回值:"+j.toJSONString());
		
		if("0".equals(j.get("errcode").toString())){
			dto.put("show_qrcode_url", j.get("show_qrcode_url"));
			String msgstr = wxCardVoucherService.createCode(dto);
			WebCxt.write(response, msgstr);
		} else {
			MessageVO msg = new MessageVO();
			msg.setCode(-1);
			msg.setMsg("创建二维码失败，请稍后再试");
			WebCxt.write(response, AOSJson.toJson(msg));
			return;
		}
		
	}
}
