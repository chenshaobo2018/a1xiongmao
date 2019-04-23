/**
 * 
 */
package com.wxactivity.login.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.ErrorCode;
import aos.system.common.utils.SystemCons;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ValidateUtil;
import com.api.message.AlidayuSmsConstant;
import com.api.message.SysMessageService;
import com.api.wx.wxConstant.WxUserResult;
import com.qcloud.sms.SmsSingleSender;
import com.qcloud.sms.SmsSingleSenderResult;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.wxactivity.share.dao.WxShareActivityDao;
import com.wxactivity.share.dao.WxUserActivityDao;
import com.wxactivity.share.dao.po.WxShareActivityPO;
import com.wxactivity.share.dao.po.WxUserActivityPO;
import com.zjc.common.util.SmsUtil;
import com.zjc.sms.dao.ZjcSmsLogDao;
import com.zjc.sms.dao.po.SmsType;
import com.zjc.sms.dao.po.ZjcSmsLogPO;
import com.zjc.sms.service.SmsService;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="wxShareLoginService")
public class wxShareLoginService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private SmsService smsService;
	@Autowired
	private ZjcUsersInfoDao ZjcUsersInfoDao;
	@Autowired
	private ZjcSmsLogDao ZjcSmsLogDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ApiLogService apiLogService;
	@Autowired
	private WxUserActivityDao WxUserActivityDao;
	@Autowired
	private WxShareActivityDao wxShareActivityDao;
	
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
	public String webSendMessage(HttpModel httpModel) throws ApiException{
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
	
	
	
	/**
	 * web登录
	 * @param httpModel
	 * @return
	 */
	public Dto webLogin(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		Dto outDto = Dtos.newDto();
		outDto.setAppCode(AOSCons.SUCCESS);
		if(AOSUtils.isEmpty(inDto.getString("account")) || AOSUtils.isEmpty(inDto.getString("sms_type"))){
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("参数错误");
			return outDto;
		}
		ZjcSmsLogPO ZjcSmsLogPO=new ZjcSmsLogPO();
		ZjcSmsLogPO.setMobile(inDto.getString("account"));
		ZjcSmsLogPO.setType(inDto.getString("sms_type"));
		ZjcSmsLogPO.setStatus(0);
		ZjcSmsLogPO.setType("register");
		List<ZjcSmsLogPO> send = sqlDao.list("com.zjc.sms.dao.ZjcSmsLogDao.selecttwe", ZjcSmsLogPO);
		if(AOSUtils.isEmpty(send)){
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("验证码不对，请重新输入。");
			return outDto;
		}
		//验证码校验
		if (!StringUtils.equalsIgnoreCase(send.get(0).getCode(), inDto.getString("vercode"))) {
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("验证码不对，请重新输入。");
			return outDto;
		}
		if(AOSUtils.isEmpty(inDto.getString("account"))){
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("登录账号必填！");
			return outDto;
		}
		if(AOSUtils.isEmpty(inDto.getString("password"))){
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("登录密码必填！");
			return outDto;
		}
		// 帐号存在校验
		Dto qDto = Dtos.newDto("mobile", inDto.getString("account"));
		qDto.put("is_del", SystemCons.IS.NO);
		ZjcUsersInfoPO userinfofoPO = ZjcUsersInfoDao.selectOne(qDto);
		Boolean is_pass = true;
		if (AOSUtils.isEmpty(userinfofoPO)) {
			outDto.setAppCode(ErrorCode.NO_ACCOUNT);
			outDto.setAppMsg("账号不存在，请重新输入。");
			is_pass = false;
		}else if(userinfofoPO.getIs_lock() == 1){
			outDto.setAppCode(ErrorCode.LOCKED_ERROR);
			outDto.setAppMsg("该帐号已锁定或已停用，请联系管理员。");
			is_pass = false;
		} else {
			// 密码校验
			String password = AOSCodec.md5("zhongjunchuangya1212"+inDto.getString("password"));
			if (!StringUtils.equals(password, userinfofoPO.getPassword())) {
				outDto.setAppCode(ErrorCode.PASSWORD_ERROR);
				outDto.setAppMsg("密码输入错误，请重新输入。");
				is_pass = false;
			} else {
				// 状态校验
				if (userinfofoPO.getIs_lock().equals(SystemCons.USER_STATUS.NORMAL)) {
					outDto.setAppCode(ErrorCode.LOCKED_ERROR);
					outDto.setAppMsg("该帐号已锁定或已停用，请联系管理员。");
					is_pass = false;
				}
			}
		}

		if (!is_pass) {
			return outDto;
		}
		// 通过检查
		outDto.setAppCode(AOSCons.SUCCESS);
		String last_ip=ParameterUtil.getIpAddr(httpModel.getRequest());
		ZjcUsersInfoPO newUserInfo = new ZjcUsersInfoPO();
		newUserInfo.copyProperties(userinfofoPO);
		newUserInfo.setLast_login(new Date());
		newUserInfo.setLast_ip(last_ip);
		ZjcUsersInfoDao.updateByKey(newUserInfo);
		send.get(0).setStatus(1);
		ZjcSmsLogDao.updateByKey(send.get(0));
		HttpSession session = httpModel.getRequest().getSession();
        //把用户数据保存在session域对象中
        session.setAttribute("user_id", newUserInfo.getUser_id());
		return outDto;
	}
	
	/**
	 * web注册
	 * @param httpModel
	 * @return
	 */
	public String registered(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new  MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		//获取分享人ID
		
		String mobile = dto.getString("phone");
		String password = "zhongjunchuangya1212"+dto.getString("password");
		String first_leader = dto.getString("shareId");
		String phonecode = dto.getString("phonecode");
		String sms_type = dto.getString("sms_type");
		
		if(AOSUtils.isEmpty(mobile)){//验证手机号不能为空
			msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		if(AOSUtils.isEmpty(password)){//验证密码是否为空
			msgVO.setMsg(Apiconstant.Password_Is_Null.getName());
			msgVO.setCode(Apiconstant.Password_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		
		if(AOSUtils.isEmpty(phonecode)){//手机验证码是否为空
			msgVO.setMsg(Apiconstant.phonecode.getName());
			msgVO.setCode(Apiconstant.phonecode.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		if(AOSUtils.isEmpty(sms_type)){//手机验证码是否为空
			msgVO.setMsg(Apiconstant.sms_type.getName());
			msgVO.setCode(Apiconstant.sms_type.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		if(AOSUtils.isEmpty(first_leader)){//验证密码是否为空
			 first_leader = "29999";
		}
		
		if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		//判断手机验证码
		ZjcSmsLogPO sms=new ZjcSmsLogPO();
		sms.setMobile(mobile);
		sms.setCode(phonecode);
		sms.setType(sms_type);
		//sms.setStatus(0);
		ZjcSmsLogPO ZjcSmsLogPO=(ZjcSmsLogPO) sqlDao.selectOne("com.zjc.sms.dao.ZjcSmsLogDao.selecttwe", sms);
		if(AOSUtils.isEmpty(ZjcSmsLogPO)){//验证码输入错误
			msgVO.setMsg(Apiconstant.phonecode_error.getName());
			msgVO.setCode(Apiconstant.phonecode_error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(!ParameterUtil.IsExpire(ZjcSmsLogPO.getAdd_time(), ConstantUtil.CODE_VALIDATE_TIME)){//验证码已过期
			msgVO.setMsg(Apiconstant.Code_Is_Unused.getName());
			msgVO.setCode(Apiconstant.Code_Is_Unused.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(ZjcSmsLogPO.getStatus() == 1){//验证码已使用
			msgVO.setMsg(Apiconstant.Code_Was_Used.getName());
			msgVO.setCode(Apiconstant.Code_Was_Used.getIndex());
			return AOSJson.toJson(msgVO);
		}
		//通过分享人ID查询上级会员信息
		ZjcUsersInfoPO zuserinfo = new ZjcUsersInfoPO();
		//转换Biginteger
		long first_leader_long = Long.parseLong(first_leader);
		BigInteger first_leader_big = BigInteger.valueOf(first_leader_long);
		zuserinfo.setUser_id(first_leader_big);
		ZjcUsersInfoPO zPO = zjcUsersInfoDao.selectByUseriInfoPO(zuserinfo);
		//查询该用户的直接下级数量
		int row = (int) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.count_first", zuserinfo);
		//查询系统参数配置，获取用户分享的合格会员上限
		List<ZjcMemberParameterPO> parameterPOList = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
		//登陆验证
		if(zPO == null){//分享人不存在
			msgVO.setMsg(Apiconstant.ShareUsername_Not_Exist.getName());
			msgVO.setCode(Apiconstant.ShareUsername_Not_Exist.getIndex());
		} else if(row>=Integer.parseInt(parameterPOList.get(0).getMembership_amount_limit()) && !"29999".equals(first_leader)){//该分享ID所属合格会员已达上限
			msgVO.setMsg(Apiconstant.ShareNum_IS_Overflow.getName());
			msgVO.setCode(Apiconstant.ShareNum_IS_Overflow.getIndex());
		} else {
			//通过手机号查询账号信息
			zuserinfo = new ZjcUsersInfoPO();
			List<ZjcUsersInfoPO> ZjcUsersInfoPOList = zjcUsersInfoDao.list(Dtos.newDto("mobile", mobile));
			if(AOSUtils.isNotEmpty(ZjcUsersInfoPOList)){//账号已存在!
				msgVO.setMsg(Apiconstant.Username_Has_Existed.getName());
				msgVO.setCode(Apiconstant.Username_Has_Existed.getIndex());
			} else {//验证通过  
				zuserinfo = new ZjcUsersInfoPO();
				password = AOSCodec.md5(password);
				BigInteger user_id = idService.nextValue(SystemCons.SEQ.SEQ_USER);
				zuserinfo.setUser_id(user_id);
				zuserinfo.setFirst_leader(Integer.valueOf(first_leader));
				zuserinfo.setSecond_leader(zPO.getFirst_leader());
				zuserinfo.setThird_leader(zPO.getSecond_leader());
				zuserinfo.setRecommended_code(user_id+"");
				zuserinfo.setMobile(mobile);
				zuserinfo.setMobile_validated(1);
				zuserinfo.setReg_time(new Date());
				zuserinfo.setPassword(password);
				zuserinfo.setIs_qualified_member(0);
				zuserinfo.setLevel("1");//默认为普通会员
				try{
					zjcUsersInfoDao.insert(zuserinfo);
					//添加账户信息
					ZjcUsersAccountInfoPO accountInfo = new ZjcUsersAccountInfoPO();
					accountInfo.setUser_id(user_id);
					//注册赠送
					if((parameterPOList.get(0).getStart_time().getTime() <= new Date().getTime()) 
							&& (new Date().getTime() <= parameterPOList.get(0).getEnd_time().getTime())){
						accountInfo.setPay_points(Integer.parseInt(parameterPOList.get(0).getGive_barter()));
					}
					zjcUsersAccountInfoDao.insert(accountInfo);
					//注册赠送日志生成
					if((parameterPOList.get(0).getStart_time().getTime() <= new Date().getTime()) 
							&& (new Date().getTime() <= parameterPOList.get(0).getEnd_time().getTime())){
						Map<String,Object> zsmap = new HashMap<String,Object>();
						zsmap.put("logType", "注册赠送");
						zsmap.put("user_id", user_id);
						zsmap.put("type", "可用");
						zsmap.put("due_tc_points", parameterPOList.get(0).getGive_barter());
						zsmap.put("now_points", accountInfo.getPay_points());
						zsmap.put("show_type", 1);//展示到APP上
						apiLogService.saveLog(zsmap);
					}
					//注册赠送日志生成
					if((parameterPOList.get(0).getStart_time().getTime() <= new Date().getTime()) 
							&& (new Date().getTime() <= parameterPOList.get(0).getEnd_time().getTime())){
						Map<String,Object> zsmap = new HashMap<String,Object>();
						zsmap.put("logType", "注册赠送");
						zsmap.put("user_id", user_id);
						zsmap.put("type", "可用");
						zsmap.put("due_tc_points", parameterPOList.get(0).getGive_barter());
						zsmap.put("now_points", accountInfo.getPay_points());
						zsmap.put("show_type", 1);//展示到APP上
						apiLogService.saveLog(zsmap);
					}
					//生成用户日志
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("logType", "注册");
					map.put("user_id", zuserinfo.getUser_id());
					map.put("recommand_code", first_leader);
					map.put("mobile", mobile);
					apiLogService.saveLog(map);
					ZjcSmsLogDao.updateByKey(ZjcSmsLogPO);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
			}
		}		
		
		return AOSJson.toJson(msgVO);	
	}
	
	/**
	 * 查询用户商品分享数据
	 * @param httpModel
	 * @return
	 */
	public String wxShareUser(HttpServletRequest request,HttpServletResponse response) {
		MessageVO MessageVO = new MessageVO();
		if(AOSUtils.isEmpty(request.getSession().getAttribute("wxuser"))){//未获取到微信用户信息
			MessageVO.setCode(Apiconstant.WX_USERINFO_IS_NULL.getIndex());
	    	MessageVO.setMsg(Apiconstant.WX_USERINFO_IS_NULL.getName());
	    	return AOSJson.toJson(MessageVO);
		}
		WxUserResult WxUserResul= AOSJson.fromJson(request.getSession().getAttribute("wxuser").toString(), WxUserResult.class);
		if(AOSUtils.isEmpty(WxUserResul)|| AOSUtils.isEmpty(WxUserResul.getUnionid())){
			MessageVO.setCode(Apiconstant.WX_OPENID_IS_NULL.getIndex());
	    	MessageVO.setMsg(Apiconstant.WX_OPENID_IS_NULL.getName());
	    	return AOSJson.toJson(MessageVO);
		}
		WxUserActivityPO oldwxUserActivityPO = WxUserActivityDao.selectOne(Dtos.newDto("open_id", WxUserResul.getUnionid()));
		if(AOSUtils.isNotEmpty(oldwxUserActivityPO)){
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	    	MessageVO.setMsg(Apiconstant.Do_Success.getName());
			request.getSession().setAttribute("open_id",WxUserResul.getUnionid());
			return AOSJson.toJson(MessageVO);
		}
		//生成活动用户数据
	    WxUserActivityPO WxUserActivityPO=new WxUserActivityPO();
	    WxUserActivityPO.setActivity_id(idService.uuid());
	    WxUserActivityPO.setAddtime(new Date());
	    WxUserActivityPO.setImg(WxUserResul.getHeadimgurl());
	    WxUserActivityPO.setNickname(ParameterUtil.filterEmoji(WxUserResul.getNickname()));
	    WxUserActivityPO.setOpen_id(WxUserResul.getUnionid());
	    int row=WxUserActivityDao.insert(WxUserActivityPO);
	    if(row==1){
	    	MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	    	MessageVO.setMsg(Apiconstant.Do_Success.getName());
			request.getSession().setAttribute("open_id",WxUserResul.getUnionid());
	    }else {
	    	MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
	    	MessageVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(MessageVO);
	}
	
	
	/**
	 * 分享是助力人填写电话默认注册
	 * @param httpModel
	 * @return
	 */
	public String wxShareAddPhone(HttpServletRequest request,HttpServletResponse response) {
		MessageVO msgVO = new  MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		//获取分享人ID
		
		//String mobile = dto.getString("phone");
		String password ="zhongjunchuangya1212123456";
		String shareopenid = dto.getString("shareId");
		if(AOSUtils.isEmpty(shareopenid)){//验证参数不能为空
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		String first_leader = "";
		//获取分享人的userid
		ZjcUsersInfoPO z = ZjcUsersInfoDao.selectOne(Dtos.newDto("openid", shareopenid));
		if(AOSUtils.isNotEmpty(z)){
			first_leader = z.getUser_id().toString();
		}
		//String phonecode = dto.getString("phonecode");
		//String sms_type = dto.getString("sms_type");
		String open_id = dto.getString("open_id");
		String goods_id = dto.getString("goods_id");
		String goods_price = dto.getString("goods_price");
		
		//判断手机验证码
	/*	ZjcSmsLogPO sms=new ZjcSmsLogPO();
		sms.setMobile(mobile);
		sms.setCode(phonecode);
		sms.setType(sms_type);
		//sms.setStatus(0);
		ZjcSmsLogPO ZjcSmsLogPO=(ZjcSmsLogPO) sqlDao.selectOne("com.zjc.sms.dao.ZjcSmsLogDao.selecttwe", sms);
		if(AOSUtils.isEmpty(ZjcSmsLogPO)){//验证码输入错误
			msgVO.setMsg(Apiconstant.phonecode_error.getName());
			msgVO.setCode(Apiconstant.phonecode_error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(!ParameterUtil.IsExpire(ZjcSmsLogPO.getAdd_time(), ConstantUtil.CODE_VALIDATE_TIME)){//验证码已过期
			msgVO.setMsg(Apiconstant.Code_Is_Unused.getName());
			msgVO.setCode(Apiconstant.Code_Is_Unused.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(ZjcSmsLogPO.getStatus() == 1){//验证码已使用
			msgVO.setMsg(Apiconstant.Code_Was_Used.getName());
			msgVO.setCode(Apiconstant.Code_Was_Used.getIndex());
			return AOSJson.toJson(msgVO);
		}*/
			
		//验证是否已经助力或者自己助力自己
		boolean validateIsShare = this.Is_share(shareopenid, open_id, goods_id);
		if(!validateIsShare){
			msgVO.setMsg(Apiconstant.IS_NOT_SHARE_HANDLE.getName());
			msgVO.setCode(Apiconstant.IS_NOT_SHARE_HANDLE.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		/*if(AOSUtils.isEmpty(mobile)){//验证手机号不能为空
			msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} */
		if(AOSUtils.isEmpty(password)){//验证密码是否为空
			msgVO.setMsg(Apiconstant.Password_Is_Null.getName());
			msgVO.setCode(Apiconstant.Password_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		
		/*if(AOSUtils.isEmpty(phonecode)){//手机验证码是否为空
			msgVO.setMsg(Apiconstant.phonecode.getName());
			msgVO.setCode(Apiconstant.phonecode.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		if(AOSUtils.isEmpty(sms_type)){//手机验证码是否为空
			msgVO.setMsg(Apiconstant.sms_type.getName());
			msgVO.setCode(Apiconstant.sms_type.getIndex());
			return AOSJson.toJson(msgVO);
		} */
		if(AOSUtils.isEmpty(first_leader)){//验证密码是否为空
			 first_leader = "29999";
		}
		
		/*if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			return AOSJson.toJson(msgVO);
		}*/
		
		//通过分享人ID查询上级会员信息
		ZjcUsersInfoPO zuserinfo = new ZjcUsersInfoPO();
		//转换Biginteger
		long first_leader_long = Long.parseLong(first_leader);
		BigInteger first_leader_big = BigInteger.valueOf(first_leader_long);
		zuserinfo.setUser_id(first_leader_big);
		ZjcUsersInfoPO zPO = zjcUsersInfoDao.selectByUseriInfoPO(zuserinfo);
		//查询该用户的直接下级数量
		int row = (int) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.count_first", zuserinfo);
		//查询系统参数配置，获取用户分享的合格会员上限
		List<ZjcMemberParameterPO> parameterPOList = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
		//登陆验证
		if(zPO == null){//分享人不存在
			msgVO.setMsg(Apiconstant.ShareUsername_Not_Exist.getName());
			msgVO.setCode(Apiconstant.ShareUsername_Not_Exist.getIndex());
		} else if(row>=Integer.parseInt(parameterPOList.get(0).getMembership_amount_limit()) && !"29999".equals(first_leader)){//该分享ID所属合格会员已达上限
			msgVO.setMsg(Apiconstant.ShareNum_IS_Overflow.getName());
			msgVO.setCode(Apiconstant.ShareNum_IS_Overflow.getIndex());
		} else {
			//通过手机号查询账号信息
			zuserinfo = new ZjcUsersInfoPO();
			
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			//ZjcUsersInfoPO.setMobile(mobile);
			ZjcUsersInfoPO.setOpenid(open_id);
			List<ZjcUsersInfoPO> ZjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.sharelist", ZjcUsersInfoPO);
			if(AOSUtils.isNotEmpty(ZjcUsersInfoPOList)){
				if(ZjcUsersInfoPOList.size()==1){//账号已存在!
					msgVO.setCode(Apiconstant.count_is_have.getIndex());
					msgVO.setMsg(Apiconstant.count_is_have.getName());
					Map<String, Object> inMap = new HashMap<String, Object>();
					inMap.put("share_open_id", shareopenid);//分享人open_id
					inMap.put("goods_id", goods_id);//商品ID
					inMap.put("goods_integral", goods_price);//商品价格
					inMap.put("open_id", open_id);//助力人id
					inMap.put("img", ZjcUsersInfoPOList.get(0).getHead_pic());//助力人头像
					inMap.put("nickname", ZjcUsersInfoPOList.get(0).getNickname());//助力人昵称
					this.addShare(inMap);
				}else if(ZjcUsersInfoPOList.size()>1){
					msgVO.setMsg(Apiconstant.mobile_openid_id_notMate.getName());
					msgVO.setCode(Apiconstant.mobile_openid_id_notMate.getIndex());
				}
			} else {
				msgVO.setMsg(Apiconstant.Do_Fails.getName());
				msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			}
			/*else {//验证通过  
				zuserinfo = new ZjcUsersInfoPO();
				password = AOSCodec.md5(password);
				BigInteger user_id = idService.nextValue(SystemCons.SEQ.SEQ_USER);
				zuserinfo.setUser_id(user_id);
				zuserinfo.setFirst_leader(Integer.valueOf(first_leader));
				zuserinfo.setSecond_leader(zPO.getFirst_leader());
				zuserinfo.setThird_leader(zPO.getSecond_leader());
				zuserinfo.setRecommended_code(user_id+"");
				zuserinfo.setMobile_validated(1);
				zuserinfo.setReg_time(new Date());
				zuserinfo.setPassword(password);
				zuserinfo.setIs_qualified_member(0);
				zuserinfo.setLevel("1");//默认为普通会员
				try{
					zjcUsersInfoDao.insert(zuserinfo);
					//添加账户信息
					ZjcUsersAccountInfoPO accountInfo = new ZjcUsersAccountInfoPO();
					accountInfo.setUser_id(user_id);
					//注册赠送
					accountInfo.setPay_points(Integer.parseInt(parameterPOList.get(0).getGive_barter()));
					zjcUsersAccountInfoDao.insert(accountInfo);
					//注册赠送日志生成
					Map<String,Object> zsmap = new HashMap<String,Object>();
					zsmap.put("logType", "注册赠送");
					zsmap.put("user_id", user_id);
					zsmap.put("type", "可用");
					zsmap.put("due_tc_points", parameterPOList.get(0).getGive_barter());
					zsmap.put("now_points", accountInfo.getPay_points());
					zsmap.put("show_type", 1);//展示到APP上
					apiLogService.saveLog(zsmap);
					//生成用户日志
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("logType", "注册");
					map.put("user_id", zuserinfo.getUser_id());
					map.put("recommand_code", first_leader);
					//map.put("mobile", mobile);
					apiLogService.saveLog(map);
					//ZjcSmsLogDao.updateByKey(ZjcSmsLogPO);
					Map<String, Object> inMap = new HashMap<String, Object>();
					inMap.put("share_open_id", shareopenid);//分享人open_id
					inMap.put("goods_id", goods_id);//商品ID
					inMap.put("goods_integral", goods_price);//商品价格
					inMap.put("open_id", open_id);//助力人id
					inMap.put("img", zuserinfo.getHead_pic());//助力人头像
					inMap.put("nickname", zuserinfo.getNickname());//助力人昵称
					this.addShare(inMap);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
			}*/
		}		
		return AOSJson.toJson(msgVO);	
	}
	
	
	/**
	 * 添加助力信息
	 * 
	 * @param inMap
	 * @return
	 */
	public WxShareActivityPO addShare(Map<String,Object> inMap) {
		WxShareActivityPO wxShareActivityPO=new WxShareActivityPO();
		wxShareActivityPO.copyProperties(inMap);
		wxShareActivityPO.setAddtime(new Date());
		wxShareActivityPO.setShare_integral("68");
		wxShareActivityPO.setShare_id(idService.nextValue(SystemCons.SEQ.SEQ_USER).toString());
		ZjcUsersInfoPO shareuser = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", inMap.get("share_open_id")));
		if(AOSUtils.isNotEmpty(shareuser)){
			wxShareActivityPO.setUser_id(shareuser.getUser_id());
		}
		//查询商品是否兑换
		Dto sumResult = sqlDao.selectDto("com.wxactivity.share.dao.WxShareActivityDao.selectTypeSum", Dtos.newDto(inMap));
		Integer typeSum = sumResult.getInteger("typeSum");
		if(AOSUtils.isNotEmpty(typeSum) && typeSum > 0 ){
			wxShareActivityPO.setType("1");//如兑换之后助力，那么生成数据的时候直接为已兑换状态
		}
		//获取查询参数
		int row=wxShareActivityDao.insert(wxShareActivityPO);
		if(row==1){
			return wxShareActivityPO;
		}else {
			return null;
		}
    }
	
	
	/**
	 * 判断是否助力
	 * @param httpModel
	 * @return
	 */
	public boolean Is_share(String share_open_id,String open_id,String goods_id) {
		//获取查询参数
		if(share_open_id.equals(open_id)){
			return false;
		}else{
			Map<String,Object> qMap = new HashMap<String, Object>();
			qMap.put("share_open_id", share_open_id);
			qMap.put("open_id", open_id);
			//qMap.put("goods_id", goods_id);
			List<WxShareActivityPO> shareFriends = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.list", qMap);
			if(AOSUtils.isEmpty(shareFriends)){
				return true;
			}else{
				return false;
			}
		}
	  }

	
}

