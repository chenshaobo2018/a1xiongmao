<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<title>注册</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link href="../../../static/css/wxactivity/share_power.css"
	rel="stylesheet" type="text/css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
</head>

<style type="text/css">
.overlay {
	position: fixed;
	top: 0px;
	bottom: 0px;
	left: 0px;
	right: 0px;
	z-index: 100;
}

.box {
	position: fixed;
	top: -5rem;
	left: 30%;
	right: 30%;
	background-color: #fff;
	color: #7F7F7F;
	padding: 20px;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	-khtml-border-radius: 20px;
	-moz-box-shadow: 0 1px 5px #333;
	-webkit-box-shadow: 0 1px 5px #333;
	z-index: 101;
	font-size: 0.2rem;
}
</style>

<body>
	<!-- 整个主体 -->
	<div class="reg_contain">
		<div class="reg_share_namesd">
			<div class='reg_log'>
				<div class="log_cen">
					<img src="../../../static/image/wxactivity/log.png" alt="中军创"
						title="中军创" />
				</div>
			</div>
		</div>
		<div class="ceri_bton">
			<div class="reg_share_name">
				<div class="onde fl">
					<div class="fl">
						<img src="../../../static/image/wxactivity/ong.png"
							class="share_ids_one" />
					</div>
					<div class="fl flor_one">
						<label class="reg_label">分享ID</label>
					</div>
					<div class="clear"></div>
				</div>
				<div class="reg_text fl">
					<input type="text" class="share_text fr" name="recommended_code"
						id="recommended_code" placeholder=" 请输入分享ID" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="reg_share_name">
				<div class="onde fl">
					<div class="fl">
						<img src="../../../static/image/wxactivity/two.png"
							class="share_ids_two" />
					</div>
					<div class="fl flor_one_iop">
						<label class="reg_label">手机号</label>
					</div>
					<div class="clear"></div>
				</div>
				<div class="reg_text fl">
					<input type="text" class="share_text fr" name="mobile" id="mobile"
						placeholder=" 请输入手机号" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="reg_share_name">

				<div class="onde fl">
					<div class="fl">
						<img src="../../../static/image/wxactivity/tree.png"
							class="share_ids" />
					</div>
					<div class="fl flor fger">
						<label class="reg_label">验证码</label>
					</div>
					<div class="clear"></div>
				</div>
				<div class="reg_text_yzm fr">
					<input type="text" class="share_texts fl" name="vercode"
						id="vercode" placeholder=" 请输入短信验证码" /> <input type="button"
						class="share_btn fr" value="获取验证码" id="getCode"
						onClick="getVertifyCode()" />
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="reg_share_name">
				<div class="onde fl">
					<div class="fl">
						<img src="../../../static/image/wxactivity/fourss.png"
							class="share_ids_pwd" />
					</div>
					<div class="fl flor fger">
						<label class="reg_label"> 设置密码</label>
					</div>
					<div class="clear"></div>
				</div>
				<div class="reg_text fl">
					<input type="password" class="share_text fr" name="password"
						id="password" placeholder=" 请设置你的登录密码" />
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="loepr">
			<div CLASS="log_cem">
				<div class="reg_btn">
					<input type="button" class="ty_btn" value="立即注册"
						onclick="register()" /> <label id="error"></label>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="box" id="box"></div>
	<div class="overlay" id="overlay" style="display: none;"></div>
	<script>
		//获取验证码
		function getVertifyCode() {
			var mobile = $("#mobile").val();
			if (mobile.length != 11) {
				$("#box").html("注册账号长度应为11位");
				tishi();
				return;
			} else {
				$.post("wxShareSendMessage.jhtml", {
					"mobile" : mobile,
					"sms_type" : "register"
				}, function(data) {
					$("#box").html(data.msg);
					tishi();
				});
			}
		}

		//注册
		function register() {
			var mobile = $("#mobile").val();
			var password = $("#password").val();
			var vercode = $("#vercode").val();
			if ($("#mobile").val().length != 11) {
				$("#box").html("注册账号长度应为11位");
				tishi();
				return;
			}
			if (password.length < 6) {
				$("#box").html("注册最少6位");
				tishi();
				return;
			}
			if (vercode.length != 6) {
				$("#box").html("验证码格式错误");
				tishi();
				return;
			}
			$.post("wxShareRegistered.jhtml", {
				"phone" : $("#mobile").val(),
				"password" : $("#password").val(),
				"shareId" : $("#recommended_code").val(),
				"phonecode" : $("#vercode").val(),
				"sms_type" : "register"
			}, function(data) {
				$("#box").html(data.msg);
				tishi();
				if(data.code == 1){
					window.location.href = "init.jhtml";
				}
			});
		}
		function tishi() {
			$('#overlay').fadeIn('fast', function() {
				$('#box').animate({
					'top' : '5rem'
				}, 500);
				setTimeout("tishiclose()", 1000);
			});
		}
		function tishiclose() {
			$('#box').animate({
				'top' : '-5rem'
			}, 500, function() {
				$('#overlay').fadeOut('fast');
			});
		}
	</script>
</body>
</html>
