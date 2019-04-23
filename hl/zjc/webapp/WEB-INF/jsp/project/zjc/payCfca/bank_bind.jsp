<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.api.cfca.controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=yes" />
		 <script src="../../../static/paycfca/js/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="../../../static/paycfca/css/bank_index.css"/> 
		 <title>绑定银行卡</title>  
	</head>
<body> 
<div class="bank_bind"> 
  <div class="bb_one"> 
  建立绑定关系
  </div>
  <div class="bb_two">
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>绑定流水号</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="TxSNBinding" name="TxSNBinding" value="${TxSNBinding}"/>
	      <input type="hidden"   id="user_id" value="${user_id}" />
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>银行ID</p>
	   </div>
	   <div class="bbt_one_cont fr">
	   <select  id="BankID" name="BankID" class="sel_bank">
            <option value="100">邮储银行</option>
            <option value="102">工商银行</option>
            <option value="103">农业银行</option>
            <option value="104">中国银行</option>
            <option value="105">建设银行</option>
            <option value="301">交通银行</option>
            <option value="302">中信银行</option>
            <option value="303">光大银行</option>
            <option value="304">华夏银行</option>
            <option value="305">民生银行</option>
            <option value="306">广发银行</option>
            <option value="307">平安银行</option>
            <option value="308">招商银行</option>
            <option value="309">兴业银行</option>
            <option value="310">浦发银行</option>
            <option value="313">华融湘江银行</option>
            <option value="316">浙商银行</option>
            <option value="317">渤海银行</option>
            <option value="321">重庆三峡银行</option>
            <option value="401">上海银行</option>
            <option value="402">厦门银行</option>
            <option value="403">北京银行</option>
            <option value="405">福建海峡银行</option>
            <option value="408">宁波银行</option>
            <option value="409">齐鲁银行</option>
            <option value="412">温州银行</option>
            <option value="413">广州银行</option>
            <option value="417">盛京银行</option>
            <option value="419">辽阳银行</option>
            <option value="420">大连银行</option>
            <option value="421">苏州银行</option>
            <option value="422">河北银行</option>
            <option value="423">杭州银行</option>
            <option value="424">南京银行</option>
            <option value="426">金华银行</option>
            <option value="427">新疆乌鲁木齐市商业银行</option>
            <option value="428">绍兴银行</option>
            <option value="430">抚顺银行</option>
            <option value="431">临商银行</option>
            <option value="432">湖北银行</option>
            <option value="433">葫芦岛银行</option>
            <option value="434">天津银行</option>
            <option value="435">郑州银行</option>
            <option value="436">宁夏银行</option>
            <option value="438">齐商银行</option>
            <option value="439">锦州银行</option>
            <option value="440">徽商银行</option>
            <option value="442">哈尔滨银行</option>
            <option value="443">贵阳银行</option>
            <option value="447">兰州银行</option>
            <option value="448">南昌银行</option>
            <option value="451">吉林银行</option>
            <option value="454">九江银行</option>
            <option value="457">秦皇岛市商业银行</option>
            <option value="458">青海银行</option>
            <option value="459">台州市商业银行</option>
            <option value="461">长沙银行</option>
            <option value="462">潍坊银行</option>
            <option value="463">赣州银行</option>
            <option value="464">泉州银行</option>
            <option value="450">青岛银行</option>
            <option value="465">营口银行</option>
            <option value="466">富滇银行</option>
            <option value="470">嘉兴银行</option>
            <option value="472">廊坊银行</option>
            <option value="473">浙江泰隆商业银行</option>
            <option value="474">内蒙古银行</option>
            <option value="478">广西北部湾银行</option>
            <option value="479">包商银行</option>
            <option value="481">威海市商业银行</option>
            <option value="483">攀枝花商行</option>
            <option value="486">泸州市商业银行</option>
            <option value="488">三门峡市商业银行</option>
            <option value="490">张家口市商业银行</option>
            <option value="491">桂林银行</option>
            <option value="495">柳州银行</option>
            <option value="496">南充市商业银行</option>
            <option value="497">莱商银行</option>
            <option value="498">德阳银行</option>
            <option value="500">六盘水市商业银行</option>
            <option value="502">曲靖市商业银行</option>
            <option value="701">昆仑银行</option>
            <option value="1401">上海农商行</option>
            <option value="1402">昆山农村商业银行</option>
            <option value="1403">常熟农商银行</option>
            <option value="1404">深圳农村商业银行</option>
            <option value="1405">广州农村商业银行</option>
            <option value="1408">顺德农商银行</option>
            <option value="1410">湖北农信</option>
            <option value="1412">江阴农村商业银行</option>
            <option value="1413">重庆农村商业银行</option>
            <option value="1414">山东农信</option>
            <option value="1415">东莞农村商业银行</option>
            <option value="1416">张家港农村商业银行</option>
            <option value="1418">北京农村商业银行</option>
            <option value="1420">宁波鄞州农村合作银行</option>
            <option value="1424">江苏省农村信用社联合社</option>
            <option value="1428">吴江农村商业银行</option>
            <option value="1433">太仓农村商业银行</option>
            <option value="1434">临汾市尧都市农村信用联社</option>
            <option value="1437">无锡农商行</option>
            <option value="1438">湖南农村信用社</option>
            <option value="1439">江西农信</option>
            <option value="1442">陕西省农村信用社联合社</option>
            <option value="1501">江苏银行</option>
            <option value="1502">邯郸市商业银行</option>
            <option value="1503">邢台银行</option>
            <option value="1504">承德银行</option>
            <option value="1505">沧州银行</option>
            <option value="1506">晋城市商业银行</option>
            <option value="1507">鄂尔多斯银行</option>
            <option value="1508">上饶银行</option>
            <option value="1509">东营市商业银行</option>
            <option value="1511">泰安市商业银行</option>
            <option value="1514">漯河商行</option>
            <option value="1515">商丘市商业银行</option>
            <option value="1517">浙江民泰商业银行</option>
            <option value="1518">龙江银行</option>
            <option value="1519">浙江稠州商业银行</option>
            <option value="1520">安徽省农村信用社联合社</option>
            <option value="1521">广西壮族农村信用社联合社</option>
            <option value="1522">海南省农村信用社</option>
            <option value="1523">云南省农村信用联社</option>
            <option value="1524">宁夏黄河农村商业银行</option>
            <option value="1526">安阳市商业银行</option>
            <option value="1527">保定市商业银行</option>
            <option value="1528">成都农商银行</option>
            <option value="1529">广东省农村信用社</option>
            <option value="1530">河北省农村信用联合社</option>
            <option value="1531">鹤壁银行</option>
            <option value="1532">衡水市商业银行</option>
            <option value="1534">晋中市商业商行</option>
            <option value="1535">库尔勒市商业银行</option>
            <option value="1536">乐山市商业银行</option>
            <option value="1537">凉山州商业银行</option>
            <option value="1538">内蒙古农信</option>
            <option value="1539">山西省农村信用社联合社</option>
            <option value="1540">深圳福田银座村镇银行</option>
            <option value="1541">遂宁市商业银行</option>
            <option value="1542">唐山市商业银行</option>
            <option value="1543">天津滨海银行</option>
            <option value="1544">乌海银行</option>
            <option value="1546">许昌银行</option>
            <option value="1547">雅安市商业银行</option>
            <option value="1548">阳泉市商业银行</option>
            <option value="1549">宜宾市商业银行</option>
            <option value="1550">玉溪市商业银行</option>
            <option value="1551">周口市商业银行</option>
            <option value="1552">自贡市商业银行</option>
            <option value="1553">遵义市商业银行</option>
            <option value="1555">长安银行</option>
            <option value="1562">吉林省农村信用合作社</option>
            <option value="1565">颖淮农村商业银行</option>
            <option value="1569">贵州银行</option>
            <option value="1570">长治商行</option>
            <option value="1571">朝阳银行</option>
            <option value="1572">江苏江南农村商业银行</option>
            <option value="1573">宁波东海银行</option>
            <option value="1574">平顶山银行</option>
            <option value="1575">青海省农村信用社联合社</option>
            <option value="1576">四川省农村信用合作社</option>
            <option value="1577">铁岭商业银行</option>
            <option value="1578">武汉农村商业银行</option>
            <option value="1579">信阳银行</option>
            <option value="1629">驻马店银行</option>
            <option value="1734">石家庄汇融农村合作银行</option>
            <option value="3001">东亚银行</option>
            <option value="3007">星展银行</option>
            <option value="3010">花旗银行</option>
            <option value="3034">渣打银行（中国）</option>
            <option value="3036">法国兴业银行（中国）</option>
            </select>
	 </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>账户名称</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="AccountName" name="AccountName" value=""/>
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>账户号码</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="AccountNumber" name="AccountNumber" value=""/>
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>开户证件类型</p>
	   </div>
	   <div class="bbt_one_cont fr">
	   <select id="IdentificationType" id="IdentificationType" name="IdentificationType" class="sel_bank">
              <option value="0">身份证</option>
              <option value="1">户口薄</option>
              <option value="2">护照</option>
              <option value="3">军官证</option>
              <option value="4">士兵证</option>
              <option value="5">港澳居民来往内地通行证</option>
              <option value="6">台湾同胞来往内地通行证</option>
              <option value="7">临时身份证</option>
              <option value="8">外国人居留证</option>
              <option value="9">警官证</option>
              <option value="X">其他证件</option>
            </select>
	  </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>证件号码	</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="IdentificationNumber" name="IdentificationNumber" />
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>手机号</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="PhoneNumber" name="PhoneNumber" value=""/>
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>卡类型</p>
	   </div>
	   <div class="bbt_one_cont fr"> 
	   <select id="CardType" name="CardType" id="CardType" class="sel_bank">
              <option value="10">个人借记</option>
              <option value="20">个人贷记</option>
            </select>
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text">信用卡有效期，格式YYMM</p>
	   </div>
	   <div class="bbt_one_cont fr"> 
			<input type="text" class="bbt_input_end" id="ValidDate" id="ValidDate" name="ValidDate" value=""/>
	   </div>
	</div>
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text">信用卡CVN号，信用卡背面的末3位数字</p>
	   </div>
	   <div class="bbt_one_cont fr"> 
			<input type="text" class="bbt_input_ends" id="CVN2" name="CVN2" value=""/>
	   </div>
	</div> 
	<button class="btn_next">下一步</button>
	</div>
	<!-- 下一步 -->
	<div class="bb_tree">	   
   <!--  <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>机构号码</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="InstitutionIDs"  name="InstitutionID" value=""/>
	   </div>
	</div> -->
    <!-- <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>绑定流水号</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="TxSNBindings" name="TxSNBinding" value=""/>
	   </div>
	</div> -->
    <div class="bbt_one clearfix">
	   <div class="bbt_one_side fl">
	     <p class="bbt_text"><span>*</span>短信验证码</p>
	   </div>
	   <div class="bbt_one_cont fr">
	     <input type="text" class="bbt_input" id="SMSValidationCode" name="SMSValidationCode" value="123456"/>
	   </div>
	</div> 
	<button class="btn_ok" onclick="tijiao()">提交</button>
	</div> 
	<!-- 验证提示 -->
	<div class="error">
	      请输入手机号！
	</div>
  </div> 
