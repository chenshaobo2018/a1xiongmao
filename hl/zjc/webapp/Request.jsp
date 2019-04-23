<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@page import="cpcn.institution.tools.util.XmlUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟商户</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
<script language="JavaScript" type="text/JavaScript">

function doSubmit() {
    document.form1.submit();
}

</script>
</head>
<body>
<p class="title">模拟商户</p>
<%
String prettyPlainText = XmlUtil.createPrettyFormat(XmlUtil.createDocument((String)request.getAttribute("plainText")));
System.out.println(prettyPlainText);
%>
<form action="${action}" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">${txName}(${txCode})</td>
    </tr>
    <tr class="mouseout">
        <td width="100" class="label" height="400">请求报文</td>
        <td width="*" class="content">            
            <textarea name="RequestPlainText" cols="120" rows="20" wrap="off"><%=prettyPlainText%></textarea>
        </td>
    </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td height="10" colspan="2"/>
    </tr>
    <tr>
        <td width="200" height="28"/>
        <td width="*" >
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'" onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
        </td>
    </tr>
</table>

<input type="hidden" name="message" value="${message}" />
<input type="hidden" name="signature" value="${signature}" />
<input type="hidden" name="txCode" value="${txCode}" />
<input type="hidden" name="txName" value="${txName}" />
<input type="hidden" name="Flag" value="${Flag}" />
</form>

</body>
</html>