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
			<%-- <aos:combobox name="settlement_center" fieldLabel="是否开通了结算中心" emptyText="是否开通了结算中心" dicField="settlement_center" columnWidth="0.25" /> --%>
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
			<aos:column header="会员ID" dataIndex="user_id" align="center"/>
			<aos:column header="会员昵称" dataIndex="nickname" align="center"/>
			<aos:column header="手机号码" dataIndex="mobile" align="center"/>
			<aos:column header="累积消费" dataIndex="total_amount" align="center"/>
			<aos:column header="可用券" dataIndex="pay_points" align="center"/> 
			<aos:column header="可转券" dataIndex="make_over_integral" align="center"/>
			<aos:column header="直推会员" dataIndex="qualified_member" align="center"/>
			<aos:column header="合格会员" dataIndex="is_qualified_member"  rendererField="is" align="center"/>
			<aos:column header="结算中心" dataIndex="settlement_center" rendererField="is" align="center"/>
			<aos:column header="注册时间" dataIndex="reg_time" align="center"/>
			<aos:column header="上级ID" dataIndex="first_leader" align="center"/>
			<aos:column header="操作" rendererFn="fn_button_render" width="180" align="center"/>
		</aos:gridpanel>
	</aos:viewport>
	
	<!-- 会员编辑 -->
	<aos:window id="w_user_detial" title="会员编辑" onshow="getUserDetial">
		<aos:formpanel id="f_zjcUser_u" width="800" layout="anchor" labelWidth="150" >
		    <aos:textfield name="user_id" id="upuser_id" fieldLabel="会员ID" allowBlank="true" maxLength="80"  readOnly="true"/>
		    <aos:textfield name="nickname" id="nickname" fieldLabel="会员昵称" allowBlank="true" maxLength="80" readOnly="true"/>
		    <aos:textfield name="total_amount"  fieldLabel="累积消费" readOnly="true" maxLength="80" />
		    <aos:textfield name="pay_points"  fieldLabel="可用券" allowBlank="true" maxLength="80" readOnly="true"/>
		    <aos:textfield name="make_over_integral" id='make_over_integrals' fieldLabel="可转券" allowBlank="true" maxLength="80" readOnly="true"/>
		    <aos:textfield name="level" id='levels'  fieldLabel="会员等级" readOnly="true" allowBlank="true" maxLength="50" />
		    <aos:textfield name="real_name" id="real_names" fieldLabel="真实姓名" allowBlank="true" maxLength="50" />
		    <aos:textfield name="head_pic" id="head_pics" fieldLabel="头像" allowBlank="true" maxLength="200" readOnly="true"/>
		    <aos:textfield name="first_leader" id="first_leader_u" fieldLabel="上级ID" readOnly="true" allowBlank="true" maxLength="200" />
		    <aos:textfield name="id_card" id="id_cards" fieldLabel="身份证号码" allowBlank="true" maxLength="80" />
		    <aos:textfield name="openid" id="openid"  fieldLabel="微信绑定" readOnly="true" emptyText="暂未绑定微信" allowBlank="true" maxLength="80" />
			<aos:textfield name="mobile" id="mobiles"  fieldLabel="手机号码" allowBlank="true" maxLength="50" />
			<aos:radioboxgroup fieldLabel="冻结 "  columns="3"  columnWidth="0.53">
				<aos:radiobox name="is_lock" id="is_lock"   boxLabel="是" inputValue="1" />
				<aos:radiobox name="is_lock" id="is_locks"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="是否禁用会员功能 " columns="3"  columnWidth="0.53">
				<aos:radiobox name="acc_is_lock" id="acc_is_lock"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="acc_is_lock" id="acc_is_locks" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="金钥匙 " columns="3"  columnWidth="0.53" disabled="true">
				<aos:radiobox name="settlement_center"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="settlement_center"  boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="云易财富 " columns="3"  columnWidth="0.53" disabled="true">
				<aos:radiobox name="settlement_center_tc"  boxLabel="是" inputValue="1" />
				<aos:radiobox name="settlement_center_tc"  boxLabel="否" inputValue="0" checked="true"/>
			</aos:radioboxgroup> 
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_user_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_user_detial.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	
	<aos:window id="w_user" title="下级"  layout="border" maximized="true" onshow="getUserLower">
			<aos:gridpanel  id="g_params" url="zjcUsersInfoService.queryUserJuniorList"  forceFit="true" region="center" headerBorder="true">
			<aos:hiddenfield name="user_id"/>
			<aos:column header="会员ID" dataIndex="user_id" align="center"/>
			<aos:column header="会员昵称" dataIndex="nickname" align="center"/>
			<aos:column header="手机号码" dataIndex="mobile" align="center"/>
			<aos:column header="累积消费" dataIndex="total_amount" align="center"/>
			<aos:column header="可用券" dataIndex="pay_points" align="center"/>
			<aos:column header="可转券" dataIndex="make_over_integral" align="center"/>
			<aos:column header="直推会员" dataIndex="qualified_member" align="center"/>
			<aos:column header="合格会员" dataIndex="is_qualified_member" rendererField="is" align="center"/>
			<aos:column header="结算中心" dataIndex="settlement_center" rendererField="is" align="center"/>
			<aos:column header="注册时间" dataIndex="reg_time" align="center"/>
			<aos:column header="上级ID" dataIndex="first_leader" align="center"/>
			</aos:gridpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="#w_user.hide();" text="关闭" icon="close.png"/>
				</aos:docked>
		</aos:window>  
	
	<aos:window id="w_zjcUser_Add" title="收货地址"  onshow="getUserAddresses" layout="border" maximized="true">
		<aos:gridpanel  id="Address" url="zjcUsersInfoService.getUserAddress"  forceFit="true" region="center">
			<aos:hiddenfield name="user_id"/>
			<aos:column header="收货人" dataIndex="consignee" id="a" celltip="true" />
			<aos:column header="手机号码" dataIndex="mobile" celltip="true" />
			<aos:column header="收货地址" dataIndex="address_info"/>
		</aos:gridpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="#w_zjcUser_Add.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window> 
	
	<!-- 查看积分日志 -->
		<aos:window id="w_user_log" title="日志"  layout="border" maximized="true" onshow="getUserLog">
		     <aos:gridpanel  id="g_paramslog" url="zjcUsersInfoService.queryForLogPage"  forceFit="true" region="center">
				<aos:column header="时间" dataIndex="time" celltip="true" width="100" />
				<aos:column header="消息类型" dataIndex="type" celltip="true" width="50" />
				<aos:column header="描述" dataIndex="descs" celltip="true" width="500" />
			</aos:gridpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="#w_user_log.hide();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>   	
	<script type="text/javascript">
		//会员主数据按钮列转换
		function fn_button_render(value, metaData, record, rowIndex, colIndex, store) {
			return '<input type="button" value="编辑" class="cbtn_danger" onclick="showAlertUser();" />'
				+'<input type="button" value="下级" class="cbtn_danger" onclick="showLowerUser();" />'  
				+ '<input type="button" value="收货地址 " class="cbtn_danger" onclick="showUserAddress();" />'
				+'<input type="button" value="日志 " class="cbtn_danger" onclick="showUserLog();" />';
	    }
		  
        //查询会员主数据列表
        function g_param_query() {
            var params = AOS.getValue('f_query');
            g_param_store.getProxy().extraParams = params;
            g_param_store.loadPage(1);
        }
        
        function getUserDetial(){
        	var record = AOS.selectone(g_param);
        	AOS.ajax({
        		url:"zjcUsersInfoService.getUserlevel",
        		params:{
        			user_id:record.data.user_id
        		},
        		ok:function(data){
        			//f_zjcUser_u.form.setValues(data);
        			var datas=data[0].ZjcUsersAccountInfoPO;
                	f_zjcUser_u.form.setValues(datas);
                	if(data[0].is_lock==0){
                		Ext.getCmp('is_lock').setValue(data[0].is_lock);
                	}else{
                		Ext.getCmp('is_locks').setValue(data[0].is_lock);
                	}
                	Ext.getCmp('upuser_id').setValue(data[0].user_id); 
                	Ext.getCmp('head_pics').setValue(data[0].head_pic);
                	Ext.getCmp('real_names').setValue(data[0].real_name);
                	Ext.getCmp('id_cards').setValue(data[0].id_card);
                	Ext.getCmp('mobiles').setValue(data[0].mobile);
                	Ext.getCmp('levels').setValue(data[0].level);
                	Ext.getCmp('nickname').setValue(data[0].nickname);
                	Ext.getCmp('first_leader_u').setValue(data[0].first_leader);
                	Ext.getCmp('openid').setValue(data[0].openid);
        		}
        	});
        }
        
        function f_zjcUser_user_submit(){
            AOS.ajax({
                //只提交一个表单
                forms : f_zjcUser_u,
                url : 'zjcUsersInfoService.updateZjcUsersInfo',
                ok : function(data) {
                    AOS.tip(data.appmsg);
                    w_user_detial.hide();
                    g_param_store.reload();
                }
            });
        }
        
        function getUserLower(){
        	var record = AOS.selectone(g_param);
        	g_params_store.getProxy().extraParams = {
        		user_id:record.data.user_id
        	};
        	g_params_store.loadPage(1);
        }
        
        function getUserAddresses(){
        	var record = AOS.selectone(g_param);
        	Address_store.getProxy().extraParams = {
        		user_id:record.data.user_id
        	};
        	Address_store.loadPage(1);
        }
        
        function getUserLog(){
        	var record = AOS.selectone(g_param);
        	g_paramslog_store.getProxy().extraParams = {
        		user_id:record.data.user_id
        	};
        	g_paramslog_store.loadPage(1);
        }
    </script>
</aos:onready>
<script type="text/javascript">
function showAlertUser(){
	Ext.getCmp("w_user_detial").show();
}
function showLowerUser(){
	Ext.getCmp("w_user").show();
}
function showUserAddress(){
	Ext.getCmp("w_zjcUser_Add").show();
}
function showUserLog(){
	Ext.getCmp("w_user_log").show();
}
</script>
