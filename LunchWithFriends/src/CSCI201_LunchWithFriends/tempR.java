package CSCI201_LunchWithFriends;

import java.util.List;

public class tempR {
	
	static public Location loc = new Location(34.02951139, -118.2848199);

	static public List<Business> businesses = YelpAPIParser.getBusiness(" ", loc);
	
//	public tempR() {
//		Location loc = new Location(37.7, -122.4);
//
//		List<Business> businesses = YelpAPIParser.getBusiness(" ", loc);
//	}

}
