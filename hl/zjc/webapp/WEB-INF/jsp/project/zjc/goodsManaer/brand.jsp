<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="品牌列表" base="http" lib="ext">
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
		<!-- 添加使用 -->
		<div id="logo_img_div" class="x-hidden" >
			<img id="logo" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv1();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_logo_picture_add();">上传图片</button>
		</div>
		<div id="picture-show" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv1();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage" src="" />
			</div>
		</div>
		<!-- 修改使用 -->
		<div id="logo_img_div_update" class="x-hidden" >
			<img id="logo_update" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv2();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_logo_picture_update();">上传图片</button>
		</div>
		<div id="picture-show-update" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv2();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage-update" src="" />
			</div>
		</div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_brand" url="zjcBrandService.listBrand" onrender="g_brand_query"  forceFit="true" onitemdblclick="w_brand_u_show">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_brand.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_brand_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_brand_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_brand_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="#w_brand.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_brand_u_show" text="修改" icon="edit.png" />
				<aos:dockeditem onclick="g_brand_del" text="删除" icon="del.png" />
				<aos:dockeditem xtype="tbseparator"/>
				<aos:triggerfield emptyText="名称" id="name" onenterkey="g_brand_query"
					trigger1Cls="x-form-search-trigger" onTrigger1Click="g_brand_query" width="180" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="品牌id" dataIndex="id" fixedWidth="150" hidden="true" />
			<aos:column header="品牌名称" dataIndex="name" celltip="true" width="200" />
			<aos:column header="品牌logo" dataIndex="logo" width="200" />
			<aos:column header="品牌地址" dataIndex="url" width="300" celltip="true" />
			<aos:column header="排序" dataIndex="sort" celltip="true" minWidth="200"  />
			<aos:column header="品牌分类" dataIndex="cat_name" celltip="true" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_brand" title="新增品牌信息"  onshow="#AOS.reset(f_brand);" >
		<aos:formpanel id="f_brand" width="800" layout="column" labelWidth="70" >
			<aos:textfield fieldLabel="品牌名称" name="name" allowBlank="false" maxLength="50" columnWidth="0.49"/>
			<aos:textfield fieldLabel="品牌网址" name="url" allowBlank="false" maxLength="255" columnWidth="0.49"/>
			<aos:combobox id="parent_cat_1" onselect="cat1Onselect" fieldLabel="所属分类" name="parent_cat_id" emptyText="请选择分类" columnWidth="0.49" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" />
			<aos:combobox id="id_cat1" fieldLabel="二级分类" queryMode="local" name="cat_id" readOnly="true" columnWidth="0.49" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" />
			<aos:hiddenfield id="cat_name_1" name="cat_name" fieldLabel="品牌分类"/>
			<aos:radioboxgroup fieldLabel="是否推荐"  columns="6" columnWidth="0.66">
				<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" checked="true"/>
				<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:hiddenfield name="logo" columnWidth="0.8"/>
			<aos:fieldset title="品牌logo：" labelWidth="120" columnWidth="1" contentEl="logo_img_div" collapsible="false" border="false" >
			</aos:fieldset> 
			<%-- <aos:fieldset columnWidth="0.99" height="450" collapsible="false">
				<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&paramkey=ad_picture_prop&juid=${juid}"/>
			</aos:fieldset> --%>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_brand_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_brand.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_brand_u" title="修改参数">
		<aos:formpanel id="f_brand_u" width="800" layout="column" labelWidth="70">
			<aos:hiddenfield name="id" />
			<aos:textfield fieldLabel="品牌名称" name="name" allowBlank="false" maxLength="50" columnWidth="0.49"/>
			<aos:textfield fieldLabel="品牌网址" name="url" allowBlank="false" maxLength="255" columnWidth="0.49"/>
			<aos:combobox id="parent_cat_2" onselect="cat11nselect" fieldLabel="所属分类" name="parent_cat_id" emptyText="请选择分类" columnWidth="0.49" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" />
			<aos:combobox id="id_cat2" fieldLabel="二级分类" queryMode="local" name="cat_id" readOnly="true" columnWidth="0.49" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" />
			<aos:hiddenfield id="cat_name_2" name="cat_name" fieldLabel="品牌分类"/>
			<aos:radioboxgroup fieldLabel="是否推荐"  columns="6" columnWidth="0.66">
				<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" checked="true"/>
				<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:hiddenfield name="logo" columnWidth="0.8"/>
			<aos:fieldset title="品牌logo：" labelWidth="120" columnWidth="1" contentEl="logo_img_div_update" collapsible="false" border="false" >
			</aos:fieldset> 
			<%-- <aos:fieldset columnWidth="0.99" height="450" collapsible="false">
				<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&paramkey=ad_picture_prop&juid=${juid}"/>
			</aos:fieldset> --%>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_brand_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_brand_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="logo_picture_add" title="上传logo图片"  width="800">
		<aos:formpanel id="f_upload">
			<aos:iframe id="iframe1" columnWidth="0.8" height="450" src="do.jhtml?router=zjcBrandService.logoUploadPage&inputName=logo&windowsId=logo_picture_add&paramkey=logo_prop&juid=${juid}"/>
		</aos:formpanel>
	</aos:window>
	<aos:window id="logo_picture_update" title="上传logo图片"  width="800">
		<aos:formpanel id="f_upload">
			<aos:iframe id="iframe2" columnWidth="0.8" height="450" src="do.jhtml?router=zjcBrandService.logoUploadPage&updateFlag=1&inputName=logo_update&windowsId=logo_picture_update&paramkey=logo_prop&juid=${juid}"/>
		</aos:formpanel>
	</aos:window>
	<script type="text/javascript">

            //弹出修改参数窗口
            function w_brand_u_show() {
                var record = AOS.selectone(g_brand);
                if (record) {
                    w_brand_u.show();
                    f_brand_u.loadRecord(record);
                 	document.getElementById("logo_update").src = record.data.logo;
    	           	document.getElementById("showImage-update").src = record.data.logo;
                }
            }

            //查询参数列表
            function g_brand_query() {
                var params = {
                		name: name.getValue()
                };
                g_brand_store.getProxy().extraParams = params;
                g_brand_store.loadPage(1);
            }

            //删除参数
            function g_brand_del() {
                var selectionIds = AOS.selection(g_brand, 'id');
                if (AOS.empty(selectionIds)) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var rows = AOS.rows(g_brand);
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'zjcBrandService.deleteBrand',
                        params: {
                            aos_rows: selectionIds
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_brand_store.reload();
                        }
                    });
                });

            }

            //新增参数保存
            function f_brand_submit() {
            	var cat_name =  Ext.getCmp("parent_cat_1").rawValue + " " +Ext.getCmp("id_cat1").rawValue;
            	Ext.getCmp("cat_name_1").setValue(cat_name);
                AOS.ajax({
                    forms: f_brand,
                    url: 'zjcBrandService.saveBrand',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_brand.hide();
                            g_brand_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
                    		AOS.err(data.appmsg);
    						AOS.get('f_brand.name').markInvalid(data.appmsg);
                    	}
                    }
                });
            }

            //修改参数保存
            function f_brand_u_submit() {
            	var cat_name =  Ext.getCmp("parent_cat_2").rawValue + " " +Ext.getCmp("id_cat2").rawValue;
            	Ext.getCmp("cat_name_2").setValue(cat_name);
                AOS.ajax({
                    forms: f_brand_u,
                    url: 'zjcBrandService.updateBrand',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_brand_u.hide();
                            g_brand_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.get('f_brand_u.name').markInvalid(data.appmsg);
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }
            

    		//一级节点选择事件监听函数
    		function cat1Onselect(me, records) {
    			var parent_id = me.getValue();
    			id_cat1_store.getProxy().extraParams = {
    				parent_id : parent_id
    			};
    			id_cat1_store.load({
    				callback : function(records, operation, success) {
    					//这个回调里还可以根据是否查询到二级模块再去做一些事情
    					if (records.length > 0) {
    						AOS.edit(id_cat1);
    					}else{
    						AOS.read(id_cat1);
    					}
    				}
    			});
    		}
    		
    		function cat11nselect(me, records) {
    			var parent_id = me.getValue();
    			id_cat2_store.getProxy().extraParams = {
    				parent_id : parent_id
    			};
    			id_cat2_store.load({
    				callback : function(records, operation, success) {
    					//这个回调里还可以根据是否查询到二级模块再去做一些事情
    					if (records.length > 0) {
    						AOS.edit(id_cat2);
    					}else{
    						AOS.read(id_cat2);
    					}
    				}
    			});
    		}
        </script> 
</aos:onready> 
<script type="text/javascript">
//图片上传
	function f_logo_picture_add(){
		Ext.getCmp('logo_picture_add').show();
	}
	function f_logo_picture_update(){
		Ext.getCmp('logo_picture_update').show();
	}
	function showdiv1(){
		   $("#picture-show").show();
	}
	function closediv1(){
		   $("#picture-show").hide();
	}
	function showdiv2(){
		   $("#picture-show-update").show();
	}
	function closediv2(){
		   $("#picture-show-update").hide();
	}
</script>