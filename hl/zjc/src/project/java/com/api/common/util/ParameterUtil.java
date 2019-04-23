package com.api.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import aos.framework.core.utils.AOSJson;

import com.api.common.po.PageVO;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

/**
 * 参数生成工具类
 * 
 * @author Administrator
 *
 */
public class ParameterUtil {
	
	/**
	 * 生成6位随机数
	 * 
	 * @return result
	 */
	public static String getRandom(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++){
			result = result * 10 + array[i];
		}
		return result + "";
	}
	
	 /**
	  * 获取客户端IP
	  * 
	 * @param request
	 * @return ip
	 */
	public static String getIp(HttpServletRequest request) {
           String ip = request.getHeader("X-Forwarded-For");
           if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
              //多次反向代理后会有多个ip值，第一个ip才是真实ip
              int index = ip.indexOf(",");
              if(index != -1){
            	  return ip.substring(0,index);
	          }else{
	              return ip;
	          }
           }
            ip = request.getHeader("X-Real-IP");
          if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
              return ip;
           }
           return request.getRemoteAddr();
    }
	
	 /**
	  * 获取客户端IP
	  * 
	 * @param request
	 * @return ip
	 */
	 public static String getIpAddr(HttpServletRequest request) {
		  String ip = request.getHeader("X-Forwarded-For");  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_FORWARDED");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_VIA");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("REMOTE_ADDR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        return ip;  
		 }
	 
   /**
	 * 把当前系统时间的long型时间转换为10位数的int型数据
	 * 
	 * @param dateLong
	 * @return dateInt
	 */
	public static int dateToInt(){
	   //获取当前时间的时间戳
	   long datelong = new Date().getTime()/1000;
	   //转换long型时间为int
	   int dateInt = new Long(datelong).intValue();
	   return dateInt;
    }
	
	/**
	 * 返回总页数
	 * 
	 * @param totalSize 总记录数
	 * @return
	 */
	public static int getTotalPage(int totalSize){
		int totalPage = 0;
		if(totalSize % ConstantUtil.pageSize==0){//判断总记录数能否被pageSize整除：能
			totalPage = totalSize/ConstantUtil.pageSize;
		} else {//不能整除，总页数+1
			totalPage = totalSize/ConstantUtil.pageSize + 1;
		}
		return totalPage;
	}
	
	/**
	 * 判断是否有下一页
	 * 
	 * @param totalPage 总页数
	 * @param page 当前页
	 * @return boolean 
	 */
	public static boolean isNextPage(int totalPage,int page){
		boolean b = true;
		if(page < totalPage){//有下一页
			b = true;
		} else {//没有下一页
			b = false;
		}
		return b;
	}
	
	/**
	 * 生成分页参数返回实体
	 * 
	 * @param totalSize 总记录数
	 * @param page 当前页数
	 * @return PageVO
	 */
	public static PageVO getPageVO(int totalSize,int page){
		PageVO pageVO = new PageVO();
		//获取总页数
		int totalPage = getTotalPage(totalSize);
		pageVO.setNowPage(page);
		pageVO.setTotalPage(totalPage);
		//设置是否有下页
		pageVO.setHasNextPage(isNextPage(totalPage,page));
		return pageVO;
	}
	
	/**
	 * 是否过期
	 * 
	 * @param date 传入时间
	 * @param validate_time 有效期
	 * @return b
	 */
	public static boolean IsExpire(Date date,int validate_time){
	   boolean b = true;
	   //获取传入时间的时间戳
	   long addTimeLong = date.getTime()/1000;
	   //转换long型时间为int
	   int addTimeInt = new Long(addTimeLong).intValue();
	   if(dateToInt()-addTimeInt>validate_time){//失效
		   b = false;
	   }
	   return b;
	}
	
	/**
	 * 生成html
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	public static String createHtmlBody(String title, String content) {
        return "<html>" +
                "<head>" +
                "<meta http-equiv=Content-Type content='text/html;charset=utf-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, minimal-ui'> " +
                "<title>" + title + "</title>" +
                "<style>img{width:100%!important;margin-top:5px}" +
                "@media(max-width:768px){div,p,span{font-size:20px!important}}" +
                "@media(max-width:480px){div,p,span{font-size:18px!important}}" +
                "@media(max-width:360px){div,p,span{font-size:16px!important}}" +
                "@media(max-width:320px){div,p,span{font-size:14px!important}}" +
                "div,p,span{color:#6b6b6b;font-family:'Microsoft YaHei','微软雅黑';line-height: 1.5em;}" +
                "</style>" +
                "</head>" +
                "<body> " + StringEscapeUtils.unescapeHtml(content) +
                "</body> " +
                "</html>";
    }

	
	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static String getOrderSn(){
		java.util.Random random=new java.util.Random();// 定义随机类
        int results=random.nextInt(9000)+1000;// 返回[1000,9999)集合中的整数，注意不包括10
        //生成编号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String a=sdf.format(new Date());
        StringBuffer sb = new StringBuffer();
        sb.append(a);
        sb.append(results);
        return sb.toString();
	}
	
	/**
     * 查询中心点内接圆正方形四个角的经纬度
     * 
     * @param longitude    经度
     * @param latitude    纬度
     * @param distance    范围（米）
     * @return
     */
    public static Map<String, double[]> returnLLSquarePoint(double longitude,  double latitude, double distance) {
        Map<String, double[]> squareMap = new HashMap<String, double[]>();
        // 计算经度弧度,从弧度转换为角度
        double dLongitude = 2 * (Math.asin(Math.sin(distance
                / (2 * ConstantUtil.EARTH_RADIUS))
                / Math.cos(Math.toRadians(latitude))));
        dLongitude = Math.toDegrees(dLongitude);
        // 计算纬度角度
        double dLatitude = distance / ConstantUtil.EARTH_RADIUS;
        dLatitude = Math.toDegrees(dLatitude);
        // 正方形
        double[] leftTopPoint = { latitude + dLatitude, longitude - dLongitude };
        double[] rightTopPoint = { latitude + dLatitude, longitude + dLongitude };
        double[] leftBottomPoint = { latitude - dLatitude,
                longitude - dLongitude };
        double[] rightBottomPoint = { latitude - dLatitude,
                longitude + dLongitude };
        squareMap.put("leftTopPoint", leftTopPoint);
        squareMap.put("rightTopPoint", rightTopPoint);
        squareMap.put("leftBottomPoint", leftBottomPoint);
        squareMap.put("rightBottomPoint", rightBottomPoint);
        return squareMap;
    }
    
    /**
     * 个推消息发送
     * 
     * @param clientid
     * @param src_client
     * @param msg
     */
    public static String tuisongToSingle(String clientid,int src_client,String msg){
    	 String appId = "wjyzQZnTYP9GmxAkGm3gb8";
	     String appKey = "fpwW5rTIc97SVA0cub7od9";
	     String masterSecret = "UwiU76WSVe6qCHUkmjFuh8";
         String host = "http://sdk.open.api.igexin.com/apiex.htm";
    	 IGtPush push = new IGtPush(host, appKey, masterSecret);
    	 TransmissionTemplate template = transmissionTemplateDemo(appId,appKey,msg,src_client);
         SingleMessage message = new SingleMessage();
         message.setOffline(true);
         message.setSync(true);
         
         // 离线有效时间，单位为毫秒，可选
         message.setOfflineExpireTime(24 * 3600 * 1000);
         message.setData(template);
         // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
         message.setPushNetWorkType(0);
         Target target = new Target();
         target.setAppId(appId);
         target.setClientId(clientid);
         //target.setAlias(Alias);
         IPushResult ret = null;
         try {
             ret = push.pushMessageToSingle(message, target);
         } catch (RequestException e) {
             e.printStackTrace();
             //ret = push.pushMessageToSingle(message, target, e.getRequestId());
         }
         if (ret != null) {
             System.out.println(ret.getResponse().toString());
             return ret.getResponse().toString();
         } else {
             System.out.println("服务器响应异常");
             return "服务器响应异常";
         }
    }
    
    /**
     * 封装推送消息模板
     * 
     * @param appId
     * @param appKey
     * @param msg
     * @return
     */
    public static TransmissionTemplate transmissionTemplateDemo(String appId,String appKey,String msg,int src_client) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(msg);
        if(src_client == 2){//ios
        	APNPayload payload = new APNPayload();
    	    //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
    	    payload.setAutoBadge("+1");
    	    payload.setContentAvailable(1);
    	    payload.setSound("default");
    	    payload.setCategory("$由客户端定义");

    	    //简单模式APNPayload.SimpleMsg
    	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msg));

    	    //字典模式使用APNPayload.DictionaryAlertMsg
    	    //payload.setAlertMsg(getDictionaryAlertMsg());

    	    // 添加多媒体资源
    	  /*  payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
    	                .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
    	                .setOnlyWifi(true));*/

    	    template.setAPNInfo(payload);
        }
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
    /**
     * 获取推送消息内容
     * 
     * @param type
     * @param title
     * @param msg
     * @return
     */
    public static String getTuisongMsg(int type,String typeValue, String title,String msg){
    	 Map<String,Object> map = new HashMap<String,Object>();
         map.put("type", type);
         map.put("typeValue", typeValue);
         map.put("title", title);
         map.put("msg", msg);
         return AOSJson.toJson(map);
    }
    
    /**
     * 过滤特殊符号表情
     * 
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("*");
            return source;
        }
        return source;
    }
    
}
