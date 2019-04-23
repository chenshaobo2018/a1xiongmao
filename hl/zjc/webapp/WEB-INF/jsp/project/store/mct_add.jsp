<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="商家后台-添加商品">
<!-- common css and zjc_index css-->
<link href="${cxt}/static/css/store/common.css" rel="stylesheet" type="text/css" />
<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet" type="text/css" />
<!-- js -->
<script type="text/javascript" src="${cxt}/static/js/store/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
<script type="text/javascript" src="${cxt}/static/js/store/upfile.js"></script>
<!-- 多文件上传js引入及css -->
<link href="${cxt}/static/css/store/imgup/common.css" rel="stylesheet" type="text/css" />
<link href="${cxt}/static/css/store/imgup/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${cxt}/static/js/store/imgup/imgUp.js"></script>
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
		<!-- mct_main start -->
		<div class="mct_main">
			<div class="mct_detailmain_add">
				<div class="mctde_cont_add">
					<div class="mct_list_tit">
						<p>商品--商品添加</p>
					</div>
					<form id="goodsAdd">
					<div class="mctde_one">
						<div class="mct_rad">
							<div class="tabs_ul_mctone">
								<ul class="">
									<li class="hit_ul_mctone">基本信息 </li>
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
										 	<input type="text" class="pric_names" name="goods_name" id="goods_name">
											<span class="mcts_span" id="m_name" style="color:red">必填</span>
										 </div>										 
										 <div class="clear"></div>
										</div>
										<div class="mct_text_two">										
										  <div class="mst_lab fl">
											<label class="mcttez_label">关&nbsp;&nbsp;键&nbsp;&nbsp;词</label>
											</div>
										 <div class="mst_labr fl">
											<input type="text" class="mctdetail_text" name="keywords" id="keywords" placeholder="多个关键词建议用“空格”分隔，总长度不超过20个字符" style="width:395px;">
											<span class="mcts_span" id="m_keywords" style="color:red"></span>
										 </div>										 
										 <div class="clear"></div>
										</div>
										<div class="mct_text_two">																			
										  <div class="mst_lab fl">
											<label class="mcttez_label_two">商品分类</label> 
											</div>
											<div class="mst_labr fl">
											<select class="mctdetail_sel" id="cat_id" name="cat_id" onchange="getCatId2()">
												<option value="">所有分类</option>
												<c:forEach var="cat" items="${sessionScope.categorys }">
													<option value="${cat.value }">${cat.display }</option>
												</c:forEach>
											</select>
											<select id="cat_id2" name="cat_id2" class="mctdetail_sel">
												<option value="" selected="selected">请选择（二级分类）</option>
											</select> 
											<span class="mcts_span" id="m_cat" style="color:red">必填</span>
											</div>				 
										 <div class="clear"></div>
										</div>
										<div class="mct_text_tree">																		
										  <div class="mst_lab fl">
											<label class="mcttez_label">商品货号</label>
											</div>
											<div class="mst_labr fl">
											
											<input type="text" class="mctdetail_text" name="goods_sn" id="goods_sn" placeholder="可不填，不填时系统自动生成" style="width:395px;">
											<span class="mcts_span" style="color:red"></span>
											</div>
										 <div class="clear"></div>											
										</div>
										<div class="mct_text_tree">													
										  <div class="mst_lab fl">		
											<label class="mcttez_label">支付方式</label>
											</div>
											<div class="mst_labr fl">
												<select class="mctdetail_sel" name="commodity_categories" id="pay_type" onchange="changePayType()" style="width:395px;">
													<option value="1">在线支付</option>
													<option value="2">易支付</option>
													<option value="3">滴支付</option>
												</select>
												<span style="color: #666666 ">（请选择需要的付款方式）</span>
											</div>
										 <div class="clear"></div>									
										</div>
										<div class="mct_text_tree cash">																
										  <div class="mst_lab fl">										
											<label class="mcttez_label">在线支付</label>
											</div>
											<div class="mst_labr fl">
											<input type="text" class="mctdetail_text" name="shop_price" id="shop_price" placeholder="必须为正数" style="width:395px;"> 
											<span class="mcts_span" id="m_shop"  style="color:red">必填</span>
											</div>
										 <div class="clear"></div>							
										</div>
										<div class="mct_text_tree market" style="display:none">															
										  <div class="mst_lab fl">		
											<label class="mcttez_label">易支付</label>
											</div>
											<div class="mst_labr fl">
											<input type="text" class="mctdetail_text" name="market_price" id="market_price" placeholder="必须为正数" style="width:395px;"> 
											<span class="mcts_span" id="m_market"  style="color:red">必填</span>
											</div>
										 <div class="clear"></div>									
										</div>	
										<div class="mct_text_tree drop" style="display:none">															
										  <div class="mst_lab fl">		
											<label class="mcttez_label">滴支付</label>
											</div>
											<div class="mst_labr fl">
											<input type="text" class="mctdetail_text" name="drops" id="drops" placeholder="必须为正数" style="width:395px;"> 
											<span class="mcts_span" id="m_drops"  style="color:red">必填</span>
											</div>
										 <div class="clear"></div>									
										</div>	
										<!-- <div class="mct_text_tree">													
										  <div class="mst_lab fl">		
											<label class="mcttez_label">混合支付</label>
											</div>
											<div class="mst_labr fl">
											<span class="mcts_sp">
												<input type="radio" name="is_mixed" value="0" checked class="mcts_rad">否
												<input type="radio" name="is_mixed" value="1" class="mcts_rad">是
												<br> 
											</span>
											</div>
										 <div class="clear"></div>									
										</div> -->						
										<div class="mct_text_tree">												
										  <div class="mst_lab fl">	
											<label class="mcttez_label">特殊商品</label>
											</div>
											<div class="mst_labr fl">
											<span class="mcts_sp">
												<input type="radio" name="is_car" value="0" checked class="mcts_rad">否
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
													<option value="${gp.id }">${gp.name }</option>
												</c:forEach>
											</select>
											</div>
										 <div class="clear"></div>		
										</div>
										<!-- <div class="mct_text_tree">								
										  <div class="mst_lab fl tr_guge">	
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
											  <input type="text" class="ku_sums" name="store_count" id="store_count" placeholder="必须为正整数">
											  <span class="mcts_span" id="m_count"  style="color:red">必填</span>
											</div>
										 <div class="clear"></div>		
										</div>
										<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">限购次数</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text"  name="today_limit_times" id="today_limit_times" style="width:395px;" placeholder="必须为正整数">
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
										    <input type="text" class="mctdetail_text" name="today_limit_nums" id="today_limit_nums" style="width:395px;" placeholder="必须为正整数">
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
										    <input type="text" class="mctdetail_text" name="shop_group_price" id="shop_group_price" style="width:395px;" placeholder="必须为正整数">
											<span style="color: #666666 ">（参与拼团产品必填,纯现金商品价格字段）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">拼团人数</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" name="shop_group_person" id="shop_group_person" style="width:395px;" placeholder="必须为正整数">
											<span style="color: #666666 ">（参与拼团产品必填，要求拼团达到的人数）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">券价格</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" name="shop_group_market_price" id="shop_group_market_price" style="width:395px;" placeholder="必须为正整数">
											<span style="color: #666666 ">（参与拼团产品必填，纯易物券商品价格字段）</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									
									<div class="mct_text_tree">
										<div class="mst_lab fl">
										<label class="mcttez_label">发货时间</label>
										</div>
										 <div class="mst_labr fl">
										    <input type="text" class="mctdetail_text" name="expect_delivery" id="expect_delivery" style="width:395px;" placeholder="必须为正整数">
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
												<input type="radio" name="is_free_shipping" value="0" class="mcts_rad" >否
												<input type="radio" name="is_free_shipping" value="1" class="mcts_rad" checked="checked">是
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
										    <input type="text" class="mctdetail_text mct_p" value="0" name="postage" id="postage" style="width:395px;" placeholder="必须为正数" disabled="disabled">
											<span class="mcts_span" id="m_postage" style="color: red">必填</span>
										 </div>										 
										 <div class="clear"></div>	
									</div>
									<div class="mct_text_two">									
										<div class="mst_lab fl">
											<label class="mcttez_label">型号/规格</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mfzo" name="ts" id="ts" >
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_two">									
										<div class="mst_lab fl">
											<label class="mcttez_label">联系电话</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mfzo" name="contract_phone" id="contract_phone" placeholder="请填写一个正常可用的联系电话">
											<span class="mcts_span" id="m_phone" style="color:red">必填</span>										
										 </div>										 
										 <div class="clear"></div>
									</div>
									<div class="mct_text_two">									
										<div class="mst_lab fl">
											<label class="mcttez_label">备注</label>
										</div>
										 <div class="mst_labr fl">
											<input type="text" class="mfzo" name="remark" id="remark" >
											<span class="mcts_span" id="m_remark" style="color:red"></span>										
										 </div>										 
										 <div class="clear"></div>
									</div>
										<div class="mct_text_tree">			
										  <div class="mst_lab fl">	
											<label class="mcttez_label fl tr_guge">商品图片</label>
											</div>
											<div class="mst_labr fl">
											<input type="hidden" name="original_img" id="img"/> 
											<div class="in_divbus">
												<div class="imgitem secondImg">
													<div class="u-add-img-icon" style="line-height: 26px;" onclick="showdiv();"></div>
													<div class="imgcontainer">
														<img src="" id="original_img" class="idImg" alt="upfile" title="upfile" />
													</div>
												</div>
											<span class="mcts_span_text" id="m_img"  style="color:red"></span>
												<!-- 放大图 -->
												<div class="big_ime_one">
													<img id="big_img" src="" alt="upfile" title="upfile" />
												</div>
												<div class="big_ime_make_one"></div>
												<!-- 放大图 -->
											</div>
											<div class="clear"></div>
											</div>
										 <div class="clear"></div>		
										</div>
								<%-- 		<div class="mct_text_tree">	
										  <div class="mst_lab fl">	
											<label class="mcttez_label">商品品牌</label>
											</div>
											<div class="mst_labr fl">
											<select class="input_sid" name="brand_id">
												<option value="0">选择品牌</option>
												<c:forEach var="b" items="${brandPOs }">
													<option value="${b.id }">${b.name }</option>
												</c:forEach>
											</select>
										</div>
										 <div class="clear"></div>	
									</div> --%>
										<div class="mctde_five">
											<button class="mct_tm" onclick="subForm1();" type="button">保存</button>
											<button class="mct_tm" onclick="cancle();" type="button">返回</button>
										</div>
								</div>
								</div>
								<!-- 详情 -->
								<div class="pane_ul_mctone">
								  <div class="panul_one">
								     <div class="panulone_one pant_text fl">
								        <p>生产地址：<input type="text" placeholder="选填" name="place_of_production"/></p>
								        <p>商品重量：<input type="text" placeholder="选填" name="weight"/></p>
								     </div>
								     <div class="panulone_two pant_text fl">
								        <p>生产厂家：<input type="text" placeholder="选填" name="manufacturer"/></p>
								        <p>商品特性：<input type="text" placeholder="选填" name="goods_features"/></p>
								      </div>
								     <div class="panulone_tre pant_text fl">
								        <p>包装情况：<input type="text" placeholder="选填" name="packing_info"/></p>
								        <!-- <p>联系电话：<input type="text" placeholder="选填" name="contract_phone"/></p> -->
								     </div>
								     <div class="clear"></div>
								  </div>				   
									<div class="mctde_two">
										<div class="mctde_two_cont">
											<div class="mop_one">
											
												<div id="div_add_ueditor">
													<script id="add_editor" type="text/plain"></script>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>										
									<div class="mctde_five">
										<button class="mct_tm" onclick="subForm2();" type="button">提交</button>
										<button class="mct_tm" onclick="cancle();" type="button">返回</button>
									</div>							 
								</div>
					</div>
					</form>
					</div>
				</div>
			</div>
		</div>		
	<!--遮罩层  -->
		<div class="mct_footer">
			<div class="mctfoot_cent">
				<p>©2017-2025 北京中军创云易物联网科技有限公司 版权所有</p>
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
		
		<!-- mct_footer end -->
		<!-- upfile -->
		<script type="text/javascript" src="${cxt}/static/js/store/upfile_vip.js"></script>
		<script type="text/javascript" src="${cxt}/static/js/store/myupfile.js" charset="UTF-8"></script>
		<script type="text/javascript">

		   $("input[name=is_free_shipping][value='0']").click(function(){//不包邮
				  $(".mct_p").val("0");
				  $(".mct_p").removeAttr("disabled");
		   });
			  $("input[name=is_free_shipping][value='1']").click(function(){//包邮
				  $(".mct_p").val("0");
				  $(".mct_p").attr("disabled","false");
		   });
		   //实例化编辑器
		   var addUE = UE.getEditor('add_editor',{
		    	zIndex:999
		    });
		   function subForm1(){
			   //验证输入
			   var i = check();
			 	//为选中的规格设值
			   setGuiges();
			   if(i == true){
				   $("#spxq").click();
			   }else{
				   alert_msg("请检查您的输入！"); 
			   }			   
		   }
		   function subForm2(){
			   //验证输入
			   var i = check();
			 	//为选中的规格设值
			   setGuiges();
			   if(i == true){
				   $.ajax({
					   url:"saveGoods.jhtml",
					   type:"POST",
					   data:$("#goodsAdd").serialize(),
					   success:function(data){
						   alert_msg(data.msg);
						   if(data.code == 1){					  
							   window.location.href="toProductPage.jhtml";
						   }
					   }
				   });
			   }else{
				   alert_msg("请检查您的输入"); 
			   }			   
		   }
		   function check(){
			   var goods_name = $("#goods_name").val();
			   if(goods_name == ""){
				   $("#m_name").html("商品名称必填");
				   return false;
			   }else{
				   $("#m_name").html("必填");
			   }
			   /*  var keywords = $("#keywords").val();
			   if(keywords == ""){
				   $("#m_keywords").html("商品关键词必填");
				   return false;
			   }else{
				   $("#m_keywords").html("必填");
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
			   var cat_id2 = $("#cat_id2 option:selected").val();
			   if(cat_id == "" || cat_id2 == ""){
				   $("#m_cat").html("商一级分类，二级分类必选");
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
				   $("#m_count").html("库存数量必须为正整数");
				   return false;
			   }else{
				   $("#m_count").html("必填");
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
		   //动态获取二级分类
		   function getCatId2() {
				jQuery.ajax({
					url : "listGoodsCategory2.jhtml",
					type : 'POST',
					dataType : 'json',
					async : false,
					data : {
						'id' : $("#cat_id").val()
					},
					success : function(data) {
						$("#cat_id2").empty();
						$("#cat_id2").append('<option value="" selected="selected">请选择（二级分类）</option>');
						for (var i = 0; i < data.length; i++) {
							$("#cat_id2").append(
									"<option value='" +data[i].value +"'>"
											+ data[i].display + "</option>");
						}
					}
				});
			}
		   //动态改变支付方式
		   function changePayType(){
			   var pay_type = $("#pay_type").val();
			   if(pay_type == 1){//在线支付
				   $(".cash").show();
				   $(".market").hide();
				   $(".drop").hide();
				   $("#market_price").val('0');
				   $("#drops").val('0');
				   
			   } else if(pay_type==2){//易支付
				   $(".market").show();
				   $(".cash").hide();
				   $(".drop").hide();
				   $("#shop_price").val('0');
				   $("#drops").val('0');
			   } else if(pay_type==3){//滴支付
				   $(".drop").show();
				   $(".market").hide();
				   $(".cash").hide();
				   $("#shop_price").val('0');
				   $("#market_price").val('0');
			   }
		   }
		   $(function(){
			   $('.add-img').click(function(){
				   return  $("#file").click();
			   });
		   });
		   function imglistUp(id){
			   $("input[name='"+id+"']").val("");
			   var imgList = $(".img-box .up-img");
			   console.log(imgList);
			   for(var i=0;i < imgList.length;i++){
				   base(imgList[i].currentSrc,id);
			   }
			   alert("上传成功！");
		   }
		   
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
								li += "<li onclick='can_click(this)' value='"+v2.item_id+"'>"+v2.spec_name+":"+v2.item_name+"</li> ";								
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
		   
		   //属性选择同类型的互异
		   function can_click(v){
			   if($(v).hasClass("guge_click")){
				   $(v).removeClass("guge_click");
			   }else{
				   $(v).addClass("guge_click");
			   }
		   }
		   //设置规格的值
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
		   function cancle(){
			   window.location.href="toProductPage.jhtml";
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
	    </script> 
</aos:body>
</aos:html>
