<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>参与拼单</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/shopgroup/index.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/shopgroup/swiper.min.css">
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/shopgroup/mobile.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/handlebars.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../../../static/js/shopgroup/dateformat.js"
	type="text/javascript" charset="utf-8"></script>

<style>
.swiper-container {
	height: 2.65rem;
	overflow: hidden;
	z-index: 0;
}

.sp_foot {
	z-index: 1;
}

@media screen and (max-width: 1080px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width: 1024px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width: 768px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width:600px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width:414px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width:412px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width:375px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width: 360px) {
	.swiper-container {
		height: 2.65rem;
	}
}

@media screen and (max-width: 320px) {
	.swiper-container {
		height: 3rem;
	}
}

.swiper-slide {
	height: auto;
	border-bottom: 1px solid #efefef;
	padding: 0.2rem 0;
}

.time span {
	font-size: 12px;
}

.swip_onecont {
	margin-right: 0.2rem;
	width: 50%;
}

.swip_oneside {
	margin-left: 0.2rem;
	width: 40%;
}

.swips_cont button {
	font-size: 0.35rem;
	padding: 0.1rem;
}

.swips_side {
	margin-right: 0.15rem;
}

.swips_side p {
	font-size: 0.35rem;
}

.spdetit_cont {
	margin-right: 0.2rem;
}
.sp_text_one span.sp_text_span{text-decoration:line-through;color:#ccc;}
</style>
<script type="text/x-handlebars-template" id="pintuanList">
		{{#each this}}
			<div class="swiper-slide clearfix">
    			 <div class="swip_oneside fl">
					   <p class="swip_img"><img  src="{{head_img}}"/> {{nike_name}}</p>
					 </div>

				     <div class="swip_onecont fr">
					   <div class="swips_side fl">
					     <p>已加入[拼团]{{sum}}人</p>
					     <p>
							<span class="{{pin_order_id}}t_h">00:</span>
        					<span class="{{pin_order_id}}t_m">00:</span>
        					<span class="{{pin_order_id}}t_s">00:</span>
							<span class="{{pin_order_id}}h_s">00:</span>
 						</p>
					   </div>
					   <div class="swips_cont fl">
						<button id="{{pin_order_id}}" class="btng">参与拼单</button>					   
					   </div>
					   <div class="clear"></div>
					 </div> 
			</div> 
		{{/each}}
	</script>
</head>
<body>
	<div class="spe_container">

		<div class="sp_detail center">商品详情</div>
		<div class="img_div">
			<img src="${goodDetail.original_img }" />
		</div>
		<div class="sp_detone">
			<p class="sp_text">${goodDetail.goods_name }</p>
			<p class="sp_text_one">
				 <c:if test="${goodDetail.market_price == '0.00'}">							 
				  ¥ ${goodDetail.shop_group_price } <span class="sp_text_span">¥ ${goodDetail.shop_group_market_price }</span>
				 </c:if>
				 <c:if test="${goodDetail.market_price != '0.00'}">		
				  ${goodDetail.shop_group_market_price }<span>券</span> <span class="sp_text_span">¥ ${goodDetail.market_price}<span>券</span></span>	
				 </c:if>				
			</p>
			<ul class="sp_ul clearfix">
				<li>库存：<span id="num">${goodDetail.store_count } </span></li>
				<li class="a2_li">已售：<span id="sale">${goodDetail.sales_sum }
				</span></li>
				<li>审核员：<span id="user">${goodDetail.aduit_user_name }</span></li>
			</ul>
			<div class="div_tse">
				限购次数 <span class="sp_cum">${goodDetail.today_limit_times}</span><span
					class="sp_left">今日每人限购 </span><span class="sp_cum">${goodDetail.today_limit_nums }</span>
			</div>
		</div>

		<!-- 拼团信息 start-->
		<div class="spde_cont_one">
			<div class="spde_tit clearfix">
				<p class="fl">
					<span id="pintuanPerson">0</span>人正在拼团
				</p>
				<p class="spdetit_cont fr">
					<!-- <a>查看更多 ></span> -->
				</p>
			</div>
			<div class="spde_content">
				<div class="swiper-container">
					<div class="swiper-wrapper" id="pintuanlist"></div>
				</div>
			</div>
		</div>
		<!-- 拼团信息 end-->


		<div class="sp_detfive">
			<div class="spd_tit center">产品详情</div>
			<div id="goodsContent" class="spd_content"></div>
		</div>
		<!-- 拼单店铺 start-->
		<div class="sp_foot">
			<div class="spfoot_cont">
				<ul>
					<li class="pr_foot_one_de"><button onclick="normalBuy();">
							立即购买<br />
							 <c:if test="${goodDetail.market_price =='0.00'}">							 
							      <span>¥ ${goodDetail.shop_price }</span>	  
							 </c:if>
							 <c:if test="${goodDetail.market_price != '0.00'}">							 
							       <span>${goodDetail.market_price }</span><span>券</span>		
							 </c:if>							
						</button></li>
					<li class="pr_foot_two_de"><button onclick="zeroBuy();">
							发起拼单<br />	
							 <c:if test="${goodDetail.market_price == '0.00'}">							 
								<span>¥ ${goodDetail.shop_group_price }</span>	 
							 </c:if>
							 <c:if test="${goodDetail.market_price != '0.00'}">							 
							      <span>${goodDetail.shop_group_market_price }</span><span>券</span>	
							 </c:if>							  
						</button></li>
				</ul>
			</div>
		</div>
		<!-- 拼单店铺 end-->
	</div>
	<script src="../../../static/js/shopgroup/swiper.min.js"></script>
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
		var goods_id = '${goodDetail.goods_id}';
		var setData = new Array();
		$
				.post(
						"../../../notokenapi/app/v1/queryPinTuanInvitation.jhtml",
						{
							"goods_id" : goods_id,
							"is_head" : "1"
						},
						function(data) {
							$("#pintuanPerson").text(data.data.length);
							var temeplte = Handlebars.compile($("#pintuanList")
									.html());
							$("#pintuanlist").html(temeplte(data.data));
							$(".btng")
									.click(
											function(e) {
												var pin_order_id = $(e.target)
														.attr('id');
												window.location.href = "initSpellBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id="
														+ pin_order_id;
											})
							var swiper = new Swiper('.swiper-container', {
								direction : 'vertical',
								slidesPerView : 'auto',
								mousewheelControl : true,
								freeMode : true,
								autoplay : true,
								observer : true,
								observeParents : true
							});
							for (var i = 0; i < data.data.length; i++) {
								setData.push(data.data[i]);
							}
						});
		function NewDate(str) {
			if (!str) {
				return 0;
			}
			arr = str.split(" ");
			d = arr[0].split("-");
			t = arr[1].split(":");
			var date = new Date();
			date.setUTCFullYear(d[0], d[1] - 1, d[2]);
			date.setUTCHours(t[0], t[1], t[2], 0);
			return date;
		}
		function GetRTime() {
			$.each(setData, function(i, obj) {
				var endDate = addDate(obj.add_time, obj.pituan_date);
				var EndTime = Date.parse(endDate);
				var NowTime = Date.parse(DateFormat.format(new Date(),
						'yyyy/MM/dd h:m:s'));
				if (EndTime < NowTime)
					return;
				var t = EndTime - NowTime;
				var h = Math.floor(t / 1000 / 60 / 60);
				var m = Math.floor(t / 1000 / 60 % 60);
				var s = Math.floor(t / 1000 % 60);
				var hs = getHaomiao(t)
				if (s < 10) {
					s = "0" + s;
				}
				if (m < 10) {
					m = "0" + m;
				}
				if (h < 10) {
					h = "0" + h;
				}
				$("." + obj.pin_order_id + "t_h").html(h + ":");
				$("." + obj.pin_order_id + "t_m").html(m + ":");
				$("." + obj.pin_order_id + "t_s").html(s + ":");
				$("." + obj.pin_order_id + "h_s").html(hs);
			});

		}
		window.onload = function() {
			setInterval(GetRTime, 0);
		}
		function getHaomiao(t) {
			var a = parseInt(t / 1000 / 60 / 60 / 24); //总毫秒除以一天的毫秒 得到相差的天数  
			var b = parseInt(t / 1000 / 60 / 60 - (24 * a)); //然后取完天数之后的余下的毫秒数再除以每小时的毫秒数得到小时 
			var c = parseInt(t / 1000 / 60 - (24 * 60 * a) - (60 * b)); //减去天数和小时数的毫秒数剩下的毫秒，再除以每分钟的毫秒数，得到分钟数 
			var d = parseInt(t / 1000 - (24 * 60 * 60 * a) - (60 * 60 * b)
					- (60 * c)); //得到最后剩下的毫秒数除以1000 就是秒数，再剩下的毫秒自动忽略即可
			var hm = Math
					.floor((t - (24 * 60 * 60 * 1000 * a)
							- (60 * 60 * 1000 * b) - (60 * 1000 * c) - (d * 1000)) / 10);
			if (hm < 10) {
				hm = "0" + hm;
			}
			return hm;
		}

		function addDate(date, days) {
			var d = new Date(date.replace(/-/g, "/"));
			d.setDate(d.getDate() + days);
			var y = d.getFullYear();
			var m = d.getMonth() + 1;
			var nd = d.getDate();
			var h = d.getHours();
			var mm = d.getMinutes();
			var ss = d.getSeconds();
			return y + "/" + m + "/" + nd + " " + h + ":" + mm + ":" + ss;
		}
		function normalBuy() {
			window.location.href = "initGoBuy.jhtml?goods_id=${goodDetail.goods_id}";
		}

		function zeroBuy() {
			window.location.href = "initSpellGoBuy.jhtml?goods_id=${goodDetail.goods_id}";
		}
	</script>
</body>
</html>