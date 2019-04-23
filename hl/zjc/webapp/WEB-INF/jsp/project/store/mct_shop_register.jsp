<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="商家注册">
<link href="${cxt}/static/css/store/common.css" rel="stylesheet" type="text/css" />
<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet" type="text/css" />
<!-- js -->
<script type="text/javascript" src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
<script src="${cxt}/static/js/store/upfile.js"></script>
<!-- upfile -->
<script src="${cxt}/static/js/store/upfile_vip.js"></script>
<!-- address	 -->
<script type="text/javascript" src="${cxt}/static/js/store/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cxt}/static/js/store/city.min.js" charset="UTF-8"></script>
<aos:body>
	<div class="mct_container">
		<!-- mct_header start -->
		<%@ include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
		<!-- mct_header end -->
		<!-- mct_main start -->
		<div class="mct_main">
			<div class="mct_detailmain">
				<div class="mctde_cont">
					<div class="mct_shop_tit">
						<p>店铺 - - 店铺基本信息设置</p>
					</div>
					<div class="mct_shopone">
						<div class="ms_one">
							<div class="lab_shops fl">商家/公司名称</div>
							<div class="inpu_div fl">
								<input type="text" class="msc_input" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">企业类别</div>
							<div class="inpu_div fl">
								<input type="text" class="msc_input" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">商家/公司简介</div>
							<div class="inpu_div fl">
								<input type="text" class="msc_input" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">营业执照</div>
							<div class="inpu_div fl">
								<div class="in_divbus">
									<div class="imgitem secondImg">
										<div class="u-add-img-icon"></div>
										<input id="fileImage2" type="file" multiple=""
											class="u-fileInput" />
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
							<div class="lab_shops fl">开店承诺书</div>
							<div class="inpu_div fl">
								<div class="in_divbus">
									<div class="imgitem secondImg">
										<div class="u-add-img-icon"></div>
										<input id="fileImage2" type="file" multiple=""
											class="u-fileInput" />
										<div class="imgcontainer">
											<img src="" class="idImg" alt="upfile" title="upfile" />
										</div>
									</div>
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="mct_shoptwo">
						<div class="ms_one">
							<div class="lab_shops fl">商家/企业地址</div>
							<div class="inpu_div fl">
								<form action="" name="form1">
									<div class="infolist">
										<div class="liststyle">
											<div id="Province">
												<i>请选择（省直辖市）</i>
												<ul>
													<li><a href="javascript:void(0)" alt="请选择（省直辖市）">请选择（省直辖市）</a></li>
												</ul>
												<div class="clear"></div>
												<input type="hidden" name="cho_Province" value="请选择（省直辖市）">
											</div>
											<div id="City">
												<i>请选择（城市）</i>
												<ul>
													<li><a href="javascript:void(0)" alt="请选择（城市）">请选择（城市）</a></li>
												</ul>
												<div class="clear"></div>
												<input type="hidden" name="cho_City" value="请选择（城市）">
											</div>
											<div id="Area">
												<i>请选择（市区县）</i>
												<ul>
													<li><a href="javascript:void(0)" alt="请选择（市区县）">请选择（市区县）</a></li>
												</ul>
												<div class="clear"></div>
												<input type="hidden" name="cho_Area" value="请选择（市区县）">
											</div>
											<div class="clear"></div>
										</div>
									</div>
									<input type="text" class="msc_input_address"
										placeholder=" 具体地址" />
								</form>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">店铺/公司LOGO</div>
							<div class="inpu_div fl">
								<div class="in_divbus">
									<div class="imgitem secondImg">
										<div class="u-add-img-icons"></div>
										<input id="fileImage2" type="file" multiple=""
											class="u-fileInput" />
										<div class="imgcontainer">
											<img src="" class="idImg" alt="upfile" title="upfile" />
										</div>
									</div>
									<p class="lab_text">要求比例：7：2</p>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">负责人电话</div>
							<div class="inpu_div fl">
								<input type="text" class="msc_input" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">办公电话</div>
							<div class="inpu_div fl">
								<input type="text" class="msc_input" />
							</div>
							<div class="clear"></div>
						</div>
						<div class="ms_one">
							<div class="lab_shops fl">电子邮箱</div>
							<div class="inpu_div fl">
								<input type="text" class="msc_input" />
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="mct_shoptree">
						<button class="mct_tmshop">提交</button>
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
</aos:body>
</aos:html>