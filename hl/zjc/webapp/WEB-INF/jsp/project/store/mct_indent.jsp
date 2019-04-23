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

	<script type="text/x-handlebars-template" id="datalist">
			<tr>
	 			<th width="28"></th>
	 			<th width="200">订单编号</th>
	 			<th width="120">会员ID</th>
	 			<th width="100">用户名</th>
	 			<th width="200">商品名称</th>
	 			<th width="100">订单金额</th>
	 			<th width="300">成单时间</th> 
	 			<th width="150">转账时间</th> 
	 			<th width="150">转账状态</th> 
  			</tr>
  			{{#each this}}
			<tr>
	 		  <td width="28">								   
				<div class="bill_list_zm fl">
					<input class="check_value" type="checkbox" value="{{order_id}}" >
					
				</div>
			   </td>
	 		  <td width="200">{{order_sn}}</td>
	 		  <td width="120">{{user_id}}</td>
	 		  <td width="100">{{real_name}}</td>
			  <td width="200">{{goods_name}}</td>
	 		  <td width="100">{{money}}</td>
	  		  <td width="300">{{add_time}}</td>
	  	  	  <td width="300">{{transfer_time}}</td>
	 		  <td width="50">{{transfer_type}}</td>
  			</tr>
			{{/each}}
	
	</script>
		<script>
		 
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
	</style> 
<aos:body>
	<div class="mct_container">
   <%@ include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
   <div class="mct_main">
   	  <div class="mct_main_list">
		<div class="mct_left fl">
			<ul id="mct_left0">
				<li class="mct_hover" onclick="setTab(0,0)">订单列表</li>
				<li onclick="setTab(0,1)">发货单</li> 
				<li onclick="setTab(0,2)">账单明细</li> 
				<li onclick="setTab(0,3)">催单退货</li> 
			</ul>
		</div>
		<div id="mcont0" class="mct_rig">
			<!-- 商家列表 -->
			<u style="display:block;">
			<div class="mct_list">
				<div class="dis_show">
					<div class="mct_list_tit">
						<p>订单--订单列表</p>
					</div>
					<div class="mctindent_one">
						<div class="midentone_top fl">
							<span class="mio_one">收货人 <input type="text" placeholder="收货人" id="f_consignee"/></span>
							<span class="mio_two">订单编号 <input type="text" placeholder="订单编号" id="f_order_sn"/></span>
							<span class="mio_tree">商品名称 <input type="text" placeholder="商品名称" id="f_goods_name"/></span>
							<span class="mio_four">下单日期(起) <input type="date" placeholder="0000-00-00-0000-00-00" id="f_add_time_s"/></span>
							<span class="mio_four">下单日期(止) <input type="date" placeholder="0000-00-00-0000-00-00" id="f_add_time_e"/></span>
						</div>
					    <div class="midentone_btn fr">
					        <select class="midne sel_two" id="f_pay_code">
						        <option value="">支付方式</option>
						        <c:forEach var="pt" items="${sessionScope.pay_type }">
						     		<option value="${pt.value }">${pt.display }</option>
						        </c:forEach>
						    </select>
						    <select class="midne sel_two" id="f_pay_status">
						        <option value="">支付状态</option>
						        <c:forEach var="pt" items="${sessionScope.pay_status }">
						     		<option value="${pt.value }">${pt.display }</option>
						        </c:forEach>
						    </select>
						    <select class="midne sel_tree" id="f_order_status">
							     <option value="">订单状态</option>
							     <c:forEach var="os" items="${sessionScope.order_status }">
							     	<option value="${os.value }">${os.display }</option>
							     </c:forEach>
							</select>
							<select class="midne sel_tree" id="f_shipping_status">
							     <option value="">发货状态</option>
							     <c:forEach var="os" items="${sessionScope.shipping }">
							     	<option value="${os.value }">${os.display }</option>
							     </c:forEach>
							</select>
							<span class="min_span"> <img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachOrder()"></span>
							<span class="min_span"> <img src="${cxt}/static/image/mct/export.png" alt="导出" title="导出" onclick="exportOrder()"></span>
						</div> 
						<div class="clear"></div>
					</div>
					<div class="mctindent_two">
					     <table >
						       <tr>
							       <th width="12%" class="tr_h">订单编号</th>
							       <th width="6%" class="tr_h">会员ID</th>
							       <th width="6%" class="tr_h">收货人</th>
							       <th width="8%" class="tr_h">商品名称</th>
							       <th width="7%" class="tr_h">现金/券</th>
							       <th width="6%" class="tr_h">商品数量</th>
							       <th width="6%" class="tr_h">收货地址</th>
							       <th width="6%" class="tr_h">收货人电话</th>
							       <th width="6%" class="tr_h">支付状态</th>
							       <th width="6%" class="tr_h">发货状态</th>
							       <th width="6%" class="tr_h">下单时间</th>
							       <th width="6%" class="tr_h">发货时间</th>
							       <th width="6%" class="tr_h">收货时间</th> 
							       <th width="7%" class="tr_h">返分时间</th> 
							       <th width="7%" class="tr_h">操作</th>
							   </tr>
							   <tbody id="t_f_order"></tbody>
						 </table>
					 </div>
					<div class="mct_l_c_four">
						<div class="mct_page">
							  <ul id="f_index">
							  	 <li>第<span id="f_order_nowPage">${orders.nowPage }</span>页,共<span id="f_order_total">${orders.totalSize }</span>条</li>
							  	 <li class="index_page" onclick="serachOrder()"><a>首页</a></li>
							  	 <li class="top_page" onclick="order_last_page()"><a>上一页</a></li>
								 <li class="next_page" onclick="order_next_page()"><a>下一页</a></li>
								 <li class="end_page" onclick="order_end_page()"><a>末页</a></li>
								 <div class="clear"></div> 
							  </ul> 
					   </div>
				    </div>
					 
				</div>
				 <!-- 订单列表操作 -->
				 <div class="dis_hide">
				 <input type="hidden" id="hide_id">
				     <div class="dish_side fl" id="confrim_order"> 
						 <div class="sdpl_tab">
							 <table class="tablse">
								<tr class="tbind_one">
								   <td colspan="9" align="center">基本信息</td>
								</tr>
								<tr class="tbind_two">
								   <td width="65">会员ID</td>
								   <td width="150">订单编号</td>
								   <td width="80">姓名</td>
								   <td width="80">电话</td>
								   <td width="50">现金/券</td>
								   <td width="100">订单状态</td>
								   <td width="100">下单时间</td>
								   <td width="100">支付时间</td>
								   <td width="150">支付方式</td>
								</tr>
								<tr class="tbind_tree" id="order_basc_info">
								</tr>
								</table>
							 </div>
							 <div class="sdpl_tab">
								<table class="tablse">
								<tr class="tbind_one">
								   <td colspan="9" align="center">收货信息</td>
								</tr>
								<tr class="tbind_two">
								   <td width="112">收货人姓名</td>
								   <td width="160">联系方式</td>
								   <td width="317">邮寄地址</td> 
								</tr>
								<tr class="tbind_tree" id="order_rece_info">
								</tr>
								</table>
							 </div>
							<div class="sdpl_tab">
								<table class="tablse" id="t_order_goods">
									<tr class="tbind_one">
									   <td colspan="9" align="center">商品信息</td>
									</tr>
									<tr class="tbind_two">
									   <td>商品名称</td>
									   <td>数量</td>
									   <td>单价</td> 
									   <td>小计</td>
									   <td>用户备注</td>
									</tr>
									<tr class="tbind_tree order_goods_info">
									</tr>
							   </table>
								</div>
							<!-- <div class="sdpl_tab">
								<table class="tablse">
									<tr class="tbind_one">
									   <td colspan="9" align="center">费用信息</td>
									</tr>
									<tr class="tbind_two">
									   <td width="119">小计</td>
									   <td width="89">运费</td>
									</tr>
									<tr class="tbind_tree" id="order_cost_info">
									</tr>
							   </table>
								</div> -->
							<div class="sdpl_tab">
								<table class="tablse">
									<tr class="tbind_one">
									   <td colspan="9" align="center">操作信息</td>
									</tr>
									<tr class="tbind_two">
									   <td width="119">操作者</td>
									   <td width="89">操作时间</td>
									   <td>订单状态</td> 
									   <td>付款状态</td>
									   <td>发货状态</td>
									   <td>描述</td>
									   <td>备注</td>
									   <td></td>
									   <td></td>
									</tr>
									<tbody id="order_do_info"></tbody>
							   </table>
								</div>
							<div class="sdpl_add">
							   <div class="sdpl_addside fl">
								  操作备注
							   </div>
							   <div class="sdpl_addcont fl">
								 <textarea class="asp_text" id="add_remark"></textarea>
							   </div>
							   <div class="clear"></div>
							
							</div>
							<div class="sdpl_btn">
							   <div class="skdf_righ">
							   <input type="hidden" id="hide_pay_status">
							     <button class="savs_ind_btn" onclick="invo_comfirm()">确认订单</button>
							     <button class="savs_ind_btn" id=cancle1>取消</button>
							   </div>
							   <div class="clear"></div>
							</div> 
					 </div>
				     <div class="dish_cont fr">
					   <button class="sd_ok" onclick="show_print(this)">打印订单</button>
					 </div>
				     <div class="clear"></div>
				     <!-- 遮罩层打印 -->
				      <div class="print_ok">
					     <div class="print_all">
						 <div  id="printArea">
						    <div class="print_one">
							  <table>
								<tr class="prints_one">
								   <td colspan="9" align="center">基本信息</td>
								</tr>
								<tr class="prints_two">
								   <td width="65">会员ID</td>
								   <td width="150">订单编号</td>
								   <td width="80">姓名</td>
								   <td width="80">电话</td>
								   <td width="50">现金/券</td>
								   <td width="100">订单状态</td>
								   <td width="100">下单时间</td>
								   <td width="100">支付时间</td>
								   <td width="150">支付方式</td>
								</tr>
								<tbody id="p_order_base"></tbody>
							  </table>
							</div>
							 <div class="print_one">
								<table>
								<tr class="prints_one">
								   <td colspan="9" align="center">收货信息</td>
								</tr>
								<tr class="prints_two">
								   <td width="112">收货人姓名</td>
								   <td width="160">联系方式</td>
								   <td width="317">邮寄地址</td>
								</tr>
								<tbody id="p_order_rec"></tbody>
								</table>
							 </div>
							<div class="print_one">
								<table>
									<tr class="prints_one">
									   <td colspan="9" align="center">商品信息</td>
									</tr>
									<tr class="prints_two">
									   <td>商品名称</td>
									   <td>数量</td>
									   <td>单价</td> 
									   <td>小计</td>
									   <td>用户备注</td>
									</tr>
									<tbody id="p_order_goods"></tbody>
							   </table>
								</div>
							<!-- <div class="print_one">
								<table>
									<tr class="prints_one">
									   <td colspan="9" align="center">费用信息</td>
									</tr>
									<tr class="prints_two">
									   <td width="119">小计</td>
									   <td width="89">运费</td>
									</tr>
									<tbody id="p_order_cost"></tbody>
							   </table>
								</div> -->
						 </div>
						 <div class="prin_adover">
						   <button class="piner" onclick="onprint()">确认打印</button>
						    <button class="savs_ind_btn" id="cancle2" onclick="can_tbn()">返回</button>
						 </div>
						 </div> 
					  </div>
				      <div class="print_make"></div> 
				 </div>
				 <!-- 填写发货单 -->
				 <div class="dis_hide_write">
				       <div class="dish_side fl">
					      <div class="sid_h_tab">
						    <table>
							  <tr class="disptr">
							     <td colspan="3" align="center">基本信息</td>
							  </tr>
							  <tr class="disptr_two" id="t_in_basic">
							     <td width="307">订单号：201701190000001</td>
							     <td width="343">下单时间：0000-00-00   00-00-00</td>
							     <td width="172">物流费用：0.00</td>
							  </tr>
							  <tr class="disptr_tree" style="background: #eee;color:#4c4c4c">
							     <td colspan='3'><!-- onkeyup="this.value=this.value.replace(/[^\d]/g,'') " -->
							    	 物流单号：<input type="text" class="text" id="invoice_no" />
							    	 <label>必填</label>
							     </td>
							  </tr>
							</table>
						  </div>
					      <div class="sid_h_tab">
						    <table>
							  <tr class="disptr">
							     <td colspan="3" align="center">收货信息</td>
							  </tr>
							  <tr class="disptr_tree" id="t_in_rece"></tr>  
							</table>
						  </div>
					      <div class="sid_h_tab">
						    <table id="t_in_goods" class="tablse">
							  <tr class="disptr">
							     <td colspan="5" align="center">商品信息</td>
							  </tr> 
							  <tr class="disptr_two">
							     <td width="117">商品名称</td>
							     <td width="80">数量</td>
							     <td width="148">单价</td>
							     <td width="155">小计</td>
							     <td width="320">用户备注</td>
							  </tr>
							</table>
						  </div>
						  <div class="sdpl_add">
							   <div class="sdpl_addside fl">
								  操作备注
							   </div>
							   <div class="sdpl_addcont fl">
								 <textarea class="asp_text" id="remark"></textarea>
							   </div>
							   <div class="clear"></div>
							
							</div>
						  <div class="div_btns">
						  <button class="all_btns dis_btns" onclick="delivery()" id="delivery">确认发货</button>
						     <button class="savs_ind_btn" id="cancle3">返回</button>
						  </div>
					   
					   </div>
				       <div class="dish_cont fr">
							<button class="sd_okems" onclick="show_print_o(this)">打印订单</button>
					   </div>
				     <div class="clear"></div>
				 </div>
				 <!-- 遮罩层 -->
				 <div class="print_okems">
				    <div class="prins_ok" id="printAreas">
					  <table id="print_order_o"></table>
					</div>
				   <div class="prin_ok">
				      <button class="prin_sd" onclick="onprints()">确认打印</button>
				      <button class="prin_sd" onclick="cancle_prints()">取消打印</button>
				   </div>
				 </div>
				 <div class="print_okems_make"></div>
				 
				 </div>
			</u>
			<!-- 发货单 -->
			<u>
			 <div class="mct_list">
				<div class="bill_top">
				     <div class="mct_list_tit">
						<p>订单--发货单列表</p>
					 </div>
					 <div class="bill_show">
					 <div class="boll_cen">
					   <span class="bollc_one name_bill">收货人 <input type="text" class="bill_in" placeholder=" 姓名" id="f_invo_consignee"/></span>
					   <span class="bollc_one id_bill">订单号 <input type="text" class="bill_in" placeholder=" 编号" id="f_invo_order_sn"/></span>
					   <span class="bollc_one sh_bill">商品 <input type="text" class="bill_in" placeholder=" 名称" id="f_invo_goods_name"/></span>
					   <span class="mio_four">下单日期 <input type="date" placeholder="0000-00-00-0000-00-00" id="ff_add_time"></span>
					   <span class="bollc_two"><img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachInvo()"></span>
					 </div>
					 <div class="bill_tab">
					    <table id="t_s_order">
						  <tr>
							 <th width="10%" class="lmct_name tr_h">订单号</th>
							 <th width="15%" class="tr_h">支付时间</th>
							 <th width="15%" class="tr_h">发货时间</th>
							 <th width="15%" class="tr_h">物流单号</th>
							 <th width="10%" class="tr_h">收货人</th>
							 <th width="20%" class="tr_h">商品名称</th>
							 <th width="15%" class="tr_h">订单总价</th> 
						   </tr>
						   <tbody id="t_invo"></tbody>
						</table>
					   
					 </div>
					 <div class="mct_l_c_four">
						  <div class="mct_page">
								  <ul>
									 <li>第<span id="invo_nowPage">${invoices.nowPage }</span>页,共<span id="invo_total">${invoices.totalSize }</span>条</li>
								  	 <li class="index_page" onclick="serachInvo()"><a>首页</a></li>
								  	 <li class="top_page" onclick="invo_last_page()"><a>上一页</a></li>
									 <li class="next_page" onclick="invo_next_page()"><a>下一页</a></li>
									 <li class="end_page" onclick="invo_end_page()"><a>末页</a></li>
									 <div class="clear"></div> 
								  </ul> 
								</div>
					   </div> 
				   </div>
					<div class="bill_hide">
					  					 <div class="bill_tbas">
							 <table class="tablse">
								<tr class="tbind_one">
								   <td colspan="9" align="center">基本信息</td>
								</tr>
								<tr class="tbind_two">
								   <td width="65">会员ID</td>
								   <td width="80">订单编号</td>
								   <td width="62">姓名</td>
								   <td width="83">电话</td>
								   <td width="70">现金/券</td>
								   <td width="157">订单状态</td>
								   <td width="100">下单时间</td>
								   <td width="100">支付时间</td>
								   <td width="94">支付方式</td>
								</tr>
								<tbody id="b_hide_base"></tbody>
								</table>
							 </div>
							 <div class="bill_tbas">
								<table class="tablse">
								<tr class="tbind_one">
								   <td colspan="5" align="center">收货信息</td>
								</tr>
								<tr class="tbind_two">
								   <td>收货人姓名</td>
								   <td>联系方式</td>
								   <td>邮寄地址</td>
								   <td>快递单号</td>  
								</tr>
								<tbody id="b_hide_rec"></tbody>
								</table>
							 </div>
							<div class="bill_tbas">
								<table class="tablse">
									<tr class="tbind_one">
									   <td colspan="9" align="center">商品信息</td>
									</tr>
									<tr class="tbind_two">
									   <td>商品名称</td>
									   <td>数量</td>
									   <td>单价</td> 
									   <td>小计</td>
									   <td>用户备注</td>
									</tr>
									<tbody id="b_hide_goods"></tbody>
							   </table>
								</div>
							<!-- <div class="bill_tbas">
								<table class="tablse">
									<tr class="tbind_one">
									   <td colspan="9" align="center">费用信息</td>
									</tr>
									<tr class="tbind_two">
									   <td width="119">小计</td>
									   <td width="89">运费</td>
									</tr>
									<tbody id="b_hide_cost"></tbody>
							   </table>
								</div> -->
							 <div class="mct_ind_end">
							     <button class="savs_ind_btn" id="cancle">返回</button>
							   </div>
					</div>				   
				   </div> 	 
		    </div> 	
		    </u> 
		      
		    <u>
		    <div class="mct_list">			   
					 <div class="mct_list_tit">
						<p>账单明细</p>
					 </div>
					 <div class="mct_zm_one">
					    <div class="mctzm_one">
						  <span class="bollc_one mc_span">会员ID <input type="text" class="mctzm_input" id="Transfer_user_id"/></span>
						  <span class="bollc_one mc_span">用户名 <input type="text" class="mctzm_input" id="Transfer_real_name"/></span>
						  <span class="bollc_one mc_span">订单号 <input type="text" class="mctzm_input" id="Transfer_order_sn"/></span>
						    <span class="bollc_one mc_span">支付方式：
						   <select class="midne sel_two" id="Transfer_pay_code">
						            <option value="">支付状态</option>
						     		<option value="1">优选商品</option>
						     		<option value="2">热销预售</option>
						     		<option value="3">礼品兑换</option>
						    </select>
						    </span>
						    <span class="bollc_one mc_span">转账状态：
						   <select class="midne sel_two" id="transfer_type">
						            <option value="">转账状态</option>
						            <option value="0">未转账</option>
						     		<option value="1">已转账</option>
						     		
						    </select>
						    </span>
						  <span class="mio_four">下单日期(起) <input type="date" placeholder="0000-00-00-0000-00-00" id="Transfer_s_add_time"/></span>
							<span class="mio_four">下单日期(止) <input type="date" placeholder="0000-00-00-0000-00-00" id="Transfer_e_add_time"/></span>
						</div> 
						<%-- <span class=""mcfg_btn_one""> <img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="check_order_page(1)"></span> --%>
						<button class="mcfg_btn_one" onclick="check_order_page(1);">搜索</button>
					     <button class="mcfg_btn_one" onclick="check_div_page();">转账确认</button>
						 <button class="mcfg_btn_one" onclick="checkexportOrder();">导出</button> 
						 <!--<span class="min_span"> <img src="${cxt}/static/image/mct/export.png" alt="导出" title="导出" onclick="checkexportOrder()"></span> -->
					 </div>
					 <div class="mct_zm_two">
					    <div class="mcdt_cont fr">
					    
						</div>
					    <div class="clear"></div>
					 </div>
					 <div class="mct_zm_tree" id="mct_zm"> 
					    <table id="data_table">
						   
						</table>					    
					 </div>
						<div class="mct_l_c_four">
						  <div class="mct_page" >
								  <ul>
									 <li>第<span id="data_nowPage">${data.nowPage }</span>页,共<span id="data_totalPage">${data.totalPage }</span>页,共<span id="data_total">${data.nowPage }</span>条</li>
									 <li class="next_page" onclick="check_order_page(1)"><a href="#">首页</a></li>
									 <li class="top_page" onclick="check_order_page(2)"><a href="#">下一页</a></li>
									 <li class="index_page" onclick="check_order_page(3)"><a href="#">上一页</a></li>
									 <li class="end_page" onclick="check_order_page(4)"><a href="#">末页</a></li> <div class="clear"></div> 
								  </ul> 
							</div>
					   </div>
			   </div>	      
			</u>  
			
			<!-- 催单退单 -->
			 <u>
		    <div class="mct_list">			   
					 <div class="dis_show">
					<div class="mct_list_tit">
						<p>订单--催单退货列表</p>
					</div>
					<div class="mctindent_one">
					<div class="mope_list fl">
						<div class="midentone_top_list fl">
							<span class="mio_one">会员ID <input type="text" placeholder="会员ID" id="t_user_id"/></span>
							<span class="mio_one">收货人 <input type="text" placeholder="收货人" id="t_consignee"/></span>
							<span class="mio_two">订单编号 <input type="text" placeholder="订单编号" id="t_order_sn"/></span>
						    <span>支付方式
						    <select class="midne sel_two" id="t_pay_code">
						        <option value="">全部</option>
						        <c:forEach var="pt" items="${sessionScope.pay_type }">
						     		<option value="${pt.value }">${pt.display }</option>
						        </c:forEach>
						    </select></span>
						     <span>催单状态
							<select class="midne sel_tree" id="t_reminder">
							      <option value="">全部</option>
						     	  <option value="1">已催单</option>
						     	  <option value="0">未催单</option>
							</select></span>
							<span class="mio_four">下单日期(起) <input type="date" placeholder="0000-00-00-0000-00-00" id="t_add_time_s"/></span>
							<span class="mio_four">下单日期(止) <input type="date" placeholder="0000-00-00-0000-00-00" id="t_add_time_e"/></span>
						</div>
						<div class="midentone_top_list fl">
							<span class="mio_four">催单日期(起) <input type="date" placeholder="0000-00-00-0000-00-00" id="t_reminderTime_s"/></span>
							<span class="mio_four">催单日期(止) <input type="date" placeholder="0000-00-00-0000-00-00" id="t_reminderTime_e"/></span>
							<span>退单状态
							<select class="midne sel_tree" id="t_sing_back">
							      <option value="">全部</option>
						     	  <option value="1">申请退单</option>
						     	  <option value="2">退单成功</option>
						     	  <option value="3">退单失败</option>
							</select></span>
							<span class="mio_four">退单日期(起) <input type="date" placeholder="0000-00-00-0000-00-00" id="t_sing_back_time_s"/></span>
							<span class="mio_four">退单日期(止) <input type="date" placeholder="0000-00-00-0000-00-00" id="t_sing_back_time_e"/></span>
							<span class="min_span" style="margin-left:58px"> <img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachCDTHOrder()"></span>
							<span class="min_span"> <img src="${cxt}/static/image/mct/export.png" alt="导出" title="导出" onclick="exportTDOrder()"></span>
						</div><div class="clear"></div>
						</div>
						<%-- <div class="midentone_btncopy fl">
							<span class="min_span"> <img src="${cxt}/static/image/mct/seach.png" alt="搜索" title="搜索" onclick="serachCDTHOrder()"></span>
							<span class="min_span"> <img src="${cxt}/static/image/mct/export.png" alt="导出" title="导出" onclick="exportOrder()"></span>
						</div>  --%>
						<div class="clear"></div>
						</div> 
					</div>
					<div class="mctindent_two">
					     <table >
						       <tr>
							       <th width="12%" class="tr_h">订单编号</th>
							       <th width="6%" class="tr_h">会员ID</th>
							       <th width="6%" class="tr_h">收货人</th>
							       <th width="8%" class="tr_h">商品名称</th>
							       <th width="6%" class="tr_h">订单金额</th>
							       <th width="6%" class="tr_h">下单时间</th>
							       <th width="6%" class="tr_h">催单时间</th>
							       <th width="6%" class="tr_h">催单次数</th>
							       <th width="6%" class="tr_h">发起退单时间</th>
							       <th width="6%" class="tr_h">确认退单时间</th>
							       <th width="6%" class="tr_h">退单状态</th>
							       <th width="7%" class="tr_h">操作</th>
							   </tr>
							   <tbody id="cdth_order"></tbody>
						 </table>
					 </div>
					<div class="mct_l_c_four">
						<div class="mct_page">
							  <ul id="f_index">
							  	 <li>第<span id="f_cdth_order_nowPage">${orders.nowPage }</span>页,共<span id="f_cdth_order_total">${orders.totalSize }</span>条</li>
							  	 <li class="index_page" onclick="serachCDTHOrder()"><a>首页</a></li>
							  	 <li class="top_page" onclick="cdth_last_page()"><a>上一页</a></li>
								 <li class="next_page" onclick="cdth_next_page()"><a>下一页</a></li>
								 <li class="end_page" onclick="cdth_end_page()"><a>末页</a></li>
								 <div class="clear"></div> 
							  </ul> 
					   </div>
				    </div>
					 
				</div>
			   </div>	      
			</u>  
			<!-- <遮罩> -->
		 <div class="zhe_st_list">
		   <div class="st_close fr">X</div>
		   <div class="clear"></div>
		   <p>会员请求申请退单</p>
		   <p>确认退单后,平台将退回会员支付的现金或易物劵,该订单视为放弃,商家不能获得该订单提成</p>
		   <p>为了你的利益,请务必与会员联系</p>
		   <div class="zheli_btn">
		   	 <span id="operate_order_id" style="display: none"></span>
		     <button class="btn_ze_one" id="refuse">拒绝退款</button>
		     <button id="confirm">确认退款</button> 
		   </div> 
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
 </div>
 <div class="mt_zen">
     <p>是否确定已完成对勾选会员的转账?</p>
     <button class="btn_ok_make">确定</button>
     <button class="btn_can_make">取消</button>
 </div>
</aos:body> 
		<script>	 
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

		</script>
<script>
var flag = true;
function exportOrder(){
	if(flag){
		flag=false;
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var add_time_start = $("#f_add_time_s").val();
		var add_time_end = $("#f_add_time_e").val();
		var pay_status = $("#f_pay_status option:selected").val();
		var pay_code = $("#f_pay_code option:selected").val();
		var order_status = $("#f_order_status option:selected").val();
		var shipping_status = $("#f_shipping_status option:selected").val();
		window.location.href='exportOrder.jhtml?consignee='+encodeURI(encodeURI(consignee)) +'&order_sn='+order_sn 
				+ '&goods_name=' + encodeURI(encodeURI(goods_name)) +'&add_time_start=' + add_time_start +"&add_time_end=" + add_time_end +'&pay_status=' + pay_status + '&pay_code=' + pay_code
				+ '&order_status=' + order_status + '&shipping_status=' + shipping_status;
		flag=true;
	}else{
		alert_msg("导出中，请耐心等待。");
		return;
	}
}

function serachOrder(){
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var add_time_start = $("#f_add_time_s").val();
		var add_time_end = $("#f_add_time_e").val();
		var pay_status = $("#f_pay_status option:selected").val();
		var pay_code = $("#f_pay_code option:selected").val();
		var order_status = $("#f_order_status option:selected").val();
		var shipping_status = $("#f_shipping_status option:selected").val();
		$.post("serachOrder.jhtml",{
				"consignee":consignee,
				"order_sn":order_sn,
				"goods_name":goods_name,
				"add_time_start":add_time_start,
				"add_time_end":add_time_end,
				"pay_status":pay_status,
				"pay_code":pay_code,
				"order_status":order_status,
				"shipping_status":shipping_status
			},function(data){
				var tr = "";
				$.each(data.list, function(index, o){
					if(o.pay_code == 'mixed_payment'){//混合支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
						parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
						"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
						"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
						format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
					} else if(o.pay_code == 'mixed_pay_drops'){//混合滴支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
						parseInt(o.cash)+"元/"+parseInt(o.drops)+"滴</td><td>"+o.goods_num + 
						"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
						"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
						format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
					} else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
						"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>"+
						o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
						format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
					}else{//现金支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
						parseInt(o.cash)+"元</td><td>"+o.goods_num + "</td><td>" + o.address + "</td><td>" + 
						o.mobile +"</td><td>" + o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
						format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
					}
				});
				$("#t_f_order").empty();
				$("#t_f_order").append(tr);
				$("#f_order_nowPage").empty();
			$("#f_order_nowPage").html(data.nowPage);
			$("#f_order_total").empty();
			$("#f_order_total").html(data.totalSize);
			}
		);
	}
	//上一页
	function order_last_page(){
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var add_time_start = $("#f_add_time_s").val();
		var add_time_end = $("#f_add_time_e").val();
		var pay_status = $("#f_pay_status option:selected").val();
		var pay_code = $("#f_pay_code option:selected").val();
		var order_status = $("#f_order_status option:selected").val();
		var shipping_status = $("#f_shipping_status option:selected").val();
		//获取当前页码
		var page = $("#f_order_nowPage").text();
		page = parseInt(page);
		if(page <= 1){
			alert_msg("到顶啦，已经是第一页了！")
			return false;
		}
		$.post("serachOrder.jhtml",{
				"page":page-1,
			"consignee":consignee,
			"order_sn":order_sn,
			"goods_name":goods_name,
			"add_time_start":add_time_start,
			"add_time_end":add_time_end,
			"pay_status":pay_status,
			"pay_code":pay_code,
			"order_status":order_status,
			"shipping_status":shipping_status
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
					"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				} else if(o.pay_code == 'mixed_pay_drops'){//混合滴支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元/"+parseInt(o.drops)+"滴</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
					"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				} else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>"+
					o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				}else{//现金支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元</td><td>"+o.goods_num + "</td><td>" + o.address + "</td><td>" + 
					o.mobile +"</td><td>" + o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				}
			});
			$("#t_f_order").empty();
			$("#t_f_order").append(tr);
			$("#f_order_nowPage").empty();
			$("#f_order_nowPage").html(data.nowPage);
			$("#f_order_total").empty();
			$("#f_order_total").html(data.totalSize);
		}
	);
	}
	//下一页
	function order_next_page(){
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var add_time_start = $("#f_add_time_s").val();
		var add_time_end = $("#f_add_time_e").val();
		var pay_status = $("#f_pay_status option:selected").val();
		var pay_code = $("#f_pay_code option:selected").val();
		var order_status = $("#f_order_status option:selected").val();
		var shipping_status = $("#f_shipping_status option:selected").val();
		//获取当前页码
		var page = $("#f_order_nowPage").text();
		page = parseInt(page);
		var total = $("#f_order_total").text();
		total = parseInt(total);
	    if(page >= getTotalPage(total)){
	    	alert_msg("到底啦，已经是最后一页了！")
			return false;
		}
	
		$.post("serachOrder.jhtml",{
			"page":page+1,
			"consignee":consignee,
			"order_sn":order_sn,
			"goods_name":goods_name,
			"add_time_start":add_time_start,
			"add_time_end":add_time_end,
			"pay_status":pay_status,
			"pay_code":pay_code,
			"order_status":order_status,
			"shipping_status":shipping_status
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
					"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				} else if(o.pay_code == 'mixed_pay_drops'){//混合滴支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元/"+parseInt(o.drops)+"滴</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
					"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				} else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>"+
					o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				}else{//现金支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元</td><td>"+o.goods_num + "</td><td>" + o.address + "</td><td>" + 
					o.mobile +"</td><td>" + o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				}
			});
			$("#t_f_order").empty();
			$("#t_f_order").append(tr);
			$("#f_order_nowPage").empty();
			$("#f_order_nowPage").html(data.nowPage);
			$("#f_order_total").empty();
			$("#f_order_total").html(data.totalSize);
		}
	);
	}
	//最后一页
	function order_end_page(){
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var add_time_start = $("#f_add_time_s").val();
		var add_time_end = $("#f_add_time_e").val();
		var pay_status = $("#f_pay_status option:selected").val();
		var pay_code = $("#f_pay_code option:selected").val();
		var order_status = $("#f_order_status option:selected").val();
		var shipping_status = $("#f_shipping_status option:selected").val();
		//获取当前页码
		var page = $("#f_order_nowPage").text();
		page = parseInt(page);
		var total = $("#f_order_total").text();
		total = parseInt(total);
	    if(page >= getTotalPage(total)){
	    	alert_msg("到底啦，已经是最后一页了！")
			return false;
		}
		$.post("serachOrder.jhtml",{
				"page":((total / 10) + 1),
			"consignee":consignee,
			"order_sn":order_sn,
			"goods_name":goods_name,
			"add_time_start":add_time_start,
			"add_time_end":add_time_end,
			"pay_status":pay_status,
			"pay_code":pay_code,
			"order_status":order_status,
			"shipping_status":shipping_status
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
					"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				} else if(o.pay_code == 'mixed_pay_drops'){//混合滴支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元/"+parseInt(o.drops)+"滴</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>" +o.pay_status+
					"</td><td>"+o.shipping_status+"</td><td>"+o.add_time+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				} else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+parseInt(o.barter_coupons)+"券</td><td>"+o.goods_num + 
					"</td><td>" + o.address + "</td><td>" + o.mobile + "</td><td>"+
					o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				}else{//现金支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
					parseInt(o.cash)+"元</td><td>"+o.goods_num + "</td><td>" + o.address + "</td><td>" + 
					o.mobile +"</td><td>" + o.pay_status+"</td><td>"+o.shipping_status+"</td><td>"+format(o.add_time)+"</td><td>"+
					format(o.shipping_time)+"</td><td>"+format(o.confirm_time)+"</td><td>"+format(o.success_time)+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='详情' title='详情' onclick='order_detial(this)' style='height:30px;'></td></tr>";
				}
			});
			$("#t_f_order").empty();
			$("#t_f_order").append(tr);
			$("#f_order_nowPage").empty();
			$("#f_order_nowPage").html(data.nowPage);
			$("#f_order_total").empty();
			$("#f_order_total").html(data.totalSize);
		}
	);
	}
	
	//订单详情
	function order_detial(v){
		var tr = v.parentNode.parentNode;
		var order_id = $(tr).find(".order_id").text();
		$.post("order_detail.jhtml",{
			"order_id":order_id
		},function(data){
			$(".dis_show").hide();
			//清除原有的数据
			$("#order_basc_info").empty();
			$("#order_rece_info").empty();
			$(".order_goods_info").remove();
			$("#order_cost_info").empty();
			$("#order_do_info").empty();
			//将新的数据存入
			var td1 ="<td style='display:none' class='order_id'>"+data.order_id+"</td><td>"+data.user_id+"</td><td>"+data.order_sn+"</td><td>"+
			data.user_name+"</td><td>"+data.mobile+"</td><td>"+data.cash + "元/"+data.barter_coupons+"券</td><td>"+
			data.order_status+"</td><td>"+format(data.add_time)+"</td><td>"+format(data.pay_time)+"</td><td>"
			+data.pay_name+"</td>";
			$("#order_basc_info").append(td1);
			
			var td2 =  "<td>"+data.consignee+"</td><td>"+data.mobile+"</td><td>"+
			data.address+"</td>";
			$("#order_rece_info").append(td2);
			
			var td3 = "";
			//小计总和
			//var xj = 0;
			$.each(data.zjcOrderGoodsPO, function(index, g){
				if(data.pay_code == 'mixed_payment'){//混合支付
					td3 += "<tr class='tbind_tree order_goods_info'><td>"+g.goods_name+"</td><td>"+
					g.goods_num+"</td><td>"+g.cost_price+"元/"+g.market_price+"券</td><td>"+parseFloat((g.goods_num * g.cost_price).toFixed(2))+"元/"+parseFloat((g.goods_num * g.market_price).toFixed(2))+
					"券</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
				}else if(data.pay_code == 'rate' || data.pay_code == 'equal'){//券支付
					td3 += "<tr class='tbind_tree order_goods_info'><td>"+g.goods_name+"</td><td>"+
					g.goods_num+"</td><td>"+g.market_price+"券</td><td>"+parseFloat((g.goods_num * g.market_price).toFixed(2))+
					"券</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
				}else{//现金支付
					td3 += "<tr class='tbind_tree order_goods_info'><td>"+g.goods_name+"</td><td>"+
					g.goods_num+"</td><td>"+g.cost_price+"元</td><td>"+parseFloat((g.goods_num * g.cost_price).toFixed(2))+
					"元</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
				}
			});
			$("#t_order_goods").append(td3);
			var list = data.zjcOrderActionPOs;
			var tr5 = "";
			for(var i = 0; i < list.length; i++){
				if(list[i].action_user == "" || list[i].action_user == null){
					tr5 +="";
				}else{
					tr5 +="<tr class='tbind_tree'><td>"+list[i].action_user+"</td><td>"+format(list[i].log_time)+"</td><td>"+
					list[i].order_status+"</td><td>"+list[i].pay_status+"</td><td>"+list[i].shipping_status+
					"</td><td>"+list[i].status_desc+"</td><td>"+list[i].action_note+"</td></tr>"
				}
			}
			$("#order_do_info").append(tr5);
			
			if(data.order_status == "已确认" && data.shipping_status == "已发货"){
				$("#delivery").attr("disabled", true);
				$("#invoice_no").val(data.tracking_no);
				$("#invoice_no").attr("disabled",true);
			}
			
			$("#hide_pay_status").val(data.pay_status);
			$("#hide_id").text(order_id);
			$(".dis_hide").show();
		});
	}
	
	//订单确认
	function invo_comfirm(){
		var order_id = $("#hide_id").text();
		var note = $("#add_remark").val();
		if($("#hide_pay_status").val() == '未支付'){
			alert_msg("该订单未支付，不能确认订单！");
		}else{
			$.post("invo_comfirm.jhtml",{
				"order_id":order_id,
				"note":note
			},function(data){
				if(data.code === 1){
					$(".dis_hide").hide();
					$.post("order_detail.jhtml",{
						"order_id":order_id
					},function(data){
						$("#t_in_basic").empty();
						var tr = "<td style='display:none' class='order_id'>"+data.order_id+"</td><td width='307'>订单号："+data.order_sn+
						"</td><td width='343'>下单时间："+format(data.add_time)+"</td><td width='172'>物流费用："+data.shipping_price+"</td>";
						$("#t_in_basic").append(tr);
						
						$("#t_in_rece").empty();
						var tr1 = "<td width='167'>收货人："+data.consignee+
						"</td><td width='238'>联系电话："+data.mobile+"</td><td width='417'>收货地址："+data.address+"</td>";
						$("#t_in_rece").append(tr1);
						
						$(".t_in_goods").remove();
						var tr2="";
						$.each(data.zjcOrderGoodsPO, function(index, g){
							if(data.pay_code == 'mixed_payment'){//混合支付
								tr2 += "<tr class='tbind_tree order_goods_info'><td>"+g.goods_name+"</td><td>"+
								g.goods_num+"</td><td>"+g.cost_price+"元/"+g.market_price+"券</td><td>"+parseFloat((g.goods_num * g.cost_price).toFixed(2))+
								"元/"+parseFloat((g.goods_num * g.market_price).toFixed(2))+"券</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
							}else if(data.pay_code == 'rate' || data.pay_code == 'equal'){//券支付
								tr2 += "<tr class='tbind_tree order_goods_info'><td>"+g.goods_name+"</td><td>"+
								g.goods_num+"</td><td>"+g.market_price+"券</td><td>"+parseFloat((g.goods_num * g.market_price).toFixed(2))+
								"券</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
							}else{//现金支付
								tr2 += "<tr class='tbind_tree order_goods_info'><td>"+g.goods_name+"</td><td>"+
								g.goods_num+"</td><td>"+g.cost_price+"(元)</td><td>"+parseFloat((g.goods_num * g.cost_price).toFixed(2))+
								"元</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
							}
						});
						$("#t_in_goods").append(tr2);
					});
					$(".dis_hide_write").show();
				}
			});
		}
		
	}
	
	//计算总页数
	function getTotalPage(total){
		if((total % 10) > 0){
			return Math.ceil(total / 10);
		}else{
			return total / 10;
		}
	}
	
	//确认发货
	function delivery(){
		var order_id = $("#hide_id").text();
		var note = $("#remark").val();
		var invoice_no = $("#invoice_no").val();
		if(invoice_no == ""){
			alert_msg("运单号必填！")
			return;
		}
		$("#delivery").attr("disabled", true);
		$.post("delivery.jhtml",{
			"order_id":order_id,
				"note":note,
				"invoice_no":invoice_no
		},function(data){
			alert_msg(data.msg);
			if(data.code === 1){
				$(".dis_hide_write").hide();
				$(".dis_show").show();
				serachOrder();
				serachInvo();
			}else{
				window.reload();
			}
			$("#delivery").attr("disabled", false);
		});
	}
	
	//搜索发货单
	function serachInvo(){
		//获取搜索条件
		var consignee = $("#f_invo_consignee").val();
		var order_sn = $("#f_invo_order_sn").val();
		var goods_name = $("#f_invo_goods_name").val();
		var pay_time = $("#ff_add_time").val();
		$.post("serachInvo.jhtml",{
				"consignee":consignee,
				"order_sn":order_sn,
				"goods_name":goods_name,
				"pay_time":pay_time
			},function(data){
				var tr = "";
				$.each(data.list, function(index, o){
					if(o.pay_code == 'mixed_payment'){//混合支付
						tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+
						"</td><td>"+format(o.shipping_time)+"</td><td>"+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元/"+o.barter_coupons+"券</td></tr>";
					}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
						tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"
						+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.barter_coupons+"券</td></tr>";
					}else{//现金支付
						tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"+
						o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元</td></tr>";
					}
				});
				$("#t_invo").empty();
				$("#t_invo").append(tr);
				$("#invo_nowPage").empty();
				$("#invo_nowPage").html(data.nowPage);
				$("#invo_total").empty();
				$("#invo_total").html(data.totalSize);
			}
		);
	}
	
	//上一页
	function invo_last_page(){
		//获取搜索条件
		var consignee = $("#f_invo_consignee").val();
		var order_sn = $("#f_invo_order_sn").val();
		var goods_name = $("#f_invo_goods_name").val();
		var pay_time = $("#ff_add_time").val();
		//获取当前页码
		var page = $("#invo_nowPage").text();
		page = parseInt(page);
		if(page <= 1){
			alert_msg("到顶啦，已经是第一页了！")
			return false;
		}
		$.post("serachInvo.jhtml",{
			"page":page-1,
			"consignee":consignee,
			"order_sn":order_sn,
			"goods_name":goods_name,
			"pay_time":pay_time
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+
					"</td><td>"+format(o.shipping_time)+"</td><td>"+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元/"+o.barter_coupons+"券</td></tr>";
				}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"
					+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.barter_coupons+"券</td></tr>";
				}else{//现金支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"+
					o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元</td></tr>";
				}
			});
			$("#t_invo").empty();
			$("#t_invo").append(tr);
			$("#invo_nowPage").empty();
			$("#invo_nowPage").html(data.nowPage);
			$("#invo_total").empty();
			$("#invo_total").html(data.totalSize);
		}
	);
	}
	
	//下一页
	function invo_next_page(){
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var pay_time = $("#ff_add_time").val();
		//获取当前页码
		var page = $("#invo_nowPage").text();
		page = parseInt(page);
		var total = $("#invo_total").text();
		total = parseInt(total);
	    if(page >= getTotalPage(total)){
	    	alert_msg("到底啦，已经是最后一页了！")
			return false;
		}
		$.post("serachInvo.jhtml",{
			"page":page+1,
			"consignee":consignee,
			"order_sn":order_sn,
			"goods_name":goods_name,
			"pay_time":pay_time
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+
					"</td><td>"+format(o.shipping_time)+"</td><td>"+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元/"+o.barter_coupons+"券</td></tr>";
				}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"
					+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.barter_coupons+"券</td></tr>";
				}else{//现金支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"+
					o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元</td></tr>";
				}
			});
			$("#t_invo").empty();
			$("#t_invo").append(tr);
			$("#invo_nowPage").empty();
			$("#invo_nowPage").html(data.nowPage);
			$("#invo_total").empty();
			$("#invo_total").html(data.totalSize);
		}
	);
	}
	//最后一页
	function invo_end_page(){
		//获取搜索条件
		var consignee = $("#f_consignee").val();
		var order_sn = $("#f_order_sn").val();
		var goods_name = $("#f_goods_name").val();
		var pay_time = $("#ff_add_time").val();
		//获取当前页码
		var page = $("#invo_nowPage").text();
		page = parseInt(page);
		var total = $("#invo_total").text();
		total = parseInt(total);
	    if(page >= getTotalPage(total)){
	    	alert_msg("到底啦，已经是最后一页了！")
			return false;
		}
		$.post("serachInvo.jhtml",{
			"page":((total / 10) + 1),
			"consignee":consignee,
			"order_sn":order_sn,
			"goods_name":goods_name,
			"pay_time":pay_time
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+
					"</td><td>"+format(o.shipping_time)+"</td><td>"+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元/"+o.barter_coupons+"券</td></tr>";
				}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"
					+o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.barter_coupons+"券</td></tr>";
				}else{//现金支付
					tr +="<tr onclick='show_invo(this)'><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+format(o.pay_time)+"</td><td>"+format(o.shipping_time)+"</td><td>"+
					o.tracking_no+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+o.cash+"元</td></tr>";
				}
			});
			$("#t_invo").empty();
			$("#t_invo").append(tr);
			$("#invo_nowPage").empty();
			$("#invo_nowPage").html(data.nowPage);
			$("#invo_total").empty();
			$("#invo_total").html(data.totalSize);
		}
	);
	}
	
	$(function() {
		var liList = $(".nav_li li");
		for (var i = 0; i < liList.length; i++) {
			if (liList[i].innerText == '订单') {
				liList.eq(i).addClass("mct_click");
			}
		}
	});
	//取消确认订单
	$("#cancle1").click(function(){
		$(".dis_hide").hide();
		$(".dis_show").show();
	}); 
	//返回发货单列表
	$("#cancle").click(function(){
		$(".bill_hide").hide();
		$(".bill_show").show();
	});
	
	$("#cancle3").click(function(){
		history.go(0); 
		//$(".dis_show").show();
	});  
	function show_print(v){ 
			var d = v.parentNode.parentNode;
			var order_id = $(d).find(".order_id").text();
			$.post("order_detail.jhtml",{
				"order_id":order_id
			},function(data){
				$("#p_order_base").empty();
				
				var tr = "<tr class='prints_tree'><td>"+data.user_id+"</td><td>"+data.order_sn+
				"</td><td>"+data.user_name+"</td><td>"+data.mobile+"</td><td>"+data.integral+
				"</td><td>"+data.order_status+"</td><td>"+format(data.add_time)+"</td><td>"+
				format(data.pay_time)+"</td><td>"+data.pay_name+"</td></tr>";
				$("#p_order_base").append(tr);
				
				$("#p_order_rec").empty();
				tr = "<tr class='prints_tree'><td width='112'>"+data.consignee+"</td><td width='160'>"+
				data.mobile+"</td><td width='317'>"+data.address+"</td></tr>";
				$("#p_order_rec").append(tr);
				
				$("#p_order_goods").empty();
				tr = "";
				var xj = 0;
				$.each(data.zjcOrderGoodsPO, function(index, g){
					if(data.pay_code == 'mixed_payment'){//混合支付
						tr += "<tr class='prints_tree'><td>"+g.goods_name+"</td><td>"+
						g.goods_num+"</td><td>"+g.cost_price+"元/"+g.market_price+"券</td><td>"+parseFloat((g.goods_num * g.cost_price).toFixed(2))+
						"元/"+parseFloat((g.goods_num * g.market_price).toFixed(2))+"券</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
					}else if(data.pay_code == 'rate' || data.pay_code == 'equal'){//易物券支付
						tr += "<tr class='prints_tree'><td>"+g.goods_name+"</td><td>"+
						g.goods_num+"</td><td>"+g.market_price+"券</td><td>"+parseFloat((g.goods_num * g.market_price).toFixed(2))+
						"券</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
					}else{//现金支付
						tr += "<tr class='prints_tree'><td>"+g.goods_name+"</td><td>"+
						g.goods_num+"</td><td>"+g.cost_price+"元</td><td>"+parseFloat((g.goods_num * g.cost_price).toFixed(2))+
						"元</td><td>"+data.user_note+" " +data.admin_note+"</td></tr>";
					}
				});
				$("#p_order_goods").append(tr);
				
				$("#p_order_cost").empty();
				tr = "<tr class='prints_tree'><td>"+xj+"现金"+data.cash+"(元),易物券"+data.barter_coupons+"(券)</td><td>"+data.shipping_price+"（元）</td></tr>";
				$("#p_order_cost").append(tr);
				$(".print_ok,.print_make").show(); 
			}); 
	}

	function can_tbn(){
		$(".print_ok,.print_make").hide(); 
	}
	function show_print_o(v){
		$("#print_order_o").empty();
		var d = v.parentNode.parentNode;
		var order_id = $(d).find(".order_id").text();
		$.post("order_invo.jhtml",{
				"order_id":order_id
		}, function(data){
			var tr ="<tr><td width='365'>下单时间："+format(data.add_time)+"</td><td width='380'>发货时间："+format(data.shipping_time)+
			"</td></tr><tr><td width='365'>订单号:"+data.order_sn+"</td><td width='380'>运单号："+data.tracking_no+
			"</td></tr><tr><td width='365'>寄件人："+data.user_id+"</td><td width='380'>收件人："+data.consignee+"</td></tr><tr><td width='365'>寄件单位："+
			data.store_name+"</td><td width='380'>收件单位："+data.address+"</td></tr><tr><td width='365'>寄件地址："+data.address_detail+"</td><td width='380'>收件地址："+
			data.address+"</td></tr><tr><td width='365'>寄件电话："+data.j_mobile+"</td><td width='380'>收件电话："+data.s_mobile+"</td></tr>";
				
			$("#print_order_o").append(tr);
			$(".print_okems,.print_okems_make").show();
		});
	}
	
	function cancle_prints(){
		$(".print_okems,.print_okems_make").hide();
	}
	
	function show_invo(v){
		var order_id =$(v).find(".order_id").text();
		$.post("order_detail.jhtml",{
			"order_id":order_id
		}, function(data){
			$("#b_hide_base").empty();
			var tr = "<tr class='tbind_tree'><td>"+data.user_id+"</td><td>"+data.order_sn+"</td><td>"+data.user_name+"</td><td>"+
			data.mobile+"</td><td>"+data.cash+ "元/" +data.barter_coupons+"券"+"</td><td>"+data.order_status+"</td><td>"+format(data.add_time)+
			"</td><td>"+format(data.pay_time)+"</td><td>"+data.pay_name+"</td><tr>";
			$("#b_hide_base").append(tr);
			
			$("#b_hide_rec").empty();
			tr = "<tr class='tbind_tree'><td>"+data.consignee+"</td><td>"+data.mobile+"</td><td>"+data.address+"</td><td><a style='color:blue' onClick='check_wliu(this)'>"+data.tracking_no+"</a></td></tr>";
			$("#b_hide_rec").append(tr);
			
			$("#b_hide_goods").empty();
			var xjm = 0, xjg = 0;
			$.each(data.zjcOrderGoodsPO, function(index, g){
				tr = "<tr class='tbind_tree'><td>"+g.goods_name+"</td><td>"+g.goods_num+"</td><td>"+g.cost_price+"元/"+g.market_price+"券</td><td>"+
				parseFloat((g.goods_num * g.cost_price).toFixed(2))+"元/"+(g.goods_num * g.market_price)+"券</td><td>"+data.user_note+"</td></tr>";
				xjm += parseFloat((g.goods_num * g.cost_price).toFixed(2));
				xjg += parseFloat((g.goods_num * g.market_price).toFixed(2));
			});
			$("#b_hide_goods").append(tr);
				
			$("#b_hide_cost").empty();
			tr = "<tr class='tbind_tree'><td>"+xjm+"元"+xjg+"券</td><td>"+data.shipping_price+"元</td></tr>";
			$("#b_hide_cost").append(tr);
			$(".bill_show").hide();
			$(".bill_hide").show();
		});
	}
	
	function check_wliu(v){
		window.open("https://m.kuaidi100.com/index_all.html?postid="+$(v).text()+"#result");
	}
	
	$(function(){//页面加载完成加载数据
		serachOrder();
		serachInvo();
		serachCDTHOrder();
	})
	
	$.post("getTransferOrderPage.jhtml", {"page" : '1'},function(data) {
			var temeplte = Handlebars.compile($("#datalist").html());
			$("#data_table").html(temeplte(data.list));
			$("#data_nowPage").html(data.nowPage);
			$("#data_total").html(data.totalSize);
			$("#data_totalPage").html(data.totalPage);
		});
	//查询账单明细
	function check_order_page(v){
		var Transfer_user_id =$("#Transfer_user_id").val();
		var Transfer_real_name =$("#Transfer_real_name").val();
		var Transfer_order_sn =$("#Transfer_order_sn").val();
		var Transfer_pay_code =$("#Transfer_pay_code").val();
		var Transfer_s_add_time =$("#Transfer_s_add_time").val();
		var Transfer_e_add_time =$("#Transfer_e_add_time").val();
		var transfer_type =$("#transfer_type").val();
		var data_nowPage =$("#data_nowPage").text();
		var data_totalPage =$("#data_totalPage").text();
		var page=1;
		if(v==1){
			page==1;
		}else if(v==2){
			page=parseInt(data_nowPage)+1;
		}else if(v==3){
			page=data_nowPage-1;
		}else{
			page=data_totalPage;
		}
		$.post("getTransferOrderPage.jhtml",
				{"page" : page,
			     "user_id":Transfer_user_id,
			     "real_name":Transfer_real_name,
			     "order_sn":Transfer_order_sn,
			     "pay_code":Transfer_pay_code,
			     "s_add_time":Transfer_s_add_time,
			     "e_add_time":Transfer_e_add_time,
			     "transfer_type":transfer_type}
				,function(data) {
			var temeplte = Handlebars.compile($("#datalist").html());
			$("#data_table").html(temeplte(data.list));
			$("#data_nowPage").html(data.nowPage);
			$("#data_total").html(data.totalSize);
			$("#data_totalPage").html(data.totalPage);
			//console.log(data);
		});
	}
	
	
	//查询账单明细
	function check_div_page(){
			var Transfer_user_id =$("#Transfer_user_id").val();
			var checked_array =new Array();
			for(var i = 0;i<$(".check_value").length;i++){
				if($(".check_value")[i].checked){
					checked_array.push($($(".check_value")[i]).val());
				}
			}
			var page=1;
			var json = JSON.stringify(checked_array);
			var Transfer_user_id =$("#Transfer_user_id").val();
			var Transfer_real_name =$("#Transfer_real_name").val();
			var Transfer_order_sn =$("#Transfer_order_sn").val();
			var Transfer_pay_code =$("#Transfer_pay_code").val();
			var Transfer_s_add_time =$("#Transfer_s_add_time").val();
			var Transfer_e_add_time =$("#Transfer_e_add_time").val();
		$(".mt_zen").show();
		//否
		$(".btn_can_make").click(function(){
			$(".mt_zen").hide(); 
		})
		//是
		$(".btn_ok_make").click(function(){
			$(".mt_zen").hide(); 
			$.post("updataTransferOrderPage.jhtml",
					{"checked_array" : json,
				     "page":page,
				     "user_id":Transfer_user_id,
				     "real_name":Transfer_real_name,
				     "order_sn":Transfer_order_sn,
				     "pay_code":Transfer_pay_code,
				     "s_add_time":Transfer_s_add_time,
				     "e_add_time":Transfer_e_add_time}
					,function(data) {
				var temeplte = Handlebars.compile($("#datalist").html());
				$("#data_table").html(temeplte(data.list));
				$("#data_nowPage").html(data.nowPage);
				$("#data_total").html(data.totalSize);
				$("#data_totalPage").html(data.totalPage);
				console.log(data);
			}); 
		})
		
		
	}
	
	function checkexportOrder(){
		if(flag){
			flag=false;
			//获取搜索条件
			var Transfer_user_id =$("#Transfer_user_id").val();
			var Transfer_real_name =$("#Transfer_real_name").val();
			var Transfer_order_sn =$("#Transfer_order_sn").val();
			var Transfer_pay_code =$("#Transfer_pay_code").val();
			var Transfer_s_add_time =$("#Transfer_s_add_time").val();
			var Transfer_e_add_time =$("#Transfer_e_add_time").val();
			window.location.href='TransferExportOrder.jhtml?user_id='+Transfer_user_id +'&order_sn='+Transfer_order_sn 
					+ '&real_name=' + encodeURI(encodeURI(Transfer_real_name)) +'&e_add_time=' + Transfer_e_add_time +"&s_add_time=" + Transfer_s_add_time 
					+ '&pay_code=' + Transfer_pay_code;
			flag=true;
		}else{
			alert_msg("导出中，请耐心等待。");
			return;
		}
	}
	
	//催单退货订单
	
	function serachCDTHOrder(){
		//获取搜索条件
		var user_id = $("#t_user_id").val();
		var consignee = $("#t_consignee").val();
		var order_sn = $("#t_order_sn").val();
		var pay_code = $("#t_pay_code option:selected").val();
		var reminder = $("#t_reminder option:selected").val();
		var add_time_start = $("#t_add_time_s").val();
		var add_time_end = $("#t_add_time_e").val();
		var reminderTime_start = $("#t_reminderTime_s").val();
		var reminderTime_end = $("#t_reminderTime_e").val();
		var sing_back = $("#t_sing_back option:selected").val();
		var sing_back_time_start = $("#t_sing_back_time_s").val();
		var sing_back_time_end = $("#t_sing_back_time_e").val();
		$.post("serachCDTHOrder.jhtml",{
				"user_id":user_id,
				"consignee":consignee,
				"order_sn":order_sn,
				"pay_code":pay_code,
				"reminder":reminder,
				"add_time_start":add_time_start,
				"add_time_end":add_time_end,
				"reminderTime_start":reminderTime_start,
				"reminderTime_end":reminderTime_end,
				"sing_back":sing_back,
				"sing_back_time_start":sing_back_time_start,
				"sing_back_time_end":sing_back_time_end
			},function(data){
				var tr = "";
				$.each(data.list, function(index, o){
					console.log("pay_code-barter_coupons-cash:" + o.pay_code+","+ o.cash +","+o.barter_coupons);
					if(o.pay_code == 'mixed_payment'){//混合支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
						+ parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
						+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
					}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
						+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
						+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
					}else{//现金支付
						tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
						o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
						+ parseInt(o.cash)+"元</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
						+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
						"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
					}
				});
				$("#cdth_order").empty();
				$("#cdth_order").append(tr);
				$("#f_cdth_order_nowPage").empty();
				$("#f_cdth_order_nowPage").html(data.nowPage);
				$("#f_cdth_order_total").empty();
				$("#f_cdth_order_total").html(data.totalSize);
			}
		);
	}
	//催单退单--上一页
	function cdth_last_page(){
		//获取搜索条件
		var user_id = $("#t_user_id").val();
		var consignee = $("#t_consignee").val();
		var order_sn = $("#t_order_sn").val();
		var pay_code = $("#t_pay_code option:selected").val();
		var reminder = $("#t_reminder option:selected").val();
		var add_time_start = $("#t_add_time_s").val();
		var add_time_end = $("#t_add_time_e").val();
		var reminderTime_start = $("#t_reminderTime_s").val();
		var reminderTime_end = $("#t_reminderTime_e").val();
		var sing_back = $("#t_sing_back option:selected").val();
		var sing_back_time_start = $("#t_sing_back_time_s").val();
		var sing_back_time_end = $("#t_sing_back_time_e").val();
		//获取当前页码
		var page = $("#f_cdth_order_nowPage").text();
		page = parseInt(page);
		if(page <= 1){
			alert_msg("到顶啦，已经是第一页了！")
			return false;
		}
		$.post("serachCDTHOrder.jhtml",{
			"page":page-1,
			"user_id":user_id,
			"consignee":consignee,
			"order_sn":order_sn,
			"pay_code":pay_code,
			"reminder":reminder,
			"add_time_start":add_time_start,
			"add_time_end":add_time_end,
			"reminderTime_start":reminderTime_start,
			"reminderTime_end":reminderTime_end,
			"sing_back":sing_back,
			"sing_back_time_start":sing_back_time_start,
			"sing_back_time_end":sing_back_time_end
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+ parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}else{//现金支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+ parseInt(o.cash)+"元</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}
			});
			$("#cdth_order").empty();
			$("#cdth_order").append(tr);
			$("#f_cdth_order_nowPage").empty();
			$("#f_cdth_order_nowPage").html(data.nowPage);
			$("#f_cdth_order_total").empty();
			$("#f_cdth_order_total").html(data.totalSize);
		}
	);
	}
	//催单退单--下一页
	function cdth_next_page(){
		//获取搜索条件
		var user_id = $("#t_user_id").val();
		var consignee = $("#t_consignee").val();
		var order_sn = $("#t_order_sn").val();
		var pay_code = $("#t_pay_code option:selected").val();
		var reminder = $("#t_reminder option:selected").val();
		var add_time_start = $("#t_add_time_s").val();
		var add_time_end = $("#t_add_time_e").val();
		var reminderTime_start = $("#t_reminderTime_s").val();
		var reminderTime_end = $("#t_reminderTime_e").val();
		var sing_back = $("#t_sing_back option:selected").val();
		var sing_back_time_start = $("#t_sing_back_time_s").val();
		var sing_back_time_end = $("#t_sing_back_time_e").val();
		//获取当前页码
		var page = $("#f_cdth_order_nowPage").text();
		page = parseInt(page);
		var total = $("#f_cdth_order_total").text();
		total = parseInt(total);
	    if(page >= getTotalPage(total)){
	    	alert_msg("到底啦，已经是最后一页了！")
			return false;
		}
		$.post("serachCDTHOrder.jhtml",{
			"page":page+1,
			"user_id":user_id,
			"consignee":consignee,
			"order_sn":order_sn,
			"pay_code":pay_code,
			"reminder":reminder,
			"add_time_start":add_time_start,
			"add_time_end":add_time_end,
			"reminderTime_start":reminderTime_start,
			"reminderTime_end":reminderTime_end,
			"sing_back":sing_back,
			"sing_back_time_start":sing_back_time_start,
			"sing_back_time_end":sing_back_time_end
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+ parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}else{//现金支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+ parseInt(o.cash)+"元</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}
			});
			$("#cdth_order").empty();
			$("#cdth_order").append(tr);
			$("#f_cdth_order_nowPage").empty();
			$("#f_cdth_order_nowPage").html(data.nowPage);
			$("#f_cdth_order_total").empty();
			$("#f_cdth_order_total").html(data.totalSize);
		}
	);
	}
	//催单退单--最后一页
	function cdth_end_page(){
		//获取搜索条件
		var user_id = $("#t_user_id").val();
		var consignee = $("#t_consignee").val();
		var order_sn = $("#t_order_sn").val();
		var pay_code = $("#t_pay_code option:selected").val();
		var reminder = $("#t_reminder option:selected").val();
		var add_time_start = $("#t_add_time_s").val();
		var add_time_end = $("#t_add_time_e").val();
		var reminderTime_start = $("#t_reminderTime_s").val();
		var reminderTime_end = $("#t_reminderTime_e").val();
		var sing_back = $("#t_sing_back option:selected").val();
		var sing_back_time_start = $("#t_sing_back_time_s").val();
		var sing_back_time_end = $("#t_sing_back_time_e").val();
		//获取当前页码
		var page = $("#f_cdth_order_nowPage").text();
		page = parseInt(page);
		var total = $("#f_cdth_order_total").text();
		total = parseInt(total);
	    if(page >= getTotalPage(total)){
	    	alert_msg("到底啦，已经是最后一页了！")
			return false;
		}
	    page = getTotalPage(total);
		$.post("serachCDTHOrder.jhtml",{
			"page":page,
			"user_id":user_id,
			"consignee":consignee,
			"order_sn":order_sn,
			"pay_code":pay_code,
			"reminder":reminder,
			"add_time_start":add_time_start,
			"add_time_end":add_time_end,
			"reminderTime_start":reminderTime_start,
			"reminderTime_end":reminderTime_end,
			"sing_back":sing_back,
			"sing_back_time_start":sing_back_time_start,
			"sing_back_time_end":sing_back_time_end
		},function(data){
			var tr = "";
			$.each(data.list, function(index, o){
				if(o.pay_code == 'mixed_payment'){//混合支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+ parseInt(o.cash)+"元/"+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+parseInt(o.barter_coupons)+"券</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}else{//现金支付
					tr +="<tr><td style='display:none' class='order_id'>"+o.order_id+"</td><td>"+o.order_sn+"</td><td>"+
					o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"
					+ parseInt(o.cash)+"元</td><td>"+o.add_time+"</td><td>"+format(o.reminderTime)+"</td><td>"
					+ o.reminder+"</td><td>"+format(o.single_back_time)+"</td><td>"+format(o.confirm_single_back_time)+"</td><td>"+o.single_back+"</td><td>"+
					"<img src='${cxt}/static/image/mct/mct_add.png' alt='退单' title='退单' onclick='single_back(this)' style='height:30px;'></td></tr>";
				}
			});
			$("#cdth_order").empty();
			$("#cdth_order").append(tr);
			$("#f_cdth_order_nowPage").empty();
			$("#f_cdth_order_nowPage").html(data.nowPage);
			$("#f_cdth_order_total").empty();
			$("#f_cdth_order_total").html(data.totalSize);
		}
	);
	}
	//点击退单按钮事件
	function single_back(v){
		var tr = v.parentNode.parentNode;
		var order_id = $(tr).find(".order_id").text();
		$("#operate_order_id").val(order_id);
		console.log($("#operate_order_id").val())
		$(".zhe_st_list").show();
	}
	//关闭
	 $(".st_close").click(function(){
		$(".zhe_st_list").hide();					 
	 })
	
	//残忍拒绝
	$("#refuse").click(function(){
		$("#refuse").attr("disabled", true);
		$.post("refuseBackOrder.jhtml",{
			"order_id":$("#operate_order_id").val()
		},function(data){
			alert_msg(data.msg);
			if(data.code === 1){
				$(".zhe_st_list").hide();
				serachCDTHOrder();
			}else{
				window.reload();
			}
			$("#refuse").attr("disabled", false);
		});
	});
	
	//确认退单
	$("#confirm").click(function(){
		$("#confirm").attr("disabled", true);
		$.post("confirmBackOrder.jhtml",{
			"order_id":$("#operate_order_id").val()
		},function(data){
			alert_msg(data.msg);
			if(data.code === 1){
				$(".zhe_st_list").hide();
				serachCDTHOrder();
			}else{
				window.reload();
			}
			$("#confirm").attr("disabled", false);
		});
	});
	
	//导出退单数据
	function exportTDOrder(){
		if(flag){
			flag=false;
			//获取搜索条件
			var user_id = $("#t_user_id").val();
			var consignee = $("#t_consignee").val();
			var order_sn = $("#t_order_sn").val();
			var pay_code = $("#t_pay_code option:selected").val();
			var reminder = $("#t_reminder option:selected").val();
			var add_time_start = $("#t_add_time_s").val();
			var add_time_end = $("#t_add_time_e").val();
			var reminderTime_start = $("#t_reminderTime_s").val();
			var reminderTime_end = $("#t_reminderTime_e").val();
			var sing_back = $("#t_sing_back option:selected").val();
			var sing_back_time_start = $("#t_sing_back_time_s").val();
			var sing_back_time_end = $("#t_sing_back_time_e").val();
			window.location.href='exportTDOrder.jhtml?consignee='+encodeURI(encodeURI(consignee)) +'&order_sn='+order_sn.trim() 
					+ '&user_id=' + user_id +'&add_time_start=' + add_time_start +"&add_time_end=" + add_time_end +'&reminder=' + reminder + '&pay_code=' + pay_code
					+ '&reminderTime_start=' + reminderTime_start + '&reminderTime_end=' + reminderTime_end+ '&sing_back=' + sing_back
					+ '&sing_back_time_start=' + sing_back_time_start + '&sing_back_time_end=' + sing_back_time_end;
			flag=true;
		}else{
			alert_msg("导出中，请耐心等待。");
			return false;
		}
	}
	
	
</script> 								   
</aos:html>
