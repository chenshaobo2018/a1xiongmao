/**
 * 
 */
package com.api.ApiPublic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.login.dao.po.LoginUsersInfoVO;
import com.api.order.OrderService;
import com.api.order.dao.po.Rebate;
import com.sellerApp.login.po.LoginSellerInfoVO;
import com.zjc.order.dao.ZjcExchangeOrderDao;
import com.zjc.order.dao.po.ZjcExchangeOrderPO;
import com.zjc.region.dao.ZjcRegionDao;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.sms.dao.ZjcSmsLogDao;
import com.zjc.sms.dao.po.ZjcSmsLogPO;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.ZjcMemberParameterDao;
import com.zjc.system.dao.po.ZjcMemberMultiplicationPO;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.system.dao.po.ZjcMemberSettlementPO;
import com.zjc.users.dao.ZjcUserLevelDao;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserLevelPO;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *api接口调用公共类
 */
@Service(value="apiPublicService")
public class ApiPublicService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcSmsLogDao zjcSmsLogDao;
	
	@Autowired
	private ZjcRegionDao zjcRegionDao;
	
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcExchangeOrderDao zjcExchangeOrderDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcMemberParameterDao memberParameterDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ZjcUserLogDao zjcUserLogDao;
	
	@Autowired
	private ZjcUserLevelDao zjcUserLevelDao;
	
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	/**
	 * 通过地区ID获取地区名字
	 * 
	 * @param id
	 * @return
	 */
	public String getAddressName(int id){
		//获取地区名字
		String str = "";
		ZjcRegionPO zjcRegionPO = zjcRegionDao.selectByKey(id);//查询地区信息
		if(!AOSUtils.isEmpty(zjcRegionPO)){
			str = zjcRegionPO.getName();
		}
		return str;
	}
	
	/**
	 * app -短信验证码验证
	 * 
	 * @param httpModel
	 * @return json
	 */
	public MessageVO sms_code_verify(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		String code = dto.getString("code");//获取验证码
		String mobile=dto.getString("mobile");//获取验证码
		dto.put("code", code);
		dto.put("mobile", mobile);
		dto.put("status",0);
		//dto.put("type", ConstantUtil.PAY_PSD_CODE_TYPE);
		//查询设置支付密码短信验证码集合(按add_time降系排列)
		List<ZjcSmsLogPO> smsPOList = sqlDao.list("com.zjc.sms.dao.ZjcSmsLogDao.listPayPsd", dto);
		if(AOSUtils.isEmpty(dto.getString("type"))){//短信验证码类型为空
			msgVo.setCode(Apiconstant.Code_Type_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Code_Type_Is_Null.getName());
		} else if(AOSUtils.isEmpty(dto.getString("mobile"))){//手机号码为空
			msgVo.setCode(Apiconstant.Phone_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Phone_Is_Null.getName());
		} /*else if(!ValidateUtil.isRightPhone(dto.getString("mobile"))){//手机号码格式错误
			msgVo.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
			msgVo.setMsg(Apiconstant.Phone_Is_Wrong.getName());
		}*/else {
			if(AOSUtils.isEmpty(smsPOList)){//未查询到短信验证码集合信息
				msgVo.setCode(Apiconstant.Code_Not_Exist.getIndex());
				msgVo.setMsg(Apiconstant.Code_Not_Exist.getName());
			} else {//查询成功
				//获取当前最新的验证码
				ZjcSmsLogPO smsPO = smsPOList.get(0);
				if(AOSUtils.isEmpty(code)){//请输入验证码
					msgVo.setMsg(Apiconstant.Code_Is_Null.getName());
					msgVo.setCode(Apiconstant.Code_Is_Null.getIndex());
				}else if(!code.equals(smsPO.getCode())){//验证码不相等
					msgVo.setMsg(Apiconstant.Code_Was_Wrong.getName());
					msgVo.setCode(Apiconstant.Code_Was_Wrong.getIndex());
				} else if(smsPO.getStatus()==1){//验证码已经使用
					msgVo.setMsg(Apiconstant.Code_Was_Used.getName());
					msgVo.setCode(Apiconstant.Code_Was_Used.getIndex());
				} else if(!ParameterUtil.IsExpire(smsPO.getAdd_time(), ConstantUtil.CODE_VALIDATE_TIME)){//验证码已过期
					msgVo.setMsg(Apiconstant.Code_Is_Unused.getName());
					msgVo.setCode(Apiconstant.Code_Is_Unused.getIndex());
				} else {
					smsPO.setStatus(1);//验证码设置为已使用
					//修改验证码使用状态
					int successNum = zjcSmsLogDao.updateByKey(smsPO);
					if(successNum == 0){//修改失败
						msgVo.setCode(Apiconstant.Save_fails.getIndex());
						msgVo.setMsg(Apiconstant.Save_fails.getName());
					} else{//修改成功
						msgVo.setCode(Apiconstant.Do_Success.getIndex());
						msgVo.setMsg(Apiconstant.Do_Success.getName());
					}
				}
			}
		}
		return msgVo;
	}
	
	
	/**
	 * 获取分页查询参数
	 * 
	 * @param dto
	 * @return
	 */
	public Dto getPageParameter(Dto dto){
		//设置limit
		dto.put("limit", ConstantUtil.pageSize);
		//设置start
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		
		return dto;
	}
	
	/**
	 * 根据积分计算手续费
	 * 
	 * @param points 积分
	 * @return map
	 */
	public Map<String,Object> getPoundage(int points){
		Map<String,Object> map = new HashMap<String,Object>();
		int fee = 0;
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		if(AOSUtils.isEmpty(zjcMemberOtherPO)){//请先配置系统参数
			return null;
		} else {
			/*if(points > Integer.valueOf(zjcMemberOtherPO.getMaximum_transfer())){//积分超过最大转账额度,易物
				fee = (int)Math.ceil(points*Double.valueOf(zjcMemberOtherPO.getBarter_guarantee_fee()));
				map.put("fee", fee);
				map.put("type", 2);//积分类型：易物
			} else {//转账
*/				fee = (int)Math.ceil(points*Double.valueOf(zjcMemberOtherPO.getBarter_voucher_transfer_fees()));
				map.put("fee", fee);
				map.put("type", 1);//积分类型：转账
			//}
			return map;
		}
	}
	
	/**
	 * 查询转账范围
	 * 
	 * @return
	 */
	public Map<String,Object> getExchangRang(){
		Map<String,Object> map = new HashMap<String,Object>();
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		if(AOSUtils.isEmpty(zjcMemberOtherPO)){//请先配置系统参数
			return null;
		} else {
			map.put("minValue",zjcMemberOtherPO.getTransfer_minimum());
			map.put("maxValue",zjcMemberOtherPO.getMaximum_transfer());
			return map;
		}
	}
	
	/**
	 * 账户转账
	 * 
	 * @param dto
	 * @param outAccountInfo 转出账户信息
	 * @param inAccountInfo 转入账户信息
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public MessageVO do_account(Dto dto,ZjcUsersAccountInfoPO outAccountInfo,ZjcUsersAccountInfoPO inAccountInfo,Map<String,Object> map) {
		MessageVO msgVO = new MessageVO();
		if(dto.getInteger("points")>outAccountInfo.getMake_over_integral()){//转账积分大于自身可转额度
			msgVO.setCode(Apiconstant.POINTS_LOW_OwnSelf_Money.getIndex());
			msgVO.setMsg(Apiconstant.POINTS_LOW_OwnSelf_Money.getName());
		} else {
			//获取转出账户原本的可转积分
			int oldMoney = outAccountInfo.getMake_over_integral();//获取转出的账户原来的可转余额
			outAccountInfo.setMake_over_integral(oldMoney-dto.getInteger("points")-Integer.parseInt(map.get("fee").toString()));//改变可转余额
			//减少自己的可转积分额度
			zjcUsersAccountInfoDao.updateByKey(outAccountInfo);
			//获取转入账户原本的可转积分
			int newMoney = inAccountInfo.getMake_over_integral();//获取转出到的账户原来的可转余额
			inAccountInfo.setMake_over_integral(newMoney+dto.getInteger("points"));//改变可转余额
			//增加自己的可转积分额度
			zjcUsersAccountInfoDao.updateByKey(inAccountInfo);
			ZjcExchangeOrderPO exchangeOrder = new ZjcExchangeOrderPO();
			//exchangeOrder.setEx_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			exchangeOrder.setEx_sn(ParameterUtil.getOrderSn());//订单编号
			exchangeOrder.setUser_id(outAccountInfo.getUser_id());
			exchangeOrder.setUser_name(dto.getString("user_name"));
			exchangeOrder.setSeller_id(Integer.valueOf(dto.getString("seller_id")));
			exchangeOrder.setSeller_name(dto.getString("real_name"));
			exchangeOrder.setEx_price(dto.getBigDecimal("points"));
			exchangeOrder.setMobile(dto.getString("mobile"));
			exchangeOrder.setEx_fee(new BigDecimal(map.get("fee").toString()));
			exchangeOrder.setStatus(1);
			exchangeOrder.setAdd_time(new Date());
			exchangeOrder.setType(1);
			exchangeOrder.setNote(ConstantUtil.EXCHANGE_ORDER_DESC);
			zjcExchangeOrderDao.insert(exchangeOrder);
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			/*} catch(Exception e){
				msgVO.setCode(Apiconstant.Do_Fails.getIndex());
				msgVO.setMsg(Apiconstant.Do_Fails.getName());
				e.printStackTrace();
				//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			}*/
		}
		return msgVO;
	}
	
	/*
	 * 上级提成分配
	 * 非商品类会员提成比例处理
	 * @param user_id 当前会员ID points 分配易物券  type
	 * @return
	 */
	public int plain_commission(BigInteger user_id, Integer points, Integer type){
		ZjcMemberParameterPO memberParameterPO = memberParameterDao.selectByMaxKey();
		//获取系统当前的返佣模型
		String model = memberParameterPO.getMember_commission_model();
		//获取当前会员信息
		ZjcUsersInfoPO usersInfoPO = zjcUsersInfoDao.selectByKey(user_id);
		//获取上级会员信息
		ZjcUsersInfoPO first_leader = zjcUsersInfoDao.getFirstLeader(usersInfoPO);
		ZjcUsersInfoPO  second_leader= zjcUsersInfoDao.getSecondLeader(usersInfoPO);
		if(AOSUtils.isEmpty(first_leader)){
			first_leader = new ZjcUsersInfoPO(BigInteger.valueOf(0), 0);
		}if(AOSUtils.isEmpty(second_leader)){
			second_leader = new ZjcUsersInfoPO(BigInteger.valueOf(0), 0);
		}
		ZjcUsersAccountInfoPO first_leader_account = zjcUsersAccountInfoDao.getFirstLeaderAccount(first_leader);
		ZjcUsersAccountInfoPO second_leader_account = zjcUsersAccountInfoDao.getSecondLeaderAccount(second_leader);
		if(AOSUtils.isEmpty(first_leader_account)){
			first_leader_account = new ZjcUsersAccountInfoPO(0, "0", "0", "0");
		}if(AOSUtils.isEmpty(second_leader_account)){
			second_leader_account = new ZjcUsersAccountInfoPO(0, "0", "0", "0");
		}
		//上级的合格下级会员数
		int first_qualified_member = first_leader.getQualified_member();
		int second_qualified_member = second_leader.getQualified_member();
		//钱包额度
		int first_wallet_quota = Integer.parseInt(first_leader_account.getWallet_quota());
		int second_wallet_quota = Integer.parseInt(second_leader_account.getWallet_quota());
		//提成比例
		float first_rate = (first_qualified_member > Float.parseFloat(memberParameterPO.getMember_comm_percentage_three()))?
				Float.parseFloat(memberParameterPO.getMember_comm_percentage_threes()):
					(first_qualified_member > Float.parseFloat(memberParameterPO.getMember_comm_percentage_two()))?
							Float.parseFloat(memberParameterPO.getMember_comm_percentage_twos()):
								(first_qualified_member > Float.parseFloat(memberParameterPO.getMember_comm_percentage_one()))?
										Float.parseFloat(memberParameterPO.getMember_comm_percentage_ones()): 0;
		float second_rate = (second_qualified_member > Float.parseFloat(memberParameterPO.getMember_comm_percentage_three()))?
				Float.parseFloat(memberParameterPO.getMember_comm_percentage_threes()):
					(second_qualified_member > Float.parseFloat(memberParameterPO.getMember_comm_percentage_two()))?
							Float.parseFloat(memberParameterPO.getMember_comm_percentage_twos()):
								(second_qualified_member > Float.parseFloat(memberParameterPO.getMember_comm_percentage_one()))?
										Float.parseFloat(memberParameterPO.getMember_comm_percentage_ones()): 0;
		//计算提成易物劵
		Rebate first = distribute_integral(Math.min((int)(points * first_rate), first_wallet_quota));
		Rebate second = distribute_integral(Math.min((int)(points * second_rate), second_wallet_quota));
		
		switch (model) {
		//直接返佣
		case "1":
				if(type == 1){
					return (int)(points * first_rate);
				}
				if(type == 2){
					//给一级上级加钱
					first_leader_account.setMake_over_integral(first_leader_account.getMake_over_integral() + 
							Math.min((int)(points * first_rate), first_wallet_quota));
					zjcUsersAccountInfoDao.updateByKey(first_leader_account);
					if((int)(points * first_rate) > 0){
						first_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getDue_tc_points()) + (int)(points * first_rate)));	
						zjcUsersAccountInfoDao.updateByKey(first_leader_account);
					}
					if(first.getKz() + first.getXf()> 0){
						first_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getPractical_tc_points()) + first.getKz() + first.getXf()));	
						first_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(first_leader_account.getWallet_quota()) - first.getKz() + first.getXf()));
						zjcUsersAccountInfoDao.updateByKey(first_leader_account);
						String descs = "亲，恭喜您获得商城赠送您的" + first.getKz() + first.getXf() + 
								"易物券已到账，请查收！当前可转易物券：" + first_leader_account.getMake_over_integral();
						ZjcUserLogPO log = new ZjcUserLogPO(first_leader.getUser_id(), "分享服务", descs);
						zjcUserLogDao.insert1(log);
					}
					return 0;
				}
				if(first.getXf() > 0){
					first_leader_account.setPay_points(first_leader_account.getPay_points() + first.getXf());
					zjcUsersAccountInfoDao.updateByKey(first_leader_account);
				}
				if(first.getKz() > 0){
					first_leader_account.setMake_over_integral(first_leader_account.getMake_over_integral() + first.getKz());
					zjcUsersAccountInfoDao.updateByKey(first_leader_account);
				}
				if(first.getKz() + first.getXf() > 0){
					first_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getDue_tc_points()) + (int)(points * first_rate)));	
					first_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getPractical_tc_points()) + first.getKz() + first.getXf()));	
					first_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(first_leader_account.getWallet_quota()) - first.getKz() + first.getXf()));
					zjcUsersAccountInfoDao.updateByKey(first_leader_account);
					String descs = "亲，恭喜您获得商城赠送您的" + first.getKz() + first.getXf() + 
							"易物券已到账，请查收！当前可转易物券：" + first_leader_account.getMake_over_integral();
					ZjcUserLogPO log = new ZjcUserLogPO(first_leader.getUser_id(), "分享服务", descs);
					zjcUserLogDao.insert1(log);
				}
				if((int)(points * first_rate) > 0){
					first_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getDue_tc_points()) + (int)(points * first_rate)));	
					zjcUsersAccountInfoDao.updateByKey(first_leader_account);
				}
			return 0;
			//双重返佣
		case "3":
			if(type == 1){
				return (int)(points * first_rate) + (int)(points * second_rate);
			}
			if(type == 2){
				first_leader_account.setMake_over_integral(first_leader_account.getMake_over_integral() + 
						Math.min((int)(points * first_rate), first_wallet_quota));
				second_leader_account.setMake_over_integral(second_leader_account.getMake_over_integral() + 
						Math.min((int)(points * second_rate), second_wallet_quota));
				zjcUsersAccountInfoDao.updateByKey(first_leader_account);
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
				if((int)(points * first_rate) > 0){
					first_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getDue_tc_points()) + (int)(points * first_rate)));	
				}
				if((int)(points * second_rate) > 0){
					second_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getDue_tc_points()) + (int)(points * second_rate)));	
				}
				if(first.getKz() + first.getXf() > 0){
					first_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getPractical_tc_points()) + first.getKz() + first.getXf()));	
					first_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(first_leader_account.getWallet_quota()) - first.getKz() + first.getXf()));
					zjcUsersAccountInfoDao.updateByKey(first_leader_account);
					String descs = "亲，恭喜您获得商城赠送您的" + first.getKz() + first.getXf() + 
							"易物券已到账，请查收！当前可转易物券：" + first_leader_account.getMake_over_integral();
					ZjcUserLogPO log = new ZjcUserLogPO(first_leader.getUser_id(), "分享服务", descs);
					zjcUserLogDao.insert1(log);
				}
				if(second.getKz() + second.getXf() > 0){
					second_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getPractical_tc_points()) + second.getKz() + second.getXf()));	
					second_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(second_leader_account.getWallet_quota()) - second.getKz() + second.getXf()));
					zjcUsersAccountInfoDao.updateByKey(second_leader_account);
					String descs = "亲，恭喜您获得商城赠送您的" + second.getKz() + second.getXf() + 
							"易物券已到账，请查收！当前可转易物券：" + second_leader_account.getMake_over_integral();
					ZjcUserLogPO log = new ZjcUserLogPO(second_leader.getUser_id(), "分享服务", descs);
					zjcUserLogDao.insert1(log);
				}
				return 0;
			}
			if(first.getXf() > 0){
				first_leader_account.setPay_points(first_leader_account.getPay_points() + first.getXf());
				zjcUsersAccountInfoDao.updateByKey(first_leader_account);
			}
			if(first.getKz() > 0){
				first_leader_account.setMake_over_integral(first_leader_account.getMake_over_integral() + first.getKz());
				zjcUsersAccountInfoDao.updateByKey(first_leader_account);
			}
			if(second.getXf() > 0){
				second_leader_account.setPay_points(second_leader_account.getPay_points() + second.getXf());
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
			}
			if(second.getKz() > 0){
				second_leader_account.setMake_over_integral(second_leader_account.getMake_over_integral() + second.getKz());
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
			}
			if(first.getKz() + first.getXf() > 0){
				first_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getPractical_tc_points()) + first.getKz() + first.getXf()));	
				first_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(first_leader_account.getWallet_quota()) - first.getKz() + first.getXf()));
				zjcUsersAccountInfoDao.updateByKey(first_leader_account);
				String descs = "亲，恭喜您获得商城赠送您的" + first.getKz() + first.getXf() + 
						"易物券已到账，请查收！当前可转易物券：" + first_leader_account.getMake_over_integral();
				ZjcUserLogPO log = new ZjcUserLogPO(first_leader.getUser_id(), "分享服务", descs);
				zjcUserLogDao.insert1(log);
			}
			if(second.getKz() + second.getXf() > 0){
				second_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getPractical_tc_points()) + second.getKz() + second.getXf()));	
				second_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(second_leader_account.getWallet_quota()) - second.getKz() + second.getXf()));
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
				String descs = "亲，恭喜您获得商城赠送您的" + second.getKz() + second.getXf() + 
						"易物券已到账，请查收！当前可转易物券：" + second_leader_account.getMake_over_integral();
				ZjcUserLogPO log = new ZjcUserLogPO(second_leader.getUser_id(), "分享服务", descs);
				zjcUserLogDao.insert1(log);
			}
			if((int)(points * first_rate) > 0){
				first_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(first_leader_account.getDue_tc_points()) + (int)(points * first_rate)));	
			}
			if((int)(points * second_rate) > 0){
				second_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getDue_tc_points()) + (int)(points * second_rate)));	
			}
			return 0;
			//隔代返佣
		default:
			System.out.println("kz="+second.getKz()+"xf="+second.getXf()+"first_rate="+first_rate+"second_rate="+second_rate);
			if(type == 1){
				return (int)(points * second_rate);
			}
			if(type == 2){
				//给一级上级加钱
				second_leader_account.setMake_over_integral(second_leader_account.getMake_over_integral() + 
						Math.min((int)(points * second_rate), second_wallet_quota));
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
				if((int)(points * second_rate) > 0){
					second_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getDue_tc_points()) + (int)(points * second_rate)));	
					zjcUsersAccountInfoDao.updateByKey(second_leader_account);
				}
				if(second.getKz() + second.getXf() > 0){
					second_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getPractical_tc_points()) + second.getKz() + second.getXf()));	
					second_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(second_leader_account.getWallet_quota()) - second.getKz() + second.getXf()));
					zjcUsersAccountInfoDao.updateByKey(second_leader_account);
					String descs = "亲，恭喜您获得商城赠送您的" + second.getKz() + second.getXf() + 
							"易物券已到账，请查收！当前可转易物券：" + second_leader_account.getMake_over_integral();
					ZjcUserLogPO log = new ZjcUserLogPO(second_leader.getUser_id(), "分享服务", descs);
					zjcUserLogDao.insert1(log);
				}
				return 0;
			}
			if(second.getXf() > 0){
				second_leader_account.setPay_points(second_leader_account.getPay_points() + second.getXf());
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
			}
			if(second.getKz() > 0){
				second_leader_account.setMake_over_integral(second_leader_account.getMake_over_integral() + second.getKz());
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
			}
			if(second.getKz() + second.getXf() > 0){
				second_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getDue_tc_points()) + (int)(points * second_rate)));	
				second_leader_account.setPractical_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getPractical_tc_points()) + second.getKz() + second.getXf()));	
				second_leader_account.setWallet_quota(String.valueOf(Integer.parseInt(second_leader_account.getWallet_quota()) - second.getKz() + second.getXf()));
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
				String descs = "亲，恭喜您获得商城赠送您的" + second.getKz() + second.getXf() + 
						"易物券已到账，请查收！当前可转易物券：" + second_leader_account.getMake_over_integral();
				ZjcUserLogPO log = new ZjcUserLogPO(second_leader.getUser_id(), "分享服务", descs);
				zjcUserLogDao.insert1(log);
			}
			if((int)(points * second_rate) > 0){
				second_leader_account.setDue_tc_points(String.valueOf(Integer.parseInt(second_leader_account.getDue_tc_points()) + (int)(points * second_rate)));	
				zjcUsersAccountInfoDao.updateByKey(second_leader_account);
			}
			return 0;
		}
	}
	
	/**
	 * 根据结算金额计算结算支付结算明细
	 * 
	 * @param user_id
	 * @param setIn
	 * @return map
	 */
	public Map<String,Object> calculateSettle(int setIn,BigInteger user_id,BigInteger settle_seller_id){
		Map<String,Object> map = new HashMap<String,Object>();
		//获取倍增倍数配置信息
		List<ZjcMemberMultiplicationPO> bzParameterList = sqlDao.list("com.zjc.system.dao.ZjcMemberMultiplicationDao.selectByMaxKey",null);
		ZjcMemberMultiplicationPO bzParameter = bzParameterList.get(0);
		//BigDecimal defaultBeizheng =  new BigDecimal(bzParameter.getDefault_multiplier());
		//ZjcMemberWalletPO walletPO = (ZjcMemberWalletPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberWalletDao.selectByMaxKey", null);
		//BigDecimal w =  new BigDecimal(walletPO.getWallet_amplification_ratio());
		//倍增倍数
		BigDecimal rate = new BigDecimal(bzParameter.getDefault_multiplier());
		if(settle_seller_id.toString().equals(bzParameter.getSpecial_merchants_multiplication_id())){//如果是特殊商家的话
			rate = new BigDecimal(bzParameter.getSpecial_merchants_multiplication());
		} else {
			rate = user_level_rate(settle_seller_id);
		}
		//计算客户支付值
		int user_pay=setIn;
		map.put("user_pay", user_pay);
		
		//用户钱包增加值
		//int user_bag = (new BigDecimal(setIn).multiply(new BigDecimal(1).divide(rate))).multiply(w).intValue();
		map.put("user_bag", 0);
		
		//实体店收入
		int center_income=setIn - (new BigDecimal(setIn).multiply(new BigDecimal(1).divide(rate))).intValue();
		map.put("center_income", center_income);
		
		//结算补贴
		//int leader_income=(int) Math.floor((setIn/1.25)*0.08);
		//int leader_income=plain_commission(user_id, new BigDecimal(user_pay).multiply(new BigDecimal(1).divide(rate)).intValue(), 1);
		map.put("leader_income", 0);
        return map;
	}
	
	/**
	 * 分配可转易物券与可用易物券
	 * 
	 * @param points
	 * @return
	 */
	/*public Map<String,Object> distribute_integral(int points){
		Map<String,Object> map = new HashMap<String,Object>();
		//查询可转可用比例
		List<ZjcMemberOtherPO> otherList = sqlDao.list("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey",null);
		BigDecimal kzRate = new BigDecimal(Double.valueOf(otherList.get(0).getConvertible_barter()));
		BigDecimal kyRate = new BigDecimal(Double.valueOf(otherList.get(0).getConsumption_coupons()));
		BigDecimal sum = kzRate.add(kyRate);
		BigDecimal rate = kzRate.divide(sum);
		//可转积分的数额
		int kz = new BigDecimal(points).multiply(rate).intValue();
		map.put("kz", kz);
		//可用积分的数额
		int ky = points - kz;
		map.put("ky", ky);
		return map;
	}*/
	
	/**
	 * 分配可转易物券与可用易物券
	 * 
	 * @param points
	 * @return Rebate
	 */
	public Rebate distribute_integral(int points){
		Rebate rebate = new Rebate();
		//查询可转可用比例
		List<ZjcMemberOtherPO> otherList = sqlDao.list("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey",null);
		BigDecimal kzRate = new BigDecimal(Double.valueOf(otherList.get(0).getConvertible_barter()));
		BigDecimal kyRate = new BigDecimal(Double.valueOf(otherList.get(0).getConsumption_coupons()));
		BigDecimal sum = kzRate.add(kyRate);
		BigDecimal rate = kzRate.divide(sum);
		//可转积分的数额
		int kz = new BigDecimal(points).multiply(rate).intValue();
		rebate.setKz(kz);
		//可用积分的数额
		int ky = points - kz;
		rebate.setXf(ky);
		return rebate;
	}
	
	/**
	 * 通过user_id查询登陆的用户信息
	 * 
	 * @param dto
	 * @return
	 */
	public LoginUsersInfoVO getLoginUserInfo(Dto dto){
		//调用存储过程
		sqlDao.call("level",dto);
		List<ZjcUsersInfoPO> zjcUsersInfoPOList = sqlDao.list("com.zjc.users.dao.ZjcUsersInfoDao.queryUserInfo", dto);
		ZjcUsersInfoPO userinfo = zjcUsersInfoPOList.get(0);
		LoginUsersInfoVO zjcUsersInfoVO= new LoginUsersInfoVO();
		zjcUsersInfoVO.setUser_id(userinfo.getUser_id());
		zjcUsersInfoVO.setLevel(userinfo.getLevel());
		zjcUsersInfoVO.setToken(userinfo.getToken());
		zjcUsersInfoVO.setNickname(userinfo.getNickname());
		zjcUsersInfoVO.setHead_pic(userinfo.getHead_pic());
		zjcUsersInfoVO.setMobile(userinfo.getMobile());
		zjcUsersInfoVO.setId_card(userinfo.getId_card());
		zjcUsersInfoVO.setReal_name(userinfo.getReal_name());
		zjcUsersInfoVO.setFirst_leader(userinfo.getFirst_leader());
		zjcUsersInfoVO.setAuthentication(userinfo.getAuthentication());
		zjcUsersInfoVO.setPay_points(userinfo.getZjcUsersAccountInfoPO().getPay_points());
		zjcUsersInfoVO.setMake_over_integral(userinfo.getZjcUsersAccountInfoPO().getMake_over_integral());
		zjcUsersInfoVO.setSqamount(userinfo.getZjcUsersAccountInfoPO().getSqamount());
		zjcUsersInfoVO.setSqpay(userinfo.getZjcUsersAccountInfoPO().getSqpay());
		zjcUsersInfoVO.setSqvip(userinfo.getZjcUsersAccountInfoPO().getSqvip());
		zjcUsersInfoVO.setUnionid(userinfo.getOpenid());
		return zjcUsersInfoVO;
	}
	
	/**
	 * 会员app登陆返回实体
	 * 
	 * @param userinfo
	 * @return
	 */
	public LoginUsersInfoVO getLoginUserInfoAPP(ZjcUsersInfoPO userinfo){
		//调用存储过程
		sqlDao.call("level",Dtos.newDto("user_id", userinfo.getUser_id()));
		LoginUsersInfoVO zjcUsersInfoVO= new LoginUsersInfoVO();
		zjcUsersInfoVO.setUser_id(userinfo.getUser_id());
		zjcUsersInfoVO.setLevel(userinfo.getLevel());
		zjcUsersInfoVO.setToken(userinfo.getToken());
		zjcUsersInfoVO.setNickname(userinfo.getNickname());
		zjcUsersInfoVO.setHead_pic(userinfo.getHead_pic());
		zjcUsersInfoVO.setMobile(userinfo.getMobile());
		zjcUsersInfoVO.setId_card(userinfo.getId_card());
		zjcUsersInfoVO.setReal_name(userinfo.getReal_name());
		zjcUsersInfoVO.setFirst_leader(userinfo.getFirst_leader());
		zjcUsersInfoVO.setAuthentication(userinfo.getAuthentication());
		zjcUsersInfoVO.setPay_points(userinfo.getZjcUsersAccountInfoPO().getPay_points());
		zjcUsersInfoVO.setMake_over_integral(userinfo.getZjcUsersAccountInfoPO().getMake_over_integral());
		zjcUsersInfoVO.setSqamount(userinfo.getZjcUsersAccountInfoPO().getSqamount());
		zjcUsersInfoVO.setSqpay(userinfo.getZjcUsersAccountInfoPO().getSqpay());
		zjcUsersInfoVO.setSqvip(userinfo.getZjcUsersAccountInfoPO().getSqvip());
		zjcUsersInfoVO.setUnionid(userinfo.getOpenid());
		zjcUsersInfoVO.setBank_card(userinfo.getBank_card());
		zjcUsersInfoVO.setWhere_it_is(userinfo.getWhere_it_is());
		zjcUsersInfoVO.setProvincial_generation(userinfo.getProvincial_generation());
		zjcUsersInfoVO.setDrops(userinfo.getZjcUsersAccountInfoPO().getDrops());
		return zjcUsersInfoVO;
	}
	
	/**
	 * 商家app登陆返回实体
	 * 
	 * @param userinfo
	 * @return
	 */
	public LoginSellerInfoVO getLoginUserInfoAPP(ZjcSellerInfoPO userinfo){
		//调用存储过程
		//sqlDao.call("level",Dtos.newDto("user_id", userinfo.getUser_id()));
		LoginSellerInfoVO zjcUsersInfoVO= new LoginSellerInfoVO();
		zjcUsersInfoVO.setUser_id(userinfo.getUser_id());
		zjcUsersInfoVO.setToken(userinfo.getToken());
		zjcUsersInfoVO.setNickname(userinfo.getNickname());
		zjcUsersInfoVO.setMobile(userinfo.getMobile());
		zjcUsersInfoVO.setId_card(userinfo.getId_card());
		zjcUsersInfoVO.setReal_name(userinfo.getReal_name());
		zjcUsersInfoVO.setAuthentication(userinfo.getAuthentication());
		zjcUsersInfoVO.setMake_over_integral(userinfo.getZjcUsersAccountInfoPO().getMake_over_integral());
		zjcUsersInfoVO.setLast_login(userinfo.getLast_login());
		ZjcStorePO store =  zjcStoreDao.selectByKey(userinfo.getStore_id());
		if(AOSUtils.isNotEmpty(store)){
			zjcUsersInfoVO.setNickname(store.getStore_name());
			zjcUsersInfoVO.setStore_name(store.getStore_name());
			zjcUsersInfoVO.setStore_logo(store.getStore_logo());
			zjcUsersInfoVO.setStore_id(store.getStore_id());
		}
		Dto dto = sqlDao.selectDto("com.zjc.store.dao.ZjcStoreDao.billListApp", Dtos.newDto("user_id", userinfo.getUser_id()));
		if(AOSUtils.isNotEmpty(dto)){
			zjcUsersInfoVO.setPending_transfer(dto.getString("pending_transfer"));
		}else{
			zjcUsersInfoVO.setPending_transfer("0");
		}
		return zjcUsersInfoVO;
	}
	
	/**
	 * 判断是否达到合格会员标准
	 * 
	 * @param user_id
	 * @param points
	 */
	public void changeQualifiedMember(BigInteger user_id,int points){
		ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectByKey(user_id);
		if(zjcUsersInfoPO.getIs_qualified_member() == 0){
			ZjcMemberParameterPO parameterPO =  memberParameterDao.selectByMaxKey();
			if(points >= Integer.parseInt(parameterPO.getQualified_menber())){//消费额度大于设置的合格会员额度时
				zjcUsersInfoPO.setIs_qualified_member(1);//设置为合格会员
				zjcUsersInfoPO.setLevel("2");
				zjcUsersInfoDao.updateByKey(zjcUsersInfoPO);
			}
		}
	}
	
	/**
	 * 判定当前会员适合哪种倍增比例
	 * 
	 * @param user_id
	 * @return
	 */
	public BigDecimal user_level_rate(BigInteger user_id){
		ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(user_id);
		BigDecimal rate = new BigDecimal(0);
		List<ZjcUserLevelPO> levelList = zjcUserLevelDao.list(null);
		for(ZjcUserLevelPO level :levelList){
			if(userInfo.getLevel().equals(level.getLevel_name())||userInfo.getLevel().equals(level.getLevel_id().toString())){//判断开通结算中心个数
				rate = new BigDecimal(level.getDiscount());
			}
		}
		return rate;
	}
	
	/**
	 * 会员所有功能是否暂停使用
	 * 
	 * @return
	 */
	public MessageVO isLockedHandle(){
		MessageVO msgVo = new MessageVO();
		ZjcMemberParameterPO paramater = (ZjcMemberParameterPO)sqlDao.selectOne("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey", null);
		if("1".equals(paramater.getIs_lock())){//会员所有功能暂停使用
			msgVo.setCode(Apiconstant.User_Handle_Is_Lock.getIndex());
			msgVo.setMsg(Apiconstant.User_Handle_Is_Lock.getName());
		} else {
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return msgVo;
	}
	
	/**
	 * 会员账户是否被冻结
	 * 
	 * @param dto
	 * @return
	 */
	public MessageVO isLockedAccount(Dto dto){
		MessageVO msgVo = new MessageVO();
		ZjcUsersAccountInfoPO accountPO = zjcUsersAccountInfoDao.selectOne(dto);
		if("1".equals(accountPO.getIs_lock())){//会员账户被冻结
			msgVo.setCode(Apiconstant.User_Account_Is_Lock.getIndex());
			msgVo.setMsg(Apiconstant.User_Account_Is_Lock.getName());
		} else {
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return msgVo;
	}
	
	/**
	 * 会员当日转入转出额度控制
	 * 
	 * @param out_user_id
	 * @param points
	 * @return
	 */
	public MessageVO isTransferAccount(Dto dto,int points){
		MessageVO msgVo = new MessageVO();
		ZjcUsersAccountInfoPO account = zjcUsersAccountInfoDao.selectByKey(dto.getBigInteger("user_id"));
		if(AOSUtils.isEmpty(account)){//账户信息不存在
			msgVo.setCode(Apiconstant.Account_Not_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Account_Not_Exist.getName());
		} else {
			if(account.getJf_sum()==1){//没有开通取消限额
				Map<String,Object> map = this.getExchangRang();
				if(AOSUtils.isEmpty(map)){//转账范围获取为空，请设置转账范围
					msgVo.setCode(Apiconstant.Parameter_Is_Null.getIndex());
					msgVo.setMsg(Apiconstant.Parameter_Is_Null.getName());
					return msgVo;
				}
				if(points>Integer.valueOf(map.get("maxValue").toString())){//超过最大转账额度
					msgVo.setCode(Apiconstant.POINTS_MORE_THAN_MAXVALUE.getIndex());
					msgVo.setMsg(Apiconstant.POINTS_MORE_THAN_MAXVALUE.getName());
					return msgVo;
				}  
				if(points<Integer.valueOf(map.get("minValue").toString())){//小于最小转账额度
					msgVo.setCode(Apiconstant.POINTS_LOW_MINVALUE.getIndex());
					msgVo.setMsg(Apiconstant.POINTS_LOW_MINVALUE.getName());
					return msgVo;
				}
				
				BigDecimal outNumStr = new BigDecimal(sqlDao.selectOne("com.zjc.order.dao.ZjcExchangeOrderDao.getTodayTransferNum", dto).toString());
				int outNum = outNumStr.intValue();
				//手续费
				int fee = dto.getInteger("fee");
				ZjcMemberParameterPO paramater = (ZjcMemberParameterPO)sqlDao.selectOne("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
				if(account.getMake_over_integral() < points+fee){//账户可转余额不足
					msgVo.setCode(Apiconstant.KZ_Money_Not_Enough.getIndex());
					msgVo.setMsg(Apiconstant.KZ_Money_Not_Enough.getName());
				}else if(points + outNum + fee> paramater.getSum_points_today()){//当日转账总额超出当日限定转出总额
					msgVo.setCode(Apiconstant.Sum_points_today.getIndex());
					msgVo.setMsg(Apiconstant.Sum_points_today.getName());
				} else {
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());
				}
			} else {//开通了不限额，不进行转账限制
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return msgVo;
	}
	
	/**
	 * 判断线下购物是否超出额度
	 * 
	 * @param user_id
	 * @param points
	 * @return
	 */
	public MessageVO isLineAccount(BigInteger user_id,int points){
		MessageVO msgVo = new MessageVO();
		BigDecimal outNumStr = new BigDecimal(sqlDao.selectOne("com.api.order.dao.ZjcXxOrderDao.getTodayLineTransferNum", Dtos.newDto("user_id", user_id)).toString());
		int outNum = outNumStr.intValue();
		ZjcMemberParameterPO paramater = (ZjcMemberParameterPO)sqlDao.selectOne("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
		if(points + outNum > paramater.getDay_line_shop_max()){//当日线下交易超出当日限定线下交易总额
			msgVo.setCode(Apiconstant.User_Day_Line_Is_Enough.getIndex());
			msgVo.setMsg(Apiconstant.User_Day_Line_Is_Enough.getName());
		} else {
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return msgVo;
	}
	
	/**
	 * 判断当日是否超过结算额度
	 * 
	 * @param user_id
	 * @param points
	 * @return
	 */
	public MessageVO isSettleAccount(BigInteger user_id,int points){
		MessageVO msgVo = new MessageVO();
		ZjcUsersAccountInfoPO account = zjcUsersAccountInfoDao.selectByKey(user_id);
		if(AOSUtils.isEmpty(account)){//账户信息不存在
			msgVo.setCode(Apiconstant.Account_Not_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Account_Not_Exist.getName());
		} else {
			if(account.getSettlement_sum()==1){//没有开通取消限额
				BigDecimal outNumStr = new BigDecimal(sqlDao.selectOne("com.zjc.order.dao.ZjcBzOrderDao.getTodaySettleNum", Dtos.newDto("user_id", user_id)).toString());
				int outNum = outNumStr.intValue();
				ZjcMemberSettlementPO paramater = (ZjcMemberSettlementPO)sqlDao.selectOne("com.zjc.system.dao.ZjcMemberSettlementDao.selectByMaxKey",null);
				
				if(points<paramater.getPoints_other_min()){//单笔结算金额最小值
					msgVo.setCode(Apiconstant.User_One_Settle_Is_Low.getIndex());
					msgVo.setMsg("您的单笔倍增结算额度低于最小额度"+ paramater.getPoints_other_min() +"，请增加结算数额");
				} else if(points + outNum > paramater.getSum_settlement()){//当日结算总额超出当日限定结算总额
					msgVo.setCode(Apiconstant.User_Day_Settle_Is_Enough.getIndex());
					msgVo.setMsg(Apiconstant.User_Day_Settle_Is_Enough.getName());
				} else {
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());
				}
			} else {//开通了不限额，不进行倍增结算限制
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return msgVo;
	}
	
	/**
	 * 判断商城购物是否超出额度
	 * 
	 * @param user_id
	 * @param points
	 * @return
	 */
	public MessageVO isShopOnLineAccount(BigInteger user_id,int points){
		MessageVO msgVo = new MessageVO();
		BigDecimal outNumStr = new BigDecimal(sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.getTodayShopOnlineNum", Dtos.newDto("user_id", user_id)).toString());
		int outNum = outNumStr.intValue();
		ZjcMemberParameterPO paramater = (ZjcMemberParameterPO)sqlDao.selectOne("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",null);
		if(points + outNum > paramater.getDay_online_shop_max()){//当日商城购物超出当日限定交易总额
			msgVo.setCode(Apiconstant.User_Day_Shop_Online_Is_Enough.getIndex());
			msgVo.setMsg(Apiconstant.User_Day_Shop_Online_Is_Enough.getName());
		} else {
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}
		return msgVo;
	}
	
	/**
	 * 截取字符首尾两端的空格，引号
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		str = str.trim();
		//str = str.replaceAll("\'", "");
		//str = str.replaceAll("\"", "");
		return str;
	}
	
	/**
	 * 判读字符串是否为整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	
}
