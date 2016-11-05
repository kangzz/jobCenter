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

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>简单示例</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="tabs_panels.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="tabs_panels.html#">选项1</a>
                                </li>
                                <li><a href="tabs_panels.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="clearfix layer-area" id="chutiyan">
                            <a href="javascript:;" class="btn btn-outline btn-default">初体验</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">第三方扩展皮肤</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">询问层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">提示层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">墨绿深蓝风</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">捕获页</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">页面层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">自定页</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">tips层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">iframe层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">iframe窗</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">加载层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">loading层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">小tips</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">prompt层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">tab层</a>
                            <a href="javascript:;" class="btn btn-outline btn-default">相册层</a>

                        </div>
                        <pre style="height:300px;overflow:auto;" id="demo1">
特别说明：事件需自己绑定，以下只展现调用代码。
<p>//初体验
parent.layer.alert('内容')</p>
<p>//第三方扩展皮肤
parent.layer.alert('内容', {
    icon: 1,
    skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，<a target="_balnk" style="color:#00B2E2;" href="http://layer.layui.com/skin.html#publish">去这里查阅</a>
})</p>
<p>//询问框
parent.layer.confirm('您是如何看待前端开发？', {
    btn: ['重要','奇葩'], //按钮
    shade: false //不显示遮罩
}, function(){
    parent.layer.msg('的确很重要', {icon: 1});
}, function(){
    parent.layer.msg('奇葩么么哒', {shift: 6});
});</p>
<p>//提示层
parent.layer.msg('玩命提示中');
</p>
<p>//墨绿深蓝风
parent.layer.alert('墨绿风格，点击确认看深蓝', {
    skin: 'layui-layer-molv' //样式类名
}, function(){
    parent.layer.alert('偶吧深蓝style', {
        skin: 'layui-layer-lan',
        shift: 4 //动画类型
    });
});
</p>
<p>//捕获页
parent.layer.open({
    type: 1,
    shade: false,
    title: false, //不显示标题
    content: $('.layer_notice'), //捕获的元素
    cancel: function(index){
        parent.layer.close(index);
        this.content.show();
        parent.layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构',{time: 5000});
    }
});
</p>
<p>//页面层
parent.layer.open({
    type: 1,
    skin: 'layui-layer-rim', //加上边框
    area: ['420px', '240px'], //宽高
    content: 'html内容'
});
</p>
<p>//自定页
parent.layer.open({
    type: 1,
    skin: 'layui-layer-demo', //样式类名
    closeBtn: false, //不显示关闭按钮
    shift: 2,
    shadeClose: true, //开启遮罩关闭
    content: '内容'
});
</p>
<p>//tips层
layer.tips('Hi，我是tips', '吸附元素选择器，如#id');
</p>
<p>//iframe层
parent.layer.open({
    type: 2,
    title: 'layer mobile页',
    shadeClose: true,
    shade: 0.8,
    area: ['380px', '90%'],
    content: 'http://layer.layui.com/mobile/' //iframe的url
});
</p>
<p>//iframe窗
parent.layer.open({
    type: 2,
    title: false,
    closeBtn: false,
    shade: [0],
    area: ['340px', '215px'],
    offset: 'rb', //右下角弹出
    time: 2000, //2秒后自动关闭
    shift: 2,
    content: ['http://www.zi-han.net', 'no'], //iframe的url，no代表不显示滚动条
    end: function(){ //此处用于演示
        parent.layer.open({
            type: 2,
            title: '很多时候，我们想最大化看，比如像这个页面。',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content: 'http://www.zi-han.net'
        });
    }
});
</p>
<p>//加载层
var index = parent.layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
</p>
<p>//loading层
var index = parent.layer.load(1, {
    shade: [0.1,'#fff'] //0.1透明度的白色背景
});
</p>
<p>//小tips
layer.tips('我是另外一个tips，只不过我长得跟之前那位稍有些不一样。', '吸附元素选择器', {
    tips: [1, '#3595CC'],
    time: 4000
});
</p>
<p>//prompt层
parent.layer.prompt({
    title: '输入任何口令，并确认',
    formType: 1 //prompt风格，支持0-2
}, function(pass){
    layer.prompt({title: '随便写点啥，并确认', formType: 2}, function(text){
        layer.msg('演示完毕！您的口令：'+ pass +' 您最后写下了：'+ text);
    });
});
</p>
<p>//tab层
parent.layer.tab({
    area: ['600px', '300px'],
    tab: [{
        title: 'TAB1',
        content: '内容1'
    }, {
        title: 'TAB2',
        content: '内容2'
    }, {
        title: 'TAB3',
        content: '内容3'
    }]
});
</p>
<p>//相册层
$.getJSON('js/demo/photos.json', function(json){
    layer.photos({
        photos: json //格式见API文档手册页
    });
});
</p></pre>

					<!-- 表单示例 start -->
					<div class="clearfix">
						<div class="col-sm-6">
	                        <div class="alert alert-info">
	                           	这是一个表单
	                        </div>
	                        <form role="form" class="form-horizontal m-t">
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">文本框：</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="" class="form-control" placeholder="请输入文本">
	                                    <span class="help-block m-b-none">说明文字</span>
	                                </div>
	                            </div>
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">密码框：</label>
	                                <div class="col-sm-9">
	                                    <input type="password" class="form-control" name="password" placeholder="请输入密码">
	                                </div>
	                            </div>
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">下拉列表：</label>
	                                <div class="col-sm-9">
	                                    <select class="form-control" name="">
	                                        <option>选项 1</option>
	                                        <option>选项 2</option>
	                                        <option>选项 3</option>
	                                        <option>选项 4</option>
	                                    </select>
	                                </div>
	                            </div>
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">文件域：</label>
	                                <div class="col-sm-9">
	                                    <input type="file" name="" class="form-control">
	                                </div>
	                            </div>
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">纯文本：</label>
	
	                                <div class="col-sm-9">
	                                    <p class="form-control-static">这里是纯文字信息</p>
	                                </div>
	                            </div>
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">单选框：
	                                </label>
	
	                                <div class="col-sm-9">
	                                    <label class="radio-inline">
	                                        <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">选项1</label>
	                                    <label class="radio-inline">
	                                        <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios">选项2</label>
	
	                                </div>
	                            </div>
	                            <div class="form-group draggable ui-draggable">
	                                <label class="col-sm-3 control-label">复选框：</label>
	
	                                <div class="col-sm-9">
	                                    <label class="checkbox-inline">
	                                        <input type="checkbox" value="option1" id="inlineCheckbox1">选项1</label>
	                                    <label class="checkbox-inline">
	                                        <input type="checkbox" value="option2" id="inlineCheckbox2">选项2</label>
	                                    <label class="checkbox-inline">
	                                        <input type="checkbox" value="option3" id="inlineCheckbox3">选项3</label>
	                                </div>
	                            </div>
	                            <div class="hr-line-dashed"></div>
	                            <div class="form-group draggable ui-draggable">
	                                <div class="col-sm-12 col-sm-offset-3">
	                                    <button class="btn btn-primary" type="submit">保存内容</button>
	                                    <button class="btn btn-white" type="submit">取消</button>
	                                </div>
	                            </div>
	                        </form>
                    	</div>
					</div>		
					<!-- 表单示例 end -->

                </div>
            </div>
        </div>

    </div>

    <!-- 全局js -->
    <script src="${path}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${path}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- layer javascript -->
    <script src="${path}/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${path}/js/content.js?v=1.0.0"></script>
    <script src="${path}/js/demo/layer-demo.js"></script>

</body>

</html>



<script type="text/javascript">
$(function(){
	//parent.layer.alert('点击确认更换图标');
});
</script>

