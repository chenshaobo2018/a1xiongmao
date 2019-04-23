<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<%-- <aos:html>
<aos:head title="用户项目信息">
	<aos:include lib="ext" />
	<aos:base href="/htctl" />
</aos:head>
<aos:body>
</aos:body> --%>
<aos:html title="用户项目信息" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="_f_upu_query" layout="column" labelWidth="70" header="false" region="north" border="false" >
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="username" fieldLabel="用户名" columnWidth="0.25" />
			<aos:textfield name="phone" fieldLabel="联系电话" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_upu_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(_f_upu_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="_g_upu" url="HtController.getT_userhlPOList" onrender="_g_upu_query" region="north" pageSize="10" onitemclick="_g_upp_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="用户信息" />
				<aos:dockeditem text="新增" icon="add.png" onclick="_w_addUser_show" />
				<aos:dockeditem text="修改" icon="edit.png" onclick="_w_updateUser_showupp" />
				<aos:dockeditem text="删除" icon="del.png" onclick="_g_userUpp_del" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="用户名" dataIndex="username" />
			<aos:column header="联系电话" dataIndex="phone" />
		</aos:gridpanel>
		<aos:gridpanel id="_g_upp" url="HtController.getT_user_projectVOList" onrender="_g_upp_query" region="center" pageSize="10">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="用户项目信息" />
				<aos:dockeditem xtype="tbseparator" />
				<aos:dockeditem text="新增" icon="add.png" onclick="_w_add_show" />
				<aos:dockeditem text="修改" icon="edit.png" onclick="_w_update_showupp" />
				<aos:dockeditem text="删除" icon="del.png" onclick="_g_upp_del" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="项目名称" dataIndex="project_name" />
			
		</aos:gridpanel>
		<aos:window id="_w_add_upp" title="项目详情" >
			<aos:formpanel id="_f_upp" width="300" layout="anchor" labelWidth="70" >
				<aos:hiddenfield name="id" />
				<aos:hiddenfield name="user_id" />
				<aos:hiddenfield name="project_id" />
				<aos:triggerfield name="project_name" id="project_name" fieldLabel="项目名称"
							trigger1Cls="x-form-search-trigger" onTrigger1Click="openProjectWin"  />
				
			</aos:formpanel>
			
			
			
			<aos:docked dock="bottom" ui="footer" height="30">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_add_save();" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="_w_add_upp.close();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
		
		<aos:window id="_w_addUSer_upp" title="修改用户信息" >
			<aos:formpanel id="_f_uppUser" width="300" layout="anchor" labelWidth="70" >
				<aos:hiddenfield name="id"/>
				<aos:textfield name="username" fieldLabel="姓名" columnWidth="0.5"/>
				<aos:textfield name="phone" fieldLabel="电话" columnWidth="0.5"/>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer" height="30">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_user_save();" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="_w_addUSer_upp.close();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
	
	<aos:window id="_w_userAdd_upp" title="添加用户" >
			<aos:formpanel id="_f_userUpp" width="300" layout="anchor" labelWidth="70" >
				<aos:hiddenfield name="id" />
				<aos:hiddenfield name="user_id" />
				<aos:hiddenfield name="project_id" />
				<aos:textfield name="username" id="username" fieldLabel="姓名"/>
				<aos:textfield name="phone" id="phone" fieldLabel="电话"/>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer" height="30">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_userAdd_save();" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="_w_userAdd_upp.close();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
	
		
	<aos:window id="_w_grid_project" title="项目列表" width="500" height="300">
		<aos:gridpanel id="_g_pj" url="HtController.getT_projectPOList" onrender="_g_project_query" width="490" height="270" onitemdblclick="voluationProject">
			<aos:docked forceBoder="0 0 1 0">
				<aos:textfield id="type_pj" name="project_name" fieldLabel="项目名称" columnWidth="0.25" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_project_query" icon="query.png" />
				<aos:dockeditem xtype="tbseparator" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="项目名称" dataIndex="project_name"  />
					
		</aos:gridpanel>
	</aos:window>
	</aos:viewport>
	<script type="text/javascript">
	
		//加载表格数据
		function _g_upu_query() {
			var params = AOS.getValue('_f_upu_query');
			_g_upu_store.getProxy().extraParams = params;
			_g_upu_store.loadPage(1);
		}
		function _g_upp_query() {
			var user_id = AOS.selection(_g_upu,'id');
			if(null != user_id && '' != user_id){
				user_id = user_id.substr(0,user_id.length-1);
				var params = {
					user_id : user_id
                };
				_g_upp_store.getProxy().extraParams = params;
				_g_upp_store.loadPage(1);

			}
			
		}
		
		function _f_add_save(){
			
			AOS.ajax({
				forms : _f_upp,
				url : 'HtController.saveT_user_projectPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_w_add_upp.hide();
	                	AOS.tip(data.appmsg);
	                	_g_upu_store.reload();
	                }
				}
			});
		}
		
  
		
    function _f_userAdd_save(){
			AOS.ajax({
				forms : _f_userUpp,
				url : 'HtController.saveT_userPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_w_userAdd_upp.hide();
	                	AOS.tip(data.appmsg);
	                	_g_upu_store.reload();
	                }
				}
			});
		}
    
    function _f_user_save(){
		AOS.ajax({
			forms : _f_uppUser,
			url : 'HtController.saveT_userPO',
			ok : function(data) {
				if (data.appcode === -1) {
                    AOS.err(data.appmsg);
                } else {
                	_w_add_upp.hide();
                	_w_addUSer_upp.hide();
                	AOS.tip(data.appmsg);
                	_g_upu_store.reload();
                }
			}
		});
	}

		function _w_add_show(){
			var record = AOS.selectone(_g_upu);
			if(record){
				AOS.reset(_f_upp);
				_w_add_upp.show();
				_w_add_upp.down('[name=user_id]').setValue(record.data.id);
			}
		}
		function _w_addUser_show(){
				_w_userAdd_upp.show();
		}
		
		function openProjectWin(){
			_w_grid_project.show();
		}
		function _g_project_query() {
			
			var params = {
				project_name: AOS.getValue('type_pj')
            };
			_g_pj_store.getProxy().extraParams = params;
			_g_pj_store.loadPage(1);
		}
		function voluationProject(){
			_w_grid_project.close();
			var record = AOS.selectone(_g_pj);
			_f_upp.down('[name=project_id]').setValue(record.data.id);
			_f_upp.down('[name=project_name]').setValue(record.data.project_name);
		}

		function _w_update_showupp(){
			AOS.reset(_f_upp);
			var record = AOS.selectone(_g_upp);
			if(record){
				_w_add_upp.show();
				AOS.ajax({
					url : 'HtController.getT_user_projectVOById',
					params: {"id" : record.data.id},
					ok : function(data) {
						if (data.appcode === -1) {
	                        AOS.err(data.appmsg);
	                    } else {
	                    	_f_upp.down('[name=id]').setValue(data.id);
	                    	_f_upp.down('[name=user_id]').setValue(data.user_id);
	                    	_f_upp.down('[name=project_id]').setValue(data.project_id);
	                    	_f_upp.down('[name=project_name]').setValue(data.project_name);
	                    }
					}
				});
				
			}
		}
		//修改用户数据
		function _w_updateUser_showupp(){
			AOS.reset(_f_uppUser);
			var record = AOS.selectone(_g_upu);
			if(record){
				_w_addUSer_upp.show();
				AOS.ajax({
					url : 'HtController.getT_userhlPO',
					params: {"id" : record.data.id,"username" : record.data.username,"phone" : record.data.phone},
					ok : function(data) {
						if (data.appcode === -1) {
	                        AOS.err(data.appmsg);
	                    } else {
	                    	_f_uppUser.down('[name=id]').setValue(data.id);
	                    	_f_uppUser.down('[name=username]').setValue(data.username);
	                    	_f_uppUser.down('[name=phone]').setValue(data.phone);
	                    }
					}
				});
				
			}
		}
		
		function _g_upp_del(){
			var selection = AOS.selection(_g_upp, 'id');
			if(AOS.empty(selection)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_upp);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.deleteT_user_projectPO',
					params:{
						aos_rows_: selection
					},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_upp_store.reload();
						
					}
				});
			});
		}
		
		function _g_userUpp_del(){
			var selection = AOS.selection(_g_upu, 'id');
			if(AOS.empty(selection)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_upu);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.deleteUser_projectPO',
					params:{
						aos_rows_: selection
					},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_upu_store.reload();
						
					}
				});
			});
		}
	</script>
</aos:onready>
<script type="text/javascript">
	
</script>
