<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String ctx=request.getContextPath();%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>中军创云易</title>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxstore/index.css"/> 
	<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script> 
	<script src="../../../static/js/wxstore/mobile.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>  
	<div class="zhe_make">
		<div class="zhe_sum clearfix"><p class="ove_sums fl">数 量：</p>
			<input type="hidden" name="pin_order_id" value="${pin_order_id }" />
			<div class="gw_num fl">
				<em class="jian">-</em>
				<input type="text" name="goodsNum" value="1" class="num" readonly="readonly"/>
				<em class="add">+</em> 
				<div class="clear"></div>
			</div>
		</div>
		<!-- <div class="zhe_sum clearfix"><p class="ove_sums fl">备 注：</p>
			<div class="gw_num fl">
				<input type="text" name="user_note" />
				<div class="clear"></div>
			</div>
		</div> -->
		<div class="to_address"><a href="javascript:void(0);">设置地址/密码</a></div>
		<!-- address start -->
		<div id="check_address">
			<input type="hidden" name="address_id" />
			<c:forEach items="${addressList }" var="address">
				<div class="address_yes">
				  <div class="add_ad clearfix"> <p class="add_name_id fl">收货人:${address.consignee }</p> <p class="add_phone fr">${address.mobile }</p></div>
				  <div class="add_ad clearfix"> <p class="add_na fl"><span>收货地址:</span>${address.address_info }</p> <p class="fr">
				  <c:if test="${address.is_default == 1 }">
					  <img id="${address.address_id }" src="../../../static/image/wxstore/sadd.png" class="span_img"/>
					  <script type="text/javascript">
					  		$("input[name='address_id']").val(${address.address_id });
					  </script>
				  </c:if>
				   <c:if test="${address.is_default != 1 }">
					  <img id="${address.address_id }" src="../../../static/image/wxstore/no_check.png" class="span_img"/>
				  </c:if>
				  </p></div>
				</div>
			</c:forEach>
		</div>
		<!-- address end -->
	</div>  
	<div class="container classSearch">
    	<div class="classSearch-box bg-fff border-box">
    		<div class="inputBox flex-nowrap" id="classSearch" data-busy="0">
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<input class="i-text" id="classInput" type="password" autocomplete="off" required="required" value="" name="pay_psd" data-role="sixDigitPassword" tabindex="" maxlength="6" minlength="6" aria-required="true">
    		</div>
    	</div>
	</div>
	<div class="go_buy"><a href="javascript:void(0);">立即购买</a></div>
	<div class="join_all">
		<div class="join_pwd">
			<p class="jon_pwd center">购买成功！</p>
			<p class="jon_pwd center">请下载APP查看订单详情。</p>
			<div class="jon_btns_pwd">
				<button class="sure_order">确定</button>
			</div>
		</div>
	</div>
	<div class="all_zhe"></div> 
	<script src="../../../static/js/wxstore/password.js"></script>
	<script type="text/javascript">
		$(".span_img").click(function(e){
			$(".span_img").attr('src',"../../../static/image/wxstore/no_check.png")
			$(e.target).attr('src',"../../../static/image/wxstore/sadd.png");
			$("input[name='address_id']").val($(e.target).attr('id'));
		});
		
		$(".go_buy").click(function(){
			var address_id = $("input[name='address_id']").val();
			var price = '${market_price}';
			var goodsNum = $("input[name='goodsNum']").val();
			var total_amount = price * goodsNum;
			var Goods = '[{"goods_id":"${goods_id}","spec_key_name":null,"spec_key":null,"goods_num":'+ goodsNum +'}]';
			var user_note = "";
			var pin_order_id = $("input[name='pin_order_id']").val();
			var pay_psd = $("input[name='pay_psd']").val();
			if(address_id != ""){
				if(pay_psd != ""){
					$.ajax({ 
						url: "pinOrderSub.jhtml",
						type: 'POST',
						data:{
							user_id : '${user_id}',
							address_id : address_id,
							total_amount : total_amount,
							store_id : '${store_id}',
							pay_type : 'rate',
							user_note : user_note,
							Goods : Goods,
							pin_order_id : pin_order_id,
							is_shop_group : '1',
							pay_password : pay_psd
						},
						success: function(data){
							var obj = JSON.parse(data);
							if(obj.code == 1){
								 $(".join_all,.all_zhe").show();
								 $(".join_pwd").show();
								 $(".sure_order").click(function(){
									window.location.href = "initSpellShare.jhtml?goods_id=${goods_id}&pin_order_id=" + obj.data.pin_order_id;
								})
							}else{
								alert(obj.msg)
							}
						}
					}); 
				}else{
					alert("请输入支付密码！");
				}
			}else{
				alert("请选择收货地址!");
			}
		})
		
		$(".to_address").click(function(){
			location.href="initSetAddress.jhtml?goods_id=${goods_id}&register_type=${register_type}&pin_order_id=" + $("input[name='pin_order_id']").val();
		})
		
	</script>
</body>
</html>