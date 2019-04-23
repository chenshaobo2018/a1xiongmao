<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=no" />
		 <title>引领财富自由，走上人生巅峰！中军创云易平台欢迎您</title>  
		<link href="../../../static/webpage/share.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../../static/qrcode/js/jquery-1.11.1.js" ></script>
	</head>
<body>  
<!-- 整个主体 -->
    <div class="reg_contain">   
	    <div class="reg_share_namesd"> 
		   <div class='reg_log'>
			    <div class="log_cen"><img src="../../../static/webpage/img/log.png" alt="中军创" title="中军创"/> </div>
			 </div>		 
		</div> 
<div class="ceri_bton">		
	<!--
   	<div class="reg_share_name"> 
		<div class="onde fl">
			 <div class="fl"><img src="../../../static/webpage/img/ong.png" width="19" height="19" /></div>
			 <div class="fl flor"><label class="reg_label"> 分享ID</label></div>
			 <div class="clear"></div>
		</div>			 
			 <div class="reg_text fr">
				<input type="text" class="share_text fr" name="recommended_code" id="recommended_code" placeholder=" 请输入分享ID"/>
			 </div>
			 <div class="clear"></div>
		</div>
		-->
	    <div class="reg_share_name"> 
			<div class="onde fl">
			 <div class="fl"><img src="../../../static/webpage/img/four.png" width="17" height="19" /></div>
			 <div class="fl flor"><label class="reg_label"> 用户名</label></div>
			 <div class="clear"></div>
			</div>	
			  <div class="reg_text fr">
				<input type="text" class="share_text fr" name="nickname" id="nickname" placeholder=" 请输入真实姓名"/>
			 </div>
			 <div class="clear"></div>
		</div>
	    <div class="reg_share_name"> 
		 <div class="onde fl">
			 <div class="fl"><img src="../../../static/webpage/img/two.png" width="14" height="21" /></div>
			 <div class="fl flor"><label class="reg_label"> 手机号</label></div>
			 <div class="clear"></div>
			</div>	
			<div class="reg_text fr">
				<input type="text" class="share_text fr" name="mobile" id="mobile" placeholder=" 请输入手机号"/>
			 </div>
			 <div class="clear"></div>
		</div>
	    <div class="reg_share_name"> 
		
		 <div class="onde fl">
			 <div class="fl"><img src="../../../static/webpage/img/tree.png" width="18" height="21" class="share_ids"/></div>
			 <div class="fl flor fger"><label class="reg_label"> 验证码</label></div>
			 <div class="clear"></div>
			</div>	 
			<div class="reg_text_yzm fr">
				<input type="text" class="share_texts fl" name="vercode" id="vercode" placeholder=" 请输入短信验证码"/>
				<!-- <button class="btn_yzma"  onclick="getCode()">获取验证码</button> -->
				<input type="button" class="share_btn fr" value="获取验证码" id="getCode"/>
			   <div class="clear"></div>
			 </div>
			 <div class="clear"></div>
		</div>
	   <!--  <div class="reg_share_name"> 
		
		 <div class="onde fl">
			 <div class="fl"><img src="../../../static/webpage/img/fourss.png" width="18" height="21" class="share_ids"/></div>
			 <div class="fl flor fger"><label class="reg_label"> 设置密码</label></div>
			 <div class="clear"></div>
			</div> 
			 <div class="reg_text fr">
				<input type="password" class="share_text fr" name="password" id="password" placeholder=" 请设置你的登录密码"/>
			 </div>
			 <div class="clear"></div>
		</div> -->
		<!--
	    <div class="reg_share_name"> 
		 <div class="onde fl">
			 <div class="fl"><img src="../../../static/webpage/img/fives.png" width="18" height="21" class="share_ids"/></div>
			 <div class="fl flor fger"><label class="reg_label"> 确认密码</label></div>
			 <div class="clear"></div>
			</div>  
			 <div class="reg_text fr">
				<input type="password" class="share_text fr" name="confrim_psd" id="confrim_psd" placeholder=" 请再次输入你的登录密码"/>
			 </div>
			 <div class="clear"></div>
		</div>
		</div>
		 -->
		<!-- <div class="xy"> -->
		   <!-- <p><input name="" type="checkbox"class="ty_check"/> 我已阅读并同意<a href="#">《中军创云易平台会员注册服务协议》</a></p> -->
		<!-- </div> -->
		<div class="loepr">
		<div CLASS="log_cem">
			<div class="reg_btn">
			   <input type="button" class="ty_btn" value="立即注册" onclick="register()"/>
			   <input type="button" class="ty_btn" value="立即下载" onclick="toLoad()" style="margin-top: 10px"/>
			   <label id="error"></label>
			</div> 
		</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
	/* window.open('dl.html', '_self') */
	var flag = true;
	function register(){
			// var recommended_code = $("#recommended_code").val();
			var mobile=$("#mobile").val();
			var nickname = $("#nickname").val();
			var vercode = $("#vercode").val();
			//var password = $("#password").val();
			// var confrim_psd = $("#confrim_psd").val();
			if(""==nickname){
				$("#error").html("请输入用户名");
				return;
			}
			if(""==mobile){
				$("#error").html("请输入手机号");
				return;
			}
			if(""==vercode){
				$("#error").html("请输入验证码");
				return;
			}
			/* if(""==password){
				$("#error").html("请输入密码");
				return;
			}
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
			if(!reg.test(password)){
				$("#error").html("密码必须是6-10位的数字和字母组合");
				return;
			} */
			var password= "zhongjunchuangya1212123456";
			if(flag){
				flag = false;
				$.post("https://zjc1518.com/aosuite/notokenapi/app/v1/web_register.jhtml",{
					// recommended_code:recommended_code,
					mobile:mobile,
					nickname:nickname,
					code:vercode,
					password:password,
					type:"register"
				},function(data,status){
					if(data.code==1){
						$("#error").html("注册成功");
						window.location.href = 'https://zjc1518.com/aosuite/notokenapi/app/v1/toWebLoadPage.jhtml';
						//window.open('http://localhost:10010/aosuite/notokenapi/app/v1/toWebLoadPage.jhtml', '_self')
					}else{
						$("#error").html(data.msg);
						flag = true;
					}
				});
			}
	}
	//挑战到下载页面
	function toLoad(){
		window.location.href = 'https://zjc1518.com/aosuite/notokenapi/app/v1/toWebLoadPage.jhtml';
	}
		
		//获取验证码
		$("#getCode").click(function (){
			var mobile = $("#mobile").val();
			if(mobile == ""){
				$("#error").html("请输入手机号！");
				return;
			}else{
				$.ajax({
					url:"https://zjc1518.com/aosuite/notokenapi/app/v1/sendSMS.jhtml",
					data:{
						"mobile":mobile,
						"sms_type":"register"
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
</script>

