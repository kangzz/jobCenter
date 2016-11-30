/* 此处存放公共的js方法，例如：登录注册等 */

// 项目根目录
if(typeof(rootPath) == "undefined" || rootPath == null || rootPath == "" ){
	rootPath = getRootPath();
}
function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'+ webName;
}

// 获取请求参数
function getParam(key,url){
	var param = key;
	var allParam  = url;
	if(url == null || typeof(url) == "undefined"){
		allParam = location.search;
	}
	var idx1 = allParam.indexOf("?"+param+"=");
	if(idx1==-1){
		idx1 = allParam.indexOf("&"+param+"=");
	}
	if(idx1!=-1){
		idx1 = idx1+1;
	}
	var idx2 = allParam.indexOf("&",idx1);
	var val = "";
	if(idx1!=-1) {
		if(idx2!=-1) {
			val = allParam.substring(idx1+param.length+1,idx2);
		} else {
			val = allParam.substring(idx1+param.length+1);
		}
	}
	return val;
}

// 判断字符串是否为null
function isnull(str){
	return str == null || str == "null" || str === "" || str == "undefined" || typeof(str) == "undefined";
}

// 返回指定路径
function backUrl(url){
	if(isnull(url)){
		window.history.back();
	} else {
		if(url.indexOf("http://") != -1){
			window.location.href = url;
		} else {
			window.location.href = rootPath + "/" + url;
		}
	}
}

// 跳转到指定的路径
function turnUrl(url) {
	window.location.href = url;
}

/**
 * 日期和时间格式化
 * 使用方法：
 * new Date().format("yyyy-MM-dd hh:mm:ss");
 * new Date().format("YYYY年MM月dd日hh小时mm分ss秒");
 * @author Jonk
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}