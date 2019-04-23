package com.zjc.common.util;
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
  
public class QiniuUtil {  
	
	 //七牛域名
	 private static String OUT_URL = "http://omhpz0ifi.bkt.clouddn.com";
	 
     
     //设置好账号的ACCESS_KEY和SECRET_KEY  
     private static String ACCESS_KEY = "WJdWFlPbSjmekkhW47M-DxCyBZgM3FuWGTsM_1zS"; //这两个登录七牛 账号里面可以找到  
     private static String SECRET_KEY = "NfrSTSWqng80tul0YnnQ6BfrrCsxtSKLhVQElkua";  
 
     //要上传的空间  
     private static String bucketname = "zjc1518"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）  
      //上传到七牛后保存的文件名  
      //String key = "MinImg.png";    
      //上传文件的路径  
     // String FilePath = "C:\\Users\\Administrator\\Desktop\\tp\\1.jpg";  //本地要上传文件路径  
     
      //密钥配置  
      private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);  
      //创建上传对象  
      private static UploadManager uploadManager = new UploadManager();  
  
      //简单上传，使用默认策略，只需要设置上传的空间名就可以了  
      public static String getUpToken(){  
          return auth.uploadToken(bucketname);  
      }  
      //普通上传  
      public static String upload(String FilePath,String key) throws IOException{ 
	    String outUrl = null;
        try {  
          //调用put方法上传  
          Response res = uploadManager.put(FilePath, key, getUpToken());  
          //打印返回的信息  
          outUrl = OUT_URL + "/" + key;
          System.out.println(res.bodyString());  
          } catch (QiniuException e) {  
              Response r = e.response;  
              // 请求失败时打印的异常的信息  
              System.out.println(r.toString());  
              try {  
                  //响应的文本信息  
                System.out.println(r.bodyString());  
              } catch (QiniuException e1) {  
                  //ignore  
              }  
          }  
        return outUrl;
      }  
}  