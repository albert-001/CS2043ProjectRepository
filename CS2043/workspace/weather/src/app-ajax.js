function get_icon_by_weather(w, t) {
	if(w.search(/sun/i)!=-1 || w.search(/clear/i)!=-1){
		return("./images/sunny_"+ t +".png");
	}else if(w.search(/cloud/i)!=-1){
		return("./images/cloudy_"+ t +".png");
	}else if(w.search(/overcast/i)!=-1 || w.search(/dreary/i)!=-1){
		return("./images/overcast.png");
	}else if(w.search(/rain/i)!=-1 || w.search(/shower/i)!=-1){
		return("./images/rainy.png");
	}else if(w.search(/snow/i)!=-1 || w.search(/sleet/i)!=-1){
		return("./images/snow.png");
	}else{
		return("");
	}
}

function get_bg_by_weather(w){
	return ("bg_"+get_weather_type(w));
}

function get_weather_type(w) {
	var weather = "";
	if(w.search(/sun/i)!=-1 || w.search(/clear/i)!=-1){
		weather = "sunny";
	}else if(w.search(/cloud/i)!=-1){
		weather = "cloudy";
	}else if(w.search(/overcast/i)!=-1 || w.search(/dreary/i)!=-1){
		weather = "overcast";
	}else if(w.search(/rain/i)!=-1 || w.search(/shower/i)!=-1){
		weather = "rainy";
	}else if(w.search(/snow/i)!=-1){
		weather = "snow";
	}else{
		weather = "sunny";
	}
	return weather;
}

function show_current_clothing() {
	var w = $('#forecast').text();
	var weather = get_weather_type(w);
	$.ajax({
		url : 'ClothingServlet',
		data : {
			we : weather,
		},
		success : function(responseText) {
			console.log(responseText);
			$("#current_clothing").attr("src", responseText);
		}
	});
}

function get_current_weather() {
	$.ajax({
		url : 'WeatherServlet',
		data : {
			type : "c",
			city : $('#city').val()
		},
		success : function(responseText) {
			var o = JSON.parse(responseText);
			var w = o.weather[0].main;
			$('#city_name').text(o.name);
			$('#curr_temp').text(o.main.temp);
			$('#forecast').text(w);
			$('#windspeed').text(o.wind.speed);
			$('#humidity').text(o.main.humidity);
			$('body').attr("class", get_bg_by_weather(w));
			$('.lw_icon').attr("src", "./images/w_"+get_weather_type(w)+".png");
		}
	});
}

function get_future_weather() {
	$.ajax({
		url : 'WeatherServlet',
		data : {
			type : "f",
			city : $('#city').val()
		},
		success : function(responseText) {
			var o = JSON.parse(responseText);
			console.log(o);
			for(i=0; i<5; i++){
				var temperature_F_Max = o.DailyForecasts[i].Temperature.Maximum.Value;
				var temperature_C_Max = (parseFloat(temperature_F_Max)-32)/1.8;
				var temperature_F_Min = o.DailyForecasts[i].Temperature.Minimum.Value;
				var temperature_C_Min = (parseFloat(temperature_F_Min)-32)/1.8;
				console.log(temperature_F_Max);
				console.log(temperature_F_Min);
				console.log(temperature_C_Max);
				console.log(temperature_C_Min);
				$("#forecast_"+i.toString()+"_date").text(o.DailyForecasts[i].Date.slice(0,10));
				$("#forecast_"+i.toString()+"_temp").text(temperature_C_Max.toString().slice(0,3) + "°C / " + temperature_C_Min.toString().slice(0,3) + "°C");
				$("#icon_"+i.toString()+"_day").attr("src", get_icon_by_weather(o.DailyForecasts[i].Day.IconPhrase, "day"));
				$("#icon_"+i.toString()+"_night").attr("src", get_icon_by_weather(o.DailyForecasts[i].Night.IconPhrase, "night"));
				$("#icon_"+i.toString()+"_day").attr("alt", o.DailyForecasts[i].Day.IconPhrase);
				$("#icon_"+i.toString()+"_night").attr("alt", o.DailyForecasts[i].Night.IconPhrase);
			}
		}
	});
}

function update_city_list(location){
	$("#city").empty();
	$("#city").append("<option value='"+location[0]+"' selected>"+location[0]+"</option>")
	for(i=1;i<location.length;i++){
		$("#city").append("<option value='"+location[i]+"'>"+location[i]+"</option>")
	}
}

function show_user_name(user){
	$("#logged_in").html("<span>"+user+"</span>");
}

function user_logged_in(user, location){
	show_user_name(user);
	$("#wrap_w0").show();
	$("#add_city").show();
	$("#chatting").show();
	update_city_list(location);
	get_future_weather();
	show_current_clothing();
}

function check_user_login() {
	$.ajax({
		url : 'LoginServlet',
		data : {
			task : "check_user_login",
		},
		success : function(responseText) {
			console.log(responseText);
			if(responseText==""){
				//hide advanced functions
				$("#wrap_w0").hide();
				$("#add_city").hide();
				$("#chatting").hide();
			} else {
				var user = responseText.split(";")[0];
				var location = responseText.split(";")[1].split(",");
				user_logged_in(user, location);
			}
		}
	});
}

$(document).ready(function() {
	    $('#today').text(Date().slice(0,16));
	    get_current_weather();
	    check_user_login();
		$( "#city" ).change(function() {
			get_current_weather();
			check_user_login();
		});
});