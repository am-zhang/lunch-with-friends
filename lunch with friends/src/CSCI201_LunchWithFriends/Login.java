package CSCI201_LunchWithFriends;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;

public class Login{
	GoogleIdToken token;
	
	public void Login(){
		 token = getToken();
		 
		 if (token != null) {
			 Payload payload = token.getPayload();
			 // search for user
			 String userId = payload.getSubject(); 
			 //create user if not found
			 if (!findUser(userId)) {
				 createUser(payload);
			 }
			 //create new authenticated session
					
		 } 
		 
		 else {
			 System.out.println("Invalid ID token.");
		 }
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
			URL url = new URL("insert url here later");
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
	public boolean findUser(String userId) {
		//if found return true
		return false;
	}
	
	public void createUser(Payload payload) {
		// use this stuff to make user

		String userId = payload.getSubject(); 
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String username = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
	    String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");
	}
	
}