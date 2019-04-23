<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="体现管理" base="http" lib="ext">
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/sitelogo.css" >
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body id="adbody">
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="170" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.25" />
			<aos:textfield name="nickname" fieldLabel="会员名称" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		<aos:gridpanel id="g_param" url="zjcUsersInfoService.getWithdrawalList"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="提成列表" />
			</aos:docked>
			<aos:plugins>
				<aos:editing id="id_plugin" clicksToEdit="2" onedit="fn_edit" autoCancel="false" onbeforeedit="fn_beforeedit" />
			</aos:plugins>
			<aos:column header="主键id" dataIndex="id" hidden="true" />
			<aos:column header="会员ID" dataIndex="user_id" celltip="true" />
			<aos:column header="会员名称" dataIndex="nickname" celltip="true" />
			<aos:column header="提现金额" dataIndex="cash" celltip="true" />
			<aos:column header="是否处理" dataIndex="is_withdrawal" rendererField="is_withdrawal">
				<aos:combobox  dicField="is_withdrawal" />
			</aos:column>
		</aos:gridpanel>
	</aos:viewport>
	<script type="text/javascript">

		//查询提成列表
	    function g_param_query() {
	        var params = AOS.getValue('f_query');
	        g_param_store.getProxy().extraParams = params;
	        g_param_store.loadPage(1);
	    }
		
		//触发编辑前事件
		function fn_beforeedit(obj, e) {
			if(e.record.data.is_withdrawal == 1){
				AOS.warn("该提成已处理，请勿重复处理");
				return false;
			}
			
			var editing = g_param.getPlugin('id_plugin');
			var rowEditor = editing.getEditor();
			//这行是修复行编辑的一个bug，当数据校验时候如果初始时数据不合法，则数据纠正后保存按钮也不能用的bug。
			rowEditor.on('fieldvaliditychange', rowEditor.onFieldChange,rowEditor);
	    }
		
		//监听行编辑事件
		function fn_edit(editor, e) {
			if (!e.record.dirty) {
				AOS.tip('数据没变化，提交操作取消。');
				return;
			}
			AOS.ajax({
				params : e.record.data,
				url : 'zjcUsersInfoService.updateWithdrawal',
				ok : function(data) {
					//保存成功后最好是reload表格，以刷新表格行序号
					g_param_store.reload();
					AOS.tip(data.appmsg);
					//客户端数据状态提交
					e.record.commit();
				}
			});
		}

            
   </script>
</aos:onready>