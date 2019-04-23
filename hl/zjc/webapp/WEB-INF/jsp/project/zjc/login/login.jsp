<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>
<aos:html title="中军创 | 后台管理" base="http">
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/css/matrix-login.css" />
	<link rel="stylesheet" type="text/css" href="${cxt}/static/zjc/font-awesome/css/font-awesome.css"  />
	<aos:body>
		<div id="loginbox">            
            <form id="loginform" class="form-vertical" action="" >
				 <div class="control-group normal_text"> <h3><img src="${cxt}/static/zjc/img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" name="account" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
               <div class="control-group" >
               		<div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lr"><i class="icon-barcode"></i></span><input class="validate" name="vercode" type="text" maxlength="4" placeholder="验证码" />
                            <aos:vercode id="vercode" uuid="${vercode_uuid}" fontSize="28" characters="${vercode_characters}" length="${vercode_length}" />
                            <input type="hidden" name="vercode_uuid" value="${vercode_uuid}" />
                        </div>
                    </div>
				</div>
                <div class="form-actions">
                    <span class="pull-right"><a type="submit" href="javascript:void(0);" onclick="fn_zjc_login();" class="btn btn-success btnlogin">登录</a></span>
                </div>
            </form>
        </div>
	</aos:body>
	<script src="${cxt}/static/zjc/js/jquery.min.js"></script> 
	<script type="text/javascript">
		function isValid(){
			if($('input[name="account"]').val() != ""  &&   $('input[name="password"]').val() != "" && $('input[name="vercode"]').val() != ""){
				return true;
			}else{
				return false;
			}
		}
		function fn_zjc_login(){
		 	if (!isValid()) {
				return;
			};
			$.ajax({
				  type: 'POST',
				  url: 'do.jhtml?router=zjcHomeService.zjcLogin',
				  data: {
					  account : $('input[name="account"]').val(),
					  password : $('input[name="password"]').val(),
					  vercode : $('input[name="vercode"]').val(),
					  vercode_uuid : $('input[name="vercode_uuid"]').val()
				  },
				  success: function(data){
					  var obj = JSON.parse(data);
					  if(obj.appcode === '1'){
						  window.location.href = 'do.jhtml?router=zjcHomeService.initZjcIndex&juid='
								+ obj.juid;
					  }else{
							if (obj.appcode === '000') {
								alert(obj.appmsg);
								//验证码错误
								//AOS.get('f_login.vercode').focus();
							} else if (obj.appcode === '001') {
								alert(obj.appmsg);
								//帐号错误
								//AOS.get('f_login.account').focus();
							} else if (obj.appcode === '002') {
								alert(obj.appmsg);
								//密码错误
								//AOS.get('f_login.password').reset();
								//AOS.get('f_login.password').focus();
								//AOS.get('f_login.password')
										//.validate();
							}
					  }
				  }
				});
		}
	</script>
</aos:html>
