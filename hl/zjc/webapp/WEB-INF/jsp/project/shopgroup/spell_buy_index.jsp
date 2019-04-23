<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>0元拼单</title>
    <link rel="stylesheet" type="text/css" href="../../../static/css/shopgroup/index.css"/> 
    <script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../../static/js/shopgroup/mobile.js" type="text/javascript" charset="utf-8"></script>  
    <script src="../../../static/js/handlebars.js" type="text/javascript" charset="utf-8"></script>
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
      <div class="spe_conindex">  	  
	      <div class="spf_four"> 
		     	<article class="khfxWarp">
				  <section class="khfxPane" style="display:block">
				   <input id="nowPage" type="hidden" value="1"> 
				   <input id="totalPage" type="hidden">
				   <ul id="goods-list">
				   </ul>
				  </section> 
				</article>		
		  </div>
		  <!-- <div class="index_fixed">
		     <a href="javascript:void(0);">我的<br/>拼单</a>
		  </div> -->
	  </div>  
	  <!-- 加载更多 -->
		<script src="../../../static/js/shopgroup/dropload.js"></script>
		<script src="../../../static/js/shopgroup/khData.js"></script>  
		<script>
			$.post("../../../notokenapi/app/v1/getPinTuanWxGoods.jhtml",{"page" : '1'}, function(data) {
				console.log(data.data);
				var temeplte = Handlebars.compile($("#goodsListTpl").html());
				$("#goods-list").html(temeplte(data.data.list));
				$("#nowPage").val(data.data.nowPage);
				$("#totalPage").val(data.data.totalPage); 
			});
		</script>
</body>
</html>