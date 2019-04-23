<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=no" />
		 <title>绑定账号</title>  
		<link href="../../../static/css/qrcode/share_regit.css" rel="stylesheet" type="text/css"/>  
	</head>
	<script type="text/javascript" src="../../../static/qrcode/js/jquery-1.11.1.js" ></script>
	<script type="text/javascript" src="../../../static/qrcode/js/jquery.qrcode.js" ></script>
    <script type="text/javascript" src="../../../static/qrcode/js/qrcode.js" ></script> 
    <script type="text/javascript" src="../../../static/qrcode/js/utf.js" ></script>
	<script  language="javascript">
		var flag = true;
		//绑定账号
		function submit(){
			var mobile=$("#account").val();
			var password=$("#password").val();
			if(""==mobile){
				$("#error").html("请输入手机号");
				return;
			}
			if(""==password){
				$("#error").html("请输入密码");
				return;
			}
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
			if(!reg.test(password)){
				$("#error").html("请密码必须是6-10位的数字和字母组合");
				return;
			}
			var methodStr = '${methodStr}';
			if(flag){
				flag = false;
				$.post("wxBind.jhtml",{
					mobile:mobile,
					password:password
				},function(data,status){
					if(data.code==1){
						$("#error").html("绑定成功");
						if(methodStr.length == 0) {//绑定页面请求跳转主页
							location.href="initWxZjcLogin.jhtml";
						} else {//其他页面请求跳回原页面
							var index = methodStr .lastIndexOf("\/"); 
							methodStr = methodStr .substring(index + 1, methodStr .length);
							location.href = methodStr;
						}
					} else if(data.code == '44444'){//该手机号尚未开通会员号
						$("#error").html(data.msg);
						location.href="toWxRegisterPage.jhtml";
					}else{
						$("#error").html(data.msg);
						flag = true;
					}
				}).error(function(XMLHttpRequest, textStatus, errorThrown){
					$("#error").html("绑定失败");
				});
			}
		}
		
		//跳转到注册页面
		function toRegister(){
			location.href="toWxRegisterPage.jhtml";
		}
		
		$(function(){
		  $(".container").height($(window).height());
		})	 	
	</script>
<body>  
	<!-- 整个主体 -->
		<div class="container">   
		  <!--  <div class="share_head">
			  <div class="share_h_side fl">
				  <img src="../../../static/qrcode/img/sh_jt.png" width="13" height="25"/>
			  </div>
			  <div class="share_h_cont fl">绑定账号</div>
			  <div class="clear"></div>
		   </div> -->
		   <div class="share_main">
			  <div class="sha_one">
				  <div class="sha_one_cen"><img src="../../../static/qrcode/img/sh_login.png"/></div>
			  </div> 
			 <!-- 手机号 -->
			 <div class="sha_mobile">
				<div class="sh_m_side fl PHONE">
				  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/sh_mob.png" width="22" height="23"/></p>
				  <p class="sh_te_mob fl color0c">手机号</p><div class="clear"></div>
				</div>
				<div class="sh_m_cont fr">
				<input type="tel" class="sh_input" id="account" placeholder="+86 请输入你的手机号"/>
				</div>
				<div class="clear"></div>			 
			 </div> 
			  <!-- 密码 --> 
			 <div class="sha_mobile">
				<div class="sh_m_side fl">
				  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/pwd.png" width="20" height="21"/></p>
				  <p class="sh_te_mob fl color0c">密码</p><div class="clear"></div>				  
				</div>
				<div class="sh_m_cont fr">
				   <input type="password" class="flsh_in_texs" id="password" placeholder="请输入6-10位字母+数字"/>
				   <img src="../../../static/qrcode/img/eye.png" width="25" height="15" class="sh_eye"/> 
				</div>
				<div class="clear"></div>			 
			 </div>
			  <div class="sha_tree">
				<button class="sh_btn" onclick="submit()">立即绑定</button>
				<p class="sha_account">尚未开通账号？<a href="javascript:void(0)" onclick="toRegister()">立即注册</a></p>
				 <p id="error" class="sh_hint"></p>
			  </div>
		   </div>
		</div> 
</body>
</html>
