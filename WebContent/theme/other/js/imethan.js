(function ($) {
	
	
	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		$("#jqGrid").jqGrid( 'setGridWidth', $(".content").width()-20 );
    })
	
    //trigger window resize to make the grid get the correct size
	$(window).triggerHandler('resize.jqGrid');
	
//	$("#jqGrid")
	
}(jQuery));