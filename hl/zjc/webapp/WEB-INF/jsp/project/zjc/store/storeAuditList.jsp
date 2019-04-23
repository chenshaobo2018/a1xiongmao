<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商家列表" base="http" lib="ext">
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
			<img id="number_elc" class="app_cursor_pointer" src="" width="100%" height="650" autoScroll="true">
		</div>
		<div id="div_number_elc2" class="x-hidden">
			<img id="food_elc" class="app_cursor_pointer" src="" width="100%" height="650" autoScroll="true">
		</div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.25" />
			<aos:textfield name="mobile" fieldLabel="手机号码" columnWidth="0.25" />
			<aos:textfield name="real_name" fieldLabel="真实姓名" columnWidth="0.25" />
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.25" />
			<aos:combobox name="store_state" fieldLabel="店铺状态" emptyText="店铺状态" columnWidth="0.25">
				<aos:option value="1" display="待审核"/>
				<aos:option value="2" display="审核失败"/>
			</aos:combobox>
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="store_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_store" url="zjcStoreService.listZjcAuditStores" onrender="store_query" region="center" >
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="商家信息" />
			</aos:docked>
			<aos:column type="rowno" />
				<aos:column header="店铺ID" dataIndex="store_id" hidden="true" />
				<aos:column header="商家ID" dataIndex="user_id"  />
				<aos:column header="店铺名称" dataIndex="store_name" width="90" />
				<aos:column header="真实姓名" dataIndex="real_name"/>
				<%-- <aos:column header="所在省市" dataIndex="area_info"/> --%>
				<%-- <aos:column header="是否有视频权限" dataIndex="show_video" rendererField="show_video"/> --%>
				<aos:column header="手机号码" dataIndex="mobile"/>
				<aos:column header="身份证号" dataIndex="id_card"/>
				<aos:column header="店铺状态" dataIndex="store_state" rendererField="store_state" width="60" />
				<aos:column header="操作" rendererFn="fn_button_render" align="center"/>
		</aos:gridpanel>
		
		<aos:window id="w_store" title="商家资料" onshow="store_OnShow" layout="border" maximized="true" autoScroll="true">
			<aos:formpanel id="storeForm" layout="column" labelWidth="70" margin="5" anchor="100%" border="false" header="false" region="north" autoScroll="true">
					<%-- 隐藏变量 --%>
					<aos:hiddenfield name="store_id" />
					<aos:textfield fieldLabel="店铺名称" name="store_name" columnWidth="0.55" readOnly="true" />
					<aos:fieldset title="商家信息" labelWidth="70" columnWidth="0.55" height="160" onrender="getUserInfo">
						<aos:textfield fieldLabel="商家ID" name="user_id" columnWidth="0.55" readOnly="true" border="false"/>
						<%-- <aos:textfield fieldLabel="用户昵称" name="nickname" columnWidth="0.55" readOnly="true" border="false"/> --%>
						<aos:textfield fieldLabel="手机号码" name="mobile" columnWidth="0.55" readOnly="true"/>
						<aos:textfield fieldLabel="真实姓名" name="real_name" columnWidth="0.55" readOnly="true"/>
						<aos:textfield fieldLabel="身份证号" name="id_card" columnWidth="0.55" readOnly="true"/>
					</aos:fieldset>
					<%-- <aos:textfield fieldLabel="所属分类" name="cat_id" columnWidth="0.55" readOnly="true"/> --%>
					<aos:combobox fieldLabel="所属分类" onselect="catSelect" name="cat_id" id="cat_id" emptyText="无分类" readOnly="true"  columnWidth="0.55"  url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0"/>
					<aos:textareafield fieldLabel="店铺简介" name="store_info" columnWidth="0.99" />
					<aos:combobox fieldLabel="地址" onselect="provinceSelect" readOnly="true" name="province_id" id="province_id" emptyText="请选择省份" columnWidth="0.2"  url="zjcRegionService.getProvince"/>
					<aos:combobox id="city_id" onselect="citySelect" readOnly="true" name="city_id" queryMode="local"  emptyText="请选择市" columnWidth="0.2" url="zjcRegionService.getAddr"  />
					<aos:combobox id="area_id" onselect="areaSelect" readOnly="true" name="area_id" queryMode="local"  emptyText="请选择区" columnWidth="0.2" url="zjcRegionService.getAddr"  />
					<aos:combobox id="twon_id" name="twon_id" readOnly="true" queryMode="local"  emptyText="请选择街道" columnWidth="0.19"  url="zjcRegionService.getAddr" />
					<aos:textfield name="address_detail" columnWidth="0.2" readOnly="true"/>
					<aos:fieldset title="经纬度" labelWidth="70" columnWidth="0.6" contentEl="div_lng_lat" height="300" />
					<aos:hiddenfield name="lng" id="lng_h"/>
					<aos:hiddenfield name="lat" id="lat_h"/>
					<aos:fieldset title="营业执照电子版"  labelWidth="70" columnWidth="0.6" contentEl="div_number_elc1" height="650" autoScroll="true"/> 
					<aos:hiddenfield name="business_licence_number_elc"/> 
					<aos:fieldset title="法人身份证"  labelWidth="70" columnWidth="0.6" contentEl="div_number_elc2" height="650" autoScroll="true"/> 
					<aos:hiddenfield name="food_hygiene_img"/> 
					<aos:textfield fieldLabel="办公电话" name="office_phone" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="电子邮箱" name="contacts_email" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="联系人" name="contacts_name" columnWidth="0.55" readOnly="true"/>
					<aos:textfield fieldLabel="联系电话" name="contacts_phone" columnWidth="0.55" readOnly="true"/>
					<aos:combobox fieldLabel="视频权限" name="show_video" dicField="show_video" columnWidth="0.55" readOnly="true"/>
					<%-- <aos:combobox fieldLabel="审核结果" name="store_state" dicField="store_state" value="4" columnWidth="0.55"/> --%>
					<aos:combobox fieldLabel="审核结果" name="store_state"  value="3" columnWidth="0.55">
						<aos:option value="3" display="审核成功" />
						<aos:option value="2" display="审核失败" />
					</aos:combobox> 
					<aos:textareafield fieldLabel="审核失败原因" name="attestation_reason" columnWidth="0.55" />
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="storeForm_submit" text="保存" icon="ok.png" />
					<aos:dockeditem onclick="#w_store.close();" text="关闭" icon="close.png" />
				</aos:docked>
			</aos:window>
        
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function store_query() {
			var params = AOS.getValue('f_query');
			g_store_store.getProxy().extraParams = params;
			g_store_store.loadPage(1);
		}
		
		//按钮列转换
		function fn_button_render(value, metaData, record, rowIndex, colIndex,
				store) {
			return '<input type="button" value="审核" class="cbtn" onclick="audit();" />';
		}
		
		
	    
		//跳转到商家信息修改界面
        function store_OnShow() {
        	var record = AOS.selectone(g_store);
        	storeForm.form.reset();
            AOS.ajax({
            	params : {
            		store_id: record.data.store_id
            	},
                url: 'zjcStoreService.getStoreInfoByStoreId',
                ok: function (data) {
                	if(data.show_video == 0){
                		data.show_video = "0";
                	} else if(data.show_video == 1){
                		data.show_video = "1";
                	}
                	if(data.store_state == 1){
                		data.store_state = "3";
                	} else if(data.store_state == 2){
                		data.store_state = "2";
                	} 
                	storeForm.form.setValues(data);
                	storeForm.down('[name=cat_id]').store.reload();
                	storeForm.down('[name=cat_id]').setValue(data.cat_id);
                	
                	storeForm.down('[name=show_video]').store.reload();
                	storeForm.down('[name=show_video]').setValue(data.show_video);
                	
                	storeForm.down('[name=province_id]').store.reload();
                	storeForm.down('[name=province_id]').setValue(data.province_id);
                	//取市级设置上级id
                	city_id_store.getProxy().extraParams = {
        				parent_id : data.province_id
        			};
                	storeForm.down('[name=city_id]').store.reload();
                	storeForm.down('[name=city_id]').setValue(data.city_id);
                	//取区级设置上级id
                	area_id_store.getProxy().extraParams = {
        				parent_id : data.city_id
        			};
                	storeForm.down('[name=area_id]').store.reload();
                	storeForm.down('[name=area_id]').setValue(data.area_id);
                	//取街道设置上级id
                	twon_id_store.getProxy().extraParams = {
        				parent_id : data.area_id
        			};
                	storeForm.down('[name=twon_id]').store.reload();
                	storeForm.down('[name=twon_id]').setValue(data.twon_id);
                	
                	document.getElementById('lng').value = data.lng;
                	document.getElementById('lat').value = data.lat;
                	initialize();
                	document.getElementById("number_elc").src  = data.business_licence_number_elc;
                	document.getElementById("food_elc").src  = data.food_hygiene_img;
                	getUserInfo();
                }
            });
        }
	    
        //数据提交(表单)
        function storeForm_submit() {
        	console.log(storeForm)
            AOS.ajax({
                //只提交一个表单
                forms : storeForm,
                url : 'zjcStoreService.auditStoreInfo',
                ok : function(data) {
                    AOS.tip(data.appmsg);
                    g_store_store.reload();
                    w_store.hide();
                }
            });
        }
        
    	//查询商家管理员信息
		function getUserInfo(){
			var record = AOS.selectone(g_store);
			var store_id = record.data.store_id;
            AOS.ajax({
            	params : {
            		user_id: record.data.user_id
            	},
                url: 'zjcStoreService.getUserInfo',
                ok: function (data) {
                	data.store_id = store_id;
                	storeForm.form.setValues(data);
                }
           })
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
	  	
	</script>
</aos:onready>

<script type="text/javascript">

	//显示编辑窗口
	function audit(){
		Ext.getCmp('w_store').show();
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
    
    //初始化加载
    window.onload=function(){ 
    	//initialize();
    }
	
</script>
