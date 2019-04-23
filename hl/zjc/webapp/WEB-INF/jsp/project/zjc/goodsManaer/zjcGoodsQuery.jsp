<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="非自营商品管理" base="http" lib="ext">
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
			<img id="original_img" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv();">
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
			<aos:formpanel id="f_query" layout="column" header="false" border="false" anchor="100%" >
				<aos:docked forceBoder="1 0 1 0"> 
					<aos:dockeditem xtype="tbtext" text="查询条件" />
				</aos:docked>
				<aos:textfield name="user_id" fieldLabel="商家ID"  columnWidth="0.24" />
				<aos:textfield name="store_name" fieldLabel="店铺名称"  columnWidth="0.24" />
				<aos:textfield name="goods_name" fieldLabel="商品名称"  columnWidth="0.24" />
				<aos:combobox name="is_on_sale" fieldLabel="上架" dicField="is" columnWidth="0.25" />
				<aos:combobox name="is_new" fieldLabel="新品" dicField="is" columnWidth="0.25" />
				<aos:combobox name="cat_id" fieldLabel="所有分类" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" columnWidth="0.25" />
				<%-- <aos:combobox name="brand_id" fieldLabel="所有品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.25" /> --%>
				<aos:combobox name="is_vip" fieldLabel="VIP" dicField="is" columnWidth="0.25" />
				<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="button" text="查询" onclick="g_goods_query" icon="query.png" />
					<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
					<aos:dockeditem xtype="tbfill" />
				</aos:docked>
			</aos:formpanel>
		
			<aos:gridpanel id="g_goods" url="zjcGoodsService.listGoods" forceFit="true" autoLoad="true" height="750" anchor="100%">
				<aos:docked forceBoder="0 0 1 0" >
					<aos:dockeditem onclick="w_goods_u_show" text="详情" icon="edit.png" />
				</aos:docked>
				<aos:selmodel type="checkbox" mode="multi" />
				<aos:column type="rowno" />
				<aos:column header="ID" dataIndex="goods_id" hidden="true" />
				<aos:column header="商家ID" dataIndex="user_id"  />
				<aos:column header="店铺名称" dataIndex="store_name" celltip="true"  />
				<aos:column header="商品名称" dataIndex="goods_name" celltip="true" rendererFn="format_goods_name"/>
				<aos:column header="分类" dataIndex="category_name" />
				<aos:column header="在线支付" dataIndex="shop_price" celltip="true" />
				<aos:column header="易支付" dataIndex="market_price" celltip="true" />
				<aos:column header="库存" dataIndex="store_count" celltip="true" />
				<aos:column header="销量" dataIndex="sales_sum" celltip="true" />
				<aos:column header="上架" dataIndex="is_on_sale" celltip="true" rendererField="is_on_sale">
					<aos:combobox dicField="is_on_sale" />
				</aos:column>
				<aos:column header="精品" dataIndex="is_recommend" rendererField="is_recommend">
					<aos:combobox  dicField="is_recommend" />
				</aos:column>
				<aos:column header="新品" dataIndex="is_new" rendererField="is_new">
					<aos:combobox  dicField="is_new" />
				</aos:column>
				<aos:column header="特殊商品" dataIndex="is_car" celltip="true" rendererField="is_car"/>
				<aos:column header="更新时间" dataIndex="last_update" celltip="true" width="150"/>
				<aos:column header="排序" dataIndex="sort" celltip="true" >
					<aos:numberfield allowBlank="false" maxValue="9999999" minValue="1" />
				</aos:column>
			</aos:gridpanel>
		</aos:panel>
	</aos:panel>
	<aos:window id="w_goods_u" title="商品详情" width="1100" maxHeight="750" layout="fit" autoScroll="true"  onclose="closeset">
		<aos:tabpanel id="id_tabs_u" region="center" tabPosition="top" bodyBorder="0 0 0 0" margin="0 0 2 0">
			<aos:tab title="通用信息" >
				<aos:formpanel id="f_goods_info_u" width="650" layout="column" labelWidth="150" msgTarget="qtip">
					<aos:hiddenfield name="goods_id"/>
					<aos:textfield fieldLabel="商品名称" name="goods_name" columnWidth="0.9" readOnly="true"/>
					<aos:textfield fieldLabel="商品货号" name="goods_sn" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商品关键词" name="keywords" emptyText="用空格分隔" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商品重量(g)" name="weight" columnWidth="0.3" readOnly="true"/>
					<aos:combobox id='brand_id' name="brand_id" fieldLabel="商品品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.3" disabled="true"/>
					<aos:combobox id="cat_id1" onselect="cat11nselect" fieldLabel="所属分类" name="cat_id" emptyText="请选择分类" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" disabled="true"/>
					<aos:combobox id="cat_id2" fieldLabel="二级分类" queryMode="local" name="cat_id2" readOnly="true" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" disabled="true"/>
					<aos:textfield fieldLabel="会员周期赠送权重" name="goods_weight"  columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商家赠送比例" name="store_rebate_rate" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="在线支付" name="shop_price" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="易支付" name="market_price" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="库存" name="store_count" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="销量" name="sales_sum" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="今日每人限购次数" name="today_limit_times" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="每人每次限购数量" name="today_limit_nums" columnWidth="0.3" readOnly="true" />
					<aos:textfield fieldLabel="预计发货时间" name="expect_delivery" columnWidth="0.3" emptyText="预计几天内发货"  readOnly="true"/>
					<aos:textfield fieldLabel="客服电话" name="contract_phone" columnWidth="0.3"  readOnly="true"/>
					<aos:radioboxgroup fieldLabel="是否包邮" columns="6" columnWidth="0.4" disabled="true" >
						<aos:radiobox name="is_free_shipping" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_free_shipping" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:textfield fieldLabel="邮费" name="postage" columnWidth="0.2" margin="0 0 0 -110" readOnly="true"/>
					<aos:textfield fieldLabel="型号/规格" name="ts" columnWidth="0.9" readOnly="true"/>
					<aos:radioboxgroup fieldLabel="支付类别" columns="1" columnWidth="1" disabled="true">
						<aos:radiobox name="commodity_categories" boxLabel="优选" inputValue="1" />
						<aos:radiobox name="commodity_categories" boxLabel="礼品兑换" inputValue="3" />
						<aos:radiobox name="commodity_categories" boxLabel="热销预售" inputValue="2" />
					</aos:radioboxgroup>
					<%-- <aos:radioboxgroup fieldLabel="特殊商品" columns="6" columnWidth="0.4" disabled="true">
						<aos:radiobox name="is_car" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_car" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup> --%>
					<aos:radioboxgroup fieldLabel="是否代金券" columns="4" columnWidth="0.3" disabled="true">
						<aos:radiobox name="is_voucher" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_voucher" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="限时抢购" columns="4" columnWidth="0.3" disabled="true" >
						<aos:radiobox name="prompt_goods" boxLabel="是" inputValue="1" />
						<aos:radiobox name="prompt_goods" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="是否VIP" columns="4" columnWidth="0.3" disabled="true">
						<aos:radiobox name="is_vip" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_vip" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<%-- <aos:radioboxgroup fieldLabel="热销预售" columns="6" columnWidth="0.4" disabled="true">
						<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="混合支付" columns="6" columnWidth="0.4" disabled="true">
						<aos:radiobox name="is_mixed" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_mixed" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="特价" columns="4" columnWidth="0.3" disabled="true">
						<aos:radiobox name="special_offer" boxLabel="是" inputValue="1" />
						<aos:radiobox name="special_offer" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="限时抢购" columns="4" columnWidth="0.3" disabled="true" >
						<aos:radiobox name="prompt_goods" boxLabel="是" inputValue="1" />
						<aos:radiobox name="prompt_goods" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="是否VIP" columns="4" columnWidth="0.3">
						<aos:radiobox name="is_vip" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_vip" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup> --%>
					<aos:textareafield fieldLabel="商品简单描述" name="goods_remark" columnWidth="0.9" readOnly="true"/>
					<aos:textareafield fieldLabel="备注" name="remark" columnWidth="0.9" readOnly="true"/>
					<aos:fieldset title="上传商品图片：" labelWidth="120" columnWidth="1" contentEl="original_img_div" collapsible="false" border="false" >
					</aos:fieldset> 
					<aos:fieldset title="限时抢购明细" labelWidth="120" columnWidth="1" border="true" id="limit_buy">
						<aos:datefield name="activity_date" id="activity_date" editable="false" fieldLabel="活动日期" columnWidth="0.42" readOnly="true"/>
						<aos:combobox name="activity_date_part" id="activity_date_part" fieldLabel="活动时间段" dicField="limit_activity_part" columnWidth="0.43" readOnly="true"/>
						<aos:textfield fieldLabel="原在线支付" name="limit_cost_price" id="limit_cost_price" columnWidth="0.42" readOnly="true"/>
						<aos:numberfield fieldLabel="原易支付" name="limit_market_price" id="limit_market_price" minValue="0"  columnWidth="0.43" readOnly="true"/>
					</aos:fieldset>
					<aos:hiddenfield name="original_img"/>
					<aos:hiddenfield name="goods_content"/>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品详情" onactivate="updateOnshow">
				<aos:formpanel id="f_goods_content_u">
					<aos:fieldset labelWidth="120" columnWidth="1" contentEl="div_update_ueditor" collapsible="false" border="false"></aos:fieldset>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品规格" layout="anchor" height="400">
				<aos:formpanel id="f_goods_spe" layout="column" labelWidth="100" margin="5" anchor="100%" border="true">
					<aos:combobox name="type_id" fieldLabel="商品类型" labelWidth="60" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.55" onchange="typeOnchange" disabled="true"/>
					<aos:gridpanel id="g_specItem" url="zjcSpecService.selectItemByTypeID" forceFit="true" border="1 0 1 3" anchor="100%" columnWidth="0.99">
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
			<aos:dockeditem onclick="#w_goods_u.close();" text="关闭" icon="close.png" />
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
			id_tabs_u.setActiveTab(0);
		}
		 //查询参数列表
	    function g_goods_query() {
	    	var params = AOS.getValue('f_query');
	    	g_goods_store.getProxy().extraParams = params;
	        g_goods_store.loadPage(1);
	    /* 	var flag = false;
			for(var item in params){
				if(params[item] != ''){
					flag = true;
					break;
				}
			}
			if(flag){ */
				
			/* }else{
				AOS.tip("查询条件不能为空！");
			} */
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
		
		//初始化编辑器及内容设置
		function updateOnshow(me, records){
			var updateUE = UE.getEditor('update_editor',{
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
		function w_goods_u_show(){
		 	var record = AOS.selectone(g_goods);
		 	f_goods_info_u.form.reset();
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
   	         	document.getElementById("original_img").src = data.original_img;
   	        	document.getElementById("showImage").src = data.original_img;
   	         	w_goods_u.show();
               }
           });
		}
		//修改提交
		function f_goods_u_submit(){
			AOS.ajax({
                forms: f_goods_info_u,
                url: 'zjcGoodsService.updateGoods',
                ok: function (data) {
                	if(data.appcode == '1'){
                		w_goods_u.close();
                		g_goods_store.reload();
                        AOS.tip(data.appmsg);
                	}else{
						AOS.get('f_goods_info_u.goods_name').markInvalid(data.appmsg);
						AOS.err(data.appmsg);
                	}
                }
            });
		}
		//删除商品
        function g_goods_del() {
            var selectionIds = AOS.selection(g_goods, 'goods_id');
            if (AOS.empty(selectionIds)) {
                AOS.tip('删除前请先选中数据。');
                return;
            }
            var rows = AOS.rows(g_goods);
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
                        g_goods_store.reload();
                    }
                });
            });

        }
		
		//触发编辑前事件
	  function fn_beforeedit(obj, e) {
		var editing = g_goods.getPlugin('id_plugin');
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
					g_goods_store.reload();
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
                    g_goods_store.reload();
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
                    g_goods_store.reload();
                }
            });
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
		
	</script>
</aos:onready>
<script type="text/javascript">
   //实例化编辑器
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
