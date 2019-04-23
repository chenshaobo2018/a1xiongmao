<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
	 <meta name="format-detection" content="telephone=yes" />
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/common.css"/>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/sign.css"/>
	<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"></script>
    <script src="../../../static/js/wxactivity/share_power.js" type="text/javascript" charset="utf-8"></script> 
	 <title>转盘抽奖</title> 
</head>
<body>
<div class="sign">  
	<!-- 滚动 -->
	<div class="sharesig_one">
	<div id="share_sign_one">
	  <ul id="share_sign_two">
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		</a></li>
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		 </a></li>
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		 </a></li>
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		 </a></li>
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		 </a></li>
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		 </a></li>
		<li><a href="#" target="_blank">
		 <img src="../../../static/image/wxactivity/peron.png" class="singm"/>伊***尔  已成功为您助力，1小时前
		 </a></li>
	  </ul>
	  <div id="share_sign_tree"></div>
	</div>
	</div>
	<div class="sharesig_two">
	   <a href="sign_prize.html">
	     <img src="../../../static/image/wxactivity/sign/see.png" class="see_jp"/>
	   </a>
	</div>
	<!-- 转盘 -->
		<div class="sign-3">
		<div class="wripe"> 
			<div class="turntable-bg"> 
				<div class="pointer">
				   <div class="poin"><img src="../../../static/image/wxactivity/sign/pointer.png" alt="pointer"/></div>
				</div>
				<div class="rotate" ><img id="rotate" src="../../../static/image/wxactivity/sign/turntable.png" alt="turntable"/></div>
			</div>			
		</div>
	</div>
	<div class="sign_ts center">
	  <!-- <p class="sign_co">可抽次数<span>3</span>次</p> -->
	  <p class="sign_ck">注:抽奖1次需花费68易物券，参加助力活动可获得易物券</p>
	</div> 
	   <!-- 弹出层 -->
		<div class="zhe_makesign clearfix">	
					 <!-- 祝福 -->
		<div class="rtio_one">
				    <div class="zhd_img">
					 <img src="../../../static/image/wxactivity/sign/x.png" class="zjdo_img"/>  
					 </div>		
					 <div class="img_zf">
				        <img id="img_rad" src=""/>
					 </div>
			</div>		 
			 <!-- 其他奖项 --> 
			 <div class="rtio_two">			 
				 <div class="img_order"> 
					 <img id="img_rads" src="" />  
				 </div> 
				  <div class="imgd">
				   <button class="btng">确认领取</button> 
				  </div> 
				  <div class="me_no">
				   <button>我不想要</button> 
				 </div>
			  </div>
			  <!-- 游戏卡 -->
			 <div class="rtio_two_ka">			 
				 <div class="img_order_ka"> 
					 <img id="img_rads_ka" src="" />  
				 </div> 
				  <div class="imgd">
				   <button class="btng_ka">确认领取</button> 
				  </div> 
				  <div class="me_no_ka">
				   <button>我不想要</button> 
				 </div>
			  </div>
		</div>
	<div class="zhe_makesign_mak"></div>  
	<!-- 游戏卡号 -->
	<div class="ka_make">
	 <div class="ka_ones center">
	    <p class="kas_text">获得奇迹游戏礼包</p>
	    <p class="kas_text_one">请确认你的游戏码</p>
	    <p class="kas_text_text">KASHHGDKUS132265</p>
	 </div>
	 <div class="kas_btn">
	   <button>确认领取</button>
	 </div>
	</div>
	<div class="ka_makesd"></div>  
	<!-- 易物劵不够的提示 -->
	<div style="display:none;">
		<div class="yi_makgn">
		   <p class="yid_text center">易物券不足，参加“助力”活动分享好友；可获得易物券</p>
			 <div class="zhe_btn clearfix">
				<button class="zhebtn_one fl">取消</button>
				<button class="zhebtn_two fl">前往领取</button>
			 </div>
		</div>
		<div class="yi_makgn_mak"></div> 
	</div> 
