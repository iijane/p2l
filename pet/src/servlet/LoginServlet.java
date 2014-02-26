package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import database.ConnectionManager;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailInput = (String) request.getParameter("email");
		String passwordInput = (String) request.getParameter("password");

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(ConnectionManager.getBaseURI());

		String jsonString = service.path("rest").path("LoginController").path("login")
				.queryParam("email", emailInput).queryParam("password", passwordInput)
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);

		if(jsonObject.has("errorMsg")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			request.setAttribute("email", emailInput);
			request.setAttribute("errorMsg", jsonObject.get("errorMsg").getAsString());
			dispatcher.forward(request, response);
			return;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("userId", jsonObject.get("userId").getAsString());
			session.setAttribute("firstName", jsonObject.get("firstName").getAsString());
			response.sendRedirect("main.jsp");
		}
		

	}

}
