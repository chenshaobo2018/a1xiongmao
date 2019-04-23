<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="委托收款列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
<aos:viewport layout="border">  
	<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="商家ID" columnWidth="0.25" />
			<aos:textfield name="store_name" fieldLabel="店铺名称"  columnWidth="0.24" />
			<aos:textfield name="real_name" fieldLabel="真实姓名" columnWidth="0.25" />
			<aos:combobox name="state" fieldLabel="申请状态" emptyText="申请状态" dicField="state" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="store_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="g_store" url="zjcStoreService.listDepute" onrender="store_query" region="center" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="委托申请信息" />
			</aos:docked>
			<aos:column type="rowno" />
				<aos:column header="商家ID" dataIndex="user_id"/>
				<aos:column header="店铺ID" dataIndex="store_id" hidden="true"/>
				<aos:column header="店铺名称" dataIndex="store_name" celltip="true"/>
				<aos:column header="真实姓名" dataIndex="real_name" celltip="true"/>
				<aos:column header="身份证号" dataIndex="id_card" celltip="true"/>
				<%-- <aos:column header="店铺编号" dataIndex="store_id" width="20"/> --%>
				<aos:column header="收款姓名" dataIndex="account_name" celltip="true"/>
				<aos:column header="开户行" dataIndex="bank" celltip="true"/>
				<aos:column header="收款账号" dataIndex="account_mumber" celltip="true"/>
				<aos:column header="申请时间" dataIndex="add_time" celltip="true"/>
				<aos:column header="申请状态" dataIndex="state" rendererField="state"/>
				<aos:column header="操作" type="action" align="center">
					<aos:action icon="query.png" tooltip="查看" handler="changeState"/>
				</aos:column>
		</aos:gridpanel> 
		
		<aos:window id="w_article_add" title="申请详情" onshow="getData()">
			<aos:formpanel id="f_article_add_info" width="800" layout="anchor" labelWidth="120" >
				<aos:textfield name="user_id" fieldLabel="商家ID" readOnly="true"/>
				<aos:hiddenfield name="store_id" fieldLabel="店铺ID"/>
				<aos:textfield name="store_name" fieldLabel="店铺名称" readOnly="true"/>
				<aos:textfield name="real_name" fieldLabel="真实姓名" readOnly="true"/>
				<aos:textfield name="id_card" fieldLabel="身份证号" readOnly="true"/>
				<%-- <aos:textfield name="contract_mobile" fieldLabel="申请人电话" readOnly="true"/> --%>
				<%-- <aos:textfield name="store_id" fieldLabel="店铺编号" readOnly="true"/> --%>
				<aos:textfield name="account_name" fieldLabel="收款姓名" readOnly="true"/>
				<aos:textfield name="bank" fieldLabel="开户行" readOnly="true"/>
				<aos:textfield name="account_mumber" fieldLabel="收款账号" readOnly="true"/>
				<aos:combobox fieldLabel="审核结果" name="state" id="state" value="3" columnWidth="0.55">
					<aos:option value="3" display="审核通过" />
					<aos:option value="2" display="审核失败" />
				</aos:combobox>
				<aos:textareafield fieldLabel="审核失败原因" name="note" id="note" height="300"/>
			</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem text="保存" icon="ok.png" onclick="w_article_add_submit" />
					<aos:dockeditem text="关闭" icon="close.png" onclick="#w_article_add.hide();"/>
				</aos:docked>
		</aos:window>
	
</aos:viewport>	
<script type="text/javascript">

//加载表格数据
function store_query() {
	var params = AOS.getValue('f_query');
	g_store_store.getProxy().extraParams = params;
	g_store_store.loadPage(1);
}

function changeState() {
	Ext.getCmp('w_article_add').show();
}

function getData(grid, rowIndex, colIndex){
	var record = AOS.selectone(g_store);
	f_article_add_info.form.reset();
    AOS.ajax({
    	params : {
    		user_id: record.data.user_id,
    		store_id:record.data.store_id
    	},
        url: 'zjcStoreService.deputeDetial',
        ok: function (data) {
        	f_article_add_info.form.setValues(data);
        }
    });
}

//提交修改后的数据
function w_article_add_submit(){
	var state = Ext.getCmp("state").getValue();
	if(state == '2'){
		var note = Ext.getCmp("note").getValue();
		if(note == "" || note.match(/^\s*$/)){
			AOS.tip("审核失败必须填写原因！");
			return;
		}
	}
	AOS.ajax({
        //只提交一个表单
        forms : f_article_add_info,
        url : 'zjcStoreService.updateDepute',
        ok : function(data) {
        	w_article_add.hide();
            AOS.tip(data.appmsg);
            g_store_store.reload();
        }
    });
}
</script>
</aos:onready>