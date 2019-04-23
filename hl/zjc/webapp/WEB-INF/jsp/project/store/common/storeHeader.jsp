<%@ page contentType="text/html; charset=utf-8"%>
    
   <div class="mct_header">
       <div class="mcthea_cen">
       <div id="fade_msg" style="text-align:center;vertical-align:middle;"></div>
	     <div class="mcthea_cen_side fl">
		   <div class="msct_text fl">中军创云易</div>
		   <div class="msct_span fl">商家中心 </div>
		   <div class="clear"></div>		   
		 </div> 
		   <div class="mct_ulli fl">
		     <ul class="nav_li">
			   <li><a href="homepage.jhtml">首页</a></li>
			   <li><a href="toProductPage.jhtml">商品</a></li>
			   <li><a href="toOrderPage.jhtml">订单</a></li>
			   <li><a href="toStoreDetailPage.jhtml">店铺</a></li>
			   <li><a href="toCardVoucherList.jhtml">卡券管理</a></li>
				<div class="clear"></div>
			 </ul>
		   </div>
		   <div class="mct_per fr">
		     <ul class="mctper_ul">
			   <li class="mv_one"><img src="${sessionScope.store_logo }" onerror="this.src='${cxt }/static/image/mct/user_fie.png'" alt="head" title="head" class="fil_head" width="46" height="46"/></li>
			   <li>${zjcStorePO.store_name}(${zjcStorePO.user_id})</li>
			   <li><a href="javaScript:void(0)" onclick="quit()">安全退出</a></li>
			  <div class="clear"></div>
			 </ul>
		   </div>
		   </div> 
	     <div class="clear"></div>
	   </div>  
	  <!--  <button class="asa">ok</button> -->
		<div class="store_ok"></div>		
	   <script type="text/javascript">
	   	function alert_msg(data){
	   		$(".store_ok").html(data);
	   		$(".store_ok").show().delay(1500).hide(1000);  
	   	}
	   	function alert_msg_1(data){
	   		$(".store_ok").html(data);
	   		$(".store_ok").show().delay(500);  
	   	}
	   	function quit(){
	   		$.ajax({
				url:"loginOut.jhtml",
				dataType:"json",
				success:function(data){
					if(data.code == 1){
						window.location.href = "initStoreLogin.jhtml";
					}
				}
			});
	   	}
	   </script>
   </div>
