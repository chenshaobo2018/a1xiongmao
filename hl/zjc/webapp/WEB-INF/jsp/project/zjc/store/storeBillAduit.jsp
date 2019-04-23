<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="委托收款申请转账审核列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.35" />
			<%-- <aos:textfield name="user_id" fieldLabel="商家ID" columnWidth="0.25" />
			<aos:textfield name="real_name" fieldLabel="真实姓名" columnWidth="0.35" /> --%>
			<%-- <aos:textfield name="operator_id" fieldLabel="操作员ID" columnWidth="0.25" /> --%>
				<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="button" text="筛选" onclick="store_query" icon="query.png" />
					<aos:dockeditem xtype="button" text="通过" onclick="via" icon="ok.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_stores" url="zjcStoreBillService.listZjcStoreBillAduit" onrender="store_query" region="center" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="转账审核信息" />
			</aos:docked>
				<%-- <aos:column type="rowno" />  --%>
				<aos:selmodel type="checkbox" mode="multi"/>
				<aos:column hidden="true" dataIndex="order_id"/>
				<aos:column hidden="true" dataIndex="store_id"/>
				<%-- <aos:column header="商家ID" dataIndex="user_id" width="40" align="center"/> --%>
				<aos:column header="店铺名称" dataIndex="store_name" align="center"/>
				<aos:column header="订单金额（元）" dataIndex="cash" width="80" align="center"/>
				<aos:column header="订单完成时间" dataIndex="confirm_time"  width="80" align="center"/>
				<aos:column header="操作员" dataIndex="operator_id" width="40" align="center" hidden="true"/>
				<aos:column header="操作员" dataIndex="operator_name" width="40" align="center"/>
		</aos:gridpanel>
	</aos:viewport>

<script type="text/javascript">
	//加载表格数据
	function store_query() {
		var params = AOS.getValue('f_query');
		g_stores_store.getProxy().extraParams = params;
		g_stores_store.loadPage(1);
	}
	function via(){
		var rows = AOS.rows(g_stores);		
		if(rows === 0){
			AOS.tip('请先选中数据。');
			return;
		}		
		AOS.confirm("确认通过！", function(btn){
			if(btn === 'cancel'){
				AOS.tip('删除操作被取消。');
				return;
			}
			AOS.ajax({
				url : 'zjcStoreBillService.via_for_transfer',
				params:{
					ids: AOS.selection(g_stores, 'order_id')
				},
				ok : function(data) {
					AOS.tip(data.appmsg);
					g_stores.store.reload();
				}
			});
		});
	}
</script>
</aos:onready>	