<script type="text/javascript" src="../../../static/js/wxactivity/awardRotate.js"></script>
<script type="text/javascript">
$(function (){    
<!-- 卡号   -->
$(".btng_ka").click(function(){
$(".ka_make,.ka_makesd").show();
$(".rtio_two_ka").hide();
})
   $(".zhd_img").click(function(){
      $(".zhe_makesign,.zhe_makesign_mak").hide(); 
	  $("#img_rads,#img_rad,#img_rads_ka").attr('src','');
   });  
	var rotateTimeOut = function (){
		$('#rotate').rotate({
			angle:0,
			animateTo:2160,
			duration:8000,
			callback:function (){ 
				alert('网络超时，请检查您的网络设置！');
			}
		});
	};
	var bRotate = false;

	var rotateFn = function (awards, angles, txt,type){
		bRotate = !bRotate;
		$('#rotate').stopRotate();
		$('#rotate').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:8000,
			callback:function (){
		       $(".zhe_makesign,.zhe_makesign_mak").show();
				if(type==1){
				<!-- 祝福 -->
					$(".rtio_one").show();  
					$(".rtio_two,.rtio_two_ka").hide();  
					$("#img_rad").attr('src',txt);
				}else if(type==2){
				<!-- 游戏码 -->
					$(".rtio_two_ka").show(); 
					$(".rtio_one,.rtio_two").hide(); 
					$("#img_rads_ka").attr('src',txt);
				}else{
					$(".rtio_two").show();	
					$(".rtio_one,.rtio_two_ka").hide();	 
				    $("#img_rads").attr('src',txt); 
				}
				bRotate = !bRotate;
			}
		})
	};

	 
	$('.pointer').click(function (){
		if(bRotate)return;
		var item = rnd(0,5);	
		switch (item) {
			case 0:		
			 
	  		<!-- 随机图片 start -->
				var aa=["img/sign/1.png","img/sign/2.png","img/sign/3.png","img/sign/4.png","img/sign/5.png","img/sign/6.png","img/sign/7.png"];
				rotateFn(0, 25, aa[(Math.floor(Math.random()*6))],1);
				break;
		<!--38易物劵 -->
		 case 1:   
		         
				var iot1="img/sign/38.png"; 
				rotateFn(1, 90, iot1);
				break;  
		<!-- ka -->
		case 2: 
				
				var iot2="img/sign/ka.png";
				rotateFn(2, 165,iot2,2);
				break; 
		<!-- 500易物劵 -->
		 case 3:		
			     
				var iot3="img/sign/500.png";
				rotateFn(3, 205, iot3); 
				break;
		 <!--再来一次  -->
			 case 4: 
				 
				var iot4="img/sign/over.png";
				rotateFn(4, 265, iot4);
				break; 
		 <!-- iphone -->
			 case 5: 
				 
				var iot5="img/sign/iphone.png";
				rotateFn(5, 305, iot5);
				break; 
		 
		}
		//console.log(item);
	});
});
function rnd(n, m){
	return Math.floor(Math.random()*(m-n+1)+n)
}

<!-- 滚动 -->
	var speed=100; 
	window.onload=function(){
	  var demo=document.getElementById("share_sign_one"); 
	  var demo2=document.getElementById("share_sign_tree"); 
	  var demo1=document.getElementById("share_sign_two"); 
	  demo2.innerHTML=demo1.innerHTML 
	  function Marquee(){ 
		if(demo.scrollTop>=demo1.offsetHeight){
		  demo.scrollTop=0; 
		}
		else{ 
		  demo.scrollTop=demo.scrollTop+1;
		} 
	  } 
	  var MyMar=setInterval(Marquee,speed) 
	  demo.onmouseover=function(){clearInterval(MyMar)} 
	  demo.onmouseout=function(){MyMar=setInterval(Marquee,speed)} 
	}
</script>
</div>  
</body>
</html>