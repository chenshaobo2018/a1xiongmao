/**
 * 
 */
package com.api.ApiPublic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.utils.AOSUtils;
import aos.system.common.id.IdService;

import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.po.ZjcUserLogPO;

/**
 * @author wgm
 * 
 *api日志模板生成
 */
@Service(value="apiLogService")
public class ApiLogService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcUserLogDao zjcUserLogDao;
	
	@Autowired
	private IdService idService;
	
    
    /**
     * 生成日志模板
     * 
     * @param list
     * @return
     */
    public String getLogMsg(Map<String, Object> map) {
        
        StringBuffer logString = new StringBuffer(0);
        	//获取日志类型
            String logType = map.get("logType").toString();
            if ("登陆".equals(logType)) {//登陆
            	logString.append(String.format("%s会员登录系统成功。",map.get("user_id")));
            } else if ("注册".equals(logType)) {//注册
	           	logString.append(String.format("%s会员推荐手机号为%s的用户注册。",
	           			map.get("recommand_code"),
	           			map.get("mobile")));
            } else if ("设置密码".equals(logType)) {//设置支付密码
	           	logString.append(String.format("%s会员在APP上设置支付密码成功。",map.get("user_id")));
            } else if ("修改密码".equals(logType)) {//修改密码
	           	logString.append(String.format("%s会员在APP上修改登陆密码成功。",map.get("user_id")));
            } else if ("找回密码".equals(logType)) {//找回密码
	           	logString.append(String.format("%s会员在APP上使用手机号%s找回了密码。",
	           			map.get("user_id"),
	           			map.get("mobile")));
            } else if ("修改地址".equals(logType)) {//收货地址（do_type:新增，修改）
	           	logString.append(String.format("%s会员在设备修改了地址。",
	           			map.get("user_id")));
            } else if ("收货地址".equals(logType)) {//收货地址（do_type:新增，修改）
	           	logString.append(String.format("%s会员%s收货地址。收货人：%s，联系方式：%s，联系地址：%s。",
	           			map.get("user_id"),
	           			map.get("do_type"),
	           			map.get("consignee"),
	           			map.get("mobile"),
	           			map.get("address")));
            } else if("转账".equals(logType)){//转账到本人
            	if("会员转本人".equals(map.get("type").toString())){
            		logString.append(String.format("%s会员将可转券%s转到可用券。当前可用券:%s， 当前可转券:%s",
                            map.get("user_id"),
                            map.get("make_over_integral"),
                            map.get("now_points"),
                            map.get("now_make_over_integral")));
            	} else if("商家转会员".equals(map.get("type").toString())){
            		logString.append(String.format("%s商家向%s会员转账%s可转券 。 当前可转券：%s",
                            map.get("user_id"),
                            map.get("to_user_id"),
                            map.get("make_over_integral"),
                            map.get("now_make_over_integral")));
            	} else if("会员收商家".equals(map.get("type").toString())){
            		logString.append(String.format("%s会员收到%s商家转账%s可转券 。 当前可转券：%s",
                            map.get("user_id"),
                            map.get("to_user_id"),
                            map.get("make_over_integral"),
                            map.get("now_make_over_integral")));
            	} else if("会员转会员".equals(map.get("type").toString())){
            		logString.append(String.format("%s会员向%s会员转账%s可转券 。对方号码：%s，名字：%s。 当前可转券：%s",
                            map.get("user_id"),
                            map.get("to_user_id"),
                            map.get("make_over_integral"),
                            map.get("mobile"),
                            map.get("user_name"),
                            map.get("now_make_over_integral")));
            	} else if("会员收会员".equals(map.get("type").toString())){
            		logString.append(String.format("%s会员收到%s会员转账%s可转券 。对方号码：%s，名字：%s。 当前可转券：%s",
            				map.get("user_id"),
                            map.get("to_user_id"),
                            map.get("make_over_integral"),
                            map.get("mobile"),
                            map.get("user_name"),
                            map.get("now_make_over_integral")));
            	}
            } else if ("结算".equals(logType)) {//结算
            	if("会员".equals(map.get("type").toString())){//会员结算
            		logString.append(String.format("%s会员在%s会员实体店结算%s可用券，并获得赠送券%s。当前可用券：%s",
    	           			map.get("user_id"),
    	           			map.get("to_user_id"),
    	           			map.get("pay_points"),
    	           			map.get("due_tc_points"),
    	           			map.get("now_points")));
            	} else if("结算中心".equals(map.get("type").toString())){//结算中心结算
            		logString.append(String.format("%s会员实体店收到%s会员从商城结算的%s可转券。对方号码:%s，名字：%s。当前可转券：%s",
    	           			map.get("user_id"),
    	           			map.get("to_user_id"),
    	           			map.get("make_over_integral"),
    	           			map.get("mobile"),
    						map.get("real_name"),
    	           			map.get("now_make_over_integral")));
            	}
	           	
            } else if ("结算提成".equals(logType)) {//结算提成
	           	logString.append(String.format("亲，恭喜您获得%s会员结算赠送您的%s券已到账，请查收！当前可转券：%s",
	           			map.get("user_id"),
	           			map.get("due_tc_points"),
	           			map.get("now_make_over_integral")));
            } else if ("商城购物".equals(logType)) {//商城购物：pay_type(易支付，混合支付，在线支付)
            	if("礼品兑换".equals(map.get("pay_type").toString())){//商城购物-礼品兑换
            		BigDecimal big = (BigDecimal) map.get("pay_points");
            		logString.append(String.format("%s会员在%s商家实体店用%s可用券兑换礼品，支付方式：易支付。订单号为:%s，当前可用券：%s",
    	           			map.get("user_id"),
    	           			map.get("to_user_id"),
    	           			big.setScale(0),
    	           			map.get("order_sn"),
    	           			map.get("now_points")));
            	} else {
            		BigDecimal big = (BigDecimal) map.get("pay_points");
		           	logString.append(String.format("%s会员在%s商家实体店用%s可用券购物，支付方式：%s。订单号为：%s，并获得赠送券%s，当前可用券：%s",
		           			map.get("user_id"),
		           			map.get("to_user_id"),
		           			big.setScale(0),
		           			map.get("pay_type"),
		           			map.get("order_sn"),
		           			map.get("due_tc_points"),
		           			map.get("now_points")));
            	}
            } else if ("线下购物".equals(logType)) {//线下购物
	           	logString.append(String.format("%s会员在%s商家实体店用%s可用券线下购物，并获得赠送券%s。当前可用券：%s",
	           			map.get("user_id"),
	           			map.get("to_user_id"),
	           			map.get("pay_points"),
						map.get("due_tc_points"),
	           			map.get("now_points")));
            } else if ("线下购物结算".equals(logType)) {//线下购物结算
	           	logString.append(String.format("%s商家实体店收到%s会员从线下购物结算的%s可转券。对方号码：%s，名字：%s，当前可转券：%s",
	           			map.get("user_id"),
	           			map.get("to_user_id"),
	           			map.get("make_over_integral"),
						map.get("mobile"),
						map.get("real_name"),
	           			map.get("now_make_over_integral")));
            } else if("自然消费".equals(logType)){//自然消费(结算)（包括倍增提成，倍增结算）
            	logString.append(String.format("亲，您的自然消费满时间段，时对时商城返点赠送的%s券已到账,请查收！当前可用券：%s，当前可转券：%s。",
            			map.get("due_tc_points"),
	           			map.get("now_points"),
	           			map.get("now_make_over_integral")));
            } else if ("商家实体店交易".equals(logType)) {//商家实体店交易
            	BigDecimal big = (BigDecimal) map.get("pay_points");
	           	logString.append(String.format("%s会员在%s商家实体店用%s可用券购物，订单号为:%s，请%s商家实体店到商家后台核对信息并发货。",
	           			map.get("to_user_id"),
	           			map.get("user_id"),
	           			big.setScale(0),
	           			map.get("order_sn"),
	           			map.get("user_id")));
            } else if ("商家实体店结算".equals(logType)) {//商家实体店结算
            	BigDecimal big = (BigDecimal) map.get("pay_points");
	           	logString.append(String.format("%s商家实体店收到%s会员从商城结算的%s券，订单号为:%s，当前可转券：%s。",
	           			map.get("to_user_id"),
	           			map.get("user_id"),
	           			big.setScale(0),
	           			map.get("order_sn"),
	           			map.get("now_make_over_integral")));
            } else if ("注册赠送".equals(logType) ||"购物赠送".equals(logType) || "售后处理".equals(logType)) {//注册赠送，购物赠送，售后处理
            	if("可用".equals(map.get("type").toString())){//到可用
            		logString.append(String.format("亲，恭喜您获得商城赠送您的%s券已到账，请查收！当前可用券：%s",
    	           			map.get("due_tc_points"),
    	           			map.get("now_points")));
            	} else if("可转".equals(map.get("type").toString())){//到可转
            		logString.append(String.format("亲，恭喜您获得商城赠送您的%s券已到账，请查收！当前可转券：%s",
    	           			map.get("due_tc_points"),
    	           			map.get("now_make_over_integral")));
            	} else if("可转可用各一半".equals(map.get("type").toString())){//可用可转各一半
            		logString.append(String.format("亲，恭喜您获得商城赠送您的%s券已到账，请查收！当前可用券：%s，当前可转券：%s",
    	           			map.get("due_tc_points"),
    						map.get("now_points"),
    	           			map.get("now_make_over_integral")));
            	} else if("退券".equals(map.get("type").toString())){//退券，售后处理独有
            		logString.append(String.format("亲，恭喜您获得申请退回的%s券已到账，请查收！当前可用券：%s，当前可转券：%s",
    	           			map.get("due_tc_points"),
    						map.get("now_points"),
    	           			map.get("now_make_over_integral")));
            	}
            } else if ("账户冻结".equals(logType)) {//账户冻结
	           	logString.append(String.format("%s会员，由于您的账户异常，现已被冻结。",
	           			map.get("user_id")));
            } else if ("账户解封".equals(logType)) {//账户解封
	           	logString.append(String.format("%s会员，恭喜您的账户解封。",
	           			map.get("user_id")));
            } else if ("商城赠送".equals(logType)) {//商城赠送
            	if("双千资格".equals(map.get("type").toString())){//双千资格
            		logString.append(String.format("亲，恭喜您成为双创合伙人。"));
            	} else if("取消双创资格".equals(map.get("type").toString())){//取消双千资格
            		logString.append(String.format("亲，很遗憾您的双创合伙人资格被取消。"));
            	} else if("双千会员".equals(map.get("type").toString())){//双千会员
            		logString.append(String.format("亲，恭喜您获得商城赠送您的%s券已到账，请查收！当前可转券：%s",
    	           			map.get("due_tc_points"),
    	           			map.get("now_make_over_integral")));
            	} else if ("抽奖赠送".equals(map.get("type").toString())) {//抽奖赠送
    	           	logString.append(String.format("亲，恭喜您获得商城赠送您的%s券已到账，请查收！当前可用券：%s",
    	           			map.get("due_tc_points"),
    	           			map.get("now_pay_points")));
                }
            } else if ("我要开店".equals(logType)) {//商城赠送
            	if("开店成功".equals(map.get("type").toString())){//开店成功
            		logString.append(String.format("%s会员，恭喜您开店审核通过，请尽快登录商家后台",
    	           			map.get("user_id")));
            	} else if("开店失败".equals(map.get("type").toString())){//开店失败
            		logString.append(String.format("%s会员，很抱歉由于您的资料不齐，开店审核未通过，请重新提交",
    	           			map.get("user_id")));
            	}
            } else if("委托收款申请".equals(logType)){
            	if("审核通过".equals(map.get("type").toString())){//开店成功
            		logString.append(String.format("%s会员，恭喜您的委托收款审核通过，请尽快登录商家后台",
    	           			map.get("user_id")));
            	} else if("审核失败".equals(map.get("type").toString())){//开店失败
            		logString.append(String.format("%s会员，很抱歉由于您的资料不齐，委托收款审核未通过，请重新提交",
    	           			map.get("user_id")));
            	}
            }else if ("解绑/绑定账号".equals(logType)) {//解绑/绑定账号
	           	logString.append(String.format("%s会员%s微信账号成功。",map.get("user_id"),map.get("do_type")));
            } else if ("易物券置换".equals(logType)) {//易物券置换
	           	logString.append(String.format("%s会员置换了%s易物券已到账，请查收！支付方式：%s，并获得赠送易物券%s。当前可用易物券：%s，当前可转易物券：%s",
	           			map.get("user_id"),
	           			map.get("pay_points"),
	           			map.get("pay_type"),
	           			map.get("due_tc_points"),
	           			map.get("now_points"),
	           			map.get("now_make_over_integral")));
            } else if ("上级提成".equals(logType)) {//分享服务
	           	logString.append(String.format("亲，恭喜您，因分享服务获得提成，商城赠送您的%s易物券已到账，请查收！当前可转易物券：%s",
	           			map.get("due_tc_points"),
	           			map.get("now_make_over_integral")));
            } else if("收到转账".equals(logType)){//收到转账
            	logString.append(String.format("收到%s会员转账的%s可转易物券 ，对方号码：%s ，名字：%s， 当前可转易物券：%s",
                        map.get("to_user_id"),
                        map.get("make_over_integral"),
                        map.get("mobile"),
                        map.get("user_name"),
                        map.get("now_make_over_integral")));
            } else if ("开通结算支付".equals(logType)) {//开通结算支付
	           	logString.append("开通结算支付成功"); 
            } else if ("提取签到奖励".equals(logType)) {//提取签到奖励
	           	logString.append("提取签到奖励成功");
            } else if ("推荐注册".equals(logType)) {//推荐注册
	           	logString.append(String.format("%s会员推荐手机号为%s的用户注册",
	           			map.get("user_id"),
	           			map.get("mobile")));
            } else if ("平台结算".equals(logType)) {//平台结算
	           	logString.append(String.format("亲，恭喜您获得商城平台结算您的%s易物券已到账，请查收！",
	           			map.get("due_tc_points")));
            } else if ("签到获赠转可用".equals(logType)) {//签到获赠转可用
	           	logString.append(String.format("亲，商城签到获赠的%s易物券提现成功！您现在的可用易物券：%s",
	           			map.get("due_tc_points"),
	           			map.get("now_points")));
            } else if ("系统提示".equals(logType)) {//系统提示
	           	logString.append(String.format("【中军创】尊敬的%s会员 : 您好！由于系统监测到您的账户于%s出现异常，现已处理。谢谢！",
	           			map.get("user_id"),
	           			map.get("do_time")));
            }else if("xpt转会员".equals(logType)){
            	logString.append(String.format("%s会员向%s会员转账%s可转券 。对方号码：%s，名字：%s。 当前可转券：%s",
                        map.get("user_id"),
                        map.get("to_user_id"),
                        map.get("make_over_integral"),
                        map.get("mobile"),
                        map.get("user_name"),
                        map.get("now_make_over_integral")));
			}else if("会员收到xpt转账".equals(logType)){
				logString.append(String.format("收到%s会员转账的%s可转易物券 ，对方号码：%s ，名字：%s， 当前可转易物券：%s",
                        map.get("to_user_id"),
                        map.get("make_over_integral"),
                        map.get("mobile"),
                        map.get("user_name"),
                        map.get("now_make_over_integral")));
			} else if("活动消费".equals(logType)){//活动抽奖扣券
            	logString.append(String.format("亲，您在大转盘活动用%s可用券抽奖,当前可用券：%s。",
            			map.get("used_points"),
	           			map.get("now_pay_points")));
            } else if("积分换取slb".equals(logType)){//活动抽奖扣券
            	logString.append(String.format("%s会员将可转券%s转到钱包换取slb,当前可转券:%s",
                        map.get("user_id"),
                        map.get("make_over_integral"),
                        map.get("now_make_over_integral")));
            } 
        
        return logString.toString();
    }
    
    /**
     * 新增用户日志
     * 
     * @param map
     * @return successNum
     */
    public int saveLog(Map<String, Object> map){
    	String descs = this.getLogMsg(map);
    	ZjcUserLogPO userLog = new ZjcUserLogPO();
    	userLog.setUser_id(BigInteger.valueOf(Long.parseLong(map.get("user_id").toString())));
    	userLog.setTime(new Date());
    	userLog.setType(map.get("logType").toString());
    	userLog.setDescs(descs);
    	if(!AOSUtils.isEmpty(map.get("show_type"))){//展示类型：0表示不展示在APP上，1表示要展示在APP上
    		userLog.setShow_type(Integer.parseInt(map.get("show_type").toString()));
    	}
    	try {
    		int successNum = zjcUserLogDao.insert(userLog);
    		return successNum;
		} catch (Exception e) {
			return 0;
		}
    }
	
}
