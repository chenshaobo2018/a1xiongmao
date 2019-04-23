<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>确认订单</title>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxstore/index.css"/>   
    <script src="../../../static/js/wxstore/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../../static/js/wxstore/mobile.js" type="text/javascript" charset="utf-8"></script> 
    <script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="../../../static/js/sha1.js"></script>
	<script type="text/javascript" src="../../../static/js/wxconfig.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<style>
		 body{background:#f1f1f1;}
		 .ord_one,.ord_two,.ord_tree,.ord_four,.ord_five{background:#fff;margin-bottom:0.2rem;}
		 .oc_img{width:0.35rem;}
		 .ordo_tit{padding:0.15rem 0;border-bottom:1px solid #efefef;}
		 .ordt_side{color:#5b5b5b;font-size:0.4rem;padding-left:3%;padding-top:0.1rem;}
		 .ordt_cont{padding-right:3%;}
		 .ords_side{width:23%;}
		 .ords_side img{width:100%;}
		 .ords_cont{width:75%;}
		 .ordo_cont{padding:0.3rem 0;width:95%;margin:0 auto;}
		 .ords_title{color:#5b5b5b;font-size:0.4rem;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;}
		 .text_clear{color:#ce1d15;font-size:0.4rem;margin-top:0.8rem;}
		 .ordt_tit{color:#5b5b5b;font-size:0.4rem;padding:0.2rem 0 0.2rem 3%;border-bottom:1px solid #efefef;margin-bottom:0.2rem;}
		 .text_area{width:95%;margin:0 2.5%;border:none;color:#333;font-size:0.4rem;font-family:"微软雅黑": }
		 .ordi_side,.cle_texts{color:#5b5b5b;font-size:0.4rem;}
		 .ordi_cont{color:#ce1d15;font-size:0.4rem;}
		 .ord_tree{padding:0.2rem 3%;}
		 .ordi_side{width:70%;}.ordi_cont{width:30%;}
		 .ortrr_side{width:90%;color:#5b5b5b;font-size:0.4rem;padding-top:0.15rem;}
		 .ortrr_cont{width:10%;}
		 .orde_con{padding-top:0.2rem;}
		 .ordft_side{width:34%;color:#5b5b5b;padding-left:3%;}
		 .ordft_cont{width:60%;color:#ce1d15;text-align:right;padding-right:3%;}
		 .ordf_tit,.ordf_cont{font-size:0.4rem;border-bottom:1px solid #efefef;padding-bottom:0.2rem;}
		 .ordf_tit{padding:0.2rem 0;}
		 .xmoney_cont{width:60%;color:#b8b8b8;text-align:right;padding-right:3%;}
		 .ordf_cont{padding-top:0.2rem;}
		 .oc_imgs{width:0.35rem;position:relative;top:0.1rem;}
		 .xmoney_side{width:34%;color:#5b5b5b;padding-left:3%;padding-top:0.1rem;}
		 .ordr_li{width:8%;} 
		.lisf_input input[type="radio"]{display: none;} 
		.lisf_input label{display:block;width:0.6rem; background:url(../../../static/image/wxstore/no.png) no-repeat; height:0.6rem;line-height:0.6rem; background-size:100% 100%; } 
		.lisf_input label.checked{background:url(../../../static/image/wxstore/yes.png) no-repeat; width:0.6rem;background-size:100% 100%;}		
		.ordr_cont{width:84%;padding-right:3%;} 
		.ord_li li{font-size:0.4rem;border-bottom:1px solid #efefef;padding:0.2rem 0;}
		.ordr_li{padding-left:3%;}
		.ordr_li{padding-left:3%;}
		.ore{color:#ce1d15;font-size:0.4rem;}
		.sod_side{color:#5b5b5b;font-size:0.4rem;width:68%;padding-top:0.4rem;padding-left:2%;}
		.sod_side span{color:#ce1d15;font-size:0.4rem;}
		.ord_six{width:100%;position:fixed;bottom:0;background:#fff;}
		.sod_cont{width:30%;}
		.sod_cont button{padding:0.4rem;width:100%;background:#db1200;border:1px solid #db1200;color:#fff;font-size:0.45rem;}
		.order_zhe_make{z-index: 9997;position:fixed;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.4;filter:alpha(opacity=40);display:none}
		.order_zhe { background:#fff;border-radius:10px;z-index:9998;position:fixed;top:30%; width:85%;margin:0 auto;left:7%;display:none; text-align:center;font-size:0.55rem;}
		.ord_text{font-size:0.45rem;margin-top:0.5rem;} 
		.ord_side img{width:0.8rem;position:relative;bottom:1rem;}
		.gw_num{	border-radius:5px; 	width:85%;	margin:0 auto;	padding:0;	margin-left:5%;}
		.gw_num em{	font-style:normal;height:0.8rem;	line-height:0.8rem;	width:25%;	float: left; 	text-align: center;	cursor: pointer;}
		.gw_num .num{	width:40%;	float: left;	text-align: center;	height:0.8rem;line-height:0.8rem;	font-style: normal; font-size: 0.55rem;	color:#333; border: 0;}
		.gw_num em.add{	font-size:0.35rem;	float: right;	color:#9d9d9d;	background:#d00a0a;	color:#fff;	border:1px solid #d00a0a; }
		.gw_num em.jian{font-size:0.35rem;	float: left;color:#333;background:#fff;border:1px solid #ccc;	}
		.dw_side{width:45%;}
		.dw_cont{width:40%;}
		.orde_size{height:5rem;background:#f1f1f1;}
		.list_main{padding-top:0.3rem; height:5rem;overflow-x:scroll;}
		.list_one{background:#fff;width:95%;margin:0 auto;margin-bottom:0.2rem;}
		.list_side{width:20%;padding:2%;}
		.list_cont{width:75%;padding-top:0.25rem;}
		.list_side img{width:100%;}
		.list_cont p{font-size:0.45rem;}
		.mctbtn_show_make {text-align:center;z-index: 9998;	position:fixed;	top:0;left:0;width:100%;height:100%;background:#000;opacity:0.4;filter:alpha(opacity=40);display:none;}
		.mctbtn_show {z-index:9999;position:fixed;top:20%;left:4%;width:85%;background:#fff;	 display:none; padding:15px;}
				 
	</style>	
</head>
<body>  
<div class="order_conainer">
    <div class="ord_one">
	   <div class="ordo_cont clearfix">
	      <div class="ords_side fl">
		    <img src="${goodDetail.original_img}"/>
		    <input type="hidden" id="store_id" value="${goodDetail.store_id}" />
		  </div> 
	      <div class="ords_cont fr">
		    <p class="ords_title">${goodDetail.goods_name}</p>
			<div class="text_clear clearfix">
			<div class="fl dw_side">￥${goodDetail.shop_price}+${goodDetail.drops}滴/${goodDetail.market_price}劵</div>
			<div class="dw_cont fr ">
				<div class="gw_num fl">
					<em class="jian">-</em>
					<input type="text" name="goodsNum" value="1" class="num" />
					<em class="add">+</em> 
					<div class="clear"></div>
				</div>
			</div>
			</div>
		  </div>
	   </div> 
	</div>
	<div class="ord_two">
	  <p class="ordt_tit">备注</p> 
	  <textarea rows="4" id="note" placeholder="请输入备注信息" class="text_area" ></textarea>	
	</div>
	<input type="hidden" name="address_id" />
	<div class="to_address"><a href="javascript:void(0);">新增收货地址</a></div>
	<c:forEach items="${addressList }" var="address">
		<div class="ord_tree">
		   <div class="orde_title clearfix">
		     <div class="ordi_side fl">${address.consignee }</div>
		     <div class="ordi_cont fr">${address.mobile }</div>
		   </div>
		   <div class="orde_con clearfix">
		     <div class="ortrr_side fl">${address.address_info }</div>
		     <div class="ortrr_cont fr"><img src="../../../static/image/wxstore/rig.png" class="oc_img"></div>
		   </div>
		</div>
		 <c:if test="${address.is_default == 1 }">
			  <script type="text/javascript">
			  		$("input[name='address_id']").val(${address.address_id });
			  </script>
		  </c:if>
	</c:forEach>
	
	<div class="ord_four" id="zkq">
	   <div class="ordf_tit clearfix">
	      <div class="ordft_side fl" id="chooseCard">折扣劵</div>
	      <div class="ordft_cont fr"><span class="ord_span"></span>折</div>
	   </div>
	</div>
	<div class="ord_five">
	  <p class="ordt_tit">支付方式</p> 
	  <ul class="ord_li clearfix"> 
	  	<c:if test="${goodDetail.goods_id!=34529}">
		    <c:if test="${goodDetail.shop_price.compareTo(BigDecimal.Zero) > 0}">
		  	<li class="clearfix">
				   <div class="ordr_li fl"> 
						<div class="lisf_input">
							<input type="radio"/>
							<label id="alipay" class="dfokgs checked" for="nba" name="caels" type="checkbox" value="product3"></label>	
						</div> 
				   </div>
				   <div class="ordr_cont fr">
					<p class="cle_texts fl">支付宝支付</p>
					<p class="ore fr">￥<span id="t_alipay">${goodDetail.shop_price}</span></p>
				   
				   </div>  
			  </li>
			  <li class="clearfix">
				   <div class="ordr_li fl"> 
						<div class="lisf_input">
							<input type="radio"/>
							<label id="wxpay" class="dfokgs" for="nba" name="caels" type="checkbox" value="product3"></label>	
						</div> 
				   </div>
				   <div class="ordr_cont fr">
					<p class="cle_texts fl">微信支付</p>
					<p class="ore fr" id="t_wxpay">￥<span id="t_wxpay">${goodDetail.shop_price}</span></p>
				   
				   </div>  
			  </li>
			   <li class="clearfix">
				   <div class="ordr_li fl"> 
						<div class="lisf_input">
							<input type="radio"/>
							<label id="shouxinpay" class="dfokgs" for="nba" name="caels" type="checkbox" value="product3"></label>	
						</div> 
				   </div>
				   <div class="ordr_cont fr">
					<p class="cle_texts fl">首信支付</p>
					<p class="ore fr" id="t_sxpay">￥<span id="t_sxpay">${goodDetail.shop_price}</span></p>
				   
				   </div>  
			  </li>
		  </c:if>
		  <c:if test="${goodDetail.market_price.compareTo(BigDecimal.Zero) > 0}"> 
			  <input type="hidden" id="tagNum" value="1"/>
			  <li class="clearfix">
				   <div class="ordr_li fl"> 
						<div class="lisf_input">
							<input type="radio"/>
							<label id="rate" class="dfokgs checked" for="nba" name="caelsa" type="checkbox" value="product3"></label>	
						</div> 
				   </div>
				   <div class="ordr_cont fr">
					<p class="cle_texts fl">易支付</p>
					<p class="ore fr">${goodDetail.market_price}劵</p>
				   
				   </div>  
			  </li>
		    </c:if>
	    </c:if>
	    
	    <c:if test="${goodDetail.goods_id==34529}">
	    	  <li class="clearfix">
				   <div class="ordr_li fl"> 
						<div class="lisf_input">
							<input type="radio"/>
							<label id="mixed_payment" class="dfokgs" for="nba" name="caels" type="checkbox" value="product3"></label>	
						</div> 
				   </div>
				   <div class="ordr_cont fr">
					<p class="cle_texts fl">混合支付券</p>
					<p class="ore fr"><span id="t_mixed_payment"></span></p>
				   </div>  
			  </li>
			  <li class="clearfix">
				   <div class="ordr_li fl"> 
						<div class="lisf_input">
							<input type="radio"/>
							<label id="mixed_pay_drops" class="dfokgs" for="nba" name="caels" type="checkbox" value="product3"></label>	
						</div> 
				   </div>
				   <div class="ordr_cont fr">
					<p class="cle_texts fl">混合支付滴</p>
					<p class="ore fr"><span id="t_mixed_pay_drops"></span></p>
				   </div>  
			  </li>
	    </c:if>
	    
	  </ul>
	</div>
  <div class="orde_size"></div>
	<div class="ord_six clearfix">
	   <div class="sod_side fl">合计:<span id="t_totalpay">￥<span id="t_totol_cash">${goodDetail.shop_price}</span>+<span id="t_total_quan">${goodDetail.drops}</span>滴/券</span></div>
	   <div class="sod_cont fl">
	     <button onclick="showPsd();">立即购买</button>
	   </div>
	</div>
	<div class="order_zhe">
	<div class="ord_side fr">
	   <img src="../../../static/image/redpacket/order_x.png"/>
	</div>	
	<p class="ord_text">请输入支付密码</p>
	<div class="container classSearch">
    	<div class="classSearch-box bg-fff border-box">
    		<div class="inputBox flex-nowrap" id="classSearch" data-busy="0">
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<div>
    				<div class="text"></div>
    			</div>
    			<input class="i-text" id="classInput" type="password" autocomplete="off" required="required" value="" name="pay_psd" data-role="sixDigitPassword" tabindex="" maxlength="6" minlength="6" aria-required="true">
    		</div>
    	</div>
    	<div class="go_buy"><a href="javascript:void(0);">确定</a></div>
	</div>
	</div>
	<div class="order_zhe_make"></div>
	
	<div class="mctbtn_show"> 
		<div class="list_main" id="ssss">	  
		 	<c:if test="${cardvoucherList==null || fn:length(cardvoucherList) == 0}">  
				<div class="list_one clearfix"> 
				  暂无卡券数据！
				</div> 
			</c:if>  
			<c:forEach items="${cardvoucherList}" var="item" varStatus="status">  
			 	 <div class="list_one clearfix">
			 	 	<div style="display:none">${item.card_id }</div> 
				    <div class="list_side fl"><img src="${item.logo_url }"/></div>
				    <div class="list_cont fl">
					   <p>${item.card_title }</p>
					   <p>${item.discount }</p>
					</div> 
				  </div>
			</c:forEach> 
	  </div>
	</div>
	<div class="mctbtn_show_make"></div>
	<script>
		//定义全局变量
		var encrypt_code='';
		var card_id = '';
		var discount='';
		var needpay = ${goodDetail.shop_price};
		var shop_price = ${goodDetail.shop_price};
		var market_price = ${goodDetail.market_price};
		var drops = ${goodDetail.drops};
		var buyNum = $(".num").val();
		$(function(){  
			$(".ord_span").bind('DOMNodeInserted', function (e) {//动态监听是否使用折扣券
				discount = $(".ord_span").text();
				needpay = needpay*(discount/10);
				shop_price = shop_price*(discount/10);
				$("#t_alipay").text('');
				$("#t_wxpay").text('');
				$("#t_sxpay").text('');
				$("#t_alipay").text(needpay);
				$("#t_wxpay").text(needpay);
				$("#t_sxpay").text(needpay);
				$("#t_totalpay").text("￥"+shop_price+"+"+market_price+"券")
			});
			
			var pay_type = getPayType();
			if(pay_type == 'mixed_pay_drops'){
				var t_totol_cash = shop_price*buyNum;
				$("#t_totol_cash").text(t_totol_cash);
				var t_total_quan = drops*buyNum;
				$("#t_total_quan").text(t_total_quan);
			} else {
				var t_totol_cash = shop_price*buyNum;
				$("#t_totol_cash").text(t_totol_cash);
				var t_total_quan = market_price*buyNum;
				$("#t_total_quan").text(t_total_quan);
			}
			$("#t_mixed_payment").text("￥"+shop_price*buyNum+"+"+market_price*buyNum+"券");
			$("#t_mixed_pay_drops").text("￥"+shop_price*buyNum+"+"+drops*buyNum+"滴");
			function getPayType(){
				var xianjinfunkuan = "";
				var pay = "";
				if($(".dfokgs.checked").length > 1){
					for(var i = 0;i < $(".dfokgs.checked").length; i++){
						if($(".dfokgs.checked")[i].id != "rate"){
							pay = $(".dfokgs.checked")[i].id;
						}
					}
					xianjinfunkuan = pay;
				}else if($(".dfokgs.checked").length == 1){
					xianjinfunkuan = $(".dfokgs.checked")[0].id;
				} else {
					xianjinfunkuan =$(".dfokgs")[0].id;
				}
				return xianjinfunkuan;
			}
			
			   // 购物车加减
			$(".add").click(function(){
				var n=$(this).prev().val();
				var num=parseInt(n)+1;
				if(num==0){ return;}
				$(this).prev().val(num);
				pay_type = getPayType();
				if(pay_type == 'mixed_pay_drops'){
					var t_totol_cash = shop_price*num;
					$("#t_totol_cash").text(t_totol_cash);
					var t_total_quan = drops*num;
					$("#t_total_quan").text(t_total_quan);
					"t_mixed_pay_drops"
				} else {
					var t_totol_cash = shop_price*num;
					$("#t_totol_cash").text(t_totol_cash);
					var t_total_quan = market_price*num;
					$("#t_total_quan").text(t_total_quan);
				}
				$("#t_mixed_payment").text("￥"+shop_price*num+"+"+market_price*num+"券");
				$("#t_mixed_pay_drops").text("￥"+shop_price*num+"+"+drops*num+"滴");
			});
			//减的效果
			$(".jian").click(function(){
				var n=$(this).next().val();
				var num=parseInt(n)-1;
				if(num==0){ return}
				$(this).next().val(num);
				pay_type = getPayType();
				if(pay_type == 'mixed_pay_drops'){
					var t_totol_cash = shop_price*num;
					$("#t_totol_cash").text(t_totol_cash);
					var t_total_quan = drops*num;
					$("#t_total_quan").text(t_total_quan);
				} else {
					var t_totol_cash = shop_price*num;
					$("#t_totol_cash").text(t_totol_cash);
					var t_total_quan = market_price*num;
					$("#t_total_quan").text(t_total_quan);
				}
				$("#t_mixed_payment").text("￥"+shop_price*num+"+"+market_price*num+"券");
				$("#t_mixed_pay_drops").text("￥"+shop_price*num+"+"+drops*num+"滴");
			});
			
			$(".dfokgs").click(function(){
				pay_type = getPayType();
				buyNum = $(".num").val();
				if(pay_type == 'mixed_pay_drops'){
					var t_totol_cash = shop_price*buyNum;
					$("#t_totol_cash").text(t_totol_cash);
					var t_total_quan = drops*buyNum;
					$("#t_total_quan").text(t_total_quan);
				} else {
					var t_totol_cash = shop_price*buyNum;
					$("#t_totol_cash").text(t_totol_cash);
					var t_total_quan = market_price*buyNum;
					$("#t_total_quan").text(t_total_quan);
				}
			})
	
		});  
		
		//微信调用异常打印
		wx.error(function(res){
			console.log("error")
		});
		
		//勾选折扣券额度
		function showValue(v){
			$(".mctbtn_show,.mctbtn_show_make").hide();
			discount = $(v).find(".list_text").text();
			var jsapi_ticket = getJsapiTicket1();
			var noncestr = getNonceStr1();
			var t_timestamp = getTimeStamp1();
			card_id =  $(v).find(".cardid").text();
			var fanyizhiqian = "api_ticket=" + jsapi_ticket  + "&nonce_str=" + noncestr + "&time_stamp=" + t_timestamp ;
			if(encrypt_code==''){
				//拉取卡券列表签名
				$.ajax({ 
					url: "getWxCardParams.jhtml",
					type: 'GET',
					data:{
						'jsapi_ticket':jsapi_ticket,
						'timestamp':t_timestamp,
						'noncestr':noncestr,
						'card_id':card_id
					},success: function(data){
						if(data.appcode==1){
							var fx_singtruestr = data.appmsg;
							//拉取卡券列表
							wx.chooseCard({
								shopId: '', // 门店Id
								cardType: 'DISCOUNT', // 卡券类型
								cardId: card_id, // 卡券Id
								timestamp: t_timestamp, // 卡券签名时间戳
								nonceStr: noncestr, // 卡券签名随机串
								signType: 'SHA1', // 签名方式，默认'SHA1'
								cardSign: fx_singtruestr, // 卡券签名
								success: function (res) {
									var cardList= res.cardList; // 用户选中的卡券列表信息
									cardList = JSON.parse(res.cardList);
									encrypt_code = cardList[0]['encrypt_code'];
									if(encrypt_code !== ''){//有选中的折扣额度，页面赋值折扣
										$(".ord_span").text(discount);//折扣额度
									}
								},fail: function (res) {//调用失败打印
									alert("优惠券使用失败");
					    	    },cancel: function(res){//放弃折扣打印
					    	    	alert("您取消了使用优惠券");
					    	    }
							});
						}
					}
				}); 
			}
		}
		
	//获取随机字符串
	function getNonceStr1() {
	    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	    var maxPos = $chars.length;
	    var noceStr = "";
	    var oldNonceStr = "";
	    for (i = 0; i < 32; i++) {
	    	oldNonceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
	    }
	    noceStr = oldNonceStr;
	    return noceStr;
	}
	
	//获取当前时间戳
	function getTimeStamp1() {
	    var timestamp = new Date().getTime();
	    var timestampstring = timestamp.toString();//一定要转换字符串
	    timestampstring = timestampstring.substring(0, timestampstring.length - 3)
	    return timestampstring;
	}
	//获取wx_card_ticket
	function getJsapiTicket1(){
		var jsapi_ticket = "";
		$.ajax({ 
			url: "getWxCardTicket.jhtml",
			type: 'POST',
			data:'',
			async : false,
			success: function(data){
				if(data.code == 1){
					jsapi_ticket = data.data;
				}else{
					alert(data.msg)
				}
			}
		}); 
		return jsapi_ticket;
	}
	
	$("#zkq").click(function(){//本地展示该商品可用的折扣券
		if(shop_price == 0){//纯券支付，不能使用折扣券
			return false;
		}
	    var store_id = ${goodDetail.store_id};
		$.ajax({
    	    type: "POST",
    	    url: "initCardVouchers.jhtml",
    	    data: {
    	    	"store_id":store_id
    	    },// 要提交的表单
    	    success: function (data) {
    	    	$(".mctbtn_show,.mctbtn_show_make").show();
    	    	$(".list_main").empty();
    	    	var htmlstr ="";
    	    	if(data==null || JSON.stringify(data) =='[]'){
    	    		htmlstr="暂无可用卡券！";
    	    		$(".list_main").append(htmlstr);
				} else {
	    	    	$.each(data, function(index, o){
	    	    		htmlstr = "<div class='list_one clearfix' onclick='showValue(this)'><div style='display:none' class='cardid'>"
	    	    				+ o.card_id + "</div> <div class='list_side fl'><img src='"
	    	    				+ o.logo_url + "'/></div> <div class='list_cont fl'><p>"
	    	    				+ o.card_title +"</p><span class='list_text'>" + o.discount/10 + "</span>折</div></div>";
	    	    		$(".list_main").append(htmlstr);
	    	    	});
				}
    	    	
    	    },
    	    error: function (error) {
    	    	console.log(1111111111111)
    	    }
    	  });
		//location.href="initCardVouchers.jhtml?store_id="+$("#store_id").val();
	})
	
	$(".mctbtn_show_make").click(function(){
	   $(".mctbtn_show,.mctbtn_show_make").hide();
	})
	
	$(".lisf_input label[name='caels']").click(function(){ 
		$(".lisf_input label[name='caels']").removeClass("checked"); 
		$(this).addClass("checked"); 
	}); 
	$(".lisf_input label[name='caelsa']").click(function(){ 
		 var tagNum = $("#tagNum").val();;
		if(tagNum == 1 ){
			$(".lisf_input label[name='caelsa']").removeClass("checked"); 
			$("#tagNum").val(2);
		}else if(tagNum == 2){
			$(this).addClass("checked"); 
			$("#tagNum").val(1);
		}
	}); 
	//显示输入密码框
	function showPsd(){
		$(".order_zhe,.order_zhe_make").show();
	}
	//获取现金付款方式
	function xianjinfukuanfangshi(){
		var xianjinfunkuan = "";
		var pay = "";
		if($(".dfokgs.checked").length > 1){
			for(var i = 0;i < $(".dfokgs.checked").length; i++){
				if($(".dfokgs.checked")[i].id != "rate"){
					pay = $(".dfokgs.checked")[i].id;
				}
			}
			xianjinfunkuan = pay;
		}else if($(".dfokgs.checked").length == 1){
			xianjinfunkuan = $(".dfokgs.checked")[0].id;
		} else {
			xianjinfunkuan =$(".dfokgs")[0].id;
		}
		return xianjinfunkuan;
	}
	//跳转到购买页面
	$(".go_buy").click(function(){
		var pay_type = "";
		var price = "";
		var xianjinfunkuan = xianjinfukuanfangshi();
		/* if($(".dfokgs.checked").length > 1){
			pay_type = "mixed_payment";
			price = "${goodDetail.shop_price}";
		}else{
			pay_type = $(".dfokgs.checked")[0].id;
			if(pay_type == "rate"){
				price = "${goodDetail.market_price}";
			}else{
				pay_type = "alipay";
				price = "${goodDetail.shop_price}";
			}
		} */
		pay_type = xianjinfunkuan
		var address_id = $("input[name='address_id']").val();
		var goodsNum = $("input[name='goodsNum']").val();
		var Goods = '[{"goods_id":"${goodDetail.goods_id}","spec_key_name":null,"spec_key":null,"goods_num":'+ goodsNum +'}]'; 
		var user_note = $("#note").val();
		var total_amount = price * goodsNum;
		var pay_psd = $("input[name='pay_psd']").val();
		discount = $(".ord_span").text();
		if(address_id != ""){
			if(pay_psd != ""){
				$.ajax({ 
					url: "orderSub.jhtml",
					type: 'POST',
					data:{
						store_id : '${goodDetail.store_id}',
						pay_type : pay_type,
						goods_id : '${goodDetail.goods_id}',
						address_id : address_id,
						total_amount : total_amount,
						user_note : user_note,
						Goods : Goods,
						pay_password : pay_psd,
						discount : discount/10
					},
					success: function(data){
						if(data.code == 1){
							alert("提交成功！");
							//折扣券使用成功，核销卡券
							if(discount.length != 0 || discount != null){
								$.ajax({ 
									url: "cancelWxCard.jhtml",
									type: 'POST',
									data:{
										'encrypt_code':encrypt_code,
										'card_id':card_id
									},success: function(data){
									}
								}); 
							}
							if(data.data.pay_type == "rate"){
								window.location.href="wxSendGoods.jhtml"; 
							}
							if(data.data.pay_type == 'alipay'){
								//支付宝
								window.location.href="initAlipay.jhtml?order_sn=" + data.data.order_sn; 
							} 
							if(data.data.pay_type == 'wxpay'){
								//微信
								window.location.href="initweixinPay.jhtml?order_sn=" + data.data.order_sn; 
							}
							if(data.data.pay_type == 'shouxinpay'){
							//首信
							window.location.href="../../../notokenapi/app/v1/payBankSubmit.jhtml?order_id=" + data.data.order_id;
							}
							
							if(data.data.pay_type == 'mixed_payment'||data.data.pay_type == 'mixed_pay_drops'){
								window.location.href="toWinePaySuccess.jhtml?order_sn=" + data.data.order_sn +"&buyNum="+data.data.buyNum;
							}
						}else{
							alert(data.msg)
						}
					}
				}); 
			}else{
				alert("请输入支付密码!");
			}
		}else{
			alert("请选择收货地址!");
		}
	})
	$(".to_address").click(function(){
		location.href="initPayPwd.jhtml";
	})
	$(".ord_side").click(function(){
		$(".order_zhe,.order_zhe_make").hide();
	})
	</script>	
	<script src="../../../static/js/wxstore/password.js"></script> 
</div>
</body>
</html>
