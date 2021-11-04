package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Friend {
	
	static Connection conn = null;
	
	public Friend() {
		conn = ConnectionObj.getConnection();
	}

	public void addToReferFriend(String programId, String referId) {
		
		String query = "INSERT INTO PURCHASE VALUES('null','" + programId + "','null', '" + referId + "')";
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
	}
	public void addToWallet(String walletId, String programId, String referId) {
		String query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + referId + "', '" + null + "', '"+ null + "', '" + null + "','REFERFRIEND')";
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
	}
		
	public void display(user u, String programId) {
			
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
		
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Refer a friend");
			System.out.println("2. Go back");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					// add transaction to wallet table
					String walletId = u.getUserId();
					String referId = "RF1"; // should be unique for every review
					addToReferFriend(programId, referId);
					addToWallet(walletId, programId, referId);
					System.out.println("Thank you for Referring friend");
					display(u, programId);
					
					break;
				case 2:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
		}

}
