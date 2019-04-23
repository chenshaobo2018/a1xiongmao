<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="反分列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
	   <aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			    <aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.2" />
			   <%--  <aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.2" /> --%>
			    <%-- <aos:textfield name="relation_id" fieldLabel="订单id" columnWidth="0.2" /> --%>
				<aos:combobox name="type" fieldLabel="消息类型" emptyText="消息类型" dicField="queuetype" columnWidth="0.2" />
				<%-- <aos:combobox name="is_send" fieldLabel="返还状态" dicField="is_send" columnWidth="0.2" /> --%>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	
		<!--反分主数据 -->
		<aos:gridpanel id="g_param" url="ZjcQueueService.getfanfen"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="待处理返分列表信息" />
				<aos:dockeditem text="批量返分" icon="edit.png" onclick="g_acount_del" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id"  fixedWidth="80" hidden="true" />
			<aos:column header="会员ID" dataIndex="user_id" />
			<%-- <aos:column header="联系电话" dataIndex="mobile" /> --%>
			<aos:column header="消息类型" dataIndex="type" rendererField="queuetype" />
			<aos:column header="可用券" dataIndex="xf_points" />
			<aos:column header="可转券" dataIndex="kz_points" />
			<aos:column header="下单时间" dataIndex="add_time" celltip="true"/>
			<aos:column header="返还状态" dataIndex="is_send" celltip="true" rendererField="is_send"/>
			<aos:column header="返还时间" dataIndex="send_time" />
			<%-- <aos:column header="备注" dataIndex="note" minWidth="80" />
			<aos:column header="联系电话" dataIndex="mobile"  fixedWidth="100" />
			<aos:column header="订单id" dataIndex="relation_id"  fixedWidth="100" /> --%>
		</aos:gridpanel>
	</aos:viewport>
	<script type="text/javascript">
		  
	      	//反分
		 	function g_acount_del(){
		 		 var selection = AOS.selection(g_param, 'id');
		            if (AOS.empty(selection)) {
		                AOS.tip('请先选中处理的数据。');
		                return;
		            }
		            var rows = AOS.rows(g_param);
		            var msg = AOS.merge('确认处理选中的[{0}]条数据吗？', rows);
		            AOS.confirm(msg, function (btn) {
		                if (btn === 'cancel') {
		                    AOS.tip('确认操作被取消。');
		                    return;
		                }
						AOS.ajax({
							url : 'ZjcQueueService.fanfen',
							params:{
								id: selection
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