/*!
Copyright 2015 eLearning Solutions S.L.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.
You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

//Wait jQuery to be loaded
function defer(method) {
	if (window.jQuery)
		method();
	else
		setTimeout(function() { defer(method) }, 50);
}

defer(function () {
	$.noConflict();
	(function( $ ) {
		$( document ).ready(function() {
			//Apply DataTables plugin to our table
			var table = $("#courses-report").DataTable( {
				//Order by time in milliseconds from epoch
		        "order": [[ 1, "asc" ]],
		        //Hide milliseconds ordering column
		        "columnDefs": [
		                       {
		                           "targets": [ 1 ],
		                           "visible": false,
		                           "searchable": false
		                       }
		                   ]
		    } );
		});
	})(jQuery);
});