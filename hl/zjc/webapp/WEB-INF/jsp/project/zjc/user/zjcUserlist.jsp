<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_param" url="zjcUserService.getUserList" onrender="g_param_query"  forceFit="true" 
		onitemdblclick="w_zjcUser_u_show">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_param.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_zjcUser_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_param_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_param_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="#w_zjcUser.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_zjcUser_u_show" text="修改" icon="edit.png" />
				<%-- <aos:dockeditem onclick="g_param_del" text="删除" icon="del.png" /> --%>
				<aos:dockeditem xtype="tbseparator"/>
				<aos:triggerfield emptyText="用户名" id="id_nkv" onenterkey="g_param_query"
					trigger1Cls="x-form-search-trigger" onTrigger1Click="g_param_query" width="180" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="用户id" dataIndex="user_id" fixedWidth="150" hidden="true" />
			<aos:column header="真实姓名" dataIndex="real_name" celltip="true" minWidth="200"  />
			<aos:column header="邮件" dataIndex="email" celltip="true" width="200" />
			<aos:column header="生日" dataIndex="birthday" width="200" />
			<aos:column header="用户金额" dataIndex="user_money" width="300" celltip="true" />
			<aos:column header="默认收货地址" dataIndex="address_id"  fixedWidth="120" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_zjcUser" title="新增用户"  onshow="#AOS.reset(f_zjcUser);" >
		<aos:formpanel id="f_zjcUser" width="500" layout="anchor" labelWidth="65" >
		   <%--  <aos:combobox name="params_group" fieldLabel="所属分组" dicField="params_group"  value="2" allowBlank="false" /> --%>
			<aos:textfield name="email" fieldLabel="邮箱地址" allowBlank="false" maxLength="50" />
			<aos:textfield name="password" fieldLabel="用户密码" allowBlank="false" maxLength="50" />
			<aos:textfield name="pay_password" fieldLabel="支付密码" allowBlank="false" maxLength="50" />
			<aos:textfield name="sex" fieldLabel="性别" allowBlank="false" maxLength="50" />
			<aos:textfield name="birthday" fieldLabel="生日" allowBlank="false" maxLength="50" />
			<aos:textfield name="user_money" fieldLabel="用户金额" allowBlank="false" maxLength="50" />
			<aos:textfield name="frozen_money" fieldLabel="冻结金额" allowBlank="false" maxLength="50" />
			<aos:textfield name="pay_points" fieldLabel="邮箱地址" allowBlank="false" maxLength="50" />
			<aos:textfield name="address_id" fieldLabel="默认收货地址" allowBlank="false" maxLength="50" />
			<aos:textfield name="reg_time" fieldLabel="注册时间" allowBlank="false" maxLength="50" />
			<aos:textfield name="qq" fieldLabel="qq " allowBlank="false" maxLength="50" />
			<aos:textfield name="mobile" fieldLabel="手机 " allowBlank="false" maxLength="50" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_zjcUser_u" title="修改用户"  onshow="#AOS.reset(f_zjcUser_u);" >
		<aos:formpanel id="f_zjcUser_u" width="500" layout="anchor" labelWidth="65" >
		   <%--  <aos:combobox name="params_group" fieldLabel="所属分组" dicField="params_group"  value="2" allowBlank="false" /> --%>
			<aos:hiddenfield name="user_id" />
			<aos:textfield name="email" fieldLabel="邮箱地址" allowBlank="false" maxLength="50" />
			<aos:textfield name="password" fieldLabel="用户密码" allowBlank="false" maxLength="50" />
			<aos:textfield name="pay_password" fieldLabel="支付密码" allowBlank="false" maxLength="50" />
			<aos:textfield name="sex" fieldLabel="性别" allowBlank="false" maxLength="50" />
			<aos:textfield name="birthday" fieldLabel="生日" allowBlank="false" maxLength="50" />
			<aos:textfield name="user_money" fieldLabel="用户金额" allowBlank="false" maxLength="50" />
			<aos:textfield name="frozen_money" fieldLabel="冻结金额" allowBlank="false" maxLength="50" />
			<aos:textfield name="pay_points" fieldLabel="邮箱地址" allowBlank="false" maxLength="50" />
			<aos:textfield name="address_id" fieldLabel="默认收货地址" allowBlank="false" maxLength="50" />
			<aos:textfield name="reg_time" fieldLabel="注册时间" allowBlank="false" maxLength="50" />
			<aos:textfield name="qq" fieldLabel="qq " allowBlank="false" maxLength="50" />
			<aos:textfield name="mobile" fieldLabel="手机 " allowBlank="false" maxLength="50" />
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_zjcUser_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_zjcUser_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<script type="text/javascript">

			 //弹出修改参数窗口
		    function w_zjcUser_u_show() {
		        var record = AOS.selectone(g_param);
		        if (record) {
		        	w_zjcUser_u.show();
		        	f_zjcUser_u.loadRecord(record);
		        }
		    }
	
			//新增参数保存
		    function f_zjcUser_submit() {
		        AOS.ajax({
		            forms: f_zjcUser,
		            url: 'zjcUserService.saveUserInfo',
		            ok: function (data) {
		            	if(data.appcode == '1'){
		            		w_zjcUser.hide();
		                    g_param_store.reload();
		                    AOS.tip(data.appmsg);
		            	}else{
		            		AOS.err(data.appmsg);
							AOS.get('f_zjcUser.params_key').markInvalid(data.appmsg);
		            	}
		            }
		        });
		    }
			
            //查询参数列表
            function g_param_query() {
                var params = {
                		real_name: id_nkv.getValue()
                };
                g_param_store.getProxy().extraParams = params;
                g_param_store.loadPage(1);
            }
            
          //修改参数保存
            function f_zjcUser_u_submit() {
                AOS.ajax({
                    forms: f_zjcUser_u,
                    url: 'zjcUserService.updateUserInfo',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                    		w_zjcUser_u.hide();
                            g_param_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.get('f_zjcUser_u.user_id').markInvalid(data.appmsg);
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }

        </script>
</aos:onready>