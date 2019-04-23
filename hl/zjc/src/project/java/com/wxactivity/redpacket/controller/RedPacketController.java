/**
 * 
 */
package com.wxactivity.redpacket.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.wxactivity.redpacket.service.RedPacketService;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 红包活动
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class RedPacketController {

	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private RedPacketService redPacketService;
	
	/**
	 * 抢红包首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initRedPacketIndex")
	public String initRedPacketIndex(HttpServletRequest request, HttpServletResponse response){
		return "project/redpacket/redindex.jsp";
	}
	
	/**
	 * 抢红包分享页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initShareRedPacket")
	public String initShareRedPacket(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		if(AOSUtils.isNotEmpty(httpModel.getInDto().getString("shareId"))){
			ZjcUsersInfoPO zInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", httpModel.getInDto().getString("shareId")));
			httpModel.setAttribute("zjcUsersInfoPO", zInfoPO);
		}
		httpModel.setAttribute("shareId", httpModel.getInDto().getString("shareId"));
		return "project/redpacket/shareRedPacket.jsp";
	}
	
	/**
	 * 领红包注册页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activity/v1/initRedPacketRegister")
	public String initRedPacketRegister(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		String randomNum = this.getRandomRedPackrt();
		httpModel.setAttribute("randomNum", randomNum);
		httpModel.setAttribute("shareId", httpModel.getInDto().getString("shareId"));
		return "project/redpacket/redpacktRegister.jsp";
	}
	
	/**
	 * 获取随机红包金额
	 * @return
	 */
	public String getRandomRedPackrt(){
//		Random rand = new Random();
//		int rNum = rand.nextInt(6)+1;
		String prizeNum = "58";
		/*switch (rNum) {
			case 1:
				prizeNum = "08";
			    break;
			case 2:
				prizeNum = "18";
			    break;
			case 3:
				prizeNum = "28";
			    break;
			case 4:
				prizeNum = "38";
			    break;
			case 5:
				prizeNum = "48";
			    break;
			case 6:
				prizeNum = "58";
			    break;
		}*/
		return prizeNum;
	}
	
	/**
	 * 注册获取红包
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/activity/v1/redPacketRegister")
	public void redPacketRegister(HttpServletRequest request, HttpServletResponse response){
		String result = redPacketService.redPacketRegister(request, response);
		WebCxt.write(response, result);
	}
	
}
