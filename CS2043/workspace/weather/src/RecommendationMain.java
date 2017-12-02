
public class RecommendationMain {

	public static void main(String[] args) {
		DataManager dm = new DataManager();
		UserObject user = null;
		
		LoginControl lcontrol = new LoginControl(dm);
		LoginUI lui = new LoginUI(lcontrol);
		
		lui.displayLoginForm();
		lui.enterInfo();
		
		user = lcontrol.getUserObject();
		if(user != null) {
			RecommendationControl control = new RecommendationControl();
			RecommendationUI ui = new RecommendationUI(control, user);
			ui.enterInfo();
		}
	}

}
