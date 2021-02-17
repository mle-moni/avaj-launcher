package fr.mle_moni.avaj.aircrafts;

import java.util.HashMap;

import fr.mle_moni.avaj.logger.Logger;
import fr.mle_moni.avaj.towers.Main;

public abstract class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = this.nextId();
	}

	private long nextId() {
		return ++Aircraft.idCounter;
	}

	// added to DRY code

	protected void weatherAction(String weather) {
		HashMap<String, Coordinates> actions = this.getActionMap();
		if (!actions.containsKey(weather)) {
			Main.exitWithError("weather " + weather + " is unknown for the type " + this.getType());
		}
		this.coordinates.merge(actions.get(weather));
		this.message(this.messageContent(weather));
	}

	public String messageHeader() {
		return this.getType() + "#" + this.name + "(" + this.id + "): ";
	}

	protected void message(String message) {
		Logger logger = Main.getLogger();
		logger.logln(this.messageHeader() + message + "			POS = " + this.coordinates.toString());
	}

	abstract protected HashMap<String, Coordinates> getActionMap();

	abstract protected String messageContent(String weather);

	abstract protected String getType();
}
