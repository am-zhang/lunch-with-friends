package CSCI201_LunchWithFriends;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User {
//	private static final long serialVersionUID = 1L;
	private String uniqueID; 
	public String name;
	private String email;
	private HashMap<Restaurant, ArrayList<InterestedUser>> potentialMatches;
	private HashMap<Restaurant, Boolean> groupOrSingle; //true if interested in group, false if only single user
	Location loc = new Location(37.7, -122.4);
	//private List<Business> businesses = YelpAPIParser.getBusiness(" ", loc);
	
	
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String nextPage = "/match.jsp";
//		String yesB = request.getParameter("yes");
//		String noB = request.getParameter("no");
//		PrintWriter out = response.getWriter();
//		System.out.print("HEYYYYY");
//		if(yesB == null || yesB.isEmpty()) {
//			this.name = "yes";
//			request.setAttribute("user1", this);
//			nextPage = "/match.jsp";
////			out.println("<html><body>");
////          out.println("yes");
////          out.println("</body></html>");
//		} else {
//			this.name = "hey";
//			//request.setAttribute("hey", this);
////			out.println("<html><body>");
////	          out.println("no");
////	          out.println("</body></html>");
//		}
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
//		dispatcher.forward(request, response);
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

//}
//
//
//
//
//
//
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//
//@WebServlet("/User")
//public class User extends HttpServlet {

//	private String name;
//	private String email;
//	private String bio;
//	private HashMap<Restaurant, ArrayList<InterestedUser>> potentialMatches;
//	private HashMap<Restaurant, Boolean> groupOrSingle; //true if interested in group, false if only single user
//	
//	public User() {}
	public User(String name, String email, String uniqueID) {
		this.name = name;
		this.email = email;
		this.uniqueID = uniqueID;
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
		
		System.out.print("hey");
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
	
	
	public String getEmail() {
		return email;
	}


	public String getName() {
		return name;
	}


	public String getUniqueID() {
		return uniqueID;
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

