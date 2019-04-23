<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>注册</title>
    <script src="${cxt}/aos/static/js/hl/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cxt}/aos/static/js/hl/mobile.js" type="text/javascript" charset="utf-8"></script>  
    <link rel="stylesheet" type="text/css" href="${cxt}/aos/static/css/hl/index.css"/>    
	<style> 
	</style>	
</head>
<body> 
  <div class="container bgf2">
     <div class="log mgauto">
	    <img src="${cxt}/aos/static/image/hl/log.png" class="img100"/>
	 </div>
     <div class="reg_user clearfix bgfff">
	    <div class="reg_side fl font50 right" >手机号</div>
	    <div class="reg_cont fl"><input type="text" id="phone"/></div>
	      <div class="reg_side fl font50 right" >密       码</div>
	    <div class="reg_cont fl"><input type="password" id="password"/></div>
	 </div>
	 <div class="reg_btn mgauto">
	   <button onclick="subForm();">登录</button>
	   <label id="errorMsg"></label>
	 </div>
  </div>
  <div class="store_ok"></div>		
</body>
<script>		
/* function alert_msg(data){
		$(".store_ok").html(data);
		$(".store_ok").show().delay(500).hide(300);  
	} */
		function subForm(){
		
			if($("#phone").val() == '' || $("#phone").val() == null){
				$("#errorMsg").html("登录账号必填");
				return;
			} 
			if($("#password").val() == '' || $("#password").val() == null){
				$("#errorMsg").html("登录密码必填");
				return;
			} 
			
			$.ajax({
				url:"storeLogin.jhtml",
				data: {
					phone:$("#phone").val(),
					password:$("#password").val()
				},
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data.code == 1){
						window.location.href = "hlIndex.jhtml?user_id="+data.data.id;
					}else{
						$("#in_account").val(data.data.username);
						$("#in_password").val(data.data.ip_address);
						$("#errorMsg").html(data.data.again_content);
					}
				}
			});
		}
	</script>
</html>