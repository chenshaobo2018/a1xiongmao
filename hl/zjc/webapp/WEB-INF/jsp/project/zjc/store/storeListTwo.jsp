<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商家列表" base="http" lib="ext">
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<script type="text/javascript" src="${cxt}/static/js/api.js"></script>
	<aos:body>
		<div id="div_lng_lat" style="margin-left:10px;margin-top:10px;margin-bottom:10px" class="x-hidden">
			<input type="text" style="width:500px;" class="form-control" name="search_map" value="" id="search_map"  placeholder="在地图上搜索"/><br />
	        <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	        <div>
		        <div id="map" style="width:500px;height:320px;float:left;"></div>
		        <div><br/><br/><br/><br/>
			        &nbsp;&nbsp;经度：<input type="text" readonly name="lng" id="lng" value=""/><br/><br/>
			        &nbsp;&nbsp;纬度：<input type="text" readonly name="lat" id="lat" value=""/>
		        </div>
	        </div>
		</div>
		<div id="div_number_elc1" class="x-hidden">
			<img id="number_elc" class="app_cursor_pointer" src="${cxt}/static/image/empty_head_photo.png" width="100%" height="300">
		</div>
		<!-- 图片预览 -->
		<div id="original_img_div" >
			<img id="original_img" src="" class="app_cursor_pointer" width="450px" height="420px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv();">
		</div>
		<div id="picture-show" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage" src="" />
			</div>
		</div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="100" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
		<%-- 	<aos:textfield name="store_name" fieldLabel="商家名称" columnWidth="0.25" /> --%>
			<aos:textfield name="user_id" fieldLabel="商家ID" columnWidth="0.25" />
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.25" />
			<aos:textfield name="real_name" fieldLabel="真实姓名" columnWidth="0.25" />
			<aos:textfield name="mobile" fieldLabel="手机号码"  columnWidth="0.24" />
			<aos:textfield name="id_card" fieldLabel="身份证号"  columnWidth="0.25" />
			<aos:combobox name="store_state" fieldLabel="店铺状态" emptyText="店铺状态" columnWidth="0.25">
				<aos:option value="3" display="正常"/>
				<aos:option value="4" display="关闭"/>
			</aos:combobox>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="store_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="导出" onclick="fn_export_excel" icon="icon70.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_store" url="zjcStoreService.listZjcStores" region="center" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="商家信息" />
			</aos:docked>
			<aos:column type="rowno" />
				<aos:column header="店铺ID" dataIndex="store_id" hidden="true" />
				<aos:column header="商家ID" dataIndex="user_id" width="60"/>
				<aos:column header="店铺名称" dataIndex="store_name" width="80"/>
				<aos:column header="真实姓名" dataIndex="real_name" width="40"/>
				<aos:column header="手机号码" dataIndex="mobile" width="40"/>
				<aos:column header="身份证号" dataIndex="id_card" width="60"/>
				<%-- <aos:column header="账户余额" dataIndex="zjcUsersAccountInfoPO.wallet_quota" width="40" /> --%>
				<aos:column header="店铺状态" dataIndex="store_state" rendererField="store_state" width="40"/>
				<aos:column header="商家状态" dataIndex="is_lock" width="40" rendererFn="format_lock"/>
				<aos:column header="视频权限" dataIndex="show_video" rendererField="show_video" width="50"/>
				<aos:column header="开通终端机" dataIndex="has_terminal" rendererField="is_has_terminal" width="40"/>	
				<aos:column hidden="true" header="商家会员ID" dataIndex="user_id" width="40"/>
				<aos:column hidden="true" header="所在省市" dataIndex="area_info" width="60"/>
				<aos:column hidden="true" header="联系人" dataIndex="contacts_name" width="40"/>
				<aos:column header="签到委托收款" dataIndex="state" rendererField="depute_state" width="70"/>
				<aos:column header="注册时间" dataIndex="reg_time" width="70"/>
				<aos:column header="操作" rendererFn="fn_button_render" align="center"/>
		</aos:gridpanel>
		<!-- 商家信息编辑 -->
		<aos:window id="w_store" title="商家资料" onshow="store_OnShow" layout="border" maximized="true" autoScroll="true">
			<aos:formpanel id="storeForm" layout="column" labelWidth="100" margin="5" anchor="100%" border="false" header="false" region="north" autoScroll="true">
					<%-- 隐藏变量 --%>
					<aos:hiddenfield name="store_id"/>
					<aos:textfield fieldLabel="商家ID" name="user_id" columnWidth="0.55" readOnly="true" />
					<aos:textfield fieldLabel="店铺名称" name="store_name" columnWidth="0.55" readOnly="true" />
					<aos:textfield fieldLabel="真实姓名" name="real_name" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="手机号码" name="mobile" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="身份证号" name="id_card" columnWidth="0.55" readOnly="true"/>
					<%-- <aos:fieldset title="商家管理员信息" labelWidth="70" columnWidth="0.55" height="170" onrender="getUserInfo">
						<aos:textfield fieldLabel="用户ID" name="user_id" columnWidth="0.55" readOnly="true" border="false"/>
						<aos:textfield fieldLabel="用户昵称" name="nickname" columnWidth="0.55" readOnly="true" border="false"/>
						<aos:textfield fieldLabel="用户电话" name="mobile" columnWidth="0.55" readOnly="true"/>
						<aos:textfield fieldLabel="真实姓名" name="real_name" columnWidth="0.55" readOnly="true"/>
						<aos:textfield fieldLabel="身份证号" name="id_card" columnWidth="0.55" readOnly="true"/>
					</aos:fieldset> --%>
					<%-- <aos:textfield fieldLabel="所属分类" name="cat_id" columnWidth="0.55" readOnly="true"/> --%>
					<aos:combobox fieldLabel="所属分类" onselect="catSelect" name="cat_id" id="cat_id" emptyText="无分类"  columnWidth="0.55" readOnly="true" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0"/>
					<aos:textareafield fieldLabel="店铺简介" name="store_info" columnWidth="0.99" readOnly="true"/>
					<aos:combobox fieldLabel="地址" onselect="provinceSelect" name="province_id" id="province_id" emptyText="请选择省份" columnWidth="0.2"  url="zjcRegionService.getProvince" readOnly="true"/>
					<aos:combobox id="city_id" onselect="citySelect" name="city_id" queryMode="local"  emptyText="请选择市" columnWidth="0.2" url="zjcRegionService.getAddr"  readOnly="true"/>
					<aos:combobox id="area_id" onselect="areaSelect" name="area_id" queryMode="local"  emptyText="请选择区" columnWidth="0.2" url="zjcRegionService.getAddr"  readOnly="true"/>
					<aos:combobox id="twon_id" name="twon_id" queryMode="local"  emptyText="请选择街道" columnWidth="0.19"  url="zjcRegionService.getAddr" readOnly="true"/>
					<aos:textfield name="address_detail" columnWidth="0.2"/>
					<aos:fieldset title="经纬度" labelWidth="70" columnWidth="0.55" contentEl="div_lng_lat" height="300" />
					<aos:hiddenfield name="lng" id="lng_h"/>
					<aos:hiddenfield name="lat" id="lat_h"/>
					<%-- <aos:fieldset title="营业执照电子版"  labelWidth="70" columnWidth="0.4" contentEl="div_number_elc1" height="300" />  --%>
					<aos:textfield fieldLabel="营业执照电子版" name="business_licence_number_elc" columnWidth="0.55" readOnly="true"/> 
					<aos:fieldset title="营业执照" columnWidth="0.5" height="450"  contentEl="original_img_div" collapsible="false">
						
						<%-- <aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&juid=${juid}" /> --%>
					</aos:fieldset>
					<aos:textfield fieldLabel="法人身份证" name="food_hygiene_img" columnWidth="0.55" readOnly="true"/>
					<%-- <aos:fieldset title="上传食品卫生证" columnWidth="0.99" height="450" >
						<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&juid=${juid}" />
					</aos:fieldset> --%>
					<%-- <aos:button columnWidth="0.08" text="上传LOGO" onclick="#w_upload.show();"></aos:button> --%>
					<aos:textfield fieldLabel="联系人" name="contacts_name" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="联系电话" name="contacts_phone" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="办公电话" name="office_phone" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="电子邮箱" name="contacts_email" columnWidth="0.55" readOnly="true"/>
					
					<%-- <aos:textfield fieldLabel="视频权限" name="show_video" columnWidth="0.55" readOnly="true"/>  --%>
					<aos:combobox fieldLabel="视频权限" name="show_video" dicField="show_video" columnWidth="0.55" readOnly="true"/>
					<aos:combobox fieldLabel="店铺状态" name="store_state" dicField="store_state" columnWidth="0.55" readOnly="true"/>
					<%-- <aos:textfield fieldLabel="视频地址" name="video_address" columnWidth="0.55"/> --%>
					<%-- <aos:fieldset title="上传视频" columnWidth="0.99" height="450" >
						<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&juid=${juid}" />
					</aos:fieldset> --%>
					<%-- <aos:filefield fieldLabel="上传文件" columnWidth="0.55"></aos:filefield> --%>
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<%-- <aos:dockeditem onclick="storeForm_submit" text="保存" icon="ok.png" /> --%>
					<aos:dockeditem onclick="#w_store.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>
			
			<!-- 上传图片windows -->
			<aos:window id="w_upload" title="上传店铺LOGO"  width="800">
				<aos:formpanel id="f_upload">
					<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&juid=${juid}"/>
				</aos:formpanel>
			</aos:window>
			
			<!-- 商家会员信息编辑 -->
			<aos:window id="w_seller_info" title="商家账户信息"  onshow="seller_OnShow" >
				<aos:formpanel id="f_seller_info" width="800" layout="anchor" labelWidth="120" >
			<!-- 	会员ID、会员昵称、累积消费、可用易物券、可转易物券、会员等级、真实姓名、头像、身份证号码、手机号码、冻结、结算中心 -->
					<aos:hiddenfield name="store_id"/>
					<aos:textfield name="user_id" fieldLabel="商家ID" readOnly="true"/>
					<%-- <aos:textfield name="nickname" fieldLabel="商家昵称" readOnly="true"/>
					<!-- <aos:textfield name="total_amount" fieldLabel="累积消费" readOnly="true" />-->
					<aos:textfield name="pay_points" fieldLabel="可用易物卷" readOnly="true" /> --%>
					<aos:textfield name="make_over_integral" fieldLabel="可转券" readOnly="true" width="auto"/>
					<%-- <aos:textfield name="level" fieldLabel="会员等级" readOnly="true" width="auto"/> --%>
					<aos:textfield name="real_name" fieldLabel="真实姓名" readOnly="true"  />
					<%-- <aos:textfield name="head_pic" fieldLabel="头像" readOnly="true" /> --%>
					<aos:textfield name="id_card" fieldLabel="身份证号码" readOnly="true"/>
					<aos:textfield name="mobile"  fieldLabel="手机号码" readOnly="true"/>
					<aos:radioboxgroup fieldLabel="冻结" columns="3"  columnWidth="0.33" disabled="true">
						<aos:radiobox name="is_lock"  boxLabel="是" inputValue="1" readOnly="true"/>
						<aos:radiobox name="is_lock"  boxLabel="否" inputValue="0" readOnly="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="开通终端机 " columns="3"  columnWidth="0.33" disabled="true">
						<aos:radiobox name="has_terminal"  boxLabel="是" inputValue="1" />
						<aos:radiobox name="has_terminal"  boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<%-- <aos:dockeditem onclick="seller_info_submit" text="保存" icon="ok.png" /> --%>
					<aos:dockeditem onclick="#w_seller_info.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window> 
			
			<aos:window id="w_user_info" title="商家会员信息"  onshow="user_OnShow" >
				<aos:formpanel id="f_user_info" width="800" layout="anchor" labelWidth="120" >
			<!-- 	会员ID、会员昵称、累积消费、可用易物券、可转易物券、会员等级、真实姓名、头像、身份证号码、手机号码、冻结、结算中心 -->
					<aos:hiddenfield name="store_id"/>
					<aos:textfield name="user_id" fieldLabel="会员ID" readOnly="true"/>
					<aos:textfield name="nickname" fieldLabel="会员昵称" readOnly="true"/>
					<aos:textfield name="total_amount" fieldLabel="累积消费" readOnly="true"/>
					<aos:textfield name="pay_points" fieldLabel="可用券" readOnly="true"/>
					<aos:textfield name="make_over_integral" fieldLabel="可转券" readOnly="true" width="auto"/>
					<aos:textfield name="level" fieldLabel="会员等级" readOnly="true" width="auto"/>
					<aos:textfield name="real_name" fieldLabel="真实姓名" readOnly="true"/>
					<%-- <aos:textfield name="head_pic" fieldLabel="头像" readOnly="true"/> --%>
					<aos:textfield name="id_card" fieldLabel="身份证号码" readOnly="true"/>
					<aos:textfield name="mobile"  fieldLabel="手机号码" readOnly="true"/>
					<aos:radioboxgroup fieldLabel="冻结" columns="3"  columnWidth="0.33" disabled="true">
						<aos:radiobox name="is_lock"  boxLabel="是" inputValue="1" readOnly="true"/>
						<aos:radiobox name="is_lock"  boxLabel="否" inputValue="0" readOnly="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="开通终端机 " columns="3"  columnWidth="0.33" disabled="true">
						<aos:radiobox name="has_terminal"  boxLabel="是" inputValue="1" />
						<aos:radiobox name="has_terminal"  boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
				<%-- 	<aos:dockeditem onclick="user_info_submit" text="保存" icon="ok.png" />--%>					
				<aos:dockeditem onclick="#w_user_info.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window> 
        
        	<!-- 查看积分日志 -->
			<aos:window id="w_seller_log" title="积分日志记录"  layout="border" maximized="true" onshow="seller_log">
		      <aos:docked forceBoder="0 0 1 0" >
			            <aos:textfield name="real_name" id="real_nameed" fieldLabel="真实姓名" maxLength="10" columnWidth="0.25" readOnly="true"/>
						<aos:textfield name="make_over_integral"  id="pay_pointsed" fieldLabel="可转券" maxLength="20" columnWidth="0.25" readOnly="true"/>
						<aos:textfield name="mobile" id="mobileed" fieldLabel="手机号码" maxLength="11" columnWidth="0.25" readOnly="true" />
						<aos:textfield name="last_login" id="last_logined" fieldLabel="最后登录时间" maxLength="20"  columnWidth="0.25" readOnly="true"/>
			  </aos:docked> 
		    <aos:gridpanel  id="g_paramslog" url="zjcSellerInfoService.queryForLogPage"  forceFit="true" region="center" onrender="g_paramslog_querys"
		    onitemdblclick="log">
			<aos:column header="时间" dataIndex="time" celltip="true" width="50" />
			<aos:column header="消息类型" dataIndex="type" celltip="true" width="50" />
			<aos:column header="描述" dataIndex="descs" celltip="true" width="500" />
			</aos:gridpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="#w_seller_log.hide();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function store_query() {
			var params = AOS.getValue('f_query');
			var flag = false;
			for(var item in params){
				if(params[item] != ''){
					flag = true;
					break;
				}
			}
			if(flag){
				g_store_store.getProxy().extraParams = params;
				g_store_store.loadPage(1);
			}else{
				AOS.tip("查询条件不能为空！");
			}
		}
		
		//按钮列转换
		function fn_button_render(value, metaData, record, rowIndex, colIndex,
				store) {
			return '<input type="button" value="商家信息" class="cbtn" style="margin-right: 5px;" onclick="edit();"/>'
					+ '<input type="button" value="商家账户管理" class="cbtn" style="margin-right: 5px;" onclick="user_edit();"/>'
					+ '<input type="button" value="商家会员信息" class="cbtn" style="margin-right: 5px;" onclick="user1_edit();"/>'
					+ '<input type="button" value="日志" class="cbtn" style="margin-right: 5px;" onclick="log();"/>';
		}
		
		//跳转到商家信息修改界面
        function store_OnShow() {
        	var record = AOS.selectone(g_store);
            AOS.ajax({
            	params : {
            		store_id: record.data.store_id
            	},
                url: 'zjcStoreService.getStoreInfoByStoreId',
                ok: function (data) {
                	//设置视频权限默认值
                	if(data.show_video == 0){
                		data.show_video = "0";
                	} else if(data.show_video == 1){
                		data.show_video = "1";
                	} 
                	//设置店铺状态默认值
                	if(data.store_state == 1){
                		data.store_state = "1";
                	} else if(data.store_state == 2){
                		data.store_state = "2";
                	} else if(data.store_state == 3){
                		data.store_state = "3";
                	} else if(data.store_state == 4){
                		data.store_state = "4";
                	}
                	storeForm.form.setValues(data);
                	//设置商品分类默认值
                	storeForm.down('[name=cat_id]').store.reload();
                	storeForm.down('[name=cat_id]').setValue(data.cat_id);
                	//设置省份默认值
                	storeForm.down('[name=province_id]').store.reload();
                	storeForm.down('[name=province_id]').setValue(data.province_id);
                	//取市级设置上级id
                	city_id_store.getProxy().extraParams = {
        				parent_id : data.province_id
        			};
                	//设置市默认值
                	storeForm.down('[name=city_id]').store.reload();
                	storeForm.down('[name=city_id]').setValue(data.city_id);
                	//取区级设置上级id
                	area_id_store.getProxy().extraParams = {
        				parent_id : data.city_id
        			};
                	//设置区默认值
                	storeForm.down('[name=area_id]').store.reload();
                	storeForm.down('[name=area_id]').setValue(data.area_id);
                	//取街道设置上级id
                	twon_id_store.getProxy().extraParams = {
        				parent_id : data.area_id
        			};
                	//设置街道默认值
                	storeForm.down('[name=twon_id]').store.reload();
                	storeForm.down('[name=twon_id]').setValue(data.twon_id);
                	
                	document.getElementById('lng').value = data.lng;
                	document.getElementById('lat').value = data.lat;
                	document.getElementById("original_img").src = data.business_licence_number_elc;
       	        	document.getElementById("showImage").src = data.business_licence_number_elc;
                	//初始化地图
                	initialize();
                	document.getElementById("number_elc").src  = data.business_licence_number_elc;
                	getUserInfo();
                }
            });
        }
	    
     	//查询商家管理员信息
		function getUserInfo(){
			var record = AOS.selectone(g_store);
            AOS.ajax({
            	params : {
            		user_id: record.data.user_id
            	},
                url: 'zjcStoreService.getUserInfo',
                ok: function (data) {
                	storeForm.form.setValues(data);
                }
           })
		}
		
        //数据提交(表单)
        function storeForm_submit() {
            AOS.ajax({
                //只提交一个表单
                forms : storeForm,
                url : 'zjcStoreService.updateStoreInfo',
                ok : function(data) {
                    AOS.tip(data.appmsg);
                    g_store_store.reload();
                    w_store.hide();
                }
            });
        }
        
      	//商品分类选择事件监听函数
		function catSelect(me, records) {
			cat_id_store.load({
				callback : function(records, operation, success) {
					//这个回调里还可以根据是否查询到二级模块再去做一些事情
					if (records.length > 0) {
						AOS.edit(city_id);
					}else{
						AOS.read(city_id);
					}
				}
			});
		}
        
      	//省份选择事件监听函数
		function provinceSelect(me, records) {
			var parent_id = me.getValue();
			city_id_store.getProxy().extraParams = {
				parent_id : parent_id
			};
			city_id_store.load({
				callback : function(records, operation, success) {
					//这个回调里还可以根据是否查询到二级模块再去做一些事情
					if (records.length > 0) {
						AOS.edit(city_id);
					}else{
						AOS.read(city_id);
					}
				}
			});
		}
      	
		//市选择事件监听函数
		function citySelect(me, records) {
			var parent_id = me.getValue();
			area_id_store.getProxy().extraParams = {
				parent_id : parent_id
			};
			area_id_store.load({
				callback : function(records, operation, success) {
					//这个回调里还可以根据是否查询到二级模块再去做一些事情
					if (records.length > 0) {
						AOS.edit(area_id);
					}else{
						AOS.read(area_id);
					}
				}
			});
		}
		
		//区域选择事件监听函数
		function areaSelect(me, records) {
			var parent_id = me.getValue();
			twon_id_store.getProxy().extraParams = {
				parent_id : parent_id
			};
			twon_id_store.load({
				callback : function(records, operation, success) {
					//这个回调里还可以根据是否查询到二级模块再去做一些事情
					if (records.length > 0) {
						AOS.edit(twon_id);
					}else{
						AOS.read(twon_id);
					}
				}
			});
		}
		
		//跳转到商家会员信息修改界面
		function seller_OnShow(){
			var record = AOS.selectone(g_store);
			f_seller_info.form.reset();
            AOS.ajax({
            	params : {
            		store_id: record.data.store_id
            	},
                url: 'zjcSellerInfoService.getSellerAccountInfo',
                ok: function (data) {
                	f_seller_info.form.setValues(data[0]);
                }
            });
		}
		
		//提交商家会员信息
		function seller_info_submit(){
			AOS.ajax({
                //只提交一个表单
                forms : f_seller_info,
                url : 'zjcSellerInfoService.updateSellerInfo',
                ok : function(data) {
                    AOS.tip(data.appmsg);
                    g_store_store.reload();
                    w_seller_info.hide();
                }
            });
		}
		
		 //积分信息查询 
        function g_paramslog_querys() {
            var record = AOS.selectone(g_store);
            g_paramslog_store.getProxy().extraParams = {
				user_id : record.data.user_id
			};
            g_paramslog_store.loadPage(1); 
        } 
		 
        //跳转到商家日志修改界面
        function seller_log() {
        	var record = AOS.selectone(g_store);
            AOS.ajax({
            	params : {
            		user_id: record.data.user_id
            	},
                url: 'zjcSellerInfoService.queryForLogOne',
                ok: function (data) {
                	Ext.getCmp('real_nameed').setValue(data[0].real_name);
                    Ext.getCmp('mobileed').setValue(data[0].mobile);
                	Ext.getCmp('last_logined').setValue(data[0].last_login);
                	Ext.getCmp('pay_pointsed').setValue(data[0].zjcUsersAccountInfoPO.make_over_integral);
                	g_paramslog_querys(); 
                }
            });
        }
        
        function user_OnShow(){
			var record = AOS.selectone(g_store);
			f_seller_info.form.reset();
            AOS.ajax({
            	params : {
            		user_id: record.data.user_id
            	},
                url: 'zjcSellerInfoService.getSellerUserInfo',
                ok: function (data) {
                	f_user_info.form.setValues(data);
                }
            });
		}
        
     	 //格式化商家状态
        function format_lock(value, metaData, record, rowIndex, colIndex,store){
			var is_lock = record.data.is_lock;
			if(is_lock == 1){//禁用
				return '<div style="color:red">停用</div>';
			} else {
				return '<div style="color:green">启用</div>';
			}
		}
	</script>
