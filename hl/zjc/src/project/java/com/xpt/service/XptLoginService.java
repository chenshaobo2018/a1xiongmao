/**
 * 
 */
package com.xpt.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.common.util.ValidateUtil;
import com.api.goods.dao.XptgoodsDao;
import com.api.goods.dao.po.XptgoodsPO;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

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
@Service(value="XptLoginService")
public class XptLoginService {
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	@Autowired
	private XptgoodsDao XptgoodsDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	public String XptRegistered(HttpModel httpModel){
		MessageVO msgVO = new  MessageVO();
		Dto dto = httpModel.getInDto();
		
		//获取分享人ID
		String first_leader = dto.getString("recommended_code");
		if(AOSUtils.isEmpty(first_leader)){
			first_leader = "5153956";
		}
		if(!ApiPublicService.isInteger(first_leader)){//检查上级ID是否合法
			msgVO.setCode(Apiconstant.ILLEGAL_PARAMETER.getIndex());
			msgVO.setMsg("您输入的上级ID不合规范，上级ID必须全部为数字");
			return AOSJson.toJson(msgVO);
		}
		String nickname = dto.getString("nickname");
		String mobile = dto.getString("mobile");
		String password = dto.getString("password");
		
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
		
		if(AOSUtils.isEmpty(nickname)){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		if(mobile.length()>20){
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			return AOSJson.toJson(msgVO);
		}
		
		/*msgVO = apiPublicService.sms_code_verify(httpModel);
		if(msgVO.getCode() != 1){//验证验证码
			return AOSJson.toJson(msgVO);
		}*/
		
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
				password = AOSCodec.md5("zhongjunchuangya1212"+password);
				BigInteger user_id = idService.nextValue(SystemCons.SEQ.SEQ_USER);
				zuserinfo.setUser_id(user_id);
				zuserinfo.setFirst_leader(Integer.valueOf(first_leader));
				zuserinfo.setSecond_leader(zPO.getFirst_leader());
				zuserinfo.setThird_leader(zPO.getSecond_leader());
				zuserinfo.setRecommended_code(user_id+"");
				zuserinfo.setMobile(mobile);
				zuserinfo.setMobile_validated(1);
				zuserinfo.setNickname(nickname);
				zuserinfo.setReg_time(new Date());
				zuserinfo.setPassword(password);
				zuserinfo.setIs_qualified_member(0);
				zuserinfo.setLevel("普通会员");//默认为普通会员
				zuserinfo.setXpt("xpt");
				try{
					zjcUsersInfoDao.insert(zuserinfo);
					msgVO.setData(zuserinfo);
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
					
					//生成用户日志
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("logType", "xpt注册");
					map.put("user_id", zuserinfo.getUser_id());
					map.put("recommand_code", first_leader);
					map.put("mobile", mobile);
					try{
						apiLogService.saveLog(map);
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
	
	
	public String XptSaveExchange(HttpModel httpModel) {
		String user_id="5153956";
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", user_id));
	    if(msgVO.getCode() != 1){//会员账户被冻结
		   return AOSJson.toJson(msgVO);
	    }
		dto.put("user_id", user_id);
		ZjcUsersInfoPO outuserInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(outuserInfo)){
			msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
			return AOSJson.toJson(msgVO);
		}
		String sign=dto.getString("sign");
		String signa=AOSCodec.md5(dto.getString("mobile")+dto.getString("points")+dto.getString("random"));
		if(!sign.equals(signa)){
			msgVO.setCode(Apiconstant.Efficacy_packet_error.getIndex());
			msgVO.setMsg(Apiconstant.Efficacy_packet_error.getName());
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
			/*if(AOSUtils.isEmpty(dto.getString("random")) || AOSUtils.isEmpty(dto.getString("sign"))){
				msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				return AOSJson.toJson(msgVO);
			}*/
			//查询转入户本身信息
			ZjcUsersInfoPO userInfo = new ZjcUsersInfoPO();
			userInfo.setMobile(dto.getString("mobile"));
			userInfo.setReal_name(dto.getString("real_name"));
			userInfo.setXpt("xpt");
			userInfo = zjcUsersInfoDao.selectByUseriInfoPO(userInfo);
			//查询转出账户本身信息
			ZjcUsersInfoPO outUserInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
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
					msgVO = apiPublicService.isTransferAccount(Dtos.newDto(exchangemap),dto.getInteger("points"));
					if(msgVO.getCode()!=1){//判断当日转出额度是否超出限制
						return AOSJson.toJson(msgVO);
					}
					//查询转出到的账户的账户信息
					ZjcUsersAccountInfoPO inAccountInfo = zjcUsersAccountInfoDao.selectByKey(userInfo.getUser_id());
					
						//if(ValidateUtil.checkPsd(outUserInfo, dto.getString("random"), dto.getString("sign"), ConstantUtil.PAY_PSD_TYPE)) {//支付密码正确
							//根据token获取user_id查询用户本身账户信息
							ZjcUsersAccountInfoPO outAccountInfo = zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
							//记录未转账钱可转积分
							int oldKz = outAccountInfo.getMake_over_integral();
							//记录转账到用户原可转积分
							int inoldKz = inAccountInfo.getMake_over_integral();
							try{
								//操作账户信息，生成转账订单
								msgVO = apiPublicService.do_account(dto, outAccountInfo, inAccountInfo,mapPoundage);
								if(msgVO.getCode() == 1){//订单生成成功，生成转账日志,转账到用户
									zjcUsersAccountInfoDao.updateByKey(outAccountInfo);
									Map<String,Object> exChangemap = new HashMap<String,Object>();
									exChangemap.put("logType", "xpt转会员");
									exChangemap.put("type", "xpt转账");
									exChangemap.put("user_id", outUserInfo.getUser_id());
									exChangemap.put("to_user_id", userInfo.getUser_id());
									exChangemap.put("make_over_integral", dto.getInteger("points"));
									exChangemap.put("mobile", dto.getString("mobile"));
									exChangemap.put("user_name", dto.getString("real_name"));
									exChangemap.put("now_make_over_integral", oldKz-dto.getInteger("points")-Integer.parseInt(mapPoundage.get("fee").toString()));
									exChangemap.put("show_type", 1);
									apiLogService.saveLog(exChangemap);
									exChangemap.put("logType", "会员收到xpt转账");
									exChangemap.put("type", "会员转账");
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
						
					}
				}
			}
		 
		return AOSJson.toJson(msgVO);
	}
  
	
	
	/**
	 * app接口-通过token获取用户信息
	 * 
	 * @param request
	 * @return json
	 */
	public String getXptUserInfo(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("mobile"))){//参数为空
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVo);
		}
		dto.put("xpt","xpt");
		//查询用户信息
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfoXpt", dto);
		
		if(AOSUtils.isEmpty(zjcUsersInfoPOList)){//未查询到用户信息
			msgVo.setMsg(Apiconstant.Username_Not_Exist.getName());
			msgVo.setCode(Apiconstant.Username_Not_Exist.getIndex());
		} else {//查询成功
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(zjcUsersInfoPOList.get(0));
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * app接口-实名认证
	 * 
	 * @param httpModel
	 * @return
	 */
	public String authen(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		String real_name = dto.getString("real_name");
		String id_card = dto.getString("id_card");
		if(AOSUtils.isEmpty(real_name)||AOSUtils.isEmpty(id_card)){//姓名或者身份证号不能为空
			msgVo.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else if(!ValidateUtil.isIdCard(id_card)){//身份证号码格式错误或者不合法
			msgVo.setCode(Apiconstant.ID_CARD_WAS_WRONG.getIndex());
			msgVo.setMsg(Apiconstant.ID_CARD_WAS_WRONG.getName());
		} else {
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id")); 
			if(userInfo.getAuthentication()==1){//验证是否实名认证，1表示已经实名认证
				msgVo.setCode(Apiconstant.User_Was_Authentication.getIndex());
				msgVo.setMsg(Apiconstant.User_Was_Authentication.getName());
			} else {
				dto.put("user_id", null);
				dto.put("token", null);
				dto.put("authentication", 1);
				List<ZjcUsersInfoPO> userInfoExist = zjcUsersInfoDao.list(dto);
				if(userInfoExist.size()>0){//该身份证号码已经存在!
					msgVo.setCode(Apiconstant.ID_CARD_WAS_EXIST.getIndex());
					msgVo.setMsg(Apiconstant.ID_CARD_WAS_EXIST.getName());
				} else {
					List<ZjcSellerInfoPO> zjcSellerInfoExist = zjcSellerInfoDao.list(dto);
					if(zjcSellerInfoExist.size()>0){//该身份证号码已经申请企业号
						msgVo.setCode(Apiconstant.ID_CARD_HAS_USED.getIndex());
						msgVo.setMsg(Apiconstant.ID_CARD_HAS_USED.getName());
					} else {
						//TODO 调用外部接口，验证身份证和真实姓名的合法性
						//修改用户实名认证信息 调用外部接口，验证身份证和真实姓名的合法性
						userInfo.setAuthentication(1);
						userInfo.setReal_name(real_name);
						userInfo.setId_card(id_card);
						int successNum = zjcUsersInfoDao.updateByKey(userInfo);
						if(successNum == 0){//修改失败
							msgVo.setCode(Apiconstant.Save_fails.getIndex());
							msgVo.setMsg(Apiconstant.Save_fails.getName());
						} else {//修改成功
							msgVo.setData(userInfo);
							msgVo.setCode(Apiconstant.Do_Success.getIndex());
							msgVo.setMsg(Apiconstant.Do_Success.getName());
						}
					}
				}
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	public String getXptUserGoods(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("user_id"))){
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg("参数错误");
			msgVo.setData("");
		}
		
		XptgoodsPO ZjcGoodsPO = (XptgoodsPO) sqlDao.selectOne("com.api.goods.dao.XptgoodsDao.list",null);
		String[] goods_sn=ZjcGoodsPO.getGoods_sn().split(",");
		
		Map<String, List<ZjcGoodsPO>> map=new HashMap<>();
		for (int i = 0; i < goods_sn.length; i++) {
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			dto.put("is_on_sale", 1);
			dto.put("goods_sn", goods_sn[i]);
			List<ZjcGoodsPO> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.xptPage", dto);
			if(list.size()>0){
				map.put(goods_sn[i], list);
			}
		}
		
		if(map.size()>0){
			msgVo.setData(map);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg("是否买了商品或者是非xpt会员");
			msgVo.setData("");
		}
		return AOSJson.toJson(msgVo);
	}
	
	public String xptAddGoods(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("goods_sn"))){
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg("参数错误");
			msgVo.setData("");
		}
		XptgoodsPO XptgoodsPO=new XptgoodsPO();
		XptgoodsPO.setId(1);
		XptgoodsPO.setGoods_sn(dto.getString("goods_sn"));
		int row=XptgoodsDao.updateByKey(XptgoodsPO);
		if(row>0){
			msgVo.setData("");
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
			msgVo.setData("");
		}
		return AOSJson.toJson(msgVo);
	}


	/**
	 * 查询小平台商品订单
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getXptOrder(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("page"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		if(dto.getInteger("page")<1){
			msgVo.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Page_Is_Null.getName());
			return AOSJson.toJson(msgVo);
		}
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		List<Dto> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.likeXptOrderListPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		msgVo.setData(pageVO);
		if(list.size() == 0){
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 查询小平台日志
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getXptLog(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("page"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		if(dto.getInteger("page")<1){
			msgVo.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Page_Is_Null.getName());
			return AOSJson.toJson(msgVo);
		}
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		List<Dto> list = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.listLogPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(list);
		pageVO.setTotalSize(dto.getInteger("total"));
		msgVo.setData(pageVO);
		if(list.size() == 0){
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
}
