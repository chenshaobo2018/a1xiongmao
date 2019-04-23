package com.api.userInfo;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ValidateUtil;
import com.zjc.sms.dao.ZjcSmsLogDao;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.ZjcWithdrawalDao;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;
import com.zjc.users.dao.po.ZjcWithdrawalPO;

/**
 * @author wgm
 * 
 *app用户信息server
 */
@Service(value="userInfoService")
public class UserInfoService {
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private ZjcSmsLogDao zjcSmsLogDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private ApiLogService apiLogService;

	@Autowired
	private ZjcWithdrawalDao ZjcWithdrawalDao;
	
	/**
	 * app接口-通过token获取用户信息
	 * 
	 * @param request
	 * @return json
	 */
	public String getUserInfo(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("token"))||AOSUtils.isEmpty(httpModel.getRequest().getAttribute("user_id"))){//参数为空
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		dto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		//查询用户信息
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo", dto);
		
		if(AOSUtils.isEmpty(zjcUsersInfoPOList)){//未查询到用户信息
			msgVo.setMsg(Apiconstant.Username_Not_Exist.getName());
			msgVo.setCode(Apiconstant.Username_Not_Exist.getIndex());
		} else {//查询成功
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(zjcUsersInfoPOList.get(0));
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 用户信息修改
	 * @param httpModel
	 * @return
	 */
	public String updateUserInfo(HttpModel httpModel){
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("token"))){//参数为空
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		String user_id = httpModel.getAttribute("user_id").toString();
		ZjcUsersInfoPO zjcUsersInfoPO = new ZjcUsersInfoPO();
		zjcUsersInfoPO.copyProperties(dto);
		zjcUsersInfoPO.setUser_id(new BigInteger(user_id));
		try{
			zjcUsersInfoDao.updateByKey(zjcUsersInfoPO);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(zjcUsersInfoPO);
		} catch(Exception e){
			e.printStackTrace();
			msgVo.setMsg(Apiconstant.Update_userInfo_Lose.getName());
			msgVo.setCode(Apiconstant.Update_userInfo_Lose.getIndex());
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 用户修改密码
	 * 
	 * @param httpModel
	 * @return
	 */
	public String updateUserPassWord(HttpModel httpModel){
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		String newPass = dto.getString("password");
		String newPass2 = dto.getString("new_password");
		if(AOSUtils.isEmpty(newPass) || AOSUtils.isEmpty(newPass2)){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		ZjcUsersInfoPO oldZjcUserPo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(oldZjcUserPo)){//物用户数据
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
		} else {
			String oldPsd = oldZjcUserPo.getPassword();
			if(!oldPsd.equals(newPass)){//旧密码输入错误
				msgVo.setMsg(Apiconstant.Pass_word_err.getName());
				msgVo.setCode(Apiconstant.Pass_word_err.getIndex());
			} else {
				oldZjcUserPo.setPassword(newPass2);
				try {
					int successNum = zjcUsersInfoDao.updateByKey(oldZjcUserPo);
					if(successNum == 0) {
						msgVo.setMsg(Apiconstant.Save_fails.getName());
						msgVo.setCode(Apiconstant.Save_fails.getIndex());
					} else {
						//生成用户日志
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("logType", "修改密码");
						map.put("user_id", oldZjcUserPo.getUser_id());
						int logSuccessNum = apiLogService.saveLog(map);
						if(logSuccessNum == 0){//用户日志生成失败
							oldZjcUserPo.setPassword(oldPsd);
							zjcUsersInfoDao.updateByKey(oldZjcUserPo);
							msgVo.setCode(Apiconstant.Log_Save_Fails.getIndex());
							msgVo.setMsg(Apiconstant.Log_Save_Fails.getName());
						} else {
							msgVo.setMsg(Apiconstant.Do_Success.getName());
							msgVo.setCode(Apiconstant.Do_Success.getIndex());
						}
					}
				} catch (Exception e) {
					msgVo.setMsg(Apiconstant.Do_Fails.getName());
					msgVo.setCode(Apiconstant.Do_Fails.getIndex());
				}
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * app接口-获取我的分享信息
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getMyShareInfo(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("token", null);
		//设置上级分享人ID为自己的ID
		dto.put("first_leader", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){//当前页数不能为空
			msgVo.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcUsersInfoPO> userInfoList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.shareInfoListPage",dto);
			if(AOSUtils.isEmpty(userInfoList)){//我的分享记录为空
				msgVo.setMsg(Apiconstant.NO_DATA.getName());
				msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			} else {//有分享记录
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(userInfoList);
				pageVO.setTotalSize(userInfoList.size());
				msgVo.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 获取操作记录
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getUserLogs(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){//当前页数不能为空
			msgVo.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			//查询操作日志记录
			List<ZjcUserLogPO> logList = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.listUserLogPage",dto);
			if(AOSUtils.isEmpty(logList)){//操作记录为空
				msgVo.setMsg(Apiconstant.NO_DATA.getName());
				msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			} else {//操作记录不为空
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(logList);
				msgVo.setData(pageVO);
			}
			
		}
		return AOSJson.toJson(msgVo);
	}

	
	/**
	 * app接口-修改支付密码
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String updatePayPsd(HttpModel httpModel) {
		//验证短信验证码
		MessageVO msgVo = apiPublicService.sms_code_verify(httpModel);
		
		if(msgVo.getCode() == 1){//验证码验证成功
			Dto dto = httpModel.getInDto();
			dto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
			if(AOSUtils.isEmpty(dto.getString("pay_password"))){//支付密码不能为空
				msgVo.setMsg(Apiconstant.Pay_Psd_Is_Null.getName());
				msgVo.setCode(Apiconstant.Pay_Psd_Is_Null.getIndex());
			} else {//成功
				//查询用户信息
				ZjcUsersInfoPO userInfoPO = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
				String oldPayPsd = userInfoPO.getPay_password();
				if(!userInfoPO.getMobile().equals(dto.getString("mobile"))){//请输入当前用户的手机号
					msgVo.setMsg(Apiconstant.Phone_Is_Not_User.getName());
					msgVo.setCode(Apiconstant.Phone_Is_Not_User.getIndex());
				} else if(dto.getString("pay_password").equals(userInfoPO.getPassword())){//请不要设置为登录密码
					msgVo.setMsg(Apiconstant.PayPsd_Equal_LoginPsd.getName());
					msgVo.setCode(Apiconstant.PayPsd_Equal_LoginPsd.getIndex());
				} else if(dto.getString("pay_password").equals(oldPayPsd)){//请不要设置为当前的支付密码
					msgVo.setMsg(Apiconstant.PayPsd_Equal_NowPayPsd.getName());
					msgVo.setCode(Apiconstant.PayPsd_Equal_LoginPsd.getIndex());
				} else {//验证通过
					userInfoPO.setPay_password(dto.getString("pay_password"));
					try {
						int successNum = zjcUsersInfoDao.updateByKey(userInfoPO);
						if(successNum == 0){//修改失败
							msgVo.setCode(Apiconstant.Save_fails.getIndex());
							msgVo.setMsg(Apiconstant.Save_fails.getName());
						} else {
							//生成用户日志
							Map<String,Object> map = new HashMap<String,Object>();
							map.put("logType", "设置密码");
							map.put("user_id", userInfoPO.getUser_id());
							int logSuccessNum = apiLogService.saveLog(map);
							if(logSuccessNum == 0){//用户日志生成失败
								//日志生成失败，回滚密码
								userInfoPO.setPay_password(oldPayPsd);
								zjcUsersInfoDao.updateByKey(userInfoPO);
								msgVo.setCode(Apiconstant.Log_Save_Fails.getIndex());
								msgVo.setMsg(Apiconstant.Log_Save_Fails.getName());
							} else {
								msgVo.setCode(Apiconstant.Do_Success.getIndex());
								msgVo.setMsg(Apiconstant.Do_Success.getName());
							}
						}
					} catch (Exception e) {
						msgVo.setCode(Apiconstant.Do_Fails.getIndex());
						msgVo.setMsg(Apiconstant.Do_Fails.getName());
					}
				}
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	
	/**
	 * 查找用户支付密码
	 * 
	 * @param httpModel
	 * @return
	 */
	public String check_user_pay_password(HttpModel httpModel){
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		//通过user_id查询用户信息
		if(AOSUtils.isEmpty(dto.getString("design")) || AOSUtils.isEmpty(dto.getString("random"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else {
			ZjcUsersInfoPO usersInfoPO = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
			//检验支付密码
			if(ValidateUtil.checkPsd(usersInfoPO, dto.getString("random"), dto.getString("design"), ConstantUtil.PAY_PSD_TYPE)){
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			} else{
				msgVo.setCode(Apiconstant.Pay_Psd_Error.getIndex());
				msgVo.setMsg(Apiconstant.Pay_Psd_Error.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 我的下级
	 * 
	 * @param httpModel
	 * @return
	 */
	public String myApprentice(HttpModel httpModel){
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.get("page")) || AOSUtils.isEmpty(dto.get("type"))||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else{
			if(dto.getInteger("page")<1){
				msgVo.setCode(Apiconstant.Page_Is_Null.getIndex());
				msgVo.setMsg(Apiconstant.Page_Is_Null.getName());
			}else{
				//设置limit每页条数
				dto.put("limit", ConstantUtil.pageSize);
				//设置start开始条数
				int page = dto.getInteger("page");
				dto.put("start", (page-1)*ConstantUtil.pageSize);
				dto.put("user_id", httpModel.getAttribute("user_id"));
				dto.put("token", null);
				//查询子会员信息
				Dto countDto = zjcUsersInfoDao.counter(dto);
				List<Dto> Dtos=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.myApprenticePage", dto);
				//生成返回分页参数实体
				int total=0;
				String type=dto.getString("type");
                if(type.equals("0")){
                	total =countDto.getInteger("total");
                }else if (type.equals("1")) {
                	total =countDto.getInteger("qualified");
				}else {
					total =countDto.getInteger("unQualified");
				}
				PageVO pageVO = ParameterUtil.getPageVO(total, dto.getInteger("page"));
				pageVO.setList(Dtos);
				Map<String,Object> out = new HashMap<String,Object>();
				out.put("total", countDto.getInteger("total"));
				out.put("qualified", countDto.getInteger("qualified"));
				out.put("unQualified", countDto.getInteger("unQualified"));
				out.put("pageVO", pageVO);
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
				msgVo.setData(out);
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * app接口-更新设备号
	 * 
	 * @param httpModel
	 * @return
	 */
	public String updateClientID(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getString("clientid"))||AOSUtils.isEmpty(dto.getInteger("src_client"))||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){//设备号或者类型不能为空
			msgVo.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id")); 
			userInfo.setClientid(dto.getString("clientid"));
			userInfo.setSrc_client(dto.getInteger("src_client"));
			//修改
			try{
				zjcUsersInfoDao.updateByKey(userInfo);
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			} catch(Exception e) {
				e.printStackTrace();
				msgVo.setCode(Apiconstant.Save_fails.getIndex());
				msgVo.setMsg(Apiconstant.Save_fails.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}

	/**
	 * app接口-实名认证
	 * 
	 * @param httpModel
	 * @return
	 */
	public String authen(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		String real_name = dto.getString("real_name");
		//String id_card = dto.getString("id_card");
		if(AOSUtils.isEmpty(real_name)){//姓名或者身份证号不能为空
			msgVo.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Condition_Is_Null.getName());
		} /*else if(!ValidateUtil.isIdCard(id_card)){//身份证号码格式错误或者不合法
			msgVo.setCode(Apiconstant.ID_CARD_WAS_WRONG.getIndex());
			msgVo.setMsg(Apiconstant.ID_CARD_WAS_WRONG.getName());
		} */else {
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id")); 
			if(userInfo.getAuthentication()==1){//验证是否实名认证，1表示已经实名认证
				msgVo.setCode(Apiconstant.User_Was_Authentication.getIndex());
				msgVo.setMsg(Apiconstant.User_Was_Authentication.getName());
			} else {
				/*dto.put("user_id", null);
				dto.put("token", null);
				dto.put("authentication", 1);
				List<ZjcUsersInfoPO> userInfoExist = zjcUsersInfoDao.list(dto);
				if(userInfoExist.size()>0){//该身份证号码已经存在!
					msgVo.setCode(Apiconstant.ID_CARD_WAS_EXIST.getIndex());
					msgVo.setMsg(Apiconstant.ID_CARD_WAS_EXIST.getName());
				} else {*/
				/*	List<ZjcSellerInfoPO> zjcSellerInfoExist = zjcSellerInfoDao.list(dto);
					if(zjcSellerInfoExist.size()>0){//该身份证号码已经申请企业号
						msgVo.setCode(Apiconstant.ID_CARD_HAS_USED.getIndex());
						msgVo.setMsg(Apiconstant.ID_CARD_HAS_USED.getName());
					} else {*/
						//TODO 调用外部接口，验证身份证和真实姓名的合法性
						//修改用户实名认证信息 调用外部接口，验证身份证和真实姓名的合法性
						userInfo.setAuthentication(1);
						userInfo.setReal_name(real_name);
						//userInfo.setId_card(id_card);
						int successNum = zjcUsersInfoDao.updateByKey(userInfo);
						if(successNum == 0){//修改失败
							msgVo.setCode(Apiconstant.Save_fails.getIndex());
							msgVo.setMsg(Apiconstant.Save_fails.getName());
						} else {//修改成功
							msgVo.setCode(Apiconstant.Do_Success.getIndex());
							msgVo.setMsg(Apiconstant.Do_Success.getName());
						}
					//}
				//}
			}
		}
		return AOSJson.toJson(msgVo);
	}
				
	
	/**
	 * 用户找回密码
	 * 
	 * @param httpModel
	 * @return
	 */
	public String findPassword(HttpModel httpModel){

		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("mobile")) || AOSUtils.isEmpty(dto.getString("code")) 
				|| AOSUtils.isEmpty(dto.getString("new_password")) || AOSUtils.isEmpty(dto.getString("type"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else{
			//验证短信验证码
			MessageVO msg = apiPublicService.sms_code_verify(httpModel);
			if(msg.getCode() == 1){
				//检查用户是否存在
				ZjcUsersInfoPO usersInfoPO = zjcUsersInfoDao.selectOne(dto);
				if(AOSUtils.isEmpty(usersInfoPO)){
					msgVo.setCode(Apiconstant.Username_Not_Exist.getIndex());
					msgVo.setMsg(Apiconstant.Username_Not_Exist.getName());
				}else{
					if(usersInfoPO.getIs_lock() == 1){
						msgVo.setCode(Apiconstant.Username_Is_Lock.getIndex());
						msgVo.setMsg(Apiconstant.Username_Is_Lock.getName());
					}else{
						usersInfoPO.setPassword(dto.getString("new_password"));
						try {
							int i = zjcUsersInfoDao.updateByKey(usersInfoPO);
							if(i > 0){
								//生成用户日志
								Map<String,Object> map = new HashMap<String,Object>();
								map.put("logType", "找回密码");
								map.put("user_id", usersInfoPO.getUser_id());
								map.put("mobile", dto.getString("mobile"));
								int logSuccessNum = apiLogService.saveLog(map);
								if(logSuccessNum == 0){//用户日志生成失败
									msgVo.setCode(Apiconstant.Log_Save_Fails.getIndex());
									msgVo.setMsg(Apiconstant.Log_Save_Fails.getName());
								} else {
									msgVo.setCode(Apiconstant.Do_Success.getIndex());
									msgVo.setMsg(Apiconstant.Do_Success.getName());
								}
							}else{
								msgVo.setCode(Apiconstant.Do_Fails.getIndex());
								msgVo.setMsg(Apiconstant.Do_Fails.getName());
							}
						} catch (Exception e) {
							msgVo.setCode(Apiconstant.Do_Fails.getIndex());
							msgVo.setMsg(Apiconstant.Do_Fails.getName());
						}
					}
				}
			}else{
				msgVo.setCode(Apiconstant.Code_Was_Wrong.getIndex());
				msgVo.setMsg(Apiconstant.Code_Was_Wrong.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	
	/**
	 * app接口-用户提成
	 * 
	 * @param httpModel
	 * @return
	 */
	public String userCommission(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		String cash=dto.getString("cash");
		String user_id=httpModel.getAttribute("user_id").toString();
		if(AOSUtils.isEmpty(dto.getString("cash"))||AOSUtils.isEmpty(user_id)){//设备号或者类型不能为空
			msgVo.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			if(Integer.parseInt(cash)<100){
				msgVo.setCode(Apiconstant.Do_Fails.getIndex());
				msgVo.setMsg("提现金额不能小于100");
				return AOSJson.toJson(msgVo);
			}
			ZjcWithdrawalPO ZjcWithdrawalPO=new ZjcWithdrawalPO();
			ZjcWithdrawalPO.setAdd_time(new Date());
			ZjcWithdrawalPO.setCash(cash);
			ZjcWithdrawalPO.setIs_withdrawal(0);
			ZjcWithdrawalPO.setUser_id(new BigInteger(user_id));
			//插入
			try{
				ZjcWithdrawalDao.insert(ZjcWithdrawalPO);
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			} catch(Exception e) {
				e.printStackTrace();
				msgVo.setCode(Apiconstant.Save_fails.getIndex());
				msgVo.setMsg(Apiconstant.Save_fails.getName());
			}
		}
		  return AOSJson.toJson(msgVo);
	}

	
	/**
	 * app接口-查询用户提成金额和总金额
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getUserCommission(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		String user_id=httpModel.getAttribute("user_id").toString();
		if(AOSUtils.isEmpty(user_id)){//设备号或者类型不能为空
			msgVo.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			Dto ytx=(Dto) sqlDao.selectOne("com.zjc.users.dao.ZjcWithdrawalDao.withdrawalCounts", dto);
			Dto zje=(Dto) sqlDao.selectOne("com.zjc.users.dao.ZjcProvincialGenerationDao.selectCount", dto);
			Map<String, String> map=new HashMap<>();
			if(AOSUtils.isEmpty(ytx)){
				map.put("ytx", "0");
			}else {
				map.put("ytx", ytx.getString("cash"));
			}
			if(AOSUtils.isEmpty(zje)){
				map.put("zje", "0");
			}else {
				map.put("zje", zje.getString("commission"));
			}
			msgVo.setData(map);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		  return AOSJson.toJson(msgVo);
	}
}
