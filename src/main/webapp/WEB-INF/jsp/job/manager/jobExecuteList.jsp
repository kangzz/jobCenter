<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>任务列表</title>
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<!-- Sweet Alert -->
	<link href="${path}/css/plugins/sweetalert/sweetalert.css?v=2016051601" rel="stylesheet">
	<link href="${path}/css/plugins/toastr/toastr.min.css?v=2016051601" rel="stylesheet">
	<link href="${path}/css/plugins/jsTree/style.min.css?v=2016051601" rel="stylesheet">
	<link href="${path }/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">

			<!-- 普通查询条件 start -->
			<div class="row row-lg">
				<form id="searchForm" method="post">
					<div class="row">
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">任务名称：</span>
								<select name="jobId" id="jobId" class="form-control" onchange="getLinkList();">
									<option value="">请选择</option>
									<c:forEach items="${jobInfoList }" var="jobInfo" varStatus="vs">
										<option value="${jobInfo.jobId }" <c:if test="${jobInfo.jobId == jobId}">selected="selected"</c:if> >
										${jobInfo.jobName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">执行uid：</span>
								<input type="text" id="jobUuid" maxlength="40" name="jobUuid" class="form-control"
									   placeholder="执行uid">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">jobLink：</span>
								<select name="jobLinkId" id="jobLinkId" class="form-control">
									<option value="">请选择</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row m-t">
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">执行时间：</span>
								<input name="jobStartTimeBegin" id="jobStartTimeBegin" class="form-control col-sm-4"
									   type="text" placeholder="请选择" readonly="readonly">
								<span class="input-group-addon">-</span>
								<input name="jobStartTimeEnd" id="jobStartTimeEnd" class="form-control col-sm-4" type="text"
									   placeholder="请选择" readonly="readonly">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">回调时间：</span>
								<input name="jobEndTimeBegin" id="jobEndTimeBegin" class="form-control col-sm-4"
									   type="text" placeholder="请选择" readonly="readonly">
								<span class="input-group-addon">-</span>
								<input name="jobEndTimeEnd" id="jobEndTimeEnd" class="form-control col-sm-4" type="text"
									   placeholder="请选择" readonly="readonly">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">执行结果：</span>
								<select id="resultStatus" class="input-sm form-control input-s-sm inline" name="resultStatus" style="width: 100px;">
									<option value="">请选择</option>
									<c:forEach items="${doneStatusMap}" var="doneStatus">
										<option value="${doneStatus.key }">${doneStatus.value }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row m-t">
						<div class="col-sm-1">
							<button class="btn btn-primary" id="queryBtn" type="button" onclick="query();">查询</button>
						</div>
					</div>
				</form>
			</div>

			<hr/>

			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="example">
						<table id="jobExecuteTableColumns"
							   data-toggle="table"
							   data-url="${path}/job/queryJobExecuteList.do"
							   data-height="508"
							   data-mobile-responsive="true"
							   data-classes="table table-hover table-condensed"
							   data-striped="true"
							   data-toolbar="#jobExecuteTableToolbar"
							   data-show-columns="true"
							   data-card-view="true"
							   data-show-toggle="true"
							   data-show-refresh="true"
							   data-icon-size="outline"
							   queryParamsType="limit"
							   data-pagination="true"
							   data-side-pagination="server"
							   data-page-number="1"
							   data-page-size="10"
						>
							<thead>
							<tr>
								<th data-formatter="serialNumber" data-align="center">序号</th>
								<th data-field="jobName" data-align="center">任务名称</th>
								<th data-field="jobUuid" data-align="center">本次Uid</th>
								<th data-field="jobLink" data-align="center">任务链接</th>
								<th data-field="jobService" data-align="center">执行Service</th>
								<th data-field="resultStatus" data-align="center">执行结果</th>
								<th data-field="resultMessage" data-align="center">执行结果信息</th>
								<th data-field="jobStartTime" data-align="center">定时任务开始时间</th>
								<th data-field="jobEndTime" data-align="center">定时任务回调时间</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<!-- 引入上传控件js -->
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>

<script type="text/javascript" src="${path}/js/common.js"></script>
<!-- Peity -->
<script src="${path }/js/plugins/peity/jquery.peity.min.js?date=20160514"></script>
<!-- Fancy box -->
<script src="${path }/js/plugins/fancybox/jquery.fancybox.js?date=20160514"></script>
<!-- 自定义的图片显示工具类 -->
<script src="${path }/js/ImageUtils.js?date=20160715"></script>

<script type="text/javascript">

	var isSearchParams = false;
	(function () {
		//时间插件
		var jobStartTimeBegin = {
			elem: '#jobStartTimeBegin', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				jobStartTimeEnd.min = datas; //开始日选好后，重置结束日的最小日期
				jobStartTimeEnd.start = datas; //将结束日的初始值设定为开始日
			}
		};
		var jobStartTimeEnd = {
			elem: '#jobStartTimeEnd', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				jobStartTimeBegin.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		var jobEndTimeBegin = {
			elem: '#jobEndTimeBegin', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				jobEndTimeEnd.min = datas; //开始日选好后，重置结束日的最小日期
				jobEndTimeEnd.start = datas; //将结束日的初始值设定为开始日
			}
		};
		var jobEndTimeEnd = {
			elem: '#jobEndTimeEnd', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				jobEndTimeBegin.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		laydate(jobStartTimeBegin);
		laydate(jobStartTimeEnd);
		laydate(jobEndTimeBegin);
		laydate(jobEndTimeEnd);
	})();

	// 带条件查询
	function query() {
		isSearchParams = true;
		$("#jobExecuteTableColumns").bootstrapTable('refresh',{silent: true});  //指定查询参数重新查询
	}
	function serialNumber(value, row, index){
		var options = $("#jobExecuteTableColumns").bootstrapTable('getOptions');
		return (options.pageNumber-1) * options.pageSize + index+1;
	}

	$('#jobExecuteTableColumns').bootstrapTable({
		queryParams:function queryParams(params) {  	//配置参数
			var tempParams = {
				pageSize: params.limit,   	//页面大小
				pageNum: params.offset/params.limit+1
			};
			if(isSearchParams){
				var t = $('#searchForm').serializeArray();
				$.each(t, function() {
					tempParams[this.name] = this.value;
				});
			}
			return tempParams;
		}

	});
	// 根据城市获取区域列表
	function getLinkList() {
		var jobId = $('#jobId option:selected').val();// 选中的值
		// var cc = $("#city").get(0).selectedIndex;// 索引
		if (isnull(jobId)) {
			// 选中请选择选项
			$("#jobLinkId").html('<option value="">请选择</option>');
		} else {
			// 查询区域列表，填充数据
			$.ajax({
				url: "${path}/job/getJobLinkListByJobId.do",
				dataType: "json",
				data: {jobId: jobId},
				type: "post",
				success: function (result) {
					var jobLinkList = result.data;
					var html = '<option value="">请选择</option>';
					for (var i = 0; i < jobLinkList.length; i++) {
						html += '<option value="' + jobLinkList[i].jobLinkId + '">' + jobLinkList[i].jobLink + '</option>';
					}
					$("#jobLinkId").html(html);
				}
			});
		}
	}

	window.onload = function () {
		getLinkList();
		query();
	}

</script>