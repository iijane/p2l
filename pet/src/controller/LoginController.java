package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import manager.UserManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import writemanager.WriteUserManager;

@Path("/LoginController")
public class LoginController {

	UserManager userManager = new UserManager();
	WriteUserManager writeUserManager = new WriteUserManager();

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String UserAuthentication (@QueryParam("email") String email, @QueryParam("password") String password){

		JsonObject resultJsonObject = new JsonObject();

		if(email.trim().isEmpty() || password.trim().isEmpty()){
			resultJsonObject.addProperty("errorMsg", "Please fill in all empty fields!");
		}else{
			String getUserJson = userManager.retrieveUserByEmail(email.trim());
			JsonObject getUserJsonObj = new Gson().fromJson(getUserJson, JsonObject.class);
			if(!getUserJsonObj.has("email")){
				resultJsonObject.addProperty("errorMsg", "Sorry, your email has not been registered.");
				resultJsonObject.addProperty("email", email);
			}else if(password.equals(getUserJsonObj.get("password").getAsString())){
				resultJsonObject.addProperty("successMsg", "Welcome to Petzlinked!");
				resultJsonObject.addProperty("userId", getUserJsonObj.get("user_id").getAsString());
				resultJsonObject.addProperty("firstName", getUserJsonObj.get("first_name").getAsString());
			}else{
				resultJsonObject.addProperty("errorMsg", "Sorry, the email and password does not match. Please try again!");
			}
		}
		return resultJsonObject.toString();
	}


	@POST
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)	
	public String UserRegistration (@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("rPassword") String rPassword, @FormParam("gender") String gender,
			@FormParam("dob") String dob, @FormParam("countries") String countries,
			@FormParam("state") String state, @FormParam("city") String city,
			@FormParam("address") String address, @FormParam("postal") String postal,
			@FormParam("countrycode") String countrycode, @FormParam("mobile") String mobile){
		
		String firstNameInput = firstName.trim();
		String lastNameInput = lastName.trim();
		String emailInput = email.trim();
		String passwordInput = password.trim();
		String rPasswordInput = rPassword.trim();
		String genderInput = gender.trim();
		String dobInput = dob.trim();
		String countriesInput = countries.trim();
		String stateInput = state.trim();
		String cityInput = city.trim();
		String addressInput = address.trim();
		String postalInput = postal.trim();
		String countrycodeInput = countrycode.trim();
		String mobileInput = mobile.trim();
		
		boolean success = true;
		
		JsonObject resultJsonObject = new JsonObject();
		
		
		
		if(firstNameInput.isEmpty() || lastNameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty()
				|| rPasswordInput.isEmpty() || dobInput.isEmpty() || countriesInput.isEmpty() || stateInput.isEmpty()
				|| cityInput.isEmpty() || addressInput.isEmpty() || countrycodeInput.isEmpty() || mobileInput.isEmpty()){
			resultJsonObject.addProperty("errorMsg", "Please fill in all empty fields!");
			success = false;
		}else{
			String getUserJson = userManager.retrieveUserByEmail(emailInput);
			JsonObject getUserJsonObj = new Gson().fromJson(getUserJson, JsonObject.class);
			if(getUserJsonObj.has("email")){
				resultJsonObject.addProperty("errorMsg", "Sorry an account has been registered under this email.");
				success = false;
			}else if(passwordInput.equals("rPasswordInput")){
				String regUserJson = writeUserManager.newUser(firstNameInput, lastNameInput, emailInput, passwordInput,
						genderInput, dobInput, countriesInput, stateInput, cityInput,addressInput, postalInput, countrycodeInput, mobileInput);

				JsonObject regUserJsonObj = new Gson().fromJson(regUserJson, JsonObject.class);
				
				if(regUserJsonObj.has("successMsg")){
					resultJsonObject.addProperty("successMsg", true);		
				}else{
					resultJsonObject.addProperty("errorMsg", regUserJsonObj.get("errorMsg").getAsString());
					success = false;
				}
			}else if(!passwordInput.equals("rPasswordInput")){
				resultJsonObject.addProperty("errorMsg", "Your password does not match.");
				success = false;
			}else{
				resultJsonObject.addProperty("errorMsg", "There has been a problem registering your account. Please try again.");
				success = false;
			}
		}
		
		if(!success){
			JsonObject inputJsonObject = new JsonObject();
			inputJsonObject.addProperty("firstNameInput", firstNameInput);
			inputJsonObject.addProperty("lastNameInput", lastNameInput);
			inputJsonObject.addProperty("emailInput", emailInput);
			inputJsonObject.addProperty("genderInput", genderInput);
			inputJsonObject.addProperty("dobInput", dobInput);
			inputJsonObject.addProperty("countriesInput", countriesInput);
			inputJsonObject.addProperty("stateInput", stateInput);
			inputJsonObject.addProperty("cityInput", cityInput);
			inputJsonObject.addProperty("addressInput", addressInput);
			inputJsonObject.addProperty("postalInput", postalInput);
			inputJsonObject.addProperty("countrycodeInput", countrycodeInput);
			inputJsonObject.addProperty("mobileInput", mobileInput);
			resultJsonObject.add("inputs", inputJsonObject);
		}
		
		return resultJsonObject.toString();
	}
}

