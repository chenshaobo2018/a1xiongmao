<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="易物券转账列表" base="http" lib="ext">
	<aos:body>
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="转出会员ID" columnWidth="0.33" />
			<aos:textfield name="seller_id" fieldLabel="转入会员ID" columnWidth="0.33" />
			<%-- <aos:textfield name="mobile" fieldLabel="手机号码" columnWidth="0.33"  emptyText="转入会员手机号码"/> --%>
			<aos:textfield name="ex_sn" fieldLabel="订单编号" columnWidth="0.33" />
			<aos:datefield name="start_time" fieldLabel="转账时间(起)"  format="Y-m-d 00:00:00" editable="false" columnWidth="0.33" />
			<aos:datefield name="end_time" fieldLabel="转账时间(止)" format="Y-m-d 23:59:59" editable="false"  columnWidth="0.33" />
			<%-- <aos:datetimefield name="start_time" fieldLabel="下单时间（起）" editable="false" columnWidth="0.33" />
			<aos:datetimefield name="end_time" fieldLabel="下单时间（止）" editable="false" columnWidth="0.33"/> --%>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcExchangeOrderService.listZjcExchangeOrder" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="转账列表" />
			</aos:docked>
			<aos:column type="rowno" />
				<aos:column header="订单编号" dataIndex="ex_sn" align="center" width="150"/>
				<aos:column header="会员ID（转出）" dataIndex="user_id" align="center"/>
				<aos:column header="真实姓名（转出）" dataIndex="user_name" align="center"/>
				<aos:column header="会员ID（转入）" dataIndex="seller_id" align="center"/>
				<aos:column header="真实姓名（转入）" dataIndex="seller_name" align="center"/>
				<aos:column header="转账金额" dataIndex="ex_price" align="center"/>
				<%-- <aos:column header="订单类型" dataIndex="type" align="center" rendererField="ex_type"/> --%>
				<aos:column header="订单状态" dataIndex="status" rendererField="exc_order_status" align="center"/>
				<aos:column header="转账时间" dataIndex="add_time"  align="center" celltip="true"/>
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