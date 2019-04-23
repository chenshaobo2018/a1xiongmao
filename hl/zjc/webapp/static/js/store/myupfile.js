//获取图片的base64
function getBase64Image(img) {
	var canvas = document.createElement("canvas");
	canvas.width = img.width;
	canvas.height = img.height;

	var ctx = canvas.getContext("2d");
	ctx.drawImage(img, 0, 0, img.width, img.height);

	var dataURL = canvas.toDataURL("image/png");
	return dataURL
}

// 建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
// 图片上传
function base64(url,id) {
	var img = document.createElement('img');
	img.src = url;
	img.onload = function() {
		var data = getBase64Image(img);
		$.ajax({
			type : "POST",
			url : "uploadBase64.jhtml",
			async : true,
			data : {
				img : data
			}
		}).done(function(data) {
			var obj = JSON.parse(data);
			$("input[name='"+ id +"']").val(obj.path);
			console.log();
		}).fail(function() {
			alert("上传文件失败");
		});
	}
}