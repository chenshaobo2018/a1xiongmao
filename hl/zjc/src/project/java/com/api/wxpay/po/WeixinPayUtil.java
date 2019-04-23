/**
 * 
 */
package com.api.wxpay.po;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSUtils;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class WeixinPayUtil {
	
    /** 
     * 取出一个指定长度大小的随机正整数. 
     *  
     * @param length 
     *         int 设定所取出随机数的长度。length小于11 
     * @return int 返回生成的随机数。 
     */  
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }  
    
    /**
     * 生成随机字符串
     *
     * @return
     */
    public static String genNonceStr() {
        Random random = new Random();
        return AOSCodec.md5(String.valueOf(random.nextInt(10000)));
    }
    
    /**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static SortedMap<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		SortedMap<String, Object> sortMap = new TreeMap<String, Object>(
				new Comparator<String>() {
					public int compare(String str1, String str2) {
						return str1.compareTo(str2);
					}
                    });
		sortMap.putAll(map);
		return sortMap;
	}
	/** 
     * @author 
     * @date 
     * @Description：sign签名 
     * @param characterEncoding 
     *            编码格式 
     * @param parameters 
     *            请求参数 
     * @return 
     */  
    @SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding, SortedMap<String, Object> packageParams, String API_KEY) {  
    	packageParams = sortMapByKey(packageParams);
    	StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            Object v = (Object) entry.getValue();  
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + API_KEY);  
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
        return sign;  
    }  

    /**
     * 将bean转化为map
     * @param obj
     * @return
     */
    public static SortedMap<String, Object> transBean2Map(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        SortedMap<String, Object> map = new TreeMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);
                    if(AOSUtils.isNotEmpty(value)){
                    	 map.put(key,value);
                    }
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  
    /** 
     * @author 
     * @date 
     * @Description：将请求参数转换为xml格式的string 
     * @param parameters 
     *            请求参数 
     * @return 
     */  
    @SuppressWarnings("rawtypes")
	public static String getRequestXml(SortedMap<String, Object> parameters) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = parameters.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            Object v = (Object) entry.getValue();  
            if ("detail".equalsIgnoreCase(k)) {  
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");  
            } else {  
                sb.append("<" + k + ">" + v + "</" + k + ">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }  

    /**
	 * post请求
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String httpPost(String url,String jsonParam){
        //post请求返回结果
	 	String str = "";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                /**读取服务器返回过来的json字符串数据**/
                str = EntityUtils.toString(result.getEntity(),"UTF-8");
            }
        } catch (IOException e) {
        	System.out.println("post请求提交失败:" + url);
        }
        return str;
    }
	
	/** 
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 
     * @return boolean 
     */  
    @SuppressWarnings("rawtypes")
	public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {  
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            String v = (String)entry.getValue();  
            if(!"sign".equals(k) && null != v && !"".equals(v)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
          
        sb.append("key=" + API_KEY);  
          
        //算出摘要  
        String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();  
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();  
        return tenpaySign.equals(mysign);  
    }  
}
