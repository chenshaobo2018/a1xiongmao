    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
        <%@ page import="com.baidu.ueditor.um.Uploader" %>
       	<%@ page import="com.zjc.common.util.QiniuUtil" %> 
     	<%@ page import="java.io.File" %> 
            <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    up.setSavePath("upload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB
    up.upload();
	//上传至qiniu云
    String path = up.getUrl();
    String servletPath = request.getServletPath();
    String realPath = request.getSession().getServletContext().getRealPath(servletPath);
    String FilePath =  new File(realPath).getParent() + "/" + path;
    System.out.println(FilePath);
    String key = up.getFileName();
    String outrl = QiniuUtil.upload(FilePath, key);
    
    String callback = request.getParameter("callback");

    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ outrl +"\"}";

    result = result.replaceAll( "\\\\", "\\\\" );
   
    if( callback == null ){
        response.getWriter().print( result );
    }else{
        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
    }
    %>
