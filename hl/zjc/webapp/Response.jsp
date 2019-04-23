<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@ page import="cpcn.institution.tools.util.XmlUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟商户</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
</head>
<body>

<p class="title">模拟商户</p>
<%
String prettyPlainText = XmlUtil.createPrettyFormat(XmlUtil.createDocument((String)request.getAttribute("plainText")));
String txCode = (String)request.getAttribute("txCode");
String status = (String)request.getAttribute("status");
String imageUrl = (String)request.getAttribute("imageUrl");
String content = (String) request.getAttribute("content");
%>
<form action="DownLoad.jsp" name="form" method="post">
<input type="hidden" name="content" value="<%=content%>">
<input type="hidden" name="txCode" value="<%=txCode%>">
</form>
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">${txName}(${txCode})</td>
    </tr>
    <tr class="mouseout">
        <td width="100" class="label" height="400">响应报文</td>
        <td width="*" class="content">            
            <textarea name="RequestPlainText" cols="120" rows="20" wrap="off"><%=prettyPlainText %></textarea>
        </td>
    </tr>
</table>
<div id="weChat" style="display:none">
      <br />
      <font color="#FF0000" size="3"><b>点击下面按钮跳转到二维码页面：</b></font><br />  
      <br />     
      <input type="button"  style="width: 83px" value="二维码页面" onclick="javascript:window.location.href='<%=imageUrl%>'"></input>
</div>
<div id="file" style="display:none">
      <br />
      <font color="#FF0000" size="3"><b>下载：</b></font><br />  
      <br />     
      <input type="button"  style="width: 83px" value="下载" onclick="down()"></input>
</div>



</body>
</html>

<script language="JavaScript" type="text/JavaScript">
    if(((<%=txCode%> == "2811") || (<%=txCode%> == "1401")) && (<%=status%> == "10")) {
    document.getElementById('weChat').style.display="";
    }
    if(<%=txCode%> == "2903"||<%=txCode%> == "2904"||<%=txCode%> == "2911") {
        document.getElementById('file').style.display="";
    }
    function down(){
    	document.form.submit();
    }

</script>

