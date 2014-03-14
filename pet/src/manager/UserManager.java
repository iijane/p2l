package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;

import database.ConnectionManager;

@Path("/UserManager")
public class UserManager {
	
	private Connection connection = null;
	//private Statement statement = null;
	private ResultSet rs = null;
	private PreparedStatement queryPstmt = null;
	
	@GET
	@Path("/retrieveUserValidationByEmail")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveUserByEmail(@QueryParam("email") String email){
		 
		JsonObject userJsonObj = new JsonObject();
		
		try{
			connection = ConnectionManager.getConnection();

			String retrieveUserSQL = "select user_id, first_name, email, password from \"user\" where email = '" + email + "';";	
			queryPstmt = connection.prepareStatement(retrieveUserSQL);

			ResultSet rs = queryPstmt.executeQuery();

			while(rs.next()){
				userJsonObj.addProperty("user_id", rs.getInt("user_id"));
				userJsonObj.addProperty("first_name", rs.getString("first_name"));
				userJsonObj.addProperty("email", rs.getString("email"));
				userJsonObj.addProperty("password", rs.getString("password"));
			}
		}catch (SQLException ex) {
			userJsonObj.addProperty("errorMsg", "There has been a problem accessing the database. Please try again later.");
			userJsonObj.addProperty("error",  ex.getMessage());
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		return userJsonObj.toString();
	}
	
	@GET
	@Path("/retrieveUserProfileByUserID")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveUserProfileByUserID(@QueryParam("userId") String userId){
		 
		JsonObject userJsonObj = new JsonObject();

		try{
			connection = ConnectionManager.getConnection();

			String retrieveUserSQL = "select *, to_char(dob, 'dd/mm/yyyy') as dob2 from \"user\" where user_id = " + userId + ";";	
			queryPstmt = connection.prepareStatement(retrieveUserSQL);

			ResultSet rs = queryPstmt.executeQuery();

			while(rs.next()){
				userJsonObj.addProperty("user_id", rs.getInt("user_id"));
				userJsonObj.addProperty("first_name", rs.getString("first_name"));
				userJsonObj.addProperty("last_name", rs.getString("last_name"));
				userJsonObj.addProperty("gender", rs.getString("gender"));
				userJsonObj.addProperty("email", rs.getString("email"));
				userJsonObj.addProperty("dob", rs.getString("dob2"));
				userJsonObj.addProperty("country", rs.getString("country"));
				userJsonObj.addProperty("state", rs.getString("state"));
				userJsonObj.addProperty("city", rs.getString("city"));
				userJsonObj.addProperty("address", rs.getString("address"));
				userJsonObj.addProperty("postal_code", rs.getString("postal_code"));
				userJsonObj.addProperty("country_code", rs.getString("country_code"));
				userJsonObj.addProperty("mobile_number", rs.getInt("mobile_number"));
				userJsonObj.addProperty("token", rs.getString("token"));
			}
		}catch (SQLException ex) {
			userJsonObj.addProperty("errorMsg", "There has been a problem accessing the database. Please try again later.");
			userJsonObj.addProperty("error",  ex.getMessage());
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		return userJsonObj.toString();
	}
	
	@GET
	@Path("/retrieveUserIdByEmail")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveUserIdByEmail(@QueryParam("email") String email){
		JsonObject userJsonObj = new JsonObject();
		
		try{
			connection = ConnectionManager.getConnection();

			String retrieveUserSQL = "select user_id from \"user\" where email = '" + email + "';";	
			queryPstmt = connection.prepareStatement(retrieveUserSQL);

			ResultSet rs = queryPstmt.executeQuery();
			
			if(rs!=null && rs.next()){
				userJsonObj.addProperty("user_id", rs.getInt("user_id"));
	        }
			
		}catch (SQLException ex) {
			userJsonObj.addProperty("errorMsg", "There has been a problem accessing the database. Please try again later.");
			userJsonObj.addProperty("error",  ex.getMessage());
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		return userJsonObj.toString();
	}
	
}
