<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="活动开始结束时间维护" base="http" lib="ext">
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_attribute" url="ZjcActivitiesService.queryActivitiy" onrender="g_attribute_query"  forceFit="true"
		onitemdblclick="w_attribute_u_show" >
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_attribute.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_attribute_u_show" icon="edit.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_attribute_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" id="serchReload">
				<aos:dockeditem onclick="#w_attribute.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_attribute_u_show" text="修改" icon="edit.png" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column header="活动id" dataIndex="time_id"  celltip="true" />
			<aos:column header="活动开始时间" dataIndex="activities_began" celltip="true" />
			<aos:column header="活动结束时间" dataIndex="activities_end" celltip="true" />
			<aos:column header="活动名称" dataIndex="activities_name" celltip="true" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_attribute" title="活动时间添加">
		<aos:formpanel id="f_attribute" width="800" layout="column" labelWidth="60">
			<aos:datetimefield name="activities_began" editable="false" fieldLabel="活动开始时间" columnWidth="0.99" allowBlank="false"/>
			<aos:datetimefield name="activities_end" editable="false" fieldLabel="活动开始时间" columnWidth="0.99" allowBlank="false"/>
			<aos:textfield name="activities_name" fieldLabel="活动名称" allowBlank="false" columnWidth="0.99" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_attribute_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_attribute.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_attribute_u" title="活动时间修改">
		<aos:formpanel id="f_attribute_u" width="800" layout="column" labelWidth="60">
			<aos:hiddenfield name="time_id" />
			<aos:datetimefield name="activities_began" editable="false" fieldLabel="活动开始时间" columnWidth="0.99" allowBlank="false"/>
			<aos:datetimefield name="activities_end" editable="false" fieldLabel="活动结束时间" columnWidth="0.99" allowBlank="false"/>
			<aos:textfield name="activities_name" fieldLabel="活动名称" allowBlank="false" columnWidth="0.99" />
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
	                var params = {};
	                g_attribute_store.getProxy().extraParams = params; 
            	
                g_attribute_store.loadPage(1);
            }
            
            function f_attribute_save(){
            	AOS.ajax({
                    forms: f_attribute,
                    url: 'ZjcActivitiesService.saveActivitiy',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_attribute.hide();
                            g_attribute_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
                    		AOS.err(data.appmsg);
                    	}
                    }
                });
            }
            
         	 //弹出修改窗口
            function w_attribute_u_show(){
            	 var record = AOS.selectone(g_attribute);
            	 console.log(record);
                 f_attribute_u.form.setValues(record.data);
                 w_attribute_u.show();
            }
         	//修改提交
            function f_attribute_u_submit(){
            	AOS.ajax({
                    forms: f_attribute_u,
                    url: 'ZjcActivitiesService.updateActivitiy',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_attribute_u.hide();
                            g_attribute_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }
         	
         	
    </script>
</aos:onready>
