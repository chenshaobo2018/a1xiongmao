<%@ page contentType="text/html; charset=utf-8"%>
<script src="${cxt}/static/zjc/js/jquery.min.js"></script>
<!-- logo -->
<div id="header">
  <h1><a href="dashboard.html">中军创|管理平台</a></h1>
</div>

<!--导航栏-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">${user_name}</span><b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a href="#"><i class="icon-user"></i>个人信息</a></li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-check"></i>修改密码</a></li>
        <li class="divider"></li>
        <li><a href="login.html"><i class="icon-key"></i>注销</a></li>
      </ul>
    </li>
  </ul>
</div>
<div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>

<!-- 菜单栏  -->
<div id="sidebar">
  <ul id="menubar">
    <!--<li class="active"><a href="#"><i class="icon icon-home"></i> <span>主页</span></a> </li>
    
     <li class="submenu"><a href="javascript:void(0);"><i class="icon icon-signal"></i> <span>系统管理</span><i class="icon icon-sort-down icon-margin"></i></a> 
    	<ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
   		 </ul>
    </li>
    
    <li class="submenu"><a href="javascript:void(0);"><i class="icon icon-inbox"></i> <span>权限管理</span><i class="icon icon-sort-down icon-margin"></i> </a> 
    	<ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
   		 </ul>
    </li>
    
    <li class="submenu"><a href="javascript:void(0);"><i class="icon icon-th"></i> <span>商家管理</span><i class="icon icon-sort-down icon-margin"></i></a>
    <ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
    </ul>
    </li>
    
    <li class="submenu"><a href="javascript:void(0);"><i class="icon icon-fullscreen"></i> <span>会员管理</span><i class="icon icon-sort-down icon-margin"></i></a>
    <ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
    </ul>
    </li>
    
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>商品管理</span><i class="icon icon-sort-down icon-margin"></i></a>
      <ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
      </ul>
    </li>
    <li class="submenu"><a href="#"><i class="icon icon-tint"></i> <span>订单管理</span><i class="icon icon-sort-down icon-margin"></i></a>
    	<ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
   		 </ul>
    </li>
    <li class="submenu"><a href="#"><i class="icon icon-pencil"></i> <span>广告管理</span><i class="icon icon-sort-down icon-margin"></i></a>
    	<ul>
        <li><a href="form-common.html">Basic Form</a></li>
        <li><a href="form-validation.html">Form with Validation</a></li>
        <li><a href="form-wizard.html">Form with Wizard</a></li>
   		 </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-file"></i> <span>内容管理</span><i class="icon icon-sort-down icon-margin"></i> </a>
      <ul>
        <li><a href="index2.html">Dashboard2</a></li>
        <li><a href="gallery.html">Gallery</a></li>
        <li><a href="calendar.html">Calendar</a></li>
        <li><a href="invoice.html">Invoice</a></li>
        <li><a href="chat.html">Chat option</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-info-sign"></i> <span>插件工具</span><i class="icon icon-sort-down icon-margin"></i> </a>
      <ul>
        <li><a href="error403.html">Error 403</a></li>
        <li><a href="error404.html">Error 404</a></li>
        <li><a href="error405.html">Error 405</a></li>
        <li><a href="error500.html">Error 500</a></li>
      </ul>
    </li> -->
  </ul>
</div>
