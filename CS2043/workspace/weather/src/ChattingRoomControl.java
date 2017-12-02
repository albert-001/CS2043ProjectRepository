import java.util.ArrayList;

public class ChattingRoomControl {

	private DataManager dm;
	
	public ChattingRoomControl(DataManager dm) {
		this.dm = dm;
	}
	
	public ArrayList<ChattingObject> getPreviousMessage() {
		return dm.getHistoryMessage();
	}
	
	public boolean writeInMessage(String message, String name) {
		return dm.writeMessage(name, message);
	}
	
	public ArrayList<ChattingObject> getLatestMessage(String time){
		return dm.getNewMessage(time);
	}
}
