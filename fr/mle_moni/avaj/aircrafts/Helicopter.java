package fr.mle_moni.avaj.aircrafts;

import java.util.HashMap;

import fr.mle_moni.avaj.towers.Main;
import fr.mle_moni.avaj.towers.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower = null;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);

		this.actions.put("SUN", new Coordinates(0, 10, 2));
		this.actions.put("RAIN", new Coordinates(0, 5, 0));
		this.actions.put("FOG", new Coordinates(0, 1, 0));
		this.actions.put("SNOW", new Coordinates(0, 0, -12));
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
			return "This is hot. (SUN / Helicopter)";
		case "RAIN":
			return "Oh no, it's raining, this is NOT pogchamp. (RAIN / Helicopter)";
		case "FOG":
			return "Oh no, it's foggy, this is really Sadge, no photographs for me :c (FOG / Helicopter)";
		case "SNOW":
			return "My rotor is going to freeze! (SNOW / Helicopter)";
		default:
			Main.exitWithError("weather " + weather + " is unknown");
		}
		return null;
	}

	@Override
	protected String getType() {
		return "Helicopter";
	}
}
