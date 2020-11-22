package CSCI201_LunchWithFriends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Restaurant {
	
	String address;
	ArrayList<User> interestedUsers;
	ArrayList<User> groupUsers;
	
	public Restaurant(String address) {
		this.address = address;
		this.interestedUsers = new ArrayList<User>();
	}
	
	public void likesRestaurant(User ie, boolean single) {
		if(single) {
			interestedUsers.add(ie);
		} else {
			groupUsers.add(ie);
		}
	}
	
	//returns whether the user has decided on the restaurant or not	
	public boolean hasDecidedOnRestaurant(User ie) {
		
		return (interestedUsers.contains(ie) || groupUsers.contains(ie));
		
	} 
	
	public ArrayList<User> getInterestedUsers() {
		return this.interestedUsers;
	}
	
	
	
	

}
