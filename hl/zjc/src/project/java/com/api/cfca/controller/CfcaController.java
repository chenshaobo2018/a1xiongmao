/**
 * 
 */
package com.api.cfca.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.cfca.service.CfcaService;
import com.api.common.po.MessageVO;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.po.ZjcOrderPO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class CfcaController {
	
	@Autowired
	private CfcaService CfcaService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcOrderDao ZjcOrderDao;
	
	/**
	 * @api {get} notokenapi/app/v1/payBankSubmit.jhtml cfca支付
	 * @apiName cfca支付
	 * @apiGroup pay
	 *
	 * @apiParam {String} order_id order_id
	 */
	@RequestMapping(value = "/app/v1/payBankSubmit")
	public String payBankSubmit(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		MessageVO msgVo = new MessageVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//定义要输出日期字符串的格式
		String dateId1 = sdf.format(new java.util.Date());//获取当前日期
		String order_id=request.getParameter("order_id");
		if(AOSUtils.isEmpty(order_id)){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		Dto qDto=httpModel.getInDto();
		qDto.put("pay_status", 0);
		ZjcOrderPO orderpo= (ZjcOrderPO) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.selectOne", qDto);
		if(AOSUtils.isEmpty(orderpo)){
			msgVo.setCode(Apiconstant.Order_NO_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Order_NO_Exist.getName());
			return AOSJson.toJson(msgVo);
		}
		try {
			String v_oid=dateId1+"-18338-"+order_id;
			String v_ymd=dateId1;
			String v_amount=String.valueOf(orderpo.getCash());
			String v_rcvname="18338";//orderpo.getConsignee();
			String v_rcvpost="18338";
			String v_rcvaddr=orderpo.getConsignee();//"18338";
			String v_rcvtel=orderpo.getOrder_sn();
			String v_orderstatus="1";
			String v_ordername="18338";
			String v_mid="18338";
			String v_moneytype="0";
			String v_pmode="126";
			String v_url="https://zjc1518.com/aosuite/notokenapi/app/v1/PayReceived1.jhtml";
			String pfxFile = "/usr/local/apache-tomcat-7.0.81/webapps/payeay.pfx";//商户私钥文件
			//String pfxFile = "C:/Users/Administrator/Desktop/payeay.pfx";
			String pfxPassword = "zjc1518";//私钥键入密码
			String aliasPassword = "zjc1518";
			String aliasName = "{17ce4d07-c8a1-457e-a139-a9d3362d8ab4}";//别名
			String src = v_moneytype + v_ymd + v_amount + v_rcvname + v_oid + v_mid + v_url;
			String sign = MerchantX509Cert.sign(src, pfxFile, aliasName, pfxPassword, aliasPassword);
			String value="v_oid="+v_oid+"&v_ymd="+v_ymd+"&v_amount="+v_amount+"&v_rcvname="+v_rcvname+"&v_rcvpost="+v_rcvpost
				     +"&v_rcvaddr="+v_rcvaddr+"&v_rcvtel="+v_rcvtel+"&v_orderstatus="+v_orderstatus+"&v_ordername="+v_ordername
				     +"&v_mid="+v_mid+"&v_moneytype="+v_moneytype+"&v_url="+v_url+"&v_pmode="+v_pmode+"&sign="+sign;
			orderpo.setV_oid(v_oid);
			ZjcOrderDao.updateByKey(orderpo);
			httpModel.setViewPath("project/zjc/payCfca/pay_BankSubmit.jsp?"+value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpModel.getViewPath();
	}
	
	
	@RequestMapping(value = "/app/v1/payBankSubmitMax")
	public void payBankSubmitMax(HttpServletRequest request, HttpServletResponse response){
		MessageVO result=CfcaService.payBankSubmitMax(request,response);
		WebCxt.write(response, AOSJson.toJson(result));
	}
	
	
	@RequestMapping(value = "/app/v1/PayReceived1")
	public String PayReceived1(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		String result=CfcaService.PayReceived1(request,response);
		if("sent".equals(result)){
			httpModel.setViewPath("project/zjc/payCfca/received1.jsp");
		}else {
			httpModel.setViewPath("project/zjc/payCfca/received2.jsp");
		}
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/PayReceived")
	public void PayReceived(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		String result=CfcaService.PayReceived2(request,response);
		WebCxt.writes(response, result);
	}
	
	
	@RequestMapping(value = "/app/v1/PayOks")
	public String PayOks(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setViewPath("project/zjc/payCfca/received2.jsp");
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/pay_success")
	public String pay_success(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setViewPath("project/zjc/payCfca/pay_success.jsp");
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/pay_fal")
	public String pay_fal(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request,response);
		httpModel.setViewPath("project/zjc/payCfca/pay_fal.jsp");
		return httpModel.getViewPath();
	}
	
	
	
	
}
