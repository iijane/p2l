package writemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

import database.ConnectionManager;

public class WriteUserManager {
	
	private Connection connection = null;
	//private Statement statement = null;
	private ResultSet rs = null;
	private PreparedStatement queryPstmt = null;

	public String newUser(String firstName, String lastName, String email, String password,
			String gender, String dob, String countries, String state, String city,
			String address, String postal, String countrycode, String mobile){
		 
		JsonObject res = new JsonObject();
		
		try{
			connection = ConnectionManager.getConnection();

			String putPetSQL = "insert into \"user\"(first_name, last_name, gender, email, password, dob, country, state, city, address, postal_code, country_code, mobile_number, token)" +
					"VALUES (?, ?, ?, ?, ?, CAST(? AS DATE), ?, ?, ?, ?, ?, ?, ?, 0);";

			queryPstmt = connection.prepareStatement(putPetSQL);
			queryPstmt.setString(1, firstName);
			queryPstmt.setString(2, lastName);
			queryPstmt.setString(3, gender);
			queryPstmt.setString(4, email);
			queryPstmt.setString(5, password);
			queryPstmt.setString(6, dob);
			queryPstmt.setString(7, countries);
			queryPstmt.setString(8, state);
			queryPstmt.setString(9, city);
			queryPstmt.setString(10, address);
			queryPstmt.setString(11, postal);
			queryPstmt.setString(12, countrycode);
			queryPstmt.setString(13, mobile);

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
