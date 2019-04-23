// font js
$(function(){ 
	var deviceWidth = document.documentElement.clientWidth; 
	if(deviceWidth > 1080) deviceWidth = 1080; 
	document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';   
	
 $(".sdp_img").click(function(){ 
			if($(this).find("img").attr("src") == ("../../../static/image/shopgroup/sc.png")){
				$(this).html("<img src='../../../static/image/shopgroup/xin.png' class='sc_pne' alt='' title=''/>");  
			}else{
				$(this).html("<img src='../../../static/image/shopgroup/sc.png' class='sc_pne' alt='' title=''/>");  
			}
		});
	  
 
});

 






