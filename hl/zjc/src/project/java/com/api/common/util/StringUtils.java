package com.api.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 字符串操作工具类，继承apache的StringUtils
 *
 * @author KangWei
 * @Date 11-12-28
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    /**
     * 批量替换
     *
     * @param source 源字符串
     * @param with   替换字符串
     * @param repls  需替换的字符串列表
     * @return 替换后的字符串
     */
    public static String replaceStr(String source, String with, String... repls) {
        for (String repl : repls) {
            source = replace(source, repl, with);
        }

        return source;
    }

    public static String regexReplace(String soucre, String with, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(soucre);
        while (matcher.find()) {
            soucre = replace(soucre, matcher.group(), with);
        }

        return soucre;
    }
    
    /**
    * @Title: replaceStrArr
    * @Description: 批量替换字符串
    * @param @param oldChar 原字符
    * @param @param newChar 替换的字符
    * @param @param strArr 原来的字符串数组
    * @return String[] 返回的字符串数组
    * @throws
     */
    public static String[] replaceStrArr(String oldChar,String newChar,String[] strArr){
    	String[] strArray = new String[strArr.length];
    	for(int i = 0;i < strArr.length;i++){
    		strArray[i] = strArr[i].replace(oldChar, newChar);
    	}
    	return strArray;
    }

    /**
     * ip校验
     *
     * @param s s
     * @return true or false
     */
    public static Boolean isIpAddress(String s) {
        String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 判断是否是手机号码
     *
     * @param source 待检查字符串
     * @return 是返回true，否则返回false
     */
    public static boolean isChinaMobile(String source) {
        return validate(source, "(13[0-9]\\d{8})|(15[0-9]\\d{8})|(18[0-9]\\d{8}|14[0-9]\\d{8})");
    }

    /**
     * 根据给定的正则表达式判断字符串是否合法
     *
     * @param source 待检查字符串
     * @param regex  正则表达式
     * @return 是返回true，否则返回false
     */
    public static boolean validate(String source, String regex) {
        return source != null && source.matches(regex);
    }

    /**
     * 将指定的字符串进行 URL 编码，用于生成 URL 或参数
     *
     * @param message 要进行编码的字符串
     * @return 编码后的字符串
     */
    public static String urlEncode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return message;
        }
    }

    /**
    * @Title: splitNumber
    * @Description: 将数字以逗号隔开
    * @param @param step 步长
    * @param @param numberStr 数字字符串
    * @param @return
    * @return String 
    * @throws
     */
    public static String splitNumber(int step,String numberStr){
    	String str = "";
    	int count = numberStr.length()/step;
    	if(numberStr.length()%step != 0){
    		count = count + 1;
    	}
		for(int i = 0; i < count; i++){
			int start = numberStr.length() - (i * step + step);
			int end = numberStr.length() - (i * step);
			if(start < 0){
				start = 0;
			}
			str = "," + numberStr.substring(start, end) + str;
		}
		if(str.startsWith(",")){
			str = str.substring(1,str.length());
		}
		return str;
    }
    
    /**
     * 返回空字符串
     *
     * @param str
     * @return
     */
    public static String getEmptyString(Object str) {
        if (str == null) {
            return "";
        }
        return str.toString();
    }
    
    /**
     * 检测是否为空
     * @param str
     * @return
     */
    public static boolean isEmptyString(Object str) {
        if (str == null || str.equals("")) {
            return true;
        }
        else
        {
        	return false;
        }
    }
    
    
    /**
     * 返回对应奖等
     * @param num 0-5 返回一至六等奖
     * @return
     */
    public static String numTransform(int num){
  		switch (num) {
  		case 0:
  			return "一等奖";
  		case 1:
  			return "二等奖";
  		case 2:
  			return "三等奖";
  		case 3:
  			return "四等奖";
  		case 4:
  			return "五等奖";
  		case 5:
  			return "六等奖";
  		default:
  			return "";
  		}
  	}
    /**
	 * 格式化奖等：x等奖x注
	 * @param winNums
	 * @param mark：标记 true代表胆拖和复试 flase代表单式
	 * @return
	 */
	public static String StringTransform(String[] winNums,boolean mark){
		String winNum = "";
		for(int i = 0 ; i < winNums.length ; i++){
			if(!"".equals(winNums[i]) && StringUtils.isNumeric(winNums[i])){
				if(Integer.parseInt(winNums[i]) > 0){
					if(mark){
						winNum += numTransform(i) + winNums[i] + "注,";
					}else{
						winNum = numTransform(i) + winNums[i] + "注";
					}
				}
			}
		}
        if (mark) {
            return ("").equals(winNum) ? "" : winNum.substring(0, winNum.lastIndexOf(","));
        } else {
            return ("").equals(winNum) ? "" : winNum;
        }
    }

	/** 
     * 产生一个随机的字符串 
     *   
     * @param 字符串长度 
     * @return 
     */ 
    public static String getRandomString(int length) {   
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
    }
    
    /**
     * 将字符串的某一部分的字符全部替换成replace
     * @param source 源字符串
     * @param replace	代替的字符串
     * @param begin	开始位置
     * @param length 长度
     * @return
     */
    public static String replaceSubWithDef(String source, String replace, int begin, int length)
    {
    	String head = "";
    	String tail = "";
    	String middle = "";
    	if(begin>0)
    		head = source.substring(0, begin);
    	if((begin+length)<source.length())
    		tail = source.substring(begin+length);
    	for(int i=0;i<length;i++)
    		middle += replace;
    	return head+middle+tail;
    	
    }
    
    /**
     * 把数组转换为字符串
     * @param strArray
     * @param split
     * @return
     */
	public static String convertArrayToString(String[] strArray, String split){
		String strReturn = "";
		if(strArray == null || strArray.length == 0){
			strReturn = "";
		}
		else{
			for(int i=0; i<strArray.length; i++){
				if(i == 0){
					strReturn = strArray[i];
				}
				else{
					strReturn = strReturn + split + strArray[i];
				}
			}
		}
		return strReturn;
    }
}
