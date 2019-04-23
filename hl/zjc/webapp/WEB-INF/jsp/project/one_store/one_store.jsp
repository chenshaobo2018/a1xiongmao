<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
	 <meta name="format-detection" content="telephone=yes" />
       <script src="../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
       <script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		<script type="text/javascript" src="../static/js/sha1.js"></script>
		<script type="text/javascript" src="../static/js/one_store/wxconfig.js"></script>
	 <title>一号店</title>   
		<style>
			*,div,p,ul,li,table,tr,td,a,span,img,label,input,button{margin:0;padding:0;}a{text-decoration:none;}html,body{height:100%;width:100%;margin:0;padding:0; font-family:'Microsoft YaHei','微软雅黑';font-size:62.5%;}  
			.right{text-align:right;}.left{text-align:left;}.fl{float:left;}.fr{float:right;}.clear{clear:both;}.clearfix:before,.clearfix:after {display: table;content:" ";}.clearfix:after {clear: both;}.clearfix{*zoom: 1;}.center{text-align:center;} 
			.act_container{background:url(../static/image/one_store/act_bg_copy.png) no-repeat; width:100%;min-height:100%;background-size:100% 100%;}
			.act_one img,.act_two img,.act_tree img,.at_text img,.fixed_con img,.act_tit img{width:100%;display:block;}
			.act_one{width:45%;margin:0 auto;padding-top:0.9rem;}
			.act_wei{width:35%;margin:0 auto;}
			.act_two{width:90%;margin:0 auto;margin-bottom:0.3rem;}
			.act_tree{margin-top:1rem;padding-bottom:0.4rem;}
			.at_text{width:70%;margin:0 auto;margin-top:0.4rem;} 
			.act_tit{width:35%;margin:0 auto;padding-top:1.87rem;padding-bottom:0.4rem;}
			.fixed_con{position:fixed;right:2%;top:25%;width:30%;} 
			.fix_two{margin-top:0.7rem;}
			.fix_one button{background:url(../static/image/one_store/mess.png) no-repeat;width:100%;height:1.2rem;background-size:100% 100%;border:none;}
			.fix_two button{background:url(../static/image/one_store/messn.png) no-repeat;width:100%;height:1.2rem;background-size:100% 100%;border:none;} 
		</style>
	</head>
<body>  
	<!-- 整个主体 -->
	<div class="act_container">
	   <div class="act_one">
	      <img src="../static/image/one_store/act_heads.png"/>
	   </div>
	   <div class="act_tit">
	      <img src="../static/image/one_store/cat_tit.png"/>
	   </div>
	   <div class="act_two">
		<a href="goodsDetails.jhtml?goods_id=33971">
	      <img src="../static/image/one_store/1.png"/>
	     </a>
	   </div>
	   <div class="act_two">
		<a href="goodsDetails.jhtml?goods_id=33575">
	      <img src="../static/image/one_store/2.png"/>
	     </a>
	   </div>
	   <div class="act_two">
		<a href="goodsDetails.jhtml?goods_id=33970">
	      <img src="../static/image/one_store/3.png"/>
	     </a>
	   </div>
	   <div class="act_two">
		<a href="goodsDetails.jhtml?goods_id=33420">
	      <img src="../static/image/one_store/4.png"/>
	     </a>
	   </div> 
	   <div class="act_tree">
	     <p class="act_wei">
		   <img src="../static/image/one_store/we_pro.png">
		 </p>
		 <p class="at_text">		 
		   <img src="../static/image/one_store/kefu.png">
		 </p>      
	   </div>
	  <div class="fixed_con">		  
	    <p><a href="tel:18388258865"> <img src="../static/image/one_store/mess.png"></a></p>
	    <p class="fix_two"><a href="../notokenapi/app/v1/getOenidResult1.jhtml"> <img src="../static/image/one_store/messn.png"></a></p> 
	  </div>
	</div>
	<script>
	$(function(){ 
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';
	});
   wx.ready(function() {
		wx.onMenuShareAppMessage({
			title : '【一号店】中军创平台自营，精致商品尽在其中', // 分享标题
			desc : '惊喜不断，连续六天在“1号店”购买任意一款预售商品，就有权购买指定超值商品！', // 分享描述
			link : 'https://zjc1518.com/aosuite/wxsShare/initOneStore.jhtml',  //分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : 'https://zjc1518.com/aosuite/static/image/one_store/one_store.jpg', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});
	  })
		
	  wx.error(function(res) {
		  // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
		  alert("微信验证失败");
	  });
	</script>
</body>
</html>
