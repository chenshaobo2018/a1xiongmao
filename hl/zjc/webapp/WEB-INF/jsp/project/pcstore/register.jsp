﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE>
<html>
<head>
	<meta charset="utf-8"> 
	<meta name="keywords" content="中军创" />
	<meta name="description" content="中军创" /> 
	<title>中军创-注册</title>
	<!-- common css and zjc_index css-->
	<link href="../../../static/css/pcstore/common.css" rel="stylesheet" type="text/css" />
	<link href="../../../static/css/pcstore/h_foot.css" rel="stylesheet" type="text/css" />
	<link href="../../../static/css/pcstore/zjc_index.css" rel="stylesheet" type="text/css" />
	<!-- js -->  
	<script type="text/javascript" src="../../../static/js/pcstore/jquery-1.11.0.min.js"></script> 
	<script src="../../../static/js/pcstore/common.js"></script>  
	<script src="../../../static/js/pcstore/zjc_detail.js"></script>   
	<script src="../../../static/js/pcstore/jquery.idcode.js"></script>    
</head>
<body> 
  <!-- container  start-->
    <div class="container"> 	
	 <!-- 首页及商品header start-->
	  <div class="header_login">	  
	  <!-- header_top nav -->
	    <div class="header_top">
		<div class="header_top_cen clearfix">
		    <ul class="head_ul fr">
			   <li>欢迎光临</li>
			   <li class="coll_register"><a href="register.html">免费注册</a></li> 
			   <li class="coll_li_my" style="display:none;">
				<div id="menu">
					<ul id="nav"> 
						<li class="mainlevel" id="mainlevel_01"><a href="#" target="_blank"><img src="images/icon/icon_tree.png" alt=" " title="" class="cll_icons"/> 中军创<img src="images/icon/botn.png" alt=" " title="" class="cll_icon"/></a>
							<ul id="sub_01">
								<li><a href="user_vip.html" target="_blank">每天签到</a></li>
								<li><a href="user_vip.html" target="_blank">我的中军创</a></li> 
							</ul>
						</li> 
						<div class="clear"></div> 
					</ul>
				</div> 
			   </li>
			   <li class="log_plear"><a href="login.html"><div class="icon_nav greet_index fl"></div> 请登录</a></li> 
			   <li class="coll_li"> 
				<div id="menu">
					<ul id="nav"> 
						<li class="mainlevel" id="mainlevel_01"><a href="coll.html" target="_blank"><img src="Images/icon/head_icon.png" alt=" " title="" class="cll_icons"/> 我的收藏<img src="Images/icon/botn.png" alt=" " title="" class="cll_icon"/></a>
							<ul id="sub_01">
								<li><a href="coll.html" target="_blank">收藏店铺</a></li>
								<li><a href="coll.html" target="_blank">收藏商品</a></li> 
							</ul>
						</li> 
						<div class="clear"></div> 
					</ul>
				</div>  
			   </li>
			   <li class="mob_li"><a href="mobil_service.html"><div class="icon_nav mob_index fl"></div> 手机中军创</a></li>
			   <li><a href="service.html"><div class="icon_nav ser_index fl"></div>客服与投诉</a></li>
				<div class="clear"></div>
			</ul>		 
		</div>
		</div>
		<!-- header_bottom -->
		<div class="header_bottom_login">
		  <div class="header_bottom_cen_log clearfix">
		    <div class="header_bottom_cenl fl">
			   <a href="index.html"><img src="images/logo.png" alt="logo" title="logo" class="cll_icons" width="385" height="149"/></a>
			</div>
		    <div class="header_bottom_cenr fl">
			   <div class="header_bottom_cenrl fl">
			   <div class="log_head">
			       <div class="search_top">
				   <div class="seo fl">
					   <div class="secf_bord fl"></div>
						<div id="searchTxt" class="searchTxt fr" onMouseOver="this.className='searchTxt searchTxtHover';" onMouseOut="this.className='searchTxt';"> 
							<input name="w" type="text" />						
								<div class="searchMenu">							
									<div class="searchSelected" id="searchSelected">全部分类 </div>							
									 <div style="display:none;" class="searchTab" id="searchTab"> 
										<ul>
											<li><a href="#">全部分类</a></li>
											<li><a href="#">平台分类</a></li>
											<li><a href="#">食品分类</a></li>
											<li><a href="#">汽车分类</a></li> 
											<li><a href="#">图书分类</a></li> 
											<li><a href="#">家具分类</a></li>
											<li><a href="#">女鞋分类</a></li>
											<li><a href="#">男装分类</a></li> 
											<li><a href="#">旅行分类</a></li> 
											<li><a href="#">男鞋分类</a></li> 
											<li><a href="#">手机分类</a></li> 
											<li><a href="#">母婴分类</a></li> 
											<li><a href="#">电器分类</a></li> 
											<div class="clear"></div>
										</ul>
									</div>							
								</div> 
						</div>	
						<div class="clear"></div>	
					</div>					
					<div class="searchBtn fr">
						<button id="searchBtn" type="submit"><img src="images/icon/ser_icon.png"></button>
					</div>
					<div class="clear"></div>
				</div>
				<div class="search_a">
					<ul>
					   <li>热搜：<a href="#">中军创云易酒</a></li>
					   <li><a href="#">房车</a></li>
					   <li><a href="#">大米</a></li>
					   <div class="clear"></div>
					</ul>
				</div>	
				</div>					
			   </div>
			     <!-- 没登录 -->
			   <div class="header_bottom_cenrr fr">
			   <div class="login_cartse">
	      <div class="dfde_null"  style="display:none;">
					  <div id="settleup" class="dorpdown">
							<div class="cw-icon">							 
							  <a target="_blank" href="#">购物车</a>
							  <i class="i_img"><img src="images/icon/carts.png"/></i>
							</div><div class="clear"></div>
							<!-- 下拉 -->
							<div class="dorpdown-layer"> 
								<!-- 登录后 -->
								<div class="drop_block">
							      <ul>
								     <li><a href="#">
									   <div class="drop_side fl">
									    <i class=""><img src="images/icon/head_cart.png" alt="购物车" title="购物车"/></i>
									   </div>
									   <div class="drop_cont fr">
									     <p>劵:424</p>
									     <a href="#">删除</a>
									   </div>
										<div class="clear"></div></a>
									 </li>
								     <li><a href="#">
									   <div class="drop_side fl">
									    <i class=""><img src="images/icon/head_cart.png" alt="购物车" title="购物车"/></i>
									   </div>
									   <div class="drop_cont fr">
									     <p>劵:424</p>
									     <a href="#">删除</a>
									   </div>
										<div class="clear"></div></a>
									 </li>
								     <li><a href="#">
									   <div class="drop_side fl">
									    <i class=""><img src="images/icon/head_cart.png" alt="购物车" title="购物车"/></i>
									   </div>
									   <div class="drop_cont fr">
									     <p>劵:424</p>
									     <a href="#">删除</a>
									   </div>
										<div class="clear"></div></a>
									 </li>
								     <li><a href="#">
									   <div class="drop_side fl">
									    <i class=""><img src="images/icon/head_cart.png" alt="购物车" title="购物车"/></i>
									   </div>
									   <div class="drop_cont fr">
									     <p>劵:424</p>
									     <a href="#">删除</a>
									   </div>
										<div class="clear"></div></a>
									 </li><div class="clear"></div>
								  </ul>								  
								</div> 
							</div>
						  </div>
					</div>
			      <div class="dfde_block">
					  <div id="settleup" class="dorpdown">
							<div class="cw-icon">							 
							  <a target="_blank" href="#">购物车</a>
							  <i class="i_img"><img src="images/icon/carts.png"/></i>
							</div><div class="clear"></div>
							<!-- 下拉 -->
							<div class="dorpdown-layer">
							<!-- 没登录 -->
								<div class="drop_none clearfix">
							      <div class="drop_noneside fl">
								      <i class=""><img src="images/icon/cart_null.png"/></i>
								  </div>
							      <div class="drop_nonecont fr">
								    <a href="#">还没有商品<br/>快去选购吧~</a>
								  </div> 
								</div> 
							</div>
						  </div>
					</div>			 
			   </div> 		 
			   </div> 
			   <div class="clear"></div>			
			 </div> 
		  </div> 
		</div> 
	  </div>
	 <!-- header end-->
	  <div class="mian_login">
		<div class="register_one">
	    <div class="mian_login_one">
		中军创云易会员注册		
		</div>
		  <div class="register_onel">
		 <form>
		      <div class="register_onel_one clearfix">
			     <div class="register_onel_onel fl">
				   <p class="zh_name">分 &nbsp;&nbsp;&nbsp;享&nbsp;&nbsp; &nbsp;ID</p>
				 </div>
			     <div class="register_onel_oner fr">
				   <input type="text" class="red_text_one" placeholder="输入你的分享ID"/>
				 </div> 	  
			  </div>
		      <div class="register_onel_one clearfix">
			     <div class="register_onel_onel fl">
				   <p class="zh_name">账号登录(会员ID)</p>
				 </div>
			     <div class="register_onel_oner fr">
				   <input type="text" class="red_text_one" placeholder="输入你的手机号"/>
				 </div> 		  
			  </div> 			  
		      <div class="register_onel_one clearfix">
			     <div class="register_onel_onel fl">
				   <p class="zh_name">验 证 码</p>
				 </div>
			     <div class="register_yzms fr">
				   <input type="text" class="red_text_one_yzm fl" placeholder="输入右边验证码" id="Txtidcode" />
				   <span><img src="/aosuite/notokenapi/app/v1/ValidateCode.jhtml" onclick="changeImage(this)" alt="换一张" style="cursor:hand"></span>
				   
				 </div> 					 
			  </div> 	  
		      <div class="register_onel_one clearfix">
			     <div class="register_onel_onel fl">
				   <p class="zh_name">手机验 证 码</p>
				 </div>
			     <div class="register_yzms fr">
				   <input type="text" class="red_text_one_yzmmb" placeholder="输入你的手机验证码"/> 
				    <button class="rep_ok">获取手机验证码</button> 
				 </div> 		  
			  </div> 
		      <div class="register_onel_one clearfix">
			     <div class="register_onel_onel fl">
				   <p class="zh_name">设 置 登 录 密 码</p>
				 </div>
			     <div class="register_onel_oner fr">
				   <input type="text" class="red_text_one" placeholder="请输入6-10位数字+字母"/>
				 </div> 		  
			  </div> 
			  <div class="register_onel_four">
			    <input type="submit" value="立即注册" class="sub_btn"/>
			  </div>
			</form>		  
		  </div> 
		</div> 
	  </div>
	  <!-- footer start -->
	  <div class="footer">
	    <div class="footer_top">
		   <div class="footer_top_cen">
		    <ul> 
				<li class="foot_lione">
					 <div class="zjxli_one clearfix">
						<div class="zjxli_onel fl"><img src="images/icon/foot (1).png" alt="品质保证" title="品质保证" width="32" height="32"/></div>
						<div class="zjxli_oner fr"><p class="pinz">品质保证</p><span class="foot_span">商品严格审查上架</span></div> 
					 </div>
				 </li>
				<li class="foot_litwo">
					 <div class="zjxli_one clearfix">
						<div class="zjxli_onel fl"><img src="images/icon/foot (2).png" alt="担保交易" title="担保交易" width="32" height="32"/></div>
						<div class="zjxli_oner fr"><p class="pinz">担保交易</p><span class="foot_span">10天平台担保交易</span></div> 
					 </div>
				 </li>
				<li class="foot_litree">
					 <div class="zjxli_two clearfix">
						<div class="zjxli_onel fl"><img src="images/icon/foot (3).png" alt="特色服务体验" title="特色服务体验" width="32" height="32"/></div>
						<div class="zjxli_oner fr"><p class="pinz">特色服务体验</p><span class="foot_span">为你提供家一样的感觉</span></div> 
					 </div>
				 </li>
				<li class="foot_lifour">
					 <div class="zjxli_two clearfix">
						<div class="zjxli_onel fl"><img src="images/icon/foot (4).png" alt="帮助中心" title="帮助中心" width="32" height="32"/></div>
						<div class="zjxli_oner fr"><p class="pinz">帮助中心</p><span class="foot_span"><a href="#">新手指南</a></span><span class="foot_span"><br/><a href="#">消费指示</a></span></div>
					  </div>
				 </li>
				<li class="foot_lifour">
					 <div class="zjxli_two">
						<p class="pinz help_cen">微信订阅号</p>
						<img src="images/icon/wei.png" alt="微信订阅号" title="微信订阅号" width="48" height="48"/>
					 </div> 
				 </li>
				 <div class="clear"></div>
			 </ul>
		  </div>
		   </div>
		   <div class="footer_bottom">
		     <div class="footer_bot_cen">
			    <p class="text_p"><span class="span_one">增值电信业务经营许可证：京a8-888888</span>     |     <span class="span_two">京公网安备案号：88888888</span></p>
			    <p class="text_p"><span class="spans_one">copyright © 2017</span><span class="spans_two">北京中军创云易物联网科技有限公司版权所有</span>     |     <span class="spans_tree">备案号：京icp备888888号</span></p>
			 </div>		    
		   </div>
		</div>  
	  <!-- footer end --> 
	</div>	
  <!-- container  end-->
<!-- yzm   -->
  <script>
			$.idcode.setCode();
			
			$("#btns").click(function (){
				var IsBy = $.idcode.validateCode(); 
				alert(IsBy);
				console.log(IsBy);
			});
			
			 function changeImage(img)  
		        {  
		            //破坏浏览器缓存  
		            img.src = img.src + "?" + new Date().getTime();  
		        }  
  </script>
</body>
</html>