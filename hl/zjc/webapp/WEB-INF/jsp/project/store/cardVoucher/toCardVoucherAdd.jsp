﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="商家后台-商品列表">
<!-- common css and zjc_index css-->
	<link href="${cxt}/static/css/store/common.css" rel="stylesheet" type="text/css"/> 
	<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet" type="text/css"/> 
	<!-- js -->  
	<script type="text/javascript" src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script> 
	<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
	<script type="text/javascript" src="${cxt}/static/js/store/upfile.js"></script>
	<script src="../static/js/handlebars.js" type="text/javascript" charset="utf-8"></script>
	<!-- 多文件上传js引入及css -->
	<link href="${cxt}/static/css/store/imgup/common.css" rel="stylesheet" type="text/css" />
	<link href="${cxt}/static/css/store/imgup/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${cxt}/static/js/store/imgup/imgUp.js"></script>
	<!-- 上传 -->
	<script src="${cxt}/static/js/store/upfile_ka.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${cxt}/static/js/datekj/laydate.js"></script>
	<script>
		$(function() {
			var liList = $(".nav_li li");
			for (var i = 0; i < liList.length; i++) {
				if (liList[i].innerText == '卡券管理') {
					liList.eq(i).addClass("mct_click");
				}
			}
		});
	</script>
	<style>
		#vercode{
			margin-left: 20px;
		}
	</style> 
