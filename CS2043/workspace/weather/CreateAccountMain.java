

public class CreateAccountMain {

	public static void main(String[] args) {
		DataManager dm = new DataManager();
		CreateAccountControl control = new CreateAccountControl(dm);
		CreateAccountUI ui = new CreateAccountUI(control);
		
		ui.displayCreateAccountForm();
		ui.enterInfo();
	}
}