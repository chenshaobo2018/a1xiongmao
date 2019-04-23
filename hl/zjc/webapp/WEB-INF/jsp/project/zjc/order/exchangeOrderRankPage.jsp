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
			<aos:textfield name="user_id" fieldLabel="转出会员ID" columnWidth="0.33" labelWidth="100"/>
			<%-- <aos:textfield name="ex_sn" fieldLabel="订单编号" columnWidth="0.33" labelWidth="100"/> --%>
			<aos:datetimefield name="start_time" fieldLabel="统计开始时间" editable="false" columnWidth="0.33" labelWidth="100"/>
			<aos:datetimefield name="end_time" fieldLabel="统计结束时间" editable="false" columnWidth="0.33" labelWidth="100"/>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcExchangeOrderService.listZjcExchangeOrders" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="转账排行" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="转出会员ID" dataIndex="user_id" align="center"/>
			<aos:column header="会员昵称" dataIndex="nickname" align="center"/>
			<aos:column header="会员真实姓名" dataIndex="real_name" align="center"/>
			<aos:column header="联系电话" dataIndex="mobile" align="center"/>
			<aos:column header="累计转账" dataIndex="sum_ex" align="center"/>
			<aos:column header="转账列表" type="action" align="center">
				<aos:action icon="query.png" tooltip="查看" handler="show_list"/>
			</aos:column>
		</aos:gridpanel>
		
		<aos:window id="w_exchang_order" layout="border" title="转账列表" maximized="true" autoScroll="true" onshow="getExchangeList">
			<aos:gridpanel id="g_exchange" region="north" url="zjcExchangeOrderService.getExchangeList" height="200" hidePagebar="true">
				<aos:column type="rowno" />
				<aos:column hidden="true" dataIndex="ex_id" align="center"/>
				<aos:column header="订单编号" dataIndex="ex_sn" align="center" width="150"/>
				<aos:column header="用户ID" dataIndex="user_id" align="center"/>
				<aos:column header="会员名称" dataIndex="user_name" align="center"/>
				<aos:column header="卖家ID" dataIndex="seller_id" align="center"/>
				<aos:column header="卖家名称" dataIndex="seller_name" align="center"/>
				<aos:column header="转账积分" dataIndex="ex_price" align="center"/>
				<aos:column header="手机号" dataIndex="mobile" align="center"/>
				<aos:column header="手续费" dataIndex="ex_fee" align="center"/>
				<aos:column header="交易时间" dataIndex="add_time" align="center" width="150"/>
				<aos:column header="交易状态" dataIndex="status" rendererField="exc_order_status" align="center"/>
				<aos:column header="交易类型" dataIndex="type" rendererField="exc_order_type" align="center"/>
				<aos:column header="验证码" dataIndex="code" align="center"/>
				<aos:column header="订单备注" dataIndex="note" align="center"/>
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