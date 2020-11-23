package CSCI201_LunchWithFriends;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InterestedServlet
 */
@WebServlet("/InterestedServlet")
public class InterestedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterestedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		//The request parameter is what the Java code in chatBasic.jsp uses to get info
		Business getBusiness = (Business) request.getAttribute("business_name");
		String getRoomID = request.getParameter("room_id");
		User getOtherUser = (User) request.getAttribute("other_user");
		User getThisUser = (User) request.getAttribute("this_user");
		
		//Adding to each other's chat pages
		getThisUser.addChat(getBusiness, getRoomID, getOtherUser);
		getOtherUser.addChat(getBusiness, getRoomID, getThisUser);

		//request.getRequestDispatcher("___.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}