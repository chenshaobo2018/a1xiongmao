<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="报表Demo" base="http" lib="ext">
<aos:body>
</aos:body>
<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="_f_query" layout="column" labelWidth="70" header="false"  region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="用户ID" columnWidth="0.33" />
			<aos:textfield name="time" fieldLabel="操作时间" columnWidth="0.33"   />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="_g_account" url="reportService.listLogs" onrender="_g_account_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="用户操作日志表" />
				<aos:dockeditem xtype="tbseparator" />
				<aos:dockeditem text="预览HTML报表" icon="icon78.png" onclick="fn_html" />
				<aos:dockeditem text="预览PDF报表" icon="icon63.png" onclick="fn_pdf" />
				<aos:dockeditem text="导出XLS报表" icon="icon70.png" onclick="fn_xls" />
				<aos:dockeditem text="导出XLSX报表" icon="icon7.png" onclick="fn_xlsx" />
				<aos:dockeditem text="导出DOCX报表" icon="icon77.png" onclick="fn_docx" />
				<aos:dockeditem text="导出PPTX报表" icon="icon88.png" onclick="fn_pptx" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="用户ID" dataIndex="user_id"/>
			<aos:column header="操作时间" dataIndex="time" width="80" />
			<aos:column header="操作类型" dataIndex="type" />
			<aos:column header="操作描述" dataIndex="descs" width="120" />
		</aos:gridpanel>
	</aos:viewport>
	<script type="text/javascript">
		//加载表格数据
		function _g_account_query() {
			_g_account_store.loadPage(1);
		}
		
		//生成HTML报表
		function fn_html() {
			AOS.ajax({
				url : 'reportService.fillReportDemo',
				ok : function(data) {
					parent.parent.fnaddtab('aosReportService.html', '预览HTML报表',
							'${param.aos_module_id }_1');
				}
			});
		}
	</script>
</aos:onready>
</aos:html>