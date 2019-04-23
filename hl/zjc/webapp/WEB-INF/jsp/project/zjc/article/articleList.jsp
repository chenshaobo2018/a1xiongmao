<%@ page contentType="text/html;charset=utf-8"%>
<meta http-equiv=”Content-Type” content=”text/html; charset=gb2312”>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="文章列表" base="http" lib="ext">
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
    <!-- UEditer -->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/lang/zh-cn/zh-cn.js"></script>
	<aos:body>
		<div id="btnDiv" class="x-hidden">
		</div>
		<div id="findDeliDiv" class="x-hidden"></div>
		<!-- UEditer -->
		<div id="div_add_ueditor" class="x-hidden">
			<script id="add_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
		<div id="div_update_ueditor" class="x-hidden">
			<script id="update_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="title" fieldLabel="文章名称" columnWidth="0.33" />
			<aos:textfield name="content" fieldLabel="内容" columnWidth="0.33" />
			<aos:textfield name="cat_name" fieldLabel="分类" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" icon="query.png" onclick="article_query" />
				<aos:dockeditem xtype="button" text="重置" icon="refresh.png" onclick="AOS.reset(f_query);"/>
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="g_article"  url="zjcArticleService.listLikeZjcArticle" onrender="article_query" region="center">
			<aos:docked forceBoder="1 0 0 0">
				<aos:dockeditem text="新增" onclick="w_article_show" icon="add.png" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="文章ID" dataIndex="article_id"/>
			<aos:column header="分类名称" dataIndex="cat_name"/>
			<aos:column header="标题" dataIndex="title"/>
			<%-- <aos:column header="作者" dataIndex="author" width="40"/>
			<aos:column header="作者邮箱" dataIndex="author_email"/> --%>
			<aos:column header="描述" dataIndex="description"/>
			<aos:column header="添加时间" dataIndex="add_time"/>
			<aos:column header="发布时间" dataIndex="publish_time"/>
			<aos:column header="是否开启" dataIndex="is_open" rendererField="is" width="50"/>
			<aos:column header="操作" type="action" align="center">
				<aos:action icon="query.png" tooltip="查看" handler="article_edit"/>
				<aos:action icon="del.png" tooltip="删除" handler="article_del"/>
			</aos:column>
		</aos:gridpanel>
		
		<!-- 文章修改窗口 -->
		<aos:window id="w_article_info" title="修改文章" onshow="article_OnShow">
			<aos:formpanel id="f_acticle_info" width="1100" height="650" layout="anchor" labelWidth="80" >
				<aos:textfield name="article_id" fieldLabel="文章ID" readOnly="true"/>
				<aos:textfield name="cat_id" fieldLabel="分类ID" readOnly="true"/>
				<aos:textfield name="title" fieldLabel="标题" allowBlank="false"/>
				<%-- <aos:textfield name="author" fieldLabel="作者" />
				<aos:textfield name="author_email"  fieldLabel="作者邮箱" maxLength="50" /> --%>
				<aos:radioboxgroup fieldLabel="是否显示" columns="4" columnWidth="0.33">
					<aos:radiobox name="is_open" boxLabel="是" inputValue="1" />
					<aos:radiobox name="is_open" boxLabel="否" inputValue="0" />
				</aos:radioboxgroup>
				<%-- <aos:textfield name="is_open"  fieldLabel="是否开启" maxLength="50" allowBlank="false"/> --%>
				<aos:fieldset title="文章内容" labelWidth="120" columnWidth="1"  contentEl="div_update_ueditor" collapsible="false" border="false"></aos:fieldset>
				<aos:hiddenfield name="content"/>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem text="保存" icon="ok.png" onclick="w_article_info_submit" />
				<aos:dockeditem text="关闭" icon="close.png" onclick="#w_article_info.hide();"/>
			</aos:docked>
		</aos:window>
		
		<!-- 文章增加窗口 -->
		<aos:window id="w_article_add" title="新增文章" onshow="addOnshow" >
			<aos:formpanel id="f_article_add_info" width="1100" height="650" layout="anchor" labelWidth="80" >
				<aos:textfield name="title" fieldLabel="标题" allowBlank="false" width="500"/>
				<aos:combobox fieldLabel="分类" name="cat_id" width="500" url="zjcArticleService.getTypes" allowBlank="false">
					<aos:option value="" display=""/>
				</aos:combobox> 
				<aos:textfield name="description" fieldLabel="摘要" width="500" allowBlank="false"/>
				<%-- <aos:textfield name="link" fieldLabel="外部链接" width="500"/> --%>
				<aos:datetimefield name="publish_time" fieldLabel="发布日期" width="500" editable="false" allowBlank="false"/>
				<aos:radioboxgroup fieldLabel="是否显示" columns="4" width="500" >
					<aos:radiobox name="is_open" boxLabel="是" inputValue="1" />
					<aos:radiobox name="is_open" boxLabel="否" inputValue="0" />
				</aos:radioboxgroup>
				<aos:fieldset title="文章内容" labelWidth="120" columnWidth="1" contentEl="div_add_ueditor" collapsible="false" border="false" ></aos:fieldset>
				<aos:hiddenfield name="content"/>
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
	function article_query() {
		var params = AOS.getValue('f_query');
		g_article_store.getProxy().extraParams = params;
		g_article_store.loadPage(1);
	}
	
	//显示分类信息信息编辑窗口
	function article_edit(){
		Ext.getCmp('w_article_info').show();
	}
	
	//弹出添加文章的窗口
	function w_article_show(){
		Ext.getCmp('w_article_add').show();
	}
	//跳转到分类信息信息修改界面
	function article_OnShow(grid, rowIndex, colIndex){
		var record = AOS.selectone(g_article);
		f_acticle_info.form.reset();
        AOS.ajax({
        	params : {
        		article_id: record.data.article_id
        	},
            url: 'zjcArticleService.selectByKey',
            ok: function (data) {
            	f_acticle_info.form.setValues(data);
            	var updateUE = UE.getEditor('update_editor',{
    				scaleEnabled:true
    			});
         		updateUE.addListener('ready',function(editor){
         			updateUE.setContent(data.content);
         		});
         		updateUE.addListener('blur',function(editor){
         			f_acticle_info.down("[name='content']").setValue(updateUE.getContent());
         		});
         		try{
    				updateUE.setContent(f_acticle_info.down("[name='content']").getValue());
    			}catch(error){
    				
    			}
            }
        });
        
        
	}
	//初始化编辑器及内容设置
	function addOnshow(me, records){
		var addUE = UE.getEditor('add_editor',{
			scaleEnabled:true
		});
		addUE.addListener('blur',function(editor){
			f_article_add_info.down("[name='content']").setValue(addUE.getContent());
		});
	}
	
	//提交修改后的数据
	function w_article_info_submit(){
		AOS.ajax({
            //只提交一个表单
            forms : f_acticle_info,
            url : 'zjcArticleService.updateArticle',
            ok : function(data) {
                w_article_info.hide();
                AOS.tip(data.appmsg);
                g_article_store.reload();
            }
        });
	}
	
	//添加新的文章
	function w_article_add_submit(){
		AOS.ajax({
            //只提交一个表单
            forms : f_article_add_info,
            url : 'zjcArticleService.insertArticle',
            ok : function(data) {
                w_article_add.hide();
                AOS.tip(data.appmsg);
                g_article_store.reload();
            }
        });
	}
	//删除文章
	function article_del(){
		var record = AOS.selectone(g_article);
    	var msg =  AOS.merge('确认删除文章？');
		AOS.confirm(msg, function(btn){
			if(btn === 'cancel'){
				AOS.tip('删除操作被取消。');
				return;
			}
        AOS.ajax({
        	params : {
        		article_id: record.data.article_id
        	},
            url: 'zjcArticleService.deleteByKey',
            ok: function (data) {
            	AOS.tip(data.appmsg);
            	g_article_store.reload();
            }
        });
		});
	}
</script>
</aos:onready>
