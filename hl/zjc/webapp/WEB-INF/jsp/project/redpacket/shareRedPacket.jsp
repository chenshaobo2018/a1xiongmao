<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>红包页</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/redpacket/index.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/redpacket/mobile.js"
	type="text/javascript" charset="utf-8"></script>
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
					<div class="red_imgpen">
						<img src="${zjcUsersInfoPO.head_pic }" />
					</div>
					<div class="red_img_two">
						<img src="../../../static/image/redpacket/mon_one.png" />
					</div>
					<div class="red_img_btn">
						<button class="red_btn" onclick="pullRedPacket();"></button>
					</div>

				</div>
			</div>
			<div class="redss fr">
				<img src="../../../static/image/redpacket/pj.png" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function pullRedPacket(){
		window.location.href = "initRedPacketRegister.jhtml?shareId=${shareId}";
	}
</script>
</html>