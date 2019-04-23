<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="广告列表" base="http" lib="ext">
	<aos:body id="adbody">
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_ad" url="ZjcFunctionIconService.listAd" onrender="g_ad_query"  forceFit="true"
			onitemdblclick="w_ad_u_show">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_ad.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_ad_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_ad_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_ad_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="#w_ad.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_ad_u_show" text="修改" icon="edit.png" />
				<aos:dockeditem onclick="g_ad_del" text="删除" icon="del.png" />
				<aos:dockeditem xtype="tbseparator"/>
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="图片路径" dataIndex="icon" celltip="true" />
			<aos:column header="描述" dataIndex="name" celltip="true" />
			<aos:column header="类型" dataIndex="type" rendererField="icon_typea" />
			<aos:column header="是否显示" dataIndex="shows" celltip="true" />
			<aos:column header="排序字段" dataIndex="sort" celltip="true" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_ad" title="新增广告" onshow="#AOS.reset(f_ad);" >
		<aos:formpanel id="f_ad" width="800" layout="column" labelWidth="65" >
			<aos:textfield name="name" fieldLabel="描述"  maxLength="50"  columnWidth="0.33" allowBlank="false"/>
			<aos:radioboxgroup fieldLabel="是否显示" columns="3" columnWidth="0.33">
				<aos:radiobox name="shows" boxLabel="是" inputValue="1" />
				<aos:radiobox name="shows" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:combobox fieldLabel="类型" name="type" dicField="icon_typea" editable="false"  columnWidth="0.33" allowBlank="false"/>
			<aos:numberfield fieldLabel="排序" name="sort" columnWidth="0.33" />
			<aos:textfield name="icon" fieldLabel="图片地址"  maxLength="100"  columnWidth="0.5" allowBlank="false"/>
			<aos:fieldset columnWidth="0.99" height="450" collapsible="false">
				<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&paramkey=mobil_image_prop&juid=${juid}"/>
			</aos:fieldset>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_ad.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_ad_u" title="修改广告">
		<aos:formpanel id="f_ad_u" width="800" layout="column" labelWidth="65">
			<aos:hiddenfield name="id" />
			<aos:textfield name="name" fieldLabel="描述"  maxLength="50"  columnWidth="0.33"/>
			<aos:radioboxgroup fieldLabel="是否显示" columns="3" columnWidth="0.33">
				<aos:radiobox name="shows" boxLabel="是" inputValue="1" />
				<aos:radiobox name="shows" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:combobox fieldLabel="类型" name="type" dicField="icon_typea" editable="false"  columnWidth="0.33" allowBlank="false"/>
			<aos:numberfield fieldLabel="排序" name="sort" columnWidth="0.33" />
			<aos:textfield name="icon" fieldLabel="图片地址"  maxLength="100"  columnWidth="0.5"/>
			<aos:fieldset columnWidth="0.99" height="450" border="false"  collapsible="false">
				<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&paramkey=mobil_image_prop&juid=${juid}" />
			</aos:fieldset>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_ad_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<script type="text/javascript">

            //弹出修改广告窗口
            function w_ad_u_show() {
            	 w_ad_u.show();
            	var record = AOS.selectone(g_ad);
            	 AOS.ajax({
 	            	params : {
 	            		id: record.data.id
 	            	},
 	                url: 'ZjcFunctionIconService.queryIcon',
 	                ok: function (data) {
 	                	console.log(data);
 	                	data[0].type+="";
 	                	f_ad_u.form.setValues(data[0]);
 	                }
 	            });
            }

            //查询广告列表
            function g_ad_query() {
                var params = {
                };
                g_ad_store.getProxy().extraParams = params;
                g_ad_store.loadPage(1);
            }

            //删除广告
            function g_ad_del() {
                var selectionIds = AOS.selection(g_ad, 'id');
                if (AOS.empty(selectionIds)) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var rows = AOS.rows(g_ad);
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'ZjcFunctionIconService.deleteIcon',
                        params: {
                            aos_rows: selectionIds
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_ad_store.reload();
                        }
                    });
                });

            }

            //新增广告保存
            function f_ad_submit() {
                AOS.ajax({
                    forms: f_ad,
                    url: 'ZjcFunctionIconService.addIcon',
                    ok: function (data) {
                            w_ad.hide();
                            g_ad_store.reload();
                            AOS.tip(data.appmsg);
                    }
                });
            }

            //修改广告保存
            function f_ad_u_submit() {
                AOS.ajax({
                    forms: f_ad_u,
                    url: 'ZjcFunctionIconService.updateIcon',
                    ok: function (data) {
                    	
                            w_ad_u.hide();
                            g_ad_store.reload();
                            AOS.tip(data.appmsg);
                    	
                    	
                    }
                });
            }
        </script>
</aos:onready>