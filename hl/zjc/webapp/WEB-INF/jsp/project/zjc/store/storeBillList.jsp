<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="委托收款商家账单列表" base="http" lib="ext">
	<script type="text/javascript" src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
	<script type="text/javascript" src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		
		<aos:formpanel id="f_query" layout="column" labelWidth="100" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="商家ID" columnWidth="0.25" />
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.35" />
			<aos:textfield name="real_name" fieldLabel="真实姓名" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="button" text="筛选" onclick="store_query" icon="query.png" />
					<aos:dockeditem xtype="button" text="导出" onclick="fn_export_excel" icon="icon70.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_stores" url="zjcStoreBillService.listZjcStoreBill" onrender="store_query" region="center" >
				<aos:docked forceBoder="1 0 1 0">
					<aos:dockeditem xtype="tbtext" text="转款汇总信息" />
				</aos:docked>
				<aos:column header="商家店铺ID" dataIndex="store_id" hidden="true"/>
				<aos:column header="通过转款金额" dataIndex="sum_cash" hidden="true"/>
				<aos:column header="商家ID" dataIndex="user_id" width="40" align="center"/>
				<aos:column header="店铺名称" dataIndex="store_name" align="center" rendererFn="format_user"/>
				<aos:column header="真实姓名" dataIndex="real_name" width="40" align="center"/>
				<aos:column header="冻结金额（元）" dataIndex="frozen_money" width="80" align="center"/>
				<aos:column header="待转账金额（元）" dataIndex="pending_transfer" width="80" align="center"/>
				<aos:column header="转账金额（元）" dataIndex="cost" width="80" align="center"/>
				<aos:column header="已转账金额（元）" dataIndex="transferred" width="80" align="center"/>
				<aos:column header="合计金额（元）" dataIndex="sum_money" width="80" align="center"/>
				<aos:column header="转账记录" type="action" align="center" width="50">
					<aos:action icon="text_col.png" tooltip="转账记录" handler="query_transfer_record"/>
				</aos:column>
				<aos:column header="操作申请明细" type="action" align="center" width="50">
					<aos:action icon="theme.png" handler="query_order_record" tooltip="操作申请明细"/>
				</aos:column>
				<aos:column header="转账" type="action" align="center" width="50">
					<aos:action icon="redo.png" handler="confirm_transfer" tooltip="转账"/>
				</aos:column>
		</aos:gridpanel>
		
		<!-- 操作申请列表 -->
		<aos:window id="w_order_list" layout="fit" title="待转账订单列表" maximized="true" autoScroll="true" onshow="show_order">
			<aos:panel layout="auto" autoScroll="true" border="false">
				<aos:panel layout="anchor" width="auto" border="false">
						<aos:formpanel id="f_query_" layout="column" header="false"  border="true" anchor="100%">
							<aos:hiddenfield name="store_id" id="store_id"/>
							<aos:textfield name="order_sn" fieldLabel="订单号" columnWidth="0.20" />
							<aos:combobox name="pay_code" fieldLabel="支付方式"  emptyText="支付方式" dicField="pay_code" columnWidth="0.20" />
							<aos:datefield name="confirm_time_start" fieldLabel="完成订单范围" format="Y-m-d 23:59:59" editable="false" columnWidth="0.30" value="起始时间"/>
							<aos:datefield name="confirm_time_end"  format="Y-m-d 23:59:59" editable="false" columnWidth="0.20" value="终止时间"/>
							<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
							<aos:dockeditem xtype="button" text="转账申请" onclick="sq_transfer()" icon="ok.png" />
							<aos:dockeditem xtype="button" text="导出" onclick="fn_export_excel_o" icon="icon70.png" />
						</aos:formpanel>
						<!-- onrender="order_query" -->
						<aos:gridpanel id="g_orders"  url="zjcStoreBillService.query_order_record" features="summary"  anchor="100%" height="825">
							<aos:selmodel type="checkbox" mode="multi"/>
							<aos:column hidden="true" dataIndex="order_id"></aos:column>
							<aos:column header="订单号" dataIndex="order_sn" align="center"/>
							<aos:column header="订单时间" dataIndex="confirm_time" align="center"/>
							<aos:column header="支付方式" dataIndex="pay_code" align="center" rendererField="pay_code"/>
							<aos:column header="货币金额" dataIndex="cash" type="number" summaryType="sum" align="center"/>
						</aos:gridpanel>
				</aos:panel>
			</aos:panel>			
		</aos:window>
				
		<aos:window id="w_recode_list" layout="fit" title="转账记录列表" maximized="true" autoScroll="true" onshow="show_recode">				
				<aos:panel layout="auto" autoScroll="true" border="false">
				<aos:panel layout="anchor" width="auto" border="false">
						<aos:gridpanel id="g_recode"  url="zjcStoreBillService.query_transfer_record" onshow="show_recode" anchor="100%" height="865">
							<aos:docked forceBoder="0 0 0 0">
								<aos:dockeditem text="导出" onclick="fn_export_excel_r" icon="add.png" />
							</aos:docked>
							<aos:column header="店铺名称" dataIndex="store_name" align="center"/>
							<aos:column header="订单下单时间" dataIndex="add_time" align="center"/>
							<aos:column header="订单完成时间" dataIndex="confirm_time" align="center"/>
							<aos:column header="转账时间" dataIndex="transit_time" align="center"/>
							<aos:column header="转账金额" dataIndex="cash" align="center"/>
							<aos:column header="操作员" dataIndex="operator_id" align="center" hidden="true"/>
							<aos:column header="审批员" dataIndex="approval_id" align="center" hidden="true"/>
							<aos:column header="操作员" dataIndex="operator_name" align="center"/>
							<aos:column header="审批员" dataIndex="approval_name" align="center"/>
						</aos:gridpanel>
				</aos:panel>
			</aos:panel>					
			</aos:window>
			
			<aos:window id="w_zjcUser_u" title="确认转账"  onshow="store_OnShow">
				<aos:formpanel id="f_zjcUser_u" width="800" layout="column" labelWidth="100" >
					<aos:fieldset title="委托人信息" labelWidth="70">
						<aos:textfield name="real_name" id="w_real_name" fieldLabel="委托人姓名" border="none" readOnly="true" columnWidth="0.5"/>
						<aos:textfield name="id_card" id="w_id_card" fieldLabel="委托人身份证号" border="none" readOnly="true" columnWidth="0.5"/>
						<aos:textfield name="store_id" id="w_store_id" fieldLabel="店铺ID" border="none" readOnly="true" columnWidth="0.5"/>
						<aos:textfield name="contract_mobile" id="w_contract_mobile" fieldLabel="联系电话" border="none" readOnly="true" columnWidth="0.5"/>
						<aos:textfield name="store_name" id="w_store_name" fieldLabel="店铺名称" border="none" readOnly="true" columnWidth="1"/>
					</aos:fieldset>
					<aos:fieldset title="收款银行信息" labelWidth="70">
						<aos:textfield name="account_name" id="w_account_name" fieldLabel="开户户名" border="none" readOnly="true" columnWidth="1"/>
						<aos:textfield name="account_mumber" id="w_account_mumber" fieldLabel="开户账号" border="none" readOnly="true" columnWidth="1"/>
						<aos:textfield name="bank" id="w_bank" fieldLabel="开户行" border="none" readOnly="true" columnWidth="1"/>
					</aos:fieldset>
					<aos:fieldset title="金额信息" labelWidth="70">
						<aos:textfield name="min_confirm_time" id="min_confirm_time" fieldLabel="订单最小时间" border="none" readOnly="true" columnWidth="1"/>
						<aos:textfield name="max_confirm_time" id="max_confirm_time" fieldLabel="订单最大时间" border="none" readOnly="true" columnWidth="1"/>
						<aos:textfield name="sum_cash" id="sum_cash" fieldLabel="合计金额" border="none" readOnly="true" columnWidth="1"/>
						<aos:textfield name="sj_cash" id="sj_cash" fieldLabel="实际金额" border="none" readOnly="true" columnWidth="1"/>
					</aos:fieldset>
					<aos:radioboxgroup fieldLabel="向商家发送短信通知 " columns="4"  columnWidth="0.55">
						<aos:radiobox name="send_msg"  boxLabel="是" inputValue="1" />
						<aos:radiobox name="send_msg"  boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>					
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="f_zjcUser_user_submit" text="确认" icon="ok.png" />
					<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window> 
		</aos:viewport>
