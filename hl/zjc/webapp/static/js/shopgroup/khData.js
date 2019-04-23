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
            domRefresh: '',
            domLoad: '<div class="dropload-load center"><span class="loading"></span><img src="../../../static/image/shopgroup/jz.gif"></div>',
            domNoData: '<div class="dropload-noData"> </div>'
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
                	var path = "../../../notokenapi/app/v1/getPinTuanWxGoods.jhtml";
                	$.ajax({
            			type:"GET",
            			dataType:"json",
            			url:path,
            			data:{
            				"page":page
            			},
            			success:function(data){
            				$("#nowPage").val(data.data.nowPage);
            				var li = ""
            				$.each(data.data.list, function(index, g){
            					var new_goods_name = "";
            					if(g.goods_name.length>10){
            						new_goods_name = g.goods_name.substring(0,10)+"...";
            					}else{
            						new_goods_name = g.goods_name;
            					}
	            				li += '' 
	                                + '      <li>'
	        						+'       <a href="initGoodsDetail.jhtml?goods_id=' + g.goods_id + '">'
	                                + '        <img src="'+ g.original_img + '">'
	        						+' <div class="news_on">新品</div>'
	                                + '        <p class="title">' + new_goods_name + '</p> '
	                                + '         <div class="pce_sum clearfix">'
	                                + '         <p class="price fl">' + g.shop_group_market_price + '<span>券</span></p>'
	                                + '         </div>'
	        						+'<button class="btn_pt">立即拼团</button>'
	                                + '        </a>' 
	        						
	                                + '      </li>'  
            				})
	            			$('#goods-list').eq(itemIndex).append(li);
            			}
            		}); 
                };
                me.resetload();
            }, 2000);
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
