package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp?errorMsg=Invaild Access!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
 
		String errorMsg = "There is an error with either your username or password, pls try again." ;
		HttpSession session = request.getSession();
		
 		try {
 	        String result = ProcessWebService.authenticateUsers(username, password);
 	        
	        JsonArray ary = new JsonParser().parse(result).getAsJsonArray();
	        JsonObject jsonObject = ary.get(0).getAsJsonObject();
	              
	        if(jsonObject.has("errorMsg")){
	        	errorMsg = jsonObject.get("errorMsg").getAsString();
	        	session.setAttribute("errorMsg", errorMsg);
				response.sendRedirect("index.jsp");
				return;
	        } else {
				
				session.setAttribute("username",username);
				response.sendRedirect("main.jsp");
		
			}
 		}catch(Exception e) {
 			e.printStackTrace();
			session.setAttribute("errorMsg", "Shameful of us to tell you that there is a problem accessing the databse. Please try again later. ");
			response.sendRedirect("index.jsp");
 		} 
	}
			
}
