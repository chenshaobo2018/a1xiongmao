﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="商家后台-商品列表">
<!-- common css and zjc_index css-->
	<link href="${cxt}/static/css/store/common.css" rel="stylesheet" type="text/css"/> 
	<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet" type="text/css"/> 
	<!-- js -->  
	<script type="text/javascript" src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script> 
	<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
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
                  <div class="mct_main_list">
			<div class="mct_left fl">
				<ul id="mct_left0">
					<li class="mct_hover" onclick="setTab(0,0)">商品列表</li>
					<li onclick="setTab(0,1)">待审商品</li>
					<li onclick="setTab(0,2)">下架商品</li>
					<li onclick="setTab(0,3)">用户评论</li> 
					<div class="clear"></div>
				</ul>
			</div>
			<div id="mcont0" class="mct_rig">
			<!-- 商家列表 -->
			<u style="display:block;">
			    <div class="mct_list">
				 <div class="mct_list_tit">
					<p>商品--商品列表</p>
				</div>		
				
				<!-- mct_l_c_one -->
			     <div class="mct_l_c_one">
			    <div class="mct_list_content">
			    <div class="bd_ionds_op">
				 <div class="mct_l_c_oneside fl">
				 
					 <div class="mct_hside fl">
						<select class="mcto_select" id="g_cat_id">
						<option value="">所有分类</option>
							<c:forEach var="cat" items="${sessionScope.categorys }">
								<option value="${cat.value }">${cat.display }</option>
							</c:forEach>
						</select>
						<select class="mcto_select_one" id="g_brand_id">
						<option value="">所有品牌</option>
							<c:forEach var="b" items="${sessionScope.brinds }">
									<option value="${b.id }">${b.name }</option>
								</c:forEach>
							</select>
					 </div>
					 <div class="mct_hcont fr">
					   <div class="mctops fl"><p>关键字 <input type="text" class="mcts_int" placeholder="搜索词" id="g_keywords"/> </p></div>
					   <div class="mctopc fr">
						 <span class="sp_img"><img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachGoods()"/></span>
					   </div>
					   <div class="clear"></div>								    
					 </div>
					 <div class="clear"></div>
				 </div>
				 <div class="mct_l_c_onecont fr">
					<button class="mct_btng" onclick="window.open('toaddgoods.jhtml', '_self')"><a>添加商品</a></button>							 
				 </div>
				<div class="clear"></div>
				
				</div>
			   </div>
			   <!-- mct_l_c_two -->
			   <div class="mct_l_c_two">
				 <table class="mct_tab_one">
				   <tr>
					 <!-- <th width="28" style="text-align: center;"></th>
					 <th width="50" style="text-align: center;" class="tr_h">编号</th> -->
					 <th width="120" class="lmct_name tr_h" style="text-align: center;">商品名称</th>
					 <th width="49" class="tr_h">货号</th>
					 <th width="88" class="tr_h">分类</th>
					 <th width="105" class="tr_h">在线支付</th>
					 <th width="95" class="tr_h">易支付</th>
					 <th width="67" class="tr_h">库存</th>
					 <th width="70" class="tr_h">销量</th>
					 <th width="100" class="tr_h">操作</th>
				   </tr>
				   <tbody id="t_goods"></tbody>
				 </table> 
			   </div>
			   <!-- mct_l_c_tree -->

			   <!-- mct_l_c_four -->
			   <div class="mct_l_c_four">
				  <div class="mct_page">
						  <ul>
						  	<li>第<span id="goods_nowPage" class="ppp">${goods.nowPage }</span>页,共<span id="goods_total">${goods.totalSize }</span>条</li>
							<li class="index_page"  onclick="serachGoods()"><a>首页</a></li>
							<li class="top_page"  onclick="goods_last_page()"><a>上一页</a></li>
							<li class="next_page"  onclick="goods_next_page()"><a>下一页</a></li>
							<li class="end_page"  onclick="goods_end_page()"><a>末页</a></li>
							<div class="clear"></div> 
						  </ul> 
						</div>
			   </div>
			</div>			
			   </div>	
			</u>
			<!-- 待审商品 -->
			<u>
			 <div  class="mct_list">
				<div class="mct_list_tit">
					<p>商品--待审商品</p>
				</div>						
				<div class="mct_list_content">
					<!-- mct_l_c_one -->
				   <div class="mct_l_c_one">
				   <div class="bd_ionds_op">
					 <div class="mct_l_c_oneside_two_de fl"> 
						   <div class="msctsid fl">								    
								<select class="mctotwo_lis" id="pd_cat_id">
									<option value="">全部分类</option>
								  <c:forEach var="cat" items="${sessionScope.categorys }">
									<option value="${cat.value }">${cat.display }</option>
								  </c:forEach>
								</select>				 					
								<select class="mctotwo_lis" id="pd_brand_id">
									<option value="">所有品牌</option>
									<c:forEach var="b" items="${sessionScope.brinds }">
										<option value="${b.id}">${b.name }</option>
									</c:forEach>
								</select>
						   </div>
						    <div class="mct_hcont_de fr">								   
						   <div class="mctops fl"><p>关键字 <input type="text" class="mcts_int" placeholder="搜索词" id="pd_keywords"/> </p></div>
						   <div class="mctopc fr">
							 <span class="sp_img"><img src="${cxt}/static/image/mct/seach.png" onclick="serachPendingGoods()"/></span>
						   </div>
						   <div class="clear"></div>
						 </div> 
						
					 </div>
					 <div class="mct_l_c_onecont_two fr">
						<button class="mct_btng" onclick="window.open('toaddgoods.jhtml', '_self')"><a>添加商品</a></button>							 
					 </div>
					<div class="clear"></div>
					</div>				
					
				   </div>
				   <!-- mct_d_c_one -->
				   <div class="mct_d_c_one">
					 <table class="mct_tab_two">
					   <tr>
						 <!-- <th width="28"></th>
						 <th width="20"><nobr>编号</nobr></th> -->
						 <th width="120" class="lmct_name tr_h">商品名称</th>
						 <th width="49" class="tr_h">货号</th>
						 <th width="88" class="tr_h">分类</th>
						 <th width="105" class="tr_h">在线支付</th>
						 <th width="95" class="tr_h">易支付</th>
						 <th width="67" class="tr_h">库存</th>
						 <th width="70" class="tr_h">销量</th>
						 <th width="40" class="tr_h">审核状态</th>
						 <th width="100" class="tr_h">操作</th>
					   </tr>
					   <tbody id="t_pd_goods"></tbody>
					 </table> 						   
				   </div>				  
				   <!-- mct_l_c_tree -->

				   <!-- mct_l_c_four -->
				   <div class="mct_l_c_four">
					  <div class="mct_page">
							  <ul>
							  	<li>第<span id="pd_goods_nowPage">${pending_goods.nowPage }</span>页,共<span id="pd_goods_total">${pending_goods.totalSize }</span>条</li>
								<li class="index_page" onclick="serachPendingGoods()"><a>首页</a></li>
								<li class="top_page" onclick="pd_goods_last_page()"><a>上一页</a></li>
								<li class="next_page" onclick="pd_goods_next_page()"><a>下一页</a></li>
								<li class="end_page" onclick="pd_goods_end_page()"><a>末页</a></li>
								<div class="clear"></div> 
							 </ul>
							</div>
				   </div>
				</div>
			 </div>			 	      
			</u> 
						<!-- 下架商品 -->
			<u>
			    <div class="mct_list">
				 <div class="mct_list_tit">
					<p>商品--下架商品列表</p>
				</div>		
				
				<!-- mct_l_c_one -->
			     <div class="mct_l_c_one">
			    <div class="mct_list_content">
				<form action="serach.jhtml" id="searchOne">
				<div class="bd_ionds_op">
				 <div class="mct_l_c_oneside fl">
					 <div class="mct_hside fl">
						<select class="mcto_select" id="us_cat_id">
						<option value="">所有分类</option>
							<c:forEach var="cat" items="${sessionScope.categorys }">
								<option value="${cat.value }">${cat.display }</option>
							</c:forEach>
						</select>
						<select class="mcto_select_one" id="us_brand_id">
						<option value="">所有品牌</option>
							<c:forEach var="b" items="${sessionScope.brinds }">
									<option value="${b.id }">${b.name }</option>
								</c:forEach>
							</select>
					 </div>
					 <div class="mct_hcont fr">
					   <div class="mctops fl"><p>关键字 <input type="text" class="mcts_int" placeholder="搜索词" name="us_keywords" id="us_keywords"/> </p></div>
					   <div class="mctopc fr">
						 <span class="sp_img"><img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachUnsale()"/></span>
					   </div>
					   <div class="clear"></div>								    
					 </div>
					 <div class="clear"></div>
				 </div>
				 </form>
				 <div class="mct_l_c_onecont fr">
					<button class="mct_btng" onclick="window.open('toaddgoods.jhtml', '_self')"><a>添加商品</a></button>							 
				 </div>
				<div class="clear"></div>
				</div>
			   </div>
			   <!-- mct_l_c_two -->
			   <div class="mct_l_c_two">
				 <table class="mct_tab_five" id="goods_list_1">
				   <tr>
					 <!-- <th width="28"></th>
					 <th width="50">编号</th> -->
					 <th width="120" class="lmct_name tr_h">商品名称</th>
					 <th width="49" class="tr_h">货号</th>
					 <th width="88" class="tr_h">分类</th>
					 <th width="105" class="tr_h">在线支付</th>
					 <th width="95" class="tr_h">易支付</th>
					 <th width="67" class="tr_h">库存</th>
					 <th width="70" class="tr_h">销量</th>
					 <th width="100" class="tr_h">操作</th>
				   </tr>
				   <tbody id="us_goods"></tbody>
				 </table> 
			   </div>
			   <!-- mct_l_c_tree -->

			   <!-- mct_l_c_four -->
			   <div class="mct_l_c_four">
				  <div class="mct_page">
						   <ul>
						   <li>第<span id="us_nowPage">${unsale.nowPage }</span>页,共<span id="us_total">${unsale.totalSize }</span>条</li>
								<li class="index_page"  onclick="serachUnsale()"><a>首页</a></li>
								<li class="top_page"  onclick="us_last_page()"><a>上一页</a></li>
								<li class="next_page" onclick="us_next_page()"><a>下一页</a></li>
								<li class="end_page" onclick="us_end_page()"><a>末页</a></li>
								<div class="clear"></div>
						  	</ul>
						</div>
			   </div>
			</div>			
			   </div>	
			</u> 
			<!-- 用户评论 -->
			<u>
			     <div class="mct_com">
				         <div class="mct_list_tit">
							<p>用户评论</p>
						</div>
						<div class="mct_com_one">
						<div class="sum_width">
								   <div class="mctops fl"><p><input type="text" class="mcts_int_dis" placeholder="搜索评论内容" id="com_conetent"/>
								   <input type="text" class="mcts_int_dis dis_input" placeholder="用户ID" id="com_user_id"/></div>
								   <div class="mctopc_cont fr">
								     <span class="sp_img2"><img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachComments()"/></span>
								   </div>
								   <div class="clear"></div>
							</div>								   
						</div>
						<div class="mct_com_tab">
						   <table class="mct_tab_four">
							   <tr>
							     <!-- <th width="35"></th>
							     <th width="35">编号</th> -->
							     <th width="12%" class="tr_h">用户ID</th>
							     <th class="tr_h" width="30%">评论内容</th>
							     <th width="16%" class="tr_h">商品</th>
							     <th width="12%" class="tr_h">评论时间</th>
							     <th class="lick list_td tr_h" width="30%">追加评论</th> 
							   </tr>
							   <tbody id="t_comments"></tbody>					
							 </tbody>
							 </table>
						</div>
						   <div class="mct_page">
						    <ul>
						  		<li>第<span id="com_nowPage">${comments.nowPage }</span>页,共<span id="com_total">${comments.totalSize }</span>条</li>
								<li class="index_page" onclick="serachComments()"><a>首页</a></li>
								<li class="top_page" onclick="com_last_page()"><a>上一页</a></li>
								<li class="next_page" onclick="com_next_page()"><a>下一页</a></li>
								<li class="end_page" onclick="com_end_page()"><a>末页</a></li>
								<div class="clear"></div> 
							 </ul>
							 </div>
				 
				 </div>
			</u> 

		</div>
		<div class="clear"></div>
		</div>   
   </div>
 <!-- mct_main end -->
 <!-- mct_footer start -->
   <div class="mct_footer">
    <div class="mctfoot_cent"><p>©2017-2025 北京中军创云易物联网科技有限公司 版权所有</p></div>
   </div>
	<h1 id="test"></h1>
 </div>
