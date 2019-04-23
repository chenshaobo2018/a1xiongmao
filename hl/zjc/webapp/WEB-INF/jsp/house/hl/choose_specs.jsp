<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>选择规格</title>
    <script src="${cxt}/aos/static/js/hl/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cxt}/aos/static/js/hl/mobile.js" type="text/javascript" charset="utf-8"></script>  
    <link rel="stylesheet" type="text/css" href="${cxt}/aos/static/css/hl/index.css"/>  
  <link rel="stylesheet" href="${cxt}/aos/static/css/hl/swiper.min.css">    
	<style> 
	</style>	
</head>
<body> 
  <div class="container bgf2"> 
    
    <div class="choo_one bgfff">
       <style>
	      .myselect{width:95%;margin:0rem auto;margin-bottom:0.3rem;}
		  .myselect select{width:100%;border:1px solid #efefef;padding:0.2rem;}
	   </style>
	    <div class="myselect">
		  <select class="val_texts">
		   <c:forEach items="${listtype}" varStatus="i" var="listtype">  
		   <option value="${listtype.goods_id}">${listtype.brand}</option>
		   </c:forEach>
		  </select>
		 </div>
    <c:forEach items="${goodsVOList}" varStatus="i" var="goodsVO">  
	   <div class="cho_one clearfix font45 color333">
	      <div  class="chose_sides" style="display:none">${goodsVO.goods_id}</div>
	       <div  class="chose_sidesd" style="display:none">${goodsVO.id}</div>
	      <div class="chose_side fl">${goodsVO.specifications_name }</div>
	      <div class="chose_cont fr right">￥<span class="price_one">${goodsVO.price}</span></div>
	   </div>
	   <div class="cho_two clearfix">
	     <div class="chot_side fl  font45 color333">数量</div>
	     <div class="chot_cont fl right clearfix">
		   <div class="shotext fl"><input type="text" placeholder="请填写请购数量" class="val_text" value='0'/></div>
		   <div class="shocont fl font45 color333">/米</div> 
		 </div>
	   </div>
   	</c:forEach>
    </div>
  <div class="add_det fixed clearfix">
	   <div class="addde_side fl bgfff font45"><p>共<span class="count">0</span>种, 合计<span>￥</span><span class="all_price"></span></p></div>
	   <div class="addde_cont fl"><button onclick="add_cat()">确定</button></div>
	</div> 
  </div>
<script>
    
     $(function(){  
	 <!-- 个数 -->
	$(".add_det").find(".count").text($(".chose_side").length); 
	<!-- 单价计算 -->
	 var sum=0;
	$(".choo_one").each(function(){  
		  var num = parseFloat($(this).find(".price_one").text());
		  var price=parseInt($(this).find(".val_text").val());
		  var sum_num=num*price;
		  sum += sum_num; 
		 $(".add_det").find(".all_price").text(sum.toFixed(2)); 
	  }); 
	  })
	  
       $(".val_text").blur(function(){
    	   var sum=0;
    	   $(".choo_one").each(function(){  
    			  var num = parseFloat($(this).find(".price_one").text());
    			  
    			  var price=parseInt($(this).find(".val_text").val());
    			  var sum_num=num*price;
    			  sum += sum_num; 
    			 $(".add_det").find(".all_price").text(sum.toFixed(2)); 
    		  }); 
	  });
     
     $(".val_texts").blur(function(){
    	var id=$("select  option:selected").val();
    	window.location.href = "getGoodsVOByMaterial.jhtml?user_id="+${user_id}+"&id="+id;
	  });
	 
	  function add_cat(){
		  $(".choo_one").each(function(){  
			  var id=parseInt($(this).find(".chose_sidesd").text());
			  /* var goods_id=parseInt($(this).find(".chose_sides").text()); */
			  var num = parseFloat($(this).find(".val_text").val());
			  if(num<=0){
					 alert("数量必须大于0"); 
					 return false;
				  }
				$.ajax({
					url:"add_cat.jhtml",
					data: {
						goods_id:id,
						user_id:${user_id},
						num:num
					},
					type:"POST",
					dataType:"json",
					success:function(data){
						if(data.code == 1){
							window.location.href = "hlIndex.jhtml?user_id="+${user_id};
						}else{
						}
					}
				});
		  }); 
	  }
</script>  
</body>
</html>