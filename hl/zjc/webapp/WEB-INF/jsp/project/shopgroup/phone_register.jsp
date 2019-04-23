<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>用户验证</title>
<link href="../../../static/css/qrcode/share_regit.css" rel="stylesheet" type="text/css"/>  
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<body>
	<div class="container">   
		   <div class="share_head">
		   </div>
		   <div class="share_main">
			  <div class="sha_one">
				  <div class="sha_one_cen"><img src="../../../static/qrcode/img/sh_login.png"/></div>
			  </div>
			  <div class="sha_two">
				 <!-- 手机号 -->
				 <div class="sha_mobile">
					<div class="sh_m_side fl">
					  <p class="fl sh_side_img"> <img src="../../../static/qrcode/img/sh_mob.png" width="22" height="23"/></p>
					  <p class="sh_te_mob fl color0c">手机号</p><div class="clear"></div>
					</div>
					<div class="sh_m_cont fr" style="overflow:hidden">
					<select id="quhao" name="support_country" style="float:left;width:25%;height:2.5rem;line-height:2.5rem;">
					</select>
					<input type="text" name="mobile" class="sh_input" placeholder="输入手机号码"  style="float:left;width:70%;margin-left:5px"/>
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
					   <input type="text" name="code" class="flsh_in_tex" placeholder="输入验证码" />
						<input type="button" value="获取验证码" id="getCode" class="sh_hq"/>
					</div>				
					<div class="clear"></div>			 
				 </div>
			  </div>
			  <div class="sha_tree">
				<button class="sh_btn" >确定</button>
				 <p id="error" class="sh_hint"></p>
			  </div>
		   </div>
		</div> 
	<!-- 加载更多 -->
	<script src="../../../static/js/redpacket/quhao.js" type="text/javascript" charset="utf-8"></script>
	<script>
	   var register_type = "${register_type}";
	   $(".sh_btn").click(function(){
		   var mobile = $("input[name='mobile']").val();
			var valiCode = $("input[name='code']").val();
			if(validateMobile(mobile)){
				if(validateCode(valiCode,"验证码")){
					//调用手机登录接口
					$.ajax({
						url : "shopGroupRegister.jhtml",
						type : 'POST',
						data : {
							mobile : mobile,
							code : valiCode,
							openid : '${openid}',
							type : 'register'
						},
						success : function(data) {
							var obj = JSON.parse(data);
							if (obj.code == 1) {
								if(register_type == "normal"){
									window.location.href = "initGoBuy.jhtml?goods_id=${goodDetail.goods_id}";
								}
								if(register_type == "spell"){
									window.location.href = "initSpellGoBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id=${pin_order_id}";
								}
							} else if(obj.code == 10170){
								if(register_type == "normal"){
									window.location.href = "initGoBuy.jhtml?goods_id=${goodDetail.goods_id}";
								}
								if(register_type == "spell"){
									window.location.href = "initSpellGoBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id=${pin_order_id}";
								}
							} else if(obj.code == 10190){
								if(register_type == "normal"){
									window.location.href = "initGoBuy.jhtml?goods_id=${goodDetail.goods_id}";
								}
								if(register_type == "spell"){
									window.location.href = "initSpellGoBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id=${pin_order_id}";
								}
							}else{
								alert(obj.msg);
							}
						}
					}); 
				}
			}
	   })
	   $(".sh_hq").click(function(){
		   var mobile = $("input[name='mobile']").val();
		   var quhao = $("#quhao").val();
			if(validateMobile(mobile)){
				//调用接口
				$.ajax({
					url : "../../../wxact/activity/v1/wxShareSendMessage.jhtml",
					type : 'POST',
					data : {
						mobile : mobile,
						sms_type : 'register',
						country_code : quhao
					},
					success : function(data) {
						if (data.code == 1) {
							alert("发送成功！");
						} else {
							alert(data.msg);
						}
					}
				});
			}
	   })
	   //手机号码验证
		function validateMobile(mobile){
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
			if(mobile != ""){
				/* if(!myreg.test(mobile)) 
				{ 
				    alert('请输入有效的手机号码！'); 
				    return false; 
				}else{
					return true; 
				} */
				return true; 
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
		var options = "<option value='86' selected>中国+86</option>";
		for(var i = 0; i < quhaoArray.length; i++ ){
			options += "<option value='" + quhaoArray[i].num_id + "'>" + quhaoArray[i].country + "+" + quhaoArray[i].num_id + "</option>";
		}
		$("#quhao").html(options);
	</script>
</body>
</html>