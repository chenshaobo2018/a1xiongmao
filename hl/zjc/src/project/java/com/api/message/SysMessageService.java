/**
 * 
 */
package com.api.message;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ValidateUtil;
import com.qcloud.sms.SmsSingleSender;
import com.qcloud.sms.SmsSingleSenderResult;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zjc.common.util.SmsUtil;
import com.zjc.sms.dao.po.SmsType;
import com.zjc.sms.service.SmsService;

/**
 * 短信接口调用方法
 * 
 * @author zhangchao
 *
 */
@Service(value="sysMessageService")
public class SysMessageService {

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private IdService idService;
	
	private static final Logger logger = LoggerFactory.getLogger(SysMessageService.class);
	
	/**
	 * 验证短信发送
	 * @param httpModel
	 * @throws ServerException
	 * @throws ClientException
	 */
	@Deprecated
	public String validateMessage(HttpModel httpModel) throws ServerException, ClientException{
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		String sms_type = dto.getString("sms_type");
		String phone_number = dto.getString("mobile");
		String code = null;
		if(sms_type.equals(SmsType.pay_password.toString())){
			code = SmsUtil.sendSms(phone_number, "我的小窝", "SMS_72930075");
		}else if(sms_type.equals(SmsType.find_password.toString())){
			code = SmsUtil.sendSms(phone_number, "我的小窝", "SMS_72930075");
		}
		//添加保存到数据库
		if(code == null || code != ""){
			Dto sdto = Dtos.newDto();
			sdto.put("mobile", phone_number);
			sdto.put("code", code);
			sdto.put("type", sms_type);
			httpModel.setInDto(sdto);
			smsService.saveSmsLog(sdto);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msgVo.setCode(Apiconstant.Sms_Is.getIndex());
			msgVo.setMsg(Apiconstant.Sms_Is.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
	
	/**
	 * app短语发送
	 * 
	 * @param httpModel
	 * @return
	 * @throws ApiException
	 */
	public String alidayuSms(HttpModel httpModel) throws ApiException{
		MessageVO msgVo = new MessageVO(); 
		Dto inDto = httpModel.getInDto();
		String country_code = inDto.getString("country_code");//国家代码
		if(AOSUtils.isEmpty(inDto.getString("country_code"))){
			country_code = "86";
		}
		String code = getSix();
		inDto.put("code", code);
		Dto sdto = Dtos.newDto();
		sdto.put("mobile", inDto.getString("mobile"));//手机号
		sdto.put("code", code);//验证码
		sdto.put("type", inDto.getString("sms_type"));//短信类型
		httpModel.setInDto(sdto);
		boolean bool = false;
		try{
			if("86".equals(country_code)){//国内短信发送
				if(!ValidateUtil.isRightPhone(inDto.getString("mobile"))){
					msgVo.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
					msgVo.setMsg(Apiconstant.Phone_Is_Wrong.getName());	
					return AOSJson.toJson(msgVo);
				}
				bool = sendMsgAlidayu(inDto);//发送短信
				if(bool){
					smsService.saveSmsLog(sdto);//保存短信发送记录
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());	
				} else {
					msgVo.setCode(Apiconstant.Sms_Is.getIndex());
					msgVo.setMsg(Apiconstant.Sms_Is.getName());
				}
			} else {//国际短信发送
				bool = sendMsgTencent(inDto);//发送短信
				if(bool){
					smsService.saveSmsLog(sdto);//保存短信发送记录
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());	
				} else {
					msgVo.setCode(Apiconstant.Sms_Is.getIndex());
					msgVo.setMsg(Apiconstant.Sms_Is.getName());
				}
			}
		} catch(Exception e){
			logger.info(e.toString());
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 阿里大于发送短信接口
	 * 
	 * @param inDto
	 * @return boolean
	 */
	private boolean sendMsgAlidayu(Dto inDto){
		String phone_number = inDto.getString("mobile");
		String code = inDto.getString("code"); 
		Map<String, String> smsParam = new HashMap<String, String>();
		smsParam.put("name", code);
		smsParam.put("product", AlidayuSmsConstant.sms_product);
		TaobaoClient client = new DefaultTaobaoClient(AlidayuSmsConstant.serverUrl, AlidayuSmsConstant.sms_appkey, AlidayuSmsConstant.sms_secretKey);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(AlidayuSmsConstant.Extend);
		req.setSmsType(AlidayuSmsConstant.SmsType);
		req.setSmsFreeSignName(AlidayuSmsConstant.sms_product);
		req.setSmsParam(AOSJson.toJson(smsParam));
		req.setRecNum(phone_number);
		req.setSmsTemplateCode("SMS_56685139");
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			BizResult biz = rsp.getResult();
			if(biz != null && biz.getSuccess()){
				logger.debug("短息测试信息："+biz.getSuccess());
				return true;
			}else{
				return false;
			}
		} catch (ApiException e) {
			e.printStackTrace();
			return false;
		}
	} 
	
	/**
	 * 腾讯云发送短信接口
	 * 
	 * @param dto
	 * @return boolean
	 */
	private boolean sendMsgTencent(Dto dto) {
		int appid = 1400040243;
		String appkey = "f78df03a114cd2c32d7235778fb0a4cb";
		String mobile = dto.getString("mobile");
		String code = dto.getString("code"); 
		String country_code = dto.getString("country_code");
		int tmplId = 41939;
		try {
			SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult singleSenderResult;
			ArrayList<String> params = new ArrayList<>();
	    	params.add(code);//验证码
			singleSenderResult = singleSender.sendWithParam(country_code, mobile, tmplId, params, "", "", "");
			logger.debug("短息测试信息："+singleSenderResult.errMsg);
			if(singleSenderResult.result==0){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 随机生成6位数验证码
	 * @param min
	 * @param max
	 * @return
	 */
	public static String getSix(){  
        Random rad=new Random();  
        String result  = rad.nextInt(1000000) +"";  
        if(result.length()!=6){  
            return getSix();  
        }  
        return result;  
    }  
	
	/**
	 * 向商家发送转账信息
	 * 
	 * @param httpModel
	 * @return
	 * @throws ApiException
	 */
	public String transferMsg(HttpModel httpModel) throws ApiException{
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		String sms_type = inDto.getString("sms_type");
		String phone_number = inDto.getString("mobile");
		Map<String, Object> smsParam = new HashMap<String, Object>();
		smsParam.put("name", inDto.getString("store_name"));
		smsParam.put("product", AlidayuSmsConstant.sms_product);
		smsParam.put("newtime", new Date());
		smsParam.put("number", inDto.getString("number"));
		TaobaoClient client = new DefaultTaobaoClient(AlidayuSmsConstant.serverUrl, AlidayuSmsConstant.sms_appkey, AlidayuSmsConstant.sms_secretKey);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(AlidayuSmsConstant.Extend);
		req.setSmsType(AlidayuSmsConstant.SmsType);
		req.setSmsFreeSignName(AlidayuSmsConstant.sms_product);
		req.setSmsParam(AOSJson.toJson(smsParam));
		req.setRecNum(phone_number);
		req.setSmsTemplateCode("SMS_92200027");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		BizResult biz = rsp.getResult();
		if(biz != null && biz.getSuccess()){
			Dto sdto = Dtos.newDto();
			sdto.put("mobile", phone_number);
			sdto.put("code", "");
			sdto.put("type", sms_type);
			httpModel.setInDto(sdto);
			smsService.saveSmsLog(sdto);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msgVo.setCode(Apiconstant.Sms_Is.getIndex());
			msgVo.setMsg(Apiconstant.Sms_Is.getName());
		}
		return AOSJson.toJson(msgVo);
	}
}
