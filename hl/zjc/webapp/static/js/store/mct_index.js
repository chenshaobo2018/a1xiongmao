/* Filename        zjc_index.js */ 
$(function(){ 
// 商家后台登录页 
	 	$(".mct_check label[name='mck_chil']").click(function(){
			//alert(1);
			if($(this).hasClass('checked')){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			}  
		});
		// 商家后台欢迎页导航切换
	$('.mct_ulli li').on('click',function(){
		    $(".mct_ulli li").removeClass("mct_click");
			$(this).addClass("mct_click");
		});		
		// 商家后台欢迎页交易提示
	$('.cmtoew_ul li').on('click',function(){
		    $(".cmtoew_ul li").removeClass("lic_ok");
			$(this).addClass("lic_ok");
		});	
		// 商家后台欢迎页店铺及商品展示
	$('.cmtoew_ul_tree li').on('click',function(){
		    $(".cmtoew_ul_tree li").removeClass("lic_ok_tree");
			$(this).addClass("lic_ok_tree");
		});		
 	// 分页
	$('.mct_page ul li a').on('click',function(){
	    $(".mct_page ul li a").removeClass("clicksd");
		$(this).addClass("clicksd");
	});	
 	// 分页
	$('.mct_page_add ul li a').on('click',function(){
	    $(".mct_page_add ul li a").removeClass("clicksd");
		$(this).addClass("clicksd");
	});
	// 商家后台单选
  	$(".bill_list_zm label[name='bill_zm'],.mct_check_list label[name='mct_list'],.mct_all label[name='all_alls'],.mct_check_dtwo label[name='mct_dtwo'],.mct_all_two label[name='all_alls_two'],.mct_check_dtree label[name='mct_dis_tree'],.mct_all_tree label[name='all_alls_tree'],.mct_check_four label[name='mct_dis_four'],.mct_all_four label[name='all_alls_four'],.mct_cks label[name='rlpo'],.bill_list label[name='bill_check'],.bill_all_del label[name='bill_del']").click(function(){
			//alert(1);
			if($(this).hasClass('checked')){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			} 			 
		});
  	
  	$(".cken_bank").find("label").click(function(){
  		if($(this).hasClass('checked')){
			$(this).removeClass("checked");
		}else{
			$(this).addClass("checked");
		} 	
  	});
		// 商品列表toggle(
 		$(".mct_all label[name='all_alls']").click(function(){
			if($(".mct_check_list label[name='mct_list']").hasClass('checked')){
				$(".mct_check_list label[name='mct_list']").addClass("checked");
				
			}else{
				$(".mct_check_list label[name='mct_list']").addClass("checked");
			}	
		});
 		
	    $(".mct_delete_two").click(function(){
		    $(this).parent().parent().remove();
			// 更新条数	
			sum_list();
	    })
	    
	   // 点击全选进行删除待审商品
		$(".textd_page").click(function(){ 						
			if($(".mct_check_dtwo label[name='mct_dtwo']").hasClass("checked")){  
				$(".mct_check_dtwo label.checked").parent().parent().parent().remove();
			// 更新条数 
				sum_list();
			}
		});  
	  
	   // 点击全选进行删除回收站
		$(".textd_page_dis").click(function(){ 						
			if($(".mct_check_dtree label[name='mct_dis_tree']").hasClass("checked")){  
				$(".mct_check_dtree label.checked").parent().parent().parent().remove();
			// 更新条数 
				dis_sum();
			}
		}); 
	   // 点击全选进行删除评论
		$(".textd_page_dis").click(function(){ 						
			if($(".mct_check_four label[name='mct_dis_four']").hasClass("checked")){  
				$(".mct_check_four label.checked").parent().parent().parent().remove();
			// 更新条数 
				dislist_sum();
			}
		});   
		
		// 首页操作记录
	    $('.tabs_ul_record ul li').click(function(){
			$(this).addClass('hit_ul_record').siblings().removeClass('hit_ul_record');
			$('.panes_ul_record>div:eq('+$(this).index()+')').show().siblings().hide();	
		});
		
		$(".mctsd_add").click(function(){ 
			$(".add_te").attr({ readonly: 'true' });
			$(".mct_div").prepend("<input type='text' class='add_te' placeholder=' 如：精装1瓶（不超过20字）'/> ");
		})
		
		$(".mctg").click(function(){
			$(".mct_te_two").before("<div class='mct_te_twos fl'><input type='text' class='texop_one' placeholder='规格名称'/><input type='text' class='texop_two' placeholder='规格详情'/></div> ");
		})
		 // app pc
	    $('.tabs_ul_mcttwo ul li').click(function(){
			$(this).addClass('hit_ul_mcttwo').siblings().removeClass('hit_ul_mcttwo');
			$('.panes_ul_mcttwo>div:eq('+$(this).index()+')').show().siblings().hide();	
		});
		// 遮罩层
		$(".img_upc").click(function(){
			$(".upfile_img,.upfile_make").show(); 
		})
		$(".up_cont,.no_btn").click(function(){
			$(".upfile_img,.upfile_make").hide(); 
		})

		$(".print_make").click(function(){
			$(".print_ok,.print_make").hide(); 
		});

		$(".print_okems_make").click(function(){
			$(".print_okems,.print_okems_make").hide();
			
		});
	 
		// 点击全选进行删除货单
		$(".bill_delas").click(function(){ 						
			if($(".bill_list label[name='bill_check']").hasClass("checked")){  
				$(".bill_list label.checked").parent().parent().parent().remove();
			// 更新条数 
				bill_sm();
			}
		});
		
		$(".bi_mon").click(function(){
			$(".mct_bi").show(); 
			
		}); 
		
});
	function sum_list(){		
			$(".mct_onetex_two").each(function(){				
				 var sumdw=$(".mct_tab_two tr").length-1;
				 $(".mct_onetex_two").text(sumdw);
			});	
	}
	// 统计商品列表总条数	
	function sujmds(){
		$(".mct_onetex").each(function(){			
				 var sumdw=$(".mct_tab_one tr").length-1;
				 $(".mct_onetex").text(sumdw);
			}); 
	}
	// 统计回收站总条数	
	function dis_sum(){
		$(".mct_onetex_tree").each(function(){			
				 var sumdw=$(".mct_tab_tree tr").length-1;
				 $(".mct_onetex_tree").text(sumdw);
			}); 
	}
	// 统计评论总条数	
	function dislist_sum(){
		$(".mct_onetex_four").each(function(){			
				 var sumdw=$(".mct_tab_four tr").length-1;
				 $(".mct_onetex_four").text(sumdw);
			}); 
	}
	// 统计下架商品总条数	
	function unsale_sum(){
		$(".mct_onetex_five").each(function(){			
				 var sumdw=$(".mct_tab_five tr").length-1;
				 $(".mct_onetex_five").text(sumdw);
			}); 
	}
	// 商家后台首页统计
	// 小计销量
	function mvt_sum_one() { 		
		var price_one = 0;  
		var simf_pr=0; 
			$(".mcton_four_cont").find(".mct_tr").each(function(){
				var muns_one=  parseFloat($(this).find(".go_trone").text()); 
				var muns_two=  parseFloat($(this).find(".go_trtwo").text());  
				price_one = (muns_one + muns_two);
				//simf_pr =parseFloat(simf_pr) + price_one;
				//console.log(price_one);
				$(this).find(".go_trtree").text(price_one);		
			});		 
	} 	
	// 总订单量
	function mvt_sum_two() { 		
		var sumdoio = 0;  
			$(".mcton_four_cont").find(".mct_tr").each(function(){
				var sumd_one=  parseFloat($(this).find(".go_trtree").text()); 
				sumdoio+=sumd_one; 
				$(".mcton_four_cont").find(".mct_count").text(sumdoio);		
			});		 
	} 	
	// 总订单额
	function mvt_sum_tree() { 		
		var sumone = 0;  
			$(".mcton_four_cont").find(".mct_tr").each(function(){
				var sumd_two=  parseFloat($(this).find(".mcr_tree").text()); 
				sumone+=sumd_two; 
				$(".mcton_four_cont").find(".sumsd_op").text(sumone);		
			});	
	} 	

	    // 个人中心左点右显
	 function setTab(m,n){
		var tli=document.getElementById("mct_left"+m).getElementsByTagName("li");
		var mli=document.getElementById("mcont"+m).getElementsByTagName("u");
		
		for(i=0;i<tli.length;i++){  
		  tli[i].className=i==n?"mct_hover":"";
		  mli[i].style.display=i==n?"block":"none";
		}
	}
	