</aos:body>
<script>
$(function() {
	var liList = $(".nav_li li");
	for (var i = 0; i < liList.length; i++) {
		if (liList[i].innerText == '商品') {
			liList.eq(i).addClass("mct_click");
		}
	}
});

//搜索商品
function serachGoods(){
	//获取搜索条件
	var cat_id = $("#g_cat_id option:selected").val();
	var brand_id = $("#g_brand_id option:selected").val();
	var keywords = $("#g_keywords").val();
	console.log(keywords);
	$.post("serachGoods.jhtml",{
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'>"+g.goods_name+"</td><td>"+g.goods_sn+"</td><td>"+
				g.cat_name+"</td><td>"+g.shop_price+"</td><td>"+g.market_price+
				"</td><td>"+g.store_count+"</td><td>"+g.sales_sum+"</td><td><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='下架' title='下架' onclick='del_to_unsale(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_goods").empty();
		$("#t_goods").append(tr);
		$("#goods_nowPage").empty();
		$("#goods_nowPage").html(data.nowPage);
		$("#goods_total").empty();
		$("#goods_total").html(data.totalSize);
		});
	}
//商品上一页
function goods_last_page(){
	//获取搜索条件
	var cat_id = $("#g_cat_id option:selected").val();
	var brand_id = $("#g_brand_id option:selected").val();
	var keywords = $("#g_keywords").val();
	//获取当前页码
	var page = $("#goods_nowPage").text();
	page = parseInt(page);
	if(page <= 1){
		return;
	}
	$.post("serachGoods.jhtml",{
		"page":page-1,
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'>"+g.goods_name+"</td><td>"+g.goods_sn+"</td><td>"+
				g.cat_name+"</td><td>"+g.shop_price+"</td><td>"+g.market_price+
				"</td><td>"+g.store_count+"</td><td>"+g.sales_sum+"</td><td><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='下架' title='下架' onclick='del_to_unsale(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_goods").empty();
		$("#t_goods").append(tr);
		$("#goods_nowPage").empty();
		$("#goods_nowPage").html(data.nowPage);
		$("#goods_total").empty();
		$("#goods_total").html(data.totalSize);
		});
	}
