package CSCI201_LunchWithFriends;

import java.io.Serializable;

public class Business implements Serializable {
	private static final long serialVersionUID = 1174418428708492256L;

	private double rating;
	private String price;
	private boolean is_closed;
	private String name;
	private String url;
	private Coordinates coordinates;
	private Address location;

	public Business(int rating, String price, boolean is_closed, String name, String url, Coordinates coordinates,
			Address location) {
		this.rating = rating;
		this.price = price;
		this.is_closed = is_closed;
		this.name = name;
		this.url = url;
		this.coordinates = coordinates;
		this.location = location;
	}

	public double getRating() {
		return rating;
	}

	public String getPrice() {
		return price;
	}

	public boolean getClosed() {
		return is_closed;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public double getLatitude() {
		return coordinates.latitude;
	}

	public double getLongitude() {
		return coordinates.longitude;
	}

	public String getAddress() {
		String output = "";
		for (String str : location.display_address)
			output += str + " ";
		return output;
	}

	class Coordinates {
		private double latitude;
		private double longitude;
	}

	class Address {
		private String[] display_address;
	}
}
