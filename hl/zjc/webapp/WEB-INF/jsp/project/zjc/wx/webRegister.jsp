<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=no" />
		 <title>分享</title>  
		<link href="../../../static/css/qrcode/share_regit.css" rel="stylesheet" type="text/css"/>  
	</head>
	<script type="text/javascript" src="../../../static/qrcode/js/jquery-1.11.1.js" ></script>
	<script type="text/javascript" src="../../../static/qrcode/js/jquery.qrcode.js" ></script>
    <script type="text/javascript" src="../../../static/qrcode/js/qrcode.js" ></script> 
    <script type="text/javascript" src="../../../static/qrcode/js/utf.js" ></script>
    <script src="../../../static/js/redpacket/quhao.js" type="text/javascript" charset="utf-8"></script>
	<script  language="javascript">
		var state;
		var wxUser;
		var binded = false;
	    var exit;
	 function GetQueryString(name){
		 var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		  var r = window.location.search.substr(1).match(reg);
		  if (r != null) {
		    return unescape(r[2]);
		  }
		  return null;
		} 
		$(document).ready(function(){
		
			state=GetQueryString('state');
			var wxCode=GetQueryString('code');
			$("#shareId").val(state);
			//https://zjc1518.com/aosuite/notokenapi/app/v1/
			$.post("https://zjc1518.com/aosuite/notokenapi/app/v1/wxLogin1.jhtml",{
				code:wxCode,
				type:'1'
			},function(data,status){
					if(data.code==1){
						wxUser=data.data;
					}else{
						$("#error").html("微信账号已使用");
						binded=true;
					}
				
			});		
		}); 
		var flag = true;
		function submit(){
			if(binded){
				return;
			}  
			var mobile=$("#account").val();
			var password=$("#password").val();
			/* $.post("http://localhost:10010/aosuite/notokenapi/app/v1/checkAccount.jhtml",{
				account:mobile
			},function(data,status){ */
			/* 	alert(data)
				var obj = JSON.parse(data);
				alert(obj);
				if(obj.code==200){ */
					/* exit=obj.exit; */
			if(""==mobile){
				$("#error").html("请输入手机号");
				return;
			}
			var vercode = $("#vercode").val();
			if(""==vercode){
				$("#error").html("请输入验证码");
				return;
			}
			/* if(""==password){
				$("#error").html("请输入密码");
				return;
			} */
			/* var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
			if(!reg.test(password)){
				$("#error").html("请密码必须是6-10位的数字和字母组合");
				return;
			} */
			
			if(flag){
				flag = false;
				$.post("wxRegisterBind1.jhtml",{
					account:mobile,
					mobile:mobile,
					password:"123456",
					shareId:state,
					unionid:wxUser.unionid,
					nickname:wxUser.nickname,
					header:wxUser.headimgurl,
					code:vercode,
					type:"register"
					//exit:exit
				},function(data,status){
					if(data.code==1){
						$("#error").html("注册成功");
						if(navigator.userAgent.indexOf("Safari") > -1){
							location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.seventc.zhongjunchuang";
						}else{
							location.href="https://itunes.apple.com/cn/app/id1192166297?mt=8";
						}
					}else{
						$("#error").html(data.msg);
						flag = true;
					}
				});
			}
				/* }else{
					$("#error").html(obj.datas.exit);
				} */
			//});
			
			
		}
		
		$(function(){
		  $(".container").height($(window).height());
		})	 	
		//$long_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=".$this->WEB_ID."&redirect_uri=".$redirect_uri."&response_type=code&scope=snsapi_userinfo&state=".$shareId."#wechat_redirect";
	</script>
