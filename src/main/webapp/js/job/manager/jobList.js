$(document).ready(function() {

});

var isSearchParams = false;

var $result = $('#examplebtTableEventsResult');

/* 事件监听 */
$('#userTableColumns').on('all.bs.table', function(e, name, args) {
	// console.log('Event:', name, ', data:', args);
}).on('click-row.bs.table', function(e, row, $element) {
	$result.text('Event: click-row.bs.table');
}).on('dbl-click-row.bs.table', function(e, row, $element) {
	$result.text('Event: dbl-click-row.bs.table');
}).on('sort.bs.table', function(e, name, order) {
	$result.text('Event: sort.bs.table');
}).on('check.bs.table', function(e, row) {
	$result.text('Event: check.bs.table');
}).on('uncheck.bs.table', function(e, row) {
	$result.text('Event: uncheck.bs.table');
}).on('check-all.bs.table', function(e) {
	$result.text('Event: check-all.bs.table');
}).on('uncheck-all.bs.table', function(e) {
	$result.text('Event: uncheck-all.bs.table');
}).on('load-success.bs.table', function(e, data) {
	$result.text('Event: load-success.bs.table');
}).on('load-error.bs.table', function(e, status) {
	toastr.error("加载数据失败，请稍后再试");
	$result.text('Event: load-error.bs.table');
}).on('column-switch.bs.table', function(e, field, checked) {
	$result.text('Event: column-switch.bs.table');
}).on('page-change.bs.table', function(e, size, number) {
	$result.text('Event: page-change.bs.table');
}).on('search.bs.table', function(e, text) {
	$result.text('Event: search.bs.table');
});

$('#userTableColumns').bootstrapTable({
	icons : {
		refresh : 'glyphicon-repeat',
		toggle : 'glyphicon-list-alt',
		columns : 'glyphicon-list'
	}
});

/* 表格里的操作按钮 */
function operateFormatter(value, row, index) {
	return [
		'<button type="button" onclick="distribution(this);" class="btn btn-primary" data-toggle="modal" data-target="">分配用户</button>',
		'<button type="button" onclick="toUpdateProject(this);" class="btn btn-primary" data-toggle="modal">修改</button>',
		'<button type="button" onclick="deleteById(this);" class="btn btn-primary" data-toggle="modal">删除</button>'
	].join('  ');
}

function deleteById($this) {
	var uid = $($this).parent("td").parent("tr").find("td").first().html();

	//询问框
	parent.layer.confirm('您确定要删除吗？', {
		btn: ['确认','取消'], // 按钮
		shade: false // 不显示遮罩
	}, function() {
		// 确认删除操作

		parent.layer.msg('删除成功！', {icon: 1});
		window.location.reload();
	}, function() {
		// 取消，无操作
		// parent.layer.msg('奇葩么么哒', {shift: 6});
	});
}

// 显示分配角色窗口
function distribution($this) {
	// 获取用户id
	var uid = $($this).parent("td").parent("tr").find("td").first().html();

	$("#user_id").val(uid);
	$("#modal-body").html('');
	// 根据用户id请求菜单列表数据
	var path = $('#url_path').val();
	$.getJSON(path + "/user/getRoleList.action?uid="+uid, function(data, status) {
		if(data != null) {
			var roleList = data.roleList;
			var arr = data.list;
			var content = '<table class="table-hover table-condensed" style="margin-top: 10px;border: 0;">';
			for(var j=0;j<arr.length;j++) {
				if(isnull(roleList) || roleList.length <= 0) {
					content += '<tr><td><input type="checkbox" name="roleId" value="' + arr[j].roleId + '" />' + arr[j].name + '</td></tr>';
				} else {
					for(var k=0;k<roleList.length;k++) {
						if(roleList[k].roleId == arr[j].roleId) {
							content += '<tr><td><input type="checkbox" checked="checked" name="roleId" value="' + arr[j].roleId + '" />' + arr[j].name + '</td></tr>';
						} else {
							content += '<tr><td><input type="checkbox" name="roleId" value="' + arr[j].roleId + '" />' + arr[j].name + '</td></tr>';
						}
					}
				}
			}
			content += '</table>';

			$("#modal-title").text("分配角色");
			// $("#modal-btn").text("添加");
			$("#modal-body").html(content);
			$("#myModal5").modal();
		}
	});
}

/* 获取远程数据的参数 params:limit, offset, search, sort, order */
function queryParams(params) { // 配置参数
	var tempParams = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize : params.limit, // 页面大小
		pageIndex : params.offset, // 页码
		sort : params.sort, // 排序列名
		sortOrder : params.order,// 排位命令（desc，asc）
		search : params.search
	};
	if (isSearchParams) {
		// 如果是查询按钮触发的刷新table数据 就要带搜索参数
		var t = $('#queryForm').serializeArray();
		$.each(t, function() {
			tempParams[this.name] = this.value;
		});
	}
	return tempParams;
}

/* 查询用户列表 */
$("#searchUser").click(function() {
	isSearchParams = true;
	$("#userTableColumns").bootstrapTable('refresh', {
		silent : true
	}); // 指定查询参数重新查询
});

/* 表格行样式 */
function userRowStyle(row, index) {
	var classes = [ 'active', 'success', 'info', 'warning', 'danger' ];
	var classIndex = index % (classes.length);
	if (index % 2 === 0) {
		return {
			classes : classes[classIndex]
		};
	}
	return {};
}

/* 时间格式化 */
function dateFormatter(value, row, index) {
	var format = 'YYYY-MM-DD hh:mm:ss';
	return laydate.now(value, format);
}

// 保存用户角色信息
function saveUserRole() {
	var path = $('#url_path').val();
	$.post(path+"/zry/save.action",$("#save_user_role").serialize(),function(data){
		if (data == "1") {
			swal({
				title: "提示信息",
				text: "保存成功.",
				type: "success"
			});
			$('#myModal5').modal('hide');
			window.location.reload();
		} else {
			alert("保存失败.");
		}
	},"json");
}

// 新增项目
function toAddProject() {
	$("#modal-titles").text("新增项目");
	$("#modal-btns").text("保存");
	$("#modal-bodys").html('');
	$("#myModal").modal();
}

function addProject() {
	var path = $('#url_path').val();
	$.post(path+"/project/save.action",$("#save_update").serialize(),function(data){
		if (data == "1") {
			swal({
				title: "提示信息",
				text: "保存成功.",
				type: "success"
			});
			$('#myModal5').modal('hide');
			window.location.reload();
		} else {
			alert("保存失败.");
		}
	},"json");
}

// 修改项目信息
function toUpdateProject() {
	$("#modal-titles").text("修改项目");
	$("#modal-btns").text("确认");
	// $("#modal-bodys").html("");
	$("#myModal").modal();
}