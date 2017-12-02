
public class AddCityMain {

	public static void main(String[] args) {
		
		DataManager dm = new DataManager();
		
		UserObject user = null;
		
		LoginControl lcontrol = new LoginControl(dm);
		LoginUI lui = new LoginUI(lcontrol);
		
		lui.displayLoginForm();
		lui.enterInfo();
		
		user = lcontrol.getUserObject();
		if(user != null) {
			AddCityControl control = new AddCityControl(dm);
			AddCityUI ui = new AddCityUI(control, user);
			ui.displayAddCityForm();
			ui.enterCityName();
			dm.updateCity(user);
		}
	}

}
