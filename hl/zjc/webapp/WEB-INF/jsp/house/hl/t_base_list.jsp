<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<%-- <aos:html>
<aos:head title="分类列表">
	<aos:include lib="ext" />
	<aos:base href="htctl" />
</aos:head>
<aos:body>
</aos:body> --%>
<aos:html title="分类列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="border">
		

		<aos:tabpanel id="_tabpanel" region="center" activeTab="0" bodyBorder="0 0 0 0" tabBarHeight="30">

			<aos:tab title="分类列表" id="_tab_type">
				<aos:gridpanel id="_g_type" url="HtController.getT_typePOList" onrender="_g_type_query">
					<aos:docked forceBoder="0 0 1 0">
						
						<aos:textfield id="type_name" name="type_name" fieldLabel="分类名称" columnWidth="0.25" />
						<aos:dockeditem xtype="button" text="查询" onclick="_g_type_query" icon="query.png" />
						<aos:dockeditem text="新增" onclick="_w_add_showType();" icon="add.png" />
						<aos:dockeditem text="修改" onclick="_w_update_showType();" icon="edit.png" />
						<aos:dockeditem text="删除" onclick="_g_type_del();" icon="del.png" />
						<aos:dockeditem xtype="tbseparator" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="multi" />
					<aos:column type="rowno" />
					<aos:column header="id" dataIndex="id" hidden="true" />
					<aos:column header="分类名称" dataIndex="type_name"  />
				</aos:gridpanel>
			</aos:tab>

			<aos:tab title="项目列表" id="_tab_project">
				<aos:gridpanel id="_g_project" url="HtController.getT_projectPOList" onrender="_g_project_query" border="false">
					<aos:docked forceBoder="0 0 1 0">
						
						<aos:textfield id="project_name" name="project_name" fieldLabel="分类名称" columnWidth="0.25" />
						<aos:dockeditem xtype="button" text="查询" onclick="_g_project_query" icon="query.png" />
						<aos:dockeditem text="新增" onclick="_w_add_showproject();" icon="add.png" />
						<aos:dockeditem text="修改" onclick="_w_update_showproject();" icon="edit.png" />
						<aos:dockeditem text="删除" onclick="_g_project_del();" icon="del.png" />
						<aos:dockeditem xtype="tbseparator" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="multi" />
					<aos:column type="rowno" />
					<aos:column header="id" dataIndex="id" hidden="true" />
					<aos:column header="项目名称" dataIndex="project_name" />
					
				</aos:gridpanel>
			</aos:tab>

			<%-- <aos:tab title="供应商列表" id="_tab_supplier" layout="border">
				<aos:gridpanel id="_g_supplier" region="center" url="HtController.getT_supplierPOList" onrender="_g_supplier_query"
					onitemclick="fn_g_supplier_click">
					<aos:docked forceBoder="0 0 1 0">
						<aos:textfield id="supplier_name" name="supplier_name" fieldLabel="供应商名称" columnWidth="0.25" />
						<aos:dockeditem xtype="button" text="查询" onclick="_g_supplier_query" icon="query.png" />
						<aos:dockeditem text="新增" onclick="_w_add_showsupplier();" icon="add.png" />
						<aos:dockeditem text="修改" onclick="_w_update_showsupplier();" icon="edit.png" />
						<aos:dockeditem text="删除" onclick="_g_supplier_del();" icon="del.png" />
						<aos:dockeditem xtype="tbseparator" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="multi" />
					<aos:column type="rowno" />
					<aos:column header="id" dataIndex="id" hidden="true" />
					<aos:column header="供应商名称" dataIndex="supplier_name" width="90" />
				</aos:gridpanel>
				
			</aos:tab> --%>

			

		</aos:tabpanel>

		

	</aos:viewport>
	<aos:window id="_w_add_Type" title="分类详情" >
		<aos:formpanel id="_f_type" width="400" layout="column" labelWidth="70" columnWidth="1">
			<aos:hiddenfield id="type_zj" name="id" />
			<aos:textfield name="type_name" fieldLabel="分类名称" columnWidth="1" allowBlank="false"/>
			
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer" height="30">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="_f_add_saveType();" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#_w_add_Type.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="_w_add_project" title="项目详情" >
		<aos:formpanel id="_f_project" width="400" layout="column" labelWidth="70" columnWidth="1">
			<aos:hiddenfield id="project_zj" name="id" />
			<aos:textfield name="project_name" fieldLabel="项目名称" columnWidth="1" allowBlank="false"/>
			
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer" height="30">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="_f_add_saveproject();" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#_w_add_project.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<%-- <aos:window id="_w_add_supplier" title="供应商详情" >
		<aos:formpanel id="_f_supplier" width="400" layout="column" labelWidth="80" columnWidth="1">
			<aos:hiddenfield id="supplier_zj" name="id" />
			<aos:textfield name="supplier_name" fieldLabel="供应商名称" columnWidth="1" allowBlank="false"/>
			
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer" height="30">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="_f_add_savesupplier();" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#_w_add_supplier.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> --%>
	<script type="text/javascript">
		
		function _g_type_query() {
			if(!_tab_type.isVisible()){
				_tabpanel.setActiveTab(_tab_type);
			}
			var params = {
				type_name: AOS.getValue('type_name')
            };
			_g_type_store.getProxy().extraParams = params;
			_g_type_store.loadPage(1);
		}
		
		
		
		function _g_project_query(){
			var params = {
				project_name: AOS.getValue('project_name')
           	};
            _g_project_store.getProxy().extraParams = params;
            _g_project_store.loadPage(1);			
		}
		
		
		function _g_supplier_query() {
			var params = {
				supplier_name: AOS.getValue('supplier_name')
           	};
			_g_supplier_store.getProxy().extraParams = params;
			_g_supplier_store.loadPage(1);
		}
		
		//表格单击事件
		function fn_g_supplier_click(obj, record) {
			_f_info.loadRecord(record);
		}
		
		
		function _w_add_showType(){
			AOS.reset(_f_type);
			_w_add_Type.show();
		}
		function _w_add_showproject(){
			AOS.reset(_f_project);
			_w_add_project.show();
		}
		function _w_add_showsupplier(){
			AOS.reset(_f_supplier);
			_w_add_supplier.show();
		}
		function _w_update_showType(){
			AOS.reset(_f_type);
			var record = AOS.selectone(_g_type);
			if(record){
				_w_add_Type.show();
				AOS.ajax({
					url : 'HtController.getT_typePOById',
					params: {"typeZj" : record.data.id},
					ok : function(data) {
						if (data.appcode === -1) {
	                        AOS.err(data.appmsg);
	                    } else {
	                    	_f_type.down('[id=type_zj]').setValue(data.id);
	    					_f_type.down('[name=type_name]').setValue(data.type_name); 
	                    }
					}
				});
				
			}
		}
		
		function _w_update_showproject(){
			AOS.reset(_f_project);
			var record = AOS.selectone(_g_project);
			if(record){
				_w_add_project.show();
				AOS.ajax({
					url : 'HtController.getT_projectPOById',
					params: {"projectZj" : record.data.id},
					ok : function(data) {
						if (data.appcode === -1) {
	                        AOS.err(data.appmsg);
	                    } else {
	                    	_f_project.down('[id=project_zj]').setValue(data.id);
	    					_f_project.down('[name=project_name]').setValue(data.project_name); 
	                    }
					}
				});
				
			}
		}
		function _w_update_showsupplier(){
			AOS.reset(_f_supplier);
			var record = AOS.selectone(_g_supplier);
			if(record){
				_w_add_supplier.show();
				AOS.ajax({
					url : 'HtController.getT_supplierPOById',
					params: {"supplierZj" : record.data.id},
					ok : function(data) {
						if (data.appcode === -1) {
	                        AOS.err(data.appmsg);
	                    } else {
	                    	_f_supplier.down('[id=supplier_zj]').setValue(data.id);
	    					_f_supplier.down('[name=supplier_name]').setValue(data.supplier_name); 
	                    }
					}
				});
				
			}
		}
		function _f_add_saveType(){
			AOS.ajax({
			forms : _f_type,
			url : 'HtController.saveT_typePO',
			ok : function(data) {
				if (data.appcode === -1) {
                    AOS.err(data.appmsg);
                } else {
                	_w_add_Type.hide();
                	AOS.tip(data.appmsg);
                	_g_type_store.reload();
                }
			}
			});
		}
		function _f_add_saveproject(){
			AOS.ajax({
			forms : _f_project,
			url : 'HtController.saveT_projectPO',
			ok : function(data) {
				if (data.appcode === -1) {
                    AOS.err(data.appmsg);
                } else {
                	_w_add_project.hide();
                	AOS.tip(data.appmsg);
                	_g_project_store.reload();
                }
			}
			});
		}
		function _f_add_savesupplier(){
			AOS.ajax({
			forms : _f_supplier,
			url : 'HtController.saveT_supplierPO',
			ok : function(data) {
				if (data.appcode === -1) {
                    AOS.err(data.appmsg);
                } else {
                	_w_add_supplier.hide();
                	AOS.tip(data.appmsg);
                	_g_supplier_store.reload();
                }
			}
			});
		}
		function _g_type_del(){
			var record = AOS.selectone(_g_type);
			if(AOS.empty(record)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_type);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.deleteT_typePO',
					params: {"id" : record.data.id},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_type_store.reload();
						
					}
				});
			});
		}
		function _g_project_del(){
			var record = AOS.selectone(_g_project);
			if(AOS.empty(record)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_project);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.deleteT_projectPO',
					params:{
						id: record.data.id
					},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_project_store.reload();
						
					}
				});
			});
		}
		function _g_supplier_del(){
			var selection = AOS.selection(_g_supplier, 'id');
			if(AOS.empty(selection)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_supplier);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.deleteT_supplierPO',
					params:{
						aos_rows_: selection
					},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_supplier_store.reload();
						
					}
				});
			});
		}
	</script>
</aos:onready>
