<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>下单记录</title>
    <script src="${cxt}/aos/static/js/hl//jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cxt}/aos/static/js/hl//mobile.js" type="text/javascript" charset="utf-8"></script>  
    <link rel="stylesheet" type="text/css" href="${cxt}/aos/static/css/hl/index.css"/>    
	<style> 
	</style>	
</head>
<body> 
  <div class="container bgf2"> 
   <div class="bgfff clearfix">
	  <div class="head_cen mgauto clearfix">
	    <div class="hc_side fl"><img src="${cxt}/aos/static/image/hl/rig.png" class="right_img"/></div>
	    <div class="hc_cont fl center font50 color333">下单记录</div>
	  </div>
    </div>
	<div class="reco_main mgauto">
	   <ul class="reco_ul">
	   <c:forEach items="${order_list}" varStatus="i" var="item"> 
	      <li class="bgfff"><a href="#" onclick="subForm()">
		    <div class="reco_div clearfix color333 font45"> 
			     <div class="reco_side fl">${item.note}</div>
			     <div class="reco_sideid" style="display:none">${item.id}</div>
			     <div class="reco_cont fr">${item.is_refer}</div>
			</div>
			<p class="color333 font45">材料下单统计:共${item.project_name}项 总数:${item.user_name}</p>		  
		  </a>
		   </li>
		 </c:forEach>
	   </ul>
	</div>  
  </div>
</body>
<script>		
		function subForm(){
			var order_id="";
			$(".reco_ul li").click(function(){ 
				order_id=$(this).find(".reco_sideid").text();
				console.log(order_id);
				window.location.href = "order_detailed.jhtml?user_id="+${user_id}+"&order_id="+order_id;
			})
		}
	</script>
</html>