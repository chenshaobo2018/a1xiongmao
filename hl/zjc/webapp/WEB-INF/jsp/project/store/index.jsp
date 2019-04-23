<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE>
<aos:html title="商家后台欢迎页">
<!-- common css and zjc_index css-->
<link href="${cxt}/static/css/store/common.css" rel="stylesheet"
	type="text/css" />
<link href="${cxt}/static/css/store/mct_index.css" rel="stylesheet"
	type="text/css" />
<!-- js -->
<script type="text/javascript"
	src="${cxt}/static/js/store/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${cxt}/static/js/store/mct_index.js"></script>
<link type="text/css" rel="stylesheet"
	href="${cxt}/static/css/store/163css.css" />
<script type="text/javascript" src="${cxt}/static/js/store/163css.js"></script>
<script type="text/javascript" src="${cxt}/static/js/store/jquery.qrcode.min.js"></script>
<script type="text/javascript">
	function getrealname() {
		var real_name = $("#real_name").val();
		if(real_name == ""){
			$("#real_names").html("真实姓名不能为空");
		}else{
			$.post("real_name.jhtml",{
				"real_name":real_name
			}, function(data){
				if(data.code == 1){
					$("#real_names").html("");
				}else{
					$("#real_names").html(data.msg);
				}
			});
		}	
	}

	function getmobile() {
		var real_name = $("#real_name").val();
		var mobile =  $("#mobile").val();
		if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)) || mobile == ""){ 
			$("#mobiles").html("请检查您输入的手机号！");
		}else{
			$.post("mobile.jhtml",{
				"real_name":real_name,
				"mobile" : mobile
			}, function(data){
				if(data.code == 1){
					$("#mobiles").html("");
				}else{
					$("#mobiles").html(data.msg);
				}
			});
		}
	}

	function getpaypoints() {
		var pay_points = $("#pay_points").val();
		if(pay_points % 1 === 0){
			if(1000 <= parseInt(pay_points) && parseInt(pay_points) <= 50000){
				$("#pay_point").empty();
			}else{
				$("#pay_point").html("单笔交易范围1000---50000易物券");
			}
		}else{
			$("#pay_point").html("转账金额只能为整数");
		}
	}

	$(function() {
		var liList = $(".nav_li li");
		for (var i = 0; i < liList.length; i++) {
			if (liList[i].innerText == '首页') {
				liList.eq(i).addClass("mct_click");
			}
		}
	});
