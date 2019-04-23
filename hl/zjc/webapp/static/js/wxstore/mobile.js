$(function(){ 
	var deviceWidth = document.documentElement.clientWidth; 
	if(deviceWidth > 1080) deviceWidth = 1080; 
	document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px'; 
function aa(){
	$(".foot li a .foot1 img").attr('src','../static/image/wxstore/foot_03.png');
	$(".foot li a .foot2 img").attr('src','../static/image/wxstore/foot_05.png');
	$(".foot li a .foot3 img").attr('src','../static/image/wxstore/foot_07.png');
	$(".foot li a .foot4 img").attr('src','../static/image/wxstore/foot_09.png');
	$(".foot li a .foot5 img").attr('src','../static/image/wxstore/foot_11.png');
}
$(".foot li a .foot1").click(function(){
	aa();
	$(".foot1 img").attr('src','../static/image/wxstore/foota_03.png');
	$(".foot li div").removeClass("foot-hover");
	$(this).addClass("foot-hover");
});
$(".foot li a .foot2").click(function(){
	aa();
	$(".foot2 img").attr('src','../static/image/wxstore/foota_05.png');
	$(".foot li div").removeClass("foot-hover");
	$(this).addClass("foot-hover");
});
$(".foot li a .foot3").click(function(){
	aa();
	$(".foot3 img").attr('src','../static/image/wxstore/foota_07.png');
	$(".foot li div").removeClass("foot-hover");
	$(this).addClass("foot-hover");
});
$(".foot li a .foot4").click(function(){
	aa();
	$(".foot4 img").attr('src','../static/image/wxstore/foota_09.png');
	$(".foot li div").removeClass("foot-hover");
	$(this).addClass("foot-hover");
});
$(".foot li a .foot5").click(function(){
	aa();
	$(".foot5 img").attr('src','../static/image/wxstore/foota_11.png');
	$(".foot li div").removeClass("foot-hover");
	$(this).addClass("foot-hover");
});


var flag=false;
$(".content .a2 .a2-3 a").click(function(){
	if (flag == false) 
	{
		$(".content .a2 .a2-3 a img").attr("src",'../static/image/wxstore/icon_071.png');
		flag=true;
	} 
	else if(flag == true)
	{
		$(".content .a2 .a2-3 a img").attr("src",'../static/image/wxstore/icon_07.png');
		flag=false;
	}
});
 

 var num = 0;
    function goLeft() {
        //750是根据你给的尺寸，可变的
        if (num == -3200) {
            num = 0;
        }
        num -= 1;
        $(".scroll").css({
            left: num
        })
    } 
    //设置滚动速度
    var timer = setInterval(goLeft, 20);
    //设置鼠标经过时滚动停止
    $(".box").hover(function() {
        clearInterval(timer);
    },function () {
      var  timer = setInterval(goLeft, 20);
    })
	
		   // 购物车加减
/*		$(".add").click(function(){
			alert(231)
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
		});*/
		// 我的订单
		$('.tabPanels_newarrpos ul.tsikl_newarrpos li').click(function(){
			$(this).addClass('hitsd_newarrpos').siblings().removeClass('hitsd_newarrpos');
			$('.panessd_newarrpos>div:eq('+$(this).index()+')').show().siblings().hide();	
		});
})

 






