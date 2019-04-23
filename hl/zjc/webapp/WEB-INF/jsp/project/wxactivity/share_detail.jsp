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
<title>分享助力详情</title>
<link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/share_power.css" />
<link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/swiper.min.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/handlebars.js" type="text/javascript" charset="utf-8"></script>
<script type="text/x-handlebars-template" id="progressBar">
		<div class="arkside fl">
			<div class="asrl_cc" style="max-width:100%;"></div>
		</div>
		<div class="arkcont fr">
            <div class="ark_side fl">{{this.share_integral}}</div>
            <div class="ark_cont fl">/</div>
            <div class="ark_cont fl">{{this.market_price}}</div>
            <div class="clear"></div> 
		</div>
</script>
<script type="text/x-handlebars-template" id="fourGoodsTpl">
	{{#each this}}
		<li>
			<a href="javascript:void(0);"> 
				<img src="{{original_img}}" /><br />
				<p class="de_title">{{goods_name}}</p>
				<p class="de_price">{{market_price}}券</p>
			</a>
		</li>
	{{/each}}
	<div class="clear"></div>
</script>
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
	<div class="share_condetail">
	<div class="shareone_img"> 
		<div class="con_sh_title clearfix">
			<div class="cont_fd fr">奖励规则</div>
		</div>
		</div>
		<!-- 遮罩 -->
		<div class="ze_make">
			<p class="zd_one center">规则说明</p>
			<div class="zd_two">
				<p>1、选择您想要的狗年好礼，邀请好友为你助力领取，分享好友无上限。
				<p>2.每新注册用户，可收到中军创赠送的158易物卷兑换好礼；每助力成功一名好友，可获赠58好友赠送积分。</p>
				<p>4.同一好友只能帮同一用户助力一次，若将活动链接分享至朋友圈。</p>
				<p>5.抽奖礼品数量限制，先到先得， 发完为止。</p>
			</div>
		</div>
		<div class="ze_make_copy"></div>
		<!-- 好友助力 -->
		<div class="sarm_one">
			<div class="sma_imgsha">
				<img src="../../../static/image/wxactivity/one.png" />
			</div>
			<div class="sdim_one clearfix">
				<div class="sdim_oneside fl">
					<img src="${zjcUsersInfoPO.head_pic }"
						class="motwo_sdim" />
				</div>
				<div class="sdim_onecont fl">${zjcUsersInfoPO.nickname }</div>

			</div>
			<div class="sdim_two clearfix">
				<div class="sdimsideone fl">
					<img src="../../../static/image/wxactivity/dh (1).png"
						class="sdide_img_one" />
				</div>
				<div class="sdimsidetwo fl center">
					我正在中军创兑换0元商品，<br />帮我助力免费拿吧~
				</div>
				<div class="sdimsidetree fl">
					<img src="../../../static/image/wxactivity/dh (2).png"
						class="sdide_img_two" />
				</div>
			</div>
			<div class="sdim_tree">
				<div class="sdfim_cen clearfix">
					<div class="sdimclside fl center">
						<img src="${zjcGoodsPO.original_img}" class="cen_img" />
					</div>
					<div class="sdimclcont fl">
						<p class="text_detail" style="font-size: 0.25rem;">
						   <c:if test="${zjcGoodsPO.goods_name.length()>30}">${zjcGoodsPO.goods_name.replaceAll(zjcGoodsPO.goods_name.substring(12,zjcGoodsPO.goods_name.length()),"...")}</c:if>
						   <c:if test="${zjcGoodsPO.goods_name.length()<31}">${zjcGoodsPO.goods_name}</c:if>
						</p>
						<div class="tex_gun clearfix progressBar"> 
								<!-- 进度条 --> 
						</div>
						<div class="tex_guncont">
							<fmt:formatNumber type="number"
								value="${zjcGoodsPO.market_price }" maxFractionDigits="0" />券
						</div>
					</div>
				</div>
			</div>
			<div class="sdim_four center">
				<button class="ok_btn_one">帮TA助力换好礼</button>
				<button class="ok_btn_two" onclick="window.open('init.jhtml')">我要参加活动</button>
				<p class="share_can">点击“我要参加活动”按钮，回到助力主页面，进入“商品兑换”可领取奖品</p>
			</div>
			<!-- 遮罩层 -->
			<div class="shde_make">
				<div class="tan_top">
					<div class="clearfix">
						<div class="shde fr">
							<img src="../../../static/image/wxactivity/x.png" class="shde_img" />
						</div>
					</div>
					<p class="shde_ie center">好友助力</p>
					<div class="shde_two">
						<div class="shde_tone">
							<input type="text" class="shdinput" name="mobile" placeholder="请输入手机号"/>
						</div>
						<div class="shde_tone ">
							<input type="text" class="shad_in" name='code' placeholder="请输入验证码" />
							<button class="btn_sd">获取验证码</button>
						</div>
						<p class="shfg_text center">帮助好友助力，登陆中军创云易平台，可获得158易物券，免费兑换商品</p>
					</div>
					<div class="dof_foot">
						<a href="javascript:void(0);">帮忙助力</a>
					</div>
				</div>
				<div class="tan_bottom">
					<p class="tan_text">
						您的登录密码为：<span>123456</span>，用于登录领取奖品。为确保您的账号安全，请更改登录密码
					</p>
					<div class="dof_foots">
						<a href="javascript:void(0);">确定</a>
					</div>
				</div>
			</div>
			<div class="shde_makes"></div>
			<div class="sdim_five clearfix">
				<div class="sdficveside fl center">
					<img src="../../../static/image/wxactivity/sar_fl.png" class="sixs_img" /><br />
					<p class="side_fj colorde1a00 bold ops">返券无上限</p>
				</div>
				<div class="sdficvecont fl center">
					<img src="../../../static/image/wxactivity/zf.png" class="sixs_img" /><br />
					<p class="side_fj colorde1a00 bold ops">分享双方均可中奖</p>
				</div>
			</div>
		</div>
		<!-- 已助力好友 -->
		<div class="sarmde_two">
			<div class="sma_imgsha">
				<img src="../../../static/image/wxactivity/ok_zl.png" />
			</div>
			<div class="desm_ul">
				<ul id="shareFriendsList"> </ul>
			</div>
		</div>
		<!-- 商品 -->
		<div class="serfoot">
			<div class="sarmde_two">
				<div class="sma_imgsha">
					<img src="../../../static/image/wxactivity/more.png" />
				</div>
				<div class="sdogli">
					<ul id="fourGoods"></ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 遮罩 -->
	<script>
		$(".ok_btn_one").click(function() {
			/* $(".shde_make,.shde_makes,.tan_top").show();
			$(".tan_bottom").hide(); */
			<c:if test="${sessionScope.openid != null && sessionScope.openid != ''}">
			$(".dof_foot").click();
			</c:if>
			<c:if test="${sessionScope.openid == null || sessionScope.openid == ''}">
			window.open('initLogin.jhtml')
			</c:if>	
		});
		$(".dof_foot").click(function() {
			var openid="${sessionScope.openid}";			
/* 			var mobile = $("input[name='mobile']").val();
			var valiCode = $("input[name='code']").val();
			if(validateMobile(mobile)){
				if(validateCode(valiCode,"验证码")){
					//调用接口 */
					$.ajax({
						 url : "wxShareAddPhone.jhtml", 
					/* 	url : "AddShare.jhtml",		 */				
						type : 'POST',
						data : {
							shareId : "${shareId}",
						/* 	phonecode : valiCode,
							phone : mobile, */
							sms_type : 'register',
							open_id : openid,
							goods_id : '${zjcGoodsPO.goods_id}',
							goods_price : '${zjcGoodsPO.market_price}'
						},
						success : function(data) {
							if (data.code == 1) {
								alert("助力成功！");
								$(".shde_make,.shde_makes,.tan_bottom").show();
								$(".tan_top").hide();
							} else if(data.code == 10170){
								alert("助力成功！");
								$(".tan_top").hide();
							}else{
								alert(data.msg);
							}
							window.location.reload();
						}
					});
			/* 	}
			} */
			
		});
		$(".dof_foots").click(function() {
			$(".shde_make,.shde_makes").hide();
		});
		$(".shde_img").click(function() {
			$(".shde_make,.shde_makes,.tan_top").hide();
			$(".tan_bottom").show();
		});
		//进度条
		$.post("goodsShare.jhtml",{"open_id": "${sessionScope.openid}","goods_id":"${goods_id}"},function(data){
			 var temeplte = Handlebars.compile($("#progressBar").html());
			 console.log(data.data.list[0]);
			$(".progressBar").html(temeplte(data.data.list[0])); 
			var precent = parseInt((data.data.list[0].share_integral / data.data.list[0].market_price) * 100);
			$(".asrl_cc").width(precent + "%");
		})
		//获取验证码
		$(".btn_sd").click(function() {
			var mobile = $("input[name='mobile']").val();
			if(validateMobile(mobile)){
				//调用接口
				$.ajax({
					url : "wxShareSendMessage.jhtml",
					type : 'POST',
					data : {
						mobile : mobile,
						sms_type : 'register',
					},
					success : function(data) {
						console.log(data)
						if (data.code == 1) {
							alert("发送成功！");
						} else {
							alert(data.msg);
						}
					}
				});
			}
		});
		
		//手机号码验证
		function validateMobile(mobile){
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
			if(mobile != ""){
				if(!myreg.test(mobile)) 
				{ 
				    alert('请输入有效的手机号码！'); 
				    return false; 
				}else{
					return true; 
				}
			}else{
				alert("请输入手机号码！");
				return false; 
			}
		}
		//验证码非空验证
		function validateCode(code,msgStr){
			if(code != ""){
				return true; 
			}else{
				alert("请输入" + msgStr + "!");
				return false; 
			}
		}
		
		$.post("shareHomePageGoods.jhtml", function(data) {
			var temeplte = Handlebars.compile($("#fourGoodsTpl").html());
			$("#fourGoods").html(temeplte(data.data));
			$("#fourGoods li:odd").css("margin-right","0");
		})
		
		//跳转到商品详情
		function toGoodsDetial(goods_id) {
			window.location.href = "goInit.jhtml?goods_id=" + goods_id;
		}
		
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
