package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.api.client.ClientResponse;

import database.ConnectionManager;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Form form = new Form();
	    form.add("firstName", (String) request.getParameter("fname"));
	    form.add("lastName", (String) request.getParameter("lname"));
	    form.add("email", (String) request.getParameter("email"));
	    form.add("password", (String) request.getParameter("pass"));
	    form.add("rPassword", (String) request.getParameter("rpass"));
	    form.add("gender", (String) request.getParameter("gender"));
	    form.add("dob", (String) request.getParameter("dob"));
	    form.add("countries", (String) request.getParameter("countries"));
	    form.add("state", (String) request.getParameter("state"));
	    form.add("city", (String) request.getParameter("city"));
	    form.add("address", (String) request.getParameter("address"));
	    form.add("postal", (String) request.getParameter("postal"));
	    form.add("countrycode", (String) request.getParameter("countrycode"));
	    form.add("mobile", (String) request.getParameter("mobile"));
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(ConnectionManager.getBaseURI());
		
		ClientResponse clientResp = service.path("rest").path("LoginController").path("registration").type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		
		String jsonString = clientResp.getEntity(String.class);
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);

		if(jsonObject.has("errorMsg")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("errorMsg", jsonObject.get("errorMsg").getAsString());
			request.setAttribute("inputs", jsonObject.get("inputs").getAsJsonObject());
			dispatcher.forward(request, response);
			return;
		} else {
			response.sendRedirect("index.jsp?regsuccess=true");
		}
		
	}

}
