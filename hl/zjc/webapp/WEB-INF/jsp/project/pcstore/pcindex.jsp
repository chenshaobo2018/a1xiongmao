<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>中军创云易</title>
<!-- common css and zjc_index css-->
<link href="../../../static/css/pcstore/common.css" rel="stylesheet"
	type="text/css" />
<link href="../../../static/css/pcstore/h_foot.css" rel="stylesheet"
	type="text/css" />
<link href="../../../static/css/pcstore/zjc_index.css" rel="stylesheet"
	type="text/css" />
<!-- js -->
<script src="../../../static/js/pcstore/jquery-1.11.0.min.js"></script>
<!-- common js and zjc_index -->
<script src="../../../static/js/pcstore/common.js"></script>
<script src="../../../static/js/pcstore/zjc_index.js"></script>

<!-- bannar切换 -->
<script type="text/javascript"
	src="../../../static/js/pcstore/jstxdm.js"></script>
</head>
<body>
	<!-- container  start-->
	<div class="container">
		<!-- 首页及商品header start-->
		<div class="header_index">
			<!-- header_top nav -->
			<div class="header_top">
				<div class="header_top_cen clearfix">
					<ul class="head_ul fr">
						<li>欢迎光临</li>
						<li class="coll_register"><a href="register.html">免费注册</a></li>
						<li class="coll_li_my" style="display: none;">
							<div id="menu">
								<ul id="nav">
									<li class="mainlevel" id="mainlevel_01"><a
										href="javascript:void(0);" target="_blank"><img
											src="../../../static/image/pcstore/icon/icon_tree.png"
											alt=" " title="" class="cll_icons" /> 中军创<img
											src="../../../static/image/pcstore/icon/botn.png" alt=" "
											title="" class="cll_icon" /></a>
										<ul id="sub_01">
											<li><a href="coll.html" target="_blank">每天签到</a></li>
											<li><a href="coll.html" target="_blank">我的中军创</a></li>
										</ul></li>
									<div class="clear"></div>
								</ul>
							</div>
						</li>
						<li class="log_plear"><a href="login.html"><div
									class="icon_nav greet_index fl"></div> 请登录</a></li>
						<li class="coll_cart"><a href="user_cart.html"><div
									class="icon_nav cart_index fl"></div> 我的购物车</a></li>
						<li class="coll_li">
							<div id="menu">
								<ul id="nav">
									<li class="mainlevel" id="mainlevel_01"><a
										href="javascript:void(0);" target="_blank"><img
											src="../../../static/image/pcstore/icon/ertw.png" alt=" "
											title="" class="cll_icons" width="18" height="16" /> 我的收藏<img
											src="../../../static/image/pcstore/icon/botn.png" alt=" "
											title="" class="cll_icon" /></a>
										<ul id="sub_01">
											<li><a href="coll.html" target="_blank">收藏店铺</a></li>
											<li><a href="coll.html" target="_blank">收藏商品</a></li>
										</ul></li>
									<div class="clear"></div>
								</ul>
							</div>
						</li>
						<li class="mob_li"><a href="mobil_service.html"><div
									class="icon_nav mob_index fl"></div> 手机中军创</a></li>
						<li><a href="service.html"><div
									class="icon_nav ser_index fl"></div>客服与投诉</a></li>
						<div class="clear"></div>
					</ul>
				</div>
			</div>
			<!-- header_bottom -->
			<div class="header_bottom">
				<div class="header_bottom_cen clearfix">
					<div class="header_bottom_cenl fl">
						<a href="index.html"><img
							src="../../../static/image/pcstore/logo.png" alt="logo"
							title="logo" class="cll_icons" width="385" height="149" /></a>
					</div>
					<div class="header_bottom_cenr fl">
						<div class="header_bottom_cenrl fl">
							<div class="search_top">
								<div class="seo fl">
									<div class="secf_bord fl"></div>
									<div id="searchTxt" class="searchTxt"
										onmouseover="this.className='searchTxt searchTxtHover';"
										onmouseout="this.className='searchTxt';">
										<input name="w" type="text">
										<div class="searchMenu">
											<div class="searchSelected" id="searchSelected">全部分类</div>
											<div style="display: none;" class="searchTab" id="searchTab">
												<ul>
													<li><a href="javascript:void(0);">全部分类</a></li>
													<li><a href="javascript:void(0);">平台分类</a></li>
													<li><a href="javascript:void(0);">食品分类</a></li>
													<li><a href="javascript:void(0);">汽车分类</a></li>
													<li><a href="javascript:void(0);">图书分类</a></li>
													<li><a href="javascript:void(0);">家具分类</a></li>
													<li><a href="javascript:void(0);">女鞋分类</a></li>
													<li><a href="javascript:void(0);">男装分类</a></li>
													<li><a href="javascript:void(0);">旅行分类</a></li>
													<li><a href="javascript:void(0);">男鞋分类</a></li>
													<li><a href="javascript:void(0);">手机分类</a></li>
													<li><a href="javascript:void(0);">母婴分类</a></li>
													<li><a href="javascript:void(0);">电器分类</a></li>
													<div class="clear"></div>
												</ul>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<div class="searchBtn fr">
									<button id="searchBtn" type="submit">
										<img src="../../../static/image/pcstore/icon/ser_icon.png">
									</button>
								</div>
								<div class="clear"></div>
							</div>
							<div class="search_a">
								<ul>
									<li>热搜：<a href="javascript:void(0);">中军创云易酒</a></li>
									<li><a href="javascript:void(0);">房车</a></li>
									<li><a href="javascript:void(0);">大米</a></li>
									<div class="clear"></div>
								</ul>
							</div>
							<div class="head_nav">
								<ul>
									<li><a href="index.html" class="click_hover">平台首页</a></li>
									<li><a href="good_product.html">优选商品</a></li>
									<li><a href="javascript:void(0);">购商品</a></li>
									<li><a href="javascript:void(0);">购实惠</a></li>
									<li><a href="javascript:void(0);">易生活</a></li>
									<div class="clear"></div>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- header end-->
		<!-- main start-->
		<div class="main">
			<div class="main_bannar">
				<!-- sidebar -->
				<div class="sidebar fl">
					<div class="nav-wrap">
						<div class="nav">
							<div class="goods">
								<h2 class="title clearfix">
									<div class="sum_pro fl">全部商品分类</div>
									<div class="title_fr fr">
										<img src="../../../static/image/pcstore/icon/titlea.png">
									</div>
								</h2>
								<div class="all-goods">
									<!-- 循环开始 -->
									<c:forEach items="${categoryList}" var="goodsCategoryVO">
										<div class="item">
											<div class="product">
												<h3 class="clearfix">
													<div class="nv_text fl">
														<a href="javascript:void(0);">${goodsCategoryVO.name }</a>
													</div>
													<div class="con_fr fr">
														<img src="../../../static/image/pcstore/icon/cont.png">
													</div>
												</h3>
											</div>
											<!-- 方式1 -->
											<div class="product-wrap pos01">
												<div class="pro_conent">
													<div class="pro_conentr fr">
														<ul class="pr_ul">
															<c:forEach items="${goodsCategoryVO.tmenu}" var="childVO">
																<li><a href="javascript:void(0);">${childVO.name}</a></li>
															</c:forEach>
														</ul>
														<div class="clear"></div>
													</div>
													<div class="clear"></div>
												</div>
											</div>
											<!-- 方式2 -->
											<%-- <div class="product-wrap pos01">
												<c:forEach items="${goodsCategoryVO.tmenu}" var="childVO">
													<div class="pro_conent">
														<div class="pro_conentl fl">
													        <div class="fl"><p>${childVO.name}</p></div>  <p class="fr"><img src="../../../static/image/pcstore/icon/right_jt.png" class="men_img"></p>
														    <div class="clear"></div>
												    	</div>
												    	<div class="clear"></div>
											    	</div>
										    	</c:forEach>
											</div> --%>
										</div>
									</c:forEach>
									<!-- 循环结束 -->
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- content -->
				<div class="content fr">
					<div class="side_ont fl">
						<!-- bannar img-->
						<div class="side_ont_top">
							<div id="demo01" class="flexslider">
								<ul class="slides">
									<c:forEach items="${goodsAdlist }" var="goodsAd">
										<li><div class="img">
													<a href="javascript:void(0);"><img
														src="${goodsAd.ad_link }"
														height="518" width="1198" alt="bannar" title="bannar" /></a>
												</div></li>
									</c:forEach>
									<div class="clear"></div>
								</ul>
							</div>
						</div>c
						<div class="side_ont_bottom">
							<ul>
								<li>
									<div class="side_good">今日热销</div> <a href="javascript:void(0);"><img
										src="../../../static/image/pcstore/product/pr_one.png"
										alt="今日特价" title="今日特价" width="592" height="194" /></a>
								</li>
								<li>
									<div class="side_good">今日特价</div> <a href="javascript:void(0);"><img
										src="../../../static/image/pcstore/product/pr_two.png"
										alt="今日特价" title="今日特价" width="592" height="194" /></a>
								</li>
								<div class="clear"></div>
							</ul>
						</div>
					</div>
					<!-- side_onr -->
					<div class="side_onr fr">
						<div class="vip_login_index">
							<a href="javascript:void(0);">会员登录</a>
						</div>
						<div class="web_zjc">
							欢迎来到<br />中军创云易平台
						</div>
						<div class="side_title">平台资讯 / 公告</div>
						<div class="side_con_ul">
							<c:forEach items="${messagePOs }" var="message">
								<div class="dsdio">
									<a href="javascript:void(0);" title="${message.title }">
										<div class="sdeou">
											<div id="triangle_right" class="fl"></div>
											<div class="ul_text fl">${message.title }</div>
											<div class="clear"></div>
										</div>
									</a>
								</div>
							</c:forEach>
							<div class="clear"></div>
						</div>
						<div class="side_more">
							<a href="javascript:void(0);">更多</a>
						</div>
						<div class="side_star">
							<p>今日推荐明星店铺</p>
							<div class="sdie_img">
								<a href="javascript:void(0);"><img
									src="../../../static/image/pcstore/star_logo.png" width="110"
									height="110" alt="推荐明星店铺" title="推荐明星店铺" /></a>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!-- adv -->
			<div class="main_adv">
				<a href="javascript:void(0);"><img
					src="../../../static/image/pcstore/adv_one.png" alt="adv"
					title="adv" width="1200" height="150"></a>
			</div>
			<!-- sell_pr -->
			<div class="sell_pr clearfix">
				<div class="sell_prl fl">
					<div class="sells_one">
						<p>中军创云易平台</p>
					</div>
					<div class="sells_two">
						<div class="sells_top">最热销商品</div>
						<div class="sells_img">
							<c:forEach items="${highHotGoodsList }" var="highHotGoods">
								<a href="javascript:void(0);"><img
									src="${highHotGoods.original_img }"
									alt="${highHotGoods.goods_name}"
									title="${highHotGoods.goods_name}" width="457" height="457" />
								</a>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="sell_prr fr">
					<!-- hover -->
					<div class="tabpanel">
						<div class="tabs_ul">
							<ul class="fr">
								<li class="hit">最新上架</li>
								<li>热销商品</li>
								<li>特价商品</li>
							</ul>
							<div class="clear"></div>
						</div>
						<div class="panes">
							<!-- 最新上架 -->
							<div class="pane" style="display: block;">
								<div class="new_paway">
									<ul>
										<c:forEach items="${newGoodsList }" var="newGoods">
											<li><a href="javascript:void(0);"><img
													src="${newGoods.original_img }" width="309" height="309"
													title="${newGoods.goods_name}" alt="${newGoods.goods_name}" /></a>
												<p class="cart_enew_new">
													<a href="javascript:void(0);"
														title="${newGoods.goods_name}">${newGoods.goods_name}</a>
												</p>
												<div class="cart_text">
													<div class="money_yi_new_mon">
														<p
															title="<fmt:formatNumber type="number" value="${newGoods.market_price }" maxFractionDigits="0"/>券">
															<fmt:formatNumber type="number"
																value="${newGoods.market_price }" maxFractionDigits="0" />
															券
														</p>
													</div>
												</div></li>
										</c:forEach>
									</ul>
									<div class="clear"></div>
								</div>
							</div>
							<!-- 热销商品 -->
							<div class="pane">
								<div class="new_paway">
									<ul>
										<c:forEach items="${hotGoodsList }" var="hotGoods">
											<li><a href="javascript:void(0);"><img
													src="${hotGoods.original_img }" width="309" height="309"
													title="${hotGoods.goods_name}" alt="${hotGoods.goods_name}" /></a>
												<p class="cart_enew_new">
													<a href="javascript:void(0);"
														title="${hotGoods.goods_name}">${hotGoods.goods_name}</a>
												</p>
												<div class="cart_text">
													<div class="money_yi_new_mon">
														<p
															title="<fmt:formatNumber type="number" value="${hotGoods.market_price }" maxFractionDigits="0"/>券">
															<fmt:formatNumber type="number"
																value="${hotGoods.market_price }" maxFractionDigits="0" />
															券
														</p>
													</div>
												</div></li>
										</c:forEach>
									</ul>
									<div class="clear"></div>
								</div>
							</div>
							<!-- 特价商品 -->
							<div class="pane">
								<div class="new_paway">
									<ul>
										<c:forEach items="${specialGoodsList }" var="specialGoods">
											<li><a href="javascript:void(0);"><img
													src="${specialGoods.original_img }" width="309"
													height="309" title="${specialGoods.goods_name}"
													alt="${specialGoods.goods_name}" /></a>
												<p class="cart_enew_new">
													<a href="javascript:void(0);"
														title="${specialGoods.goods_name}">${specialGoods.goods_name}</a>
												</p>
												<div class="cart_text">
													<div class="money_yi_new_mon">
														<p
															title="<fmt:formatNumber type="number" value="${specialGoods.market_price }" maxFractionDigits="0"/>券">
															<fmt:formatNumber type="number"
																value="${specialGoods.market_price }"
																maxFractionDigits="0" />
															券
														</p>
													</div>
												</div></li>
										</c:forEach>
									</ul>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 品牌 -->
			<div class="star">
				<!-- 购商品 -->
				<div class="star_one">
					<img src="../../../static/image/pcstore/pin.jpg" />
				</div>
				<div class="star_tree">
					<ul>
						<c:forEach items="${shopGoods}" var="sGoods">
							<li><a href="javascript:void(0);"><img
									src="${sGoods.original_img }" width="280" height="280"
									title="${sGoods.goods_name}" alt="${sGoods.goods_name}" /></a>
								<p class="cart_enew">
									<a href="javascript:void(0);" title="${sGoods.goods_name}">${sGoods.goods_name}</a>
								</p>
								<div class="cart_text">
									<div class="money_yi_more">
										<p
											title="<fmt:formatNumber type="number" value="${sGoods.market_price }" maxFractionDigits="0"/>券">
											<fmt:formatNumber type="number"
												value="${sGoods.market_price }" maxFractionDigits="0" />
											券
										</p>
									</div>
								</div></li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- 购实惠 -->
				<div class="star_one">
					<img src="../../../static/image/pcstore/gsh.png" />
				</div>
				<div class="star_tree_x">
					<ul>
						<c:forEach items="${substantialGoods}" var="tialGoods">
							<li><a href="javascript:void(0);"><img
									src="${tialGoods.original_img }" width="280" height="280"
									title="${tialGoods.goods_name}" alt="${tialGoods.goods_name}" /></a>
								<p class="cart_enew">
									<a href="javascript:void(0);" title="${tialGoods.goods_name}">${tialGoods.goods_name}</a>
								</p>
								<div class="cart_text">
									<div class="money_yi_more">
										<p
											title="<fmt:formatNumber type="number" value="${tialGoods.market_price }" maxFractionDigits="0"/>券">
											<fmt:formatNumber type="number"
												value="${tialGoods.market_price }" maxFractionDigits="0" />
											券
										</p>
									</div>
								</div></li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- 易生活 -->
				<div class="star_one">
					<img src="../../../static/image/pcstore/yi.jpg" />
				</div>
				<div class="star_tree_y">
					<ul>
						<c:forEach items="${changeSelfGoods}" var="selfGoods">
							<li><a href="javascript:void(0);"><img
									src="${selfGoods.original_img }" width="280" height="280"
									title="${selfGoods.goods_name}" alt="${selfGoods.goods_name}" /></a>
								<p class="cart_enew">
									<a href="javascript:void(0);" title="${selfGoods.goods_name}">${selfGoods.goods_name}</a>
								</p>
								<div class="cart_text">
									<div class="money_yi_more">
										<p
											title="<fmt:formatNumber type="number" value="${selfGoods.market_price }" maxFractionDigits="0"/>券">
											<fmt:formatNumber type="number"
												value="${selfGoods.market_price }" maxFractionDigits="0" />
											券
										</p>
									</div>
								</div></li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="star_four">
					<a href="javascript:void(0);"> <img
						src="../../../static/image/pcstore/adv_two.png" width="1200"
						height="150" alt="adv" title="adv" /></a>
				</div>
				<div class="st_ou">
					<img src="../../../static/image/pcstore/ok_xu.png" width="1850"
						height="8" alt="adv" title="adv" />
				</div>
			</div>
			<!-- 产品 -->
			<div class="zjc_product clearfix">
				<div class="zjc_lfl fl">
					<div class="zjc_product_top">
						<div class="zjc_product_head">
							<div class="zjc_product_headl fl">
								<p class="zjc_po">平台产品 / 教育培训 / 会议门票</p>
							</div>
							<div class="zjc_product_headr fr">
								<a href="javascript:void(0);" class="mores">更多 <img
									src="../../../static/image/pcstore/icon/cont.png" alt="更多"
									title="更多" /></a>
							</div>
							<div class="clear"></div>
						</div>
						<div class="zjc_product_content">
							<ul class="piro">
								<!-- 平台产品、教育、门票 -->
								<c:forEach items="${myGoods }" var="mGoods">
									<li><a href="javascript:void(0);"><img
											src="${mGoods.original_img }" width="292" height="292"
											alt="${mGoods.goods_name}" title="${mGoods.goods_name}" /></a>
										<p class="cart_enew">
											<a href="javascript:void(0);" title="${mGoods.goods_name}">${mGoods.goods_name}</a>
										</p>
										<div class="cart_text_more">
											<div class="money_yi_mon">
												<p
													title="<fmt:formatNumber type="number" value="${mGoods.market_price }" maxFractionDigits="0"/>券">
													<fmt:formatNumber type="number"
														value="${mGoods.market_price }" maxFractionDigits="0" />
													券
												</p>
											</div>
										</div></li>
								</c:forEach>
							</ul>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="zjc_lfr fr">
					<div class="zjc_product_top">
						<div class="zjc_product_head">
							<div class="zjc_product_headl fl">
								<p class="zjc_po">食品 / 茶酒 / 饮料 / 特产</p>
							</div>
							<!-- 846 880 2 20 -->
							<div class="zjc_product_headr fr">
								<a href="javascript:void(0);" class="mores">更多 <img
									src="../../../static/image/pcstore/icon/cont.png" alt="更多"
									title="更多" /></a>
							</div>
							<div class="clear"></div>
						</div>
						<div class="zjc_product_content">
							<!-- 食品、茶酒、饮料、特产 -->
							<ul class="piro">
								<c:forEach items="${moreTypeGoods}" var="drink">
									<li><a href="javascript:void(0);"><img
											src="${drink.original_img }" width="292" height="292"
											alt="${drink.goods_name}" title="${drink.goods_name}" /> </a>
										<p class="cart_enew">
											<a href="javascript:void(0);" title="${drink.goods_name}">${drink.goods_name}</a>
										</p>
										<div class="cart_text_more">
											<div class="money_yi_mon">
												<p
													title="<fmt:formatNumber type="number" value="${drink.market_price }" maxFractionDigits="0"/>券">
													<fmt:formatNumber type="number"
														value="${drink.market_price }" maxFractionDigits="0" />
													券
												</p>
											</div>
										</div></li>
								</c:forEach>
							</ul>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="adv_tree">
				<a href="javascript:void(0);"><img
					src="../../../static/image/pcstore/adv_tree.png" alt="adv"
					title="adv" width="1200" height="150" /></a>
			</div>
		</div>
		<!-- main end-->
		<!-- footer start -->
		<div class="footer">
			<div class="footer_top">
				<div class="footer_top_cen">
					<ul>
						<li class="foot_lione">
							<div class="zjxli_one">
								<div class="zjxli_onel fl">
									<img src="../../../static/image/pcstore/icon/foot (1).png"
										alt="品质保证" title="品质保证" width="32" height="32" />
								</div>
								<div class="zjxli_oner fr">
									<p class="pinz">品质保证</p>
									<span class="foot_span">商品严格审查上架</span>
								</div>
								<div class="clear"></div>
							</div>
						</li>
						<li class="foot_litwo">
							<div class="zjxli_one">
								<div class="zjxli_onel fl">
									<img src="../../../static/image/pcstore/icon/foot (2).png"
										alt="担保交易" title="担保交易" width="32" height="32" />
								</div>
								<div class="zjxli_oner fr">
									<p class="pinz">担保交易</p>
									<span class="foot_span">10天平台担保交易</span>
								</div>
								<div class="clear"></div>
							</div>
						</li>
						<li class="foot_litree">
							<div class="zjxli_two">
								<div class="zjxli_onel fl">
									<img src="../../../static/image/pcstore/icon/foot (3).png"
										alt="特色服务体验" title="特色服务体验" width="32" height="32" />
								</div>
								<div class="zjxli_oner fr">
									<p class="pinz">特色服务体验</p>
									<span class="foot_span">为你提供家一样的感觉</span>
								</div>
								<div class="clear"></div>
							</div>
						</li>
						<li class="foot_lifour">
							<div class="zjxli_two">
								<div class="zjxli_onel fl">
									<img src="../../../static/image/pcstore/icon/foot (4).png"
										alt="帮助中心" title="帮助中心" width="32" height="32" />
								</div>
								<div class="zjxli_oner fr">
									<p class="pinz">帮助中心</p>
									<span class="foot_span"><a href="javascript:void(0);">新手指南</a></span><span
										class="foot_span"><br />
									<a href="javascript:void(0);">消费指示</a></span>
								</div>
								<div class="clear"></div>
							</div>
						</li>
						<li class="foot_lifour">
							<div class="zjxli_two">
								<p class="pinz help_cen">微信订阅号</p>
								<img src="../../../static/image/pcstore/icon/wei.png"
									alt="微信订阅号" title="微信订阅号" width="48" height="48" />
							</div>
						</li>
						<div class="clear"></div>
					</ul>
				</div>
			</div>
			<div class="footer_bottom">
				<div class="footer_bot_cen">
					<p class="text_p">
						<span class="span_one">增值电信业务经营许可证：京a8-888888</span> | <span
							class="span_two">京公网安备案号：88888888</span>
					</p>
					<p class="text_p">
						<span class="spans_one">copyright © 2017</span><span
							class="spans_two">北京中军创云易物联网科技有限公司版权所有</span> | <span
							class="spans_tree">备案号：京icp备888888号</span>
					</p>
				</div>
			</div>
		</div>
		<!-- footer end -->
	</div>
	<!-- container  end-->
</body>
</html>