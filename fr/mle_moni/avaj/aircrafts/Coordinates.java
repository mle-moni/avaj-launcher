package fr.mle_moni.avaj.aircrafts;

public class Coordinates {
	private int latitude;
	private int longitude;
	private int height;

	public Coordinates(int latitude, int longitude, int height) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getHeight() {
		return height;
	}

	// added to DRY code
	public void merge(Coordinates coordinatesChanges) {
		this.latitude += coordinatesChanges.latitude;
		this.longitude += coordinatesChanges.longitude;
		this.height += coordinatesChanges.height;
		if (this.latitude < 0) {
			this.latitude = 0;
		}
		if (this.longitude < 0) {
			this.longitude = 0;
		}
		if (this.height < 0) {
			this.height = 0;
		}
		if (this.height > 100) {
			this.height = 100;
		}
	}

	@Override
	public String toString() {
		return "lat: " + this.latitude + ", lng: " + this.longitude + ", h: " + this.height;
	}
}
