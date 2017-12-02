public class ChattingObject {

	private String date;
	private String message;
	private String name;
	
	public ChattingObject(String date, String name, String message) {
		this.date = date;
		this.name = name;
		this.message = message;
	}
	
	public String toString() {
		return this.date + "," + this.name + "," + this.message;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMessage() {
		return this.message;
	}
}