</script>
<aos:body>
	<div class="mct_container">
		<!-- mct_header start -->
		<%@include file="/WEB-INF/jsp/project/store/common/storeHeader.jsp"%>
		<!-- mct_header end -->
		<!-- mct_main start -->
		<div class="mct_main">
			<div class="mct_main_cent">
				<div class="mct_main_cside fl">
					<img src="${cxt}/static/image/mct/side_main.png" alt="complan"
						title="complan" />
				</div>
				<div class="mct_main_cont">
					<div class="mcton_one">
						<div class="mcton_oneside fl">
							<div class="mcton_onesideimg fl">
								<img src="${cxt}/static/image/mct/index_login.png" alt="logo"
									title="logo" />
							</div>

							<div class="mcton_onesidecon fl">
								<p class="hid_one bold">北京中军创云易物联网科技有限公司</p>
								<div class="mcton_text">
									<p>
										用户名：<span>${homepage.user_name}</span>
									</p>
									<p>
										管理权限：<span>管理员</span>
									</p>
								</div>
							</div>

							<div class="clear"></div>
						</div> 
							<div class="mcton_onecontfl fl">
								<p>
									店铺名称：<span>${homepage.store_name}</span>
								</p>
								<p>
									最后登录时间：<span>${homepage.time}</span>
								</p>
							</div>
							
							<button class="fanxiang" style="margin-top: 50px;margin-left: 30px;" id="create_user_account_btn" onclick="create_user_account()">开通商家会员号</button>
							
							<div class="fr" style="margin-right:30px;">
							<input type="hidden" value="${homepage.user_id}" id="qr_user_id">
							<input type="hidden" value="${homepage.has_terminal}" id="qr_has_tim">
								<div id="divOne"></div>  
	  							<img id='imgOne' width="150" height="150" alt="二维码"/>  
	  							</div>					 
						<div class="clear"></div>
					</div>
					<!-- 获得易物券 -->
					<div class="all_one">
						<div class="asl_two">
						    <div class="slot fl">
								<p>可转券</p>
						    </div>
						    <div class="seloti fl">
							<div class="all_one_side fl">
								<div class="lod fl">${homepage.total_amount}</div>
								<div class="fcon fl">
									<button class="all_mon">我要转账</button>
									<span>备注：仅限本人实名的会员ID号转账</span>
								</div>
								<div class="clear"></div>
							</div>
							<div class="all_one_cont fr">
								<button class="all_record" onclick="serachLog()">交易记录</button>
							</div>
							<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					
					<!-- 易物券转账 -->
					<form id="myform" name="myform">
						<div class="show_two">
							<div class="show_two_title">我要转账</div>
							<div class="trans_content">
								<div class="trans_content_cen">
									<div class="set_conl_head">
										<img src="${cxt}/static/image/mct/t_head.png" alt="结算"
											title="结算" width="624" height="18">
									</div>
									<div class="trans_cont">
										<!-- <div class="trans_cont_one">
											<div class="trans_cont_ode fl">姓名：</div>
											<div class="trans_cont_odr fl">
												<div class="fl">
													<input type="text" name="real_name" 
														id="real_name" class="trans_input" placeholder=" 请输入真实姓名" /><br/>
														<span id="real_names"></span>
												</div>
												<div class="clear"></div>
											</div>
											<div class="clear"></div>
										</div>
										<div class="trans_cont_one">
											<div class="trans_cont_ode fl">手机号：</div>
											<div class="trans_cont_odr fl">
												<div class="fl">
													<input type="text" name="mobile" id="mobile"
													class="trans_input" placeholder=" 请输入本人手机号" /><br/>
														<span id="mobiles"></span>
												</div> 
												<div class="clear"></div>
											</div>
											<div class="clear"></div>
										</div> -->
										<div class="trans_cont_one">
											<div class="trans_cont_ode fl">金额：</div>
											<div class="trans_cont_odr fl">
												<div class="fl">
													<input type="text" name="pay_points" id="pay_points"
														class="trans_input"/><br/>
														<span id="pay_point"></span>
												</div> 
												<div class="clear"></div>
											</div>
											<div class="clear"></div>
										</div>
										<div class="trans_check">
											<div class="tran_check">
												<div class="mct_cks fl">
													<input type="checkbox"> <label class="dfokgs" id="is_check"
														for="nba" ondblclick="check()" name="rlpo" type="checkbox"></label>
												</div>
												<div class="check_text fl">
													我已阅读以上内容并同意<a  class="hover_click">《中军创云易平台转账注意事项》</a>
												</div>
												<div class="clear"></div>
											</div>
										</div>
										<div class="trans_btn">
											<input class="trans_two" type="button" onclick="confirm_transfer()" value="确认转账" />
											<input class="trans_one" type="reset" value="重置" />
											<input class="indexs_back" type="button" value="返回"/> 
										</div>
									</div>
								</div>
							</div>
						</div>
					</form> 
								<!-- two -->
					<div class="show_tree">
						<div class="show_two_title">我的交易记录</div>
						<div class="record_bg">
							<div class="side_record">
							   <div class="panes_ul_record">
								<input type="hidden" id="d_nowPage" value="1">
								<input type="hidden" id="d_total" value="${fn:length(homepage.recentDays) }">
									<!-- 最新上架 -->
									<div class="pane_ul_record" style="display: block;">
										<table class="tab_vipa"> 
												<tr>
													<th width="50">序号</th>
													<th width="150">交易时间</th>
													<th width="300">订单号</th>
													<th width="300">交易类型</th>
													<th width="300">会员ID</th>
													<th width="300">收入券</th>
													<th width="300">支出券</th>
													<th width="300">余额</th>
												</tr> 
												<tbody id="income_flow"></tbody>
										</table>
									</div>
								</div>
								<div class="mct_l_c_four">
									  <div class="mct_page_add">
									  		<input type="hidden" id="nowPage"><input type="hidden" id="total">
											  <ul>
												<li class="top_page"  onclick="last_page()"><a>上一页</a></li>
												 <li class="next_page" onclick="next_page()"><a>下一页</a></li>
												 <li class="next_page on_back" onclick="serachLog()"><a>返回</a></li>
												 <div class="clear"></div> 
											  </ul> 
											</div>
								   </div> 
							</div>
						</div>
					</div>
		  <div class="do_recod">
			<div class="show_two_title">
		      委托收款转账申请		   
			</div>
			<form id="trustForm" name="trustForm">
			<div class="trans_content" id="tr_setsd">
				   <div class="trans_content_mon">
				    <div class="set_conl_head">
					  <img src="${cxt }/static/image/mct/t_head3.png" alt="账户信息" title="个人信息" width="624" height="18"> 
					</div>
					<div class="trans_cont">
				       <div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">委托人姓名：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="real_name" id="w_name" placeholder="委托人真实姓名"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
						  </div>
					      <div class="clear"></div>
					   </div>
				       <div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">委托人身份证号：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="id_card" id="w_id" placeholder="委托人身份证号"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div>
				       <div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">联系电话：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="contract_mobile" id="w_contrack" placeholder="委托人联系电话"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div>
					   <!-- <div class="tre_one open_bank"  style="display: none">
					      <div class="trd_side fl">
						     <label class="hads_name">企业ID：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="user_id" id="w_store"  placeholder="委托企业ID"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div> -->
					   <div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">商家名称：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="store_name" id="w_store_name" placeholder="商家店铺名称"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div>
					   </div>
					   <div class="set_conl_head">
						  <img src="${cxt }/static/image/mct/t_head2.png" alt="账户信息" title="账户信息" width="624" height="18"> 
						</div>
						<div class="trans_cont">
						<div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">开户户名：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="account_name" id="w_ac_name"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div>
					   <div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">开户账号：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="account_mumber" id="w_ac_num"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div>
					   <div class="tre_one open_bank">
					      <div class="trd_side fl">
						     <label class="hads_name">开户行：</label>
						  </div>
					      <div class="trd_cont fl">
						     <input type="text" class="lab_input" name="bank" id="w_bank"/>
						  </div>
						  <div class="trans_conts fl">
							<div></div>
							</div>
					      <div class="clear"></div>
					   </div>
				       <div class="tre_one bank_gos"> 
					   <div class="cken_bank fl">
								<input type="checkbox">
								<label class="dfokgs" for="nba" name="cdbank" type="checkbox" id="cdbank"></label>	
								</div>
								<div class="check_text fl"> 我已阅读以上内容并同意<a class="hove_clic_show">《委托收款协议》</a></div>								
					      <div class="clear"></div>
					   </div>
					   <div class="tre_one btn_over_mon">
						    <input class="overtbsn_pwd" type="button" id="btn1" value="返回">
						    <input class="overtbsn_pwd_2" type="reset" id="btn3" value="重置">			   
						    <button class="seconrtbsn_pwd btn_sub_two" id="btn2" onclick="confirm_trust()" type="button">确认</button>
					   </div>					   
					</div> 
				   </div> 
				</div>
				</form> 
				<!--表格  -->
				<div class="tab_indexs">
				<div class="dfn_tab_one">
				   <table>
				     <tr>
				       <td width="150">委托人姓名</td>
				       <td id="wt_name"></td>
				       <td  width="150">委托人身份证号</td>
				       <td id="wt_id_card"></td> 
				     </tr>
				     <tr>
				       <td  width="150">联系电话</td>
				       <td id="wt_contract"></td>
				       <td  width="150">企业号ID</td>
				       <td id="wt_store_id"></td>
				     </tr>
				     <tr>
				       <td  width="150">企业名称</td>
				       <td id="wt_store_name"></td>
				       <td  width="150">开户户名</td>
				       <td id="wt_account_name"></td>
				     </tr>
				     <tr>
				       <td  width="150">开户账号</td>
				       <td id="wt_account_num"></td>
				       <td  width="150">开户行</td>
				       <td id="wt_bank"></td>
				     </tr>
				   </table>
				   </div> 
				   <input type="button" value="返回" class="btns_goback"/>
				</div>
		  	</div>
					<!-- one -->
					<div class="show_one">
					<!-- 获得货币 -->
						  <div class="all_money"> 
							<div class="asl_two">
								<div class="alos fl">
								  <p>可得货币</p>
								</div>
								<div class="alosr fl">
							      <div class="all_one_side fl">
								      <div class="lod2 fl">
									     ${homepage.balance }
									  </div>
								      <div class="fcon fl">
									    <button class="all_go">委托收款协议</button> <span>备注：请务必提供真实有效的信息</span>
									  </div>
								      <div class="clear"></div>
								  </div>
							      <div class="clear"></div>
								  </div>
							      <div class="clear"></div>
							  </div>
						  </div>
						<!-- mcton_two -->
						<div class="mcton_two">
							<div class="mcton_two_tit">
								<p>交易提示</p>
							</div>
							<div class="mcton_two_cont">
								<div class="mcton_two_contside fl">
									<p>
										今天成交订单量<br />
										<span class="span_tex"> ${homepage.total_order}</span>
									</p>
								</div>
								<div class="mcton_two_contcont fl">
									<ul class="cmtoew_ul">
										<li class="lic_ok" onclick="wait_pay_order(1)"><a>待支付订单:${homepage.day_amount}</a></li>
										<li onclick="wait_delivery_order(1)"><a>待发货订单 :${homepage.day_paid}</a></li>
										<li onclick="wait_comment_order(1)"><a href="#">待评价订单 :${homepage.day_send_goods}</a></li>
										<li onclick="finish_order(1)"><a href="#">已完成订单 :${homepage.day_evaluate}</a></li>
										<div class="clear"></div>
									</ul>
								</div>
								<div class="clear"></div>
							</div>
						</div> 
						<!--待支付  -->
						<div class="mct_one_lis">
							<table class="tab_vipa">
							   <tr>
							      <th>订单编号</th>
							      <th>会员ID</th>
							      <th>收货人</th>
							      <th>商品名称</th>
							      <th>订单金额</th>
							      <th>下单时间</th>
							   </tr>
							   <tbody id="t_order"></tbody>
							</table>
							<div class="mct_l_c_four">
								<div class="mct_page_add">
									<input type="hidden" id="order_nowPage">
									<input type="hidden" id="order_total">
									<input type="hidden" id="order_hasNextPage">
										<ul>
											<li class="top_page"  onclick="order_last_page()"><a>上一页</a></li>
											<li class="next_page" onclick="order_next_page()"><a>下一页</a></li>
											<li class="next_page on_back" onclick="cancle_table(this)"><a>返回</a></li>
										<div class="clear"></div> 
									</ul> 
								</div>
							</div> 
						</div>
						
					<!-- mcton_tree -->
						<div class="mcton_tree">
							<div class="mcton_two_tit_tree">
								<p>店铺及商品提示</p>
							</div>
							<div class="mcton_two_cont">
								<div class="mcton_two_contside fl">
									<p>
										今天成交商品数<br />
										<span class="span_tex_tree">${homepage.total_goods}</span>
									</p>
								</div>
								<div class="mcton_two_contcont fl">
									<ul class="cmtoew_ul_tree">
										<li class="lic_ok_tree" onclick="sale_goods(1)"><a>出售中的商品:${homepage.the_sale_goods}</a></li>
										<li onclick="stock_goods(1)"><a>缺货商品 :${homepage.out_stock_goods}</a></li>
										<li onclick="unsale_goods(1)"><a>下架商品 :${homepage.shelves_goods}</a></li>
										<li onclick="sells_hot_goods(1)"><a>热销商品 :${homepage.hot_goods}</a></li>
										<div class="clear"></div>
									</ul>
								</div>
								<div class="clear"></div>
							</div>
							<div class=""></div>
						</div>
						<!--  -->
						<div class="mct_one_lis_tr">
							<table class="tab_vipa">
							   <tr>
							      <th>商品名称</th>
							      <th>货号</th>
							      <th>分类</th>
							      <th>在线支付</th>
							      <th>易支付</th>
							      <th>库存</th>
							      <th>销量</th>
							   </tr>
							   <tbody id="t_goods"></tbody>
							</table> 
							<div class="mct_l_c_four">
								<div class="mct_page_add">
									<input type="hidden" id="goods_nowPage" >
									<input type="hidden" id="goods_total">
									<input type="hidden" id="goods_hasNextPage">
										<ul class="goods_ul_tree">
											<li class="top_page"  onclick="goods_last_page()"><a>上一页</a></li>
											<li class="next_page" onclick="goods_next_page()"><a>下一页</a></li>
											<li class="next_page on_back" onclick="cancle_table(this)"><a>返回</a></li>
										<div class="clear"></div> 
									</ul> 
								</div>
							</div> 
						</div>
						<!-- mcton_four -->
						<div class="mcton_four">
							<div class="mcton_two_tit_tree">
								<p>店铺数据</p>
							</div>
							<div class="mcton_four_cont">
								<table>
									<tr class="mct_tr">
										<td align="left" width="78"><span
											class="mcton_four_tdone">移动端</span></td>
										<td align="center" width="114">昨天销量:<br />
										<span
											style="width: 114px; overflow: hidden; word-break: break-all;color:#e66e2e;">${homepage.yesterday_sales}</span></td>
										<td align="center" width="86">月销量:<br />
										<span
											style="width: 86px; overflow: hidden; word-break: break-all;color:#e66e2e;">${homepage.sales}</span></td>
										<td align="center" width="114">小计销量:<br />
										<span
											style="width: 114px; overflow: hidden; word-break: break-all;color:#e66e2e;">${homepage.amount}</span></td>
										<td  align="center" width="208">
											<div >
												<p>总订单量（笔）</p>
												<p
													style="font-weight: bold; font-size: 14px; color: #e66e2e; width: 200px; padding: 0px 4px; overflow: hidden; word-break: break-all;">${homepage.count_order}</p>
											</div>
										</td>
										<td class="bg_bord" align="center" width="207">
											<div>
												<p>总订单额（券）</p>
												<p
													style="font-weight: bold; font-size: 14px; color: #e66e2e; width: 200px; padding: 0px 4px; overflow: hidden; word-break: break-all;">${homepage.total_sales}</p>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<!--  遮罩层-->
						<div class="suc_on">
							<div class="suc_text">
								<p>我要转账是中军创云易平台提供的一个为保障交易安全的支付方式，同时为消费者和商家提供交易安全保障。</p>
								<p>消费者在转账时，请认真填写好交易信息，消费者在平台操作时要确认对方信息准确无误，转账方可成功。</p>
								<p>请各消费者及商家铭记，并按平台提示操作。</p>
    							<p>备注：单笔交易，最低限额不低于1000券，最高限额不超过50000券。</p>
							</div>
						    <button class="index_btn_page">确认</button>
						</div>
						<div class="suc_on_make"></div>
					<!--  遮罩层-->
						<div class="suc_on_copy">
							<div class="suc_text">
							   <p>1、在接受本服务协议前，请您仔细阅读、理解并接受本协议所有条款，若您不理解或不接受本协议任一条款，请不要进行后续操作，否则视为您完全接受并遵守。</p>
							   <p>2、本协议是北京中军创云易物联网科技有限公司（以下简称“中军创”）为保证您在中军创云易平台（以下简称“本平台”）的交易安全及满足您去库存、去产能，真正服务于广大消费者之目的，特与您签订的。</p>
							   <p>3、您在使用本平台进行“以物易物”交易时应签署本协议，委托本平台代为收款。</p>	
							   <p>4、“代收”：是指按照本平台发布的规定或规则，在您与第三方发生易物交易关系，且第三方需要以银行转账方式向您支付款项的情况下，由发生交易第三方将该款项直接支付至本平台对公账户。</p>
							   <p>5、您同意，中军创按照本平台发布的规定或规则，将上述账户信息作为第三方向您支付款项的转款账户，并添加至您在本平台展示的产品信息中；在第三方向上述账户支付并到账时，相应款项视为已向您支付，中军创应在收到款项后及时将转款信息通过本平台发送给您，请您注意查验并按合同约定向第三方履行发货义务。</p>
							   <p>6、在第三方与您发生交易并向中军创账户转入相应款项后，您应及时与中军创核实名称、金额、到账情况等，否则造成的责任由您承担，中军创不承担任何责任。</p>
							   <p>7、在您与第三方在本平台的易物交易完成后20至40个工作日内，由本公司将该款项在扣除相应税费（综合税率为6%）后，无息转至您指定收款账户。</p>
							   <p>8、您指定的上述收款账户作为中军创向您转账的凭据，您不得随意变更或解除；如需变更的，应及时书面告知中军创，否则向您指定的上述账户转账的，视为已向您完成支付，中军创不再因此承担任何责任。</p>
							   <p>9、待中军创将所代为收取的款项转至您指定账户时，本合同终止。</p>
							</div>
						    <button class="index_btn_page_copy">确认</button>
						</div>
						<div class="suc_on_make_copy"></div>
						<div class="suc_on_copy_2">
							<div class="suc_text">
							   <p>注意事项未勾选,请仔细阅读后勾选!</p>
							</div>
						    <button class="index_btn_page_copy">确认</button>
						</div>
						<div class="suc_on_make_copy_2"></div>    
				</div>
				<div class="clear"></div>
			</div>
		</div> 
		<!-- mct_main end -->
		<!-- mct_footer start -->
		<div class="mct_footer">
			<div class="mctfoot_cent">
				<p>© 2017-2025 北京中军创云易物联网科技有限公司 版权所有</p>
			</div>
		</div>
		<!-- mct_footer end -->
	</div>
