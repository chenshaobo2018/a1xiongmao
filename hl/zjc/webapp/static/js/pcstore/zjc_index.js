/* Filename        zjc_index.js */
/* description    js */
/* Authorship      MiFen */
$(function(){ 
	// 头部搜索框 		
	$(".side_ont_bottom ul li:last").css("margin-right","0"); 
	$(".side_con_ul ul li:last").css("border-bottom","0"); 
	$(".star_tree ul li:last,.star_tree_x ul li:last,.star_tree_y ul li:last").css("margin-right","0"); 
	$(".pr_ul li a:last-child").css("border-right","0"); 
});

$(function(){
	//商品分类
	$('.all-goods .item').hover(function(){
		$(this).addClass('active').find('s').hide();
		$(this).find('.product-wrap').show();
	},function(){
		$(this).removeClass('active').find('s').show();
		$(this).find('.product-wrap').hide();
	});
	// bannar 切换
	$('#demo01').flexslider({
		animation: "slide",
		direction:"horizontal", 
		easing:"swing"
	}); 	
	// 最新上架 
		$('.tabpanel ul li').click(function(){
			$(this).addClass('hit').siblings().removeClass('hit');
			$('.panes>div:eq('+$(this).index()+')').show().siblings().hide();	
		});
 	
 
});
