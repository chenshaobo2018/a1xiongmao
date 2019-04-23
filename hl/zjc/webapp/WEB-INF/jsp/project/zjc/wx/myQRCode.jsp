<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=no" />
		 <title>引领财富自由，走上人生巅峰！中军创云易平台欢迎您</title>  
		 <link href="../../../static/css/qrcode/share_regit.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
    	<script type="text/javascript" src="../../../static/qrcode/js/jquery-1.11.1.js" ></script>
		<!-- <script type="text/javascript" src="../../../static/css/qrcode/share.css" ></script> -->
		<script type="text/javascript" src="../../../static/qrcode/js/jquery.qrcode.js" ></script>
	    <script type="text/javascript" src="../../../static/qrcode/js/qrcode.js" ></script> 
	    <script type="text/javascript" src="../../../static/qrcode/js/utf.js" ></script>
    	<div class="container_we"> 
	   		<div class="we_main">
			   <div class="we_m_text">			      	
					<img src="../../../static/qrcode/img/share_text.png">
			   </div>
			   <div class="we_img">				
				 <div  id="qrcodeCanvas"></div>
			   </div>
			   <p class="we_text">长按识别图中二维码注册</p>
			   <div class="we_foot">
			     <img src="../../../static/qrcode/img/foot.png"/>		      
			   </div>
		   </div>
		</div>
		<script type="text/javascript">
			  function GetQueryString(name){
				var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
				var r = window.location.search.substr(1).match(reg);
				if(r!=null)return  unescape(r[2]); return null;
			}  
			$(document).ready(function(){	
				var shareUrl='${QrcodeContextUrl}';
				jQuery('#qrcodeCanvas').qrcode({
					render: "canvas",
					ecLevel:'H',
					text: shareUrl,
					width: "150",               //二维码的宽度
					height: "150",              //二维码的高度
					background : "#ffffff",       //二维码的后景色
					foreground : "#dc1200",        //二维码的前景色
					src: '../../../static/qrcode/img/logo.png',             //二维码中间的图片
					radius:0.3
				});    
			});	
		</script>
    </body>
</html>
