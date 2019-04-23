<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商品类型" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_type" url="zjcGoodsTypeService.listGoodsType" onrender="g_type_query"  forceFit="true"
		 onitemdblclick="w_type_u_show">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_type.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_type_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_type_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_type_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="#w_type.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_type_u_show" text="修改" icon="edit.png" />
				<aos:dockeditem onclick="g_type_del" text="删除" icon="del.png" />
				<aos:dockeditem xtype="tbseparator"/>
				<aos:triggerfield emptyText="类型名称" id="name" onenterkey="g_type_query"
					trigger1Cls="x-form-search-trigger" onTrigger1Click="g_type_query" width="180" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column header="类型id" dataIndex="id"  celltip="true" width="200"/>
			<aos:column header="类型名称" dataIndex="name" celltip="true" width="200"/>
			<aos:column header="查看" rendererFn="fn_button_render" align="center" fixedWidth="60" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_type" title="新增商品类型">
		<aos:formpanel id="f_type" width="300" layout="column" labelWidth="80">
			<aos:textfield name="name" fieldLabel="类型名称" allowBlank="false" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_type_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_type.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_type_u" title="修改商品类型">
		<aos:formpanel id="f_type_u" width="300" layout="column" labelWidth="80">
			<aos:hiddenfield name="id" />
			<aos:textfield name="name" fieldLabel="类型名称" allowBlank="false" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_type_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_type_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_type_grid" title="商品类型属性" minWidth="600" maxHeight="400" minHeight="400" onshow="g_type_grid_query">
		<aos:gridpanel id="g_type_grid" url="zjcGoodsAttributeService.listGoodsAttribute">
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem xtype="tbseparator"/>
				<aos:combobox fieldLabel="商品类型" labelWidth="60" id="type_id" name="type_id" editable="false" readOnly="true" url="zjcGoodsTypeService.listTypeComboBoxData" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column header="ID" dataIndex="attr_id"  celltip="true" />
			<aos:column header="属性名称" dataIndex="attr_name" celltip="true" />
			<aos:column header="属性值的输入方式" dataIndex="attr_input_type" celltip="true" rendererField="attr_input_type"/>
			<aos:column header="可选值列表" dataIndex="attr_values" celltip="true" />	
			<aos:column header="筛选" dataIndex="attr_index" celltip="true" rendererField="is"/>
			<aos:column header="排序" dataIndex="order" celltip="true" />
		</aos:gridpanel>
	</aos:window>
	<script type="text/javascript">
            //查询参数列表
            function g_type_query() {
                var params = {
               		name : name.getValue()
                };
                g_type_store.getProxy().extraParams = params;
                g_type_store.loadPage(1);
            }
            
          	//查询类型属性列表
            function g_type_grid_query() {
                var params = {
            			 type_id : type_id.getValue()
                }; 
            	g_type_grid_store.getProxy().extraParams = params;
                g_type_grid_store.loadPage(1);
            }
			//弹出修改窗口
            function w_type_u_show(){
            	 var record = AOS.selectone(g_type);
                 if (record) {
                     w_type_u.show();
                     f_type_u.loadRecord(record);
                 }
            }
            
            //商品类型新增保存
			function f_type_save(){
				 AOS.ajax({
                    forms: f_type,
                    url: 'zjcGoodsTypeService.saveGoodsType',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_type.hide();
                            g_type_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
                    		AOS.err(data.appmsg);
    						AOS.get('f_type.name').markInvalid(data.appmsg);
                    	}
                    }
                });
			}
            //修改保存
            function f_type_u_submit(){
            	AOS.ajax({
                    forms: f_type_u,
                    url: 'zjcGoodsTypeService.upDateGoodsType',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_type_u.hide();
                            g_type_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.get('f_type_u.name').markInvalid(data.appmsg);
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }
            
            //删除商品类型
            function g_type_del() {
                var selectionIds = AOS.selection(g_type, 'id');
                if (AOS.empty(selectionIds)) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var rows = AOS.rows(g_type);
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'zjcGoodsTypeService.deleteGoodsType',
                        params: {
                            aos_rows: selectionIds
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_type_store.reload();
                        }
                    });
                });

            }

         	 //按钮列转换
    		function fn_button_render(value, metaData, record, rowIndex, colIndex, store) {
    			return "<input type='button' value='查看' class='cbtn' onclick='w_type_grid_show("+record.raw.id+");' />";
    		}
        </script>
</aos:onready>

<script type="text/javascript">
	function w_type_grid_show(id){
		Ext.getCmp('type_id').store.reload();
		Ext.getCmp('type_id').setValue(id);
		Ext.getCmp('w_type_grid').show();
	}
</script>