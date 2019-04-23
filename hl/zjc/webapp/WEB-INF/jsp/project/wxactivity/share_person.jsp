<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>分享助力首页</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/share_power.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/swiper.min.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/handlebars.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/x-handlebars-template" id="swiperTpl">
	{{#each this}}
		<li>
			<div class="desmside fl">
				<img src="
					{{#if img}}
							{{img}}	
						{{else}}
							../../../static/image/wxactivity/default_head.png
					{{/if}}
				" class="motwo_li"> 
				<span class="des_one">{{nickname}}</span>
			</div>
			<div class="desmcont fr">
				<img src="../../../static/image/wxactivity/ji.png" class="mode_img"> 
				<span class="des_two bold">助力{{share_integral}}积分</span>
			</div>
			<div class="clear"></div>
		</li>
	{{/each}}
	<div class="clear"></div>
	</script>
</head>
<body>
	<div class="share_person">
		<!-- <div class="con_sh_title clearfix">
			<div class="cont_fd fr">奖励规则</div>
		</div> -->
		<!-- 遮罩 -->
		<div class="ze_make">
			<p class="zd_one center">规则说明</p>
			<div class="zd_two">
				<p>1、选择您想要的狗年好礼，邀请好友为你助力领取，分享好友无上限。
				<p>2.每新注册用户，可收到中军创赠送的158易物卷兑换好礼；每助力成功一名好友，可获赠58好友赠送积分。</p>

				<p>4.同一好友只能帮同一用户助力一次，若将活动链接分享至朋友圈。</p>
				<p>5.抽奖礼品数量限制，先到先得，发完为止。</p>
			</div>
		</div>
		<div class="ze_make_copy"></div>
		<div class="zdon_main">
			<div class="sarmde_two">
				<div class="sma_imgsha">
					<img src="../../../static/image/wxactivity/ok_zl.png">
				</div>
				<div class="desm_ul">
					<ul id="shareFriendsList"></ul>
				</div>
			</div>
		</div>

	</div>
	<script>
		$.post("shareFriends.jhtml", {
			"page" : 1,
			"share_open_id" : "${sessionScope.openid}"
		}, function(data) {
			console.log(data);
			var temeplte = Handlebars.compile($("#swiperTpl").html());
			$("#shareFriendsList").html(temeplte(data.data));
		});
	</script>
</body>
</html>