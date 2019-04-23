<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<%-- <aos:html>
<aos:head title="请购统计">
	<aos:include lib="ext" />
	<aos:base href="/htctl" />
</aos:head>
<aos:body>
</aos:body> --%>
<aos:html title="请购统计" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>
<aos:onready>

	<aos:viewport layout="border">
		<aos:formpanel id="_f_qgtjorder_query" layout="column" labelWidth="70" header="false" region="north" border="false" >
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="material_name" fieldLabel="商品名称" columnWidth="0.35" />
			<aos:hiddenfield id="type_id" name="type_id" fieldLabel="类型id" />
				<aos:triggerfield name="type_name" id="type_name" fieldLabel="类型"
							trigger1Cls="x-form-search-trigger" onTrigger1Click="openTypeWin" columnWidth="0.25" />
			<aos:textfield name="brand" fieldLabel="品牌" columnWidth="0.25" />
			<aos:datetimefield name = "add_dateBegin" fieldLabel="下单时间"></aos:datetimefield>
			<aos:displayfield value="至"/>
			<aos:datetimefield name = "add_dateEnd"></aos:datetimefield>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_qgtjorder_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(_f_qgtjorder_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:displayfield value="统计:" height="20" style="color:'red'" />
				<aos:displayfield value = "共" height="20"/>
				<aos:displayfield id = "specount" height="15"/>
				<aos:displayfield value = "种规格" height="20"/>
				
				<aos:displayfield value = "合计:￥" height="20"/>
				<aos:displayfield id = "sumprice" height="15"/>
				
				<aos:displayfield value = "总数量:" height="20"/>
				<aos:displayfield id = "totalnum" height="15"/>
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="_g_qgtjorder" url="HtController.getQgorderTjList" onrender="_g_qgtjorder_query" region="center" pageSize="10" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="请购统计" />
				<aos:dockeditem text="导出" icon="icon70.png" onclick="fn_xls" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="商品名称" dataIndex="material_name" />
			<aos:column header="分类" dataIndex="type_name" />
			<aos:column header="品牌" dataIndex="brand" />
			<aos:column header="规格" dataIndex="specifications_name" />
			<aos:column header="价格" dataIndex="price" />
			<aos:column header="数量" dataIndex="number" />
			<aos:column header="请购人名称" dataIndex="username" />
			<aos:column header="项目" dataIndex="project_name" />
			<aos:column header="供应商" dataIndex="suppliername" />
			<aos:column header="下单时间" dataIndex="add_date" />
		</aos:gridpanel>
		<aos:window id="_w_grid_type" title="分类列表" width="500" height="300">
		<aos:gridpanel id="_g_tp" url="HtController.getT_typePOList" onrender="_g_type_query" width="490" height="270" onitemdblclick="voluationType">
			<aos:docked forceBoder="0 0 1 0">
				
			<aos:textfield id="type_nm" name="type_name" fieldLabel="分类名称" columnWidth="0.25" />
			<aos:dockeditem xtype="button" text="查询" onclick="_g_type_query" icon="query.png" />
			
			<aos:dockeditem xtype="tbseparator" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="分类名称" dataIndex="type_name"  />
		</aos:gridpanel>
	</aos:window>
	</aos:viewport>
	<script type="text/javascript">
	
		//加载表格数据
		function _g_qgtjorder_query() {
			var params = AOS.getValue('_f_qgtjorder_query');
			_g_qgtjorder_store.getProxy().extraParams = params;
			_g_qgtjorder_store.loadPage(1);
			
			AOS.ajax({
				url : 'HtController.getQgorderTjCount',
				params: params,
				ok : function(data) {
					if (data.appcode === -1) {
                        AOS.err(data.appmsg);
                    } else {
                        AOS.setValue("specount",data.specount);
                        AOS.setValue("totalnum",data.totalnum);
                        AOS.setValue("sumprice",data.sumprice);
                    }
				}
			});
		}
		
		
		function openTypeWin(){
			_w_grid_type.show();
		}
		function _g_type_query() {
			
			var params = {
				type_name: AOS.getValue('type_nm')
            };
			_g_tp_store.getProxy().extraParams = params;
			_g_tp_store.loadPage(1);
		}
		function voluationType(){
			_w_grid_type.close();
			var record = AOS.selectone(_g_tp);
			AOS.setValue('type_id',record.data.id);
			AOS.setValue('type_name',record.data.type_name);
		}
		
		function fn_xls() {
			AOS.tip("已导出到桌面");
			var params = AOS.getValue('_f_qgtjorder_query');
			AOS.ajax({
				url : 'HtController.fillReport',
				params : params,
				ok : function(data) {
					
				}
			});
		}
	</script>
</aos:onready>
<script type="text/javascript">

</script>
