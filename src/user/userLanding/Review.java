package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Review {
	
static Connection conn = null;
	
	public Review() {
		conn = ConnectionObj.getConnection();
	}

	public void addToReview(String programId, String reviewId, String reviewString) {
		
		String query = "INSERT INTO LEAVEREVIEW VALUES('" + reviewId + "', '" + programId + "', '" + reviewString + "', null)";
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
	public void addToWallet(String walletId, String programId, String reviewId) {
		String query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + reviewId + "', '" + null + "', '"+ null + "', '" + null + "','LEAVEREVIEW')";
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
			System.out.println("Please write a review");
			String reviewString = sc.next();
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Leave a review");
			System.out.println("2. Go back");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					// add transaction to wallet table
					// addToWallet(u, programId, activityId, reviewString);
					
					String walletId = u.getUserId();
					String reviewId = "R1"; // should be unique for every review
					addToWallet(walletId, programId, reviewId);
					addToReview(programId, reviewId, reviewString);
					System.out.println("Thanks for the Review");
					
					Activity activity = new Activity();
					activity.display(u);
					
					break;
				case 2:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
		}

}
