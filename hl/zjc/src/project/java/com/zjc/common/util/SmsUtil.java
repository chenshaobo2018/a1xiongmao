/**
 * 
 */
package com.zjc.common.util;

import aos.framework.core.utils.AOSJson;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zjc.common.po.SmsParam;

/**
 * 短信util
 * 
 * @author zhangchao
 *
 */
public class SmsUtil {
	
	//替换成你的AK
	public final static String sms_product = "中军创";
	public final static String accessKeyId = "LTAIuQARyZ9WxFOp";//你的accessKeyId,参考本文档步骤2
	public final static String accessKeySecret = "l78Gwt2xHbvA7f4a5ZrxZMrACBgDLd";//你的accessKeySecret，参考本文档步骤2

	public static String sendSms(String phone,String signName,String templateCode){
		String code = 1 + (int)(Math.random() * ((999999 - 1) + 1)) + "";
		SmsParam smsParam = new SmsParam();
		smsParam.setName(signName);
		smsParam.setCode(code);
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
		
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request.setPhoneNumbers(phone);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName(signName);
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode(templateCode);
		 
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 request.setTemplateParam(AOSJson.toJson(smsParam));
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				//请求成功
				return code;
			}
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
