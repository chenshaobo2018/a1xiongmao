/* Filename        zjc_detail.js */
/* description    js */ 
$(function(){ 
	  	$('.md_gkr ul li').on('click',function(){
		    $(".md_gkr ul li").removeClass("click_hovers");
			$(this).addClass("click_hovers");
		});
 
	   // 购物车加减
		$(".add").click(function(){
			var n=$(this).prev().val();
			var num=parseInt(n)+1;
			if(num==0){ return;}
			$(this).prev().val(num);
		});
		//减的效果
		$(".jian").click(function(){
			var n=$(this).next().val();
			var num=parseInt(n)-1;
			if(num==0){ return}
			$(this).next().val(num);
		});
		// 收藏 		 	
	    $(".praises").click(function(){ 
			if($(this).find("img").attr("src") == ("images/icon/sc.png")){
				$(this).find(".praise").html("<img src='images/icon/sc (2).png' class='praise-img' alt='' title='' />");  
			}else{
				$(this).find(".praise").html("<img src='images/icon/sc.png' class='praise-img' alt='' title=''/>");  
			}
		}); 
		$('.tabPanel_detail ul li').click(function(){
			$(this).addClass('hit_de').siblings().removeClass('hit_de');
			$('.panes_detail>div:eq('+$(this).index()+')').show().siblings().hide();	
		}); 
	// 登录扫码切换		
	$('.login_content_one').on("click",'.heart',function(){		
		var A=$(this).attr("id");
		var B=A.split("like");
		var messageID=B[1];
		var C=parseInt($("#likeCount"+messageID).html()); 
		var D=$(this).attr("rel");	   
		if(D === 'like') {       
			$(".likeCount1").show();
			$(".likeCount").hide();			
			$(this).addClass("heartAnimation").attr("rel","unlike");
		}else{
			$(".likeCount").show();
			$(".likeCount1").hide(); 
			$(this).removeClass("heartAnimation").attr("rel","like"); 
		}
	});
	// 详情页收藏店铺
	$('body').on("click",'.feed_detal',function(){
		
		var A=$(this).attr("id");
		var B=A.split("like_de");
		var messageID=B[1];
		var C=parseInt($("#likeCount_de"+messageID).html()); 
		var D=$(this).attr("rel");
	   
		if(D === 'like_de') {        
			$(this).addClass("heartAnimation_detail").attr("rel","unlike-de");
		}else{ 
			$(this).removeClass("heartAnimation_detail").attr("rel","like_de"); 
		}
	});
	// register
 	   $('.btn_sub').click(function(){ 
		   $("#content1").hide();
		   $("#content2").show(); 
		   $("#step-1").hide();
		   $("#step-2").show(); 
		   $(".sdfop").hide();
			})
	  $(".btn_sub_two").click(function(){
		   $("#content1").hide();
		   $("#content2").hide(); 
		   $("#content3").show(); 
		   $("#step-1").hide();
		   $("#step-2").hide(); 
		   $("#step-3").show();  
	  })
	  $(".li_btn_once").click(function(){
		   $("#content1").hide();
		   $("#content2").hide(); 
		   $("#content3").hide(); 
		   $("#content4").show(); 
		   $("#step-1").hide();
		   $("#step-2").hide(); 
		   $("#step-3").hide(); 
		   $("#step-4").show(); 
		});
		$(".vaipd_side_reg label[name='vip_cjekc_reg']").click(function(){
			if($(this).hasClass('checked')){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			} 
		}); 
		
	    $('.cart_page ul li a').on('click',function(){
		    $(".cart_page ul li a").removeClass("clicksd");
			$(this).addClass("clicksd");
		}); 
		$(".cart_tree_content ul li:last").css("margin-right","0");		
	    $('.tabPanel_coll ul li').click(function(){
			$(this).addClass('hit_coll').siblings().removeClass('hit_coll');
			$('.panes_coll>div:eq('+$(this).index()+')').show().siblings().hide();	
		}); 
		// 收藏
		$(".in_check label[name='che_input'],.coll_span_ones label[name='che_input_all'],.coll_span_ones_pin label[name='che_input_all_pin'],.in_check_pin label[name='che_input']").click(function(){
			if($(this).hasClass('checked')){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			}			 
		}); 
		// 全选与全不选 收藏的商铺
			$(".coll_span_ones label[name='che_input_all']").click(function(){
			if($(".in_check label[name='che_input']").hasClass('checked')){
				$(".in_check label[name='che_input']").removeClass("checked");
			}else{
				$(".in_check label[name='che_input']").addClass("checked");
			}
		}); 
		// 全选与全不选 收藏的商品
				$(".coll_span_ones_pin label[name='che_input_all_pin']").click(function(){
			if($(".in_check_pin label[name='che_input']").hasClass('checked')){
				$(".in_check_pin label[name='che_input']").removeClass("checked");
			}else{
				$(".in_check_pin label[name='che_input']").addClass("checked");
			}
		}); 
		$(".coll_span_two").click(function(){ 		 
				// 删除当前被选中的父类元素pand_div 收藏的商铺
				if($(".in_check label[name='che_input']").hasClass("checked")){  
					$(".in_check label.checked").parent().parent().parent().parent().remove(); 
				}
		}); 
			$(".delr_pan").click(function(){ 	
					$(this).parent().parent().parent().parent().remove();
				// 删除当前被选中的父类元素pand_div 收藏的商铺				 
		});  
		$(".coll_span_twos").click(function(){ 		 
				// 删除当前被选中的父类元素pand_div收藏的商品
				if($(".in_check_pin label[name='che_input']").hasClass("checked")){  
					$(".in_check_pin label.checked").parent().parent().parent().parent().remove(); 
				}
		}); 	
		$(".psf").click(function(){ 	
					$(this).parent().parent().parent().parent().remove();
				// 删除当前被选中的父类元素pand_div 收藏的商铺 
		});  
  
	   	$('.tabPanel_vip ul li').click(function(){
			$(this).addClass('hit_vip').siblings().removeClass('hit_vip');
			$('.panes_vip>div:eq('+$(this).index()+')').show().siblings().hide();	
		}); 
		
		// 四中状态
		$(".tabs_ul_vip ul li:last").css({'margin-right':'0','width':'220px','text-align':'right'}); 
		$(".vdf_content ul li").eq(4).css("margin-right","0"); 
		$(".vdf_content ul li").eq(9).css("margin-right","0");
		
		
		$(".text_update").click(function(){
			$(".account_two").show();
			$(".account_one").hide(); 
		})
		$(".pwd_update").click(function(){
			$(".account_tree").show();
			$(".account_one").hide(); 
			$(".account_two").hide(); 
		})
		$(".pwd_oks").click(function(){
			$(".account_four").show();
			$(".account_one").hide(); 
			$(".account_two").hide(); 
			$(".account_tree").hide(); 
		})
		
		// 个人中心check
		$(".vaipd_side label[name='vip_cjekc'],.vaipd_side label[name='trans_cel'],.addres_check label[name='addres_cel']").click(function(){
			//alert(1);
			if($(this).hasClass('checked')){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			}  
		}); 
		// 收货地址初始页
		$(".addres_one li.add_on_six").click(function(){
			$(this).parent().remove(); 
		})
		 	
		$(".strats").click(function(){ 
			$(".addre_top,.addre_startbom").hide(); 
			$(".addre_content_one").show();
			
		})
		 $(".add_texts").click(function(){
			  $(".addre_content_two,.addre_top").show(); 
			  $(".addre_content_one").hide();			
		  }) 
		
		// 收货地址内页		
		$(".adebtn_ul_one li.adebtn_li_six").click(function(){
			$(this).parent().remove(); 
		})
		// 我的订单 单选及更新个数
		$(".lisf_indent label[name='caels_indet'],.cart_checkbox_all label[name='indentCheckBox_check']").click(function(){
				var numer_one_cart = parseFloat($(".indef_foot").find("#num_sumindent").text());
				if($(this).hasClass('checked')){
					numer_one_cart--;
					$("#num_sumindent").text(numer_one_cart); 
					$(this).removeClass("checked");
				}else{
					numer_one_cart++;
					
					$("#num_sumindent").text(numer_one_cart); 
					$(this).addClass("checked");
				}			  
			}); 
		
		// 全选与全不选
			var flag = true;
			$("#select_vip_all").click(function(){ 
				if(flag){
					$("#all_vip_ca").addClass("checked");
					$(".lisf_indent label[name='caels_indet']").addClass("checked");
					flag = false;
				}else{
					$("#all_vip_ca").removeClass("checked");
					$(".lisf_indent label[name='caels_indet']").removeClass("checked");
					flag = true;
				}
				
			   // 更新总个数
				var loy=$(".lisf_indent label.checked").size();
				$("#num_sumindent").text(loy); 
			});  
		
		$(".del_indent").click(function(){
			$(this).parent().parent().parent().remove();			 
		});
		// // 更新总个数
		// $(".lisf_indent label[name='caels_indet']").click(function(){
			// if($(this).hasClass('checked')){
				 // var num1a=parseFloat($("#num_sumindent").text());
				 // num1a++; 
				 // $("#num_sumindent").text(num1a); 			
			// }
		// });
 
		
	 // 系统消息
	 //1 单选
		$(".sys_check label[name='sys_cck'],.syes_all label[name='all_sys_uio']").click(function(){
			if($(this).hasClass('checked')){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			}
			 
		});   
		// 全选与全不选 
		$("#allsys_check").click(function(){ 
				if(flag){
					$("#allsys_ok").addClass("checked");
					$(".sys_check label[name='sys_cck']").addClass("checked");
					flag = false;
				}else{
					$("#allsys_ok").removeClass("checked");
					$(".sys_check label[name='sys_cck']").removeClass("checked");
					flag = true;
				}				 
			}); 
		//3 删除当前被选中的ul
		$(".del_sys").click(function(){
			if($(".sys_check label[name='sys_cck']").hasClass("checked")){  
				$(".sys_check label.checked").parent().parent().parent().remove(); 
			}
		}); 
		// 标题与内容显示和隐藏
		$(".sye_four a").click(function(){
			$(".sys_content").show();
			$(".syts_main").hide(); 
		});   
	   	// register
 	    $('.order_onebtn').click(function(){ 
		   $("#content1_order,#step-1_order,.sdfop").hide();
		   $("#content2_order,#step-2_order").show();   
	    })
		$(".btn_sub_two").click(function(){
		   $("#content1_order,#content2_order,#step-1_order,#step-2_order").hide();
		   $("#content3_order,#step-3_order").show();  
		})
		$(".li_btn_once").click(function(){
		   $("#content1_order,#content2_order,#content3_order,#step-1_order,#step-2_order,#step-3_order").hide(); 
		   $("#content4_order,#step-4_order").show();   
		});
	   
	   // 个人中心
	   // 第一种
	   $(".ok_suee").click(function(){
		   $(".vip_one_popover,.vip_one_popover_maker,.detail_vip_one").show();  
	   })
	   $(".sud_ok").click(function(){
		   $(".sdd_vip").show();
		   $(".detail_vip_one").hide();
		   setTimeout('$(".sdd_vip,,.vip_one_popover_maker,.vip_one_popover").hide()',3000);  
	   })
	   // 第二种
	     $(".ok_suee_ti").click(function(){
		   $(".sdd_vip_ti,.vip_two_popover_maker,.vip_two_popover").show(); 
		   setTimeout('$(".sdd_vip_ti,,.vip_two_popover_maker,.vip_two_popover").hide()',3000); 		   
	   })
	  // 我的操作记录
	  $('.tabs_ul_record ul li').click(function(){
			$(this).addClass('hit_ul_record').siblings().removeClass('hit_ul_record');
			$('.panes_ul_record>div:eq('+$(this).index()+')').show().siblings().hide();	
		}); 	   
	  // 我的分享和二维码
	  $('.tabs_ul_share ul li').click(function(){
			$(this).addClass('hit_ul_share').siblings().removeClass('hit_ul_share');
			$('.panes_ul_share>div:eq('+$(this).index()+')').show().siblings().hide();	
		}); 	   	   
	   sums_prices_vip();
	   
	// 会议门票
	$(".tik_ul li:last").css("margin-right","0"); 
	$(".tik_tr_li ul li:eq(5)").css("margin-right","0");  
	$(".tik_tr_li ul li:eq(11)").css("margin-right","0");  
	$(".tik_tr_li ul li:eq(17)").css("margin-right","0");  
	$(".tik_tr_li ul li:eq(23)").css("margin-right","0");  
	
	// 平台产品分类
	$(".pr_head_ul ul li:last").css("border-bottom","0");  
	$('.ulili li a').on('click',function(){
		$(".ulili li a").removeClass("active");
		$(this).addClass("active"); 
	});
	// 综合排序
	$(".kili_one").click(function(){ 
		$("#searchSelected_lione").html('销量排序'+'<img src="images/nav/f_jt.png" class="sores" />');
		$("#searchSelected_litwo").html('信用排序'+'<img src="images/nav/f_jt.png" class="sores" />'); 
		$("#searchSelected_litree").html('价格排序'+'<img src="images/nav/f_jt.png" class="sores" />');   
	});
 // <!-- 销量排序 -->
	$("#searchSelected_lione").click(function(){ 
		$("#searchTab_lione").show();  
	});
	$("#searchTab_lione li").click(function(){
		$("#searchSelected_lione").html($(this).html());
		$("#searchTab_lione").hide(); 		
		$("#searchSelected_litwo").html('信用排序'+'<img src="images/nav/f_jt.png" class="sores" />'); 
		$("#searchSelected_litree").html('价格排序'+'<img src="images/nav/f_jt.png" class="sores" />');   		
	});
	//<!-- 信用排序 --> 
	$("#searchSelected_litwo").click(function(){ 
		$("#searchTab_litwo").show();  
	});  
	$("#searchTab_litwo li").click(function(){
		$("#searchSelected_litwo").html($(this).html());
		$("#searchTab_litwo").hide(); 		
		$("#searchSelected_lione").html('销量排序'+'<img src="images/nav/f_jt.png" class="sores" />');
		$("#searchSelected_litree").html('价格排序'+'<img src="images/nav/f_jt.png" class="sores" />');   
	});
	//<!-- 价格排序 --> 
	$("#searchSelected_litree").click(function(){ 
		$("#searchTab_litree").show();  
	});  
	$("#searchTab_litree li").click(function(){
		$("#searchSelected_litree ").html($(this).html());
		$("#searchTab_litree").hide();   
		$("#searchSelected_lione").html('销量排序'+'<img src="images/nav/f_jt.png" class="sores" />');
		$("#searchSelected_litwo").html('信用排序'+'<img src="images/nav/f_jt.png" class="sores" />'); 
	});
	   // 平台产品分类
	   var liNum = $(".pr_head_ul ul li").length;
			if(liNum > 5){
				//超过10个执行
				$(".prd_img").show(); 
			} else {
				//10个以内执行
				$(".prd_img").hide();
			}   
	 	$('.tabPanel_ul_class ul li').click(function(){
			$(this).addClass('hit_ul').siblings().removeClass('hit_ul');
			$('.panes_ul_class>div:eq('+$(this).index()+')').show().siblings().hide();	
		});  
		
		// 个人中心 -待评价商品跳转至评价
		$(".wait_ord a").click(function(){
			window.location.href="order.html?orders_url="+12;			
		});  		
   });	  
   
   // 个人中心//我的订单全选删除
   function delGoods(){
		$(".dfokgs.checked").each(function(){
			var shopNum = $(this).data("shop");
			var goodsNum = $(this).data("goods");
			$(".goods_"+shopNum+"_"+goodsNum).remove(); 
			console.log($("#shop_"+shopNum).find(".indent_con_one_cont").length);
			if($("#shop_"+shopNum).find(".indent_con_one_cont").length==0){
				$("#shop_"+shopNum).remove();
			}
		}); 
	}
	 
   // 个人中心左点右显
	 function setTab(m,n){
		var tli=document.getElementById("leftmenu"+m).getElementsByTagName("li");
		var mli=document.getElementById("mcont"+m).getElementsByTagName("u");
		for(i=0;i<tli.length;i++){  
		  tli[i].className=i==n?"hover":"";
		  mli[i].style.display=i==n?"block":"none";
		}
	}
	// 个人中心的小计
	function sums_prices_vip() { 		
		var sumss_price = 0;
		$(".orde_tree_contvip").each(function(){ 	
			var soif = 0;
			// 数量
			$(this).find(".cons_tr").each(function(){
				var num1s_order=  parseFloat($(this).find(".sum_ons_order").text());
				// 单价			
				var num2s_order=  parseFloat($(this).find(".osde_on").text()); 
				sumss_price = num1s_order * num2s_order;
				soif += sumss_price;
			});				
			$(this).find(".usmdf").text(soif);				
		});
		
	} 	 
		
 
   
   
   
		