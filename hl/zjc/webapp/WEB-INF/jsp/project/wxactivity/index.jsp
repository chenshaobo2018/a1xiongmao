<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>分享助力首页</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/share_power.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/swiper.min.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/handlebars.js" type="text/javascript"
	charset="utf-8"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="../../../static/js/sha1.js"></script>
<script type="text/javascript"
	src="../../../static/js/wxconfig.js"></script>
<!-- 滑动商品模板 -->
<script type="text/x-handlebars-template" id="swiperTpl">
	{{#each this}}
		<div class="swiper-slide">
			<input type="hidden" value="{{goods_id}}">
			<input type="hidden" value="{{original_img}}">
			<p class="img_p"> <img src="{{original_img}}" class="mob" onClick="toGoodsDetial({{goods_id}})"/> </p>
			<p class="slide_text center" onClick="toGoodsDetial({{goods_id}})">{{goods_name}}</p>
		    <p class="slide_span">累计<span>{{market_price}}</span>助力积分免费领取</p>
       </div>
	{{/each }}
</script>
<script type="text/x-handlebars-template" id="powerDataTpl">
	    <div class="sarmdt_sidetree fl">
		<img src="../../../static/image/wxactivity/zl.png" class="sixs_img" /><br/>
		<p><a href="initShareInte.jhtml">
			{{#if this.market_price}}
				<span>{{this.share_integral}}</span>
			{{else}}
				<span>0</span>
			{{/if}}
		<img src="../../../static/image/wxactivity/jt.png"class="img_cj"></a></p>
	</div>
</script>

</head>
<body>
	<div class="share_container">
	<div class="shareone_img"> 
	<div class="con_sh_title clearfix">
			<div class="cont_fd fr">奖励规则</div>
		</div>
	</div>
		
		<!-- 遮罩 -->
		<div class="ze_make">
			<p class="zd_one center">规则说明</p>
			<div class="zd_two">
				<p>1、选择您想要的狗年好礼，邀请好友为你助力领取，分享好友无上限。</p>
				<p>2、每新注册用户，可收到中军创赠送的158易物卷兑换好礼；每助力成功一名好友，可获赠68好友赠送易物券。</p>
				<p>3、参与活动同时在转盘抽奖赢好礼活动中凭运气抽取新年好礼，分享朋友圈可获得再次抽奖的机会。</p>
				<p>4、同一好友只能帮同一用户助力一次，若将活动链接分享至朋友圈。（说明：手机号、ID等信息、银行信息一致或指向同一用户的，视为同一用户）</p>
				<p>5、抽奖礼品数量有限，先到先得，发完为止。</p>
				<p>6、成功抽取/领取到虚拟卷类礼品后，点击个人中心查看虚拟卷信息；抽取/领取到实物奖品后，需填写有效的收取地址，联系方式等信息。</p>
			</div>
		</div>

		<div class="ze_make_copy"></div>
		<div class="sarm_one">
			<div class="sma_imgsha">
				<img src="../../../static/image/wxactivity/one.png" />
			</div>
			<div class="sarm_tree">
				<div class="swiper-container">
					<div class="swiper-wrapper"></div>
					<!-- Add Arrows -->
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>

			</div>
			<!-- 遮罩 -->
			<div class="ze_makebtn">
				<div class="clearfix">
					<div class="fr">
						<img src="../../../static/image/wxactivity/cgt.png"
							class="msl_img">
					</div>
				</div>
				<p class="zue_texts center">请点击右上角</p>
				<p class="zue_texts center">将它发送给指定的朋友</p>
				<p class="zue_texts center">或分享到朋友圈</p>
			</div>

			<div class="ze_make_copybtn"></div>
			<div class="sarm_five">
				<button class="ok_btn">立即分享</button>

				<p>邀请好友帮忙“助力”免费领取</p>
				<p class="my_href">
				<c:if test="${sessionScope.openid != null && sessionScope.openid != ''}">
				<a href="https://zjc1518.com/aosuite/wxact/activity/v1/initShareProduction.jhtml">我的助力记录 ></a>
				</c:if>
				<c:if test="${sessionScope.openid == null || sessionScope.openid == ''}">
				<a href="https://zjc1518.com/aosuite/wxact/activity/v1/initLogin.jhtml">我的助力记录 ></a>
				</c:if>		
				
				</p>
			</div>
		</div>
		<div class="sarms_tree">
			<div class="sma_imgsha_one">
				<img src="../../../static/image/wxactivity/href_shop.png" />
			</div>
			<div id="mescroll" class="mescroll">
				<div id="goodsList" class="good_list"></div>
			</div>			
		</div>
	</div>
	<script src="../../../static/js/wxactivity/mescroll.min.js"></script>
	<script src="../../../static/js/wxactivity/swiper.min.js"></script>
	<script>
	var scId;
	var scImg;
		$(function(){ 
			$.ajax({
				url : "wxShareUser.jhtml",
				type : 'POST',
				success : function(data) {
					console.log(data);
				}
			});
		}); 
		//回调方法
		function shareActivityGoods(goods_id,share_id){
			$.ajax({ 
				url : "shareActivityGoodsUser.jhtml",
				type : 'POST',
				data : {
					share_open_id : share_id,
					goods_id : goods_id
				},
				success: function(data){
					console.log("分享回调结果"+data);
		      	}
			});
		}
		//进度条
		var precent = parseInt(('${current}' / '${total}') * 100);
		$(".asrl").width(precent + "%");
		//获取首页的滚动商品
		$.post("shareHomePageGoods.jhtml", function(data) {
			data.msg = '${goods_id}';
			if (data.msg == "") {//直接进入首页
				var temeplte = Handlebars.compile($("#swiperTpl").html());
				$(".swiper-wrapper").html(temeplte(data.data));
				var swiper = new Swiper('.swiper-container', {
					navigation : {
						nextEl : '.swiper-button-next',
						prevEl : '.swiper-button-prev',
					},
					//loop : true,
				});
				scId=$(".swiper-slide-active").find("input").eq(0).val();
				scImg = $(".swiper-slide-active").find("input").eq(1).val();
				wx.ready(function() {
					wx.onMenuShareAppMessage({
						title : '分享助力', // 分享标题
						desc : '我正在春节好礼0元换活动中换取商品，快来帮我助力！一起领春节礼物！！', // 分享描述
						link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareDetail.jhtml?goods_id='
								+ scId
								+ '&shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
						imgUrl : scImg, // 分享图标
						success : function() {
							// 用户确认分享后执行的回调函数
							var shareId = '${sessionScope.openid}';
							shareActivityGoods(scId,shareId);
						},
						cancel : function() {
							// 用户取消分享后执行的回调函数
						}
					});
				})
				
			} else {//从商品详情进入页面
				var ii;
				$.each(data.data, function(index, ele) {
					if (ele.goods_id == data.msg) {//商品是首页滑动的商品里的一个
						ii = index;
						return;
					}
				});
				if (ii == undefined || ii == "") {//商品不是滑动商品里的某个，则新增一个商品到滑动商品里
					var list = data.data;
					$.post("shareGoodsDetailed.jhtml", {
						"goods_id" : data.msg
					}, function(data) {
						//固定4个商品，如果超过4个，移除其他的，添加ajax数据
						var fixedLen = 4;
						list.splice(4, list.length - fixedLen);
						list.push(data.data);
						var temeplte = Handlebars.compile($("#swiperTpl")
								.html());
						$(".swiper-wrapper").html(temeplte(list));
						var swiper = new Swiper('.swiper-container', {
							navigation : {
								nextEl : '.swiper-button-next',
								prevEl : '.swiper-button-prev',
							},
							initialSlide : fixedLen,
							//loop : true,
						});
						scId=$(".swiper-slide-active").find("input").eq(0).val();
						scImg = $(".swiper-slide-active").find("input").eq(1).val();
						wx.ready(function() {
							wx.onMenuShareAppMessage({
								title : '分享助力', // 分享标题
								desc : '我正在春节好礼0元换活动中换取商品，快来帮我助力！一起领春节礼物！！', // 分享描述
								link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareDetail.jhtml?goods_id='
										+ scId
										+ '&shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
								imgUrl : scImg, // 分享图标
								success : function() {
									// 用户确认分享后执行的回调函数
									var shareId = '${sessionScope.openid}';
									shareActivityGoods(scId,shareId);
								},
								cancel : function() {
									// 用户取消分享后执行的回调函数
								}
							});
						})
					})
				} else {
					var temeplte = Handlebars.compile($("#swiperTpl").html());
					$(".swiper-wrapper").html(temeplte(data.data));
					var swiper = new Swiper('.swiper-container', {
						navigation : {
							nextEl : '.swiper-button-next',
							prevEl : '.swiper-button-prev',
						},
						initialSlide : ii,
						//loop : true,
					});
					scId=$(".swiper-slide-active").find("input").eq(0).val();
					scImg = $(".swiper-slide-active").find("input").eq(1).val();
					wx.ready(function() {
						wx.onMenuShareAppMessage({
							title : '分享助力', // 分享标题
							desc : '我正在春节好礼0元换活动中换取商品，快来帮我助力！一起领春节礼物！！', // 分享描述
							link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareDetail.jhtml?goods_id='
									+ scId
									+ '&shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
							imgUrl : scImg, // 分享图标
							success : function() {
								// 用户确认分享后执行的回调函数
								var shareId = '${sessionScope.openid}';
								shareActivityGoods(scId,shareId);
							},
							cancel : function() {
								// 用户取消分享后执行的回调函数
							}
						});
					})
				}
			}
		})
		//获取累计助力的数据
		$.post("shareHomePowers.jhtml", {
			"open_id" : '${sessionScope.openid}'
		}, function(data) {
			var temeplte = Handlebars.compile($("#powerDataTpl").html());
			$("#smd_top").html(temeplte(data.data));
		})
		//跳转到商品详情
		function toGoodsDetial(goods_id) {
			window.location.href = "getActivityGoodsDetails.jhtml?goods_id="
					+ goods_id + "&direct=" + false;
		}
				
		$('.swiper-button-next').click(function(){
			
			if($(".swiper-slide-active").next().find("input").eq(0).val() == undefined){
				return;
			}
			scId = $(".swiper-slide-active").next().find("input").eq(0).val();
			scImg = $(".swiper-slide-active").next().find("input").eq(1).val();
			wx.ready(function() {
				wx.onMenuShareAppMessage({
					title : '分享助力', // 分享标题
					desc : '我正在春节好礼0元换活动中换取商品，快来帮我助力！一起领春节礼物！！', // 分享描述
					link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareDetail.jhtml?goods_id='
							+ scId
							+ '&shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : scImg, // 分享图标
					success : function() {
						// 用户确认分享后执行的回调函数
						var shareId = '${sessionScope.openid}';
						shareActivityGoods(scId,shareId);
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
					}
				});
			})
		  })		  
		$('.swiper-button-prev').click(function(){
			
			if($(".swiper-slide-active").prev().find("input").eq(0).val() == undefined){
				return;
			}
			scId = $(".swiper-slide-active").prev().find("input").eq(0).val();
			scImg = $(".swiper-slide-active").prev().find("input").eq(1).val();
			wx.ready(function() {
				wx.onMenuShareAppMessage({
					title : '分享助力', // 分享标题
					desc : '我正在春节好礼0元换活动中换取商品，快来帮我助力！一起领春节礼物！！', // 分享描述
					link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareDetail.jhtml?goods_id='
							+ scId
							+ '&shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : scImg, // 分享图标
					success : function() {
						var shareId = '${sessionScope.openid}';
						shareActivityGoods(scId,shareId);
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
					}
				});
			})
		  })
		//https://zjc1518.com
			

			wx.error(function(res) {
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
				alert("微信验证失败");
			});
		  
	</script>
	<script>

	  $("#goodsList li:odd").css("margin-right","0");
		$(function() {
			//创建MeScroll对象
			var mescroll = new MeScroll("mescroll", {
				down : {
					auto : false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
					callback : downCallback
				//下拉刷新的回调
				},
				up : {
					auto : true, //是否在初始化时以上拉加载的方式自动加载第一页数据; 默认false
					callback : upCallback
				//上拉回调,此处可简写; 相当于 callback: function (page) { upCallback(page); }
				}
			});

			/*下拉刷新的回调 */
			function downCallback() {
				//联网加载数据
				getListDataFromNet(0, 1, function(data) {
					//联网成功的回调,隐藏下拉刷新的状态
					mescroll.endSuccess();
					//设置列表数据
					setListData(data, false);
				}, function() {
					//联网失败的回调,隐藏下拉刷新的状态
					mescroll.endErr();
				});
			}

			/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
			function upCallback(page) {
				//联网加载数据
				getListDataFromNet(page.num, page.size, function(data) {
					//联网成功的回调,隐藏下拉刷新和上拉加载的状态;
					mescroll.endSuccess(data.length);//传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
					//设置列表数据
					setListData(data, true);
				}, function() {
					//联网失败的回调,隐藏下拉刷新和上拉加载的状态;
					mescroll.endErr();
				});
			}

			/*设置列表数据*/
			function setListData(data, isAppend) {
				var listDom = $("#goodsList");
				var li = "";
				$
						.each(
								data,
								function(index, ele) {
									li += "<li><a href='getActivityGoodsDetails.jhtml?goods_id="
											+ ele.goods_id
											+ "&direct=false'><img src='" + ele.original_img 
						+ "'><p class='title'>"
											+ ele.goods_name
											+ "</p><p class='price'>"
											+ ele.shop_price
											+ " 元  + " 
											+ ele.market_price
											+ "<span> 券</span></p></a></li>";
								});
				if (isAppend) {
					listDom.append(li);//加在列表的后面,上拉加载
				} else {
					listDom.html(li);//加在列表的前面,下拉刷新
				}
			}

			/*联网加载列表数据*/
			function getListDataFromNet(pageNum, pageSize, successCallback,
					errorCallback) {
				//延时一秒,模拟联网
				setTimeout(function() {
					try {
						var temp = [];
						if (pageNum == 0) {

							//关键字收索商品
							$.ajax({
								url : 'getActivityWxGoods.jhtml',
								dataType : 'json',
								type : 'POST',
								data : {
									"page" : 1,
									"goods_name" : $("#keywords").val()
								},
								success : function(data) {
									var newArr = temp.concat(data.list);
									successCallback && successCallback(newArr);
								}
							});
						} else {
							//此处模拟上拉加载返回的数据
							$.ajax({
								url : 'getActivityWxGoods.jhtml',
								dataType : 'json',
								type : 'POST',
								data : {
									"page" : pageNum + 1,
									"goods_name" : $("#keywords").val()
								},
								success : function(data) {
									var newArr = temp.concat(data.list);
									successCallback && successCallback(newArr);
								}
							});
						}
					} catch (e) {
						//联网失败的回调
						errorCallback && errorCallback();
					}
				}, 1000)
			}

		});
		function serach(){
			var temp = [];
			$.ajax({
				url : 'getActivityWxGoods.jhtml',
				dataType : 'json',
				type : 'POST',
				data : {
					"page" : 1,
					"goods_name" : $("#keywords").val()
				},
				success : function(data) {
					var newArr = temp.concat(data.list);
					var listDom = $("#goodsList");
					var li = "";
					$.each(newArr,function(index, ele) {
						li += "<li><a href='getActivityGoodsDetails.jhtml?goods_id="
							+ ele.goods_id + "&direct=false'><img src='" + ele.original_img 
							+ "'><p class='title'>"+ ele.goods_name+ "</p><p class='price'>"+ ele.shop_price + " 元  + "+ ele.market_price+ "<span> 券</span></p></a></li>";});
					listDom.html(li);//加在列表的前面,下拉刷新
				}
			});
		}
	</script>
	<c:if test="${sessionScope.openid != null && sessionScope.openid != ''}">
		<script>
		//https://zjc1518.com
			wx.ready(function() {
				wx.onMenuShareAppMessage({
					title : '分享助力', // 分享标题
					desc : '我正在春节好礼0元换活动中换取商品，快来帮我助力！一起领春节礼物！！', // 分享描述
					link : 'https://zjc1518.com/aosuite/wxact/activity/v1/initShareDetail.jhtml?goods_id='
							+ $(".swiper-slide-active").find("input").eq(0).val()
							+ '&shareId=${sessionScope.openid}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : $(".swiper-slide-active").find("input").eq(1).val(), // 分享图标
					success : function() {
						// 用户确认分享后执行的回调函数
						var shareId = '${sessionScope.openid}';
						shareActivityGoods(scId,shareId);
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
					}
				});
			})

			wx.error(function(res) {
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
				alert("微信验证失败");
			});
		</script>
	</c:if>
</body>
</html>
