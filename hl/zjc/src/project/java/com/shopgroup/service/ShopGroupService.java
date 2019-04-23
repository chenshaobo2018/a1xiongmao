/**
 * 
 */
package com.shopgroup.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.filter.HttpWxActivityFilter;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.login.dao.ZjcVouchersDao;
import com.api.login.dao.po.ZjcVouchersPO;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="ShopGroupService")
public class ShopGroupService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ZjcVouchersDao ZjcVouchersDao;
	@Autowired
	private ApiLogService apiLogService;
	
	private static Logger log = LoggerFactory.getLogger(HttpWxActivityFilter.class);
	
	public String zeroShopGroupRegister(HttpServletRequest request,HttpServletResponse response) {
		MessageVO msgVO = new  MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		//获取分享人ID
		String mobile = dto.getString("mobile");
		String password ="zhongjunchuangya1212123456";
		//默认头像
		String normal_head = "http://omhpz0ifi.bkt.clouddn.com//usr/local/apache-tomcat-7.0.81/webapps/aosuite/uploadfile/img/2018-04-10/37a3bc57-de36-41fa-9199-dc38cb2f26ba.jpg";
		String open_id = AOSUtils.isEmpty(request.getSession().getAttribute("openid")) ? "" : request.getSession().getAttribute("openid").toString();
		log.info("======-----------------------------------------0元购 open_id：--------------------------------------------------=====:" + open_id);
		String first_leader = "29999";
		if(AOSUtils.isEmpty(open_id)){//获取openid失败
			msgVO.setMsg(Apiconstant.OPEN_ID_FAILS.getName());
			msgVO.setCode(Apiconstant.OPEN_ID_FAILS.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		if(AOSUtils.isEmpty(mobile)){//验证手机号不能为空
			msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			return AOSJson.toJson(msgVO);
		}
		if(AOSUtils.isEmpty(dto.getString("code"))){//请输入验证码
			msgVO.setMsg(Apiconstant.Code_Is_Null.getName());
			msgVO.setCode(Apiconstant.Code_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.sms_code_verify(httpModel);
		if(msgVO.getCode() != 1){//验证验证码
			return AOSJson.toJson(msgVO);
		}
		
		if(AOSUtils.isEmpty(password)){//验证密码是否为空
			msgVO.setMsg(Apiconstant.Password_Is_Null.getName());
			msgVO.setCode(Apiconstant.Password_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		//查询系统参数配置
		List<ZjcMemberParameterPO> parameterPOList = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
		//通过手机号查询账号信息
		ZjcUsersInfoPO mobilePO = zjcUsersInfoDao.selectOne(Dtos.newDto("mobile", mobile));
		ZjcUsersInfoPO openidPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", open_id));
		
		if(AOSUtils.isNotEmpty(mobilePO) && AOSUtils.isNotEmpty(openidPO)){
			if(openidPO.getOpenid().equals(mobilePO.getOpenid())){//账号已存在!
				msgVO.setCode(Apiconstant.count_is_have.getIndex());
				msgVO.setMsg(Apiconstant.count_is_have.getName());
			}else{
				msgVO.setMsg(Apiconstant.mobile_openid_id_notMate.getName());
				msgVO.setCode(Apiconstant.mobile_openid_id_notMate.getIndex());
			}
		}else if(AOSUtils.isNotEmpty(mobilePO)){
			if(AOSUtils.isNotEmpty(mobilePO.getOpenid())){//账号已存在!
				msgVO.setMsg(Apiconstant.mobile_openid_id_notMate.getName());
				msgVO.setCode(Apiconstant.mobile_openid_id_notMate.getIndex());
			}else{
				//绑定
				mobilePO.setOpenid(open_id);
				int row = zjcUsersInfoDao.updateByKey(mobilePO);
				if(row == 1){
					msgVO.setMsg(Apiconstant.openid_bind_success.getName());
					msgVO.setCode(Apiconstant.openid_bind_success.getIndex());
				}else{
					msgVO.setMsg(Apiconstant.openid_bind_false.getName());
					msgVO.setCode(Apiconstant.openid_bind_false.getIndex());
				}
			}
		}else if(AOSUtils.isNotEmpty(openidPO)){
			if(AOSUtils.isNotEmpty(openidPO.getMobile())){//账号已存在!
				msgVO.setMsg(Apiconstant.mobile_openid_id_notMate.getName());
				msgVO.setCode(Apiconstant.mobile_openid_id_notMate.getIndex());
			}else{
				//绑定
				openidPO.setMobile(mobile);
				int row = zjcUsersInfoDao.updateByKey(openidPO);
				if(row == 1){
					msgVO.setMsg(Apiconstant.openid_bind_success.getName());
					msgVO.setCode(Apiconstant.openid_bind_success.getIndex());
				}else{
					msgVO.setMsg(Apiconstant.openid_bind_false.getName());
					msgVO.setCode(Apiconstant.openid_bind_false.getIndex());
				}
			}
		}else {//验证通过  
			ZjcUsersInfoPO zuserinfo = new ZjcUsersInfoPO();
			password = AOSCodec.md5(password);
			BigInteger user_id = idService.nextValue(SystemCons.SEQ.SEQ_USER);
			zuserinfo.setUser_id(user_id);
			zuserinfo.setFirst_leader(Integer.valueOf(first_leader));
			zuserinfo.setRecommended_code(user_id+"");
			zuserinfo.setMobile(mobile);
			zuserinfo.setMobile_validated(1);
			zuserinfo.setReg_time(new Date());
			zuserinfo.setOpenid(open_id);
			zuserinfo.setPassword(password);
			zuserinfo.setIs_qualified_member(0);
			zuserinfo.setLevel("1");//默认为普通会员
			zuserinfo.setHead_pic(normal_head);//默认头像
			try{
				zjcUsersInfoDao.insert(zuserinfo);
				//添加账户信息
				ZjcUsersAccountInfoPO accountInfo = new ZjcUsersAccountInfoPO();
				accountInfo.setUser_id(user_id);
				//注册赠送
				accountInfo.setPay_points(Integer.parseInt(parameterPOList.get(0).getGive_barter()));
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
				Map<String,Object> zsmap = new HashMap<String,Object>();
				zsmap.put("logType", "注册赠送");
				zsmap.put("user_id", user_id);
				zsmap.put("type", "可用");
				zsmap.put("due_tc_points", parameterPOList.get(0).getGive_barter());
				zsmap.put("now_points", accountInfo.getPay_points());
				zsmap.put("show_type", 1);//展示到APP上
				apiLogService.saveLog(zsmap);
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
			} catch(Exception e) {
				e.printStackTrace();
				msgVO.setCode(Apiconstant.Save_fails.getIndex());
				msgVO.setMsg(Apiconstant.Save_fails.getName());
			}
		}
		return AOSJson.toJson(msgVO);	
	}
	
}
