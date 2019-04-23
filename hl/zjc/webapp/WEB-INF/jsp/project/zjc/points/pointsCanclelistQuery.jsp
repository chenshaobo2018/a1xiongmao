<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="已取消返分列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
	   <aos:formpanel id="f_query" layout="column" labelWidth="120" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			    <aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.3" />
			    <aos:textfield name="order_sn" fieldLabel="订单编号" columnWidth="0.3" />
				<aos:combobox name="type" fieldLabel="消息类型" emptyText="消息类型" dicField="queuetype" columnWidth="0.3" />
				<aos:datefield name="add_time_start" fieldLabel="下单时间(起)"  format="Y-m-d 00:00:00" editable="false" columnWidth="0.3" id="addStart"/>
				<aos:datefield name="add_time_end" fieldLabel="下单时间(止)" format="Y-m-d 23:59:59" editable="false" columnWidth="0.3" id="addEnd"/>
				<%-- <aos:combobox name="is_send" fieldLabel="返还状态" dicField="is_send" columnWidth="0.2" /> --%>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	
		<!--反分主数据 -->
		<aos:gridpanel id="g_param" url="ZjcQueueService.pointsCancleList"  region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="已取消返分列表" />
				<%-- <aos:dockeditem text="批量处理" icon="edit.png" onclick="confirmAllReq" /> --%>
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id"  fixedWidth="80" hidden="true" />
			<aos:column header="会员ID" dataIndex="user_id"  />
			<aos:column header="消息类型" dataIndex="type" rendererField="queuetype" />
			<aos:column header="可用券" dataIndex="xf_points" />
			<aos:column header="可转券" dataIndex="kz_points" />
			<aos:column header="下单时间" dataIndex="add_time" celltip="true"/>
			<aos:column header="返还状态" dataIndex="is_send" celltip="true" rendererField="is_send"/>
			<aos:column header="返还时间" dataIndex="success_time" />
			<%-- <aos:column header="订单id" dataIndex="relation_id"  fixedWidth="100" /> --%>
			<%-- <aos:column header="操作" type="action" align="center">
				<aos:action handler="confirmReq" icon="ok1.png" tooltip="处理" /> 
			</aos:column> --%>
		</aos:gridpanel>
	</aos:viewport>
	<script type="text/javascript">
		  
		function confirmReq(grid, rowIndex, colIndex){
			var record = AOS.selectone(g_param);
			var msg =  AOS.merge('确认将记录【{0}】重新加入到返分队列吗？', record.data.id);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('确认操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'ZjcQueueService.confirmReq',
					params:{
						id: record.data.id
					},
					ok : function(data) {
						AOS.tip(data.appmsg);
						g_param_store.reload();
					}
				});
			});
		}
	
      	//批量处理
	 	function confirmAllReq(){
	 		 var selection = AOS.selection(g_param, 'id');
	            if (AOS.empty(selection)) {
	                AOS.tip('请先选中待处理的数据。');
	                return;
	            }
	            var rows = AOS.rows(g_param);
	            var msg = AOS.merge('确认将选中的[{0}]条数据重新加入到返分队列吗？', rows);
	            AOS.confirm(msg, function (btn) {
	                if (btn === 'cancel') {
	                    AOS.tip('操作被取消。');
	                    return;
	                }
					AOS.ajax({
						url : 'ZjcQueueService.confirmAllReq',
						params:{
							aos_rows: selection
						},
						ok : function(data) {
							AOS.tip(data.appmsg);
							g_param_store.reload();
						}
					});
	            })
			}
			
           //查询会员主数据列表
            function g_param_query() {
            	 var params = AOS.getValue('f_query');
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
        </script>
</aos:onready>