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
	<script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="../../../static/js/sha1.js"></script>
	<script type="text/javascript" src="../../../static/js/wxactivity/wxconfig.js"></script>
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
			<button class="sof_btn">分享好友</button>
			<div class="order center">
				<a href="javascript:void(0);"><img src="../../../static/image/shopgroup/order.png"
					class="my_order" /> 我的订单</a>
			</div>
		</div>
		<!-- 遮罩层 -->
		<!-- 我的订单 -->
		<div class="my_order_one">
			<p class="ord_text center">
				请前往下载APP<br />查看订单详情
			</p>
			<div class="ord_con clearfix">
				<button class="ord_side">取消</button>
				<button class="ord_cont">下载</button>
			</div>
		</div>
		<!-- 参与 -->
		<div class="join_all">

			<div class="clear"></div>

		</div>
		<div class="ze_makebtn">
			<div class="clearfix"><div class="fr"><img src="../../../static/image/redpacket/cgt.png" class="msl_img"></div></div>
			<p class="zue_texts center">请点击右上角</p>
			<p class="zue_texts center">将它发送给指定的朋友</p>
			<p class="zue_texts center">或分享到朋友圈 </p>
		</div>
		<div class="all_zhe"></div>

		<div class="spbuy_tree center">热门推荐</div>
		<div class="spf_four">
			  <input id="nowPage" type="hidden" value="1"> 
			  <input id="totalPage" type="hidden">
			<article class="khfxWarp">
				<section class="khfxPane" style="display: block">
					<ul id="goods-list">
					</ul>
				</section>
			</article>
		</div>
	</div>
	<!-- 加载更多 -->
	<script src="../../../static/js/shopgroup/dropload.js"></script>
	<script src="../../../static/js/shopgroup/khData.js"></script>
	<script src="../../../static/js/shopgroup/swiper.min.js"></script>
	<script>
	   <!-- 我的订单 -->
	   $(".order").click(function(){
		$(".all_zhe,.my_order_one").show();
	   });
	   <!-- 点击下载关闭 -->
	   $(".ord_cont").click(function(){
		 window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.seventc.zhongjunchuang";
	   })
	   
	    <!-- 点击取消关闭 -->
	   $(".ord_side").click(function(){
		$(".all_zhe,.my_order_one").hide();
	   })
	   
	   $(".sof_btn").click(function(){
	     $(".join_all,.all_zhe").show();
	     $(".ze_makebtn").show();
	   })
	   
	   $.post("queryOrderPersonnel.jhtml",{"pin_order_id" : '${pin_order_id}'}, function(data) {
		   var temeplte = Handlebars.compile($("#shopGroupList").html());
		   $("#personGroup").html(temeplte(JSON.parse(data).data));
		   var swiper = new Swiper('.swiper-container', {
	          slidesPerView: 4,//同时显示的slides数量
	      	  spaceBetween:0,//slide之间的距离
	          centeredSlides: false,
	       });
	   });
	   $.post("../../../notokenapi/app/v1/getPinTuanWxGoods.jhtml",{"page" : '1'}, function(data) {
		   var temeplte = Handlebars.compile($("#goodsListTpl").html());
		   $("#goods-list").html(temeplte(data.list));
		   $("#nowPage").val(data.nowPage);
		   $("#totalPage").val(data.totalPage);
		});
	   $(".sure_app").click(function(){
		   $(".join_all,.all_zhe").hide();
	   })
	   $(".all_zhe,.ze_makebtn").click(function(){
		   $(".join_all,.all_zhe").hide();
		   $(".ze_makebtn").hide();
	   })
	   
	   wx.ready(function() {
		wx.onMenuShareAppMessage({
			title : '【0元拼单】免费精品好礼就差一步好货拼到你手软！', // 分享标题
			desc : '${goodDetail.goods_name }', // 分享描述
			//link : 'http://wexin.web.zjc1518.cn/aosuite/shopGroup/activity/v1/initSpellBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id=${pin_order_id}',
			link : 'https://zjc1518.com/aosuite/shopGroup/activity/v1/initSpellBuy.jhtml?goods_id=${goodDetail.goods_id}&pin_order_id=${pin_order_id}',  //分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : '${goodDetail.original_img }', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});
	  })
	
	  wx.error(function(res) {
		  // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
		  alert("微信验证失败");
	  });
	</script>
</body>
</html>