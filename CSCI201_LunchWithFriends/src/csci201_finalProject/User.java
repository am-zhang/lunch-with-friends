package csci201_finalProject;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

	private String name;
	private String email;
	private String bio;
	private HashMap<Restaurant, ArrayList<InterestedUser>> potentialMatches;
	private HashMap<Restaurant, Boolean> groupOrSingle; //true if interested in group, false if only single user
	
	public User(String name, String email, String bio) {
		this.name = name;
		this.email = email;
		this.bio = bio;
	}
	
	
	/**
	 * Adds restaurant user is in.
	 * 
	 * If group is true, only adds Restaurant to group 
	 * NEED: group chat addition if group is true
	 * 
	 * If group is false, add to list of restaurants and potential matches maps
	 */
	public void addRestaurant(Restaurant restaurant, boolean group) {
		
		
		if(group) {
			groupOrSingle.put(restaurant, true);
		} else {
			groupOrSingle.put(restaurant, false);
			ArrayList<InterestedUser> usersInterestedIn = null;
			potentialMatches.put(restaurant, usersInterestedIn);
			
		}
	}
	
	public boolean isInterestedInRestaurant(Restaurant restaurant) {
		if(groupOrSingle.get(restaurant) != null) return true;
		return false;
	}
	
	public boolean wantsGroupOrSingle(Restaurant restaurant) {
		
		if(isInterestedInRestaurant(restaurant)) {
			return groupOrSingle.get(restaurant);
		} else {
			throw new RuntimeException("User is not interested in this restaurant");
		}
	}
	
	public void addIntrestedUserAtRestaurant(Restaurant restaurant, User user, boolean isInterested) {
		ArrayList<InterestedUser> listOfUsers = potentialMatches.get(restaurant);
		listOfUsers.add(new InterestedUser(user, isInterested));
	}
	
	public boolean isInterestedInUserAtRestaurant(Restaurant restaurant, User user) {
		ArrayList<InterestedUser> listOfUsers = potentialMatches.get(restaurant);
		
		if(listOfUsers == null) throw new RuntimeException("Restaurant has not been added yet");
		
		
		if(hasDecidedOn(restaurant, user)) {
			InterestedUser intrUsr = getInterestedUser(restaurant, user);
			if(intrUsr.isInterested()) return true;
			return false;
		}
		
		return false;
	}
	
	public boolean hasDecidedOn(Restaurant restaurant, User user) {
		ArrayList<InterestedUser> listOfUsers = potentialMatches.get(restaurant);
		
		if(listOfUsers == null) throw new RuntimeException("Restaurant has not been added yet");
		
		for(int i = 0; i < listOfUsers.size(); i++) {
			InterestedUser intrUsr = listOfUsers.get(i);
			if(intrUsr.getUser() == user) {
				return true;
			}
		}
		return false;
	}
	
	public InterestedUser getInterestedUser(Restaurant restaurant, User user) {
		ArrayList<InterestedUser> listOfUsers = potentialMatches.get(restaurant);
		
		if(listOfUsers == null) throw new RuntimeException("Restaurant has not been added yet");
		
		for(int i = 0; i < listOfUsers.size(); i++) {
			InterestedUser intrUsr = listOfUsers.get(i);
			if(intrUsr.getUser() == user) {
				return intrUsr;
			}
		}
		return null;
	}
	
	
	public ArrayList<InterestedUser> getChoicesForRestaurant(Restaurant restaurant) {
		
		return potentialMatches.get(restaurant);
	}
	
	
	public boolean isMatch(Restaurant restaurant, User user) {
		return (isInterestedInUserAtRestaurant(restaurant, user) && user.isInterestedInUserAtRestaurant(restaurant, this));
	}
	
	
	private class InterestedUser {
		
		User user;
		boolean isInterested;
		
		public InterestedUser(User user, boolean isInterested) {
			this.user = user;
			this.isInterested = isInterested;
		}
		
		public User getUser() {
			return this.user;
		}
		
		public boolean isInterested() {
			return this.isInterested;
		}
	}
	
	
	
	
}
