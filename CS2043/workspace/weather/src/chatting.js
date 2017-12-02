function display_own_msg(msg, name, time){
	var own_msg = "<input type='hidden' value='"+ time +"'><div class='msg_right'><span class='msg_content_right'>"+ msg +"</span><span class='caret_right'></span><span class='chat_name'>"+ name +"</span></div>";
	$("#chat_container").append(own_msg);
}

function display_others_msg(msg, name, time){
	var others_msg = "<input type='hidden' value='"+ time +"'><div class='msg_left'><span class='chat_name'>"+ name +"</span><span class='caret_left'></span><span class='msg_content_left'>"+ msg +"</span></div>";
	$("#chat_container").append(others_msg);
}

function display_failed(){
	$("#error_info").text("failed to send message...");
}

function clear_error(){
	$("#error_info").text("");
}

function clear_msg(){
	$("#message").val("");
}

function send_msg(message){
    $.ajax({
		url : 'ChatServlet',
		data : {
			msg : message,
		},
		method: "post",
		success : function(responseText) {
			if(responseText==""){
				display_failed();
				clear_msg();
			} else {
				clear_error();
				clear_msg();
				get_new_msg();
			}
		},
		error: function(){
			display_failed();
			clear_msg();
		}
	});
}

function parse_msgs(messages){
	var msg_objs = []
	if(messages!=""){
		messages = messages.slice(0, -1);
		var msgs = messages.split(";").reverse();
		msgs.forEach(function(e){
			var fields = e.split(",");
			var obj = {"time":fields[0], "user":fields[1], "msg":fields[2]};
			msg_objs.push(obj);
		});
	}
	return msg_objs
}

function display_msgs(msg_objs, type){
	msg_objs.forEach(function(e){
		if(e.user=="me"){
			display_own_msg(e.msg, e.user, e.time);
		}else{
			display_others_msg(e.msg, e.user, e.time);
		}
	});
	$('#chat_container').scrollTop($('#chat_container')[0].scrollHeight);
}

function get_new_msg(){
	var ts = $("#chat_container").find("input");
	var time = ts[ts.length-1].value;
    $.ajax({
		url : 'ChatServlet',
		data : {
			type: "new",
			ts : time,
		},
		success : function(responseText) {
			if(responseText!=""){
				clear_error();
				clear_msg();
				display_msgs(parse_msgs(responseText));
			}
		}
	});
}

function get_his_msg(){
    $.ajax({
		url : 'ChatServlet',
		data : {
			type: "his",
		},
		success : function(responseText) {
			if(responseText!=""){
				display_msgs(parse_msgs(responseText));
			}
		}
	});
}

function refresh_new_msg(){
	setInterval(get_new_msg, 2000);
}

$(document).ready(function() {
	get_his_msg();
	setTimeout(refresh_new_msg, 3000);
	$("#send").on("click", function(e){
		e.preventDefault();
		var msg = $("#message").val();
		if(msg !=""){
			send_msg(msg);
		}
	});
});