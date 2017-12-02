public class ChattingRoomMain {

	public static void main(String[] args) {

		DataManager dm = new DataManager();
		UserObject user = null;
		
		LoginControl lcontrol = new LoginControl(dm);
		LoginUI lui = new LoginUI(lcontrol);
		
		lui.displayLoginForm();
		lui.enterInfo();
		
		user = lcontrol.getUserObject();
		if(user != null) {
			ChattingRoomControl control = new ChattingRoomControl(dm);
			ChattingRoomUI ui = new ChattingRoomUI(control, user);
			control.getPreviousMessage();
			ui.displayMessageForm("2017-11-10 16:44:00");
		}
	}

}
