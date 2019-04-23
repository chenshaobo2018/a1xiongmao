<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
    <!-- UEditer -->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/lang/zh-cn/zh-cn.js"></script>
	<aos:body>
		<!-- UEditer -->
		<div id="div_add_ueditor" class="x-hidden">
			<script id="add_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
		<div id="div_update_ueditor" class="x-hidden">
			<script id="update_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
	   <aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
			<aos:dockeditem onclick="#w_zjcUser_us.show();" text="新增系统消息" icon="add.png" />
			</aos:docked>
			<aos:textfield name="title" fieldLabel="标题" columnWidth="0.25" />
			<aos:combobox name="type" fieldLabel="类型" emptyText="类型" dicField="type" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
	
		
		<aos:gridpanel id="g_param" url="ZjcMessageService.getUserList"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="系统消息列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="表id" dataIndex="message_id"  fixedWidth="20" hidden="true"/>
			<aos:column header="管理者id" dataIndex="admin_id" celltip="true" minWidth="80"/>
			<aos:column header="标题" dataIndex="title" celltip="true" width="80" />
			<aos:column hidden="true" header="站内信内容" dataIndex="message"  fixedWidth="100" />
			<aos:column header="类型" dataIndex="type" celltip="true" width="50" rendererField="type"/>
			<aos:column header="发送时间 " dataIndex="send_time" width="50" celltip="true" rendererField="is"/>
			<aos:column header="是否弹出" dataIndex="is_alert"  fixedWidth="100" rendererField="is"/>
			<aos:column header="弹出截至日期" dataIndex="alert_deth_line"  fixedWidth="100"/>
			<aos:column header="是否显示" dataIndex="is_display"  fixedWidth="100" rendererField="is"/>
			<aos:column header="操作" rendererFn="fn_button_render" align="center" fixedWidth="100"/>
		</aos:gridpanel>
	</aos:viewport>
	
	
	<aos:window id="w_zjcUser_u" title="系统消息修改"  onshow="store_OnShow" >
		<aos:formpanel id="f_zjcUser_u" width="1100" height="650" layout="anchor" labelWidth="65" >
			<aos:hiddenfield name="message_id"/>
			<aos:textfield name="title"  fieldLabel="标题" allowBlank="false" maxLength="50" readOnly="true"/>
			<aos:fieldset title="消息内容" labelWidth="120" columnWidth="1"  contentEl="div_update_ueditor" collapsible="false" border="false"></aos:fieldset>
			<aos:combobox name="type" fieldLabel="消息类型" emptyText="暂无" dicField="type" columnWidth="0.25" />
			<aos:radioboxgroup fieldLabel="是否弹出" columns="3" columnWidth="0.33">
				<aos:radiobox name="is_alert" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_alert" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:datetimefield name="alert_deth_line" fieldLabel="截止日期" />
			<aos:radioboxgroup fieldLabel="是否显示" columns="3" columnWidth="0.25">
				<aos:radiobox name="is_display" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_display" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:datetimefield name="send_time" fieldLabel="发送时间" />
			<aos:hiddenfield name="message"/>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
		
	<aos:window id="w_zjcUser_us" title="新增系统消息"  onshow="addOnshow" >
		<aos:formpanel id="f_ad_position" width="1100" height="650" layout="anchor" labelWidth="65" >
			<aos:textfield name="title"  fieldLabel="标题" allowBlank="false" maxLength="50" />
			<aos:fieldset title="消息内容" labelWidth="120" columnWidth="1"  contentEl="div_add_ueditor" collapsible="false" border="false"></aos:fieldset>
			<aos:hiddenfield name="message" />
			<aos:combobox name="type" fieldLabel="类型" emptyText="类型" dicField="type" columnWidth="0.25" />
			<aos:combobox name="is_alert" fieldLabel="是否弹出" emptyText="是否弹出" dicField="is_alert" columnWidth="0.25" />
			<aos:combobox name="is_display" fieldLabel="是否显示" emptyText="是否显示" dicField="is_display" columnWidth="0.25" />
			<aos:datefield name="alert_deth_line" fieldLabel="截止日期" format="Y-m-d 23:59:59" editable="false" maxValue="${maxValue}" disabledDaysText="测试" minValue="${minValue}"
							columnWidth="0.33" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_position_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_us.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
	<script type="text/javascript">
		
	 //系统消息按键列表 
	function fn_button_render(value, metaData, record, rowIndex, colIndex,
			store) {
		return '<input type="button" value="修改" class="cbtn_danger" onclick="w_zjcUser_u_show();" />'
		       +'<input type="button" value="删除" class="cbtn_danger" onclick="store_OnShows();" />';
	}
	
		  //系统修改页面 
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	        	//设置默认的消息类型
	        	if(record.data.type == 0){
	        		record.data.type = "0";
              	} else if(record.data.type == 1){
              		record.data.type = "1";
              	} 
	        	f_zjcUser_u.loadRecord(record);
	        	var updateUE = UE.getEditor('update_editor',{
					scaleEnabled:true
				});
				updateUE.addListener('ready',function(editor){
					updateUE.setContent(record.data.message);
				});
				updateUE.addListener('blur',function(editor){
					f_zjcUser_u.down("[name='message']").setValue(updateUE.getContent());
				});
				try{
    				updateUE.setContent(record.data.message);
    			}catch(error){
    				
    			}
	        }
		  
	      
	      //初始化编辑器及内容设置
			function addOnshow(me, records){
				var addUE = UE.getEditor('add_editor',{
					scaleEnabled:true
				});
				addUE.addListener('blur',function(editor){
					f_ad_position.down("[name='message']").setValue(addUE.getContent());
				});
			}
		  
			
	        //系统消息 修改方法
	        function f_zjcUser_u_submit() {
	            AOS.ajax({
	                //只提交一个表单
	                forms : f_zjcUser_u,
	                url : 'ZjcMessageService.updateStoreInfo',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    g_param_store.reload();
	                    w_zjcUser_u.hide();
	                }
	            });
	        }
	        
	        
	       
	      //新增参数保存
	        function f_ad_position_submit() {
	            AOS.ajax({
	                forms: f_ad_position,
	                url: 'ZjcMessageService.saveMsg',
	                ok: function (data) {
	                	   AOS.tip(data.appmsg);
	                	   w_zjcUser_us.hide();
	                	   g_param_store.reload();
	                	   //w_zjcUser_us.tip(data.appmsg);
	                }
	            });
	        }
         
	        function format_shippingTime(value, metaData, record, rowIndex, colIndex,store){
				var apptype = record.data.type;
				if(apptype ==1){
					return '全体消息';
				} else {
					return '个体消息';
				}
			}
          
           //查询系统消息 主数据列表
            function g_param_query() {
            	var params = AOS.getValue('f_query');
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
        </script>
</aos:onready>

<script type="text/javascript">
		//弹出系统消息修改参数窗口
		function w_zjcUser_u_show() {
				Ext.getCmp('w_zjcUser_u').show();
		} 
		 //弹出系统消息新增参数窗口
		function w_zjcUser_us_show() {
				Ext.getCmp('w_zjcUser_us').show();
		} 
		  //删除系统消息
        function store_OnShows() {
        	var record = AOS.selectone(Ext.getCmp('g_param'));
        	var msg =  AOS.merge('确认要删除标题【{0}】吗？', record.data.title);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
            AOS.ajax({
            	params : {
            		message_id: record.data.message_id
            	},
                url: 'ZjcMessageService.deleteByKey',
                ok: function (data) {
                	Ext.getCmp("g_param").store.reload();
                }
            });
			});
        }
		 
</script>
