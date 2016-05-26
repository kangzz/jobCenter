//修正弹框居中位置
function adjustTanboxCenter() {
	$(".window-shadow").remove();
	var winWidth = $(window).width();
	var winHeight = $(window).height();
	$(".window").each(function(){
		var $el = $(this);
		var boxWidth = $el.width();
		var boxHeight = $el.height();
		var top = (winHeight - boxHeight) / 2;
		var left = (winWidth - boxWidth) / 2;
		if (winHeight < boxHeight) {
			top = 0;
		} else {
			top = (winHeight - boxHeight) / 2;
		}
		if (winWidth < boxWidth) {
			left = 0;
		} else {
			left = (winWidth - boxWidth) / 2;
		}

		left+=$(window).scrollLeft();
		top+=$(window).scrollTop();

		$el.css({
			"position":"absolute",
			"top" : top,
			"left" : left
		});
	});
}