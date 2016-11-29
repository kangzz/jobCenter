/**
 * Created by wangws21 on 2016/5/27.
 * 显示图片的公用js类
 * 功能：弹窗显示图片，点击图片可以放大
 * 需要引入：layer.js，peity.js,fancybox.js fancybox.css
 */
var ImageUtils = {
    openImageDialog: function (data) {
        var html = this.buildTemplate(data);
        layer.open({
            title: "图片",
            shadeClose: true,
            content: html,
            area: ['740px', '520px']
        });
    },
    buildTemplate: function (data) {
        var temp = new Array();
        if (data && data.length > 0) {
            temp.push('    <div class="wrapper wrapper-content" style="padding: 0px;">                                                 ');
            temp.push('        <div class="row">                                                                                       ');
            temp.push('            <div class="col-sm-12">                                                                             ');
            temp.push('                <div class="ibox float-e-margins" style="margin: 0px;">                                         ');
            temp.push('                    <div class="ibox-content" style="padding: 2px; border-style: none;">                        ');
            for (var i in data) {
                temp.push('                        <a class="fancybox" href="' + data[i].imgUrl + '" title=""> ');
                temp.push('                            <img alt="image" src="' + data[i].imgUrl + '" />            ');
                temp.push('                        </a>                                                                                ');
            }
            temp.push('                    </div>                                                                                      ');
            temp.push('                </div>                                                                                          ');
            temp.push('            </div>                                                                                              ');
            temp.push('        </div>                                                                                                  ');
            temp.push('    </div>                                                                                                      ');
        } else {
            temp.push('    <div class="wrapper wrapper-content" style="padding: 0px;">没有图片</div>                          ');
        }
        return temp.join("\r\n");
    },

    openImageDialogOfUrl: function (data, projectid) {
        var html = this.buildTemplateOfUrl(data, projectid);
        layer.open({
            title: "图片",
            shadeClose: true,
            content: html,
            area: ['740px', '520px'],
            success: function () {
                // 初始化Web Uploader
                uploader = WebUploader.create({
                    auto: true,
                    // swf文件路径
                    swf: contextPath + '/js/plugins/webuploader/Uploader.swf',
                    // 文件接收服务端。
                    server: contextPath + '/project/uploadProjectPic.action?projectId=' + projectid,
                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: '#filePicker',
                    // 只允许选择文件，可选。
                    accept: {
                        title: 'Images',
                        extensions: 'gif,jpg,jpeg,bmp,png',
                        mimeTypes: 'image/*'
                    }
                });
                // 文件上传成功，给item添加成功class, 用样式标记上传成功。
                uploader.on('uploadSuccess', function (file, response) {
                    var count = response.affect;
                    if (parseInt(count) > 0) {
                        showHouseTypeImages(projectid);

                        //var html = '<img alt="" style="float: left;margin: 5px;width: 24%;height: 110px;"';
                        //html += ' src="' + response.imgUrl + '" />';
                        //$("#ibox-content").append(html);
                        //var htm = '<input type="hidden" name="imgUrl" value="' + response.imgUrl + '" />';
                        //$("#hide_img_url").append(htm);// 填充表单隐藏控件
                    } else {
                        parent.layer.alert("上传图片失败!", {icon: 2});
                    }
                });

                // 文件上传失败，现实上传出错。
                uploader.on('uploadError', function (file) {
                    parent.layer.alert("上传图片失败!", {icon: 2});
                });

                //解决webuploader在弹出框中浏览文件按钮不起作用的bug  解决的很牵强
                $("#filePicker .webuploader-pick").click(function () {
                    $("#filePicker :file").click();
                });
            }
        });
    },
    buildTemplateOfUrl: function (data, projectId) {
        var temp = new Array();
        temp.push('<div align="center"><a href="javaScript:void(0);" id="filePicker">上传图片</a></div>      ');
        temp.push('<div id="hide_img_url"></div>                                               ');

        //if (data && data.length > 0) {
        temp.push('    <div class="wrapper wrapper-content" style="padding: 0px;">                                                 ');
        temp.push('        <div class="row">                                                                                       ');
        temp.push('            <div class="col-sm-12">                                                                             ');
        temp.push('                <div class="ibox float-e-margins" style="margin: 0px;">                                         ');
        temp.push('                    <div id="ibox-content" class="ibox-content" style="padding: 2px; border-style: none;">      ');
        for (var i in data) {
            temp.push('                        <div style="width: 24%; margin: 10px;float: left;text-align: center;"> ');
            temp.push('                        <a class="fancybox" href="' + data[i].imgUrl + '" title="">                                ');
            temp.push('                            <img style="width: 100%;" alt="image" src="' + data[i].imgUrl + '" />                  ');
            if (i == 0) {
                temp.push('                    <a style="color: #cc0000; font-weight: bold;">当前首页</a>                                                   ');
            } else {
                temp.push('                            <a onclick="setHeadPic(\'' + data[i].imgBid + '\', \'' + projectId + '\');">设为首页</a> ');
            }
            temp.push('                        &nbsp;&nbsp;&nbsp;<a onclick="deleteProjectPic(\'' + data[i].imgBid + '\', \'' + projectId + '\');">删除</a></a></div>  ');
        }
        temp.push('                    </div>                                                                                      ');
        temp.push('                </div>                                                                                          ');
        temp.push('            </div>                                                                                              ');
        temp.push('        </div>                                                                                                  ');
        temp.push('    </div>                                                                                                      ');
        //    } else {
        //        temp.push('    <div class="wrapper wrapper-content" style="padding: 0px;">没有图片</div>                          ');
        //    }
        return temp.join("\r\n");
    },
    /*区域图片上传 created by cuigh6 on 2016/06/17 */
    openImageDialogForAreaPic: function (type, data, url) {

        var loading = layer.load(); // 显示加载提示
        /*加载已上传的图片*/
        var html = template('uploadTemp', {'fileNumLimit': 10, 'list': data});
        /*弹出支付方式选择窗口 start*/
        layer.open({
            title: "区域图片",
            shadeClose: true,
            content: html,
            area: ['740px', '520px']
            , success: function () {
                var uploader = uploadImage({
                    baseUrl: contextPath,
                    serverUrl: contextPath + url,
                    //param: {stayPersonBid: stayPersonBid},
                    fileNumLimit: 10
                });


                uploader.on('uploadSuccess', function (file, response) {
                    //添加隐藏域
                    var picData = response.data;
                    /*var imgId= imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.lastIndexOf("."));*/
                    var imgId = picData.stayPersonImgBid;
                    var htm = '<input id=' + imgId + ' type="hidden" name="imgUrl" value="' + picData.imgUrl + '" />';
                    $("#hide_img_url").append(htm);// 填充表单隐藏控件
                });

                /*图片删除按钮 start*/
                $("#stayPersonImg .imgList").off('mouseenter').on('mouseenter', function () {
                    $(this).find(".file-panel").stop().animate({height: 30});
                }).off('mouseleave').on('mouseleave', function () {
                    $(this).find(".file-panel").stop().animate({height: 0});
                });
                //暂时只是页面删除
                $("#stayPersonImg .file-panel .cancel").off("click").on("click", function () {
                    var $img = $(this).closest(".imgList");
                    var stayPersonImgBid = $img.attr("staypersonimgbid");
                    /* var imgId = stayPersonImgBid.substring(stayPersonImgBid.lastIndexOf("/")+1,stayPersonImgBid.lastIndexOf("."));*/
                    $('input[id=' + stayPersonImgBid + ']').remove();//删除隐藏input标签

                    /*类型为修改时 删除图片*/
                    if (type == "updateAreaImg") {
                        $.ajax({
                            url: contextPath + "/area/delAreaPic.action",
                            dataType: "json",
                            type: "get",
                            data: {'areaImgBid': stayPersonImgBid},
                            success: function (result) {
                                if (result.success == true) {
                                    $img.remove();
                                } else {
                                    toastr.error("删除失败！");
                                }
                            },
                            error: function () {
                                toastr.error("删除失败！");
                            }
                        });
                    } else {
                        $img.remove();
                    }

                    return false;
                });
                /*图片删除按钮 end*/
            }
        });
        /*弹出支付方式选择窗口 end*/
        layer.close(loading);  //加载完成后隐藏加载提示
    },
    /*床型图片上传 created by cuigh6 on 2016/06/17 */
    openImageDialogForBedTypePic: function (type, data, url) {

        var loading = layer.load(); // 显示加载提示
        /*加载已上传的图片*/
        var html = template('uploadTemp', {'fileNumLimit': 10, 'list': data});
        /*弹出支付方式选择窗口 start*/
        layer.open({
            title: "区域图片",
            shadeClose: true,
            content: html,
            area: ['740px', '520px']
            , success: function () {
                var uploader = uploadImage({
                    baseUrl: contextPath,
                    serverUrl: contextPath + url,
                    //param: {stayPersonBid: stayPersonBid},
                    fileNumLimit: 10
                });


                uploader.on('uploadSuccess', function (file, response) {
                    //添加隐藏域
                    var picData = response.data;
                    /*var imgId= imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.lastIndexOf("."));*/
                    var imgId = picData.stayPersonImgBid;
                    var htm = '<input id=' + imgId + ' type="hidden" name="imgUrl" value="' + picData.imgUrl + '" />';
                    $("#hide_img_url").append(htm);// 填充表单隐藏控件
                });

                /*图片删除按钮 start*/
                $("#stayPersonImg .imgList").off('mouseenter').on('mouseenter', function () {
                    $(this).find(".file-panel").stop().animate({height: 30});
                }).off('mouseleave').on('mouseleave', function () {
                    $(this).find(".file-panel").stop().animate({height: 0});
                });
                //暂时只是页面删除
                $("#stayPersonImg .file-panel .cancel").off("click").on("click", function () {
                    var $img = $(this).closest(".imgList");
                    var stayPersonImgBid = $img.attr("staypersonimgbid");
                    /* var imgId = stayPersonImgBid.substring(stayPersonImgBid.lastIndexOf("/")+1,stayPersonImgBid.lastIndexOf("."));*/
                    $('input[id=' + stayPersonImgBid + ']').remove();//删除隐藏input标签

                    /*类型为修改时 删除图片*/
                    if (type == "updateBedTypeImg") {
                        $.ajax({
                            url: contextPath + "/bedType/delBedTypePic.action",
                            dataType: "json",
                            type: "get",
                            data: {'bedTypeImgBid': stayPersonImgBid},
                            success: function (result) {
                                if (result.success == true) {
                                    $img.remove();
                                } else {
                                    toastr.error("删除失败！");
                                }
                            },
                            error: function () {
                                toastr.error("删除失败！");
                            }
                        });
                    } else {
                        $img.remove();
                    }

                    return false;
                });
                /*图片删除按钮 end*/
            }
        });
        /*弹出支付方式选择窗口 end*/
        layer.close(loading);  //加载完成后隐藏加载提示
    }

}

$('.fancybox').fancybox({
    openEffect: 'none',
    closeEffect: 'none'
});



