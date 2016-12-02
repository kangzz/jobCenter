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
								<input type="text" id="jobName" maxlength="20" name="jobName" class="form-control"
									   placeholder="请输入任务名称">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">任务归属系统：</span>
								<input type="text" id="jobSystem" maxlength="20" name="jobSystem" class="form-control"
									   placeholder="请输入任务归属系统">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="input-group">
								<span class="input-group-addon">有效：</span>
								<select name="isValid" id="isValid" class="form-control">
									<option value="">请选择</option>
									<c:forEach items="${IsMap}" var="isMapItem">
										<option value="${isMapItem.key }">${isMapItem.value }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row m-t">
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">创建时间：</span>
								<input name="startCreateTime" id="startCreateTime" class="form-control col-sm-4"
									   type="text" placeholder="请选择" readonly="readonly">
								<span class="input-group-addon">-</span>
								<input name="endCreateTime" id="endCreateTime" class="form-control col-sm-4" type="text"
									   placeholder="请选择" readonly="readonly">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">任务时间：</span>
								<input name="jobStartTime" id="jobStartTime" class="form-control col-sm-4"
									   type="text" placeholder="请选择" readonly="readonly">
								<span class="input-group-addon">-</span>
								<input name="jobEndTime" id="jobEndTime" class="form-control col-sm-4" type="text"
									   placeholder="请选择" readonly="readonly">
							</div>
						</div>
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
						<div class="hidden-xs" id="jobTableToolbar" role="group">
							<button type="button" class="btn btn-primary" onclick="addJob();">
								新增任务
							</button>
						</div>
						<table id="jobTableColumns"
							   data-toggle="table"
							   data-url="${path}/job/queryJobList.do"
							   data-height="508"
							   data-mobile-responsive="true"
							   data-classes="table table-hover table-condensed"
							   data-striped="true"
							   data-toolbar="#jobTableToolbar"
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
								<th data-field="jobSystem" data-align="center">任务归属系统</th>
								<th data-field="jobExecuteRule" data-align="center">执行规则</th>
								<th data-field="previousFireTime" data-align="center">上次执行时间</th>
								<th data-field="nextFireTime" data-align="center">下次执行时间</th>
								<th data-field="executeType" data-align="center">运行状态</th>
								<th data-field="jobStartTime" data-align="center">任务开始日期</th>
								<th data-field="jobEndTime" data-align="center">任务结束日期</th>
								<th data-field="isValid" data-align="center">是否有效</th>
								<%--<th data-formatter="projectImgFormatter"  data-align="center">项目图片</th>--%>
								<th data-formatter="operateFormatter" data-align="center">操作</th>
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
		var startCreateTime = {
			elem: '#startCreateTime', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				endCreateTime.min = datas; //开始日选好后，重置结束日的最小日期
				endCreateTime.start = datas; //将结束日的初始值设定为开始日
			}
		};
		var endCreateTime = {
			elem: '#endCreateTime', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				startCreateTime.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		var jobStartTime = {
			elem: '#jobStartTime', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				jobEndTime.min = datas; //开始日选好后，重置结束日的最小日期
				jobEndTime.start = datas; //将结束日的初始值设定为开始日
			}
		};
		var jobEndTime = {
			elem: '#jobEndTime', format: 'YYYY-MM-DD', max: '2099-06-16', istime: false, istoday: true,
			choose: function (datas) {
				jobStartTime.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		laydate(startCreateTime);
		laydate(endCreateTime);
		laydate(jobStartTime);
		laydate(jobEndTime);
	})();

	// 跳转到新增项目页面
	function addJob() {
		parent.addMenuItem("${path}/job/toAddJob.do", "addJob", "新增任务页");
	}
	/*
	// 逻辑删除项目
	function deleteById(id) {
		// 询问框
		parent.layer.confirm('您确认要删除？', {
			icon: 3,
			btn: ['确认', '取消'], //按钮
			shade: false //不显示遮罩
		}, function () {
			// parent.layer.msg('的确很重要', {icon: 1});
			$.ajax({
				url: "${path}/project/delete.action",
				dataType: "json",
				data: {id: id},
				type: "post",
				success: function (result) {
					if (result.data != null && parseInt(result.data.affect) > 0) {
						parent.layer.msg("删除成功！", {icon: 1});

//				   				$("#pageNum").val(1);
//				   				$("#pageSize").val(10);
//				   				$("#searchForm").submit();
						window.location.href = "${path}/project/toListProject.action";
						// parent.addMenuItem("${path}/goods/toListProject.action", "listGoods", "项目管理");
					} else if(result.data != null && parseInt(result.data.affect) == -1){
						parent.layer.alert("此项目存在尚未结束的订单，不可删除！", {icon: 2});
					} else {
						parent.layer.alert("删除失败！", {icon: 2});
					}
				}
			});
		}, function () {
			// parent.layer.msg('奇葩么么哒', {shift: 6});
		});
	}*/

	// 带条件查询
	function query() {
		isSearchParams = true;
		$("#jobTableColumns").bootstrapTable('refresh',{silent: true});  //指定查询参数重新查询
	}

