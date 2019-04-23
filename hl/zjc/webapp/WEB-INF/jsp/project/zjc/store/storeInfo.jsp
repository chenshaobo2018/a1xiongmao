<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商家资料" base="http" lib="ext">
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<script type="text/javascript" src="${cxt}/static/js/api.js"></script>
	<aos:body>
		<div id="div_lng_lat" style="margin-left:10px;margin-top:10px;margin-bottom:10px" class="x-hidden"  >
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
		<div id="div_number_elc1" class="x-hidden" >
			<img id="number_elc" class="app_cursor_pointer" src="${cxt}/static/image/empty_head_photo.png" width="100%" height="300">
		</div>
	</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="border"  autoScroll="true">
		<aos:window id="g_store" title="商家资料"  layout="border" maximized="true"  autoScroll="true">
			<aos:formpanel id="storeForm" title="商家资料" onrender="storeForm_load" layout="column" labelWidth="70" margin="5" anchor="100%"  header="false" region="north" autoScroll="true">
				<%-- 隐藏变量 --%>
				<aos:hiddenfield name="store_id"/>
				<aos:textfield fieldLabel="商家名称" name="store_name" columnWidth="0.55" readOnly="true"/>
				<%-- <aos:textfield fieldLabel="所属分类" name="cat_id" columnWidth="0.55" readOnly="true"/> --%>
				<aos:combobox fieldLabel="所属分类" onselect="catSelect" name="cat_id" id="cat_id" emptyText="无分类" readOnly="true" columnWidth="0.55"  url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0"/>
				<aos:textareafield fieldLabel="店铺简介" name="store_info" columnWidth="0.99" />
				<aos:combobox fieldLabel="地址" onselect="provinceSelect" name="province_id" id="province_id" emptyText="请选择省份" columnWidth="0.2"  url="zjcRegionService.getProvince"/>
				<aos:combobox id="city_id" onselect="citySelect" name="city_id" queryMode="local"  emptyText="请选择市" columnWidth="0.2" url="zjcRegionService.getAddr"  />
				<aos:combobox id="area_id" onselect="areaSelect" name="area_id" queryMode="local"  emptyText="请选择区" columnWidth="0.2" url="zjcRegionService.getAddr"  />
				<aos:combobox id="twon_id" name="twon_id" queryMode="local"  emptyText="请选择街道" columnWidth="0.19"  url="zjcRegionService.getAddr" />
				<aos:textfield name="address_detail" columnWidth="0.2"/>
				<aos:fieldset title="经纬度" labelWidth="70" columnWidth="0.6" contentEl="div_lng_lat" height="300" />
				<aos:hiddenfield name="lng" id="lng_h"/>
				<aos:hiddenfield name="lat" id="lat_h"/>
				<aos:fieldset title="营业执照电子版"  labelWidth="70" columnWidth="0.39" contentEl="div_number_elc1" height="300" /> 
				<aos:hiddenfield name="business_licence_number_elc"/> 
				<%-- <aos:filefield fieldLabel="店铺LOGO" name="store_logo" columnWidth="0.55" on></aos:filefield> --%>
				<%-- <aos:textfield fieldLabel="店铺LOGO" name="store_logo" columnWidth="0.55" /> --%>
				<aos:textfield name="store_logo" fieldLabel="店铺LOGO"  maxLength="100"  columnWidth="0.55"/>
				<aos:fieldset title="上传店铺LOGO" columnWidth="0.99" height="450" >
					<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&juid=${juid}" />
				</aos:fieldset>
				<%-- <aos:button columnWidth="0.08" text="上传LOGO" onclick="#w_upload.show();"></aos:button> --%>
				<aos:textfield fieldLabel="办公电话" name="office_phone" columnWidth="0.55" />
				<aos:textfield fieldLabel="电子邮箱" name="contacts_email" columnWidth="0.55" />
				<aos:textfield fieldLabel="联系人" name="contacts_name" columnWidth="0.55" />
				<aos:textfield fieldLabel="联系电话" name="contacts_phone" columnWidth="0.55" />
				<aos:combobox fieldLabel="视频权限" name="show_video" dicField="show_video" columnWidth="0.55"/> 
				<aos:textfield fieldLabel="视频地址" name="video_address" columnWidth="0.55"/>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem text="保存数据" icon="ok.png" onclick="storeForm_submit" />
				<aos:dockeditem text="刷新" icon="refresh.png" onclick="store_refresh"/>
			</aos:docked>
			</aos:window>
			<aos:window id="w_upload" title="上传店铺LOGO"  width="800">
				<aos:formpanel id="f_upload">
					<aos:iframe columnWidth="0.8" height="450" src="do.jhtml?router=homeService.upload&juid=${juid}"/>
				</aos:formpanel>
			</aos:window>
			<%-- </aos:tab>--%> 
	</aos:viewport>
	<script type="text/javascript">

		// 百度地图API功能
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
	    
	  	//数据加载(表单)
        function storeForm_load() {
            AOS.ajax({
                url: 'zjcStoreService.getStoreInfo&type=1',
                ok: function (data) {
                	if(data.show_video == 0){
                		data.show_video = "0";
                	} else if(data.show_video == 1){
                		data.show_video = "1";
                	} 
                	storeForm.form.setValues(data);
                	storeForm.down('[name=cat_id]').store.reload();
                	storeForm.down('[name=cat_id]').setValue(data.cat_id);
                	
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
                }
            });
        }
	    
        //数据提交(表单)
        function storeForm_submit() {
            AOS.ajax({
                //只提交一个表单
                forms : storeForm,
                url : 'zjcStoreService.updateStoreInfo',
                ok : function(data) {
                    AOS.tip(data.appmsg);
                }
            });
        }
        
        //刷新界面
        function store_refresh(){
        	storeForm_load();
        }
        
      	//给select赋初值
        function  evaluate_region(id,idStr) {
      		console.log("hhh:"+Ext.getCmp('city_id'));
            AOS.ajax({
            	params: {
					id: id
            	},
                url: 'zjcRegionService.getRegion',
                ok: function (data) {

                	Ext.getCmp("'"+idStr+"'").setValue(data.id);
                	Ext.getCmp("'"+idStr+"'").setRawValue(data.name);
                	
                }
            });
        }
      	
        
      	//商品分类选择事件监听函数
		function catSelect(me, records) {
			/* var parent_id = me.getValue();
			city_id_store.getProxy().extraParams = {
				parent_id : parent_id
			}; */
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
						AOS.edit( );
					}else{
						AOS.read(twon_id);
					}
				}
			});
		}
		
		function change_logo(){
			w_upload.hide();
		}
	  	
	    //初始化加载
	    window.onload=function(){ 
	    	//初始加载window
	    	g_store.show();
	    }
        </script>
</aos:onready>