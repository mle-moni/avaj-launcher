package fr.mle_moni.avaj.towers;

import java.util.List;

import fr.mle_moni.avaj.aircrafts.AircraftFactory;
import fr.mle_moni.avaj.logger.Logger;

public class Main {
	static private Logger logger = new Logger("simulation.txt");

	public static void main(String[] args) {
		if (args.length == 0) {
			Main.exitWithError("This program takes the path to the scenario file as first argument.");
		}
		if (args.length != 1) {
			Main.exitWithError("This program takes only 1 argument.");
		}
		Main.start(args[0]);
	}

	private static void start(String filePath) {
		WeatherTower tower = new WeatherTower();
		InputFile scenario = new InputFile(filePath);
		List<InputFile.AircraftOptions> aircraftOptions = scenario.getAircraftsOptions();
		for (InputFile.AircraftOptions option : aircraftOptions) {
			AircraftFactory.newAircraft(option.type, option.name, option.longitude, option.latitude, option.height)
					.registerTower(tower);
		}
		for (int i = 0; i < scenario.getIterations(); i++) {
			tower.changeWeather();
		}
	}

	static public Logger getLogger() {
		return Main.logger;
	}

	static public void exitWithError(String errorMessage) {
		Main.exitWithError(errorMessage, 1);
	}

	static public void exitWithError(String errorMessage, int status) {
		System.out.println("ERROR: " + errorMessage);
		System.exit(status);
	}
}
