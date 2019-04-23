<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>中军创云易</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxstore/index.css" />
<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxstore/mobile.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="../../../static/js/wxstore/pick-pcc.min.1.0.1.js"></script>
	<style>
	.pay_tel_cont_set{width:70%;}
	.pay_tel_cont_set select{width:35%;height:1rem;}
	.pay_tel_cont_set input{width:55%;line-height: 1.5rem;border:none;font-size:0.4rem;}
	</style>
</head>
<body>
	<div class="pay_name clearfix">
		<div class="payside fl">
		<span class="clearfix">
			<span class="pat_img fl"><img src="${userInfoPO.head_pic }" /></span>
			<span class="pat_span fl"> 
			<span>${userInfoPO.nickname }</span><br/>
			 <span>会员ID:${userInfoPO.user_id }</span></span>
		</span>
		</div>
		<div class="paycont fr">可用易物券:${zjcUsersAccountInfoPO.pay_points }</div>
	</div>

	<div class="tab_order clearfix">
		<ul class="ts_order clearfix">


			<li  class="hit_order">设置地址</li>
			<li>设置密码</li>
		</ul>
		<div class="pane_order">
			<div class="panes_order" style="display: block;">
				<div class="pay_main">
					<div class="pay_one">
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">收 货 人</div>
							<div class="pay_tel_cont fl">
								<input type="text" name="consignee" placeholder="请输入收货人姓名" />
							</div>
						</div>
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">联系电话</div>
							<div class="pay_tel_cont fl">
								<input type="text" name="address_mobile" placeholder="请输入联系电话" />
							</div>
						</div>
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">省 市 区</div>
							<div class="pay_tel_cont fl">
								<a href="javascript:void(0)" id="pick-area"
									class="pick-area pick-area1" style="width: 100%;"></a>
							</div>
						</div>
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">详细地址</div>
							<div class="pay_tel_cont fl">
								<input type="text" name="address" placeholder="请输入详细地址" />
							</div>
						</div>
					</div>
				</div>

				<div class="set_address" onclick="saveAddress();">
					<a href="javascript:void(0);">保存收货地址</a>
				</div>
			</div>
			<div class="panes_order">
				<div class="pay_main">

					<div class="pay_one">
						
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">手机号</div>
							<div class="pay_tel_cont_set fl">
							<select id="quhao" name="support_country" >
							</select>
								<input type="text" name="mobile" placeholder="请输入手机号" />
							</div>
						</div>
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">验证码</div>
							<div class="pay_tel_cont_yzm fl">
								<input type="text" name="code" class="paty" placeholder="请输入验证码" />
								<button class="te_yzm" onclick="sendMsg();">获取验证码</button>
							</div>
						</div>
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">支付密码</div>
							<div class="pay_tel_cont fl">
								<input type="password" name="pay_password"
									placeholder="请输入6位数字密码" />
							</div>
						</div>
						<div class="pay_tel clearfix">
							<div class="pay_tel_side fl">确认密码</div>
							<div class="pay_tel_cont fl">
								<input type="password" name="again_pay_password"
									placeholder="请再次输入密码" />
							</div>
						</div>
					</div>
				</div>
				<div class="set_pwd" onclick="sub();">
					<a href="javascript:void(0);">确认</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="../../../static/js/redpacket/quhao.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(".pick-area1").pickArea();
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
	
	function formCheck(consignee,mobile,area_info,address){
		if(validateCode(consignee,"收货人")){
			if(validateMobile(mobile)){
				if(validateCode(area_info,"地区")){
					if(validateCode(address,"详细地址")){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	//保存收货地址
	function saveAddress() {
		var consignee = $("input[name='consignee']").val();
		var mobile = $("input[name='address_mobile']").val();
		var area_info = $("input[class='pick-area']").val();
		var address = $("input[name='address']").val();
		var register_type = "${register_type}";
		if(formCheck(consignee,mobile,area_info,address)){
			var address_info = area_info + address;
			$.ajax({
				url : "saveAddress.jhtml",
				type : 'POST',
				data : {
					consignee : consignee,
					mobile : mobile,
					area_info : area_info,
					address : address,
					address_info : address_info
				},
				success : function(data) {
					var obj = JSON.parse(data);
					if (obj.code == 1) {
						alert("保存成功！");
						if(register_type == "spell"){
							window.location.href = "initSpellGoBuy.jhtml?goods_id=${goods_id}&pin_order_id=${pin_order_id}";
						}
						if(register_type == "normal"){
							window.location.href = "initGoBuy.jhtml?goods_id=${goods_id}";
						}
					} else {
						alert(obj.msg);
					}
				}
			});
		}
	}
	$('.tab_order ul.ts_order li').click(
		function() {
			$(this).addClass('hit_order').siblings().removeClass(
					'hit_order');
			$('.pane_order>div:eq(' + $(this).index() + ')').show()
					.siblings().hide();
		});
	function sendMsg() {
		var phone_num = $("input[name='mobile']").val();
		var quhao = $("#quhao").val();
		if (phone_num != "") {
			$.ajax({
				url : "../../../notokenapi/app/v1/sendSMS.jhtml",
				type : "POST",
				data : {
					sms_type : 'pay_password',
					mobile : phone_num,
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
		} else {
			alert("请输入电话号码！")
		}

	}
	function sub() {
		$.ajax({
			url : "updatePayPsd.jhtml",
			type : 'POST',
			data : {
				type : "pay_password",
				mobile : $("input[name='mobile']").val(),
				code : $("input[name='code']").val(),
				pay_password : $("input[name='pay_password']").val()
			},
			success : function(data) {
				var obj = JSON.parse(data);
				if (obj.code == 1) {
					alert("修改成功！")
				} else {
					alert(obj.msg)
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