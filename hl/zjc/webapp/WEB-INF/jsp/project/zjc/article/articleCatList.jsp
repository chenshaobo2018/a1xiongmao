<%@ page contentType="text/html;charset=utf-8"%>
<meta http-equiv=”Content-Type” content=”text/html; charset=gb2312”>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="类别列表" base="http" lib="ext">
	<aos:body>
		<div id="btnDiv" class="x-hidden">
		</div>
		<div id="findDeliDiv" class="x-hidden"></div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="cat_name" fieldLabel="分类名称" columnWidth="0.33" />
			<aos:textfield name="keywords" fieldLabel="关键字" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" icon="query.png" onclick="article_cat_query" />
				<aos:dockeditem xtype="button" text="重置" icon="refresh.png" onclick="AOS.reset(f_query);"/>
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="g_articlecat"  url="zjcArticleCatService.listLikeZjcArticleCat" onrender="article_cat_query" region="center">
			<aos:docked forceBoder="0 0 0 0">
				<aos:dockeditem text="新增" onclick="article_cat_add_show" icon="add.png" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="分类ID" dataIndex="cat_id"/>
			<aos:column header="分类名称" dataIndex="cat_name"/>
			<aos:column header="类别编号" dataIndex="cat_type"/>
			<aos:column header="上级分类" dataIndex="parent_id" />
			<aos:column header="is_show_nav" dataIndex="show_in_nav"/>
			<aos:column header="分类排序" dataIndex="sort_order"/>
			<aos:column header="描述" dataIndex="cat_desc"/>
			<aos:column header="关键字" dataIndex="keywords"/>
			<aos:column header="别名" dataIndex="cat_alias"/>
			<aos:column header="操作" type="action" align="center">
				<aos:action icon="query.png" tooltip="查看" handler="article_cat_edit"/>
				<aos:action icon="del.png" tooltip="删除" handler="article_cat_del"/>
			</aos:column>
		</aos:gridpanel>
		
		<!-- 分类修改窗口 -->
		<aos:window id="w_article_cat_info" title="修改分类"  onshow="article_cat_OnShow" >
			<aos:formpanel id="f_acticle_cat_info" width="800" layout="anchor" labelWidth="80" >
					<aos:textfield name="cat_id" fieldLabel="分类ID" readOnly="true" />
					<aos:textfield name="cat_name" fieldLabel="分类名称" readOnly="true"/>
					<aos:textfield name="cat_type" fieldLabel="类别编号" readOnly="true" width="auto"/>
					<aos:textfield name="parent_id" fieldLabel="上级分类" readOnly="true"/>
					<aos:combobox name="is_show_nav" columnWidth="0.55" fieldLabel="是否显示在导航栏" value="0">
						<aos:option value="0" display="否"/>
						<aos:option value="1" display="是"/>
					</aos:combobox>
					<aos:textfield name="cat_desc" fieldLabel="描述" readOnly="false"/>
					<aos:textfield name="sort_order" fieldLabel="分类排序" readOnly="true"/>
					<aos:textfield name="keywords"  fieldLabel="关键字"/>
					<aos:textfield name="cat_alias" fieldLabel="别名"/>
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem text="保存" icon="ok.png" onclick="w_article_cat_info_submit" />
					<aos:dockeditem text="关闭" icon="close.png" onclick="#w_article_cat_info.hide();"/>
				</aos:docked>
		</aos:window>
		
		<!-- 新增分类 -->
		<aos:window id="w_article_cat_add_show" title="添加分类">
			<aos:formpanel id="f_article_cat_add" width="800" layout="anchor" labelWidth="80" >
				<aos:textfield fieldLabel="分类名称" name="cat_name"/>
				<aos:textfield name="cat_desc" fieldLabel="描述"/>
				<aos:textfield name="keywords" fieldLabel="关键字"/>
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem text="保存" icon="ok.png" onclick="article_add_submit" />
					<aos:dockeditem text="关闭" icon="close.png" onclick="#w_article_cat_add_show.hide();"/>
				</aos:docked>
		</aos:window>
	</aos:viewport>

<script type="text/javascript">
	//加载表格数据
	function article_cat_query() {
		var params = AOS.getValue('f_query');
		g_articlecat_store.getProxy().extraParams = params;
		g_articlecat_store.loadPage(1);
	}
	
	//显示分类信息信息编辑窗口
	function article_cat_edit(){
		Ext.getCmp('w_article_cat_info').show();
	}
	
	//i、新增分类
	function article_cat_add_show(){
		Ext.getCmp('w_article_cat_add_show').show();
	}
	//跳转到分类信息信息修改界面
	function article_cat_OnShow(grid, rowIndex, colIndex){
		var record = AOS.selectone(g_articlecat);
		f_acticle_cat_info.form.reset();
        AOS.ajax({
        	params : {
        		cat_id: record.data.cat_id
        	},
            url: 'zjcArticleCatService.selectByCatId',
            ok: function (data) {
            	console.log(data.cat_id);
            	f_acticle_cat_info.form.setValues(data);
            }
        });
	}
	
	//提交数据
	function article_add_submit(){
		AOS.ajax({
            //只提交一个表单
            forms : f_article_cat_add,
            url : 'zjcArticleCatService.insertArticleCat',
            ok : function(data) {
                AOS.tip(data.appmsg);
                g_articlecat_store.reload();
                w_article_cat_add_show.hide();
            }
        });
	}
	//提交修改后的数据
	function w_article_cat_info_submit(){
		AOS.ajax({
            //只提交一个表单
            forms : f_acticle_cat_info,
            url : 'zjcArticleCatService.updateArticleCat',
            ok : function(data) {
                AOS.tip(data.appmsg);
                g_param_store.reload();
                w_zjcUser_u.hide();
            }
        });
	}
	
	//删除分类
	function article_cat_del(){
		var record = AOS.selectone(Ext.getCmp('g_articlecat'));
    	var msg =  AOS.merge('确认删除分类？');
		AOS.confirm(msg, function(btn){
			if(btn === 'cancel'){
				AOS.tip('删除操作被取消。');
				return;
			}
        AOS.ajax({
        	params : {
        		cat_id: record.data.cat_id
        	},
            url: 'zjcArticleCatService.deleteByKey',
            ok: function (data) {
            	Ext.getCmp("g_articlecat").store.reload();
            }
        });
		});
	}
</script>
</aos:onready>