</aos:body>
<script>
$(function(){
	$(".hover_click").click(function(){
		$(".suc_on,.suc_on_make").show();
		
	});
	$(".index_btn_page").click(function(){
		$(".suc_on,.suc_on_make").hide();
		
	});
	$(".hove_clic_show").click(function(){
		$(".suc_on_copy,.suc_on_make_copy").show();
	});
	$(".index_btn_page_copy").click(function(){
		$(".suc_on_copy,.suc_on_make_copy").hide();
		
	});
	$(".btns_goback").click(function(){
		$(".do_recod").hide();
		$(".show_one").show();
	});
	$(".on_back").click(function(){
		$(".show_tree").hide();
		$(".show_one").show();
	});
});

//操作记录
var j = 0;
function serachLog(){
	j += 1;
	if(j % 2 === 0){
		$(".show_two, .show_tree").hide();
		$(".show_one").show();
		return;
	}
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"serachRecord.jhtml",
		success:function(data){
			var tr = "";
			$.each(data.list, function(index, e){
				tr += "<tr><td width='50'>"+(index+1)+"</td><td width='150'>"+e.exchange_time+"</td><td width='300'>"+
				e.order_sn+"</td><td width='300'>"+e.type+"</td><td width='300'>"+e.out_user_id+"</td><td width='300'>"+e.income+"</td><td width='300'>"+e.expend+"</td><td width='300'>"+e.balance+"</td></tr>";
			});
			$("#nowPage").val(data.nowPage);
			$("#total").val(data.totalPage);
			$("#income_flow").empty();
			$("#income_flow").append(tr);
		}
	});
	$(".all_record").css('background','#f14d3c'); 
	$(".all_record").css('color','#fff'); 
	$(".all_mon").css('background','#fff'); 
	$(".all_mon").css('color','#7d7d7d'); 
	$(".show_tree").show();
	$(".show_one,.show_two .all_money").hide(); 
}
function last_page(){
	var page = $("#nowPage").val();
	if(page == 1){
		return;
	}
	next_flag = false;
	page--;
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"serachRecord.jhtml",
		data:{
			"page":page,
		},
		success:function(data){
			var tr = "";
			$.each(data.list, function(index, e){
				tr += "<tr><td width='50'>"+(index+1)+"</td><td width='150'>"+e.exchange_time+"</td><td width='300'>"+
				e.order_sn+"</td><td width='300'>"+e.type+"</td><td width='300'>"+e.out_user_id+"</td><td width='300'>"+e.income+"</td><td width='300'>"+e.expend+"</td><td width='300'>"+e.balance+"</td></tr>";
			});
			$("#nowPage").val(data.nowPage);
			$("#total").val(data.totalPage);
			$("#income_flow").empty();
			$("#income_flow").append(tr);
		}
	});
}
function next_page(){
	var page = $("#nowPage").val();
	var total = $("#total").val();
	if(page == total){
		return;
	}
	page++;
	$.ajax({
		type:"GET",
		dataType:"json",
		url:"serachRecord.jhtml",
		data:{
			"page":page,
		},
		success:function(data){
			var tr = "";
			$.each(data.list, function(index, e){
				tr += "<tr><td width='50'>"+(index+1)+"</td><td width='150'>"+e.exchange_time+"</td><td width='300'>"+
				e.order_sn+"</td><td width='300'>"+e.type+"</td><td width='300'>"+e.out_user_id+"</td><td width='300'>"+e.income+"</td><td width='300'>"+e.expend+"</td><td width='300'>"+e.balance+"</td></tr>";
			});
			$("#nowPage").val(data.nowPage);
			$("#total").val(data.totalPage);
			$("#income_flow").empty();
			$("#income_flow").append(tr);
		}
	});
}
//易物券转账
var i = 0;
$(".all_mon").click(function(){
	i += 1;
	if(i % 2 === 0){
		$(".show_two").hide();
		$(".show_one").show();
		return;
	}
	$(".all_mon").css('background','#f14d3b'); 
	$(".all_mon").css('color','#fff'); 
	$(".all_record").css('background','#fff'); 
	$(".all_record").css('color','#7d7d7d'); 
	 $(this).removeClass("lick");
	$(".show_two").show();
	$(".show_one,.show_tree").hide();
})
$(".indexs_back").click(function(){
	i += 1;
	if(i % 2 === 0){
		$(".show_two").hide();
		$(".show_one").show();
		return;
	}
	$(".all_mon").css('background','#f14d3b'); 
	$(".all_mon").css('color','#fff'); 
	$(".all_record").css('background','#fff'); 
	$(".all_record").css('color','#7d7d7d'); 
	 $(this).removeClass("lick");
	$(".show_two").show();
	$(".show_one,.show_tree").hide();
})

