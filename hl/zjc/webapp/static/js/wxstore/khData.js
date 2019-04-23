$(function () {

    var itemIndex = 0;
    
  var totalPage=20;
  
    var tabLoadEndArray = [false, false, false];
    var tabLenghtArray = [1, 6, 9];
    var tabScroolTopArray = [0, 0, 0];
    
    // dropload
    var dropload = $('.khfxWarp').dropload({
        scrollArea: window,
        domDown: {
            domClass: 'dropload-down',
            domRefresh: '<div class="dropload-refresh">上拉加载更多</div>',
            domLoad: '<div class="dropload-load"><span class="loading"></span><img src="../../../static/image/wxstore/jz.gif"></div>',
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
                        var page = $("#nowPage").text();
                        var totalPage = $("#totalPage").text();
                       /* alert("page="+page);
                        alert("totalPage="+totalPage);*/
                        page++;
                        if(page<=totalPage){
                        	$.ajax({
                    			type:"GET",
                    			dataType:"json",
                    			url:"../../../notokenapi/app/v1/GoodsLists.jhtml",
                    			data:{
                    				"page":page,
                    			},
                    			success:function(data){
                    				$("#nowPage").text(data.data.nowPage);
                    				var li = ""
                    				$.each(data.data.list, function(index, g){
                    				li += "<li><a href='../v1/goodsDetails.jhtml?goods_id=" + g.goods_id + "'><img src='"+g.original_img +"'><p class='title'>"+g.goods_name+"</p><p class='price'>"+g.market_price+"券</span></p></a></li>"
                    				})
                    				$("#goods-list").append(li)
                    			}
                    		}); 
                        }else {
                        	var li = ""
                				$.each(data.data.list, function(index, g){
                				li += "<li>已经没有数据了</li>"
                				})
                				$("#goods-list").append(li)
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