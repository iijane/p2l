package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	@Path("/retrieveUserByEmail")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveUserByEmail(@QueryParam("email") String email){
		 
		JsonObject userJsonObj = new JsonObject();
		
		try{
			connection = ConnectionManager.getConnection();

			String retrieveUserSQL = "select * from user where email = '" + email + "';";	
			queryPstmt = connection.prepareStatement(retrieveUserSQL);

			ResultSet rs = queryPstmt.executeQuery();

			while(rs.next()){
				userJsonObj.addProperty("userID", rs.getInt("userID"));
				userJsonObj.addProperty("firstName", rs.getString("firstName"));
				userJsonObj.addProperty("lastName", rs.getString("lastName"));
				userJsonObj.addProperty("email", rs.getString("email"));
				userJsonObj.addProperty("password", rs.getString("password"));
				userJsonObj.addProperty("dob", rs.getString("dob"));
			}
		}catch (SQLException ex) {
			userJsonObj.addProperty("errorMsg", "There has been a problem accessing the database. Please try again later");
		} finally {
			ConnectionManager.close(connection, queryPstmt, rs);
		}
		return userJsonObj.toString();
	}
}
