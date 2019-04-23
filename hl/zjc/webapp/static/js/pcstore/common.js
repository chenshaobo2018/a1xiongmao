/* Filename        common.js */
/* description    js */
/* Authorship      MiFen */
$(function(){ 
// 头部搜索框
	$("#searchSelected").hover(function(){ 
		$("#searchTab").show();
		$(this).addClass("searchOpen");
	}); 
	$('#searchTab').hover(function(){
        $(this).show();
    }, function(){
        $(this).hide();
    });
	$("#searchTab li").hover(function(){
		$(this).addClass("selected");
	},function(){
		$(this).removeClass("selected");
	});	
	$("#searchTab li").click(function(){
		$("#searchSelected").html($(this).html());
		$("#searchTab").hide();
		$("#searchSelected").removeClass("searchOpen");
	});
	$('.head_nav ul li a').on('click',function(){
		$(".head_nav ul li a").removeClass("click_hover");
		$(this).addClass("click_hover");
	}); 
	$(".head_nav ul li:last").css("padding-right","0");
	// 头部悬浮
	  $('li.mainlevel').mousemove(function(){
		$(this).find('ul').slideDown();
	  });
	  $('li.mainlevel').mouseleave(function(){
		$(this).find('ul').stop(true,true).slideUp("fast");
	  });
});
 
 