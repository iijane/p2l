package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import manager.UserManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/LoginController")
public class LoginController {

	UserManager userManager = new UserManager();

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String UserAuthentication (@QueryParam("email") String email, @QueryParam("password") String password){

		JsonObject resultJsonObject = new JsonObject();

		if(email.trim().isEmpty() || password.trim().isEmpty()){
			resultJsonObject.addProperty("errorMsg", "Please fill in all empty fields!");
		}else{
			String getUser = userManager.retrieveUserByEmail(email.trim());
			JsonObject getUserJsonObj = new JsonParser().parse(getUser).getAsJsonObject();
			if(!getUserJsonObj.has("email")){
				resultJsonObject.addProperty("errorMsg", "Sorry, your email has not been registered.");
			}else if(password.equals(getUserJsonObj.get("password").getAsString())){
				resultJsonObject.addProperty("successMsg", "Welcome to Petzlinked!");
			}else{
				resultJsonObject.addProperty("errorMsg", "Sorry, the email and password does not match. Please try again!");
			}
		}
		return resultJsonObject.toString();
	}
}

