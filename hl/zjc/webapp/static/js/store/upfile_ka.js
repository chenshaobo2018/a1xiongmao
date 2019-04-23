	$(function() {
		var objUrl;
		var img_html;
		$("#myFile").change(function(e) {
			var img_div = $(".img_div");
			var filepath = $("input[name='myFile']").val();
			var data = new FormData();
            $.each(e.target.files[0],function(i,file){
            	data.append('file', file);
            });
			for(var i = 0; i < this.files.length; i++) {
				objUrl = getObjectURL(this.files[i]);
				var extStart = filepath.lastIndexOf(".");
				var ext = filepath.substring(extStart, filepath.length).toUpperCase(); 
				if(ext != ".PNG" && ext != ".JPG") {
					$(".shade").fadeIn(500);
					$(".text_span").text("图片限于png,jpg格式");
					this.value = "";
					$(".img_div").html("");
					return false;
				} else {
					/*
					 若规则全部通过则在此提交url到后台数据库
					 * */
					
					 $.ajax({
						url : "uploadCardLogo.jhtml",
						data : data,
						type : "POST",
						processData : false, // 告诉jQuery不要去处理发送的数据
						contentType : false,// 告诉jQuery不要去设置Content-Type请求头
						dataType : 'json',
						success : function(data) {
							
						}
					}); 
					
					img_html = "<div class='isImg'><img src='" + objUrl + "' onclick='javascript:lookBigImg(this)' style='height: 100%; width: 100%;' /><button class='removeBtn' onclick='javascript:removeImg(this)'>x</button></div>";
					img_div.append(img_html);
					$(".a-upload").hide();
				}
			} 
			var file_size = 0;
			var all_size = 0;
			for(j = 0; j < this.files.length; j++) {
				file_size = this.files[j].size;
				all_size = all_size + this.files[j].size;
				var size = all_size / 1024;
				if(size > 500) {
					$(".shade").fadeIn(500);
					$(".text_span").text("上传的图片大小不能超过100k！");
					this.value = "";
					$(".img_div").html("");
					return false;
				}
			} 
			return true;
		}); 
		function getObjectURL(file) {
			var url = null;
			if(window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if(window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if(window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			//console.log(url);
			return url;
		}
	}); 
	function removeImg(r){
		$(r).parent().remove();
			$(".a-upload").show();
	} 
	function lookBigImg(b){
		$(".shadeImg").fadeIn(500);
		$(".showImg").attr("src",$(b).attr("src"))
	} 
	function closeShade(){
		$(".shade").fadeOut(500);
	} 
	function closeShadeImg(){
		$(".shadeImg").fadeOut(500);
	}