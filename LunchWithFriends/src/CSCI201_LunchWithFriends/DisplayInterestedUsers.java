package CSCI201_LunchWithFriends;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * Servlet implementation class DisplayInterestedUsers
 */
@WebServlet("/DisplayInterestedUsers")
public class DisplayInterestedUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CREDENTIALS_STRING = "jdbc:mysql://google/lunchwithfriends?cloudSqlInstance=csci201-lunchwithfriends:us-west2:lunchwithfriends&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=lunchwithfriendsTest&password=lunchwithfriends";
	static Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayInterestedUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchname = request.getParameter("searchname");
		ArrayList<User> list = new ArrayList<User>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(CREDENTIALS_STRING);
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM serialized_java_restaurants");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			businessDBAccess busDB = new businessDBAccess();
			
			
			while (resultSet.next()) {
				long serializedID = resultSet.getLong("serialized_id");
				Business addBusiness = (Business) busDB.deSerializeJavaObjectFromDB(connection, serializedID);
				String name = addBusiness.getName();
				if(name.equalsIgnoreCase(searchname)) {
					list = addBusiness.getInterestedUsers();
				}
				
			}
		} catch(Exception e) {
			
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("possibleUsersList", list);
		
		request.setAttribute("possibleUsersList", list);
		request.getRequestDispatcher("match.jsp").forward(request, response);

	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
