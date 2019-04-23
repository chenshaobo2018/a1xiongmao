<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="中奖纪录查询" base="http" lib="ext">
<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
<aos:body>

</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="fit">
		<aos:panel layout="auto" autoScroll="true" border="false">
			<aos:panel layout="anchor" width="auto" border="false">
				<aos:formpanel id="f_query" layout="column" header="false"
					border="false" anchor="100%">
					<aos:docked forceBoder="1 0 1 0">
						<aos:dockeditem xtype="tbtext" text="查询条件" />
					</aos:docked>
					<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.25" />
					<aos:textfield name="phone_num" fieldLabel="电话号码"
						columnWidth="0.25" />
					<aos:textfield name="prize_name" fieldLabel="奖品名称"
						columnWidth="0.25" />
					<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
						<aos:dockeditem xtype="tbfill" />
						<aos:dockeditem xtype="button" text="查询"
							onclick="g_win_prize_query" icon="query.png" />
						<aos:dockeditem xtype="button" text="重置"
							onclick="AOS.reset(f_query);" icon="refresh.png" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
				</aos:formpanel>

				<aos:gridpanel id="g_win_prize" url="zjcPrizeHTService.listWinPrize"
					forceFit="true" autoLoad="true" height="750" anchor="100%">
					<aos:docked forceBoder="0 0 1 0">
						<aos:dockeditem xtype="tbtext" text="中奖纪录列表" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="single" />
					<aos:column type="rowno" />
					<aos:column header="中奖ID" dataIndex="win_prize_id" hidden="true" />
					<aos:column header="会员ID" dataIndex="user_id" />
					<aos:column header="电话号码" dataIndex="phone_num" celltip="true" />
					<aos:column header="奖品ID" dataIndex="prize_id" hidden="true" />
					<aos:column header="奖品名称" dataIndex="prize_name" celltip="true" />
					<aos:column header="中奖时间" dataIndex="win_time" />
				</aos:gridpanel>
			</aos:panel>
		</aos:panel>
	</aos:viewport>
	<script type="text/javascript">
		//查询参数列表
		function g_win_prize_query() {
			var params = AOS.getValue('f_query');
			g_win_prize_store.getProxy().extraParams = params;
			g_win_prize_store.loadPage(1);
		}
	</script>
</aos:onready>