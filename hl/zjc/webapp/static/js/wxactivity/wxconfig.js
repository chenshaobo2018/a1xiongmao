var fx_noncestr = getNonceStr();
var fx_tampstr = getTimeStamp();
var fx_JsApi_Taken = getJsapiTicket();
var fanyizhiqian = "jsapi_ticket=" + fx_JsApi_Taken + "&noncestr=" + fx_noncestr + "&timestamp=" + fx_tampstr + "&url=" + window.location.href;
var fx_singtruestr = "" + CryptoJS.SHA1(fanyizhiqian);
wx.config({
    debug: false,
    //appId: 'wx49a3deae3dc46dcc',
    appId: 'wx32f6536e0f00e9f1',
    timestamp: fx_tampstr,
    nonceStr: fx_noncestr,
    signature: fx_singtruestr,
    jsApiList: [
      'checkJsApi',
      'onMenuShareTimeline',
      'onMenuShareAppMessage',
      'onMenuShareQQ',
      'onMenuShareWeibo',
      'hideMenuItems',
      'showMenuItems',
      'hideAllNonBaseMenuItem',
      'showAllNonBaseMenuItem',
      'translateVoice',
      'startRecord',
      'stopRecord',
      'onRecordEnd',
      'playVoice',
      'pauseVoice',
      'stopVoice',
      'uploadVoice',
      'downloadVoice',
      'chooseImage',
      'previewImage',
      'uploadImage',
      'downloadImage',
      'getNetworkType',
      'openLocation',
      'getLocation',
      'hideOptionMenu',
      'showOptionMenu',
      'closeWindow',
      'scanQRCode',
      'chooseWXPay',
      'openProductSpecificView',
      'addCard',
      'chooseCard',
      'openCard'
    ]
});

//获取随机字符串
function getNonceStr() {
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    var noceStr = "";
    var oldNonceStr = "";
    for (i = 0; i < 32; i++) {
    	oldNonceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    noceStr = oldNonceStr;
    return noceStr;
}

//获取jsapi_ticket
function getJsapiTicket(){
	var jsapi_ticket = "";
	$.ajax({ 
		url: "getJsapiTicket.jhtml",
		type: 'POST',
		data:'',
		async : false,
		success: function(data){
			var obj = JSON.parse(data);
			if(obj.code == 1){
				jsapi_ticket = obj.data;
			}else{
				alert(obj.msg)
			}
		}
	}); 
	return jsapi_ticket;
}

//获取当前时间戳
function getTimeStamp() {
    var timestamp = new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    timestampstring = timestampstring.substring(0, timestampstring.length - 3)
    return timestampstring;
}