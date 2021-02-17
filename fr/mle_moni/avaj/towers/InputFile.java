package fr.mle_moni.avaj.towers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputFile {
	private String filePath;
	private int iterations;
	private List<InputFile.AircraftOptions> aircraftsOptions = new ArrayList<>();

	public InputFile(String filePath) {
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
				Main.exitWithError("cannot parse iteration count in file " + this.filePath + ":" + lineNumber);
			}
			while ((strLine = file.readLine()) != null) {
				lineNumber++;
			}
			this.aircraftsOptions.add(new InputFile.AircraftOptions("Helicopter", "H1", 10, 10, 10));
			this.aircraftsOptions.add(new InputFile.AircraftOptions("Baloon", "B1", 10, 10, 10));
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
			Main.exitWithError("cannot parse file " + this.filePath);
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