/*
	// 跳转到修改页面
	function toUpdateProject(id) {
		parent.addMenuItem("${path}/project/toUpdateProject.action?id=" + id, "updateProject", "修改项目页");
	}

	//停用/启用项目
	function validOrNot(id, isValid) {
		var message = "";
		if (parseInt(isValid) == 1) {
			message = "您确认要启用？";
		} else {
			message = "您确认要停用？";
		}
		parent.layer.confirm(message, {
			icon: 3,
			btn: ['确认', '取消'], //按钮
			shade: false //不显示遮罩
		}, function () {
			// parent.layer.msg('的确很重要', {icon: 1});
			$.ajax({
				url: "${path}/project/validOrNot.action",
				dataType: "json",
				data: {id: id, isValid: isValid},
				type: "post",
				success: function (result) {
					if (result.data != null && parseInt(result.data.affect) > 0) {
						parent.layer.msg("操作成功！", {icon: 1});

						window.location.href = "${path}/project/toListProject.action";
					} else {
						parent.layer.alert("操作失败!", {icon: 2});
					}
				}
			});
		}, function () {
			// parent.layer.msg('奇葩么么哒', {shift: 6});
		});
	}



	// 根据城市获取区域列表
	function getAreaByCityCode() {
		// jQuery中获得选中select值
		var city = $('#cityCode option:selected').text();// 选中的文本
		var cityCode = $('#cityCode option:selected').val();// 选中的值
		// var cc = $("#city").get(0).selectedIndex;// 索引
		if (isnull(cityCode)) {
			// 选中请选择选项
			$("#areaName").html('<option value="">请选择</option>');
		} else {
			// 查询区域列表，填充数据
			$.ajax({
				url: "${path}/project/getAreaByCityCode.action",
				dataType: "json",
				data: {cityCode: cityCode},
				type: "post",
				success: function (result) {
					var areaList = result.list;
					var html = '<option value="">请选择</option>';
					for (var i = 0; i < areaList.length; i++) {
						html += '<option value="' + areaList[i].areaName + '">' + areaList[i].areaName + '</option>';
					}
					$("#areaName").html(html);
				}
			});
		}
	}

	function showHouseTypeImages(id) {

		$.ajax({
			url: "${path}/project/getProjectImgList.action",
			dataType: "json",
			type: "get",
			data: {'id': id},
			success: function (result) {
				ImageUtils.openImageDialogOfUrl(result.data, id);
			}
		});
	}

	window.onload = function () {
		var cityCodeF = $('#cityCode option:selected').val();
		if (!isnull(cityCodeF)) {
			var areaNameF = $('#areaName').val();
			// 查询区域列表，填充数据
			$.ajax({
				url: "${path}/project/getAreaByCityCode.action",
				dataType: "json",
				data: {cityCode: cityCodeF},
				type: "post",
				success: function (result) {
					var areaList = result.list;
					var html = '<option value="">请选择</option>';
					for (var i = 0; i < areaList.length; i++) {
						if (areaNameF == areaList[i].areaName) {
							html += '<option selected="selected" value="' + areaList[i].areaName + '">' + areaList[i].areaName + '</option>';
						} else {
							html += '<option value="' + areaList[i].areaName + '">' + areaList[i].areaName + '</option>';
						}
					}
					$("#areaName").html(html);
				}
			});
		}
	}


	function setHeadPic(imgBid, projectId) {
		parent.layer.confirm('您确认要将此图设为首页？', {
			icon: 3,
			btn: ['确认', '取消'], //按钮
			shade: false //不显示遮罩
		}, function () {
			$.ajax({
				url: "${path}/project/setHeadPic.action",
				dataType: "json",
				type: "get",
				data: {'imgBid': imgBid, 'projectId': projectId},
				success: function (result) {
					var coun = result.affect;
					if (parseInt(coun) > 0) {
						parent.layer.msg("设置成功！", {icon: 1});
						showHouseTypeImages(projectId);
					} else {
						parent.layer.alert("设置首页出错！", {icon: 2});
					}
				}
			});
		}, function () {

		});
	}
	*/
	function deleteJobInfoById(jobId) {
		parent.layer.confirm('您确认要删除？', {
			icon: 3,
			btn: ['确认', '取消'], //按钮
			shade: false //不显示遮罩
		}, function () {
			$.ajax({
				url: "${path}/job/deleteJobInfoById.do",
				dataType: "json",
				type: "get",
				data: {'jobId': jobId},
				success: function (result) {
					if (result.status != null && 'success' == result.status) {
						parent.layer.msg("删除成功！", {icon: 1});
						query();
					} else {
						parent.layer.alert(result.error_message, {icon: 2});
					}
				}
			});
		}, function () {
		});
	}
	//不展示/展示项目
	function changeJobValidById(jobId, isValid) {
		var message = "";
		if (parseInt(isValid) == 1) {
			message = "您确认要启用该任务？";
		} else {
			message = "您确认要停用该任务？";
		}
		parent.layer.confirm(message, {
			icon: 3,
			btn: ['确认', '取消'], //按钮
			shade: false //不显示遮罩
		}, function () {
			$.ajax({
				url: "${path}/job/changeJobValidById.do",
				dataType: "json",
				data: {jobId: jobId, isValid: isValid},
				type: "post",
				success: function (result) {
					if (result.status != null && 'success' == result.status) {
						parent.layer.msg("操作成功！", {icon: 1});
						query();
					} else {
						parent.layer.alert(result.error_message, {icon: 2});
					}
				}
			});
		}, function () {
			// parent.layer.msg('奇葩么么哒', {shift: 6});
		});
	}

	function toUpdateJobInfo(jobId) {
		parent.addMenuItem("${path}/job/toUpdateJobInfo.do?jobId=" + jobId, "updateJobInfo", "修改任务页");
	}
	function toQueryJobExecuteList(jobId) {
		parent.addMenuItem("${path}/job/toQueryJobExecuteList.do?jobId=" + jobId, "toQueryJobExecuteList", "执行结果查询");
	}




	function serialNumber(value, row, index){
		var options = $("#jobTableColumns").bootstrapTable('getOptions');
		return (options.pageNumber-1) * options.pageSize + index+1;
	}

	$('#jobTableColumns').bootstrapTable({
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
	function operateFormatter(value, row, index){
		var isValid = row.isValid;
		var jobId = row.jobId;
		var str ="";
		str = str + "<a href=\"#\" onclick=\"deleteJobInfoById('"+jobId+"');\">删除</a>&nbsp;";
		str = str + "<a href=\"#\" onclick=\"toUpdateJobInfo('"+jobId+"');\">修改</a>&nbsp;";
		if(isValid == '是'){
			str = str + "<a href=\"javascript:;\" onclick=\"changeJobValidById('"+jobId+"', '0');\">停用</a>&nbsp;";
		}else{
			str = str + "<a href=\"javascript:;\" onclick=\"changeJobValidById('"+jobId+"', '1');\">启用</a>&nbsp;";
		}
		str = str + "<a href=\"#\" onclick=\"toQueryJobExecuteList('"+jobId+"');\">执行结果</a>&nbsp;";
		return str;
	}
</script>