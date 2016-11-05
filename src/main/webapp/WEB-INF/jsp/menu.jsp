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

    <link href="${path}/css/animate.css" rel="stylesheet">
    <link href="${path}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${path}/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
    <link href="${path}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="row row-lg wrapper wrapper-content animated fadeInRight">
        <div >
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单树</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="${path}/buttons.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="${path}/buttons.html#">选项1</a>
                            </li>
                            <li><a href="${path}/buttons.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content col-sm-12">
                	<div class="col-sm-3">
                    	<div id="menuTreeView" class="test"></div>
                    </div>
		            <div class="col-sm-9">
		                        
		                <div class="example">
		                
		                	<div class="btn-group hidden-xs" id="userTableToolbar" role="group">
		                        <button id="addUser" type="button" class="btn btn-outline btn-default">
		                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
		                        </button>
		                        <button type="button" class="btn btn-outline btn-default">
		                            <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
		                        </button>
		                        <button type="button" class="btn btn-outline btn-default">
		                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
		                        </button>
		                    </div>
		                    
		                    <table id="userTableColumns"
		                    	   data-toggle="table" 
		                    	   data-url="${path}/user/getAllUser.action" 
		                    	   data-height="600" 
		                    	   data-mobile-responsive="true"
		                    	   data-classes="table table-hover table-condensed"
		                    	   data-striped="true"
		                    	   data-row-style="userRowStyle"
		                    	   data-sort-name="First" 
		                    	   data-sort-order="desc"
									   data-show-columns="true"
									   data-card-view="true"
									   data-toolbar="#userTableToolbar"
									   data-search="true"
									   data-show-toggle="true"
									   data-show-refresh="true"
									   data-icon-size="outline"
									   data-query-params="queryParams" 
									   queryParamsType="limit"
									   data-pagination="true" 
									   data-side-pagination="server"
									   data-page-number="1"
									   data-page-size="50"
		                    	   >
		                        <thead>
		                            <tr>
		                            	<!-- <th data-field="state" data-radio="true"></th> -->
		                            	<th data-field="state" data-checkbox="true"></th>
		                                <th data-field="id" data-sortable="true">ID</th>
		                                <th data-field="name" data-switchable="false" data-sortable="true">姓名</th>
		                                <th data-field="acount" >账号</th>
		                                <th data-field="dept" >部门</th>
		                                <th data-field="telphone" data-cell-style="cellStyle">电话</th>
		                                <th data-field="operate" 
		                                	data-title="操作" 
		                                	data-align="center"
		                                	data-events="operateEvents"
		                                	data-formatter="operateFormatter"
		                                	>评分</th>
		                            </tr>
		                        </thead>
		                    </table>
		                </div>
		        	</div>
                </div>
            </div>
            
        </div>
        
        <!-- 
        	树结构
        	1. levels: 1   展开级别  全部展开就设置一个很大的数
        	2. color: "#428bca",  主题颜色
        	3.  expandIcon: 'glyphicon glyphicon-chevron-right',
		        collapseIcon: 'glyphicon glyphicon-chevron-down',
		        nodeIcon: 'glyphicon glyphicon-bookmark',
		            设置图标
	        4. showTags: true, 使用违章作为标签
        
         -->
	    <div class="alert alert-success col-sm-12" id="examplebtTableEventsResult" role="alert">
	      	  事件结果
	    </div>
         
    <!-- 全局js -->
    <script src="${path}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${path}/js/content.js?v=1.0.0"></script>

    <!-- Bootstrap-Treeview plugin javascript -->
    <script src="${path}/js/plugins/treeview/bootstrap-treeview.js"></script>
    
     <!-- Bootstrap table -->
    <script src="${path}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${path}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${path}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

</body>

</html>

<script type="text/javascript" >

	(function() {
		var $result = $('#examplebtTableEventsResult');
		
		var rootData = ${treeData};
		$('#menuTreeView').treeview({
			/* expandIcon: "glyphicon glyphicon-stop",
	        collapseIcon: "glyphicon glyphicon-unchecked",
	        nodeIcon: "glyphicon glyphicon-user",
	        backColor: "purple",
	        onhoverColor: "orange",
	        borderColor: "red",
	        showBorder: false, */
	        levels:1,
	        // showTags: true,
	        color: "#428bca",
	        highlightSelected: true,
	        /* selectedColor: "yellow",
	        selectedBackColor: "darkorange", */
	        //enableLinks: true,
	        data: rootData,
	        onNodeSelected: function (event, node) {
	        	$result.text('node.text:'+node.text+'   node.href:'+node.href);
	        }
	    });
		
	})(); 
	
    /* 获取远程数据的参数  params:limit, offset, search, sort, order*/
    function queryParams(params) {  //配置参数
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.limit,   //页面大小
            pageIndex: params.offset,  //页码
            sort: params.sort,  //排序列名
            sortOrder: params.order,//排位命令（desc，asc）
            search:params.search
        };
        return temp;
    }
</script>

