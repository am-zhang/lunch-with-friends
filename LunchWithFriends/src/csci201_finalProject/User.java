package csci201_finalProject;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/User")
public class User extends HttpServlet {

	private String name;
	private String email;
	private String bio;
	private HashMap<Restaurant, ArrayList<InterestedUser>> potentialMatches;
	private HashMap<Restaurant, Boolean> groupOrSingle; //true if interested in group, false if only single user
	
	public User() {}
	public User(String name, String email, String bio) {
		this.name = name;
		this.email = email;
		this.bio = bio;
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//PrintWriter out = response.getWriter();
		String error = "";
		System.out.print("hello");
		String button = request.getParameter("button");
		if ("button1".equals(button)) {
           out.println("<html><body>");
           out.println("hey");
           out.println("</body></html>");
        } else if ("button2".equals(button)) {
        	System.out.print("hello");
        } else if ("button3".equals(button)) {
        	System.out.print("goodbye");
        }
		//String lna = request.getParameter("last_name");
//		if (fna.contentEquals(""))
//			error += "<p>first name missing</p>";
//		if (lna.contentEquals(""))
//			error += "<p>last name missing</p>";
//
//		if (error.equals(""))
//	        request.getRequestDispatcher("Submitted.jsp").forward(request, response);
//		else
//		{
//			request.setAttribute("error", error);
//			request.getRequestDispatcher("Exercise.jsp").include(request, response);
//		}
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


	public String getBio() {
		return bio;
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
