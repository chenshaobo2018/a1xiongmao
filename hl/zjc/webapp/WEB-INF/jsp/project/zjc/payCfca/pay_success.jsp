<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=yes" />
		 <script src="../../../static/paycfca/js/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="../../../static/paycfca/css/index.css"/> 
		 <title>支付</title>  
	</head>
<body>  
  <div class="pay_succ">
    <div class="pay_img">
	    <p class="center pay_imsrc"><img src="../../../static/paycfca/img/pay_su.png"/></p>
	    <p class="center pay_imsrc">支付成功</p>
	</div>
	<div class="pays_one">
	   <div class="pays_one_one clearfix pay_border">
	     <div class="psyd_side fl">付款方式</div>
	     <div class="psyd_cont fr">快捷支付</div>
	   </div>
	   <div class="pays_one_one clearfix">
	     <div class="psyd_side fl">收款人</div>
	     <div class="psyd_cont fr">中军创云易物联网公司</div>
	   </div>
	</div>
	<div class="pays_two">
	<div class="paytwo_title center">更多惊喜</div>
	<div class="paytwo_content clearfix">
	   <!--  <div class="pays_side fl center">
		<p class="hu_one">获得支付金额</p>
		<p class="hu_twos"><span>16%</span> 的劵</p>
		<p class="hu_tree">已自动到账</p>
		</div> -->
	    <div class="pays_cont center">
		<p class="hu_two">分享注册成功一位好友</p>
		<p class="hu_five">可获得<span>18</span>现金券</p>
		<button class="five_share">分享</button>
	</div>
	</div> 
  </div> 
  <div class="pays_tree">
  
  </div>
  </div>
	<script>
	$(function(){	
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';
	});
	
	function paySuccess(){
	    alert("js://paySuccess");
	}
	</script>
	<button class="psys_ok" onclick="paySuccess()">完成</button>
</body>
</html>