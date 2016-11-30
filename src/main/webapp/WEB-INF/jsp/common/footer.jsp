<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 全局js -->
<script src="${path}/js/jquery.min.js?v=2.1.4"></script>
<script src="${path}/js/bootstrap.min.js?v=3.3.6"></script>
<!-- Bootstrap table -->
<script src="${path}/js/plugins/bootstrap-table/bootstrap-table.min.js?date=20160508"></script>
<script src="${path}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js?date=20160508"></script>
<script src="${path}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js?date=20160508"></script>

<!-- 全局js -->
<script src="${path }/js/plugins/metisMenu/jquery.metisMenu.js?date=20160508"></script>
<script src="${path }/js/plugins/slimscroll/jquery.slimscroll.min.js?date=20160508"></script>
<script src="${path }/js/plugins/layer/layer.min.js?date=20160508"></script>

<!-- 自定义js -->
<script src="${path }/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="${path }/js/contabs.js?date=20160508"></script>
<script src="${path}/js/content.js?v=1.0.0"></script>

<!-- 第三方插件 -->
<script src="${path }/js/plugins/pace/pace.min.js?date=20160508"></script>

<!-- layerDate plugin javascript -->
<script src="${path }/js/plugins/layer/laydate/laydate.js?date=20160508"></script>
<!-- Toastr script -->
<script src="${path }/js/plugins/toastr/toastr.min.js?date=20160508"></script>
<!-- Sweet alert -->
<script src="${path }/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${path }/js/plugins/validate/jquery.validate.min.js?date=20160530"></script>
<script src="${path }/js/plugins/validate/messages_zh.min.js?date=20160530"></script>
<script src="${path}/js/validate.js?date=20160530"></script>

<!-- 自定义的公共方法、属性 -->
<script type="text/javascript">
    var curWin = this;                  //当前窗口对象 用于判断是否在父窗口中
    var contextPath = '${path}';        //基础url
    var winHeight = $(window).height(); //iframe子页窗口高度

    $.ajaxSetup({
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        complete:function(XMLHttpRequest,textStatus){
            //通过XMLHttpRequest取得响应头，sessionstatus，
            var sessionStatus=XMLHttpRequest.getResponseHeader("sessionStatus");
            if(sessionStatus=="session_timeout"){
                //如果超时就处理 ，指定要跳转的页面
                alert("登录超时，请重新登录!");
                window.location = "<c:url value="${path}/login.do" />";
            }
        }
    });
</script>