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
		<div class="in_main">
			<div class="in_cen">
				<!-- 共有六张现金及劵 -->
				<div class="incen_one center">
					<img src="../../../static/image/redpacket/${randomNum}.png" />
				</div>

				<div class="incen_two">
					<div class="in_one">
					<select id="quhao" name="support_country">
					</select>
						<input type="text" name="mobile" placeholder="请输入手机号"/>
					</div>
					<div class="in_two clearfix">
						<input type="text" name="code" class="fl" placeholder="验证码"/>
						<button class="huy fr" onclick="sendMsg();">获取验证码</button>
					</div>
				</div>
				<div class="opg_btn">
					<button class="red_btn_one" onclick="redRegister();"></button>
				</div>
				<!-- 登录成功与否-->
				<div class="btn_o">
					<p class="btnd">
						<img src="../../../static/image/redpacket/x.png">
					</p>
					<!-- 点击登录显示 '下载app' -->
					<div class="app_img">
						<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.seventc.zhongjunchuang"> <img src="../../../static/image/redpacket/app.png" />
						</a>
					</div>
					<!-- 点击登录显示 '分享领取红包' -->
					<div class="app_img_share">
						<a href="initRedPacketIndex.jhtml"> <img src="../../../static/image/redpacket/01.png" />
						</a>
					</div>
				</div>
				<div class="btn_omake"></div>
			</div>
		</div>
	</div>
</body>
<script src="../../../static/js/redpacket/quhao.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(".app_img").hide();
	$(".app_img_share").hide();
	function redRegister(){
		var code = $('input[name="code"]').val();
		var mobile = $('input[name="mobile"]').val();
		if (mobile.length == 0) {
			alert('请输入手机号码！');
			return;
		}
		/* if (mobile.length != 11) {
			alert('请输入有效的手机号码！');
			return;
		}

		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		if (!myreg.test(mobile)) {
			alert('请输入有效的手机号码！');
			return;
		} */
		if (code.length == 0) {
			alert('请输入验证码！');
			return;
		}
		$.ajax({ 
			url : "redPacketRegister.jhtml",
			type : 'POST',
			data : {
				share_id : '${shareId}',
				randomNum : '${randomNum}',
				mobile : mobile,
				code : code,
				type : 'register'
			},
			success: function(data){
				if(data.code == 1){
					$(".app_img").show();
					$(".app_img_share").hide();
					$(".btn_o,.btn_omake").show();
				}else if(data.code == 10170){
					$(".app_img").hide();
					$(".app_img_share").show();
					$(".btn_o,.btn_omake").show();
				}else{
					alert(data.msg);
				}
	      	}
		});
	}
	
	//发送验证码	
	function sendMsg() {
		var mobile = $('input[name="mobile"]').val();
		var quhao = $("#quhao").val();
		console.log(quhao);
		if (mobile.length == 0) {
			alert('请输入手机号码！');
			return;
		}
		/* if (mobile.length != 11) {
			alert('请输入有效的手机号码！');
			return;
		} */

		/* var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		if (!myreg.test(mobile)) {
			alert('请输入有效的手机号码！');
			return;
		} */
		$.ajax({
			url : "../../../notokenapi/app/v1/sendSMS.jhtml",
			type : "POST",
			data : {
				sms_type : 'register',
				mobile : mobile,
				country_code : quhao
			},
			success : function(data) {
				if (data.code == 1) {
					alert("发送成功！")
				} else {
					alert(data.msg)
				}
			}
		});
	}
	var options = "<option value='86' selected>中国+86</option>";
	for(var i = 0; i < quhaoArray.length; i++ ){
		options += "<option value='" + quhaoArray[i].num_id + "'>" + quhaoArray[i].country + "+" + quhaoArray[i].num_id + "</option>";
	}
	$("#quhao").html(options);
</script>
</html>
