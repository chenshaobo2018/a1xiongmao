package com.api.login;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.api.login.dao.ZjcLoginParameterDao;
import com.api.login.dao.po.LoginUsersInfoVO;
import com.api.login.dao.po.ZjcLoginParameterPO;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import redis.clients.jedis.Jedis;

/**
 * 不需要Tiken的app接口- 登陆
 * 
 * @author wgm
 */
@Service(value="loginServiceApi")
public class loginServiceApi {
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcLoginParameterDao zjcLoginParameterDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	/**
	 * app接口-登陆
	 * 
	 * @param request
	 * @return message
	 */
	public String app_login(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		String mobile=dto.getString("mobile");
		//获取APP生成的校验包
		String design=dto.getString("design");
		//获取app端的随机码
		String LoginRandom=dto.getString("LoginRandom");
		if(AOSUtils.isEmpty(mobile)||AOSUtils.isEmpty(design)||AOSUtils.isEmpty(LoginRandom)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		//查询登陆状态信息
		ZjcLoginParameterPO zPO =new ZjcLoginParameterPO();
		zPO.setLoginrandom(LoginRandom.trim());
		ZjcLoginParameterPO zjcLoginParameterPO = zjcLoginParameterDao.selectByLoginRandom(zPO);
		//通过手机号查询账号信息
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo", Dtos.newDto("mobile", mobile));
		if (zjcLoginParameterPO.getLoginstatus()==0){
			//登陆验证
			if(zjcUsersInfoPOList.size() == 0){
				msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
				msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			} else if(zjcUsersInfoPOList.get(0).getIs_lock()==1){//账号异常已被锁定！
				msgVO.setMsg(Apiconstant.Username_Is_Lock.getName());
				msgVO.setCode(Apiconstant.Username_Is_Lock.getIndex());
			} else {//验证校验包（密码）
				ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoPOList.get(0);
				String password=zjcUsersInfoPO.getPassword();
				String oldToken = zjcUsersInfoPO.getToken();
				//拼装校验包
				String sign=LoginRandom+mobile+password+"zjc_1815";
				sign=AOSCodec.md5(sign);
				if(sign.equals(design)) {
					//保存登录时间、ip、token,token有效期
					zjcUsersInfoPO.setLast_login(new Date());
					//获取客户端IP
					String last_ip=ParameterUtil.getIpAddr(httpModel.getRequest());
					zjcUsersInfoPO.setLast_ip(last_ip);
					//获取当前时间(10位数的int型数字)
		            int nowTime= ParameterUtil.dateToInt();
					//生成token
					String token=AOSCodec.md5(nowTime + ParameterUtil.getRandom());
					//token有效期
					zjcUsersInfoPO.setToken_validate_time(nowTime);
					zjcUsersInfoPO.setToken(token);
					int successNum = zjcUsersInfoDao.updateByKey(zjcUsersInfoPO);
					//修改登录参数状态，改为已使用
					zjcLoginParameterPO.setLoginstatus(1);
					int psuccessNum = zjcLoginParameterDao.updateByKey(zjcLoginParameterPO);
					if(successNum==0 || psuccessNum ==0){//存储失败
						msgVO.setCode(Apiconstant.Save_fails.getIndex());
						msgVO.setMsg(Apiconstant.Save_fails.getName());
					} else {//返回数据 （用户信息及token）
						//生成用户日志
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("logType", "登陆");
						map.put("user_id", zjcUsersInfoPO.getUser_id());
						int logSuccessNum = apiLogService.saveLog(map);
						if(logSuccessNum == 0){//用户日志生成失败
							msgVO.setCode(Apiconstant.Log_Save_Fails.getIndex());
							msgVO.setMsg(Apiconstant.Log_Save_Fails.getName());
						} else {
							dto.put("user_id", zjcUsersInfoPO.getUser_id());
							//封装登陆返回的用户信息实体
							LoginUsersInfoVO zjcUsersInfoVO = apiPublicService.getLoginUserInfoAPP(zjcUsersInfoPO);
							Jedis jedis = JedisUtil.getJedisClient();
							try{
								if(!AOSUtils.isEmpty(oldToken)){
									jedis.del(oldToken);
								}
								jedis.set(token, zjcUsersInfoPO.getUser_id()+"_"+zjcUsersInfoPO.getIs_lock() + "_" + nowTime);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								jedis.close();
							}
							//添加返回状态
							msgVO.setCode(Apiconstant.Do_Success.getIndex());
							msgVO.setMsg(Apiconstant.Do_Success.getName());
							msgVO.setData(zjcUsersInfoVO);
						}
					}
				} else {//校验包不正确（密码错误）
					msgVO.setMsg(Apiconstant.Password_Wrong.getName());
					msgVO.setCode(Apiconstant.Password_Wrong.getIndex());
				}
			}
			
		} else {//登陆失败，状态已使用
			msgVO.setCode(Apiconstant.Login_Status_Is_Used.getIndex());
			msgVO.setMsg(Apiconstant.Login_Status_Is_Used.getName());
		}
		return AOSJson.toJson(msgVO);	
	}
	
	/**
	 *搜了登录接口
	 * 
	 * @param request
	 * @return message
	 */
	public String SLlogin(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		String mobile=dto.getString("mobile");
		//获取APP生成的校验包
		String pass=dto.getString("password");
		String sign=dto.getString("sign");
		//获取app端的随机码
		if(AOSUtils.isEmpty(mobile)||AOSUtils.isEmpty(pass)||AOSUtils.isEmpty(sign)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		String signValue=AOSCodec.md5("zjc1518"+pass+mobile).toUpperCase();
		if(!sign.equals(signValue)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		dto.put("password", "");
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo",dto);
		String password=AOSCodec.md5("zhongjunchuangya1212"+pass);
		//通过手机号查询账号信息
		if(zjcUsersInfoPOList.size()==0){
			msgVO.setCode(0);
			msgVO.setMsg("账号不存在");
			msgVO.setData("");
		}else if(!password.equals(zjcUsersInfoPOList.get(0).getPassword())){
			msgVO.setCode(0);
			msgVO.setMsg("密码错误");
			msgVO.setData("");
		}else{
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setData(zjcUsersInfoPOList.get(0).getUser_id());
		}
		return AOSJson.toJson(msgVO);	
	}
	
	/**
	 * app接口-生成登陆参数
	 * 
	 * @param request
	 * @return LoginRandom
	 */
	public String get_login_parameter(HttpServletRequest request){
		MessageVO msgVO = new  MessageVO();
		try {
			String mobile=request.getParameter("mobile");
			if(AOSUtils.isEmpty(request.getParameter("mobile"))){
				msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
				msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
				return AOSJson.toJson(msgVO);
			}
			if(mobile.length() > 20){
				msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
				msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
				return AOSJson.toJson(msgVO);
			}
			String LoginRandom=new Date().getTime()+ "" + ParameterUtil.getRandom();
			ZjcLoginParameterPO zjcLoginParameterPO = new ZjcLoginParameterPO();
			zjcLoginParameterPO.setLoginrandom(LoginRandom);
			zjcLoginParameterPO.setLoginstatus(0);
			zjcLoginParameterPO.setMobile(mobile);
			zjcLoginParameterDao.insert(zjcLoginParameterPO);
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setData(LoginRandom);
			
		} catch (Exception e) {
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
			e.printStackTrace();
		}
		
		return AOSJson.toJson(msgVO);
	}
	
	
	/**
	 * app接口-存cliendid
	 * 
	 * @param request
	 * @return LoginRandom
	 */
	public String loginClientid(HttpServletRequest request){
		MessageVO msgVO = new  MessageVO();
		try {
			String clientid=request.getParameter("clientid");
			if(AOSUtils.isEmpty(clientid)){
				msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(msgVO);
			}
			Jedis jedis = JedisUtil.getJedisClient();
			try{
				if(!AOSUtils.isEmpty(clientid)){
					jedis.del(clientid);
				}
				jedis.sadd("clientidList",clientid);
				int time=TimeInMillis();
				jedis.expire("clientidList",time);
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				//取出列表
				//msgVO.setData(jedis.smembers("clientid"));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
			e.printStackTrace();
		}
		
		return AOSJson.toJson(msgVO);
	}
	
	   public int TimeInMillis() {
		    Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        cal.add(Calendar.DAY_OF_MONTH, 1);
	        long time=(cal.getTimeInMillis()- new Date().getTime())/1000;
			return (int)time;
		    
	}
	
	
}