<script type="text/javascript">
	//加载表格数据
	function store_query() {
		var params = AOS.getValue('f_query');
		g_stores_store.getProxy().extraParams = params;
		g_stores_store.loadPage(1);
	}
	//转账申请列表
	function query_transfer_record() {
		w_recode_list.show();
	}
	
	//转账申请列表
	function query_order_record() {
		var record = AOS.selectone(g_stores);
		Ext.getCmp('store_id').setValue(record.data.store_id);
		w_order_list.show();
	}
	
	function order_query() {
		var params = AOS.getValue('f_query_');
		g_orders_store.getProxy().extraParams = params;
		g_orders_store.loadPage(1);
	}
	function show_order(){
		var params = AOS.getValue('f_query_');
		g_orders_store.getProxy().extraParams = params;
		g_orders_store.loadPage(1);
	}
	
	function show_recode(){
		var record = AOS.selectone(g_stores);
		g_recode_store.getProxy().extraParams = {
			store_id : record.data.store_id
		};
		g_recode_store.loadPage(1);
	}
	
	function sq_transfer(){
		var rows = AOS.rows(g_orders);		
		if(rows === 0){
			AOS.tip('转账申请前请先选中数据。');
			return;
		}		
		AOS.confirm("确认要申请转账！", function(btn){
			if(btn === 'cancel'){
				AOS.tip('转账申请操作被取消。');
				return;
			}
			AOS.ajax({
				url : 'zjcStoreBillService.apply_for_transfer',
				params:{
					ids: AOS.selection(g_orders, 'order_id'),
					cashs: AOS.selection(g_orders, 'cash')
				},
				ok : function(data) {
					AOS.tip(data.appmsg);
					w_order_list.hide();
					store_query();
				}
			});
		});
	}
	
	function confirm_transfer(){
		var record = AOS.selectone(g_stores);
		/* if(record.data.cost == '0' || record.data.cost == '0.0'){
			AOS.tip("商家转账金额为0！");
			return;
		} */
		w_zjcUser_u.show();
	}
	
	//导出订单记录excel
	function fn_export_excel(){
		var params = AOS.getValue('f_query');
		AOS.file('do.jhtml?router=zjcStoreBillService.exportBill&juid=${juid}&store_name='+params.store_name +'&store_id='+params.store_id);
	}
	
	function fn_export_excel_o(){
		var params = AOS.getValue('f_query_');
		AOS.file('do.jhtml?router=zjcStoreBillService.exportBillOrder&juid=${juid}&store_id='+params.store_id +'&order_sn='+params.order_sn+'&pay_code='+params.pay_code+'&confirm_time_start='+params.confirm_time_start+'&confirm_time_end='+params.confirm_time_end);
	}
	
	function fn_export_excel_r(){
		var record = AOS.selectone(g_stores);
		AOS.file('do.jhtml?router=zjcStoreBillService.exportBillRecord&juid=${juid}&store_id='+record.data.store_id);
	}
	
	function store_OnShow(){
		var record = AOS.selectone(g_stores);
		AOS.ajax({
			params : {
    			store_id: record.data.store_id
    		},
	        url: 'zjcStoreBillService.getDepute',
	        ok: function (data) {
	        	Ext.getCmp("w_real_name").setValue(data.real_name);
	        	Ext.getCmp("w_id_card").setValue(data.id_card);
	        	Ext.getCmp("w_store_id").setValue(data.store_id);
	        	Ext.getCmp("w_contract_mobile").setValue(data.contract_mobile);
	        	Ext.getCmp("w_store_name").setValue(data.store_name);
	        	Ext.getCmp("w_account_name").setValue(data.account_name);
	        	Ext.getCmp("w_account_mumber").setValue(data.account_mumber);
	        	Ext.getCmp("w_bank").setValue(data.bank);
	        	Ext.getCmp("min_confirm_time").setValue(data.min_confirm_time);
	        	Ext.getCmp("max_confirm_time").setValue(data.max_confirm_time);
	        	Ext.getCmp("sum_cash").setValue(data.sum_cash);
	        }
		});
	}
	
	function f_zjcUser_user_submit(){
		var record = AOS.selectone(g_stores);
		 AOS.ajax({
        	forms:f_zjcUser_u,
            url: 'zjcStoreBillService.confirm_transfer',
            ok: function (data) {
            	AOS.tip(data.appmsg);
            	store_query();
            	w_zjcUser_u.hide();
            }
        });
	}
	
	//格式化商家ID
	function format_user(value, metaData, record, rowIndex, colIndex,store){
		var sum_cash = record.data.sum_cash;
		var store_name = record.data.store_name;
		if(sum_cash > 0){
			return '<div style="color:green">'+ store_name +'</div>';
		} else{
			return '<div>'+ store_name +'</div>';
		}
	}
	
</script>
</aos:onready>