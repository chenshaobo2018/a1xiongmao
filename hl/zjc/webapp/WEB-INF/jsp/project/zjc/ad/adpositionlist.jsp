<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="广告位置" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_ad_position" url="zjcAdService.listAdposition" onrender="g_ad_position_query"  forceFit="true"
			onitemdblclick="w_ad_position_u_show">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_ad_position.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_ad_position_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_ad_position_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_ad_position_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="#w_ad_position.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_ad_position_u_show" text="修改" icon="edit.png" />
				<aos:dockeditem onclick="g_ad_position_del" text="删除" icon="del.png" />
				<aos:dockeditem xtype="tbseparator"/>
				<aos:triggerfield emptyText="广告位置" id="position_name" onenterkey="g_ad_position_query"
					trigger1Cls="x-form-search-trigger" onTrigger1Click="g_ad_position_query" width="180" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="广告位置id" dataIndex="position_id" fixedWidth="150" hidden="true" />
			<aos:column header="广告位置名称" dataIndex="position_name" celltip="true" width="200" />
			<aos:column header="广告位宽度" dataIndex="ad_width" width="200" />
			<aos:column header="广告位高度" dataIndex="ad_height" width="300" celltip="true" />
			<aos:column header="广告描述" dataIndex="position_desc" celltip="true" minWidth="200"  />
			<aos:column header="是否开启" dataIndex="is_open" celltip="true" rendererField="is"  />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_ad_position" title="新增广告位置"  onshow="#AOS.reset(f_ad_position);" >
		<aos:formpanel id="f_ad_position" width="500" layout="anchor" labelWidth="90" >
			<aos:textfield name="position_name" fieldLabel="广告位置名称" allowBlank="false" maxLength="50" />
			<aos:numberfield fieldLabel="广告位宽度" name="ad_width" columnWidth="0.33" minValue="0" />
			<aos:numberfield fieldLabel="广告位高度" name="ad_height" columnWidth="0.33" minValue="0"/>
			<aos:radioboxgroup fieldLabel="是否开启"  columns="6" columnWidth="0.66">
				<aos:radiobox name="is_open" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_open" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:textareafield fieldLabel="广告描述" name="position_desc" maxLength="2000" height="60" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_position_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_ad_position.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_ad_position_u" title="修改参数">
		<aos:formpanel id="f_ad_position_u" width="500" layout="anchor" labelWidth="90">
			<aos:hiddenfield name="position_id" />
			<aos:textfield name="position_name" fieldLabel="广告位置名称" allowBlank="false" maxLength="50" />
			<aos:numberfield fieldLabel="广告位宽度" name="ad_width" columnWidth="0.33" minValue="0" />
			<aos:numberfield fieldLabel="广告位高度" name="ad_height" columnWidth="0.33" minValue="0"/>
			<aos:radioboxgroup fieldLabel="是否开启"  columns="6" columnWidth="0.66">
				<aos:radiobox name="is_open" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_open" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:textareafield fieldLabel="广告描述" name="position_desc" maxLength="2000" height="60" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_position_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_ad_position_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<script type="text/javascript">

            //弹出修改参数窗口
            function w_ad_position_u_show() {
                var record = AOS.selectone(g_ad_position);
                if (record) {
                    w_ad_position_u.show();
                    f_ad_position_u.loadRecord(record);
                }
            }

            //查询参数列表
            function g_ad_position_query() {
                var params = {
                	position_name: position_name.getValue()
                };
                g_ad_position_store.getProxy().extraParams = params;
                g_ad_position_store.loadPage(1);
            }

            //删除参数
            function g_ad_position_del() {
                var selectionIds = AOS.selection(g_ad_position, 'position_id');
                if (AOS.empty(selectionIds)) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var rows = AOS.rows(g_ad_position);
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'zjcAdService.deleteAdposition',
                        params: {
                            aos_rows: selectionIds
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_ad_position_store.reload();
                        }
                    });
                });

            }

            //新增参数保存
            function f_ad_position_submit() {
                AOS.ajax({
                    forms: f_ad_position,
                    url: 'zjcAdService.saveAdposition',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_ad_position.hide();
                            g_ad_position_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
                    		AOS.err(data.appmsg);
    						AOS.get('f_ad_position.position_name').markInvalid(data.appmsg);
                    	}
                    }
                });
            }

            //修改参数保存
            function f_ad_position_u_submit() {
                AOS.ajax({
                    forms: f_ad_position_u,
                    url: 'zjcAdService.updateAdposition',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_ad_position_u.hide();
                            g_ad_position_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.get('f_ad_position_u.position_name').markInvalid(data.appmsg);
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }
        </script>
</aos:onready>