$("#btn1").click(function(){
	$(".do_recod").hide();
	$(".show_one").show();
});
var k = 0;
$(function(){
	$.get("deputeDetial.jhtml",function(data){
		if(data.state == 1){
			$("#w_name").val(data.real_name);
			$("#w_id").val(data.id_card);
			$("#w_contrack").val(data.contract_mobile);
			$("#w_store").val(data.user_id);
			$("#w_store_name").val(data.store_name);
			$("#w_ac_name").val(data.account_name);
			$("#w_ac_num").val(data.account_mumber);
			$("#w_bank").val(data.bank);
			$('input',$("#trustForm")).prop('readonly',true);
			$('button',$("#trustForm")).prop('disabled',true);
			$("#btn1").prop("readonly",false);
			$("#btn3").prop("disabled",true);
		}else if(data.state == 2){
			$("#w_name").val(data.real_name);
			$("#w_id").val(data.id_card);
			$("#w_contrack").val(data.contract_mobile);
			$("#w_store").val(data.user_id);
			$("#w_store_name").val(data.store_name);
			$("#w_ac_name").val(data.account_name);
			$("#w_ac_num").val(data.account_mumber);
			$("#w_bank").val(data.bank);
			Showbo.Msg.alert('您的委托收款申请未通过审核!原因：'+data.note);
		}else {
			$("#wt_name").html(data.real_name);
			$("#wt_id_card").html(data.id_card);
			$("#wt_contract").html(data.contract_mobile);
			$("#wt_store_id").html(data.user_id);
			$("#wt_store_name").html(data.store_name);
			$("#wt_account_name").html(data.account_name);
			$("#wt_account_num").html(data.account_mumber);
			$("#wt_bank").html(data.bank);
			$("#tr_setsd").hide();
			$(".tab_indexs").show();
		}
	});
});
$(".all_go").click(function(){
	i += 1;
	if(i % 2 === 0){
		$(".do_recod").hide();
		$(".show_one").show();
		return;
	}
	
	$(".all_go").css('background','#f14d3b'); 
	$(".all_go").css('color','#fff'); 
	$(".all_conds,.all_record,.all_mon").css('background','#fff'); 
	$(".all_conds,.all_record,.all_mon").css('color','#7d7d7d'); 
	$(this).removeClass("lick");
	$(".do_recod").show();
	$(".show_one,.show_tree,.show_two").hide();		
})

