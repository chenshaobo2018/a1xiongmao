package com.sellerApp.login;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.api.login.dao.ZjcLoginParameterDao;
import com.api.login.dao.po.ZjcLoginParameterPO;
import com.sellerApp.login.po.LoginSellerInfoVO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;

/**
 * 不需要Tiken的app接口- 登陆
 * 
 * @author wgm
 */
@Service(value="sellerLoginService")
public class SellerLoginService {
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
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
		zPO.setLoginrandom(LoginRandom);
		ZjcLoginParameterPO zjcLoginParameterPO = zjcLoginParameterDao.selectByLoginRandom(zPO);
		//通过手机号查询账号信息
		List<ZjcSellerInfoPO> zjcSellerInfoList = sqlDao.list("com.zjc.store.dao.ZjcSellerInfoDao.queryUserInfo", Dtos.newDto("mobile", mobile));
		if (zjcLoginParameterPO.getLoginstatus()==0){
			//登陆验证
			if(zjcSellerInfoList.size() == 0){
				msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
				msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			} else if(zjcSellerInfoList.get(0).getIs_lock()==1){//账号异常已被锁定！
				msgVO.setMsg(Apiconstant.Store_Not_Used.getName());
				msgVO.setCode(Apiconstant.Store_Not_Used.getIndex());
			} else {//验证校验包（密码）
				ZjcSellerInfoPO zjcSellerInfoPO = zjcSellerInfoList.get(0);
				String password=zjcSellerInfoPO.getPassword();
				String oldToken = zjcSellerInfoPO.getToken();
				//拼装校验包
				//String sign=LoginRandom+mobile+password+"zjc_1815";
				//sign=AOSCodec.md5(sign);
				if(password.equals(design)) {
					//保存登录时间、ip、token,token有效期
					zjcSellerInfoPO.setLast_login(new Date());
					//获取客户端IP
					String last_ip=ParameterUtil.getIpAddr(httpModel.getRequest());
					System.out.println("----------------------------------商家app_login--last_ip:" + last_ip + "---------------------------------------------------");
					System.out.println("----------------------------------商家app_login--last_ip:" + last_ip + "---------------------------------------------------");
					zjcSellerInfoPO.setLast_ip(last_ip);
					//获取当前时间(10位数的int型数字)
		            int nowTime= ParameterUtil.dateToInt();
					//生成token
					String token=AOSCodec.md5(nowTime + ParameterUtil.getRandom());
					//token有效期
					zjcSellerInfoPO.setToken_validate_time(nowTime);
					zjcSellerInfoPO.setToken(token);
					try{
						zjcSellerInfoDao.updateByKey(zjcSellerInfoPO);
						//修改登录参数状态，改为已使用
						zjcLoginParameterPO.setLoginstatus(1);
						zjcLoginParameterDao.updateByKey(zjcLoginParameterPO);
						//生成用户日志
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("logType", "登陆");
						map.put("user_id", zjcSellerInfoPO.getUser_id());
						apiLogService.saveLog(map);
						dto.put("user_id", zjcSellerInfoPO.getUser_id());
						//封装登陆返回的用户信息实体
						LoginSellerInfoVO zjcUsersInfoVO = apiPublicService.getLoginUserInfoAPP(zjcSellerInfoPO);
						Jedis jedis = JedisUtil.getJedisClient();
						try{
							if(!AOSUtils.isEmpty(oldToken)){
								jedis.del(oldToken);
							}
							jedis.set(token, zjcSellerInfoPO.getUser_id()+"_"+zjcSellerInfoPO.getIs_lock() + "_" + nowTime);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							jedis.close();
						}
						//添加返回状态
						msgVO.setCode(Apiconstant.Do_Success.getIndex());
						msgVO.setMsg(Apiconstant.Do_Success.getName());
						msgVO.setData(zjcUsersInfoVO);
					} catch(Exception e){
						msgVO.setCode(Apiconstant.Save_fails.getIndex());
						msgVO.setMsg(Apiconstant.Save_fails.getName());
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
	 * app接口-生成登陆参数
	 * 
	 * @param request
	 * @return LoginRandom
	 */
	public String get_login_parameter(HttpServletRequest request){
		MessageVO msgVO = new  MessageVO();
		try {
			String mobile=request.getParameter("mobile");
			//mobile=request.getAttribute("mobile").toString();
			String LoginRandom=new Date().getTime()+ "" + ParameterUtil.getRandom();
			ZjcLoginParameterPO zjcLoginParameterPO = new ZjcLoginParameterPO();
			zjcLoginParameterPO.setLoginrandom(LoginRandom);
			zjcLoginParameterPO.setLoginstatus(0);
			zjcLoginParameterPO.setMobile(mobile);
			int successNum = zjcLoginParameterDao.insert(zjcLoginParameterPO);
			if(successNum==0){//参数生成失败
				msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			} else {
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(LoginRandom);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return AOSJson.toJson(msgVO);
	}
}
