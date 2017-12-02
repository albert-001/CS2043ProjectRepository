import java.util.ArrayList;
import java.util.Scanner;

public class ChattingRoomUI {

	private ChattingRoomControl control;
	private UserObject user;
	
	public ChattingRoomUI(ChattingRoomControl control, UserObject user) {
		this.control = control;
		this.user = user;
	}
	
	public void displayMessageForm(String ts) {
		System.out.println("input message:");
		Scanner sc = new Scanner(System.in);
		String message = sc.nextLine();
		sc.close();
		boolean r1 = control.writeInMessage(message, this.user.getName());
		if(r1) {
			displayConfirmation();
		}
		ArrayList<ChattingObject> msg_list = control.getLatestMessage(ts);
	}
	
	public void displayConfirmation() {
		System.out.println("write message successful");
	}
}
