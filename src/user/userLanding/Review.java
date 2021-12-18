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
		
		String query = "INSERT INTO LEAVEREVIEW VALUES('" + reviewId + "', '" + programId + "', '" + reviewString + "', SYSDATE)";
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
	public void addToWallet(String walletId, String programId, String reviewId, int points) {
		
		String query = "";
		String dateStr = Activity.activityDate;

		if(dateStr.equals("SYSDATE")) {
			query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + reviewId + "', " + "SYSDATE" + ", '"+ points + "', '" + null + "','LEAVEREVIEW')";
		} else {
			query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + reviewId + "', TO_DATE('" + dateStr + "', 'MM-DD-YYYY'), '"+ points + "', '" + null + "','LEAVEREVIEW')";
		}
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
	
	public String generateReviewId()
	{
		//String lpId="LP"+String.valueOf(lpIdCounter);
		conn = ConnectionObj.getConnection();
		ResultSet rs=null;
		Statement stmt=null;
		int max=0;
		String query="select REVIEWID from LEAVEREVIEW";
		String temp="";
		String pId="";
		String finalString="";
		String maxValueString="";
		try
		{
			stmt=conn.prepareStatement(query);
			rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				temp=rs.getString("REVIEWID");
				String tempSubs=temp.substring(1);
				max=Math.max(max,Integer.parseInt(tempSubs));
				

			}	
			max++;
			maxValueString=String.valueOf(max);
			finalString="R"+maxValueString;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return finalString;
	}
	
	
	
	

	
	public void display(user u, String programId, int points) {
			
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
			System.out.println("Please write a review");
			String reviewString = sc.nextLine();
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
					String reviewId = generateReviewId(); // should be unique for every review
					int multiplier = Multiplier.getMultiplier(walletId, programId);
					addToWallet(walletId, programId, reviewId, points*multiplier);
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
