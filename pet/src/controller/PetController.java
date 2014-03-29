package controller;

import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import database.ConnectionManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import entity.Error;
import manager.PetManager;
import manager.UserManager;

@Path("/PetController")
public class PetController {

	PetManager petManager = new PetManager();
	UserManager userManager = new UserManager();

	@GET
	@Path("/GetPetsByUserId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPetsByUserId(@QueryParam("user_id") String user_id) {
		Connection connection = null;
		JsonObject resultJsonObject = new JsonObject();

		try {
			connection = ConnectionManager.getConnection();

			if (user_id.trim().isEmpty()) {
				resultJsonObject.addProperty("errorMsg", Error.blankErrorMsg);
			} else {
				String getPetsJson = petManager.retrievePetsByUserId(
						user_id.trim(), connection);
				JsonObject getPetsJsonObj = new Gson().fromJson(getPetsJson,
						JsonObject.class);

				if (!getPetsJsonObj.has("errorMsg")) {
					resultJsonObject.addProperty("errorMsg", getPetsJsonObj
							.get("errorMsg").getAsString());
				} else {
					resultJsonObject
							.addProperty("successMsg", "Pets Retrieved");
					resultJsonObject.add("results", getPetsJsonObj);
				}
			}

		} catch (Exception ex) {
			resultJsonObject.addProperty("errorMsg", Error.databaseErrorMsg);
		}
		return resultJsonObject.toString();
	}

	/*
	 * @GET
	 * 
	 * @Path("/getpet")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String
	 * (@QueryParam("user_id") String user_id,
	 * 
	 * @QueryParam("pet_id") String pet_id) {
	 * 
	 * JsonObject resultJsonObject = new JsonObject();
	 * 
	 * if (user_id.trim().isEmpty() || pet_id.trim().isEmpty()) {
	 * resultJsonObject.addProperty("errorMsg", Error.blankErrorMsg); } else {
	 * String getPetsJson = petManager.retrievePet(user_id.trim(),
	 * pet_id.trim(), connection); JsonObject getPetJsonObj = new
	 * Gson().fromJson(getPetsJson, JsonObject.class);
	 * 
	 * if (!getPetJsonObj.has("errorMsg")) {
	 * resultJsonObject.addProperty("errorMsg",
	 * getPetJsonObj.get("errorMsg").getAsString()); } else {
	 * resultJsonObject.addProperty("successMsg", "Pets Retrieved");
	 * resultJsonObject.add("results", getPetJsonObj); } } return
	 * resultJsonObject.toString(); }
	 */
	@POST
	@Path("/CreatePet")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String CreatePet(@FormParam("user_id") String user_id,
			@FormParam("name") String name, @FormParam("tag") String tag,
			@FormParam("gender") String gender,
			@FormParam("breed") String breed, @FormParam("dob") String dob,
			@FormParam("country") String country,
			@FormParam("description") String description) {

		Connection connection = null;
		JsonObject resultJsonObject = new JsonObject();

		try {
			connection = ConnectionManager.getConnection();

			String userIdInput = user_id.trim();
			String nameInput = name.trim();
			String tagInput = tag.trim();
			String genderInput = gender.trim();
			String breedInput = breed.trim();
			String dobInput = dob.trim();
			String countryInput = country.trim();
			String descInput = description.trim();

			boolean success = true;

			if (userIdInput.isEmpty() || nameInput.isEmpty()
					|| tagInput.isEmpty() || genderInput.isEmpty()
					|| breedInput.isEmpty() || dobInput.isEmpty()
					|| countryInput.isEmpty() || descInput.isEmpty()) {
				resultJsonObject.addProperty("errorMsg", Error.blankErrorMsg);
				success = false;
			} else {
				String getUserJson = userManager.retrieveUserById(userIdInput);
				JsonObject getUserJsonObj = new Gson().fromJson(getUserJson,
						JsonObject.class);
				if (getUserJsonObj.has("user_id")) {
					String regUserJson = petManager.newPet(userIdInput,
							nameInput, tagInput, genderInput, breedInput,
							dobInput, countryInput, descInput, connection);
					JsonObject regUserJsonObj = new Gson().fromJson(
							regUserJson, JsonObject.class);

					if (regUserJsonObj.has("successMsg")) {
						resultJsonObject.addProperty("successMsg", true);
					} else {
						resultJsonObject.addProperty("errorMsg", regUserJsonObj
								.get("errorMsg").getAsString());
						success = false;
					}

				}
			}
			if (!success) {
				JsonObject inputJsonObject = new JsonObject();
				inputJsonObject.addProperty("userIdInput", userIdInput);
				inputJsonObject.addProperty("nameInput", nameInput);
				inputJsonObject.addProperty("tagInput", tagInput);
				inputJsonObject.addProperty("genderInput", genderInput);
				inputJsonObject.addProperty("breedInput", breedInput);
				inputJsonObject.addProperty("dobInput", dobInput);
				inputJsonObject.addProperty("countryInput", countryInput);
				inputJsonObject.addProperty("descInput", descInput);
				resultJsonObject.add("inputs", inputJsonObject);
			}
		} catch (Exception ex) {
			resultJsonObject.addProperty("errorMsg", Error.databaseErrorMsg);
		} finally {
			try {
				ConnectionManager.closeConnection(connection);
			} catch (Exception ex) {
				resultJsonObject.addProperty("errorMsg", Error.databaseErrorMsg);
			}
		}

		return resultJsonObject.toString();
	}

	@GET
	@Path("/retrievePetsByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrievePetsByUserId(@QueryParam("user_id") String user_id) {
		Connection connection = null;
		JsonObject jsonObj = new JsonObject();
		String s = "";
		try {
			connection = ConnectionManager.getConnection();
			s = petManager.retrievePetsByUserId(user_id, connection);
		} catch (Exception ex) {
			jsonObj.addProperty("errorMsg", Error.databaseErrorMsg);
			s = jsonObj.toString();
		} finally {
			try {
				ConnectionManager.closeConnection(connection);
			} catch (Exception ex) {
				jsonObj.addProperty("errorMsg", Error.databaseErrorMsg);
				s = jsonObj.toString();
			}
		}
		return s;
	}

	@GET
	@Path("/retrievePet")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrievePet(@QueryParam("user_id") String user_id,
			@QueryParam("pet_id") String pet_id) {
		Connection connection = null;
		JsonObject jsonObj = new JsonObject();
		String s = "";
		try {
			connection = ConnectionManager.getConnection();
			s = petManager.retrievePet(user_id, pet_id, connection);
		} catch (Exception ex) {
			jsonObj.addProperty("errorMsg", Error.databaseErrorMsg);
			s = jsonObj.toString();
		} finally {
			try {
				ConnectionManager.closeConnection(connection);
			} catch (Exception ex) {
				jsonObj.addProperty("errorMsg", Error.databaseErrorMsg);
				s = jsonObj.toString();
			}
		}
		return s;
	}
}
