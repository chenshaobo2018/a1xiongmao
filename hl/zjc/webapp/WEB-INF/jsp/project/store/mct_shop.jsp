<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE>
<aos:html title="商家后台-店铺">
<link href="${cxt}/static/css/store/common.css" rel="stylesheet"
	type="text/css" />
<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet"
	type="text/css" />
<!-- js -->
<script type="text/javascript"
	src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
<script type="text/javascript" src="${cxt}/static/js/store/upfile.js"></script>
<aos:body>
	<div class="mct_container">
		<%@include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
		<!-- mct_main start -->
		<div class="mct_main">
			<div class="mct_detailmain">
				<div class="mctde_cont">
					<div class="mct_shop_tit">
						<p>店铺 - - 店铺基本信息设置</p>
					</div>
					<form id="updateform" action="updateStoreInfo.jhtml" method="post">
					<div class="mct_shopone">
						<div class="ms_one">
							<div class="lab_shops fl">商家/公司名称</div>
							<div class="inpu_div fl">
								<input type="hidden" name="store_id" value="${map.zjcStorePO.store_id }"/>
								<input type="text" class="msc_input" readonly
									value="${map.zjcStorePO.store_name }" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">企业类别</div>
							<div class="inpu_div fl">
							<select value="${map.zjcStorePO.cat_id }" id="province" disabled>
								<c:if test="${map.zjcStorePO.cat_id == null}">
									<option value="" selected="selected">暂无类别</option>
								</c:if>
								<c:forEach var="c" items="${map.categorys }">
									<c:if test="${map.zjcStorePO.cat_id == c.value}">
										<option value="${c.value}" selected="selected">${c.display}</option>
									</c:if>
									<option value="${c.value}">${c.display}</option>
								</c:forEach>
							</select>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">商家/公司简介</div>
							<div class="inpu_div fl">
								<textarea class="msc_input" style="height:100px" name="store_info">${map.zjcStorePO.store_info }</textarea>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">营业执照</div>
							<div class="inpu_div fl">
								<div class="in_divbus fl">
									<div class="imgitem secondImg">
										<div class="u-add-img-icon"></div>
										<input type="hidden" name="business_licence_number_elc" value="${map.zjcStorePO.business_licence_number_elc }"/>
										<input type="file" multiple="" class="u-fileInput" onchange="imgPreview1(this);" />
										<div class="imgcontainer">
											<img src="" class="idImg" alt="upfile" title="upfile" />
										</div>
									</div>
									<!-- 放大图 -->
									<div class="big_ime_one">
										<img id="big_img" src="" alt="upfile" title="upfile" />
									</div>
									<div class="big_ime_make_one"></div>
									<!-- 放大图 -->
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">身份证正面</div>
									<div class="in_divbus fl">
												<div class="imgitem secondImg">
													<div class="u-add-img-icon" style="line-height: 26px;"></div>
													<input type="hidden" name="id_card_img" value="${map.zjcStorePO.id_card_img }"/>
													<input type="file" multiple="" class="u-fileInput" onchange="imgPreview4(this);" />
													<div class="imgcontainer">
														<img src="" class="idImg" alt="upfile" title="upfile" />
													</div>
												</div>
												<div class="big_ime_one">
													<img id="big_img" src="" alt="upfile" title="upfile" />
												</div>
												<div class="big_ime_make_one"></div>
												</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">卫生许可证</div>
							<div class="inpu_div fl">
								<div class="in_divbus">
									<div class="imgitem secondImg">
										<div class="u-add-img-icon"></div>
										<input type="hidden" name="food_hygiene_img" value="${map.zjcStorePO.food_hygiene_img }"/>
										<input type="file" multiple="" class="u-fileInput" onchange="imgPreview2(this);"/>
										<div class="imgcontainer">
											<img src="" class="idImg" alt="upfile" title="upfile" />
										</div>
									</div>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">特殊经营许可证</div>
									<div class="in_divbus fl">
												<div class="imgitem secondImg">
													<div class="u-add-img-icon" style="line-height: 26px;"></div>
													<input type="hidden" name="special_premit_img" value="${map.zjcStorePO.special_premit_img }"/>
													<input type="file" multiple="" class="u-fileInput" onchange="imgPreview5(this);" />
													<div class="imgcontainer">
														<img src="" class="idImg" alt="upfile" title="upfile" />
													</div>
												</div>
												<div class="big_ime_one">
													<img id="big_img" src="" alt="upfile" title="upfile" />
												</div>
												<div class="big_ime_make_one"></div>
											</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="mct_shoptwo">
						<div class="ms_one">
							<div class="lab_shops fl">商家/企业地址</div>
							<div class="inpu_div fl">
								<select value="${map.zjcStorePO.province_id }" id="province" disabled
									style="width: 100px" onchange="getCity()">
									<c:if test="${map.zjcStorePO.province_id == null}">
										<option value="" selected="selected">请选择（省直辖市）</option>
									</c:if>
									<c:forEach var="p" items="${map.provinceList }">
										<c:if test="${map.zjcStorePO.province_id == p.value}">
											<option value="${p.value}" selected="selected">${p.display}</option>
										</c:if>
										<option value="${p.value}">${p.display}</option>
									</c:forEach>
								</select> 
								<select onchange="getArea()" value="${map.zjcStorePO.city_id }" disabled
									id="city" style="width: 100px">
									<c:if test="${map.zjcStorePO.city_id == null}">
										<option value="" selected="selected">请选择（城市）</option>
									</c:if>
									<c:forEach var="c" items="${map.cityList }">
										<c:if test="${map.zjcStorePO.city_id == c.value}">
											<option value="${c.value}" selected="selected">${c.display}</option>
										</c:if>
										<option value="${c.value}">${c.display}</option>
									</c:forEach>
								</select> 
								<select value="${map.zjcStorePO.area_id }" id="area" disabled
									style="width: 100px">
									<c:if test="${map.zjcStorePO.area_id == null}">
										<option value="" selected="selected">请选择（区、县）</option>
									</c:if>
									<c:forEach var="a" items="${map.areaList }">
										<c:if test="${map.zjcStorePO.area_id == a.value}">
											<option value="${a.value}" selected="selected">${a.display}</option>
										</c:if>
										<option value="${a.value}">${a.display}</option>
									</c:forEach>
								</select> <input type="text" class="msc_input_address" readonly style="width:400px;"
									placeholder=" 具体地址" value="${map.zjcStorePO.address_detail }" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">店铺/公司LOGO</div>
									<div class="in_divbus">
												<div class="imgitem secondImg">
													<div class="u-add-img-icon" style="line-height: 26px;"></div>
													<input type="hidden" name="store_logo" value="${map.zjcStorePO.store_logo }"/>
													<input type="file" multiple="" class="u-fileInput" onchange="imgPreview3(this);" />
													<div class="imgcontainer">
														<img src="" class="idImg" alt="upfile" title="upfile" />
													</div>
												</div>
											<span class="mcts_span_text" id="m_img">为避免显示变形，建议7:1</span>
												<!-- 放大图 -->
												<div class="big_ime_one">
													<img id="big_img" src="" alt="upfile" title="upfile" />
												</div>
												<div class="big_ime_make_one"></div>
												<!-- 放大图 -->
											</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">联系人姓名</div>
							<div class="inpu_div fl">
								<input type="text" name="real_name" class="msc_input" placeholder="申请电话" value="${map.real_name }" />
							</div>
							<div class="clear"></div>
						</div>						
						<div class="ms_one">
							<div class="lab_shops fl">负责人电话</div>
							<div class="inpu_div fl">
								<input type="text" name="contacts_phone" class="msc_input"
									value="${map.zjcStorePO.contacts_phone }" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">联系电话</div>
							<div class="inpu_div fl">
								<input type="text" name="office_phone" class="msc_input" placeholder="申请电话"
									value="${map.zjcStorePO.office_phone }" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">电子邮箱</div>
							<div class="inpu_div fl">
								<input type="text" name="contacts_email" class="msc_input"
									value="${map.zjcStorePO.contacts_email }" />
							</div>
							<div class="clear"></div>
						</div>
					</div>
					</form>
					<div class="mct_shoptree">
						<button class="mct_tmshop" onclick="subForm();">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- mct_main end -->
		<!-- mct_footer start -->
		<div class="mct_footer">
			<div class="mctfoot_cent">
				<p>©2017-2025 北京中军创云易物联网科技有限公司 版权所有</p>
			</div>
		</div>
		<!-- mct_footer end -->
	</div>
	<!-- upfile -->
	<script type="text/javascript" src="${cxt}/static/js/store/upfile_vip.js"></script>
	<!-- address -->
	<script type="text/javascript" src="${cxt}/static/js/store/jquery.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${cxt}/static/js/store/city.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${cxt}/static/js/store/myupfile.js" charset="UTF-8"></script>
	<script type="text/javascript">
		//动态生成市、区数据
		function getCity() {
			$.ajax({
				url : '${ctx }/store/findAddr.jhtml',
				type : 'GET',
				dataType : 'json',
				async : false,
				data : {
					parent_id : $("#province").val()
				},
				success : function(data) {
					$("#city").empty();
					console.log("1111:" + $("#city").html());
					for (var i = 0; i < data.length; i++) {
						$("#city").append(
								"<option value='"+data[i].value+"'>"
										+ data[i].display + "</option>");
					}
				}
			});
		}
		//动态生成县数据
		function getArea() {
			jQuery.ajax({
				url : "${ctx }/store/findAddr.jhtml",
				type : 'POST',
				dataType : 'json',
				async : false,
				data : {
					'parent_id' : $("#city").val()
				},
				success : function(data) {
					$("#area").empty();
					for (var i = 0; i < data.length; i++) {
						$("#area").append(
								"<option value='" +data[i].value +"'>"
										+ data[i].display + "</option>");
					}
				}
			});
		}
		$(function() {
			var liList = $(".nav_li li");
			for (var i = 0; i < liList.length; i++) {
				if (liList[i].innerText == '店铺') {
					liList.eq(i).addClass("mct_click");
				}
			}
		});
		
		//公司LOGO图片change的时候调用预览及上传
		function imgPreview3(fileDom){
			var id = "store_logo";
	        //获取文件
	        var file = fileDom.files[0];
	        var url = getObjectURL(file);
	        base64(url,id);
	    }
		
		function imgPreview2(fileDom){
			var id = "food_hygiene_img";
	        //获取文件
	        var file = fileDom.files[0];
	        var url = getObjectURL(file);
	        base64(url,id);
	    }
		//公司LOGO图片change的时候调用预览及上传
		function imgPreview4(fileDom){
			var id = "id_card_img";
	        //获取文件
	        var file = fileDom.files[0];
	        var url = getObjectURL(file);
	        base64(url,id);
	    }
		
		function imgPreview5(fileDom){
			var id = "special_premit_img";
	        //获取文件
	        var file = fileDom.files[0];
	        var url = getObjectURL(file);
	        base64(url,id);
	    }
		
		function imgPreview1(fileDom){
			var id="business_licence_number_elc";
	        //获取文件
	        var file = fileDom.files[0];
	        var url = getObjectURL(file);
	        base64(url,id);
	    }
		
		function subForm(){
			$("#updateform").submit();
		}
		
		$(document).ready(function(){ 
			 var logoObj = $("input[name='store_logo']");
			 if(logoObj.val() != ""){
				logoObj.next().next().find("img").attr('src', logoObj.val());
				logoObj.next().next().show();
				//logoObj.prev().hide();  
				logoObj.prev().show();  
				$(".imgitem").css('height','84px');
			 }
			 var food = $("input[name='food_hygiene_img']");
			 if(food.val() != ""){
				 food.next().next().find("img").attr('src', food.val());
				 food.next().next().show();
				 food.prev().hide();  
				$(".imgitem").css('height','84px');
			 }
			 var business = $("input[name='business_licence_number_elc']");
			 if(business.val() != ""){
				 business.next().next().find("img").attr('src', business.val());
				 business.next().next().show();
				 business.prev().hide();  
				$(".imgitem").css('height','84px');
			 }
			 var id_card_img = $("input[name='id_card_img']");
			 if(id_card_img.val() != ""){
				 id_card_img.next().next().find("img").attr('src', id_card_img.val());
				 id_card_img.next().next().show();
				 id_card_img.prev().hide();  
				$(".imgitem").css('height','84px');
			 }
			 var special_premit_img = $("input[name='special_premit_img']");
			 if(special_premit_img.val() != ""){
				 special_premit_img.next().next().find("img").attr('src', special_premit_img.val());
				 special_premit_img.next().next().show();
				 special_premit_img.prev().hide();  
				$(".imgitem").css('height','84px');
			 }
		 })
		 
	</script>
</aos:body>
</aos:html>
