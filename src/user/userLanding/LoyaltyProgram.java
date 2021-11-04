package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;
public class LoyaltyProgram {
	
	static ResultSet rs=null;
	static Connection conn = null;
	
	public LoyaltyProgram() {
		conn = ConnectionObj.getConnection();
	}
	
	public void getLoyaltyProgram() {
		
		String query = "SELECT DISTINCT PROGRAMID FROM LOYALTYPROGRAM";
		Statement stmt = null;
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
		try {
			while(rs.next()) {
				System.out.println(rs.getString("PROGRAMID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void enrollInLoyaltyProgram(String programId, String walletId){
		
		String activityId = "0";
		String points = "0";
		
		String query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "', '" + programId + "', '" + activityId + "', '"+ null + "', '" + points + "', '" + null + "', 'JOIN')";
		Statement stmt = null;
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
	}
	
	public void display(user u) {
		

		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select id to enroll in loyalty program:");		
		getLoyaltyProgram();
		String programId = sc.next();
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Enroll in Loyalty Program");
		System.out.println("2. Go back");
		int choice = sc.nextInt();
		userLanding uLanding = new userLanding();
		switch(choice)
		{
			case 1:
				String walletId = u.getUserId();
				enrollInLoyaltyProgram(programId, walletId);
				System.out.println("successfully added");
				uLanding.display(u);	
				break;
			case 2:		
				uLanding.display(u);
				break;
		}
		
		
		
		
	}

}
