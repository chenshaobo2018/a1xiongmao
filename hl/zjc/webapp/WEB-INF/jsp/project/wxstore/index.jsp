<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<aos:html>
 <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>中军创云易</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxstore/index.css" />
<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxstore/mobile.js" type="text/javascript"
	charset="utf-8"></script>
<!-- bannar -->
<script type="text/javascript"
	src="../../../static/js/wxstore/mob_index_bannar.js" charset="utf-8"></script>
<script type="text/javascript"
	src="../../../static/js/wxstore/touch_slide.js" charset="utf-8"></script>
<aos:body>
	<div class="top top_index" name="top">
		<div id="top-right clearfix">
			<div class="serch fl">
				<input type="text" id="GoodsName"/><a href="../../../notokenapi/wx/v1/wxGoodsSearch.jhtml" ><img
					src="../../../static/image/wxstore/home_06.png" class="fr" /></a>
			</div>
			<div class="sec_a fr">
			<!-- 设置 -->
			   <a href="../../../notokenapi/wx/v1/initPayPwd.jhtml">
			      <img src="../../../static/image/wxstore/sett.png" class="fr"/>
			   </a>
			</div>
		</div>
	</div>
	<div class="relative">
		<div id="slider_box">
			<div id="slider" class="hboxpage" style="width: 100%; height: 100%; transform: translate3d(0px, 0px, 0px); transition-duration: 500ms;">
				<c:forEach items="${goodslist}" varStatus="i" var="item">
                      <div class="center slider_img" style="background-image: url('${item.ad_code}');">
					     <a class="ablock nou" href="#">&nbsp;</a>
				       </div>
                </c:forEach>
			</div>
			<ul class="center" id="proint">
				<li class="proint_white"></li>
				<li class="proint_gray"></li>
				<li class="proint_gray"></li>
			</ul>
		</div>
	</div>

	<div class="content">
		<div class="boc_one ">
			<div class="boc_side fl">
				<img src="../../../static/image/wxstore/zx.png" />
			</div>
			<div class="boc_cont fl">
				<div class="bocks fl">
					<div class="box">
						<div class="winBox">
							<ul class="scroll">
								 <c:forEach items="${message}" varStatus="i" var="item"> 
		                           <li>
										<a href="#">${item.title}</a>
				                  </li>
                                </c:forEach> 
							</ul>
						</div>
					</div>
				</div>
				<!-- <div class="bock_cs fr">
					<a href="#">>></a>
				</div> -->
			</div>
		</div>
		<ul class="index_active clearfix">
		 <li>
		    <a href="initLottery.jhtml">
		       <img src="../../../static/image/wxstore/games.png"/>
		    	 <p> 幸运抽奖</p>
		    </a>
		 </li>
		 <li>
		    <a href="../../../wxact/activity/v1/init.jhtml">
		       <img src="../../../static/image/wxstore/helper.png"/>
		    	  <p> 助力免单</p> 
		    </a>
		 </li>
		 <li>
		    <a href="../../../shopGroup/activity/v1/initShopGroupIndex.jhtml">
		       <img src="../../../static/image/wxstore/pintuan.png"/>
		    	  <p> 0元拼单</p> 
		    </a>
		 </li>
		 <li>
		    <a href="wxSendGoods.jhtml">
		      <img src="../../../static/image/wxstore/my_order.png"/>
		    	  <p> 我的订单</p>
		    </a>
		 </li>
		</ul>
		<div class="goods">
		    <div class="good-title" >优选商品</div>
			<div hidden="false" id="nowPage"></div>
			<div hidden="false" id="totalPage"></div>
			<article class="khfxWarp">
				<section class="khfxPane" style="display: block">
					<ul id="goods-list">
					  <%--  <c:forEach items="${PageVO.list}" varStatus="i" var="item"> 
                           <li>
								<a href="goods-details.html">
								   <img src='${item.original_img}'>
								<p class="title">${item.goods_name}</p>
								 <p class="price">￥${item.shop_price}+${item.market_price}<span>劵</span></p>
							   </a>
		                  </li>
                       </c:forEach>  --%>
					</ul> 
				</section>
		</div>
	</div>
	<div class="div_top">
		<a href="#top" class="top_div"><img src="../../../static/image/wxstore/top.png"></a>
	</div>
	<ul class="foot">
		<li><a href="##">
				<div class="foot-icon foot1 foot-hover" >
					<img src="../../../static/image/wxstore/foota_03.png" />首页
				</div>
		</a></li>
		<li><a href="../../../notokenapi/wx/v1/wxSendGoods.jhtml" >
				<div class="foot-icon foot2" >
					<img src="../../../static/image/wxstore/foot_05.png" />我的订单
				</div>
		</a></li>
		<li><a href="##">
				<div class="foot-icon foot3">
					<img src="../../../static/image/wxstore/foot_07.png" />发现
				</div>
		</a></li>
		<li><a href="##">
				<div class="foot-icon foot4">
					<img src="../../../static/image/wxstore/foot_09.png" /><br/>购物车
				</div>
		</a></li>
		<li><a href="##">
				<div class="foot-icon foot5">
					<img src="../../../static/image/wxstore/foot_11.png" />商家
				</div>
		</a></li>
	</ul>
	<!-- 加载更多 -->
	<script src="../../../static/js/wxstore/dropload.js"></script>
	<script src="../../../static/js/wxstore/khData.js"></script>
	<script>
// <!-- 回到顶部 -->
	var h3_height = $("#goods-list").offset().top;
	$(window).scroll(function(){
		var this_scrollTop = $(this).scrollTop();
		if(this_scrollTop>h3_height ){
			$(".div_top").show();
		}else{
			$(".div_top").hide();
		}
	});
	
	function init(){
		$.ajax({
			type:"GET",
			dataType:"json",
			url:"../../../notokenapi/app/v1/GoodsLists.jhtml",
			data:{
				"page":1,
			},
			success:function(data){
				$("#nowPage").text(data.data.nowPage);
				$("#totalPage").text(data.data.totalPage);
				var li = ""
				$.each(data.data.list, function(index, g){
				li += "<li><a href='../v1/goodsDetails.jhtml?goods_id=" + g.goods_id + "'><img src='"+g.original_img +"'><p class='title'>"+g.goods_name+"</p><p class='price'>"+g.market_price+"券</span></p></a></li>"
				})
				$("#goods-list").append(li)
			}
		});
	}
	window.onload = init();
	
	
	
	
	
	
</script>
</aos:body>
</aos:html>