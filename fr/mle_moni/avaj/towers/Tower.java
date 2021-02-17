package fr.mle_moni.avaj.towers;

import fr.mle_moni.avaj.aircrafts.Aircraft;
import fr.mle_moni.avaj.aircrafts.Flyable;
import fr.mle_moni.avaj.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	private List<Flyable> observers = new ArrayList<Flyable>();

	public void register(Flyable flyable) {
		Logger logger = Main.getLogger();
		if (this.observers.contains(flyable)) {
			Main.exitWithError("cannot register observer: array already contains this element.");
		}
		this.observers.add(flyable);
		Aircraft aircraft = (Aircraft) flyable;
		logger.logln("Tower says: " + aircraft.messageHeader() + "registered to weather tower");
	}

	public void unregister(Flyable flyable) {
		Logger logger = Main.getLogger();
		if (!this.observers.contains(flyable)) {
			Main.exitWithError("cannot unregister: observers array does not contain this element");
		}
		this.observers.remove(flyable);
		Aircraft aircraft = (Aircraft) flyable;
		logger.logln("Tower says: " + aircraft.messageHeader() + "unregistered to weather tower");
	}

	protected void conditionsChanged() {
		for (int i = 0; i < this.observers.size(); i++) {
			this.observers.get(i).updateCondition();
		}
	}
}
