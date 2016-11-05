<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>
<link rel="shortcut icon" href="favicon.ico"/>
<link href="${path }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet"/>
<link href="${path }/css/font-awesome.min.css?v=4.4.0" rel="stylesheet"/>
<link href="${path}/css/plugins/bootstrap-table/bootstrap-table.min.css?date=20160508" rel="stylesheet"/>
<link href="${path }/css/animate.css?v=4.1.0" rel="stylesheet"/>
<link href="${path }/css/style.css?date=20160528" rel="stylesheet"/>
<link href="${path }/css/plugins/toastr/toastr.min.css?date=20160508" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${path }/js/plugins/layer/laydate/need/laydate.css?date=20160508" />
<link href="${path }/css/plugins/sweetalert/sweetalert.css?date=20160508" rel="stylesheet">