package fr.mle_moni.avaj.towers;

import fr.mle_moni.avaj.aircrafts.Coordinates;

public class WeatherTower extends Tower {
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	void changeWeather() {
		this.conditionsChanged();
	}
}
