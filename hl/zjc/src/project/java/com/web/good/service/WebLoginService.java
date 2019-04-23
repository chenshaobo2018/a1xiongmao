/**
 * 
 */
package com.web.good.service;

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

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ValidateUtil;
import com.api.message.AlidayuSmsConstant;
import com.api.message.SysMessageService;
import com.qcloud.sms.SmsSingleSender;
import com.qcloud.sms.SmsSingleSenderResult;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zjc.common.util.SmsUtil;
import com.zjc.prize.dao.ZjcWinPrizeDao;
import com.zjc.prize.dao.po.ZjcWinPrizePO;
import com.zjc.sms.dao.ZjcSmsLogDao;
import com.zjc.sms.dao.po.SmsType;
import com.zjc.sms.dao.po.ZjcSmsLogPO;
import com.zjc.sms.service.SmsService;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

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

/**
 * @author Administrator
 *
 */
@Service(value="WebLoginService")
public class WebLoginService {
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
	private ZjcWinPrizeDao ZjcWinPrizeDao;
	
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
		ZjcSmsLogPO ZjcSmsLogPO=new ZjcSmsLogPO();
		ZjcSmsLogPO.setMobile(inDto.getString("account"));
		ZjcSmsLogPO.setStatus(0);
		ZjcSmsLogPO send = sqlDao.list("com.zjc.sms.dao.ZjcSmsLogDao.selecttwe", ZjcSmsLogPO);
		//验证码校验
	
		if (!StringUtils.equalsIgnoreCase(send.getCode(), inDto.getString("vercode"))) {
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("验证码不对，请重新输入。");
			return outDto;
		}
		if(AOSUtils.isEmpty(inDto.getString("account"))){
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("登录账号必填！");
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
		send.setStatus(1);
		ZjcSmsLogDao.updateByKey(send);
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
		String password = dto.getString("password");
		String first_leader = dto.getString("shareId");
		String checkcode = dto.getString("checkcode");
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
		if(AOSUtils.isEmpty(checkcode)){//验证码是否为空
			msgVO.setMsg(Apiconstant.checkcode.getName());
			msgVO.setCode(Apiconstant.checkcode.getIndex());
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
		//判断图形验证码
		String sessioncode=(String) request.getSession().getAttribute("checkcode");
		if(!sessioncode.equals(checkcode)){
			msgVO.setMsg(Apiconstant.checkcode_error.getName());
			msgVO.setCode(Apiconstant.checkcode_error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		//判断手机验证码
		ZjcSmsLogPO sms=new ZjcSmsLogPO();
		sms.setMobile(mobile);
		sms.setCode(phonecode);
		sms.setType(sms_type);
		sms.setStatus(0);
		ZjcSmsLogPO ZjcSmsLogPO=(ZjcSmsLogPO) sqlDao.selectOne("com.zjc.sms.dao.ZjcSmsLogDao.selecttwe", sms);
		if(!phonecode.equals(ZjcSmsLogPO.getCode())){
			msgVO.setMsg(Apiconstant.phonecode_error.getName());
			msgVO.setCode(Apiconstant.phonecode_error.getIndex());
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
					try{
						apiLogService.saveLog(map);
						ZjcWinPrizePO ZjcWinPrizePO=new ZjcWinPrizePO();
						//注册成功后赠送抽奖到的易物卷
						ZjcWinPrizePO zjcWinPrizePO=sqlDao.list("com.zjc.prize.dao.ZjcWinPrizeDao.XcxList",ZjcWinPrizePO);
						if(zjcWinPrizePO.getPrize_name().contains("易物券")){//奖品是赠送易物券
							String prizeNum = zjcWinPrizePO.getPrize_name().substring(0, zjcWinPrizePO.getPrize_name().lastIndexOf("易"));
							if(AOSUtils.isNotEmpty(prizeNum)){//如果没有中易物券
								ZjcUsersAccountInfoPO account = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", user_id));
								if(AOSUtils.isNotEmpty(account)){//如果账户信息不为空
									account.setPay_points(account.getPay_points()+Integer.parseInt(prizeNum));//增加可转券
									zjcUsersAccountInfoDao.updateByKey(account);
									zjcWinPrizePO.setIs_use("1");
									ZjcWinPrizeDao.updateByKey(zjcWinPrizePO);
								}
							}
						}
						msgVO.setCode(Apiconstant.Do_Success.getIndex());
						msgVO.setMsg(Apiconstant.Do_Success.getName());
					} catch (Exception e){
						e.printStackTrace();
						msgVO.setCode(Apiconstant.Log_Save_Fails.getIndex());
						msgVO.setMsg(Apiconstant.Log_Save_Fails.getName());
						//日志生成失败，删除已注册信息
						zjcUsersInfoDao.deleteByKey(zuserinfo.getUser_id());
					}
				} catch(Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
			}
		}		
		return AOSJson.toJson(msgVO);	
	}
	
}
