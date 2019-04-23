<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.api.cfca.controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=yes" />
		 <script src="../../../static/paycfca/js/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="../../../static/paycfca/css/bank_index.css"/> 
		 <title>市场订单快捷支付</title>  
	</head>
<body> 
<div class="bank_bind">
  <div class="bb_one"> 
  市场订单快捷支付
  </div>
	<div class="bb_fix">	   
    <div class="bbt_one clearfix" Style="display:none;">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>订单号</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="OrderNo" name="OrderNo" placeholder="必填" value="${orderNo}"/>
	   </div>
	</div> 	   
    <div class="bbt_one clearfix" Style="display:none;">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>支付流水号</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" name="PaymentNo" id="PaymentNo" placeholder="必填" value="${paymentNo}"/>
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>短信验证码</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="SMSValidationCode" name="SMSValidationCode" value="123456"/>
	   </div>
	</div> 
	<button class="btn_ok_pay" onclick="tijiao()">提交</button>
	</div> 
	<!-- 验证提示 -->
	<div class="error">
	      请输入手机号！
	</div>
  </div> 
</div>
	<script>
	$(function(){	
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px'; 
	});
	
	function tijiao(){
		var OrderNo=$("#OrderNo").val();
		var PaymentNo=$("#PaymentNo").val();
		var SMSValidationCode=$("#SMSValidationCode").val();
		window.location.href="../../app/v1/Pay1376.jhtml?OrderNo="+OrderNo+"&PaymentNo="+PaymentNo+"&SMSValidationCode="+SMSValidationCode;
	}
	 
	</script>
</body>
</html>