package fr.mle_moni.avaj.aircrafts;

import java.util.HashMap;

import fr.mle_moni.avaj.towers.Main;
import fr.mle_moni.avaj.towers.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower = null;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);

		this.actions.put("SUN", new Coordinates(10, 0, 2));
		this.actions.put("RAIN", new Coordinates(5, 0, 0));
		this.actions.put("FOG", new Coordinates(1, 0, 0));
		this.actions.put("SNOW", new Coordinates(0, 0, -7));
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
				return "SUN JetPlane";
			case "RAIN":
				return "RAIN JetPlane";
			case "FOG":
				return "FOG JetPlane";
			case "SNOW":
				return "SNOW JetPlane";
			default:
				Main.exitWithError("weather " + weather + " is unknown");
		}
		return null;
	}

	@Override
	protected String getType() {
		return "JetPlane";
	}
}
