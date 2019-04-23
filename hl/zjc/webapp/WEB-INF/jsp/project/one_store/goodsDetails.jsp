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
<link rel="stylesheet" type="text/css" href="../static/css/wxstore/index.css" />
<link rel="stylesheet" href="../static/css/wxstore/swiper-3.4.2.min.css" />
<script src="../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../static/js/wxstore/mobile.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../static/js/wxstore/pick-pcc.min.1.0.1.js"></script>
<style type="text/css">
	.all_zhe{z-index: 9999;position:fixed;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;}
	.my_order_one{background:#fff;z-index:10000;position:fixed;top:25%; width:85%;margin:0 auto;left:8%; padding-top:10%;display:none;box-shadow: 0 0 10px #666;border-radius:15px;}
	.ord_text{color:#333;font-size:0.5rem;}
	.ord_con{border-top:1px solid #e0e0e0;margin-top:0.5rem;}
	.ord_con button{width:48%;font-size:0.55rem;background:#fff;padding:3% 0;}
	.ord_side{color:#808080;border:1px solid #fff;border-right:1px solid #e0e0e0;border-bottom-left-radius:15px;}
	.ord_cont{color:#dd251b;border:1px solid #fff;border-bottom-right-radius:15px;} 
</style>
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
			</p> 
			<ul class="a2-3">
				<li>库存：<span id="num">${goodDetail.store_count } </span></li>
				<li>已售：<span id="sale">${goodDetail.sales_sum } </span></li>
				<li>审核员：<span id="user">${goodDetail.aduit_user_name }</span></li>
			</ul>
		</div>
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
	<div class="my_order_one">
		<p class="ord_text center">
			该商品为平台专属商品<br />请下载APP进行购买
		</p>
		<div class="ord_con clearfix">
			<button class="ord_side">取消</button>
			<button class="ord_cont">下载</button>
		</div>
	</div>
	<div class="all_zhe"></div>
	<script type="text/javascript"
		src="../static/js/wxstore/swiper-3.4.2.jquery.min.js"></script>
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
			$(".all_zhe,.my_order_one").show();
		})
		 <!-- 点击取消关闭 -->
	   $(".ord_side").click(function(){
		$(".all_zhe,.my_order_one").hide();
	   })
	   //下载 
	    $(".ord_cont").click(function(){
		 window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.seventc.zhongjunchuang";
	   })
		</script>
</body>
</html>
