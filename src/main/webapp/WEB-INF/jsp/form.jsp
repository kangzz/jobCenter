<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>自如驿</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="${path}/favicon.ico"> <link href="${path}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${path}/css/animate.css" rel="stylesheet">
    <link href="${path}/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row" id="modal-form">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>所有表单元素 <small>包括自定义样式的复选和单选按钮</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content clearfix">
                        <form method="get" class="form-horizontal col-sm-6 ">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">普通</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">带说明信息</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"> <span class="help-block m-b-none">帮助文本，可能会超过一行，以块级元素显示</span>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>

                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="password">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提示</label>

                                <div class="col-sm-10">
                                    <input type="text" placeholder="提示信息" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">禁用</label>

                                <div class="col-sm-10">
                                    <input type="text" disabled="" placeholder="已被禁用" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">静态控制</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">i@zi-han.net</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">复选框&amp;单选框
                                    <br/>
                                    <small class="text-navy">普通Bootstrap元素</small>
                                </label>

                                <div class="col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">选项1</label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">选项1</label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios">选项2</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内联复选框</label>

                                <div class="col-sm-10">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="option1" id="inlineCheckbox1">a</label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="option2" id="inlineCheckbox2">b</label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="option3" id="inlineCheckbox3">c</label>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">复选框&amp;单选框
                                    <br/><small class="text-navy">自定义样式</small>
                                </label>

                                <div class="col-sm-10">
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value=""> <i></i> 选项1</label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value="" checked=""> <i></i> 选项2（选中）</label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value="" disabled="" checked=""> <i></i> 选项3（选中并禁用）</label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value="" disabled=""> <i></i> 选项4（禁用）</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" value="option1" name="a"> <i></i> 选项1</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" checked="" value="option2" name="a"> <i></i> 选项2（选中）</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" disabled="" checked="" value="option2"> <i></i> 选项3（选中并禁用）</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" disabled="" name="a"> <i></i> 选项4（禁用）</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内联复选框</label>

                                <div class="col-sm-10">
                                    <label class="checkbox-inline i-checks">
                                        <input type="checkbox" value="option1">a</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="checkbox" value="option2">b</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="checkbox" value="option3">c</label>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Select</label>

                                <div class="col-sm-10">
                                    <select class="form-control m-b" name="account">
                                        <option>选项 1</option>
                                        <option>选项 2</option>
                                        <option>选项 3</option>
                                        <option>选项 4</option>
                                    </select>

                                    <div class="col-sm-4 m-l-n">
                                        <select class="form-control" multiple="">
                                            <option>选项 1</option>
                                            <option>选项 2</option>
                                            <option>选项 3</option>
                                            <option>选项 4</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group has-success">
                                <label class="col-sm-2 control-label">验证通过</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group has-warning">
                                <label class="col-sm-2 control-label">未填写</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group has-error">
                                <label class="col-sm-2 control-label">验证未通过</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">自定义尺寸</label>

                                <div class="col-sm-10">
                                    <input type="text" placeholder=".input-lg" class="form-control input-lg m-b">
                                    <input type="text" placeholder="Default input" class="form-control m-b">
                                    <input type="text" placeholder=".input-sm" class="form-control input-sm">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">列尺寸</label>

                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <input type="text" placeholder=".col-md-2" class="form-control">
                                        </div>
                                        <div class="col-md-3">
                                            <input type="text" placeholder=".col-md-3" class="form-control">
                                        </div>
                                        <div class="col-md-4">
                                            <input type="text" placeholder=".col-md-4" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">文本框组</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b"><span class="input-group-addon">@</span>
                                        <input type="text" placeholder="用户名" class="form-control">
                                    </div>
                                    <div class="input-group m-b">
                                        <input type="text" class="form-control"> <span class="input-group-addon">.00</span>
                                    </div>
                                    <div class="input-group m-b"><span class="input-group-addon">&yen;</span>
                                        <input type="text" class="form-control"> <span class="input-group-addon">.00</span>
                                    </div>
                                    <div class="input-group m-b"><span class="input-group-addon"> <input type="checkbox"> </span>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group"><span class="input-group-addon"> <input type="radio"> </span>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">按钮插件</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b"><span class="input-group-btn">
                                            <button type="button" class="btn btn-primary">搜</button> </span>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control"> <span class="input-group-btn"> <button type="button" class="btn btn-primary">搜索
                                        </button> </span>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">带下拉框</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b">
                                        <div class="input-group-btn">
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">操作 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="form_basic.html#">选项1</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项2</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项3</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="form_basic.html#">选项4</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control">

                                        <div class="input-group-btn">
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">操作 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu pull-right">
                                                <li><a href="form_basic.html#">选项1</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项2</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项3</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="form_basic.html#">选项4</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">分段</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b">
                                        <div class="input-group-btn">
                                            <button tabindex="-1" class="btn btn-white" type="button">操作</button>
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button"><span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="form_basic.html#">选项1</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项2</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项3</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="form_basic.html#">选项4</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control">

                                        <div class="input-group-btn">
                                            <button tabindex="-1" class="btn btn-white" type="button">操作</button>
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button"><span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu pull-right">
                                                分段
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                    <button class="btn btn-white" type="submit">取消</button>
                                </div>
                            </div>
                        </form>
                        
                        
                         <!-- 表单验证 start -->
				            <div class="col-sm-6">
				            	<form class="form-horizontal m-t" id="signupForm">
		                            <div class="form-group">
		                                <label class="col-sm-3 control-label">姓氏：</label>
		                                <div class="col-sm-8">
		                                    <input id="firstname" name="firstname" class="form-control" type="text">
		                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 这里写点提示的内容</span>
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="col-sm-3 control-label">名字：</label>
		                                <div class="col-sm-8">
		                                    <input id="lastname" name="lastname" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid">
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="col-sm-3 control-label">用户名：</label>
		                                <div class="col-sm-8">
		                                    <input id="username" name="username" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="col-sm-3 control-label">密码：</label>
		                                <div class="col-sm-8">
		                                    <input id="password" name="password" class="form-control" type="password">
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="col-sm-3 control-label">确认密码：</label>
		                                <div class="col-sm-8">
		                                    <input id="confirm_password" name="confirm_password" class="form-control" type="password">
		                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="col-sm-3 control-label">E-mail：</label>
		                                <div class="col-sm-8">
		                                    <input id="email" name="email" class="form-control" type="email">
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <div class="col-sm-8 col-sm-offset-3">
		                                    <div class="checkbox">
		                                        <label>
		                                            <input type="checkbox" class="checkbox" id="agree" name="agree"> 我已经认真阅读并同意《H+使用协议》
		                                        </label>
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <div class="col-sm-8 col-sm-offset-3">
		                                    <button class="btn btn-primary" type="submit">提交</button>
		                                    <button class="btn btn-white" type="reset" id="reset">重置</button>
		                                </div>
		                            </div>
		                        </form>
				            </div>
				            
			            <!-- 表单验证  end -->
                        
                    </div>
                </div>
            </div>
            
        </div>
    </div>

    <!-- 全局js -->
    <script src="${path}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${path}/js/content.js?v=1.0.0"></script>

    <!-- iCheck -->
    <script src="${path}/js/plugins/iCheck/icheck.min.js"></script>
    
        <!-- jQuery Validation plugin javascript-->
    <script src="${path}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${path}/js/plugins/validate/messages_zh.min.js"></script>

    <script>
	  //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
	    $.validator.setDefaults({
	        highlight: function (element) {
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	        },
	        success: function (element) {
	            element.closest('.form-group').removeClass('has-error').addClass('has-success');
	        },
	        errorElement: "span",
	        errorPlacement: function (error, element) {
	            if (element.is(":radio") || element.is(":checkbox")) {
	                error.appendTo(element.parent().parent().parent());
	            } else {
	                error.appendTo(element.parent());
	            }
	        },
	        errorClass: "help-block m-b-none",
	        validClass: "help-block m-b-none"
	    });

    
        $(document).ready(function () {
        	
        	// validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";
            var validator = $("#signupForm").validate({
                rules: {
                    firstname: "required",
                    lastname: "required",
                    username: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    topic: {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: icon + "请输入你的姓",
                    lastname: icon + "请输入您的名字",
                    username: {
                        required: icon + "请输入您的用户名",
                        minlength: icon + "用户名必须两个字符以上"
                    },
                    password: {
                        required: icon + "请输入您的密码",
                        minlength: icon + "密码必须5个字符以上"
                    },
                    confirm_password: {
                        required: icon + "请再次输入密码",
                        minlength: icon + "密码必须5个字符以上",
                        equalTo: icon + "两次输入的密码不一致"
                    },
                    email: icon + "请输入您的E-mail",
                    agree: {
                        required: icon + "必须同意协议后才能注册",
                        element: '#agree-error'
                    }
                }
            });
        	
            //重置表单清除表单项样式
        	$("#reset").click(function() {
                validator.resetForm();
                $("#signupForm .form-group").removeClass('has-error').removeClass('has-success');
            });
        });
    </script>

</body>

</html>