//商品下一页
function goods_next_page(){
	//获取搜索条件
	var cat_id = $("#g_cat_id option:selected").val();
	var brand_id = $("#g_brand_id option:selected").val();
	var keywords = $("#g_keywords").val();
	//获取当前页码
	var page = $("#goods_nowPage").text();
	page = parseInt(page);
	var total = $("#goods_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachGoods.jhtml",{
		"page":page+1,
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'>"+g.goods_name+"</td><td>"+g.goods_sn+"</td><td>"+
				g.cat_name+"</td><td>"+g.shop_price+"</td><td>"+g.market_price+
				"</td><td>"+g.store_count+"</td><td>"+g.sales_sum+"</td><td><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='下架' title='下架' onclick='del_to_unsale(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_goods").empty();
		$("#t_goods").append(tr);
		$("#goods_nowPage").empty();
		$("#goods_nowPage").html(data.nowPage);
		$("#goods_total").empty();
		$("#goods_total").html(data.totalSize);
		});
	}
//商品最后一页
function goods_end_page(){
	//获取搜索条件
	var cat_id = $("#g_cat_id option:selected").val();
	var brand_id = $("#g_brand_id option:selected").val();
	var keywords = $("#g_keywords").val();
	//获取当前页码
	var page = $("#goods_nowPage").text();
	page = parseInt(page);
	var total = $("#goods_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachGoods.jhtml",{
		"page":((total / 10) + 1),
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'>"+g.goods_name+"</td><td>"+g.goods_sn+"</td><td>"+
				g.cat_name+"</td><td>"+g.shop_price+"</td><td>"+g.market_price+
				"</td><td>"+g.store_count+"</td><td>"+g.sales_sum+"</td><td><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='下架' title='下架' onclick='del_to_unsale(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_goods").empty();
		$("#t_goods").append(tr);
		$("#goods_nowPage").empty();
		$("#goods_nowPage").html(data.nowPage);
		$("#goods_total").empty();
		$("#goods_total").html(data.totalSize);
		});
	}

