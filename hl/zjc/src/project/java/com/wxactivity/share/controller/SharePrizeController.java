/**
 * 
 */
package com.wxactivity.share.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.order.OrderService;
import com.wxactivity.share.dao.po.ZjcSharePrizePO;
import com.wxactivity.share.service.SharePrizeService;
import com.wxactivity.share.util.SharePrizeUtil;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.prize.dao.po.ZjcPrizePO;
import com.zjc.prize.util.PrizeUtil;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class SharePrizeController {
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private SharePrizeService sharePrizeService;
	@Autowired
	private SqlDao sqlDao;
	
	@RequestMapping(value = "/activity/v1/initWinPrize")
	public String initWinPrize(HttpServletRequest request, HttpServletResponse response){
		return "project/wxactivity/sign_prize.jsp";
	}
	
	@RequestMapping(value = "/activity/v1/getWinPrize")
	public void getWinPrize(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		MessageVO messageVO = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(inDto.getString("user_id"))){
			WebCxt.write(response, AOSJson.toJson(messageVO));
			return;
		}
		inDto.put("limit", ConstantUtil.pageSize*2);
		int page = inDto.getInteger("page");
		inDto.put("start", (page-1)*ConstantUtil.pageSize*2);
		List<Dto> list = sqlDao.list("com.wxactivity.share.dao.ZjcSharePrizeDao.listWinPricePage", inDto);
		if(list.size()==0){
			messageVO.setCode(Apiconstant.NO_DATA.getIndex());
			messageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			//生成返回分页参数实体
			PageVO pageVO = ParameterUtil.getPageVO(inDto.getInteger("total"), inDto.getInteger("page"));
			pageVO.setList(list);
			messageVO.setData(pageVO);
			messageVO.setCode(Apiconstant.Do_Success.getIndex());
			messageVO.setMsg(Apiconstant.Do_Success.getName());
		}
		WebCxt.write(response, AOSJson.toJson(messageVO));
	}
	
	/**
	 * 抽奖方法
	 * @param points 抽一次扣除易物券数量
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/activity/v1/getWinSharePrize")
	public void getWinSharePrize(HttpServletRequest request, HttpServletResponse response){
		MessageVO messageVO =new MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(request.getSession().getAttribute("openid"))){
			messageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			messageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			WebCxt.write(response, AOSJson.toJson(messageVO)); 
			return;
		}
		
		if(AOSUtils.isEmpty(request.getSession().getAttribute("user_id"))){
			messageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			messageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			WebCxt.write(response, AOSJson.toJson(messageVO)); 
			return;
		}
		String openid = request.getSession().getAttribute("openid").toString();
		String user_id = request.getSession().getAttribute("user_id").toString();
		//抽奖次数改为易物券抽奖
		MessageVO msgVo = sharePrizeService.consumeVoucher(openid, user_id, dto.getInteger("points"));
		ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("user_id", user_id));
		if(msgVo.getCode() == 1){
			//抽奖前必须执行这个
			sharePrizeService.selectShareGame();
			List<ZjcSharePrizePO> prizeList = sharePrizeService.getZjcSharePrizeList();
			if(prizeList != null && prizeList.size() > 0){
				//本次抽奖结果
				ZjcSharePrizePO prize = SharePrizeUtil.getPrizeIndex(prizeList);
				messageVO.setData(prize);
				messageVO.setCode(Apiconstant.lottery_Number_SUCCESS.getIndex());
				messageVO.setMsg(Apiconstant.lottery_Number_SUCCESS.getName());
				Map<String,Object> winMap = new HashMap<String, Object>();
				winMap.put("user_id",zjcUsersInfoPO.getUser_id());
				winMap.put("phone_num", zjcUsersInfoPO.getMobile());
				winMap.put("prize_id", prize.getShare_prize_id());
				winMap.put("prize_name", prize.getShare_prize_name());
				sharePrizeService.saveWinSharePrize(winMap);
			}
		}else{
			messageVO.setCode(Apiconstant.Pay_points_not_enough.getIndex());
			messageVO.setMsg(Apiconstant.Pay_points_not_enough.getName());
		}
		WebCxt.write(response, AOSJson.toJson(messageVO));
	}
}
	

