<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
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
     <div class="bgfff clearfix">
	  <div class="head_cen mgauto clearfix">
	    <div class="hc_side fl"><img src="${cxt}/aos/static/image/hl/rig.png" class="right_img"/></div>
	    <div class="hc_cont fl center font50 color333">新增材料</div>
	  </div>
    </div>
	<div class="addde_one bgfff">
	   <div class="adddeo_cen mgauto">
	      <div class="ado_one clearfix border_bottom">
		    <div class="ado_side fl font50 color333">类型：</div>
		    <div class="ado_cont fl font50 add_type"> 
		     	<input type="text" class="text_type" id="type_name"/>
			</div>
			      <div class="zhe_make">
		         <select id="sel" onchange="fun()">
		          <c:forEach items="${type}" varStatus="i" var="item"> 
		            <option value="${item.id}">${item.type_name}</option>
		          </c:forEach>
		         </select>
		       </div>
		       <div class="zhe_makes"></div>
		  </div>
	      <div class="ado_two clearfix border_bottom">
		    <div class="ado_sides fl font50 color333">名称：</div>
		    <div class="ado_cont fl"> 			
			   <input type="text" class="arr_input" id="material_name" placeholder="请填写材料名称"/>
			</div>
		  </div>
	      <div class="ado_two clearfix border_bottom">
		    <div class="ado_sides fl font50 color333">单位：</div>
		    <div class="ado_cont fl"> 			
			   <input type="text" class="arr_input" id="unit" placeholder="例如:个"/>
			</div>
		  </div>
	      <div class="ado_two clearfix border_bottom">
		    <div class="ado_sides fl font50 color333">品牌：</div>
		    <div class="ado_cont fl"> 			
			   <input type="text" class="arr_input" id="brand" placeholder="请填写材料品牌"/>
			</div>
		  </div>
	      <div class="ado_two clearfix border_bottom">
		    <div class="ado_sides fl font50 color333">规格：</div>
		    <div class="ado_cont fl"> 
				<div class="adosum clearfix">
					<div class="adons_side fl"> 
						<input type="text" class="adons_input" id="specifications_name" placeholder="请填写型号规格"/>
						<!-- <span class="aso_span font45 right">+添加规格</span> -->
					</div>
				</div>			  
			</div>
		  </div>
		  <div class="ado_tree">
		     <p class="font50 color333">备注：</p>
			 <textarea class="ado_area" placeholder=" 限制25字" id="feedbackcontent"></textarea>
		  </div>  
	   </div> 
	</div>
	  <div class="addde_two mgauto">
	    <button onclick="add();">确定上传</button> 
	  </div>
	  <!-- 提示 -->
	  <div class="arr_make">
	         <p class="center font45 color333 mak_text">上传成功</p>
	         <p class="font45 color333 mak_text">信息已上传后台,如有紧急情况,请电话与后台工作人员联系!</p>
			<div  class="mke_btn"> <button>确定</button></div>
	  </div><div class="itb_make"></div>
  </div>    
<script type="text/javascript"> 
   $(".add_type").click(function(){
	   $(".zhe_makes,.zhe_make").show();
	   
   })
  function fun(){
	   var tx = $('#sel option:selected').text(); 
	   var vs = $('#sel option:selected').val(); 
	   $(".text_type").val(tx);	  
	   $(".text_type").text(vs);	 
   } 
   $(".zhe_makes").click(function(){
	   $(".zhe_makes,.zhe_make").hide(); 	   
   })
	$(".aso_span").click(function(){ 
		$(".adons_side").prepend( $(this).prev().clone() );
	})
	/* $(".addde_two").click(function(){
		$(".arr_make,.itb_make").show();
	}) */
	
	function add(){
	 var type_name=$("#type_name").text();
	 var material_name=$("#material_name").val();
	 var unit=$("#unit").val();
	 var brand=$("#brand").val();
	 var specifications_name=$("#specifications_name").val();
	 var feedbackcontent=$("#feedbackcontent").val();
	 $.ajax({
			url : "query_detail.jhtml",
			type : 'POST',
			data : {
				type_name : type_name,
				material_name:material_name,
				unit:unit,
				brand:brand,
				specifications_name:specifications_name,
				feedbackcontent:feedbackcontent
			},
			success : function(data) {
				if (data.code == 1) {
					$(".arr_make,.itb_make").show();
				} else {
					alert(data.msg)
				}
			}
		});
	 
   }
	
</script>
</body>
</html>