package csci201_finalProject;

public class userSession{
	
	static Login login;
	
	public static void main(String[] args) {
		User currUser = login.Login();
		if (currUser != null) {
			//enable user functionality
		}
		else {
			//enable guest functionality
		}	
	}
}