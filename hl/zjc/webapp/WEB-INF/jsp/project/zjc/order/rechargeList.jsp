<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="积分充值记录列表" base="http" lib="ext">
	<aos:body>
		
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="border">
		<aos:formpanel id="f_query" layout="column" labelWidth="150" header="false" region="north" border="false">
			<aos:docked forceBoder="0 0 1 0">
				<aos:dockeditem xtype="tbtext" text="查询条件" />
			</aos:docked>
			<aos:textfield name="order_sn" fieldLabel="充值单号"  columnWidth="0.33" />
			<aos:textfield name="nickname" fieldLabel="会员昵称" columnWidth="0.33" />
			<aos:textfield name="mobile" fieldLabel="联系电话" columnWidth="0.33" />
			<aos:textfield name="user_id" fieldLabel="会员编号" columnWidth="0.33" />
			<aos:datefield name="ctime_start" fieldLabel="充值日期（起）"  format="Y-m-d 00:00:00" editable="false" columnWidth="0.33" />
			<aos:datefield name="ctime_end" fieldLabel="充值日期（止）" format="Y-m-d 23:59:59" editable="false" maxValue="" disabledDaysText="测试" minValue="" columnWidth="0.33" />
			<aos:combobox name="pay_status" fieldLabel="充值状态" emptyText="充值状态" dicField="recharge_status" columnWidth="0.33" />
			<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem xtype="button" text="筛选" onclick="order_query" icon="query.png" />
				<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_query);" icon="refresh.png" />
				<aos:dockeditem xtype="tbfill" />
			</aos:docked>
		</aos:formpanel>

		<aos:gridpanel id="g_order"  url="zjcRechargeService.listZjcRecharges" onrender="order_query" region="center">
			<aos:docked forceBoder="1 0 1 0">
				<aos:dockeditem xtype="tbtext" text="订单列表" />
			</aos:docked>
			<aos:column type="rowno" />
			<aos:column header="订单ID" dataIndex="order_id" hidden="true" />
			<aos:column header="充值单号" dataIndex="order_sn"/>
			<aos:column header="会员昵称" dataIndex="nickname" />
			<aos:column header="会员编号" dataIndex="user_id"/>
			<aos:column header="联系电话" dataIndex="mobile"/>
			<aos:column header="充值金额（元）" dataIndex="account"/>
			<aos:column header="购买积分" dataIndex="buy_points"/>
			<aos:column header="充值时间" dataIndex="ctime" />
			<aos:column header="充值状态" dataIndex="pay_status" rendererField="recharge_status" />
			<aos:column header="支付方式" dataIndex="pay_code" rendererField="pay_code"/>
			<aos:column header="支付时间" dataIndex="pay_time" rendererFn="format_payTime"/>
		</aos:gridpanel>
		
	</aos:viewport>
	
	<script type="text/javascript">
		//加载表格数据
		function order_query() {
			var params = AOS.getValue('f_query');
			g_order_store.getProxy().extraParams = params;
			g_order_store.loadPage(1);
		}
		
		//扩展Date的format方法   
	    Date.prototype.format = function (format) {  
		    var o = {  
		        "M+": this.getMonth() + 1,  
		        "d+": this.getDate(),  
		        "h+": this.getHours(),  
		        "m+": this.getMinutes(),  
		        "s+": this.getSeconds(),  
		        "q+": Math.floor((this.getMonth() + 3) / 3),  
		        "S": this.getMilliseconds()  
		    }  
		    if (/(y+)/.test(format)) {  
		        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
		    }  
		    for (var k in o) {  
		        if (new RegExp("(" + k + ")").test(format)) {  
		            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
		        }  
		    }  
		    return format;  
		}  
		
	    /**   
	     *转换日期对象为日期字符串   
	     * @param l long值   
	     * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
	     * @return 符合要求的日期字符串   
	     */    
	     function getFormatDate(date, pattern) {  
	         if (date == undefined) {  
	             date = new Date();  
	         }  
	         if (pattern == undefined) {  
	             pattern = "yyyy-MM-dd hh:mm:ss";  
	         }  
	         return date.format(pattern);  
	     }
		
	    /**   
	     *转换long值为日期字符串   
	     * @param l long值   
	     * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
	     * @return 符合要求的日期字符串   
	     */    
	   
	     function getFormatDateByLong(l, pattern) { 
	         return getFormatDate(new Date(l), pattern);  
	     }  
		//格式化发货时间
		function format_payTime(value, metaData, record, rowIndex, colIndex,store){
			var payTimeStr = record.data.pay_time;
			if(payTimeStr == ''){
				return '未支付';
			} else {
				return '<div>'+ payTimeStr +'</div>'
			}
		}
		
	</script>
</aos:onready>