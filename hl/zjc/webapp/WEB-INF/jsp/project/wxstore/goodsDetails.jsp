<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>中军创云易</title>
<link rel="stylesheet" type="text/css" href="../../../static/css/wxstore/index.css" />
<link rel="stylesheet" href="../../../static/css/wxstore/swiper-3.4.2.min.css" />
<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxstore/mobile.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../../../static/js/wxstore/pick-pcc.min.1.0.1.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="../../../static/js/sha1.js"></script>
<script type="text/javascript" src="../../../static/js/wxconfig.js"></script>
</head>
<body>
	<div class="content">
		<!-- 详情图 -->
		<div class="zjc_debannar">
			<div class="goodsDetail-picture-cont">
				<div class="gdp-info">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<img src="${goodDetail.original_img}" />
							</div>
						</div>
						<!-- <div class="swiper-pagination"></div> -->
						<div class="gdp-info-num">
							<span>1</span>/<span class="sw_sum">1</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="detail a2">
			<p class="a2-1">${goodDetail.goods_name }</p>
			<p class="a2-2 clearfix">
				<span class="a2_span fl">¥${goodDetail.shop_price }+<fmt:formatNumber type="number" value="${goodDetail.market_price }" maxFractionDigits="0"/>券<span></span>
			</span>
			<c:if test="${openid != null && openid != ''}">
				<span class="shareHelp fr">
				<img id="share_img" src="../../../static/image/wxstore/share_1.png">
				</span>
			</c:if>
			</p> 
			<ul class="a2-3">
				<li>库存：<span id="num">${goodDetail.store_count } </span></li>
				<li>已售：<span id="sale">${goodDetail.sales_sum } </span></li>
				<li>审核员：<span id="user">${goodDetail.aduit_user_name }</span></li>
			</ul>
		</div>
		<%-- <div class="detail a3">
			<a href="##">已有${goodDetail.comment_count }条评论 <img
				src="../../../static/image/wxstore/details3.png" /></a>
		</div> --%>
		<%-- <div class="detail a3">
			<img class="detail-logo"
				src="../../../static/image/wxstore/details4.png" />${goodDetail.store_name }
		</div> --%>
		<div class="detail a3 a3-a">
			<p>商品详情</p>
		</div>
		<div id="goodsContent" class="detail img1">
		</div>
	</div>
	<div class="details-foot">
		<div class="details-foot1">
			<a href="javascript:void(0);">立即购买</a>
		</div>
	</div>
	<script type="text/javascript"
		src="../../../static/js/wxstore/swiper-3.4.2.jquery.min.js"></script>
	<script>
	
		//转义取到的html代码
		function htmlspecialchars_decode(str) {
			str = str.replace(/&amp;/g, '&');
			str = str.replace(/&lt;/g, '<');
			str = str.replace(/&gt;/g, '>');
			str = str.replace(/&quot;/g, "'");
			str = str.replace(/&#039;/g, "'");
			return str;
		}
		var str = '${goodDetail.goods_content }';
		//str = htmlspecialchars_decode(str);
		$('#goodsContent').html(str);
		//查找屏幕高度
		//图片数量
		var imgLength = $(".swiper-slide").length;
		$(".gdp-info-num span.sw_sum").text(imgLength);
		//	查看全部图片 滑动数字联动
		var mySwiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			paginationClickable : true,
			//回调函数  
			onSlideChangeStart : function(swiper) {
				//因index是从0开始  所以+1
				$(".gdp-info-num span").text(swiper.activeIndex + 1);
			}
		});
		
		$(".details-foot1").click(function(){
			window.location.replace("initGoBuy.jhtml?goods_id=${goodDetail.goods_id }");
		})
		
		</script>
		<c:if test="${openid != null && openid != ''}">
			<script>
				 wx.ready(function () {
						 wx.onMenuShareAppMessage({
							 title: '分享助力', // 分享标题
							 desc: '为我助力，不花钱购物', // 分享描述
							 //link: 'http://wexin.web.zjc1518.cn/aosuite/notokenapi/wx/v1/initShareHelp.jhtml?goods_id=${goodDetail.goods_id }&share_openid=${openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
							 link: 'https://zjc1518.com/aosuite/notokenapi/wx/v1/initShareHelp.jhtml?goods_id=${goodDetail.goods_id }&share_openid=${openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
							 //link:'https://zjc1518.com/aosuite/notokenapi/wx/v1/initWxZjcLogin.jhtml',
							 //link:'http://www.baidu.com',
							 imgUrl: 'https://pic1.zhimg.com/da8e974dc_s.jpg', // 分享图标
							 //type: '', // 分享类型,music、video或link，不填默认为link
							 //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
							 success: function () {
							 // 用户确认分享后执行的回调函数
							 },
							 cancel: function () {
							 // 用户取消分享后执行的回调函数
							 }
					   });
				}) 
				
				wx.error(function(res){  
				    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
				    alert("微信验证失败");  
				});  
			</script>
		</c:if>
		
</body>
</html>
