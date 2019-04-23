<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="商家日志工具" base="http" lib="ext">
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<script type="text/javascript" src="${cxt}/static/js/api.js"></script>
	<aos:body>
	</aos:body>
</aos:html>
<aos:onready>
<aos:viewport layout="border" autoScroll="true">
<aos:formpanel id="f_add" layout="column" labelWidth="150" header="false" region="north" border="false">
	<aos:datetimefield name="exchange_time" fieldLabel="交易时间" columnWidth="0.33" allowBlank="false" editable="false"/>
	<aos:textfield name="order_sn" fieldLabel="订单编号" columnWidth="0.33" allowBlank="false"/>
	<aos:combobox name="type" fieldLabel="交易类型" columnWidth="0.33" allowBlank="false">
		<aos:option value="0" display="商场购物"/>
		<aos:option value="1" display="转账"/>
		<aos:option value="2" display="线下购物"/>
		<aos:option value="3" display="售后处理-支出"/>
		<aos:option value="4" display="售后处理-收入"/>
	</aos:combobox>
	<aos:numberfield name="num" fieldLabel="金额" columnWidth="0.33" allowBlank="false"/>
	<aos:textfield name="seller_id" fieldLabel="商家ID"  columnWidth="0.33" allowBlank="false"/>
	<aos:textfield name="user_id" fieldLabel="用户ID"  columnWidth="0.33" allowBlank="false"/>
	<aos:docked dock="bottom" ui="footer" margin="0 0 8 0">
		<aos:dockeditem xtype="tbfill" />
		<aos:dockeditem xtype="button" text="提交" onclick="f_add_submit" icon="ok.png" />
		<aos:dockeditem xtype="button" text="重置" onclick="AOS.reset(f_add);" icon="refresh.png" />
		<aos:dockeditem xtype="tbfill" />
	</aos:docked>
</aos:formpanel>	
</aos:viewport>
<script type="text/javascript">
function f_add_submit(){
	AOS.ajax({
		forms:f_add,
		url:"zjcSellerInfoService.saveLog",
		ok:function(data){
			AOS.tip(data.appmsg);
			AOS.reset(f_add);
		}
	});
}
</script>
</aos:onready>