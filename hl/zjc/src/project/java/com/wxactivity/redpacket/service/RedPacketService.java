/**
 * 
 */
package com.wxactivity.redpacket.service;

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
@Service(value="redPacketService")
public class RedPacketService {

	@Autowired
	private ApiPublicService apiPublicService;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ApiLogService apiLogService;
	@Autowired
	private ZjcVouchersDao ZjcVouchersDao;
	
	private static Logger log = LoggerFactory.getLogger(HttpWxActivityFilter.class);
	
	public String redPacketRegister(HttpServletRequest request,HttpServletResponse response) {
		MessageVO msgVO = new  MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		//获取分享人ID
		String mobile = dto.getString("mobile");
		String password ="zhongjunchuangya1212123456";
		String shareopenid = dto.getString("share_id");
		String open_id = AOSUtils.isEmpty(request.getSession().getAttribute("openid")) ? "" : request.getSession().getAttribute("openid").toString();
		log.info("======-----------------------------------------抢红包 open_id：--------------------------------------------------=====:" + open_id);
		String randomNum = dto.getString("randomNum");
		if(AOSUtils.isEmpty(shareopenid)){//验证参数不能为空
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		String first_leader = "";
		//获取分享人的userid
		ZjcUsersInfoPO z = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", shareopenid));
		if(AOSUtils.isNotEmpty(z)){
			first_leader = z.getUser_id().toString();
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
		if(AOSUtils.isEmpty(first_leader)){//验证上级是否为空
			 first_leader = "29999";
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
			
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			ZjcUsersInfoPO.setMobile(mobile);
			ZjcUsersInfoPO.setOpenid(open_id);
			List<ZjcUsersInfoPO> ZjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.sharelist", ZjcUsersInfoPO);
			if(AOSUtils.isNotEmpty(ZjcUsersInfoPOList)){
				if(ZjcUsersInfoPOList.size()==1 && mobile.equals(ZjcUsersInfoPOList.get(0).getMobile())){//账号已存在!
					msgVO.setCode(Apiconstant.count_is_have.getIndex());
					msgVO.setMsg(Apiconstant.count_is_have.getName());
				}else if(ZjcUsersInfoPOList.size()>1 || !mobile.equals(ZjcUsersInfoPOList.get(0).getMobile())){
					msgVO.setMsg(Apiconstant.mobile_openid_id_notMate.getName());
					msgVO.setCode(Apiconstant.mobile_openid_id_notMate.getIndex());
				}
			}else {//验证通过  
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
				zuserinfo.setOpenid(open_id);
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
					//注册用户获取代金券
					ZjcVouchersPO ZjcVouchersPO=new ZjcVouchersPO();
					ZjcVouchersPO.setAdd_time(new Date());
					ZjcVouchersPO.setIs_use(0);
					ZjcVouchersPO.setUser_id(accountInfo.getUser_id().toString());
					ZjcVouchersPO.setVoucher_limit(randomNum);
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
		return AOSJson.toJson(msgVO);	
	}
	
}
