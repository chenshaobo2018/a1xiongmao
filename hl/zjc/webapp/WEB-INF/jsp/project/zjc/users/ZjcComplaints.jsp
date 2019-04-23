<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
		<div id="file_upload">
			<!-- 图片展示-->
			<div id="original_img_div"  >
			</div>
		</div>
		<div id="file_upload1">
			<!-- 图片展示 -->
			<div id="original_img_div1"  >
			</div>
		</div>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
	   <aos:formpanel id="f_query" layout="column" labelWidth="100" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			    <aos:textfield name="from_user_id" fieldLabel="投诉会员ID" columnWidth="0.2" />
				<aos:textfield name="phone" fieldLabel="投诉会员手机号码" columnWidth="0.2" />
				<%-- <aos:textfield name="nickname" fieldLabel="投诉会员昵称" columnWidth="0.2" /> --%>
				<aos:textfield name="to_user_id" fieldLabel="被投诉会员ID" columnWidth="0.2" />
				<aos:combobox name="status" fieldLabel="投诉状态" emptyText="投诉状态" dicField="status" columnWidth="0.2" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	
		<!-- 会员主数据 -->
		<aos:gridpanel id="g_param" url="ZjcComplaintsService.getUserList"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="投诉信息" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="表id" dataIndex="id"  fixedWidth="20" hidden="true" />
			<aos:column header="投诉会员ID" dataIndex="from_user_id" celltip="true" />
			<aos:column header="投诉会员手机号码" dataIndex="phone"  />
			<aos:column header="被投诉会员ID" dataIndex="to_user_id" celltip="true" />
			<aos:column header="投诉理由" dataIndex="info" celltip="true" width="200"/>
			<aos:column header="图片地址" dataIndex="img" celltip="true"/>
			<%-- <aos:column header="管理员编号" dataIndex="admin_id" /> --%>
			<aos:column header="管理员姓名" dataIndex="admin_name" />
			<aos:column header="管理员备注" dataIndex="note" />
			<aos:column header="投诉状态 " dataIndex="status" celltip="true" rendererFn="format_shippingTime"/>
			<aos:column header="投诉时间" dataIndex="add_time"   />
			<aos:column header="处理时间" dataIndex="handle_time"  />
			<aos:column header="操作" rendererFn="fn_button_render" align="center"/>
		</aos:gridpanel>
	</aos:viewport>
	
	<!-- 会员用户编辑 -->
	<aos:window id="w_zjcUser_u" title="投诉管理"  onshow="store_OnShow" height="600">
		<aos:formpanel id="f_zjcUser_u" width="800" layout="anchor" labelWidth="110" >
			<aos:hiddenfield name="id"/>
			<aos:textfield name="from_user_id" fieldLabel="投诉会员ID" allowBlank="true" maxLength="50" readOnly="true" />
			<aos:textfield name="phone" fieldLabel="投诉会员手机号码" allowBlank="true" maxLength="50" readOnly="true" />
			<aos:textfield name="to_user_id"  fieldLabel="被投诉会员ID" allowBlank="true" maxLength="50" readOnly="true"/>
			<aos:textfield name="info" fieldLabel="投诉理由"  allowBlank="true" readOnly="true"/>
			<aos:hiddenfield name="img" fieldLabel="图片地址" id="img"/>
			<aos:fieldset title="投诉图片:" margin="0 0 0 50" columnWidth="0.99" contentEl="file_upload" collapsible="false" border="false">
			</aos:fieldset>
			<%-- <aos:textfield name="admin_id" id="admin_id" fieldLabel="处理管理员ID" allowBlank="true" maxLength="50" readOnly="true"/> --%>
			<aos:textfield name="admin_name" id="admin_name" fieldLabel="管理员姓名" allowBlank="true" maxLength="50" readOnly="true"/>
			<aos:textfield name="add_time" fieldLabel="投诉时间" allowBlank="true" maxLength="50" readOnly="true"/>
			<aos:radioboxgroup fieldLabel="投诉状态 " columns="3"  columnWidth="0.33">
				<aos:radiobox name="status"  boxLabel="待处理" inputValue="0" />
				<aos:radiobox name="status"  boxLabel="处理中" inputValue="1" />
				<aos:radiobox name="status"  boxLabel="已处理" inputValue="2" />
			</aos:radioboxgroup>
			<aos:textareafield name="note" fieldLabel="管理员备注" allowBlank="true" maxLength="50" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
	<!-- 会员用户编辑 -->
	<aos:window id="w_zjcUser_us" title="投诉管理"  onshow="store_OnShow"  height="600">
		<aos:formpanel id="f_zjcUser_us" width="800" layout="anchor" labelWidth="110" >
			<aos:textfield name="id" fieldLabel="id" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:textfield name="from_user_id" fieldLabel="投诉用户ID" allowBlank="false" maxLength="50" readOnly="true" />
			<aos:textfield name="to_user_id"  fieldLabel="被投诉ID" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:textfield name="phone" fieldLabel="投诉人联系电话" allowBlank="false" maxLength="50" readOnly="true" />
			<aos:textfield name="info" fieldLabel="投诉理由"  allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:hiddenfield name="img" fieldLabel="图片地址"/>
			<aos:fieldset title="投诉图片:" margin="0 0 0 50"  columnWidth="0.99" contentEl="file_upload1" collapsible="false" border="false">
			</aos:fieldset>
			<aos:textfield name="admin_id" fieldLabel="处理管理员ID" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:textfield name="admin_name" fieldLabel="管理员名" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:textfield name="add_time" fieldLabel="投诉时间" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:radioboxgroup fieldLabel="是否处理 " columns="3"  columnWidth="0.33" >
				<aos:radiobox name="status"  boxLabel="未处理" inputValue="0" readOnly="true"/>
				<aos:radiobox name="status"  boxLabel="处理中" inputValue="1" readOnly="true"/>
				<aos:radiobox name="status"  boxLabel="已处理" inputValue="2" readOnly="true"/>
			</aos:radioboxgroup>
			<aos:textfield name="note" fieldLabel="管理员备注" allowBlank="true" maxLength="50" readOnly="true"/>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="#w_zjcUser_us.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>  
	<script type="text/javascript">
		
	 //会员等级按钮列转换
	function fn_button_render(value, metaData, record, rowIndex, colIndex,
			store) {
		return '<input type="button" value="修改" class="cbtn_danger" onclick="w_zjcUser_u_show();" />';
	}
	
		  //跳转到商家信息修改界面
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	        	console.log(record.data.id);
	            AOS.ajax({
	            	params : {
	            		id: record.data.id
	            	},
	                url: 'ZjcComplaintsService.getUserLists',
	                ok: function (data) {
	                	console.log(data);
	                	if(data.rows[0].status==2){
	                		f_zjcUser_us.form.setValues(data.rows[0]);
	                	}else{
	                		f_zjcUser_u.form.setValues(data.rows[0]);
	                	}
	                	var img = record.data.img;
	                	var imgarr = img.split(',');
	                	console.log("img:" + img +",imgarr:"+imgarr);
	                	$('#original_img_div').empty();
	                	$('#original_img_div1').empty();
	                	for(var i=0;i<imgarr.length;i++){
	                		$('#original_img_div').append('<img id="original_img" src="'
	                				+ imgarr[i] + '" class="app_cursor_pointer" width="215px" height="150px" style="margin-left: 30px;display: block; float: left;" >');
	                		$('#original_img_div1').append('<img id="original_img" src="'
	                				+ imgarr[i] + '" class="app_cursor_pointer" width="215px" height="150px" style="margin-left: 30px;display: block; float: left;" >');
	                	}
	                }
	            });
	        }
			
	        //数据提交(表单)
	        function f_zjcUser_u_submit() {
	            AOS.ajax({
	                //只提交一个表单
	                forms : f_zjcUser_u,
	                url : 'ZjcComplaintsService.updateStoreInfo',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    g_param_store.reload();
	                    w_zjcUser_u.hide();
	                }
	            });
	        }
	        
	        //格式化发货时间
			function format_shippingTime(value, metaData, record, rowIndex, colIndex,store){
				var apptype = record.data.status;
				if(apptype ==0){
					return '未处理';
				} else if(apptype ==1) {
					return '处理中';
				}else{
					return '已处理';
				}
			}
		  
           //查询会员主数据列表
            function g_param_query() {
            	 var params = AOS.getValue('f_query');
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
        </script>
</aos:onready>

<script type="text/javascript">
		//弹出会员修改参数窗口
		function w_zjcUser_u_show() {
			var record = AOS.selectone(Ext.getCmp('g_param'));
			AOS.ajax({
				url : 'homeService.getUser',
				ok : function(data) {
					Ext.getCmp('admin_id').setValue(data.id);
					Ext.getCmp('admin_name').setValue(data.name);
				}
			});
			if(record.data.status==2){
				Ext.getCmp('w_zjcUser_us').show();
			}else{
				Ext.getCmp('w_zjcUser_u').show();
			}
		    
		} 
</script>