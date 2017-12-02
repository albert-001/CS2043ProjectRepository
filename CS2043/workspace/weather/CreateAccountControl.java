
public class CreateAccountControl {

	private DataManager dm;
	
	public CreateAccountControl(DataManager dm) {
		this.dm = dm;
	}
	
	public boolean processCreateAccount(String name) {
		boolean found = dm.getUserAccountByUserName(name);
		return !found;
	}
	
	public boolean addUserObject(UserObject user) {
		if(this.processCreateAccount(user.getName())){
			return dm.addUser(user);
		}
		return false;
	}
}
