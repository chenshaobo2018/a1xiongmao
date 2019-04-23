<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.api.cfca.controller.*"%>
<% 
String path = request.getContextPath();
String v_mid = request.getParameter("v_mid");
String v_oid = request.getParameter("v_oid");
String v_moneytype = request.getParameter("v_moneytype");
String v_ymd = request.getParameter("v_ymd");
String v_amount = request.getParameter("v_amount");
String v_pmode = request.getParameter("v_pmode");
String v_rcvname = request.getParameter("v_rcvname");
String v_rcvpost = request.getParameter("v_rcvpost");
String v_url =request.getParameter("v_url");//"https://zjc1518.com/aosuite/notokenapi/app/v1/PayReceived1.jhtml";
String v_rcvaddr = request.getParameter("v_rcvaddr");
String v_rcvtel = request.getParameter("v_rcvtel");
String v_orderstatus = request.getParameter("v_orderstatus");
String v_ordername = request.getParameter("v_ordername");
//CFCA加密
String sign =request.getParameter("sign");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>B2C支付提交</title>
<script>
   function sub(){
      test1.submit();
    }   
</script>
  </head>
  <body onload="sub();" Style="display:none;">
    <form id="test1" name="test1" method="post" action="https://pay.yizhifubj.com/customer/gb/pay_bank.jsp"> 
       <input type="text" name="v_mid" id="v_mid" value="<%=v_mid%>">
       <input type="text" name="v_oid" id="v_oid" value="<%=v_oid%>">
       <input type="text" name="v_rcvname" id="v_rcvname" value="<%=v_rcvname%>">
       <input type="text" name="v_rcvaddr"id="v_rcvaddr" value="<%=v_rcvaddr%>">
       <input type="text" name="v_rcvtel" id="v_rcvtel" value="<%=v_rcvtel%>">
       <input type="text" name="v_rcvpost" id="v_rcvpost" value="<%=v_rcvpost%>">
       <input type="text" name="v_amount" id="v_amount" value="<%=v_amount%>">
       <input type="text" name="v_pmode" id="v_pmode" value="<%=v_pmode%>">
       <input type="text" name="v_ymd" id="v_ymd" value="<%=v_ymd%>">
       <input type="text" name="v_orderstatus" id="v_orderstatus" value="<%=v_orderstatus%>">
       <input type="text" name="v_ordername" id="v_ordername" value="<%=v_ordername%>">
       <input type="text" name="v_moneytype" id="v_moneytype" value="<%=v_moneytype%>">
       <input type="text" name="v_url" id="v_url" value="<%=v_url%>">
       <input type="text" id="v_md5info" name="v_md5info" value="<%=sign%>">
    </form>
  </body>
</html>  
  
