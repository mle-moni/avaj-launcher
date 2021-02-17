package fr.mle_moni.avaj.towers;

import fr.mle_moni.avaj.aircrafts.Coordinates;
import java.util.Random;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = null;
	private static String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

	private static Random random = new Random();

	private WeatherProvider() {

	}

	static public WeatherProvider getProvider() {
		if (WeatherProvider.weatherProvider == null) {
			WeatherProvider.weatherProvider = new WeatherProvider();
		}
		return WeatherProvider.weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int index = coordinates.getLatitude() + coordinates.getLongitude() + coordinates.getHeight();
		index += WeatherProvider.random.nextInt(20);
		return WeatherProvider.weather[(index + 3) % 4];
	}
}
