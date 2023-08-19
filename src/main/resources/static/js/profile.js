$(document).ready(function() {

	// EDIT MODAL
	var curBodyWidth = $('body').width();
	var bodyWithScroll;

	$("#edit-modal").hide();
	$('#edit-mdl-btn').click(function() {
		$('#edit-modal').show();
		$('body').css('overflow', 'hidden');
		// bodyWithScroll = $('body').width() - curBodyWidth;
		$('body').css('margin-right', 1.3 + "vw");

	});
	$('#close-edit').click(function() {
		$('#edit-modal').hide();
		$('body').css('overflow', 'auto');
		$('body').css('margin-right', 0)
	});

	// ADD MODAL
	$("#add-modal").hide();
	$('#add-mdl-btn').click(function() {
		$('#add-modal').show();
		$('body').css('overflow', 'hidden');
		// bodyWithScroll = $('body').width() - curBodyWidth;
		$('body').css('margin-right', 1.3 + "vw");

	});

	$('#close-add').click(function() {
		$('#add-modal').hide();
		$('body').css('overflow', 'auto');
		$('body').css('margin-right', 0)
	});

	// PROFILE CONFIG
	var showConfig = document.querySelector("#config-pf");

	$("#config-pf").hide();
	$('#show-pf').click(function() {
		$('#config-pf').toggle();

	});

});