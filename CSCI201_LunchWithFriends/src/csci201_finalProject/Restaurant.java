package csci201_finalProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Restaurant {
	
	String address;
	ArrayList<User> interestedUsers;
	ArrayList<User> groupUsers;
	
	public Restaurant(String address) {
		this.address = address;
	}
	
	public void likesRestaurant(User ie, boolean single) {
		if(single) {
			interestedUsers.add(ie);
		} else {
			groupUsers.add(ie);
		}
	}
	
	
	
	

}
