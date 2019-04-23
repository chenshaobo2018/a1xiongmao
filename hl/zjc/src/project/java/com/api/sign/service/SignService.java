/**
 * 
 */
package com.api.sign.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.sign.dao.ZjcSignRecordDao;
import com.api.sign.dao.po.ZjcSignRecordPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * @author pubing
 *
 */
@Service(value = "signService")
public class SignService {
	
	@Autowired 
	private ZjcSignRecordDao signRecordDao;
	
	@Autowired 
	private ZjcUserLogDao userLogDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ApiLogService apiLogService;
	/**
	 * 每日签到
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  daySign(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel=new HttpModel(request, response);
		MessageVO messageVO=new MessageVO();
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", request.getAttribute("user_id"));
		ZjcMemberOtherPO memberOtherPO=(ZjcMemberOtherPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey", qDto);
		qDto.put("nowDate", new Date());
		//判断用户当天是否已签到
		ZjcSignRecordPO oldSignRecord = (ZjcSignRecordPO)sqlDao.selectOne("com.api.sign.dao.ZjcSignRecordDao.selectByToday", qDto);
		//判断用户是否为企业用户
		ZjcStorePO storePO=(ZjcStorePO) sqlDao.selectOne("com.zjc.store.dao.ZjcStoreDao.selectOne", qDto);
		if(!AOSUtils.isEmpty(oldSignRecord)){//当前用户当天已经签到了
			messageVO.setCode(Apiconstant.User_Was_Signed.getIndex());
			messageVO.setMsg(Apiconstant.User_Was_Signed.getName());
		} else if(AOSUtils.isEmpty(storePO)){
			//获取用户签到次数
			int signTimes=signRecordDao.signTimes(qDto);
			//第一次签到
			if(signTimes==0){
				ZjcSignRecordPO recordPO=new ZjcSignRecordPO(qDto.getInteger("user_id"), 
						memberOtherPO.getSignstep(), "用户签到", 
						memberOtherPO.getSignstep(), 
						memberOtherPO.getSignstep(), 1);
				try{
					signRecordDao.insert(recordPO);
					//签到成功
					messageVO.setCode(Apiconstant.Do_Success.getIndex());
					messageVO.setMsg(Apiconstant.Do_Success.getName());
				}catch(Exception e){//插入过程异常
					messageVO.setCode(Apiconstant.Do_Fails.getIndex());
					messageVO.setMsg(Apiconstant.Do_Fails.getName());
					return AOSJson.toJson(messageVO);
				}
			//非第一次签到
			}else{
				//用户累计签到获取的易物劵
				int total=signRecordDao.getSignTotal(qDto);
				//用户可以转出的易物劵数量
				int abvial_total=signRecordDao.getAbTotal(qDto);
				//当天签到
				ZjcSignRecordPO recordPO=new ZjcSignRecordPO(qDto.getInteger("user_id"), 
						memberOtherPO.getSignstep(), "用户签到", 
						memberOtherPO.getSignstep()+total, 
						memberOtherPO.getSignstep()+abvial_total, 1);
				int i = signRecordDao.insert(recordPO);
				if(i>=1){
					//签到成功
					messageVO.setCode(Apiconstant.Do_Success.getIndex());
					messageVO.setMsg(Apiconstant.Do_Success.getName());
				}else{
					messageVO.setCode(Apiconstant.Do_Fails.getIndex());
					messageVO.setMsg(Apiconstant.Do_Fails.getName());
				}
			}
		}else{
			messageVO.setCode(Apiconstant.Un_Sign_Able.getIndex());
			messageVO.setMsg(Apiconstant.Un_Sign_Able.getName());
		}
		return AOSJson.toJson(messageVO);
	}
	
	/**
	 * 签到历史
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getSignList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel=new HttpModel(request, response);
		MessageVO messageVO=new MessageVO();
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page"))&&qDto.getInteger("page")<1){//当前页数不能为空
			messageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			messageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);			
			qDto.put("user_id", request.getAttribute("user_id"));
			List<ZjcSignRecordPO> signPOs=signRecordDao.listPage(qDto);
			if(signPOs.size()==0){
				messageVO.setCode(Apiconstant.query_Is_Null.getIndex());
				messageVO.setMsg(Apiconstant.query_Is_Null.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				pageVO.setList(signPOs);
				messageVO.setData(pageVO);
				messageVO.setCode(Apiconstant.Do_Success.getIndex());
				messageVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(messageVO);
	}
	
	/**
	 * 本月签到数据
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getMonthSign(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		MessageVO messageVO=new MessageVO();
		qDto.put("user_id", request.getAttribute("user_id"));
		List<ZjcSignRecordPO> signPOs=signRecordDao.listMonthSign(qDto);
		if(signPOs.size()<=0){
			messageVO.setCode(Apiconstant.NO_DATA.getIndex());
			messageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			Map<String,Object> out = new HashMap<String,Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			out.put("today", sdf.format(new Date()));
			out.put("total", signPOs.get(0).getTotal());
			out.put("available_total", signPOs.get(0).getAbvial_total());
			out.put("list", signPOs);
			messageVO.setCode(Apiconstant.Do_Success.getIndex());
			messageVO.setMsg(Apiconstant.Do_Success.getName());
			messageVO.setData(out);
		}
		return AOSJson.toJson(messageVO);
	}
	
	/**
	 * 提取签到易物劵到可用
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  signOut(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		MessageVO messageVO=new MessageVO();
		if(AOSUtils.isEmpty(qDto.getInteger("outPoint"))){
			messageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			messageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(messageVO);
		}
		qDto.put("user_id", request.getAttribute("user_id"));
		//获取用户当前的签到易物劵信息
		ZjcSignRecordPO signRecordPO=(ZjcSignRecordPO) signRecordDao.findCurrent(qDto);
		//检查是否满足转出的条件
		ZjcMemberOtherPO memberOtherPO=(ZjcMemberOtherPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey", qDto);
		int minOutPoint = memberOtherPO.getMinoutpoint();
		if(AOSUtils.isEmpty(signRecordPO)){
			messageVO.setCode(Apiconstant.NO_DATA.getIndex());
			messageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			
				if(qDto.getInteger("outPoint") < minOutPoint){//检查用户是否能转出
					messageVO.setCode(Apiconstant.Below_Minimum.getIndex());
					messageVO.setMsg("低于"+ minOutPoint +"易物劵不能转出");
				}else{
					if(qDto.getInteger("outPoint") > signRecordPO.getAbvial_total()){
						messageVO.setCode(Apiconstant.Beyond_The_Ceiling.getIndex());
						messageVO.setMsg(Apiconstant.Beyond_The_Ceiling.getName());
					}else{
						ZjcSignRecordPO recordPO = new ZjcSignRecordPO(qDto.getInteger("user_id"), 
								-qDto.getInteger("outPoint"), "提取签到奖励", signRecordPO.getTotal(), 
								signRecordPO.getAbvial_total() - qDto.getInteger("outPoint"), 2);
						try{
							sqlDao.insert("com.api.sign.dao.ZjcSignRecordDao.insert", recordPO);
							ZjcUsersAccountInfoPO accountInfo = zjcUsersAccountInfoDao.selectByKey(new BigInteger(signRecordPO.getUser_id()+""));
							accountInfo.setDue_tc_points((Integer.parseInt(accountInfo.getDue_tc_points()) + qDto.getInteger("outPoint")) +"");
							accountInfo.setPay_points(accountInfo.getPay_points()+qDto.getInteger("outPoint"));
							zjcUsersAccountInfoDao.updateByKey(accountInfo);
							messageVO.setCode(Apiconstant.Do_Success.getIndex());
							messageVO.setMsg(Apiconstant.Do_Success.getName());
							Map<String,Object> map = new HashMap<String,Object>();
							map.put("logType", "商城赠送");
							map.put("user_id", signRecordPO.getUser_id());
							map.put("type", "签到");
							map.put("due_tc_points", qDto.getInteger("outPoint"));
							map.put("now_points", accountInfo.getPay_points());
							apiLogService.saveLog(map);
						} catch(Exception e){
							messageVO.setCode(Apiconstant.Do_Fails.getIndex());
							messageVO.setMsg(Apiconstant.Do_Fails.getName());
							e.printStackTrace();
						}
					}
				}
			}
		return AOSJson.toJson(messageVO);
	}
	
	/**
	 * 获取系统的签到设置
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getSignSetting(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		MessageVO messageVO=new MessageVO();
		//获取系统当前的设置
		ZjcMemberOtherPO memberOtherPO=(ZjcMemberOtherPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey", qDto);
		if(AOSUtils.isEmpty(memberOtherPO)){
			messageVO.setCode(Apiconstant.NO_DATA.getIndex());
			messageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			messageVO.setCode(Apiconstant.Do_Success.getIndex());
			messageVO.setMsg(Apiconstant.Do_Success.getName());
			Map<String, String> out = new HashMap<>();
			out.put("signStep", memberOtherPO.getSignstep().toString());
			out.put("minOutPoint", memberOtherPO.getMinoutpoint().toString());
			messageVO.setData(out);
		}
		return AOSJson.toJson(messageVO);
	}
}
