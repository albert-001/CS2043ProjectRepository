
public class FutureWeatherMain {

	public static void main(String[] args) {
		WeatherManager wm = new WeatherManager();
		FutureWeatherControl ctrl = new FutureWeatherControl(wm);
		FutureWeatherUI ui = new FutureWeatherUI(ctrl);
		
		ui.displayGetFutureWeatherForm();
		ui.enterInfo();
	}

}
