package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

import database.ConnectionManager;

import javax.ws.rs.core.MediaType;


/**
 * Servlet implementation class CreatePetServlet
 */
public class CreatePetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("./createpet.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String name = (String) request.getParameter("name");
		String tag = (String) request.getParameter("tag");
		String gender = (String) request.getParameter("gender");
		String breed = (String) request.getParameter("breed");
		String dob = (String) request.getParameter("dob");
		String country = (String) request.getParameter("country");
		String description = (String) request.getParameter("description");
		PrintWriter out = response.getWriter();
		
		Form form = new Form();
	    form.add("name", name);
	    form.add("user_id", username);
	    form.add("tag", tag);
	    form.add("gender", gender);
	    form.add("breed", breed);
	    form.add("dob", dob);
	    form.add("country", country);
	    form.add("description", description);
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(ConnectionManager.getBaseURI());
		
		ClientResponse clientResp = service.path("rest").path("PetController").path("createpet").type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		
		String jsonString = clientResp.getEntity(String.class);
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);

		if(jsonObject.has("errorMsg")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("createpet.jsp");
			request.setAttribute("errorMsg", jsonObject.get("errorMsg").getAsString());
			request.setAttribute("inputs", jsonObject.get("inputs").getAsJsonObject());
			dispatcher.forward(request, response);
			return;
		} else {
			response.sendRedirect("createpet.jsp?regsuccess=true");
		}
		
	}

}
