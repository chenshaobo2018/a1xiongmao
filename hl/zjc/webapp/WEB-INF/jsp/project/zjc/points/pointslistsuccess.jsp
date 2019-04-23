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
			    <aos:textfield name="user_id" fieldLabel="会员id" columnWidth="0.25" />
			    <aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.25" />
			      <aos:textfield name="relation_id" fieldLabel="订单id" columnWidth="0.25" />
				<aos:combobox name="type" fieldLabel="消息类型" emptyText="消息类型" dicField="queuetype" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	
		<!--反分主数据 -->
		<aos:gridpanel id="g_param" url="ZjcQueueService.listsuccessPage"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="返分列表信息" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id"  fixedWidth="20" hidden="true" />
			<aos:column header="备注" dataIndex="note" minWidth="30" />
			<aos:column header="消息类型" dataIndex="type" minWidth="30" rendererField="queuetype" />
			<aos:column header="添加时间" dataIndex="add_time" celltip="true" width="50" rendererField="is"/>
			<aos:column header="用户id" dataIndex="user_id"  fixedWidth="150" />
			<aos:column header="联系电话" dataIndex="mobile"  fixedWidth="100" />
			<aos:column header="可转积分" dataIndex="kz_points"  fixedWidth="100" />
			<aos:column header="消费积分" dataIndex="xf_points"  fixedWidth="100" />
			<aos:column header="订单id" dataIndex="relation_id"  fixedWidth="100" />
			<aos:column header="执行时间" dataIndex="success_time"  fixedWidth="100" />
		</aos:gridpanel>
	</aos:viewport>
	<script type="text/javascript">
		  //跳转到商家信息修改界面
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		id: record.data.id
	            	},
	                url: 'ZjcQueueService.listsuccessPage',
	                ok: function (data) {
	                	console.log(data.rows[0].level_name);
	                	if(data.rows[0].status==1){
	                		f_zjcUser_us.form.setValues(data.rows[0]);
	                	}else{
	                		f_zjcUser_u.form.setValues(data.rows[0]);
	                	}
	                }
	            });
	        }
		  
	     
			
           //查询会员主数据列表
            function g_param_query() {
            	 var params = AOS.getValue('f_query');
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
        </script>
</aos:onready>