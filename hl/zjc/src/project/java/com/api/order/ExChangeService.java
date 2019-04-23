package com.api.order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.HttpUtil;
import com.api.common.util.ValidateUtil;
import com.gexin.fastjson.JSONObject;
import com.zjc.order.dao.ZjcExchangeOrderDao;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 需要Token的app接口- 易物交易
 * 
 * @author wgm
 */
@Service(value="exChangeService")
public class ExChangeService {
	@Autowired
	private ZjcExchangeOrderDao zjcExchangeOrderDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	/**
	 * app接口-提交易物交易
	 * 
	 * @param httpModel
	 * @return json
	 */
	@Transactional(rollbackFor=Exception.class)
	public String saveExchange(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		boolean b = false;
		if(b){//关闭转账功能
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg("很抱歉，转账功能已关闭。");
			return AOSJson.toJson(msgVO);
		}
		//获取查询条件
		Dto dto = httpModel.getInDto();
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
	    if(msgVO.getCode() != 1){//会员账户被冻结
		   return AOSJson.toJson(msgVO);
	    }
		dto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcUsersInfoPO outuserInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(outuserInfo)){
			msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
			return AOSJson.toJson(msgVO);
		}
		if(AOSUtils.isEmpty(dto.getString("mobile"))){//手机号码不能为空
			msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
		} else if(AOSUtils.isEmpty(dto.getString("real_name"))){//姓名不能为空
			msgVO.setCode(Apiconstant.REAL_NAME_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.REAL_NAME_Is_Null.getName());
		} else if(outuserInfo.getIs_lock() == 1){//转出账户被锁定
			msgVO.setCode(Apiconstant.Out_Account_Is_Lock.getIndex());
			msgVO.setMsg(Apiconstant.Out_Account_Is_Lock.getName());
		} else {//判断转账用户是否存在
			if(AOSUtils.isEmpty(dto.getString("random")) || AOSUtils.isEmpty(dto.getString("sign"))){
				msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				return AOSJson.toJson(msgVO);
			}
			//查询转入户本身信息
			ZjcUsersInfoPO userInfo = new ZjcUsersInfoPO();
			userInfo.setMobile(dto.getString("mobile"));
			userInfo.setReal_name(dto.getString("real_name"));
			userInfo = zjcUsersInfoDao.selectByUseriInfoPO(userInfo);
			//查询转出账户本身信息
			ZjcUsersInfoPO outUserInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
			ZjcSellerInfoPO seller = zjcSellerInfoDao.selectByMobile(Dtos.newDto("mobile", outUserInfo.getMobile()));
			/*if(outUserInfo.getReg_time().getTime()>){//转出账户为小平台用户时
				if("普通会员".equals(outUserInfo.getLevel())){//未购买过小平台商品不能转账
					msgVO.setMsg(Apiconstant.First_Exchange_Is_Xpt.getName());
					msgVO.setCode(Apiconstant.First_Exchange_Is_Xpt.getIndex());
					return AOSJson.toJson(msgVO);
				}
			}*/
			if(AOSUtils.isEmpty(seller)){//当前转账会员不是商家会员号
				if("普通会员".equals(outUserInfo.getLevel())){//普通用户不能转账
					msgVO.setMsg(Apiconstant.Regular_Members_Not_Transfer.getName());
					msgVO.setCode(Apiconstant.Regular_Members_Not_Transfer.getIndex());
					return AOSJson.toJson(msgVO);
				}
			}
			/*if(AOSUtils.isEmpty(outUserInfo.getXpt())){//转出账户不为小平台用户时
				if(AOSUtils.isNotEmpty(userInfo.getXpt())){//转入账户不能为小平台用户
					msgVO.setMsg(Apiconstant.User_Is_Xpt.getName());
					msgVO.setCode(Apiconstant.User_Is_Xpt.getIndex());
					return AOSJson.toJson(msgVO);
				}
			}*/
			if(AOSUtils.isEmpty(userInfo)||AOSUtils.isEmpty(outUserInfo)){//转账用户不存在
				msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
			} else if(dto.getString("mobile").equals(outUserInfo.getMobile())){//手机号相等，不能向用户本身转账
				msgVO.setCode(Apiconstant.ACCOUNT_NOT_MYSELF.getIndex());
				msgVO.setMsg(Apiconstant.ACCOUNT_NOT_MYSELF.getName());
			} else if(userInfo.getIs_lock() == 1){//转入账号被锁定
				msgVO.setCode(Apiconstant.In_Account_Is_Lock.getIndex());
				msgVO.setMsg(Apiconstant.In_Account_Is_Lock.getName());
			} else {//存在，验证转账积分
				dto.put("seller_id", userInfo.getUser_id());
				dto.put("user_name", outUserInfo.getReal_name());
				if(AOSUtils.isEmpty(dto.getString("points"))&&dto.getInteger("points")<1){//积分不能为空
					msgVO.setCode(Apiconstant.Points_Was_Wrong.getIndex());
					msgVO.setMsg(Apiconstant.Points_Was_Wrong.getName());
				} else {//获取转账范围
					//获取手续费
					Map<String,Object> mapPoundage = apiPublicService.getPoundage(dto.getInteger("points"));
					Map<String,String> exchangemap = new HashMap<String,String>();
					exchangemap.put("user_id", dto.getString("user_id"));
					exchangemap.put("type", "1");//易物券转账
					exchangemap.put("fee", mapPoundage.get("fee").toString());
					/*msgVO = apiPublicService.isTransferAccount(Dtos.newDto(exchangemap),dto.getInteger("points"));
					if(msgVO.getCode() != 1){//判断当日转出额度是否超出限制
						return AOSJson.toJson(msgVO);
					}*/
					//查询转出到的账户的账户信息
					ZjcUsersAccountInfoPO inAccountInfo = zjcUsersAccountInfoDao.selectByKey(userInfo.getUser_id());
					if(AOSUtils.isEmpty(dto.getString("random"))){//随机码为空
						msgVO.setCode(Apiconstant.ROUDOM_IS_NULL.getIndex());
						msgVO.setMsg(Apiconstant.ROUDOM_IS_NULL.getName());
					}else if(AOSUtils.isEmpty(dto.getString("sign"))){//校验包为空
						msgVO.setCode(Apiconstant.SIGN_IS_NULL.getIndex());
						msgVO.setMsg(Apiconstant.SIGN_IS_NULL.getName());
					} else {
						if(ValidateUtil.checkPsd(outUserInfo, dto.getString("random"), dto.getString("sign"), ConstantUtil.PAY_PSD_TYPE)) {//支付密码正确
							//根据token获取user_id查询用户本身账户信息
							ZjcUsersAccountInfoPO outAccountInfo = zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
							//判断用户是否在搜了注册
							 String urls ="http://api1.xn--ykq093c.com/sl/api/common/v1/BulkRegistration/judgeRegistration";
							  Map<String,Object> paramst = new HashMap<String,Object>();
							  paramst.put("phone", outuserInfo.getMobile());
							  String ab=HttpUtil.doPost(urls, paramst);
							  JSONObject zhuce = JSONObject.parseObject(ab);
							  if("false".equals(zhuce.get("success").toString())){
								  msgVO.setCode(Apiconstant.LiaoDou_Too_Manys.getIndex());
								  msgVO.setMsg("请在搜了平台注册以后再来转分");
								  return AOSJson.toJson(msgVO);
								}
							/* BigDecimal decimal = new BigDecimal(100000);
							 
							 BigDecimal decimals= outAccountInfo.getTransferred().add(new BigDecimal(dto.getInteger("points")));
							 if("0".equals(outAccountInfo.getIs_sq())){
								 if(decimals.compareTo(decimal)==1) {//转账限制
										msgVO.setCode(Apiconstant.LiaoDou_Too_Manys.getIndex());
										msgVO.setMsg(Apiconstant.LiaoDou_Too_Manys.getName());
										return AOSJson.toJson(msgVO);
									} 
							 }*/
							 
							//记录未转账钱可转积分
							int oldKz = outAccountInfo.getMake_over_integral();
							//记录转账到用户原可转积分
							int inoldKz = inAccountInfo.getMake_over_integral();
							try{
								//操作账户信息，生成转账订单
								msgVO = apiPublicService.do_account(dto, outAccountInfo, inAccountInfo,mapPoundage);
								outAccountInfo.setTransferred(outAccountInfo.getTransferred().add(new BigDecimal(dto.getInteger("points"))));
								if(msgVO.getCode() == 1){//订单生成成功，生成转账日志,转账到用户
									zjcUsersAccountInfoDao.updateByKey(outAccountInfo);
									Map<String,Object> exChangemap = new HashMap<String,Object>();
									exChangemap.put("logType", "转账");
									exChangemap.put("type", "会员转会员");
									exChangemap.put("user_id", outUserInfo.getUser_id());
									exChangemap.put("to_user_id", userInfo.getUser_id());
									exChangemap.put("make_over_integral", dto.getInteger("points"));
									exChangemap.put("mobile", dto.getString("mobile"));
									exChangemap.put("user_name", dto.getString("real_name"));
									exChangemap.put("now_make_over_integral", oldKz-dto.getInteger("points")-Integer.parseInt(mapPoundage.get("fee").toString()));
									exChangemap.put("show_type", 1);
									apiLogService.saveLog(exChangemap);
									exChangemap.put("type", "会员收会员");
									exChangemap.put("user_id", userInfo.getUser_id());
									exChangemap.put("to_user_id", outUserInfo.getUser_id());
									exChangemap.put("make_over_integral", dto.getInteger("points"));
									exChangemap.put("mobile", outUserInfo.getMobile());
									exChangemap.put("user_name", outUserInfo.getReal_name());
									exChangemap.put("now_make_over_integral", inoldKz+dto.getInteger("points"));
									apiLogService.saveLog(exChangemap);
									msgVO.setCode(Apiconstant.Do_Success.getIndex());
									msgVO.setMsg(Apiconstant.Do_Success.getName());
								}
							} catch(Exception e){
								msgVO.setCode(Apiconstant.Do_Fails.getIndex());
								msgVO.setMsg(Apiconstant.Do_Fails.getName());
								e.printStackTrace();
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
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

}
