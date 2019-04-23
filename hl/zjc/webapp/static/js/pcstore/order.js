$(function(){ 
		// 四种状态切换
		$('.subd').click(function(){ 
				   $("#content1_order").hide();
				   $("#content2_order").show(); 
				   $("#step-1_order").hide();
				   $("#step-2_order").show(); 
				   $(".sdfop").hide();
			})
			// 遮罩层
			$(".ok_btn").click(function(){
					$(".order_pwd_popover,.order_pwd_popover_maker").show();  
			});
			// $(".op_oks").click(function(){
				   // $("#content1_order").hide();
				   // $("#content2_order").hide(); 
				   // $("#content3_order").hide(); 
				   // $("#content4_order").show(); 
				   // $("#step-1_order").hide();
				   // $("#step-2_order").hide(); 
				   // $("#step-3_order").hide(); 
				   // $("#step-4_order").show(); 
		// });
		$('.op_oks').click(function(){ 
				   $("#content1_order").hide();
				   $("#content2_order").hide(); 
				   $("#content3_order").hide(); 
				   $("#content4_order").show(); 
				   $("#step-1_order").hide();
				   $("#step-2_order").hide(); 
				   $("#step-3_order").hide(); 
				   $("#step-4_order").show(); 
				   $(".sdfop").hide();
			})
		// content1_order 默认地址
		$(".order_addconts ul li").click(function(){   
			$(".order_addconts ul li").removeClass('selected'); 
			$(this).addClass('selected');   			
		});
	 
		// radio content1_order
		$('.order_addconts ul li').click(function(){
			var    $radio = $(this).find("input[type=radio]"),
					 $flag  = $radio.is(":checked");
			if( !$flag ){
				$radio.prop("checked",true);   
			}
		});
	
		// content2_order中的支付方式选择
		$(".oderg_botn_side ul li").click(function(){  
			$(".oderg_botn_side ul li").removeClass('select'); 
			$(this).addClass('select');   
			
		}); 
		// content2_order支付方式选择
		$(".btn_zf p").click(function(){  
			$(".btn_zf p").removeClass('sders'); 
			$(this).addClass('sders');   
			
		});
		$(".fl_ul li").click(function(){  
			$(".fl_ul li").removeClass('sders'); 
			$(this).addClass('sders');   
			
		});
		// content2_order中的支付方式选择input
		$('.oderg_botn_side ul li').click(function(){
			var    $radio = $(this).find("input[type=radio]"),
					 $flag  = $radio.is(":checked");
			if( !$flag ){
				$radio.prop("checked",true);   
			}
		});	
	 
		  // 成功
		  $(".btn_pwds").click(function(){
			 $(".sudn_order").show();
			  $(".pwd_zf,.over_order").hide();
			   setTimeout('$(".sudn_order,.order_popover,.order_popover_mask").hide("slow")',3000);
			   setTimeout('$("#content3_order,#step-3_order").show()',3000); 
			   setTimeout('$("#content1_order,#content2_order,#content4_order,#step-1_order,#step-2_order,#step-4_order").hide()',3000);
			   
		  })  
		    
		  	// 第四部遮罩层
		  $(".op_oks_pins").click(function(){
			  $(".order_popover_four,.ofr_fou").show();
			  $(".order_popover_mask_four").show();
			  
		  }) 
		    $(".sud_ok").click(function(){
			  $(".oder_sudd").show();
			  $(".ofr_fou").hide();
			  setTimeout('$(".oder_sudd,.order_popover_mask_four").hide()',3000);
			  
		  })
		  
		  $(".sdop").click(function(){
			  $(".order_pwd_popover,.order_pwd_popover_maker,.odsr_tex").show(); 
		  })
		  // 输入密码确定跳转第三步
		  $(".btn_pwds_oi").click(function(){
				$(".pf_pwd_scuu,.psdf_succe").show();
				$(".odsr_tex").hide();
			   setTimeout('$(".sudn_order,.order_popover,.order_popover_mask").hide("slow")',3000);
			   setTimeout('$("#content3_order,#step-3_order").show()',3000); 
			   setTimeout('$("#content1_order,#content2_order,#content4_order,#step-1_order,#step-2_order,#step-4_order").hide()',3000);
		    })
	   // 取消
		   $(".cth_btn").click(function(){
			  $(".onv_pwd_popover,.onv_pwd_popover_maker,.psdf_succe_noop").show(); 
			  setTimeout('$(".psdf_succe_noop,.onv_pwd_popover,.onv_pwd_popover_maker").hide()',3000); 
		  }); 		  
			totl();
			adddel();  
			totl_yun();
			sums_prices(); 
			
}); 
	 //定义函数记录星星状态
	function SaveClass()
	{
		 var temClassArry = new Array()
		 $("#UserStart > li").each(function(i){
		 temClassArry[i] = $(this).attr("class");
		 })
		 return temClassArry;
	}
	$(document).ready(function(){
		 var temparray = SaveClass();
		//鼠标滑到星星上
		// $("#UserStart > li").mouseout( 
		 $("#UserStart > li").click( 
		  function(){
		   var currentCount=0;
		   currentCount = $("#UserStart > li").index($(this)[0])
		   $("#UserStart > li").each(function(i){if(i<=currentCount){
			   $(this).removeClass();
			   $(this).addClass("on");
			  }else{
			   $(this).removeClass();
			   $(this).addClass("off");
			  }
			})
		  }//鼠标在星星上点击
		 ).click(function(){
		   temparray = SaveClass();
		  }).mouseout(function(){//鼠标滑出星星上
		  $("#UserStart > li").each(function(i){
		   $(this).removeClass();
		   $(this).addClass(temparray[i]);
		  })
		  })
	});
	//合计
	function totl() { 		
			var sums = 0;
			$(".order_onecontes").each(function(){ 	
					var num1=  parseFloat($(this).find(".pices").text());
					var num2 = parseFloat($(this).find(".text_order").val());
					sums = sums + (num1*num2); 
					$("#order_total").text(sums);					
			});
	} 
	// 运费
	function totl_yun() { 		
			var sumss = 0;
			$(".order_onecontes").each(function(){ 	
					var num1s=  parseFloat($(this).find(".sumd_or").text()); 
					sumss += num1s;
					$(".sdyu").text(sumss);	
		});
	} 	 
	// 3/4 结算平均总价(每一行商品的数量与单价相乘 再相加得以总价)
	function sums_prices() { 		
			var sumss_price = 0;
			$(".orde_tree_cont").each(function(){ 	
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
	function adddel(){
		//小计和加减
		//加 
		$(".add_order").each(function() {
				$(this).click(function() {
					var $multi = 0;
					var vall = $(this).prev().val();
					vall++;
					$(this).prev().val(vall);
					$multi = parseFloat(vall) * parseFloat($(this).parent().prev().text());
					$(this).parent().next().text(Math.round($multi));
					totl();  
					// 更新每一行的小计
					var sums_one = 0;
					var num1_one=  parseFloat($(this).parent().parent().next().find(".pices").text()); 
					 sums_one = sums_one + ( num1_one * vall );  
					//console.log(sums_one);
					 $(this).parent().parent().next().next().next().find(".osde_sum").text(sums_one); 
				 })

			});
			//减
		$(".red_order").each(function() {
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
					// 更新每一行的小计
					var sums_one = 0;
					var num1_one=  parseFloat($(this).parent().parent().next().find(".pices").text()); 
					 sums_one = sums_one + ( num1_one * vall1 );  
					  $(this).parent().parent().next().next().next().find(".osde_sum").text(sums_one); 
				})
			})	
	}			
	 /* 支付密码 */
 $(function(){				
	var payPassword = $("#payPassword_container"),
		_this = payPassword.find('i'),	
		k=0,j=0,
		password = '' ,
		_cardwrap = $('#cardwrap');
		//点击隐藏的input密码框,在6个显示的密码框的第一个框显示光标
		payPassword.on('focus',"input[name='payPassword_rsainput']",function(){
		
			var _this = payPassword.find('i');
			if(payPassword.attr('data-busy') === '0'){ 
			//在第一个密码框中添加光标样式
			   _this.eq(k).addClass("active");
			   _cardwrap.css('visibility','visible');
			   payPassword.attr('data-busy','1');
			}
			
		});	
		//change时去除输入框的高亮，用户再次输入密码时需再次点击
		payPassword.on('change',"input[name='payPassword_rsainput']",function(){
			_cardwrap.css('visibility','hidden');
			_this.eq(k).removeClass("active");
			payPassword.attr('data-busy','0');
		}).on('blur',"input[name='payPassword_rsainput']",function(){
			
			_cardwrap.css('visibility','hidden');
			_this.eq(k).removeClass("active");					
			payPassword.attr('data-busy','0');
			
		});
		
		//使用keyup事件，绑定键盘上的数字按键和backspace按键
		payPassword.on('keyup',"input[name='payPassword_rsainput']",function(e){
		
		var  e = (e) ? e : window.event;
		
		//键盘上的数字键按下才可以输入
		if(e.keyCode == 8 || (e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105)){
				k = this.value.length;//输入框里面的密码长度
				l = _this.size();//6
				
				for(;l--;){
				
				//输入到第几个密码框，第几个密码框就显示高亮和光标（在输入框内有2个数字密码，第三个密码框要显示高亮和光标，之前的显示黑点后面的显示空白，输入和删除都一样）
					if(l === k){
						_this.eq(l).addClass("active");
						_this.eq(l).find('b').css('visibility','hidden');
						
					}else{
						_this.eq(l).removeClass("active");
						_this.eq(l).find('b').css('visibility', l < k ? 'visible' : 'hidden');
						
					}				
				
				if(k === 6){
					j = 5;
				}else{
					j = k;
				}
				$('#cardwrap').css('left',j*30+'px');
			
				}
			}else{
			//输入其他字符，直接清空
				var _val = this.value;
				this.value = _val.replace(/\D/g,'');
			}
		});	
 })	 /* 支付密码 */
 $(function(){				
	var payPassword = $("#payPassword_container1"),
		_this = payPassword.find('i'),	
		k=0,j=0,
		password = '' ,
		_cardwrap = $('#cardwrap1');
		//点击隐藏的input密码框,在6个显示的密码框的第一个框显示光标
		payPassword.on('focus',"input[name='payPassword_rsainput1']",function(){
			var _this = payPassword.find('i');
			if(payPassword.attr('data-busy') === '0'){ 
			//在第一个密码框中添加光标样式
			   _this.eq(k).addClass("active");
			   _cardwrap.css('visibility','visible');
			   payPassword.attr('data-busy','1');
			}
		});	
		//change时去除输入框的高亮，用户再次输入密码时需再次点击
		payPassword.on('change',"input[name='payPassword_rsainput1']",function(){
			_cardwrap.css('visibility','hidden');
			_this.eq(k).removeClass("active");
			payPassword.attr('data-busy','0');
		}).on('blur',"input[name='payPassword_rsainput1']",function(){			
			_cardwrap.css('visibility','hidden');
			_this.eq(k).removeClass("active");					
			payPassword.attr('data-busy','0');			
		});
		
		//使用keyup事件，绑定键盘上的数字按键和backspace按键
		payPassword.on('keyup',"input[name='payPassword_rsainput1']",function(e){
		var  e = (e) ? e : window.event;		
		//键盘上的数字键按下才可以输入
		if(e.keyCode == 8 || (e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105)){
				k = this.value.length;//输入框里面的密码长度
				l = _this.size();//6				
				for(;l--;){				
				//输入到第几个密码框，第几个密码框就显示高亮和光标（在输入框内有2个数字密码，第三个密码框要显示高亮和光标，之前的显示黑点后面的显示空白，输入和删除都一样）
					if(l === k){
						_this.eq(l).addClass("active");
						_this.eq(l).find('b').css('visibility','hidden');						
					}else{
						_this.eq(l).removeClass("active");
						_this.eq(l).find('b').css('visibility', l < k ? 'visible' : 'hidden');
					}				
				if(k === 6){
					j = 5;
				}else{
					j = k;
				}
				$('#cardwrap1').css('left',j*30+'px');			
				}
			}else{
			//输入其他字符，直接清空
				var _val = this.value;
				this.value = _val.replace(/\D/g,'');
			}
		});	
	});
	 
	