var flag = true;
function confirm_transfer(){
	if ($(myform).find("#is_check").hasClass('dfokgs checked') == false) {
		Showbo.Msg.alert('注意事项未勾选,请仔细阅读后勾选!');
		return;
	}
	if($("#pay_points").val() == "" || $("#pay_point").text() != ""){
		alert_msg("请输入转账易物劵！");
		return;
	}
	var re = /^[0-9]+$/;
	if(!re.test($("#pay_points").val())){
		alert_msg("转账易物劵只能为正整数！");
		return;
	}
	/* if($("#mobile").val() == "" || $("#mobiles").text() != ""){
		alert_msg("请输入账号电话号码！");
		return;
	}
	if($("#real_name").val() == "" || $("#real_names").text() != ""){
		alert_msg("请输入转入账号真实姓名！");
		return;
	} */
	if(flag){
		flag = false;
		$.ajax({
			 type: "POST",
	         dataType: "json",
	         url: "${cxt }/store/transfer.jhtml",
	         data: $('#myform').serialize(),
	         success: function (data) {
	             alert_msg(data.msg);
	             flag = true;
	         }
		});
	}
}

function confirm_trust(){
	if ($(trustForm).find("#cdbank").hasClass('dfokgs checked') == false) {
		Showbo.Msg.alert('注意事项未勾选,请仔细阅读后勾选!');
		return;
	}
	var v = $("#trustForm").find("input"), flag = true;
	$.each(v, function (index, e){
		if($(e).val() == ""){
			flag = false;
			return false;
		}
	});
	if(flag){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:'${cxt }/store/confirm_trust.jhtml',
			data:$('#trustForm').serialize(),
			success: function (data) {
	            alert_msg(data.msg);
	            if(data.code == 1){
	            	window.location.reload();
	            }
	        }
		});
	}else{
		return;
	}
}

