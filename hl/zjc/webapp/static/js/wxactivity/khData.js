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
            domRefresh: '<div class="dropload-refresh">上拉加载更多</div>',
            domLoad: '<div class="dropload-load"><span class="loading"></span><img src="../../../static/image/wxactivity/jz.gif"></div>',
            domNoData: '<div class="dropload-noData">已无数据</div>'
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
                var page = $("#nowPage").val();
                var totalPage = $("#totalPage").val();
                page++;
                if(page<=totalPage){
                	var path = "getActivityWxGoods.jhtml";
                	$.ajax({
            			type:"GET",
            			dataType:"json",
            			url:path,
            			data:{
            				"page":page,
            				"goods_name": $("#keywords").val()
            			},
            			success:function(data){
            				$("#nowPage").val(data.nowPage);
            				var li = ""
            				$.each(data.list, function(index, g){
            				li += "<li><a href='getActivityGoodsDetails.jhtml?goods_id=" + g.goods_id + "&direct=false'><img src='"+g.original_img +"'><p class='title'>"+g.goods_name+"</p><p class='price'>"+g.market_price+"券</span></p></a></li>"
            				})
            				$("#goods-list").append(li)
            			}
            		}); 
                	flag = true;
                }else {
                	if(flag){
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