</aos:onready>

<script type="text/javascript">

	//改变状态
	function changeState(obj){
		var record = AOS.selectone(Ext.getCmp('g_store'));
    	if(record.data.store_state == 3){
    		record.data.store_state =4;
    		record.commit();
    	}else if(record.data.store_state == 4){
    		record.data.store_state =3;
    		record.commit();
    	} else {
    		AOS.tip("审核中，不能改变状态");
    		return false;
    	}
    	//修改状态
    	 AOS.ajax({
             //只提交选中行状态信息
             params: {
            	 store_id: record.data.store_id,
            	 store_state:record.data.store_state
            	},
             url : 'zjcStoreService.changeStoreState',
             ok : function(data) {
            	 //刷新选中行
    			Ext.getCmp('g_store').getView().refresh();
             }
         }); 
	}
	//显示商家信息编辑窗口
	function edit(){
		Ext.getCmp('w_store').show();
	}
	
	//显示商家会员信息编辑窗口
	function user_edit(){
		Ext.getCmp('w_seller_info').show();
	}
	
	//显示商家日志窗口
	function log(){
		Ext.getCmp('w_seller_log').show();
	}
	
	// 百度地图API功能S
  	var point = '';
    var mp = '';
    var marker = '';

    function G(id) {
        return document.getElementById(id);
    }
    //建立地图
  	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {
                "input" : "search_map",
                "location" : mp
            });

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
        setPlace();
    });

    function setPlace(){
        mp.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            mp.centerAndZoom(pp, 18);
            mp.addOverlay(new BMap.Marker(pp));    //添加标注
            setMarker(pp);
            setLngAndLat(pp.lng,pp.lat);
        }
        var local = new BMap.LocalSearch(mp, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }
	
    //初始化地图
    function initialize() {
    	var lng = document.getElementById('lng').value;
    	var lat = document.getElementById('lat').value;
        point = new BMap.Point(lng, lat);
        mp = new BMap.Map('map');
        mp.centerAndZoom(point, 18);
        mp.enableScrollWheelZoom(true);
        mp.addEventListener("click", function(e){
            setLngAndLat(e.point.lng, e.point.lat)
            setMarker(new BMap.Point(e.point.lng, e.point.lat));
        });
        setMarker(point);

    }

    //给坐标点赋值
    function setLngAndLat(lng,lat){
        document.getElementById('lng').value = lng;
        document.getElementById('lat').value = lat;
        Ext.getCmp("lng_h").setValue(lng);
        Ext.getCmp("lat_h").setValue(lat);
    }

    //获取坐标点
    function setMarker(point){
        mp.removeOverlay(marker);
        marker = new BMap.Marker(point);
        marker.enableDragging();
        marker.addEventListener("dragend", function(e){
            setLngAndLat(e.point.lng, e.point.lat)
        })
        mp.addOverlay(marker);

    }
    
    //导出店铺列表excel
	function fn_export_excel(){
		var params = AOS.getValue('f_query'); 
		//juid需要再这个页面的初始化方法中赋值,这里才引用得到
		AOS.file('do.jhtml?router=zjcStoreService.exportStoreInfo&juid=${juid}&store_name='+encodeURI(encodeURI(params.store_name))  +'&user_id='+params.user_id
				+ '&mobile=' + params.mobile +'&real_name=' + encodeURI(encodeURI(params.real_name)) +'&id_card=' + params.id_card + '&store_state=' + params.store_state);
	}
    
    //初始化加载
    window.onload=function(){ 
    	//initialize();
    }
	
    function showdiv(){
 	   $("#picture-show").show();
    }
    function closediv(){
 	   $("#picture-show").hide();
    }
    
  //显示商家会员信息编辑窗口
	function user1_edit(){
		Ext.getCmp('w_user_info').show();
	}
</script>
