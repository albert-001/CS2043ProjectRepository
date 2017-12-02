

public class LoginControl {

	private DataManager dm;
	private UserObject user = null;
	
	public LoginControl(DataManager dm) {
		this.dm = dm;
	}
	
	public boolean processLogin(String name, String password) {
		user = dm.getUserObject(name, password);
		if(user != null) return true;
		else return false;
	}
	
	public UserObject getUserObject() {
		return user;
	}
}