<body>  
	<!-- 整个主体 -->
		<div class="container">   
		   <div class="share_head">
			  <div class="share_h_side fl">
				  <img src="../../../static/qrcode/img/sh_jt.png" width="13" height="25"/>
			  </div>
			  <div class="share_h_cont fl">注册</div>
			  <div class="clear"></div>
		   </div>
		   <div class="share_main">
			  <div class="sha_one">
				  <div class="sha_one_cen"><img src="../../../static/qrcode/img/sh_login.png"/></div>
			  </div>
			  <div class="sha_two">
			  <!-- 分享ID -->
				 <div class="sha_mobile" style="display:none;">
					<div class="sh_m_side fl">
					  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/sh_mob.png" width="22" height="23"/></p>
					  <p class="sh_te_mob fl color0c">分享ID</p><div class="clear"></div>
					</div>
					<div class="sh_m_cont fr">
						<input type="text" class="sh_input" id="shareId" placeholder="请输入分享ID"/>
					</div>
					<div class="clear"></div> 
				 </div>
				 <!-- 手机号 -->
				 <div class="sha_mobile">
					<div class="sh_m_side fl">
					  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/sh_mob.png" width="22" height="23"/></p>
					  <p class="sh_te_mob fl color0c">手机号</p><div class="clear"></div>
					</div>
					<div class="sh_m_cont fr" style="overflow:hidden">
					<select id="quhao" name="support_country" style="float:left;width:25%;height:2.5rem;line-height:2.5rem;"/>
					<input type="text" class="sh_input" id="account" placeholder="请输入你的手机号" style="float:left;width:70%;margin-left:5px"/>
					</div>
					<div class="clear"></div>			 
				 </div>
				 <!-- 验证码 -->
				 <div class="sha_mobile">
					<div class="sh_m_side fl">
					  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/yzm.png" width="20" height="21"/></p>
					  <p class="sh_te_mob fl color0c">验证码</p><div class="clear"></div>
					</div>
					<div class="sh_m_cont fr">
					   <input type="text" class="flsh_in_tex" id="vercode" placeholder="请输入短信验证码"/>
						<input type="button" value="获取验证码" id="getCode" class="sh_hq"/>
					</div>				
					<div class="clear"></div>			 
				 </div>
				 <!-- 密码 --> 
				 <!-- <div class="sha_mobile">
					<div class="sh_m_side fl">
					  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/pwd.png" width="20" height="21"/></p>
					  <p class="sh_te_mob fl color0c">设置密码</p><div class="clear"></div>				  
					</div>
					<div class="sh_m_cont fr">
					   <input type="password" class="flsh_in_texs" id="password" placeholder="请输入6-10位字母+数字"/>
					   <img src="../../../static/qrcode/img/eye.png" width="25" height="15" class="sh_eye"/> 
					</div>
					<div class="clear"></div>			 
				 </div> -->
			  </div>
			  <div class="sha_tree">
				<button class="sh_btn" onclick="submit()">立即注册</button>
				 <p id="error" class="sh_hint"></p>
			  </div>
		   </div>
		</div> 
</body>
</html>
<script type="text/javascript">
	/* $(".sh_btn").click(function (){
		$(".sh_id").show();
	})
 */
	//获取验证码
	$("#getCode").click(function (){
		var mobile = $("#account").val();
		if(binded){
			return;
		}
		var quhao = $("#quhao").val();
		console.log(quhao);
		if(mobile == ""){
			$("#error").html("请输入手机号！");
			return;
		}else{
			$.ajax({
				url:"../../app/v1/sendSMS.jhtml",
				data:{
					"mobile":mobile,
					"sms_type":"register",
					country_code : quhao
				},
				success:function(data){
					if(data.code != 1){
						$("#error").html(data.msg);
					}else{
						$("#error").html("验证码已发送！");
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
				}
			});
		}
	});
	
	var options = "<option value='86' selected>中国+86</option>";
	for(var i = 0; i < quhaoArray.length; i++ ){
		options += "<option value='" + quhaoArray[i].num_id + "'>" + quhaoArray[i].country + "+" + quhaoArray[i].num_id + "</option>";
	}
	$("#quhao").html(options);

</script>