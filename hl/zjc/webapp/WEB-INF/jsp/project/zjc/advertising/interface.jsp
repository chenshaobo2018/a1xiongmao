<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
			<aos:dockeditem onclick="#w_zjcUser_us.show();" text="新增系统消息" icon="add.png" />
			</aos:docked>
			<aos:textfield name="version_number" fieldLabel="版本号" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		<!-- 接口列表主数据 -->
		<aos:gridpanel id="g_param" url="ZjcSystemInterfaceService.getZjcSystemInterface"   region="center" onrender="g_param_query"
		onitemdblclick="w_zjcUser_u_show" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="接口列表信息" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id"  fixedWidth="20" hidden="true" />
			<aos:column header="接口路径" dataIndex="interfacesd" celltip="true" minWidth="80"  />
			<aos:column header="接口名称" dataIndex="interface_name" celltip="true" width="80" />
			<aos:column header="版本号" dataIndex="version_number" celltip="true" width="50" />
			<aos:column header="接口描述" dataIndex="interface_note" width="50" celltip="true" />
			<aos:column header="操作" rendererFn="fn_button_render" align="center" fixedWidth="200"/>
		</aos:gridpanel>
	</aos:viewport>
	<!-- 会员用户编辑 -->
	<aos:window id="w_zjcUser_u" title="修改用户"  onshow="store_OnShow" >
		<aos:formpanel id="f_zjcUser_u" width="800" layout="anchor" labelWidth="100" >
			<aos:hiddenfield name="id"  />
			<aos:textfield name="interfacesd" fieldLabel="接口路径" allowBlank="false" maxLength="50" />
			<aos:textfield name="interface_name"  fieldLabel="接口名称" allowBlank="false" maxLength="80" />
			<aos:textfield name="version_number"   fieldLabel="版本号" allowBlank="false" maxLength="50" />
			<aos:textareafield name="interface_note"  fieldLabel="接口描述" allowBlank="false"/>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
	<aos:window id="w_zjcUser_us" title="新增接口信息"  onshow="#AOS.reset(f_ad_position);" >
		<aos:formpanel id="f_ad_position" width="800" layout="anchor" labelWidth="65" >
			<aos:textfield name="interfacesd" fieldLabel="接口路径" allowBlank="false" maxLength="50" />
			<aos:textfield name="interface_name"  fieldLabel="接口名称" allowBlank="false" maxLength="80" />
			<aos:combobox name="version_number" fieldLabel="版本号" url="ZjcAppversonService.listBrandComboBoxData" columnWidth="0.25" />
			<aos:textareafield name="interface_note"  fieldLabel="接口描述" allowBlank="false"/>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_position_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_us.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
			
	<script type="text/javascript">
		  //会员主数据按钮列转换
			function fn_button_render(value, metaData, record, rowIndex, colIndex,
					store) {
				return '<input type="button" value="编辑" class="cbtn_danger" onclick="w_zjcUser_u_show();" />' 
				       +'<input type="button" value="删除 " class="cbtn_danger" onclick="updatestate();" />'
			}
			
			//会员下级按钮列转换
			function fn_button_renders(value, metaData, record, rowIndex, colIndex,
					store) {
				return '<input type="button" value="下级" class="cbtn_danger" onclick="UserFirstLeaders();" />';
			}
			 
		  //跳转到商家信息修改界面
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		id: record.data.id
	            	},
	                url: 'ZjcSystemInterfaceService.getlistPage',
	                ok: function (data) {
	                	f_zjcUser_u.form.setValues(data[0]);
	                }
	            });
	        }
			 
	       
			
           //查询会员主数据列表
            function g_param_query() {
                var params = AOS.getValue('f_query');
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
           
		  //新增参数保存
	        function f_ad_position_submit() {
	            AOS.ajax({
	                forms: f_ad_position,
	                url: 'ZjcSystemInterfaceService.saveZjcSystemInterface',
	                ok: function (data) {
	                	   w_zjcUser_us.hide();
	                	   g_param_store.reload();
	                	   AOS.tip(data.appmsg);
	                }
	            });
	        }
            
	        //接口信息修改方法
	        function f_zjcUser_u_submit() {
	            AOS.ajax({
	                //只提交一个表单
	                forms : f_zjcUser_u,
	                url : 'ZjcSystemInterfaceService.updateInterface',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    g_param_store.reload();
	                    w_zjcUser_u.hide();
	                }
	            });
	        }
	        
	       
       
 
        </script>
</aos:onready>

<script type="text/javascript">

		//弹出会员修改参数窗口
		function w_zjcUser_u_show() {
		    Ext.getCmp('w_zjcUser_u').show();
		} 
		 
		 //接口信息删除js 理论删除
        function updatestate() {
        	var record = AOS.selectone(Ext.getCmp('g_param'));
        	var msg =  AOS.merge('确认要删除标题【{0}】吗？', record.data.title);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
            AOS.ajax({
            	params : {
            		id: record.data.id
            	},
                url: 'ZjcSystemInterfaceService.updatestate',
                ok: function (data) {
                	Ext.getCmp("g_param").store.reload();
                }
            });
			});
        }
</script>