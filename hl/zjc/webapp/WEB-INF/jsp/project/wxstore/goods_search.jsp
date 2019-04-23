<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>中军创云易</title>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxstore/index.css"/> 
	<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script> 
	<script src="../../../static/js/wxstore/mobile.js" type="text/javascript" charset="utf-8"></script> 
</head>
<body>
	<div class="top top_search clearfix">
		<a href="javascript:history.go(-1)" class="fl "><img src="../../../static/image/wxstore/details1.png" class="mu_img_sec"/></a>
		<div class="goo_sear fl"><input type="text" class="sec_text fl" placeholder=" 搜索你想要的东西" id="GoodsName"/><a href="##" class="fr"><img src="../../../static/image/wxstore/home_06.png" onclick="queryGoodsName()"></a></div>
	</div>
 	 <div class="goods_se">
	    <!-- 加载 -->
		<article class="khfxWarp">
		  <section class="khfxPane" style="display:block">
		   <ul id="goods-list">
		    
			</ul>
		  </section> 
		  </article>	 
	 </div> 
<script type="text/javascript">
function queryGoodsName(){
	var goods_name = $("#GoodsName").val();
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"../../../notokenapi/app/v1/GoodsLists.jhtml",
		data:{
			page : "1",
			"goods_name":goods_name,
		},
		success:function(data){
			$("#goods-list").empty();
		   var li = ""
			$.each(data.data.list, function(index, g){
			li += "<li><a href='../v1/goodsDetails.jhtml?goods_id=" + g.goods_id + "'><img src='"+g.original_img +"'><p class='title'>"+g.goods_name+"</p><p class='price'>"+g.market_price+"券</span></p></a></li>"
			})
			$("#goods-list").append(li)  
		}
	}); 
	}
</script>
</body>
</html>