//计算总页数
function getTotalPage(total){
	if((total % 10) > 0){
		return total / 10 + 1;
	}else{
		return total / 10;
	}
}
//搜索待定的商品数据
function serachPendingGoods(){
	//获取搜索条件
	var cat_id = $("#pd_cat_id option:selected").val();
	var brand_id = $("#pd_brand_id option:selected").val();
	var goods_state_1 = $("#pd_goods_state_1 option:selected").val();
	var goods_state_2 = $("#pd_goods_state_2 option:selected").val();
	var keywords = $("#pd_keywords").val();
	$.post("serachPendingGoods.jhtml",{
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords,
		"goods_state_1":goods_state_1,
		"goods_state_2":goods_state_2
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'  width='120'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+
				g.cat_name+"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+
				"</td><td width='67'>"+g.store_count+"</td><td width='70'>"+g.sales_sum+"</td><td width='40'>"+g.goods_state_2+
				"</td><td  width='100'><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='撤销' title='撤销' onclick='revoke(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_pd_goods").empty();
		$("#t_pd_goods").append(tr);
		$("#pd_goods_nowPage").empty();
		$("#pd_goods_nowPage").html(data.nowPage);
		$("#pd_goods_total").empty();
		$("#pd_goods_total").html(data.totalSize);
	});
}

