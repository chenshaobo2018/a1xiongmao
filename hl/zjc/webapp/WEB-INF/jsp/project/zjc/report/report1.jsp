<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="报表①" base="http" lib="ext">
<aos:body>
</aos:body>
<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="_f_query" layout="column" labelWidth="70" header="false"  region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="card_id" fieldLabel="信用卡号" columnWidth="0.33" />
			<aos:textfield name="id_no" fieldLabel="身份证号" columnWidth="0.33"  />
			<aos:datefield name="birthday" fieldLabel="出生日期" format="Y-m-d" editable="false" columnWidth="0.33" />
			<aos:textfield name="name" fieldLabel="持卡人" columnWidth="0.33" />
			<aos:numberfield name="credit_line" fieldLabel="信用额度" columnWidth="0.33" />
			<aos:numberfield name="balance" fieldLabel="可用余额" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="_g_account" url="reportService.listAccounts" onrender="_g_account_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="信用卡账户信息" />
				<aos:dockeditem xtype="tbseparator" />
				<aos:dockeditem text="预览HTML报表" icon="icon78.png" onclick="fn_html" />
				<aos:dockeditem text="预览PDF报表" icon="icon63.png" onclick="fn_pdf" />
				<aos:dockeditem text="导出XLS报表" icon="icon70.png" onclick="fn_xls" />
				<aos:dockeditem text="导出XLSX报表" icon="icon7.png" onclick="fn_xlsx" />
				<aos:dockeditem text="导出DOCX报表" icon="icon77.png" onclick="fn_docx" />
				<aos:dockeditem text="导出PPTX报表" icon="icon88.png" onclick="fn_pptx" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="流水号" dataIndex="id" hidden="true" />
			<aos:column header="信用卡号" dataIndex="card_id" width="80" />
			<aos:column header="卡类型" dataIndex="card_type" rendererField="card_type_" width="60" />
			<aos:column header="身份证号" dataIndex="id_no" width="120" />
			<aos:column header="持卡人" dataIndex="name" width="80" />
			<aos:column header="信用额度" dataIndex="credit_line" type="number" />
			<aos:column header="可用余额" dataIndex="balance" type="number" />
			<aos:column header="性别" dataIndex="sex" rendererField="sex_" width="60" />
			<aos:column header="出生日期" dataIndex="birthday" type="date" format="Y-m-d" />
			<aos:column header="年龄" dataIndex="age" width="60" />
			<aos:column header="创建时间" dataIndex="create_time" width="160" />
			<aos:column header="持卡人住址" dataIndex="address" width="180" />
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
				url : 'reportService.fillReport',
				ok : function(data) {
					parent.parent.fnaddtab('aosReportService.html', '预览HTML报表',
							'${param.aos_module_id }_1');
				}
			});
		}

		//生成PDF报表
		function fn_pdf() {
			AOS.ajax({
				url : 'reportService.fillReport',
				ok : function(data) {
					parent.fnaddtab('aosReportService.pdf', '预览PDF报表',
							'${param.aos_module_id_ }_2');
				}
			});
		}

		//生成XLS报表
		function fn_xls() {
			AOS.ajax({
				url : 'reportService.fillReport',
				ok : function(data) {
					AOS.file('do.jhtml?router=aosReportService.xls&juid=${juid}');
				}
			});
		}

		//生成XLSX报表
		function fn_xlsx() {
			AOS.ajax({
				url : 'reportService.fillReport',
				ok : function(data) {
					AOS.file('do.jhtml?router=aosReportService.xlsx&juid=${juid}');
				}
			});
		}

		//生成DOCX报表
		function fn_docx() {
			AOS.ajax({
				url : 'reportService.fillReport',
				ok : function(data) {
					AOS.file('do.jhtml?router=aosReportService.docx&juid=${juid}');
				}
			});
		}

		//生成PPTX报表
		function fn_pptx() {
			AOS.ajax({
				url : 'reportService.fillReport',
				ok : function(data) {
					AOS.file('do.jhtml?router=aosReportService.pptx&juid=${juid}');
				}
			});
		}
		
		//打开菜单功能页面
		function fnaddtab(url, menuname, module_id, root_id) {
			if (Ext.isEmpty(url)) {
				return;
			}
			var id = "id_tab_" + module_id;
			url = url.indexOf('http://') === 0 ? url : '${cxt}/http/do.jhtml?router=' + url;
			var index = url.indexOf('?');
			//一级菜单的主页面所属的页面元素其pageid_同moduleid_。
			url = url + (index === -1 ? '?' : '&') + 'juid=${juid}' + '&aos_module_id=' + module_id;
			var tabs = Ext.getCmp('tabs');
			var tab = tabs.getComponent(id);
			var tempflag = 0;
			if (!tab) {
				var iframe = Ext.create('AOS.ux.IFrame', {
					id : id + '.iframe',
					mask : true,
					layout : 'fit',
					//这个参数仅起到将iframe组件自带的mask调节到相对居中位置的作用
					height : document.body.clientHeight - 200,
					loadMask : '${page_load_msg}'
				});
				tab = tabs.add({
					id : id,
					module_id: module_id, //供Tab与导航树逆向联动使用。
					root_id: root_id, //菜单节点所属的那个卡片标识，也是当前菜单树的根节点。供Tab与导航树逆向联动使用。
					title : '<span class="app-container-title-normal">' + menuname + '</span>',
					closable : true,
					layout : 'fit',
					items : [ iframe ]
				});
				tab.on('activate', function() {
					//防止已打开的功能页面切回时再次加载
					if (tempflag === 0) {
						iframe.load(url);
						tempflag = 1;
					}
					//切换的时候和导航树保持同步
					fn_sync_tab_tree(tab);
				});
			}
			//激活新增Tab
			tabs.setActiveTab(tab);
		}
	</script>
</aos:onready>
</aos:html>