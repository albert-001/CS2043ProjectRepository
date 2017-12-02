import java.util.Scanner;

public class LocalWeatherUI {

	private LocalWeatherControl ctrl;
	
	public LocalWeatherUI(LocalWeatherControl ctrl) {
		this.ctrl = ctrl;
	}
	
	public void displayGetLocalWeatherForm() {
		System.out.println("Enter city name below: ");
	}
	
	public void enterInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter city info: ");
		String city = scanner.nextLine();
		scanner.close();
		boolean yes = ctrl.processGetLocalWeather(city);
		if(yes) {
			displayConfirmation();
		}
		else
			displayFailedMessage();
	}
	
	public void displayConfirmation() {
		System.out.println("Get local weather successful!");
	}
	
	public void displayFailedMessage() {
		System.out.println("Cannot get weather for the city, please try another one.");
	}
}
