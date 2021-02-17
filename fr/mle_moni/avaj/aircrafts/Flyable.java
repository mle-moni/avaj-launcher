package fr.mle_moni.avaj.aircrafts;

import fr.mle_moni.avaj.towers.WeatherTower;

public interface Flyable {
	public void updateCondition();

	public void registerTower(WeatherTower weatherTower);
}
