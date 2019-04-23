/**
 * 
 */
package com.api.cfca.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.ApiPublic.Apiconstant;
import com.api.cfca.dao.ZjcZjBankcardDao;
import com.api.cfca.dao.po.ZjcZjBankcardPO;
import com.api.cfca.service.SendMessage;
import com.api.cfca.service.ZjPayService;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class ZjPayController {
	
	@Autowired
	private ZjPayService ZjPayService;
	@Autowired
	private SendMessage SendMessage;
	@Autowired
	private ZjcZjBankcardDao ZjcZjBankcardDao;
	
	
	@RequestMapping(value = "/app/v1/payBank")
	public String payBank(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO MessageVO=new MessageVO();
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("user_id"))&&AOSUtils.isEmpty(qDto.getString("order_id"))){
			httpModel.setViewPath("project/zjc/payCfca/spell.jsp");
		}else {
			List<ZjcZjBankcardPO> ZjcZjBankcardPO =ZjcZjBankcardDao.list(qDto);
			if(AOSUtils.isNotEmpty(ZjcZjBankcardPO)){
				for (int i = 0; i < ZjcZjBankcardPO.size(); i++) {
					if("10".equals(ZjcZjBankcardPO.get(i).getCardtype())){
						ZjcZjBankcardPO.get(i).setCardtype("借记卡");
					}else {
						ZjcZjBankcardPO.get(i).setCardtype("贷记卡");
					}
				}
			}
			httpModel.setAttribute("ZjcZjBankcardPO", ZjcZjBankcardPO);
			httpModel.setAttribute("order_id", qDto.getString("order_id"));
			httpModel.setViewPath("project/zjc/payCfca/bank.jsp");
		}
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/payBankBind")
	public String payBankBind(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setAttribute("user_id", request.getParameter("user_id"));
		int nowTime= ParameterUtil.dateToInt();
		String TxSNBinding=nowTime + ParameterUtil.getRandom();
		httpModel.setAttribute("TxSNBinding",TxSNBinding);
		httpModel.setViewPath("project/zjc/payCfca/bank_bind.jsp");
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/payBankPay")
	public String payBankPay(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setAttribute("order_id", request.getParameter("order_id"));
		httpModel.setViewPath("project/zjc/payCfca/bank_pay.jsp");
		return httpModel.getViewPath();
	}
	
	
	@RequestMapping(value = "/app/v1/Pay2531")
	public void Pay2531(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO MessageVO=ZjPayService.Pay2531(request,response);
		WebCxt.write(response, AOSJson.toJson(MessageVO));
	}
	
	@RequestMapping(value = "/app/v1/Pay2532")
	public void Pay2532(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO MessageVO=ZjPayService.Pay2532(request,response);
		WebCxt.write(response, AOSJson.toJson(MessageVO));
	}
	
	
	@RequestMapping(value = "/app/v1/Pay1375")
	public String Pay1375(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO MessageVO =ZjPayService.Pay1375(request,response);
		httpModel.setAttribute("orderNo", MessageVO.getCode());
		httpModel.setAttribute("paymentNo", MessageVO.getMsg());
		httpModel.setViewPath(MessageVO.getData().toString());
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/Pay1376")
	public String Pay1376(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO MessageVO =ZjPayService.Pay1376(request,response);
		httpModel.setViewPath(MessageVO.getData().toString());
		return httpModel.getViewPath();
	}
    
	@RequestMapping(value = "/app/v1/SendMessage")
	public void SendMessage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		SendMessage.SendMessage(request,response);
	}
	
}
