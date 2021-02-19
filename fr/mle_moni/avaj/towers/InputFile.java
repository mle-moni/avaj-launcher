package fr.mle_moni.avaj.towers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputFile {
	private String filePath;
	private int iterations;
	private List<InputFile.AircraftOptions> aircraftsOptions = new ArrayList<>();
	private List<String> aircraftTypes = new ArrayList<>();

	public InputFile(String filePath) {
		aircraftTypes.add("Helicopter");
		aircraftTypes.add("JetPlane");
		aircraftTypes.add("Baloon");
		this.filePath = filePath;
		this.parse();
	}

	private void parse() {
		int lineNumber = 1;
		try {
			BufferedReader file = new BufferedReader(new FileReader(this.filePath));
			String strLine = file.readLine();
			// the first line is the number of iterations
			try {
				this.iterations = Integer.parseInt(strLine);
			} catch (Exception e) {
				Main.exitWithError("cannot parse iteration count in file at " + this.filePath + ":" + lineNumber);
			}
			if (this.iterations <= 0) {
				Main.exitWithError("iteration count should be at least 1 at " + this.filePath + ":" + lineNumber);
			}
			while ((strLine = file.readLine()) != null) {
				lineNumber++;
				if (strLine != "") {
					this.parseLine(strLine, lineNumber);
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
			Main.exitWithError("cannot parse file " + this.filePath);
		}
	}

	private void parseLine(String line, int lineNumber) {
		// split on whitespaces
		List<String> words = Arrays.asList(line.split("\\s+"));
		if (words.size() != 5) {
			String errMsg = "bad number of argument in scenario file, expected 5 but got " + words.size();
			errMsg += " at ./" + this.filePath + ":" + lineNumber;
			Main.exitWithError(errMsg);
		}
		String type = words.get(0);
		if (!this.aircraftTypes.contains(type)) {
			Main.exitWithError("unknown aircraft type in scenario file at ./" + this.filePath + ":" + lineNumber);
		}
		String name = words.get(1);
		try {
			int lng = Integer.parseInt(words.get(2));
			int lat = Integer.parseInt(words.get(3));
			int h = Integer.parseInt(words.get(4));
			if (lng < 0 || lat < 0 || h < 0) {
				String errMsg = "latitude, longitude and height should be positive in scenario file at ./";
				Main.exitWithError(errMsg + this.filePath + ":" + lineNumber);
			}
			if (h > 100) {
				String errMsg = "height should be less than 100 in scenario file at ./";
				Main.exitWithError(errMsg + this.filePath + ":" + lineNumber);
			}
			this.aircraftsOptions.add(new InputFile.AircraftOptions(type, name, lng, lat, h));
		} catch (Exception e) {
			String errMsg = "latitude, longitude and height should be integers in scenario file at ./";
			Main.exitWithError(errMsg + this.filePath + ":" + lineNumber);
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public int getIterations() {
		return iterations;
	}

	public List<InputFile.AircraftOptions> getAircraftsOptions() {
		return aircraftsOptions;
	}

	public static class AircraftOptions {
		public String name;
		public String type;
		public int latitude;
		public int longitude;
		public int height;

		public AircraftOptions(String type, String name, int latitude, int longitude, int height) {
			this.type = type;
			this.name = name;
			this.latitude = latitude;
			this.longitude = longitude;
			this.height = height;
		}
	}
}
