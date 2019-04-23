package com.api.register;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.alibaba.druid.util.StringUtils;
import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.login.dao.ZjcLoginParameterDao;
import com.api.login.dao.ZjcVouchersDao;
import com.api.login.dao.po.ZjcVouchersPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 不需要Tiken的app接口- 注册
 * 
 * @author wgm
 */
@Service(value="registerService")
public class RegisterService {
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcLoginParameterDao zjcLoginParameterDao;
	
	@Autowired
	private ZjcUsersDao zjcUserDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private ZjcVouchersDao ZjcVouchersDao;
	/**
	 * app接口-注册
	 * 
	 * @param request
	 * @return message
	 */
	public String app_register(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		//图片验证码
		String code = httpModel.getRequest().getParameter("code");  
		HttpSession session = httpModel.getRequest().getSession();  
		String sessionCode = (String) session.getAttribute("code");  
		if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {  //忽略验证码大小写  
		    throw new RuntimeException("验证码对应不上code=" + code + "  sessionCode=" + sessionCode);  
		}  
		//获取分享人ID
		String first_leader = dto.getString("recommended_code");
		if(AOSUtils.isEmpty(first_leader)){
			first_leader = "29999";
		}
		if(!ApiPublicService.isInteger(first_leader)){//检查上级ID是否合法
			msgVO.setCode(Apiconstant.ILLEGAL_PARAMETER.getIndex());
			msgVO.setMsg("您输入的上级ID不合规范，上级ID必须全部为数字");
			return AOSJson.toJson(msgVO);
		}
		String nickname = dto.getString("nickname");
		String mobile = dto.getString("mobile");
		String password = dto.getString("password");
		
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
		
		if(AOSUtils.isEmpty(nickname)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		if(AOSUtils.isEmpty(dto.getString("code"))){//请输入验证码
			msgVO.setMsg(Apiconstant.Code_Is_Null.getName());
			msgVO.setCode(Apiconstant.Code_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		msgVO = apiPublicService.sms_code_verify(httpModel);
		if(msgVO.getCode() != 1){//验证验证码
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
		} else if(AOSUtils.isEmpty(password)){//验证密码是否为空
			msgVO.setMsg(Apiconstant.Password_Is_Null.getName());
			msgVO.setCode(Apiconstant.Password_Is_Null.getIndex());
		} else {
			//通过手机号查询账号信息
			zuserinfo = new ZjcUsersInfoPO();
			List<ZjcUsersInfoPO> ZjcUsersInfoPOList = zjcUsersInfoDao.list(Dtos.newDto("mobile", mobile));
			//ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectByMobile(Dtos.newDto("mobile", mobile));
			if(AOSUtils.isNotEmpty(ZjcUsersInfoPOList)){//账号已存在!
				msgVO.setMsg(Apiconstant.Username_Has_Existed.getName());
				msgVO.setCode(Apiconstant.Username_Has_Existed.getIndex());
			} else {//验证通过  
				zuserinfo = new ZjcUsersInfoPO();
				BigInteger user_id = idService.nextValue(SystemCons.SEQ.SEQ_USER);
				zuserinfo.setUser_id(user_id);
				zuserinfo.setFirst_leader(Integer.valueOf(first_leader));
				zuserinfo.setSecond_leader(zPO.getFirst_leader());
				zuserinfo.setThird_leader(zPO.getSecond_leader());
				zuserinfo.setRecommended_code(user_id+"");
				zuserinfo.setMobile(mobile.trim());
				zuserinfo.setMobile_validated(1);
				zuserinfo.setNickname(nickname);
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
					//分享人获取获取代金券
					if(!"29999".equals(first_leader)){//如果分享ID不等于29999
						ZjcVouchersPO ZjcVouchers=new ZjcVouchersPO();
						ZjcVouchers.setAdd_time(new Date());
						ZjcVouchers.setIs_use(0);
						ZjcVouchers.setUser_id(first_leader);
						ZjcVouchers.setVoucher_limit("18");
						ZjcVouchers.setVouchers_id(String.valueOf(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue()));
						ZjcVouchersDao.insert(ZjcVouchers);
					}
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
					map.put("recommand_code", first_leader);
					map.put("mobile", mobile);
					apiLogService.saveLog(map);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
			}
		}			
		
		/*msgVO.setCode(Apiconstant.Do_Fails.getIndex());
		msgVO.setMsg("当前系统禁止注册账号");*/
		return AOSJson.toJson(msgVO);	
	}
	
	/**
	 * web页面注册
	 * 
	 * @param httpModel
	 * @return
	 */
	public String web_register(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		//图片验证码
		String code = httpModel.getRequest().getParameter("code");  
		HttpSession session = httpModel.getRequest().getSession();  
		String sessionCode = (String) session.getAttribute("code");  
		if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {  //忽略验证码大小写  
		    throw new RuntimeException("验证码对应不上code=" + code + "  sessionCode=" + sessionCode);  
		}  
		//获取分享人ID
		String first_leader = dto.getString("recommended_code");
		//if(AOSUtils.isEmpty(first_leader)){
			first_leader = "29999";
		//}
		if(!ApiPublicService.isInteger(first_leader)){//检查上级ID是否合法
			msgVO.setCode(Apiconstant.ILLEGAL_PARAMETER.getIndex());
			msgVO.setMsg("您输入的上级ID不合规范，上级ID必须全部为数字");
			return AOSJson.toJson(msgVO);
		}
		String nickname = dto.getString("nickname");
		String mobile = dto.getString("mobile");
		String password = dto.getString("password");
		
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
		
		if(AOSUtils.isEmpty(nickname)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		msgVO = apiPublicService.sms_code_verify(httpModel);
		if(msgVO.getCode() != 1){//验证验证码
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
				zuserinfo.setMobile(mobile.trim());
				zuserinfo.setMobile_validated(1);
				zuserinfo.setNickname(nickname);
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
					//分享人获取获取代金券
					if(!"29999".equals(first_leader)){//如果分享ID不等于29999
						ZjcVouchersPO ZjcVouchers=new ZjcVouchersPO();
						ZjcVouchers.setAdd_time(new Date());
						ZjcVouchers.setIs_use(0);
						ZjcVouchers.setUser_id(first_leader);
						ZjcVouchers.setVoucher_limit("18");
						ZjcVouchers.setVouchers_id(String.valueOf(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue()));
						ZjcVouchersDao.insert(ZjcVouchers);
					}
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
		/*msgVO.setCode(Apiconstant.Do_Fails.getIndex());
		msgVO.setMsg("当前系统禁止注册账号");*/
		return AOSJson.toJson(msgVO);	
	}
}
