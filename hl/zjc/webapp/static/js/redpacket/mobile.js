// font js
$(function(){ 
	var deviceWidth = document.documentElement.clientWidth; 
	if(deviceWidth > 1080) deviceWidth = 1080; 
	document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';  
 // 电话号码
 
 // <!-- 滚动 -->
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
	  //$(".phons")[].text($(".phons").text().substring(0, 3) + "****" + $(".phons").text().substring(7, 11)); 
	  var MyMar=setInterval(Marquee,speed) 
	  demo.onmouseover=function(){clearInterval(MyMar)} 
	  demo.onmouseout=function(){MyMar=setInterval(Marquee,speed)} 
	  $("#share_sign_two .phons").each(function(){
		  $(this).text($(this).text().substring(0, 3) + "****" + $(this).text().substring(7, 11));
		 }); 
	}
 
 
 
 

$(".ebd_p").click(function(){
	$(".zhe_tx,.zhe_tx_make").show();
})
$(".zhe_tx_make").click(function(){
	$(".zhe_tx,.zhe_tx_make").hide();
})
$(".reden_cont").click(function(){
	$(".zhe_gz,.zhe_gz_make").show();
})
$(".zhe_gz_make").click(function(){
	$(".zhe_gz,.zhe_gz_make").hide();
})
// 登录
/*$(".red_btn_one").click(function(){
	$(".btn_o,.btn_omake").show();
})
*/
$(".btnd").click(function(){
	$(".btn_o,.btn_omake").hide();
})
 
 
 
 
 
 
 
 
 
 
 
 
 
 
});

 






