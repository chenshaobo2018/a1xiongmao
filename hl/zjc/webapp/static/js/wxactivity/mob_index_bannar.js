 
        $(function(){ 
            //滑动幻灯片
            var str = '';
            var num = $("#slider .slider_img").length;
            for(var i=0;i<num;i++){
                if(i == 0){
                    str += '<li class="proint_white"></li>';
                }else{
                    str += '<li class="proint_gray"></li>';
                }
            }
            $('#proint').html(str);
            //滑动时当前图片的小点颜色为白色
            var slider = new zySlide("slider", "H", function(){
                var cur = slider.currentPoint;
                if($("#proint li").hasClass("proint_white")){
                    $("#proint li").removeClass("proint_white").addClass("proint_gray");
                }
                if( $("#proint li").eq(cur).hasClass("proint_gray")){
                    $("#proint li").eq(cur).removeClass("proint_gray").addClass("proint_white");
                }
            },true,function(e){});
            setInterval(function(){
                var k = slider.currentPoint;
                if(k >= (num-1)){
                    slider.moveToPoint(0);
                    var m=0;
                }else{
                    slider.moveToPoint(k+1);
                    var m=k+1;
                }
                if($("#proint li").hasClass("proint_white")){
                    $("#proint li").removeClass("proint_white").addClass("proint_gray");
                }
                if( $("#proint li").eq(m).hasClass("proint_gray")){
                    $("#proint li").eq(m).removeClass("proint_gray").addClass("proint_white");
                }
            },5000);

        });






















 
			