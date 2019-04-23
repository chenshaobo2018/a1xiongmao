<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商品规格" base="http" lib="ext">
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:panel layout="auto" autoScroll="true" border="false">
			<aos:panel layout="anchor" width="auto" border="false">
				<aos:formpanel id="f_query" layout="column" header="false" border="false" anchor="100%">
					<aos:docked forceBoder="1 0 1 0"> 
						<aos:dockeditem xtype="tbtext" text="查询条件" />
					</aos:docked>
					<aos:combobox fieldLabel="商品类型" id="typeId" labelWidth="60" name="type_id" editable="false" url="zjcGoodsTypeService.listTypeComboBoxData"/>
					<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
						<aos:dockeditem xtype="tbfill" />
						<aos:dockeditem xtype="button" text="查询" onclick="g_spec_query" icon="query.png" />
						<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
				</aos:formpanel>
				
				<aos:gridpanel id="g_spec" url="zjcSpecService.listSpec"  forceFit="true" autoLoad="true" 
			height="750" anchor="100%" onitemdblclick="w_spec_u_show">
					<aos:menu>
						<aos:menuitem text="新增" onclick="#w_spec.show();" icon="add.png" />
						<aos:menuitem text="修改" onclick="w_spec_u_show" icon="edit.png" />
						<aos:menuitem text="删除" onclick="g_spec_del" icon="del.png" />
						<aos:menuitem xtype="menuseparator" />
						<aos:menuitem text="刷新" onclick="#g_spec_store.reload();" icon="refresh.png" />
					</aos:menu>
					<aos:docked forceBoder="0 0 1 0" >
						<aos:dockeditem onclick="#w_spec.show();" text="新增" icon="add.png" />
						<aos:dockeditem onclick="w_spec_u_show" text="修改" icon="edit.png" />
						<aos:dockeditem onclick="g_spec_del" text="删除" icon="del.png" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="multi" />
					<aos:column header="ID" dataIndex="id"  celltip="true" />
					<aos:column header="规格类型id" dataIndex="type_id" celltip="true" hidden="true"/>
					<aos:column header="规格类型" dataIndex="type_name" celltip="true"/>
					<aos:column header="规格名称" dataIndex="name" celltip="true" />
					<aos:column header="规格项" dataIndex="specItems" celltip="true" width="400"/>
					<aos:column header="筛选" dataIndex="search_index" celltip="true" rendererField="is"/>
					<aos:column header="排序" dataIndex="order" celltip="true" />
				</aos:gridpanel>
			</aos:panel>
		</aos:panel>
		
	</aos:viewport>
	<aos:window id="w_spec" title="新增商品规格">
		<aos:formpanel id="f_spec" width="500" layout="column" labelWidth="80">
			<aos:textfield name="name" fieldLabel="规格名称" columnWidth="0.7"/>
			<aos:combobox fieldLabel="商品类型" name="type_id" editable="false" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.7"/>
			<aos:textareafield fieldLabel="规格项" name="specItems" columnWidth="0.8" height="100" />
			<aos:numberfield name="order" fieldLabel="排序" value="0" columnWidth="0.99" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_spec_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_spec.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_spec_u" title="修改商品规格">
		<aos:formpanel id="f_spec_u" width="500" layout="column" labelWidth="80">
			<aos:hiddenfield name="id" />
			<aos:textfield name="name" fieldLabel="规格名称" columnWidth="0.7"/>
			<aos:combobox fieldLabel="商品类型" id="type_id" name="type_id" editable="false" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.7"/>
			<aos:textareafield fieldLabel="规格项" name="specItems" columnWidth="0.8" height="100" />
			<aos:numberfield name="order" fieldLabel="排序" value="0" columnWidth="0.99" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_spec_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_spec_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<script type="text/javascript">
         //查询参数列表
         function g_spec_query() {
        	 var params = AOS.getValue('f_query');
        	 g_spec_store.getProxy().extraParams = params;
             g_spec_store.loadPage(1);
         }
         
         //弹出修改窗口
         function w_spec_u_show(){
         	 var record = AOS.selectone(g_spec);
              if (record) {
            	  f_spec_u.down("[name='type_id']").store.reload();
              	  f_spec_u.down("[name='type_id']").setValue(record.data.type_id);
                  w_spec_u.show();
                  f_spec_u.loadRecord(record);
              }
         }
         
         //商品类型新增保存
		 function f_spec_save(){
			 AOS.ajax({
                forms: f_spec,
                url: 'zjcSpecService.saveSpec',
                ok: function (data) {
                	if(data.appcode == '1'){
                        w_spec.hide();
                        g_spec_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
                		AOS.err(data.appmsg);
						AOS.get('f_spec.name').markInvalid(data.appmsg);
                	}
                }
            });
		 }
         //修改保存
         function f_spec_u_submit(){
         	AOS.ajax({
                 forms: f_spec_u,
                 url: 'zjcSpecService.updataSpec',
                 ok: function (data) {
                 	if(data.appcode == '1'){
                         w_spec_u.hide();
                         g_spec_store.reload();
                         AOS.tip(data.appmsg);
                 	}else{
 						AOS.get('f_spec_u.name').markInvalid(data.appmsg);
 						AOS.err(data.appmsg);
                 	}
                 }
             });
         }
         
         //删除商品类型
         function g_spec_del() {
             var selectionIds = AOS.selection(g_spec, 'id');
             if (AOS.empty(selectionIds)) {
                 AOS.tip('删除前请先选中数据。');
                 return;
             }
             var rows = AOS.rows(g_spec);
             var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
             AOS.confirm(msg, function (btn) {
                 if (btn === 'cancel') {
                     AOS.tip('删除操作被取消。');
                     return;
                 }
                 AOS.ajax({
                     url: 'zjcSpecService.deleteSpec',
                     params: {
                         aos_rows: selectionIds
                     },
                     ok: function (data) {
                         AOS.tip(data.appmsg);
                         g_spec_store.reload();
                     }
                 });
             });

         }
    </script>
</aos:onready>
