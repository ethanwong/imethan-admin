(function ($) {
	
	
	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		$("#jqGrid").jqGrid( 'setGridWidth', $(".content").width()-20 );
    })
	
    //trigger window resize to make the grid get the correct size
	$(window).triggerHandler('resize.jqGrid');
	
//	$("#jqGrid")
	
	$("#showmessage").css("display","none");
	
}(jQuery));

//展现一般提示消息
function showWarn(message){
	$("#showmessage").attr("class","callout callout-info warnmessage");
	$('#showmessage').html(message);
	$("#showmessage").css("display","block");
	
	setTimeout(function(){
		$("#showmessage").css("display","none");
	}, 2000);
};
//展现错误提示消息
function showError(message){
	$("#showmessage").attr("class","callout callout-danger warnmessage");
	$('#showmessage').html(message);
	$("#showmessage").css("display","block");
	
	
	setTimeout(function(){
		$("#showmessage").css("display","none");
	}, 2000);
	
};