<%@ page contentType="text/html; charset=utf-8"%>
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
<style>
.swiper-container {
	width: 100%;
	height: 100%;
	background: #fff;
}

.swiper-slide {
	width: 10% !important;
	text-align: center;
	background: #fff;
	padding: 0 2%;
	padding-top: 3%;
	padding-bottom: 3%;
}
</style>
<script src="../../../static/js/handlebars.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	Handlebars.registerHelper({
		'if_eq': function(v1, v2, opts) {
			if(v1 == v2)
			return opts.fn(this);
		else
			return opts.inverse(this);
		},  
		'substr': function(str) {  
	        var newStr = '';
	        if(str.length > 10){  
	        	newStr = str.substring(0,10) + '...';  
	        } else {
	        	newStr = str;
	        }
	        return newStr;  
	    }
	});  
</script>
<script type="text/x-handlebars-template" id="shopGroupList">
		{{#each this}}
			{{#if_eq is_head 1}}
    			<div class="swiper-slide">
					<p class="sw_texts">团长</p>
					<img src="{{head_img}}" class="sw_img"/>
				</div> 
			{{else}}
   				<div class="swiper-slide">
					<p class="sw_texts">团员</p>
					<img src="{{head_img}}" class="sw_img"/>
				</div>
			{{/if_eq}}
		{{/each}}
		<div class="swiper-slide">
			<p class="sw_texts">团员</p>
			<div class="se_div"><a href="javascript:void(0);">+</a></div>
		</div>
	</script>
<script type="text/x-handlebars-template" id="goodsListTpl">
		{{#each this}}
			<li>
				<a href="initGoodsDetail.jhtml?goods_id={{goods_id}}">
					<img src="{{original_img}}">
					<div class="news_on">新品</div>
					<p class="title">{{substr goods_name}}</p>
					<div class="pce_sum clearfix">
					<p class="price fl">{{shop_group_market_price}}<span>券</span></p>
					</div>
					<button class="btn_pt">立即拼团</button>
				</a>
			</li>
		{{/each}}
	</script>
</head>
<body>
	<div class="spe_container">
		<div class="spbuy clearfix">
			<div class="spbu_side fl">
				<img src="${goodDetail.original_img }" />
			</div>
			<div class="spbu_cont fr">
				<p class="spdbu">${goodDetail.goods_name }</p>
				<p class="spd_price">${goodDetail.shop_group_market_price }<span>券</span></p>
			</div>
		</div>
		<!-- 参与人 -->
		<div class="spbuy_one">
			<div class="swiper-container">
				<div class="swiper-wrapper" id="personGroup"></div>
			</div>
		</div>
		<div class="spbuy_two">
			<!-- 参与拼单 -->
			<button class="sof_btn">参与拼单</button>
		</div>

		<!-- <div class="spbuy_tree center">热门推荐</div>
		<div class="spf_four">
			  <input id="nowPage" type="hidden" value="1"> 
			  <input id="totalPage" type="hidden">
			<article class="khfxWarp">
				<section class="khfxPane" style="display: block">
					<ul id="goods-list">
					</ul>
				</section>
			</article>
		</div> -->
	</div>
	<!-- 加载更多 -->
	<script src="../../../static/js/shopgroup/dropload.js"></script>
	<script src="../../../static/js/shopgroup/khData.js"></script>
	<script src="../../../static/js/shopgroup/swiper.min.js"></script>
	<script src="../../../static/js/redpacket/quhao.js" type="text/javascript" charset="utf-8"></script>
	<script>
	   <!-- 点击参与拼单 -->
	   $(".sof_btn").click(function(){
		   window.location.href = "initSpellGoBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id=${pin_order_id}";
	   })
	   
	   $.post("queryOrderPersonnel.jhtml",{"pin_order_id" : '${pin_order_id}'}, function(data) {
		   var temeplte = Handlebars.compile($("#shopGroupList").html());
			$("#personGroup").html(temeplte(JSON.parse(data).data));
			var swiper = new Swiper('.swiper-container', {
		          slidesPerView: 4,//同时显示的slides数量
		      	  spaceBetween:0,//slide之间的距离
		          centeredSlides: false,//居中 
		    });
	   });
	 /*   $.post("../../../notokenapi/app/v1/getPinTuanWxGoods.jhtml",{"page" : '1'}, function(data) {
		   var temeplte = Handlebars.compile($("#goodsListTpl").html());
		   $("#goods-list").html(temeplte(data.list));
		   $("#nowPage").val(data.nowPage);
		   $("#totalPage").val(data.totalPage);
		}); */
	</script>
</body>
</html>