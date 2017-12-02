import java.util.Scanner;

public class RecommendationUI {

	private RecommendationControl control;
	private UserObject user;
	
	public RecommendationUI(RecommendationControl control, UserObject user) {
		this.control = control;
		this.user = user;
	}
	
	public void displayConfirmation() {
		System.out.println("Login successful!");
	}
	
	public void displayLoginFailedMessage() {
		System.out.println("No user record is found. Please check your name and password then try again.");
	}
	
	public void enterInfo() {
		System.out.println("Enter weather:");
		Scanner sc = new Scanner(System.in);
		String weather = sc.nextLine();
		String a = user.getGender();
		int b = user.getAgeRange();
		if(control.processRecommendation(a,b,weather)!="")
			displayConfirmation();
		else
			displayLoginFailedMessage();
	}
}
