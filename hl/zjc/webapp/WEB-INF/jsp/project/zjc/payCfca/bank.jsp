<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
		 <meta name="format-detection" content="telephone=yes" />
        <script src="../../../static/paycfca/js/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		 <title>active</title>   
		<style>
		*,div,p,ul,li,table,tr,td,a,span,img,label,input,button{margin:0;padding:0;}a{text-decoration:none;}html,body{height:100%;width:100%;margin:0;padding:0; font-family:'Microsoft YaHei','微软雅黑';font-size:62.5%;}  
		.right{text-align:right;}.left{text-align:left;}.fl{float:left;}.fr{float:right;}.clear{clear:both;}.clearfix:before,.clearfix:after {display: table;content:" ";}.clearfix:after {clear: both;}.clearfix{*zoom: 1;}.center{text-align:center;} 
		body{background:#f7f7f7;height:100%;}
		.banks_side{font-size:0.4rem;color:#e3372a;padding-left:3%;}
		.banks_one{background:#fff;padding:0.3rem 0;}
			.banks_img{width:0.5rem;position:relative;top:0.1rem;}
			.banks_img{width:0.5rem;}
			.banks_imgs{width:0.35rem;}
			.banks_cont{padding-right:4%;}
			.banks_two{width:95%;margin:0 auto;}
			.bankst_one{background:#fff;border-radius:0.2rem;margin-top:0.2rem; padding-top:0.3rem;}
			.banck_one{padding-left:5%;}
			.banck_one,.banck_two{color:#333;font-size:0.45rem;}
			.bank_bgs{position:relative;height:1rem;}
			.bank_bg{position:absolute;width:20%;right:0;margin-right:0;background:url(../../../static/paycfca/img/bank_bg.png) no-repeat top left;
			background-size:100% 100%;bottom:0; font-size:0.35rem; padding:1rem 0.5rem 0.3rem 0.5rem;color:#fff;}
		</style>
		<script type="text/x-handlebars-template" id="datalist">
	</script>
	</head>
<body>   
<div class="bank_href">
   <div class="banks_one clearfix" onclick="tiaozhuan()">
      <div class="banks_side fl"><img src="../../../static/paycfca/img/banks_add.png" class="banks_img"/> 添加银行卡</div>
      <div class="banks_cont fr"><img src="../../../static/paycfca/img/bank_jt.png" class="banks_imgs"/></div>
   </div>
   <c:forEach items="${ZjcZjBankcardPO}" var ="c"> 
    <div class="banks_two" onclick="zhifu()">
     <div class="bankst_one">
	   <p class="banck_one">${c.banktext}</p>
	   <p class="banck_two center">${c.accountnumber}</p>
	    <div class="bank_bgs"><div class="bank_bg right">
	      ${c.cardtype}
	   </div> 
	   <div Style="display:none;" id="user_id">
	      ${c.user_id}
	   </div>
	   <div Style="display:none;" id="txsnbinding">
	      ${c.txsnbinding}
	   </div>
	   </div> 
	 </div>
   </div>   
</c:forEach>   
    <div Style="display:none;" id="order_id">
	      ${order_id}
	</div>
</div>
	<script>
	$(function(){  
	 $(".banck_two").text( "**** **** **** " + $(".banck_two").text().substring(12, 16));   
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth > 1080) deviceWidth = 1080; 
		document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';
	});
	
	function tiaozhuan(){
		var user_id=$("#user_id").html();
		window.location.href="../../app/v1/payBankBind.jhtml?user_id="+user_id;
	 }
	
	function zhifu(){
		var order_id=$("#order_id").html();
		var txsnbinding=$("#txsnbinding").html();
		window.location.href="../../app/v1/Pay1375.jhtml?order_id="+order_id+"&TxSNBinding="+txsnbinding;
	}
	</script>
</body>
</html>