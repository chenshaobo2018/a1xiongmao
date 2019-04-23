/**
 * 
 */
package com.api.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 *
 */
public final class GenerateXml {
	
	public static String callMapToXML(Map map) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        mapToXMLTest2(map, sb);  
        sb.append("</xml>");  
        try {  
            return sb.toString();  
        } catch (Exception e) {  
        	e.printStackTrace();
        }  
        return null;  
    }  
  
    private static void mapToXMLTest2(Map map, StringBuffer sb) {  
        Set set = map.keySet();  
        for (Iterator it = set.iterator(); it.hasNext();) {  
            String key = (String) it.next();  
            Object value = map.get(key);  
            if (null == value)  
                value = "";  
            if (value.getClass().getName().equals("java.util.ArrayList")) {  
                ArrayList list = (ArrayList) map.get(key);  
                sb.append("<" + key + ">");  
                for (int i = 0; i < list.size(); i++) {  
                    HashMap hm = (HashMap) list.get(i);  
                    mapToXMLTest2(hm, sb);  
                }  
                sb.append("</" + key + ">");  
  
            } else {  
                if (value instanceof HashMap) {  
                    sb.append("<" + key + ">");  
                    mapToXMLTest2((HashMap) value, sb);  
                    sb.append("</" + key + ">");  
                } else {  
                    sb.append("<" + key + ">" + value + "</" + key + ">");  
                }  
  
            }  
  
        }  
    }  
    
    private static void change(char c) {
        //如果输入的是大写，+32即可得到小写
        if(c>='A' && c<='Z'){
            c+=32;
            System.out.println("您输入的大写字母"+(char)(c-32)+"被转换成了"+c);
        }else if(c>='a' && c<='z'){    //如果输入的是小写，-32即可得大小写
            c-=32;
            System.out.println("您输入的小写字母"+(char)(c+32)+"被转换成了"+c);
        }else{
            System.out.println("输入的字符有误！！");
        }
    }
}
