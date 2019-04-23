<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="商家后台登录">
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/store/common.css" >
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/store/mct_index.css" >
	<!-- js -->
	<script src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script>
	<script src="${cxt}/static/js/store/mct_index.js"></script>
	<style>
		#vercode{
			margin-left: 20px;
		}
	</style>
	<aos:body>
		<div class="mct_login">
			<div class="mctlo_main">
				<div class="mctlo_title">
					<img src="${cxt}/static/image/mct/mct_login.png" class="mct_lo" alt="logo" title="logo" /> 中军创云易
				</div>
				<div class="mctlo_cont">
				
			<div class="login_top">
					<div class="log_tit">商家管理中心</div>
					<div class="log_conts">
						<div class="log_contside fl">
							<img src="${cxt}/static/image/mct/mst_img.png" alt="logo" title="logo" />
						</div>
	
						<div class="log_contd fr">
							<form id="loginForm">
								<!-- user -->
								<div class="log_user">
									<div class="loguser_side fl">
										<img src="${cxt}/static/image/mct/user_img.png" alt="user" title="user" />
									</div>
									<div class="loguser_cons fr">
										<input type="text" name="account" id="in_account" class="side_user" placeholder="用户名" value="${account }"/>
									</div>
									<div class="clear"></div>
								</div>
								<!-- pwd -->
								<div class="log_pwd">
									<div class="loguser_side fl">
										<img src="${cxt}/static/image/mct/pwd_img.png" alt="pwd" title="pwd" />
									</div>
									<div class="loguser_cons fr">
										<input type="password" name="password" id="in_password" class="side_user" placeholder="密码" value="${password }"/>
									</div>
									<div class="clear"></div>
								</div>
								<!-- 验证码 -->
								<div class="log_yzm">
									<div class="loguser_side_uzm fl"></div>
									<div class="loguser_cons fr">
										<input type="text" name="vercode" class="side_user_one fl" placeholder="验证码" />
										<aos:vercode id="vercode" uuid="${vercode_uuid}" width="90" height="39" fontSize="28" characters="${vercode_characters}" length="${vercode_length}" />
										<input type="hidden" name="vercode_uuid"  id="in_vercode" value="${vercode_uuid}" />
										<div class="clear"></div>
									</div>
									<div class="clear"></div>
								</div>
								<!-- 忘记密码 -->
								<div class="log_yzm">
									<div class="loguser_side_uzm fl"></div>
									<div class="loguser_cons ckec_no fr">
										<div class="text_sides fl">
											<div class="mct_check fl">
												<input type="checkbox"> <label class="dfokgs checked"
													for="nba" name="mck_chil" type="checkbox"></label>
											</div>
											<div class="text_fev fr">记住密码</div>
											<div class="clear"></div>
										</div>
										<div class="sp_right fr">
											<a class="forget_pwd">忘记密码</a>
										</div>
	
									</div>
									<div class="clear"></div>
								</div> 
							</form>
							<!-- 验证码 -->
							<div class="log_yzm">
								<div class="loguser_side_uzm fl"></div>
								<div class="loguser_cons fr">
									<button class="log_btn" onclick="subForm();">登录</button>
									<label id="errorMsg"></label>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					</div>
					<!-- forget pwd  start-->
	   <div class="login_bottom">
	      <div class="find_main_two_cen"> 
			  <div class="file_indexl"> 
				 <div class="reg-steps">
					<ul class="reg-step-1">
						 <li id="step-1" class="step-1"></li> 
						<li id="step-2" style="display:none;"></li> 
						<div class="clear"></div>
					</ul>
				</div>
			</div>
		  <ul class="dsdf">		  
		    <li style="display:block;" id="content1"> 
				<div class="content_pwdtwo">
			      <div class="conpwd_one contlomarg">
					  <div class="pwdone_side fl">
						<p>手机号：</p>
					  </div>
					  <div class="pwdone_cont fl">
						<input type="text" class="pwd_onput" placeholder="请填写注册时的手机号" id="mobile"/>
					  </div>
					  <div class="clear"></div>
				  </div> 
			      <div class="conpwd_one con_sec_pwd contlomarg">
					  <div class="pwdone_side fl">
						<p>验证码：</p>
					  </div>
					  <div class="pwdone_cont pdli fl">
						<input type="text" class="clos_inptu" placeholder="验证码" id="verification_code"/>
						<button class="btn_yzma">获取验证码</button>
					  </div>
					  <div class="clear"></div>
				  </div> 
				<div class="conpwd_one btn_pwds">
				    <button class="btn_sub_two overtbsn_pwd" onclick="next_step()">下一步</button>
				    <button class="seconrtbsn_pwd" onclick="btn_hide()" >取消</button>
				  </div> 
				</div>			 
			</li>
		    <li id="content2">
		 <div class="cont_pwd_tree">
			      <div class="con_tree_one mstc">
				    <div class="con_tree_oneside fl">
					  <p>手 机 号</p>
					</div>
				    <div class="con_tree_onecont fl">
					  <input type="text" class="contre" placeholder="138****8888" id="read_mobile" readonly="readonly"/>			
					</div>
				    <div class="clear"></div>				  
				  </div>	
			      <div class="con_tree_one mstc">
				    <div class="con_tree_oneside fl">
					  <p>设 置 登 录 密 码</p>
					</div>
				    <div class="con_tree_onecont fl">
					  <input type="password" class="contre" placeholder="请输入6-10位字母+数字" id="new_password"/>				
					</div>
				    <div class="clear"></div>				  
				  </div>	
			      <div class="con_tree_one">
				    <div class="con_tree_oneside fl">
					  <p>确 认 登 录 密 码</p>
					</div>
				    <div class="con_tree_onecont fl">
					  <input type="password" class="contre" placeholder="请再次输入你的登录密码" id="new_password_1"/>					
					</div>
				    <div class="clear"></div>				  
				  </div>
					<div class="contr_btn">
					   <button class="vconty" onclick="confirm_reset()">确定</button>
					   <button class="over_end" onclick="btn_hide()">取消</button>
					</div>	 
			   </div> 				 
			  </li>  
			<div class="clear"></div>
		  </ul> 
		  </div>  	   
	   </div>
	   <!-- forget pwd  end-->
				</div> 				
				<div class="login_foot">
					<p>© 2017-2025 北京中军创云易物联网科技有限公司 版权所有</p>
				</div>
			</div>
		</div>
		<div class="store_ok"></div>		
	</aos:body>
	<script>		
	   	function alert_msg(data){
	   		$(".store_ok").html(data);
	   		$(".store_ok").show().delay(500).hide(300);  
	   	}
		function subForm(){
			if($("#in_account").val() == '' || $("#in_account").val() == null){
				$("#errorMsg").html("登录账号必填");
				return;
			}
			if($("#in_password").val() == '' || $("#in_password").val() == null){
				$("#errorMsg").html("登录密码必填");
				return;
			}
			if($("#in_vercode").val() == '' || $("#in_vercode").val() == null){
				$("#errorMsg").html("获取验证码失败，请待验证码加载成功后再尝试登陆");
				return;
			}
			$.ajax({
				url:"storeLogin.jhtml",
				data:$("#loginForm").serialize(),
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data.code == 1){
						window.location.href = "homepage.jhtml";
					}else{
						$("#in_account").val(data.data.username);
						$("#in_password").val(data.data.ip_address);
						$("#errorMsg").html(data.data.again_content);
					}
				}
			});
		}
		function btn_hide(){
			$(".login_bottom").hide();
			$(".login_top").show();
		}
		//获取验证码
		$(".btn_yzma").click(function (){
			var mobile = $("#mobile").val();
			if(mobile == ""){
				alert_msg("请输入手机号！");
				return;
			}else{
				$.ajax({
					url:"getVerificationCode.jhtml",
					data:{
						"mobile":mobile
					},
					success:function(data){
						console.log(data.code);
						if(data.code != 1){
							alert_msg(data.msg);
						}else{
							alert_msg("验证码已发送！");
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
		//下一步
		function next_step(){
			$("#read_mobile").val($("#mobile").val());
			var mobile = $("#read_mobile").val();
			var verification_code = $("#verification_code").val();
			if(mobile == "" || verification_code == ""){
				return;
			}else{
				$.ajax({
					url:"validate_code.jhtml",
					data:{
						"mobile":mobile,
						"code":verification_code
					},
					success:function(data){
						if(data.code == 1){
							$("#content1,#step-1,.sdfop").hide();
							$("#content2,#step-2").show(); 
						}else{
							alert_msg(data.msg);
						}
					},
					error:function(){
						alert_msg("您的操作太频繁，请稍后再试！");
					}
				});						
			} 
		}
		//确认新密码
		function confirm_reset(){
			var new_password = $("#new_password").val();
			var new_password_1 = $("#new_password_1").val();
			var mobile = $("#read_mobile").val(); 
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
			if(new_password != new_password_1){
				alert_msg("两次输入的密码不一致");
			} else if(!reg.test(new_password)){
				alert_msg("新密码必须是6-10位的数字和字母组合");
			} else if(mobile == '' || mobile == null){
				alert_msg("电话号码必填");
			}else{
				$.post("confirm_reset.jhtml",{
					"mobile":mobile,
					"new_password":new_password
				}, function(data){
					alert_msg(data.msg);
					$(".login_bottom").hide();
					$(".login_top").show();
				});
			}
		}
		
		$(".forget_pwd").click(function(){
			$(".login_bottom").show();
			$(".login_top").hide();
		}); 
		
	</script>
</aos:html>