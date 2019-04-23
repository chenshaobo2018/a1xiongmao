<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="结算排行" base="http" lib="ext">
	<aos:body>
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" labelWidth="100"/>
			<aos:datetimefield name="start_time" fieldLabel="统计开始时间" columnWidth="0.33" labelWidth="100"/>
			<aos:datetimefield name="end_time" fieldLabel="统计结算时间" columnWidth="0.33" labelWidth="100"/>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcXXOrderService.listXXOrderRankPage" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="消费排行" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="会员ID" dataIndex="user_id" align="center"/>
			<aos:column header="累计总额" dataIndex="sum_total_amount" align="center"/>
			<aos:column header="订单列表" type="action" align="center">
				<aos:action icon="query.png" tooltip="查看" handler="show_list"/>
			</aos:column>
		</aos:gridpanel>
		
		<aos:window id="w_exchang_order" layout="border" title="订单列表" autoScroll="true" maximized="true" onshow="getExchangeList">
			<aos:gridpanel id="g_exchange" region="north" url="zjcXXOrderService.getOrderList" height="200" hidePagebar="true">
				<aos:column type="rowno" />
				<aos:column header="订单ID" dataIndex="xx_id" align="center"/>
				<aos:column header="订单编号" dataIndex="order_sn" align="center"/>
				<aos:column header="会员ID" dataIndex="user_id" align="center"/>
				<aos:column header="商家会员ID" dataIndex="seller_user_id" align="center"/>
				<aos:column header="订单总额" dataIndex="total_amount" align="center"/>
				<aos:column header="商家实得" dataIndex="seller_amount" align="center"/>
				<aos:column header="消费时间" dataIndex="add_time" align="center"/>
			</aos:gridpanel>
		</aos:window>
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function order_query() {
			var params = AOS.getValue('f_query');
			g_order_store.getProxy().extraParams = params;
			g_order_store.loadPage(1);
		}
		
		function show_list(){
			w_exchang_order.show();
		}
		
		//查询用户在统计时间段内的全部转账记录
		function getExchangeList(){
			var start_time =  Ext.getCmp('f_query').getForm().findField('start_time').getValue();
			var end_time = Ext.getCmp('f_query').getForm().findField('end_time').getValue();
			var user_id = AOS.selectone(g_order).data.user_id;
			g_exchange_store.getProxy().extraParams = {
				user_id: user_id,
				start_time:start_time,
				end_time:end_time
			};
			g_exchange_store.loadPage(1);
		}
	</script>
</aos:onready>