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
<title>商品详细</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/detail_index.css" />
<link rel="stylesheet"
	href="../../../static/css/wxactivity/swiper-3.4.2.min.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="../../../static/js/wxactivity/pick-pcc.min.1.0.1.js"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<div class="content">
	<!-- <div class="detail a1"></div> -->
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
		<!-- 	¥${goodDetail.shop_price }+
			<fmt:formatNumber type="number" value="${goodDetail.market_price }"
				maxFractionDigits="0" />
			券</span>
			 -->
		<p class="goods_title_ll">累计<span><fmt:formatNumber type="number" value="${goodDetail.market_price }"	maxFractionDigits="0" /></span>助力积分免费领取</p>		
		<c:if test="${openid != null && openid != ''}">
			<span class="shareHelp fr"> <img id="share_img"
				src="../../../static/image/wxstore/share_1.png">
			</span>
		</c:if>
		<ul class="a2-3 overt">
			<li>库存：<span id="num">${goodDetail.store_count } </span></li>
			<li class="a2_li">已售：<span id="sale">${goodDetail.sales_sum }
			</span></li>
			<li>审核员：<span id="user">${goodDetail.aduit_user_name }</span></li>
		</ul>
	</div>
	<div class="detail a3 a3-a">
		<p>商品详情</p>
	</div>
	<div id="goodsContent" class="detail img1"></div>
</div>
<div class="details-foot clearfix">
	<c:if test="${direct== false }">
		<div class="details-foot1">
			<a href="goInit.jhtml?goods_id=${goodDetail.goods_id }&goods_img=${goodDetail.original_img}">参加“助力”活动</a>
		</div>
	</c:if>
	<c:if test="${direct== true }">
		<div class="detabtn_one fl">
			<a href="initShareFriends.jhtml">已助力好友</a>
		</div>
		<c:if test="${isShareGoods == true }">
			<div class="detabtn_two fl">
				<a href="initLogin2.jhtml?goods_id=${goodDetail.goods_id}">立即兑换</a>
			</div>
		</c:if>
		<c:if test="${isShareGoods == false }">
			<div class="detabtn_two fl" style="background-color:#b3b3b3;">
				<a href="javascript:void(0);">立即兑换</a>
			</div>
		</c:if>
	</c:if>
</div>

<script type="text/javascript"
	src="../../../static/js/wxactivity/swiper.min.js"></script>
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
		$('#goodsContent').html(str);
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
			window.location.replace("initGoBuy.jhtml?store_id=${goodDetail.store_id }&goods_id=${goodDetail.goods_id }&market_price=${goodDetail.market_price }");
		})
	</script>
<script>  
		//查找屏幕高度
		//图片数量
		var imgLength=$(".swiper-slide").length;
		$(".gdp-info-num span.sw_sum").text(imgLength);  
		//	查看全部图片 滑动数字联动
		var mySwiper = new Swiper('.swiper-container', {
			pagination:'.swiper-pagination', 
			paginationClickable: true,
			//回调函数  
			onSlideChangeStart: function(swiper){
				//因index是从0开始  所以+1
				$(".gdp-info-num span").text(swiper.activeIndex+1);
		   }
		});
		<!-- 地址 -->
        $(".pick-area1").pickArea();		 
	</script>
</body>
</html>