//待审核商品上一页
function pd_goods_last_page(){
	//获取搜索条件
	var cat_id = $("#pd_cat_id option:selected").val();
	var brand_id = $("#pd_brand_id option:selected").val();
	var goods_state_1 = $("#pd_goods_state_1 option:selected").val();
	var goods_state_2 = $("#pd_goods_state_2 option:selected").val();
	var keywords = $("#pd_keywords").val();
	//获取当前页码
	var page = $("#pd_goods_nowPage").text();
	page = parseInt(page);
	if(page <= 1){
		return;
	}
	$.post("serachPendingGoods.jhtml",{
		"page":page-1,
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords,
		"goods_state_1":goods_state_1,
		"goods_state_2":goods_state_2
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'  width='120'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+
				g.cat_name+"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+
				"</td><td width='67'>"+g.store_count+"</td><td width='70'>"+g.sales_sum+"</td><td width='40'>"+g.goods_state_2+
				"</td><td  width='100'><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='撤销' title='撤销' onclick='revoke(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_pd_goods").empty();
		$("#t_pd_goods").append(tr);
		$("#pd_goods_nowPage").empty();
		$("#pd_goods_nowPage").html(data.nowPage);
		$("#pd_goods_total").empty();
		$("#pd_goods_total").html(data.totalSize);
	});
	}
