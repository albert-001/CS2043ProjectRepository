
public class LocalWeatherControl {
	
	private WeatherManager wm;
	String local_weather = "";
	
	public LocalWeatherControl(WeatherManager wm) {
		this.wm = wm;
	}
	
	public boolean processGetLocalWeather(String city) {
		this.local_weather = wm.getCurrentWeather(city);
		return !this.local_weather.equals("");
	}
	
	public String localWeather() {
		return this.local_weather;
	}
}
