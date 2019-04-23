<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="中军创 | 后台管理" base="http" >
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/matrix-style.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/matrix-media.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/font-awesome/css/font-awesome.css"  />
	<aos:body>
		<%@ include file="/WEB-INF/jsp/project/zjc/common/header.jsp"%>
		<!--main-->
		<div id="content">
		
		 	<div id="content-header">
		    	<div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
		 	</div>
		 	
	  		<div class="container-fluid">
	  			<iframe id="iframe_main" src="do.jhtml?router=homeService.initPortal&juid=${juid}"></iframe>
		    </div>
		</div>
		<!-- footer -->
		<div class="row-fluid">
		  	<div id="footer" class="span12"> 2013 &copy; Matrix Admin. More Templates - Collect from</div>
		</div>
	</aos:body>
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script> 
	<script src="${cxt}/static/zjc/js/bootstrap.min.js"></script> 
	
	<script type="text/javascript">
		 $(function(){  
			 var obj = ${menuDtos};
			 var menuStr = "";
			 for(var i=0;i<obj.length;i++){
				 var childStr = "";
				 var childs = obj[i].children;
				 if(childs){
					 for(var j=0;j<childs.length;j++){
						 childStr += '<li><a href="'+childs[j].a+'&juid=${juid}">'+ childs[j].text +'</a></li>';
					 }
					 menuStr += '<li class="submenu"><a href="javascript:void(0);"><i class="icon '+ obj[i].iconCls +'"></i> <span>'+obj[i].text+'</span><i class="icon icon-sort-down icon-margin"></i></a>'
					 		  + '<ul>'
					 		  + childStr
					 		  + '</ul>'
					 		  + '</li>';
				 }else{
					 menuStr += '<li class="submenu"><a href="javascript:void(0);"><i class="icon '+ obj[i].iconCls +'"></i> <span>'+obj[i].text+'</span><i class="icon icon-sort-down icon-margin"></i></a>'
		        	  + '</li>';
				 }
			 }
			 $('#menubar').html(menuStr);
		});
	</script>
	<script src="${cxt}/static/zjc/js/matrix.js"></script> 
</aos:html>