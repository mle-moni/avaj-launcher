package fr.mle_moni.avaj.aircrafts;

import java.util.HashMap;

import fr.mle_moni.avaj.towers.Main;
import fr.mle_moni.avaj.towers.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower = null;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);

		this.actions.put("SUN", new Coordinates(0, 2, 4));
		this.actions.put("RAIN", new Coordinates(0, 0, -5));
		this.actions.put("FOG", new Coordinates(0, 0, -3));
		this.actions.put("SNOW", new Coordinates(0, 0, -15));
	}

	@Override
	public void updateCondition() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		this.weatherAction(weather);
		if (this.coordinates.getHeight() == 0) {
			this.message("(landing) " + this.coordinates.toString());
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		weatherTower.register(this);
		this.weatherTower = weatherTower;
	}

	// added to DRY code

	private HashMap<String, Coordinates> actions = new HashMap<>();

	@Override
	protected HashMap<String, Coordinates> getActionMap() {
		return this.actions;
	}

	@Override
	protected String messageContent(String weather) {
		switch (weather) {
		case "SUN":
			return "Let's enjoy the good weather and take some pics. (SUN / Baloon)";
		case "RAIN":
			return "Damn you rain! You messed up my baloon. (RAIN / Baloon)";
		case "FOG":
			return "The fog is too dense, let's dance! (FOG / Baloon)";
		case "SNOW":
			return "It's snowing. We're gonna crash. (SNOW / Baloon)";
		default:
			Main.exitWithError("weather " + weather + " is unknown");
		}
		return null;
	}

	@Override
	protected String getType() {
		return "Baloon";
	}
}
