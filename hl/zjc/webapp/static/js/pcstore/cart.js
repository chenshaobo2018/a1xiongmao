	$(function(){  
			// 我的购物车
			// 单选
			$(".lisf_input label[name='caels'],.cart_checkbox_tree label[name='cartCheckBox_check']").click(function(){
				var numer_one_cart = parseFloat($(".sum_all").find("#totalNum").text());
				if($(this).hasClass('checked')){
					numer_one_cart--;
					$("#totalNum").text(numer_one_cart); 
					$(this).removeClass("checked");
				}else{
					numer_one_cart++;
					$("#totalNum").text(numer_one_cart); 
					$(this).addClass("checked");
				} 
			});  
			// 全选与全不选
			var flag = true;
			$("#select_all_cart").click(function(){ 
				if(flag){
					$("#sel_carts").addClass("checked");
					$(".lisf_input label[name='caels']").addClass("checked");
					flag = false;
				}else{
					$("#sel_carts").removeClass("checked");
					$(".lisf_input label[name='caels']").removeClass("checked");
					flag = true;
				} 
					// 更新总个数
					var loy=$(".lisf_input label.checked").size();
					$("#totalNum").text(loy);  
			}); 
			
			//点击一行商品后的‘删除’按钮进行删除
			$(".scdfdele").click(function(){ 		 
				var shopNum = $(this).data("shop");
				var goodsNum = $(this).data("goods"); 
				$(".goods_"+shopNum+"_"+goodsNum).remove();
				if($("#shop_"+shopNum).find(".csd_cont").length==0){
					$("#shop_"+shopNum).remove();
				}
			});  
			totl();
			adddel();
			//sum_all();
			//countNum();
			//sums_num();
			sums_num_add(); 
	});  
	//合计
	function totl() { 		
			var sums = 0;
			$(".csd_cont").each(function(){ 	
					var num1=  parseFloat($(this).find(".pices").text());
					var num2 = parseFloat($(this).find(".reducsdero").val());
					sums = sums + (num1*num2);
					$("#priceTotal").text(sums);					
			}); 
	}
	
	function adddel(){
		//小计和加减
		//加 
		$(".add_one").each(function() {
				$(this).click(function() {
					var $multi = 0;
					var vall = $(this).prev().val();
					vall++;
					$(this).prev().val(vall);
					$multi = parseFloat(vall) * parseFloat($(this).parent().prev().text());
					$(this).parent().next().text(Math.round($multi));
					totl(); 
					//sums_num();  
					// 更新每一行的小计
					var sums_one = 0;
					var num1_one=  parseFloat($(this).parent().parent().prev().find(".pices").text()); 
					 sums_one = sums_one + ( num1_one * vall );  
					 $(this).parent().parent().next().find(".totle").text(sums_one); 
				})
			})
			//减
		$(".reduc").each(function() {
				$(this).click(function() {
					var $multi1 = 0;
					var vall1 = $(this).next().val();
					vall1--;
					if(vall1 <= 0) {
						vall1 = 1;
					}
					$(this).next().val(vall1);
					$multi1 = parseFloat(vall1) * parseFloat($(this).parent().prev().text());
					$(this).parent().next().text(Math.round($multi1)); 
					totl(); 
					//sums_num(); 
					// 更新每一行的小计
					var sums_one = 0;
					var num1_one=  parseFloat($(this).parent().parent().prev().find(".pices").text()); 
					 sums_one = sums_one + ( num1_one * vall1 );  
					 $(this).parent().parent().next().find(".totle").text(sums_one); 
				})
			})	
	}			
	
	/*js更新总个数*/
	// function countNum(){
	// var numberEle=document.getElementsByClassName("reducsdero"); 

	// var totalNum = 0;
	// for(var i = 0;i<numberEle.length;i++){
		// totalNum+= parseInt(numberEle[i].value);
	// }
	// document.getElementById("totalNum").innerHTML =totalNum;
	// }
	// 更新总个数
	 // function sums_num(){
		 // var sumsd = 0;
		 // $(".csd_cont").each(function(){ 
			 // var numer = parseFloat($(this).find(".reducsdero").val());
			 // sumsd += numer; 
			 // $("#totalNum").text(sumsd); 
		 // }); 
	 // }  
	// 更新邮费
	 function sums_num_add(){
		 var sumsd = 0;
		 // 邮费累加
		  $(".csd_cont").each(function(){ 
			  var numer = parseFloat($(this).find(".sdf").text());
			  sumsd += numer; 
			  $(".mosn_sim").text(sumsd); 
		  });  
	 }  
	// 更新总个数
	// function sums_num(){ 
		// $(".cart_two").each(function(){
	     // $(".lisf_input label[name='caels']").click(function(){		
			// var numer = parseFloat($(".sum_all").find("#totalNum").text());
			// //sumsd += numer; 
			// numer++;
			// console.log(numer);
			// $("#totalNum").text(numer); 
			// });
		// });  
	function delGoods(){
		$(".dfokgs.checked").each(function(){
			var shopNum = $(this).data("shop");
			var goodsNum = $(this).data("goods");
			$(".goods_"+shopNum+"_"+goodsNum).remove();
			// 当全选删除之后把文本设为0
			// $("#totalNum").text("0"); 
			// $("#priceTotal").text("0");  
			//console.log($("#shop_"+shopNum).find(".csd_cont").length);
			if($("#shop_"+shopNum).find(".csd_cont").length==0){
				$("#shop_"+shopNum).remove(); 
			}		
		}); 
	}   
	//删除已下架的
	 function del_one(){
		$(".dfokgs.checked").each(function(){
			var shopNum = $(this).data("shop");
			var goodsNum = $(this).data("goods");
			$(".goods_"+shopNum+"_"+goodsNum).remove();
			// 当全选删除之后把文本设为0
			// $("#totalNum").text("0"); 
			// $("#priceTotal").text("0");  
			//console.log($("#shop_"+shopNum).find(".csd_cont").length);
			if($("#shop_"+shopNum).find(".csd_cont").length==0){
				$("#shop_"+shopNum).remove();
			}		
		}); 
		
	 } 
	 










