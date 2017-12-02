
public class LocalWeatherMain {

	public static void main(String[] args) {
		WeatherManager wm = new WeatherManager();
		LocalWeatherControl ctrl = new LocalWeatherControl(wm);
		LocalWeatherUI ui = new LocalWeatherUI(ctrl);
		
		ui.displayGetLocalWeatherForm();
		ui.enterInfo();
	}

}
