<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商品分类" base="http" lib="ext">
<aos:body>
</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:treepanel id="t_goods_category" region="west" bodyBorder="0 1 0 0" width="250" rootText="商品分类"
			singleClick="false" url="zjcGoodsCategoryService.TreeGoodsCategoty" nodeParam="parent_id" onitemclick="g_goods_category_query">
			<aos:docked forceBoder="0 1 1 0">
				<aos:dockeditem xtype="tbtext" text="商品分类树" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
			<aos:menu>
				<aos:menuitem text="新增分类" onclick="w_goods_category_show" icon="add.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="t_goods_category_refresh" icon="refresh.png" />
			</aos:menu>
		</aos:treepanel>
		<aos:gridpanel id="g_goods_category" url="zjcGoodsCategoryService.listGoodsCategoty" region="center" onitemdblclick="w_goods_category_u_show"
			onrender="g_goods_category_query" bodyBorder="1 0 1 0">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem text="新增" onclick="w_goods_category_show" icon="add.png" />
				<aos:dockeditem text="修改" onclick="w_goods_category_u_show" icon="edit.png" />
				<aos:dockeditem text="删除" onclick="g_goods_category_del" icon="del.png" />
				<aos:dockeditem xtype="tbseparator" />
				<aos:triggerfield emptyText="分类名称" id="name" onenterkey="g_goods_category_query" trigger1Cls="x-form-search-trigger"
					onTrigger1Click="g_goods_category_query" width="180" />
			</aos:docked>
			<aos:menu>
				<aos:menuitem text="新增商品分类" onclick="w_goods_category_show" icon="add.png" />
				<aos:menuitem text="修改商品分类" onclick="w_goods_category_u_show" icon="edit.png" />
				<aos:menuitem text="删除商品分类" onclick="g_goods_category_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_goods_category_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column header="分类id" dataIndex="id" />
			<aos:column header="分类名称" dataIndex="name" />
			<aos:column header="手机显示名称" dataIndex="mobile_name" />
			<aos:column header="是否推荐" dataIndex="is_hot" rendererField="is" />
			<aos:column header="是否显示" dataIndex="is_show" rendererField="is" />
			<aos:column header="分组" dataIndex="cat_group" />
			<aos:column header="排序" dataIndex="sort_order" />
				<aos:column dataIndex="commission_rate_1" hidden="true"/>
				<aos:column dataIndex="commission_rate_2" hidden="true"/>
				<aos:column dataIndex="commission_rate_3" hidden="true"/>
				<aos:column dataIndex="wallet_amplification_rate" hidden="true" />
				<aos:column dataIndex="store_rebate_rate" hidden="true"/>
		</aos:gridpanel>
	</aos:viewport>

	<aos:window id="w_goods_category" title="新增商品分类">
		<aos:formpanel id="f_goods_category" width="800" layout="column" labelWidth="100">
			<aos:hiddenfield name="parent_id" fieldLabel="上级分类id" />
			<aos:textfield columnWidth="0.49" name="parent_name" fieldLabel="上级分类名称" allowBlank="false" readOnly="true" />
			<aos:textfield columnWidth="0.49"  name="name" fieldLabel="分类名称" allowBlank="false" maxLength="50"/>
			<aos:textfield columnWidth="0.49" name="mobile_name" fieldLabel="手机分类名称" maxLength="100" />
			<aos:textfield columnWidth="0.49" name="cat_group" fieldLabel="分类分组" maxLength="50" />
			<aos:radioboxgroup columnWidth="0.49" fieldLabel="导航显示" columns="3">
				<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" checked="true"/>
				<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup columnWidth="0.49" fieldLabel="是否启用" columns="3" >
				<aos:radiobox name="is_show" boxLabel="是" inputValue="1" checked="true"/>
				<aos:radiobox name="is_show" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:numberfield columnWidth="0.99" name="sort_order" fieldLabel="排序号" value="0" minWidth="0" maxValue="99999999" />
			<%-- <aos:textfield columnWidth="0.5" name="wallet_amplification_rate" fieldLabel="钱包扩增比例" /> 
			<aos:textfield columnWidth="0.5" name="store_rebate_rate" fieldLabel="商家返利比例" />  --%>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_goods_category_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_goods_category.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_goods_category_u" title="修改商品分类">
		<aos:formpanel id="f_goods_category_u" width="800" layout="column" labelWidth="100">
			<aos:hiddenfield name="id" fieldLabel="分类Id" />
			<aos:hiddenfield name="parent_id" fieldLabel="上级分类id" />
			<aos:textfield columnWidth="0.49" name="parent_name" fieldLabel="上级分类名称" allowBlank="false" readOnly="true" />
			<aos:textfield columnWidth="0.49" name="name" fieldLabel="分类名称" allowBlank="false" maxLength="50" />
			<aos:textfield columnWidth="0.49" name="mobile_name" fieldLabel="手机分类名称" maxLength="100" />
			<aos:textfield columnWidth="0.49" name="cat_group" fieldLabel="分类分组" maxLength="50" />
			<aos:radioboxgroup columnWidth="0.49" fieldLabel="导航显示" columns="3" >
				<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup columnWidth="0.49" fieldLabel="是否启用" columns="3" >
				<aos:radiobox name="is_show" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_show" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:numberfield columnWidth="0.99" name="sort_order" fieldLabel="排序号" value="0" minWidth="0" maxValue="99999999" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_goods_category_u_update" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_goods_category_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>

	<script type="text/javascript">
	
	    //模块查询
		function g_goods_category_query() {
			var params = {
				name: name.getValue()
			};
			var record = AOS.selectone(t_goods_category);
			if(!AOS.empty(record)){
				params.id = record.raw.id;
				params.parent_id_path = record.raw.b;
			}
			g_goods_category_store.getProxy().extraParams = params;
			g_goods_category_store.loadPage(1);
		}
		
		//自动选中根节点
		AOS.job(function(){
			t_goods_category.getSelectionModel().select(t_goods_category.getRootNode());
			g_goods_category_query();
		},10);
	
		//弹出新增商品分类菜单
		function w_goods_category_show(){
			AOS.reset(f_goods_category);
			var record = AOS.selectone(t_goods_category);
			if(!AOS.empty(record)){
				AOS.setValue('f_goods_category.parent_id',record.raw.id); 
				AOS.setValue('f_goods_category.parent_name',record.raw.text); 
			} 
			w_goods_category.show();
		}
		
	    //弹出修改商品分类菜单窗口
		function w_goods_category_u_show(){
			    AOS.reset(f_goods_category_u);
				var record = AOS.selectone(g_goods_category);
				var treeRecord = AOS.selectone(t_goods_category);
				if(record){
					record.raw.is_hot +="";
					record.raw.is_show +="";
					AOS.setValue('f_goods_category_u.parent_id',treeRecord.raw.id); 
					AOS.setValue('f_goods_category_u.parent_name',treeRecord.raw.text);
	 				f_goods_category_u.loadRecord(record);
					w_goods_category_u.show();
			   }
		}
	    
		//新增商品分类保存
		function f_goods_category_save(){
				AOS.ajax({
				forms : f_goods_category,
				url : 'zjcGoodsCategoryService.saveGoodsCategoty',
				ok : function(data) {
					w_goods_category.hide();
					g_goods_category_store.reload();
					t_goods_category_refresh();
					AOS.tip(data.appmsg);	
				}
			});
		}
		
	   //修改商品分类保存
		function f_goods_category_u_update(){
				AOS.ajax({
				forms : f_goods_category_u,
				url : 'zjcGoodsCategoryService.updateGoodsCategoty',
				ok : function(data) {
					AOS.tip(data.appmsg);	
					w_goods_category_u.hide();
					t_goods_category_refresh('parent');
					g_goods_category_store.reload();
					/* if(AOS.getValue('f_goods_category_u.parent_id') == '0'){
						t_goods_category.getRootNode().set('text',AOS.getValue('f_goods_category_u.name'));
					} */
				}
			});
		}
	   
        //删除商品分类菜单
		function g_goods_category_del(){
			var rows = AOS.rows(g_goods_category);
			if(rows === 0){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var msg =  AOS.merge('确认要删除选中的[{0}]个商品分类菜单数据吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'zjcGoodsCategoryService.deleteGoodsCategoty',
					params:{
						aos_rows:  AOS.selection(g_goods_category, 'id')
					},
					ok : function(data) {
						if(data.appcode == '1'){
							AOS.tip(data.appmsg);
							g_goods_category_store.reload();
							t_goods_category_refresh('root');
						}else{
							AOS.err(data.appmsg);
						}
					}
				});
			});
			}
	    
		//刷新菜单树  flag:parent 刷新父节点；root：刷新根节点
		function t_goods_category_refresh(flag) {
			var refreshnode = AOS.selectone(t_goods_category);
			if (!refreshnode) {
				refreshnode = t_goods_category.getRootNode();
			}
			if (refreshnode.isLeaf() || (flag=='parent' && !refreshnode.isRoot())) {
				refreshnode = refreshnode.parentNode;
			}
			//参数标记为刷新根节点
			if(flag == 'root'){
				refreshnode = t_goods_category.getRootNode();
			}
			t_goods_category_store.load({
				node : refreshnode,
				callback : function() {
					//收缩再展开才能在刷新时正确显示expanded=true的节点的子节点
					refreshnode.collapse();
					refreshnode.expand();
					t_goods_category.getSelectionModel().select(refreshnode);
				}
			});
		}

	</script>
</aos:onready>