//生成二维码
$(document).ready(function(){
	var has_terminal = $("#qr_has_tim").val();
	var user_id = $("#qr_user_id").val();
	if(has_terminal == 1){
		var qrcode= $('#divOne').qrcode("zjc1518"+user_id).hide();   
		 var canvas=qrcode.find('canvas').get(0);  
		 $('#imgOne').attr('src',canvas.toDataURL('image/jpg'))
	}else{
		$("#imgOne").hide();
		
	}	
});

//关闭订单table
function cancle_table(v){
	var table =  v.parentNode.parentNode.parentNode.parentNode;
	$(table).hide();
	$(".mcton_tree,.mcton_four").show();
}
//待支付订单
function wait_pay_order(page){
	//打开table
	$(".mct_one_lis").show();
	$(".mcton_tree,.mcton_four").hide();
	$.get("wait_pay_order.jhtml",{
		"page":page
	},function(data){
		var tr = '';
		$.each(data.list, function(index, o){
			if(o.pay_code == 'mixed_payment'){//混合支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券，"+o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券</td><td>"+format(o.add_time)+"</td></tr>";
			}else{//现金支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}
		});
		$("#order_nowPage").val(data.nowPage);
		$("#order_total").val(data.totalSize);
		$("#order_hasNextPage").val(data.hasNextPage);
		$("#t_order").empty();
		$("#t_order").append(tr);
	});  
}
//代发货订单
function wait_delivery_order(page){
	//打开table
	$(".mct_one_lis").show();
	$(".mcton_tree,.mcton_four").hide();
	$.get("wait_delivery_order.jhtml",{
		"page":page
	},function(data){
		var tr = '';
		$.each(data.list, function(index, o){
			if(o.pay_code == 'mixed_payment'){//混合支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券，"+o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券</td><td>"+format(o.add_time)+"</td></tr>";
			}else{//现金支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}
		});
		$("#order_nowPage").val(data.nowPage);
		$("#order_total").val(data.totalSize);
		$("#order_hasNextPage").val(data.hasNextPage);
		$("#t_order").empty();
		$("#t_order").append(tr);
	});  
}

//代发货订单
function wait_comment_order(page){
	//打开table
	$(".mct_one_lis").show();
	$(".mcton_tree,.mcton_four").hide();
	$.get("wait_comment_order.jhtml",{
		"page":page
	},function(data){
		var tr = '';
		$.each(data.list, function(index, o){
			if(o.pay_code == 'mixed_payment'){//混合支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券，"+o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券</td><td>"+format(o.add_time)+"</td></tr>";
			}else{//现金支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}
		});
		$("#order_nowPage").val(data.nowPage);
		$("#order_total").val(data.totalSize);
		$("#order_hasNextPage").val(data.hasNextPage);
		$("#t_order").empty();
		$("#t_order").append(tr);
	});  
}

//代发货订单
function finish_order(page){
	//打开table
	$(".mct_one_lis").show();
	$(".mcton_tree,.mcton_four").hide();
	$.get("finish_order.jhtml",{
		"page":page
	},function(data){
		var tr = '';
		$.each(data.list, function(index, o){
			if(o.pay_code == 'mixed_payment'){//混合支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券，"+o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}else if(o.pay_code == 'rate' || o.pay_code == 'equal'){//易物券支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.barter_coupons+"券</td><td>"+format(o.add_time)+"</td></tr>";
			}else{//现金支付
				tr +="<tr><td>"+o.order_sn+"</td><td>"+o.user_id+"</td><td>"+o.consignee+"</td><td>"+o.goods_name+"</td><td>"+
				o.cash+"元</td><td>"+format(o.add_time)+"</td></tr>";
			}
		});
		$("#order_nowPage").val(data.nowPage);
		$("#order_total").val(data.totalSize);
		$("#order_hasNextPage").val(data.hasNextPage);
		$("#t_order").empty();
		$("#t_order").append(tr);
	});  
}
//上一页
function order_last_page(){
	$.each($(".cmtoew_ul li"), function(index, el){
		if($(el).hasClass("lic_ok")){
			//获取页码
			var page = parseInt($("#order_nowPage").val());
			if(page == 1){
				return;
			}
			if(index == 0){//待支付
				wait_pay_order(page - 1);
			}else if(index == 1){//待发货
				wait_delivery_order(page - 1);
			}else if(index == 2){//待待评价
				wait_comment_order(page - 1);
			}else if(index == 3){//已完成
				finish_order(page - 1);
			}
		}
	});
}
//下一页
function order_next_page(){
	console.log($("#order_hasNextPage").val());
	if($("#order_hasNextPage").val() == "true"){
		$.each($(".cmtoew_ul li"), function(index, el){
			if($(el).hasClass("lic_ok")){
				var page = parseInt($("#order_nowPage").val())
				if(index == 0){//待支付
					wait_pay_order(page + 1);
				}else if(index == 1){//待发货
					wait_delivery_order(page + 1);
				}else if(index == 2){//待待评价
					wait_comment_order(page + 1);
				}else if(index == 3){//已完成
					finish_order(page + 1);
				}
			}
		});
	}
}

//获取在售商品
function sale_goods(page){
	//打开table
	$(".mct_one_lis_tr").show();
	$(".mcton_four").hide();
	$.get("sale_goods.jhtml", {
		"page":page
	}, function(data){
		console.log(data);
		var tr = "";
		$.each(data.list, function(index, el){
			tr += "<tr><td>"+el.goods_name+"</td><td>"+el.goods_sn+"</td><td>"+el.cat_name+"</td><td>"+
			el.shop_price+"</td><td>"+el.market_price+"</td><td>"+el.store_count+"</td><td>"+el.sales_sum+"</td></tr>";
		});
		$("#goods_nowPage").val(data.nowPage);
		$("#goods_total").val(data.totalSize);
		$("#goods_hasNextPage").val(data.hasNextPage);
		$("#t_goods").empty();
		$("#t_goods").append(tr);
	});
}
//获取缺货商品
function stock_goods(page){
	//打开table
	$(".mct_one_lis_tr").show();
	$(".mcton_four").hide();
	$.get("stock_goods.jhtml", {
		"page":page
	}, function(data){
		var tr = "";
		$.each(data.list, function(index, el){
			tr += "<tr><td>"+el.goods_name+"</td><td>"+el.goods_sn+"</td><td>"+el.cat_name+"</td><td>"+
			el.shop_price+"</td><td>"+el.market_price+"</td><td>"+el.store_count+"</td><td>"+el.sales_sum+"</td></tr>";
		});
		$("#goods_nowPage").val(data.nowPage);
		$("#goods_total").val(data.totalSize);
		$("#goods_hasNextPage").val(data.hasNextPage);
		$("#t_goods").empty();
		$("#t_goods").append(tr);
	});
}
//获取下架商品
function unsale_goods(page){
	//打开table
	$(".mct_one_lis_tr").show();
	$(".mcton_four").hide();
	$.get("unsale_goods.jhtml", {
		"page":page
	}, function(data){
		var tr = "";
		$.each(data.list, function(index, el){
			tr += "<tr><td>"+el.goods_name+"</td><td>"+el.goods_sn+"</td><td>"+el.cat_name+"</td><td>"+
			el.shop_price+"</td><td>"+el.market_price+"</td><td>"+el.store_count+"</td><td>"+el.sales_sum+"</td></tr>";
		});
		$("#goods_nowPage").val(data.nowPage);
		$("#goods_total").val(data.totalSize);
		$("#goods_hasNextPage").val(data.hasNextPage);
		$("#t_goods").empty();
		$("#t_goods").append(tr);
	});
}
//获取热销商品
function sells_hot_goods(page){
	//打开table
	$(".mct_one_lis_tr").show();
	$(".mcton_four").hide();
	$.get("sells_hot_goods.jhtml", {
		"page":page
	}, function(data){
		var tr = "";
		$.each(data.list, function(index, el){
			tr += "<tr><td>"+el.goods_name+"</td><td>"+el.goods_sn+"</td><td>"+el.cat_name+"</td><td>"+
			el.shop_price+"</td><td>"+el.market_price+"</td><td>"+el.store_count+"</td><td>"+el.sales_sum+"</td></tr>";
		});
		$("#goods_nowPage").val(data.nowPage);
		$("#goods_total").val(data.totalSize);
		$("#goods_hasNextPage").val(data.hasNextPage);
		$("#t_goods").empty();
		$("#t_goods").append(tr);
	});
}

//上一页
function goods_last_page(){
	$.each($(".cmtoew_ul_tree li"), function(index, el){
		if($(el).hasClass("lic_ok_tree")){
			//获取页码
			var page = parseInt($("#goods_nowPage").val());
			if(page == 1){
				return;
			}
			if(index == 0){//待支付
				sale_goods(page - 1);
			}else if(index == 1){//待发货
				stock_goods(page - 1);
			}else if(index == 2){//待待评价
				unsale_goods(page - 1);
			}else if(index == 3){//已完成
				sells_hot_goods(page - 1);
			}
		}
	});
}
//下一页
function goods_next_page(){
	if($("#goods_hasNextPage").val() == "true"){
		$.each($(".cmtoew_ul_tree li"), function(index, el){
			if($(el).hasClass("lic_ok_tree")){
				//获取页码
				var page = parseInt($("#goods_nowPage").val());
				if(index == 0){//待支付
					sale_goods(page + 1);
				}else if(index == 1){//待发货
					stock_goods(page + 1);
				}else if(index == 2){//待待评价
					unsale_goods(page + 1);
				}else if(index == 3){//已完成
					sells_hot_goods(page + 1);
				}
			}
		});
	}
}

function create_user_account(){
	//点击后禁用
	$("#create_user_account_btn").attr("disabled", true);
	$.get("create_user_account.jhtml", function(data){
		alert_msg(data.msg);
		$("#create_user_account_btn").attr("disabled", false);
	});
}
</script>
</aos:html>
