package com.api.common.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aos.framework.core.utils.AOSCodec;

import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 验证工具类
 * 
 * @author wgm
 *
 */
public class ValidateUtil {
	
	/**
	 * 验证手机号是否正确
	 * 
	 * @return 验证通过返回true
	 */
	public static boolean isRightPhone(String mobile){
		if(mobile.startsWith("1") && mobile.length() == 11){
			return true;
		} else {
			return false;
		}
	}
	
	 /** 
	  * 电话号码验证 
	  * @param  str 
	  * @return 验证通过返回true 
	  */  
	 public static boolean isTelephone(final String str) {  
	     Pattern p1 = null, p2 = null;  
	     Matcher m = null;  
	     boolean b = false;  
	     p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
	     p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
	     if (str.length() > 9) {  
	        m = p1.matcher(str);  
	        b = m.matches();  
	     } else {  
	         m = p2.matcher(str);  
	        b = m.matches();  
	     }  
	     return b;  
	 }
	 
	 /**
	  * 校验密码是否正确
	  * 
	 * @param userinfo 用户信息
	 * @param random 随机码
	 * @param design 校验包
	 * @param psd_type 密码类型
	 * @return boolean
	 */
	public static boolean checkPsd(ZjcUsersInfoPO userinfo,String random,String design,String psd_type){
		 boolean psd_bol = false;
		 if(ConstantUtil.LOGIN_PSD_TYPE.equals(psd_type)){//验证登陆密码
			//拼装校验包
			String sign=random+userinfo.getMobile()+userinfo.getPassword()+"zjc_1815";
			sign=AOSCodec.md5(sign);
			if(sign.equals(design)){
				psd_bol = true;
			}
		 } else if(ConstantUtil.PAY_PSD_TYPE.equals(psd_type)){//验证支付密码
			//拼装校验包
			String sign=random+userinfo.getMobile()+userinfo.getPay_password()+"zjc_1815";
			sign=AOSCodec.md5(sign);
			if(sign.equals(design)){
				psd_bol = true;
			}
		 }
		return psd_bol;
	 }
	
	/**
     * 判断身份证格式
     * 
     * @param idCard
     * @return boolean
     */
    public static boolean isIdCard(String idCard) {
        // 中国公民身份证格式：长度为15或18位，最后一位可以为字母
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        // 格式验证
        if (!idNumPattern.matcher(idCard).matches())
            return false;
        // 合法性验证
        int year = 0;
        int month = 0;
        int day = 0;
        if (idCard.length() == 15) {// 一代身份证
            // 提取身份证上的前6位以及出生年月日
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{2})(\\d{2})(\\d{2}).*");
            Matcher birthDateMather = birthDatePattern.matcher(idCard);
            if (birthDateMather.find()) {
                year = Integer.valueOf("19" + birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));
            }
        } else if (idCard.length() == 18) {// 二代身份证
            // 提取身份证上的前6位以及出生年月日
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");
            Matcher birthDateMather = birthDatePattern.matcher(idCard);
            if (birthDateMather.find()) {
                year = Integer.valueOf(birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));
            }
        }
        // 年份判断，100年前至今
        Calendar cal = Calendar.getInstance();
        // 当前年份
        int currentYear = cal.get(Calendar.YEAR);
        if (year <= currentYear - 100 || year > currentYear)
            return false;
        // 月份判断
        if (month < 1 || month > 12)
            return false;
        // 日期判断
        // 计算月份天数
        int dayCount = 31;
        switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            dayCount = 31;
            break;
        case 2:
            // 2月份判断是否为闰年
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                dayCount = 29;
                break;
            } else {
                dayCount = 28;
                break;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            dayCount = 30;
            break;
        }
        if (day < 1 || day > dayCount)
            return false;
        return true;
    }
}