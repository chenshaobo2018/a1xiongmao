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
<title>我的积分</title>
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/common.css" />
<link rel="stylesheet" type="text/css"
	href="../../../static/css/wxactivity/share_power.css" />
<script src="../../../static/js/wxactivity/jQuery-2.1.4.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/wxactivity/share_power.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../../../static/js/handlebars.js" type="text/javascript"
	charset="utf-8"></script>

<script type="text/x-handlebars-template" id="list">
<div class="inte_main">
	<div class="inte_one center">易物劵 {{countQuan this}}</div>
	<div class="inte_two">
	<ul>
		{{#each this}}
		<li>
			<div class="fl">
				<p class="intetext_one">{{nickname}}</p>
				<p class="intetext_two">{{addtime}}</p>
			</div>
			<div class="intetext_tree fr bold">+{{share_integral}}</div>
			<div class="clear">
			</div>
		</li>
		<div class="clear"></div>
		{{/each}}
	</ul>
	</div>
	</div>
</div>
</script>
</head>
<body>
	<div class="inte_container">
	</div>
	<script>
		Handlebars.registerHelper({
			'countQuan' : function(data) {
				var count = 0;
				$.each(data, function(index, element) {
					count += parseInt(element.share_integral);
				});
				return count;
			}
		});

		$.post("shareFriends.jhtml", {
			"share_open_id" : '${sessionScope.openid}',
		}, function(data) {
			var temeplte = Handlebars.compile($("#list").html());
			$(".inte_container").html(temeplte(data.data));
		});
	</script>
</body>
</html>