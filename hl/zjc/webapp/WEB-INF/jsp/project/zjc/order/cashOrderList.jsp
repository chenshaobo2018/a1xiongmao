<%@ page contentType="text/html;charset=utf-8"%>
<meta http-equiv=”Content-Type” content=”text/html; charset=gb2312”>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="混合支付订单列表" base="http" lib="ext">
	<style type="text/css"> 
		a:link { 
			font-size: 12px; 
			color: #000000; 
			text-decoration: none; 
		} 
		a:visited { 
			font-size: 12px; 
			color: #000000; 
			text-decoration: none; 
		} 
		a:hover { 
			font-size: 12px; 
			color: #999999; 
			text-decoration: underline; 
		}
	</style>
	<aos:body>
		<div id="btnDiv" class="x-hidden">
		</div>
		<div id="findDeliDiv" class="x-hidden"></div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" />
			<aos:textfield name="mj" fieldLabel="商家ID" columnWidth="0.33" />
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.33" />
			<aos:textfield name="goods_name" fieldLabel="商品名称" columnWidth="0.33" />
			<aos:textfield name="consignee" fieldLabel="收货人"  columnWidth="0.33" />
			<aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.33" />
			<aos:textfield name="order_sn" fieldLabel="订单编号" columnWidth="0.33" />
			<aos:datefield name="add_time_start" fieldLabel="下单时间(起)"  format="Y-m-d 00:00:00" editable="false" columnWidth="0.33" id="addStart"/>
			<aos:datefield name="add_time_end" fieldLabel="下单时间(止)" format="Y-m-d 23:59:59" editable="false" columnWidth="0.33" id="addEnd"/>
			<aos:combobox name="order_status" fieldLabel="订单状态" emptyText="订单状态" dicField="order_status" columnWidth="0.33" />
			<aos:combobox name="pay_status" fieldLabel="支付状态" emptyText="支付状态" dicField="pay_status" columnWidth="0.33" />
			<aos:combobox name="shipping_status" fieldLabel="发货状态" emptyText="发货状态" dicField="shipping_status" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="导出" onclick="fn_export_excel" icon="icon70.png" /> 
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcOrderService.listZjcOrders&pay_code=cash" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="在线支付订单列表" /> 
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="订单ID" dataIndex="order_id" hidden="true" />
			<aos:column header="店铺ID" dataIndex="store_id" hidden="true" />
			<aos:column header="特殊商品" dataIndex="is_car" hidden="true"/>
			<aos:column header="商家ID" dataIndex="mj" width="60"/>
			<aos:column header="订单编号" dataIndex="order_sn" celltip="true" width="130"/>
			<aos:column header="店铺名称" dataIndex="store_name" celltip="true"/>
			<aos:column dataIndex="goods_name" header="商品名称"  celltip="true"  rendererFn="format_goods_name"/>
			<%-- <aos:column header="商家电话" dataIndex="contacts_phone" /> --%>
			<aos:column header="会员ID" dataIndex="user_id" width="60"/>
			<aos:column header="收货人" dataIndex="consignee"/>
			<aos:column header="联系电话" dataIndex="mobile"/>
			<%-- <aos:column header="总金额" dataIndex="order_amount"/> --%>
			<aos:column header="现金" dataIndex="cash" width="60"/>
			<%-- <aos:column header="易支付" dataIndex="barter_coupons"/> --%>
			<aos:column header="在线支付状态" dataIndex="pay_status" rendererField="points_pay_status" width="80"/>
			<%-- <aos:column header="订单状态" dataIndex="order_status" rendererField="order_status" width="80"/> --%>
			<aos:column header="支付方式" dataIndex="pay_code" rendererField="pay_code_name" width="60"/>
			<%-- <aos:column header="发货状态" dataIndex="shipping_status" rendererField="shipping_status" width="60"/> --%>
			<aos:column header="下单时间" dataIndex="add_time" celltip="true" width="130"/>
			<aos:column header="发货时间" dataIndex="shipping_time" rendererFn="format_shippingTime" celltip="true" width="130"/>
			<aos:column header="收货时间" dataIndex="confirm_time" rendererFn="format_confirmTime" celltip="true" width="130"/>
		</aos:gridpanel>
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function order_query() {
			var params = AOS.getValue('f_query');
			g_order_store.getProxy().extraParams = params;
			g_order_store.loadPage(1);
		}
		
		
		//导出订单记录excel
		function fn_export_excel(){
			var params = AOS.getValue('f_query'); 
			//juid需要再这个页面的初始化方法中赋值,这里才引用得到
			//httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
			AOS.file('do.jhtml?router=zjcOrderService.exportOrderInfo&juid=${juid}&store_name='+encodeURI(encodeURI(params.store_name)) +'&user_id='+params.user_id  +'&order_sn='+params.order_sn
					+ '&order_status=' + params.order_status +'&pay_code=cash&pay_status=' + params.pay_status + '&shipping_status=' + params.shipping_status
					+ '&add_time_start=' + params.add_time_start + '&add_time_end=' + params.add_time_end + '&mj='+params.mj +'&goods_name='+encodeURI(encodeURI(params.goods_name)));
		}
		
		//格式化发货时间
		function format_shippingTime(value, metaData, record, rowIndex, colIndex,store){
			var shippingTimeStr = record.data.shipping_time;
			if(shippingTimeStr == ''){
				return '未确认发货';
			} else {
				return '<div>'+ shippingTimeStr +'</div>'
			}
		}
		//格式化收货时间
		function format_confirmTime(value, metaData, record, rowIndex, colIndex,store){
			var confirmTimeStr = record.data.confirm_time;
			if(confirmTimeStr == ''){
				return '未确认收货';
			} else {
				return '<div>'+ confirmTimeStr +'</div>'
			}
		}
		
		//格式化商品
		function format_goods_name(value, metaData, record, rowIndex, colIndex,store){
			var is_car = record.data.is_car;
			var goods_name = record.data.goods_name;
			if(is_car==1){
				return '<div style="color:red">'+ goods_name +'</div>';
			} else {
				return '<div>'+ goods_name +'</div>';
			}
		}
	  
	</script>
</aos:onready>
