<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="商家后台-商品详情">
<!-- common css and zjc_index css-->
<link href="${cxt}/static/css/store/common.css" rel="stylesheet" type="text/css" />
<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${cxt}/static/js/store/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
<!-- 文本编辑器引入 -->
<script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${cxt}/static/UEditer/lang/zh-cn/zh-cn.js"></script>
<aos:body>
<div class="mct_container">
	<%@ include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
	<!-- mct_main start -->
	<div class="mct_main">
		<div class="mct_detailmain_add">
			<div class="mctde_cont_add">
				<div class="mct_list_tit">
					<p>商品--商品详情</p>
				</div>
				<form id="updateform">
				<div class="mctde_one">
					<div class="mct_rad">
						<div class="tabs_ul_mctone">
							<ul class="">
								<li class="hit_ul_mctone">商品详情</li>
								<li class="" id="spxq">商品详情</li>
							</ul>
							<div class="clear"></div>
						</div>
						<div class="panes_ul_mctone">
							<!-- 最新上架 -->
							<div class="pane_ul_mctone" style="display: block;">
								<div class="mct_one_tab">
									<div class="mct_text_one">									
										<div class="mst_lab fl">
											<label class="mcttez_label">商品名称</label>
										</div>
										 <div class="mst_labr fl">
											<input type="hidden" value="${goods.goods_id }" name="goods_id" id="goods_name">
											<input type="text" class="mfzo" value="${goods.goods_name }" name="goods_name" id="goods_name" readonly="readonly">
											<span class="mcts_span" id="m_name" style="color:red"></span>										
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_two">							
										<div class="mst_lab fl">
											<label class="mcttez_label">关&nbsp;&nbsp;键&nbsp;&nbsp;词</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" name="keywords" id="keywords" value="${goods.keywords }" placeholder="多个关键词建议用“空格”分隔，总长度超过20个字符" style="width:395px;">
											<span class="mcts_span" id="m_keywords"  style="color:red"></span>										
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_two">						
										<div class="mst_lab fl">
										<label class="mcttez_label_two">商品分类</label> 
										</div>
										 <div class="mst_labr fl">
										<select class="mctdetail_sel" disabled="disabled" id="cat_id">
											<option value="">所有分类</option>
											<c:forEach var="cat" items="${sessionScope.categorys }">
												<c:if test="${cat.value==goods.cat_id }">
													<option value="${cat.value }" selected="selected">${cat.display }</option>
												</c:if>
												<c:if test="${cat.value!=goods.cat_id }">
													<option value="${cat.value }">${cat.display }</option>
												</c:if>
											</c:forEach>
										</select>
										<select id="cat_id2" name="cat_id2" class="mctdetail_sel" disabled="disabled" id="cat_id2">
											<option value="">请选择（二级分类）</option>
										</select>									
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_tree">				
										<div class="mst_lab fl">
										<label class="mcttez_label">商品货号</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.goods_sn }" name="goods_id" id="goods_sn" style="width:395px;" disabled="disabled">
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_tree">													
									  <div class="mst_lab fl">		
										<label class="mcttez_label">支付方式</label>
										</div>
										<div class="mst_labr fl">
											<select class="mctdetail_sel" name="commodity_categories" id="pay_type"  style="width:395px;" onchange="changePayType()">
												<c:if test="${goods.commodity_categories==1 }">
													<option value="1" selected="selected">在线支付</option>
													<option value="2">易支付</option>
													<option value="3">滴支付</option>
												</c:if>
												<c:if test="${goods.commodity_categories==2 }">
													<option value="1">在线支付</option>
													<option value="2" selected="selected">易支付</option>
													<option value="3">滴支付</option>
												</c:if>
												<c:if test="${goods.commodity_categories==3 }">
													<option value="1">在线支付</option>
													<option value="2">易支付</option>
													<option value="3" selected="selected">滴支付</option>
												</c:if>
											</select>
											<span style="color: #666666 ">（请选择需要的付款方式）</span>
										</div>
									  <div class="clear"></div>									
									</div>
									
									<c:if test="${goods.commodity_categories==1 }">
										<div class="mct_text_tree cash">		
											<div class="mst_lab fl">
											<label class="mcttez_label">在线支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.shop_price }" name="shop_price" id="shop_price" style="width:395px;"> 
											<span class="mcts_span" id="m_shop" style="color: red"></span>	 
											 </div>										 
											 <div class="clear"></div>									
										</div>
										<div class="mct_text_tree market" style="display:none">
											<div class="mst_lab fl">
											<label class="mcttez_label" >易支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.market_price }" name="market_price" id="market_price" style="width:395px;"> 
											<span class="mcts_span" id="m_market" style="color: red"></span>
											 </div>										 
											 <div class="clear"></div>					
										</div>
										<div class="mct_text_tree drop" style="display:none">
											<div class="mst_lab fl">
											<label class="mcttez_label" >滴支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.drops }" name="drops" id="drops" style="width:395px;"> 
											<span class="mcts_span" id="m_drops" style="color: red"></span>
											 </div>										 
											 <div class="clear"></div>					
										</div>
									</c:if>
									<c:if test="${goods.commodity_categories==2 }">
										<div class="mct_text_tree cash" style="display:none">		
											<div class="mst_lab fl">
											<label class="mcttez_label">在线支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.shop_price }" name="shop_price" id="shop_price" style="width:395px;"> 
											<span class="mcts_span" id="m_shop" style="color: red"></span>	 
											 </div>										 
											 <div class="clear"></div>									
										</div>
										<div class="mct_text_tree market">
											<div class="mst_lab fl">
											<label class="mcttez_label" >易支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.market_price }" name="market_price" id="market_price" style="width:395px;"> 
											<span class="mcts_span" id="m_market" style="color: red"></span>
											 </div>										 
											 <div class="clear"></div>					
										</div>
										<div class="mct_text_tree drop" style="display:none">
											<div class="mst_lab fl">
											<label class="mcttez_label" >滴支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.drops }" name="drops" id="drops" style="width:395px;"> 
											<span class="mcts_span" id="m_drops" style="color: red"></span>
											 </div>										 
											 <div class="clear"></div>					
										</div>
									</c:if>
									<c:if test="${goods.commodity_categories==3 }">
											<div class="mct_text_tree cash" style="display:none">		
											<div class="mst_lab fl">
											<label class="mcttez_label">在线支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.shop_price }" name="shop_price" id="shop_price" style="width:395px;"> 
											<span class="mcts_span" id="m_shop" style="color: red"></span>	 
											 </div>										 
											 <div class="clear"></div>									
										</div>
										<div class="mct_text_tree market" style="display:none">
											<div class="mst_lab fl">
											<label class="mcttez_label" >易支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.market_price }" name="market_price" id="market_price" style="width:395px;"> 
											<span class="mcts_span" id="m_market" style="color: red"></span>
											 </div>										 
											 <div class="clear"></div>					
										</div>
										<div class="mct_text_tree drop" >
											<div class="mst_lab fl">
											<label class="mcttez_label" >滴支付</label>
											</div>
											 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" value="${goods.drops }" name="drops" id="drops" style="width:395px;"> 
											<span class="mcts_span" id="m_drops" style="color: red"></span>
											 </div>										 
											 <div class="clear"></div>					
										</div>
									</c:if>
									
									
									
							<!-- 		<div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label">混合支付</label>
										</div>
										 <div class="mst_labr fl">
											<span class="mcts_sp">
												<input type="radio" name="is_mixed" value="0" class="mcts_rad">否
												<input type="radio" name="is_mixed" value="1" class="mcts_rad">是
											</span>
										 </div>										 
										 <div class="clear"></div>					
									</div> -->
									<%-- <div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label">返券权重</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" name="goods_weight" id="goods_weight" value="${goods.goods_weight }" style="width:395px;" disabled="disabled"> 
											<span class="mcts_span" id="m_wet"></span>
										 </div>										 
										 <div class="clear"></div>	
									</div>	 --%>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label">特殊商品</label>
										</div>
										 <div class="mst_labr fl">
											<span class="mcts_sp">
												<input type="radio" name="is_car" value="0" class="mcts_rad">否
												<input type="radio" name="is_car" value="1" class="mcts_rad">是
												<br> 
											</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label">商品类型</label>
										</div>
										 <div class="mst_labr fl">
											<select class="input_sid" name="goods_type" onchange="getSpec()" id="goods_type">
												<option value="">选择类型</option>
												<c:forEach var="gp" items="${goodsTypePOs }">
													<c:if test="${gp.id==goods.goods_type }">
													<option value="${gp.id }" selected="selected">${gp.name }</option>
													</c:if>
													<c:if test="${gp.id!=goods.goods_type }">
													<option value="${gp.id }">${gp.name }</option>
													</c:if>
												</c:forEach>
											</select>
											<span class="mcts_span" id="m_cat"></span>
										 </div>										 
										 <div class="clear"></div>	
										</div>
										<!-- <div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label fl">商品规格</label>
										</div>
										 <div class="mst_labr fl">
											<input type="hidden" name="guige_key" id="guige_key">
											<input type="hidden" name="guige_name" id="guige_name">
												<div class="guge fl" >
												   <ul id="guiges"></ul> 
												</div>
											<div class="clear"></div>
										 </div>										 
										 <div class="clear"></div>	
										</div> -->
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">库存数量</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" value="${goods.store_count }" name="store_count" id="store_count" style="width:395px;">
											<span class="mcts_span" id="m_count" style="color: red"></span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">限购次数</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" value="${goods.today_limit_times }" name="today_limit_times" id="today_limit_times" style="width:395px;">
											<span class="mcts_span" id="m_times" style="color: red">必填</span>
											<span style="color: #666666 ">（每天每人限购次数）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">限购数量</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" value="${goods.today_limit_nums }" name="today_limit_nums" id="today_limit_nums" style="width:395px;">
											<span class="mcts_span" id="m_nums" style="color: red">必填</span>
											<span style="color: #666666 ">（每人每次限购数量）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">现金价</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" name="shop_group_price" id="shop_group_price" style="width:395px;" placeholder="必须为正整数" value="${goods.shop_group_price }">
											<span style="color: #666666 ">（参与拼团产品必填,纯现金商品价格字段）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">拼团人数</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" name="shop_group_person" id="shop_group_person" style="width:395px;" placeholder="必须为正整数" value="${goods.shop_group_person }">
											<span style="color: #666666 ">（参与拼团产品必填，要求拼团达到的人数）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">券价格</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" name="shop_group_market_price" id="shop_group_market_price" style="width:395px;" placeholder="必须为正整数" value="${goods.shop_group_market_price }">
											<span style="color: #666666 ">（参与拼团产品必填，纯易物券商品价格字段）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">发货时间</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" value="${goods.expect_delivery }" name="expect_delivery" id="expect_delivery" style="width:395px;">
											<span class="mcts_span" id="m_expect" style="color: red">必填</span>
											<span style="color: #666666 ">（预计多少天内发货）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label">是否包邮</label>
										</div>
										 <div class="mst_labr fl">
											<span class="mcts_sp">
												<input type="radio" name="is_free_shipping" value="0" class="mcts_rad">否
												<input type="radio" name="is_free_shipping" value="1" class="mcts_rad">是
												<br> 
											</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">邮费</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text mct_p" value="${goods.postage }" name="postage" id="postage" style="width:395px;">
											<span class="mcts_span" id="m_postage" style="color: red"></span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_two">									
										<div class="mst_lab fl">
											<label class="mcttez_label">型号/规格</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mfzo" value="${goods.ts }" name="ts" id="ts" >
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_two">									
										<div class="mst_lab fl">
											<label class="mcttez_label">联系电话</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mfzo" value="${goods.contract_phone }" name="contract_phone" id="contract_phone" >
											<span class="mcts_span" id="m_phone" style="color:red">必填</span>										
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_two">									
										<div class="mst_lab fl">
											<label class="mcttez_label">备注</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mfzo" value="${goods.remark }" name="remark" id="remark" >
											<span class="mcts_span" id="m_name" style="color:red"></span>										
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label fl">商品图片</label>
										</div>
										<div class="mst_labr fl">
											<div class="in_divbus ">
												<div class="imgitem secondImg">
													<div class="u-add-img-icon" style="line-height: 26px;left:160px" onclick="showdiv();"></div>
													<input type="hidden" name="original_img" value="${goods.original_img }" id="img"/>
													<!-- <input type="file" multiple="" class="u-fileInput" onchange="imgPreview(this);" /> -->
													<div class="imgcontainer">
														<img src="" id="original_img" class="idImg" alt="upfile" title="upfile" />
													</div>
												</div>
												<!-- 放大图 -->
												<div class="big_ime_one">
													<img id="big_img" src="" alt="upfile" title="upfile" />
												</div>
												<div class="big_ime_make_one"></div>
												<!-- 放大图 -->
											</div>
											<span class="mcts_span_text" id="m_img"  style="color:red"></span>
											<!-- <div class="clear"></div>	 -->
										</div>										 
										<div class="clear"></div>	
									</div>
							<%-- 		<div class="mct_text_tree">
										<div class="mst_lab fl">
											<label class="mcttez_label">商品品牌</label>
										</div>
										 <div class="mst_labr fl">
											<select class="input_sid" name="brand_id" disabled="disabled">
												<c:forEach var="b" items="${brandPOs }">
													<c:if test="${b.id==goods.brand_id }">
														<option value="${b.id }">${b.name }</option>
													</c:if>
												</c:forEach>
											</select>
										 </div>										 
										 <div class="clear"></div>	
										</div> --%>	
								</div>						
							<div class="mctde_five">
								<button class="mct_tm" onclick="subForm1()" type="button">保存</button>
								<button class="mct_tm" onclick="cancle();" type="button">返回</button>
							</div>
							</div>
							<div class="pane_ul_mctone">
							<div class="panul_one">
								     <div class="panulone_one pant_text fl">
								        <p>生产地址：<input type="text" placeholder="选填" name="place_of_production" value="${goods.place_of_production }"/></p>
								        <p>商品重量：<input type="text" placeholder="选填" name="weight" value="${goods.weight }"/></p>
								     </div>
								     <div class="panulone_two pant_text fl">
								        <p>生产厂家：<input type="text" placeholder="选填" name="manufacturer" value="${goods.manufacturer }"/></p>
								        <p>商品特性：<input type="text" placeholder="选填" name="goods_features" value="${goods.goods_features }"/></p>
								      </div>
								     <div class="panulone_tre pant_text fl">
								        <p>包装情况：<input type="text" placeholder="选填" name="packing_info" value="${goods.packing_info }"/></p>
								      <%--   <p>联系电话：<input type="text" placeholder="选填" name="contract_phone" value="${goods.contract_phone }"/></p> --%>
								     </div>
								     <div class="clear"></div>
								  </div>								
								<div class="mctde_two">
									<!-- <div class="mctde_two_tit">商品详情</div> -->
									<div class="mctde_two_cont">
										<div class="mop_one">
											<div id="div_update_ueditor">
												<script id="update_editor" type="text/plain"></script>
											</div>
											<div class="clear"></div>
										</div>
									</div>
								</div>
																
							<div class="mctde_five">
								<button class="mct_tm" onclick="subForm2()" type="button">提交</button>
								<button class="mct_tm" onclick="cancle();" type="button">返回</button>
							</div>
							</div>	
						</div>
					</div>
				</div>
				</form>
				</div>
			</div>
		</div>
		<div class="suc_on">
			<p></p>
		    <button class="btn_addok">确认</button>
		</div>
		<div class="suc_on_make"></div>
		<div class="mct_footer">
			<div class="mctfoot_cent">
				<p>©2017-2025 北京中军创云易物联网科技有限公司 版权所有</p>
			</div>
		</div>
	</div>
	<!-- 商品图片上传 -->
	<div id="picture-upload" style="display: none; width:100%; height:100%;position:fixed; top :0px;background-color:#000;background: rgba(0, 0, 0, 0.5);text-align:center;">
		<div  style="width:700px; height: 38px;margin-left: 35%; margin-top: 8%; background-color: #F3F3F3;position: absolute; text-align:left; ">
			<label class="mcttez_label" style="line-height: 40px; margin-left: 10px;">上传图片</label>
			<button onclick="closediv();" style="padding: 10px; float: right;">关闭</button>
		</div>
		<div  style="width:700px; height:450px;margin-left: 35%; margin-top: 10%; background-color: white;" >
			<iframe style="margin:0px;   width:700px; height:450px;border: 0px " src="newGoodsUpload.jhtml?inputName=original_img&windowsId=picture-upload&paramkey=goods_picture_prop&juid=${juid}"></iframe>
		</div>
	</div>
	<script type="text/javascript" src="${cxt}/static/js/store/upfile_vip.js"></script>
	<script type="text/javascript" src="${cxt}/static/js/store/myupfile.js" charset="UTF-8"></script>
	<script type="text/javascript">
	
	  $("input[name=is_free_shipping][value='0']").click(function(){//不包邮 
		  $(".mct_p").val("0");
		  $(".mct_p").removeAttr("disabled");
	  });
	  $("input[name=is_free_shipping][value='1']").click(function(){ //包邮
		  $(".mct_p").val("0");
		  $(".mct_p").attr("disabled","false");
	  });
	  
	  var shop_price = $("#shop_price").val();
  	   if(shop_price == ''||shop_price==null){
  			shop_price=0;
  		}
	   var market_price = $("#market_price").val();
	   	if(market_price == ''||market_price==null){
  			market_price=0;
  		}
  	   var drops = $("#drops").val();
  	   if(drops == ''||drops==null){
  			drops=0;
  	   }
	  
	  
	$(function(){
		var isFree= '${goods.is_free_shipping}';
		 if( isFree == 1){
			  $(".mct_p").val("0");
			  $(".mct_p").attr("disabled","false");
		 };
		var goods_state_1 = '${goods.goods_state_1}';
		var goods_state_2 = '${goods.goods_state_2}';
		if(goods_state_1 == '2'){//初审失败
			var aduit_info = '${goods.aduit_info}';
			$(".suc_on p").text('中军创提醒您，您的商品审核失败原因：'+aduit_info);
			$(".suc_on,.suc_on_make").show();
			$(".btn_addok").click(function(){
				$(".suc_on,.suc_on_make").hide();				
			})
		}else if(goods_state_2 == '2'){//终审失败
			var admin_aduit_info = '${goods.admin_aduit_info}';
			$(".suc_on p").text('中军创提醒您，您的商品审核失败原因：'+admin_aduit_info);
			$(".suc_on,.suc_on_make").show();
			$(".btn_addok").click(function(){
				$(".suc_on,.suc_on_make").hide();				
			})
		} 
		
	})
	   //实例化编辑器
	    var updateUE = UE.getEditor('update_editor',{
	    	zIndex:999
	    });
	    updateUE.addListener('ready',function(editor){
	   		updateUE.setContent('${goods.goods_content}');
		});
	   function subForm1(){
		 //验证输入
		   var i = check();
		   setGuiges();
		   if(i == true){
			   $("#spxq").click();
		   }else{
			   alert_msg("请检查您的输入。"); 
		   }
	   }
	   function subForm2(){
		 //验证输入
		   var i = check();
		   setGuiges();
		   if(i == true){
			   $.ajax({
				   url:"updateGoods.jhtml",
				   type:"POST",
				   data:$("#updateform").serialize(),
				   success:function(data){
					   alert_msg(data.msg);
					   if(data.code == 1){					  
						   window.location.href="toProductPage.jhtml";
					   }
				   }
			   });
		   }else{
			   alert_msg("请检查您的输入。"); 
		   }
	   }
	   
	   function imglistUp(id){
		   $("input[name='"+id+"']").val("");
		   var imgList = $(".img-box .up-img");
		   for(var i=0;i < imgList.length;i++){
			   base(imgList[i].currentSrc,id);
		   }
		   alert("上传成功。");
	   }
	   
	  
	   
	   
		function changePayType(){
		   var pay_type = $("#pay_type").val();
		   if(pay_type == 1){//在线支付
			   $(".cash").show();
			   $(".market").hide();
			   $(".drop").hide();
			   $("#shop_price").val(shop_price)
			   $("#market_price").val('0');
			   $("#drops").val('0');
			   
		   } else if(pay_type==2){//易支付
			   $(".market").show();
			   $(".cash").hide();
			   $(".drop").hide();
			   $("#shop_price").val('0');
			   $("#drops").val('0');
			   $("#market_price").val(market_price);
		   } else if(pay_type==3){//滴支付
			   $(".drop").show();
			   $(".market").hide();
			   $(".cash").hide();
			   $("#shop_price").val('0');
			   $("#market_price").val('0');
			   $("#drops").val(drops);
		   }
	   }
	   
	   
	   //动态获取二级分类
	   $(function(){
		   var logoObj = $("input[name='original_img']");
			 if(logoObj.val() != ""){
				$("#original_img").attr("src",logoObj.val());
				//图片预览设置回显
				$(".imgcontainer").show();
				$(".imgitem").css('height','84px');
			    $(".mcts_span_text").css('position','relative');
			    var goods_state_1 = '${goods.goods_state_1}';
				var goods_state_2 = '${goods.goods_state_2}';
				console.log("goods_state_1:"+goods_state_1);
				console.log("goods_state_2:"+goods_state_2);
				if(goods_state_1 == '2'||goods_state_2 == '2'||goods_state_2 == '3'){
					 $(".u-add-img-icon,.imgcontainer,").css('position','absolute'); 
					 $(".u-add-img-icon,.u-fileInput").css('left','160px');
				}else {
					 $(".u-add-img-icon,.imgcontainer,").css('position','absolute'); 
					 $(".u-add-img-icon,.u-fileInput").css('left','60px');
				}
			    $(".mcts_span_text").css('left','105px');
			    $(".mcts_span_text").css('bottom','65px');
			    $("#big_img").prop("src",logoObj.val()); 
				$(".imgcontainer").click(function(){
				    $(".big_ime_one,.big_ime_make_one").show();  
				    $(".big_ime_make_one").click(function(){
						$(".big_ime_one,.big_ime_make_one").hide(); 
					})
				})
			}
		   jQuery.ajax({
				url : "listGoodsCategory2.jhtml",
				type : 'POST',
				dataType : 'json',
				async : false,
				data : {
					'id' : ${goods.cat_id }
				},
				success : function(data) {
					var cat2 = ${goods.cat_id2 };
					$("#cat_id2").empty();
					$("#cat_id2").append('<option value="" selected="selected">请选择（二级分类）</option>');
					for (var i = 0; i < data.length; i++) {
						if(cat2==data[i].value){
							$("#cat_id2").append(
									"<option value='" +data[i].value +"' selected='selected'>"
											+ data[i].display + "</option>");
						}else{
							$("#cat_id2").append(
									"<option value='" +data[i].value +"'>"
											+ data[i].display + "</option>");
						}
					}
				}
			});
	   })
	   
	   //radio渲染
	   $(document).ready(function(){
		   var rg = new Array();
		   var is_car = '${goods.is_car}';
		   rg = $.find("input[type='radio'][name='is_car']");
		   $.each($(rg), function(index, v){
			   if($(this).val() == is_car){
				   $(this).attr("checked",true);
			   }
		   });
		   
		   var is_mixed = '${goods.is_mixed}';
		   rg = $.find("input[type='radio'][name='is_mixed']");
		   $.each($(rg), function(index, v){
			   if($(this).val() == is_mixed){
				   $(this).attr("checked",true);
			   }
		   });
		   
		   var is_free_shipping = '${goods.is_free_shipping}';
		   rg = $.find("input[type='radio'][name='is_free_shipping']");
		   $.each($(rg), function(index, v){
			   if($(this).val() == is_free_shipping){
				   $(this).attr("checked",true);
			   }
		   });
		   
		   
	   });
	   
	   $(function(){
		   $('.add-img').click(function(){
			   return  $("#file").click();
		   });
	   });
	   
	 //动态获取规格
	   function getSpec() {
				jQuery.ajax({
					url : "listGoodsSpec.jhtml",
					type : 'POST',
					dataType : 'json',
					async : false,
					data : {
						'type_id' : $("#goods_type").val()
					},
					success : function(data) {
						$("#guiges").empty();
						data = divide(data);
						$.each(data, function(index1, v1){
							var li = "";
							$.each(v1.data, function(index2, v2){
								li += "<li onclick='can_click(this)' class='flag' value='"+v2.item_id+"'>"+v2.spec_name+":"+v2.item_name+"</li> ";								
							});
							$("#guiges").append(li);
						});
					}
				});
			}
	   
	   //分组
	   function divide(data){
		   var map = {}, dest = [];
			for(var i = 0; i < data.length; i++){
			    var sp = data[i];
			    if(!map[sp.spec_id]){
			        dest.push({
			        	spec_id: sp.spec_id,
			            data: [sp]
			        });
			        map[sp.spec_id] = sp;
			    }else{
			        for(var j = 0; j < dest.length; j++){
			            var dj = dest[j];
			            if(dj.spec_id == sp.spec_id){
			                dj.data.push(sp);
			                break;
			            }
			        }
			    }
			}
			return dest;
	   }
	   
	   //设置规格的值
	   $(function(){
		   var key = '${goods.zjcSpecGoodsPricePO.get(0).key}';
		   var keys = new Array();
		   keys = key.split("_");
		   getSpec();
		   console.log($(".flag"));
		   $.each($(".flag"), function(index, li){
			   for (var i = 0; i < keys.length; i++){
				   console.log(keys[i]);
				   console.log(li);
				   if($(li).val() == keys[i]){
					   $(this).addClass("guge_click");
				   }
			   }
		   });
	   })
	   
	   function cancle(){
		   window.location.href="toProductPage.jhtml";
	   }
	   
	   function check(){
		   var goods_name = $("#goods_name").val();
		   if(goods_name == ""){
			   $("#m_name").html("商品名称必填");
			   return false;
		   }
		   /* var keywords = $("#keywords").val();
		   if(keywords == ""){
			   $("#m_keywords").html("商品关键词必填");
			   return false;
		   } */
		   var strs = new Array(), sum = 0; //定义一数组 
		   var keywords = $("#keywords").val();
		   if(keywords.length > 20){
			   $("#m_keywords").html("关键词过长");
			   return false;
		   }else{
			   $("#m_keywords").html("");
		   }
		   strs = keywords.split(",");
		   if(strs.length > 3){
			   $("#m_keywords").html("关键词最多三个");
			   return false;
		   }else{
			   $("#m_keywords").html("");
		   }
		   var cat_id = $("#cat_id option:selected").val();
		   if(cat_id == ""){
			   $("#m_cat").html("商品分类必选");
			   return false;
		   }else{
			   $("#m_cat").html("必选");
		   }
		   var pay_type = $("#pay_type").val();
		   if(pay_type==1){//在线支付
			   var shop_price = $("#shop_price").val();
			   if(shop_price <= 0 || shop_price == '' || isNaN(shop_price)){
				   $("#m_shop").html("在线支付金额必填，最多两位小数");
				   return false;
			   }else{
				   $("#m_shop").html("必填");
			   }
		   } else if(pay_type == 2){//易支付
			   var market_price = $("#market_price").val();
			   if(market_price % 1 != 0 || market_price <= 0 ||  market_price == '' || isNaN(market_price)){
				   $("#m_market").html("易支付金额必填。");
				   return false;
			   }else{
				   $("#m_market").html("必填"); 
			   }
		   } else if(pay_type == 3){//滴支付
			   var drops = $("#drops").val();
			   if(drops % 1 != 0 || drops <= 0 ||  drops == '' || isNaN(drops)){
				   $("#m_drops").html("滴支付金额必填。");
				   return false;
			   }else{
				   $("#m_drops").html("必填"); 
			   }
		   } 
		   var store_count = $("#store_count").val();
		   if(!(/(^[1-9]\d*$)/.test(store_count))){
			   $("#m_count").html("库存数量必须为正整数，如果库存为0，请直接下架");
			   return false;
		   }
		   
		   var today_limit_times = $("#today_limit_times").val();
		   if(!(/(^[1-9]\d*$)/.test(store_count))){
			   $("#m_times").html("限购数量必须为正整数");
			   return false;
		   }else{
			   $("#m_times").html("必填");
		   }
		   
		   var today_limit_nums = $("#today_limit_nums").val();
		   if(!(/(^[1-9]\d*$)/.test(store_count))){
			   $("#m_nums").html("限购次数必须为正整数");
			   return false;
		   }else{
			   $("#m_nums").html("必填");
		   }
		   
		   var expect_delivery = $("#expect_delivery").val();
		   if(!(/(^[1-9]\d*$)/.test(store_count))){
			   $("#m_expect").html("发货时间必须为正整数");
			   return false;
		   }else{
			   $("#m_expect").html("必填");
		   }
		   
		   var contract_phone = $("#contract_phone").val();
		   if(contract_phone == '' || contract_phone == null){
			   $("#m_phone").html("联系电话不能为空");
			   return false;
		   }else{
			   $("#m_phone").html("必填");
		   }
		   
		   var img = $("#img").val();
		   if(img == null || img == ''){
			   $("#m_img").html("商品图片必填");
			   return false;
		   }else{
			   $("#m_img").html("必填");
		   }
		   return true;
	   }
	   
	   $('.tabs_ul_mctone ul li').click(function(){
			$(this).addClass('hit_ul_mctone').siblings().removeClass('hit_ul_mctone');
			$('.panes_ul_mctone>div:eq('+$(this).index()+')').show().siblings().hide();	
		});
	   
	   function showdiv(){
		   $("#picture-upload").show();
	   }
	   function closediv(){
		   $("#picture-upload").hide();
	   }
	 //属性选择同类型的互异
	   function can_click(v){
		   if($(v).hasClass("guge_click")){
			   $(v).removeClass("guge_click");
		   }else{
			   $(v).addClass("guge_click");
		   }
	   }
	 
	   function setGuiges(){
		   var xz = $.find(".guge_click");
		   var key="", name="";
		   $.each(xz, function(index, e){
			   key += $(e).val()+"_";
			   name += $(e).html();
		   });
		   key = key.substr(0,key.length-1);
		   
		   $("#guige_key").val(key);
		   $("#guige_name").val(name);
	   }
	</script>
</aos:body>
</aos:html>
