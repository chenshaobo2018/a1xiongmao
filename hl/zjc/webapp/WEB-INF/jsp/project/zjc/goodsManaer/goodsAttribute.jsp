<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商品属性" base="http" lib="ext">
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_attribute" url="zjcGoodsAttributeService.listGoodsAttribute" onrender="g_attribute_query"  forceFit="true"
		onitemdblclick="w_attribute_u_show" >
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_attribute.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_attribute_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_attribute_del" icon="del.png" /> 
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_attribute_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" id="serchReload">
				<aos:dockeditem onclick="#w_attribute.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_attribute_u_show" text="修改" icon="edit.png" />
				<aos:dockeditem onclick="g_attribute_del" text="删除" icon="del.png" />
				<aos:dockeditem xtype="tbseparator"/>
				<aos:combobox id="type_id" fieldLabel="商品类型" labelWidth="60" url="zjcGoodsTypeService.listTypeComboBoxData" />
				<aos:button text="查询" margin="0 0 0 10" icon="query.png" onclick="g_attribute_query" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column header="ID" dataIndex="attr_id"  celltip="true" />
			<aos:column header="规格id" dataIndex="type_id" celltip="true" hidden="true" />
			<aos:column header="规格类型" dataIndex="name" celltip="true" />
			<aos:column header="属性名称" dataIndex="attr_name" celltip="true" />
			<aos:column header="属性值的输入方式" dataIndex="attr_input_type" celltip="true" rendererField="attr_input_type"/>
			<aos:column header="可选值列表" dataIndex="attr_values" celltip="true" />	
			<aos:column header="筛选" dataIndex="attr_index" celltip="true" rendererField="is"/>
			<aos:column header="排序" dataIndex="order" celltip="true" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_attribute" title="新增商品规格">
		<aos:formpanel id="f_attribute" width="400" layout="column" labelWidth="60">
			<aos:textfield name="attr_name" fieldLabel="规格名称" allowBlank="false" columnWidth="0.99" />
			<aos:combobox name="type_id" fieldLabel="商品类型" allowBlank="false" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.99" />
			<aos:textareafield name="attr_values" fieldLabel="规格项" allowBlank="false" columnWidth="0.99" />
			<aos:numberfield name="order" fieldLabel="排序" value="0" columnWidth="0.99" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_attribute_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_attribute.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_attribute_u" title="修改商品规格">
		<aos:formpanel id="f_attribute_u" width="400" layout="column" labelWidth="60">
			<aos:hiddenfield name="attr_id" />
			<aos:textfield name="attr_name" fieldLabel="规格名称" allowBlank="false" columnWidth="0.99" />
			<aos:combobox name="type_id" fieldLabel="商品类型" allowBlank="false" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.99" />
			<aos:textareafield name="attr_values" fieldLabel="规格项" allowBlank="false" columnWidth="0.99" />
			<aos:numberfield name="order" fieldLabel="排序" value="0" columnWidth="0.99" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_attribute_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_attribute_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<script type="text/javascript">
            //查询参数列表
            function g_attribute_query() {
            	if(type_id.getValue() != null){
	                var params = {
	               		type_id : type_id.getValue()
	                };
	                g_attribute_store.getProxy().extraParams = params; 
            	}
                g_attribute_store.loadPage(1);
            }
            
            function f_attribute_save(){
            	AOS.ajax({
                    forms: f_attribute,
                    url: 'zjcGoodsAttributeService.saveGoodsAttribute',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_attribute.hide();
                            g_attribute_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
                    		AOS.err(data.appmsg);
    						AOS.get('f_attribute.attr_name').markInvalid(data.appmsg);
                    	}
                    }
                });
            }
            
         	 //弹出修改窗口
            function w_attribute_u_show(){
            	 var record = AOS.selectone(g_attribute);
                 if (record) {
                	 f_attribute_u.down("[name='type_id']").store.reload();
                 	 f_attribute_u.down("[name='type_id']").setValue(record.data.type_id);
                     w_attribute_u.show();
                     f_attribute_u.loadRecord(record);
                 }
            }
         	//修改提交
            function f_attribute_u_submit(){
            	AOS.ajax({
                    forms: f_attribute_u,
                    url: 'zjcGoodsAttributeService.upDateGoodsAttribute',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_attribute_u.hide();
                            g_attribute_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.get('f_attribute_u.attr_name').markInvalid(data.appmsg);
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }
         	
         	function g_attribute_del(){
         		var selectionIds = AOS.selection(g_attribute, 'attr_id');
                if (AOS.empty(selectionIds)) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var rows = AOS.rows(g_attribute);
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'zjcGoodsAttributeService.deleteGoodsAttribute',
                        params: {
                            aos_rows: selectionIds
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_attribute_store.reload();
                        }
                    });
                });
         	}
    </script>
</aos:onready>
