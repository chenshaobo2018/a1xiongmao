<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>材料下单</title>
    <script src="${cxt}/aos/static/js/hl/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cxt}/aos/static/js/hl/mobile.js" type="text/javascript" charset="utf-8"></script>  
    <link rel="stylesheet" type="text/css" href="${cxt}/aos/static/css/hl/index.css"/>  
  <link rel="stylesheet" href="${cxt}/aos/static/css/hl/swiper.min.css">  
	<style> 
	</style>	
</head>
<body> 
  <div class="container bgf2"> 
    <div class="index_one bgfff">
	   <div class="title center font50">材料下单</div>
	   <div class="slid clearfix">
	    <div class="divs" style="display: none;"></div>
	      <div class="sli_slide fl"> <div class="swiper-container">
				<div class="swiper-wrapper"> 
				 <c:forEach items="${type}" varStatus="i" var="item"> 
		         <div class="swiper-slide">
				   <p class="sw_texts font50 se_lv" onclick="type('${item.id}')">${item.type_name}</p> 
				  </div>  
            </c:forEach>  
				</div> 
			  </div>
			   </div>
			  <div class="search center fr bgfff"> 
			     <img src="${cxt}/aos/static/image/hl/search.png" class="se_img"/>
			  </div>
	   </div>
	   <!-- 搜索 -->
	   <div class="opt_search">
	     <div class="opt_cen mgauto clearfix">
		    <div class="opt_one fl center "><img src="${cxt}/aos/static/image/hl/search.png" class="se_img"/></div>
		    <div class="opt_two fl"><input type="text" id="ctname" placeholder=" 请输入材料名称/品牌名称"/></div>
		    <div class="opt_tree fl color333 font50 center" onclick="sousuo()">搜索</div>
		 </div>
	   </div>
	   <div class="opt_search_make"></div>
	</div>
	<!-- 规格 -->
	<div class="main_slid">
	 <c:forEach items="${goods}" varStatus="i" var="item"> 
	   <div class="index_two bgfff">
		<div class="cont_one mgauto">
		  <div class="con_top clearfix">
			<div class="cont_side fl">
			  <p class="cont_img"><img src="${cxt}/aos/static/image/hl/produ.png" class="img_text"></p>
			</div>  
			<div class="cont_cont fl">
			  <p class="font50 color333"  onclick="getGoodsVOByMaterial_name('${item.id}')"> ${item.material_name}</p>
				<div class="con_text_mak clearfix color999 font50">
				  <div class="ctm_side fl left">单位:${item.unit}</div>
				</div>
				<p class="bt_text font40">备注:${item.note}</p>
			</div> 
		  </div>
		</div> 
	  </div>
	    </c:forEach> 
	</div>
	</div>	
	
	<div class="fix_xu clearfix fixed">
	   <div class="fix_cent fl bgfff center font45 color333">
	     <img src="${cxt}/aos/static/image/hl/me.png" class="fix_img"/>
		 <p><a href="#" onclick="orderlist()">我的记录</a></p>
		</div>  
	   <div class="fix_cont fl">
	     <button onclick="order()">立即下单</button>
	   </div>
	</div> 
  </div> 
</body> 	
	<script src="${cxt}/aos/static/js/hl/swiper.min.js"></script> 	
	<script>
	/* $(function(){
		
	}) */
	var c="";
	var b="";
	var d="";
	function add(){
		window.location.href = "add_detail.jhtml";
	}
	<!-- 规格多选 -->
	$(".ul_dilis li").click(function(){ 
		if($(this).hasClass('ulis')){
				$(this).removeClass("ulis");
			}else{
				$(this).addClass("ulis");			
				$(".itb,.itb_make").show();		
				var a=$(".itb_text").text($(this).find(".uli").text());
				c=$(this).find(".uliid").text();
				d=$(this).find(".ulitypeid").text();
				console.log("c:"+c);
				
			}     
	})
	<!-- li的eq -->
	var indexall=""; 
	var indexdata="";
		$(".ul_dilis li").click(function(){  
		indexall=$(this).index();  
	})    
	<!-- div的eq -->
	var indexall2="";      
	$(".index_two").click(function(){
		indexall2=$(this).index();  
	} )

	<!-- 输入数量 -->
	$(".btn_ok").click(function(){
		$(".itb,.itb_make").hide(); 
		$(".index_two:eq("+indexall2+") .ul_dilis li:eq("+indexall+")").find(".uli_sum").text($(".val_text").val()); 
		var b=$(".val_text").val();
		 if(b>0){
			var s="id="+c+":num="+b+":typeid="+d+",";
			var f=$(".divs").text();
			$(".divs").text(f+s);
		} 
		console.log(indexall); 
	})
	<!-- 选中效果 -->
	$('.swiper-wrapper .swiper-slide p').on('click',function(){
		$(".swiper-wrapper .swiper-slide p").removeClass("se_lv");
		$(this).addClass("se_lv");
	});
	$(".search").click(function(){
		$(".opt_search,.opt_search_make").show();
	})
	<!-- 滑动 -->
	var swiper = new Swiper('.swiper-container', {
		slidesPerView: 4,//同时显示的slides数量
		spaceBetween:0,//slide之间的距离
		centeredSlides: false,//居中 
	});
	
	function type(type_id){
		window.location.href = "hltypeIndex.jhtml?type_id="+type_id+"&user_id="+${user_id};
	}
	
	function order(){
		var order=$(".divs").text();
		window.location.href = "con_order.jhtml?order="+order+"&user_id="+${user_id};
	}
	function orderlist(){
		window.location.href = "order_list.jhtml?user_id="+${user_id};
	}
	<!--点击材料名称跳转 -->
	function getGoodsVOByMaterial_name(id){
		window.location.href = "getGoodsVOByMaterial_name.jhtml?id="+id+"&user_id="+${user_id};
	}
	function sousuo(){
		var project_name=$("#ctname").val();
		window.location.href = "hlIndexs.jhtml?project_name="+project_name+"&user_id="+${user_id};
	}
	
  </script> 
</html>