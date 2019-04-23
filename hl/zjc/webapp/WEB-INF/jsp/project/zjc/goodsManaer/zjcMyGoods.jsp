<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="自营商品管理" base="http" lib="ext">
    <script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
    <!-- UEditer -->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/lang/zh-cn/zh-cn.js"></script>
	<aos:body>
		<!-- UEditer -->
		<div id="div_add_ueditor" class="x-hidden">
			<script id="add_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
		<div id="div_update_ueditor" class="x-hidden">
			<script id="update_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
		<!-- 添加使用 -->
		<div id="original_img_div" class="x-hidden" >
			<img id="original_img" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv1();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_goods_picture_upload();">上传图片</button>
		</div>
		<div id="picture-show" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv1();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage" src="" />
			</div>
		</div>
		<!-- 修改使用 -->
		<div id="original_img_div_update" class="x-hidden" >
			<img id="original_img_update" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv2();">
			<button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_goods_picture_update_upload();">上传图片</button>
		</div>
		<div id="picture-show-update" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center; z-index: 20000;" onclick="closediv2();" >
			<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%;  z-index: 20001;" >
				<img id="showImage-update" src="" />
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
				<aos:textfield name="user_id" fieldLabel="商家ID"  columnWidth="0.24" />
				<aos:textfield name="store_name" fieldLabel="店铺名称"  columnWidth="0.24" />
				<aos:textfield name="goods_name" fieldLabel="商品名称"  columnWidth="0.24" />
				<aos:combobox name="is_on_sale" fieldLabel="上架" dicField="is" columnWidth="0.25" />
				<aos:combobox name="is_new" fieldLabel="新品" dicField="is" columnWidth="0.25" />
				<aos:combobox name="cat_id" fieldLabel="所有分类" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" columnWidth="0.25" />
				<aos:combobox name="brand_id" fieldLabel="所有品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.25" />
				<%-- <aos:combobox name="name" fieldLabel="是否上架" dicField="is" columnWidth="0.25" />
				<aos:combobox name="name" fieldLabel="" dicField="is" columnWidth="0.25" /> --%>
				<%-- <aos:textfield name="goods_name" fieldLabel="商品名称"  columnWidth="0.24" /> --%>
				<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="button" text="查询" onclick="g_myGoods_query" icon="query.png" />
					<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
					<aos:dockeditem xtype="tbfill" />
				</aos:docked>
			</aos:formpanel>
		
			<aos:gridpanel id="g_myGoods" url="zjcGoodsService.listZjcMyGoods" forceFit="true" autoLoad="true"
			 height="750" anchor="100%">
				<aos:menu>
					<aos:menuitem text="新增" onclick="#w_myGoods.show();" icon="add.png" />
					<aos:menuitem text="修改" onclick="w_myGoods_u_show" icon="edit.png" />
					<aos:menuitem xtype="menuseparator" />
					<aos:menuitem text="刷新" onclick="#g_myGoods_store.reload();" icon="refresh.png" />
				</aos:menu>
				<aos:docked forceBoder="0 0 1 0" >
					<aos:dockeditem onclick="#w_myGoods.show();" text="新增" icon="add.png" />
					<aos:dockeditem onclick="w_myGoods_u_show" text="修改" icon="edit.png" />
					<%-- <aos:dockeditem onclick="g_myGoods_del" text="删除" icon="del.png" /> --%>
				</aos:docked>
				<aos:plugins>
					<%-- clicksToEdit可以控制是单击还是双击进入编辑模式 --%>
					<aos:editing id="id_plugin" clicksToEdit="2" onedit="fn_edit" autoCancel="false" onbeforeedit="fn_beforeedit" />
				</aos:plugins>
				<aos:selmodel type="checkbox" mode="multi" />
				<aos:column type="rowno" />
				<aos:column header="ID" dataIndex="goods_id" hidden="true" />
				<aos:column header="商家ID" dataIndex="user_id"  />
				<aos:column header="店铺名称" dataIndex="store_name" celltip="true"  />
				<aos:column header="商品名称" dataIndex="goods_name" celltip="true" />
				<aos:column header="分类" dataIndex="category_name" />
				<aos:column header="在线支付" dataIndex="shop_price" celltip="true" />
				<aos:column header="易支付" dataIndex="market_price" celltip="true" />
				<%-- <aos:column header="易支付" dataIndex="cost_price" celltip="true" />--%>
				<%-- <aos:column header="初审" dataIndex="goods_state_1" celltip="true" rendererField="goods_audit_status" />
				<aos:column header="终审" dataIndex="goods_state_2" celltip="true" rendererField="goods_audit_status"/> --%>
				<aos:column header="库存" dataIndex="store_count" celltip="true" />
				<%-- <aos:column header="库存" dataIndex="store_count" celltip="true" >
					<aos:numberfield allowBlank="false" maxValue="100" minValue="1" />
				</aos:column> --%>
				<aos:column header="销量" dataIndex="sales_sum" celltip="true" />
				<aos:column header="上架" dataIndex="is_on_sale" celltip="true" rendererField="is_on_sale">
					<aos:combobox dicField="is_on_sale" />
				</aos:column>
				<aos:column header="优选" dataIndex="is_recommend" rendererField="is_recommend">
					<aos:combobox  dicField="is_recommend" />
				</aos:column>
				<aos:column header="新品" dataIndex="is_new" rendererField="is_new">
					<aos:combobox  dicField="is_new" />
				</aos:column>
				<aos:column header="特殊商品" dataIndex="is_car" celltip="true" rendererField="is_car"/>
				<%-- <aos:column header="特殊商品" dataIndex="is_car" celltip="true" rendererField="is_car">
					<aos:combobox  dicField="is_car" />
				</aos:column> --%>
				<aos:column header="排序" dataIndex="sort" celltip="true" >
					<aos:numberfield allowBlank="false" maxValue="9999999" minValue="1" />
				</aos:column>
				<aos:column header="操作" type="action" fixedWidth="60" align="center">
						<aos:action handler="fn_do_up" icon="up.png" tooltip="上移" />
						<aos:action handler="fn_do_down" icon="down.png" tooltip="下移" />
				</aos:column>
			</aos:gridpanel>
		</aos:panel>
	</aos:panel>
	
	<aos:window id="w_myGoods" title="新增商品" width="1100" maxHeight="800" layout="fit" autoScroll="true" any="zIndex:1000">
		<aos:tabpanel id="id_tabs" region="center" tabPosition="top" bodyBorder="0 0 0 0" margin="0 0 2 0">
			<aos:tab title="通用信息">
				<aos:formpanel id="f_goods_info_s" width="650" layout="column" autoScroll="true" labelWidth="150" msgTarget="qtip">
					<aos:hiddenfield name="goods_id"/>
					<aos:textfield fieldLabel="商品名称" name="goods_name" columnWidth="0.3" />
					<aos:textfield fieldLabel="商品货号" name="goods_sn" columnWidth="0.3" emptyText="不填写时由系统后台生成"/>
					<aos:textfield fieldLabel="商品重量(g)" name="weight" columnWidth="0.3" />
					<aos:combobox id="cat_id" onselect="cat1Onselect" fieldLabel="所属分类" name="cat_id" allowBlank="false" emptyText="请选择分类" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" />
					<aos:combobox id="cat_id_2" fieldLabel="二级分类" queryMode="local" name="cat_id2" readOnly="true" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" />
					<aos:combobox name="brand_id" fieldLabel="商品品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.3" />
					<aos:textfield fieldLabel="商家返利比例" name="store_rebate_rate" columnWidth="0.3" />
					<aos:textfield fieldLabel="会员返还周期权重"  name="goods_weight"  columnWidth="0.3" />
					<aos:textfield fieldLabel="易支付" name="market_price" columnWidth="0.3" />
					<%-- <aos:radioboxgroup fieldLabel="是否特殊商品" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_car" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_car" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup> --%>
					<aos:radioboxgroup fieldLabel="是否混合支付" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_mixed" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_mixed" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="是否特价" columns="6" columnWidth="0.4">
						<aos:radiobox name="special_offer" boxLabel="是" inputValue="1" />
						<aos:radiobox name="special_offer" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="是否热卖" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:textfield fieldLabel="返分倍数" name="market_price_bs" columnWidth="0.3" />
					<aos:textfield fieldLabel="在线支付" name="shop_price" columnWidth="0.3" />
					<aos:textfield fieldLabel="库存数量" name="store_count" columnWidth="0.3" />
					<aos:textfield fieldLabel="商品关键词" name="keywords" emptyText="用空格分隔" columnWidth="0.3" />
					<aos:textfield fieldLabel="拼团价格" name="shop_group_price" columnWidth="0.5" />
					<aos:textfield fieldLabel="需要几人拼团" name="shop_group_person" columnWidth="0.4" />
					<aos:textareafield fieldLabel="商品简单描述" name="goods_remark" columnWidth="0.8" />
					<aos:fieldset title="上传商品图片：" labelWidth="120" columnWidth="1" contentEl="original_img_div" collapsible="false" border="false"  margin="0 0 0 58">
					</aos:fieldset> 
					<aos:hiddenfield name="original_img"/>
					<aos:hiddenfield name="goods_content"/>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品详情" onshow="addOnshow">
				<aos:formpanel id="f_goods_content_s">
					<aos:fieldset labelWidth="120" columnWidth="1" contentEl="div_add_ueditor" collapsible="false" border="false" ></aos:fieldset>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品规格" layout="anchor" height="400">
				<aos:formpanel id="f_goods_spe_s" layout="column" labelWidth="100" margin="5" anchor="100%" border="true">
					<aos:combobox name="type_id" fieldLabel="商品类型" labelWidth="60" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.55" onchange="typeOnchange_s"/>
					<aos:gridpanel id="g_specItem_s" url="zjcSpecService.selectItemByTypeID" forceFit="true" border="1 0 1 3" anchor="100%" columnWidth="0.99">
						<aos:selmodel type="checkbox" mode="multi" />
						<aos:column type="rowno" />
						<aos:column header="ID" dataIndex="id"  celltip="true" hidden="true"/>
						<aos:column header="规格类型id" dataIndex="type_id" celltip="true" hidden="true"/>
						<aos:column header="规格名称" dataIndex="name" celltip="true" />
						<aos:column header="规格项" dataIndex="item" celltip="true" />
						<aos:column header="规格项id" dataIndex="spec_id" celltip="true" hidden="true" />
					</aos:gridpanel>
				</aos:formpanel>
			</aos:tab>
		</aos:tabpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_myGoods_save" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_myGoods.close();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	
	<aos:window id="w_myGoods_u" title="修改商品" width="1100" maxHeight="800" layout="fit" autoScroll="true" onclose="closeset">
		<aos:tabpanel id="id_tabs_u" region="center" tabPosition="top" bodyBorder="0 0 0 0" margin="0 0 2 0" activeTab="0">
			<aos:tab title="通用信息" >
				<aos:formpanel id="f_goods_info_u" width="650" layout="column" labelWidth="150" msgTarget="qtip">
					<aos:hiddenfield name="goods_id"/>
					<aos:textfield fieldLabel="商品名称" name="goods_name" columnWidth="0.3" allowBlank="false" />
					<aos:textfield fieldLabel="商品货号" name="goods_sn" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商品重量(g)" name="weight" columnWidth="0.3" />
					<aos:combobox id="cat_id1" onselect="cat11nselect" fieldLabel="所属分类" name="cat_id" allowBlank="false" emptyText="请选择分类" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" />
					<aos:combobox id="cat_id2" fieldLabel="二级分类" queryMode="local" name="cat_id2" allowBlank="false" readOnly="true" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" />
					<aos:combobox id="brand_id" name="brand_id" fieldLabel="商品品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.3" />
					<aos:textfield fieldLabel="商家返利比例" name="store_rebate_rate" columnWidth="0.3" />
					<aos:textfield fieldLabel="会员返还周期权重" name="goods_weight"  columnWidth="0.3" />
					<aos:textfield fieldLabel="易支付" name="market_price" columnWidth="0.3" />
					<%-- <aos:radioboxgroup fieldLabel="是否特殊商品"  columns="6" columnWidth="0.4">
						<aos:radiobox name="is_car" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_car" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup> --%>
					<aos:radioboxgroup fieldLabel="是否混合支付" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_mixed" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_mixed" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="是否特价" columns="6" columnWidth="0.4">
						<aos:radiobox name="special_offer" boxLabel="是" inputValue="1" />
						<aos:radiobox name="special_offer" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="是否热卖" columns="6" columnWidth="0.4">
						<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:textfield fieldLabel="返分倍数" name="market_price_bs" columnWidth="0.3" />
					<aos:textfield fieldLabel="在线支付" name="shop_price" columnWidth="0.3" />
					<aos:textfield fieldLabel="库存数量" name="store_count" columnWidth="0.3" />
					<aos:textfield fieldLabel="商品关键词" name="keywords" emptyText="用空格分隔" columnWidth="0.3" />
					<aos:textfield fieldLabel="拼团价格" name="shop_group_price" columnWidth="0.5" />
					<aos:textfield fieldLabel="需要几人拼团" name="shop_group_person" columnWidth="0.4" />
					<aos:textareafield fieldLabel="商品简单描述" name="goods_remark" columnWidth="0.8" />
					<aos:fieldset title="上传商品图片：" labelWidth="120" columnWidth="1" contentEl="original_img_div_update" collapsible="false" border="false" margin="0 0 0 58">
					</aos:fieldset> 
					<aos:hiddenfield name="goods_content"/>
					<aos:hiddenfield name="original_img"/>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品详情" onactivate="updateShow">
				<aos:formpanel id="f_goods_content_u" >
					<aos:fieldset labelWidth="120" columnWidth="1"  contentEl="div_update_ueditor" collapsible="false" border="false"></aos:fieldset>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品规格" layout="anchor" height="400">
				<aos:formpanel id="f_goods_spe_u" layout="column" labelWidth="100" margin="5" anchor="100%" border="true">
					<aos:combobox name="type_id" fieldLabel="商品类型" labelWidth="60" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.55" onchange="typeOnchange_u"/>
					<aos:gridpanel id="g_specItem_u" url="zjcSpecService.selectItemByTypeID" forceFit="true" border="1 0 1 3" anchor="100%" columnWidth="0.99">
						<aos:selmodel type="checkbox" mode="multi" />
						<aos:column type="rowno" />
						<aos:column header="ID" dataIndex="id"  celltip="true" hidden="true"/>
						<aos:column header="规格类型id" dataIndex="type_id" celltip="true" hidden="true"/>
						<aos:column header="规格名称" dataIndex="name" celltip="true" />
						<aos:column header="规格项" dataIndex="item" celltip="true" />
						<aos:column header="规格项id" dataIndex="spec_id" celltip="true" hidden="true" />
					</aos:gridpanel>
				</aos:formpanel>
			</aos:tab>
		</aos:tabpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_myGoods_u_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_myGoods_u.close();" text="关闭" icon="close.png" />
		</aos:docked>
	</aos:window>
	<aos:window id="goods_picture_add_upload" title="上传商品图片"  width="800">
		<aos:formpanel id="f_upload">
			<aos:iframe id="iframe1" columnWidth="0.8" height="450" src="do.jhtml?router=homeService.newGoodsUpload&inputName=original_img&windowsId=goods_picture_add_upload&paramkey=goods_picture_prop&juid=${juid}"/>
		</aos:formpanel>
	</aos:window>
	<aos:window id="goods_picture_update_upload" title="上传商品图片"  width="800">
		<aos:formpanel id="f_upload">
			<aos:iframe id="iframe2" columnWidth="0.8" height="450" src="do.jhtml?router=homeService.newGoodsUpload&updategoods=1&inputName=original_img_update&windowsId=goods_picture_update_upload&paramkey=goods_picture_prop&juid=${juid}"/>
		</aos:formpanel>
	</aos:window>
	</aos:viewport>
	<script type="text/javascript">
		function closeset(){
			id_tabs_u.setActiveTab(0);
		}
		 //查询参数列表
	    function g_myGoods_query() {
	    	var params = AOS.getValue('f_query');
	    	g_myGoods_store.getProxy().extraParams = params;
	        g_myGoods_store.loadPage(1);
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
	    
		function typeOnchange_s(me, records){
		  	var params = AOS.getValue('f_goods_spe_s');
		  	g_specItem_s_store.getProxy().extraParams = params;
		  	g_specItem_s_store.loadPage(1);
		}
	  
		function typeOnchange_u(me, records){
		  	var params = AOS.getValue('f_goods_spe_u');
	    	g_specItem_u_store.getProxy().extraParams = params;
	        g_specItem_u_store.loadPage(1);
		}
		
		//初始化编辑器及内容设置
		function addOnshow(me, records){
			var addUE = UE.getEditor('add_editor',{
				scaleEnabled:true
			});
			addUE.addListener('blur',function(editor){
				f_goods_info_s.down("[name='goods_content']").setValue(addUE.getContent());
			});
		}
		//修改初始化编辑器及内容设置
		function updateShow(me, records){
			var updateUE= UE.getEditor('update_editor',{
				scaleEnabled:true
			});
			updateUE.addListener('ready',function(editor){
				updateUE.setContent(f_goods_info_u.down("[name='goods_content']").getValue());
			}); 
			updateUE.addListener('blur',function(editor){
				f_goods_info_u.down("[name='goods_content']").setValue(updateUE.getContent());
			});
			try{
				updateUE.setContent(f_goods_info_u.down("[name='goods_content']").getValue());
			}catch(error){
				
			}
		}
		//弹出修改窗口
		function w_myGoods_u_show(){
		 	var record = AOS.selectone(g_myGoods);
		 	console.log(record);
		 	if(record == undefined){
		 		AOS.tip("未选择任何数据!");
		 		return;
		 	}
		 	 AOS.ajax({
            	params : {
            		goods_id: record.data.goods_id
            	},
                url: 'zjcGoodsService.getGoodsInfo',
                ok: function (data) {
                	f_goods_info_u.down("[id='cat_id1']").store.reload();
    	           	f_goods_info_u.down("[id='cat_id1']").setValue(data.cat_id);
    	           	f_goods_info_u.down("[id='cat_id2']").store.reload();
    	           	f_goods_info_u.down("[id='cat_id2']").setValue(data.cat_id2);
    	           	f_goods_info_u.down("[id='brand_id']").store.reload();
    	           	f_goods_info_u.form.setValues(data);
    	           	document.getElementById("original_img_update").src = data.original_img;
    	           	document.getElementById("showImage-update").src = data.original_img;
    	           	w_myGoods_u.show();
                }
            });
		}
		//修改提交
		function f_myGoods_u_submit(){
			AOS.ajax({
                forms: f_goods_info_u,
                url: 'zjcGoodsService.updateGoods',
                ok: function (data) {
                	if(data.appcode == '1'){
                		w_myGoods_u.close();
                		g_myGoods_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
						AOS.get('f_goods_info_u.goods_name').markInvalid(data.appmsg);
						AOS.err(data.appmsg);
                	}
                }
            });
		}
		
		//商品添加
		function f_myGoods_save(){
			AOS.ajax({
                forms: f_goods_info_s,
                url: 'zjcGoodsService.saveGoods',
                ok: function (data) {
                	if(data.appcode == '1'){
                		w_myGoods.close();
                		g_myGoods_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
                		AOS.err(data.appmsg);
						AOS.get('f_goods_info.goods_name').markInvalid(data.appmsg);
                	}
                }
            });
		}
		
		//删除商品类型
        function g_myGoods_del() {
            var selectionIds = AOS.selection(g_myGoods, 'goods_id');
            if (AOS.empty(selectionIds)) {
                AOS.tip('删除前请先选中数据。');
                return;
            }
            var rows = AOS.rows(g_myGoods);
            var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
            AOS.confirm(msg, function (btn) {
                if (btn === 'cancel') {
                    AOS.tip('删除操作被取消。');
                    return;
                }
                AOS.ajax({
                    url: 'zjcGoodsService.deleteGoods',
                    params: {
                        aos_rows: selectionIds
                    },
                    ok: function (data) {
                        AOS.tip(data.appmsg);
                        g_myGoods_store.reload();
                    }
                });
            });

        }
		
      //触发编辑前事件
	  function fn_beforeedit(obj, e) {
		var editing = g_myGoods.getPlugin('id_plugin');
		var rowEditor = editing.getEditor();
		//这行是修复行编辑的一个bug，当数据校验时候如果初始时数据不合法，则数据纠正后保存按钮也不能用的bug。
		rowEditor.on('fieldvaliditychange', rowEditor.onFieldChange,
				rowEditor);
      }
      
		//监听行编辑事件
		function fn_edit(editor, e) {
			if (!e.record.dirty) {
				AOS.tip('数据没变化，提交操作取消。');
				return;
			}
			AOS.ajax({
				params : e.record.data,
				url : 'zjcGoodsService.updateGoods',
				ok : function(data) {
					//保存成功后最好是reload表格，以刷新表格行序号
					g_myGoods_store.reload();
					AOS.tip(data.appmsg);
					//客户端数据状态提交
					e.record.commit();
				}
			});
		}
		//上移排序
		function fn_do_up(grid, rowIndex, colIndex) {
			var rec = grid.getStore().getAt(rowIndex);
            AOS.ajax({
                url: 'zjcGoodsService.upOrDownGoodsSort',
                params: {
                	goods_id: rec.get('goods_id'),
                    upOrDown:"1"
                },
                ok: function (data) {
                    AOS.tip(data.appmsg);
                    g_myGoods_store.reload();
                }
            });
		}
		//下移排序
		function fn_do_down(grid, rowIndex, colIndex) {
			var rec = grid.getStore().getAt(rowIndex);
            AOS.ajax({
                url: 'zjcGoodsService.upOrDownGoodsSort',
                params: {
                	goods_id: rec.get('goods_id'),
                    upOrDown:"2"
                },
                ok: function (data) {
                    AOS.tip(data.appmsg);
                    g_myGoods_store.reload();
                }
            });
		}
		 function setCont(ob,id){
		 alert(ob)
   		ob.setContent(id);
   }
	</script>
</aos:onready>
<script type="text/javascript">
        
   //图片上传
   function f_goods_picture_upload(){
		Ext.getCmp('goods_picture_add_upload').show();
	}
   function f_goods_picture_update_upload(){
		Ext.getCmp('goods_picture_update_upload').show();
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
