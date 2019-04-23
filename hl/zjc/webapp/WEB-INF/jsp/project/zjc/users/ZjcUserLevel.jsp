<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
		<!-- 会员主数据 -->
		<aos:gridpanel id="g_param" url="ZjcUserLevelService.getUserList"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="会员账户信息" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="表id" dataIndex="level_id" id="user_id" fixedWidth="20" hidden="true" />
			<aos:column header="头衔名称" dataIndex="level_name" celltip="true" minWidth="80"  />
			<aos:column header="等级" dataIndex="amount" celltip="true" width="80" />
			<aos:column header="开通结算中心个数" dataIndex="is_use_number"  fixedWidth="200" />
			<aos:column header="折扣" dataIndex="discount" celltip="true" width="50" />
			<aos:column header="描述" dataIndex="describes" width="50" celltip="true" />
			<aos:column header="是否开通结算中心" dataIndex="is_use_js"  fixedWidth="200"  rendererField="is"/>
			<aos:column header="操作" rendererFn="fn_button_render" align="center" fixedWidth="200"/>
		</aos:gridpanel>
	</aos:viewport>
	
	<!-- 会员用户编辑 -->
	<aos:window id="w_zjcUser_u" title="修改用户"  onshow="store_OnShow" >
		<aos:formpanel id="f_zjcUser_u" width="800" layout="anchor" labelWidth="65" >
			<aos:textfield name="level_id" fieldLabel="表id" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:textfield name="level_name" fieldLabel="头衔名称" allowBlank="false" maxLength="50" />
			<aos:textfield name="amount"  fieldLabel="等级" allowBlank="false" maxLength="50" />
			<aos:textfield name="is_use_number" fieldLabel="开通结算中心个数" allowBlank="false" maxLength="50" />
			<aos:textfield name="discount" fieldLabel="折扣" id="nicknames" allowBlank="false" maxLength="50" />
			<aos:textfield name="describes" fieldLabel="描述" allowBlank="false" maxLength="50" />
			<aos:radioboxgroup fieldLabel="是否开通了结算中心 " columns="3"  columnWidth="0.33">
				<aos:radiobox name="is_use_js"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_use_js"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	

	
	
     
	
	
	<script type="text/javascript">
		
	 //会员等级按钮列转换
	function fn_button_render(value, metaData, record, rowIndex, colIndex,
			store) {
		return '<input type="button" value="修改" class="cbtn_danger" onclick="w_zjcUser_u_show();" />';
	}
	
		  //跳转到商家信息修改界面
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		level_id: record.data.level_id
	            	},
	                url: 'ZjcUserLevelService.getUserlevel',
	                ok: function (data) {
	                	f_zjcUser_u.form.setValues(data[0]);
	                }
	            });
	        }
			
	        //数据提交(表单)
	        function f_zjcUser_u_submit() {
	            AOS.ajax({
	                //只提交一个表单
	                forms : f_zjcUser_u,
	                url : 'ZjcUserLevelService.updateStoreInfo',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    g_param_store.reload();
	                    w_zjcUser_u.hide();
	                }
	            });
	        }
		  
           //查询会员主数据列表
            function g_param_query() {
                var params;
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
        </script>
</aos:onready>

<script type="text/javascript">
		//弹出会员修改参数窗口
		function w_zjcUser_u_show() {
		    Ext.getCmp('w_zjcUser_u').show();
		} 
</script>