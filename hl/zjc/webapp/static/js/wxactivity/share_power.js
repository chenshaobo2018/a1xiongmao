$(function(){ 
	// 页面字体
	var deviceWidth = document.documentElement.clientWidth; 
	if(deviceWidth > 1080) deviceWidth = 1080; 
	document.documentElement.style.fontSize = deviceWidth / 10.8+ 'px';  
	// 规则
	$(".sarms_four ul li:last,.sdogli ul li:odd").css("margin-right","0");  
	$(".cont_fd").click(function(){
		$(".ze_make,.ze_make_copy").show(); 
	})
	$(".ze_make_copy").click(function(){
		$(".ze_make,.ze_make_copy").hide(); 
	})
	// 立即分享
	$(".ok_btn").click(function(){
		
		$(".ze_makebtn,.ze_make_copybtn").show();
	})
	$(".ze_make_copybtn,.ze_makebtn").click(function(){
		
		$(".ze_makebtn,.ze_make_copybtn").hide();
	})
	 
})

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
	




