/**
 * 
 */
package com.zjc.users.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.model.UserModel;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcAfterSalesRecordDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcAfterSalesRecordPO;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 售后操作记录表
 * @author Administrator
 *
 */
@Service(value="ZjcAfterSalesRecordService")
public class ZjcAfterSalesRecordService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	@Autowired
	private ZjcUsersInfoDao ZjcUsersInfoDao;
	
	@Autowired
	private ZjcAfterSalesRecordDao ZjcAfterSalesRecordDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	
	/**
	 * 投诉管理页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/users/ZjcAfterSalesRecord.jsp");
		}
	/**
	 * 根据id修改用户数据,并添加售后操作表
	 * @param httpModel
	 */
	public void addOrUpdateAfterSales(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("双千返卷");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			int sqpay=0;
			int make_over_integral=0;
			int operating_content=0;
			if(paramDtos.get(0).getSqvip()==1){
				make_over_integral =paramDtos.get(0).getMake_over_integral()+Integer.parseInt(inDto.get("operating_content").toString());
				operating_content=paramDtos.get(0).getSqpay()+Integer.parseInt(inDto.get("operating_content").toString());
				paramDtos.get(0).setSqpay(operating_content);
				sqpay+=paramDtos.get(0).getSqpay();
				paramDtos.get(0).setSqpay(sqpay);
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				paramDtos.get(0).setSqamount(paramDtos.get(0).getSqamount() - Integer.parseInt(inDto.get("operating_content").toString()));
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "商城赠送");
				map.put("type", "双千会员");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("due_tc_points", inDto.get("operating_content").toString());
				map.put("now_make_over_integral", paramDtos.get(0).getMake_over_integral());
				map.put("show_type", 1);
				apiLogService.saveLog(map);
				outDto.setAppMsg("操作成功");
			}else {
				outDto.setAppMsg(queid[i]+"该用户不是双千会员");
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	/**
	 * 添加双千会员
	 * @param httpModel
	 */
	public void addAfterSales(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		String sqID = "";
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				outDto.setAppMsg("会员"+queid[i]+"不存在，不能开通双创资格");
				break;
			}
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("开通双千");
			ZjcAfterSalesRecordPO.setOperating_content("开通双千");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			if(paramDtos.get(0).getSqvip() == 1){//已开通双千资格
				sqID += paramDtos.get(0).getUser_id().toString()+",";
			} else {//未开通双千资格
				paramDtos.get(0).setSqvip(1);;
				paramDtos.get(0).setSqamount(10000000);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "商城赠送");
				map.put("type", "双千资格");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("show_type", 1);
				apiLogService.saveLog(map);
			}
			
		}
		if(AOSUtils.isEmpty(sqID)){
			outDto.setAppMsg("开通双创资格成功");
		} else {
			outDto.setAppMsg(sqID + "会员已开通双创资格");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 取消双创资格
	 * 
	 * @param httpModel
	 */
	public void cancleDouble(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		String sqID = "";
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				outDto.setAppMsg("会员"+queid[i]+"不存在，不能取消双创资格");
				break;
			}
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("取消双创");
			ZjcAfterSalesRecordPO.setOperating_content("取消双创");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			if(paramDtos.get(0).getSqvip() == 0){//未开通双千资格
				sqID += paramDtos.get(0).getUser_id().toString()+",";
			} else {//已开通双千资格，取消双千资格
				paramDtos.get(0).setSqvip(0);
				paramDtos.get(0).setSqamount(0);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "商城赠送");
				map.put("type", "取消双创资格");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("show_type", 1);
				apiLogService.saveLog(map);
			}
			
		}
		if(AOSUtils.isEmpty(sqID)){
			outDto.setAppMsg("取消双创资格成功");
		} else {
			outDto.setAppMsg(sqID + "会员未开通双创资格");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 售后中赠送到可用
	 * @param httpModel
	 */
	public void payPoints(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("赠送到可用");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			if(paramDtos.size()==0){
				outDto.setAppMsg(queid[i]+"不存在");
			}else {
				int payPoints =paramDtos.get(0).getPay_points()+Integer.parseInt(inDto.get("operating_content").toString());
				paramDtos.get(0).setPay_points(payPoints);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				//添加用户日志
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "售后处理");
				map.put("type", "可用");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("due_tc_points", inDto.get("operating_content").toString());
				map.put("now_points", paramDtos.get(0).getPay_points());
				map.put("show_type", 1);
				apiLogService.saveLog(map);
				outDto.setAppMsg("操作成功");
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 赠送到可转
	 * @param httpModel
	 */
	public void MakeOverIntegral(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=inDto.getString("user_id").replaceAll("\n", ",").replaceAll(" ", "");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("赠送到可转");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			if(paramDtos.size()==0){
				outDto.setAppMsg(queid[i]+"不存在");
			}else {
				int make_over_integral =paramDtos.get(0).getMake_over_integral()+Integer.parseInt(inDto.get("operating_content").toString());
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				//添加用户日志
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "售后处理");
				map.put("type", "可转");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("due_tc_points", inDto.get("operating_content").toString());
				map.put("now_make_over_integral", paramDtos.get(0).getMake_over_integral());
				map.put("show_type", 1);
				apiLogService.saveLog(map);
				outDto.setAppMsg("操作成功");
			}
			
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 赠送商家到可转
	 * @param httpModel
	 */
	public void MakeOverIntegrals(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("赠送到商家可转");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			if(paramDtos.size()==0){
				outDto.setAppMsg(queid[i]+"不存在");
			}else {
			int make_over_integral =paramDtos.get(0).getMake_over_integral()+Integer.parseInt(inDto.get("operating_content").toString());
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				//添加用户日志
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "售后处理");
				map.put("type", "可转");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("due_tc_points", inDto.get("operating_content").toString());
				map.put("now_make_over_integral", paramDtos.get(0).getMake_over_integral());
				apiLogService.saveLog(map);
				outDto.setAppMsg("操作成功");
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 商城置换
	 * @param httpModel
	 */
	public void MakeOverIntegralsd(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("商城置换");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			int make_over_integral =paramDtos.get(0).getMake_over_integral()+Integer.parseInt(inDto.get("operating_content").toString());
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				//添加用户日志
				ZjcUserLogPO ZjcUserLogPO=new ZjcUserLogPO();
				ZjcUserLogPO.setUser_id(new BigInteger(queid[i]));
				ZjcUserLogPO.setTime(new Date());
				ZjcUserLogPO.setType("商城置换");
				ZjcUserLogPO.setDescs("商城置换");
				outDto.setAppMsg("操作成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	/**
	 * 可转可用各一半
	 * @param httpModel
	 */
	public void half(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("赠送到可转可用各一半");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			if(paramDtos.size()==0){
				outDto.setAppMsg(queid[i]+"不存在");
			}else {
			int operating_content=Integer.parseInt(inDto.get("operating_content").toString())/2;
			int make_over_integral =paramDtos.get(0).getMake_over_integral()+operating_content;
			paramDtos.get(0).setMake_over_integral(make_over_integral);
			int payPoints =paramDtos.get(0).getPay_points()+operating_content;
			paramDtos.get(0).setPay_points(payPoints);
			ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
			//添加用户日志
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logType", "售后处理");
			map.put("type", "可转可用各一半");
			map.put("user_id", new BigInteger(queid[i]));
			map.put("due_tc_points", inDto.get("operating_content").toString());
			map.put("now_points", paramDtos.get(0).getPay_points());
			map.put("now_make_over_integral", paramDtos.get(0).getMake_over_integral());
			map.put("show_type", 1);
			apiLogService.saveLog(map);
			outDto.setAppMsg("操作成功");
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	/**
	 * 赠送到会员
	 * @param httpModel
	 */
	public void halfs(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("赠送到会员");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			int operating_content=Integer.parseInt(inDto.get("operating_content").toString())/2;
			int make_over_integral =paramDtos.get(0).getMake_over_integral()+operating_content;
			paramDtos.get(0).setMake_over_integral(make_over_integral);
			int payPoints =paramDtos.get(0).getPay_points()+operating_content;
			paramDtos.get(0).setPay_points(payPoints);
			ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
			//添加用户日志
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logType", "售后处理");
			map.put("type", "可转可用各一半");
			map.put("user_id", new BigInteger(queid[i]));
			map.put("due_tc_points", inDto.get("operating_content").toString());
			map.put("now_points", paramDtos.get(0).getPay_points());
			map.put("now_make_over_integral", paramDtos.get(0).getMake_over_integral());
			map.put("show_type", 1);
			apiLogService.saveLog(map);
			outDto.setAppMsg("操作成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	/**
	 * 会员(退劵)
	 * @param httpModel
	 */
	public void halfd(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("会员(退劵)");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			int operating_content=Integer.parseInt(inDto.get("operating_content").toString())/2;
			int make_over_integral =paramDtos.get(0).getMake_over_integral()+operating_content;
			paramDtos.get(0).setMake_over_integral(make_over_integral);
			int payPoints =paramDtos.get(0).getPay_points()+operating_content;
			paramDtos.get(0).setPay_points(payPoints);
			ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
			//添加用户日志
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logType", "售后处理");
			map.put("type", "退券");
			map.put("user_id", new BigInteger(queid[i]));
			map.put("due_tc_points", inDto.get("operating_content").toString());
			map.put("now_points", paramDtos.get(0).getPay_points());
			map.put("now_make_over_integral", paramDtos.get(0).getMake_over_integral());
			map.put("show_type", 1);
			apiLogService.saveLog(map);
			outDto.setAppMsg("操作成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	/**
	 * 批量封号
	 * @param httpModel
	 */
	public void titles(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("批量封号");
			ZjcAfterSalesRecordPO.setOperating_content("批量封号");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			ZjcUsersInfoPO.setRecommended_code(queid[i]);
			//查询用户积分情况
			List<ZjcUsersInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getUser", ZjcUsersInfoPO);
			if(paramDtos.size()==0){
				outDto.setAppMsg(queid[i]+"不存在");
			}else {
				paramDtos.get(0).setIs_lock(1);
				ZjcUsersInfoDao.updateByKey(paramDtos.get(0));
				//添加用户日志
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("logType", "账户冻结");
				map.put("user_id", new BigInteger(queid[i]));
				map.put("show_type", 1);
				apiLogService.saveLog(map);
				outDto.setAppMsg("操作成功");
			}
			
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 批量解封
	 * @param httpModel
	 */
	public void unlock(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("批量解封");
			ZjcAfterSalesRecordPO.setOperating_content("批量解封");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			ZjcUsersInfoPO ZjcUsersInfoPO=new ZjcUsersInfoPO();
			ZjcUsersInfoPO.setRecommended_code(queid[i]);
			//查询用户积分情况
			List<ZjcUsersInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getUser", ZjcUsersInfoPO);
			if(paramDtos.size()==0){
				outDto.setAppMsg(queid[i]+"不存在");
			}else {
			paramDtos.get(0).setIs_lock(0);
			ZjcUsersInfoDao.updateByKey(paramDtos.get(0));
			//添加用户日志
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logType", "账户解封");
			map.put("user_id", new BigInteger(queid[i]));
			map.put("show_type", 1);
			apiLogService.saveLog(map);
			outDto.setAppMsg("操作成功");
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 平台线下置换
	 * @param httpModel
	 */
	public void PlatformOfflineReplacement(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("平台线下置换");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			//查询系统配置参数
			List<ZjcMemberParameterPO> param = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			int Offline =param.get(0).getOffline_top_up_barter_volume();
			int mini=param.get(0).getTop_up_barter_volume_minimum();
			int operating_content=Integer.parseInt(inDto.get("operating_content").toString());
			if(Offline!=0&& operating_content>=mini){
				int juan=operating_content*Offline/2;
				int make_over_integral =paramDtos.get(0).getMake_over_integral()+juan;
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				int payPoints =paramDtos.get(0).getPay_points()+juan;
				paramDtos.get(0).setPay_points(payPoints);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
				
			}else {
				int make_over_integral =paramDtos.get(0).getMake_over_integral()+operating_content;
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
			}
			//添加用户日志
			ZjcUserLogPO ZjcUserLogPO=new ZjcUserLogPO();
			ZjcUserLogPO.setUser_id(new BigInteger(queid[i]));
			ZjcUserLogPO.setTime(new Date());
			ZjcUserLogPO.setType("平台线下置换");
			ZjcUserLogPO.setDescs("平台线下置换");
			outDto.setAppMsg("操作成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 平台置换补返
	 * @param httpModel
	 */
	public void PlatformReplacementBack(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		UserModel um=httpModel.getUserModel();
		String user_id=(String) inDto.get("user_id");
		user_id = user_id.replace("\n", ",");
		String [] queid=user_id.split(",");
		for (int i = 0; i < queid.length; i++) {
			if(AOSUtils.isEmpty(queid[i])){
				break;
			}
			ZjcUsersAccountInfoPO accountInfo = ZjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id",queid[i]));
			if(AOSUtils.isEmpty(accountInfo)){
				break;
			}
			//添加售后操作记录
			ZjcAfterSalesRecordPO ZjcAfterSalesRecordPO =new ZjcAfterSalesRecordPO();
			ZjcAfterSalesRecordPO.copyProperties(inDto);
			ZjcAfterSalesRecordPO.setUser_id(Integer.parseInt(queid[i]));
			ZjcAfterSalesRecordPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			ZjcAfterSalesRecordPO.setOperation(um.getId());
			ZjcAfterSalesRecordPO.setAdd_time(new Date());
			ZjcAfterSalesRecordPO.setOperation_type("平台置换补返");
			ZjcAfterSalesRecordDao.insert(ZjcAfterSalesRecordPO);
			//查询系统配置参数
			List<ZjcMemberParameterPO> param = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
			ZjcUsersAccountInfoPO UsersAccount=new ZjcUsersAccountInfoPO();
			UsersAccount.setUser_id(new BigInteger(queid[i]));
			//查询用户积分情况
			List<ZjcUsersAccountInfoPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users", UsersAccount);
			int Offline =param.get(0).getOffline_top_up_barter_volume();
			int mini=param.get(0).getTop_up_barter_volume_minimum();
			int operating_content=Integer.parseInt(inDto.get("operating_content").toString());
			if(Offline!=0&& operating_content>=mini){
				int juan=operating_content*Offline/2;
				int make_over_integral =paramDtos.get(0).getMake_over_integral()+juan;
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				int payPoints =paramDtos.get(0).getPay_points()+juan;
				paramDtos.get(0).setPay_points(payPoints);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
			}else {
				int make_over_integral =paramDtos.get(0).getMake_over_integral()+operating_content;
				paramDtos.get(0).setMake_over_integral(make_over_integral);
				ZjcUsersAccountInfoDao.updateByKey(paramDtos.get(0));
			}
			//添加用户日志
			ZjcUserLogPO ZjcUserLogPO=new ZjcUserLogPO();
			ZjcUserLogPO.setUser_id(new BigInteger(queid[i]));
			ZjcUserLogPO.setTime(new Date());
			ZjcUserLogPO.setType("平台置换补返");
			ZjcUserLogPO.setDescs("平台置换补返");
			outDto.setAppMsg("操作成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
