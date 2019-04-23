/* Filename        zjc_detail.js */
/* description    js */
/* Authorship      MiFen */
$(function(){  

		$(".shop_pare").click(function(){ 
			if($(this).parent().find(".shop_pare img").attr("src") == ("images/shop_index/fff.png")){
				$(this).html("<img src='images/shop_index/ye.png' class='praise-img' alt='' title='' width='13' height='13' />");  
				// $(this).parent().find(".praise-txt").hide(); 
				// $(this).parent().find(".praise-txt1").show();  		 
			}else{
				$(this).html("<img src='images/shop_index/fff.png' class='praise-img' alt='' title='' width='13' height='13' />"); 
				// $(this).parent().find(".praise-txt").show();  
				// $(this).parent().find(".praise-txt1").hide(); 
			}
		}); 
	$('#demo01').flexslider({
		animation: "slide",
		direction:"horizontal", 
		easing:"swing"
	}); 
}); 
	
	
   
   
   
   
   
		