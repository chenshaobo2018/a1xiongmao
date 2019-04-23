<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html base="http" lib="ext">
	<script type="text/javascript">
		var prop = "${prop }";
	</script>
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/cropper.min.css" >
	<link rel="stylesheet" type="text/css" href="${cxt}/static/css/upload/sitelogo.css" >
	<script src="${cxt}/static/zjc/js/bootstrap.min.js"></script>
	<script src="${cxt}/static/js/upload/cropper.js"></script>
	<script src="${cxt}/static/js/upload/sitelogo.js"></script>
	<script src="${cxt}/static/js/upload/html2canvas.min.js"></script>
	<aos:body>
		<div class="modal-content" id='avatar-modal'>
			<form class="avatar-form" id="upload-form" enctype="multipart/form-data">
				<div class="avatar-upload">
					<button class="btn btn-danger"  type="button" onclick="$('input[id=avatarInput]').click();">请选择图片</button>
					<button class="fa fa-save upload-btn" id="picture" type="button"> 上传图片</button>
					<input class="avatar-input hide" id="avatarInput" name="fileUpload" type="file">
				</div>
				<div class="row">
					<div class="avatar-wrapper"></div>
					<div class="avatar-preview preview-lg" id="imageHead" ></div>
				</div>
			</form>
		</div>
	</aos:body>
</aos:html>
<aos:onready>
	<script type="text/javascript">
	//页面初始化
	$(function(){ 
		btnDis();
	});  
	//控制按钮启用和禁用
	function btnDis(){
		if($("#avatarInput").val() == "" || $("#avatarInput").val() == null){
			$("#picture").attr({"disabled":"disabled"});
		}else{
			$("#picture").removeAttr("disabled");
		}
	}
	//对图片进行验证
	$('#avatarInput').on('change', function(e) {
		var filemaxsize = 1024 * 5;//5M
		var target = $(e.target);
		var Size = target[0].files[0].size / 1024;
		if (Size > filemaxsize) {
			AOS.tip('图片过大，请重新选择!');
			$(".avatar-wrapper").childre().remove;
			return false;
		}
		if (!this.files[0].type.match(/image.*/)) {
			AOS.tip('请选择正确的图片!');
			return false;
		} 
		btnDis();
	});
	//图片截取上传
	$("#picture").on("click", function() {
		var img_lg = document.getElementById('imageHead');
		// 截图小的显示框内的内容
		html2canvas(img_lg, {
			allowTaint : true,
			taintTest : false,
			onrendered : function(canvas) {
				canvas.id = "mycanvas";
				//生成base64图片数据
				var dataUrl = canvas.toDataURL("image/jpeg");
				var newImg = document.createElement("img");
				newImg.src = dataUrl;
				imagesAjax(dataUrl)
				$('#${windowsId}', parent.document).hide();
			}
		});
	})

	function imagesAjax(src) {
		var data = {};
		data.img = src;
		data.jid = $('#jid').val();
		$.ajax({
			url : "uploadCardLogo.jhtml?juid=${juid}",
			data : data,
			type : "POST",
			dataType : 'json',
			success : function(data) {
					//为预览图片和图片地址设置值
					$('input[name="${inputName}"]',parent.document).val(data.path);
					$("#${inputName}", parent.document).attr("src",data.filePath);
					//图片预览设置回显
					$(".imgcontainer", parent.document).show();
					$(".imgitem", parent.document).css('height','84px');
				    $(".mcts_span_text", parent.document).css('position','relative');
				    $(".u-add-img-icon,.imgcontainer,", parent.document).css('position','absolute'); 
				    $(".u-add-img-icon,.u-fileInput", parent.document).css('left','160px');
				    $(".mcts_span_text", parent.document).css('left','105px');
				    $(".mcts_span_text", parent.document).css('bottom','65px');
				    $("#big_img", parent.document).prop("src",data.path); 
					$(".imgcontainer").click(function(){
					    $(".big_ime_one,.big_ime_make_one", parent.document).show();  
					    $(".big_ime_make_one", parent.document).click(function(){
							$(".big_ime_one,.big_ime_make_one", parent.document).hide(); 
						})
					})
			}
		}); 
	}
	</script>
</aos:onready>
