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
			<%-- <aos:textfield name="v_oid1" fieldLabel="首信易订单id" columnWidth="0.33" /> --%>
			<aos:textfield name="user_id" fieldLabel="会员ID" columnWidth="0.33" />
			<aos:textfield name="mj" fieldLabel="商家ID" columnWidth="0.33" />
			<aos:textfield name="store_name" fieldLabel="店铺名称" columnWidth="0.33" />
			<aos:textfield name="goods_name" fieldLabel="商品名称" columnWidth="0.33" />
			<%-- <aos:textfield name="contacts_phone" fieldLabel="商家电话" columnWidth="0.33" /> --%>
			<%-- <aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.33" /> --%>
			<aos:textfield name="order_sn" fieldLabel="订单编号" columnWidth="0.33"/>
			<aos:textfield name="consignee" fieldLabel="收货人"  columnWidth="0.33" /> 
			<aos:datefield name="add_time_start" fieldLabel="下单时间(起)"  format="Y-m-d 00:00:00" editable="false" value="new Date()" columnWidth="0.33" id="addStart"/>
			<aos:datefield name="add_time_end" fieldLabel="下单时间(止)" format="Y-m-d 23:59:59" editable="false" value="NOM()" columnWidth="0.33" id="addEnd"/>
			<aos:combobox name="order_status"  fieldLabel="订单状态" emptyText="订单状态" dicField="order_status" columnWidth="0.33" />
			<aos:combobox name="pay_status" fieldLabel="支付状态" emptyText="支付状态" dicField="pay_status" columnWidth="0.33" />
			<%-- <aos:combobox name="pay_code" fieldLabel="支付方式"  emptyText="支付方式" dicField="pay_code" columnWidth="0.33" /> --%>
			<aos:combobox name="shipping_status" fieldLabel="发货状态" emptyText="发货状态" dicField="shipping_status" columnWidth="0.33" />
			<aos:textareafield id="v_oid" name="v_oid"  allowBlank="true" fieldLabel="首信易订单id" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query()" icon="query.png" />
				<aos:dockeditem xtype="button" text="导出" onclick="fn_export_excel" icon="icon70.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcOrderService.listZjcSXYOrders&pay_code=paySYX" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="订单列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="订单ID" dataIndex="order_id" hidden="true"/>
			<aos:column header="商品ID" dataIndex="goods_id" hidden="true"/>
			<aos:column header="特殊商品" dataIndex="is_car" hidden="true"/>
			<aos:column header="支付时间" dataIndex="pay_time" hidden="true"/>
			<aos:column header="首信易订单id" dataIndex="v_oid" width="200" />
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
			</aos:column>
		</aos:gridpanel>
	</aos:viewport>
	
	<script type="text/javascript">
	
		function getTextArea(){
			var params = AOS.getValue('f_query');
			var value=params.v_oid;
			console.log(value);
		}
	
		//加载表格数据
		function order_query() {
			var params = AOS.getValue('f_query');
			g_order_store.getProxy().extraParams = params;
			g_order_store.loadPage(1);
		}
		
		//导出订单记录excel
		function fn_export_excel(){
			var params = AOS.getValue('f_query');
			var v_oid= params.v_oid.replace(/[\r\n]/g,",");
			//juid需要再这个页面的初始化方法中赋值,这里才引用得到
			//httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
			AOS.file('do.jhtml?router=zjcOrderService.exportSXYOrderInfo&juid=${juid}&mj='+params.mj +'&store_name='+encodeURI(encodeURI(params.store_name)) +'&goods_name='+encodeURI(encodeURI(params.goods_name)) 
					+ '&order_sn=' + params.order_sn +'&add_time_start=' + params.add_time_start +'&add_time_end=' + params.add_time_end + '&order_status=' + params.order_status
					+ '&pay_code=paySYX&pay_status=' + params.pay_status +'&shipping_status='+params.shipping_status+'&user_id='+params.user_id+'&v_oid='+v_oid);
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
        
       //打开查询订单详情页面
		function getOrderInfo(grid, rowIndex, colIndex){
			var record = grid.getStore().getAt(rowIndex);
			var pay_status= record.data.pay_status;
			if(pay_status==1){
				AOS.tip("该订单为已支付,请确认是否是线下已支付");
				return;
			}
			AOS.ajax({
	          	params : {
	          		v_oid: record.data.v_oid
	          	},
	              url: 'zjcOrderService.payOneQuery',
	              ok: function (data) {
	            	AOS.tip(data.msg);
	  				var params = AOS.getValue('f_query');
	  				g_order_store.getProxy().extraParams = params;
	  				g_order_store.loadPage(1);
	              }
	         });
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
			}else if(pay=="mixed_payment"||pay=="paySYX"){
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
		
		
		  function getInvoiceInfos(){
			  var record = AOS.selectone(g_order);
				g_goods_invoice_store.getProxy().extraParams = {
					order_id : record.data.order_id
				};
				g_goods_invoice_store.loadPage(1);
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
