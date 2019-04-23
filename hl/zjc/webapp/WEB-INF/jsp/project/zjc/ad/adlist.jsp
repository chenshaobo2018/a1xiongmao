<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="广告列表" base="http" lib="ext">
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/sitelogo.css" >
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body id="adbody">
		<!-- 新增 -->
		<div id="picture-show" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv1();" >
			<div  style="width:500px; height:500px;margin-left: 5%; margin-top: 5%;  z-index: 20001;" >
				<img id="showImage" src="" />
			</div>
		</div>
		<div id="file_upload">
			<!-- 图片预览 -->
			<div id="original_img_div"  >
				<img id="original_img" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv1();">
			</div>
			<form class="avatar-form" id="upload-form-file" enctype="multipart/form-data">
				<div class="avatar-upload">
					<button class="btn btn-danger" style="margin-top: 25px;margin-left: 20px;"  type="button" onclick="$('#imgUpload').click();">选择图片</button>
					<input class="avatar-input hide" id="imgUpload" name="imgUpload" type="file">
				</div>
			</form>
		</div>
		<!-- 修改 -->
		<div id="picture-show-update" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv2();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage-update" src="" />
			</div>
		</div>
		<div id="file_upload_update">
			<div id="original_img_div_update">
				<img id="original_img_update" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv2();">
			</div>
			<form class="avatar-form" id="update-form-file" enctype="multipart/form-data">
				<div class="avatar-upload">
					<button class="btn btn-danger" style="margin-top: 25px;margin-left: 20px;"  type="button" onclick="$('#imgUpload-update').click();">选择图片</button>
					<input class="avatar-input hide" id="imgUpload-update" name="imgUpload" type="file">
				</div>
			</form>
		</div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_ad" url="zjcAdService.listAd" onrender="g_ad_query"  forceFit="true"
			onitemdblclick="w_ad_u_show">
			<aos:menu>
				<aos:menuitem text="新增" onclick="#w_ad.show();" icon="add.png" />
				<aos:menuitem text="修改" onclick="w_ad_u_show" icon="edit.png" />
				<aos:menuitem text="删除" onclick="g_ad_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_ad_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="#w_ad.show();" text="新增" icon="add.png" />
				<aos:dockeditem onclick="w_ad_u_show" text="修改" icon="edit.png" />
				<aos:dockeditem onclick="g_ad_del" text="删除" icon="del.png" />
				<aos:dockeditem onclick="g_ad_up" text="上升" icon="up.png" />
				<aos:dockeditem onclick="g_ad_down" text="下降" icon="down.png" />
				<aos:dockeditem xtype="tbseparator"/>
				<aos:triggerfield emptyText="广告名称" id="ad_name" onenterkey="g_ad_query"
					trigger1Cls="x-form-search-trigger" onTrigger1Click="g_ad_query" width="180" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="广告id" dataIndex="ad_id" celltip="true"  />
			<aos:column header="广告位置ID" dataIndex="pid" celltip="true" />
			<aos:column header="广告位置名称" dataIndex="position_name" celltip="true" />
			<aos:column header="跳转方式" dataIndex="jump_type" rendererField="jump_type" />
			<aos:column header="关联ID" dataIndex="relation_id" celltip="true" />
			<aos:column header="广告名称" dataIndex="ad_name" celltip="true" />
			<aos:column header="链接地址" dataIndex="ad_link" celltip="true" />
			<aos:column header="图片地址" dataIndex="ad_code" celltip="true" />
			<aos:column header="是否显示" dataIndex="enabled" rendererField="is" />
			<aos:column header="排序" dataIndex="orderby" celltip="true" />
			<aos:column header="生效时间" dataIndex="start_time" celltip="true" />
			<aos:column header="结束时间" dataIndex="end_time" celltip="true" />
		</aos:gridpanel>
	</aos:viewport>
	<aos:window id="w_ad" title="新增广告" onshow="#AOS.reset(f_ad);" >
		<aos:formpanel id="f_ad" width="800" layout="column" labelWidth="65" >
			<aos:combobox fieldLabel="广告位置" name="pid" editable="true" columnWidth="0.33" url="zjcAdService.listPositionComboBoxData" allowBlank="false"/>
			<aos:combobox fieldLabel="跳转方式" name="jump_type" dicField="jump_type" editable="false"  columnWidth="0.33" allowBlank="false"/>
			<%-- <aos:textfield name="relation_id" fieldLabel="关联ID"  maxLength="50" columnWidth="0.25" /> --%>
			<aos:textfield name="ad_name" fieldLabel="广告名称"  columnWidth="0.33" allowBlank="false"/>
			<aos:textfield name="ad_link" fieldLabel="链接地址"  columnWidth="0.33" />
			<aos:datetimefield name="start_time" editable="false" fieldLabel="投放时间" columnWidth="0.33" allowBlank="false"/>
			<aos:datetimefield name="end_time" editable="false" fieldLabel="结束时间" columnWidth="0.33" allowBlank="false"/>
			<aos:radioboxgroup fieldLabel="是否显示" columns="3" columnWidth="0.33">
				<aos:radiobox name="enabled" boxLabel="是" inputValue="1" />
				<aos:radiobox name="enabled" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="是否开启浏览器新窗口" labelWidth="130" columns="6" columnWidth="0.66">
				<aos:radiobox name="target" boxLabel="是" inputValue="1" />
				<aos:radiobox name="target" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:textfield name="bgcolor" fieldLabel="背景颜色"  maxLength="50" columnWidth="0.33"/>
			<aos:numberfield fieldLabel="排序" name="orderby" columnWidth="0.33" />
			<aos:textfield name="relation_id" fieldLabel="关联ID"  maxLength="50" columnWidth="0.33"/>
			<aos:fieldset title="添加人信息"  columnWidth="1" border="false"  collapsible="false">
				<aos:textfield name="link_man" fieldLabel="姓名"  maxLength="50" columnWidth="0.33"/>
				<aos:textfield name="link_email" fieldLabel="邮箱"  maxLength="50"  columnWidth="0.33"/>
				<aos:textfield name="link_phone" fieldLabel="联系电话"  maxLength="50" columnWidth="0.33"/>
			</aos:fieldset>
			<aos:hiddenfield name="ad_code" fieldLabel="图片地址" id="ad_code"/>
			<aos:fieldset title="上传图片" labelWidth="70" columnWidth="0.99" contentEl="file_upload" collapsible="false" border="false">
			</aos:fieldset>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_ad.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="w_ad_u" title="修改广告">
		<aos:formpanel id="f_ad_u" width="800" layout="column" labelWidth="65">
			<aos:hiddenfield name="ad_id" />
			<aos:combobox fieldLabel="广告位置" name="pid" editable="false" columnWidth="0.33" url="zjcAdService.listPositionComboBoxData" />
			<aos:combobox fieldLabel="跳转方式" name="jump_type" dicField="jump_type" editable="false"  columnWidth="0.33" />
			<aos:textfield name="ad_name" fieldLabel="广告名称"  columnWidth="0.33"/>
			<aos:textfield name="ad_link" fieldLabel="链接地址"  columnWidth="0.33"/>
			<aos:datetimefield name="start_time" editable="false" fieldLabel="投放时间" columnWidth="0.33" />
			<aos:datetimefield name="end_time" editable="false" fieldLabel="结束时间" columnWidth="0.33" />
			<aos:radioboxgroup fieldLabel="是否显示" columns="3" columnWidth="0.33">
				<aos:radiobox name="enabled" boxLabel="是" inputValue="1" />
				<aos:radiobox name="enabled" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:radioboxgroup fieldLabel="是否开启浏览器新窗口" labelWidth="130" columns="6" columnWidth="0.66">
				<aos:radiobox name="target" boxLabel="是" inputValue="1" />
				<aos:radiobox name="target" boxLabel="否" inputValue="0" />
			</aos:radioboxgroup>
			<aos:textfield name="bgcolor" fieldLabel="背景颜色"  maxLength="50" columnWidth="0.33"/>
			<aos:numberfield fieldLabel="排序" name="orderby" columnWidth="0.33" />
			<aos:textfield name="relation_id" fieldLabel="关联ID"  maxLength="50" columnWidth="0.33"/>
			<aos:fieldset title="添加人信息"  columnWidth="1" border="false"  collapsible="false">
				<aos:textfield name="link_man" fieldLabel="姓名"  maxLength="50" columnWidth="0.33"/>
				<aos:textfield name="link_email" fieldLabel="邮箱"  maxLength="50"  columnWidth="0.33"/>
				<aos:textfield name="link_phone" fieldLabel="联系电话"  maxLength="50" columnWidth="0.33"/>
			</aos:fieldset>
			<aos:hiddenfield name="ad_code" fieldLabel="图片地址" id="ad_code2"/>
			<aos:fieldset title="上传图片" labelWidth="70" columnWidth="0.99" contentEl="file_upload_update" collapsible="false" border="false">
			</aos:fieldset>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_ad_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_ad_u.hide();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<script type="text/javascript">

            //弹出修改广告窗口
            function w_ad_u_show() {
                var record = AOS.selectone(g_ad);
                if (record) {
                	f_ad_u.down("[name='pid']").store.reload();
                	f_ad_u.down("[name='pid']").setValue(record.data.position_name);
                    record.data.jump_type +="";
                    w_ad_u.show();
                    f_ad_u.form.setValues(record.data);
                    f_ad_u.loadRecord(record);
                    document.getElementById("showImage-update").src = record.data.ad_code;
	                document.getElementById("original_img_update").src = record.data.ad_code;
                }
            }

            //查询广告列表
            function g_ad_query() {
                var params = {
                		ad_name: ad_name.getValue()
                };
                g_ad_store.getProxy().extraParams = params;
                g_ad_store.loadPage(1);
            }

            //删除广告
            function g_ad_del() {
                var selectionIds = AOS.selection(g_ad, 'ad_id');
                if (AOS.empty(selectionIds)) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var rows = AOS.rows(g_ad);
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'zjcAdService.deleteAd',
                        params: {
                            aos_rows: selectionIds
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_ad_store.reload();
                        }
                    });
                });

            }

            //新增广告保存
            function f_ad_submit() {
            	Ext.getCmp("ad_code").setValue(original_img_update.src); 
            	var ad_code = Ext.getCmp("ad_code").getValue();
            	if(ad_code == null || ad_code == ''){
            		AOS.tip('广告必须添加图片！');
            		return;
            	}
                AOS.ajax({
                    forms: f_ad,
                    url: 'zjcAdService.saveAd',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_ad.hide();
                            g_ad_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
                    		AOS.err(data.appmsg);
    						AOS.get('f_ad.ad_name').markInvalid(data.appmsg);
                    	}
                    }
                });
            }

            //修改广告保存
            function f_ad_u_submit() {
            	Ext.getCmp("ad_code2").setValue(original_img_update.src); 
            	var ad_code = Ext.getCmp("ad_code2").getValue();
            	if(ad_code == null || ad_code == ''){
            		AOS.tip('广告必须添加图片！');
            		return;
            	}
                AOS.ajax({
                    forms: f_ad_u,
                    url: 'zjcAdService.updateAd',
                    ok: function (data) {
                    	if(data.appcode == '1'){
                            w_ad_u.hide();
                            g_ad_store.reload();
                            AOS.tip(data.appmsg);
                    	}else{
    						AOS.get('f_ad_u.ad_name').markInvalid(data.appmsg);
    						AOS.err(data.appmsg);
                    	}
                    }
                });
            }
            
            function g_ad_down(){
            	var selectionId = AOS.selection(g_ad, 'ad_id');
            	var rows = AOS.rows(g_ad);
                if (AOS.empty(selectionId)) {
                    AOS.tip('移动前请先选中数据。');
                    return;
                }else if(rows>1){
                	AOS.tip('只能移动一条数据。');
                	return;
                }
                console.log(selectionId);
                AOS.ajax({
                    url: 'zjcAdService.upOrDownAd',
                    params: {
                    	ad_id: selectionId,
                        upOrDown:"0"
                    },
                    ok: function (data) {
                        AOS.tip(data.appmsg);
                        g_ad_store.reload();
                    }
                });
            }
            
			function g_ad_up(){
				var selectionId = AOS.selection(g_ad, 'ad_id');
            	var rows = AOS.rows(g_ad);
                if (AOS.empty(selectionId)) {
                    AOS.tip('移动前请先选中数据。');
                    return;
                }else if(rows>1){
                	AOS.tip('只能移动一条数据。');
                	return;
                }
                console.log(selectionId);
                AOS.ajax({
                    url: 'zjcAdService.upOrDownAd',
                    params: {
                    	ad_id: selectionId,
                        upOrDown:"1"
                    },
                    ok: function (data) {
                        AOS.tip(data.appmsg);
                        g_ad_store.reload();
                    }
                });
            }
			//新增图片上传部分
			$("#imgUpload").on('change', function(e) {
				var filemaxsize = 1024 * 5;//5M
				var target = $(e.target);
				var Size = target[0].files[0].size / 1024;
				if (Size > filemaxsize) {
					AOS.tip('图片过大，请重新选择!');
					return false;
				}
				if (!this.files[0].type.match(/image.*/)) {
					AOS.tip('请选择正确的图片!');
					return false;
				} 
				var form=document.getElementById("upload-form-file");
		        var fd =new FormData(form);
		        uploadFunction(fd,"ad_code","showImage","original_img");
			}); 
			//修改图片上传部分
			$("#imgUpload-update").on('change', function(e) {
				var filemaxsize = 1024 * 5;//5M
				var target = $(e.target);
				var Size = target[0].files[0].size / 1024;
				if (Size > filemaxsize) {
					AOS.tip('图片过大，请重新选择!');
					return false;
				}
				if (!this.files[0].type.match(/image.*/)) {
					AOS.tip('请选择正确的图片!');
					return false;
				} 
				var form=document.getElementById("update-form-file");
		        var fd =new FormData(form);
		        uploadFunction(fd,"ad_code","showImage-update","original_img_update");
			}); 
			//调用后台图片上传
			function uploadFunction(fd,name,bigImgId,smallImgID){
				 $.ajax({
		             url: "uploadImage.jhtml?juid=${juid}",
		             type: "POST",
		             processData: false,  // 告诉jQuery不要去处理发送的数据
		             contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
		     		 data: fd,
		             success: function(data){
		               var fileobj = JSON.parse(data);
		               $('input[name="' + name + '"]').val(fileobj.path)
		               document.getElementById(bigImgId).src = fileobj.path;
		               document.getElementById(smallImgID).src = fileobj.path;
		             }
		        });
			}
        </script>
</aos:onready>
<script type="text/javascript">
	function showdiv1(){
	   $("#picture-show").show();
	}
	function closediv1(){
	   $("#picture-show").hide();
	}
	function showdiv2(){
	   $("#picture-show-update").show();
	}
	function closediv2(){
	   $("#picture-show-update").hide();
	}
</script>