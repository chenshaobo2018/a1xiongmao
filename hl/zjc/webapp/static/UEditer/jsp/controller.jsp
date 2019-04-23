<%@page import="java.util.HashMap"%>
<%@ page import="com.zjc.common.util.QiniuUtil" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter,aos.framework.core.utils.AOSJson,java.util.Map,java.lang.Exception"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	String fileContent = new ActionEnter( request, rootPath ).exec();
	
	out.write( fileContent );
	
	System.out.println(fileContent);
	Map<String,Object> map  = new HashMap<String,Object>();
	map = AOSJson.fromJson(fileContent, HashMap.class);
	System.out.println(map.get("state"));
	if(map.get("state") != null ){
		if(map.get("state").equals("SUCCESS")){
		    String filepath = rootPath + map.get("url");
			String key = "goodsimg/" +  map.get("title").toString();
		    String outrl = QiniuUtil.upload(filepath, key);
		    System.out.println(outrl);
		}
	}
%>