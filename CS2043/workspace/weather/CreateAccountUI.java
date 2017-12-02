

import java.util.Scanner;
import java.util.ArrayList;

public class CreateAccountUI {

	private CreateAccountControl control;
	
	public CreateAccountUI(CreateAccountControl control) {
		this.control = control;
	}
	
	public void displayCreateAccountForm() {
		System.out.println("Enter user info below: ");
	}
	
	public void enterInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter user name(need to be special): ");
		String name = scanner.nextLine();
		System.out.println("Enter your password: ");
		String password = scanner.nextLine();
		System.out.println("Enter your location: ");
		String location = scanner.nextLine();
		System.out.println("Enter your gender(male or female only): ");
		String gender = scanner.nextLine();
		System.out.println("Enter your age: ");
		String age = scanner.nextLine();
		scanner.close();
		boolean yes = control.processCreateAccount(name);
		if(yes) {
			UserObject user = new UserObject(name, password, location, gender, age);
			if(control.addUserObject(user)) {
				displayConfirmation();
			} else {
				displayCreateFailedMessage();
			}
		}
		else {
			displayUserExistMessage();
		}
	}
	
	public void displayConfirmation() {
		System.out.println("Create account successful!");
	}
	
	public void displayUserExistMessage() {
		System.out.println("User name already exists, please try another one.");
	}
	
	public void displayCreateFailedMessage() {
		System.out.println("Failed to create user account.");
	}
}
