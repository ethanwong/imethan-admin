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
 * 初始化通用modal
 * @param title modal标题
 * @param htmlContent modal html内容
 * @param buttonClass 按钮样式
 * @param buttonName 按钮名称
 * @param formId form的ID
 * @returns modal执行按钮对象
 * 
 * @author Ethan Wong
 * @datetime 2015年9月26日下午4:15:45
 */
function setGeneralModal(title,htmlContent,buttonClass,buttonName,formId){
	console.log("buttonClass:"+buttonClass);
	console.log("buttonName:"+buttonName);
	if(buttonClass == ''){
		buttonClass =  "btn-info";
	}
	if(buttonName == ''){
		buttonName =  "确定";
	}
	var  generalModalAppendDivId = ""+
		"<div class='modal fade' id='generalModalAppendDivId' tabindex='-1' role='dialog' aria-labelledby='generalConfirmModalLabel' aria-hidden='true'>"+
		"<div class='modal-dialog'>"+
			"<div class='modal-content'>"+
				"<div class='modal-header'>"+
					"<button type='button' class='close' data-dismiss='modal' aria-label='Close' aria-hidden='true'>"+
						"<span aria-hidden='true'>&times;</span>"+
					"</button>"+
					"<h4 class='modal-title'>"+title+"</h4>"+
				"</div>"+
				"<div class='modal-body'>"+
				htmlContent +
				"</div>"+
				"<div class='modal-footer'>"+
				"	<button  type='button' class='btn btn-defaul btn-flat' data-dismiss='modal'>关闭</button>"+
				"	<button id='generalConfirmModalClick' type='button' class='btn btn-flat "+buttonClass+"' >"+buttonName+"</button>"+
				"</div>"+
			"</div>"+
		"</div>"+
	"</div>	";
	$(document.body).append(generalModalAppendDivId);
	
	
	//弹出modal框
	$('#generalModalAppendDivId').modal();
	
	//在modal隐藏之后触发该方法
	$('#generalModalAppendDivId').on('hidden.bs.modal', function (e) {
		$(".modal-backdrop").remove();
		$("#generalModalAppendDivId").remove();
	});
	
//	$('#generalModalAppendDivId').on('hide.bs.modal', function (e) {
//		console.log();
//	});
	
	//绑定点击按钮关闭modal事件
	$("#generalConfirmModalClick").bind('click',function(){
		if(formId != ''){
			if($("#"+formId).valid()){
				$('#generalModalAppendDivId').modal('toggle')
			}
		}else{
			$('#generalModalAppendDivId').modal('toggle')
		}
	});

	return $("#generalConfirmModalClick");
};



/**
 * 展示消息
 * @param jsonresult controll返回的json结果
 */
function showMessage(jsonresult,place){
	var result = eval("(" + jsonresult + ")");
	console.log("showMessage result.success:"+result.success);
	console.log("showMessage result.message:"+result.message);
	if(result.success){
		showSuccess(result.message,place);
	}else{
		showError(result.message,place);
	}
}

/**
 * 展现一般提示消息
 * @param message 提示信息
 */
function showWarn(message,place){
	var showmessageId = "showmessage";
	if(place =='left'){
		showmessageId = place+showmessageId;
	}
	if(place =='right'){
		showmessageId = place+showmessageId;
	}
	$("#"+showmessageId).attr("class","message warn");
	$('#'+showmessageId).html("<i class='icon fa fa-warning'></i> "+message);
	$("#"+showmessageId).css("display","inline");
	
	setTimeout(function(){
		$("#"+showmessageId).css("display","none");
	}, 3000);
};

/**
 * 展现错误提示消息
 * @param message 错误提示信息
 */
function showError(message,place){
	var showmessageId = "showmessage";
	if(place =='left'){
		showmessageId = place+showmessageId;
	}
	if(place =='right'){
		showmessageId = place+showmessageId;
	}
	
	$("#"+showmessageId).attr("class","message error");
	$('#'+showmessageId).html("<i class='icon fa fa-ban'></i> "+message);
	$("#"+showmessageId).css("display","inline");
	
	
	setTimeout(function(){
		$("#"+showmessageId).css("display","none");
	}, 3000);
	
};

/**
 * 展现成功提示消息
 * @param message 错误提示信息
 */
function showSuccess(message,place){
	
	var showmessageId = "showmessage";
	if(place =='left'){
		showmessageId = place+showmessageId;
	}
	if(place =='right'){
		showmessageId = place+showmessageId;
	}
	
	$("#"+showmessageId).attr("class","message success");
	$('#'+showmessageId).html("<i class='icon fa fa-check'></i> "+message);
	$("#"+showmessageId).css("display","inline");
	
	setTimeout(function(){
		$("#"+showmessageId).css("display","none");
	}, 3000);
	
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
	}, 2000);
	
};