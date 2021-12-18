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
		
		String query2 = "SELECT LP.PROGRAMNAME, W.POINTS FROM WALLET W LEFT JOIN LOYALTYPROGRAM LP ON W.PROGRAMID = LP.PROGRAMID WHERE WALLETID = '" + walletId + "'";
		
		
		ResultSet rs2=null;
		Statement stmt2 = null;
		try {
			stmt2 = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs2=stmt2.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			while(rs2.next()) {
				System.out.println("Total Points in Wallet: Loyalty Program: " + rs2.getString("PROGRAMNAME") + ": " + rs2.getString("POINTS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("____________________________________________________________________________________");
		
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

		System.out.println("____________________________________________________________________________________");
		
		
		
		
		String queryReward = "SELECT WALLETID, PROGRAMID, REWARDID, QUANTITY, POINTS, TRANSACTION_DATE FROM WALLET_REWARD_TRANSACTIONS WHERE WALLETID="+"'"+walletId+"'";
		Statement stmtR = null;
		ResultSet rsR = null;
		try {
			stmtR = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rsR=stmtR.executeQuery(queryReward);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			System.out.println("WALLET ID | LOYALTY PROGRAM ID| REWARD ID | QUANTITY | POINTS | TRANSACTION DATE");
			while(rsR.next()) {
				System.out.println(rsR.getString("WALLETID")+" | "+rsR.getString("PROGRAMID")+" | "+rsR.getString("REWARDID")+" | "+rsR.getInt("QUANTITY")+" | "+rsR.getInt("POINTS")+" | "+rsR.getDate("TRANSACTION_DATE"));
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
