
function checkMobile(val){
    if(!/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/.test(val) || val.length!=11){
        return false;
    }else{
    	return true;
    }
}
function checkIntegerGT(val){
    if(val != null && $.trim(val)!='' && /^\+?[1-9][0-9]*$/.test(val)){
        return true;
    }else{
    	return false;
    }
}
function isNullOrBlank(obj){
	if(obj == null || $.trim(obj) == "" || obj == undefined){
		return true;
	}else{
		return false;
	}
}
function checkEmpty(val){
	if($.trim(val)==""|| $.trim(val)==null || val.length==0){
		return false;
	}else{
		return true;
	}
}

function inpKeyPress(obj,id){
	
	if($(obj).val()!=''){
		$('#'+id).show();
	}
	
	if($(obj).val()==''){
		
		$('#'+id).hide();
	}
}

function closeOtherClear(obj){
	
	$('.ui-icon-close').hide();
	if($(obj).val()!=''){	
		$(obj).next('.ui-icon-close').show();
	}
}

function clearInp(obj,id){
	$(obj).hide();
	
	$('#'+id).val('').focus();
	
}



//隐藏tips
function hideTips(){
	
	var oTxt=$('#bodyTipsTxt');
	oTxt.text(" ");
	
}




var picktime_yy_val = false;
$(function(){
	
	var text=$("#tab .ui-tab .ui-tab-nav li.ui-tab-nav-li.current").text();
	/*if(text=="申请入住"){
		$(".sqxz").dialog("show");
	}*/
	
	$("#tab .ui-tab .ui-tab-nav li.ui-tab-nav-li").click(function(){
		/*if($(this).text()=="申请入住"){
			$(".sqxz").dialog("show");
		}*/
		$(this).addClass("current").siblings("li").removeClass("current");
		var n=$(this).index();
		$("#tab .ui-tab-content li.ui-tab-content-li").eq(n).addClass("current").siblings("li.ui-tab-content-li").removeClass("current");
	})
	
	
	//个人信息设置
	$('#user_info_set').find("input").on('keyup',function(){			
		user_info_set_Fn();			
	})
		
	$('#user_info_set').find("input").on('blur',function(){			
		user_info_set_Fn();			
	})
	
	$('#user_info_set').find(".ui-icon-close").on('click',function(){			
		user_info_set_Fn();			
	})
	
	yuyue_kanfang_Fn();
	//预约看房
	$('#yuyue_kanfang').find("input").on('keyup',function(){			
		yuyue_kanfang_Fn();			
	});	
	
	$('#yuyue_kanfang').find("input").on('blur',function(){			
		yuyue_kanfang_Fn();			
	});	
	
	$('#yuyue_kanfang').find(".ui-icon-close").on('click',function(){			
		yuyue_kanfang_Fn();			
	});

	$('#picktime_yy').on('blur',function(){
		picktime_yy_val = true;
		//alert(111);
		yuyue_kanfang_Fn();	
	});
	
	$('.mt_ok').on('click',function(){
		picktime_yy_val = true;
		yuyue_kanfang_Fn();	
	});
	
	//申请入住
	$('#shenqing_ruzhu').find("input").on('keyup',function(){			
		shenqing_ruzhu_Fn();			
	});	
	
	$('#shenqing_ruzhu').find("input").on('blur',function(){			
		shenqing_ruzhu_Fn();			
	});
	$('#shenqing_ruzhu').find("textarea").on('keyup',function(){			
		shenqing_ruzhu_Fn();			
	});
	$('#shenqing_ruzhu').find("textarea").on('blur',function(){			
		shenqing_ruzhu_Fn();			
	});
	
	$('#shenqing_ruzhu').find(".ui-icon-close").on('click',function(){			
		shenqing_ruzhu_Fn();			
	});
	
	
	//申请入住 第二步
	$('#shenqing_ruzhu2').find("input").on('keyup',function(){			
		shenqing_ruzhu_Fn2();			
	});
	
	$('#shenqing_ruzhu2').find("input").on('blur',function(){	
		shenqing_ruzhu_Fn2();			
	});	
	
	$('#shenqing_ruzhu2').find(".ui-icon-close").on('click',function(){			
		shenqing_ruzhu_Fn2();			
	});
	
	
	//登录
	$('#logBox').find("input").on('keyup',function(){			
		logFn();			
	})
		
	$('#logBox').find("input").on('blur',function(){			
		logFn();			
	})
	
	$('#logBox').find(".ui-icon-close").on('click',function(){			
		logFn();			
	})
	
	
	//注册
	$('#register').find("input").on('keyup',function(){			
		registerFn();			
	})
		
	$('#register').find("input").on('blur',function(){			
		registerFn();			
	})
	
	$('#register').find(".ui-icon-close").on('click',function(){			
		registerFn();			
	})
	
	
	//忘记密码
	$('#forgetPw').find("input").on('keyup',function(){			
		forgetPwFn();			
	})
		
	$('#forgetPw').find("input").on('blur',function(){			
		forgetPwFn();			
	})
	
	$('#forgetPw').find(".ui-icon-close").on('click',function(){			
		forgetPwFn();			
	})
	
	
	//完善信息
	$('#user_info_ws').find("input").on('keyup',function(){			
		user_info_ws_Fn();			
	})
		
	$('#user_info_ws').find("input").on('blur',function(){			
		user_info_ws_Fn();			
	})
	
	$('#user_info_ws').find(".ui-icon-close").on('click',function(){			
		user_info_ws_Fn();			
	})
	
	//签约入住 1
	$('#qianyue1').find("input").on('keyup',function(){			
		qianyue1_Fn();			
	})
		
	$('#qianyue1').find("input").on('blur',function(){			
		qianyue1_Fn();			
	})
	
	$('#qianyue1').find(".ui-icon-close").on('click',function(){			
		qianyue1_Fn();			
	})
	
	//添加合住人
	$('#hzr').find("input").on('keyup',function(){			
		hzrFn();			
	})
		
	$('#hzr').find("input").on('blur',function(){			
		hzrFn();			
	})
	
	$('#hzr').find(".ui-icon-close").on('click',function(){			
		hzrFn();			
	})
	
	
	$(".user_info .top_logo .logo .img").height(function(n,c){
		var h=$(".user_info .top_logo .logo .img").width();
		return h;
	});
	
	//取消订单
	$("#qxdd_btn").click(function(){
		$(".qxdd_box").dialog("show");
	});
	
	//签约须知
	$("#qxxz_btn").click(function(){
		$(".qyxz_box").dialog("show");
	});
	
	//自如寓首页
	var w_h=$(window).height();
	var w_w=$(window).width();
	$("#zry .zry_part01").height(w_h);
	$(".ui-slider").width(w_w);
	
	var i_h=$(".ui-slider li img").height();
	$(".zry_sq").height(i_h);
	$(".ui-slider-content").height(i_h);
	$(".ui-slider-content li").height(i_h);
	
	//活动报名
	$('#signUpActiveForm').find("input").on('keyup',function(){			
		signUpActiveForm_Fn();			
	})
		
	$('#signUpActiveForm').find("input").on('blur',function(){			
		signUpActiveForm_Fn();			
	})
	
	$('#signUpActiveForm').find(".ui-icon-close").on('click',function(){			
		signUpActiveForm_Fn();			
	})
	
})


