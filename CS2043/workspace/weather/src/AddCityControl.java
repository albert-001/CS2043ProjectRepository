
public class AddCityControl {

	private DataManager dm;
	
	public AddCityControl(DataManager dm) {
		this.dm = dm;
	}
	
	public boolean processAddCity(String name) {
		return dm.getCityByName(name);
	}
}
