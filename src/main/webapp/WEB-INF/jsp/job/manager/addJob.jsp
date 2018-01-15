<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增项目</title>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <form id="save_jobInfo" action="${path}/job/saveJobInfo.action" method="post">
                <table border="0" class="table-hover table-condensed" style="margin-top: 10px;">
                    <tr>
                        <td style="width: 20%;" align="right"><font style="color: red;">*</font>任务名称:</td>
                        <td>
                            <input id="jobName" maxlength="20" name="jobName" type="text" class="form-control"
                                   style="width: 220px;">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" align="right"><font style="color: red;">*</font>归属系统:</td>
                        <td>
                            <select id="jobSystem" class="input-sm form-control input-s-sm inline" name="jobSystem" style="width: 100px;">
                                <option value="">请选择</option>
                                <c:forEach items="${jobSystemTypeMap}" var="jobSystem">
                                    <option value="${jobSystem.key }">${jobSystem.value }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" align="right"><font style="color: red;">*</font>执行类型:</td>
                        <td>
                            <select id="jobExecuteType" class="input-sm form-control input-s-sm inline" name="jobExecuteType" style="width: 100px;">
                                <c:forEach items="${jobExecuteTypeMap}" var="jobExecuteType">
                                    <option value="${jobExecuteType.key }">${jobExecuteType.value }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 12%;" align="right"><font style="color: red;">*</font>执行规则:</td>
                        <td>
                            <input id="jobExecuteRule" maxlength="20" name="jobExecuteRule" type="text" class="form-control"
                                   style="width: 220px;">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" align="right"><font style="color: red;">*</font>通知成功:</td>
                        <td>
                            <select id="jobNotifySucc" class="input-sm form-control input-s-sm inline" name="jobNotifySucc" style="width: 100px;">
                                <c:forEach items="${IsMap}" var="isMapItem">
                                    <option value="${isMapItem.key }">${isMapItem.value }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 12%;" align="right"><font style="color: red;">*</font>重试次数:</td>
                        <td>
                            <select id="jobRetryTimes" class="input-sm form-control input-s-sm inline" name="jobRetryTimes"
                                    style="width: 100px;">
                                <option value="1">1次</option>
                                <option value="2">2次</option>
                                <option value="3">3次</option>
                                <option value="4">4次</option>
                                <option value="5">5次</option>
                                <option value="6">6次</option>
                                <option value="7">7次</option>
                                <option value="8">8次</option>
                                <option value="9">9次</option>
                                <option value="10">10次</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 12%;" align="right"><font style="color: red;">*</font>开始时间:</td>
                        <td>
                            <input type="text" id="jobStartTime" name="jobStartTime" class="form-control inline"
                                   style="width: 220px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 12%;" align="right"><font style="color: red;">*</font>结束时间:</td>
                        <td>
                            <input type="text" id="jobEndTime" name="jobEndTime" class="form-control inline"
                                   style="width: 220px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%;" align="right"><font style="color: red;">*</font>是否生效:</td>
                        <td>
                            <select id="isValid" class="input-sm form-control input-s-sm inline" name="isValid" style="width: 100px;">
                                <c:forEach items="${IsMap}" var="isMapItem">
                                    <option value="${isMapItem.key }">${isMapItem.value }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 12%;" align="right"><font style="color: red;">*</font>执行机器:</td>
                        <td>
                            <input id="jobLinkListStr" maxlength="200" name="jobLinkListStr" type="text" class="form-control"
                                   style="width: 480px;" placeholder="格式:serviceName1,jobLinkUrl1;serviceName2,jobLinkUrl2">
                        </td>
                    </tr>
                </table>
                <div style="margin-left: 450px; margin-top: 10px;">
                    <input class="btn btn-primary" type="button" onclick="save();" value="保存" style="width: 80px;"/>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script type="text/javascript" src="${path}/js/common.js"></script>
<script type="text/javascript">
    function save() {
        // $("#save_good").submit();
        var jobName = $("#jobName").val();
        if (isnull(jobName)) {
            parent.layer.alert("任务名称不能为空！", {icon: 2});
            return;
        }
        var jobSystem = $("#jobSystem").val();
        if (isnull(jobSystem)) {
            parent.layer.alert("归属系统不能为空！", {icon: 2});
            return;
        }

        var jobExecuteRule = $("#jobExecuteRule").val();
        if (isnull(jobExecuteRule)) {
            parent.layer.alert("执行规则不能为空！", {icon: 2});
            return;
        }

        var jobStartTime = $("#jobStartTime").val();
        if (isnull(jobStartTime)) {
            parent.layer.alert("开始时间不能为空！", {icon: 2});
            return;
        }

        var jobEndTime = $("#jobEndTime").val();
        if (isnull(jobEndTime)) {
            parent.layer.alert("结束时间不能为空！", {icon: 2});
            return;
        }

        var jobLinkListStr = $("#jobLinkListStr").val();
        if (isnull(jobLinkListStr)) {
            parent.layer.alert("执行机器不能为空！", {icon: 2});
            return;
        }
        $.ajax({
            url: "${path}/job/saveJobInfo.do",
            dataType: "json",
            data: $("#save_jobInfo").serialize(),
            type: "post",
            success: function (result) {
                if (result.status != null && 'success' == result.status) {
//							alert("保存成功.");
                    swal({
                        title: "提示信息",
                        text: "保存成功！",
                        type: "success"
                    }, function () {
                        parent.turnHref("${path}/job/jobList.do", "jobList", "定时任务列表");
                    });

                    <%--parent.layer.msg("保存成功！", {icon: 1});--%>
                    <%--window.close();--%>
                    <%--window.location.href = "${path}/project/toListProject.action";--%>
                } else {
                    parent.layer.alert(result.error_message, {icon: 2});
                }
            }
        });
    }

    (function () {
        //时间插件
        var jobStartTime = {
            elem: '#jobStartTime', format: 'YYYY-MM-DD hh:mm:ss', max: '2099-06-16 00:00:00', istime: true, istoday: true
        };
        laydate(jobStartTime);
        var jobEndTime = {
            elem: '#jobEndTime', format: 'YYYY-MM-DD hh:mm:ss', max: '2099-06-16 00:00:00', istime: true, istoday: true
        };
        laydate(jobEndTime);
    })();
</script>
</body>
</html>