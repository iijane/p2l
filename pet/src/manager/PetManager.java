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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import database.ConnectionManager;

public class PetManager {

	public String retrievePetsByUserId(String id, Connection connection) {
		
		JsonObject petsJsonObj = new JsonObject();
		JsonArray pets = new JsonArray();

		try {

			String retrieveUserSQL = "SELECT * FROM pet WHERE user_id = ?;";
			PreparedStatement queryPstmt = connection.prepareStatement(retrieveUserSQL);
			queryPstmt.setString(1, id);

			ResultSet rs = queryPstmt.executeQuery();

			while (rs.next()) {
				JsonObject petJsonObj = new JsonObject();
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
				pets.add(petJsonObj);
			}
			
			petsJsonObj.addProperty("user_id", id);
			petsJsonObj.add("pets", pets);
		} catch (SQLException ex) {
			petsJsonObj.addProperty("errorMsg",
							"There has been a problem accessing the database. Please try again later");
		}
		
		return petsJsonObj.toString();
	}
	
	public String retrievePet(String user_id, String pet_id, Connection connection) {
		
		JsonObject petJsonObj = new JsonObject();

		try {
			String retrieveUserSQL = "SELECT * FROM pet WHERE user_id = ? AND pet+id = ? ;";
			PreparedStatement queryPstmt = connection.prepareStatement(retrieveUserSQL);
			queryPstmt.setString(1, user_id);
			queryPstmt.setString(2, pet_id);

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
			petJsonObj.addProperty("errorMsg",
							"There has been a problem accessing the database. Please try again later");
		}
		return petJsonObj.toString();
	}
	
	public String newPet(String userId, String petName, String tagId, String gender,
			String breed, String dob, String country, String description, Connection connection){
		 
		JsonObject res = new JsonObject();
		
		try{
			connection = ConnectionManager.getConnection();

			String putPetSQL = "INSERT INTO pet(pet_id, user_id, pet_name, tag_id, gender, breed, dob, country, description "
					+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, CAST(? AS DATE), ?, ?);";

			PreparedStatement queryPstmt = connection.prepareStatement(putPetSQL);
			queryPstmt.setString(1, userId);
			queryPstmt.setString(2, petName);
			queryPstmt.setString(3, tagId);
			queryPstmt.setString(4, gender);
			queryPstmt.setString(5, breed);
			queryPstmt.setString(6, dob);
			queryPstmt.setString(7, country);
			queryPstmt.setString(8, description);
			
			int affectedRows = queryPstmt.executeUpdate();
			
			if (affectedRows == 1) {
				res.addProperty("successMsg", true);
			} else {
				res.addProperty("errorMsg", "There has been a problem accessing the database. Please try again later.");
			}
			
		}catch (SQLException ex) {
			res.addProperty("errorMsg", "There has been a problem accessing the database. Please try again later.");
			res.addProperty("error",  ex.getMessage());
		}
		return res.toString();
	}
	
}
