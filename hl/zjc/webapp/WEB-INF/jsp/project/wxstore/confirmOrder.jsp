<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title>确认下单</title>
        <script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="../../../static/js/wxstore/clipboard.min.js" type="text/javascript" charset="utf-8"></script> 
		<style>
			*{
				padding:0;
				margin:0;
				box-sizing: border-box;
				font-size:14px;
				color:#666666;
			}
			body{
				background:#eeeeee;
			}
			.state{
				padding:15px;
				display:flex;
				justify-content: center;
				align-items: center;
				width:100%;
				flex-direction: column;
				background:#fff;
				
			}
			.state img {
				width:30px;
			}
			.state p{
				margin-top:10px;
				color:#DA707D;
			}
			.ps {
				margin-top:1px;
				background:#fff;
				height:40px;
				display:flex;
				align-items: center;
				padding:0 15px;
			}
			.ps img {
				width:15px;
			}
			.ps  span{
				padding-left:10px;
			}
			.productInfo{
				background:#fff;
				margin-top:1px;
				width:100%;
				display:flex;
				align-items: center;
				padding:15px;
			}
			.productInfo .pic {
				width:30%;
				display:flex;
				align-items: center;
				justify-content: center;
			}
			.productInfo .pic img {
				width:100%;
			}
			.productInfo .text {
				width:70%;
				display:flex;
				justify-content: space-between;
				flex-direction: column;
				padding-left:10px;
				height:100%;
			}
			.productInfo .text .title{
				font-size:16px;
				font-weight: bold;
				margin-bottom:20px;
			}
			.productInfo .text .price{
				width:100%;
				display:flex;
				justify-content: space-between;
				margin-bottom:10px;
			}
			.productInfo .text .price .left {
				color:#DA707D;
			}
			.productInfo .text .num{
				width:100%;
				display:flex;
				justify-content: space-between;
			}
			.orderId{
				background:#fff;
				margin-top:4px;
				height:40px;
				display:flex;
				justify-content: space-between;
				align-items: center;
				width:100%;
				padding:0 15px;
			}
			.orderId span {
				padding:6px 12px;
				border:1px solid #DFAB77;
				color:#DFAB77;
				border-radius: 3px;
			}
			.attention{
				height:60px;
				padding:0 15px;
				display:flex;
				align-items: center;
				color:#DFAB77;
			}
			.pay{
				
			}
			.pay .payName{
				height:40px;
				width:100%;
				background:#fff;
				display:flex;
				justify-content:space-around;
				align-items: center;
			}
			.selected{
				color:#DFAB77;
			}
			.payType{
				display:flex;
				justify-content:center;
				align-items: center;
				flex-direction: column;
				padding:15px;
			}
			.payType .payText{
				margin-bottom:15px;
			}
			.weixinContent{
				display:none;
			}
		</style>
		<script>
			$(function(){
				$('.pay .payName>div').click(function(){
					$(this).addClass('selected').siblings().removeClass('selected');
					if($(this).hasClass("zhifubao")){
						$('.pay .payCode .zhifubaoContent').css('display','flex').siblings().hide();
					}else if($(this).hasClass("weixin")){
						$('.pay .payCode .weixinContent').css('display','flex').siblings().hide();
					};
				});
			});
		</script>
	</head>
	<body>
		<div class="state">
			<img src="../../../static/image/wxstore/drop/ic_finish.png"/>
			<p>恭喜你兑换古朴坊原浆酒完成</p>
			<p>券已抵扣成功</p>
		</div>
		<div class="ps">
			<img src="../../../static/image/wxstore/drop/ic_tips.png"/>
			<span>请购买古朴坊盛装酒瓶（含盛装费用）</span>
		</div>
		<div class="productInfo">
			<div class='pic'><img src="../../../static/image/wxstore/drop/img_9.jpg"/></div>
			<div class="text">
				<div class="title">古朴坊盛装酒瓶一斤装*6</div>
				<div class="price">
					<div class="left">优惠价:￥41.88/6个</div>
					<div class="right">总价:￥${total_pz_price}</div>
				</div>
				<div class="num">
					<div class="left">需要酒瓶个数</div>
					<div class="right">X${pz_num}个</div>
				</div>
			</div>
		</div>
		<div class="orderId">
			<div class="left">订单编号：${order_sn}</div>
			<div class="right"><span class="orderSn" data-clipboard-text="${order_sn }">复制</span></div>
		</div>
		<div class="attention">
			注意：请务必将订单编号添加到支付页面的备注中
		</div>
		<div class="pay">
			<div class="payName">
				<div class="zhifubao selected" >支付宝支付</div>
				<div class="weixin">微信支付</div>
			</div>
			<div class="payCode">
				<div class="zhifubaoContent payType">
					<div class="payText">请使用支付宝扫描二维码</div>
					<div class='pic'><img src="https://zjc1518.com/aosuite/static/image/alipay.jpg" style="width:100%;height:width:70%"/></div>
				</div>
				<div class="weixinContent payType">
					<div class="payText">请使用微信扫描二维码</div>
					<div class='pic'><img src="https://zjc1518.com/aosuite/static/image/weixinpay.jpg" style="width:100%;height:width:70%"/></div>
				</div>
			</div>
		</div>
			<script>
		var clipboard = new ClipboardJS('.orderSn');
	    clipboard.on('success', function(e) {
	    	alert("复制成功！")  
	    });
	    clipboard.on('error', function(e) {
	    	 alert("复制失败！请手动复制")  
	    });
	</script>
	</body>
</html>
