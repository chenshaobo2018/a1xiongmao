/**
 * 
 */
package com.wxactivity.share.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.HttpClientUtil;
import com.api.common.util.ParameterUtil;
import com.api.wx.wxConstant.WxConstant;
import com.gexin.fastjson.JSONObject;
import com.wxactivity.goods.service.WxActivityGoodsService;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.users.dao.ZjcMessageDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author HP
 *
 */
@Controller
@RequestMapping("/wxact")
public class PowerController {
	
	private static final Logger logger = LoggerFactory.getLogger(PowerController.class);
	
	@Autowired
	private WxActivityGoodsService wxActivityGoodsService;
	@Autowired
	private ZjcMessageDao messageDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	/**
	 * 进入活动首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/v1/init")
	public String init(HttpServletRequest request, HttpServletResponse response){
		return "project/wxactivity/index.jsp";
	}
	
	/**
	 * 被分享人进入助力详情界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/v1/initShareDetail")
	public String initShareDetail(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("goods_id"))){
			zjcGoodsPO = wxActivityGoodsService.getGoodsDetailByGoodsId(httpModel.getInDto());
		}
		httpModel.setAttribute("open_id",httpModel.getRequest().getSession().getAttribute("openid"));
		logger.info("----------------------shareID---------------------:"+httpModel.getInDto().getString("shareId"));
		httpModel.setAttribute("shareId",httpModel.getInDto().getString("shareId"));
		httpModel.setAttribute("goods_id",httpModel.getInDto().getString("goods_id"));
		httpModel.setAttribute("zjcGoodsPO", zjcGoodsPO);
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("shareId"))){
			ZjcUsersInfoPO zInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", httpModel.getInDto().getString("shareId")));
			httpModel.setAttribute("zjcUsersInfoPO", zInfoPO);
		}
		return "project/wxactivity/share_detail.jsp";
	}
	
	/**
	 * 进入助力累计页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initShareInte")
	public String initShareInte(HttpServletRequest request){
		return "project/wxactivity/share_inte.jsp";
	}
	
	/**
	 * 获取资讯公告
	 */
	@RequestMapping(value = "/activity/v1/getNotice")
	public void getNotice(HttpServletRequest request, HttpServletResponse response) {
		Dto dto = Dtos.newInDto(request);
		MessageVO msgVO = new MessageVO();
	//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		List<Dto> messagePOs = messageDao.listAllPage(dto);
	//生成返回分页参数实体
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		if(AOSUtils.isEmpty(messagePOs)){
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			pageVO.setList(messagePOs);
			msgVO.setData(pageVO);
		}
		WebCxt.write(response, AOSJson.toJson(pageVO));
	}
	
	/**
	 * 初始化抽奖页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initLockDraw")
	public String initLuckDraw(HttpServletRequest request, HttpServletResponse response){
		return "project/wxactivity/register.jsp";
	}
	
	/**
	 * 点击参加助力活动，返回首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/goInit")
	public String goInit(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		httpModel.setAttribute("goods_id", request.getParameter("goods_id"));
		httpModel.setAttribute("goods_img", request.getParameter("goods_img"));
		return "project/wxactivity/index.jsp";
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
        //String params = "grant_type=client_credential&appid=" + WxConstant.TEST_WEB_ID + "&secret=" + WxConstant.TEST_WEB_SECRET + "";  
        String params = "grant_type=client_credential&appid=" + WxConstant.WEB_ID + "&secret=" + WxConstant.WEB_SECRET + "";  
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
	
		@RequestMapping(value = "/activity/v1/initShareFriends")
		public String initShareFriends(HttpServletRequest request){
			return "project/wxactivity/share_person.jsp";
		}
}
