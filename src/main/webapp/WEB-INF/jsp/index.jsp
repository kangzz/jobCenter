<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>定时任务管理中心</title>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" src="${path }/img/profile_small.jpg"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear"/>
                               <span class="block m-t-xs"><strong class="font-bold">定时任务管理中心</strong></span>
                        </a>
                    </div>
                </li>

                <!-- 菜单 开始 -->
                <c:forEach items="${menuList}" var="m1">
                <li>
                    <c:choose>
                    <c:when test="${m1.href =='' }">
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">${m1.name }</span>
                        <span class="fa arrow"></span>
                    </a>
                    <c:if test="${m1.children.size()>0}">
                    <ul class="nav nav-second-level">
                        <c:forEach items="${m1.children}" var="m2">
                            <li>
                                <a class="J_menuItem" href="${path }${m2.href }" data-index="0">${m2.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    </c:if>
                    </c:when>
                    <c:otherwise>
                <li>
                    <a class="J_menuItem" href="${m1.href }"><i class="fa fa-columns"></i> <span class="nav-label">${m1.name }</span></a>
                </li>
                </c:otherwise>
                </c:choose>
                </li>
                </c:forEach>
            </ul>
                <!-- 菜单 结束 -->
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>

                    <%--<ul class="navbar-city-custom">

                        <li><label class="control-label mtop">选择项目：</label></li>
                        <li>
                            <div class="btn-group roll-nav roll-right">
                                <button id="button-project" class="dropdown" data-toggle="dropdown" aria-expanded="true" style="width: 160px">
                                    ${sessionScope.CURRENTUSER.currentProject.projectName}
                                    <span class="caret"></span>
                                </button>
                                <ul role="menu" class="dropdown-menu dropdown-menu-right" style="right: auto">
                                    <c:forEach items="${projectList}" var="m1">
                                        <li class="J_tab"><a href="javascript:selectProject('${m1.projectName}','${m1.bid}','${m1.cityCode}','${m1.projectCode}');">${m1.projectName}</a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </li>

                    </ul>--%>

                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown hidden-xs">
                        <a class="right-sidebar-toggle" aria-expanded="false">
                            <i class="fa fa-tasks"></i> 主题
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">定时任务大盘</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <%--<a href="javascript:exit();" class="roll-nav roll-right J_tabExit" ><i class="fa fa fa-sign-out"></i> 退出</a>--%>
            <a href="${path}/logout.do" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${path}/jobReport/queryJobTotalInfo.do?v=4.0" frameborder="0" data-id="index_v1.html" seamless>
            </iframe>
        </div>
        <div class="footer">
            <div class="pull-right">定时任务管理中心
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">

            <ul class="nav nav-tabs navs-3">

                <li class="active" style="width:100%;">
                    <a data-toggle="tab" href="#tab-1">
                        <i class="fa fa-gear"></i> 主题
                    </a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="sidebar-title">
                        <h3><i class="fa fa-comments-o"></i> 主题设置</h3>
                        <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                    </div>
                    <div class="skin-setttings">
                        <div class="title">主题设置</div>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                    <label class="onoffswitch-label" for="collapsemenu">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                    <label class="onoffswitch-label" for="fixednavbar">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                    <label class="onoffswitch-label" for="boxedlayout">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                        </div>
                        <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                        </div>
                        <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!--右侧边栏结束-->
</div>
</body>
</html>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<%--<!-- 全局js -->
<script src="${path}/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 全局js -->
<script src="${path }/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${path }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${path }/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${path }/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="${path }/js/contabs.js"></script>

<!-- 第三方插件 -->
<script src="${path }/js/plugins/pace/pace.min.js"></script>--%>

<script type="text/javascript">

    /* 新建选项卡  子页面使用 */
    function addMenuItem(dataUrl, iframeName, menuName) {
        // 获取标识数据
        /*var dataUrl = '
        ${path}/order/toOrderDetail.action?orderNo='+orderNo,
         dataIndex = 1000+index,
         menuName = '订单详情:'+orderNo;
         if (orderNo == undefined || $.trim(orderNo).length == 0)return false;
         */
        var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.J_menuTab').removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="J_iframe" name="iframe' + iframeName + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

        //显示loading提示
//        var loading = layer.load();
//
//        $('.J_mainContent iframe:visible').load(function () {
//            //iframe加载完成后隐藏loading提示
//            layer.close(loading);
//        });
        // 添加选项卡
        $('.J_menuTabs .page-tabs-content').append(str);
        var activeTab = $('.J_menuTab.active');
        var marginLeftVal = calSumWidth(activeTab.prevAll()), marginRightVal = calSumWidth(activeTab.nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".J_menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - activeTab.outerWidth(true) - activeTab.next().outerWidth(true))) {
            if ((visibleWidth - activeTab.next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - activeTab.outerWidth(true) - activeTab.prev().outerWidth(true))) {
            scrollVal = marginLeftVal - activeTab.prev().outerWidth(true);
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        }, "fast");
        return false;
    }

    //计算元素集合的总宽度
    function calSumWidth(elements) {
        var width = 0;
        $(elements).each(function () {
            width += $(this).outerWidth(true);
        });
        return width;
    }

    //操作选择项目 created by cuigh6 on 2016/05/25
    function selectProject(projectName, projectBid, cityCode, projectCode) {
        $('#button-project').html(projectName + '<span class="caret"></span>');
        closeAllTable();
        //发送请求
        $.ajax({
            url: "${path}/security/saveProjectToSession.action",
            dataType: "json",
            type: "post",
            data: {"projectName": projectName, "cityCode": cityCode, "projectCode": projectCode, "projectBid": projectBid},
            success: function (result) {
                console.log(result.status);
            }
        });
        //刷新iframe方法
        window.parent.frames["iframe0"].location.href = "${path}/security/toWorkbench.action?v=4.0";
    }
    //关闭tab页
    function closeAllTable() {
        $('.page-tabs-content').children("[data-id]").not(":first").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').children("[data-id]:first").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').show();
            $(this).addClass("active");
        });
        $('.page-tabs-content').css("margin-left", "0");
    }

    /**
     * wangws21 2016-6-6
     * 关闭当前显示的的选项卡
     */
    function closeActiveTab(){
        $('.J_menuTab.active i').trigger("click");
    }
    
    // 关闭所有选项卡并打开新的选项卡/by zhangtb 2016-6-6 15:37:40
    function turnHref(url, ref, title){
        closeAllTable();
        addMenuItem(url, ref, title);
    }
</script>
