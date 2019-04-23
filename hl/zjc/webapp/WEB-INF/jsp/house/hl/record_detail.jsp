<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>下单记录</title>
    <script src="${cxt}/aos/static/js/hl/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cxt}/aos/static/js/hl/mobile.js" type="text/javascript" charset="utf-8"></script>  
    <link rel="stylesheet" type="text/css" href="${cxt}/aos/static/css/hl/index.css"/>    
	<style> 
	</style>	
</head>
<body> 
  <div class="container bgf2">  
	   <div class="title center font50 bgfff">记录详情</div>
  <ul class="dere_ul">
   <c:forEach items="${list}" varStatus="i" var="item"> 
    <li class="bgfff">
	   <div class="li_re clearfix mgauto font45 color333">
	      <div class="lisr_side fl">材料名称:${item.material_name}</div>
	      <div class="lisr_cont fr right">下单数量:${item.number}</div>
	   </div>
	</li>
   </c:forEach>
  </ul>
  </div> 
</body>
</html>