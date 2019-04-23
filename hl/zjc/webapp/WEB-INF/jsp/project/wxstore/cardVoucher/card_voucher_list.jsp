<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>中军创云易</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxstore/index.css" />
<script src="../../../static/js/wxstore/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxstore/mobile.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="../../../static/js/wxstore/pick-pcc.min.1.0.1.js"></script>
<style>
	*,div,p,ul,li,table,tr,td,a,span,img,label,input,button{margin:0;padding:0;}a{text-decoration:none;}html,body{height:100%;width:100%;margin:0;padding:0; font-family:'Microsoft YaHei','微软雅黑';font-size:62.5%;}  
	.right{text-align:right;}.left{text-align:left;}.fl{float:left;}.fr{float:right;}.clear{clear:both;}.clearfix:before,.clearfix:after {display: table;content:" ";}.clearfix:after {clear: both;}.clearfix{*zoom: 1;}.center{text-align:center;} 
	body{background:#f7f7f7;height:100%;}

</style>
</head>
<body>
	  <div class="list_main">
	  
		  	<c:if test="${cardvoucherList==null || fn:length(cardvoucherList) == 0}">  
				<div class="list_one clearfix"> 
				  暂无卡券数据！
				</div> 
			</c:if>  
			<c:forEach items="${cardvoucherList}" var="item" varStatus="status">  
			 	 <div class="list_one clearfix">
			 	 	<div style="display:none">${item.card_id }</div> 
				    <div class="list_side fl"><img src="${item.logo_url }"/></div>
				    <div class="list_cont fl">
					   <p>${item.card_title }</p>
					   <p>${item.discount }</p>
					</div> 
				  </div>
			 
			</c:forEach>  
	  </div>
</body>
<script type="text/javascript">
	$(function(){   
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';
	});
</script>
</html>