//待审核商品下一页
function pd_goods_next_page(){
	//获取搜索条件
	var cat_id = $("#pd_cat_id option:selected").val();
	var brand_id = $("#pd_brand_id option:selected").val();
	var goods_state_1 = $("#pd_goods_state_1 option:selected").val();
	var goods_state_2 = $("#pd_goods_state_2 option:selected").val();
	var keywords = $("#pd_keywords").val();
	//获取当前页码
	var page = $("#pd_goods_nowPage").text();
	page = parseInt(page);
	var total = $("#pd_goods_total").text();
	total = getTotalPage(parseInt(total));
	console.log(total);
    if(page >= total){
		return;
	}
    
	$.post("serachPendingGoods.jhtml",{
		"page":page+1,
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords,
		"goods_state_1":goods_state_1,
		"goods_state_2":goods_state_2
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'  width='120'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+
				g.cat_name+"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+
				"</td><td width='67'>"+g.store_count+"</td><td width='70'>"+g.sales_sum+"</td><td width='40'>"+g.goods_state_2+
				"</td><td  width='100'><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='撤销' title='撤销' onclick='revoke(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_pd_goods").empty();
		$("#t_pd_goods").append(tr);
		$("#pd_goods_nowPage").empty();
		$("#pd_goods_nowPage").html(data.nowPage);
		$("#pd_goods_total").empty();
		$("#pd_goods_total").html(data.totalSize);
	});
}
//待审核商品最后一页
function pd_goods_end_page(){
	//获取搜索条件
	var cat_id = $("#pd_cat_id option:selected").val();
	var brand_id = $("#pd_brand_id option:selected").val();
	var goods_state_1 = $("#pd_goods_state_1 option:selected").val();
	var goods_state_2 = $("#pd_goods_state_2 option:selected").val();
	var keywords = $("#pd_keywords").val();
	//获取当前页码
	var page = $("#pd_goods_nowPage").text();
	page = parseInt(page);
	var total = $("#pd_goods_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachPendingGoods.jhtml",{
		"page":((total / 10) + 1),
		"cat_id":cat_id,
		"brand_id":brand_id,
		"keywords":keywords,
		"goods_state_1":goods_state_1,
		"goods_state_2":goods_state_2
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td class='lmct_name'  width='120'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+
				g.cat_name+"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+
				"</td><td width='67'>"+g.store_count+"</td><td width='70'>"+g.sales_sum+"</td><td width='40'>"+g.goods_state_2+
				"</td><td  width='100'><span class='mct_add'><img src='${cxt }/static/image/mct/mct_add.png' alt='编辑' title='编辑' onclick='edit(this)' style='height:30px;'/></span>"+
				"<span class='mct_delete'><img src='${cxt }/static/image/mct/mct_delete.png' alt='撤销' title='撤销' onclick='revoke(this)' style='height:30px;'/></span></td></tr>";
			});
		$("#t_pd_goods").empty();
		$("#t_pd_goods").append(tr);
		$("#pd_goods_nowPage").empty();
		$("#pd_goods_nowPage").html(data.nowPage);
		$("#pd_goods_total").empty();
		$("#pd_goods_total").html(data.totalSize);
	});
}
//搜索评论
function serachComments(){
	//获取搜索条件
	var user_id = $("#com_user_id").val();
	var content = $("#com_content").val();
	$.post("serachComments.jhtml",{
		"user_id":user_id,
		"content":content
	},function(data){
		var tr = "";
		$.each(data.list, function(index, com){
			tr +="<tr><td class='lmct_name'>"+com.user_id+"</td><td>"+com.content+"</td><td>"+
				com.goods_name+"</td><td>"+com.add_time+"<td><div class='link_mct'><p><a class='tooltip' title='最佳评论'>"+
				com.again_content+"</a></p>"+"</div></td></tr>";
		});
		$("#t_comments").empty();
		$("#t_comments").append(tr);
		$("#com_nowPage").empty();
		$("#com_nowPage").html(data.nowPage);
		$("#com_total").empty();
		$("#com_total").html(data.totalSize);
	});
}

//评论上一页
function com_last_page(){
	//获取搜索条件
	var user_id = $("#com_user_id").val();
	var content = $("#com_content").val();
	//获取当前页码
	var page = $("#com_nowPage").text();
	page = parseInt(page);
	if(page <= 1){
		return;
	}
	$.post("serachComments.jhtml",{
		"page":page-1,
		"user_id":user_id,
		"content":content
	},function(data){
		var tr = "";
		$.each(data.list, function(index, com){
			tr +="<tr><td class='lmct_name'>"+com.user_id+"</td><td>"+com.content+"</td><td>"+
			com.goods_name+"</td><td>"+com.add_time+"<td><div class='link_mct'><p><a class='tooltip' title='最佳评论'>"+
			com.again_content+"</a></p>"+"</div></td></tr>";
		});
		$("#t_comments").empty();
		$("#t_comments").append(tr);
		$("#com_nowPage").empty();
		$("#com_nowPage").html(data.nowPage);
		$("#com_total").empty();
		$("#com_total").html(data.totalSize);
		});
	}
