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
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.25" />
			<aos:textfield name="nickname" fieldLabel="会员昵称" columnWidth="0.25" />
			<aos:textfield name="mobile" fieldLabel="手机号码" columnWidth="0.25" />
			<aos:textfield name="id_card" fieldLabel="身份证号" columnWidth="0.25" />
			<aos:combobox name="settlement_center" fieldLabel="开通结算中心" emptyText="是否开通了结算中心" dicField="is" columnWidth="0.25" />
			<aos:combobox name="is_distribut" fieldLabel="开通企业号" emptyText="是否开通了企业号" dicField="is" columnWidth="0.25" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="g_param_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		<!-- 会员主数据 -->
		<aos:gridpanel id="g_param" url="zjcUsersInfoService.getUserList"   region="center" onrender="g_param_query">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="会员信息" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column dataIndex="sqvip" hidden="true"/>
			<aos:column header="会员ID" dataIndex="user_id" id="user_id" width="60"/>
			<aos:column header="会员昵称" dataIndex="nickname" id="nickname" width="60" rendererFn="Format_nickname"/>
			<aos:column header="手机号码" dataIndex="mobile" id="mobile" width="100"/>
			<aos:column header="累积消费" dataIndex="total_amount" id="total_amount" width="80"/>
			<aos:column header="可用券" dataIndex="pay_points" id="pay_points" width="60"/> 
			<aos:column header="可转券" dataIndex="make_over_integral" id="make_over_integral" width="60"/>
			<aos:column header="直推会员" dataIndex="qualified_member" id="qualified_member" width="60"/>
			<aos:column header="合格会员" dataIndex="is_qualified_member"  rendererField="is" id="is_qualified_member" width="60"/>
			<aos:column header="结算中心" dataIndex="settlement_center" rendererField="is" id="settlement_center" width="60"/>
			<aos:column header="企业号" dataIndex="is_distribut" rendererField="is" id="is_distribut" width="60"/>
			<aos:column header="注册时间" dataIndex="reg_time" id="reg_time" width="130"/>
			<aos:column header="上级ID" dataIndex="first_leader" id="first_leader"/>
			<aos:column header="实名认证" hidden="true" dataIndex="authentication"  rendererField="is" id="authentication"/>
			<aos:column header="云易财富" hidden="true" dataIndex="settlement_center_tc"  fixedWidth="80" rendererField="is" id="settlement_center_tc"/>
			<aos:column header="双千会员" dataIndex="sqvip"  fixedWidth="80" rendererField="sqvip"/>
			<aos:column header="省代" dataIndex="province_name" id="province_name" width="100" align="center"/>
			<aos:column header="操作" rendererFn="fn_button_render" width="180" align="center"/>
		</aos:gridpanel>
	</aos:viewport>
	<!-- 会员用户编辑 -->
	<aos:window id="w_zjcUser_u" title="修改用户"  onshow="store_OnShow" >
		<aos:formpanel id="f_zjcUser_u" width="800" layout="anchor" labelWidth="150" >
		    <aos:textfield name="user_id" id="e_user_id" fieldLabel="会员ID" allowBlank="true" maxLength="80"  readOnly="true"/>
		    <aos:textfield name="nickname" fieldLabel="会员昵称" allowBlank="true" maxLength="80" readOnly="true"/>
		    <aos:textfield name="total_amount" id="total_amount1"  fieldLabel="累积消费" readOnly="true" maxLength="80" />
		    <aos:textfield name="pay_points" id="pay_points1" fieldLabel="可用券" allowBlank="true" maxLength="80" />
		    <aos:textfield name="make_over_integral" id="make_over_integral1" fieldLabel="可转券" allowBlank="true" maxLength="80" />
		    <aos:textfield name="level" fieldLabel="会员等级" readOnly="true" allowBlank="true" maxLength="50" />
		    <aos:textfield name="real_name" fieldLabel="真实姓名" allowBlank="true" maxLength="50" />
		    <aos:textfield name="head_pic" fieldLabel="头像" allowBlank="true" maxLength="200" />
		    <aos:textfield name="first_leader" fieldLabel="上级ID" readOnly="true" allowBlank="true" maxLength="200" />
		    <aos:textfield name="id_card" fieldLabel="身份证号码"  allowBlank="true" maxLength="80" />
		    <aos:textfield name="openid" fieldLabel="微信绑定" readOnly="true" emptyText="暂未绑定微信" allowBlank="true" maxLength="80" />
			<aos:textfield name="password" id="password" fieldLabel="重置新密码" allowBlank="true" maxLength="50" emptyText="不填写表示不修改密码"/>
			<aos:textfield name="passwords" id="passwords"  fieldLabel="确认密码" allowBlank="true" maxLength="50" emptyText="请确认新密码"/>
			<aos:textfield name="mobile" fieldLabel="手机号码" allowBlank="true" maxLength="50" />
			<aos:radioboxgroup fieldLabel="冻结 "  columns="3"  columnWidth="0.53">
				<aos:radiobox name="is_lock" boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_lock" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="是否禁用会员功能 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="acc_is_lock" id="acc_is_lock" boxLabel="是" inputValue="1" />
				<aos:radiobox name="acc_is_lock" id="acc_is_locks" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="金钥匙 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="settlement_center" id="settlement_center1"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="settlement_center" id="settlement_center2"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="云易财富 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="settlement_center_tc" id="settlement_center_tc1"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="settlement_center_tc" id="settlement_center_tc2"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup> 
			<aos:radioboxgroup fieldLabel="转账限制" columns="3"  columnWidth="0.53">
				<aos:radiobox name="jf_sum" id="jf_sum1" boxLabel="是" inputValue="1" />
				<aos:radiobox name="jf_sum" id="jf_sum2" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_user_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();AOS.reset(f_zjcUser_u);" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
	<!--查询会员收货地址  -->
	 <aos:window id="w_zjcUser_Add" title="收货地址"  onshow="Address_querys" layout="border" maximized="true">
			<aos:gridpanel  id="Address" url="zjcUsersInfoService.getUserAddress"  forceFit="true" region="center" onrender="Address_querys"
		onitemdblclick="getUser_Address" >
			<aos:hiddenfield name="user_id"/>
			<aos:column header="收货人" dataIndex="consignee" id="a" celltip="true" />
			<aos:column header="手机号码" dataIndex="mobile" celltip="true" />
			<aos:column header="收货地址" dataIndex="address_info"  />
			</aos:gridpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="#w_zjcUser_Add.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>  
	
	
        <!--查询下级代码  -->
		 <aos:window id="w_user" title="下级"  layout="border" maximized="true" onshow="g_param_querys">
			<aos:gridpanel  id="g_params" url="zjcUsersInfoService.queryUserJuniorList"  forceFit="true" region="center">
			<aos:hiddenfield name="user_id"/>
			<aos:column header="会员ID" dataIndex="user_id"  celltip="true" />
			<aos:column header="会员昵称" dataIndex="real_name" id="a" celltip="true" />
			<aos:column header="手机号码" dataIndex="mobile" />
			<aos:column header="累积消费" dataIndex="total_amount" celltip="true" />
			<aos:column header="可用券" dataIndex="pay_points"  />
			<aos:column header="可转券" dataIndex="make_over_integral"/>
			<aos:column header="直推会员" dataIndex="qualified_member" />
			<aos:column header="合格会员" dataIndex="is_qualified_member"  rendererField="is"/>
			<aos:column header="结算中心" dataIndex="settlement_center"  rendererField="is"/>
			<aos:column header="企业号" dataIndex="is_distribut" rendererField="is"/>
			<aos:column header="注册时间" dataIndex="reg_time"/>
			<aos:column header="上级ID" dataIndex="first_leader"/>
			</aos:gridpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="#w_user.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>  
			
			<!-- 查看积分日志 -->
			 <aos:window id="w_users" title="积分日志记录"  layout="border" maximized="true" onshow="user_log">
		      <aos:docked forceBoder="0 0 1 0" >
			            <aos:textfield name="real_name" id="real_nameed" fieldLabel="真实姓名" maxLength="10" columnWidth="0.25" readOnly="true"/>
						<aos:textfield name="pay_points"  id="pay_pointsed" fieldLabel="积分" maxLength="20" columnWidth="0.25" readOnly="true"/>
						<aos:textfield name="mobile" id="mobileed" fieldLabel="电话" maxLength="11" columnWidth="0.25" readOnly="true" />
						<aos:textfield name="last_login" id="last_logined" fieldLabel="最后登录时间" maxLength="20"  columnWidth="0.25" readOnly="true"/>
			  </aos:docked> 
			<aos:gridpanel  id="g_paramslog" url="zjcUsersInfoService.queryForLogPage"  forceFit="true" region="center" onrender="g_paramslog_querys"
		   onitemdblclick="queryForLogPage">
			<aos:column header="时间" dataIndex="time" celltip="true" width="50" />
			<aos:column header="消息类型" dataIndex="type" celltip="true" width="50" />
			<aos:column header="描述" dataIndex="descs" celltip="true" width="500" />
			</aos:gridpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="#w_users.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>  
			
		<aos:window id="w_agent" title="设置代理" onshow="set_agent">
			<aos:formpanel id="f_agent_u" width="300" layout="column" labelWidth="150" >
				<aos:combobox fieldLabel="选择省代"  name="province_id" labelWidth="100"  id="province_id" emptyText="请选择省份" columnWidth="1"  url="zjcRegionService.getProvince"/>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="setAgent" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="#w_agent.hide();AOS.reset(f_agent_u);" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window> 
	<script type="text/javascript">
		  //会员主数据按钮列转换
			function fn_button_render(value, metaData, record, rowIndex, colIndex,
					store) {
				return '<input type="button" value="下级" class="cbtn_danger" onclick="UserFirstLeader();" />'  
				      + '<input type="button" value="编辑" class="cbtn_danger" onclick="w_zjcUser_u_show();" />' 
				       +'<input type="button" value="收货地址 " class="cbtn_danger" onclick="getUser_Address();" />'
				       +'<input type="button" value="日志 " class="cbtn_danger" onclick="queryForLogPage();" />'
				       +'<input type="button" value="设置代理 " class="cbtn_danger" onclick="set_agent()" />';
			}
			 //格式化昵称
	        function Format_nickname(value, metaData, record, rowIndex, colIndex,store){
				var nickname = record.data.nickname;
				var sqvip = record.data.sqvip;
				if(sqvip == 0){
					return nickname;
				} else {
					return '<div style="color:red">'+ nickname +'</div>'
				}
			}
		  //跳转到商家信息修改界面
	        function store_OnShow() {
	        	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		user_id: record.data.user_id
	            	},
	                url: 'zjcUsersInfoService.getUserlevel',
	                ok: function (data) {
	                	f_zjcUser_u.form.setValues(data[0]);
                    	if(data[0].ZjcUsersAccountInfoPO.acc_is_lock==0){
                    		Ext.getCmp('acc_is_lock').setValue(data[0].ZjcUsersAccountInfoPO.acc_is_lock);
                    	}else{
                    		Ext.getCmp('acc_is_locks').setValue(data[0].ZjcUsersAccountInfoPO.acc_is_lock);
                    	}
                    	if(data[0].ZjcUsersAccountInfoPO.acc_is_lock==0){
                    		Ext.getCmp('settlement_center2').setValue(data[0].ZjcUsersAccountInfoPO.settlement_center);
                    	}else{
                    		Ext.getCmp('settlement_center1').setValue(data[0].ZjcUsersAccountInfoPO.settlement_center);
                    	}
                    	if(data[0].ZjcUsersAccountInfoPO.settlement_center_tc==0){
                    		Ext.getCmp('settlement_center_tc2').setValue(data[0].ZjcUsersAccountInfoPO.settlement_center_tc);
                    	}else{
                    		Ext.getCmp('settlement_center_tc1').setValue(data[0].ZjcUsersAccountInfoPO.settlement_center_tc);
                    	}
                    	if(data[0].ZjcUsersAccountInfoPO.jf_sum==0){
                    		Ext.getCmp('jf_sum2').setValue("0");
                    	}else{
                    		Ext.getCmp('jf_sum1').setValue("1");
                    	}
                    	Ext.getCmp('total_amount1').setValue(data[0].ZjcUsersAccountInfoPO.total_amount);
                    	Ext.getCmp('pay_points1').setValue(data[0].ZjcUsersAccountInfoPO.pay_points);
                    	Ext.getCmp('make_over_integral1').setValue(data[0].ZjcUsersAccountInfoPO.make_over_integral);
	                }
	            });
	        }
			 
	        //跳转到商家信息修改界面
	        function user_log() {
	        	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		user_id: record.data.user_id
	            	},
	                url: 'zjcUsersInfoService.queryForLogOne',
	                ok: function (data) {
	                	 Ext.getCmp('real_nameed').setValue(data[0].real_name);
	                     Ext.getCmp('mobileed').setValue(data[0].mobile);
	                	 Ext.getCmp('last_logined').setValue(data[0].last_login);
	                	 Ext.getCmp('pay_pointsed').setValue(data[0].ZjcUsersAccountInfoPO.pay_points);
	                	 var params = AOS.getValue('f_query');
	                     var record = AOS.selectone(Ext.getCmp('g_param'));
	                     params.user_id=record.data.user_id;
	                     g_paramslog_store.getProxy().extraParams = params;
	                     g_paramslog_store.loadPage(1);
	                }
	            });
	        }

		  
	        //会员用户编辑查询代码 
	        function user_OnShow() {
	        	var record = AOS.selectone(w_user);
	            AOS.ajax({
	            	params : {
	            		user_id: record.data.user_id
	            	},
	                url: 'zjcUsersInfoService.getUserFirstLeader',
	                ok: function (data) {
	                	var datas=data[0].ZjcUsersAccountInfoPO;
	                	w_userForm.form.setValues(datas);
	                	Ext.getCmp('recommended_codes').setValue(data[0].recommended_code);
	                	Ext.getCmp('head_pics').setValue(data[0].head_pic);
	                	Ext.getCmp('nicknames').setValue(data[0].nickname);
	                	Ext.getCmp('real_names').setValue(data[0].real_name);
	                	Ext.getCmp('mobiles').setValue(data[0].mobile);
	                }
	            });
	        }
	      
	      
			
			
           //查询会员主数据列表
            function g_param_query() {
                var params = AOS.getValue('f_query');
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            } 
           
           //下级数据查询
            function g_param_querys() {
            	var params = AOS.getValue('f_query');
                var record = AOS.selectone(Ext.getCmp('g_param'));
                params.user_id=record.data.user_id;
                g_params_store.getProxy().extraParams = params;
                g_params_store.loadPage(1); 
            } 
            
          //下级数据查询
            function Address_querys() {
            	var params = AOS.getValue('f_query');
                var record = AOS.selectone(Ext.getCmp('g_param'));
                params.user_id=record.data.user_id;
                Address_store.getProxy().extraParams = params;
                Address_store.loadPage(1); 
            } 
           
            //积分信息查询 
            function g_paramslog_querys() {
            	var params = AOS.getValue('f_query');
                var record = AOS.selectone(Ext.getCmp('g_param'));
                params.user_id=record.data.user_id;
                g_paramslog_store.getProxy().extraParams = params;
                g_paramslog_store.loadPage(1); 
            } 
            
       
            //会员修改方法
	        function f_zjcUser_user_submit() {
	        	var pasword=Ext.getCmp('password').getValue();
	        	var paswords=Ext.getCmp('passwords').getValue();
	        	if(pasword!=paswords){
	        		AOS.tip("两次密码输入不一致,请从新输入!");
	        		return false;
	        	}
	            AOS.ajax({
	                //只提交一个表单
	                forms : f_zjcUser_u,
	                url : 'zjcUsersInfoService.updateZjcUsersInfo',
	                ok : function(data) {
	                    AOS.tip(data.appmsg);
	                    AOS.reset(f_zjcUser_u);
	                    w_zjcUser_u.hide();
	                    g_param_store.reload();
	                    
	                }
	            });
	        }
            
            //设置省代
            function setAgent(){
            	var record = AOS.selectone(g_param);
	            AOS.ajax({
	            	params : {
	            		user_id: record.data.user_id,
	            		province_id :Ext.getCmp('province_id').getValue()
	            	},
	                url: 'zjcUsersInfoService.setAgent',
	                ok: function (data) {
	                	 if(data.appcode == 1){
		                	 AOS.tip(data.appmsg);
		                	 AOS.reset(f_agent_u);
		                     w_agent.hide();
		                     g_param_store.reload();
	                	 } else {
	                		 AOS.warn(data.appmsg);
	                	 } 
	                }
	            });
            }
            
        </script>
</aos:onready>

<script type="text/javascript">

		//下级弹出修改参数窗口
		function UserFirstLeader() {
		    Ext.getCmp('w_user').show();
		} 
		
		//弹出会员修改参数窗口
		function w_zjcUser_u_show() {
		    Ext.getCmp('w_zjcUser_u').show();
		   
		} 
		 
		//下级弹出修改参数窗口
		function queryForLogPage() {
			Ext.getCmp('w_users').show()
		} 
		//弹出收货地址参数窗口
		function getUser_Address() {
		   Ext.getCmp('w_zjcUser_Add').show();
		} 
		
		 function set_agent(){
			 Ext.getCmp('w_agent').show();
         }
</script>