<aos:body>
 <div class="mct_container">
 <%@ include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
 <!-- mct_main start -->
   <div class="mct_main">
       <div class="mct_main_list" class=""> 
	       <div class="mctde_card">
			   <div class="mct_list_tit" style="margin-top:20px;margin-left:20px;">
					<p>卡券管理 - - 新增卡券</p>
			   </div>
			   <div class="mct_list_add">
			   	  <div class="mcka_two clearfix">
					   <div class="mckat_side fl">商户名字</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="brand_name" value="${zjcStorePO.store_name }" readonly="readonly"/>
					      <input type="hidden" id="store_id" value="${zjcStorePO.store_id }" />
					   </div>
				  </div>
				  <div class="mcka_one clearfix" style="margin-top:20px">
						<div class="mckd_side fl">
						卡券LOGO
						</div>
						<div class="mckd_cont fl">
							<div class="in_divbus ">
								<div class="imgitem secondImg">
									<div class="u-add-img-icon" style="line-height: 26px;left:160px" onclick="showdiv();"></div>
									<input type="hidden" name="original_img" value="" id="img"/>
									<div class="imgcontainer">
										<img src="" id="original_img" class="idImg" alt="upfile" title="upfile" />
									</div>
								</div>
								<!-- 放大图 -->
								<div class="big_ime_one">
									<img id="big_img" src="" alt="upfile" title="upfile" />
								</div>
								<div class="big_ime_make_one"></div>
								<!-- 放大图 -->
							</div>
							<span class="mcts_span_text" id="m_img"  style="color:red"></span>
							<!-- <div class="clear"></div>	 -->
						</div>										 
						<div class="clear"></div>	
				 </div>
				 <div class="mcka_two clearfix">
					   <div class="mckat_side fl">卡券名称</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="card_title"/>
					   </div>
				 </div>
				  <div class="mcka_two clearfix" style="display:none">
					   <div class="mckat_side fl">卡券类型</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="card_type" value="DISCOUNT"/>
					   </div>
				 </div>
				 <div class="mcka_two clearfix">
					   <div class="mckat_side fl">折扣额度(%)</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="discount" placeholder="0-100整数，如70就是七折"/>
					   </div>
				 </div>
				 <div class="mcka_two clearfix">
					   <div class="mckat_side fl">卡券颜色</div>
					   <div class="mckat_cont cl_con fl"> 
							 <div class="mc_int clearfix">  
									<input type="hidden" class="mct_hidd" id="color" value=""/>
									<div class="fl"><div class="mct" id="mct"></div></div>
									<div class="lctcon fr">▼</div>
							 </div>  
							 <div class="mct_clickcolor">  
							    <input class="mctrcolor mct1 fl" data-id="1"/>  
							    <input class="mctrcolor mct2 fl" data-id="2"/>  
							    <input class="mctrcolor mct3 fl" data-id="3"/>  
							    <input class="mctrcolor mct4 fl" data-id="4"/>  
							    <input class="mctrcolor mct5 fl" data-id="5"/>  
								<input class="mctrcolor mct6 fl" data-id="6"/>  
							    <input class="mctrcolor mct7 fl" data-id="7"/>  
							    <input class="mctrcolor mct8 fl" data-id="8"/> 
							    <input class="mctrcolor mct9 fl" data-id="9"/>  
							    <input class="mctrcolor mct10 fl" data-id="10"/> 
							    <input class="mctrcolor mct11 fl" data-id="11"/> 
							    <input class="mctrcolor mct12 fl" data-id="12"/> 
							    <input class="mctrcolor mct13 fl" data-id="13"/> 
							    <input class="mctrcolor mct14 fl" data-id="14"/> 
							 </div> 
					   </div>
				  </div>
				  <div class="mcka_two clearfix">
					   <div class="mckat_side fl">卡券提醒</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="notice"/>
					   </div>
				  </div>
				  <div class="mcka_two clearfix">
					   <div class="mckat_side fl">卡券说明</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="description"/>
					   </div>
				  </div>
				  <div class="mcka_two clearfix">
					   <div class="mckat_side fl">卡券库存</div>
					   <div class="mckat_cont fl">
					      <input type="text" class="cl_text" id="quantity" placeholder="整数，上限为100000000"/>
					   </div>
				   </div>
				   <div class="mcka_tree clearfix">
					   <div class="mckat_side_cl fl">有效期</div>
					   <div class="mckat_cont fl">
					     <p class="mck_yx clearfix">
							<p class="mckfs_die fl"><input type="radio" name="radio" value="1" class="va_radi0" checked /> 固定日期 </p>
							<p class="fl">
							<input placeholder="请选择生效开始时间" class="cl_text" id="start_time" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
							--<input placeholder="请选择生效结束时间" class="cl_text" id="end_time" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
						 </p>
						<p class="mck_yx clearfix"> 
							<p class="mckfs_die fl"><input type="radio" name="radio" value="2" class="va_radi1" /> 领取后 </p>
							<p class="img_cm fl"><input type="text" class="cl_texts tru" value="" disabled="disabled" id="fixed_term"/> 生效,有效天数 <input type="text" disabled="disabled" class="cl_texts tru" value="" id="fixed_begin"/>
							统一过期时间  <input placeholder="请选择统一过期时间" disabled="disabled" class="cl_text" id="end_timestamp" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
							</p>
						 </p> 
					   </div>
					 </div>
				 </div>
				 <div class="mcmon_btn">
					<button onclick="addCard();">保存</button>
					<button onclick="cancle();">返回</button>
				</div>
			</div>
	   </div> 
	   	<!-- logo上传 -->
		<div id="picture-upload" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center;">
			<div  style="width:700px; height: 38px;margin-left: 35%; margin-top: 8%; background-color: #F3F3F3;position: absolute; text-align:left; ">
				<label class="mcttez_label" style="line-height: 40px; margin-left: 10px;">上传图片</label>
				<button onclick="closediv();" style="padding: 10px; float: right;">关闭</button>
			</div>
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%; background-color: white;" >
				<iframe style="margin:0px;   width:700px; height:450px;border: 0px " src="cardLogoUpload.jhtml?inputName=original_img&windowsId=picture-upload&paramkey=goods_picture_prop&juid=${juid}"></iframe>
			</div>
		</div>
		<div class="clear"></div>
	</div>   
</div>
 <!-- mct_main end -->
 <!-- mct_footer start -->
   <div class="mct_footer">
    	<div class="mctfoot_cent"><p>©2017-2025 北京中军创云易物联网科技有限公司 版权所有</p></div>
   </div>
 <!-- mct_footer end -->
