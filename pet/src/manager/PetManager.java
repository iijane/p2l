package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import database.ConnectionManager;

@Path("/PetManager")
public class PetManager {

	private Connection connection = null;
	// private Statement statement = null;
	private ResultSet rs = null;
	private PreparedStatement queryPstmt = null;

	@GET
	@Path("/retrievePetByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrievePetByUser(@QueryParam("email") String email) {

		JsonObject petJsonObj = new JsonObject();

		try {
			connection = ConnectionManager.getConnection();

			String retrieveUserSQL = "SELECT * FROM pet WHERE user_id = (SELECT user_id FROM \"user\" WHERE email = ?);";
			queryPstmt = connection.prepareStatement(retrieveUserSQL);
			queryPstmt.setString(1, email);

			ResultSet rs = queryPstmt.executeQuery();

			while (rs.next()) {
				petJsonObj.addProperty("pet_id", rs.getInt("pet_id"));
				petJsonObj.addProperty("user_id", rs.getInt("user_id"));
				petJsonObj.addProperty("pet_name", rs.getString("pet_name"));
				petJsonObj.addProperty("tag_id", rs.getString("tag_id"));
				petJsonObj.addProperty("gender", rs.getString("gender"));
				petJsonObj.addProperty("breed", rs.getString("breed"));
				petJsonObj.addProperty("dob", rs.getString("dob"));
				petJsonObj.addProperty("country", rs.getString("country"));
				petJsonObj.addProperty("description",
						rs.getString("description"));
			}
		} catch (SQLException ex) {
			petJsonObj
					.addProperty("errorMsg",
							"There has been a problem accessing the database. Please try again later");
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		return petJsonObj.toString();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putPet(JAXBElement<JsonObject> ele) {
		JsonObject res = new JsonObject();
		
		try {
			JsonObject pet = ele.getValue();//new Gson().fromJson(jsonString, JsonObject.class);
			connection = ConnectionManager.getConnection();

			String putPetSQL = "INSERT INTO pet(pet_id, user_id, pet_name, tag_id, gender, breed, dob, country, description "
					+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?);";

			queryPstmt = connection.prepareStatement(putPetSQL);
			
			queryPstmt.setString(1, pet.get("user_id").getAsString());
			queryPstmt.setString(2, pet.get("pet_name").getAsString());
			queryPstmt.setString(3, pet.get("tag_id").getAsString());
			queryPstmt.setString(4, pet.get("gender").getAsString());
			queryPstmt.setString(5, pet.get("breed").getAsString());
			queryPstmt.setString(5, pet.get("dob").getAsString());
			queryPstmt.setString(5, pet.get("country").getAsString());
			queryPstmt.setString(5, pet.get("description").getAsString());

			int affectedRows = queryPstmt.executeUpdate();
			
			if (affectedRows == 1) {
				res.addProperty("success", true);
			} else {
				res.addProperty("success", false);
			}
			
		} catch (SQLException ex) {
			res.addProperty("error", ex.getMessage());
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		
		//return res.toString();
	}
}
