package fr.mle_moni.avaj.logger;

import java.io.IOException;

import fr.mle_moni.avaj.towers.Main;

import java.io.FileWriter;

public class Logger {
	private FileWriter file;

	public Logger(String filePath) {
		try {
			this.file = new FileWriter(filePath);
			this.file.write(""); // clear file
			this.file.flush();
		} catch (IOException e) {
			e.printStackTrace();
			Main.exitWithError("cannot create FileWriter.");
		}
	}

	public void log(String message) {
		try {
			this.file.append(message);
			this.file.flush();
		} catch (IOException e) {
			e.printStackTrace();
			Main.exitWithError("cannot write file.");
		}
	}

	public void logln(String message) {
		this.log(message + "\n");
	}

	public void close() {
		try {
			this.file.close();
		} catch (IOException e) {
			e.printStackTrace();
			Main.exitWithError("cannot close file.");
		}
	}
}
