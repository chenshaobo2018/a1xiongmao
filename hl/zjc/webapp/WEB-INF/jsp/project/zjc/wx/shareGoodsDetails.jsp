<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="详情" base="http">
<link href="${cxt}/static/css/shareGoods/zjc_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${cxt}/static/css/shareGoods/swiper-3.4.2.min.css" />

<aos:body>
	<!-- 整个主体 -->
	<div class="container">
		<div class="share_head clearfix">
			<div class="share_h_side fl">
			</div>
			<div class="share_h_cont fl">商品详情</div>
		</div>
		<div class="zjc_detail_main">
			<div class="zjc_dm_cen">
				<!-- 详情图 -->
				<div class="zjc_debannar">
					<div class="goodsDetail-picture-cont">
						<div class="gdp-info">
							<div class="swiper-container">
								<div class="swiper-wrapper">
									<div class="swiper-slide">
										<img src="${goodDetail.original_img }" />
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
				<!-- 商品详情 -->
				<div class="zjc_det">
					<div class="zjc_det_one colorfff">
						<p class="zjo_text">${goodDetail.goods_name }</p>
						<p class="zjo_text_one bold">¥${goodDetail.shop_price } + ${goodDetail.market_price }券 </p>
						<div class="zjs_two">
							<ul class="clearfix">
								<li class="zjs_lione">库存:${goodDetail.store_count }</li>
								<li class="zjs_litwo">已售:${goodDetail.sales_sum }</li>
								<li class="zjs_litree">审批员:${goodDetail.aduit_user_name }</li>
							</ul>
						</div>
					</div>
					<div class="zjc_det_two colorfff clearfix">
						<div class="zjcdt_side fl color656565">已有${goodDetail.comment_count }条评论</div>
						<div class="zjcdt_cont fr">
							<img src="${cxt }/static/image/shareGoods/plr.png" class="zkd_im"/>
						</div>
					</div>
				</div>
				<!-- 详情zjc_det -->
				<div class="zjc_addres">
					<div class="zjc_addres_title">商品详情</div>
					<div id="goodsContent" class="zjc_addres_content colorfff">
					</div>
				</div>
			</div>
		</div>
		<!-- 购物车 -->
		<div class="div_fixed">
			<div class="fix_cont">
				<button class="fixbtn_two" onclick="buyBtn();">立即购买</button>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${cxt}/static/js/shareGoods/jquery.min.js"></script>
	<script type="text/javascript"
		src="${cxt}/static/js/shareGoods/swiper-3.4.2.jquery.min.js"></script>
	<script>
		//转义取到的html代码
		function htmlspecialchars_decode(str){           
			 str = str.replace(/&amp;/g, '&'); 
             str = str.replace(/&lt;/g, '<');
             str = str.replace(/&gt;/g, '>');
             str = str.replace(/&quot;/g, "'");  
             str = str.replace(/&#039;/g, "'");  
             return str;  
		}
	    var str = "${goodDetail.goods_content }";
	    str = htmlspecialchars_decode(str);
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
		function buyBtn(){
			window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.seventc.zhongjunchuang";
		}
	</script>
</aos:body>
</aos:html>


