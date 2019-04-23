<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>订单信息-地址</title>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/common.css"/>
    <link rel="stylesheet" type="text/css" href="../../../static/css/wxactivity/share_power.css"/> 
    <script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script> 
    <script src="../../../static/js/wxactivity/share_power.js" type="text/javascript" charset="utf-8"></script> 
</head>
<body>  
   <div class="ord_add_container">
   <!-- 商品 -->
   <div class="ordadd_list">
      <ul>
	     <li> 
		     <div class="od_side fl">
				<img src="${zjcGoodsPO.original_img}"/>
			 </div>
		     <div class="od_cont fl">
			 <p class="ord_title otd_text">${zjcGoodsPO.goods_name}</p>
			 <p class="ord_cn_text colorde1a00">¥0<span><fmt:formatNumber type="number" value="${zjcGoodsPO.market_price }" maxFractionDigits="0" />券</span></p>
			 </div>
		     <div class="clear"></div>
		 </li> 
	  </ul>
   </div>
   <!-- 地址 -->
   <c:forEach items="${addressList }" var="address">
   	<div class="ordadd_address">
     <div class="ordeaddd_one clearfix">
	    <div class="oe_side fl">收货人:${address.consignee}</div>
	    <div class="oe_cont fr">${address.mobile}</div>
	 </div>
     <div class="ordeaddd_two clearfix">
	    <div class="tw_side fl colorde1a00">收货地址:<span>${address.address_info}</span></div>
	    <div class="tw_cont fr">
			<img src="../../../static/image/wxactivity/ok_red.png" class="tw_img"/>
		</div>
	 </div>   
    </div>
   </c:forEach>
   <div class="new_addrs"><a href="initSettingAddress.jhtml?goods_id=${zjcGoodsPO.goods_id}" class="colorde1a00">新增收货地址+</a></div>
   <div class="newadd_btn">
	   <c:if test="${is_typeSum == true }">
	     <button class="nesbtn_go">立即兑换</button><br/>
	   </c:if>
	   <c:if test="${is_typeSum == false }">
	     <button class="nesbtn_no">已兑换</button><br/>
	   </c:if>
   </div>  
   <!-- 遮罩 -->
   <div class="go_th">
     恭喜你兑换成功!
   </div>
   <div class="go_thmake"></div>
   </div>
   <script> 
   $(".nesbtn_go").click(function(){
	  var Goods = '[{"goods_id":"${zjcGoodsPO.goods_id}","spec_key_name":null,"spec_key":null,"goods_num":'+ 1 +'}]';
	   $.ajax({
			url : "orderSub.jhtml",
			type : 'POST',
			data : {
				goods_id : '${zjcGoodsPO.goods_id}',
				user_id : '${user_id}',
				address_id : '${address_id}',
				total_amount : '${total_amount}',
				store_id : '${store_id}',
				Goods : Goods,
				pay_type : 'rate',
				user_note : ''
			},
			success : function(data) {
				if (data.code == 1) {
					$(".go_th,.go_thmake").show();
				} else {
					alert(data.msg);
				}
			}
				
		})
	   
	});
   //下单成功点击恭喜刷新界面
   $(".go_th").click(function(){
	   $(".go_th,.go_thmake").hide();
	   window.location.reload();
   })
   </script>
</body>
</html>