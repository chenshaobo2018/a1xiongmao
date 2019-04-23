package com.api.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String doPost(String url,Map<String,String> map,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url); 
            //httpPost.setHeader("Content-Encoding", "GBK");
            //httpPost.setHeader("Content-Type", "application/json; charset=gbk");  
         
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity); 
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
                resEntity.consumeContent();
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    } 
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String doPost(String url,String value,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url); 
            //httpPost.setHeader("Content-Encoding", "GBK");
            //httpPost.setHeader("Content-Type", "application/json; charset=gbk");  
         
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity); 
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
                resEntity.consumeContent();
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    } 
    
    /** 
     * post请求 
     * @param url         url地址 
     * @return 
     */  
    public static String httpPost(String url){  
        //post请求返回结果  
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpPost method = new HttpPost(url);  
        String str = "";  
        try {  
            HttpResponse result = httpClient.execute(method);  
            url = URLDecoder.decode(url, "UTF-8");  
            /**请求发送成功，并得到响应**/  
            if (result.getStatusLine().getStatusCode() == 200) {  
                try {  
                    /**读取服务器返回过来的json字符串数据**/  
                    str = EntityUtils.toString(result.getEntity());  
                } catch (Exception e) {  
                }  
            }  
        } catch (IOException e) {  
        }  
        return str;  
    }  
   
   
    /** 
     * 发送get请求 
     * @param url    路径 
     * @return 
     */  
    public static String httpGet(String url){  
        //get请求返回结果  
         String strResult = null;  
        try {  
            DefaultHttpClient client = new DefaultHttpClient();  
            //发送get请求  
            HttpGet request = new HttpGet(url);  
            HttpResponse response = client.execute(request);  
   
            /**请求发送成功，并得到响应**/  
            if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {  
                /**读取服务器返回过来的json字符串数据**/  
                  strResult = EntityUtils.toString(response.getEntity());  
            } else {  
            }  
        } catch (IOException e) {  
        }  
        return strResult;  
    } 
    
    /**
     * 发送带json数据的post请求
     * 
     * @param json
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String httpReqUrl(String json, String url) throws ClientProtocolException, IOException {  
  
        DefaultHttpClient httpClient = new DefaultHttpClient();  
  
        HttpPost method = new HttpPost(url);  
        StringEntity entity = new StringEntity(json,"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");    
        method.setEntity(entity);  
        HttpResponse result = httpClient.execute(method);  
        // 请求结束，返回结果  
        String resData = EntityUtils.toString(result.getEntity());
		return resData;  
    }  
}  