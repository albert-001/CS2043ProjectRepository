import java.util.Scanner;

public class AddCityUI {

	private AddCityControl control;
	private UserObject user;
	
	public AddCityUI(AddCityControl control, UserObject user) {
		this.control = control;
		this.user = user;
	}
	
	public void displayAddCityForm() {
		System.out.println("Enter the name of city you want to add: ");
	}
	
	public void displayConfirmation() {
		System.out.println("Add city successful!");
	}
	
	public void displayAdditionFailedMessage() {
		System.out.println("Sorry,  that city is not provided.");
	}
	
	public void enterCityName() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		scanner.close();
		boolean yes = control.processAddCity(name);
		if(yes) {
			displayConfirmation();
			user.addCity(name);
		}
		else
			displayAdditionFailedMessage();
	}
}