</div>
	<script>
	$(function(){	
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px'; 
		$(".btn_next").click(function(){
			var InstitutionID=$("#InstitutionID").val();
			var TxSNBinding=$("#TxSNBinding").val();
			var BankID=$("#BankID").val();
			var AccountName=$("#AccountName").val();
			var AccountNumber=$("#AccountNumber").val();
			var IdentificationType=$("#IdentificationType").val();
			var IdentificationNumber=$("#IdentificationNumber").val();
			var PhoneNumber=$("#PhoneNumber").val();
			var CardType=$("#CardType").val();
			var ValidDate=$("#ValidDate").val();
			var CVN2=$("#CVN2").val();
			$.ajax({
				url:"../../app/v1/Pay2531.jhtml",
				data:{
					"InstitutionID":InstitutionID,
					"TxSNBinding":TxSNBinding,
					"BankID":BankID,
					"AccountName":AccountName,
					"AccountNumber":AccountNumber,
					"IdentificationType":IdentificationType,
					"IdentificationNumber":IdentificationNumber,
					"PhoneNumber":PhoneNumber,
					"CardType":CardType,
					"ValidDate":ValidDate,
					"CVN2":CVN2
				},
				success:function(data){
					console.log("数据:"+data.msg);
					console.log("数据:"+data.data);
					console.log("数据:"+data.code);
					if(2000==data.data){
						 $(".bb_two").hide();
						 $(".bb_tree").show();
					}else{
						alert("绑定失败,请检查你的银行卡信息");
					}
				}
			});
		})
	});
	
	function tijiao(){
		var SMSValidationCode=$("#SMSValidationCode").val();
		var InstitutionID=$("#InstitutionID").val();
		var TxSNBinding=$("#TxSNBinding").val();
		var BankID=$("#BankID").val();
		var user_id=$("#user_id").val();
		var Banktext=$("#BankID option:selected").text();
		var AccountName=$("#AccountName").val();
		var AccountNumber=$("#AccountNumber").val();
		var IdentificationType=$("#IdentificationType").val();
		var IdentificationNumber=$("#IdentificationNumber").val();
		var PhoneNumber=$("#PhoneNumber").val();
		var CardType=$("#CardType").val();
		var ValidDate=$("#ValidDate").val();
		var CVN2=$("#CVN2").val();
		$.ajax({
			url:"../../app/v1/Pay2532.jhtml",
			data:{
				"InstitutionID":InstitutionID,
				"TxSNBinding":TxSNBinding,
				"BankID":BankID,
				"AccountName":AccountName,
				"AccountNumber":AccountNumber,
				"IdentificationType":IdentificationType,
				"IdentificationNumber":IdentificationNumber,
				"PhoneNumber":PhoneNumber,
				"CardType":CardType,
				"InstitutionID":InstitutionID,
				"TxSNBinding":TxSNBinding,
				"Banktext":Banktext,
				"user_id":user_id,
				"SMSValidationCode":SMSValidationCode,
				"ValidDate":ValidDate,
				"CVN2":CVN2
			},
			success:function(data){
				if(2000==data.data){
					window.location.href="../../app/v1/payBankPay.jhtml?TxSNBinding="+TxSNBinding;
				}else{
					alert("绑定失败,请检查你的银行卡信息");
				}
			}
		});
	}
	</script>
</body>
</html>