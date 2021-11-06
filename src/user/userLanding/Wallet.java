package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Wallet {
	
	static ResultSet rs=null;
	static Connection conn = null;
	
	public Wallet() {
		conn = ConnectionObj.getConnection();
	}
	
	public void getWalletDetails(String walletId) {
		
		String query = "SELECT * FROM WALLET_TRANSACTIONS WHERE WALLETID="+"'"+walletId+"'";
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
			System.out.println("WALLET ID | LOYALTY PROGRAM ID| ACTIVITY ID | ACTIVITY DATE | POINTS | RE RULE CODE | ACTIVITY NAME");
			while(rs.next()) {
				System.out.println(rs.getString("WALLETID")+" | "+rs.getString("LOYALTY_PROGRAM_ID")+" | "+rs.getString("ACTIVITY_ID")+" | "+rs.getString("ACTIVITY_DATE")+" | "+rs.getString("POINTS")+" | "+rs.getString("RE_RULE_CODE")+" | "+rs.getString("ACTIVITY_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void display(user u) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
			System.out.println("Wallet details");
			System.out.println(" ");
			
			// show wallet details for customer
			String walletId = u.getUserId();
			getWalletDetails(walletId);
			

			System.out.println("Please select from below menu:");
			System.out.println("1. Go back");
			int activityId = sc.nextInt();
			switch(activityId)
			{
				case 1:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
	}

}
