<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="抽奖次数查询" base="http" lib="ext">
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
	</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="fit">
	<aos:panel layout="auto" autoScroll="true" border="false">
		<aos:panel layout="anchor" width="auto" border="false">
			<aos:formpanel id="f_query" layout="column" header="false" border="false" anchor="100%" >
				<aos:docked forceBoder="1 0 1 0"> 
					<aos:dockeditem xtype="tbtext" text="查询条件" />
				</aos:docked>
				<aos:textfield name="user_id" fieldLabel="会员ID"  columnWidth="0.25" />
				<aos:textfield name="wx_count" fieldLabel="微信号"  columnWidth="0.25" />
				<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="button" text="查询" onclick="g_lottery_query" icon="query.png" />
					<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
					<aos:dockeditem xtype="tbfill" />
				</aos:docked>
			</aos:formpanel>
		
			<aos:gridpanel id="g_lottery_draw" url="zjcPrizeHTService.listLotteryDraw" forceFit="true" autoLoad="true" height="750" anchor="100%">
				<aos:docked forceBoder="0 0 1 0" >
					<aos:dockeditem onclick="w_lottery_draw_u_show" text="修改" icon="edit.png" />
				</aos:docked>
				<aos:selmodel type="checkbox" mode="single" />
				<aos:column type="rowno" />
				<aos:column header="抽奖表ID" dataIndex="lottery_draw_id" hidden="true" />
				<aos:column header="会员ID" dataIndex="user_id" />
				<aos:column header="微信号" dataIndex="wx_count" />
				<aos:column header="剩余抽奖次数" dataIndex="lottery_number" celltip="true"  />
				<aos:column header="open_id" dataIndex="open_id" hidden="true" />
			</aos:gridpanel>
		</aos:panel>
	</aos:panel>
	<aos:window id="w_lottery_draw_u" title="修改抽奖次数">
		<aos:formpanel id="f_lottery_draw_u" width="500" layout="column"
			labelWidth="80">
			<aos:hiddenfield name="lottery_draw_id" fieldLabel="抽奖表ID" columnWidth="0.7" />
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.7" />
			<aos:textfield name="lottery_number" fieldLabel="剩余抽奖次数" columnWidth="0.7" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_lottery_draw_u_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_lottery_draw_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	</aos:viewport>
	<script type="text/javascript">
		//查询参数列表
	    function g_lottery_query() {
			var params = AOS.getValue('f_query');
			g_lottery_draw_store.getProxy().extraParams = params;
			g_lottery_draw_store.loadPage(1);
	    }
	  //弹出修改窗口
        function w_lottery_draw_u_show(){
         	var record = AOS.selectone(g_lottery_draw);
              if (record) {
            	 w_lottery_draw_u.show();
            	 f_lottery_draw_u.loadRecord(record);
            }
        }
      //修改保存
        function f_lottery_draw_u_save(){
        	AOS.ajax({
                forms: f_lottery_draw_u,
                url: 'zjcPrizeHTService.updateLotteryDraw',
                ok: function (data) {
                	if(data.appcode == '1'){
                		w_lottery_draw_u.hide();
                		g_lottery_draw_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
						AOS.err(data.appmsg);
                	}
                }
            });
        }
	</script>
</aos:onready>