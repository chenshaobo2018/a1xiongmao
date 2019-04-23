 $(".u-fileInput").each(function(){
		var $this = $(this);
		$this.uploadPreview({imgObj:$this.next().find("img"), callback:uploadHandler});
		//获取url设置img src成功后，回调，隐藏add-icon；显示图片
		function uploadHandler() {
			$this.next().show();
			$this.prev().hide();  
		} 
	});
	// <!-- 判断是否上传了图片及内核判断(查看遮罩层) -->
	$(function(){
			  if($('.idImg').attr('src') == undefined){
					console.log('图片没有src值');
			  }else{
					$('.u-fileInput').attr("disabled",false); 
					$(".imgcontainer").click(function(){ 
						// 判断ie内核
						if($.browser.msie){
							var imgSrc = $(this).get(0).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src; 
						}else{
							var imgSrc = $(this).find("img").attr("src");
							//console.log($(this).find("img").attr("src"));	 
						}  
					   // <!-- 显示遮罩层放大图 -->
					   $(".big_ime_one,.big_ime_make_one").show();  
					   $("#big_img").prop("src",imgSrc); 
					})
				}
				// <!-- 隐藏遮罩层放大图 -->
				$(".big_ime_make_one").click(function(){
					$(".big_ime_one,.big_ime_make_one").hide(); 
				})
			});	