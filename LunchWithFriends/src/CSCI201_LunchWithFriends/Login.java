package CSCI201_LunchWithFriends;

import java.io.BufferedReader;
import java.sql.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.UUID;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;

public class Login{
	GoogleIdToken token;
	
	JDBC_Access dbaccess = new JDBC_Access();

	userDBAccess useraccess = new userDBAccess();
	
	public static final String CREDENTIALS_STRING = "jdbc:mysql://google/lunchwithfriends?cloudSqlInstance=csci201-lunchwithfriends:us-west2:lunchwithfriends&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=lunchwithfriendsTest&password=lunchwithfriends";
	
	static Connection connection = null;
	
	
	
	public User Login(){
		 token = getToken();
		 
		 if (token != null) {
			 Payload payload = token.getPayload();
			 // search for user
			 String email = payload.getEmail();
			 User currUser = findUser(payload);
			 
			 //create user if not found
			 if (currUser == null) {
				 try {
					 return createUser(payload);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			 }
			 else {return currUser;}		
		 } 
		 
		 else {
			 System.out.println("Invalid ID token.");
		 }
		 
		 return null;
	}
	
	//creates and verifies token
	public GoogleIdToken getToken() {//note to self: might need to change ret type 
		
		JsonFactory jsonFactory = null;
		HttpTransport transport = null;
		
		//Create verifier object
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
			    .setAudience(Collections.singletonList
			    		("1086689429442-1v6o19vn962eclqnv5oirtrp5r5g7d33"))
			    .build();
		
		
		
		
		//recieve token string (nts: check if this is fine or you need to use post)
	    String token = null;
		GoogleIdToken idToken = null;
		
		try {
			URL url = new URL("ws://localhost:8080/LunchWithFriends/index.jsp");
			URLConnection connect = url.openConnection();
			connect.setDoInput(true);
			connect.setDoOutput(true);
			
			 BufferedReader in = new BufferedReader
					 (new InputStreamReader(connect.getInputStream()));
			 token = in.readLine(); //should be formatted as JSON
			 String[] temp = token.split("=");
			 token = temp[1];
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create actual token
		try {
			idToken = verifier.verify(token);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idToken;
		
	}

	//verify account -> see if account matches existing user or need to create new
	public User findUser(Payload payload) {
		
		try {
			String sql = "SELECT serID FROM serIDs WHERE email =" +
					payload.getEmail();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs != null) {
				Long serID = rs.getLong("serID");
				User u;
				u = (User) useraccess.deSerializeJavaObjectFromDB(connection, serID);
				return u;
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public User createUser(Payload payload) throws IOException, ClassNotFoundException {
		
		/// use this stuff to make user
		
		String uniqueId = payload.getSubject(); 
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String username = (String) payload.get("name");
		/*String pictureUrl = (String) payload.get("picture");
	    String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");*/
		
	
		
		if (emailVerified) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(CREDENTIALS_STRING);
				User u = new User(username, email,uniqueId);
				Long sID = useraccess.serializeJavaObjectToDB(connection, u);
				
				String sql = "INSERT INTO serIDs VALUES ("
						+ sID + ", " + email + ")";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.execute(sql);
				
				return u;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
