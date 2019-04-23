/**
 * 
 */
package com.api.prize.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.prize.service.ZjcPrizeService;
import com.api.wx.wxConstant.Authorize;
import com.api.wx.wxConstant.WxConstant;
import com.google.common.collect.Maps;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.prize.dao.ZjcWinPrizeDao;
import com.zjc.prize.dao.po.ZjcPrizePO;
import com.zjc.prize.dao.po.ZjcWinPrizePO;
import com.zjc.prize.util.PrizeUtil;
import com.zjc.users.dao.ZjcUsersDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;
import com.zjc.users.dao.po.ZjcUsersPO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.filter.HttpApiNoTokenFilter;
import aos.framework.web.httpclient.AOSHttpClient;
import aos.framework.web.httpclient.HttpRequestVO;
import aos.framework.web.httpclient.HttpResponseVO;
import aos.framework.web.router.HttpModel;

/**
 * 抽奖接口
 * 
 * @author zhangchao
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class ZjcPrizeController {
	private static Logger log = LoggerFactory.getLogger(ZjcPrizeController.class);
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcPrizeService zjcPrizeService;
	@Autowired
	private ZjcWinPrizeDao zjcWinPrizeDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	/**
	 * 获取微信code
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/wx/v1/toLotteryPage")
	public void toRegister(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = "https://zjc1518.com/aosuite/";
		String urla = tempContextUrl +"prize/initLottery.jhtml";
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConstant.WEB_ID + "&redirect_uri="+urla+"&response_type=code&scope=snsapi_userinfo&state="+""+"#wechat_redirect");
		httpModel.getViewPath();
	}
	
	/**
	 * 初始化抽奖页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/initLottery")
	public String initLottery(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		/*Authorize authorize=getWxAccesstoken(request.getParameter("code"),1);
		String openid = authorize.getOpenid();
		httpModel.setAttribute("openid", openid);*/
		List<ZjcWinPrizePO> winPrizePOs = zjcPrizeService.getWinPrizeList();
		httpModel.setAttribute("winPrizes", winPrizePOs);
		httpModel.setViewPath("project/zjc/prize/lotteryPage.jsp");
		return httpModel.getViewPath();
	}
	
	/**
	 * 获取奖品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/getWinPrize")
	public void getWinPrize(HttpServletRequest request, HttpServletResponse response){
		MessageVO messageVO =new MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		String openid = request.getSession().getAttribute("openid").toString();
		String user_id = request.getSession().getAttribute("user_id").toString();
		Integer lotteryNumber = zjcPrizeService.getLotteryNumberByOpenID(user_id);
		ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("user_id", user_id));
		if(lotteryNumber > 0){
			List<ZjcPrizePO> prizeList = zjcPrizeService.getZjcPrizeList();
			if(prizeList != null && prizeList.size() > 0){
				//本次抽奖结果
				ZjcPrizePO prize = PrizeUtil.getPrizeIndex(prizeList);
				messageVO.setData(prize);
				messageVO.setCode(Apiconstant.lottery_Number_SUCCESS.getIndex());
				messageVO.setMsg(Apiconstant.lottery_Number_SUCCESS.getName());
				Map<String,Object> winMap = new HashMap<String, Object>();
				winMap.put("user_id",zjcUsersInfoPO.getUser_id());
				winMap.put("phone_num", zjcUsersInfoPO.getMobile());
				winMap.put("prize_id", prize.getPrize_id());
				winMap.put("prize_name", prize.getPrize_name());
				zjcPrizeService.saveWinPrize(winMap);
			}
		}else{
			messageVO.setCode(Apiconstant.lottery_Number_NO.getIndex());
			messageVO.setMsg(Apiconstant.lottery_Number_NO.getName());
		}
		
		WebCxt.write(response, AOSJson.toJson(messageVO));
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
	 * 获取奖品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/getWinPrizeList")
	public void getWinPrizeList(HttpServletRequest request, HttpServletResponse response){
		   MessageVO messageVO =new MessageVO();
		   HttpModel httpModel = new HttpModel(request,response);
		   Dto qDto = httpModel.getInDto();
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			String a=request.getParameter("page");
			int page=Integer.parseInt((String)a);
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcWinPrizePO> List = sqlDao.list("com.zjc.prize.dao.ZjcWinPrizeDao.listPage", qDto);
			PageVO pageVO=new PageVO();
			if(List.size()==0){
				messageVO.setCode(Apiconstant.Do_Fails.getIndex());
				messageVO.setMsg(Apiconstant.Do_Fails.getName());
			}else{
				List<ZjcWinPrizePO> winPrizeList=new ArrayList<ZjcWinPrizePO>();
				ZjcWinPrizePO ZjcWinPrizePO=new ZjcWinPrizePO();
				for (int i = 0; i < List.size(); i++) {
					String phone=List.get(i).getPhone_num();
					ZjcWinPrizePO=List.get(i);
					StringBuffer buffer = new StringBuffer(phone);
					buffer.replace(4, 7, "****");
					ZjcWinPrizePO.setPhone_num(buffer.toString());
					winPrizeList.add(ZjcWinPrizePO);
				}
				pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), 1);
				pageVO.setList(winPrizeList);
				pageVO.setNowPage(page);
			}
			WebCxt.write(response, AOSJson.toJson(pageVO));
	}

}
