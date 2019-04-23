<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Error Report</title>
<style type="text/css">
A	      {color: #FF0000; text-decoration: none;}
A:Visited {color: #FF0000; text-decoration: none;}
A:Active  {color: #004567; text-decoration: none;}
A:Hover   {color: #0000FF; text-decoration: none;}
p {
    align: center;
    background-color: #FFFFCC;
    font-size: 9pt;
    line-height: 20pt;
    border: 1px solid black;
    margin: 10px 10px 10px 10px;
    padding: 6px 12px 6px 12px; /* 上、右、下、左*/
}
</style>
</head>
<body bgcolor="#ffffff">
<br>
<br>
<p>
报告：<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="4" color="#FF0000">${errorMessage}</font><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;受理交易请求出现问题。
<br><br>
</p>
</body>
</html>



