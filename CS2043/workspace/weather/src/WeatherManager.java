import java.net.*;
import java.io.*;
import java.util.*;

public class WeatherManager {
	private HashMap<String, String> city_ids;
	public WeatherManager() {
	      this.city_ids = new HashMap<String, String>(); 
	      this.city_ids.put("Fredericton", "1001");
	      this.city_ids.put("Moncton", "49417");
	      this.city_ids.put("Toronto", "55488");
	      this.city_ids.put("Montreal", "56186");
	      this.city_ids.put("Halifax", "49538");
	      this.city_ids.put("Calgary", "52479");
	      this.city_ids.put("Ottawa", "55487");
	      this.city_ids.put("Vancouver", "53286");
	      this.city_ids.put("Quebec", "50011");
	      this.city_ids.put("Edmonton", "52478");
	}
	public String getCurrentWeather(String city) {
		  String result="";
	      try {
	    	  String urlString = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=10035a8fd2dbe5a3272f73450cb60190";
	          URL url = new URL(urlString);
	          URLConnection urlConnection = url.openConnection();
	          HttpURLConnection connection = (HttpURLConnection) urlConnection;
	          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	          String current;
	          while((current = in.readLine()) != null) {
	        	  result += current;
	          }
	       } catch (IOException e) {
	          e.printStackTrace();
	       }
	      return result;
	}
	
	public String getFutureWeather(String city) {
		  String result="";
		  String city_id = this.city_ids.getOrDefault(city, "1001");
	      try {
	    	  String urlString = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + city_id + "?apikey=BeSC7CWiO4yX3wNW6KryAHgeVgsdaZrN";
	          URL url = new URL(urlString);
	          URLConnection urlConnection = url.openConnection();
	          HttpURLConnection connection = (HttpURLConnection) urlConnection;
	          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	          String current;
	          while((current = in.readLine()) != null) {
	        	  result += current;
	          }
	       } catch (IOException e) {
	          e.printStackTrace();
	       }
	      return result;
	}

	public static void main(String[] args) {
		WeatherManager w = new WeatherManager();
		System.out.println(w.getCurrentWeather("Fredericton"));
		System.out.println(w.getFutureWeather("Fredericton"));
	}
}
