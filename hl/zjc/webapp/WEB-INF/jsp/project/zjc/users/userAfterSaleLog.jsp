<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
		<aos:formpanel id="f_query" layout="column" labelWidth="170" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:datetimefield name="start_time" fieldLabel="起始时间" columnWidth="0.4" editable="false"/>
			<aos:datetimefield name="end_time" fieldLabel="结束时间" columnWidth="0.4" editable="false"/>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_log_load" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	<!-- 会员主数据 -->
	<aos:gridpanel id="g_log" url="zjcUsersInfoService.getAfterSaleLog" region="center">
		<aos:docked forceBoder="1 0 1 0">
			<aos:dockeditem xtype="tbtext" text="日志信息" />
		</aos:docked>
		<aos:column type="rowno" />
		<aos:column header="会员ID" dataIndex="user_id" width="60"/>
		<aos:column header="操作时间" dataIndex="time" width="60"/>
		<aos:column header="操作类型" dataIndex="type" width="60"/>
		<aos:column header="描述" dataIndex="descs" width="300"/>
	</aos:gridpanel>
</aos:viewport> 
<script type="text/javascript">
function g_log_load(){
	var params = AOS.getValue('f_query');
	if(params.start_time == '' && params.end_time == ''){
		AOS.tip("查询条件不能为空");
		return;
	}
	g_log_store.getProxy().extraParams = params;
	g_log_store.loadPage(1);
}
</script>
</aos:onready>

