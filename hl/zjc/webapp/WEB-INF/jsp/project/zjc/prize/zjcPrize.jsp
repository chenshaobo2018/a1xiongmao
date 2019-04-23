<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="奖品管理" base="http" lib="ext">
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
					<aos:textfield name="prize_id" fieldLabel="奖品ID" columnWidth="0.25" />
					<aos:textfield name="prize_name" fieldLabel="奖品名称" columnWidth="0.25" />
					<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
						<aos:dockeditem xtype="tbfill" />
						<aos:dockeditem xtype="button" text="查询" onclick="g_prizes_query"
							icon="query.png" />
						<aos:dockeditem xtype="button" text="重置"
							onclick="AOS.reset(f_query);" icon="refresh.png" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
				</aos:formpanel>

				<aos:gridpanel id="g_prizes" url="zjcPrizeHTService.listPrize"
					forceFit="true" autoLoad="true" height="750" anchor="100%">
					<aos:docked forceBoder="0 0 1 0">
						<aos:dockeditem onclick="#w_prizes.show()" text="新增"
							icon="add.png" />
						<aos:dockeditem onclick="w_prizes_u_show" text="修改"
							icon="edit.png" />
						<aos:dockeditem onclick="g_prizes_del" text="删除"
							icon="del.png" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="single" />
					<aos:column header="奖品ID" dataIndex="prize_id" />
					<aos:column header="奖品名称" dataIndex="prize_name" celltip="true" />
					<aos:column header="奖品权重" dataIndex="prize_weight" celltip="true" />
				</aos:gridpanel>
			</aos:panel>
		</aos:panel>
		<aos:window id="w_prizes" title="新增奖品">
			<aos:formpanel id="f_prizes" width="500" layout="column"
				labelWidth="80">
				<aos:textfield name="prize_name" fieldLabel="奖品名称" columnWidth="0.7" />
				<aos:textfield name="prize_weight" fieldLabel="奖品权重"
					columnWidth="0.7" />
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="f_prizes_save" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="#w_prizes.hide();" text="关闭"
					icon="close.png" />
			</aos:docked>
		</aos:window>
		<aos:window id="w_prizes_u" title="修改奖品">
			<aos:formpanel id="f_prizes_u" width="500" layout="column"
				labelWidth="80">
				<aos:hiddenfield name="prize_id" fieldLabel="奖品ID" columnWidth="0.7" />
				<aos:textfield name="prize_name" fieldLabel="奖品名称" columnWidth="0.7" />
				<aos:textfield name="prize_weight" fieldLabel="奖品权重"
					columnWidth="0.7" />
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="f_prizes_u_save" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="#w_prizes_u.hide();" text="关闭"
					icon="close.png" />
			</aos:docked>
		</aos:window>
	</aos:viewport>
	<script type="text/javascript">
		//查询参数列表
	    function g_prizes_query() {
			var params = AOS.getValue('f_query');
			g_prizes_store.getProxy().extraParams = params;
			g_prizes_store.loadPage(1);
	    }
		//弹出修改窗口
        function w_prizes_u_show(){
         	var record = AOS.selectone(g_prizes);
              if (record) {
            	 w_prizes_u.show();
            	 f_prizes_u.loadRecord(record);
            }
        }
		
      //商品类型新增保存
		 function f_prizes_save(){
			 AOS.ajax({
               forms: f_prizes,
               url: 'zjcPrizeHTService.savePrize',
               ok: function (data) {
               	if(data.appcode == '1'){
               		w_prizes.hide();
               		g_prizes_store.reload();
                    AOS.tip(data.appmsg);
               	}else{
               		AOS.err(data.appmsg);
               	}
               }
           });
		 }
        //修改保存
        function f_prizes_u_save(){
        	AOS.ajax({
                forms: f_prizes_u,
                url: 'zjcPrizeHTService.updatePrize',
                ok: function (data) {
                	if(data.appcode == '1'){
                		w_prizes_u.hide();
                		g_prizes_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
						AOS.err(data.appmsg);
                	}
                }
            });
        }
        
        //删除商品类型
        function g_prizes_del() {
            var selectionIds = AOS.selection(g_prizes, 'prize_id');
            if (AOS.empty(selectionIds)) {
                AOS.tip('删除前请先选中数据。');
                return;
            }
            var rows = AOS.rows(g_prizes);
            var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
            AOS.confirm(msg, function (btn) {
                if (btn === 'cancel') {
                    AOS.tip('删除操作被取消。');
                    return;
                }
                AOS.ajax({
                    url: 'zjcPrizeHTService.deletePrize',
                    params: {
                        aos_rows: selectionIds
                    },
                    ok: function (data) {
                        AOS.tip(data.appmsg);
                        g_prizes_store.reload();
                    }
                });
            });

        }
    </script>
</aos:onready>