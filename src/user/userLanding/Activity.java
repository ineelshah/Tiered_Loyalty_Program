package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Activity {
	
	static ResultSet rs=null;
	static Connection conn = null;
	
	public Activity() {
		conn = ConnectionObj.getConnection();
	}
	
	public void getLoyaltyProgram(String userId) {
		
		String query = "SELECT DISTINCT LOYALTY_PROGRAM_ID FROM WALLET_TRANSACTIONS WHERE WALLETID="+"'"+userId+"'";
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
				System.out.println(rs.getString("LOYALTY_PROGRAM_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getActivityName(String activityId) {
		String activityName="";
		String query = "SELECT ACTIVITY_NAME FROM ACTIVITY WHERE ACTIVITY_CODE="+"'"+activityId+"'";
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
				activityName = rs.getString("ACTIVITY_NAME");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activityName;
	}
	public void getActivity(String programId) {
		
		String query = "SELECT DISTINCT ACTIVITYID FROM LP_ACTIVITY WHERE PROGRAMID="+"'"+programId+"'";
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
				System.out.println("HELLO");
				String activityId = rs.getString("ACTIVITYID");
				String activityName = getActivityName(activityId);
				System.out.println(activityId+'.'+activityName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void display(user u) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select loyalty program id:");
		System.out.println(" ");
		String userId = u.getUserId();
		// show loyalty programs for customer
		getLoyaltyProgram(userId);
		
		String programId = sc.next();
		System.out.println("Please select activity id:");
		
		// show activities for selected loyalty program
		getActivity(programId);
		
		int activityId = sc.nextInt();
		switch(activityId)
		{
			case 1:
				//purchase
				System.out.println("Purchase");
				Purchase purchase = new Purchase();
				purchase.display(u, programId);
				
				break;
			case 2:
				//leave review
				System.out.println("Review");
				Review review = new Review();
				review.display(u, programId);
				
				break;
			case 3:
				//refer friend
				System.out.println("Friend");
				Friend friend = new Friend();
//<<<<<<< HEAD
				//friend.display(u, programId, activityId);
//=======
				friend.display(u, String.valueOf(programId));
//>>>>>>> a30bbddc5783b75fcedd5367226f68ecf222db4b
				
				break;
			case 4:
				userLanding uLanding = new userLanding();
				uLanding.display(u);
				break;
		}
		
		
		
		
	}

}
