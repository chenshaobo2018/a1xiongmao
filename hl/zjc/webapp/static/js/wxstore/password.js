var searchContent=$("#classSearch"),
k=0,
j=0,
code = '';
var _this = searchContent.find('.text');
//点击隐藏的input密码框,在4个显示的输入框的第一个框显示光标
$("#classSearch").on("focus","#classInput",function(){
	if(searchContent.attr('data-busy') === '0'){ 
	//在第一个密码框中添加光标样式
	   _this.eq(k).addClass("current");
	   searchContent.attr('data-busy','1');
	}
});
//change时去除输入框的高亮，用户再次输入密码时需再次点击
searchContent.on('change',"#classInput",function(){
	_this.eq(k).removeClass("current");
	_this.eq(k).text('');
	searchContent.attr('data-busy','0');
}).on('blur',"#classInput",function(){
	_this.eq(k).removeClass("current");					
	searchContent.attr('data-busy','0');
});

//使用keyup事件，绑定键盘上的数字按键和backspace按键
searchContent.on('keyup',"#classInput",function(e){

var  e = (e) ? e : window.event;

//键盘上的数字键按下才可以输入
if(e.keyCode == 8 || (e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105) || (e.keyCode >= 65 && e.keyCode <= 90)){
		k = this.value.length;//输入框里面的密码长度
		l = _this.size();//6
		for(;l--;){
		//，第几个框就显示高亮和光标（在输入框内有2个数字密码，第三个密码框要显示高亮和光标，之前的显示黑点后面的显示空白，输入和删除都一样）
		if(l === k){
			_this.eq(l).addClass("current");
		}else{
			_this.eq(l).removeClass("current");
		}				
		if(k === 4){
			j = 3;
		}else{
			j = k;
		};
		}
	}else{
	//输入其他字符，直接清空
		var _val = this.value;
		this.value = _val.replace(/\D/g,'');
		this.value = _val.replace(/[\u4E00-\u9FA5]/g,'');
		this.value=_val.replace(/[]/g,'');
	}
	var str=this.value;
	for(var i=0; i<this.value.length;i++){
		_this.eq(i).text("·")
	}
	if(e.keyCode == 8){
		_this.eq(str.length).text('')
	}
});	