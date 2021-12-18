package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionObj;

public class Multiplier {
	
	static Connection conn = null;
	
	public Multiplier() {
		conn = ConnectionObj.getConnection();
	}
	
	public static int getMultiplier(String custId, String programId) {

		conn = ConnectionObj.getConnection();
		String query = "SELECT MULTIPLIER FROM CUSTOMER_LP_MASTER WHERE CUSTOMERID = '" + custId + "' AND LPID = '" + programId + "'";
		Statement stmt = null;
		ResultSet rs=null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String mult = "1";
		try { 
			while(rs.next()) {
				mult = rs.getString("MULTIPLIER");
			}
		} catch (Exception e) {
			
		}
		return Integer.valueOf(mult);
	}
}