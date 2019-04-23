<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>我的奖品</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/share_power.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/mescroll.min.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
</head>

<body>
	<div class="container">
		<div id="mescroll" class="mescroll">
			<div id="newsList"></div>
		</div>
	</div>
	<script src="../../../static/js/wxactivity/mescroll.min.js"></script>
	<script>
		$(function (){
			//创建MeScroll对象
			var mescroll = new MeScroll("mescroll", {
				down: {
					auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
					callback: downCallback //下拉刷新的回调
				},
				up: {
					auto: true, //是否在初始化时以上拉加载的方式自动加载第一页数据; 默认false
					callback: upCallback //上拉回调,此处可简写; 相当于 callback: function (page) { upCallback(page); }
				}
			});
			
			/*下拉刷新的回调 */
			function downCallback(){
				//联网加载数据
				getListDataFromNet(0, 1, function(data){
					//联网成功的回调,隐藏下拉刷新的状态
					mescroll.endSuccess();
					//设置列表数据
					setListData(data, false);
				}, function(){
					//联网失败的回调,隐藏下拉刷新的状态
	                mescroll.endErr();
				});
			}
			
			/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
			function upCallback(page){
				//联网加载数据
				getListDataFromNet(page.num, page.size, function(data){
					//联网成功的回调,隐藏下拉刷新和上拉加载的状态;
					mescroll.endSuccess(data.length);//传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
					//设置列表数据
					setListData(data, true);
				}, function(){
					//联网失败的回调,隐藏下拉刷新和上拉加载的状态;
	                mescroll.endErr();
				});
			}
			
			/*设置列表数据*/
			function setListData(data, isAppend) {
				var listDom=$("#newsList");
				var li = "";
				$.each(data, function(index, ele){
					li += "<div class='signprize_one'><div class='signlist_two fl'><p>" + ele.prize_name + 
					"</p></div><div class='signlist_tree fr'>" + ele.win_time +"</div><div class='clear'></div></div>";
				}); 
				if (isAppend) {
					listDom.append(li);//加在列表的后面,上拉加载
				} else{
					listDom.html(li);//加在列表的前面,下拉刷新
				}
			}
			
			/*联网加载列表数据*/
			function getListDataFromNet(pageNum,pageSize,successCallback,errorCallback) {
				//延时一秒,模拟联网
                setTimeout(function () {
                	try{
                		var temp = [];
	                	if(pageNum==0){
	                		$.ajax({
	                			url : 'getWinPrize.jhtml',
	    						dataType : 'json',
	    						type : 'POST',
	    						data : {
	    							"page" : 1,
	    							"user_id" : "${sessionScope.user_id}"
	    						},
	    						success: function(data){
	    							var newArr = temp.concat(data.data.list);
	    		                	successCallback&&successCallback(newArr);
	    						}
	                		});
	                	}else{
	                		//此处模拟上拉加载返回的数据
	                		$.ajax({
		                		url : 'getWinPrize.jhtml',
		    					dataType : 'json',
		    					type : 'POST',
		    					data : {
		    						"page" : pageNum,
		    						"user_id" : "${sessionScope.user_id}"
		    					},
		    					success: function(data){
		    						var newArr = temp.concat(data.data.list);
	    		                	successCallback&&successCallback(newArr);
		    					}
		                	});
	                	}
                	}catch(e){
                		//联网失败的回调
                		errorCallback&&errorCallback();
                	}
                },1000)
			}
			
		});
	</script>
</body>
</html>