//评论下一页
function com_next_page(){
	//获取搜索条件
	var user_id = $("#com_user_id").val();
	var content = $("#com_content").val();
	//获取当前页码
	var page = $("#com_nowPage").text();
	page = parseInt(page);
	var total = $("#com_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachComments.jhtml",{
		"page":page+1,
		"user_id":user_id,
		"content":content
	},function(data){
		var tr = "";
		$.each(data.list, function(index, com){
			tr +="<tr><td class='lmct_name'>"+com.user_id+"</td><td>"+com.content+"</td><td>"+
			com.goods_name+"</td><td>"+com.add_time+"<td><div class='link_mct'><p><a class='tooltip' title='最佳评论'>"+
			com.again_content+"</a></p>"+"</div></td></tr>";
		});
		$("#t_comments").empty();
		$("#t_comments").append(tr);
		$("#com_nowPage").empty();
		$("#com_nowPage").html(data.nowPage);
		$("#com_total").empty();
		$("#com_total").html(data.totalSize);
	});
}
//评论最后一页
function com_end_page(){
	//获取搜索条件
	var user_id = $("#com_user_id").val();
	var content = $("#com_content").val();
	//获取当前页码
	var page = $("#com_nowPage").text();
	page = parseInt(page);
	var total = $("#com_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachComments.jhtml",{
		"page":((total / 10) + 1),
		"user_id":user_id,
		"content":content
	},function(data){
		var tr = "";
		$.each(data.list, function(index, com){
			tr +="<tr><td class='lmct_name'>"+com.user_id+"</td><td>"+com.content+"</td><td>"+
			com.goods_name+"</td><td>"+com.add_time+"<td><div class='link_mct'><p><a class='tooltip' title='最佳评论'>"+
			com.again_content+"</a></p>"+"</div></td></tr>";
		});
		$("#t_comments").empty();
		$("#t_comments").append(tr);
		$("#com_nowPage").empty();
		$("#com_nowPage").html(data.nowPage);
		$("#com_total").empty();
		$("#com_total").html(data.totalSize);
	});
}

//搜索下架
function serachUnsale(){
	//获取搜索条件
	var keywords = $("#us_keywords").val();
	var cat_id = $("#us_cat_id option:selected").val();
	var brand_id = $("#us_brand_id option:selected").val();
	$.post("serachUnsale.jhtml",{
		"keywords":keywords,
		"cat_id":cat_id,
		"brand_id":brand_id
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td width='120' class='lmct_name'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+g.cat_name+
			"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+"</td><td width='67'>"+g.store_count+
			"</td><td width='70'>"+g.sales_sum+"</td><td width='100'>"+
			"<span><img src='${cxt }/static/image/mct/mct_gr.png' alt='上架' title='重新上架' onclick='grounding(this)' style='height:30px;'></span></td></tr>";
		});
		$("#us_goods").empty();
		$("#us_goods").append(tr);
		$("#us_nowPage").empty();
		$("#us_nowPage").html(data.nowPage);
		$("#us_total").empty();
		$("#us_total").html(data.totalSize);
	});
}

