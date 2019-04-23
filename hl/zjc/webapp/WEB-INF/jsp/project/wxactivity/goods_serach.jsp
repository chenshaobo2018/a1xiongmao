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
<title>首页</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/detail_index.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/mescroll.min.css" />
<!-- bannar -->
<script type="text/javascript"
	src="../../../static/js/wxactivity/mob_index_bannar.js" charset="utf-8"></script>
<script type="text/javascript"
	src="../../../static/js/wxactivity/touch_slide.js" charset="utf-8"></script>

</head>
<body>
	<div class="top top_index" name="top">
		<div id="top-right">
			<div class="serch ">
				<input type="text" id="keywords" /> <a onClick="serach()"><img
					src="../../../static/image/wxactivity/home_06.png" class="fr" /></a>
			</div>
		</div>
	</div>
	<div class="relative">
		<div id="slider_box">
			<div id="slider" class="hboxpage"
				style="width: 100%; height: 100%; transform: translate3d(0px, 0px, 0px); transition-duration: 500ms;">
				<div class="center slider_img"
					style="background-image: url('../../../static/image/wxactivity/activity_001.jpg');">
					<a class="ablock nou" href="#">&nbsp;</a>
				</div>
				<div class="center slider_img"
					style="background-image: url('../../../static/image/wxactivity/home_10.png');">
					<a class="ablock nou" href="#">&nbsp;</a>
				</div>
				<div class="center slider_img"
					style="background-image: url('../../../static/image/wxactivity/home_10.png');">
					<a class="ablock nou" href="#">&nbsp;</a>
				</div>
			</div>
			<ul class="center" id="proint">
				<li class="proint_white"></li>
				<li class="proint_gray"></li>
				<li class="proint_gray"></li>
			</ul>
		</div>
	</div>
	<div class="content">
		<div class="goods">
			<div class="good-title">优选商品</div>
			<div id="mescroll" class="mescroll">
				<div id="goodsList" class="good_list"></div>
			</div>
		</div>
	</div>
	<div class="div_top">
		<a href="#top" class="top_div"><img
			src="../../../static/image/wxactivity/top.png"></a>
	</div>
	<script src="../../../static/js/wxactivity/mescroll.min.js"></script>
	<!-- 加载更多 -->
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
</body>
</html>