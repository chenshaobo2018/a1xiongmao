<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>电影</title>
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<style>
	*,div,p,ul,li,table,tr,td,a,span,img,label,input,button{margin:0;padding:0;}  
	html,body{height: 100%;width: 100%;margin:0;padding:0;font-size:62.5%;font-family:'Microsoft YaHei','微软雅黑';} 
	li{list-style:none;} 
	.clear{clear:both;} 
	.mob_con img,.mon_img li img{width:100%;}
	.mon_one{color:#212121;font-size:0.5rem;font-weight:bold;}
	.mob_text{width:95%;margin-left:3%;border-bottom:1px solid #eee;margin-top:0.5rem;padding-bottom:0.3rem;}
	.mon_two{font-size:0.35rem;color:#a0a0a0;margin-top:0.15rem;}
	.mob_img{width:95%;margin:0 auto;margin-top:0.4rem;}
	.mon_img li img{height:3rem;}
	.mon_img{margin-top:0.3rem;margin-bottom:0.3rem;}
	.mon_img li{width:47.5%;float:left;margin-bottom:0.2rem;margin-right:5%;background:#000;height:5rem;}
	.mon_img video{height:5rem;}
	.play_btn{background:url(../../../static/image/movie/play.png) no-repeat;width:1rem;height:1rem;margin-top:-1.3rem; margin-right:0.5rem;background-size:100% 100%;border:none;float: right;}
</style>
</head>
<body>
<div class="mov_cont">
    <div class="mob_con">
		<a href="#"><img src="../../../static/image/movie/head_mon.png"></a>
	</div>	
	<div class="mob_text">
	   <p class="mon_one">逢凶化吉之前咖啡馆</p>
	   <p class="mon_two">315爆笑上映 中军创云易出品</p>
	   <button class="play_btn" onclick="play_btn_click();"></button>
	</div>
	<div class="mob_img">
	   <p class="mon_one">名人介绍</p>
	   <ul class="mon_img">
		 <li><a href="#"> 
		   <video controls="" autoplay="" name="media" width="100%">
			  <source src="http://video.web.zjc1518.cn/c56b0a56cb384457b9729ef8ad2cecfc/c7ae2119f2854de1920f16e378407a9c-292b097d02ca57d92e5c20de3095ab22-sd.mp4" type="video/mp4">
		   </video>
		 </a></li>
		 <li><a href="#"> 		   
		   <video controls="" autoplay="" name="media" width="100%">
			  <source src="http://video.web.zjc1518.cn/3ff3c9db669f485889f48e89b95d750e/773586422e68457c97eb99ac08b955d4-6d807b876d2039671ee3e19176086b89-sd.mp4" type="video/mp4">
		   </video>
		   </a></li>
		 <li><a href="#"> 		   
		   <video controls="" autoplay="" name="media" width="100%">
			  <source src="http://video.web.zjc1518.cn/ba467b0422c140f69560f47459cb8fc3/9ac1c821bd4c40fb80f4fd7a101cbf9d-bb5d8b01970e65816a5ea180da835bd4-sd.mp4" type="video/mp4">
		   </video>
		   </a></li>
	     <li><a href="#"> 		  
		   <video controls="" autoplay="" name="media" width="100%">
			  <source src="http://video.web.zjc1518.cn/c460df9b0a494d12919a0c6ab00e9885/9f6fd7b72da6416f91b35b3dc3e19b96-529e6e178af52f869d772b07a045f5c2-sd.mp4" type="video/mp4">
		   </video>
		 </a></li> 
		 <li><a href="#"> 
		   <video controls="" autoplay="" name="media" width="100%">
			  <source src="http://video.web.zjc1518.cn/ffdc1609159c428c8c827f44ff61d499/0c50b24004ed42f4b589e9046ebb1e13-62f14797b796c88abb4e6c434eb669e6-sd.mp4" type="video/mp4">
		   </video> 
		   </a></li>
		 <li><a href="#"> 
		 	<video controls="" autoplay="" name="media" width="100%">
			  <source src="http://video.web.zjc1518.cn/5f05e7d41a6243d0bddf3859fb9175aa/bd76e1436877445faf92e6a286081ba5-9e1beee870027fd6990f490ec456ab2e-sd.mp4" type="video/mp4">
		   </video> 
		 </a></li>
		 <div class="clear"></div>
	   </ul> 
	</div>   
  </div>
 <script>
    $(function(){
	 $(".mon_img li:odd").css("margin-right","0");
		var deviceWidth = document.documentElement.clientWidth; 
	if(deviceWidth > 1080) deviceWidth = 1080; 
	document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px'; })
	
	function play_btn_click(){
    	window.location.href = "http://m.iqiyi.com/v_19rrbg3hho.html?dummy=&weixin_platform=friend&share=MSG&from=singlemessage";
    }
 </script>
</body>
</html>