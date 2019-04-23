<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="结算中心订单列表" base="http" lib="ext">
	<aos:body>
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_name" fieldLabel="会员名称" columnWidth="0.33" />
			<aos:textfield name="balance_sn" fieldLabel="订单编号" columnWidth="0.33" />
			<aos:datefield name="add_time_start" fieldLabel="购买时间（起）"  format="Y-m-d 00:00:00" editable="false" columnWidth="0.33" />
			<aos:datefield name="add_time_end" fieldLabel="购买时间（止）" format="Y-m-d 23:59:59" editable="false" maxValue="" disabledDaysText="测试" minValue="" columnWidth="0.33" />
			<aos:combobox name="status" fieldLabel="审核状态" emptyText="审核状态" dicField="audit_status" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcBalanceOrderService.listZjcBalanceOrders" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="结算中心订单列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="订单ID" dataIndex="balance_id" hidden="true" />
			<aos:column header="订单编号" dataIndex="balance_sn"/>
			<aos:column header="会员名称" dataIndex="user_name" />
			<aos:column header="结算中心价格" dataIndex="balance_price"/>
			<aos:column header="购买时间" dataIndex="add_time"/>
			<aos:column header="审核管理员" dataIndex="admin_name"/>
			<aos:column header="审核备注" dataIndex="verify_note"/>
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