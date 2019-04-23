<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String ctx=request.getContextPath();%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
	<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script> 
    <script src="../../../static/js/wxstore/clipboard.min.js" type="text/javascript" charset="utf-8"></script> 
	<title>支付宝支付</title>
		<style>
			*,div,p,ul,li,table,tr,td,a,span,img,label,input,button{margin:0;padding:0;}  
			html,body{height: 100%;width: 100%;margin:0;padding:0;font-size:62.5%;font-family:'Microsoft YaHei','微软雅黑';}
			a{text-decoration: none;color: #333;}
			ul li{list-style: none;}
			.fl{float:left;}
			.fr{float:right;}
			/* clear */
			.clearfix:before,.clearfix:after {display: table;content: " ";}
			.clearfix:after {clear: both;}
			.clearfix{*zoom: 1;}
			.center{text-align:center;}
		    body{background:#efefef;}
			.pays_one{padding-top:0.2rem;}
			.payso_side{width:80%;}
			.payso_side p{color:#666;font-size:0.4rem;margin-top:0.1rem;padding-left:3%;}
			.payso_cont{padding-right:3%;width:15%;}
			.pays_copy{background:#efefef;width:100%;border:1px solid #db1200;color:#db1200;font-size:0.45rem;padding:0.1rem;border-radius:0.1rem;}
			.pays_twotext{color:#db1200;font-size:0.38rem;margin:0.8rem 0 0.1rem 0;padding-left:3%;}
			.pa_cae{font-weight:bold;font-size:0.48rem;margin-bottom:0.4rem;}
			.pays_treediv{width:60%;margin:0 auto;margin-top:0.5rem;}
			.cae_img img{width:100%;}
		</style>	
</head>
<body> 

<div class="pays_one clearfix">
   <div class="payso_side fl">
     <p>订单编号：<span>${order_sn }</span></p>
   
   </div>
   <div class="payso_cont fr">
      <button class="pays_copy" data-clipboard-text="${order_sn }">复 制</button>
   </div>
  
</div>
<p class="pays_twotext">注：请务必讲订单编号添加到支付页面的备注中 </p>
<div class="pays_treediv">
   <p class="pa_cae center">请使用支付宝扫描二维码</p>
	<p class="cae_img"><img src="https://zjc1518.com/aosuite/static/image/alipay.jpg"></p>
</div> 
	<script>
		$(function(){ 
			var deviceWidth = document.documentElement.clientWidth; 
			if(deviceWidth > 1080) deviceWidth = 1080; 
			document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';
		});
		
		var clipboard = new ClipboardJS('.pays_copy');
	    clipboard.on('success', function(e) {
	    	alert("复制成功！")  
	    });
	    clipboard.on('error', function(e) {
	    	 alert("复制失败！请手动复制")  
	    });
	</script>
</body>
</html>
