<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>抽奖大转盘</title>
<style>
		*{margin:0;padding:0;}
			body{width:100%;height:100%;font-size:62.5%;font-family:'Microsoft YaHei','微软雅黑';}
			p{margin:0;padding:0;}
			.fl{float:left;}  
			.fr{float:right;}  
			.clear{clear:both;}
			.sign{width:100%; background:#f43639;padding-bottom:5rem;} 
			.wripe{width:95%;margin:0 auto; } 
			
			.sign-3-1 img{width:100%;} 
			
			.sign-3 .sign-3-2{margin-top:2rem;}  
			.sign-3-2 h3{font-size:20px;color:#fff;width:170px;margin:0 auto;text-align:center;line-height:45px;background:url(../../../static/prize/images/ti_head.png) no-repeat;background-size:100% 100%;}
			.sign-3-2-1{ background:url(../../../static/prize/images/ty_two.png) no-repeat;width:100%;background-size:100% 100%;}
			.sign-3-2-1 .span1{color:#d01b21;line-height:3rem;font-size:1rem;font-weight:bold;margin-left:1.5rem;margin-top:0.5rem;}
			.sign-3-2-1 .span2{color:#d01b21;line-height:3rem;font-size:1rem;font-weight:bold;margin-right:1.5rem;margin-top:0.5rem;}
			.sign-3-2-2{padding-left:2.8rem;padding-right:0.5rem;padding-bottom:2rem;background:#ffe694;}
			
			.sign-3-2-2 ul{margin:0;list-style:none;height:20rem;overflow-y: auto;}
			.sign-3-2-2 ul li{color:#8e5a17;font-size:1rem;line-height:35px;}
			.turntable-bg {width: 100%;margin: 0 auto;} 
			.turntable-bg .pointer{width: 5rem;margin:0 auto; cursor:pointer;position:relative;z-index:9996;}
			.turntable-bg .rotate{width: 100%;}
			.rotate img,.pointer img{width:100%;}
			.poin{width: 100%;position:absolute;top:6rem;z-index:9996;}
			.sign_text{margin-right:1.5rem;}
			.zhe_makesign_mak {z-index: 9997;position:fixed;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.4;filter:alpha(opacity=40);display:none;padding:0 1rem;}
			.zhe_makesign {z-index:9998;position:fixed;top:10%;width:85%;margin:0 auto;left:5%;padding:2%; padding-top:5%;display:none;}
			.btn_ok{text-align:right;background:#f8420a;border:1px solid #f8420a;color:#fff;line-height:1rem;padding:0 0.5rem;}
			#img_rads,#img_rad{width:100%;}
			.zhd_img{position:relative;} 
			.zjdo_img{position:absolute;right:0;margin-top:0.5rem;margin-right:0.5rem;width:1.5rem; }
			@media screen and (max-width: 1080px){.turntable-bg .pointer{width: 15rem;}.poin{top:15rem;}}
			@media screen and (max-width: 1024px){.turntable-bg .pointer{width: 13rem;}.poin{top:16rem;}}
			@media screen and (max-width: 800px){.turntable-bg .pointer{width:10rem;}.poin{top:13rem;}}
			@media screen and (max-width: 768px){.turntable-bg .pointer{width:11rem;}.poin{top:12rem;}}
			@media screen and (max-width: 600px){.turntable-bg .pointer{width:9rem;}.poin{top:13rem;}}
			@media screen and (max-width: 414px){.turntable-bg .pointer{width:7rem;}.poin{top:8rem;}}
			@media screen and (max-width: 412px){.turntable-bg .pointer{width:7rem;}.poin{top:8rem;}}
			@media screen and (max-width: 375px){.turntable-bg .pointer{width:7rem;}.poin{top:7rem;}}
			@media screen and (max-width: 360px){.turntable-bg .pointer{width: 6rem;}.poin{top:7rem;}}
			@media screen and (max-width: 320px){.poin{top:6rem;}}
			<!-- font -->
			@media only screen and (min-width: 1242px){html {font-size: 187.5% !important;}}  
			@media only screen and (min-width: 750px) and (min-width: 768px) and (min-width: 800px){html {font-size: 150% !important;}} 
			@media only screen and (min-width: 640px) and (min-width: 600px) and (min-width: 736px){html {font-size: 125% !important;}}
			@media only screen and (min-width: 320px) and (min-width: 360px) and (min-width: 375px) and (min-width: 412px) and (min-width: 414px){* html {font-size: 62.5% !important;}} 
</style>
</head>
<body>
	<div class="sign">
		<div class="sign-3">
			<div class="sign-3-1">
			    <img src="../../../static/prize/images/head_sign.png"/>			
			</div>
			<div class="wripe">
				<div class="turntable-bg">
				<div class="pointer">
						<div class="poin">
							<img src="../../../static/prize/images/pointer.png" alt="pointer" />
						</div>
					</div>
					<div class="rotate">
						<img id="rotate" src="../../../static/prize/images/turntable.png"
							alt="turntable" />
					</div>
				</div>
				<div class="sign-3-2">
					<h3>获奖记录</h3>
					<div class="sign-3-2-1">
						<span class="span1 fl">手机号</span> <span class="span2 fr">奖品</span>
						<div class="clear"></div>
					</div>
					<div class="sign-3-2-2">
						<ul>
							<c:forEach items="${winPrizes }" var="winPrize">
								<li><span class="fl">${winPrize.phone_num}</span><span class="sign_text fr">${winPrize.prize_name}</span>
										<div class="clear"></div></li>
							</c:forEach>
						</ul></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- 弹出层 -->
		<div class="zhe_makesign clearfix">	
			<div class="zhd_img">
				<img src="../../../static/prize/images/X.png" class="zjdo_img"/>
			 </div>		
			 <!-- 祝福 -->
			 <div class="img_zf">
				<img id="img_rad" src=""/>
			 </div>
			 <div class="img_order">
				<!-- 其他奖项 --> 
				 <img id="img_rads" src=""/>  
			 </div>
		</div>
        <div class="zhe_makesign_mak"></div>
</body>
<script src="../../../static/prize/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="../../../static/prize/js/awardRotate.js"></script>
<script type="text/javascript">
	$(function() {		   
		   $(".zhd_img").click(function(){
		      $(".zhe_makesign,.zhe_makesign_mak").hide(); 
			  $("#img_rads,#img_rad").attr('src','');
		   });  
		var rotateTimeOut = function() {
			$('#rotate').rotate({
				angle : 0,
				animateTo : 2160,
				duration : 8000,
				callback : function() {
					alert('网络超时，请检查您的网络设置！');
				}
			});
		};
		var bRotate = false;

		var rotateFn = function(awards, angles, txt, type) {
			bRotate = !bRotate;
			$('#rotate').stopRotate();
			$('#rotate').rotate({
				angle : 0,
				animateTo : angles + 1800,
				duration : 8000,
				callback : function() {
					   $(".zhe_makesign,.zhe_makesign_mak").show();
						if(type==1){
							$(".img_zf").show();  
							$(".img_order").hide();  
							$("#img_rad").attr('src',txt);
						}else{ 
							$(".img_order").show();	
							$(".img_zf").hide();	 
						    $("#img_rads").attr('src',txt); 
						}
						bRotate = !bRotate;
				}
			})
		};
		
		var time = 0;
		$('.pointer').click(function() {
			if(time == 0){
				time = 10;//设置间隔时间
				//启动计时器，倒计时time秒后自动关闭计时器。
		        var index = setInterval(function(){
		            time--;
		            if (time == 0) {
		                clearInterval(index);
		            }
		        }, 1000);
				$.ajax({
					url:"getWinPrize.jhtml",
					success:function(data){
						if(data.code != 1){
							 alert(data.msg)
						}else{
							if (bRotate)
								return;
							var item = rnd(0,5);
							switch (data.data.prize_id) {
								case 1://iphone
									var iot5="../../../static/prize/images/5.gif"; 
									rotateFn(5, 305, iot5);
									break;
								case 2://再来一次
									var iot4="../../../static/prize/images/6.gif"; 
									rotateFn(4, 265, iot4);
									break;
								case 3://500易物劵
									var iot3="../../../static/prize/images/4.gif"; 
									rotateFn(3, 205,iot3);
									break;
								case 4://38易物劵
									var iot2="../../../static/prize/images/3.gif"; 
									rotateFn(2, 165, iot2);
									break;
								case 5://18易物劵	
									var iot1="../../../static/prize/images/2.gif"; 
									rotateFn(1, 90,iot1);
									break;
								case 6://春节奇葩祝福
									<!-- 随机图片 start -->
									var aa=["../../../static/prize/images/1_1.gif","../../../static/prize/images/1_2.gif","../../../static/prize/images/1_3.gif","../../../static/prize/images/1_4.gif","../../../static/prize/images/1_5.gif",
									"../../../static/prize/images/1_6.gif","../../../static/prize/images/1_7.gif","../../../static/prize/images/1_8.gif","../../../static/prize/images/1_9.gif","../../../static/prize/images/1_10.gif","../../../static/prize/images/1_11.gif"];
									rotateFn(0, 25, aa[(Math.floor(Math.random()*10))],1); 
									break;
							} 
						} 
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(XMLHttpRequest.status);
						alert(XMLHttpRequest.readyState);
						alert(textStatus);
					}
				});
			} else {
				 alert('很抱歉，您操作太过频繁，请稍后再试');
			}
		});
	});

	function rnd(n, m){
		return Math.floor(Math.random()*(m-n+1)+n)
	}
	$("#sign-right-1").click(function() {
		$("body,html").animate({
			scrollTop : 630
		}, 500);
	})
	$("#sign-right-2").click(function() {
		$("body,html").animate({
			scrollTop : 1525
		}, 500);
	})
	$("#sign-right-3").click(function() {
		$("body,html").animate({
			scrollTop : 2859
		}, 500);
	})
	$("#sign-right-5").click(function() {
		$("body,html").animate({
			scrollTop : 0
		}, 500);
	})

	var date = new Date;
	var month = date.getMonth() + 1;
	var day = date.getDate();
	function signSubmit() {
		$(".sign-date").each(function() {
			if ($(this).find("span").text() == day) {
				if ($(this).find(".sign-date-1").css("display") == "block") {
					alert("您今天已经签到啦！");
				}
				$(this).find(".sign-date-1").show();
				$(this).find("p").show();

			}
		});
	}
</script>
</html>
