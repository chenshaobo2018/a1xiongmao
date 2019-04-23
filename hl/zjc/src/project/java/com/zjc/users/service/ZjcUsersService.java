/**
 * 
 */
package com.zjc.users.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.SystemCons;

import com.zjc.common.util.PassWordMD5;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.ZjcWithdrawalDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;
import com.zjc.users.dao.po.ZjcWithdrawalPO;

/**
 * @author Administrator
 *会员接口
 */
@Service(value="zjcUsersInfoService")
public class ZjcUsersService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersInfoDao zuif;
	
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcWithdrawalDao zjcWithdrawalDao;
	
	/**
	 * 会员页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/users/zjcUsersInfolist.jsp");
			
		}
	
	/**
	 * 会员列表分页查询
	 * 
	 * @param httpModel
	 */
	public void getUserList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryForList2Page", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 会员修改列表页面数据
	 * 
	 * @param httpModel
	 */
	public void getUserlevel(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		//sqlDao.call("level",qDto);
		List<ZjcUsersInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getUserlevel",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
		
	}
	
	/**
	 * 会员发货地址
	 * 
	 * @param httpModel
	 */
	public void getUserAddress(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUserAddressDao.listUserAddressByUserId",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
		
	}
	
	/**
	 * 积分信息记录查询
	 * 
	 * @param httpModel
	 */
	public void queryForLogPage(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUsersInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.listPage",qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 积分信息记录查询
	 * 
	 * @param httpModel
	 */
	public void queryForLogOne(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUsersInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryLog",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 会员信息修改
	 * @param httpModel
	 */
	public void updateZjcUsersInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcUsersInfoPO ZjcUsersInfoPO = zjcUsersInfoDao.selectByKey(inDto.getBigInteger("user_id"));
			 String oldMobile = ZjcUsersInfoPO.getMobile();
			 if(!oldMobile.equals(inDto.getString("mobile"))){
				 ZjcUsersInfoPO isExitUser = zjcUsersInfoDao.selectOne(Dtos.newDto("mobile", inDto.getString("mobile")));
				 if(AOSUtils.isNotEmpty(isExitUser)){
					 outDto.setAppCode(AOSCons.ERROR);
					 outDto.setAppMsg("该手机号已开通会员");
					 httpModel.setOutMsg(AOSJson.toJson(outDto));
					 return;
				 }
			 }
			ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO =new ZjcUsersAccountInfoPO();
			ZjcUsersAccountInfoPO.copyProperties(inDto);
			ZjcUsersAccountInfoPO.setIs_lock(inDto.getString("acc_is_lock"));
		    String password=inDto.getString("password");
		    String passwords = inDto.getString("passwords");
		    String oldPsd = ZjcUsersInfoPO.getPassword();
		    ZjcUsersInfoPO.copyProperties(inDto);
		    try {
			    if(AOSUtils.isEmpty(password)){
			    	ZjcUsersInfoPO.setPassword(oldPsd);
			    	zuif.updateByKey(ZjcUsersInfoPO);
			    } else if(password.equals(passwords)){
			    	String p="zhongjunchuangya1212"+password;
			    	ZjcUsersInfoPO.setPassword(PassWordMD5.MD5Encode(p));
			    	zuif.updateByKey(ZjcUsersInfoPO);
			    }
				ZjcUsersAccountInfoDao.updateByKey(ZjcUsersAccountInfoPO);
				Jedis jedis = JedisUtil.getJedisClient();
				try{
					jedis.del(ZjcUsersInfoPO.getToken());
					jedis.set(ZjcUsersInfoPO.getToken(), ZjcUsersInfoPO.getUser_id()+"_"+ZjcUsersInfoPO.getIs_lock() + "_" + ZjcUsersInfoPO.getToken_validate_time());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					jedis.close();
				}
				outDto.setAppMsg("会员信息修改成功");
		    } catch(Exception e) {
		    	e.printStackTrace();
		    	outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg("会员信息修改失败");
		    }
		} else {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg("会员信息修改失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void initQueryUserPage(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/users/zjcQueryUserPage.jsp");
	}
	
	public void queryUserJuniorList(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserJuniorList", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	public void initCustomerHandlerPage(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/users/customerHandlerPage.jsp");
	}
	
	public void getUserDetial(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		Dto outDto = sqlDao.selectDto("com.zjc.users.dao.ZjcUsersInfoDao.getUserDetial", inDto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	public void initAfterSaleLog(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/users/userAfterSaleLog.jsp");
	}
	
	public void getAfterSaleLog(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> outDto = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.getAfterSaleLogPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(outDto, inDto.getPageTotal()));
	}
	
	
	/**
	 * 设置省代
	 * 
	 * @param httpModel
	 */
	public void setAgent(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		List<Dto> agentList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getAgentList", inDto);
		if(agentList.size()>0){//已存在该省的省代
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg("该省已有省代，请重新选择");
		} else {
			ZjcUsersInfoPO user = zjcUsersInfoDao.selectByKey(inDto.getBigInteger("user_id"));
			user.setProvincial_generation(inDto.getString("province_id"));
			int row = zjcUsersInfoDao.updateByKey(user);
			if(row>0){
				outDto.setAppCode(AOSCons.SUCCESS);
				outDto.setAppMsg("省代设置成功");
			} else {
				outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg("省代设置失败，请稍后再试");
			}
		}
		
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	
	/**
	 * 跳转到提成管理页面
	 * @param httpModel
	 */
	public void initWithdrawalPage(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/users/withdrawal.jsp");
	}
	
	
	/**
	 * 提成列表查询
	 * 
	 * @param httpModel
	 */
	public void getWithdrawalList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcWithdrawalDao.withdrawalListPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 修改提成状态
	 * 
	 * @param httpModel
	 */
	public void updateWithdrawal(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		
		ZjcWithdrawalPO withdrawalPO = new ZjcWithdrawalPO();
		withdrawalPO.copyProperties(inDto);
		int row = zjcWithdrawalDao.updateByKey(withdrawalPO);
		if(row == 1){
			outDto.setAppCode(AOSCons.SUCCESS);
			outDto.setAppMsg("提成状态修改成功");
		} else {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg("提成状态修改失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
