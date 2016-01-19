function defer(method) {
	if (window.jQuery)
		method();
	else
		setTimeout(function() { defer(method) }, 50);
}

defer(function () {
	$.noConflict();
	(function( $ ) {
		$(function() {
			$('#sizes').click(function() {
				
				$.ajax({
			    	type: 'GET',
			        dataType: "json",
			    	url : '/webapps/elea-toolsManagement-BBLEARN/controller/getSizes',
		    		success : function(sizes) {
		    			
		    			$('div.container>div.row').remove();
		    			var keys = Object.keys(sizes);
		    			
		    			for(i=0; i<keys.length; i++) {
		    				$('div.container').append('<div class="row">' + keys[i] + ': ' + sizes[keys[i]] + ' Bytes</div>');
		    			}
		    			
		    		}
				});
				
			})
		});
	})(jQuery);
});