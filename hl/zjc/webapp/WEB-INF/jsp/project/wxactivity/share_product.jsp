<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>商品分享</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/share_power.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/handlebars.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/x-handlebars-template" id="goodsListTpl">
		{{#each this}}
		<li><a href="getActivityGoodsDetails.jhtml?goods_id={{goods_id}}&direct=true">
	     <div class="ord_side fl">
		    <img src="{{original_img}}"/>
		  </div>
	     <div class="ord_cont fr">
			<p class="ord_title">{{goods_name}}</p></p>
			<div class="miphone clearfix">
			   <div class="mi_side fl"><div class="miside_cen" style="max-width:100%;width:{{precent share_integral market_price}}%"></div></div>
			   <div class="mi_cont fl"><span>{{share_integral}}</span>/<span>{{toInt market_price}}</span></div>
				<div class="clear"></div>
			</div>
		 </div>
		 <div class="clear"></div>
		 </a>
	   	</li>
		{{/each}}
		<div class="clear"></div>
	</script>	
	<script type="text/x-handlebars-template" id="goodsListTpl2">
      	<div class="sharetwo_img">		  
		</div>
	</script>
</head>
<body>
	<div class="inte_container">
		<div class="order_main">
			<div class="ord_ul">
				<ul id="goods_list_ul"></ul>
				<input id="nowPage" type="hidden" value="1"> <input
					id="totalPage" type="hidden">
			</div>
		</div>
	</div>
	</div>

	<script>
		Handlebars.registerHelper({
			'precent' : function(current, total) {
				return parseInt((current / total) * 100);
			},

			'toInt' : function(data) {
				return parseInt(data);
			}
		});
		
		$.post("goodsShare.jhtml", {
			"open_id" : '${sessionScope.openid}',
			"page" : $("#nowPage").val()
		}, function(data) {			
			if(data.data.list == null){
				var temeplte = Handlebars.compile($("#goodsListTpl2").html());
				$("#goods_list_ul").html(temeplte());
			}else{
				var temeplte = Handlebars.compile($("#goodsListTpl").html());
				$("#goods_list_ul").html(temeplte(data.data.list));
			}
		});
	</script>
</body>
</html>