</aos:body>
<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库 
}(); 
</script>
		<script>  
		 var type;
		 $(function(){
			 //获取卡券有效期类型
			 type = $("input:radio[value='1']").val();
			 $(".mct_hidd").val("Color020");//设置默认颜色
			 $(".mc_int").click(function(){
			 	if($('.mct_clickcolor').is(':hidden')){//如果当前隐藏  
					  $('.mct_clickcolor').show();//那么就显示div 
				      $(".mct_clickcolor .mctrcolor").click(function(){
						$("#mct").attr("class","mct mct"+$(this).data("id"));
				    	 var co="";
				    	 if($(this).data("id")==9){
				    		 $(".mct_hidd").val("Color081");
				    		 return false;
			    		 } else if($(this).data("id")==10){
			    			 $(".mct_hidd").val("Color082");
				    		 return false;
			    		 } else if($(this).data("id")==11){
			    			 $(".mct_hidd").val("Color090");
				    		 return false;
			    		 } else if($(this).data("id")==12){
			    			 $(".mct_hidd").val("Color100");
				    		 return false;
			    		 } else if($(this).data("id")==13){
			    			 $(".mct_hidd").val("Color101");
				    		 return false;
			    		 } else if($(this).data("id")==14){
			    			 $(".mct_hidd").val("Color102");
				    		 return false;
			    		 }
				    	 for(var i=1;i<15;i++){
				    		 if(i==$(this).data("id") && i < 9){
				    			 co = "Color0"+i+"0"; 
				    			 $(".mct_hidd").val(co);
				    		 }
				    	 }
					  }); 
					}else{//否则  
						 $('.mct_clickcolor').hide();//就隐藏div																	
					}
					$(document).bind("click",function(e){ 
							var target = $(e.target); 
							if(target.closest(".cl_con").length == 0){ 
								$(".mct_clickcolor").hide(); 
							} 
					 })	 
				})
				
			 var logoObj = $("input[name='original_img']");
			 if(logoObj.val() != ""){
				$("#original_img").attr("src",logoObj.val());
				//图片预览设置回显
				$(".imgcontainer").show();
				$(".imgitem").css('height','84px');
			    $(".mcts_span_text").css('position','relative');
			    var goods_state_1 = '${goods.goods_state_1}';
				var goods_state_2 = '${goods.goods_state_2}';
				if(goods_state_1 == '2'||goods_state_2 == '2'||goods_state_2 == '3'){
					 $(".u-add-img-icon,.imgcontainer,").css('position','absolute'); 
					 $(".u-add-img-icon,.u-fileInput").css('left','160px');
				}else {
					 $(".u-add-img-icon,.imgcontainer,").css('position','absolute'); 
					 $(".u-add-img-icon,.u-fileInput").css('left','60px');
				}
			    $(".mcts_span_text").css('left','105px');
			    $(".mcts_span_text").css('bottom','65px');
			    $("#big_img").prop("src",logoObj.val()); 
				$(".imgcontainer").click(function(){
				    $(".big_ime_one,.big_ime_make_one").show();  
				    $(".big_ime_make_one").click(function(){
						$(".big_ime_one,.big_ime_make_one").hide(); 
					})
				})
			}
				
				
			})  
		  
		  $("input:radio[value='1']").click(function(){
				$(".tru").attr("disabled",true);
				 $("#start_time").removeAttr("disabled");
				 $("#end_time").removeAttr("disabled");
				 $("#end_timestamp").attr("disabled",true);
				type = $("input:radio[value='1']").val();
				
			})
		  $("input:radio[value='2']").click(function(){
			 $(".tru").attr("disabled",false);
			 $("input:radio[value='2']").val();
			 $("#start_time").attr("disabled",true);
			 $("#end_time").attr("disabled",true);
			 $("#end_timestamp").removeAttr("disabled");
			 type = $("input:radio[value='2']").val();
		  })
		//打印
		function oppr(){
			var bodyhtml=document.body.innerHTML;
			document.body.innerHTML=html;
			window.print();
			document.body.innerHTML=bodyHtml; 
		}
		function onprint_zm() { 
			var html = $(mct_zm).html();
			printHtml(html);
		} 
		//返回上一页
		function cancle(){
			window.history.go(-1);
	    }
		
	    //转账/未转账
		$(".mct_zm_tree table tr:gt(0)").each(function(){
			$(this).children("td").each(function(i){
				console.log($(this).text()=="未转账");
				if($(this).text()=="未转账"){
				  $(this).parent().find("input[name='bill_zm']").attr("disabled","disabled"); 
				}
			}); 
		}); 
	    function addCard(){
	    	var logo_url = $("#img").val();
	    	var card_title = $("#card_title").val();
	    	var discount = $("#discount").val();
	    	var card_type = $("#card_type").val();
	    	var quantity = $("#quantity").val();
	    	var start_time;
	    	var end_time;
	    	var fixed_term;
	    	var fixed_begin;
	    	if(type==1){//固定日期
		    	start_time = $("#start_time").val();
		    	if(start_time==""){
		    		alert_msg("卡券启用时间不能为空！")
		    		return false;
		    	}
		    	end_time = $("#end_time").val();
		    	if(end_time==""){
		    		alert_msg("卡券结束时间不能为空！")
		    		return false;
		    	}
	    	} else {//固定时长
		    	fixed_term = $("#fixed_term").val();
		    	if(fixed_term==""){
		    		alert_msg("卡券领取后生效天数不能为空！")
		    		return false;
		    	}
		    	fixed_begin = $("#fixed_begin").val();
		    	if(fixed_begin==""){
		    		alert_msg("卡券领取后开始生效天数不能为空！")
		    		return false;
		    	}
	    	}
	    	var color = $("#color").val();
	    	var brand_name = $("#brand_name").val();
	    	var description = $("#description").val();
	    	var notice = $("#notice").val();
	    	var end_timestamp = $("#end_timestamp").val();
	    	if(logo_url==""){
	    		alert_msg("卡券logo不能为空！")
	    		return false;
	    	} 
	    	if(card_title==""){
	    		alert_msg("卡券名称不能为空！")
	    		return false;
	    	}
	    	if(discount==""){
	    		alert_msg("折扣额度不能为空！")
	    		return false;
	    	}
	    	if(notice==""){
	    		alert_msg("卡券提醒不能为空！")
	    		return false;
	    	}
	    	if(description==""){
	    		alert_msg("卡券说明不能为空！")
	    		return false;
	    	}
	    	if(quantity==""){
	    		alert_msg("卡券库存不能为空！")
	    		return false;
	    	}
	    	
	    	$.ajax({
	    	    type: "POST",
	    	    url: "../store/addCardInfo.jhtml",
	    	    data: {
	    	    	"logo_url":logo_url,
	    	    	"card_title":card_title,
	    	    	"discount":discount,
	    	    	"card_type":card_type,
	    	    	"quantity":quantity,
	    	    	"type":type,
	    	    	"start_time":start_time,
	    	    	"end_time":end_time,
	    	    	"fixed_term":fixed_term,
	    	    	"fixed_begin":fixed_begin,
	    	    	"color":color,
	    	    	"brand_name":brand_name,
	    	    	"description":description,
	    	    	"notice":notice,
	    	    	"end_timestamp":end_timestamp,
	    	    	"store_id":$("#store_id").val()
	    	    },// 要提交的表单
	    	    success: function (data) {
	    	    	var da=JSON.parse(data) ;
	    	    	if(da.code == 1){//新增成功
	    	    		window.location.href="toCardVoucherList.jhtml";
	    	    		alert_msg(da.msg);
	    	    	} else {
	    	    		alert_msg(da.msg)
	    	    	}
	    	    },
	    	    error: function (error) {
	    	    	console.log(1111111111111)
	    	    }
	    	  });
	    }
	    
	   function showdiv(){
		   $("#picture-upload").show();
	   }
	   function closediv(){
		   $("#picture-upload").hide();
	   }
		</script> 
</aos:html>