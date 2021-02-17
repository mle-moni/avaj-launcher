package fr.mle_moni.avaj.aircrafts;

import fr.mle_moni.avaj.towers.Main;

public abstract class AircraftFactory {
	static public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		switch (type) {
			case "Helicopter":
				return new Helicopter(name, new Coordinates(latitude, longitude, height));
			case "Baloon":
				return new Baloon(name, new Coordinates(latitude, longitude, height));
			case "JetPlane":
				return new JetPlane(name, new Coordinates(latitude, longitude, height));
			default:
				Main.exitWithError("unknown aircraft type: " + type);
		}
		return null;
	}
}
