<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="线下易物列表" base="http" lib="ext">
	<aos:body>
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" labelWidth="100"/>
			<aos:textfield name="seller_user_id" fieldLabel="商家ID" columnWidth="0.33" labelWidth="100"/>
			<aos:textfield name="order_sn" fieldLabel="订单编号"  columnWidth="0.33" labelWidth="100"/>
			<aos:datetimefield name="start_time" fieldLabel="下单时间（起）" editable="false" columnWidth="0.33" labelWidth="100"/>
			<aos:datetimefield name="end_time" fieldLabel="下单时间（止）" editable="false" columnWidth="0.33" labelWidth="100"/>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcXXOrderService.initXXOrderListPage" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="线下购物列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column hidden="true" dataIndex="xx_id" />
			<aos:column header="订单编号" dataIndex="order_sn"/>
			<aos:column header="会员ID" dataIndex="user_id"/>
			<aos:column header="商家ID" dataIndex="seller_user_id" />
			<aos:column header="线下购物金额" dataIndex="total_amount"/>
			<%-- <aos:column header="线下购物结算" dataIndex="seller_amount"/> --%>
			<aos:column header="下单时间" dataIndex="add_time"/>
		</aos:gridpanel>
		
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function order_query() {
			var params = AOS.getValue('f_query');
			g_order_store.getProxy().extraParams = params;
			g_order_store.loadPage(1);
		}
	</script>
</aos:onready>