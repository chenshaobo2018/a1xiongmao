<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
		<aos:formpanel id="f_query" layout="column" header="false" region="center" border="false" center="true">
			<aos:numberfield name="user_id" fieldLabel="会员ID" labelWidth="100" columnWidth="0.30" allowBlank="false" emptyText="请输入正确的会员ID"/>
			<aos:textfield name="type" fieldLabel="类型" labelWidth="100" columnWidth="0.30" allowBlank="false"/>
			<aos:datetimefield name="time" fieldLabel="操作时间" emptyText="如果不填默认为当前时间" labelWidth="100" columnWidth="0.30"/>
			<aos:button text="保存" icon="ok.png" onclick="f_submit" margin="0 0 0 30"/>
			<aos:textareafield name="descs" fieldLabel="日志" height="400" labelWidth="100" columnWidth="1" allowBlank="false"/>
		</aos:formpanel>
	</aos:viewport>
	<script type="text/javascript">
		function f_submit(){
			AOS.ajax({
				forms : f_query,
                url : 'zjcUserLogService.insertLog',
                ok : function(data) {
                    AOS.tip(data.appmsg);
                }
            });
		}
    </script>
</aos:onready>