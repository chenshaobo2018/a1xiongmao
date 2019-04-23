<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<aos:html>
<aos:head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>中军创云易</title>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxstore/index.css"/>
    <script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../../static/js/wxstore/mobile.js" type="text/javascript" charset="utf-8"></script> 
</aos:head>
<aos:body>
	<div class="order_main">
		<div class="tabPanels_newarrpos">
			<ul class="tsikl_newarrpos overt">
				<li class="hitsd_newarrpos">待发货</li>
				<li>待收货</li>   
			</ul>
			<div class="panessd_newarrpos">
		        <div class="panes_newarrpos" style="display:block;">
			<c:forEach items="${zjcOrderList}" varStatus="i" var="item"> 
				       <div class="order_company">
					   <div class="order_top">
					     <div class="com_title clearfix">
						    <div class="com_title_side fl ">
							   ${item.store_name}
							</div>
						   <!--  <div class="com_title_cont fr ">
							  <a href="#"> <img src="../../../static/image/wxstore/details3.png"></a>
							</div> -->						 
						 </div>
						 <div class="com_content clearfix">
						    <div class="com_content_side fl">
							   <img src='${item.original_img}'> 							
							</div>
						    <div class="com_content_cont fr">
							   <p class="com_text">${item.goods_name}</p>
							   <p class="com_texts clearfix"><span class="con_span fl">数量 ${item.goods_num}</span><span class="con_span_con fr">￥${item.cash}+${item.market_price}券</span></p>							
							</div>
						 </div>
						 </div>
						 <div class="com_cdetail">
						   <div class="com_id">订单编号 <span>${item.order_sn}</span></div>
						   <div class="com_id">下单时间 <span><fmt:formatDate value="${item.add_time}" pattern="yyyy-MM-dd hh:mm:ss" /></span></div>
						   <div class="com_id">${item.user_name}</div>
						   <div class="com_id">${item.address}</div>
						 </div>
					   </div>
					    </c:forEach> 
				</div>
                
			
				<div class="panes_newarrpos">					
				<!-- 待收货 -->
				      <c:forEach items="${zjcOrder}" varStatus="i" var="item"> 
				       <div class="order_company">
					   <div class="order_top">
					     <div class="com_title clearfix">
						    <div class="com_title_side fl ">
							   ${item.store_name}
							</div>
						    <!-- <div class="com_title_cont fr ">
							  <a href="#"> <img src="../../../static/image/wxstore/details3.png"></a>
							</div>	 -->					 
						 </div>
						 <div class="com_content clearfix">
						    <div class="com_content_side fl">
							   <img src='${item.original_img}'> 							
							</div>
						    <div class="com_content_cont fr">
							   <p class="com_text">${item.goods_name}</p>
							   <p class="com_texts clearfix"><span class="con_span fl">数量 ${item.goods_num}</span><span class="con_span_con fr"><%-- ￥${item.cash}+ --%>${item.market_price}券</span></p>							
							</div>
						 </div>
						 </div>
						 <div class="com_cdetail">
						   <div class="com_id">订单编号 <span>${item.order_sn}</span></div>
						   <div class="com_id">下单时间 <span><fmt:formatDate value="${item.add_time}" pattern="yyyy-MM-dd hh:mm:ss" /></span></div>
						   <div class="com_id">${item.user_name}</div>
						   <div class="com_id">${item.address}</div>
						   <div class="set_pwd confirm_order" id="${item.order_id}">
							   确认收货
						   </div>
						 </div>
					     </div>
					    </c:forEach> 
				</div>     
			</div>
		</div> 
	</div>
	<script type="text/javascript">
		 $(".set_pwd").click(function(e){
			 var order_id = $(e.target).attr('id');
			 $.ajax({
					url : "confirm_order.jhtml",
					type : 'POST',
					data : {
						order_id : order_id
					},
					success : function(data) {
						if (data.code == 1) {
							alert("保存成功！")
						} else {
							alert(data.msg)
						}
					}
				});
		})
	</script>
</aos:body>
</aos:html>
