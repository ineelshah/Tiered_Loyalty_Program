package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;
public class LoyaltyProgram {
	
	static ResultSet rs=null;
	static Connection conn = null;
	
	public LoyaltyProgram() {
		conn = ConnectionObj.getConnection();
	}
	
	public HashMap<Integer, String> getLoyaltyProgram() {
		
		String query = "SELECT DISTINCT PROGRAMID, PROGRAMNAME FROM LOYALTYPROGRAM";
		Statement stmt = null;
		int count = 1;
		HashMap<Integer, String> hmap = new HashMap<>();
		
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
				hmap.put(count, rs.getString("PROGRAMID"));
				System.out.println(count + ". " + rs.getString("PROGRAMNAME"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(count + ". Exit");
		return hmap;
	}
	
	public void enrollInLoyaltyProgram(String programId, String walletId){
		
		String activityId = "A0";
		String points = "0";
		
		String query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "', '" + programId + "', '" + activityId + "', "+ "SYSDATE" + ", '" + points + "', '" + null + "','JOIN')";
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
		String tierId = "T1";
		String insertIntoMaster = "INSERT INTO CUSTOMER_LP_MASTER (CUSTOMERID, LPID, TIERID, MULTIPLIER) VALUES ('" + walletId + "', '" + programId + "', '" + tierId + "', '1')";
		stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(insertIntoMaster);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("This customer has been enrolled in this program already. Please choose some other option.");
		} 
	}
	
	public void display(user u) {
		

		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select id to enroll in loyalty program:");		
		HashMap<Integer, String> hmap = getLoyaltyProgram();
		int programIdInt = sc.nextInt();
		String programId = hmap.get(programIdInt);
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
