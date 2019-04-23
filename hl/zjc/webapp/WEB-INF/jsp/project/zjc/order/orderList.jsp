<%@ page contentType="text/html;charset=utf-8"%>
<meta http-equiv=”Content-Type” content=”text/html; charset=gb2312”>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="订单列表" base="http" lib="ext">
	<style type="text/css"> 
		a:link { 
			font-size: 12px; 
			color: #000000; 
			text-decoration: none; 
		} 
		a:visited { 
			font-size: 12px; 
			color: #000000; 
			text-decoration: none; 
		} 
		a:hover { 
			font-size: 12px; 
			color: #999999; 
			text-decoration: underline; 
		}
	</style>
	<script type="text/javascript" src="${cxt}/static/zjc/js/jquery.min.js"></script>
    <!-- UEditer -->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/lang/zh-cn/zh-cn.js"></script>
	<aos:body>
		<div id="btnDiv" class="x-hidden">
		</div>
		<div id="findDeliDiv" class="x-hidden"></div>
		<!-- UEditer -->
		<div id="div_update_ueditor" class="x-hidden">
			<script id="update_editor" type="text/plain" style="width:1070px;height:400px;"></script>
		</div>
		<div id="original_img_div" class="x-hidden" >
			<img id="original_img" src="" class="app_cursor_pointer" width="215px" height="100px" style="margin-left: 105px;display: block; float: left;" onclick="showdiv();">
			<!-- <button class="btn" style="margin: 30px 0px 0px 30px;padding: 10px;" onclick="f_goods_picture_upload();">上传图片</button> -->
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
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" />
			<aos:textfield name="mj" fieldLabel="商家ID" columnWidth="0.33" />
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.33" />
			<aos:textfield name="goods_name" fieldLabel="商品名称" columnWidth="0.33" />
			<%-- <aos:textfield name="contacts_phone" fieldLabel="商家电话" columnWidth="0.33" /> --%>
			<%-- <aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.33" /> --%>
			<aos:textfield name="order_sn" fieldLabel="订单编号" columnWidth="0.33" value="0"/>
			<aos:textfield name="consignee" fieldLabel="收货人"  columnWidth="0.33" /> 
			<aos:datefield name="add_time_start" fieldLabel="下单时间(起)"  format="Y-m-d 00:00:00" editable="false" value="new Date()" columnWidth="0.33" id="addStart"/>
			<aos:datefield name="add_time_end" fieldLabel="下单时间(止)" format="Y-m-d 23:59:59" editable="false" value="NOM()" columnWidth="0.33" id="addEnd"/>
			<aos:combobox name="order_status"  fieldLabel="订单状态" emptyText="订单状态" dicField="order_status" columnWidth="0.33" />
			<aos:combobox name="pay_status" fieldLabel="支付状态" emptyText="支付状态" dicField="pay_status" columnWidth="0.33" />
			<aos:combobox name="pay_code" fieldLabel="支付方式"  emptyText="支付方式" dicField="pay_code" columnWidth="0.33" />
			<aos:combobox name="shipping_status" fieldLabel="发货状态" emptyText="发货状态" dicField="shipping_status" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="导出" onclick="fn_export_excel" icon="icon70.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcOrderService.listZjcOrders" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="订单列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="订单ID" dataIndex="order_id" hidden="true"/>
			<aos:column header="商品ID" dataIndex="goods_id" hidden="true"/>
			<aos:column header="特殊商品" dataIndex="is_car" hidden="true"/>
			<aos:column header="支付时间" dataIndex="pay_time" hidden="true"/>
			<aos:column header="商家ID" dataIndex="mj" width="60" />
			<aos:column header="订单编号" dataIndex="order_sn" celltip="true" width="120" rendererFn="format_ordersn"/>
			<aos:column header="店铺名称" dataIndex="store_name" celltip="true"/>
			<aos:column header="商品名称" dataIndex="goods_name" celltip="true" rendererFn="to_goods_page"/>
			<aos:column header="商品数量" dataIndex="goods_num" width="60"/>
			<aos:column header="会员ID" dataIndex="user_id" width="60"/>
			<aos:column header="收货人" dataIndex="consignee" width="70"/>
			<%-- <aos:column header="收货人地址" dataIndex="address" width="70" celltip="true"/> --%>
			<aos:column header="现金" dataIndex="cash" width="60"/>
			<aos:column header="券" dataIndex="barter_coupons" rendererFn="praseToInt" width="60"/>
			<aos:column header="支付状态" dataIndex="pay_status" rendererField="pay_status" width="60"/>
			<aos:column header="订单状态" dataIndex="order_status"  hidden="true"/>
			<aos:column header="支付方式" dataIndex="pay_code" rendererField="pay_code_name" width="60"/>
			<%-- <aos:column header="发货状态" dataIndex="shipping_status" rendererField="shipping_status" width="60"/> --%>
			<aos:column header="下单时间" dataIndex="add_time" celltip="true" width="120"/>
		<%-- 	<aos:column header="发货时间" dataIndex="shipping_time" rendererFn="format_shippingTime" celltip="true" width="120"/>
			<aos:column header="收货时间" dataIndex="confirm_time" rendererFn="format_confirmTime" celltip="true" width="120"/> --%>
			<aos:column header="操作" type="action" align="center" width="70">
				<aos:action handler="getOrderInfo" icon="query.png" tooltip="查看"/>
				<aos:action handler="deleteOrderInfo" icon="del.png" tooltip="删除" disabledFn="fn_disabled"/>
				<aos:action handler="afterSale" icon="edit.png" tooltip="售后处理" disabledFn="fn_aftersaledisabled"/>
				</aos:column>
		</aos:gridpanel>
		
		<!-- 订单详情订单确认页面 -->
		<aos:window id="w_order_detail" layout="border" title="订单详情" maximized="true" autoScroll="true" onshow="order_onShow">
			<aos:formpanel id="f_order_detail" layout="column" labelWidth="70" margin="5" anchor="100%" border="false" header="false" region="north" autoScroll="true">
				<%-- 隐藏变量 --%>
				<%-- <aos:hiddenfield name="order_id"/> --%>
				<aos:fieldset title="基本信息" labelWidth="70" >
					<aos:textfield name="order_id" fieldLabel="订单ID" border="none" id="orderid" readOnly="true" columnWidth="0.33" />
					<aos:textfield name="order_sn" fieldLabel="订单编号" border="none" readOnly="true" columnWidth="0.33" />
					<aos:textfield name="store_name" fieldLabel="所属商家" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="user_name" fieldLabel="会员名称" columnWidth="0.33" readOnly="true" emptyText="暂无"/>
					<aos:textfield name="email" fieldLabel="E-Mail" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="goods_price" fieldLabel="商品总价" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="cash" fieldLabel="应付现金" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="barter_coupons" fieldLabel="应付券" columnWidth="0.33" readOnly="true"/>
					<aos:combobox fieldLabel="订单状态" name="order_status" id="order_status" dicField="order_status" columnWidth="0.33" readOnly="true"/>
					<aos:combobox fieldLabel="支付状态" name="pay_status" dicField="pay_status" columnWidth="0.33" readOnly="true"/>
					<aos:combobox fieldLabel="发货状态" name="shipping_status" dicField="shipping_status" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="add_time" fieldLabel="下单时间" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="pay_time" fieldLabel="支付时间" columnWidth="0.33" />
					<aos:combobox fieldLabel="支付方式" id="pay" name="pay_code" dicField="pay_code" columnWidth="0.33" emptyText="暂无" readOnly="true"/>
				</aos:fieldset>
				<aos:fieldset title="收货信息" labelWidth="70" margin="0 0 20 0">
					<aos:textfield name="consignee" fieldLabel="收货人" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="address" fieldLabel="收货地址" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="zipcode" id="zipcode" fieldLabel="邮政编码" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="tracking_no" fieldLabel="物流单号" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="user_note" fieldLabel="用户备注" columnWidth="0.33" emptyText="暂无" readOnly="true"/>
				</aos:fieldset>
			</aos:formpanel>
			<aos:gridpanel id="g_goods" region="north" url="zjcOrderService.getGoodsByOrderId" features="summary" hidePagebar="true">
				<aos:docked forceBoder="0 0 1 0">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="tbtext" text="商品信息" />
					<aos:dockeditem xtype="tbfill" />
				</aos:docked>
				<aos:column header="商品名称" dataIndex="goods_name" />
				<aos:column header="商品属性" dataIndex="spec_key_name" />
				<aos:column header="数量" dataIndex="goods_num"/>
				<%-- <aos:column header="单品价格（元）" dataIndex="goods_price"/> --%>
				<%-- <aos:column header="会员折扣价（元）" dataIndex="member_goods_price"/> --%>
				<%-- <aos:column header="本店价(等额积分支付)" dataIndex="goods_price"/> --%>
				<aos:column header="现金" dataIndex="cost_price"/>
				<aos:column header="券" dataIndex="market_price"/>
				<%-- <aos:column header="单品小计" dataIndex="single_fee" type="number" rendererFn="sum_fee" /> --%>
			</aos:gridpanel>
			
			<aos:gridpanel id="g_work_record" region="north" url="zjcOrderService.getOrderActionByOrderId" hidePagebar="true">
				<aos:docked forceBoder="0 0 1 0">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem xtype="tbtext" text="操作记录" />
					<aos:dockeditem xtype="tbfill" />
				</aos:docked>
				<aos:column type="rowno" />
				<aos:column header="操作者ID" dataIndex="action_user" width="150" />
				<aos:column header="操作者名称" dataIndex="action_user_name" width="150" />
				<aos:column header="操作时间" dataIndex="log_time" format="Y-m-d" width="150" />
				<aos:column header="订单状态" dataIndex="order_status" rendererField="order_status" width="180" />
				<aos:column header="付款状态" dataIndex="pay_status" rendererField="pay_status" width="220" />
				<aos:column header="发货状态" dataIndex="shipping_status" rendererField="shipping_status"/>
				<aos:column header="描述" dataIndex="status_desc" width="220" celltip="true"/>
				<aos:column header="备注" dataIndex="action_note" celltip="true"/>
			</aos:gridpanel>
			<aos:panel anchor="100%" layout="fit"  border="false" region="north" id="g_word_panel">
				<aos:formpanel id="g_work" layout="column" labelWidth="70" border="false">
					<aos:docked forceBoder="0 0 0 0">
						<aos:dockeditem xtype="tbfill" />
						<aos:dockeditem xtype="tbtext" text="操作信息" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
					<aos:textfield name="remark" id="remark" fieldLabel="操作备注" columnWidth="0.33" />
					<aos:container margin="0 0 0 15" width="60" contentEl="btnDiv"></aos:container>
				</aos:formpanel>
			</aos:panel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="invoice" text="配货单" icon="ok.png" />
				<aos:dockeditem onclick="close" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
		
		<!-- 配货单页面 -->
		<aos:window id="w_invoice" layout="border" title="配货单" maximized="true" autoScroll="true" onshow="getInvoiceInfo">
			<aos:formpanel id="invoiceForm" layout="column" labelWidth="70" margin="5" anchor="100%" border="false" header="false" region="north" autoScroll="true">
				<%-- 隐藏变量 --%>
				<aos:hiddenfield name="order_id"/>
				<aos:fieldset title="订单详情" labelWidth="70" >
					<aos:textfield name="order_sn" fieldLabel="订单编号" border="false" readOnly="true" columnWidth="0.5" />
					<aos:textfield name="add_time" fieldLabel="下单时间" columnWidth="0.5" readOnly="true"/>
					<aos:textfield name="tracking_no" fieldLabel="物流单号" columnWidth="0.5" readOnly="true"/>
					<aos:combobox name="pay_code" fieldLabel="支付方式" dicField="pay_code" columnWidth="0.5" emptyText="暂无" readOnly="true"/>
				</aos:fieldset>
				<aos:fieldset title="发送自" labelWidth="70" onrender="getSendInfo">
					<aos:textfield id="store_name" fieldLabel="公司名称" columnWidth="0.5" readOnly="true"/>
					<aos:textfield id="area_info" fieldLabel="发货地址" columnWidth="0.5" readOnly="true"/>
					<aos:textfield id="contacts_phone" fieldLabel="联系电话" columnWidth="0.5" readOnly="true"/>
					<aos:textfield id="contacts_email" fieldLabel="电子邮箱" columnWidth="0.5" readOnly="true"/>
				</aos:fieldset>
				<aos:fieldset title="收货信息" labelWidth="70" margin="0 0 20 0">
					<aos:textfield name="consignee" fieldLabel="收货人" columnWidth="0.5" readOnly="true"/>
					<aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.5" readOnly="true"/>
					<aos:textfield name="zipcode" id="zipcodes" fieldLabel="邮政编码" columnWidth="0.5" readOnly="true"/>
					<aos:textfield name="address" fieldLabel="收货地址" columnWidth="0.5" readOnly="true"/>
				</aos:fieldset>
			</aos:formpanel>
			<aos:gridpanel id="g_goods_invoice" region="north" url="zjcOrderService.getGoodsByOrderId2" onrender="invoice_goods_query" height="200" hidePagebar="true">
					<aos:docked forceBoder="0 0 1 0">
						<aos:dockeditem xtype="tbtext" text="商品信息" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
					<aos:column header="商品名称" dataIndex="goods_name" rendererFn="goodsBtn"/>
					<aos:column header="商品属性" dataIndex="spec_key_name" />
					<aos:column header="数量" dataIndex="goods_num" />
					<aos:column header="商品单价" dataIndex="goods_price" />
			</aos:gridpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="print" text="打印配货单" icon="ok.png" />
				<aos:dockeditem onclick="invoice_close" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
		<!-- 订单详情发货页面 -->
		<aos:window id="w_order_delivery" layout="border" title="订单发货" maximized="true" autoScroll="true" ><!-- delivery_onShow -->
			<aos:formpanel id="deliveryForm" layout="column" labelWidth="70" margin="5" anchor="100%" border="false" header="false" region="north" autoScroll="true" onrender="getOrderBasicInfo">
				<%-- 隐藏变量 --%>
				<aos:hiddenfield name="order_id"/>
				<aos:fieldset title="基本信息" labelWidth="70" >
					<aos:textfield name="order_sn" fieldLabel="订单编号" border="false" readOnly="true" columnWidth="0.33" />
					<aos:textfield name="add_time" fieldLabel="下单时间" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="store_name" fieldLabel="所属商家" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="shipping_price" fieldLabel="配送费用" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="invoice_no" id="invoice_no" fieldLabel="物流单号" columnWidth="0.33" allowBlank="false"/>
				</aos:fieldset>
				<aos:fieldset title="收货信息" labelWidth="70">
					<aos:textfield name="consignee" fieldLabel="收货人" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="zipcode" id="zipcodees" fieldLabel="邮政编码" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="address" fieldLabel="收货地址" columnWidth="0.33" readOnly="true"/>
				</aos:fieldset>
				<aos:fieldset title="发货记录" labelWidth="70" onrender="getDeliveryRecord" margin="0 0 20 0">
					<aos:hiddenfield name="id"/>
					<%-- <aos:textfield name="user_id" fieldLabel="操作者" columnWidth="0.5" /> --%>
					<aos:textfield name="create_time" fieldLabel="发货时间" emptyText="暂无" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="invoice_no" id="invoice_no" fieldLabel="发货单号" emptyText="暂无" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="consignee" id="consignee" fieldLabel="收货人" emptyText="暂无" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="shipping_name" fieldLabel="快递公司" emptyText="暂无" columnWidth="0.33" readOnly="true"/>
					<aos:textfield name="note" fieldLabel="备注" columnWidth="0.33" emptyText="暂无" readOnly="true"/>
					<aos:container margin="0 0 0 30" width="60" contentEl="findDeliDiv"></aos:container>
				</aos:fieldset>
			</aos:formpanel>
			<aos:gridpanel id="g_goods_delivery" region="north" url="zjcOrderService.getGoodsByOrderId" onrender="delivery_goods_query" height="200" hidePagebar="true">
					<aos:docked forceBoder="0 0 1 0">
						<aos:dockeditem xtype="tbtext" text="商品信息" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
					<aos:column header="商品名称" dataIndex="goods_name" rendererFn="goodsBtn"/>
					<aos:column header="商品属性" dataIndex="spec_key_name" />
					<aos:column header="数量" dataIndex="goods_num"/>
					<aos:column header="商品单价" dataIndex="goods_price"/>
			</aos:gridpanel>
			<aos:panel anchor="100%" layout="fit"  border="false" region="north" id="p_delivery">
				<aos:formpanel id="f_delivery_info" layout="column" labelWidth="70" border="false">
					<aos:docked forceBoder="0 0 0 0">
						<aos:dockeditem xtype="tbtext" text="发货信息" />
						<aos:dockeditem xtype="tbfill" />
					</aos:docked>
					
				</aos:formpanel>
			</aos:panel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:textfield name="note" id="note" fieldLabel="发货单备注" columnWidth="0.33" />
				<aos:dockeditem onclick="confirm_delivery" text="确认发货" icon="ok.png"  margin="0 0 0 5"/>			
			</aos:docked>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="invoice_delivery" text="配货单" icon="ok.png" />
				<aos:dockeditem onclick="delivery_close" text="关闭" icon="close.png" />
			</aos:docked>
		</aos:window>
		
		<!-- 查看商品详情 -->
		<aos:window id="w_goods_u" title="商品详情" width="1100" maxHeight="750" layout="fit" autoScroll="true" onclose="closeset">
		<aos:tabpanel id="id_tabs_u" region="center" tabPosition="top" bodyBorder="0 0 0 0" margin="0 0 2 0">
			<aos:tab title="通用信息" >
				<aos:formpanel id="f_goods_info_u" width="650" layout="column" labelWidth="150" msgTarget="qtip">
					<aos:hiddenfield name="goods_id"/>
					<aos:textfield fieldLabel="商品名称" name="goods_name" columnWidth="0.9" readOnly="true"/>
					<aos:textfield fieldLabel="商品货号" name="goods_sn" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商品关键词" name="keywords" emptyText="暂无关键字" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商品重量(g)" name="weight" columnWidth="0.3" />
					<aos:combobox id='brand_id' name="brand_id" fieldLabel="商品品牌" url="zjcBrandService.listBrandComboBoxData" columnWidth="0.3" emptyText="暂无品牌" readOnly="true"/>
					<aos:combobox id="cat_id1" fieldLabel="所属分类" name="cat_id" emptyText="暂无分类" columnWidth="0.3" readOnly="true" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam&parent_id=0" />
					<aos:combobox id="cat_id2" fieldLabel="二级分类" emptyText="暂无分类" queryMode="local" name="cat_id2" readOnly="true" columnWidth="0.3" url="zjcGoodsCategoryService.listPositionComboBoxDataByParam" />
					<aos:textfield fieldLabel="会员周期赠送权重" name="goods_weight"  columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="商家赠送比例" name="store_rebate_rate" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="在线支付" name="shop_price" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="易支付" name="market_price" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="库存" name="store_count" columnWidth="0.3" readOnly="true"/>
					<aos:textfield fieldLabel="销量" name="sales_sum" columnWidth="0.3" readOnly="true"/>
					<aos:radioboxgroup fieldLabel="支付类别" columns="1" columnWidth="0.3" disabled="true">
						<aos:radiobox name="commodity_categories" boxLabel="购商品" inputValue="1" />
						<aos:radiobox name="commodity_categories" boxLabel="购实惠" inputValue="2" />
						<aos:radiobox name="commodity_categories" boxLabel="易生活" inputValue="3" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="特殊商品" columns="6" columnWidth="0.4" disabled="true">
						<aos:radiobox name="is_car" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_car" boxLabel="否" inputValue="0" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="混合支付" columns="6" columnWidth="0.4" disabled="true">
						<aos:radiobox name="is_mixed" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_mixed" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="特价" columns="6" columnWidth="0.4" disabled="true">
						<aos:radiobox name="special_offer" boxLabel="是" inputValue="1" />
						<aos:radiobox name="special_offer" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="热销" columns="2" columnWidth="0.537" margin="0 0 0 323" disabled="true">
						<aos:radiobox name="is_hot" boxLabel="是" inputValue="1" />
						<aos:radiobox name="is_hot" boxLabel="否" inputValue="0" checked="true"/>
					</aos:radioboxgroup>
					<aos:textareafield fieldLabel="商品简单描述" name="goods_remark" columnWidth="0.9" readOnly="true"/>
					<aos:fieldset title="上传商品图片：" labelWidth="120" columnWidth="1" contentEl="original_img_div" collapsible="false" border="false" >
					</aos:fieldset> 
					<aos:hiddenfield name="original_img"/>
					<aos:hiddenfield name="goods_content"/>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品详情" onactivate="updateOnshow">
				<aos:formpanel id="f_goods_content_u">
					<aos:fieldset labelWidth="120" columnWidth="1" contentEl="div_update_ueditor" collapsible="false" border="false" disabled="true"></aos:fieldset>
				</aos:formpanel>
			</aos:tab>
			<aos:tab title="商品规格" layout="anchor" height="400">
				<aos:formpanel id="f_goods_spe" layout="column" labelWidth="100" margin="5" anchor="100%" border="true">
					<aos:combobox name="type_id" fieldLabel="商品类型" labelWidth="60" url="zjcGoodsTypeService.listTypeComboBoxData" columnWidth="0.55" readOnly="true"/>
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
	
	<aos:window id="w_sale" title="订单售后页面">
		<aos:formpanel id="f_sale" width="800" layout="column" labelWidth="120">
			<aos:hiddenfield name="order_id" />
			<aos:hiddenfield name="user_id" />
			<aos:hiddenfield name="order_sn" />
			<aos:textfield name="barter_coupons" fieldLabel="待处理券" id="bCoupons" maxLength="50"  columnWidth="0.45" readOnly="true"/>
			<aos:textfield fieldLabel="首次处理比例" name="proportion" columnWidth="0.45" emptyText="请输入大于0的正数" center="true" margin="0 0 0 10" allowBlank="false"/>
		</aos:formpanel>
		<aos:docked dock="bottom" ui="footer">
			<aos:dockeditem xtype="tbfill" />
			<aos:dockeditem onclick="f_sale_submit" text="保存" icon="ok.png" />
			<aos:dockeditem onclick="#w_sale.hide();" text="关闭" icon="close.png" />
			<aos:dockeditem xtype="tbfill" />
		</aos:docked>
	</aos:window>
	
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function order_query() {
			var params = AOS.getValue('f_query');
			g_order_store.getProxy().extraParams = params;
			g_order_store.loadPage(1);
		}
		
		//导出订单记录excel
		function fn_export_excel(){
			var params = AOS.getValue('f_query'); 
			//juid需要再这个页面的初始化方法中赋值,这里才引用得到
			//httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
			AOS.file('do.jhtml?router=zjcOrderService.exportOrderInfo&juid=${juid}&mj='+params.mj +'&store_name='+encodeURI(encodeURI(params.store_name)) +'&goods_name='+encodeURI(encodeURI(params.goods_name)) 
					+ '&order_sn=' + params.order_sn +'&add_time_start=' + params.add_time_start +'&add_time_end=' + params.add_time_end + '&order_status=' + params.order_status
					+ '&pay_status=' + params.pay_status + '&pay_code=' + params.pay_code+'&shipping_status='+params.shipping_status+'&user_id='+params.user_id);
		}
		
		function to_goods_page(value, metaData, record, rowIndex, colIndex,store){
			var goods_id = record.data.goods_id;
			var is_car = record.data.is_car;
			if(is_car==1){
				return '<a href="javascript:void(0)" onclick="to_page('+goods_id+')" style="color:red">' +  record.data.goods_name + '</a>';
			} else {
				return '<a href="javascript:void(0)" onclick="to_page('+goods_id+')" style="color:blue">' +  record.data.goods_name + '</a>';
			}
		}
		//格式化订单编号
		function format_ordersn(value, metaData, record, rowIndex, colIndex,store){
			var pay_status = record.data.pay_status;
			var pay_time = record.data.pay_time;
			if(pay_time == '' && pay_status == 1){//手动点击确认支付操作
				return '<div style="color:red">'+ record.data.order_sn +'</div>';
			} else {
				return '<div>'+ record.data.order_sn +'</div>';
			}
		}
		
		//格式化发货时间
		function format_shippingTime(value, metaData, record, rowIndex, colIndex,store){
			var shippingTimeStr = record.data.shipping_time;
			if(shippingTimeStr == ''){
				return '未确认发货';
			} else {
				return '<div>'+ shippingTimeStr +'</div>';
			}
		}
		
		function quzheng(value, metaData, record, rowIndex, colIndex,store){
			return parseInt(record.data.barter_coupons);
		}
		//格式化收货时间
		function format_confirmTime(value, metaData, record, rowIndex, colIndex,store){
			var confirmTimeStr = record.data.confirm_time;
			if(confirmTimeStr == ''){
				return '未确认收货';
			} else {
				return '<div>'+ confirmTimeStr +'</div>';
			}
		}
		//格式化券
		function praseToInt(value, metaData, record, rowIndex, colIndex,store){
			var barter_coupons = record.data.barter_coupons;
			if(barter_coupons == ''){
				return '0';
			} else {
				barter_coupons = barter_coupons.split(".");
				return '<div>'+ barter_coupons[0] +'</div>';
			}
		}
		
		//打开查询订单详情页面
		function getOrderInfo(grid, rowIndex, colIndex){
			var record = grid.getStore().getAt(rowIndex);
			AOS.ajax({
	          	params : {
	          		order_id: record.data.order_id
	          	},
	              url: 'zjcOrderService.getZjcOrderByOrderId',
	              ok: function (data) {
	  				addHtml(data.order_status,data.shipping_status,data.pay_status);
	            	  w_order_detail.show();
	              }
	         });
			
		}
		//订单详情window显示的时候执行
		function order_onShow(){
			//初始化加载订单基本信息
			getOrderDetailInfo();
			//初始化加载商品信息
			goods_query();
			//初始化加载操作记录
			action_query();
			var record = AOS.selectone(g_order);
			//动态隐藏操作信息
			if(record.data.order_status!='0'&& record.data.order_status!='1'){
				g_word_panel.hide();
			} else if( (record.data.order_status=='1'&& record.data.shipping_status =='1')){
				g_word_panel.hide();
			} else {
				g_word_panel.show();
			}
		}
		
		//根据值禁用删除按钮
		function fn_disabled(view, rowIndex, colIndex, item, record) {
			//订单状态为已取消时，可以删除
			if (record.data.order_status == '3') {
				return false;
			} else {//否则不能删除
				return true;
			}
		}
		
		//删除订单
		function deleteOrderInfo(grid, rowIndex, colIndex){
			var record = AOS.selectone(g_order);
			var msg =  AOS.merge('确认要删除订单【{0}】吗？', record.data.order_sn);
			AOS.confirm(msg, function(btn){
				if(btn === 'cancel'){
					AOS.tip('删除操作被取消。');
					return;
				}
				AOS.ajax({
					url : 'zjcOrderService.delOrderInfo',
					params:{
						order_id: record.data.order_id
					},
					ok : function(data) {
						AOS.tip(data.appmsg);
						g_order_store.reload();
					}
				});
			});
		}
        
      //初始化加载基本信息数据
       function getOrderDetailInfo(){
    	   Ext.getCmp('zipcode').setValue();
    	   Ext.getCmp('zipcodes').setValue();
    	   Ext.getCmp('zipcodees').setValue();
		  var record = AOS.selectone(g_order);
		  AOS.ajax({
          	params : {
          		order_id: record.data.order_id
          	},
              url: 'zjcOrderService.getZjcOrderByOrderId',
              ok: function (data) {
	            	//设置订单状态默认值
	              	if(data.order_status == 0){
	              		data.order_status = "0";
	              	} else if(data.order_status == 1){
	              		data.order_status = "1";
	              	} else if(data.order_status == 2){
	              		data.order_status = "2";
	              	} else if(data.order_status == 3){
	              		data.order_status = "3";
	              	} else if(data.order_status == 4){
	              		data.order_status = "4";
	              	} else if(data.order_status == 5){
	              		data.order_status = "5";
	              	}  
	              	//设置支付状态默认值
	              	if(data.pay_status == 0){
	              		data.pay_status = "0";
	              	} else if(data.pay_status == 1){
	              		data.pay_status = "1";
	              	} 
	             	//设置发货状态默认值
	              	if(data.shipping_status == 0){
	              		data.shipping_status = "0";
	              	} else if(data.shipping_status == 1){
	              		data.shipping_status = "1";
	              	}
            	    f_order_detail.form.setValues(data);
              }
         })
      }
        
		//初始化加载订单商品信息
		function goods_query() {
			var record = AOS.selectone(g_order);
			AOS.ajax({
				wait : false, //防止出现2个遮罩层。(ajax和表格load)
				params : {
					order_id : record.data.order_id
				},
				url : 'zjcOrderService.querySummary',
				ok : function(data) {
					summary = data;
					g_goods_store.getProxy().extraParams = {
						order_id : record.data.order_id
					};
					g_goods_store.loadPage(1);
				}
			});
		}
		//计算商品单品小计
		function sum_fee(value, metaData, record){
			var pay=Ext.getCmp('pay').getValue();
			if(pay==null){
				value=accSub(0,0,0);
			}else if(pay=="rate"){
				value=accSub(record.data.market_price,0,record.data.goods_num);
			}else if(pay=="equal"){
				value=accSub(record.data.goods_price,0,record.data.goods_num);
			}else if(pay=="cash"||pay=="wxpay"||pay=="alipay"){
				value=accSub(record.data.cost_price,0,record.data.goods_num);
			}else if(pay=="mixed_payment"){
				var sum=record.data.cost_price+record.data.market_price;
				value=accSub(sum,0,record.data.goods_num);
			}
			return Math.abs(value);
		}
		
		/** 
		 * 浮点数减法运算方法复写
		 */  
		function accSub(arg1,arg2,arg3){  
		    var r1,r2,m,n;  
		    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
		    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
		    m=Math.pow(10,Math.max(r1,r2));  
		    //last modify by deeka  
		    //动态控制精度长度  
		    n=(r1>=r2)?r1:r2;
		    return ((arg1*m*arg3-arg2*m)/m).toFixed(n);
		    }  
		
		//初始化操作记录数据
		function action_query(){
			var record = AOS.selectone(g_order);
			//初始化加载操作记录
			g_work_record_store.getProxy().extraParams = {
				order_id : record.data.order_id
			};
			g_work_record_store.loadPage(1);
		}
		//关闭订单确认窗口
		function close(){
			Ext.getCmp('remark').setValue("");
			w_order_detail.hide();
		}
		//关闭配货单页面
		function invoice_close(){
			w_invoice.hide();
			g_order_store.reload();
			
		}
		//打开配货单页面
		function invoice(){
			w_order_detail.hide();
			w_invoice.show();
			 getInvoiceInfos();
		}
		//发货页面打开配送单
		function invoice_delivery(){
			w_order_delivery.hide();
			w_invoice.show();
		}
		
		 //配货单页面订单基本信息查询
		  function getInvoiceInfo(){
			  var record = AOS.selectone(g_order);
			  AOS.ajax({
	          	params : {
	          		order_id: record.data.order_id
	          	},
	              url: 'zjcOrderService.getZjcOrderInfoByOrderId',
	              ok: function (data) {
	            	  Ext.getCmp('invoiceForm').doLayout();
	            	  invoiceForm.form.setValues(data);
	            	 
	              }
	         })
		  }
		 
		  function getInvoiceInfos(){
			  var record = AOS.selectone(g_order);
				g_goods_invoice_store.getProxy().extraParams = {
					order_id : record.data.order_id
				};
				g_goods_invoice_store.loadPage(1);
		  }
		 
		 
		 //配货单页面发送方信息查询
		 function getSendInfo(){
			 var record = AOS.selectone(g_order);
			  AOS.ajax({
	          	params : {
	          		store_id: record.data.store_id
	          	},
	              url: 'zjcStoreService.getStoreInfoByStoreId',
	              ok: function (data) {
	            	  Ext.getCmp('store_name').setValue(data.store_name);
	            	  Ext.getCmp('address').setValue(data.area_info+''+data.address_detail);
	            	  Ext.getCmp('contacts_phone').setValue(data.contacts_phone);
	            	  Ext.getCmp('contacts_email').setValue(data.contacts_email);
	              }
	         })
		 }
		
		//初始化加载订单商品信息（页面）
		function invoice_goods_query() {
			var record = AOS.selectone(g_order);
			g_goods_invoice_store.getProxy().extraParams = {
				order_id : record.data.order_id
			};
			g_goods_invoice_store.loadPage(1);
		}
		 
		//点击查看订单详情时，判断订单状态（订单详情页面）
	    function addHtml(order_status,shipping_status,pay_status){ 
	    	var btnDiv = document.getElementById("btnDiv");
			if(order_status == '0' && pay_status == '1'){//订单状态为待确认时
				btnDiv.innerHTML = ' <input type="button" value="确认" class="cbtn_danger" onclick="confirm();" />';
			} else if(order_status == '1' && shipping_status == '0' && pay_status == '1'){//订单状态为已确认且发货状态为未发货,支付状态为已支付时
				btnDiv.innerHTML = ' <input type="button" value="去发货" class="cbtn_danger" onclick="deliver();" />'
			}else{
				btnDiv.innerHTML = "";
			}
	    }
		
	  //初始化加载订单商品信息（订单详情页面）
		function delivery_goods_query() {
			var record = AOS.selectone(g_order);
			g_goods_delivery_store.getProxy().extraParams = {
				order_id : record.data.order_id
			};
			g_goods_delivery_store.loadPage(1);
		}
	  //发货页面订单基本信息查询
	  function getOrderBasicInfo(){
		  var record = AOS.selectone(g_order);
		  AOS.ajax({
          	params : {
          		order_id: record.data.order_id
          	},
              url: 'zjcOrderService.getZjcOrderByOrderId',
              ok: function (data) {
            	  deliveryForm.form.setValues(data);
              }
         })
         var findDeliDiv = document.getElementById("findDeliDiv");
		 findDeliDiv.innerHTML = '<a href="http://www.kuaidi100.com/" style="text-decoration:none;color:green;" target="_blank">查看物流</a>';
	  }
	  
	  //查询发货记录
	  function getDeliveryRecord(){
		  var record = AOS.selectone(g_order);
		  AOS.ajax({
	          	params : {
	          		order_id: record.data.order_id
	          	},
	              url: 'zjcOrderService.getZjcDeliveryByOrderId',
	              ok: function (data) {
	            	  if(data != null){
	            		  Ext.getCmp("invoice_no").setValue(data.invoice_no);
	            		  Ext.getCmp("consignee").setValue(data.consignee);
	            	  }
	            	  
	            	  deliveryForm.form.setValues(data);
	              }
	         })
	  }
	  
	  //商品信息商品名称列转换
	  function goodsBtn(value, metaData, record, rowIndex, colIndex,store){
		  return '<a href="#" style="text-decoration:none;color:green;" target="_blank">'+ record.data.goods_name +'</a>';
	  }
	  
	  //查看发货记录时，生产查看物流按钮
	  function deliRecord_onshow(){
		 var findDeliDiv = document.getElementById("findDeliDiv");
		 findDeliDiv.innerHTML = '<a href="http://www.kuaidi100.com/" style="text-decoration:none;color:green;" target="_blank">查看物流</a>';
	  }
	  
	  //确认发货
	  function confirm_delivery(){
		  var record = AOS.selectone(g_order);
		  var invoice_no = deliveryForm.down('[name=invoice_no]').getValue();
		  var note = Ext.getCmp('note').getValue();
		  var reg = new RegExp("^[0-9]*$");
		  if(invoice_no==""||invoice_no==null){
			  AOS.tip("请输入物流单号!");
			  return false;
		  }
		  /*   if(!reg.test(invoice_no)){
		    	AOS.tip("对不起，您输入的整数类型格式不正确!");
				  return false;
		    }
		    if(!/^[0-9]*$/.test(invoice_no)){
		    	AOS.tip("对不起，您输入的整数类型格式不正确!");
				  return false;
		    } */
		  AOS.ajax({
		        params: {
		         order_id: record.data.order_id,
		         invoice_no : invoice_no,
		         note:note
		       	},
		        url : 'zjcOrderService.delivery',
		        ok : function(data) {
		        	p_delivery.hide();
		        	getDeliveryRecord();
		        	AOS.tip(data.appmsg);
		        }
		    }); 
	  }
	  
	  //发货页面关闭窗口
	  function delivery_close(){
		  Ext.getCmp('invoice_no').setValue("");
		  w_order_delivery.hide();
		  g_order_store.reload();
	  }
	  //获取当前时间
	  function getNowDate(){
		  var date = new Date();
		  var seperator1 = "-";
		  var seperator2 = ":";
		  var month = date.getMonth() + 1;
		  var strDate = date.getDate();
		  if (month >= 1 && month <= 9) {
		      month = "0" + month;
		  }
		  if (strDate >= 0 && strDate <= 9) {
		      strDate = "0" + strDate;
		  }
		  var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		          + " " + date.getHours() + seperator2 + date.getMinutes()
		          + seperator2 + date.getSeconds();
		  return currentdate;
	  }
	  //获取3个月前的时间
	  function get3MonthBefor(){
	      var resultDate,year,month,date,hms;
	      var currDate = new Date();
	      year = currDate.getFullYear();
	      month = currDate.getMonth()+1;
	      date = currDate.getDate();
	      hms = currDate.getHours() + ':' + currDate.getMinutes() + ':' + (currDate.getSeconds() < 10 ? '0'+currDate.getSeconds() : currDate.getSeconds());
	      switch(month)
	      {
	        case 1:
	        case 2:
	        case 3:
	          month += 9;
	          year--;
	          break;
	        default:
	          month -= 3;
	          break;
	      }
	      month = (month < 10) ? ('0' + month) : month;
	      resultDate = year + '-'+month+'-'+date+' ' + hms;
	    return resultDate;
	  }
	  
		function closeset(){
			id_tabs_u.setActiveTab(0);
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
	  
	//初始化加载
    window.onload=function(){ 
		//var addStart = "'"+ get3MonthBefor() +"'";
		//var endStart = "'"+ getNowDate() +"'";
		/* console.log(Ext.getCmp('addStart').getValue());
    	Ext.getCmp('addStart').setValue(get3MonthBefor());
    	Ext.getCmp('endEnd').setValue(getNowDate()); */
    	/* var params = AOS.getValue('f_query'); 
    	params.add_time_start = get3MonthBefor();
    	Ext.getCmp('addStart').setValue(get3MonthBefor());
    	Ext.getCmp('addStart').value = get3MonthBefor();
    	console.log(Ext.getCmp('addStart').value); */
    	
    } 
	
	//条件禁用售后处理按钮
    function fn_aftersaledisabled(view, rowIndex, colIndex, item, record) {
		//订单状态为已取消时，可以删除
		if (record.data.pay_status == '0') {//不能操作
			return true;
		} else if (record.data.shipping_status == '1') {//不能操作
			return true;
		} else if (record.data.order_status == '2' || record.data.order_status == '3' || record.data.order_status == '4') {//不能操作
			return true;
		} else if (record.data.pay_code == 'alipay' || record.data.pay_code == 'wxpay' || record.data.pay_code == 'unionpay') {//不能操作
			return true;
		} else {//否则可以守候处理
			return false;
		}
	}
	
	//订单售后处理
    function afterSale(grid, rowIndex, colIndex){
		var record = AOS.selectone(g_order);
		f_sale.form.setValues(record.data);
		var barter_coupons = record.data.barter_coupons;
		var bCoupons = 0;
		if(barter_coupons == ''){
			bCoupons = 0;
		} else {
			barter_coupons = barter_coupons.split(".");
			bCoupons = barter_coupons[0];
		} 
		Ext.getCmp("bCoupons").setValue(bCoupons);
		w_sale.show();
	}
	
	function f_sale_submit(){
	   AOS.ajax({
              forms: f_sale,
              url: 'zjcOrderService.afterSale',
              ok: function (data) {
              	if(data.appcode == '1'){
              		w_sale.hide();
              		g_order_store.reload();
                    AOS.tip(data.appmsg);
              	}else{
					AOS.err(data.appmsg);
              	}
              }
          });
	}
	  
	</script>
</aos:onready>

<script type="text/javascript">
	function confirm(){
		var record = AOS.selectone(Ext.getCmp('g_order'));
		var remark = Ext.getCmp('remark').value;
		//修改订单状态
	   	 AOS.ajax({
	        params: {
	         order_id: record.data.order_id,
	         action_note:remark,
	         order_status: 1,//订单状态改为已确认
	         shipping_status: 0//发货状态为未发货
	       	},
	        url : 'zjcOrderService.updateOrderStatus',
	        ok : function(data) {
		       	//刷新
		       	Ext.getCmp('order_status').setValue("1");
		       	Ext.getCmp('f_order_detail').doLayout();
		     	Ext.getCmp('g_goods').store.reload();
		       	Ext.getCmp('g_work_record').store.reload();
		     	AOS.reset(Ext.getCmp('g_work'));
		     	Ext.getCmp('g_work').doLayout();
		     	addHtml(1,record.data.shipping_status,record.data.pay_status);
		    	//Ext.getCmp('g_order').store.reload();
		     	//g_order_store.reload();
	        }
	    }); 
	}
	
	function deliver(){
		Ext.getCmp('w_order_detail').hide();
		Ext.getCmp('w_order_delivery').show();
	}
	
	//点击查看订单详情时，判断订单状态
    function addHtml(order_status,shipping_status,pay_status){ 
    	var btnDiv = document.getElementById("btnDiv");
		if(order_status == '0' && pay_status == '1'){//订单状态为待确认时
			btnDiv.innerHTML = ' <input type="button" value="确认" class="cbtn_danger" onclick="confirm();" />';
		} else if(order_status == '1' && shipping_status == '0' && pay_status == '1'){//订单状态为已确认且发货状态为未发货,支付状态为已支付时
			btnDiv.innerHTML = ' <input type="button" value="去发货" class="cbtn_danger" onclick="deliver();" />';
		}
    }
	
    function to_page(goods_id){
    	AOS.ajax({
           	params : {
           		goods_id: goods_id
           	},
            url: 'zjcGoodsService.getGoodsInfo',
            ok: function (data) {
            	Ext.getCmp('brand_id').store.reload();
            	Ext.getCmp('brand_id').setValue(data.brand_id);
            	Ext.getCmp('cat_id1').store.reload();
            	Ext.getCmp('cat_id1').setValue(data.cat_id);
            	Ext.getCmp('cat_id2').store.reload();
            	Ext.getCmp('cat_id2').setValue(data.cat_id2);
   	           	Ext.getCmp('f_goods_info_u').form.setValues(data);
   	         	document.getElementById("original_img").src = data.original_img;
   	        	document.getElementById("showImage").src = data.original_img;
   	        	Ext.getCmp('w_goods_u').show();
               }
           });
	}
    
    function showdiv(){
 	   $("#picture-show").show();
    }
    function closediv(){
 	   $("#picture-show").hide();
    }

</script>
