<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<%-- <aos:html>
<aos:head title="请购记录">
	<aos:include lib="ext" />
	<aos:base href="/htctl" />
</aos:head>
<aos:body>
</aos:body> --%>
<aos:html title="请购记录" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>
<aos:onready>

	<aos:viewport layout="border">
		<aos:formpanel id="_f_qgorder_query" layout="column" labelWidth="70" header="false" region="north" border="false" >
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="username" fieldLabel="账号" columnWidth="0.35" />
			<aos:textfield name = "is_refer" fieldLabel="状态" columnWidth="0.35" ></aos:textfield>
			<aos:datetimefield name = "add_dateBegin" fieldLabel="下单时间"></aos:datetimefield>
			<aos:displayfield value="至"/>
			<aos:datetimefield name = "add_dateEnd"></aos:datetimefield>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_qgorder_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(_f_qgorder_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
			<aos:docked dock="bottom" ui="footer" margin="0 0 0 700">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem text="详情" onclick="_w_detail_show();" icon="edit.png" />
				<aos:dockeditem text="确认" onclick="saveChangeQgorderSure();" icon="ok.png" />
				<aos:dockeditem text="删除" onclick="_g_qgorder_del();" icon="del.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="_g_qgorder" url="HtController.getQgorderList" onrender="_g_qgorder_query" region="center" pageSize="10" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="请购记录信息" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="账号" dataIndex="username" />
			<aos:column header="请购总价" dataIndex="sum_price" />
			<aos:column header="调整总价" dataIndex="adjust_price" />
			<aos:column header="项目" dataIndex="project_name" />
			<aos:column header="供应商" dataIndex="user_name" />
			<aos:column header="下单时间" dataIndex="add_date" />
			<aos:column header="状态" dataIndex="is_refer" />
			<aos:column header="备注" dataIndex="note" />
		</aos:gridpanel>
		
		<aos:window id="_w_add_qgorderdetail" title="详情" width="800" height="300">
			<aos:hiddenfield id = "order_id"></aos:hiddenfield>
			<aos:hiddenfield id = "project_name"></aos:hiddenfield>
			<aos:hiddenfield id = "xdsj"></aos:hiddenfield>
			<aos:hiddenfield id = "zhanghao"></aos:hiddenfield>
			<aos:hiddenfield id = "detailjsonstr"></aos:hiddenfield>
			<aos:gridpanel id="_g_qgorderdetail" url="HtController.qgorder_detailedByOrderId" region="center" pageSize="30" width="800" height="300">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="请购记录信息" />
				
			</aos:docked>
			<aos:plugins>
				<aos:editing ptype="cellediting" autoCancel="false" />
			</aos:plugins>
			
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="order_id" dataIndex="order_id" hidden="true" />
			<aos:column header="名称" dataIndex="materialname" />
			<aos:column header="分类" dataIndex="type_name" />
			<aos:column header="品牌" dataIndex="brand" />
			<aos:column header="规格" dataIndex="specifications_name" />
			<aos:column header="规格id" dataIndex="specifications_id" hidden="true"/>
			<aos:column header="数量" dataIndex="number" />
			<aos:column header="商品备注" dataIndex="note" />
			<aos:column header="请购单价" dataIndex="sum_price" />
			<aos:column header="调整单价" dataIndex="adjust_price" >
				<aos:numberfield allowDecimals="false"></aos:numberfield>
			</aos:column>
		</aos:gridpanel>
			<aos:docked dock="bottom" ui="footer" height="30">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_add_saveChangeQgorderPrice();" text="保存" icon="ok.png" />
				<aos:dockeditem text="导出" icon="icon70.png" onclick="fn_xls" />
				<aos:dockeditem onclick="_w_add_qgorderdetail.close();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
	
	
		
	
	</aos:viewport>
	<script type="text/javascript">
	function fn_xls() {
		var order_id = AOS.getValue("order_id");
		var project_name = AOS.getValue("project_name");
		var xdsj = AOS.getValue("xdsj");
		var zhanghao = AOS.getValue("zhanghao");
		AOS.tip("已导出到桌面");
		AOS.ajax({
			url : 'fillReportQgDetail.jhtml',
			params : {"order_id" : order_id,"project_name" : project_name,"xdsj" : xdsj,
				"zhanghao" : zhanghao},
			ok : function(data) {
				
			}
		});
	}
		//加载表格数据
		function _g_qgorder_query() {
			var params = AOS.getValue('_f_qgorder_query');
			_g_qgorder_store.getProxy().extraParams = params;
			_g_qgorder_store.loadPage(1);
		}
		
		function _f_add_saveChangeQgorderPrice(){
			var aos_rows_ = AOS.ars(_g_qgorderdetail);
			var detailjsonstr = '';
			if(null != aos_rows_ && '[]' != aos_rows_){
				detailjsonstr = aos_rows_;
			}
			var order_id = AOS.getValue('order_id');
			AOS.ajax({
				url : 'HtController.saveChangeQgorderPrice',
				params : {"detailjsonstr" : detailjsonstr,order_id : order_id},
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_w_add_qgorderdetail.hide();
	                	AOS.tip(data.appmsg);
	                	_g_qgorder_store.reload();
	                }
				}
			});
		}

		function saveChangeQgorderSure(){
			var selection = AOS.selection(_g_qgorder, 'id');
			console.log(selection);
			if(AOS.empty(selection)){
				AOS.tip('请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_qgorder);
			var msg =  AOS.merge('确认要确认选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.saveChangeQgorderSure',
					params:{
						aos_rows_: selection
					},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_qgorder_store.reload();
						
					}
				});
			});
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

		function _w_detail_show(){
			var record = AOS.selectone(_g_qgorder);
			console.log(record);
			if(record){
				_w_add_qgorderdetail.show();
				/* AOS.setValue('order_id',record.data.id);
				AOS.setValue('project_name',record.data.project_name);
				AOS.setValue('xdsj',record.data.add_date);
				AOS.setValue('zhanghao',record.data.username); */
				var params = {
       				order_id: record.data.id
                };
              	_g_qgorderdetail_store.getProxy().extraParams = params;
              	_g_qgorderdetail_store.loadPage(1);
				
			}
			
		}
		function _g_qgorder_del(){
			var selection = AOS.selection(_g_qgorder, 'id');
			if(AOS.empty(selection)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_qgorder);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.saveQgorder',
					params:{
						aos_rows_: selection
					},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_qgorder_store.reload();
						
					}
				});
			});
		}
		
		
		
	</script>
</aos:onready>
<script type="text/javascript">

</script>