// 用户评论备注悬浮
$(function(){		 
	var x=10;
	var y=20;
	$("a.tooltip").mouseover(function(e){
		this.myTitle=this.title;
		this.title="";
		var tooltip="<div id='tooltip'>"+this.myTitle+"</div>";   //创建DIV元素
		$(".link_mct").append(tooltip); 	//追加到文档中
		$("#tooltip").css({"top": (e.pageY+y) + "px","left": (e.pageX+x) + "px"}).show();	//设置X  Y坐标， 并且显示
	}).mouseout(function(){
		this.title=this.myTitle;
		$("#tooltip").remove();    //移除
	}).mousemove(function(e){
		$("#tooltip").css({"top": (e.pageY+y) + "px","left": (e.pageX+x) + "px"});
	})		
});
	// d打印
function printHtml(html) {
	var bodyHtml = document.body.innerHTML;
	document.body.innerHTML = html;
	window.print();
	document.body.innerHTML = bodyHtml;
}
function onprint() {
	var html = $("#printArea").html();
	printHtml(html);
}
function onprints() {
	var html = $("#printAreas").html();
	printHtml(html);
}
	// 统计货单列表总条数	
	 function bill_sm(){
		 $(".bill_sums").each(function(){			
				  var sum_bills=$(".bill_tab table tr").length-1;
				  $(".bill_sums").text(sum_bills);
		}); 
	 }

//时间格式化
function format(date){
	if(date == null){
		return "";
	}else{
		return date;
	}
	/*var date = new Date(date);
	var Y = date.getFullYear();
	var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
	var D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate());
	var h = (date.getHours() < 10 ? '0'+(date.getHours()) : date.getHours());
	var m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes());
	var s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
	return Y+"-"+M+"-"+D+" "+h+":"+m+":"+s;*/
}

