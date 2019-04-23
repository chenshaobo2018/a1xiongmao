/**
 * 
 */
package com.zjc.store.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.store.record.dao.ZjcIncomeFlowDao;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="zjcSellerInfoService")
public class ZjcSellerInfoService {

	//private static Logger logger = LoggerFactory.getLogger(ZjcSellerInfoService.class);
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcIncomeFlowDao zjcIncomeFlowDao;
	@Autowired
	private ZjcUserLogDao userLogDao;
	

	/**
	 * 通过店铺id查询店铺信息
	 * 
	 * @param httpModel
	 */
	public void selectByUserId(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		//查询店铺信息
		List<Dto> zjcSellerInfoList=sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.selectByUserID",inDto);
		httpModel.setOutMsg(AOSJson.toJson(zjcSellerInfoList, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 
	 * 商家账户信息修改
	 * 
	 * @param httpModel
	 */
	public void updateSellerInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//查询原商家信息
		ZjcSellerInfoPO oldZjcSellerInfoPO = zjcSellerInfoDao.selectByKey(inDto.getBigInteger("user_id"));
		ZjcUsersAccountInfoPO oldZjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(inDto.getBigInteger("user_id"));
		if(!oldZjcSellerInfoPO.getMobile().equals(inDto.getString("mobile"))){
			ZjcSellerInfoPO isExitSeller = zjcSellerInfoDao.selectOne(Dtos.newDto("mobile", inDto.getString("mobile")));
			if(AOSUtils.isNotEmpty(isExitSeller)){//该手机号一存在
				outDto.setAppMsg("该手机号已注册企业号");
				httpModel.setOutMsg(AOSJson.toJson(outDto));
				return;
			}
		}
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcSellerInfoPO zjcSellerInfoPO = new ZjcSellerInfoPO();
			zjcSellerInfoPO.copyProperties(oldZjcSellerInfoPO);
			zjcSellerInfoPO.setMobile(inDto.getString("mobile"));
			zjcSellerInfoPO.setReal_name(inDto.getString("real_name"));
			zjcSellerInfoPO.setId_card(inDto.getString("id_card"));
			//更新商家会员信息时同时账户信息
			try{
				zjcSellerInfoDao.updateByKey(zjcSellerInfoPO);
				ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = new ZjcUsersAccountInfoPO();
				zjcUsersAccountInfoPO.copyProperties(oldZjcUsersAccountInfoPO);
				zjcUsersAccountInfoPO.setMake_over_integral(inDto.getInteger("make_over_integral"));
				zjcUsersAccountInfoPO.setHas_terminal(inDto.getInteger("has_terminal"));
				zjcUsersAccountInfoPO.setIs_lock(inDto.getString("is_lock"));
				zjcUsersAccountInfoDao.updateByKey(zjcUsersAccountInfoPO);
				outDto.setAppMsg("商家信息修改成功");
			} catch(Exception e){
				outDto.setAppMsg("商家信息修改失败");
			}
		} else {
			outDto.setAppMsg("商家信息修改失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 积分信息记录查询
	 * 
	 * @param httpModel
	 */
	public void queryForLogPage(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUserLogPO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.listPage",qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 积分信息记录查询
	 * 
	 * @param httpModel
	 */
	public void queryForLogOne(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcUsersInfoPO> paramDtos = sqlDao.list("com.zjc.store.dao.ZjcSellerInfoDao.queryLog",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	public void getSellerUserInfo(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectByKey(qDto.getBigInteger("user_id"));
		qDto.put("mobile", sellerInfoPO.getMobile());
		Dto outDto = sqlDao.selectDto("com.zjc.users.dao.ZjcUsersInfoDao.selectByUserID", qDto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void getSellerAccountInfo(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.getSellerAccountInfo", qDto);
		httpModel.setOutMsg(AOSJson.toJson(list));
	}
	
	public void initLogTool(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/logToolPage.jsp");
	}
	
	/**
	 * 后台为商家添加日志
	 * @param httpModel
	 */
	public void saveLog(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		//用户账号
		//ZjcUsersAccountInfoPO userAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(inDto.getBigInteger("user_id"));
		//商家账号
		ZjcUsersAccountInfoPO sellerAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(inDto.getBigInteger("seller_id"));
		if(AOSUtils.isEmpty(sellerAccountInfoPO)){
			httpModel.setOutMsg("商家ID不存在");
			return;
		}
		int type = inDto.getInteger("type");
		if(type ==1 || type==3){//支出
			inDto.put("expend", inDto.getString("num"));
			inDto.put("in_user_id", 0);
			inDto.put("out_user_id", sellerAccountInfoPO.getUser_id());
		} else {
			inDto.put("income", inDto.getString("num"));
			inDto.put("in_user_id", sellerAccountInfoPO.getUser_id());
			inDto.put("out_user_id", 0);
			if(type == 4){
				inDto.put("type", "3");	
			}
		}
		inDto.put("balance", sellerAccountInfoPO.getMake_over_integral());
		ZjcIncomeFlowPO incomeFlowPO = new ZjcIncomeFlowPO();
		AOSUtils.copyProperties(inDto, incomeFlowPO);
		try{
			zjcIncomeFlowDao.insert(incomeFlowPO);
			httpModel.setOutMsg("操作成功");
			ZjcUserLogPO userLogPO = new ZjcUserLogPO();
			if(type == 1 || type == 3){
				userLogPO.setDescs("亲，很抱歉因系统异常，您被扣除"+inDto.getString("num")+"券！当前可转券：" + sellerAccountInfoPO.getMake_over_integral());
			} else {
				userLogPO.setDescs("亲，恭喜您获得商城赠送您的"+inDto.getString("num")+"券已到账，请查收！订单号为："+inDto.getString("order_sn")+"，当前可转券为：" + sellerAccountInfoPO.getMake_over_integral());
			}
			userLogPO.setShow_type(0);
			userLogPO.setTime(new Date());
			userLogPO.setType("售后处理");
			userLogPO.setUser_id(inDto.getBigInteger("seller_id"));
			userLogDao.insert(userLogPO);
		} catch (Exception e){
			e.printStackTrace();
			httpModel.setOutMsg("操作失败");
		}
	}
	
	/**
	 * 修改商家状态
	 * 
	 * @param httpModel
	 */
	public void changeLock(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//查询原店铺信息
		ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectByKey(inDto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(sellerInfoPO)){
			outDto.setAppMsg("操作失败");
		} else {
			if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
				sellerInfoPO.setIs_lock(inDto.getInteger("is_lock"));
				zjcSellerInfoDao.updateByKey(sellerInfoPO);
				if(inDto.getInteger("is_lock") == 0){
					outDto.setAppMsg("启用成功");
				} else {
					outDto.setAppMsg("停用成功");
				}
				
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	
}
