$(function () {

    var itemIndex = 0; 
    var tabLoadEndArray = [false, false, false];
    var tabLenghtArray = [20, 6, 9];
    var tabScroolTopArray = [0, 0, 0];
    var flag = true;
    // dropload
    var dropload = $('.khfxWarp').dropload({
        scrollArea: window,
        domDown: {
            domClass: 'dropload-down',
            domRefresh: '<div class="dropload-refresh" style="font-size:0.45rem;margin:0.2rem 0;text-align:center;">上拉加载更多</div>',
            domLoad: '<div class="dropload-load"  style="margin:0.2rem 0;text-align:center;"><span class="loading"></span><img src="../../../static/image/wxactivity/jz.gif"></div>',
            domNoData: '<div class="dropload-noData" style="font-size:0.45rem;margin:0.2rem 0;text-align:center;">已无数据</div>'
        },
        loadDownFn: function (me) {
            setTimeout(function () {
                if (tabLoadEndArray[itemIndex]) {
                    me.resetload();
                    me.lock();
                    me.noData();
                    me.resetload();
                    return;
                }
                var page = $("#nowPage").val() == undefined? 1 : $("#nowPage").val();
                var totalPage = $("#totalPage").val() == undefined? 1 : $("#totalPage").val();
                var keywords = $("#keywords").val();
                page++;
                if(page<=totalPage){
                	var path = "getWinPrize.jhtml";
                	$.ajax({
            			type:"GET",
            			dataType:"json",
            			url:path,
            			data:{
            				"page":page,
            			},
            			success:function(data){
            				$("#nowPage").val(data.data.nowPage);
            				var li = ""
            				$.each(data.data.list, function(index, g){
            					li += "<li><div class='signlist_two fl'>" + g.share_prize_name +"</div><div class='signlist_tree fl'>" 
            					+ g.win_time + "</div><div class='clear'></div></li>";
            				})
            				$("#goods-list").append(li)
            			}
            		});
                	flag=false;
                }else {
                	if(flag  ){
                		flag = false;
                    	var li = "<li>已经没有数据了</li>";
            			$("#goods-list").append(li);
            			$(".dropload-refresh").hide();
                	}
				}
        me.resetload();
    }, 500);
}
});



    $('.tabHead span').on('click', function () {

        tabScroolTopArray[itemIndex] = $(window).scrollTop();
        var $this = $(this);
        itemIndex = $this.index();
        $(window).scrollTop(tabScroolTopArray[itemIndex]);
        
        $(this).addClass('active').siblings('.tabHead span').removeClass('active');
        $('.tabHead .border').css('left', $(this).offset().left + 'px');
        $('#goods-list').eq(itemIndex).show().siblings('#goods-list').hide();

        if (!tabLoadEndArray[itemIndex]) {
            dropload.unlock();
            dropload.noData(false);
        } else {
            dropload.lock('down');
            dropload.noData();
        }
        dropload.resetload();
    });
});