<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("currDate", new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()));
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />



<link rel="stylesheet" type="text/css" href="${ path }/common/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${ path }/common/js/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ path }/common/js/jquery-easyui-1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ path }/common/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ path }/common/css/login.css"/>
<link rel="stylesheet" type="text/css" href="${ path }/common/font/iconfont.css"/>




<script src="${path }/common/js/jquery-2.1.1.min.js"></script>
<script src="${path }/common/js/jquery.cookie.js"></script>
<script src="${path }/common/js/common.js"></script>
<script src="${path }/common/js/ui.js"></script>
<script src="${path }/common/js/myJs.js"></script>
<script src="${path }/common/My97DatePicker/WdatePicker.js"></script>
<script src="${path }/common/js/ui_common.js"></script>
<script type="text/javascript" src="${path }/common/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/common/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script src="${path}/common/js/bootstrap.min.js"></script>

