<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>定时任务管理中心</title>
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
    <link href="${path}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/css/animate.css" rel="stylesheet">
    <link href="${path}/css/style.css" rel="stylesheet">
    <link href="${path}/css/login.css" rel="stylesheet">    
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
    
</head>


<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post" action="login.do">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">定时任务管理中心</p>
                    <input type="text" class="form-control uname" placeholder="用户名" name="userCode" />
                    <input type="password" class="form-control pword m-b" placeholder="密码"  name="userPwd"/>
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
            </div>
        </div>
    </div>
</body>

</html>