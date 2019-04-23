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
			<aos:textfield name="user_id" fieldLabel="会员编号" columnWidth="0.33" labelWidth="150"/>
			<aos:textfield name="user_name" fieldLabel="会员名称" columnWidth="0.33" labelWidth="150"/>
			<aos:textfield name="seller_id" fieldLabel="结算中心持有人ID" columnWidth="0.33" labelWidth="150"/>
			<aos:textfield name="seller_name" fieldLabel="结算中心持有人名称" columnWidth="0.33" labelWidth="150"/>
			<aos:datetimefield name="start_time" fieldLabel="统计开始时间" columnWidth="0.33" labelWidth="150"/>
			<aos:datetimefield name="end_time" fieldLabel="统计结束时间" columnWidth="0.33" labelWidth="150"/>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcBzOrderService.listBzOrderRankPage" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="结算排行" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="会员编号" dataIndex="user_id" align="center"/>
			<aos:column header="会员名称" dataIndex="user_name" align="center"/>
			<aos:column header="结算中心累计" dataIndex="sum_price" align="center"/>
			<aos:column header="会员推荐码" dataIndex="recommended_code" align="center"/>
			<aos:column header="结算中心持有人ID" dataIndex="seller_id" align="center"/>
			<aos:column header="结算中心持有人名称" dataIndex="seller_name" align="center"/>
			<aos:column header="转账列表" type="action" align="center">
				<aos:action icon="query.png" tooltip="查看" handler="show_list"/>
			</aos:column>
		</aos:gridpanel>
		
		<aos:window id="w_exchang_order" layout="border" title="转账列表" maximized="true" autoScroll="true" onshow="getExchangeList">
			<aos:gridpanel id="g_exchange" region="north" url="zjcBzOrderService.getBzOrderList" height="200" hidePagebar="true">
				<aos:column type="rowno" />
				<aos:column header="订单ID" dataIndex="bz_id" align="center"/>
				<aos:column header="订单编号" dataIndex="bz_sn" align="center"/>
				<aos:column header="会员ID" dataIndex="user_id" align="center"/>
				<aos:column header="会员名称" dataIndex="user_name" align="center"/>
				<aos:column header="结算中心价格" dataIndex="bz_price" align="center"/>
				<aos:column header="倍增金额" dataIndex="bz_after_price" align="center"/>
				<aos:column header="会员邀请码" dataIndex="recommended_code" align="center"/>
				<aos:column header="结算中心持有人ID" dataIndex="seller_id" align="center"/>
				<aos:column header="结算中心持有人名称" dataIndex="seller_name" align="center"/>
				<aos:column header="结算时间" dataIndex="add_time" align="center"/>
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
