
public class FutureWeatherControl {
	private WeatherManager wm;
	String future_weather = "";
	
	public FutureWeatherControl(WeatherManager wm) {
		this.wm = wm;
	}
	
	public boolean processGetFutureWeather(String city) {
		this.future_weather = wm.getFutureWeather(city);
		return !this.future_weather.equals("");
	}
	
	public String futureWeather() {
		return this.future_weather;
	}
}
