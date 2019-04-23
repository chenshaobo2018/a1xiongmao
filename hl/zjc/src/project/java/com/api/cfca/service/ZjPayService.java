/**
 * 
 */
package com.api.cfca.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.Apiconstant;
import com.api.cfca.dao.ZjcZjBankcardDao;
import com.api.cfca.dao.po.ZjcZjBankcardPO;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.po.ZjcOrderPO;

import aos.framework.core.typewrap.Dtos;
import payment.api.tx.marketorder.Tx1375Request;
import payment.api.tx.marketorder.Tx1376Request;
import payment.api.tx.paymentbinding.Tx2531Request;
import payment.api.tx.paymentbinding.Tx2532Request;

/**
 * @author Administrator
 *
 */
@Service(value="ZjPayService")
public class ZjPayService {
	@Autowired
	private SendMessage SendMessage;
	@Autowired
	private ZjcZjBankcardDao ZjcZjBankcardDao;
	@Autowired
	private ZjcOrderDao ZjcOrderDao;
	
	public MessageVO Pay2531(HttpServletRequest request, HttpServletResponse response) {
		    MessageVO msgVo = new MessageVO();
		try {
            // 1.获取参数
            String institutionID ="003785"; //request.getParameter("InstitutionID");
            String txCode = "1";//request.getParameter("TxCode");
            String txSNBinding = request.getParameter("TxSNBinding");//"201804231433426931117841612";
            String bankID =request.getParameter("BankID"); //"105";
            String accountName =request.getParameter("AccountName"); //"侯淑玉";
            String accountNumber =request.getParameter("AccountNumber");// "6217003810047570091";
            String identificationType = request.getParameter("IdentificationType");//"0"
            String identificationNumber =request.getParameter("IdentificationNumber");//"511027197212033940";//
            String phoneNumber = request.getParameter("PhoneNumber");//"15882232521";//
            String cardType = request.getParameter("CardType");//"10";//
            String validDate =!"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 =!"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;

            // 创建交易请求对象
            Tx2531Request txRequest = new Tx2531Request();

            txRequest.setInstitutionID(institutionID);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setBankID(bankID);
            txRequest.setAccountName(accountName);
            txRequest.setAccountNumber(accountNumber);
            txRequest.setIdentificationNumber(identificationNumber);
            txRequest.setIdentificationType(identificationType);
            txRequest.setPhoneNumber(phoneNumber);
            txRequest.setCardType(cardType);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);

            // 3.执行报文处理
            txRequest.process();
        	
			request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());            
            request.setAttribute("txCode", "2531");
            request.setAttribute("txName", "建立绑定关系（发送验证短信）");
            request.setAttribute("Flag", "null");
            // 4.将参数放置到request对象
			String data=SendMessage.SendMessage(request, response);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(data);
			
        } catch (Exception e) {
            e.printStackTrace();
            
        }
		return msgVo;
	}
	
	
	public MessageVO Pay2532(HttpServletRequest request, HttpServletResponse response) {
		        MessageVO msgVo = new MessageVO();
		  try {
	            // 1.获取参数
	            //String txCode = request.getParameter("TxCode");
	            String txSNBinding =request.getParameter("TxSNBinding");//"201804231433426931117841612";// 
	            String SMSValidationCode = request.getParameter("SMSValidationCode");
	            String institutionID ="003785"; //request.getParameter("InstitutionID");
	            String txCode = "1";//request.getParameter("TxCode");
	            String bankID =request.getParameter("BankID"); //"105";
	            String user_id =request.getParameter("user_id").trim();
	            String Banktext =request.getParameter("Banktext");
	            String accountName =request.getParameter("AccountName"); //"侯淑玉";
	            String accountNumber =request.getParameter("AccountNumber");// "6217003810047570091";
	            String identificationType = request.getParameter("IdentificationType");
	            String identificationNumber = request.getParameter("IdentificationNumber");
	            String phoneNumber = request.getParameter("PhoneNumber");
	            String cardType =request.getParameter("CardType");
	            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
	            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;

	            // 创建交易请求对象
	            Tx2532Request txRequest = new Tx2532Request();

	            txRequest.setInstitutionID(institutionID);
	            txRequest.setTxSNBinding(txSNBinding);
	            txRequest.setSMSValidationCode(SMSValidationCode);
	            txRequest.setValidDate(validDate);
	            txRequest.setCvn2(cvn2);

	            // 3.执行报文处理
	            txRequest.process();

	            // 4.将参数放置到request对象

	            request.setAttribute("plainText", txRequest.getRequestPlainText());
	            request.setAttribute("message", txRequest.getRequestMessage());
	            request.setAttribute("signature", txRequest.getRequestSignature());
	            request.setAttribute("txCode", "2532");
	            request.setAttribute("txName", "建立绑定关系（验证和绑定）");
	            request.setAttribute("Flag", "null");
	            // 4.将参数放置到request对象
				String data=SendMessage.SendMessage(request, response);
				if("2000".equals(data)){
				    ZjcZjBankcardPO ZjcZjBankcardPO=new ZjcZjBankcardPO();
				    ZjcZjBankcardPO.setAccountname(accountName);
				    ZjcZjBankcardPO.setAccountnumber(accountNumber);
				    ZjcZjBankcardPO.setBank_id(bankID);
				    ZjcZjBankcardPO.setCardtype(cardType);
				    ZjcZjBankcardPO.setIdentificationnumber(identificationNumber);
				    ZjcZjBankcardPO.setIdentificationtype(identificationType);
				    ZjcZjBankcardPO.setPhonenumber(phoneNumber);
				    ZjcZjBankcardPO.setTxsnbinding(txSNBinding);
				    ZjcZjBankcardPO.setUser_id(new BigInteger(user_id));
				    ZjcZjBankcardPO.setBanktext(Banktext);
				    ZjcZjBankcardDao.insert(ZjcZjBankcardPO);
				}
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
				msgVo.setData(data);
	        } catch (Exception e) {
	            e.printStackTrace();
	            processException(request, response, e.getMessage());
	        }
		return msgVo;
	}
	
	public MessageVO Pay1375(HttpServletRequest request, HttpServletResponse response) {
		 MessageVO msgVo = new MessageVO();
		try {
            // 1.取得参数
            String institutionID = "003785";//request.getParameter("InstitutionID");
            String orderNo = request.getParameter("order_id").trim();
            ZjcOrderPO order=ZjcOrderDao.selectOne(Dtos.newDto("order_id",orderNo));
            
            int nowTime= ParameterUtil.dateToInt();
    		String paymentNo=nowTime + ParameterUtil.getRandom();
            long amount = order.getCash().longValue();
            String txSNBinding = request.getParameter("TxSNBinding").trim();
            String validDate =null; //!"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = null;//!"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;
            String remark = null;//request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1375Request txRequest = new Tx1375Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setOrderNo(orderNo);
            txRequest.setPaymentNo(paymentNo);
            txRequest.setAmount(amount);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);
            txRequest.setRemark(remark);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1375");
            request.setAttribute("txName", "市场订单快捷支付(发送短信)");
            request.setAttribute("Flag", "null");
            String data=SendMessage.SendMessage(request, response);
            if("2000".equals(data)){
            	msgVo.setCode(Integer.parseInt(orderNo));
				msgVo.setMsg(paymentNo);
    			msgVo.setData("project/zjc/payCfca/bank_pay.jsp");
            }else {
            	msgVo.setData("project/zjc/payCfca/spell.jsp");
			}
        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
		return msgVo;
	}
	
	
	public MessageVO Pay1376(HttpServletRequest request, HttpServletResponse response) {
		MessageVO msgVo = new MessageVO();
		try {
            // 1.取得参数
            String institutionID = "003785";
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            String smsValidationCode = request.getParameter("SMSValidationCode");
            String validDate = null;//!"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = null;//!"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;

            // 2.创建交易请求对象
            Tx1376Request txRequest = new Tx1376Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setOrderNo(orderNo);
            txRequest.setPaymentNo(paymentNo);
            txRequest.setSmsValidationCode(smsValidationCode);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1376");
            request.setAttribute("txName", "市场订单快捷支付(验证并支付)");
            request.setAttribute("Flag", "null");
            String data=SendMessage.SendMessage(request, response);
            if("2000".equals(data)){
            	msgVo.setCode(Integer.parseInt(orderNo));
				msgVo.setMsg(paymentNo);
    			msgVo.setData("project/zjc/payCfca/pay_success.jsp");
            }else {
            	msgVo.setData("project/zjc/payCfca/pay_fal.jsp");
			}
        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
		return msgVo;
	}
	
	
	 private void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
	        try {
	            request.setAttribute("errorMessage", errorMessage);
	            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
	        } catch (Exception e) {

	        }
	    }
}
