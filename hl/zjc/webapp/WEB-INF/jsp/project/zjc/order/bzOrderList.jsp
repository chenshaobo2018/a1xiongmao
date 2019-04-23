<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="倍增订单列表" base="http" lib="ext">
	<aos:body>
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" />
			<aos:textfield name="seller_id" fieldLabel="结算中心ID" columnWidth="0.33" />
			<aos:textfield name="bz_sn" fieldLabel="订单编号" columnWidth="0.33" />
			<aos:datefield name="add_time_start" fieldLabel="结算时间（起）" editable="false" columnWidth="0.33"/>
			<aos:datefield name="add_time_end" fieldLabel="结算时间（止）" editable="false" columnWidth="0.33" />
			<aos:combobox name="is_send" value="0" fieldLabel="返还状态" dicField="is_send" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcBzOrderService.listZjcBzOrders" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="倍增订单列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="订单ID" dataIndex="bz_id" hidden="true" />
			<aos:column header="订单编号" dataIndex="bz_sn"/>
			<aos:column header="会员ID" dataIndex="user_id" />
			<aos:column header="会员名称" dataIndex="user_name" />
			<%-- <aos:column header="手机号码" dataIndex="mobile" /> --%>
			<aos:column header="结算中心ID" dataIndex="seller_id"/>
			<aos:column header="真实名称" dataIndex="seller_name"/>
			<aos:column header="结算金额" dataIndex="bz_price"/>
			<aos:column header="结算时间" dataIndex="add_time"/>
			<aos:column header="返还状态" dataIndex="is_send" celltip="true" width="30" rendererField="is_send"/>
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
