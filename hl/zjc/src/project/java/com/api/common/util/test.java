package com.api.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.net.*;  
import java.io.*; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class test {    

	public static byte[] callMapToXML(Map map) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        mapToXMLTest2(map, sb);  
        
        sb.append("</xml>");  
        try {  
            return sb.toString().getBytes("UTF-8");  
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
	
	public static void main(String[] args) {
		try {
			URL yahoo = new URL("http://www.baidu.com");  
		    BufferedReader in = new BufferedReader(  
		                new InputStreamReader(  
		                yahoo.openStream()));  
		  
		    String inputLine;  
		  
		    while ((inputLine = in.readLine()) != null)  
		        System.out.println(inputLine);  
		  
		    in.close(); 
		} catch (Exception e) {
			// TODO: handle exception
		}

	
	}
    
}