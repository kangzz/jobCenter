<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>

<!DOCTYPE html>
<html>

<head>

    <title>自如驿</title>

    <link rel="shortcut icon" href="${path}/favicon.ico"> <link href="${path}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${path}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${path}/css/animate.css" rel="stylesheet">
    <link href="${path}/css/style.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>表格示例</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#">选项1</a>
                        </li>
                        <li><a href="#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                                
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
        
        <!-- 
        1. 加载数据  
        	1.1  指定 data-url="${path}/js/demo/table_base.json"
        	1.2 本地指定数据
        	
        2. 样式
        	data-classes="table table-hover table-condensed" 边框等
        	data-striped="true" 行背景切换
        	data-halign="right" data-align="center" 对齐  left right center
        	data-row-style="rowStyle" 行样式
        	data-cell-style="cellStyle" 列样式
        	
        	data-detail-view="true"  显示详细
            data-detail-formatter="detailFormatter"  详情怎么显示方法
        3.排序
        	data-sort-name="First" data-sort-order="desc"
        	data-formatter="nameFormatter"  格式化   nameFormatter是一个函数，可以获取当前单元格内容，然后做出特殊处理
        	
        4.头信息
        	data-show-header="false" 是否显示列头
        	
       	5. 选择框
       		<th data-field="state" data-radio="true"></th>    单选框
       		<th data-field="state" data-checkbox="true"></th> 复选框
       		
       	6. 列的显示与隐藏
       		data-switchable="false"  ：False to disable the switchable of columns item.
       		data-visible="false"	  ：False to hide the columns item
       		data-show-columns="true"  显示选择列下拉框
       		
       	7. 卡片视图
       		data-card-view="true"   没发现什么区别
       	
       	8. 工具条
       		data-toolbar="userTableToolbar"  工具
       		data-search="true"  显示搜索框
       		data-show-toggle="true" toggle table / card view
       		data-show-refresh="true" 显示刷新按钮
       		data-icon 修改  刷新 搜索 图标
       	
       	9. 分页
       		data-query-params="queryParams"  方法  在此可以处理向后台传输的参数
       		data-pagination="true" 
       		queryParamsType="limit"  Set 'limit' to send query params width RESTFul type.
       		
       		data-show-pagination-switch="true" 是否显示按钮  按钮作用显示分页栏
       		
       		data-side-pagination="server"  分页远程取数据
       		
       		data-page-size="50"  每页多少数据
       	10. 事件
       	
         -->
        
        <div class="alert alert-success" id="examplebtTableEventsResult" role="alert">
          	  事件结果
        </div>
    </div>

    <!-- 全局js -->
    <script src="${path}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${path}/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <script src="${path}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${path}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${path}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

</body>

</html>

<script type="text/javascript" >

	/* 图标 */
	(function() {
		$('#userTableColumns').bootstrapTable({
			icons : {
				refresh : 'glyphicon-repeat',
				toggle : 'glyphicon-list-alt',
				columns : 'glyphicon-list'
			} 
		});
		
		var $result = $('#examplebtTableEventsResult');

		/*  事件监听 */
	    $('#userTableColumns').on('all.bs.table', function(e, name, args) {
	        console.log('Event:', name, ', data:', args);
	      })
	      .on('click-row.bs.table', function(e, row, $element) {
	        $result.text('Event: click-row.bs.table');
	      })
	      .on('dbl-click-row.bs.table', function(e, row, $element) {
	        $result.text('Event: dbl-click-row.bs.table');
	      })
	      .on('sort.bs.table', function(e, name, order) {
	        $result.text('Event: sort.bs.table');
	      })
	      .on('check.bs.table', function(e, row) {
	        $result.text('Event: check.bs.table');
	      })
	      .on('uncheck.bs.table', function(e, row) {
	        $result.text('Event: uncheck.bs.table');
	      })
	      .on('check-all.bs.table', function(e) {
	        $result.text('Event: check-all.bs.table');
	      })
	      .on('uncheck-all.bs.table', function(e) {
	        $result.text('Event: uncheck-all.bs.table');
	      })
	      .on('load-success.bs.table', function(e, data) {
	        $result.text('Event: load-success.bs.table');
	      })
	      .on('load-error.bs.table', function(e, status) {
	        $result.text('Event: load-error.bs.table');
	      })
	      .on('column-switch.bs.table', function(e, field, checked) {
	        $result.text('Event: column-switch.bs.table');
	      })
	      .on('page-change.bs.table', function(e, size, number) {
	        $result.text('Event: page-change.bs.table');
	      })
	      .on('search.bs.table', function(e, text) {
	        $result.text('Event: search.bs.table');
	      });
		
		
	    
	    /* 工具栏的点击事件 addUser */
	    $("#addUser").click(function(){
	    	var data = $.map($("#userTableColumns").bootstrapTable('getSelections'), function (row) {
	            return row.id
	        });
	    	alert(data.length);
	    });
	})(); 
	
	/* 表格里的操作按钮 */
	function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-heart"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }
    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row));
        },
        'click .remove': function (e, value, row, index) {
        	alert('You click remove action, row: ' + JSON.stringify(row));
        }
    };
    
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
    
    /* 表格行样式 */
    function userRowStyle(row, index) {
   	  var classes = ['active', 'success', 'info', 'warning', 'danger'];
   	  var classIndex = index%(classes.length);
   	  if (index % 2 === 0) {
   	    return {
   	      classes: classes[classIndex]
   	    };
   	  }
   	  return {};
   	}
    
    /* 显示行详情内容 */
    function detailFormatter(){
    	var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
</script>
