/**
 * 
 */
package com.api.slb.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.AESUtils;
import com.api.common.util.MD5SignUtils;
import com.api.common.util.RSAUtils;
import com.api.common.util.StringUtils;
import com.api.common.util.HttpUtil;
import com.api.goods.controller.GoodsController;
import com.gexin.fastjson.JSONObject;
import com.zjc.system.dao.ZjcMemberParameterDao;
import com.zjc.system.dao.po.ZjcMemberParameterPO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;
/**
 * @author Administrator
 *
 */
@Service(value="SlbService")
public class SlbService {
	private static final Logger logger = LoggerFactory.getLogger(SlbService.class);

	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	@Autowired
	private ZjcMemberParameterDao ZjcMemberParameterDao;
	@Autowired
	private ApiLogService apiLogService;
	/**
	 *获取用户地址
	 * 
	 * @param request
	 * @return message
	 */
	public String checkUserRegister(String mobile,String mobileArea){
		//公钥
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCx4DP4sSde3yncPdJlPHLGNisl5PRpcvjjeet88Jd5vj1uMmAqPWSHwzn+k0TWXxQclL0h/GhWNQ49dWV1ooc+NlF91T9ChRNDr0VMvRwYYmx/5fT/BzWhFWD1g6WvgDKbCezE6DH+gckszVjNXmhZeeJVSTqT8uK0JZU7MYbYZwIDAQAB";
		//生成加密随机串
		String noteStr =  String.valueOf(System.currentTimeMillis());
		noteStr = StringUtils.leftPad(noteStr, 16,  "0");
		String path=null;
		//生成签名
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("mobile", mobile);
		packageParams.put("mobileArea", mobileArea);
		packageParams.put("noteStr", noteStr);
		String sign = MD5SignUtils.createMD5Sign(packageParams, MD5SignUtils.CHARSET_NAME_DEFAULT);
		String url = MD5SignUtils.url+"/wallet/externalWallet/checkUserRegister.htm";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("mobile", mobile);
		params.put("mobileArea", mobileArea);
		params.put("noteStr", noteStr);
		params.put("sign", sign);
		try {
			String result = HttpUtil.doPost(url, params);
			JSONObject json_test = JSONObject.parseObject(result);
			logger.info("========================app获取的钱包地址获取回来的参数"+json_test);
			if(StringUtils.isNotEmpty(json_test.toString())){
				if("0".equals(json_test.get("resultCode").toString())){
					String json=json_test.get("data").toString();
					JSONObject jsont = JSONObject.parseObject(json);
					if("0".equals(jsont.get("exist").toString())){
						//生成加密随机串
						noteStr =  String.valueOf(System.currentTimeMillis());
						noteStr = StringUtils.leftPad(noteStr, 16,  "0");	
						//加密登录密码
						String loginPwd = "li1234567890";
						loginPwd = AESUtils.encode(loginPwd, noteStr);
						//公钥加密随机串
						String encodedNoteStr = RSAUtils.encryptByPublicKey(noteStr, publicKey);
						//生成签名
						SortedMap<String, String> packageParamszc = new TreeMap<String, String>();
						packageParamszc.put("mobile", mobile);
						packageParamszc.put("loginPwd", loginPwd);
						packageParamszc.put("mobileArea", mobileArea);
						packageParamszc.put("noteStr", encodedNoteStr);
						sign = MD5SignUtils.createMD5Sign(packageParamszc, MD5SignUtils.CHARSET_NAME_DEFAULT);
						url = MD5SignUtils.url+"/wallet/externalWallet/userRegister.htm";
						Map<String,Object> paramszc = new HashMap<String,Object>();
						paramszc.put("mobile", mobile);
						paramszc.put("loginPwd", loginPwd);
						paramszc.put("mobileArea", mobileArea);
						paramszc.put("noteStr", encodedNoteStr);
						paramszc.put("sign", sign);
						result = HttpUtil.doPost(url, paramszc);
						JSONObject zhuce = JSONObject.parseObject(result);
						if("0".equals(zhuce.get("resultCode").toString())&&"成功".equals(zhuce.get("message").toString())){
							path=getWalletList(mobile,mobileArea);
						}
					}else {
						    path=getWalletList(mobile,mobileArea);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;	
	}
	
	public String getWalletList(String mobile,String mobileArea){
				//公钥
				String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCx4DP4sSde3yncPdJlPHLGNisl5PRpcvjjeet88Jd5vj1uMmAqPWSHwzn+k0TWXxQclL0h/GhWNQ49dWV1ooc+NlF91T9ChRNDr0VMvRwYYmx/5fT/BzWhFWD1g6WvgDKbCezE6DH+gckszVjNXmhZeeJVSTqT8uK0JZU7MYbYZwIDAQAB";
				//生成加密随机串
				String noteStr = String.valueOf(System.currentTimeMillis());
				noteStr = StringUtils.leftPad(noteStr, 16,  "0");
				//加密登录密码
				String platTransPwd = "1234567890";
				platTransPwd = AESUtils.encode(platTransPwd, noteStr);
				//公钥加密随机串
				String encodedNoteStr = RSAUtils.encryptByPublicKey(noteStr, publicKey);
				//生成签名
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("mobile", mobile);
				packageParams.put("mobileArea", mobileArea);
				packageParams.put("platTransPwd", platTransPwd);
				packageParams.put("noteStr", encodedNoteStr);
				String sign = MD5SignUtils.createMD5Sign(packageParams, MD5SignUtils.CHARSET_NAME_DEFAULT);
				String url = MD5SignUtils.url+"/wallet/externalWallet/getWalletList.htm";
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("mobile", mobile);
				params.put("mobileArea", mobileArea);
				params.put("platTransPwd", platTransPwd);
				params.put("noteStr", encodedNoteStr);
				params.put("sign", sign);
				String result = HttpUtil.doPost(url, params);
				JSONObject json_test = JSONObject.parseObject(result);
				String json=json_test.get("data").toString();
				JSONObject jsont = JSONObject.parseObject(json);
		        return jsont.get("walletAddress").toString();
	}
	
	  public String transferToSLB(HttpModel httpModel){
				MessageVO msgVO = new  MessageVO();
				Dto dto = httpModel.getInDto();
				boolean b = true;
				if(b){//关闭转账功能
					msgVO.setCode(Apiconstant.Do_Fails.getIndex());
					msgVO.setMsg("很抱歉，兑换已停止");
					return AOSJson.toJson(msgVO);
				}
				String mobile=dto.getString("mobile");
				String mobileArea=dto.getString("mobileArea");
				logger.info("========================app获取的钱包地址"+mobile,mobileArea);
				 //钱包地址
				String walletAddress=checkUserRegister(mobile,mobileArea);
				logger.info("========================app获取的钱包地址"+walletAddress);
				if(AOSUtils.isNotEmpty(walletAddress)){
					//中军创减分
					ZjcUsersAccountInfoPO  user=ZjcUsersAccountInfoDao.selectByKey(new BigInteger(httpModel.getAttribute("user_id").toString()));
					ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id",httpModel.getAttribute("user_id").toString()));
					ZjcMemberParameterPO ZjcMemberParameterPO=ZjcMemberParameterDao.selectByMaxKey();
					BigDecimal decimal = new BigDecimal(ZjcMemberParameterPO.getIntegral());//限制分数
					BigDecimal integral =new BigDecimal(dto.getString("integral"));//用户转账分数
					BigDecimal minintegral =new BigDecimal(1000);//用户转账分数
					BigDecimal make_over_integral =new BigDecimal(user.getMake_over_integral());//用户可转分数
					BigDecimal decimals= user.getPending_transfer().add(integral);//加上转账分数后用户转账额度
					BigDecimal transfAmount;
					SimpleDateFormat dftsd = new SimpleDateFormat("yyyy-MM-dd");
					boolean a = false;
					if(integral.compareTo(minintegral)==-1){
						  msgVO.setCode(Apiconstant.Do_Fails.getIndex());
						  msgVO.setMsg("单次至少转账1000券");
						  return AOSJson.toJson(msgVO);	
					}
					
					if(dftsd.format(user.getUpdate_time()).equals(dftsd.format(new Date()))){
						if(decimal.compareTo(decimals)>-1 && make_over_integral.compareTo(integral)>-1){
							a = true;
						}
					}else {
						if( decimal.compareTo(integral)>-1 && make_over_integral.compareTo(integral)>-1){
							a = true;
							decimals=integral;
						}
					}
					
					if(a) {//转账限制
							transfAmount=integral.divide(new BigDecimal(ZjcMemberParameterPO.getAmount()));
							String orderSn = Long.toString(System.currentTimeMillis());
						    Map<String,Object> exChangemap = new HashMap<String,Object>();
							exChangemap.put("logType", "积分换取slb");
							exChangemap.put("type", "会员转钱包");
							exChangemap.put("user_id", user.getUser_id());
							exChangemap.put("to_user_id", orderSn);
							exChangemap.put("make_over_integral", integral);
							exChangemap.put("mobile", userInfo.getMobile());
							exChangemap.put("user_name", userInfo.getReal_name());
							exChangemap.put("now_make_over_integral", user.getMake_over_integral()-Integer.parseInt(integral.toString()));
							exChangemap.put("show_type", 1);
							//去sl平台注册
							  String urls ="http://api1.xn--ykq093c.com/sl/api/common/v1/BulkRegistration/registration";
							  Map<String,Object> paramst = new HashMap<String,Object>();
							  paramst.put("phone", mobile);
							  String ab=HttpUtil.doPost(urls, paramst);
							  System.out.println(ab);
							
							//公钥
							String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCx4DP4sSde3yncPdJlPHLGNisl5PRpcvjjeet88Jd5vj1uMmAqPWSHwzn+k0TWXxQclL0h/GhWNQ49dWV1ooc+NlF91T9ChRNDr0VMvRwYYmx/5fT/BzWhFWD1g6WvgDKbCezE6DH+gckszVjNXmhZeeJVSTqT8uK0JZU7MYbYZwIDAQAB";
							//生成加密随机串
							String noteStr =  String.valueOf(System.currentTimeMillis());
							noteStr = StringUtils.leftPad(noteStr, 16,  "0");
							
							String platTransPwd = "1234567890";
							String endcodePaltTransPwd = AESUtils.encode(platTransPwd, noteStr);
							Calendar cale = null;  
						    cale = Calendar.getInstance(); 
							int day = cale.get(Calendar.DATE); 
							String lockBeginDate=null;
							String lockEndDate=null;
							SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
							try {
								    Calendar calendar = Calendar.getInstance();
								    if(day>16){
								         calendar.add(Calendar.MONTH, 1);
								         calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
								         lockBeginDate=dft.format(calendar.getTime())+"16";

								    }else {
								         calendar.add(Calendar.MONTH,0);
								         calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
								         lockBeginDate=dft.format(calendar.getTime())+"16";
									}
								    SimpleDateFormat dfts = new SimpleDateFormat("yyyyMMdd");
								    Calendar calendars = new GregorianCalendar();
						            calendars.setTime(dfts.parse(lockBeginDate));
						            calendars.add(Calendar.YEAR,1);// 把日期往后增加一年.整数往后推,负数往前移动
						            Date date = calendars.getTime();
									lockEndDate =dft.format(date)+"16";
							} catch (Exception e) {
								msgVO.setCode(Apiconstant.Do_Fails.getIndex());
								msgVO.setMsg(Apiconstant.Do_Fails.getName());
								e.printStackTrace();
							}
							String releaseNum = "36";
							String releasePercent = "2.78";
							//String transfAmount = "50000";
							//String orderSn = Long.toString(System.currentTimeMillis());
							String batchType = "C";
							//公钥加密随机串
							String encodedNoteStr = RSAUtils.encryptByPublicKey(noteStr, publicKey);
							//生成签名
							SortedMap<String, String> packageParams = new TreeMap<String, String>();
							packageParams.put("walletAddress", walletAddress);
							packageParams.put("platTransPwd", endcodePaltTransPwd);
							packageParams.put("lockBeginDate", lockBeginDate);
							packageParams.put("lockEndDate", lockEndDate);
							packageParams.put("releaseNum", releaseNum);
							packageParams.put("releasePercent", releasePercent);
							packageParams.put("transfAmount", transfAmount.toString());
							packageParams.put("noteStr", encodedNoteStr);
							packageParams.put("orderSn", orderSn);
							packageParams.put("batchType", batchType);
							String sign = MD5SignUtils.createMD5Sign(packageParams, MD5SignUtils.CHARSET_NAME_DEFAULT);
							String url =MD5SignUtils.url+ "/wallet/externalWallet/transferToSlbSc.htm";
							Map<String,Object> params = new HashMap<String,Object>();
							params.put("walletAddress", walletAddress);
							params.put("platTransPwd", endcodePaltTransPwd);
							params.put("lockBeginDate", lockBeginDate);
							params.put("lockEndDate", lockEndDate);
							params.put("releaseNum", releaseNum);
							params.put("releasePercent", releasePercent);
							params.put("transfAmount", transfAmount.toString());
							params.put("noteStr", encodedNoteStr);
							params.put("orderSn", orderSn);
							params.put("batchType", batchType);
							params.put("noteStr", encodedNoteStr);
							params.put("sign", sign);
							params.put("transfType", "ZJC");
							String result = HttpUtil.doPost(url, params);
							JSONObject json_test = JSONObject.parseObject(result);
							if("0".equals(json_test.get("resultCode").toString())){
								apiLogService.saveLog(exChangemap);
								user.setMake_over_integral(user.getMake_over_integral()-Integer.parseInt(integral.toString()));
								user.setPending_transfer(decimals);
								user.setUpdate_time(new Date());
								ZjcUsersAccountInfoDao.updateByKey(user);
								msgVO.setCode(Apiconstant.Do_Success.getIndex());
								msgVO.setMsg(Apiconstant.Do_Success.getName());
							}else {
								msgVO.setCode(Apiconstant.Do_Fails.getIndex());
								msgVO.setMsg(Apiconstant.Do_Fails.getName());
							}
						}else {
							   msgVO.setCode(Apiconstant.LiaoDou_Too_Manyb.getIndex());
							   msgVO.setMsg("你今天转slb超出了限额,请明天转账");
						}
					 
					  }else {
						  msgVO.setCode(Apiconstant.Do_Fails.getIndex());
						  msgVO.setMsg("系统正忙,请检查稍后再试");
					}
		        return AOSJson.toJson(msgVO);	
	}
	  
	 
}
