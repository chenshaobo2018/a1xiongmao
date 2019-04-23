<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="订单列表">
<!-- common css and zjc_index css-->
	<link href="${cxt}/static/css/store/common.css" rel="stylesheet" type="text/css"/> 
	<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet" type="text/css"/>
	<link href="${cxt}/static/css/store/jquery.bigautocomplete.css" rel="stylesheet" type="text/css"/> 
	<!-- js -->  
	<script type="text/javascript" src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script> 
	<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
	<script type="text/javascript" src="${cxt}/static/js/store/jquery.bigautocomplete.js"></script>
	<script src="../static/js/handlebars.js" type="text/javascript" charset="utf-8"></script>

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
		 .st_close{font-size:20px;COLOR:#ffb35e;margin-right:5px;cursor:pointer;}
		 .zhe_st_list{border:1px solid #dadbdb;z-index:9999;position:fixed;top:30%;left:38%;width:350px;background:#fff;padding:10px 10px 0 10px;	 display:none;}
		 .zhe_st_list p{color:#757475;font-size:16px;text-align:center;margin-bottom:10px;}
		 .zheli_btn {border-top:2px solid #efefef;padding-top:5px;width:340px;margin:0 auto;}
		 .zheli_btn button{border:none;color:#757475;font-size:16px;width:166px;background:#fff;height:45px;line-height:45px;}
		 .zheli_btn button.btn_ze_one{border-right:1px solid #ccc;}
		 .mctbtn_show_make {text-align:center;z-index: 9998;	position:fixed;	top:0;left:0;width:100%;height:100%;background:#000;opacity:0.4;filter:alpha(opacity=40);display:none;}
		 .mctbtn_show {z-index:9999;position:fixed;top:20%;left:35%;width:430px;background:#fff;	 display:none; padding:15px;}
		 .msid button,.mcon button{width:200px;height:35px;line-height:35px;border:1px solid #efefef;background:#e66e2e;color:#fff;margin-top:15px;}
		 .text_cancl{text-align:center;}
		 .can_one{width:400px;margin-left:15px;}
	</style> 
<aos:body>
	<div class="mct_container">
   <%@ include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
   <div class="mct_main">
   	  <div class="mct_main_list">
		<!-- <div id="mcont0"> -->
			<!-- 商家列表 -->
			<!-- <u style="display:block;"> -->
			<div class="mct_list">
				<div class="dis_show">
					<div class="mct_list_tit">
						<p>卡券管理--卡券列表</p>
					</div>
					<div class="mctindent_one">
						<div class="midentone_top fl">
							<input type="text"  id="store_id" style="display: none" value="${zjcStorePO.store_id }"/>
							<span class="mio_one">卡券名称 <input type="text" placeholder="卡券名称" id="card_title"/></span>
							<span class="min_span" style="margin-left:30px"> <img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachCardVoucher()"></span>
							<span class="min_span"> <button onclick="addCardVoucher()" class="add_licon">新 增</button></span>
						</div>
						<div class="clear"></div>
					</div>
					<div class="mctindent_two">
					     <table id="cardList">
						       <tr>
							       <th width="12%" class="tr_h" style="display: none">卡券ID</th>
							       <th width="6%" class="tr_h">卡券名称</th>
							       <th width="6%" class="tr_h">折扣额度(%)</th>
							       <th width="6%" class="tr_h">卡券类型</th>
							       <th width="8%" class="tr_h">库存数量</th>
							       <th width="7%" class="tr_h">生效类型</th>
							       <th width="6%" class="tr_h">生效开始日期</th>
							       <th width="6%" class="tr_h">生效结束日期</th>
							       <th width="6%" class="tr_h">生效天数</th>
							       <th width="6%" class="tr_h">开始生效天数</th>
							       <th width="6%" class="tr_h">领取地址</th>
							       <th width="6%" class="tr_h">创建时间</th>
							       <th width="6%" class="tr_h">操作</th>
							   </tr>
							   <tbody id="t_f_order">
								   <c:if test="${cardVoucherList==null || fn:length(cardVoucherList) == 0}">  
										<tr>  
										  <td colspan="10">暂无卡券数据！</td>  
										</tr>  
									</c:if>  
									<c:forEach items="${cardVoucherList}" var="item" varStatus="status">  
									  <tr>  
									  	<td style="display: none" class="card_id">${item.card_id}</td> 
									    <td>${item.card_title}</td>  
									    <td>${item.discount}</td>  
									    <td>${item.card_type}</td>  
									    <td>${item.quantity}</td>  
									    <c:if test="${item.type==1}">  
											<td>固定日期</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.start_time}" /></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.end_time}" /></td>
											<td>--</td>
											<td>--</td>  
										</c:if> 
										<c:if test="${item.type==2}">  
											<td>固定时长</td>
											<td>--</td>
											<td>--</td>
											<td>${item.fixed_term}</td>
											<td>${item.fixed_begin}</td>  
										</c:if> 
										<c:if test="${empty item.show_qrcode_url}"> 
									    	<td>暂无分享码</td>   
										 </c:if>
										 <c:if test="${not empty item.show_qrcode_url}"> 
									    	<td><a href="${item.show_qrcode_url}">点击查看分享码</a></td>   
										 </c:if>
									    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.add_time}" /></td>
									    <td><img src='${cxt}/static/image/mct/mct_add.png' alt='投放' title='投放' onclick='createCode(this)' style='height:30px;'></td> 
									  </tr>  
									</c:forEach>  
							   </tbody>
						 </table>
					 </div>
					 
				</div></div><!-- </u> </div>--></div></div>
 </div>
   <div class="mct_footer">
    	<div class="mctfoot_cent"><p>©2017-2025 北京中军创云易物联网科技有限公司 版权所有</p></div>
   </div>
   
   <div class="mctbtn_show"> 
		<p class="text_cancl">是否确认生成二维码?</p >
		<input type="hidden" id="hid_card_id"/>
	    <div class="can_one">
		  <div class="msid fl">
			 <button id="create">确定</button>
		   </div>
		   <div class="mcon fl">
			 <button id="cancle">取消</button>					 
			 </div>
		   <div class="clear"></div>
	    </div>
	</div>
	<div class="mctbtn_show_make"></div>
   
</aos:body> 
	<script>
	
		$(function(){
			$(".mctbtn_show_make").click(function(){
			   $(".mctbtn_show,.mctbtn_show_make").hide();
			})
			$("#cancle").click(function(){
			   $(".mctbtn_show,.mctbtn_show_make").hide();
			})
	  	})
	  	
		//跳转到新增页面
		function addCardVoucher(){
			window.location.href="toCardVoucherAdd.jhtml";
		}
		
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
		
		function serachCardVoucher(){
			//获取搜索条件
			var card_title = $("#card_title").val();
			var store_id = $("#store_id").val();
			$.post("serachCardVoucher.jhtml",{
					"store_id":store_id,
					"card_title":card_title
				},function(data){
					console.log(data)
					$("#t_f_order").empty();
					var tr = "";
					if(data==null || data =='[]'){
						tr="<tr><td colspan='10'>暂无卡券数据！</td></tr>";
					} else {
						$.each($.parseJSON(data), function(index, o){
							var tr1 = "<tr><td style='display: none' class='card_id'>" + o.card_id + "</td>"
							+ "<td>" + o.card_title +"</td>"
							+ "<td>" + o.discount +"</td>"
							+ "<td>" + o.card_type +"</td>"
							+ "<td>" + o.quantity +"</td>";
							var tr2 = "";
							if(o.type == 1){
								tr2 = "<td>固定日期</td>"
									+ "<td>" + o.start_time + "</td>"
									+ "<td>" + o.end_time + "</td>"
									+ "<td>--</td>"
									+ "<td>--</td>";
							} else {
								tr2 = "<td>固定时长</td>"
									+ "<td>--</td>"
									+ "<td>--</td>"
									+ "<td>" + o.fixed_term + "</td>"
									+ "<td>" + o.fixed_begin + "</td>";
							}var tr3 = "";
							if(o.show_qrcode_url == ''){
								tr3 = "<td>暂无分享码</td>";
							} else {
								tr3 = "<td><a href="+ o.show_qrcode_url +">点击查看分享码</a></td>";
							}
							var tr4 = "<td>"+ o.add_time +"</td><td><img src='${cxt}/static/image/mct/mct_add.png' alt='投放' title='投放' onclick='createCode(this)' style='height:30px;'></td></tr>"
							tr = tr + (tr1+tr2+tr3+tr4);
							tr1="";
							tr2="";
							tr3="";
							tr4="";
						}); 
					}
					$("#t_f_order").append(tr);
				}
			);
		}
		
		//生成二维码
		function createCode(v){
			var tr = v.parentNode.parentNode;
			var card_id = $(tr).find(".card_id").text();
			$(".mctbtn_show,.mctbtn_show_make").show();
			$("#hid_card_id").val(card_id);
		}
		//确认生成二维码
		$("#create").click(function(){
			var card_id = $("#hid_card_id").val();
			$.ajax({
	    	    type: "POST",
	    	    url: "../store/createCode.jhtml",
	    	    data: {
	    	    	"card_id":card_id
	    	    },// 要提交的表单
	    	    success: function (data) {
	    	    	var da=JSON.parse(data) ;
	    	    	if(da.code == 1){//新增成功
	    	    		$(".mctbtn_show,.mctbtn_show_make").hide();
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
		})
		
	
	</script>
</aos:html>