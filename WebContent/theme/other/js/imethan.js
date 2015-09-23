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

/**
 * 初始化删除modal
 * @returns modal删除按钮对象
 * 
 * @author Ethan
 * @datetime 2015年9月24日上午12:47:37
 */
function setDeleteModal(){
	var  deleteModalAppendDiv = ""+
		"<div class='modal fade' id='deleteModalAppendDivId' tabindex='-1' role='dialog' aria-labelledby='deleteConfirmModalLabel' aria-hidden='true'>"+
		"<div class='modal-dialog'>"+
			"<div class='modal-content'>"+
				"<div class='modal-header'>"+
					"<button type='button' class='close' data-dismiss='modal' aria-label='Close' aria-hidden='true'>"+
						"<span aria-hidden='true'>&times;</span>"+
					"</button>"+
					"<h4 class='modal-title'>删除确认</h4>"+
				"</div>"+
				"<div class='modal-body'>"+
					"确定要删除吗？"+
				"</div>"+
				"<div class='modal-footer'>"+
				"	<button  type='button' class='btn btn-defaul' data-dismiss='modal'>关闭</button>"+
				"	<button id='deleteConfirmModalClick' type='button' class='btn btn-danger' data-dismiss='modal'>删除</button>"+
				"</div>"+
			"</div>"+
		"</div>"+
	"</div>	";
	$(document.body).append(deleteModalAppendDiv);
	
	//弹出modal框
	$('#deleteModalAppendDivId').modal();
	
	//在modal隐藏之后触发该方法
	$('#deleteModalAppendDivId').on('hidden.bs.modal', function (e) {
		$(".modal-backdrop").remove();
		$("#deleteModalAppendDivId").remove();
	});
	
	return $("#deleteConfirmModalClick");
};



/**
 * 展示消息
 * @param jsonresult controll返回的json结果
 */
function showMessage(jsonresult){
	var result = eval("(" + jsonresult + ")");
	console.log("showMessage result.success:"+result.success);
	console.log("showMessage result.message:"+result.message);
	if(result.success){
		showWarn(result.message);
	}else{
		showError(result.message);
	}
}

/**
 * 展现一般提示消息
 * @param message 提示信息
 */
function showWarn(message){
	$("#showmessage").attr("class","callout callout-info warnmessage");
	$('#showmessage').html(message);
	$("#showmessage").css("display","block");
	
	setTimeout(function(){
		$("#showmessage").css("display","none");
	}, 2000);
};

/**
 * 展现错误提示消息
 * @param message 错误提示信息
 */
function showError(message){
	$("#showmessage").attr("class","callout callout-danger warnmessage");
	$('#showmessage').html(message);
	$("#showmessage").css("display","block");
	
	
	setTimeout(function(){
		$("#showmessage").css("display","none");
	}, 2000);
	
};


/**
 * Modal提示信息
 * @param msg
 */
function showWarnModal(msg){
	$('#warnConfirmModal').modal({
	 	 keyboard: true
	});
	$("#warnConfirmModal").find('.modal-body').html(msg);
	setTimeout(function(){
		$('#warnConfirmModalClick').click();
	}, 1000);
	
};