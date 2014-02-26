package writemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

import database.ConnectionManager;

public class WritePetManager {
	
	private Connection connection = null;
	//private Statement statement = null;
	private ResultSet rs = null;
	private PreparedStatement queryPstmt = null;
	
	public String newPet(String userId, String petName, String tagId, String gender,
			String breed, String dob, String country, String description){
		 
		JsonObject res = new JsonObject();
		
		try{
			connection = ConnectionManager.getConnection();

			String putPetSQL = "INSERT INTO pet(pet_id, user_id, pet_name, tag_id, gender, breed, dob, country, description "
					+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, CAST(? AS DATE), ?, ?);";

			queryPstmt = connection.prepareStatement(putPetSQL);
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
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		return res.toString();
	}
	
	
}
