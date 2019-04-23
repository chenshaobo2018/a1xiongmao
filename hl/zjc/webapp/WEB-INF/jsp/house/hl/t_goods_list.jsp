<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<%-- <aos:html>
<aos:head title="材料信息">
	<aos:include lib="ext" />
	<aos:base href="/HtService" />
</aos:head>
<aos:body>
</aos:body>
</aos:html> --%>
<aos:html title="材料信息" base="http" lib="ext">
  <link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/sitelogo.css" >
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<aos:body>
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

	<aos:viewport layout="border">
		<aos:formpanel id="_f_query" layout="column" labelWidth="70" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="material_name" fieldLabel="商品名称" columnWidth="0.25" />
			
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_goods_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(_f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
			<aos:docked dock="bottom" ui="footer" margin="0 0 0 700">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="新增材料" onclick="_w_add_show()" icon="add.png" />
				<aos:dockeditem text="修改" onclick="_w_update_showgoods();" icon="edit.png" />
				<aos:dockeditem text="删除" onclick="_g_goods_del();" icon="del.png" />
			<%-- 	<aos:dockeditem text="导出" icon="icon70.png" onclick="fn_xls" /> --%>
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>
		
		<aos:gridpanel id="_g_goods" url="HtController.getT_goodsPOList" onrender="_g_goods_query" region="center" pageSize="20">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="材料信息" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
					<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" />
			<aos:column header="名称" dataIndex="material_name" />
			<aos:column header="分类" dataIndex="type_name" />
			<aos:column header="单位" dataIndex="unit" />
			<%-- <aos:column header="供应商" dataIndex="supplier_name" /> --%>
			<aos:column header="商品备注" dataIndex="note" />
			<aos:column header="创建时间" dataIndex="createTime" />
			<aos:column header="操作" rendererFn="fn_button_render" align="center" width="50" />
		</aos:gridpanel>

		<aos:window id="_w_add_goods" title="材料详情" >
			<aos:formpanel id="_f_goods" width="700" layout="column" labelWidth="70" columnWidth="1">
				<aos:hiddenfield name="id" />
				<aos:hiddenfield name="specjson" />
				<aos:hiddenfield name="rspecjson" />
				<aos:hiddenfield name="type_id" fieldLabel="类型id" />
				<aos:triggerfield name="type_name" id="type_name" fieldLabel="类型"
							trigger1Cls="x-form-search-trigger" onTrigger1Click="openTypeWin" columnWidth="0.5" />
				<aos:textfield name="material_name" fieldLabel="材料名称" columnWidth="0.5" allowBlank="false"/>
				<aos:textfield name="unit" fieldLabel="单位" columnWidth="0.5"/>
				
				<aos:hiddenfield name="supplier_id" fieldLabel="供应商id" />
				<%-- <aos:triggerfield name="supplier_name" id="supplier_name" fieldLabel="供应商"
							trigger1Cls="x-form-search-trigger" onTrigger1Click="openSupplierWin" columnWidth="0.5" /> --%>
				<%-- <aos:filefield fieldLabel="图片" name="file" columnWidth="0.5" /> --%>
				<aos:hiddenfield name="ad_code" fieldLabel="图片地址" id="ad_code"/>
			    <aos:fieldset title="上传图片" labelWidth="70" columnWidth="0.99" contentEl="file_upload" collapsible="false" border="false">
			    </aos:fieldset>
				<aos:hiddenfield name="img_src" columnWidth="0.5" />
				<aos:button text="预览" onclick="downloadfile()"></aos:button>
				<aos:textareafield name="note" fieldLabel="备注" columnWidth="1" maxLength="200"/>
				
			</aos:formpanel>
		<aos:docked dock="bottom" ui="footer" height="30">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="_f_file_savegoods();" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="_w_add_goods.close();" text="关闭" icon="close.png" />
		</aos:docked>
		</aos:window>
		
	<aos:window id="_w_goods" title="材料详情" >
	   <aos:gridpanel url="HtController.getT_specificationsPOListByGoodsId" hidePagebar="true" onrender="_g_query" id="_g_specifications" split="true" width="600" height="200" >
			<aos:docked>
				<aos:dockeditem xtype="tbseparator" />
				<aos:dockeditem xtype="button" text="材料出库" onclick="_w_outbound()" icon="add.png" />
				<aos:dockeditem text="新增" icon="add2.png" onclick="fn_add_row" />
				<aos:dockeditem text="删除" icon="del.png" onclick="fn_remove_rows" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:plugins>
				<aos:editing id="id_plugin" ptype="cellediting" autoCancel="false" />
			</aos:plugins>
			<aos:column header="id" dataIndex="id" hidden="true" />
			<%-- <aos:column header="goods_id" dataIndex="goods_id"/> --%>
			<aos:column header="品牌" dataIndex="brand"  width="90" >
				<aos:textfield allowBlank="false" />
			</aos:column>
			<aos:column header="规格" dataIndex="specifications_name" width="90" >
				<aos:textfield allowBlank="false" />
			</aos:column>
			<aos:column header="价格" dataIndex="price" width="90" >
				<aos:numberfield allowBlank="false"/>
			</aos:column>
		</aos:gridpanel>
	    <aos:docked dock="bottom" ui="footer" height="30">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="_f_file_save();" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="_w_goods.close();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	
	
	<aos:window id="_w_grid_type" title="分类列表" width="500" height="300">
		<aos:gridpanel id="_g_tp" url="HtController.getT_typePOList" onrender="_g_type_query" width="490" height="270" onitemdblclick="voluationType">
					<aos:docked forceBoder="0 0 1 0">
						<aos:textfield id="type_nm" name="type_name" fieldLabel="分类名称" columnWidth="0.25" />
						<aos:dockeditem xtype="button" text="查询" onclick="_g_type_query" icon="query.png" />
						<aos:dockeditem xtype="tbseparator" />
					</aos:docked>
					<aos:selmodel type="checkbox" mode="multi" />
					<aos:column type="rowno" />
					<aos:column header="id" dataIndex="id" hidden="true" />
					<aos:column header="分类名称" dataIndex="type_name"  />
				</aos:gridpanel>
	</aos:window>
		
	<aos:window id="_w_grid_supplier" title="供应商列表" width="500" height="300">
		<aos:gridpanel id="_g_sp" url="HtController.getT_supplierPOList" onrender="_g_supplier_query" width="490" height="270" onitemdblclick="voluationSupplier">
			<aos:docked forceBoder="0 0 1 0">
				<aos:textfield id="type_sp" name="supplier_name" fieldLabel="供应商名称" columnWidth="0.25" />
				<aos:dockeditem xtype="button" text="查询" onclick="_g_supplier_query" icon="query.png" />
				<aos:dockeditem xtype="tbseparator" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column type="rowno" />
			<aos:column header="id" dataIndex="id" hidden="true" />
			<aos:column header="供应商名称" dataIndex="supplier_name"  />
					
		</aos:gridpanel>
	</aos:window>
	
	<aos:window id="_w_outbound_type" title="材料出库" width="500" height="100">
		<aos:formpanel id="_f_outboundUpp" width="300" layout="anchor" labelWidth="70" >
		       <%--  <aos:hiddenfield name="id" /> --%>
		        <aos:hiddenfield name="outboundID"/>
				<aos:textfield name="num" id="num" fieldLabel="出库数量"/>
			    <aos:docked dock="bottom" ui="footer" height="30">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="_f_userAdd_save();" text="保存" icon="ok.png" />
				<aos:dockeditem onclick="_w_outbound_type.close();" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:formpanel>
	</aos:window>
	
	</aos:viewport>
	<script type="text/javascript">
	var ugoods_id=null;
	 function _f_userAdd_save(){
			AOS.ajax({
				forms : _f_outboundUpp,
				url : 'HtController.saveGutboundPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_w_outbound_type.hide();
	                	AOS.tip(data.appmsg);
	                }
				}
			});
		}
	
		//加载表格数据
		function _g_goods_query() {
			var params = AOS.getValue('_f_query');
			_g_goods_store.getProxy().extraParams = params;
			_g_goods_store.loadPage(1);
		}
		
		function _g_query() {
			var record = AOS.selectone(_g_goods);
			ugoods_id=record.data.id;
			_g_specifications_store.getProxy().extraParams = {
				goods_id : record.data.id
			};
			_g_specifications_store.loadPage(1);
			_g_goods_store.reload();
		}
		/* function action_query(){
			var record = AOS.selectone(g_order);
			//初始化加载操作记录
			g_work_record_store.getProxy().extraParams = {
				order_id : record.data.order_id
			};
			g_work_record_store.loadPage(1);
		} */
		
		
		function fn_add_row(editor, e) {
			Ext.form.Field.prototype.msgTarget = 'qtip';
			editing = _g_specifications.getPlugin('id_plugin');
			editing.cancelEdit();
			_g_specifications_store.insert(0, {
				goods_id : '',
				brand : '',
				specifications_name : '',
				price : ''
			});
			editing.startEdit(0, 2);
		}
		function fn_remove_rows() {
			var records = AOS.selectone(_g_specifications);
			console.log(records.data);
			 Ext.Array.each(records, function(record) {
				_g_specifications_store.remove(record);
			});
			AOS.tip(AOS.merge('删除了{0}条记录。', records.length));
			AOS.ajax({
				params : {
					id : records.data.id
				},
				url : 'HtController.importdeleteT_goodsPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_g_goods_store.reload();
	                }
				}
				});
		}
		function _f_add_save(){
			AOS.ajax({
				forms : _f_goods,
				url : 'HtController.saveT_goodsPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_w_add_goods.hide();
	                	AOS.tip(data.appmsg);
	                	_g_goods_store.reload();
	                }
				}
				});
			
		}
          
		function _w_outbound(){
			AOS.reset(_f_outboundUpp);
			var record = AOS.selectone(_g_specifications);
			console.log(record);
			if(record){
			_w_outbound_type.show();
			_f_outboundUpp.down('[name=outboundID]').setValue(record.data.id);
			}
		}
		
		
		function _w_add_show(){
			AOS.reset(_f_goods);
			var params = {
       			goods_id : ""
            };
			_g_specifications_store.getProxy().extraParams = params;
			_g_specifications_store.loadPage(1);
			_w_add_goods.show();
		}
		function openTypeWin(){
			_w_grid_type.show();
		}
		function _g_type_query() {
			
			var params = {
				type_name: AOS.getValue('type_nm')
            };
			_g_tp_store.getProxy().extraParams = params;
			_g_tp_store.loadPage(1);
		}
		function voluationType(){
			_w_grid_type.close();
			var record = AOS.selectone(_g_tp);
			_f_goods.down('[name=type_id]').setValue(record.data.id);
			_f_goods.down('[name=type_name]').setValue(record.data.type_name);
		}
		
		function openSupplierWin(){
			_w_grid_supplier.show();
		}
		function _g_supplier_query() {
			
			var params = {
				supplier_name: AOS.getValue('type_sp')
            };
			_g_sp_store.getProxy().extraParams = params;
			_g_sp_store.loadPage(1);
		}
		function voluationSupplier(){
			_w_grid_supplier.close();
			var record = AOS.selectone(_g_sp);
			_f_goods.down('[name=supplier_id]').setValue(record.data.id);
			_f_goods.down('[name=supplier_name]').setValue(record.data.supplier_name);
		}

		
		//按钮列转换
		function fn_button_render(value, metaData, record, rowIndex, colIndex,
				store) {
			return '<input type="button" value="规格" class="cbtn" onclick="specificationsGoods()"/>';
		}
		function _f_file_save(){
			AOS.reset(_f_goods);
			var records = AOS.selectone(_g_specifications);
			console.log(records);
			AOS.ajax({
				params: {
					     "id":records.data.id,
					     "goods_id" :ugoods_id, 
					     "specifications_name": records.data.specifications_name,
					     "price": records.data.price,
					     "brand": records.data.brand},
				url : 'HtController.importSaveT_goodsPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	AOS.tip(data.appmsg);
	                	_g_specifications_store.reload(); 
	                }
				}
				});
		}
		
		//添加商品
		function _f_file_savegoods(){
			AOS.ajax({
				forms : _f_goods,
				url : 'HtController.saveT_goodsPO',
				ok : function(data) {
					if (data.appcode === -1) {
	                    AOS.err(data.appmsg);
	                } else {
	                	_w_add_goods.hide();
	                	AOS.tip(data.appmsg);
	                	_g_goods_store.reload();
	                }
				}
				});
		}
		
		function _w_update_showgoods(){
			AOS.reset(_f_goods);
			var record = AOS.selectone(_g_goods);
			if(record){
				_w_add_goods.show();
				AOS.ajax({
					url : 'HtController.getT_goodsVOById',
					params: {"id" : record.data.id},
					ok : function(data) {
						if (data.appcode === -1) {
	                        AOS.err(data.appmsg);
	                    } else {
	                    	_f_goods.down('[name=id]').setValue(data.id);
	                    	_f_goods.down('[name=material_name]').setValue(data.material_name);
	                    	_f_goods.down('[name=type_id]').setValue(data.type_id);
	                    	_f_goods.down('[name=type_name]').setValue(data.type_name);
	                    	_f_goods.down('[name=unit]').setValue(data.unit);
	                    	_f_goods.down('[name=supplier_id]').setValue(data.supplier_id);
	                    /* 	_f_goods.down('[name=supplier_name]').setValue(data.supplier_name); */
	                    	_f_goods.down('[name=note]').setValue(data.note);
	                    	_f_goods.down('[name=img_src]').setValue(data.img_src);
	                    	var params = {
                    			goods_id : data.id
                            };
	                    	_g_specifications_store.getProxy().extraParams = params;
	                    	_g_specifications_store.loadPage(1);
	                    	_g_goods_store.reload();
	                    }
					}
				});
				
			}
		}
		function _g_goods_del(){
			var record = AOS.selectone(_g_goods);
			console.log(record.data.id);
			if(AOS.empty(record)){
				AOS.tip('删除前请先选中数据。');
				return;
			}
			var rows = AOS.rows(_g_goods);
			var msg =  AOS.merge('确认要删除选中的{0}条记录吗？', rows);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'HtController.deleteT_goodsPO',
					params: {"id" : record.data.id},
					ok : function(data) {
						if(data.appcode === -1){
							AOS.err(data.appmsg);
							return ;
						}
						AOS.tip(data.appmsg);
						_g_goods_store.reload();
						
					}
				});
			});
		}
		/* function fn_xls() {
			var params = AOS.getValue('_f_query');
			AOS.ajax({
				url : 'HtController.fillReportGoods',
				params : params,
				ok : function(data) {
					
				}
			}); 
		} */
		function downloadfile(){
			var img_src = _f_goods.down('[name=img_src]').getValue();
			window.open(img_src);
			
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
function specificationsGoods(){
	Ext.getCmp('_w_goods').show();
	/* _g_specifications_store.reload();  */
}
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
