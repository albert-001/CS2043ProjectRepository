

public class UserObject {

	private String name;
	private String password;
	private String[] location;
	private String gender;
	private String age;
	
	public UserObject(String name, String password, String locationIn, String gender, String age) {
		location = new String[10];
		this.name = name;
		this.password = password;
		location[0] = locationIn;
		this.gender = gender;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String[] getLocation() {
		return location;
	}
	
	public String getGender() {
		
		return gender;
	}
	
	public String getAge() {
		return age;
	}
	
	public String getLocationString() {
		String[] location =  this.getLocation();
		String new_location = location[0];
		for(int i=1; i<location.length; i++) {
			if(location[i] != null) {
				new_location += ("," + location[i]);
			}
		}
		return new_location;
	}
	
	public int getAgeRange() {
		int year = Integer.parseInt(age);
		int ageRange = 0;
		if(year<15) ageRange = 1;
		else if(year>=15 && year<30) ageRange = 2;
		else if(year>=30) ageRange = 3;
		return ageRange;
	}
	
	public boolean addCity(String name) {
		int i=1;
		while(i<=10 && location[i] != null) {
			i++;
		}
		
		if(i<=10) {
			CityList city = new CityList(name);
			location[i] = city.getCityName();
			return true;
		}
		else {
			System.out.println("You can only add up to 10 cities.");
			return false;
		}
	}
}
