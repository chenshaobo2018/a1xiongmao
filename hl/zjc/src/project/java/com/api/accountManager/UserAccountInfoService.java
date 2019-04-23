package com.api.accountManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ValidateUtil;
import com.api.find.dao.ZjcCpOrderDao;
import com.api.find.dao.po.ZjcCpOrderPO;
import com.zjc.store.dao.ZjcCompanyPublicDao;
import com.zjc.store.dao.po.ZjcCompanyPublicPO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * app接口-用户账户管理
 * 
 * @author wgm
 */
@Service(value="userAccountInfoService")
public class UserAccountInfoService {
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcCompanyPublicDao zjcCompanyPublicDao;
	
	@Autowired
	private ZjcCpOrderDao zjcCpOrderDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;

	/**
	 * app接口-易物券转换（可转到可用）
	 * 
	 * @param httpModel
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public String changeKzToKy(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		
		if(AOSUtils.isEmpty(dto.getString("points_num"))){//转换积分数量为空
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
		} else if(dto.getInteger("points_num")<1){//转换数量不正确
			msgVO.setMsg(Apiconstant.Data_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Data_Is_Wrong.getIndex());
		}else if(AOSUtils.isEmpty(dto.getInteger("user_id"))){//转换数量不正确
			msgVO.setMsg(Apiconstant.Data_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Data_Is_Wrong.getIndex());
		} else {
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(dto);
			//查询账户信息
			ZjcUsersAccountInfoPO accountInfo = zjcUsersAccountInfoDao.selectOne(dto);
			if(AOSUtils.isEmpty(userInfo)||AOSUtils.isEmpty(accountInfo)){//账户不存在
				msgVO.setCode(Apiconstant.Account_Not_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Account_Not_Exist.getName());
			} else if("1".equals(accountInfo.getIs_lock())){//账户被冻结了
				msgVO.setCode(Apiconstant.Account_Is_Lock.getIndex());
				msgVO.setMsg(Apiconstant.Account_Is_Lock.getName());
			} else if(accountInfo.getMake_over_integral()<dto.getInteger("points_num")){//用户可转积分不足
				msgVO.setMsg(Apiconstant.KZ_Money_Not_Enough.getName());
				msgVO.setCode(Apiconstant.KZ_Money_Not_Enough.getIndex());
			} else {//改变可用可转数量
				int oldPayPoints = accountInfo.getPay_points();//获取原本可用积分
				int oldKzPoints = accountInfo.getMake_over_integral();//获取原本可转积分
				accountInfo.setPay_points(oldPayPoints+(dto.getInteger("points_num")));
				accountInfo.setMake_over_integral(oldKzPoints-dto.getInteger("points_num"));
				try{
					zjcUsersAccountInfoDao.updateByKey(accountInfo);
					//生成用户日志
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("logType", "转账");
					map.put("type", "会员转本人");
					map.put("user_id", httpModel.getAttribute("user_id"));
					map.put("make_over_integral", dto.getInteger("points_num"));
					map.put("now_points", oldPayPoints+(dto.getInteger("points_num")));
					map.put("now_make_over_integral", oldKzPoints-dto.getInteger("points_num"));
					map.put("show_type", 1);
					apiLogService.saveLog(map);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e){
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-支付企业宣传
	 * 
	 * @param httpModel
	 * @return
	 */
	public String companyPublicPay(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getString("cp_id"))){//企业宣传ID不能为空
			msgVO.setCode(Apiconstant.CP_ID_IS_NULL.getIndex());
			msgVO.setMsg(Apiconstant.CP_ID_IS_NULL.getName());
		} else if(AOSUtils.isEmpty(dto.getString("random"))){//随机码为空
			msgVO.setCode(Apiconstant.ROUDOM_IS_NULL.getIndex());
			msgVO.setMsg(Apiconstant.ROUDOM_IS_NULL.getName());
		} else if(AOSUtils.isEmpty(dto.getString("sign"))){//校验包为空
			msgVO.setCode(Apiconstant.SIGN_IS_NULL.getIndex());
			msgVO.setMsg(Apiconstant.SIGN_IS_NULL.getName());
		} else {//查询企业宣传详情，判断该企业宣传是否需要付费支付
			//通过cp_id查询企业宣传详情
			ZjcCompanyPublicPO companyPublic = zjcCompanyPublicDao.selectByKey(dto.getInteger("cp_id"));
			if(AOSUtils.isEmpty(companyPublic)){//该企业宣传不存在
				msgVO.setCode(Apiconstant.CP_NOT_EXIST.getIndex());
				msgVO.setMsg(Apiconstant.CP_NOT_EXIST.getName());
			} else if(companyPublic.getCost_price().compareTo(BigDecimal.ZERO)==-1){//等额积分价小于0，为免费宣传，不用支付
				msgVO.setCode(Apiconstant.CP_IS_FREE.getIndex());
				msgVO.setMsg(Apiconstant.CP_IS_FREE.getName());
			} else {//查询企业宣传订单，判断该企业宣传是否已购买
				ZjcCpOrderPO cpOrder = zjcCpOrderDao.selectOne(dto);
				if(!AOSUtils.isEmpty(cpOrder)){//订单不为空，则已购买该企业宣传
					msgVO.setCode(Apiconstant.CP_WAS_BUY.getIndex());
					msgVO.setMsg(Apiconstant.CP_WAS_BUY.getName());
				} else{//查询个人账户信息，判断自己可用积分是否够支付企业宣传
					ZjcUsersAccountInfoPO accountInfo = zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
					if(AOSUtils.isEmpty(accountInfo)){
						msgVO.setCode(Apiconstant.Account_Not_Exist.getIndex());
						msgVO.setMsg(Apiconstant.Account_Not_Exist.getName());
					}else if(accountInfo.getPay_points() < companyPublic.getCost_price().intValue()){//可用积分不足，请置换后进行购买
						msgVO.setCode(Apiconstant.POINTS_NOT_ENOUGH.getIndex());
						msgVO.setMsg(Apiconstant.POINTS_NOT_ENOUGH.getName());
					} else {//验证支付密码
						ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
						if(ValidateUtil.checkPsd(userInfo, dto.getString("random"), dto.getString("sign"), ConstantUtil.PAY_PSD_TYPE)){//支付密码正确
							int oldPoints = accountInfo.getPay_points();
							int oldTotalAmounts = Integer.valueOf(accountInfo.getTotal_amount());
							//支付企业宣传
							accountInfo.setPay_points(oldPoints-companyPublic.getCost_price().intValue());
							accountInfo.setTotal_amount(oldTotalAmounts + companyPublic.getCost_price().intValue() + "");
							int successNum = zjcUsersAccountInfoDao.updateByKey(accountInfo);
							if(successNum==0){
								msgVO.setCode(Apiconstant.Save_fails.getIndex());
								msgVO.setMsg(Apiconstant.Save_fails.getName());
								accountInfo.setPay_points(oldPoints);
								accountInfo.setTotal_amount(oldTotalAmounts + "");
								zjcUsersAccountInfoDao.updateByKey(accountInfo);
							} else {//生成企业宣传订单
								cpOrder = new ZjcCpOrderPO();
								cpOrder.setOrder_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
								cpOrder.setCp_id(dto.getInteger("cp_id"));
								cpOrder.setCost_price(companyPublic.getCost_price());
								cpOrder.setUser_id(dto.getBigInteger("user_id"));
								cpOrder.setAdd_time(new Date());
								int orderSuccessNum = zjcCpOrderDao.insert(cpOrder);
								if(orderSuccessNum==0){//新增企业宣传订单失败,回滚
									msgVO.setCode(Apiconstant.SAVE_CP_ORDER_FAIL.getIndex());
									msgVO.setMsg(Apiconstant.SAVE_CP_ORDER_FAIL.getName());
									accountInfo.setPay_points(oldPoints);
									accountInfo.setTotal_amount(oldTotalAmounts + "");
									zjcUsersAccountInfoDao.updateByKey(accountInfo);
								} else {//操作成功，生成操作记录
									//TODO 生产日志
									msgVO.setCode(Apiconstant.Do_Success.getIndex());
									msgVO.setMsg(Apiconstant.Do_Success.getName());
								}
							}
						} else {//支付密码错误
							msgVO.setCode(Apiconstant.Pay_Psd_Error.getIndex());
							msgVO.setMsg(Apiconstant.Pay_Psd_Error.getName());
						}
					}
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 获取转账范围
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getExchangRang(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcUsersAccountInfoPO account = zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
		//获取查询条件
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		if(AOSUtils.isEmpty(zjcMemberOtherPO)){//请先配置系统参数
			msgVO.setCode(Apiconstant.Parameter_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Parameter_Is_Null.getName());
			return AOSJson.toJson(msgVO);
		} 
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("min_point", zjcMemberOtherPO.getTransfer_minimum());
		map.put("max_point", zjcMemberOtherPO.getMaximum_transfer());
		if(AOSUtils.isNotEmpty(account)&&account.getJf_sum()==0){//是否开通限额
			map.put("min_point", 1);
			map.put("max_point", Long.MAX_VALUE);
		}
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(map);
		
		return AOSJson.toJson(msgVO);
	}
	
}
