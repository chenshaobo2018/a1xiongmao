/**
 * 
 */
package com.api.login;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.api.login.dao.ZjcVouchersDao;
import com.api.login.dao.po.LoginUsersInfoVO;
import com.api.login.dao.po.ZjcVouchersPO;
import com.api.wx.wxConstant.WxUserResult;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;
import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 *
 */
@Service(value="LoginNotPasswordService")
public class LoginNotPasswordService {
   
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ApiLogService apiLogService;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcVouchersDao ZjcVouchersDao;
	/**
	 * app接口-根据open_id登陆
	 * 
	 * @param request
	 * @return message
	 */
	public String wxOpenIdLogin(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		String open_id=dto.getString("open_id");
		if(AOSUtils.isEmpty(open_id)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		//通过open_id查询账号信息
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo",dto );
			//登陆验证
			if(zjcUsersInfoPOList.size() == 0){
				LoginUsersInfoVO zjcUsersInfoType=new LoginUsersInfoVO();
				zjcUsersInfoType.setType(0);//系统中没有这个open_id
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(zjcUsersInfoType);
			} else if(zjcUsersInfoPOList.get(0).getIs_lock()==1){//账号异常已被锁定！
				msgVO.setMsg(Apiconstant.Username_Is_Lock.getName());
				msgVO.setCode(Apiconstant.Username_Is_Lock.getIndex());
			} else {
					ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoPOList.get(0);
					String oldToken = zjcUsersInfoPO.getToken();
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
					if(successNum==0){//存储失败
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
			}
		
		return AOSJson.toJson(msgVO);	
	}
	
	
	/**
	 * app接口-登陆
	 * 
	 * @param request
	 * @return message
	 */
	public String mobileLogin(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		String mobile=dto.getString("mobile");
		String wxresultJson = dto.getString("wxJson");
		WxUserResult wxUser=new WxUserResult();
		List<ZjcUsersInfoPO> zjcUsersOpenIdList=new ArrayList<>();
		if(AOSUtils.isNotEmpty(wxresultJson)){
		    wxUser = AOSJson.fromJson(wxresultJson, WxUserResult.class);
		    zjcUsersOpenIdList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo", Dtos.newDto("open_id", wxUser.getUnionid()));
		}
		//String open_id=dto.getString("open_id");
		//获取app端的随机码
		String code=dto.getString("code");
		if(AOSUtils.isEmpty(mobile)||AOSUtils.isEmpty(code)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		//查询登陆状态信息
		msgVO = apiPublicService.sms_code_verify(httpModel);
		if(msgVO.getCode() != 1){//验证验证码
			return AOSJson.toJson(msgVO);
		}
		
		//通过手机号查询账号信息
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo", Dtos.newDto("mobile", mobile));
		    //手机号不存在
			if(zjcUsersInfoPOList.size() == 0){
				//生成新的用户
				ZjcUsersInfoPO	zuserinfo = new ZjcUsersInfoPO();
				BigInteger user_id = idService.nextValue(SystemCons.SEQ.SEQ_USER);
				zuserinfo.setUser_id(user_id);
				zuserinfo.setFirst_leader(29999);
				zuserinfo.setRecommended_code(user_id+"");
				zuserinfo.setMobile(mobile.trim());
				zuserinfo.setMobile_validated(1);
				zuserinfo.setReg_time(new Date());
				zuserinfo.setIs_qualified_member(0);
				zuserinfo.setPassword(AOSCodec.md5("zhongjunchuangya1212123456"));
				zuserinfo.setLevel("1");//默认为普通会员
				if(zjcUsersOpenIdList.size()==0){
					zuserinfo.setOpenid(wxUser.getUnionid());
					zuserinfo.setHead_pic(wxUser.getHeadimgurl());
					zuserinfo.setNickname(ParameterUtil.filterEmoji(wxUser.getNickname()));
				}
				try{
					//获取当前时间(10位数的int型数字)
		            int nowTime= ParameterUtil.dateToInt();
					//生成token
					String token=AOSCodec.md5(nowTime + ParameterUtil.getRandom());
					//token有效期
					zuserinfo.setToken_validate_time(nowTime);
					zuserinfo.setToken(token);
					zjcUsersInfoDao.insert(zuserinfo);
					Jedis jedis = JedisUtil.getJedisClient();
					try{
						if(!AOSUtils.isEmpty(token)){
							jedis.del(token);
						}
						jedis.set(token, zuserinfo.getUser_id()+"_"+zuserinfo.getIs_lock() + "_" + nowTime);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						jedis.close();
					}
					//查询系统参数配置，获取用户分享的合格会员上限
					//List<ZjcMemberParameterPO> parameterPOList = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
					//添加账户信息
					ZjcUsersAccountInfoPO accountInfo = new ZjcUsersAccountInfoPO();
					accountInfo.setUser_id(user_id);
					//注册赠送
					/*if((parameterPOList.get(0).getStart_time().getTime() <= new Date().getTime()) 
							&& (new Date().getTime() <= parameterPOList.get(0).getEnd_time().getTime())){
						accountInfo.setPay_points(Integer.parseInt(parameterPOList.get(0).getGive_barter()));
					}*/
					zjcUsersAccountInfoDao.insert(accountInfo);
					//注册用户获取代金券
					ZjcVouchersPO ZjcVouchersPO=new ZjcVouchersPO();
					ZjcVouchersPO.setAdd_time(new Date());
					ZjcVouchersPO.setIs_use(0);
					ZjcVouchersPO.setUser_id(accountInfo.getUser_id().toString());
					ZjcVouchersPO.setVoucher_limit("58");
					ZjcVouchersPO.setVouchers_id(String.valueOf(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue()));
					ZjcVouchersDao.insert(ZjcVouchersPO);
					//注册赠送日志生成
					/*if((parameterPOList.get(0).getStart_time().getTime() <= new Date().getTime()) 
							&& (new Date().getTime() <= parameterPOList.get(0).getEnd_time().getTime())){
						Map<String,Object> zsmap = new HashMap<String,Object>();
						zsmap.put("logType", "注册赠送");
						zsmap.put("user_id", user_id);
						zsmap.put("type", "可用");
						zsmap.put("due_tc_points", parameterPOList.get(0).getGive_barter());
						zsmap.put("now_points", accountInfo.getPay_points());
						zsmap.put("show_type", 1);//展示到APP上
						apiLogService.saveLog(zsmap);
					}*/
					
					//生成用户日志
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("logType", "注册");
					map.put("user_id", zuserinfo.getUser_id());
					map.put("recommand_code", 29999);
					map.put("mobile", mobile);
					apiLogService.saveLog(map);
					List<ZjcUsersInfoPO> zuserinfolist = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo", Dtos.newDto("mobile", mobile));
					LoginUsersInfoVO zjcUsersInfoVO = apiPublicService.getLoginUserInfoAPP(zuserinfolist.get(0));
					Map<String, Object> maps=new HashMap<>();
					maps.put("loginUser", zjcUsersInfoVO);
					maps.put("register", true);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
					msgVO.setData(maps);
				} catch(Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
				
				/*msgVO.setCode(Apiconstant.Do_Fails.getIndex());
				msgVO.setMsg("当前系统禁止注册账号");*/
			} else if(zjcUsersInfoPOList.get(0).getIs_lock()==1){//账号异常已被锁定！
				msgVO.setMsg(Apiconstant.Username_Is_Lock.getName());
				msgVO.setCode(Apiconstant.Username_Is_Lock.getIndex());
			} else {//手机号存在
					ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoPOList.get(0);
					String oldToken = zjcUsersInfoPO.getToken();
					if(AOSUtils.isNotEmpty(wxUser.getUnionid())){
						//手机号绑定了微信时
						if(AOSUtils.isNotEmpty(zjcUsersInfoPO.getOpenid())){
								//手机号的微信与当前登录微信时绑定的
								if(zjcUsersInfoPO.getOpenid().equals(wxUser.getUnionid())){
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
									if(successNum==0 ){//存储失败
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
											Map<String, Object> maps=new HashMap<>();
											maps.put("loginUser", zjcUsersInfoVO);
											maps.put("register", false);
											msgVO.setData(maps);
										}
									}
								}else {
									msgVO.setCode(Apiconstant.openid_different.getIndex());
									msgVO.setMsg(Apiconstant.openid_different.getName());
								}
							
						}else {//open_id为空 默认绑定该手机号

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
							if(zjcUsersOpenIdList.size()==0){
								zjcUsersInfoPO.setOpenid(wxUser.getUnionid());
							}
							int successNum = zjcUsersInfoDao.updateByKey(zjcUsersInfoPO);
							if(successNum==0 ){//存储失败
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
									Map<String, Object> maps=new HashMap<>();
									maps.put("loginUser", zjcUsersInfoVO);
									maps.put("register", false);
									msgVO.setData(maps);
								}
							}
						
						}
					}else {

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
						if(successNum==0 ){//存储失败
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
								Map<String, Object> maps=new HashMap<>();
								maps.put("loginUser", zjcUsersInfoVO);
								maps.put("register", false);
								msgVO.setData(maps);
							}
						}
					
					}
			}
		return AOSJson.toJson(msgVO);	
	}
}
