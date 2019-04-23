<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="../../../static/css/redpacket/index.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/redpacket/mobile.js" type="text/javascript" charset="utf-8"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="../../../static/js/sha1.js"></script>
<script type="text/javascript" src="../../../static/js/wxactivity/wxconfig.js"></script>
<style>
a {
	color: #c50298;
	font-size: 0.4rem;
}
</style>
</head>
<body>
	<div class="reden_contr">
		<!-- 滚动 -->
		<div class="sharesig_one clearfix">		
			<div class="sjof fl">
				<div id="share_sign_one" class="fl">
					<ul id="share_sign_two">
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/peron.png" class="singm" /><span
								class="phons">13028179389</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx1.png" class="singm" /><span
								class="phons">18048501966</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx2.png" class="singm" /><span
								class="phons">18140229299</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx3.png" class="singm" /><span
								class="phons">15397622323</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx4.png" class="singm" /><span
								class="phons">15196667999</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx5.png" class="singm" /><span
								class="phons">18628197687</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx6.png" class="singm" /><span
								class="phons">15528190971</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx7.png" class="singm" /><span
								class="phons">18628227901</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx8.png" class="singm" /><span
								class="phons">15528368983</span> 已领取红包 </a></li>
						<li><a href="#" target="_blank"><img src="../../../static/image/redpacket/tx1.png" class="singm" /><span
								class="phons">18681353810</span> 已领取红包 </a></li>
					</ul>
					<div id="share_sign_tree"></div>
				</div>
			</div>
			<div class="reden_cont fr">活动规则</div>
			<div class="zhe_gz">
				<p>1. 活动期间，凡符合要求的新老用户皆可以发起红包；</p>
				<p>2. 分享红包次数不限，一个新账户只能领取一次红包；</p>
				<p>3. 到账红包可用于购买商品抵扣现金；</p>
				<p>4. 购买单次只能使用一张现金券；</p>
				<p>5. 已经注册过的手机号不可领取红包；</p>
				<p>6. 通过微信获得红包，用户需登陆app后，红包才能到账。</p>

			</div>
			<div class="zhe_gz_make"></div>
		</div>
		<!-- main -->
		<div class="redkpo" style="position: relative">
			<div class="redn_main">
				<div class="redn_main_one">
					<div class="red_img">
						<img src="../../../static/image/redpacket/ones.png" />
					</div>
					<div class="red_img_one">
						<img src="../../../static/image/redpacket/sw.png" />
					</div>
					<div class="red_img_two">
						<img src="../../../static/image/redpacket/mon.png" />
					</div>
					<div class="red_img_btn_index">
						<button class="red_btn"></button>
					</div>

				</div>
			</div>
			<div class="redss fr">
				<img src="../../../static/image/redpacket/pj.png" />
			</div>
		</div>
	</div>
	
	
	<div class="ze_makebtn">
		<div class="clearfix"><div class="fr"><img src="../../../static/image/redpacket/cgt.png" class="msl_img"></div></div>
		<p class="zue_texts center">请点击右上角</p>
		<p class="zue_texts center">将它发送给指定的朋友</p>
		<p class="zue_texts center">或分享到朋友圈 </p>
	</div>
	<div class="ze_make_copybtn"></div>
</body>

<script>
	$(".red_btn").click(function(){
		$(".ze_makebtn,.ze_make_copybtn").show();
	})
	$(".ze_make_copybtn,.ze_makebtn").click(function(){
		$(".ze_makebtn,.ze_make_copybtn").hide();
	})
	//https://zjc1518.com
	wx.ready(function() {
		wx.onMenuShareAppMessage({
			title : '【微信红包】帮我拆分现金红包！最高188元现金先到先得~', // 分享标题
			desc : '新用户立得8-188元现金红包，提现立即到账，点击领取>>', // 分享描述
			link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareRedPacket.jhtml?shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : 'https://zjc1518.com/aosuite/static/image/redpacket/shareImg.png', // 分享图标
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
</html>