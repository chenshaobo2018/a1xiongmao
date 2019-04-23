<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String ctx=request.getContextPath();%> 
<!DOCTYPE html>
<html>
<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=yes" />
		 <script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script> 
		 <script src="../../../static/js/wxstore/clipboard.min.js" type="text/javascript" charset="utf-8"></script> 
		 <title>微信支付</title>  
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
			.wetcat_one{margin-left:3%;margin-right:3%;margin-top:1rem;}
			.set_text{font-size:0.46rem;color:#414141;}
			.set_text_one{font-size:0.4rem;color:#636363;margin-top:0.2rem;}
			.wet_btn{background:#db1200;border:1px solid #db1200;color:#fff;font-size:0.45rem;width:100%;padding:0.2rem 0;border-radius:0.1rem;margin-top:0.3rem;margin-bottom:1rem;}
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
   <p class="pa_cae center">请使用微信扫描二维码</p>
	<p class="cae_img"><img src="https://zjc1518.com/aosuite/static/image/weixinpay.jpg"></p>
</div>
<div class="wetcat_one">
  <p class="set_text">只用一个手机也能支付</p>
  <p class="set_text_one">点击分享二维码,将二维码分享至微信好友-文件传输助手,停留在微信页面,打开分享图片,长按图片,选择识别图中二维码,即可进行支付</p>
  <button class="wet_btn">分享二维码</button>
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