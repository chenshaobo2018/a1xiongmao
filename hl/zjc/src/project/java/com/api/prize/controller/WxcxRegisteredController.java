/**
 * 
 */
package com.api.prize.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.prize.service.ZjcPrizeService;
import com.zjc.prize.dao.ZjcWinPrizeDao;
import com.zjc.prize.dao.po.ZjcPrizePO;
import com.zjc.prize.dao.po.ZjcWinPrizePO;
import com.zjc.prize.util.PrizeUtil;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class WxcxRegisteredController {
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ZjcPrizeService zjcPrizeService;
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcWinPrizeDao ZjcWinPrizeDao;
	
	
	/**
	 * web页面注册
	 * 
	 * @param httpModel
	 * @return
	 */
	@RequestMapping(value = "/wx/v1/wxcx_register")
	public String wxcx_register(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new  MessageVO();
		HttpModel httpModel = new HttpModel(request,response);
		Dto dto = httpModel.getInDto();
		//获取分享人ID
		String first_leader = "29999";
		String mobile = dto.getString("phone");
		String password = dto.getString("password");
		String open_id = dto.getString("open_id");
		
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
		if(AOSUtils.isEmpty(open_id)){//验证密码是否为空
			msgVO.setMsg(Apiconstant.open_id_Is_Null.getName());
			msgVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		
		
		if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
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
				zuserinfo.setOpenid(open_id);
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
						ZjcWinPrizePO.setOpen_id(open_id);
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
