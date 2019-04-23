<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=yes" />
         <script src="../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
       	 <script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		 <script type="text/javascript" src="../static/js/sha1.js"></script>
		 <script type="text/javascript" src="../static/js/wxs_share/wxconfig.js"></script>  
		 <title>县级代理</title>   
		<style>
			*,div,p,ul,li,table,tr,td,a,span,img,label,input,button{margin:0;padding:0;}a{text-decoration:none;}html,body{height:100%;width:100%;margin:0;padding:0; font-family:'Microsoft YaHei','微软雅黑';font-size:62.5%;}  
			.fl{float:left;}.fr{float:right;}.clear{clear:both;}.clearfix:before,.clearfix:after {display: table;content:" ";}.clearfix:after {clear: both;}.clearfix{*zoom: 1;}.center{text-align:center;} 
			.wxs_container{background:url(../static/image/wxs_share/wxs_bg.png) no-repeat; width:100%;min-height:100%;background-size:100% 100%;}
			.wxs_one{width:65%;margin-left:21%;padding-top:1.1rem;}			
			.wxs_one img,.exs_ime img,.td_img img,.td_img_one img,.xsd_img img,.xsd_img_one,.xsd_img_one img,.wxs_sen img,.wxs_eig img,.zhe_show img{width:100%;}
			.wxs_two{width:90%;margin:0 auto;background:#fff;margin-top:0.8rem;border-radius:0.2rem;}
			.wxst_one{width:75%;margin:0 auto;padding-top:0.5rem;padding-bottom:0.8rem;}
			.se_tit{border-bottom:1px solid #efefef;padding-bottom:0.3rem;}
			.sef_img{width:2.2rem;}
			.wxs_img{width:0.5rem;position:relative;top:0.05rem;}
			.wxs_imgs{width:0.5rem;position:relative;top:0.1rem;}
			.sep_text{font-size:0.45rem;color:#333;}
			.se_titcont{margin-top:0.6rem;}
			.se_phon a,.se_phon_tel a{color:#fff;font-size:0.45rem;}
			.se_phon,.se_phon_tel{background:#d42223;border-radius:0.2rem;padding:0.3rem 0;margin-top:0.25rem;box-shadow: 0 0 10px #d42223;}
			.exs_ime,.td_img_one{width:95%;margin:0 auto;}
			.wxs_tree{margin-top:0.8rem;}.exs_text,.wxs_five{width:80%;margin:0 auto;}
			.exs_text p{color:#fff;font-size:0.43rem;line-height:0.65rem;text-indent:0.7rem;}
			.td_img{width:25%;margin:0 auto;}.wxs_four{margin-top:0.8rem;}.td_img_one{margin-top:0.65rem;}
			.wxs_join{margin-bottom:0.7rem;}
			.wxs_five{font-size:0.43rem;padding-bottom:4rem;margin-top:0.8rem;}
			.wxsd_side,.wxsd_cont{color:#fff;}
			.add_img{width:0.4rem;position:relative;top:0.1rem;}
			.wxsd_side{width:33%;}.wxsd_cont{width:67%;margin-top:0.05rem;}
			.wxs_btn{width:70%;margin:0 auto;margin-top:0.8rem;}
			.wxs_btn button{width:100%;padding:0.3rem 0;background:#fff;border-radius:0.2rem;color:#da0211;font-size:0.5rem;border:1px solid #fff;}
			.zhe_show_make{z-index: 9997;position:fixed;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;}
			.zhe_show{color:#fff;z-index:9998;position:fixed;top:10%; width:45%;margin:0 auto;left:25%; padding-top:10%;display:none;border-radius:15px;}
			.zhe_img{text-align:right;}
			.zhe_img img{width:2rem;}
			.zhe_show p{text-align:center;font-size:0.5rem; }
			.wxs_six{width:80%;margin:0 auto;margin-top:1.1rem;}
			.xsd_img{width:80%;margin:0 auto;margin-bottom:0.35rem;}
			.sdx_tweo{margin-top:0.6rem;}
			.wxs_sen{width:89%;margin-left:10%;margin-top:0.3rem;}
			.wxs_eig{width:95%;margin:0 auto;margin-top:0.6rem;}
			.zhe_show p{color:#fff;text-align:center;font-size:0.4rem;}
			.zhe_img{margin-bottom:0.2rem;}
		</style>
	</head>
<body>  
	<!-- 整个主体 -->    
	<div class="wxs_container"> 
	  <div class="wxs_one center">
	      <img src="../static/image/wxs_share/wxs_heads.png"/>
	  </div>
	  <div class="wxs_two">
	     <div class="wxst_one">
		  <div class="se_tit center">
		    <img src="../static/image/wxs_share/bm.png" class="sef_img"/>
		  </div>
		  <div class="se_titcont">
		    <p class="sep_text"><img src="../static/image/wxs_share/phone.png" class="wxs_img"/> 报名热线：</p>
			<div class="se_phon_tel center"><a href="tel:400-017-1518">400-017-1518</a></div>
		   </div>
		   <div class="se_titcont">
		    <p class="sep_text"><img src="../static/image/wxs_share/qq.png" class="wxs_imgs"/> 微信咨询：</p>
			<div class="se_phon center"><a href="javascript:void(0);">187 8169 6999</a></div>
		   </div>
		 </div>
	  </div>
	  <div class="wxs_six">
	     <div class="xsd_img">
		  <img src="../static/image/wxs_share/dl.png"/>
		 </div>
	     <div class="xsd_img_one">
		  <img src="../static/image/wxs_share/1.png"/>
		 </div>
	     <div class="xsd_img_one sdx_tweo">
		  <img src="../static/image/wxs_share/2.png"/>
		 </div>
	  </div>
	  <div class="wxs_sen">
	  <img src="../static/image/wxs_share/3.png"/>
	  </div>
	  <div class="wxs_eig">
	  <img src="../static/image/wxs_share/6.png"/>	  
	  </div>
	  <div class="wxs_tree">
	     <div class="exs_ime">
			<img src="../static/image/wxs_share/jj.png"/>		 
		 </div>
		 <div class="exs_text">
		   <p>北京中军创云易物联网科技有限公司是一家顺应时代发展，响应“大众创业，万众创新”的号召，在国家多部委以及一些军政领导的亲切关怀和支持下，与多家企业强力合作，由数位互联网电子技术和经济专家，依托国家相关政策的支持，建立高效益的军民融合深度发展的新格局，助力中国梦，践行创新经济模式而强强联盟组建的物联网科技公司，在短短成立一年的时间会员注册量就突破了700万，并创造了无数清库存和去产能神话，挽救了大量濒临破产的企业，实现企业升级与发展，拉动内需，促进增收。并推出消费创业的销售模式，短短的几个月，就打造了数十位千万富翁和1000多位百万富豪，并让每位公司内部员工月收入都突破六位数。 </p>
		  </div>
	  </div>
	  <div class="wxs_four">
	     <div class="td_img center">
			<img src="../static/image/wxs_share/td.png"/>
		 </div>
	     <div class="td_img_one">
			<img src="../static/image/wxs_share/sh.png"/>
		 </div>
	  </div>
	  <div class="wxs_four">
	     <div class="td_img wxs_join center">
			<img src="../static/image/wxs_share/join.png"/>
		 </div>
		 <div class="exs_text">
		   <p>实现线上线下一体化，我们提供了由线上电子商务现货交易和网上商城网上大宗商品贸易，实现了线下提供实物交割仓储运输一体化的符合国家十三五规划整体发展方针。海企业文化精髓，同时用爱心做事业，以感恩心做人的精神态度，互联互通天下人物，让人民群众充分享受新经济新模式带来的改革红利。</p>
		  </div>
	  </div>
	  <div class="wxs_five">
	     <div class="wsx_addres clearfix"><div class="wxsd_side fl"><img src="../static/image/wxs_share/address.png" class="add_img"/> 公司地址：</div>
	     <div class="wxsd_cont fl">北京市东城区建国门内大街7号长安光华大厦第一座1601</div></div>
	  </div> 
	<div class="zhe_show">
		<p class="zhe_img"><img src="../static/image/wxs_share/we.png"/></p>
		<p>分享链接至微信</p>
		<p>长按二维码添加好友</p>
	</div>
	<div class="zhe_show_make"></div>
	</div>
	<script>
	$(function(){
		$(".se_phon").click(function(){
			$(".zhe_show,.zhe_show_make").show();
		})
		$(".zhe_show_make").click(function(){
			$(".zhe_show_make,.zhe_show").hide();
		})
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';
	});		
		 wx.ready(function() {
			wx.onMenuShareAppMessage({
				title : '县级代理火热招募', // 分享标题
				desc : '亿万财富等你来拿，县级代理报名正式启动高额收益千分之五', // 分享描述
				link : 'https://zjc1518.com/aosuite/wxsShare/initWxsShareIndex.jhtml',  //分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl : 'https://zjc1518.com/aosuite/static/image/wxs_share/xdxiaotu.png', // 分享图标
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
