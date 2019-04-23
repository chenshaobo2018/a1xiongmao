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
	.con_two {margin-bottom:2rem;}
	</style>	
</head>
<body> 
  <div class="container bgf2">  
     <div class="bgfff clearfix">
	  <div class="head_cen mgauto clearfix">
	    <div class="hc_side fl"><img src="${cxt}/aos/static/image/hl/rig.png" class="right_img"/></div>
	    <div class="hc_cont fl center font50 color333">确认下单</div>
	  </div>
    </div> 
	<div class="con_one">
	<div class="conon_top bgf2 font45">
	  <p>补充信息</p>
	</div>
	<div class="con_bottom bgfff">
	  <div class="cn_one clearfix mgauto">
	     <div class="cn_side fl font50">项目</div>
	     <div class="cn_cont fl">
	     <!-- <input type="text" id="project_name" placeholder="请输入工程"/> -->
	      <select class="val_texts">
		   <c:forEach items="${list}" varStatus="i" var="listtype">  
		   <option>${listtype.project_name}</option>
		   </c:forEach>
		  </select>
	     </div>
	     <div class="cn_cent fl font50 center">*</div>
	  </div>
	  <div class="cn_one clearfix mgauto">
	     <div class="cn_side fl font50">姓名</div>
	     <div class="cn_cont fl"><input type="text" id="user_name" placeholder="请输入姓名"/></div>
	     <div class="cn_cent fl font50 center">*</div>
	  </div>
	  <div class="cn_one clearfix mgauto">
	     <div class="cn_side fl font50">备注</div>
	     <div class="cnt_cont fl">
		     <textarea class="ado_areate" placeholder=" 补充信息告知收单人员(可不填)" id="feedbackcontent"></textarea>
		 </div>
	  </div>
	</div>
	</div>
	<div class="conon_top bgf2 font45">
	  <p>确认下单材料</p>
	  <div class="addde_sides "></div>
	</div>
	<div class="con_two bgfff">
	  <ul class="con_ul"> 
	   <c:forEach items="${goodslist}" varStatus="i" var="item"> 
	     <li class="clearfix">
		    <div class="con_div mgauto">
		    <div class="conli_side fl">
				<div class="lisf_input">
					<input type="radio"/>
					<label class="dfokgs" for="nba" name="caels" type="radio" value="product3"></label>	
				</div>  
			</div>
		    <div class="conli_cent fl font45">材料名称:${item.material_name}</div>
		    <div class="conli_cont fl font45 right">
			<span class="conl_span">${item.num}${item.unit}</span>
			<span class="conl_spanid" style="display:none">${item.id}</span>
			 <c:forEach items="${item.specificationsPO}" varStatus="i" var="itemsd"> 
		         <span class="ulitypeid" style="display:none"> ${itemsd.id}</span> 
				 <span class="uli">${itemsd.specifications_name}</span>   
		     </c:forEach>
			</div>
			</div>
		 </li> 
	   </c:forEach>
	  </ul> 
	</div> 
	<div class="add_det fixed clearfix">
	   <div class="addde_side fl"><span class="ad_sum"></span></div>
	   <div class="addde_cont fl"><button onclick="add_order()">确定</button></div>
	</div>
	

	  <div class="arr_make">
	         <p class="center font45 color333 mak_text">下单完成</p>
	         <p class="font45 color333 mak_text">信息已上传后台,如有紧急情况,请电话与后台工作人员联系!</p>
			<div  class="mke_btns"><button onclick="queding()">确定</button></div>
	  </div>
	  <div class="itb_make"></div> 
	  
	   <div class="arr_makes">
	         <p class="font45 color333 mak_text">你没有项目,所以暂时不能添加订单!</p>
			<div  class="mke_btn"><button onclick="queding()">确定</button></div>
	  </div>
	
  </div> 
<script>
function queding(){
	$(".arr_make,.itb_make,.arr_makes,.itb_makes").hide();
	window.location.href = "hlIndex.jhtml?user_id="+${user_id};
}

$(".addde_cont").click(function(){
	$(".arr_make,.itb_make").show();
})
<!-- 修改米数 -->
	$(".con_img").click(function(){
			$(this).prev(".dead_input").show();
			$(this).prev().prev(".conl_span").hide();
			$(this).prev(".dead_input").val($(this).prev().prev(".conl_span").text());
	})
	<!-- 文本框失去焦点 -->
	$('.dead_input').blur(function() { 
		$(this).hide();
		$(this).prev(".conl_span").show();
		$(this).prev(".conl_span").text($(this).val());
	});
	<!-- 下单选中 -->
	/* $(".lisf_input label[name='caels']").click(function(){
				var count=0;
				if($(this).hasClass('checked')){
						$(this).removeClass("checked");
						var totalNum = parseInt($(".ad_sum").text());
						totalNum--;
						$(".ad_sum").text(totalNum);
						var a=$(this).parent().parent().next().next().find(".conl_spanid").text();
						var b=$(this).parent().parent().next().next().find(".conl_span").text();
						var c=$(this).parent().parent().next().next().find(".ulitypeid").text();
						 $.ajax({
								url : "delete_cat.jhtml",
								type : 'POST',
								data : {
								    goods_id : a,
									num:b,
									type_id:c,
									user_id:${user_id}
								},
								success : function(data) {
									
								}
							});
					}else{
						$(this).addClass("checked");
						var totalNum = parseInt($(".ad_sum").text());
						totalNum++; 
						$(".ad_sum").text(totalNum);
						var a=$(this).parent().parent().next().next().find(".conl_spanid").text();
						var b=$(this).parent().parent().next().next().find(".conl_span").text();
						var c=$(this).parent().parent().next().next().find(".ulitypeid").text();
						 $.ajax({
								url : "add_cat.jhtml",
								type : 'POST',
								data : {
								    goods_id : a,
									num:b,
									type_id:c,
									user_id:${user_id}
								},
								success : function(data) {
									
								}
							});
						
					} 
		});  */
	
	function add_order(){
		var project_name=$("select  option:selected").text();
		if(project_name==null||project_name==""){
			$(".arr_makes").show();
			$(".mke_btns").hide();
		}
		var user_name=$("#user_name").val();
		var note=$("#feedbackcontent").val();
		 $.ajax({
				url : "add_order.jhtml",
				type : 'POST',
				data : {
					note:note,
					user_name:user_name,
					project_name:project_name,
					user_id:${user_id}
				},
				success : function(data) {
				}
			});
	}
</script>  
</body>
</html>