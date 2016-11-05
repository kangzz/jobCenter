<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>自如驿</title>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
    <style>
        .mtop {
            margin-top: 20px;
        }

        .mbottom {
            margin: 10px 0;
        }

        .today-info {
            width: 30%;
        }

        .green {
            color: #1ab394;
            font-size: 20px;
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-8">

            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>管家工作台</h5>
                </div>
                <div class="ibox-content">
                    <table class="table">
                        <tbody>
                        <tr style="border:none;">
                            <td class="col-sm-2"><h4 class="btn btn-primary btn-lg">入住退房</h4></td>
                            <td class="col-sm-8">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label mtop">订单号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" id="orderNumber" name="orderNumber" class="form-control mbottom" placeholder="请输入订单号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label mtop">预订人手机号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" id="phone" name="phone" class="form-control mbottom" placeholder="请输入预订人手机号">

                                    </div>
                                </div>
                            </td>
                            <td class="col-sm-2">
                                <button class="btn btn-primary btn-lg" onclick="toOrderListForFastCheckIn();">查询</button>
                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-2"><h4 class="btn btn-primary btn-lg">床位查询</h4></td>
                            <td class="col-sm-8">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label mtop">选择日期：</label>
                                    <div class="col-sm-7">
                                        <div class="input-daterange input-group" id="datepicker">
                                            <input type="text" class="input-sm form-control" name="checkInTime" id="start" readOnly="true">
                                            <span class="input-group-addon">到</span>
                                            <input type="text" class="input-sm form-control" name="checkOutTime" id="end" readOnly="true">
                                        </div>
                                    </div>
                                    <%--  <label class="col-sm-4 control-label mtop">订单号：</label>
                                      <div class="col-sm-7">
                                          <input type="text" id="orderNumberForCheckOut" name="orderNumber" class="form-control mbottom" placeholder="请输入订单号">
                                      </div>--%>
                                </div>
                                <div class="form-group">
                                    <%-- <label class="col-sm-4 control-label mtop">床位号：</label>
                                     <div class="col-sm-7">
                                         <input type="text" id="bedCode" name="bedCode" class="form-control mbottom" placeholder="请输入床位号">

                                     </div>--%>
                                </div>
                            </td>
                            <td class="col-sm-2">
                                <button class="btn btn-primary btn-lg" onclick="toBedSearch();">查询</button>
                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-1"><h4 class="btn btn-primary btn-lg"> 床位信息</h4></td>
                            <td>
                                <p class=" mtop">
                                    <label class="control-label mtop">当日清洁密码：</label>
                                    <label id="cleanPwd" class="mtop green"></label>

                                    <label class="control-label mtop" style="margin-left: 50px;"> 当日清洁数量：</label>
                                    <label id="cleanCount" class="mtop green"></label>

                                </p>
                                <p class="mtop">
                                    <label class="control-label mtop">当日维修密码：</label>
                                    <label id="repairPwd" class="mtop green"></label>

                                    <label class="control-label mtop" style="margin-left: 50px;">当日维修数量：</label>
                                    <label id="repairCount" class="mtop green"></label>
                                </p>

                            </td>
                            <td>


                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-1"><h4 class="btn btn-primary btn-lg"> 库存信息</h4></td>
                            <td class="col-sm-4" id="stockCount">
                                <%--<p style="width:250px;">1号房型库存量：<label class="mtop">66</label></p>
                                <p style="width:250px;">1号房型库存量：<label class="mtop">66</label></p>--%>
                            </td>
                        </tr>
                        </tbody>
                    </table>


                </div>
            </div>

        </div>

        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <%--<div class="ibox-title">
                    <h5>公告</h5>
                </div>--%>
                <div class="ibox-content no-padding">
                    <ul class="list-group">
                        <%--  <li class="list-group-item">
                              <p><a class="text-info" href="javascript:;">【公告1】xxxxxxxxxxxxxxxxxxxxxxxxxx</a> 这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告……</p>
                              <small class="block text-muted"><i class="fa fa-clock-o"></i> 1分钟前</small>
                          </li>
                           <li class="list-group-item">
                              <p><a class="text-info" href="javascript:;">【公告1】xxxxxxxxxxxxxxxxxxxxxxxxxx</a> 这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告这是一个公告……</p>
                              <small class="block text-muted"><i class="fa fa-clock-o"></i> 1分钟前</small>
                          </li>--%>
                    </ul>


                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script type="text/javascript">
    function toOrderListForFastCheckIn() {
        var orderNumber = $("#orderNumber")[0].value;
        var phone = $("#phone")[0].value;
        var dataUrl = "${path}/order/toListOrder.action?orderNumber=" + orderNumber + "&phone=" + phone;
        parent.addMenuItem(dataUrl, "1", "订单列表");
    }

    //跳转到床位查询
    function toBedSearch() {
        /*var orderNumberForCheckOut = $("#orderNumberForCheckOut")[0].value;
         var bedCode = $("#bedCode")[0].value;*/
        var dataUrl = "${path}/stock/toListStock.action?checkInTime=" + $("#start").val() + "&checkOutTime=" + $("#end").val();
        parent.addMenuItem(dataUrl, "1", "床位查询");
    }

    //获取房型下的可用库存
    function getHouseTypeStock() {
        $.ajax({
            url: "${path}/project/getProjectStock.action",
            dataType: "json",
            data: {},
            type: "get",
            success: function (result) {
                var html = new Array();
                for (var i in result) {
                    var stock = result[i];
                    html.push("<p style='width:500px;'><label class='control-label mtop'>当日库存量：【房型：" + stock.houseShowName + "】：</label> &nbsp; &nbsp;<label class='mtop green'>&nbsp;" + stock.availableCount + "</label></p>");
                }
                if (result.length==0){
                    html.push("<p style='width:500px;'><label class='control-label mtop'>当日库存量：</label><label class='mtop green' >暂无库存</label></p>");
                }
                $("#stockCount").html(html.join("\r\n"));
            }
        });
    }
    function getPassword() {
//获取项目下维修和保洁密码
        $.ajax({
            url: "${path}/project/getPassword.action",
            dataType: "json",
            data: {},
            type: "get",
            success: function (result) {
                for (var i in result) {
                    var password = result[i];
                    if (password.pswType == 1) {
                        $("#cleanPwd").html(password.password);
                    } else if (password.pswType == 2) {
                        $("#repairPwd").html(password.password);
                    }
                }
                if (result.length==0){
                    $("#cleanPwd").html("暂未生成");
                    $("#repairPwd").html("暂未生成");
                }

            },
            error: function () {
                $("#cleanPwd").html("生成密码失败!");
                $("#repairPwd").html("生成密码失败!");
            }
        });
    }

    function loadDateComponent() {
        //时间插件
        var start = {
            elem: '#start',
            format: 'YYYY-MM-DD',
            min: laydate.now(-1), //设定最小日期为当前日期
            max: laydate.now(+90), //最大日期
            istime: false,
            istoday: true,
            choose: function (datas) {
                var dms = 24 * 60 * 60 * 1000; //一天时间的毫秒数
                var nextDatas = laydate.now(new Date(datas).getTime() + dms); //当前选中时间的下一天
                end.min = nextDatas; //开始日选好后，重置结束日的最小日期 为开始日期的下一天
                end.start = nextDatas; //将结束日的初始值设定为开始日
                var startTime = new Date(datas).getTime();
                var endTime = new Date($("#end").val()).getTime();
                if (endTime - startTime < dms) {
                    $("#end").val(nextDatas);
                }
            }
        };

        var end = {
            elem: '#end',
            format: 'YYYY-MM-DD',
            min: laydate.now(),
            max: laydate.now(+90),
            istime: false,
            istoday: true,
            choose: function (datas) {
                //start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);
        $("#start").val(laydate.now());
        $("#end").val(laydate.now(+1));
    }

    window.onload = function () {
        loadDateComponent();

        //获取床位保洁和维修的数量
        $.ajax({
            url: "${path}/project/getBedCountByStatus.action",
            dataType: "json",
            data: {},
            type: "get",
            success: function (result) {
                $("#cleanCount").html(result.cleanCount==null?"暂无床位":result.cleanCount);
                $("#repairCount").html(result.repairCount==null?"暂无床位":result.repairCount);

                getHouseTypeStock();
                getPassword();
            }
        });
    }

</script>
</html>

