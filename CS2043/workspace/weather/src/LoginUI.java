

import java.util.*;

public class LoginUI {

	private LoginControl control;
	
	public LoginUI(LoginControl control) {
		this.control = control;
	}
	
	public void displayLoginForm() {
		System.out.println("Enter user name and password separated by space ' '");
	}
	
	public void enterInfo() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		String password = scanner.next();		
		if(control.processLogin(name, password))
			displayConfirmation();
		else
			displayLoginFailedMessage();
	}
	
	public void displayConfirmation() {
		System.out.println("Login successful!");
	}
	
	public void displayLoginFailedMessage() {
		System.out.println("No user record is found. Please check your name and password then try again.");
	}
}