function us_last_page(){
	//获取搜索条件
	var keywords = $("#us_keywords").val();
	var cat_id = $("#us_cat_id option:selected").val();
	var brand_id = $("#us_brand_id option:selected").val();
	//获取当前页码
	var page = $("#us_nowPage").text();
	page = parseInt(page);
	if(page <= 1){
		return;
	}
	$.post("serachUnsale.jhtml",{
		"page":page-1,
		"keywords":keywords,
		"cat_id":cat_id,
		"brand_id":brand_id
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td width='120' class='lmct_name'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+g.cat_name+
			"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+"</td><td width='67'>"+g.store_count+
			"</td><td width='70'>"+g.sales_sum+"</td><td width='100'>"+
			"<span><img src='${cxt }/static/image/mct/mct_gr.png' alt='上架' title='重新上架' onclick='grounding(this)' style='height:30px;'></span></td></tr>";
		});
		$("#us_goods").empty();
		$("#us_goods").append(tr);
		$("#us_nowPage").empty();
		$("#us_nowPage").html(data.nowPage);
		$("#us_total").empty();
		$("#us_total").html(data.totalSize);
	});
}
//回收站下一页
function us_next_page(){
	//获取搜索条件
	var keywords = $("#us_keywords").val();
	var cat_id = $("#us_cat_id option:selected").val();
	var brand_id = $("#us_brand_id option:selected").val();
	//获取当前页码
	var page = $("#us_nowPage").text();
	page = parseInt(page);
	var total = $("#us_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachUnsale.jhtml",{
		"page":page+1,
		"keywords":keywords,
		"cat_id":cat_id,
		"brand_id":brand_id
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td width='120' class='lmct_name'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+g.cat_name+
			"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+"</td><td width='67'>"+g.store_count+
			"</td><td width='70'>"+g.sales_sum+"</td><td width='100'>"+
			"<span><img src='${cxt }/static/image/mct/mct_gr.png' alt='上架' title='重新上架' onclick='grounding(this)' style='height:30px;'></span></td></tr>";
		});
		$("#us_goods").empty();
		$("#us_goods").append(tr);
		$("#us_nowPage").empty();
		$("#us_nowPage").html(data.nowPage);
		$("#us_total").empty();
		$("#us_total").html(data.totalSize);
	});
}
//回收站最后一页
function us_end_page(){
	//获取搜索条件
	var keywords = $("#us_keywords").val();
	var cat_id = $("#us_cat_id option:selected").val();
	var brand_id = $("#us_brand_id option:selected").val();
	//获取当前页码
	var page = $("#us_nowPage").text();
	page = parseInt(page);
	var total = $("#us_total").text();
	total = parseInt(total);
    if(page >= getTotalPage(total)){
		return;
	}
	$.post("serachUnsale.jhtml",{
		"page":((total / 10) + 1),
		"keywords":keywords,
		"cat_id":cat_id,
		"brand_id":brand_id
	},function(data){
		var tr = "";
		$.each(data.list, function(index, g){
			tr +="<tr><td style='display:none' class='goods_id'>"+g.goods_id+"</td><td width='120' class='lmct_name'>"+g.goods_name+"</td><td width='49'>"+g.goods_sn+"</td><td width='88'>"+g.cat_name+
			"</td><td width='105'>"+g.shop_price+"</td><td width='95'>"+g.market_price+"</td><td width='67'>"+g.store_count+
			"</td><td width='70'>"+g.sales_sum+"</td><td width='100'>"+
			"<span><img src='${cxt }/static/image/mct/mct_gr.png' alt='上架' title='重新上架' onclick='grounding(this)' style='height:30px;'></span></td></tr>";
		});
		$("#us_goods").empty();
		$("#us_goods").append(tr);
		$("#us_nowPage").empty();
		$("#us_nowPage").html(data.nowPage);
		$("#us_total").empty();
		$("#us_total").html(data.totalSize);
	});
}
//商品修改
function edit(v){
	var tr = v.parentNode.parentNode.parentNode;
	var goods_id = $(tr).find(".goods_id").text();
	window.location.href="goodsDetial.jhtml?goods_id="+goods_id;
}

//待审商品撤销审核
function revoke(v){
	var tr = v.parentNode.parentNode.parentNode;
	var goods_id = $(tr).find(".goods_id").text();
	$.ajax({
		url:"revoke.jhtml",
		type:"get",
		data:{
			"goods_id":goods_id
		},
		success:function(data){
			if(data.code == 1){
				serachGoods();
				serachUnsale();
				serachPendingGoods();
				serachRe();
			}
			alert_msg(data.msg);
		}
	});
}
//商场商品到下架
function del_to_unsale(v){
	var tr = v.parentNode.parentNode.parentNode;
	var goods_id = $(tr).find(".goods_id").text();
	$.ajax({
		url:"del_to_unsale.jhtml",
		type:"get",
		data:{
			"goods_id":goods_id
		},
		success:function(data){
			if(data.code == 1){
				serachGoods();
				serachUnsale();
				serachPendingGoods();
				serachRe();
			}
			alert_msg(data.msg);
		}
	});
}
//商品重新上架
function grounding(v){
	var tr = v.parentNode.parentNode.parentNode;
	var goods_id = $(tr).find(".goods_id").text();
	$.post("grounding.jhtml",{
		"goods_id":goods_id
	}, function(data){
		alert_msg(data.msg);
		if(data.code == 1){
			serachGoods();
			serachUnsale();
			serachPendingGoods();
			serachRe();
		}
	});
}

$(function(){//页面加载完成加载数据
	serachGoods();
	serachPendingGoods();
	serachComments();
	serachUnsale();
})
</script>
</aos:html>
