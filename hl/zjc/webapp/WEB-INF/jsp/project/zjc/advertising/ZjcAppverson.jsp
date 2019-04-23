<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="版本列表" base="http" lib="ext">
	<aos:body>
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/cropper.min.css" >
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/sitelogo.css" >
	<script src="${cxt}/static/zjc/js/bootstrap.min.js"></script>
	<script src="${cxt}/static/js/upload/cropper.js"></script>
	<script src="${cxt}/static/js/upload/sitelogo.js"></script>
	<script src="${cxt}/static/js/upload/html2canvas.min.js"></script>
	<div id="file_upload">
			<form class="avatar-form" id="upload-form-file" enctype="multipart/form-data">
				<div class="avatar-upload">
					<button class="btn btn-danger"  type="button" onclick="$('input[id=avatarInput]').click();">选择文件</button>
					<input class="avatar-input hide" id="avatarInput" name="fileUpload" type="file">
				</div>
			</form>
		</div>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
			<aos:dockeditem onclick="#w_zjcUser_us.show();" text="新增版本信息" icon="add.png" />
			</aos:docked>
			<aos:textfield name="app_verson_name" fieldLabel="版本号名称" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		<!-- 接口列表主数据 -->
		<aos:gridpanel id="g_param" url="ZjcAppversonService.getZjcAppverson"   region="center" onrender="g_param_query"
		onitemdblclick="w_zjcUser_u_show" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="接口列表信息" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id"  fixedWidth="20" hidden="true" />
			<aos:column header="版本号名称" dataIndex="app_verson_name" celltip="true" minWidth="80"  />
			<aos:column header="md5" dataIndex="md5" celltip="true" width="80" />
			<aos:column header="是否强制升级" dataIndex="is_force" celltip="true" width="50" rendererField="is"/>
			<aos:column header="应用类型"  dataIndex="app_type" width="50" celltip="true" rendererFn="format_shippingTime"/>
			<aos:column header="服务类型"  dataIndex="type" width="50" celltip="true" rendererFn="format_type"/>
			<aos:column header="操作" rendererFn="fn_button_render" align="center" fixedWidth="200"/>
		</aos:gridpanel>
	</aos:viewport>
	<!-- 会员用户编辑 -->
	<aos:window id="w_zjcUser_u" title="修改用户"  onshow="store_OnShow" >
		<aos:formpanel id="f_zjcUser_u" width="800" layout="anchor" labelWidth="200" >
			<aos:hiddenfield name="id"  />
			<aos:textfield name="app_verson" fieldLabel="版本号" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:radioboxgroup fieldLabel="应用类型 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="app_type"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="app_type"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="服务类型 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="type"  boxLabel="用户端" inputValue="0" />
				<aos:radiobox name="type"  boxLabel="商家端" inputValue="1" />
			</aos:radioboxgroup>
			<aos:textfield name="app_verson_name"   fieldLabel="版本名称" allowBlank="false" maxLength="50" />
			<aos:textfield name="md5"  fieldLabel="md5值" allowBlank="false" maxLength="50" />
			<aos:textareafield name="verson_log"  fieldLabel="升级日志" allowBlank="false"/>
			<aos:radioboxgroup fieldLabel="是否强制升级 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="is_force"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_force"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	<aos:window id="w_zjcUser_us" title="新增版本信息"  onshow="#AOS.reset(f_ad_position);" >
		<aos:formpanel id="f_ad_position" width="800" layout="anchor" labelWidth="200" >
			<aos:hiddenfield name="id"  />
			<aos:textfield name="app_verson" fieldLabel="版本号" allowBlank="false" maxLength="50"/>
			<aos:radioboxgroup fieldLabel="应用类型 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="app_type"  boxLabel="安卓移动端" inputValue="0" />
				<aos:radiobox name="app_type"  boxLabel="苹果移动端" inputValue="1" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="服务类型 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="type"  boxLabel="用户端" inputValue="0" />
				<aos:radiobox name="type"  boxLabel="商家端" inputValue="1" />
			</aos:radioboxgroup>
			<aos:textfield name="app_verson_name"   fieldLabel="版本名称" allowBlank="false" maxLength="50" />
			<aos:textfield name="app_verson_names"   fieldLabel="上一个版本号" allowBlank="false" maxLength="50" />
			<aos:textfield name="md5"  fieldLabel="md5值" allowBlank="false" maxLength="50" />
			<aos:textareafield name="verson_log"  fieldLabel="升级日志" allowBlank="false"/>
			<aos:textfield fieldLabel="文件地址" name="download_url" columnWidth="0.55"/>
			<aos:fieldset title="上传视频"  labelWidth="70" columnWidth="0.55" contentEl="file_upload" />
			<aos:radioboxgroup fieldLabel="是否强制升级 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="is_force"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_force"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_position_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_us.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
			
		<!--查询下级代码  -->
		 <aos:window id="w_user" title="版本接口详情"  layout="border" maximized="true">
			<aos:docked forceBoder="0 0 1 0">
			<aos:dockeditem onclick="#w_zjcUser_usds.show();" text="新增系统消息" icon="add.png" />
			</aos:docked>
			<aos:gridpanel  id="g_params" url="ZjcSystemInterfaceService.getZjcSystemInterface"  forceFit="true" region="center" onrender="g_param_querys"
		onitemdblclick="UserFirstLeader" >
			<aos:column header="接口路径" dataIndex="interfacesd" celltip="true" minWidth="80"  />
			<aos:column header="接口名称" dataIndex="interface_name" celltip="true" width="80" />
			<aos:column header="版本号" dataIndex="version_number" celltip="true" width="50" />
			<aos:column header="接口描述" dataIndex="interface_note" width="50" celltip="true" />
			<aos:column header="操作" rendererFn="fn_button_rendersd" align="center" fixedWidth="200"/>
			</aos:gridpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="storeForm_submit" text="保存" icon="ok.png" />
					<aos:dockeditem onclick="#w_user.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>  
			
			<aos:window id="w_zjcUser_usds" title="新增接口信息"  onshow="#AOS.reset(f_ad_positionds);" >
				<aos:formpanel id="f_ad_positionds" width="800" layout="anchor" labelWidth="200" >
					<aos:textfield name="interfacesd" fieldLabel="接口路径" allowBlank="false" maxLength="50" />
					<aos:textfield name="interface_name"  fieldLabel="接口名称" allowBlank="false" maxLength="80" />
					<aos:textfield name="version_number"   fieldLabel="版本号" allowBlank="false" maxLength="50" />
					<aos:textareafield name="interface_note"  fieldLabel="接口描述" allowBlank="false"/>
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="f_ad_positionds_submit" text="保存" icon="ok.png" />
					<aos:dockeditem onclick="#w_zjcUser_usds.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window> 
			
			
			<!-- 会员用户编辑 -->
		<aos:window id="w_zjcUser_ud" title="修改用户"  onshow="store_OnShowsd" >
			<aos:formpanel id="f_zjcUser_ud" width="800" layout="anchor" labelWidth="200" >
				<aos:hiddenfield name="id"  />
				<aos:textfield name="interfacesd" fieldLabel="接口路径" allowBlank="false" maxLength="50" />
				<aos:textfield name="interface_name"  fieldLabel="接口名称" allowBlank="false" maxLength="80" />
				<aos:textfield name="version_number"   fieldLabel="版本号" allowBlank="false" maxLength="50" />
				<aos:textareafield name="interface_note"  fieldLabel="接口描述" allowBlank="false"/>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="f_zjcUser_ud_submit" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="#w_zjcUser_ud.hide();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window> 
	<script type="text/javascript">
		  //会员主数据按钮列转换
			function fn_button_render(value, metaData, record, rowIndex, colIndex,
					store) {
				return '<input type="button" value="编辑" class="cbtn_danger" onclick="w_zjcUser_u_show();" />' 
				      +'<input type="button" value="接口详情 " class="cbtn_danger" onclick="UserFirstLeader();" />'}
		  
			
			//新增参数保存
	        function f_ad_positionds_submit() {
	            AOS.ajax({
	                forms: f_ad_positionds,
	                url: 'ZjcSystemInterfaceService.saveZjcSystemInterface',
	                ok: function (data) {
	                	   w_zjcUser_usds.hide();
	                	   g_params_store.reload();
	                	   AOS.tip(data.appmsg);
	                }
	            });
	        }
			 
		  //跳转到商家信息修改界面
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		id: record.data.id
	            	},
	                url: 'ZjcAppversonService.getlistPage',
	                ok: function (data) {
	                	f_zjcUser_u.form.setValues(data[0]);
	                }
	            });
	        }
	        //跳转到商家信息修改界面
	        function store_OnShowsd() {
	        	var record = AOS.selectone(g_params);
	            AOS.ajax({
	            	params : {
	            		id: record.data.id
	            	},
	                url: 'ZjcSystemInterfaceService.getlistPage',
	                ok: function (data) {
	                	f_zjcUser_ud.form.setValues(data[0]);
	                }
	            });
	        }
		  
	        //下级数据查询
            function g_param_querys() {
            	var params = AOS.getValue('f_query');
                var record = AOS.selectone(Ext.getCmp('g_param'));
                params.version_number=record.data.app_verson_name;
                g_params_store.getProxy().extraParams = params;
                g_params_store.loadPage(1); 
            } 
		  
	      //格式化发货时间
			function format_shippingTime(value, metaData, record, rowIndex, colIndex,store){
				var apptype = record.data.app_type;
				if(apptype ==0){
					return '安卓客户端';
				} else {
					return '苹果客户端';
				}
			}
			
			function format_type(value, metaData, record, rowIndex, colIndex,store){
				var type = record.data.type;
				if(type ==0){
					return '用户端';
				} else {
					return '商家端';
				}
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
	                url: 'ZjcAppversonService.saveZjcAppverson',
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
	                url : 'ZjcAppversonService.updateInterface',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    g_param_store.reload();
	                    w_zjcUser_u.hide();
	                }
	            });
	        }
	        
	      $("#avatarInput").on('change', function(e) {
				//获取视频名称
				var form=document.getElementById("upload-form-file");
		        var fd =new FormData(form);
		        $.ajax({
		             url: "fileUpload.jhtml?juid=${juid}",
		             type: "POST",
		             processData: false,  // 告诉jQuery不要去处理发送的数据
		             contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
		     		 data: fd,
		             success: function(data){
		               var fileobj = JSON.parse(data);
		               $('input[name="download_url"]').val(fileobj.path)
		               console.log(fileobj);
		             }
		        });
			});  
	      
	      //接口信息修改方法
	        function f_zjcUser_ud_submit() {
	            AOS.ajax({
	                //只提交一个表单
	                forms : f_zjcUser_ud,
	                url : 'ZjcSystemInterfaceService.updateInterface',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    g_params_store.reload();
	                    w_zjcUser_ud.hide();
	                }
	            });
	        }
        </script>
</aos:onready>

<script type="text/javascript">
//会员主数据按钮列转换
function fn_button_rendersd(value, metaData, record, rowIndex, colIndex,
		store) {
	return '<input type="button" value="编辑" class="cbtn_danger" onclick="w_zjcUser_ud_show();" />' 
	       +'<input type="button" value="删除 " class="cbtn_danger" onclick="updatestate();" />'
}

		//弹出版本修改参数窗口
		function w_zjcUser_u_show() {
		    Ext.getCmp('w_zjcUser_u').show();
		} 
		
		function UserFirstLeader() {
		    Ext.getCmp('w_user').show();
		} 
		
		//弹出会员修改参数窗口
		function w_zjcUser_ud_show() {
		    Ext.getCmp('w_zjcUser_ud').show();
		} 
		 
		 //接口信息删除js 理论删除
        function updatestate() {
        	var record = AOS.selectone(Ext.getCmp('g_params'));
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
                	Ext.getCmp("g_params").store.reload();
                }
            });
			});
        }
		
</script>