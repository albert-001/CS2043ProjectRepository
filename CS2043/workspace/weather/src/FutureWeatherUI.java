import java.util.Scanner;

public class FutureWeatherUI {

	private FutureWeatherControl ctrl;
	
	public FutureWeatherUI(FutureWeatherControl ctrl) {
		this.ctrl = ctrl;
	}
	
	public void displayGetFutureWeatherForm() {
		System.out.println("Enter city name below: ");
	}
	
	public void enterInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter city info: ");
		String city = scanner.nextLine();
		scanner.close();
		boolean yes = ctrl.processGetFutureWeather(city);
		if(yes) {
			displayConfirmation();
		}
		else
			displayFailedMessage();
	}
	
	public void displayConfirmation() {
		System.out.println("Get future weather successful!");
	}
	
	public void displayFailedMessage() {
		System.out.println("Cannot get weather for the city, please try another one.");
	}
	
}
