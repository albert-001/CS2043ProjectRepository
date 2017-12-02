function display_confirmation(city){
	$("#confirm_info").text("Add "+city+" successful");
}

function display_failed(city){
	$("#confirm_info").text("Add "+city+" failed");
}

function add_city(city){
    $.ajax({
		url : 'AddCityServlet',
		data : {
			name : city
		},
		success : function(responseText) {
			if(responseText=="y"){
				display_confirmation(city);
			} else {
				display_failed(city);
			}
		},
		error : function(){
			display_failed(city);
		}
	});
}

$(document).ready(function() {
		$( ".city_i" ).on("click", function(e) {
			e.preventDefault();
			add_city(e.target.text);
		});
});