//侧边栏
function home(){
	$(".fixed_menu").show();
	$(".fixed_menu .menu").addClass("f_menu animated");
	$(".wraper").css("position","fixed");
	$(".fixed_menu").click(function(){
		$(".fixed_menu").hide();
		$(".wraper").css("position","relative");
	})
	$(".fixed_menu .menu li").click(function(event){
		event.stopPropagation();
	})
}




$(window).resize(function(){
	//自如寓首页
	var w_h=$(window).height();
	var w_w=$(window).width();
	$("#zry .zry_part01").height(w_h);
	$(".ui-slider").width(w_w);
})



//个人信息设置 相关
function user_info_set_Fn(){
	var oName = $('#user_info_set #userName');
	var oTel = $('#user_info_set #userTel');
	var oAddress = $('#user_info_set #workAddress');
	var oBtn = $('#user_info_set .submitBtn');

	if( jQuery.trim(oName.val())!='' && checkMobile(jQuery.trim(oTel.val())) &&jQuery.trim(oAddress.val())!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//预约看房 相关
function yuyue_kanfang_Fn(){
	var oName = $('#yuyue_kanfang #userName_yy');
	var oTel = $('#yuyue_kanfang #userTel_yy');
	var oAddress = $('#yuyue_kanfang #workAddress_yy');
	var picktime = $('#yuyue_kanfang #picktime_yy');
	var oBtn = $('#yuyue_kanfang .submitBtn');
	
	
	
	if(jQuery.trim(oName.val())!='' && checkMobile(jQuery.trim(oTel.val())) 
			&& jQuery.trim(oAddress.val())!='' && (jQuery.trim(picktime.val())!='' || picktime_yy_val)){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		//oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//申请入住 相关
function shenqing_ruzhu_Fn(){
	var oName = $('#shenqing_ruzhu #userName_rz');
	var oAge = $('#shenqing_ruzhu #age_rz');
	var oSchool = $('#shenqing_ruzhu #school_rz');
	var oTel = $('#shenqing_ruzhu #userTel_rz');
	var selfInfo = $('#shenqing_ruzhu #selfInfo_rz');
	var oBtn = $('#shenqing_ruzhu .submitBtn');
	if($.trim(oName.val())!='' && $.trim(oAge.val())!='' && $.trim(oSchool.val())!='' 
		&& checkMobile(oTel.val()) 
		&& $.trim(selfInfo.val())!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//申请入住 第二步 相关
function shenqing_ruzhu_Fn2(){
	var oJob = $('#shenqing_ruzhu2 #userJob_rz');
	var oWorkName = $('#shenqing_ruzhu2 #workName_rz');
	var oWorkAddress = $('#shenqing_ruzhu2 #workAddress_rz');
	var oIDnumber = $('#shenqing_ruzhu2 #idNumber_rz');
	var oBtn = $('#shenqing_ruzhu2 .submitBtn');
	
	if($.trim(oJob.val())!='' && $.trim(oWorkName.val())!='' && $.trim(oWorkAddress.val())!='' && $.trim(oIDnumber.val())!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//登录 相关
function logFn(){
	var oTel = $('#logBox #ziroomName');
	var oPas = $('#logBox #ziroomPsw');
	var oBtn = $('#logBox .submitBtn');

	if( checkMobile(oTel.val()) &&oPas.val()!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
	if(oTel.val().trim()==''){
    	$('#logBox #ziroomNameErrMsg').hide();
    }
}


//注册 相关
function registerFn(){
	var oTel = $('#register #phoneNo');
	var oCode = $('#register #verifyCode');
	var oCodeBtn = $('#register .getCodeBtn');
	//var oPas = $('#register #ziroomPsw');
	var oBtn = $('#register .submitBtn');
	var isReg = $('#register #isreg');
	var codeflag=$('#register #authCodeflag');
	
    if(oTel.val().trim()==''){
    	$('#register #phoneNoErrMsg2').hide();
    	$('#register #phoneNoErrMsg').hide();
    }
	if( checkMobile(oTel.val()) && isReg.val()=='0'&& codeflag.val()!='1'){
		oCodeBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oCodeBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
	
	if( checkMobile(oTel.val()) &&oCode.val()!='' ){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//忘记密码 相关
function forgetPwFn(){
	var oTel = $('#forgetPw #phoneNo');
	var oCode = $('#forgetPw #verifyCode');
	var oCodeBtn = $('#forgetPw .getCodeBtn');
	var oPas = $('#forgetPw #ziroomPsw');
	var oBtn = $('#forgetPw .submitBtn');
	var isReg = $('#forgetPw #isreg');
	var codeflag=$('#forgetPw #authCodeflag');

	if(oTel.val().trim()==''){
    	$('#forgetPw #phoneNoErrMsg').hide();
    }
	if( checkMobile(oTel.val()) && isReg.val()=='1'&& codeflag.val()!='1'){
		oCodeBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oCodeBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
	
	if( checkMobile(oTel.val()) &&oCode.val()!='' && oPas.val()!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//完善信息相关 相关
function user_info_ws_Fn(){
	var oName = $('#user_info_ws #userName');
	var oBtn = $('#user_info_ws .submitBtn');
	var oAge = $("#user_info_ws #age");
	if( oName.val()!=null && $.trim(oName.val())!='' && oAge.val()!=null && checkIntegerGT($.trim(oAge.val()))){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}


//签约入住 1 相关
function qianyue1_Fn(){
	var oID = $('#qianyue1 #IDnumber');
	var oBtn = $('#qianyue1 .submitBtn');
	
	if( oID.val()!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}

//添加合住人 相关
function hzrFn(){
	var oName = $('#hzr #userName');
	var oTel = $('#hzr #userTel');
	var oID = $('#hzr #IDnumber');
	var oBtn = $('#hzr .submitBtn');
	
	
	if( checkMobile(oTel.val()) &&oName.val()!='' && oID.val()!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}

//ajax
function ajaxCommonRequest(url, paramData, fnSuccBack){
	jQuery.ajax({
		type : 'POST',
		url: url ,	
		cache:false,
		dataType :'json',
		data : paramData,					
		success : function(data){
			fnSuccBack( data );
		}
		
	});
}

//ajax
function ajaxCommonRequestget(url, paramData, fnSuccBack){
	jQuery.ajax({
		type : 'GET',
		url: url ,	
		cache:false,
		dataType :'json',
		data : paramData,					
		success : function(data){
			fnSuccBack( data );
		}
		
	});
}

//活动报名  相关
function signUpActiveForm_Fn(){
	var oName = $('#signUpActiveForm #name');
	var oage = $('#signUpActiveForm #age');
	var oTel = $('#signUpActiveForm #tel');
	var onum = $('#signUpActiveForm #num');
	var oBtn = $('#signUpActiveForm .submitBtn');
	
	
	if( checkMobile(oTel.val()) &&oName.val()!='' && oage.val()!='' && onum.val()!=''){
		oBtn.removeClass('btn_disabled').removeAttr('disabled');
	}else{
		oBtn.addClass('btn_disabled').attr('disabled','disabled');
	}
}

//校验身份证号
function isCardNo(card) {  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
    return  reg.test(card);  
}  

//判断字符的长度  单字节加1，汉字占2个字节，加2
function getStrlen(str) {
	var len = 0;
	for ( var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		//单字节加1 
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			len++;
		} else {
			len += 2;
		}
	}
	return len;
}
// horizontal display

		function hengshuping(){ 
  if(window.orientation==180||window.orientation==0){  
		$('#tips').hide();
       /* $('#sidebar').show();*/
        $('header').show();
        $('#mainContent').show();
//        $('#bookingTip').show();
//        $('#qianyueTip').show();
        
   } 
if(window.orientation==90||window.orientation==-90){ 
        //alert("ºáÆÁ×´Ì¬£¡")     
		
        $('#tips').show();
       /* $('#sidebar').hide();*/
        $('header').hide();
        $('#mainContent').hide();
//        $('#bookingTip').hide();
//        $('#qianyueTip').hide();
    } 
 } 
window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", hengshuping, false);  