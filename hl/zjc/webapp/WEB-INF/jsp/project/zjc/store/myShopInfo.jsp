<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="自营店信息" base="http" lib="ext">
	<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=bXNmcWSY1iB4Epz1x0MUcUcQ"></script> -->
	<script type="text/javascript" src="${cxt}/static/js/api.js"></script>
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/cropper.min.css" >
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/sitelogo.css" >
	<script src="${cxt}/static/zjc/js/bootstrap.min.js"></script>
	<script src="${cxt}/static/js/upload/cropper.js"></script>
	<script src="${cxt}/static/js/upload/sitelogo.js"></script>
	<script src="${cxt}/static/js/upload/html2canvas.min.js"></script>
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
		
		<div id="file_upload">
			<form class="avatar-form" id="upload-form-file" enctype="multipart/form-data">
				<div class="avatar-upload">
					<button class="btn btn-danger"  type="button" onclick="$('input[id=avatarInputFile]').click();">选择文件</button>
					<input class="avatar-input hide" id="avatarInputFile" name="fileUpload" type="file">
				</div>
			</form>
		</div>
		
		<div id="original_img_div" class="x-hidden" >
			<img id="business_licence_number_elc" src="" class="app_cursor_pointer" width="800px" height="450px" style="margin-left: 10px;display: block; float: left;" onclick="showdiv2();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_goods_picture_upload();">上传图片</button>
		</div>
		<div id="picture-show" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv2();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage" src="" />
			</div>
		</div>
		<div id="original_img_div_update" class="x-hidden" >
			<img id="store_logo" src="" class="app_cursor_pointer" width="800px" height="450px" style="margin-left: 10px;display: block; float: left;" onclick="showdiv3();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_goods_picture_update_upload();">上传图片</button>
		</div>
		<div id="picture-show-update" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv3();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage-update" src="" />
			</div>
		</div>
	</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="fit" autoScroll="true">
	<aos:panel id="g_store" title="商家资料" autoScroll="true">
			<aos:formpanel id="storeForm" title="商家资料" onrender="storeForm_load" layout="column" labelWidth="70" margin="5" anchor="100%"  header="false" region="north" autoScroll="true">
				<%-- 隐藏变量 --%>
				<aos:hiddenfield name="store_id"/>
				<aos:textfield fieldLabel="商家名称" name="store_name" columnWidth="0.55"/>
				<aos:textareafield fieldLabel="店铺简介" name="store_info" columnWidth="0.99" />
				<aos:combobox fieldLabel="地址" onselect="provinceSelect" name="province_id" id="province_id" emptyText="请选择省份" columnWidth="0.2"  url="zjcRegionService.getProvince"/>
				<aos:combobox id="city_id" onselect="citySelect" name="city_id" queryMode="local"  emptyText="请选择市" columnWidth="0.2" url="zjcRegionService.getAddr"  />
				<aos:combobox id="area_id" onselect="areaSelect" name="area_id" queryMode="local"  emptyText="请选择区" columnWidth="0.2" url="zjcRegionService.getAddr"  />
				<aos:combobox id="twon_id" name="twon_id" queryMode="local"  emptyText="请选择街道" columnWidth="0.19"  url="zjcRegionService.getAddr" />
				<aos:textfield name="address_detail" columnWidth="0.2"/>
				<aos:fieldset title="经纬度" labelWidth="70" columnWidth="0.55" contentEl="div_lng_lat" height="300" />
				<aos:hiddenfield name="lng" id="lng_h"/>
				<aos:hiddenfield name="lat" id="lat_h"/>
				<aos:fieldset title="营业执照" columnWidth="0.99" height="480" width="900" contentEl="original_img_div">
				</aos:fieldset>
				<aos:hiddenfield fieldLabel="营业执照" name="business_licence_number_elc" />
				<aos:fieldset title="店铺LOGO" columnWidth="0.99" height="480" width="900" contentEl="original_img_div_update">
				</aos:fieldset>
				<aos:hiddenfield fieldLabel="店铺LOGO" name="store_logo" />
				<aos:textfield fieldLabel="办公电话" name="office_phone" columnWidth="0.55" />
				<aos:textfield fieldLabel="电子邮箱" name="contacts_email" columnWidth="0.55" />
				<aos:textfield fieldLabel="联系人" name="contacts_name" columnWidth="0.55" />
				<aos:textfield fieldLabel="联系电话" name="contacts_phone" columnWidth="0.55" />
				<aos:textfield fieldLabel="开户行" name="bank_account_name" columnWidth="0.55" />
				<aos:textfield fieldLabel="银行账户" name="bank_account_number" columnWidth="0.55" />
				<aos:textfield fieldLabel="视频地址" name="video_address" columnWidth="0.55"/>
				<aos:fieldset title="上传视频" labelWidth="70" columnWidth="0.55" contentEl="file_upload" />
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem text="保存数据" icon="ok.png" onclick="storeForm_submit" />
				<aos:dockeditem text="刷新" icon="refresh.png" onclick="store_refresh"/>
			</aos:docked>
		</aos:panel>
		
		<aos:window id="goods_picture_upload" title="上传营业执照"  width="800">
			<aos:formpanel id="f_upload">
				<aos:iframe id="iframe2" columnWidth="0.8" height="450" src="do.jhtml?router=homeService.newGoodsUpload&inputName=business_licence_number_elc&windowsId=goods_picture_upload&paramkey=goods_picture_prop&juid=${juid}"/>
			</aos:formpanel>
		</aos:window>
		<aos:window id="goods_picture_update_upload" title="上传店铺LOGO"  width="800">
			<aos:formpanel id="f_upload_logo">
				<aos:iframe id="iframe" columnWidth="0.8" height="450" src="do.jhtml?router=homeService.newGoodsUpload&updategoods=1&inputName=store_logo&windowsId=goods_picture_update_upload&paramkey=goods_picture_prop&juid=${juid}"/>
			</aos:formpanel>
		</aos:window>
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
                url: 'zjcStoreService.getStoreInfo',
                ok: function (data) {
                	
                	storeForm.form.setValues(data);
                	
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
                	//获取营业执照
                	document.getElementById("business_licence_number_elc").src = data.business_licence_number_elc;
                	document.getElementById("showImage").src = data.business_licence_number_elc;
                	//获取店铺LOGO
                	document.getElementById("store_logo").src = data.store_logo;
                	document.getElementById("showImage-update").src = data.store_logo;
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
	  	
		$("#avatarInputFile").on('change', function(e) {
			
			//获取视频名称
			var videoName = this.files[0].name;
			if (!checkTv(videoName)) {
				AOS.tip('请选择正确的文件!');
				return false;
			} 
			var form=document.getElementById("upload-form-file");
	        var fd =new FormData(form);
	        $.ajax({
	             url: "fileUpload.jhtml?juid=${juid}",
	             type: "POST",
	             processData: false,  // 告诉jQuery不要去处理发送的数据
	             contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
	     		 data: fd,
	             success: function(data){
	               var fileobj = JSON.parse(data);
	               $('input[name="video_address"]').val(fileobj.path)
	             }
	        });
		}); 
		
		//判断是否是视频文件
		function checkTv(tv_id){
			var index= tv_id.indexOf("."); //得到"."在第几位
			tv_id=tv_id.substring(index); //截断"."之前的，得到后缀
			    if(tv_id!=".mp4"&&tv_id!=".rmvb"&&tv_id!=".avi"&&tv_id!=".ts"){ //根据后缀，判断是否符合视频格式
			       return false; 
			    }
			return true;
		}
		
	    //初始化加载
	    window.onload=function(){ 
	    	//初始加载window
	    	g_store.show();
	    }
        </script>
</aos:onready>
 <script type="text/javascript">
 	function w_licence_show(){
 		Ext.getCmp('w_licence').show();
 	}
 	function w_store_logo_show(){
 		Ext.getCmp('w_store_logo').show();
 	}
    function showdiv2(){
 	   $("#picture-show").show();
    }
    
    function closediv2(){
 	   $("#picture-show").hide();
    }
    
    function f_goods_picture_upload(){
		Ext.getCmp('goods_picture_upload').show();
	}
    
    function showdiv3(){
 	   $("#picture-show-update").show();
    }
    
    function closediv3(){
 	   $("#picture-show-update").hide();
    }
    function f_goods_picture_update_upload(){
		Ext.getCmp('goods_picture_update_upload').show();
	}
</script>