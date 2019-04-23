<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
	   <aos:formpanel id="f_query" layout="column" labelWidth="120" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			    <aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" />
				<aos:textfield name="order_sn" fieldLabel="订单编号" columnWidth="0.33" />
				<aos:combobox name="type" fieldLabel="消息类型" emptyText="消息类型" dicField="queuetype" columnWidth="0.33"/>
				<aos:datetimefield name="add_time_start" fieldLabel="下单时间(起)" editable="false" columnWidth="0.33" id="addStart"/>
				<aos:datetimefield name="add_time_end" fieldLabel="下单时间(止)" editable="false" columnWidth="0.33" id="addEnd"/>
				<aos:combobox name="is_send" fieldLabel="返还状态" columnWidth="0.33" dicField="is_send" />
				<%-- <aos:combobox name="is_cancle" fieldLabel="是否取消" columnWidth="0.33" dicField="is_car" value="0"/> --%>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	
		<!-- 易物券列表 -->
		<aos:gridpanel id="g_param" url="ZjcQueueService.getUserList" region="center" onrender="g_param_query">
			<aos:menu>
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_param_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="券返还列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id"  fixedWidth="20" hidden="true" />
			<aos:column header="会员ID" dataIndex="user_id"  fixedWidth="100" />
			<aos:column header="消息类型" dataIndex="type" minWidth="80" rendererField="queuetype" />
			<aos:column header="可用券" dataIndex="xf_points" celltip="true" minWidth="60"  />
			<aos:column header="可转券" dataIndex="kz_points" celltip="true" minWidth="60"  />
			<aos:column header="下单时间" dataIndex="add_time" celltip="true" minWidth="80"  />
			<aos:column header="返还状态" dataIndex="is_send" celltip="true" width="30" rendererField="is_send"/>
			<aos:column header="返还时间" dataIndex="success_time"  minWidth="80" />
		<%-- 	<aos:column header="备注" dataIndex="note" celltip="true" minWidth="80"  /> --%>
			<%-- <aos:column header="操作" type="action" align="center">
				<aos:action handler="cancleQueue" icon="del.png" tooltip="取消" disabledFn="can_click"/>
			</aos:column> --%>
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
	                url: 'ZjcQueueService.getUserList',
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
           
           function cancleQueue(grid, rowIndex, colIndex){
        	   var record = grid.getStore().getAt(rowIndex);
        	   AOS.ajax({
   	          	params : {
   	          		id: record.data.id
   	          	},
   	              url: 'ZjcQueueService.cancleQueue',
   	              ok: function (data) {
   	  				AOS.tip(data.appmsg);
   	  				g_param_store.reload();
   	              }
   	         });
           }
           
           function can_click(grid, rowIndex, colIndex){
        	   var record = grid.getStore().getAt(rowIndex);
        	   if(record.data.is_send == 0){
        		   return false;
        	   }else{
        		   return true;
        	   }
           }
        </script>
</aos:onready>
