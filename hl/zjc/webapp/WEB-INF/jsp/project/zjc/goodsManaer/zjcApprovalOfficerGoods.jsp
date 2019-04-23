<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商品初审" base="http" lib="ext">
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
    <!-- UEditer -->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/lang/zh-cn/zh-cn.js"></script>
	<aos:body>
		<!-- UEditer -->
		<div id="div_update_ueditor" class="x-hidden">
			<script id="update_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
		<div id="original_img_div" class="x-hidden" >
			<img id="original_img" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_goods_picture_upload();">上传图片</button>
		</div>
		<div id="picture-show" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage" src="" />
			</div>
		</div>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
	<aos:panel layout="auto" autoScroll="true" border="false">
		<aos:panel layout="anchor" width="auto" border="false">
			<aos:formpanel id="f_query" layout="column" header="false" border="false" anchor="100%">
				<aos:docked forceBoder="1 0 1 0"> 
					<aos:dockeditem xtype="tbtext" text="查询条件" />
				</aos:docked>
				<%-- <aos:combobox name="cat_id" fieldLabel="所有分类" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" columnWidth="0.25" />
				<aos:combobox name="brand_id" fieldLabel="所有品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.25" /> --%>
				<aos:textfield name="user_id" fieldLabel="商家ID"  columnWidth="0.24" />
				<aos:textfield name="store_name" fieldLabel="店铺名称"  columnWidth="0.24" />
				<%-- <aos:combobox name="name" fieldLabel="是否上架" dicField="is" columnWidth="0.25" />
				<aos:combobox name="name" fieldLabel="" dicField="is" columnWidth="0.25" /> --%>
				<aos:textfield name="goods_id" fieldLabel="商品ID"  columnWidth="0.24" />
				<aos:textfield name="goods_name" fieldLabel="商品名称"  columnWidth="0.24" />
				<aos:combobox name="cat_id" fieldLabel="所有分类" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" columnWidth="0.25" />
				<aos:combobox name="goods_state_1" fieldLabel="审核状态"  dicField="goods_audit_status" columnWidth="0.25" />
				<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="button" text="查询" onclick="g_approval_officer_goods_query" icon="query.png" />
					<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
					<aos:dockeditem xtype="tbfill" />
				</aos:docked>
			</aos:formpanel>
		
			<aos:gridpanel id="g_approval_officer_goods" url="zjcGoodsService.listApprovalOfficerGoods" border="1 0 1 3" forceFit="true" autoLoad="true" height="750" anchor="100%">
				<aos:menu>
					<aos:menuitem text="刷新" onclick="#g_approval_officer_goods_store.reload();" icon="refresh.png" />
				</aos:menu>
				<aos:selmodel type="checkbox" mode="multi" />
				<aos:column type="rowno" />
				<aos:column dataIndex="goods_id" hidden="true"/>
				<aos:column dataIndex="is_car" hidden="true"/>
				<aos:column header="商家ID" dataIndex="user_id" celltip="true" />
				<aos:column header="店铺名称" dataIndex="store_name" celltip="true"/>
				<aos:column header="商品ID" dataIndex="goods_id" celltip="true" />
				<aos:column header="商品名称" dataIndex="goods_name" celltip="true" rendererFn="format_goods_name"/>
				<aos:column header="分类" dataIndex="category_name" />
				<aos:column header="在线支付" dataIndex="shop_price" celltip="true" />
				<aos:column header="易支付" dataIndex="market_price" celltip="true" />
				<aos:column header="滴支付" dataIndex="drops" celltip="true" />
				<aos:column header="更新时间" dataIndex="last_update" celltip="true" />
				<%-- <aos:column header="易支付" dataIndex="cost_price" celltip="true" /> --%>
				<aos:column header="审核状态" dataIndex="goods_state_1" celltip="true" rendererField="goods_audit_status"/>
				<aos:column header="操作" rendererFn="fn_button_render" align="center" fixedWidth="60" />
			</aos:gridpanel>
		</aos:panel>
	</aos:panel>
	
	<aos:window id="w_approval_officer_goods" title="商品审核" height="600" width="1100" layout="fit" autoScroll="true" onshow="w_approval_officer_goods_selection" onclose="closeset">
		<aos:tabpanel id="id_tabs" region="center" tabPosition="top" bodyBorder="0 0 0 0" margin="0 0 2 0">
			<aos:tab title="通用信息" onshow="setGrey">
				<aos:formpanel id="f_goods_info" width="1100" layout="column" labelWidth="150" msgTarget="qtip">
					<aos:hiddenfield name="goods_id"/>
					<aos:textfield fieldLabel="商品名称" name="goods_name" columnWidth="0.9" readOnly="true"/>
					<aos:textfield fieldLabel="商品货号" name="goods_sn" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商品关键词" name="keywords" emptyText="用空格分隔" columnWidth="0.3"  />
					<aos:combobox id="cat_id1" onselect="cat11nselect" fieldLabel="所属分类" name="cat_id" emptyText="请选择分类" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0"/>
					<aos:combobox id="cat_id2" fieldLabel="二级分类" queryMode="local" name="cat_id2" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" />
					<aos:combobox id="brand_id" name="brand_id" fieldLabel="商品品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.3" />
					<aos:textfield fieldLabel="商品重量(g)" name="weight" columnWidth="0.3" />
					<aos:textfield fieldLabel="会员周期赠送权重" id="goods_weight" name="goods_weight" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商家赠送比例" name="store_rebate_rate" columnWidth="0.3" readOnly="true"/>
					<%-- <aos:textfield fieldLabel="商品返还倍数" name="market_price_bs" columnWidth="0.3" /> --%>
					<aos:textfield fieldLabel="在线支付" name="shop_price" columnWidth="0.3" readOnly="true"/>
					<aos:numberfield fieldLabel="易支付" name="market_price" minValue="0" columnWidth="0.3" readOnly="true"/>
					<aos:numberfield fieldLabel="滴支付" name="drops" minValue="0"  columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="库存" name="store_count" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="销量" name="sales_sum" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="今日每人限购次数" name="today_limit_times" columnWidth="0.3"/>
					<aos:textfield fieldLabel="每人每次限购数量" name="today_limit_nums" columnWidth="0.3" />
					<aos:textfield fieldLabel="预计发货时间" name="expect_delivery" columnWidth="0.3" emptyText="预计几天内发货"/>
					<aos:textfield fieldLabel="客服电话" name="contract_phone" columnWidth="0.3"/>
					<aos:radioboxgroup fieldLabel="是否包邮" columns="6" columnWidth="0.4" onchange="free_shipping" onrender="free_render">
						<aos:radiobox name="is_free_shipping" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_free_shipping" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:textfield fieldLabel="邮费" name="postage" columnWidth="0.2" id="postage1" margin="0 0 0 -110" allowBlank="false"/>
					<aos:textfield fieldLabel="型号/规格" name="ts" columnWidth="0.9" />
					<%-- <aos:textfield fieldLabel="拼团价格" name="shop_group_price" columnWidth="0.5" />
					<aos:textfield fieldLabel="需要几人拼团" name="shop_group_person" columnWidth="0.4" /> --%>
					<aos:radioboxgroup fieldLabel="支付类别" columns="1" columnWidth="1" onchange="changeRate">
						<aos:radiobox name="commodity_categories" boxLabel="优选" inputValue="1" checked="true"/>
						<aos:radiobox name="commodity_categories" boxLabel="礼品兑换" inputValue="3" />
						<aos:radiobox name="commodity_categories" boxLabel="热销预售" inputValue="2" />
					</aos:radioboxgroup>
					<aos:hiddenfield name="is_hot" fieldLabel="热销预售"/>
					<%-- <aos:hiddenfield name="is_mixed" fieldLabel="混合支付"/>
					<aos:hiddenfield name="is_car" fieldLabel="特殊商品"/> --%>
					<%-- <aos:radioboxgroup fieldLabel="热销预售" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup> --%>
					<aos:radioboxgroup fieldLabel="混合支付" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_mixed" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_mixed" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="特殊商品" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_car" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_car" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="特价" columns="6" columnWidth="0.4">
						<aos:radiobox name="special_offer" boxLabel="是" inputValue="1" />
						<aos:radiobox name="special_offer" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					
					<aos:textareafield fieldLabel="商品简单描述" name="goods_remark" columnWidth="0.9"/>
					<aos:textareafield fieldLabel="备注" name="remark" columnWidth="0.9" />
					<aos:fieldset title="上传商品图片：" labelWidth="120" columnWidth="1" contentEl="original_img_div" collapsible="false" border="false" >
					</aos:fieldset>
					<aos:hiddenfield name="original_img"/>
					<aos:hiddenfield name="goods_content" id="goods_content"/>
					<aos:fieldset title="商品审核" labelWidth="70" columnWidth="1" border="true">
						<aos:radioboxgroup fieldLabel="审核" columns="4" columnWidth="0.85">
							<aos:radiobox name="goods_state_1" boxLabel="通过" inputValue="3" />
							<aos:radiobox name="goods_state_1" boxLabel="不通过" inputValue="2" />
						</aos:radioboxgroup>
						<aos:textareafield fieldLabel="备注" name="goods_state_1_mark" id="goods_state_1_mark" columnWidth="0.9" />
					</aos:fieldset>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品详情" onactivate="updateOnshow">
				<aos:formpanel id="f_goods_content">
					<aos:fieldset labelWidth="120" columnWidth="1"  contentEl="div_update_ueditor" collapsible="false" border="false"></aos:fieldset>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品规格" layout="anchor" onshow="romove_ash">
				<aos:formpanel id="f_goods_spe" layout="column" labelWidth="100" margin="5" anchor="100%" border="true" >
					<aos:textfield id="type_name" fieldLabel="商品类型" labelWidth="70" columnWidth="0.55" readOnly="true" emptyText="暂无类型"/>
					<aos:gridpanel id="g_specItem" url="zjcGoodsTypeService.getGoodsSpec" forceFit="true" border="1 0 1 3" anchor="100%" columnWidth="0.99">
						<aos:column header="规格名称" dataIndex="type_name" celltip="true" />
						<aos:column header="规格项" dataIndex="item_name" celltip="true" />
					</aos:gridpanel>
				</aos:formpanel>
			</aos:tab>
		</aos:tabpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_approval_officer_submit" text="保存" icon="ok.png" id="save_btn"/>
			<aos:dockeditem onclick="#w_approval_officer_goods.close();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="goods_picture_upload" title="上传商品图片"  width="800">
		<aos:formpanel id="f_upload">
			<aos:iframe id="iframe1" columnWidth="0.8" height="450" src="do.jhtml?router=homeService.newGoodsUpload&inputName=original_img&windowsId=goods_picture_upload&paramkey=goods_picture_prop&juid=${juid}"/>
		</aos:formpanel>
	</aos:window>
	</aos:viewport>
	<script type="text/javascript">
		function closeset(){
			$(".x-mask").css("display","none");
			id_tabs.setActiveTab(0);
		}
		
		  //动态监控是否包邮决定是否显示邮费
		function free_shipping(me,newValue){
			is_free = newValue.is_free_shipping;
			if(is_free == 1){//包邮
				$("#postage1").hide();
				f_goods_info.down("[name='postage']").setValue('0');
			} else {//不包邮
				$("#postage1").show();
			}
		}
	    //组件被渲染后触发
	    function free_render(){
			is_free = f_goods_info.down("[name='is_free_shipping']").getValue();
			if(is_free == 1){//包邮
				$("#postage1").hide();
			} else {//不包邮
				$("#postage1").show();
			}
		}
		
		//给select赋初值
        function  getGoodsSpec(id,idStr) {
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
		
		 //查询参数列表
	    function g_approval_officer_goods_query() {
	    	var params = AOS.getValue('f_query');
	    	g_approval_officer_goods_store.getProxy().extraParams = params;
	    	g_approval_officer_goods_store.loadPage(1);
	    }
		 
	    //一级节点选择事件监听函数
		function cat1Onselect(me, records) {
			var parent_id = me.getValue();
			cat_id_2_store.getProxy().extraParams = {
				parent_id : parent_id
			};
			cat_id_2_store.load({
				callback : function(records, operation, success) {
					//这个回调里还可以根据是否查询到二级模块再去做一些事情
					if (records.length > 0) {
						AOS.edit(cat_id_2);
					}else{
						AOS.read(cat_id_2);
					}
				}
			});
		}
	    
		 function cat11nselect(me, records) {
			var parent_id = me.getValue();
			cat_id2_store.getProxy().extraParams = {
				parent_id : parent_id
			};
			cat_id2_store.load({
				callback : function(records, operation, success) {
					//这个回调里还可以根据是否查询到二级模块再去做一些事情
					if (records.length > 0) {
						AOS.edit(cat_id2);
					}else{
						AOS.read(cat_id2);
					}
				}
			});
		}
	  
		function typeOnchange(me, records){
		  	var params = AOS.getValue('f_goods_spe');
	    	g_specItem_store.getProxy().extraParams = params;
	        g_specItem_store.loadPage(1);
		}
		
		//按钮列转换
		function fn_button_render(value, metaData, record, rowIndex, colIndex,
				store) {
			return '<input type="button" value="审核" class="cbtn" onclick="w_approval_officer_goods_show();" />';
		}
		/* 弹出窗口监听 */
		function w_approval_officer_goods_selection(){
			var record = AOS.selectone(g_approval_officer_goods);
			f_goods_info.form.reset();
            AOS.ajax({
            	params : {
            		goods_id: record.data.goods_id
            	},
                url: 'zjcGoodsService.getGoodsInfo',
                ok: function (data) {
                	f_goods_info.down("[id='cat_id1']").store.reload();
                	f_goods_info.down("[id='cat_id1']").setValue(data.cat_id);
                	//取二级分类上级id
       	         	cat_id2_store.getProxy().extraParams = {
       	 				parent_id : data.cat_id
       	 			};
    	           	f_goods_info.down("[id='cat_id2']").store.reload();
    	           	f_goods_info.down("[id='cat_id2']").setValue(data.cat_id2);
                	f_goods_info.down("[id='brand_id']").store.reload();
                	f_goods_info.down("[id='brand_id']").setValue(data.brand_id);
                	document.getElementById("original_img").src  = data.original_img;
                	document.getElementById("showImage").src  = data.original_img;
                	f_goods_info.form.setValues(data);
                	f_goods_info.down('[name=goods_weight]').setValue('0');
    				f_goods_info.down('[name=store_rebate_rate]').setValue('0');
                	if(data.aduit_info == undefined || data.aduit_info==''){
                		$("#goods_state_1_mark-inputEl").val("");
                	}else{
                		var strs = new Array();
                    	strs = data.aduit_info.split(";");
                    	$("#goods_state_1_mark-inputEl").val(strs[3].split(":")[2].replace(/\"/g,"")); 
                	}
                	 AOS.ajax({
                     	params : {
                     		goods_id: record.data.goods_id
                     	},
                         url: 'zjcGoodsTypeService.getGoodsType',
                         ok: function (data) {
                        	 Ext.getCmp("type_name").setValue(data.appmsg);
                         }
                     })
        	    	g_specItem_store.getProxy().extraParams = {
        	    		goods_id: record.data.goods_id
        			};
        	        g_specItem_store.loadPage(1);
                }
            });
            setGrey();
		}
		
		function f_approval_officer_submit(){
			var test=Ext.getCmp('goods_weight').getValue();
			var temp;
			for(var i in test){
				temp =test[i]; 
				} 
			var szReg=/^[^\u4e00-\u9fa5]{0,}$/; 
			var bChk=temp.match(szReg); 
			if(bChk==null){
				AOS.warn("权重里面不能输如汉字,请按照格式输入!");
				return false;
			}
			var cat2Val = Ext.getCmp('cat_id2').value;
			if(cat2Val==null || cat2Val == ''){
				 AOS.warn("请选择商品的二级分类");
				 return;
			}
			AOS.ajax({
                forms: f_goods_info,
                url: 'zjcGoodsService.updateApprovalOfficerGoods',
                ok: function (data) {
                	if(data.appcode == '1'){
                		w_approval_officer_goods.close();
                		g_approval_officer_goods_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
						AOS.get('f_goods_info.goods_id').markInvalid(data.appmsg);
						AOS.err(data.appmsg);
                	}
                }
            });
		}
		
		//初始化编辑器及内容设置
		function updateOnshow(me, records){
			var updateUE = UE.getEditor('update_editor',{
				scaleEnabled:true
			});
			updateUE.addListener('ready',function(editor){
				updateUE.setContent(f_goods_info.down("[name='goods_content']").getValue());
			});
			updateUE.addListener('blur',function(editor){
				f_goods_info.down("[name='goods_content']").setValue(updateUE.getContent());
			});
			try{
				updateUE.setContent(f_goods_info.down("[name='goods_content']").getValue());
			}catch(error){
				
			}
			
			setGrey();
		}
		
		function setGrey(){
			Ext.getCmp("save_btn").disable();
		}

		function romove_ash(){
			Ext.getCmp("save_btn").enable();
		}
		
		//查询ICON列表
		function icons_query() {
			var goods_id = f_goods_info.down("[name='goods_id']").getValue();
			var params = {
                    goods_id: goods_id
                };
			icons_store.getProxy().extraParams = params;
			icons_store.loadPage(1);
		}
		
		function format_goods_name(value, metaData, record, rowIndex, colIndex,store){
			var is_car = record.data.is_car;
			var goods_name = record.data.goods_name;
			if(is_car == 1){//如果是特殊商品
				return '<div style="color:red">'+ goods_name +'</div>';
			} else {
				return '<div>'+ goods_name +'</div>';
			}
		}
		
		//改变支付类别调整比例
		function changeRate(me,newValue){
			if(newValue.commodity_categories == 1){//优选
				f_goods_info.down('[name=goods_weight]').setValue('1');
				f_goods_info.down('[name=store_rebate_rate]').setValue('1');
				f_goods_info.down('[name=is_hot]').setValue('0');
				f_goods_info.down('[name=is_mixed]').setValue('0');
				f_goods_info.down('[name=is_car]').setValue('0');
			} else if(newValue.commodity_categories == 2){//精品
				f_goods_info.down('[name=goods_weight]').setValue('0');
				f_goods_info.down('[name=store_rebate_rate]').setValue('1');
				f_goods_info.down('[name=is_hot]').setValue('1');
				f_goods_info.down('[name=is_mixed]').setValue('1');
				f_goods_info.down('[name=is_car]').setValue('1');
			} else if(newValue.commodity_categories == 3){//礼品兑换
				f_goods_info.down('[name=goods_weight]').setValue('0.05');
				f_goods_info.down('[name=store_rebate_rate]').setValue('0.05');
				f_goods_info.down('[name=is_hot]').setValue('0');
				f_goods_info.down('[name=is_mixed]').setValue('0');
				f_goods_info.down('[name=is_car]').setValue('0');
			}
		}
	</script>
</aos:onready>

<script type="text/javascript">
	function w_approval_officer_goods_show(){
		Ext.getCmp('w_approval_officer_goods').show();
	}
	function f_goods_picture_upload(){
		Ext.getCmp('goods_picture_upload').show();
	}
	function showdiv(){
	   $("#picture-show").show();
   }
   function closediv(){
	   $("#picture-show").hide();
   }
</script>
