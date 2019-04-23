/**
 * 
 */
package com.store.login;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.ErrorCode;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ParameterUtil;
import com.api.message.SysMessageService;
import com.api.order.OrderService;
import com.taobao.api.ApiException;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersDao;
import com.zjc.users.dao.ZjcUsersInfoDao;

/**
 * 商家管理后台登录
 * 
 * @author zc
 *
 */
@Service(value="storeLoginService")
public class StoreLoginService {
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired
	private SysMessageService sysMessageService;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersDao zjcUsersDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	private static final Logger logger = LoggerFactory
			.getLogger(StoreLoginService.class);
	
	
	public Dto login(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		Dto outDto = Dtos.newDto();
		outDto.setAppCode(AOSCons.SUCCESS);
		//验证码校验
		String cached_vercode_ = JedisUtil.getString(inDto.getString("vercode_uuid"));
		if (!StringUtils.equalsIgnoreCase(cached_vercode_, inDto.getString("vercode"))) {
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
		ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectOne(qDto);
		Boolean is_pass = true;
		if (AOSUtils.isEmpty(sellerInfoPO)) {
			outDto.setAppCode(ErrorCode.NO_ACCOUNT);
			outDto.setAppMsg("账号不存在，请重新输入。");
			is_pass = false;
		}else if(sellerInfoPO.getIs_lock() == 1){
			outDto.setAppCode(ErrorCode.LOCKED_ERROR);
			outDto.setAppMsg("该帐号已锁定或已停用，请联系管理员。");
			is_pass = false;
		} else {
			// 密码校验
			String password = AOSCodec.md5("zhongjunchuangya1212"+inDto.getString("password"));
			if (!StringUtils.equals(password, sellerInfoPO.getPassword())) {
				outDto.setAppCode(ErrorCode.PASSWORD_ERROR);
				outDto.setAppMsg("密码输入错误，请重新输入。");
				is_pass = false;
			} else {
				// 状态校验
				if (sellerInfoPO.getIs_lock().equals(SystemCons.USER_STATUS.NORMAL)) {
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
		ZjcSellerInfoPO newSellerInfo = new ZjcSellerInfoPO();
		newSellerInfo.copyProperties(sellerInfoPO);
		newSellerInfo.setLast_login(new Date());
		logger.info("----------------------------------商家后台登录last_ip:" + last_ip + "---------------------------------------------------");
		newSellerInfo.setLast_ip(last_ip);
		zjcSellerInfoDao.updateByKey(newSellerInfo);
		//ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		ZjcStorePO zjcStorePO = zjcStoreDao.selectOne(Dtos.newDto("user_id",sellerInfoPO.getUser_id()));
		if(AOSUtils.isEmpty(zjcStorePO)){
			outDto.setAppCode(ErrorCode.NO_STORE);
			outDto.setAppMsg("您还未开店，请开店后再尝试登录。");
		}else if(zjcStorePO.getStore_state() != 3){
			outDto.setAppCode(ErrorCode.NOT_ADUIT);
			outDto.setAppMsg("您的店铺店铺还未通过审核。");
		}else{
			zjcStorePO.setLast_login_time(new Date());
			zjcStorePO.setLast_login_ip(last_ip);
			zjcStoreDao.updateByKey(zjcStorePO);
			//创建session对象
	        HttpSession session = httpModel.getRequest().getSession();
	        //把用户数据保存在session域对象中
	        session.setAttribute("sellerInfo", sellerInfoPO);
	        //获取店铺logo
	        session.setAttribute("store_logo", zjcStorePO.getStore_logo());
		}
		return outDto;
	}
	
	//短信验证
	public String getVerificationCode(HttpModel httpModel) throws ApiException{
		httpModel.getInDto().put("sms_type", "find_password");
		String str = sysMessageService.alidayuSms(httpModel);
		return str;
	}
	
	//短信验证
	public String validate_code(HttpModel httpModel) throws ApiException{
		httpModel.getInDto().put("type", "find_password");
		return AOSJson.toJson(apiPublicService.sms_code_verify(httpModel));
	}
	
	//短信验证
	@Transactional(rollbackFor=Exception.class)
	public MessageVO confirm_reset(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) sqlDao.selectOne("com.zjc.store.dao.ZjcSellerInfoDao.selectByMobile", dto);
		if(AOSUtils.isEmpty(sellerInfoPO)){
			msg.setCode(Apiconstant.Account_Not_Exist.getIndex());
			msg.setMsg(dto.getString("mobile")+"的商家不存在，请确认您的手机号是否正确！");
		}else{
			//加密密码
			String new_password = AOSCodec.md5("zhongjunchuangya1212"+dto.getString("new_password"));
			sellerInfoPO.setPassword(new_password);
			try {
				zjcSellerInfoDao.updateByKey(sellerInfoPO);
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg(Apiconstant.Do_Success.getName());
			} catch (Exception e) {
				e.printStackTrace();
				msg.setCode(Apiconstant.Do_Fails.getIndex());
				msg.setMsg(Apiconstant.Do_Fails.getName());
			}
		}
		return msg;
	}
}
